/*
 ** PWCCommonUtil.java
 **
 ** Copyright (c) 1993-2012 Dassault Systemes. All Rights Reserved.
 ** This program contains proprietary and trade secret information of
 ** Dassault Systemes.
 ** Copyright notice is precautionary only and does not evidence any actual
 ** or intended publication of such program
 ** This Java class contains all the common methods for PWC
 ** @author DS
 ** @version Phase1.Main
 */

package com.ds.common;

import java.util.Map;
import java.util.Vector;

import matrix.db.Access;
import matrix.db.BusinessObject;
import matrix.db.BusinessObjectList;
import matrix.db.Context;
import matrix.db.Person;
import matrix.util.StringList;

import org.apache.log4j.Logger;

import ca.pwc.security.client.AuthenticatorClient;

import com.matrixone.apps.common.Company;
import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.domain.util.ContextUtil;
import com.matrixone.apps.domain.util.EnoviaResourceBundle;
import com.matrixone.apps.domain.util.FrameworkUtil;
import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.MqlUtil;
import com.matrixone.apps.domain.util.PersonUtil;
import com.matrixone.apps.domain.util.PropertyUtil;
import com.matrixone.apps.exportcontrol.ExportControlConstants;
import com.matrixone.apps.framework.ui.UIUtil;

public class PWCCommonUtil {

	private static final Logger _logger = Logger.getLogger("PWCCommonUtil");
	private static final String CODE_VERSION 				= "2012_11_30";
	private static final String _superUser = PropertyUtil.getSchemaProperty("person_UserAgent");
	
