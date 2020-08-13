package com.ds.export.engine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import matrix.db.BusinessObject;
import matrix.db.BusinessObjectWithSelect;
import matrix.db.BusinessObjectWithSelectList;
import matrix.db.Context;
import matrix.db.ErrorObject;
import matrix.db.Relationship;
import matrix.db.RelationshipWithSelect;
import matrix.db.RelationshipWithSelectList;
import matrix.util.StringList;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.eMatrixDateFormat;
import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.DomainObject;

public class DSExportBeanCreator 
{
	private static final Logger LOG = Logger.getLogger("DSExportBeanCreator");
	private static final String ATTR_DATEFORMAT = "dateFormat";
	private static final String ATTR_CHECKER = "checker";
	private static final String ATTR_TYPE = "type";
	private static final String ATTR_MULTIVALUE = "multivalue";
	private static final String ATTR_ISDATE = "isDate";
	private static final String ATTR_NAME = "name";
	private static final String ATTR_MANDATORY = "mandatory";
	private static final String ATTR_FROM = "from";
	private static final String ATTR_RECURSIVE = "recursive";
	private static final String NODE_SELECTABLES = "selectables";
	private static final String NODE_SELECT = "select";
	private static final String NODE_OBJECT = "object";
	private static final String NODE_RELATIONSHIP = "relationship";
	private static final String BEAN_CLASS = "com.ds.export.engine.DSExportBean";
	
	private String _templateName;
	private String _oID;
	private Context _context;
	//private String _checkerClass;
	private Element _el_export;
	private DateFormat _dateFormat;
	private Map<String, Object> _jpoArgs;
	public DSExportBeanCreator(Context iContext, String iTemplateName, String iOid, Map<String, Object> iArgs) throws Exception
	{
		LOG.info("--> IN DSExportBeanCreator");
		if(null == iContext)//just making sure!
		{
			LOG.error("The Enovia context is null!!");
			throw new Exception("The Enovia context is null!!");
		}
		_context = iContext;
		
		if(null == iTemplateName || iTemplateName.length() == 0)
		{
			LOG.error("The template name is not provided");
			throw new Exception("The template name is not provided");
		}
		_templateName = iTemplateName;
		
		if(null == iOid || iOid.length() == 0)
		{
			LOG.error("The input object ID name is not provided");
			throw new Exception("The input object ID name is not provided");
		}
		_oID = iOid;
				
		if(null != iArgs)
		{
			_jpoArgs = iArgs;
		}
		else
		{
			_jpoArgs = new Hashtable<String, Object>();
		}
		Document xmlDoc = DSSymbolicNamesEvaluator.getXMLDocument(_templateName);
		_el_export = xmlDoc.getDocumentElement();
		String st_dateFormat = _el_export.getAttribute(ATTR_DATEFORMAT);
		if(null == st_dateFormat || st_dateFormat.isEmpty())
		{
			_dateFormat = new SimpleDateFormat(eMatrixDateFormat.getEMatrixDateFormat());
		}
		else
		{
			try
			{
				_dateFormat = new SimpleDateFormat(st_dateFormat);
			}
			catch (IllegalArgumentException iaex)
			{
				LOG.error("The export date format pattern: [" + st_dateFormat + "] is invalid");
				throw new Exception("The pattern date format: [" + st_dateFormat + "] is invalid");
			}
		}
		LOG.info("<-- OUT DSExportBeanCreator");
	}
	
	private Node getFirstNamedChild(Node iXMLNode, String iName)
	{
		LOG.info("--> IN getFirstNamedChild");
		Node child = null;
		NodeList list = iXMLNode.getChildNodes();
		for (int i = 0; i < list.getLength(); i++)
		{
			LOG.debug("Node @ [" + i + "] is named: [" + list.item(i).getNodeName() + "]");
			if(list.item(i).getNodeName().equals(iName))
			{
				child = list.item(i);
				break;
			}
		}
		LOG.info("<-- OUT getFirstNamedChild");
		return child;
	}
	public DSIExportBean create() throws Exception
	{
		LOG.info("--> IN create()");
		BusinessObject bo = new BusinessObject(_oID);
		if(!bo.exists(_context))
		{
			LOG.error("The input object ID is not valid");
			throw new Exception("The input object ID is not valid");
		}
		Node nd_ObjectRoot = getFirstNamedChild(_el_export, NODE_OBJECT);
		LOG.debug("export child node name: [" + nd_ObjectRoot.getNodeName() + "]");
		if(null == nd_ObjectRoot)
		{
			LOG.error("Invalid XML Export Template");
			throw new Exception ("Invalid XML Export Template");
		}
		LOG.info("<-- OUT create()");
		return executeXMLTemplate(bo, nd_ObjectRoot);
		
	}
	
