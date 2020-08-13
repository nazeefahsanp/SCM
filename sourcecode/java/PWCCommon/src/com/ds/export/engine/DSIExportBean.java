package com.ds.export.engine;

import java.util.List;
import java.util.Map;

public interface DSIExportBean 
{
	public static final String LEVEL_RELATIVE_TO_ROOT_EXPAND = "_level_relative_to_root_expand";
	public static final String OBJECT_ID = "_object_id";
	public static final String RELATIONSHIP_ID = "_connection_id";
	
	/**
	 * 
	 * @return the selectables, key=xml tag name; value = the value of the selectable
	 */
	public Map<String, Object> getSelectables();
	
	/**
	 * 
	 * @param iSel = the map of the selectables for this bean
	 */
	public void setSelectables(Map<String, Object> iSel);
	
	/**
	 * 
	 * @param iSelToPropagate = the pairs key, value to be set on this bean and all children (all levels under it)
	 * @param iOverrideOnlyExisting = flag indicating either put the value only if the key exists in _selectables Map or put key, value regardless of the key existence
	 * 									
	 */
	public void propagateSelectables(Map<String, Object> iSelToPropagate, boolean iOverrideOnlyExisting);
	
	/**
	 * 
	 * @return the list of child beans according to the xml query template structure
	 */
	public List<DSIExportBean> getBeans();
	
	/**
	 * 
	 * @param iRelativeLevel = the nb of levels to go up
	 * @return the parent bean at the specified level
	 * @throws Exception
	 */
	public DSIExportBean getParent(short iRelativeLevel) throws Exception;
	
	/**
	 * 
	 * @return return the direct bean parent. Same as getParentBean(1)
	 */
	public DSIExportBean getParent();
	
	/**
	 * 
	 * @param iParent = the parent of this bean
	 */
	public void setParent(DSIExportBean iParent);
	
	/**
	 * Remove this bean from the parent list
	 */
	public void discardFromStructure();
	
	/**
	 * 
	 * @param iParent = the new parent of this bean
	 * Move this bean from the current parent to the input parent
	 */
	public void move(DSIExportBean iParent);
	
	
}