	public PWCCommonUtil (Context context, String[] args) throws Exception
    {
    	_logger.debug("constructor()");
    	_logger.debug("code version is: " + CODE_VERSION);
    }
	
	
	/**
	 * This API gives the Object Id of the given Active EC Class of which classpath matches the given classpath criteria
	 * 
	 * @param context
	 * @param strClassName - class name
	 * @param strCriteria - classpath criteria
	 * @return
	 * @throws Exception
	 */
	public String getECClassificationID(Context context, String strClassName, String strCriteria) throws Exception
	{
		_logger.debug("Start of PWCCommonUtil:getECClassificationID()");
		
		StringList slSelects 	= new StringList(DomainConstants.SELECT_ID);
		String strClassID = DomainConstants.EMPTY_STRING;
		try
		{
			if(!UIUtil.isNullOrEmpty(strClassName))
			{
				StringBuilder sbWhere = new StringBuilder();
				sbWhere.append("current == \"").append(PWCConstants.STATE_POLICY_EXC_CLASSIFICATION_ACTIVE).append("\" && ");
				sbWhere.append("attribute[").append(PWCConstants.ATTRIBUTE_PWC_EC_CLASS__PATH).append("] ~~ \"").append(strCriteria).append("\"");

				MapList mlClasses = DomainObject.findObjects(context,
							ExportControlConstants.TYPE_EXPORT_CONTROL_CLASS, // Type
							strClassName,									// Name
							DomainConstants.QUERY_WILDCARD, 				// Revision
							DomainConstants.QUERY_WILDCARD, 				// Owner
							PWCCommonUtil.getVaultPattern(context), 		// Vault
							sbWhere.toString(),								// where clause
							false,
							slSelects);

				if (mlClasses != null && mlClasses.size() > 0)
				{
					Map tempMap = (Map)mlClasses.get(0);
					strClassID = (String) tempMap.get(DomainConstants.SELECT_ID);
				}
			}
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
		
		_logger.debug("End of PWCCommonUtil:getECClassificationID()");
		
		return strClassID;
	}

	/**
	 * Method to check if the context user is super user or not
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public static boolean isSuperUser(Context context) throws Exception
	{
		_logger.debug("Start of PWCCommonUtil:isSuperUser()");
		boolean isSuperUser = false;
		Person person = new Person(context.getUser());
		person.open(context);
		isSuperUser = (person.isAssigned(context, PWCConstants.GROUP_SHADOW_AGENT) && person.isSystemAdmin());
		person.close(context);
		_logger.debug("End of PWCCommonUtil:isSuperUser()");
		return isSuperUser;
	}
	
	/**
	 * Method to read the vault details from properties file
	 * @param context
	 * @return
	 * @throws Exception
	 */
		public static String getVaultPattern(Context context)throws Exception
	{
		_logger.debug("Start of PWCCommonUtil:getVaultPattern()");
		String strVaultPattern = DomainConstants.QUERY_WILDCARD;
		String strVaults = EnoviaResourceBundle.getProperty(context, "pwcFramework.pwcSafetyVaults");
		if (!UIUtil.isNullOrEmpty(strVaults))
		{
			strVaultPattern = buildPattern(FrameworkUtil.split(strVaults, PWCConstants.STR_COMMA));
		}
		_logger.debug("End of PWCCommonUtil:getVaultPattern()");
		return strVaultPattern;
	}

		/**
		 * Method to convert the elements of a StringList to a String with comma separator
		 * @param context
		 * @return
		 * @throws Exception
		 */
	public static String buildPattern(StringList patternList) 
	{
		_logger.debug("Start of PWCCommonUtil:buildPattern()");
		StringBuilder sbPattern = new StringBuilder();
		if ( patternList != null && !patternList.isEmpty())	{			
			int size = patternList.size();
			for (int index = 0; index < size ; index++)
			{
				if(sbPattern.length()>0)
					sbPattern.append(PWCConstants.STR_COMMA);
				sbPattern.append(PropertyUtil.getSchemaProperty((String)patternList.get(index)));
			}
			_logger.debug("End of PWCCommonUtil:buildPattern()");
		}		
		return sbPattern.toString();
	}
	
	/**
	 * Method to read the vault details from properties file
	 * @param context
	 * @return
	 * @throws Exception
	 */
		public static String getIDACEnv()throws Exception
		{
			_logger.debug("Start of PWCCommonUtil:getVaultPattern()");
			// Get LDAP server details from environment
			String strLDAPServer = DomainObject.EMPTY_STRING;
			String LDAP_SERVER = System.getenv("PWC_EXCLDAPIntegration_SERVER_ENV");

			if ("DEVT".equals(LDAP_SERVER))
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_DEVT;
			}
			else if ("QA".equals(LDAP_SERVER))
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_QA;
			}
			else if ("PROD".equals(LDAP_SERVER))
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_PROD;
			}
			else if ("PROD2".equals(LDAP_SERVER))
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_PROD2;
			}
			else if ("PROD3".equals(LDAP_SERVER))
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_PROD3;
			}
			else
			{
				strLDAPServer = AuthenticatorClient.CONTEXT_QA;
			}
			_logger.debug("End of PWCCommonUtil:getVaultPattern()");
			return strLDAPServer;
		}
		
		//Below Method added by A1L: To Grant access to RFA, to Fix the issue related to RFA Promotion/Demotion when owner (RFA Coordinator) is not same as creator
		//This method should be removed or handled in different way in Drop5 
		public static Boolean grantAccessToRFA(Context context, DomainObject RFAObj) throws Exception
		{
			_logger.debug("Start of grantAccessToRFA");
			boolean isContextPushed = false;
			String strPerson = context.getUser();
			boolean isAccessGranted = false;
			try
			{
				BusinessObjectList objectList = new BusinessObjectList();
				objectList.add(RFAObj);
				Access accessMask = new Access();
				accessMask.setAllAccess(true);
				accessMask.setUser(context.getUser());
				if (!strPerson.equals(_superUser))
				{
					ContextUtil.pushContext(context, _superUser, DomainConstants.EMPTY_STRING, DomainConstants.EMPTY_STRING);
					isContextPushed = true;
				}
				MqlUtil.mqlCommand(context, "trigger off", true);
				MqlUtil.mqlCommand(context, "history off", true);
				BusinessObject.grantAccessRights(context,objectList,accessMask);
				isAccessGranted = true;
			}
			finally
			{
				MqlUtil.mqlCommand(context, "trigger on", true);
				MqlUtil.mqlCommand(context, "history on", true);
				
				if (isContextPushed)
				{
					ContextUtil.popContext(context);
					isContextPushed = false;
				}
			}
			_logger.debug("End of grantAccessToRFA");
			return new Boolean(isAccessGranted);
		}
		
		//Below Method added by A1L: To revoke access from RFA, to Fix the issue related to RFA Promotion/Demotion when owner (RFA Coordinator) is not same as creator
		//This method should be removed or handled in different way in Drop5 
		public static void revokeAccessGrantedToRFA(Context context, DomainObject RFAObj) throws Exception
		{
			_logger.debug("Start of revokeAccessGrantedToRFA");
			boolean isContextPushed = false;
			String strPerson = context.getUser();
			try
			{
				if (!strPerson.equals(_superUser))
				{
					ContextUtil.pushContext(context, _superUser, DomainConstants.EMPTY_STRING, DomainConstants.EMPTY_STRING);
					isContextPushed = true;
				}
				MqlUtil.mqlCommand(context, "trigger off", true);
				MqlUtil.mqlCommand(context, "history off", true);
				RFAObj.revokeAccess(context,_superUser,strPerson);
			}
			finally
			{
				MqlUtil.mqlCommand(context, "trigger on", true);
				MqlUtil.mqlCommand(context, "history on", true);
				if (isContextPushed)
				{
					ContextUtil.popContext(context);
					isContextPushed = false;
				}
			}
			_logger.debug("End of revokeAccessGrantedToRFA");
		}
		
		
		/*
		 * Method to retrieve the Security Context of Person
		 * Retuens all roles starts with 'ctx::'
		 */
		
		public static StringList getSecurityContext(Context context) throws Exception
		{
			String strContextRole 	= DomainConstants.EMPTY_STRING;
			StringList userRoleList = new StringList();
			try
			{
				// Get all person Assignments
				Vector userRoleAllList 	= PersonUtil.getAssignments(context);
				int intRoleSize 		= userRoleAllList.size();
				
				// Iterate through the assignments and makin glist of roles starting with "ctx::"
				for (int i = 0; i < intRoleSize; i++)
				{
					strContextRole = (String)userRoleAllList.get(i);
					if (strContextRole.startsWith("ctx::"))
					{
						userRoleList.add(strContextRole);
					}
				}
			}
			catch (Exception exc)
			{
				throw exc;
			}
			
			return userRoleList;
		}
		
		/*
		 * Method to retrieve the Security Context of Person
		 * Retuens all roles starts with 'ctx::'
		 */
		
		public static boolean hasSecurityContext(Context context, String strRole, String strProj) throws Exception
		{
			boolean blnHasContext 	= false;
			int intSize = 0;
			String strMqlQury = new StringBuilder("list role ctx::* where 'parent == \"").append(strRole).append("\" && parent == \"").append(strProj)
									.append("\"' select name dump |").toString();
			Vector userRoleAllList 	= PersonUtil.getAssignments(context);
			String strResult = MqlUtil.mqlCommand(context, strMqlQury);
			
			if(!UIUtil.isNullOrEmpty(strResult))
			{
				StringList strRoleList = FrameworkUtil.split(strResult, "\n");
				
				intSize = strRoleList.size();
				
				for(int i = 0; i < intSize; i++)
				{
					if(userRoleAllList.contains(strRoleList.get(i)))
						blnHasContext = true;
				}
			}
			
			return blnHasContext;
		}
		
		/**
		 * This method return the Collaborative Space specific to Business Process.
		 * @param context & Business Process name
		 * @return String: Collaborative Space name.
		 * @throws Exception: if the operation fails
		 **/
		public static String getRFASpecificCollaborativeSpace(Context context, String strBusinessProcess)throws Exception
		{
			_logger.debug("Start of PWCCommonUtil:getRFASpecificCollaborativeSpace");
			String sCollaborativeSpaceName = DomainConstants.EMPTY_STRING;
			if(UIUtil.isNotNullAndNotEmpty(strBusinessProcess))
			{
				strBusinessProcess = strBusinessProcess.replaceAll("\\s+", "");
				strBusinessProcess.trim();
				StringBuilder keyBuilder = new StringBuilder("pwcComponents.RFA.");
				keyBuilder.append(strBusinessProcess).append(".CollabSpace");
				try
				{
					sCollaborativeSpaceName = EnoviaResourceBundle.getProperty(context, keyBuilder.toString());
				}
				catch (Exception e)
				{
					_logger.error("Exception in PWCCommonUtil:getRFASpecificCollaborativeSpace : " + e.getMessage());
					throw e;
				}
			}
			_logger.debug("End of PWCCommonUtil:getRFASpecificCollaborativeSpace");
			return sCollaborativeSpaceName;
		}
		
		/* wqz : Excluding Utility method for types with specific policy : Start */
		
		/**
		 * Method to check which type:policy to ignore for multiple-ownership.
		 * @param context
		 * @return
		 * @throws Exception
		 */
		public static boolean checkTypePolicyToExcludeForMultiOwnership(Context context, String objectId) throws Exception
		{
			_logger.debug("Start of PWCCommonUtil:checkTypePolicyToExcludeForMultiOwnership()");
			String strExcludeTypePolicy = "";
			try {
				strExcludeTypePolicy = EnoviaResourceBundle.getProperty(context, "pwcIPEC.MultiOwnership.IgnoreTypePolicy");
			} catch (Exception e) {
				System.out.println("No KEY 'pwcIPEC.MultiOwnership.IgnoreTypePolicy' found: Assuming nothing to be excluded");
				// suppress that key was not found and assume nothing is to be excluded.
			}
			if (!UIUtil.isNullOrEmpty(strExcludeTypePolicy))
			{
				DomainObject doObj = DomainObject.newInstance(context, objectId);
				StringList slObjSelect = new StringList(2);
				slObjSelect.add(DomainConstants.SELECT_POLICY);
				slObjSelect.add(DomainConstants.SELECT_TYPE);
				Map objSelectMap = doObj.getInfo(context, slObjSelect);
				
				String strObjType = (String)objSelectMap.get(DomainConstants.SELECT_TYPE);
				//String strObjTypeAlias 	= FrameworkUtil.getAliasForAdmin(context, "type", strObjType, true);
				String strObjPolicy = (String)objSelectMap.get(DomainConstants.SELECT_POLICY);
				String strObjPolicyAlias 	= FrameworkUtil.getAliasForAdmin(context, "policy", strObjPolicy, true);

				StringList slTypesPolicys = FrameworkUtil.split(strExcludeTypePolicy, PWCConstants.STR_COMMA);
				for (Object objTypePolicy:slTypesPolicys) {
					String strTypePolicy = (String)objTypePolicy;

					if (UIUtil.isNotNullAndNotEmpty(strTypePolicy)) {
						StringList slTypePolicy = FrameworkUtil.split(strTypePolicy, PWCConstants.STR_COLON);
						String strExcludeType = (String)slTypePolicy.get(0);
						String strExcludeTypeActual = PropertyUtil.getSchemaProperty(context,strExcludeType);
						String strExcludePolicy = (String)slTypePolicy.get(1);

						if ((doObj.isKindOf(context, strExcludeTypeActual) && strExcludePolicy.equals(strObjPolicyAlias))
							|| (strExcludeType.equals("All") && strExcludePolicy.equals(strObjPolicyAlias))
							|| (doObj.isKindOf(context, strExcludeTypeActual) && strExcludePolicy.equals("All"))) {
							return true;
						}
					}
				}
			}
			_logger.debug("End of PWCCommonUtil:checkTypePolicyToExcludeForMultiOwnership()");
			return false;
		}
		/* wqz : Excluding Utility method for types with specific policy : End */
		
		
		public static String getHostCompanyName(Context context)
			throws Exception
		{
			
			String strReturn 		= DomainConstants.EMPTY_STRING;
			String strHostCompID 	= DomainConstants.EMPTY_STRING;
			
			
			strHostCompID = Company.getHostCompany(context);
			if(UIUtil.isNotNullAndNotEmpty(strHostCompID))
			{
				DomainObject domHostComp = DomainObject.newInstance(context, strHostCompID);
				strReturn = domHostComp.getInfo(context, DomainConstants.SELECT_NAME);
			}
			
			return strReturn;
			
		}
		
		public static String getLoginProject(Context context)
				throws Exception
		{
			String strReturn 		= DomainConstants.EMPTY_STRING;
			String strContextRole	= context.getRole();
			
			if (UIUtil.isNotNullAndNotEmpty(strContextRole))
			{
				String str1 = "print role $1 select project dump;";
				String str2	= MqlUtil.mqlCommand(context, str1, true, new String[] {strContextRole});
				
				if (UIUtil.isNotNullAndNotEmpty(str2))
				{
					strReturn = str2;
				}
			}
			return strReturn;
		}
		
		public static String getLoginOrganization(Context context)
				throws Exception
		{
			String strReturn 		= DomainConstants.EMPTY_STRING;
			String strContextRole	= context.getRole();
			
			if (UIUtil.isNotNullAndNotEmpty(strContextRole))
			{
				String str1 = "print role $1 select org dump;";
				String str2	= MqlUtil.mqlCommand(context, str1, true, new String[] {strContextRole});
				
				if (UIUtil.isNotNullAndNotEmpty(str2))
				{
					strReturn = str2;
				}
			}
			return strReturn;
		}
}