	private DSIExportBean executeXMLTemplate(BusinessObject iObjectRoot,  Node iXMLNode) throws Exception
	{
		LOG.info("--> IN executeXMLTemplate");
		String st_attrObjectType = ((Element)iXMLNode).getAttribute(ATTR_TYPE);
		// THC - 20111003 - using the "getObjectId" method to prevent a NullPointerExpceiton if the super user context
		//       was pushed and someone called the "exists" method on the BusinessObject.
		DomainObject do_Obj = DomainObject.newInstance(_context, iObjectRoot.getObjectId());
		
		if(!do_Obj.isKindOf(_context, st_attrObjectType))
		{
			String bo_type = do_Obj.getInfo(_context, DomainConstants.SELECT_TYPE);
			LOG.error("The input object type: [" + bo_type + "] is not expected. Expected types: [" + st_attrObjectType + "] or sub-types of it");
			throw new Exception("The input object type: [" + bo_type + "] is not expected. Expected types: [" + st_attrObjectType + "] or sub-types of it");
		}
		NodeList lst_nodes = iXMLNode.getChildNodes();
		DSIExportBean rootBean = null;
		int cnt_nodes = lst_nodes.getLength();
		if(cnt_nodes > 0)
		{
			rootBean = instantiateBean();
			Node firstNode = getFirstNamedChild(iXMLNode, NODE_SELECTABLES);
			if(null != firstNode)//this should be the first node and unique
			{
				getResultsForSelectablesTag(iObjectRoot, firstNode, rootBean);
				//if a checker class is declared for root object tag we check the result - 
				//it's the only place we check only the object's bean
				//from this point on the check is performed only for the relationship's beans 
				//which includes object's selectables 
				String checkerClass = ((Element)iXMLNode).getAttribute(ATTR_CHECKER);
				if(null != checkerClass && checkerClass.length() != 0)
				{
					if(!instantiateChecker(checkerClass).check(_context, iObjectRoot, rootBean, _jpoArgs));
					{
						//SEND NULL
						return null;//means we skip the whole export
					}
				}
			}
			else
			{
				//throw new Exception("The XML template is invalid");
				LOG.info("No selectables for this node: [" + iXMLNode.getNodeName() + "]");
			}
			for(int i = 0; i < cnt_nodes; i++)
			{
				Node crt_node = lst_nodes.item(i);
				if(crt_node.getNodeName().equals(NODE_RELATIONSHIP))
				{
					getResultsForRelationshipTag(iObjectRoot, crt_node, rootBean);//this is recursive (calling itself)
				}
			}
		}
		if(LOG.isDebugEnabled())
		{
			printBean(rootBean, 0);
		}
		LOG.info("<-- OUT executeXMLTemplate");
		return rootBean;
	}
	
	private void printBean(DSIExportBean iBean, int iIndentLevel)
	{
		Map<String, Object> map_select = iBean.getSelectables();
		Iterator<String> itr_keys = map_select.keySet().iterator();
		StringBuilder st_indent = new StringBuilder();
		for (int ind = 0; ind < iIndentLevel; ind++)
		{
			st_indent.append("\t");
		}
		LOG.debug(st_indent + "=================================================================================");
		while(itr_keys.hasNext())
		{
			String crt_key = (String)itr_keys.next();
			LOG.debug(st_indent + "[" + crt_key + "] = [" + map_select.get(crt_key) + "]");
		}
		LOG.debug(st_indent + "=================================================================================");
		List<DSIExportBean> lst_beans = iBean.getBeans();
		iIndentLevel++;
		for(int i = 0; i < lst_beans.size(); i++)
		{
			printBean(lst_beans.get(i), iIndentLevel);
		}
	}
	public static DSIExportBean instantiateBean() throws Exception
	{
		return (DSIExportBean)Class.forName(BEAN_CLASS).newInstance();
	}
	
