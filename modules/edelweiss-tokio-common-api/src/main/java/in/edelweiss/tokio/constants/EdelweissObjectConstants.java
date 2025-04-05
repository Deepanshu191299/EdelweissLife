package in.edelweiss.tokio.constants;

public class EdelweissObjectConstants {
	
	private EdelweissObjectConstants() {
	    throw new IllegalStateException("EdelweissObjectConstants >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	}

	public static final String CREATOR = "creator";
	public static final String EXTERNAL_REFERENCE_CODE = "externalReferenceCode";
	public static final String ID = "id";
	public static final String STATUS = "status";
	public static final String ANNUAL_INCOME = "annualIncome";
	public static final String COUNTRY_CODE = "countryCode";
	public static final String CREATE_DATE = "createDate";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String EDUCATION_QUALIFICATION = "educationQualification";
	public static final String EDUCATION = "Education";
	public static final String EMAIL = "email";
	public static final String FULL_NAME = "fullName";
	public static final String GENDER = "gender";
	public static final String INVESTMENT_OBJECTIVE = "investmentObjective";
	public static final String INVESTMENT_OBJECTIVE_CATEGORY = "investmentObjectiveCategory";
	public static final String PRODUCT_CATEGORY = "productCategory";
	public static final String ISNRI = "isNRI";
	public static final String LEAD_ID = "leadId";
	public static final String APP_NUMBER = "applicationNumber";
	public static final String POLICY_NUMBER = "policyNumber";
	public static final String PARTNER_COOKIE = "isptnr";
	public static final String LEAD_STATUS = "leadStatus";
	public static final String LMS_INTEREST_ID = "lmsInterestId";
	public static final String LMS_QUOTE_ID = "lmsQuoteId";
	public static final String MOBILE_NUMBER = "mobileNumber";
	public static final String INDIA_STD = "indiaSTD";
	public static final String MODIFIED_DATE = "modifiedDate";
	public static final String NATURE_OF_DUTY = "natureOfDuty";
	public static final String NDNC_CONSENT = "nDNCConsent";
	public static final String NRI_MOBILE_NUMBER = "nRIMobileNumber";
	public static final String OCCUPATION = "occupation";
	public static final String OI_SUM_ASSURED = "oISumAssured";
	public static final String OTHER_INSURANCE = "otherInsurance";
	public static final String PINCODE = "pincode";
	public static final String PERMANENT_PINCODE = "PermanentPinCode";
	public static final String PRODUCT_ID = "productId";
	public static final String O_PRODUCT_NAME = "productName";
	public static final String O_QUOTE_STAGE = "quoteStage";
	public static final String RISK_APPETITE = "riskAppetite";
	public static final String SMOKER = "smoker";
	public static final String SUITABILITY_CONSENT = "suitabilityConsent";
	public static final String FAMILY_ID = "familyId";
	public static final String LMS_QUOTE_INTEREST_ID = "LMSInerestId";
	public static final String LMS_QUOTE_JOURNEY_ID = "LMSQuoteId";
	public static final String INDIAN = "Indian";
	public static final String NAME = "name";
	public static final String PAGES = "pages";
	public static final String DEFAULT_LANGUAGE_ID = "defaultLanguageId";
	public static final String TITLE = "title";
	public static final String FIELDS = "fields";
	public static final String TYPE = "type";
	public static final String TEXT = "text";
	public static final String NUMERIC = "numeric";
	public static final String FIELD_REFERENCE = "fieldReference";
	public static final String PLACEHOLDER = "placeholder";
	public static final String LABEL = "label";
	public static final String HIDE_FIELD = "hideField";
	public static final String REQUIRED = "required";
	public static final String REQUIRED_ERROR_MESSAGE = "requiredErrorMessage";
	public static final String RADIO = "radio";
	public static final String OPTIONS = "options";
	public static final String SELECT = "select";
	public static final String TIP = "tip";
	public static final String PARTNER_SOURCE = "partnerSource";
	
	// saral jeevan bima three extra fields 
	public static final String SPOUSE = "spouseParentName";
	public static final String SPOUSE_PARENT_RELATION = "spouseParentRelation";
	public static final String SPOUSE_OCCUPATION = "spouseParentOccupation";
	public static final String SPOUSE_SUMASSURED = "spouseParentSumassured";
	public static final String TOTAL_SUM_ASSURED = "totalSumAssured";
	public static final String RELATION = "Relation";
	public static final String LIFE_COVER = "lifeCover";
	
	public static final String C_ID = "cId";
	public static final String CP_ID = "cpId";
	public static final String SOURCE_OF_TRAFFIC = "sourceOfTraffic";
	public static final String V_ID = "vId";
	public static final String CAMPAIGN_ID = "campaign";
	public static final String VISITOR_ID = "visitorId";

	public static final String PHONE = "Phone";
	public static final String DOB = "Dob";
	public static final String INCOME = "Income";
	public static final String TOBACCO = "Tobacco";
	public static final String MARRIED = "Married";
	public static final String CUST_MARRIED = "married";
	public static final String SPOUSE_NAME = "SpouseName";
	public static final String SPOUSE_DOB = "SpouseDob";
	public static final String SPOUSE_SMOKE = "SpouseSmoke";
	public static final String CHILD_NAME = "ChildName";
	public static final String CHILD_DOB = "ChildDob";
	
	public static final String FAMILY_SPOUSE_NAME = "spouseName";
	public static final String FAMILY_SPOUSE_DOB = "spouseDateOfBirth";
	public static final String FAMILY_SPOUSE_SMOKE = "spouseSmoke";
	public static final String FAMILY_CHILD = "child";
	public static final String FAMILY_CHILD_DOB = "childDateOfBirth";
	
	public static final String FAMILY_OCCUPATION = "familyOccupation";
	public static final String FAMILY_TOTAL_SUM_ASSURED = "familyTotalSumAssured";
	
	public static final String GOAL_ID = "GoalId";
	public static final String LIFE_ASSURED_AGE = "LifeAssuredAge";
	public static final String LIFE_ASSURED_GENDER = "LifeAssuredGender";
	public static final String IS_SMOKE = "isSmoker";
	public static final String MARITAL_STATUS_IS = "MaritalStatusId";
	public static final String SPOUSE_AGE = "SpouseAge";
	public static final String SPOUSE_GENDER = "SpouseGender";
	public static final String IS_SPOUSE_SMOKER = "isSpouseSmoker";
	public static final String MODEL_INPUT = "ModeInput";
	public static final String PROPOSER_AGE = "ProposerAge";
	public static final String PROPOSER_GENDER = "ProposerGender";
	public static final String SAME_PROPOSER = "SameProposer";
	public static final String UNDERWRITING = "Underwriting";
	public static final String FILTER_PRODUCT_ID = "FilterProductID";
	public static final String QUOTATION_ID = "quotationId";

	// Start Liferay Objects Constants

	public static final String PRODUCT_JSON_CONFIGURATION_SERVICE_URL = "/o/c/productjsonconfigurations/?filter=productId%20eq%20%27${productId}%27";
	public static final String LIFERAY_PRODUCT_ENQUIRY_URL = "/o/c/customerenquiries/";
	public static final String LIFERAY_CUSTOMER_RELATION_URL = "/o/c/customerfamilydetailses/";
	public static final String LIFERAY_PRODUCT_PLANS_RIDERS_URL = "/o/c/productsmasters/${productMasterId}?nestedFields=plans,riders,features&nestedFieldsDepth=2";
	public static final String LIFERAY_PRODUCT_RIDERS_URL = "/o/c/productsmasters/${productMasterId}?nestedFields=riders,riderValidations&nestedFieldsDepth=2";
	public static final String LIFERAY_DIRECT_PRODUCT_RIDERS_URL = "/o/c/productridervalidations/?filter=r_riderValidations_c_productsMasterId%20eq%20%27${productMasterId}%27";
	public static final String LIFERAY_RIDERS_URL = "/o/c/productriders/${productRiderId}";
	public static final String LIFERAY_PRODUCT_MASTER_NEASTED_URL = "/o/c/productsmasters/${productMasterId}?nestedFields=plans,riders,features&nestedFieldsDepth=2";
	public static final String GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS = "?nestedFields=productMetaDataRelationship";
	public static final String GET_PRODUCT_MASTER_RELATIONAL_API = "/o/c/productsmasters/{0}";
	public static final String GET_ERC_PRODUCT_MASTER_RELATIONAL_API = "/o/c/productsmasters/by-external-reference-code/{0}";

	// End Liferay ObjectConstants


	public static final String ACCESS_TOKEN = "access_token";
	public static final String EDELWEISS_PRODUCT_LIST_API_URL = "get.product.list.api.url";
	// End custom properties constants

	// Start Expando Column Constants
	public static final String PRODUCT_NAME = "product_name";
	public static final String PRODUCT_CODE = "product_code";
	public static final String COLUMN_PRODUCT_ID = "product_id";
	public static final String LEAD_FORM_JSP = "lead_form_jsp";
	public static final String BUY_FORM_JSP = "buy_form_jsp";
	public static final String REDIRECTION_URL = "redirection_url";
	public static final String SHOW_THANK_YOU_MESSAGE = "show_thank_you_message";
	// End Expando Column Constants

	public static final String EDELWEISS_WEB_SALES = "EdelweissWebsales";
	public static final String WEB_URL = "Web_url";
	public static final String BUY_URL = "/buy";
	public static final String RESPONSE_DATA = "responseData";
	public static final String LMS_ID = "LMSId";

	public static final String LMS_SITE_SECTION = "Customize";

	public static final String UPDATE_CUSTOMER_DATA = "/o/c/customerenquiries/by-external-reference-code/{referenceCode}";
	public static final String PUT_CUSTOMER_DATA = "/o/c/customerenquiries/{customerEnquiryId}";

	public static final String CUSTOMER_INVESTMENT_DETAILS_BASE_URL = "/o/c/customerinvestmentdetails/";
	public static final String PUT_CUSTOMER_INVESTMENT_DETAILS_URL = "/o/c/customerinvestmentdetails/{customerInvestmentDetailId}";
	
	public static final String MULTIPLE_LOGINS_BLOCK_BASE_URL = "/o/c/multipleloginblocks/";
	public static final String PUT_MULTIPLE_LOGINS_DETAILS_URL = "/o/c/multipleloginblocks/{policyNumber}";
	public static final String MULTIPLE_LOGINS_DETAILS_ID_PARAM = "{policyNumber}";
	
	public static final String CSB_INTEGRATION_BASE_URL = "/o/c/csbintegrations/";
	public static final String GET_CSB_INTEGRATION_DETAILS_URL = "/o/c/csbintegrations/{appId}";
	public static final String GET_CSB_BY_ENC_APPID_INTEGRATION_DETAILS_URL = "/o/c/csbintegrations/?search={appId}";
	public static final String GET_CSB_INTEGRATION_DETAILS_ID_PARAM = "{appId}" ;
	
	public static final String CSB_SECRETKEY = "secretKey";
	public static final String CSB_ENCREYTEDAPPID = "encryptedData";
	public static final String CSB_ACCOUNTNO = "cSBAccountNo";
	public static final String CSB_EMPLOYEEID	= "cSBEmployeeId";
	public static final String CSB_EMPLOYEENAME = "cSBEmployeeName";
	public static final String CSB_CLIENTNAME = "clientName";
	public static final String CLIENT_DOB = "clientDOB";
	public static final String CUSTOMER_ACCOUNTNO = "customerAccountNo";
	public static final String CUSTOMER_OCCUPATION = "occupation";
	public static final String BRANCH_NAME = "branchName";
	public static final String BRANCH_CODE = "branchCode";
	public static final String PIN_CODE = "pinCode";
	public static final String NEO_APPLICATIONNO = "neoApplicationNo";
	public static final String NOMINEE_NAME = "nomineeName";
	public static final String CUSTOMER_RELATION = "relation";
	public static final String CUSTOMER_MOBILE_NO = "mobileNo";
	public static final String EMAIL_ID = "emailId";
	public static final String PREMIUM = "premium";
	public static final String RISK_COVER_OPTED = "riskCoverOpted";
	public static final String MEDICATION = "medication";
	public static final String TREATMENT = "surgery";
	public static final String COVID = "covid";
	public static final String CUSTOMER_GENDER = "gender";
	public static final String CSB_INTEGRATION_STATUS = "csbIntegrationStatus";
	public static final String CSB_TRANSACTION_ID = "transactionid";
	public static final String AGE = "age";
	public static final String TRANSACTION_MONTH = "transactionMonth";
	public static final String TRANSACTION_DATE = "transactionDate";
	
	
	public static final String CUSTOMER_POLICY_DETAILS_BASE_URL = "/o/c/customerpolicydetails/";
	public static final String PUT_CUSTOMER_POLICY_DETAILS_URL = "/o/c/customerpolicydetails/{customerPolicyDetailId}";

	public static final String CUSTOMER_FUND_ALLOCATION_DETAILS_BASE_URL = "/o/c/customerfundallocationdetails/";
	public static final String PUT_CUSTOMER_FUND_ALLOCATION_DETAILS_URL = "/o/c/customerfundallocationdetails/{customerFundAllocationDetailId}";

	public static final String CUSTOMER_INVESTMENT_DETAILS_ID_PARAM = "{customerInvestmentDetailId}";
	public static final String CUSTOMER_FUND_ALLOCATION_DETAILS_ID_PARAM = "{customerFundAllocationDetailId}";
	public static final String CUSTOMER_POLICY_DETAILS_ID_PARAM = "{customerPolicyDetailId}";

	public static final String URL_FOR_CUSTOMER_FAMILY_DETAILS = "/o/c/customerfamilydetailses/";

	public static final String PRODUCT_RELS_SERVICE_URL = "/o/c/productsmasters/{PRODUCT_ID}?nestedFields=productPolicyOptionRel,productInvestingForRel,productFundManagementRel,productPaymentOptionRel,productDefaultValueRel";
	public static final String PRODUCT_MASTER_BASE_URL = "/o/c/productsmasters/";
	
	public static final String PRODUCT_ELIGIBILITY_RELATIONS = "/o/c/producteligibilityrelations?pageSize=-1";

	public static final String MYSELF = "Myself";
	public static final String FAMILY = "Family";
	public static final String ASSURED_FULL_NAME = "assuranceFullName";
	public static final String ASSURED_DATE_OF_BIRTH = "assuranceDob";
	public static final String ASSURED_RELATION = "assuredRelation";
	public static final String SMOKE = "smoke";
	public static final String LA_SMOKER_YN = "LA_Smoker_YN";
	public static final String RCUSTOMERFAMILYDETAILSRELCCUSTOMERENQUIRYID = "r_customerFamilyDetailsRel_c_customerEnquiryId";

	public static final String ITEMS = "items";

	public static final String FILTER_QUERY_PARAM = "?filter=";

	// Lr objects json values
	public static final String PREMIUM_PAYING_TERM = "premiumPayingTerm";
	public static final String TOTAL_RETURNS = "totalReturns";
	public static final String PAYOUT_OPTION = "payoutOption";
	public static final String FAMILY_INCOME_BENEFIT = "familyIncomeBenefit";
	public static final String POLICY_FOR = "policyFor";
	public static final String PAYOR_WAIVER_BENEFIT_OPTIONS = "PAYOR_WAIVER_BENEFIT_OPTIONS";
	public static final String LIFE_COVER_CONTINUATION_BENEFIT = "LIFE_COVER_CONTINUATION_BENEFIT";
	public static final String RIDER_ID = "riderId";
	
	public static final String SOURCE = "EdelweissWebsales";
	public static final String SCREEN = "1";
	public static final String QUOTE_DATA = "QuoteData";
	public static final String RESUME_URL = "resumeURL";
	public static final String PARTNER_ID = "partnerId";
	public static final String REFERENCE_ID = "?referenceId=";

	public static final String CUSTOMER_DROPOUT_DETAILS_URL = "/o/c/customerdropoutdetailses/";
	public static final String CUSTOMER_DROPOUT_DETAILS_PUT_URL = "/o/c/customerdropoutdetailses/{customerDropOutId}";

	public static final String CUSTOMER_DROPOUT_DETAILS_GET_URL = "/o/c/customerdropoutdetailses/";
	public static final String CUSTOMIZE_URL = "/customize";
	
	public static final String GENERATE_LEAD_API_URL = "generate.lead.api.url";
	public static final String LIFERAY_USERNAME = "liferay.username";
	public static final String LIFERAY_PASSWORD = "liferay.password";
	
	public static final String GET_CUSTOMER_ENQUIRY_RELATIONAL_API = "/o/c/customerenquiries/{0}";
	public static final String CUSTOMER_ENQUIRY_FAMILY_POLICY_FUND_REL_PARAM = "?nestedFields=customerFamilyDetailsRel,customerPolicyDetailsRel,customerFundAllocationDetailsRel,customerInvestmentDetailsRel";
	public static final String GET_CUSTOMER_ENQUIRY_RELATIONAL_DATA_QUERY_PARAMS = "?nestedFields=customerFamilyDetailsRel";
	public static final String GET_CUSTOMER_ENQUIRY_INVESTMENT_RELATIONAL_DATA_QUERY_PARAM = "?nestedFields=customerFamilyDetailsRel,customerInvestmentDetailsRel";
}
