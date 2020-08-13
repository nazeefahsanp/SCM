package com.ds.export.engine;

import java.util.Map;

import matrix.db.Context;
import matrix.db.ErrorObject;

public interface DSIExportChecker 
{
	/**
	 * @param iContext = enovia context
	 * @param iObject = input object of type matrix.db.BusinessObject or matrix.db.Relationship.
	 * @param iBean = bean, from XML query template: relationship's selectables + object's selectables.
	 * @param iArgs = other arguments
	 * @return boolean (true/false). 
	 * If true then the input bean is added to the parent's bean list and it retrieves the data of the child beans
	 * If false the input bean it's not added to the parent's bean list and it does not retrieve data of the child beans
	 * @throws Exception
	 * If an Exception is thrown the export is aborted
	 */
	public boolean check(Context iContext, ErrorObject iObject, DSIExportBean iBean, Map<String, Object> iArgs) throws Exception;
	
}
