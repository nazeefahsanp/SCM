package com.ds.export.engine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import matrix.util.StringList;

public class DSExportBean implements DSIExportBean
{
	private Map<String, Object> _selectables;
	private List<DSIExportBean> _beans;
	private DSIExportBean _parent;
	
	/* constructor */
	public DSExportBean()
	{
		_selectables = new Hashtable<String, Object>();
		_beans = new ArrayList<DSIExportBean>();
		_parent = null;
	}
	
	/* getters and setters*/
	
	@Override
	/**
	 * @return Map the alive Map
	 */
	//TODO this should return a clone for being able to modify it only through setSelectables
	public Map<String, Object> getSelectables()
	{
		return _selectables;
	}
	
	@Override
	/**
	 * @param Map iSelectables = the Map to be set
	 */
	//TODO At least we should control the type (only String or StringList)
	//Better to normalize it only for a list of values (removing String)
	public void setSelectables(Map<String, Object> iSelectables)
	{
		_selectables = iSelectables;
	}
	
	@Override
	//We accept only String or StringList, the other types are not set to the _selectables Map
	public void propagateSelectables(Map<String, Object> iSelToPropagate, boolean iOverrideOnlyExisting)
	{
		if(null != iSelToPropagate && !iSelToPropagate.isEmpty())
		{
			Iterator<String> itr_keys= iSelToPropagate.keySet().iterator();
			while(itr_keys.hasNext())
			{
				String aKey = itr_keys.next();
				Object aValue = iSelToPropagate.get(aKey);
				if(null != aValue)
				{
					Object newValue = aValue instanceof StringList ? new StringList((StringList)aValue) : (aValue instanceof String ? aValue : null);
					if(null != newValue)
					{
						if(iOverrideOnlyExisting)
						{
							
							if(_selectables.containsKey(aKey))
							{
								_selectables.put(aKey, newValue);
							}
						}
						else
						{
							_selectables.put(aKey, newValue);
						}
					}
					else
					{
						itr_keys.remove();//remove undesired values
					}
				}
				else
				{
					itr_keys.remove();//remove undesired values
				}
			}
		
			int cnt_beans = _beans.size();
			for (int i = 0; i < cnt_beans; i++)
			{
				DSIExportBean aBean = _beans.get(i);
				if(null != aBean)
				{
					_beans.get(i).propagateSelectables(iSelToPropagate, iOverrideOnlyExisting);
				}
			}
		}
	}
	
	@Override
	/**
	 * @return List the alive list
	 */
	public List<DSIExportBean> getBeans()
	{
		return _beans;
	}

	@Override
	public DSIExportBean getParent(short iRelativeLevel)  throws Exception
	{
		if(iRelativeLevel <= 0)
		{
			throw new IllegalArgumentException("DSIExportBean.getParentBean(short relativeLevel): The input number has to be greater than 0");
		}
		DSIExportBean relative_parent = this.getParent();
		if(null == relative_parent)
		{
			throw new Exception("DSIExportBean.getParentBean(short relativeLevel): The next higher bean is null!!");
		}
		for (short i = 1; i < iRelativeLevel; i++)
		{
			relative_parent = relative_parent.getParent();
			if(null == relative_parent)
			{
				throw new Exception("DSIExportBean.getParentBean(short relativeLevel): Found a null parent bean");
			}
		}
		return relative_parent;
	}
	
	@Override
	public void setParent(DSIExportBean iParent)
	{
		
		if(null != iParent)
		{
			_parent = iParent;
			_parent.getBeans().add(this);
		}
		else
		{
			throw new IllegalArgumentException("The input parent bean is null!!");
		}
	}

	@Override
	public DSIExportBean getParent() 
	{
		return _parent;
	}

	@Override
	public void discardFromStructure() 
	{
		
		if ( null != _parent )
		{
			_parent.getBeans().remove(this);
			_parent = null;
		}
		
	}

	@Override
	public void move(DSIExportBean iParent)
	{
		discardFromStructure();
		setParent(iParent);
	}
}
