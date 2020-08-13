package com.ds.export.engine;

import java.util.Map;

import matrix.util.StringList;
import matrix.db.Context;
import matrix.db.ErrorObject;


public interface DSIExportValueProvider 
{
	/**
	 * @param iObject = the object on which we have the selectables (BusinessObject or Relationship)
	 * @param iKeysOutputMap = the list of key used in the output map
	 * @param iArgs = some other args: e.g. arguments coming from the jpo
	 * @return a map key = (String)iKeysOutputMap[i], value = (StringList) the value retrieved by this method's code
	 * @throws Exception
	 */
	public Map<String, StringList> getValues(Context iContext, ErrorObject iObject, StringList iKeysOutputMap, Map<String, Object> iArgs) throws Exception;

}
