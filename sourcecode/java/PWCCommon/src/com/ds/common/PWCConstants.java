/*
 ** PWCConstants.java
 **
 ** Copyright (c) 1993-2012 Dassault Systemes. All Rights Reserved.
 ** This program contains proprietary and trade secret information of
 ** Dassault Systemes.
 ** Copyright notice is precautionary only and does not evidence any actual
 ** or intended publication of such program
 ** This Java class contains all the constants for PWC Constants
 ** @author DS
 ** @version Phase1.Main
 */

package com.ds.common;

import com.matrixone.apps.common.Issue;
import com.matrixone.apps.domain.util.PropertyUtil;
import com.matrixone.apps.domain.DomainObject;
import com.matrixone.apps.exportcontrol.ExportControlConstants;
import com.matrixone.apps.requirements.RequirementsConstants;
import org.apache.log4j.Logger;

public class PWCConstants {
	
	private static final Logger _logger = Logger.getLogger("PWCConstants");
	private static final String CODE_VERSION 				= "2014_04_07";
	
	public static final String STR_PIPE 	= "|";
	public static final String STR_COLON = ":";
	public static final String STR_COMMA = ",";
	public static final String STR_NEWLINE = "\n\n";
	
	/* Strings to read values from properties files */
    public static final String RESOURCE_BUNDLE_PRODUCTS_STR = "emxRequirementsStringResource";
    public static final String BUNDLE_PRODUCTS_STR 			= "emxRequirements";

	// Key constants for code logic
    public static final String STR_OBJECT_LIST 	= "objectList";
	public static final String STR_DEFAULT 		= "notReserved";

	//HTML code
	public static final String IMAGE_TAG_START 		= "<img src=\"";
	public static final String IMAGE_TAG_ALT_MIDDLE 	= "\" alt=\"";
	public static final String IMAGE_TAG_END 		= "\"/>";
	public static final String NOT_DETERMINED = "Not Determined";
	
	//Added constants for RFA ERIS integration
    public static final String STR_ITEM_NO = "ITEM_NO";
    public static final String STR_DESCRIPTION = "DESCRIPTION";
    public static final String STR_PART_FAMILY_DATA = "PART_FAMILY_DATA";
    public static final String STR_CRITICAL_PART_FL = "CRITICAL_PART_FL";
    public static final String STR_ENSIP_FL = "ENSIP_FL";
    public static final String STR_FAR = "FAR";
    public static final String STR_ENGINE_MODELS = "ENGINE_MODELS";
    public static final String STR_ENGINE_MODEL_FAMILIES = "ENGINE_MODEL_FAMILIES";
    public static final String STR_SINS = "SINS";
	
			
	// Relationships	
	// Requirements Management	
	public static final String REL_PWC_RMT_PRODUCT_VNV_SPECIFICATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTProductVnVSpecification");
	public static final String REL_PWC_RMT_REQUIREMENT_SOURCE 			= PropertyUtil.getSchemaProperty("relationship_PWC_RMTRequirementSource");	
	public static final String REL_PWC_RMT_COMPLIANCE_ALLOCATION 		= PropertyUtil.getSchemaProperty("relationship_PWC_RMTComplianceAllocation");
	public static final String REL_PWC_RMT_INFORMATION_ALLOCATION 		= PropertyUtil.getSchemaProperty("relationship_PWC_RMTInformationAllocation");	
	public static final String REL_PWC_RMT_MODULE_INSTANCE_VALIDATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleInstanceValidation");
	public static final String REL_PWC_RMT_MODULE_INSTANCE_VERIFICATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleInstanceVerification");		
	public static final String REL_PWC_RMT_MODULE_INSTANCE_APPLICABILITY                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleInstanceApplicability");
	public static final String REL_PWC_RMT_REQUIREMENT_INSTANCE_APPLICABILITY                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTRequirementInstanceApplicability");
	public static final String REL_PWC_RMT_REQUIREMENT_INSTANCE_VALIDATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTRequirementInstanceValidation");
	public static final String REL_PWC_RMT_REQUIREMENT_INSTANCE_VERIFICATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTRequirementInstanceVerification");		
	public static final String REL_PWC_RMT_MODULE_INSTANCE_VALIDATION_ROLLUP                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleInstanceValidationRollup");		
	public static final String REL_PWC_RMT_MODULE_INSTANCE_VERIFICATION_ROLLUP                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleInstanceVerificationRollup");
	public static final String REL_PWC_RMT_MODULE_LEAD                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleLead");
	public static final String REL_PWC_RMT_VnV_ASSIGNEE                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTVnVAssignee");
    public static final String REL_PWC_RMT_MODULE_SET_SPECIFICATION                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleSetSpecification");
	public static final String REL_PWC_RMT_PRODUCT_MODULE_SET                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTProductModuleSet");
	public static final String REL_PWC_RMT_MODULE_SET                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTModuleSet");
	public static final String REL_LOGICAL_FEATURE                  = PropertyUtil.getSchemaProperty("relationship_LogicalFeatures");
	public static final String REL_PWC_RMT_VNV_DERIVED                  = PropertyUtil.getSchemaProperty("relationship_PWC_RMTVnVDerived");
        public static final String REL_ASSIGNED_REQUIREMENT                  = PropertyUtil.getSchemaProperty("relationship_AssignedRequirement");
	
	// Integrated Process Planning
        public static final String REL_VERSION_OF = PropertyUtil.getSchemaProperty("relationship_VersionOf");
        public static final String REL_ACTIVE_VERSION = PropertyUtil.getSchemaProperty("relationship_ActiveVersion");
        public static final String REL_ASSOCIATED_DRAWING = PropertyUtil.getSchemaProperty("relationship_AssociatedDrawing");
	public static final String RELATIONSHIP_VERSION_OF = PropertyUtil.getSchemaProperty("relationship_VersionOf");	
	public static final String REL_MOS_ITEM 	= PropertyUtil.getSchemaProperty("relationship_PWC_MOSItem");
	public static final String REL_CADSUBCOMPONENT 	= PropertyUtil.getSchemaProperty("relationship_CADSubComponent");
	public static final String REL_DERIVED_OUTPUT 	= PropertyUtil.getSchemaProperty("relationship_DerivedOutput");
	public static final String PWC_MOS_PART_DEFAULT_POLICY = PropertyUtil.getSchemaProperty("policy_PWC_MOSPartPolicy");
	public static final String REL_PART_SPECIFICATION 	= PropertyUtil.getSchemaProperty("relationship_PartSpecification");
	