	private DSIExportChecker instantiateChecker(String iClass) throws Exception
	{
		return (DSIExportChecker)Class.forName(iClass).newInstance();
	}
	
	private DSIExportValueProvider instantiateClassSelect(String iClass) throws Exception
	{
		return (DSIExportValueProvider)Class.forName(iClass).newInstance();
	}
	
	private DSIExportRecursionLevelProvider instantiateRecursiveLevelProvider(String iClass) throws Exception
	{
		return (DSIExportRecursionLevelProvider)Class.forName(iClass).newInstance();
	}
	
	@SuppressWarnings("unchecked")
	private void getResultsForRelationshipTag(BusinessObject iObject, Node iXMLNode, DSIExportBean ioBean) throws Exception
	{
		LOG.info("--> IN getResultsForRelationshipTag");
		//start node = relationship
		String st_attrTypeRel = ((Element)iXMLNode).getAttribute(ATTR_TYPE);
		if(null == st_attrTypeRel || st_attrTypeRel.length() == 0)
		{
			LOG.error("The relationship type attribute is not specified");
			throw new Exception("The relationship type attribute is not specified");
		}
		String attrFrom = ((Element)iXMLNode).getAttribute(ATTR_FROM);
		if(null == attrFrom || attrFrom.length() == 0)
		{
			LOG.error("The relationship from attribute is not specified");
			throw new Exception("The relationship from attribute is not specified");
		}
		boolean b_from = new Boolean(attrFrom).booleanValue();
		String attrMandatory = ((Element)iXMLNode).getAttribute(ATTR_MANDATORY);
		if(null == attrMandatory || attrMandatory.length() == 0)
		{
			attrMandatory = "false";
		}
		boolean b_mandatory = new Boolean(attrMandatory).booleanValue();
		
		String checkerClass = ((Element)iXMLNode).getAttribute(ATTR_CHECKER);
		boolean b_check = true;
		if(null == checkerClass || checkerClass.isEmpty())
		{
			b_check = false;
		}
		else
		{
			if(checkerClass.charAt(0) == '$' && checkerClass.length() > 1)
			{
				checkerClass = checkerClass.substring(1);
			}
		}
		
		short short_recursionLevel = -1;
		String st_recursionLevel = ((Element)iXMLNode).getAttribute(ATTR_RECURSIVE);
		if(null == st_recursionLevel || st_recursionLevel.isEmpty())
		{
			short_recursionLevel = 1;
		}
		else
		{
			if(st_recursionLevel.charAt(0) != '$')
			{
				short_recursionLevel = new Short(st_recursionLevel).shortValue();
				if(short_recursionLevel < 0)
				{
					//we do not accept direct input as a negative number
					LOG.error("The value of recursive attribute is negative: [" + short_recursionLevel + "]");
					throw new Exception ("The value of recursive attribute is negative: [" + short_recursionLevel + "]");
				}
			}
			else if(st_recursionLevel.length() > 1)
			{
				st_recursionLevel = st_recursionLevel.substring(1);
			}
			else
			{
				short_recursionLevel = 1;
			}
		}
		//down to selectables and object tags of the relationship
		NodeList lst_rel_nodes = iXMLNode.getChildNodes();
		int cnt_rel_nodes = lst_rel_nodes.getLength();
		
		StringList stLst_rel_selectables = new StringList();
		StringList stLst_rel_select_Names = new StringList();
		StringList stLst_rel_select_multivalues = new StringList();
		StringList stLst_rel_select_dates = new StringList();
		if(cnt_rel_nodes > 0)
		{
			Map<String, StringList> map_rel_ClassSelect = new Hashtable<String, StringList>();
			Node firstNode_rel = getFirstNamedChild(iXMLNode, NODE_SELECTABLES);
			if(null != firstNode_rel)//this should be the first node and unique
			{
				setListSelectables(firstNode_rel, stLst_rel_selectables, stLst_rel_select_Names, map_rel_ClassSelect, stLst_rel_select_multivalues, stLst_rel_select_dates);
			}
			else
			{
				//throw new Exception("The XML template is invalid");
				LOG.info("No selectables for this node: [" + iXMLNode.getNodeName() + "]");
			}
			for(int i = 0; i < cnt_rel_nodes; i++)//for the object tags
			{
				Node crt_rel_node = lst_rel_nodes.item(i);
				
				StringList stLst_obj_selectables = new StringList();
				StringList stLst_obj_select_Names = new StringList();
				StringList stLst_obj_select_multivalues = new StringList();
				StringList stLst_obj_select_dates = new StringList();
				String st_attrTypeObj = "";
				String objWhere = null;
				String relWhere = null;
				if(crt_rel_node.getNodeName().equals(NODE_OBJECT))//multiple tags
				{
					st_attrTypeObj = ((Element)crt_rel_node).getAttribute(ATTR_TYPE);
					if(short_recursionLevel < 0)//it's initialized to -1
					{
						Map <String, Object> map_args = new Hashtable<String, Object>();
						map_args.put(DSIExportRecursionLevelProvider.RELATIONSHIP_TYPE, st_attrTypeRel);
						map_args.put(DSIExportRecursionLevelProvider.RELATIONSHIP_FROM, Boolean.toString(b_from));
						map_args.put(DSIExportRecursionLevelProvider.RELATIONSHIP_SECOND_END_OBJECT_TYPE, st_attrTypeObj);
						map_args.putAll(_jpoArgs);
						short_recursionLevel = instantiateRecursiveLevelProvider(st_recursionLevel).getRecursionLevel(_context, iObject, map_args);
						if(short_recursionLevel < 0)
						{
							LOG.warn("The value of the recursive attribute is negative: [" + short_recursionLevel + "]...we do not work on it");
							continue;
						}
					}
					NodeList lst_obj_child_nodes = crt_rel_node.getChildNodes();//selectables and/or relationship tags
					int cnt_obj_child_nodes = lst_obj_child_nodes.getLength();
					if(cnt_obj_child_nodes > 0)
					{
						Map<String, StringList> map_obj_ClassSelect = new Hashtable<String, StringList>();
						Node firstNode_obj = getFirstNamedChild(crt_rel_node, NODE_SELECTABLES);//<object><selectables>
						if(null != firstNode_obj)//this should be the first node and unique
						{
							setListSelectables(firstNode_obj, stLst_obj_selectables, stLst_obj_select_Names, map_obj_ClassSelect, stLst_obj_select_multivalues, stLst_obj_select_dates);
						}
						else
						{
							LOG.info("No selectables for this node: [" + crt_rel_node.getNodeName() + "]");
						}
						//adding the IDs
						//stLst_obj_selectables.add(DomainConstants.SELECT_ID);
						//stLst_rel_selectables.add(DomainConstants.SELECT_RELATIONSHIP_ID);

						// THC - 20111003 - using the "getObjectId" method to prevent a NullPointerExpceiton if the super user context
						//       was pushed and someone called the "exists" method on the BusinessObject.
						DomainObject domainObj = DomainObject.newInstance(_context, iObject.getObjectId());
						MapList mpl_results = domainObj.getRelatedObjects(_context, 
																			st_attrTypeRel, st_attrTypeObj, 
																			stLst_obj_selectables, stLst_rel_selectables, 
																			!b_from, b_from, 
																			short_recursionLevel, 
																			objWhere, relWhere, 
																			(short)0);
						//removing the IDs
						//stLst_obj_selectables.remove(DomainConstants.SELECT_ID);
						//stLst_rel_selectables.remove(DomainConstants.SELECT_RELATIONSHIP_ID);
						
						if(null == mpl_results || mpl_results.size() == 0)
						{
							LOG.info("We do not find any relationship: [" + st_attrTypeRel + "]");
							if (b_mandatory)
							{
								LOG.error("This relationship is mandatory...we abort the export");
								throw new Exception("We do not find any mandatory relationship: [" + st_attrTypeRel + "]");
							}
							else
							{
								LOG.error("This relationship is not mandatory...continue");
							}
						}
						LOG.debug("We found: [" + mpl_results.size() + "] results");
		
						Iterator itr_results = mpl_results.iterator();
						DSIExportBean crt_parent_bean = ioBean;
						DSIExportBean crt_child_bean = null;
						short short_obj_level = 1;
						short short_crt_obj_level = 1;
						boolean b_resultCheck = true;
						while(itr_results.hasNext())
						{
							short_obj_level = short_crt_obj_level;
							
							Map result = (Map)itr_results.next();
							
							String st_obj_level = (String)result.get(DomainConstants.SELECT_LEVEL);
							short_crt_obj_level = new Short(st_obj_level).shortValue();
							if(short_obj_level < short_crt_obj_level)
							{
								
								LOG.debug("The level diff is: [" + (short_obj_level - short_crt_obj_level) + "]");
								if(!b_resultCheck)////we go down only if the parent is OK
								{
									//it means we are on the same branch and the parent's checker is KO
									//restore the level before
									LOG.debug("checker KO: we are not going down to level:[" + short_crt_obj_level + "]...staying on the last level: [" + short_obj_level + "]");
									short_crt_obj_level = short_obj_level;
									continue;
								}
								else
								{
									LOG.debug("we are going down...");
									crt_parent_bean = crt_child_bean;
								}
							}
							else if(short_obj_level > short_crt_obj_level)
							{
								
								LOG.debug("The level diff is: [" + (short_obj_level - short_crt_obj_level) + "]");
								LOG.debug("we are going up...");
								if( b_resultCheck )
								{
									crt_parent_bean = crt_child_bean.getParent((short)(short_obj_level - short_crt_obj_level + 1));
								}
								else
								{
									crt_parent_bean = crt_parent_bean.getParent((short)(short_obj_level - short_crt_obj_level));
								}
							}
							else
							{
								LOG.debug("we are staying on the same level:[" + short_obj_level + "]");
								//do nothing, this object is on the same level as the object before
							}
							
							String rel_id = (String)result.get(DomainConstants.SELECT_RELATIONSHIP_ID);
							LOG.debug("rel_id: [" + rel_id + "]");
							Relationship rel = new Relationship(rel_id);
							
							String obj_id = (String)result.get(DomainConstants.SELECT_ID);
							LOG.debug("obj_id: [" + obj_id + "]");
							BusinessObject bo_child = new BusinessObject(obj_id);
							

							Map<String, Object> map_result = new Hashtable<String, Object>();
							//it may be usefull to have the level in the selectables
							map_result.put(DSIExportBean.LEVEL_RELATIVE_TO_ROOT_EXPAND, st_obj_level);
							//for rel
							populateMapResultFromQuery(result, stLst_rel_selectables, stLst_rel_select_Names, stLst_rel_select_multivalues, stLst_rel_select_dates, map_result);
							populateMapResultsFromClassSelectable(rel, map_rel_ClassSelect, stLst_rel_select_multivalues, stLst_rel_select_dates, map_result);
							
							//for obj
							populateMapResultFromQuery(result, stLst_obj_selectables, stLst_obj_select_Names, stLst_obj_select_multivalues, stLst_rel_select_dates, map_result);
							populateMapResultsFromClassSelectable(bo_child, map_obj_ClassSelect, stLst_obj_select_multivalues, stLst_rel_select_dates, map_result);
							
							//set map_result to the current bean
							crt_child_bean = instantiateBean();//relationship + object
							crt_child_bean.setSelectables(map_result);
							//set the potential parent for the current bean 
							//add the child in the beans' list of the parent 
							//if checker KO (see below)the crt_child_bean will be removed from the the beans' list of the parent.
							//we set the parent just for being able to navigate up from this crt_child_bean.
							//having already set the parent may be useful in the checker implementation for upper structure navigation
							crt_child_bean.setParent(crt_parent_bean);
							//boolean b_resultCheck = true;
							if(b_check)
							{
								//instantiate the checker for the current bean - each relationship and the objects under it represents a bean
								DSIExportChecker crt_checker = instantiateChecker(checkerClass);
								//if the checker throws exception we abort the export
								b_resultCheck = crt_checker.check(_context,rel, crt_child_bean, _jpoArgs);
							}
							
							if(b_resultCheck)
							{
								for(int j = 0; j < cnt_obj_child_nodes; j++)
								{
									Node crt_obj_child_node = lst_obj_child_nodes.item(j);
									if(crt_obj_child_node.getNodeName().equals(NODE_RELATIONSHIP))
									{
										getResultsForRelationshipTag(bo_child, crt_obj_child_node, crt_child_bean);
									}
								}
							}
							else//if the checker returns false we skip the export for this bean and its children
							{
								//remove the child bean from the parent list
								crt_child_bean.discardFromStructure();
							}
						}//while we have query results
					}//if child object tag has children
				}//if child is object tag
				else
				{
					//do nothing
				}
			}//for each child tag of this relationship tag
		}//if the relationship tag has children
		LOG.info("<-- OUT getResultsForRelationshipTag");
	}
	@SuppressWarnings("unchecked")
	private void populateMapResultFromQuery(Map iQueryMap, StringList iSelectables,  StringList iSelect_Names, StringList iSelect_multivalues, StringList iSelect_dates, Map<String, Object> oMapResult) throws Exception
	{
		LOG.info("--> IN populateMapResultFromQuery");
		int cnt_select = iSelectables.size();
		for(int k = 0; k < cnt_select; k++)
		{
			String key = (String)iSelectables.get(k);
			String key_name = (String)iSelect_Names.get(k);
			Object entry_value = iQueryMap.get(key);
			StringList stLst_value = null;
			if(null != entry_value)
			{
				LOG.debug("The selectable:[" + key_name + "] = [" + entry_value + "]...");
				if(entry_value instanceof String)
				{
					LOG.debug("...is of type String");
					stLst_value = new StringList((String)entry_value);
				}
				else if(entry_value instanceof StringList)
				{
					LOG.debug("...is of type StringList");
					stLst_value = (StringList)entry_value;
				}
				else
				{
					LOG.error("...is of type: [" + entry_value.getClass().getName() + "]");
					throw new Exception ("The selectable:[" + key_name + "] = [" + entry_value + "] is of type: [" + entry_value.getClass().getName() + "]");
				}
			}
			addValuesToMapResult(stLst_value, key_name, iSelect_multivalues, iSelect_dates, oMapResult);
		}
		LOG.info("<-- OUT populateMapResultFromQuery");
	}
	
