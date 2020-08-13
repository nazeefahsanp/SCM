package com.ds.export.engine;

import java.util.Map;

import matrix.db.Context;
import matrix.db.ErrorObject;

public interface DSIExportRecursionLevelProvider 
{
	public static final String RELATIONSHIP_TYPE = "relationshipType";
	public static final String RELATIONSHIP_FROM = "relationshipFrom";
	public static final String RELATIONSHIP_SECOND_END_OBJECT_TYPE= "objectType";
	/**
	 * 
	 * @param iContext = enovia context
	 * @param iObject = In the actual implementation it is only BusinessObject type. 
	 * 					Represents the object of the starting point of the expand. This is considered as the FIRST END OBJECT
	 * @param iArgs = other arguments. In this map we find at least the following key, value pairs:
	 * 					1. key = DSIExportRecursionLevelProvider.RELATIONSHIP_TYPE, value = the type of the relationship as specified in the xml query template
	 * 					2. key = DSIExportRecursionLevelProvider.RELATIONSHIP_FROM, value = true or false as specified in the xml query template
	 * 					3. key = DSIExportRecursionLevelProvider.RELATIONSHIP_SECOND_END_OBJECT_TYPE, value = the type (as specified in the xml query template) of the other relationship's end object (ending point of the expand)
	 * 					It contains as well: Map<String, Object> iArgs, passed as input in the constructor of DSExportBeanCreator
	 * @return the recursion level as short primitive type
	 * @throws Exception
	 */
	public short getRecursionLevel(Context iContext, ErrorObject iObject, Map<String, Object> iArgs) throws Exception;
}