	// RFA Relationships
	 public static final String RELATIONSHIP_SOURCE = PropertyUtil.getSchemaProperty("relationship_PWCSource");
	 public static final String RELATIONSHIP_ASSIGNEE_DOCUMENT = PropertyUtil.getSchemaProperty("relationship_PWCAssigneeDocument");
	 public static final String RELATIONSHIP_DECISION_WHEREUSED = PropertyUtil.getSchemaProperty("relationship_Decision");
	 public static final String RELATIONSHIP_DECISION_APPLIESTO = PropertyUtil.getSchemaProperty("relationship_DecisionAppliesTo");
	 public static final String RELATIONSHIP_ASSIGNED_ISSUE = PropertyUtil.getSchemaProperty("relationship_AssignedIssue");
     public static final String RELATIONSHIP_PWC_RFA_MODEL = PropertyUtil.getSchemaProperty("relationship_PWC_RFAModel");
     public static final String RELATIONSHIP_PWC_RFA_SELECTION = PropertyUtil.getSchemaProperty("relationship_PWC_RFASection");
     public static final String RELATIONSHIP_PWC_RFA_EVENT_LOCATION = PropertyUtil.getSchemaProperty("relationship_PWC_RFAEventLocation");
     public static final String RELATIONSHIP_PWC_RFA_PHYSICAL_PART = PropertyUtil.getSchemaProperty("relationship_PWC_RFAPhysicalPart");
     public static final String RELATIONSHIP_PWC_RFA_DOCUMENT = PropertyUtil.getSchemaProperty("relationship_PWC_RFADocument");
     public static final String RELATIONSHIP_REPORTING_ORGANIZATION = PropertyUtil.getSchemaProperty("relationship_ReportingOrganization");
     public static final String RELATIONSHIP_PWC_RFA_CFCA = PropertyUtil.getSchemaProperty("relationship_PWC_RFACFCA");
     public static final String RELATIONSHIP_PWC_RFA_TOOL = PropertyUtil.getSchemaProperty("relationship_PWC_RFAToolingIssue");
     public static final String RELATIONSHIP_PWC_RFA_REFERENCE = PropertyUtil.getSchemaProperty("relationship_PWC_RFAReference");
     public static final String RELATIONSHIP_PWC_RFA_PART = PropertyUtil.getSchemaProperty("relationship_PWC_RFAPhysicalPart");
     public static final String RELATIONSHIP_PWC_RFAMODELCOORDINATOR = PropertyUtil.getSchemaProperty("relationship_PWC_RFAModelCoordinator");
     public static final String RELATIONSHIP_PWC_RFAREPORTINGORGANIZATIONCOORDINATOR = PropertyUtil.getSchemaProperty("relationship_PWC_RFAReportingOrganizationCoordinator");
     public static final String RELATIONSHIP_PWC_RFAEVENTLOCATIONCOORDINATOR = PropertyUtil.getSchemaProperty("relationship_PWC_RFAEventLocationCoordinator");
     public static final String RELATIONSHIP_PWC_RFA_ENGINE_INFO = PropertyUtil.getSchemaProperty("relationship_PWC_RFAEngineInfo");
     public static final String RELATIONSHIP_PWC_RFA_AFFECTED_SECTION = PropertyUtil.getSchemaProperty("relationship_PWC_RFAAffectedSection");
     public static final String RELATIONSHIP_PWC_RFA_AIRCRAFT = PropertyUtil.getSchemaProperty("relationship_PWC_RFAAircraft");
	 public static final String RELATIONSHIP_PWC_RFA_ENGINE_PRIMARY_INFO = PropertyUtil.getSchemaProperty("relationship_PWC_RFAEnginePrimaryInfo");
	 public static final String RELATIONSHIP_PWC_RFA_DOCUMENT_PRIMARY = PropertyUtil.getSchemaProperty("relationship_PWC_RFADocumentPrimary");
	 public static final String RELATIONSHIP_PWC_RFA_AIRCRAFT_PRIMARY = PropertyUtil.getSchemaProperty("relationship_PWC_RFAAircraftPrimary");
	  public static final String RELATIONSHIP_PWC_RFA_PHYSICAL_PART_PRIMARY = PropertyUtil.getSchemaProperty("relationship_PWC_RFAPhysicalPartPrimary");
	  public static final String RELATIONSHIP_PWC_RFA_CFCA_PRIMARY = PropertyUtil.getSchemaProperty("relationship_PWC_RFACFCAPrimary");
	  public static final String RELATIONSHIP_PWC_RFA_REFERENCE_PRIMARY = PropertyUtil.getSchemaProperty("relationship_PWC_RFAReferencePrimary");
	 public static final String RELATIONSHIP_PWC_RFA_PRIMARY_DATA = PropertyUtil.getSchemaProperty("relationship_PWC_RFAPrimaryData");
	 public static final String RELATIONSHIP_PUBLIC_LINE_MODELS = PropertyUtil.getSchemaProperty("relationship_ProductLineModels");
	 public static final String RELATIONSHIP_PWC_RFA_RESOLVED_TO = PropertyUtil.getSchemaProperty("relationship_PWC_RFAResolvedTo");
	 
	
	 //COORD ASSIGNMENT
	 public static final String RELATIONSHIP_PWC_RFACOORDINATORASSIGNMENT = PropertyUtil.getSchemaProperty("relationship_PWC_RFACoordinatorAssignment");
	 public static final String RELATIONSHIP_PWC_RFAENGINEFAMILYCOORDINATOR = PropertyUtil.getSchemaProperty("relationship_PWC_RFAEngineFamilyCoordinator");
	public static final String RELATIONSHIP_PWC_RFA_ASSOCIATED_TASK = PropertyUtil.getSchemaProperty("relationship_PWC_RFAAssociatedTask");
	
	// IPEC Relationships
	public static final String RELATIONSHIP_PWC_IP_SOURCE					= PropertyUtil.getSchemaProperty("relationship_PWC_IPSource");	
	public static final String RELATIONSHIP_PWC_IP_OWNER					= PropertyUtil.getSchemaProperty("relationship_PWC_IPOwner");	
	public static final String RELATIONSHIP_VALID_COLLABORATIVE_SPACES 		= PropertyUtil.getSchemaProperty("relationship_PWC_IPValidCollaborationSpaces");
	public static final String RELATIONSHIP_IPSHARING_COMPANY 				= PropertyUtil.getSchemaProperty("relationship_PWC_IPSharingCompany");
	public static final String RELATIONSHIP_PWC_IP_SHARE_REQUEST_COMPANY	= PropertyUtil.getSchemaProperty("relationship_PWC_IPShareRequestCompany");
	public static final String RELATIONSHIP_PWC_IP_SHARE_REQUEST_COUNTRY	= PropertyUtil.getSchemaProperty("relationship_PWC_IPShareRequestCountry");
	public static final String RELATIONSHIP_PWC_IP_SHARE_REQUEST_ITEM		= PropertyUtil.getSchemaProperty("relationship_PWC_IPShareRequestItem");
	public static final String RELATIONSHIP_PWC_IP_ASSIGNED_BSR				= PropertyUtil.getSchemaProperty("relationship_PWC_IPAssignedBSR");
	
	
	 // Types	
	// Requirements Management	
	public static final String ADMIN_TYPE_MODULE_INSTANCE_VALIDATION = "type_PWC_RMTModuleInstanceValidation";
	public static final String ADMIN_TYPE_MODULE_INSTANCE_VERIFICATION = "type_PWC_RMTModuleInstanceVerification";
	public static final String ADMIN_TYPE_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP = "type_PWC_RMTRequirementInstanceValidationRollup";
	public static final String ADMIN_TYPE_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP = "type_PWC_RMTRequirementInstanceVerificationRollup";
	public static final String ADMIN_TYPE_PROGRAM_MODULE = "type_PWC_RMTProgramModule";
	public static final String ADMIN_TYPE_MODULE_CONTEXT_SET = "type_PWC_RMTModuleContextSet";
	
	public static final String TYPE_MODULE_INSTANCE_VALIDATION = PropertyUtil.getSchemaProperty(ADMIN_TYPE_MODULE_INSTANCE_VALIDATION);
	public static final String TYPE_MODULE_INSTANCE_VERIFICATION = PropertyUtil.getSchemaProperty(ADMIN_TYPE_MODULE_INSTANCE_VERIFICATION);
	public static final String TYPE_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP = PropertyUtil.getSchemaProperty(ADMIN_TYPE_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP);
	public static final String TYPE_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP = PropertyUtil.getSchemaProperty(ADMIN_TYPE_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP);
	public static final String TYPE_PROGRAM_MODULE = PropertyUtil.getSchemaProperty(ADMIN_TYPE_PROGRAM_MODULE);
	public static final String TYPE_MODULE_CONTEXT_SET = PropertyUtil.getSchemaProperty(ADMIN_TYPE_MODULE_CONTEXT_SET);
	
	// Integrated Process Planning
	public static final String TYPE_CATDRAWING = PropertyUtil.getSchemaProperty("type_CATDrawing");
	public static final String TYPE_MOS_PART 	= PropertyUtil.getSchemaProperty("type_PWC_MOSPart");
	public static final String TYPE_MOS_LIST 	= PropertyUtil.getSchemaProperty("type_PWC_MOSList");
	public static final String TYPE_VERSIONED_CATPART 	= PropertyUtil.getSchemaProperty("type_VersionedCATPart");
	public static final String TYPE_CATPART 	= PropertyUtil.getSchemaProperty("type_CATPart");
	public static final String TYPE_VERSIONED_CATDRAWING 	= PropertyUtil.getSchemaProperty("type_VersionedCATDrawing");
	public static final String TYPE_VERSIONED_CATPRODUCT 	= PropertyUtil.getSchemaProperty("type_VersionedCATProduct");
	public static final String TYPE_PWC_IPPWSQUEUE 	= PropertyUtil.getSchemaProperty("type_PWC_IPPWSQueue");
	public static final String TYPE_CATPRODUCT 	= PropertyUtil.getSchemaProperty("type_CATProduct");
	public static final String TYPE_DERIVED_OUTPUT 	= PropertyUtil.getSchemaProperty("type_DerivedOutput");
	
	// RFA Types
	 public static final String ADMIN_TYPE_RFA = "type_PWC_RFAIssue";
	 public static final String TYPE_CFCA = PropertyUtil.getSchemaProperty("type_PWC_RFACFCA");
	 public static final String TYPE_RC = PropertyUtil.getSchemaProperty("type_PWC_RFARC");
	 public static final String TYPE_CA = PropertyUtil.getSchemaProperty("type_PWC_RFACA");
	 public static final String TYPE_RFA = PropertyUtil.getSchemaProperty("type_PWC_RFAIssue");
	 public static final String TYPE_PWC_RFA_DOCBUILD = PropertyUtil.getSchemaProperty("type_PWC_RFADocumentBuild");
	 public static final String TYPE_PWC_RFA_PHYSICAL_TOOL = PropertyUtil.getSchemaProperty("type_PWC_RFAPhysicalTool");
	 public static final String TYPE_PWC_RFA_PHYSICAL_PART = PropertyUtil.getSchemaProperty("type_PWC_RFAPhysicalPart");
	 public static final String TYPE_PWC_ASSEMBLY_CELL = PropertyUtil.getSchemaProperty("type_PWC_AssemblyCell");
	 public static final String TYPE_PWC_TEST_CELL = PropertyUtil.getSchemaProperty("type_PWC_TestCell");
	 public static final String TYPE_PWC_RFA_EVENT_LOCATION = PropertyUtil.getSchemaProperty("type_PWC_RFAEventLocation");
	public static final String TYPE_ORGANIZATION = PropertyUtil.getSchemaProperty(Issue.SYMBOLIC_type_Organization);
	public static final String TYPE_PWC_RFA_ENGINE_INFO = PropertyUtil.getSchemaProperty("type_PWC_RFAEngineInfo");
	public static final String TYPE_PWC_RFA_AIRCRAFT = PropertyUtil.getSchemaProperty("type_PWC_RFAAircraft");
	public static final String TYPE_PWC_RFA_COORDINATOR = PropertyUtil.getSchemaProperty("type_PWC_RFA_Coordinator");
	 