	/*
	 * oClassSelect key = xmlNodeValue, value = (stringList){xmlNodeName1, xmlNodeName2,...}
	 */
	@SuppressWarnings("unchecked")
	private void setListSelectables(Node iSelectables, StringList oSelectables,  StringList oSelect_Names, Map<String, StringList> oClassSelect, StringList oSelect_multivalues, StringList oSelect_Dates) throws Exception
	{
		LOG.info("--> IN setListSelectables");
		String parentNodeName = iSelectables.getParentNode().getNodeName();
		if(NODE_OBJECT.equals(parentNodeName))
		{
			oSelectables.add(DomainConstants.SELECT_ID);
			oSelect_Names.add(DSIExportBean.OBJECT_ID);
		}
		else if(NODE_RELATIONSHIP.equals(parentNodeName))
		{
			oSelectables.add(DomainConstants.SELECT_RELATIONSHIP_ID);
			oSelect_Names.add(DSIExportBean.RELATIONSHIP_ID);
		}
		NodeList lst_selectables = iSelectables.getChildNodes();
		
		int cnt_selectables = lst_selectables.getLength();
		for(int j = 0; j < cnt_selectables; j++)
		{
			Node crt_selectable = lst_selectables.item(j);
			if(NODE_SELECT.equals(crt_selectable.getNodeName()))
			{
				Node nd_txt = crt_selectable.getFirstChild();
				if(null != nd_txt)
				{
					String st_selectValue = nd_txt.getNodeValue();
					String st_selectName = ((Element)crt_selectable).getAttribute(ATTR_NAME);
					
					if(st_selectName.startsWith("_"))
					{
						LOG.error("The selectable name: [" + st_selectName + "] must not start with '_', this being reserved for internal selectable names");
						throw new Exception ("The selectable name: [" + st_selectName + "] must not start with '_', this being reserved for internal selectable names");
					}
					if(st_selectValue.charAt(0) == '$')//we have a class select: $com.foo.bar.SelectProvider(must implement BHTIExportVlaueProvider)
					{
						st_selectValue = st_selectValue.substring(1);
						if(oClassSelect.containsKey(st_selectValue))
						{
							StringList lst_NodeNames = oClassSelect.get(st_selectValue);
							lst_NodeNames.add(st_selectName);
						}
						else
						{
							oClassSelect.put(st_selectValue, new StringList(st_selectName));
						}
					}
					else//we have query select: attribute[XYZ]
					{
						oSelectables.add(st_selectValue);
						oSelect_Names.add(st_selectName);
					}
					String crt_multivalue = ((Element)crt_selectable).getAttribute(ATTR_MULTIVALUE);
					if (null != crt_multivalue && crt_multivalue.equalsIgnoreCase("true"))
					{
						oSelect_multivalues.add(st_selectName);
					}
					String crt_date = ((Element)crt_selectable).getAttribute(ATTR_ISDATE);
					if (null != crt_date && crt_date.equalsIgnoreCase("true"))
					{
						oSelect_Dates.add(st_selectName);
					}
				}
			}
		}
		LOG.info("<-- OUT setListSelectables");
	}
	