	public static final String TYPE_PWC_RFA_ACTIVITY_INFO = PropertyUtil.getSchemaProperty("type_PWC_RFAActivityInfo");
	public static final String TYPE_PWC_RFA_REFERENCE = PropertyUtil.getSchemaProperty("type_PWC_RFAReference");
	public static final String TYPE_PWC_RFA_AIRCRAFT_TYPE = PropertyUtil.getSchemaProperty("type_PWC_RFAAircraftType");
	public static final String TYPE_MODEL = PropertyUtil.getSchemaProperty("type_Model");
	public static final String TYPE_PLM_PARAMETER = PropertyUtil.getSchemaProperty("type_PlmParameter");

	//IPEC Type
	public static final String TYPE_PWC_IP_BULK_SHARING_REQUEST			= PropertyUtil.getSchemaProperty("type_PWC_IPBulkSharingRequest");
	public static final String TYPE_PERMISSION_TO_SHARE					= PropertyUtil.getSchemaProperty("type_PWC_IPPermissionToShare");
	public static final String TYPE_GENERAL_CLASS					= PropertyUtil.getSchemaProperty("type_GeneralClass");
	
	
	//ATTRIBUTES
	 //RMT
	public static final String ATTRIBUTE_FINAL_DECISION = PropertyUtil.getSchemaProperty("attribute_PWC_RMTFinalDecision");
	public static final String ATTRIBUTE_COMPLIANCE_STATUS = PropertyUtil.getSchemaProperty("attribute_PWC_RMTComplianceStatus");
	public static final String ATTRIBUTE_COMPLIANCE_STATEMENT = PropertyUtil.getSchemaProperty("attribute_PWC_RMTComplianceStatement");
	public static final String ATTRIBUTE_LEAD_DESIGNATION= PropertyUtil.getSchemaProperty("attribute_PWC_RMTLeadDesignation");	
	public static final String ATTR_CHAPTER_SEQUENCE = PropertyUtil.getSchemaProperty("attribute_PWC_RMTChapterSequence");
	public static final String ATTR_VnV_TASK = PropertyUtil.getSchemaProperty("attribute_PWC_RMTVnVTask");
	public static final String ATTR_REQUIREMENT_SOURCE = PropertyUtil.getSchemaProperty("attribute_PWC_RMTRequirementSource");
	public static final String ATTRIBUTE_COMPLIANCE_METHOD = PropertyUtil.getSchemaProperty("attribute_PWC_RMTComplianceMethod");
	public static final String ATTRIBUTE_ROUTE_COMPLETE_ACTION = PropertyUtil.getSchemaProperty("attribute_RouteCompletionAction");
	
	
	// EXPORT CONTROL ATTRIBUTES
	public static final String ATTRIBUTE_CONTAINS_TECH_DATA = PropertyUtil.getSchemaProperty("attribute_PWC_EXCContainsTechData");
	public static final String ATTRIBUTE_CONTAINS_THIRD_PARTY_INFO = PropertyUtil.getSchemaProperty("attribute_PWC_EXCContainsThirdPartyInformation");
	public static final String ATTRIBUTE_IS_FIRST_MILITART_USAGE = PropertyUtil.getSchemaProperty("attribute_PWC_EXCIsFirstMilitaryUsage");
	public static final String ATTRIBUTE_PWC_IP_COMPANY_CAN_OWN_IP		= PropertyUtil.getSchemaProperty("attribute_PWC_IPCompanyCanOwnIP");
	public static final String ATTRIBUTE_PWC_IP_REQUESTED_DATE			= PropertyUtil.getSchemaProperty("attribute_PWC_IPRequestedDate");
	public static final String ATTRIBUTE_PWC_EC_CLASS__PATH		= PropertyUtil.getSchemaProperty("attribute_PWC_ECClassPath");

	
	//RFA
	public static final String ATTRIBUTE_VALIDATION_COMPLIANCE = PropertyUtil.getSchemaProperty("attribute_PWCValidationCompliance");
    public static final String ATTRIBUTE_VERIFICATION_COMPLIANCE = PropertyUtil.getSchemaProperty("attribute_PWCVerificationCompliance");
    public static final String ATTRIBUTE_CHANGE_REASON = PropertyUtil.getSchemaProperty("attribute_ReasonforChange");
    public static final String ATTRIBUTE_CHANGE_TYPE = PropertyUtil.getSchemaProperty("attribute_RequestedChange");
    public static final String ATTRIBUTE_CHANGE_INCORP_FLAG = PropertyUtil.getSchemaProperty("attribute_PWCCRIncorporation");
    public static final String ATTRIBUTE_ISSUE_CLASSIFICATION = PropertyUtil.getSchemaProperty("attribute_IssueClassification");
    public static final String ATTRIBUTE_RFA_BUSINESS_PROCESS = PropertyUtil.getSchemaProperty("attribute_PWC_RFABusinessProcess");
    public static final String ATTRIBUTE_RFA_SEVERITY = PropertyUtil.getSchemaProperty("attribute_PWC_RFASeverity");
    public static final String ATTRIBUTE_PWC_ROOT_CAUSE = PropertyUtil.getSchemaProperty("attribute_PWC_RootCause");
    public static final String ATTRIBUTE_PWC_CORR_ACTION = PropertyUtil.getSchemaProperty("attribute_PWC_CorrectiveAction");
    public static final String ATTRIBUTE_PWC_ROOT_CAUSE_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RootCauseFamily");
    public static final String ATTRIBUTE_PWC_COOR_ACTION_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_CorrectiveActionFamily");
    public static final String ATTRIBUTE_PWC_ROOT_CAUSE_DESC = PropertyUtil.getSchemaProperty("attribute_PWC_RootCauseDescription");
    public static final String ATTRIBUTE_PWC_COOR_ACTION_DESC = PropertyUtil.getSchemaProperty("attribute_PWC_CorrectiveActionDescription");
    public static final String ATTRIBUTE_PWC_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_Family");
    public static final String ATTRIBUTE_BRANCH_TO = PropertyUtil.getSchemaProperty("attribute_BranchTo");
    public static final String ATTRIBUTE_CATEGORY = PropertyUtil.getSchemaProperty("attribute_Category");
    public static final String ATTRIBUTE_PROB_FOUND_DURING = PropertyUtil.getSchemaProperty("attribute_PWC_RFAStepProblemFound");
	public static final String ATTRIBUTE_ENGINE_SERIAL = PropertyUtil.getSchemaProperty("attribute_PWC_EngineSerial");
	public static final String ATTRIBUTE_ENGINE_BUILD = PropertyUtil.getSchemaProperty("attribute_PWC_EngineBuild");
	public static final String ATTRIBUTE_ENGINE_SYMPTOM = PropertyUtil.getSchemaProperty("attribute_PWC_EngineSymptom");
	public static final String ATTRIBUTE_ENGINE_CONDITION = PropertyUtil.getSchemaProperty("attribute_PWC_EngineCondition");
	public static final String ATTRIBUTE_ENGINE_CONFIGURATION = PropertyUtil.getSchemaProperty("attribute_PWC_EngineConfiguration");
	public static final String ATTRIBUTE_ENGINE_BUILD_SPEC = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineBuildSpecification");
	public static final String ATTRIBUTE_ENGINE_POSITION = PropertyUtil.getSchemaProperty("attribute_PWC_EnginePosition");
	public static final String ATTRIBUTE_ENGINE_TTSN = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTTSN");
	public static final String ATTRIBUTE_INVESTIGATION_STATUS = PropertyUtil.getSchemaProperty("attribute_PWC_InvestigationStatus");
	public static final String ATTRIBUTE_PWC_RFAAnyAllModel = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAnyAllModel");
	public static final String ATTRIBUTE_PWC_RFA_EVENT_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEventType");
	public static final String ATTRIBUTE_PWC_RFA_BUSINESS_PROCESS = PropertyUtil.getSchemaProperty("attribute_PWC_RFABusinessProcess");
	public static final String ATTRIBUTE_INVESTIGATION_DESCRIPTION = PropertyUtil.getSchemaProperty("attribute_PWC_InvestigationDescription");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_SYMPTOM_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineSymptomFamily");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_CONDITION_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineConditionFamily");
	public static final String ATTRIBUTE_PWC_DATE_SEND_TO_COORDINATOR = PropertyUtil.getSchemaProperty("attribute_PWC_DateSendToCoordinator");
	public static final String ATTRIBUTE_PWC_RFA_COORDINATOR = PropertyUtil.getSchemaProperty("attribute_PWC_RFA_Coordinator");
	public static final String ATTRIBUTE_PWC_RFA_EVENTLOCATION = PropertyUtil.getSchemaProperty("attribute_PWC_RFA_EventLocation");
	public static final String ATTRIBUTE_PWC_RFA_ENGINEMODEL = PropertyUtil.getSchemaProperty("attribute_PWC_RFA_EngineModel");
	public static final String ATTRIBUTE_PWC_RFA_PROBLEM_VALIDATION = PropertyUtil.getSchemaProperty("attribute_PWC_ProblemValidation");
	public static final String ATTRIBUTE_PWC_RFA_REPORTING_ORGANIZATION = PropertyUtil.getSchemaProperty("attribute_PWC_RFAReportingOrganization");
	public static final String ATTRIBUTE_PWC_RFA_EVENT_DATE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEventDate");
	
	public static final String ADMIN_ATTRIBUTE_PWC_RFA_REFERENCE_NUMBER = "attribute_PWC_ReferenceNumber";
	public static final String ADMIN_ATTRIBUTE_PWC_RFA_ENGINE_MODEL = "attribute_PWC_RFA_EngineModel";
	
	public static final String ATTRIBUTE_PWC_RFAPROCESSSUBTYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAProcessSubType");
	public static final String ATTRIBUTE_PWC_SPAQAREVIEWGROUP = PropertyUtil.getSchemaProperty("attribute_PWC_SPAQAReviewGroup");
	public static final String ATTRIBUTE_PWC_RFA_SPAQAREVIEWPERSON = PropertyUtil.getSchemaProperty("attribute_PWC_RFA_SPAQAReviewPerson");
	public static final String ATTRIBUTE_PWC_RFA_EXPAPPROVALFORCLOSURE = PropertyUtil.getSchemaProperty("attribute_PWC_RFA_ExpApprovalforClosure");
	public static final String ATTRIBUTE_PWC_ACTION_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAActionType");
	public static final String ATTRIBUTE_PWC_QUESTION_DESCRIPTION = PropertyUtil.getSchemaProperty("attribute_PWC_RFAQuestionDescription");
	public static final String ATTRIBUTE_PWC_RFA_ASSIGNEE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAssignee");
	public static final String ATTRIBUTE_PWC_RFA_CA_TARGET_DATE = PropertyUtil.getSchemaProperty("attribute_PWC_RFACATargetDate");
	public static final String ATTRIBUTE_PWC_RFA_IS_SUPPLEMENT = PropertyUtil.getSchemaProperty("attribute_PWC_RFAIsSupplement");
	public static final String ATTRIBUTE_PWC_RFA_SUPPLEMENT_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFASupplementType");
	public static final String ATTRIBUTE_PWC_RFA_INVESTIGATION_STATUS_FOR_SUPPLEMENT = PropertyUtil.getSchemaProperty("attribute_PWC_RFAInvestigationStatusForSupplement");
	public static final String ATTRIBUTE_PWC_RFA_TROUBLESHOOTING_PERFORMED = PropertyUtil.getSchemaProperty("attribute_PWC_RFATroubleshootingPerformed");

	// For Part
    public static final String ATTRIBUTE_CIFER = PropertyUtil.getSchemaProperty("attribute_PWC_PartCifer");
    public static final String ATTRIBUTE_PART_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_PartNumber");
    public static final String ATTRIBUTE_OFFICIAL_PART_NAME = PropertyUtil.getSchemaProperty("attribute_PWC_RFAOfficialPartName");
    public static final String ATTRIBUTE_PART_NAME = PropertyUtil.getSchemaProperty("attribute_PWC_PartName");
    public static final String ATTRIBUTE_PART_SERIAL_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartSerialNumber");
    public static final String ATTRIBUTE_PART_CON_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartConditionFamily");
    public static final String ATTRIBUTE_PART_TSN = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTSN");
    public static final String ATTRIBUTE_SIN = PropertyUtil.getSchemaProperty("attribute_PWC_SIN");
    public static final String ATTRIBUTE_PART_CONDITION = PropertyUtil.getSchemaProperty("attribute_PWC_PartCondition");
    public static final String ATTRIBUTE_PART_QTY_DEF = PropertyUtil.getSchemaProperty("attribute_PWC_QuantityDefective");
    public static final String ATTRIBUTE_PART_COMMENTS = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartComments");
    public static final String ATTRIBUTE_PART_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_PartFamily");
    public static final String ATTRIBUTE_PART_FAR = PropertyUtil.getSchemaProperty("attribute_PWC_FAR");
    public static final String ATTRIBUTE_PART_CRITICAL_PART = PropertyUtil.getSchemaProperty("attribute_PWC_CriticalPart");
    public static final String ATTRIBUTE_PART_APPLICABLE_ENGINE_MODELS = PropertyUtil.getSchemaProperty("attribute_PWC_ApplicableEngineModels");
    public static final String ATTRIBUTE_PART_APPLICABLE_ENGINE_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_ApplicableEngineFamily");
    public static final String ATTRIBUTE_PART_ENSIP = PropertyUtil.getSchemaProperty("attribute_PWC_ENSIP");
    public static final String ATTRIBUTE_PART_DELIVERY_DATE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartDeliveryDate");
    public static final String ATTRIBUTE_PART_REV_LETTER =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartRevletter");
    public static final String ATTRIBUTE_PWC_RFA_REFERENCE_PART_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAReferencePartNumber");
    public static final String ATTRIBUTE_PWC_RFA_DEFECT_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFADefectType");
    public static final String ATTRIBUTE_PART_DELIVERY_Type = PropertyUtil.getSchemaProperty("attribute_PWC_RFADeliveryType");
    
    // For tool
	   public static final String ATTRIBUTE_TOOL_NAME = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolName");
	   public static final String ATTRIBUTE_TOOL_CONDITION = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolCondition");
	   public static final String ATTRIBUTE_TOOL_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolFamily");
	   public static final String ATTRIBUTE_TOOL_COMMENTS = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolComments");
	   public static final String ATTRIBUTE_TOOL_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolNumber");
	   public static final String ATTRIBUTE_TOOL_CONDITION_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolConditionFamily");
	   public static final String ATTRIBUTE_PWC_RFA_TOOL_SN = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolS/N");
	   public static final String ATTRIBUTE_PWC_RFA_TOOL_REV_LETTER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAToolRevletter");
	   
	   // For document
	   public static final String ATTRIBUTE_DOC_NAME = PropertyUtil.getSchemaProperty("attribute_PWC_RFADocumentName");
	   public static final String ATTRIBUTE_DOC_CONDITION = PropertyUtil.getSchemaProperty("attribute_PWC_RFADocumentCondition");
	   public static final String ATTRIBUTE_DOC_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_Family");
	   public static final String ATTRIBUTE_DOC_COMMENTS = PropertyUtil.getSchemaProperty("attribute_PWC_RFADocumentComments");
	   public static final String ATTRIBUTE_DOC_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFADocumentType");
	   public static final String ATTRIBUTE_DOC_CONDITION_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFADocumentConditionFamily");
	   public static final String ATTRIBUTE_DOC_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_DocNumber");

	   	public static final String ATTR_DESIGN_JOB_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_DesignJobNumber");
		public static final String ATTR_CLOSE_WITH_JUSTIFICATION = PropertyUtil.getSchemaProperty("attribute_PWC_RFAClosewithJustification");
		public static final String ATTR_CORRECTIVE_ACTION_VALIDATION_COMMENT = PropertyUtil.getSchemaProperty("attribute_PWC_CorrectiveActionValidationComment");
		public static final String ATTR_CORRECTIVE_ACTION_CONFIRMED = PropertyUtil.getSchemaProperty("attribute_PWC_CorrectiveActionConfirmed");
		public static final String ATTRIBUTE_PWC_RFA_LIABLE_GROUP = PropertyUtil.getSchemaProperty("attribute_PWC_RFALiableGroup");
		public static final String ATTRIBUTE_PWC_IPT_ID = PropertyUtil.getSchemaProperty("attribute_PWC_IPTID");
		public static final String ATTRIBUTE_PWC_RFA_IS_QA_REVIEW_REQUIRED = PropertyUtil.getSchemaProperty("attribute_PWC_RFAIsQAreviewrequired");
	
	// Integrated Process Planning
	public static final String ATTRIBUTE_PWC_MOS_CHANGE_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_MOSChangeNumber");		
	public static final String ATTRIBUTE_PWC_MOS_SEQUENCE_OPERATION = PropertyUtil.getSchemaProperty("attribute_PWC_MOSSequenceOperation");
	public static final String ATTRIBUTE_PWC_IPP_QUEUE_DIRTY_FLAG = PropertyUtil.getSchemaProperty("attribute_PWC_IPPWSQueueDirtyFlag");
	public static final String ATTRIBUTE_SERVER_UNIQUE_IDENTIFIER = PropertyUtil.getSchemaProperty("attribute_ServerUniqueIdentifier");
	public static final String ATTRIBUTE_PWC_IPP_GROUP = PropertyUtil.getSchemaProperty("attribute_PWC_IPPGroup");
	public static final String ATTRIBUTE_PWC_IPP_GROUP_COUNTER = PropertyUtil.getSchemaProperty("attribute_PWC_IPPGroupCounter");
	public static final String ATTRIBUTE_PWC_IPP_TASK_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_IPPTaskType");
	public static final String ATTRIBUTE_PWC_IPP_MATERIAL_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_IPPMaterialNumber");
	public static final String ATTRIBUTE_PWC_IPP_MATERIAL_SECURITY_FLAG = PropertyUtil.getSchemaProperty("attribute_PWC_IPPMaterialSecurityFlag");
	public static final String ATTRIBUTE_PWC_IPP_CLASSIFICATION_FLAG = PropertyUtil.getSchemaProperty("attribute_PWC_IPPClassificationFlag");
	public static final String ATTRIBUTE_PWC_IPP_OPERATION_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_IPPOperationNumber");
	public static final String ATTRIBUTE_PWC_IPP_ESA_FLAG = PropertyUtil.getSchemaProperty("attribute_PWC_IPPESAFlag");
	public static final String ATTRIBUTE_PWC_IPP_FROMSAPFLAG = PropertyUtil.getSchemaProperty("attribute_PWC_IPPFromSAPFlag");
	public static final String ATTRIBUTE_PWC_MOS_REVISION_LEVEL = PropertyUtil.getSchemaProperty("attribute_PWC_MOSRevisionLevel");
	public static final String ATTRIBUTE_PWC_REFERENCE_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_ReferenceNumber");
	public static final String ATTRIBUTE_COMMENT = PropertyUtil.getSchemaProperty("attribute_Comment");
	public static final String ATTRIBUTE_PWC_REFERENCE_ADDED_DATE = PropertyUtil.getSchemaProperty("attribute_PWC_ReferenceAddedDate");
	public static final String ATTRIBUTE_PWC_REFERENCE_ADDED_BY = PropertyUtil.getSchemaProperty("attribute_PWC_ReferenceAddedBy");
	public static final String ATTRIBUTE_PWC_RFA_PART_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartType");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftType");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftNumber");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_OPERATOR = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftOperator");
	public static final String ATTRIBUTE_PWC_RFA_COFA = PropertyUtil.getSchemaProperty("attribute_PWC_RFACofA");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_SYMPTOM_DEVIATION  = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineSymptomdeviation");
	public static final String ATTRIBUTE_PWC_RFA_IFSD  = PropertyUtil.getSchemaProperty("attribute_PWC_RFAIFSD");
	public static final String ATTRIBUTE_PWC_RFA_FAULT_CODE  = PropertyUtil.getSchemaProperty("attribute_PWC_RFAFaultCode");
	public static final String ATTRIBUTE_PWC_ENGINE_CONDITION  = PropertyUtil.getSchemaProperty("attribute_PWC_EngineCondition");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_MODEL_FAMILY = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineModelFamily");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_PARAMETER  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineParameter");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_PARAMETER_FAMILY  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineParameterFamily");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_PARAMETER_ACTUAL  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineParameterActual");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_PARAMETER_MIN_LIMIT  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineParameterMinLimit");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_PARAMETER_MAX_LIMIT  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineParameterMaxLimit");
	public static final String ATTRIBUTE_PWC_ENGINE_SERIAL  =  PropertyUtil.getSchemaProperty("attribute_PWC_EngineSerial");
	public static final String ATTRIBUTE_PWC_ENGINE_CONFIGURATION  =  PropertyUtil.getSchemaProperty("attribute_PWC_EngineConfiguration");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_BUILD_SPECIFICATION  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineBuildSpecification");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TTSN  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTTSN");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TCSN  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTCSN");
	public static final String ATTRIBUTE_PWC_ENGINE_POSITION  =  PropertyUtil.getSchemaProperty("attribute_PWC_EnginePosition");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_IPPS_DISPOSITION  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngine/IPPSDisposition");
	public static final String ATTRIBUTE_PWC_RFA_SHIPSET_NUMBER  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAShipsetNumber");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_COMMENTS  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineComments");
	public static final String ATTRIBUTE_PWC_RFA_PART_PARAMETER  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartParameter");
	public static final String ATTRIBUTE_PWC_RFA_PART_PARAMETER_FAMILY  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartParameterFamily");
	public static final String ATTRIBUTE_PWC_RFA_PART_PARAMETER_ACTUAL  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartParameterActual");
	public static final String ATTRIBUTE_PWC_RFA_PART_PARAMETER_MIN_LIMIT  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartParameterMinLimit");
	public static final String ATTRIBUTE_PWC_RFA_PART_PARAMETER_MAX_LIMIT  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartParameterMaxLimit");
	public static final String ATTRIBUTE_PWC_RFA_PART_DISPOSITION  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartDisposition");
	public static final String ATTRIBUTE_PWC_RFA_REPLACEMENT_PART_REQUIREMENTS  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAReplacementPartRequirements");
	public static final String ATTRIBUTE_PWC_RFA_PART_TTSN  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTTSN");
	public static final String ATTRIBUTE_PWC_RFA_PART_TCSN  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTCSN");
	public static final String ATTRIBUTE_PWC_RFA_VENDOR_CODE  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAVendorCode");
	public static final String ATTRIBUTE_PWC_RFA_VENDOR_CODE_NAME  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAVendorCodeName");
	public static final String ATTRIBUTE_PWC_RFA_REFERENCE_COMMENTS  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAReferenceComments");
	public static final String ATTRIBUTE_PWC_RFA_CAUSAL_FACTOR_TYPE  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFACausalFactorType");
	public static final String ATTRIBUTE_PWC_APPLICABLE_ENGINE_MODELS  =  PropertyUtil.getSchemaProperty("attribute_PWC_ApplicableEngineModels");

	public static final String ATTRIBUTE_PWC_RFA_PART_NUMBER_ORDERED = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartNumberOrdered");
	public static final String ATTRIBUTE_PWC_RFA_OCCURRENCE_TYPE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAOccurrenceType");
	public static final String ATTRIBUTE_PWC_RFA_PART_QTY_ORDERED = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartQTYOrdered");
	public static final String ATTRIBUTE_PWC_RFA_PART_QTY_RECEIVED = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartQTYReceived");
	public static final String ATTRIBUTE_PWC_RFA_PART_TCSO_TCSR = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTCSO/TCSR");
	public static final String ATTRIBUTE_PWC_RFA_PART_TTSO_TTSR = PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTTSO/TTSR");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TCSO_TCSR = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTCSO/TCSR");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TTSO_TTSR = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTTSO/TTSR");

	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TTSO  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTTSO");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TCSO  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTCSO");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TTSR  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTTSR");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TCSR  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTCSR");

	public static final String ATTRIBUTE_PWC_RFA_PART_TTSO  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTTSO");
	public static final String ATTRIBUTE_PWC_RFA_PART_TCSO  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTCSO");
	public static final String ATTRIBUTE_PWC_RFA_PART_TTSR  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTTSR");
	public static final String ATTRIBUTE_PWC_RFA_PART_TCSR  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartTCSR");

	public static final String ATTRIBUTE_PWC_CRITICAL_PART  =  PropertyUtil.getSchemaProperty("attribute_PWC_CriticalPart");
	public static final String ATTRIBUTE_PWC_ENSIP  =  PropertyUtil.getSchemaProperty("attribute_PWC_ENSIP");
	public static final String ATTRIBUTE_PWC_FAR  =  PropertyUtil.getSchemaProperty("attribute_PWC_FAR");
	public static final String ATTRIBUTE_PWC_RFA_PART_PRIME_INDICATOR  =  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartPrimeIndicator");


	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_CONFIGURATION_FAMILY	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftConfigurationFamily");
	public static final String ATTRIBUTE_PWC_RFA_FLIGHT_CONDITION	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAFlightCondition");
	public static final String ATTRIBUTE_PWC_RFA_AIRPORT_LOCATION	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAirportLocation");
	public static final String ATTRIBUTE_PWC_RFA_AIRPORT_COUNTRY	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAirportCountry");
	public static final String ATTRIBUTE_PWC_RFA_MARKET_SEGMENT	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAMarketSegment");
	public static final String ATTRIBUTE_PWC_RFA_APPLICATION_TYPE	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAApplicationType");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_FATALITIES	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftFatalities");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_CREW_TOTAL	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftCrewTotal");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_CREW_INJURED	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftCrewInjured");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_CREW_FATAL	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftCrewFatal");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_PASSENGERS_TOTAL	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftPassengersTotal");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_PASSENGERS_INJURED	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftPassengersInjured");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_PASSENGERS_FATAL	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftPassengersFatal");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_ENGINE_INVOLVED	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftEngineInvolved");
	public static final String ATTRIBUTE_PWC_RFA_FLIGHT_EFFECT	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAFlightEffect");
	public static final String ATTRIBUTE_PWC_RFA_EVENT_CATEGORY	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEventCategoryBasic/NonBasic");
	public static final String ATTRIBUTE_PWC_RFA_SUBSTANTIATION	 = PropertyUtil.getSchemaProperty("attribute_PWC_RFASubstantiation");

	
	public static final String ATTRIBUTE_PWC_RFA_ACTION=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAAction");
	public static final String ATTRIBUTE_PWC_RFA_ACTION_TYPE=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAActionType");
	public static final String ATTRIBUTE_PWC_RFA_QUESTION_DESCRIPTION=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAQuestionDescription");
	public static final String ATTRIBUTE_PWC_RFA_ACTIONEE=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAActionee");
	public static final String ATTRIBUTE_PWC_RFA_REINSPECTION_METHOD=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAReInspectionmethod");
	public static final String ATTRIBUTE_PWC_RFA_QTY_AFFECTED=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAQtyAffected");
	public static final String ATTRIBUTE_PWC_RFA_PRODUCT_TYPE_AFFECTED=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAProductTypeAffected");
	public static final String ATTRIBUTE_PWC_RFA_PRODUCT_LOCATION=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAProductLocation");
	public static final String ATTRIBUTE_PWC_RFA_ACTION_STATUS=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAActionStatus");
	public static final String ATTRIBUTE_PWC_RFA_ACTION_COMMENTS=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAActionComments");

	public static final String ATTRIBUTE_PWC_RFA_PART_DEVIATION_DESCRIPTION=  PropertyUtil.getSchemaProperty("attribute_PWC_RFAPartDeviationDescription");
	public static final String ATTRIBUTE_PWC_RFA_LOGISTICS_REQUIRED=  PropertyUtil.getSchemaProperty("attribute_PWC_RFALogisticsrequired");

	
	
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_TAIL_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftTailNumber");
	public static final String ATTRIBUTE_PWC_RFA_AIRCRAFT_TFSN = PropertyUtil.getSchemaProperty("attribute_PWC_RFAAircraftTFSN");
	
	public static final String ATTRIBUTE_PWC_RFA_REFERENCE = PropertyUtil.getSchemaProperty("attribute_PWC_RFAReference");
	public static final String ATTRIBUTE_PWC_RFA_DEVIATION_DESCRIPTION = PropertyUtil.getSchemaProperty("attribute_PWC_RFADeviationDescription");
	public static final String ATTRIBUTE_PWC_RFA_ENGINE_TRANSIENT_RECORDING_NUMBER = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngineTransientRecordingNumber");
	public static final String ATTRIBUTE_PWC_RFA_ENGINEIPPS_DEVIATION_DISPOSITION = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEngine/IPPSDeviationDisposition");
	public static final String ATTRIBUTE_PWC_RFA_DISPOSITION_INSTRUCTION = PropertyUtil.getSchemaProperty("attribute_PWC_RFADispositionInstruction");
	public static final String ATTRIBUTE_PWC_RFA_LIABILITY_CONFIRMATION_DATE = PropertyUtil.getSchemaProperty("attribute_PWC_RFALiabilityConfirmationDate");
	public static final String ATTRIBUTE_PWC_RFA_SEVERITY = PropertyUtil.getSchemaProperty("attribute_PWC_RFASeverity");
	public static final String ATTRIBUTE_PWC_RFA_EVENT_IMPACT = PropertyUtil.getSchemaProperty("attribute_PWC_RFAEventImpact");
	
	public static final String ATTRIBUTE_PWC_IPP_IPSource = PropertyUtil.getSchemaProperty("attribute_PWC_IPP_IPSource");
	public static final String ATTRIBUTE_PWC_IPP_IPSensitivity = PropertyUtil.getSchemaProperty("attribute_PWC_IPP_IPSensitivity");
	
	// Attribute Selects
	public static final String SELECT_ATTRIBUTE_TITLE = "attribute["+DomainObject.ATTRIBUTE_TITLE+"]";
	public static final String SELECT_ATTRIBUTE_FINAL_DECISION = "attribute["+ATTRIBUTE_FINAL_DECISION+"]";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS = "attribute["+ATTRIBUTE_COMPLIANCE_STATUS+"]";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATEMENT = "attribute["+ATTRIBUTE_COMPLIANCE_STATEMENT+"]";
	public static final String SELECT_ATTRIBUTE_CHAPTER_SEQUENCE = "attribute["+ATTR_CHAPTER_SEQUENCE+"]";
	public static final String SELECT_ATTRIBUTE_VnV_TASK = "attribute["+ATTR_VnV_TASK+"]";
	public static final String SELECT_ATTRIBUTE_LEAD_DESIGNATION = "attribute["+ATTRIBUTE_LEAD_DESIGNATION+"]";
	public static final String SELECT_ATTR_REQUIREMENT_SOURCE = "attribute["+ATTR_REQUIREMENT_SOURCE+"]";
	public static final String SELECT_ATTR_COMPLIANCE_METHOD = "attribute["+ATTRIBUTE_COMPLIANCE_METHOD+"]";

	public static final String SELECT_ATTRIBUTE_FINAL_DECISION_COMPLY = "Comply";
	public static final String SELECT_ATTRIBUTE_FINAL_DECISION_NOTED = "Noted";
	public static final String SELECT_ATTRIBUTE_FINAL_DECISION_NONCOMPLY = "Non-Comply";
	public static final String SELECT_ATTRIBUTE_FINAL_DECISION_DEVIATION_ACCEPTED = "Deviation Accepted";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_COMPLY = "Comply";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_COMPLY_EXPECTED = "Comply Expected";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_LOW_NONCOMPLY_RISK = "Low Non-Comply Risk";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_HIGH_NONCOMPLY_RISK = "High Non-Comply Risk";	
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_NON_COMPLY = "Non-Comply";
	public static final String SELECT_ATTRIBUTE_COMPLIANCE_STATUS_NOTED = "Noted";
	public static final String SELECT_ATTRIBUTE_PWC_IPP_OPERATION_NUMBER = "attribute["+ATTRIBUTE_PWC_IPP_OPERATION_NUMBER+"]";
	// Policy data
	// Requirements Management
	public static final String ADMIN_POLICY_PWC_MODULE_INSTANCE_VALIDATION = "policy_PWC_RMTModuleInstanceValidation";
	public static final String ADMIN_POLICY_PWC_MODULE_INSTANCE_VERIFICATION = "policy_PWC_RMTModuleInstanceVerification";
	public static final String ADMIN_POLICY_PWC_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP = "policy_PWC_RMTRequirementInstanceValidation";
	public static final String ADMIN_POLICY_PWC_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP = "policy_PWC_RMTRequirementInstanceVerification";
	public static final String ADMIN_POLICY_PWC_REQUIREMENTS_SPECIFICATION_NON_REQUIREMENT = "policy_PWC_RMTRequirementsSpecificationNonRequirement";
	public static final String ADMIN_POLICY_PWC_REQUIREMENT = "policy_SoftwareUserRequirement";

	public static final String POLICY_PWC_MODULE_INSTANCE_VALIDATION = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_MODULE_INSTANCE_VALIDATION);
	public static final String POLICY_PWC_MODULE_INSTANCE_VERIFICATION = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_MODULE_INSTANCE_VERIFICATION);
	public static final String POLICY_PWC_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_REQUIREMENT_INSTANCE_VALIDATION_ROLLUP);
	public static final String POLICY_PWC_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_REQUIREMENT_INSTANCE_VERIFICATION_ROLLUP);
	public static final String POLICY_PWC_REQUIREMENTS_SPECIFICATION_NON_REQUIREMENT = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_REQUIREMENTS_SPECIFICATION_NON_REQUIREMENT);
	public static final String POLICY_PWC_REQUIREMENT = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_REQUIREMENT);
	
	public static final String STATE_POLICY_REQUIREMENT_VALIDATE = PropertyUtil.getSchemaProperty("policy", RequirementsConstants.POLICY_REQUIREMENT, "state_Validate");
	
	// Integrated Process Planning
	public static final String POLICY_PWC_MOS_CAD = PropertyUtil.getSchemaProperty("policy_PWC_MOSCATIAV5DesignPolicy");
	public static final String POLICY_PWC_RFAOEMI = PropertyUtil.getSchemaProperty("policy_PWC_RFAOEMi");
	public static final String POLICY_PWC_MOS_VERSION_CAD = PropertyUtil.getSchemaProperty("policy_PWC_MOSVersionedCATIAV5DesignPolicy");
	public static final String POLICY_MOS_PART 	= PropertyUtil.getSchemaProperty("policy_PWC_MOSPartPolicy");
	public static final String POLICY_MOS_LIST 	= PropertyUtil.getSchemaProperty("policy_PWC_MOSListPolicy");
	public static final String POLICY_PWC_IPPWSQUEUE 	= PropertyUtil.getSchemaProperty("policy_PWC_IPPWSQueue");
	
	// IPP Policy States
	public static final String STATE_POLICY_PWC_IPPWSQUEUE_INPROGRESS =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_PWC_IPPWSQUEUE,
                     								"state_InProgress");
	public static final String STATE_POLICY_PWC_IPPWSQUEUE_READY =
										PropertyUtil.getSchemaProperty("policy",
													POLICY_PWC_IPPWSQUEUE,
													"state_Ready");
	public static final String STATE_POLICY_PWC_MOS_CAD_REVIEW = PropertyUtil.getSchemaProperty("policy", POLICY_PWC_MOS_CAD, "state_Review");
	public static final String STATE_POLICY_PWC_MOS_CAD_RELEASE = PropertyUtil.getSchemaProperty("policy", POLICY_PWC_MOS_CAD, "state_Release");
	
	// RFA Policy 
	public static final String POLICY_RFADUMMY = PropertyUtil.getSchemaProperty("policy_PWC_RFADummy");
    public static final String POLICY_RFA_DRDEV = PropertyUtil.getSchemaProperty("policy_PWC_RFADrDev");
    public static final String POLICY_RFA_SPA 	= PropertyUtil.getSchemaProperty("policy_PWC_RFASPA");
    public static final String POLICY_PWC_RFAOEMi 	= PropertyUtil.getSchemaProperty("policy_PWC_RFAOEMi");
    public static final String POLICY_PWC_RFA_DOCUMENT = PropertyUtil.getSchemaProperty("policy_PWC_RFADocument");
    public static final String POLICY_PWC_RFA_PLM_PARAMETER = PropertyUtil.getSchemaProperty("policy_PWC_RFAPLMParameter");
    
    public static final String ADMIN_POLICY_PWC_RFAFLD = "policy_PWC_RFAFLD";
    public static final String ADMIN_POLICY_PWC_RFASPA = "policy_PWC_RFASPA";
    public static final String ADMIN_POLICY_PWC_RFAEXP_RFEA = "policy_PWC_RFAEXPRFEA";
    public static final String POLICY_PWC_RFAFLD = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_RFAFLD);
    public static final String POLICY_PWC_RFAEXP_RFEA = PropertyUtil.getSchemaProperty(ADMIN_POLICY_PWC_RFAEXP_RFEA);
    public static final String POLICY_PWC_RFA_COORDINATOR = PropertyUtil.getSchemaProperty("policy_PWC_RFA_Coordinator");
  
    public static final String POLICY_PWC_RFAECNTS = PropertyUtil.getSchemaProperty("policy_PWC_RFAECNTS");
    public static final String POLICY_PWC_RFASIE = PropertyUtil.getSchemaProperty("policy_PWC_RFASIE");
    public static final String POLICY_PWC_RFAEQP = PropertyUtil.getSchemaProperty("policy_PWC_RFAEQP");

    public static final String POLICY_ORGANIZATION = PropertyUtil.getSchemaProperty("policy_Organization");
    
    public static final String POLICY_CLASSIFICATION = PropertyUtil.getSchemaProperty("policy_Classification");

    
    // RFA Policy States
    
    // Policy States
 		public static final String STATE_POLICY_RFA_DRDEV_CREATE =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_Create");

 		public static final String STATE_POLICY_RFA_DRDEV_COORD_VALIDATION =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_CoordinatorValidation");

 		public static final String STATE_POLICY_RFA_DRDEV_ANALYSIS =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_Analysis");

 		public static final String STATE_POLICY_RFA_DRDEV_CA_REVIEW =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_CAReview");

 		public static final String STATE_POLICY_RFA_DRDEV_CA_VALIDATION =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_CAValidation");

 		public static final String STATE_POLICY_RFA_DRDEV_FINAL_REVIEW_APPROVAL =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_FinalReviewandApproval");

 		public static final String STATE_POLICY_RFA_DRDEV_CLOSED =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_Closed");

 		public static final String STATE_POLICY_RFA_DRDEV_CANCELLED =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_DRDEV,
                     								"state_Cancelled");

	public static final String STATE_POLICY_RFA_SPA_CREATE =
										PropertyUtil.getSchemaProperty("policy",
													POLICY_RFA_SPA,
													"state_Create");

        public static final String STATE_POLICY_RFA_SPA_COORD_VALIDATION =
										PropertyUtil.getSchemaProperty("policy",
													POLICY_RFA_SPA,
													"state_CoordinatorValidation");

        public static final String STATE_POLICY_RFA_SPA_QAREVIEW =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_RFA_SPA,
                     								"state_QAReview");
        
		public static final String STATE_POLICY_RFA_SPA_ANALYSIS_CAREVIEW =
										PropertyUtil.getSchemaProperty("policy",
													POLICY_RFA_SPA,
													"state_Analysis/CAReview");

 public static final String STATE_POLICY_RFA_OEM_CREATE =
					PropertyUtil.getSchemaProperty("policy",
     						POLICY_PWC_RFAOEMi,
								"state_Create");

        public static final String STATE_POLICY_RFA_OEM_COORDINATOR_VALIDATION =
					PropertyUtil.getSchemaProperty("policy",
     						POLICY_PWC_RFAOEMi,
								"state_CoordinatorValidation");
        
        public static final String STATE_POLICY_RFA_OEM_ANALYSIS =
					PropertyUtil.getSchemaProperty("policy",
     						POLICY_PWC_RFAOEMi,
								"state_Analysis");

        public static final String STATE_POLICY_RFA_OEM_COORDINATOR_REVIEW =
					PropertyUtil.getSchemaProperty("policy",
     						POLICY_PWC_RFAOEMi,
								"state_CoordinatorReview");
              
		public static final String STATE_POLICY_RFA_OEM_CORRECTIVE_ACTION_VALIDATION =
							PropertyUtil.getSchemaProperty("policy",
									POLICY_PWC_RFAOEMi,
								"state_CorrectiveActionValidation");

        public static final String STATE_POLICY_RFA_OEM_APPROVAL_FOR_CLOSURE =
				PropertyUtil.getSchemaProperty("policy",
 						POLICY_PWC_RFAOEMi,
							"state_ApprovalforClosure");
        
 		public static final String STATE_POLICY_RFA_FLD_CREATE =
				PropertyUtil.getSchemaProperty("policy",
 						POLICY_PWC_RFAFLD,
							"state_Create");

        public static final String STATE_POLICY_RFA_FLD_COORDINATOR_VALIDATION =
				PropertyUtil.getSchemaProperty("policy",
 						POLICY_PWC_RFAFLD,
							"state_CoordinatorValidation");
    
    public static final String STATE_POLICY_RFA_FLD_ANALYSIS =
				PropertyUtil.getSchemaProperty("policy",
						POLICY_PWC_RFAFLD,
							"state_Analysis");
	public static final String STATE_POLICY_RFA_EXP_RFEA_CREATE =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_Create");

    public static final String STATE_POLICY_RFA_EXP_RFEA_COORDINATOR_VALIDATION =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_CoordinatorValidation");
    public static final String STATE_POLICY_RFA_EXP_RFEA_TE_REVIEW =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_TEReview");
    public static final String STATE_POLICY_RFA_EXP_RFEA_DISPOSITION =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_Disposition");
    public static final String STATE_POLICY_RFA_EXP_RFEA_REVIEW =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_Review");
    public static final String STATE_POLICY_RFA_RFA_EXP_RFEA_APPROVAL_FOR_CLOSURE =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAEXP_RFEA,
						"state_ApprovalforClosure");
	
	public static final String STATE_POLICY_RFA_RFA_EXP_RFEA_CLOSED =
 										PropertyUtil.getSchemaProperty("policy",
 		                    						POLICY_PWC_RFAEXP_RFEA,
                     								"state_Closed");
	
	
	public static final String STATE_POLICY_RFA_ECNTS_COORDINATOR_VALIDATION =
			PropertyUtil.getSchemaProperty("policy",
					POLICY_PWC_RFAECNTS,
						"state_CoordinatorValidation");
	
	 public static final String STATE_POLICY_RFA_ECNTS_LOGISTICS_ANALYSIS =
				PropertyUtil.getSchemaProperty("policy",
						POLICY_PWC_RFAECNTS,
							"state_LogisticsAnalysis");
	    public static final String STATE_POLICY_RFA_ECNTS_INVESTIGATION_ANALYSIS =
				PropertyUtil.getSchemaProperty("policy",
						POLICY_PWC_RFAECNTS,
							"state_Investigation/Analysis");
	   
	    public static final String STATE_POLICY_RFA_RFA_ECNTS_APPROVAL_FOR_CLOSURE =
				PropertyUtil.getSchemaProperty("policy",
						POLICY_PWC_RFAECNTS,
							"state_ApprovalforClosure");
		public static final String STATE_POLICY_RFA_RFA_ECNTS_CLOSED =
	 										PropertyUtil.getSchemaProperty("policy",
	 												POLICY_PWC_RFAECNTS,
	                     								"state_Closed");
	
		public static final String STATE_POLICY_ORGANIZATION_ACTIVE =
	 										PropertyUtil.getSchemaProperty("policy",
	 												POLICY_ORGANIZATION,
	                     								"state_Active");
		
		public static final String STATE_POLICY_CLASSIFICATION_ACTIVE =
					PropertyUtil.getSchemaProperty("policy",
							POLICY_CLASSIFICATION,
								"state_Active");
	
		public static final String STATE_POLICY_EXC_CLASSIFICATION_ACTIVE =
					PropertyUtil.getSchemaProperty("policy",
							ExportControlConstants.POLICY_EXC_CLASSIFICATION,
								"state_Active");
		
	
	
	//Project Task
 		public static final String STATE_POLICY_PROJECT_TASK_ASSIGN  = PropertyUtil.getSchemaProperty("policy",DomainObject.POLICY_PROJECT_TASK,"state_Assign");
 		public static final String STATE_POLICY_PROJECT_TASK_ACTIVE = PropertyUtil.getSchemaProperty("policy",DomainObject.POLICY_PROJECT_TASK,"state_Active");
 		public static final String STATE_POLICY_PROJECT_TASK_REVIEW = PropertyUtil.getSchemaProperty("policy",DomainObject.POLICY_PROJECT_TASK,"state_Review");
 		public static final String STATE_POLICY_PROJECT_TASK_COMPLETE = PropertyUtil.getSchemaProperty("policy",DomainObject.POLICY_PROJECT_TASK,"state_Complete");
 		//Inbox Task
 		public static final String STATE_POLICY_INBOX_TASK_ASSIGNED = PropertyUtil.getSchemaProperty("policy",DomainObject.POLICY_INBOX_TASK,"state_Assigned");
 		public static final String STATE_POLICY_INBOX_TASK_COMPLETE = PropertyUtil.getSchemaProperty("policy", DomainObject.POLICY_INBOX_TASK,"state_Complete");
 		
 		// Policy "PWC_MOS CATIA V5 Design Policy" states
 		public static final String STATE_POLICY_MOS_CAD_PRELIMINARY  				= PropertyUtil.getSchemaProperty("policy",PWCConstants.POLICY_PWC_MOS_CAD,"state_Preliminary");
 		public static final String STATE_POLICY_MOS_CAD_REVIEW  							= PropertyUtil.getSchemaProperty("policy",PWCConstants.POLICY_PWC_MOS_CAD,"state_Review");
 		public static final String STATE_POLICY_MOS_CAD_RELEASE  						= PropertyUtil.getSchemaProperty("policy",PWCConstants.POLICY_PWC_MOS_CAD,"state_Release");
 		public static final String REL_CATIA_V5_IMPORT_GEOMETRY 						= PropertyUtil.getSchemaProperty("relationship_CATIAV5GeometryImport"); 
	
 		public static final String STATE_POLICY_MOS_PART_RELEASE  						= PropertyUtil.getSchemaProperty("policy", PWCConstants.POLICY_MOS_PART, "state_Release");
	 
 	//IP Policies and States
 		public static final String POLICY_PERMISSION_TO_SHARE	=	  PropertyUtil.getSchemaProperty("policy_PWC_IPPermissionToShare");
 		public static final String STATE_POLICY_PERMISSION_TO_SHARE_INEFFECT	= PropertyUtil.getSchemaProperty("policy", POLICY_PERMISSION_TO_SHARE,"state_InEffect");
	 
	public static final String SELECT_FROMMID = "frommid";
	public static final String SELECT_TOREL = "torel";
	public static final String CONSTANT_VALIDATION = "Validation";
	public static final String CONSTANT_VERIFICATION = "Verification";
	public static final String CONSTANT_PRIMARY_VALIDATION_LEAD = "Primary Validation Lead";
	public static final String CONSTANT_PRIMARY_VERIFICATION_LEAD = "Primary Verification Lead";
	public static final String CONSTANT_PRILIMINARY		= "Preliminary";
	public static final String CONSTANT_REVIEW				= "Review";
	public static final String CONSTANT_COMPLETE			= "Complete";
	public static final String CONSTANT_DRAFT			= "Draft";
	public static final String CONSTANT_IS_FROMCOMPLIANCE			= "PWC_RMTModuleAllocation";
	public static final String CONSTANT_YES			= "Yes";
	public static final String CONSTANT_NO			= "No";
	public static final String CONSTANT_TRUE		= "TRUE";
	public static final String CONSTANT_FALSE		= "FALSE";
	
	// Selects
    public static final String SELECT_ATTRIBUTE_START = "attribute[";
    public static final String SELECT_ATTRIBUTE_END = "]";
    public static final String SELECT_VALIDATION_COMPLIANCE = SELECT_ATTRIBUTE_START+ATTRIBUTE_VALIDATION_COMPLIANCE+SELECT_ATTRIBUTE_END;
    public static final String SELECT_VERIFICATION_COMPLIANCE = SELECT_ATTRIBUTE_START+ATTRIBUTE_VERIFICATION_COMPLIANCE+SELECT_ATTRIBUTE_END;
    public static final String SELECT_CHANGE_REASON = SELECT_ATTRIBUTE_START+ATTRIBUTE_CHANGE_REASON+SELECT_ATTRIBUTE_END;
    public static final String SELECT_CHANGE_TYPE = SELECT_ATTRIBUTE_START+ATTRIBUTE_CHANGE_TYPE+SELECT_ATTRIBUTE_END;
    public static final String SELECT_CHANGE_INCORP_FLAG = SELECT_ATTRIBUTE_START+ATTRIBUTE_CHANGE_INCORP_FLAG+SELECT_ATTRIBUTE_END;
	
	public static final String INTERFACE_PWC_MOS_ITF = PropertyUtil.getSchemaProperty("interface_PWC_MOSInterface");
	
	public static final String GROUP_SHADOW_AGENT = PropertyUtil.getSchemaProperty("group_ShadowAgent");
	
	// RFA role
	public static final String ROLE_DRDEV_COORDINATOR = PropertyUtil.getSchemaProperty("role_PWCDRDevCoordinator");
	public static final String ROLE_ECNSUPPLIER = PropertyUtil.getSchemaProperty("role_PWCECNSupplier");
	public static final String ROLE_PWC_RFA_COORDINATOR = PropertyUtil.getSchemaProperty("role_PWCRFACoordinator");
	
	// IPEC Roles
	public static final String ROLE_PWC_JURISDICTION_CLASSIFIER = PropertyUtil.getSchemaProperty("role_PWCJurisdictionClassifier");
	public static final String ROLE_PWC_LICENSE_REVIEWER 		= PropertyUtil.getSchemaProperty("role_PWCLicenseReviewer");
	public static final String ROLE_PWC_LICENSE_MANAGEMENT 		= PropertyUtil.getSchemaProperty("role_PWCLicenseManagement");
	public static final String ROLE_PWC_IP_CLASSIFIER 			= PropertyUtil.getSchemaProperty("role_PWCIPClassifier");
	public static final String ROLE_PWC_BAIPR 					= PropertyUtil.getSchemaProperty("role_PWCBAIPR");
	public static final String ROLE_PWC_EXCEPTION_CONTROL 		= PropertyUtil.getSchemaProperty("role_PWCExceptionControl");
	public static final String ROLE_PWC_IP_FOCAL		 		= PropertyUtil.getSchemaProperty("role_PWC_IPFocal");
	
	
	// IPP Roles
	public static final String ROLE_PWC_MANUFACTURING_ADMINISTRATIOR = PropertyUtil.getSchemaProperty("role_PWCManufacturingAdministrator");
	public static final String ROLE_MANUFACTURING_PLANNER = PropertyUtil.getSchemaProperty("role_ManufacturingPlanner");
	public static final String ROLE_PWC_POWER_PLANNER = PropertyUtil.getSchemaProperty("role_PWC_IPPPowerPlanner");
	
	// Vault
    public static final String PRODUCTION_VAULT = PropertyUtil.getSchemaProperty("vault_eServiceProduction");
    public static final String ADMINISTRATION_VAULT = PropertyUtil.getSchemaProperty("vault_eServiceAdministration");

    //Super User
	public static final String SUPER_USER = PropertyUtil.getSchemaProperty("person_UserAgent");
	
	// PDF Format
	public static final String FORMAT_PDF = PropertyUtil.getSchemaProperty("format_PDF");
	
	public static final String POLICY_RFA_SUB_OBJECT = PropertyUtil.getSchemaProperty("policy_PWC_RFASubObject");
	
	// Types of user assignment
	public static final String USER_GROUP = "group";
	public static final String USER_ROLE = "role";
}