	private void addValuesToMapResult(StringList iValues, String iSelectName, StringList iSelect_multivalues, StringList iSelect_dates, Map<String, Object> oMapResult) throws Exception
	{
		LOG.info("--> IN addValuesToMapResult");
		if(oMapResult.containsKey(iSelectName))
		{
			LOG.error("The XML atribute name's value : [" + iSelectName + "] exists for at least 2 select nodes...it has to be unique within the same bean");
			throw new Exception ("The XML atribute name's value : [" + iSelectName + "] exists for at least 2 select nodes...it has to be unique within the same bean");
		}
		StringList stLst_values = null;
		if(iSelect_dates.contains(iSelectName))
		{
			stLst_values = formatDates(iValues);
		}
		else
		{
			stLst_values = iValues;
		}
		if(null != stLst_values && !stLst_values.isEmpty())
		{
			if(iSelect_multivalues.contains(iSelectName))
			{
				oMapResult.put(iSelectName, stLst_values);
			}
			else
			{
				oMapResult.put(iSelectName, (String)stLst_values.get(0));
			}
		}
		else
		{
			if(iSelect_multivalues.contains(iSelectName))
			{
				oMapResult.put(iSelectName, new StringList(""));
			}
			else
			{
				oMapResult.put(iSelectName, "");
			}
		}
		LOG.info("<-- OUT addValuesToMapResult");
	}
	
	@SuppressWarnings("unchecked")
	private StringList formatDates(StringList iValueDates)
	{
		LOG.info("--> IN formatDates()");
		StringList formattedDates = new StringList();
		int cnt_valueDates = iValueDates.size();
		for(int i = 0; i < cnt_valueDates; i++)
		{
			String st_rawDate = (String)iValueDates.get(i);
			if(null != st_rawDate && !st_rawDate.isEmpty())
			{
			Date dt_rawDate = eMatrixDateFormat.getJavaDate(st_rawDate);
			formattedDates.add(_dateFormat.format(dt_rawDate));
		}
		}
		LOG.info("<-- OUT formatDates()");
		return formattedDates;
	}
	
	private void populateMapResultsFromClassSelectable(ErrorObject iObject, Map<String, StringList> iMapClassSelect, StringList iSelect_multivalues, StringList iSelect_dates, Map<String, Object> oMapResults) throws Exception
	{
		LOG.info("--> IN populateMapResultsFromClassSelectable");
		Set<String> keys = iMapClassSelect.keySet();
		Iterator<String> itr_keys = keys.iterator();
		while(itr_keys.hasNext())
		{
			String providerClassName = itr_keys.next();
			StringList lst_selectNames = iMapClassSelect.get(providerClassName);
			DSIExportValueProvider provider = instantiateClassSelect(providerClassName);
			Map<String, StringList> map_values = provider.getValues(_context,iObject, lst_selectNames, _jpoArgs);
			//populate the map_result
			if(null != map_values)
			{
				int cnt_selectNames = lst_selectNames.size();
				for(int idx = 0; idx < cnt_selectNames; idx++)
				{
					String st_selectName = (String)lst_selectNames.get(idx);
					StringList lst_value = map_values.get(st_selectName);
					addValuesToMapResult(lst_value, st_selectName, iSelect_multivalues, iSelect_dates, oMapResults);
				}
			}
			
		}
		LOG.info("<-- OUT populateMapResultsFromClassSelectable");
	}
	private void getResultsForSelectablesTag(ErrorObject iObject, Node iXMLNode, DSIExportBean ioRootBean) throws Exception
	{
		
		System.out.println("****************IN getResultsForSelectablesTag**************");
		
		LOG.info("--> IN getResultsForSelectablesTag");
		Map<String, Object> map_results = new Hashtable<String, Object>();
		
		//build the list of selectables
		Map<String, StringList> map_ClassSelect = new Hashtable<String, StringList>();
		StringList stLst_selectables = new StringList();
		StringList stLst_select_Names = new StringList();
		StringList stLst_select_multivalues = new StringList();
		StringList stLst_select_dates = new StringList();
		setListSelectables(iXMLNode, stLst_selectables,  stLst_select_Names, map_ClassSelect, stLst_select_multivalues, stLst_select_dates);
		
		//get selectable from provider class
		populateMapResultsFromClassSelectable(iObject, map_ClassSelect, stLst_select_multivalues, stLst_select_dates, map_results);
		BusinessObject bo = null;
		Relationship rel = null;
		if(iObject instanceof BusinessObject)
		{
			bo = (BusinessObject)iObject;
			String[] array_oID = {bo.getObjectId(_context)};
			BusinessObjectWithSelectList lst_bows = BusinessObject.getSelectBusinessObjectData(_context, array_oID , stLst_selectables);
			BusinessObjectWithSelect bows = null;
			if(lst_bows.size() > 0)
			{
				bows = lst_bows.getElement(0);
			}
			if(null == bows)
			{
				LOG.error("Failed in BusinessObject::getSelectBusinessObjectData()");
				throw new Exception("Failed in BusinessObject::getSelectBusinessObjectData()");
			}
			
			
			int cnt_keys = stLst_selectables.size();
			
			for (int i = 0; i < cnt_keys; i++)
			{
				StringList lst_crt_select_data = bows.getSelectDataList((String)stLst_selectables.get(i));
				String crt_selectName = (String)stLst_select_Names.get(i);
				addValuesToMapResult(lst_crt_select_data, crt_selectName, stLst_select_multivalues, stLst_select_dates, map_results);
			}
		}
		else if(iObject instanceof Relationship)
		{
			rel = (Relationship)iObject;
			String[] array_oID = {rel.getName()};
			RelationshipWithSelectList lst_relws = Relationship.getSelectRelationshipData(_context, array_oID , stLst_selectables);
			RelationshipWithSelect relws = null;
			if(lst_relws.size() > 0)
			{
				relws = (RelationshipWithSelect)lst_relws.get(0);
			}
			if(null == relws)
			{
				LOG.error("Failed in Relationship::getSelectRelationshipData()");
				throw new Exception("Failed in Relationship::getSelectRelationshipData()");
			}
			int cnt_keys = stLst_selectables.size();
			for (int i = 0; i < cnt_keys; i++)
			{
				StringList lst_crt_select_data = relws.getSelectDataList((String)stLst_selectables.get(i));
				String crt_selectName = (String)stLst_select_Names.get(i);
				addValuesToMapResult(lst_crt_select_data, crt_selectName, stLst_select_multivalues, stLst_select_dates, map_results);
			}
		}
		else
		{
			//do nothing
		}
		LOG.info("--> OUT getResultsForSelectablesTag");
		ioRootBean.setSelectables(map_results);
	}

}
