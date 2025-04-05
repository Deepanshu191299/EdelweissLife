package in.edelweiss.tokio.constants;

import java.util.Arrays;
import java.util.List;

public class EdelweissCommonConstants {
	
	private EdelweissCommonConstants() {
	    throw new IllegalStateException("EdelweissCommonConstants >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	}


	// Page Custom Field Key Name
	public static final String PAGE_CUSTOM_FIELD_PRODUCT_ID = "product_id";
	public static final String PAGE_CUSTOM_FIELD_PRODUCT_CODE = "product_code";
	public static final String PAGE_CUSTOM_FIELD_PRODUCT_NAME = "product_name";
	
	public static final String PRODUCT_ID_SERVICE_PARAM = "{PRODUCT_ID}";
	
	public static final String ACCESS_TOKEN = "access_token";
	
	public static final String EXPIRE_IN = "expires_in";
	
	// Edelweiss Picklist's Names Constants
	public static final String MASTER_POLICY_OPTIONS_PICKLIST_EXTREF_CODE = "POLICY_OPTIONS_MASTER";

	public static final String PAYMENT_OPTIONS_MASTER_PICKLIST_EXTREF_CODE = "PAYMENT_OPTIONS_MASTER";

	public static final String SWP_MASTER_PICKLIST_EXTREF_CODE = "SWP_MASTER";
	
	public static final String WRP_POLICY_TERM_PICKLIST_EXTREF_CODE = "WRP_POLICY_TERM";
	
	public static final String FUND_MANAGEMENT_MASTER_PICKLIST_EXTREF_CODE = "FUND_MANAGEMENT_MASTER";
	
	public static final String INVESTING_FOR_MASTER_PICKLIST_EXTREF_CODE = "INVESTING_FOR_MASTER";
	
	public static final String RISK_APPETITE_PICKLIST_ERC = "RISK_APPETITE";
	
	public static final String YOUR_INCOME_MASTER_PICKLIST_ERC = "YOUR_INCOME_MASTER";
	
	public static final String INVESTMENT_OBJECTIVE_PICKLIST_ERC = "investmentObjective";
	
	public static final String OCCUPATION_OPTION_MASTER_PICKLIST_ERC = "OCCUPATION_OPTION_MASTER";
	
	public static final String EDUCATION_OPTION_MASTER_PICKLIST_ERC = "EDUCATION_OPTION_MASTER";

	// GCAP Payout Option Picklist
	public static final String GCAP_PAYOUT_OPTION_PICKLIST_EXTREF_CODE = "GCAP_PAYOUT_OPTION";
	
	// Wealth Secure Plus Policy Term Picklist
	public static final String WSP_POLICY_TERM_PICKLIST_EXTREF_CODE = "WSP_POLICY_TERM";
	
	// saral jeevan bima
	public static final String SJB_POLICY_TERM_PICKLIST_EXTREF_CODE_STRING = "SJB_POLICY_TERM";
	
	// Wealth Secure Plus Pay Term Picklist
	public static final String WSP_PAY_FOR_PICKLIST_EXTREF_CODE = "WSP_PAY_FOR";
	
	public static final String FUND_WITHDRAWAL_FREQUENCY_MASTER_PICKLIST_EXTREF_CODE = "FUND_WITHDRAWAL_FREQUENCY_MASTER";
	
	public static final String SWP_WITHDRAWAL_YEAR_MASTER_PICKLIST_EXTREF_CODE = "SWP_WITHDRAWAL_YEAR_MASTER";
	
	public static final String FUND_WITHDRAWAL_PERCENTAGE_MASTER_PICKLIST_EXTREF_CODE = "FUND_WITHDRAWAL_PERCENTAGE_MASTER";
	
	public static final String WEALTH_PLUS_POLICY_TERM = "WEALTH_PLUS_POLICY_TERM";
	
	public static final String WEALTH_PLUS_PAY_FOR = "WEALTH_PLUS_PAY_FOR";
	
	public static final String CAPITAL_SECURE_PLUS_POLICY_TERM = "CAPITAL_SECURE_PLUS_POLICY_TERM";
	
	public static final String CAPITAL_SECURE_PLUS_PAY_FOR = "CAPITAL_SECURE_PLUS_PAY_FOR";
	
	public static final String ACTIVE_INCOME_PAY_FOR = "ACTIVE_INCOME_PAY_FOR";
	
	public static final String AIP_INCOME_OPTION = "AIP_INCOME_OPTION";
	
	public static final String AIP_FAMILY_INCOME_BENEFIT = "AIP_FAMILY_INCOME_BENEFIT";
	
	public static final String AIP_MATURITY_AGE = "AIP_MATURITY_AGE";
	
	public static final String IS_ERROR = "isError";
	
	public static final String ERRORS = "errors";
	
	public static final String INTERNAL_ERROR = "internalError";
	
	public static final String ERROR_KEY = "errorKey";
	
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	public static final String APPLICATION_NUMBER = "Application_Number";
	
	public static final String POLICY_NUMBER = "Policy_Number";
	
	public static final String STATUS = "Status";
	
	public static final String SUCCESS = "success";
	
	public static final String LMS_QUOTE_ID = "LMSQuoteId";
	
	public static final String CHILD_NAME = "ChildName";
	
	public static final String CHILD_DOB = "ChildDob";
	
	public static final String AGE = "age";
	
	public static final String INVESTING_FOR = "investingFor";
	
	public static final String ILLUSTRATION_URL =  "illustrationURL";
	
	public static final String SUMMARY_DATA = "summaryData";
	
	public static final String PRODUCT_NAME = "productName";
	
	public static final String SELECTIVE_PICKLIST = "selectivePicklist";
	
	public static final String PAYMENT_FREQUENCY_PICKLIST = "paymentFrequencyPicklist";
	
	public static final String GCAP_PAYOUT_OPTION_PICKLIST = "gcapPayoutOptionPicklist";
	
	public static final String SWP_MASTER_PICKLIST = "swpMasterPicklist";
	
	public static final String FUND_MANAGEMENT_PICKLIST = "fundManagementPicklist";
	
	public static final String POLICY_OPTION_PICKLIST = "policyOptionPicklist";
	
	public static final String INVESTING_FOR_PICKLIST = "investingForPicklist";
	
	public static final String FUND_WITHDRAWAL_PER_PICKLIST_DATA = "fundWithdrawalPercentPicklistData";
	
	public static final String SWP_WITHDRAWAL_YEAR_PICKLIST_DATA = "swpWithdrawalYearPicklistData";
	
	public static final String FUND_WITHDRAWAL_FREQ_PICKLIST_DATA = "fundWithdrawalFrequencyPicklistData";
	
	public static final String RISK_APPETITE_PICKLIST_DATA = "riskAppetitePickListData";
	
	public static final String LEAD_FORM_TYPE_RECCOMENDATION = "Reccomendation";
	
	public static final String PROPOSER_LA_RELATION = "ProposerLARelation";
	
	// Zindagi Protect Picklist External Reference Code
	public static final String ZP_ANNUAL_INCOME_MASTER_PICKLIST_ERC = "ZP_ANNUAL_INCOME_MASTER";
	public static final String ZP_PAY_FOR_PICKLIST = "ZP_PAY_FOR";
	public static final String ZP_INCOME_TYPE_PICKLIST = "ZP_INCOME_TYPE";
	public static final String OCCUPATION_MASTER = "OCCUPATION_MASTER";
	public static final String CHILD_BENEFIT_SA_PERCENT_PICKLIST = "CHILD_BENEFIT_SA_PERCENT_MASTER";

	// saral jeevan bima picklist
	public static final String YOUR_OCCUPATION = "YOUR_OCCUPATION";
	public static final String YOUR_EDUCATIONAL_QUALIFICATION="YOUR_EDUCATIONAL_QUALIFICATION";
	public static final String SJB_EDUCATIONQULIFICATION = "SJB_EDUCATIONQUALIFICATION"; 
	
	public static final String YOUR_NATURE_OF_DUTY = "YOUR_NATURE_OF_DUTY";
	public static final String YOUR_SPOUSE = "YOUR_SPOUSE";
	public static final String SPOUSE_OCCUPATION = "SPOUSE_OCCUPATION";
	public static final String SMOKER ="SMOKER";
	public static final String SJB_GENDER = "SJB_GENDER";
	
	// saral jeevan bima picklist
	public static final String GFS_GENDER = "GFS_GENDER";
	//LMS Lead Param
	public static final String SPOUSE_NAME_KEY = "SpouseName";
	public static final String SPOUSE_DOB_KEY = "SpouseDob";
	
	//LMS Request Interest Sessions Params
	public static final String LEAD_FORM_TYPE = "leadFormType";
	public static final String RECOMMENDED_OBJECTIVE_CATEGORY = "Recommended_Objective_Category";
	public static final String RECOMMENDED_OBJECTIVE = "Recommended_Objective";
	public static final String RECOMMENDED_ANNUAL_INCOME = "Recommended_Annual_Income";
	public static final String RECOMMENDED_PRODUCT = "Recommended_Product";
	public static final String PERSON = "Persona";
	public static final String LEAD_FORM_PRODUCT_CATEGORY = "Lead_Form_Product_Category";
	public static final String LEAD_FORM_PRODUCT_CODE = "Lead_Form_Product_Code";
	public static final String STAGE_INTEREST_SESSION = "Stage";
	public static final String SITE_SECTION = "Site_Section";
	public static final String INTEREST_SESSION_STAGE_GET_STARTED ="Get Started";
	public static final String SITE_SECTION_PRE_QUOTE ="Pre-Quote";
	public static final String SITE_SECTION_SUMMARY ="Summary";
	public static final String SITE_SECTION_PRODUCT_PAGE ="Product Page";
	public static final String STAGE_INTEREST_SESSION_PRE_QUOTE = "Pre-Quote";
	
	public static final String QUOTE_STAGE_CUSTOMISE ="Customise";
	public static final String QUOTE_STAGE_PROPOSAL_FORM = "Proposal Form";
	public static final String QUOTE_STAGE_IPS = "Investment Product Suggession";
	public static final String REQUEST_TYPE = "requestType";
	
	//LMS Request Quote Params
	public static final String PRODUCT_CODE_KEY = "Product_Code";
	public static final String QUOTE_STAGE ="QuoteStage";
	public static final String BI_PDF_PATH = "BI_PDF_PATH";
	public static final String TOTAL_PREMIUM_AMOUNT = "TotalPremiumAmount";
	public static final String BASE_PREMIUM_AMOUNT = "BasePremiumAmount";
	public static final String SUM_ASSURED = "SumAssured";
	public static final String PREMIUM_PAYMENT_FREQUENCY = "PremiumPaymentFrequency";
	public static final String POLICY_TERM = "PolicyTerm";
	public static final String PREMIUM_PAYMENT_TERM = "PremiumPaymentTerm";
	public static final String PAYOUT_OPTIONS="PayoutOptions";
	public static final String BI_QUATATION_NUMBER = "BIQuotationNumber";
	public static final String PRODUCT_NAME_QUOTE = "ProductName";
	public static final String IS_LA_PR_SAME_YN = "IS_LA_PR_Same_YN";
	public static final String LITTLE_CHAMP_BENEFIT = "LittleChampBenefit_YN";
	public static final String RISING_START_BENEFIT = "RisingStarBenefit_YN";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String TOTAL_PREMIUM_AMOUNT_WO_TAX = "TotalPremiumAmountWoTax";
	public static final String BASE_PREMIUM_AMOUNT_WO_TAX = "BasePremiumAmountWoTax";
	public static final String EQUITY_LARGE_CAP_FUND= "EquityLargeCapFund";
	public static final String EQUITY_TOP_250_FUND = "EquityTop250Fund";
	public static final String BOND_FUND= "BondFund";
	public static final String MONEY_MARKET_FUND = "MoneyMaketFund";
	public static final String MANAGED_FUND = "ManagedFund";
	public static final String EQUITY_MID_CAP_FUND = "EquityMidCapFund";
	public static final String GILT_FUND= "GILTFund";
	public static final String EQUITY_BLUE_CHIP_FUND = "EquityBlueChipFund";
	public static final String LONG_TERM_BOND_FUND= "LongTermBondFund";
	public static final String POLICY_OPTION = "PolicyOption";
	public static final String SWP_YN = "SWP_YN";
	public static final String SWP_PAYABLE = "SWPPayable";
	public static final String ALT_PHONE_NUMBER= "AlternatePhone";
	public static final String LA_PROPOSER_RELATION= "LAProposerRelation";
	public static final String FAMILY_INCOME_BENEFIT_YN= "FamilyIncomeBenefit_YN";
	public static final String PLAN_OPTIONS= "PlanOptions";
	public static final String PAYOUT_PERIOD_YEARS = "PayoutPeriodYears";
	
	public static final String ADDITIONAL_BENEFIT = "Additional_Benefits";
	//Riders
	public static final String ADB_RIDER_YN = "ADBRider_YN";
	public static final String ADB_RIDER_PT = "ADBRiderPT";
	public static final String ADB_RIDER_PPT = "ADBRiderPPT";
	public static final String ADB_RIDER_SA = "ADBRiderSA";
	public static final String ADB_RIDER_AMOUNT = "ADBRiderAmount";
	public static final String ADB_RIDER_AMOUNT_WO_TAX = "ADBRiderAmountWoTax";

	public static final String ATPD_RIDER_YN = "ATPDRider_YN";
	public static final String ATPD_RIDER_PT = "ATPDRiderPT";
	public static final String ATPD_RIDER_PPT = "ATPDRiderPPT";
	public static final String ATPD_RIDER_SA = "ATPDRiderSA";
	public static final String ATPD_RIDER_AMOUNT = "ATPDRiderAmount";
	public static final String ATPD_RIDER_AMOUNT_WO_TAX = "ATPDRiderAmountWoTax";

	public static final String WOP_RIDER_YN = "WaiverOfPremiumRider_YN";
	public static final String WOP_RIDER_PT = "WaiverOfPremiumRiderPT";
	public static final String WOP_RIDER_PPT = "WaiverOfPremiumRiderPPT";
	public static final String WOP_RIDER_SA = "WaiverOfPremiumRiderSA";
	public static final String WOP_RIDER_AMOUNT = "WaiverOfPremiumRiderAmount";
	public static final String WOP_RIDER_AMOUNT_WO_TAX = "WaiverOfPremiumRiderAmountWoTax";
	
	public static final String PWB_RIDER_YN = "PayorWaiverBenefitRider_YN";
	public static final String PWB_RIDER_PT = "PayorWaiverBenefitRiderPT";
	public static final String PWB_RIDER_PPT = "PayorWaiverBenefitRiderPPT";
	public static final String PWB_RIDER_SA = "PayorWaiverBenefitRiderSA";
	public static final String PWB_RIDER_AMOUNT = "PayorWaiverBenefitRiderAmount";
	public static final String PWB_RIDER_AMOUNT_WO_TAX = "PayorWaiverBenefitRiderAmountWoTax";
	public static final String PWB_RIDER_ON_DEATH_YN = "PWbRiderOnDeath_YN";
	public static final String PWB_RIDER_CIATPD_YN = "PWbRiderCIATPD_YN";
	public static final String PWB_RIDER_DEATH_CIATPD = "PWBRiderDeathCIATPD_YN";
	
	public static final String CI_RIDER_YN = "CriticalIllnessRider_YN";
	public static final String CI_RIDER_PT = "CriticalIllnessRiderPT";
	public static final String CI_RIDER_PPT = "CriticalIllnessRiderPPT";
	public static final String CI_RIDER_SA = "CriticalIllnessRiderSA";
	public static final String CI_RIDER_AMOUNT = "CriticalIllnessRiderAmount";
	public static final String CI_RIDER_AMOUNT_WO_TAX = "CriticalIllnessRiderAmountWoTax";
	
	public static final String IB_RIDER_YN = "IncomeBenefitRider_YN";
	public static final String IB_RIDER_PT = "IncomeBenefitRiderPT";
	public static final String IB_RIDER_PPT = "IncomeBenefitRiderPPT";
	public static final String IB_RIDER_SA = "IncomeBenefitRiderSA";
	public static final String IB_RIDER_AMOUNT = "IncomeBenefitRiderAmount";
	public static final String IB_RIDER_AMOUNT_WO_TAX = "IncomeBenefitRiderAmountWoTax";
	
	public static final String SWP_YES_VALUE = "5";
	public static final String SWP_NO_VALUE = "6";
	
	public static final String GENERATE_LEAD_API_URL = "generate.lead.api.url";
	public static final String GET_STARTED="Get Started";
	public static final String PRODUCT_CATEGORY = "productCategory";
	public static final String PRODUCT_RELATIONSHIP = "productMetaDataRelationship";
	public static final String ING_PLAN_SUB_TYPE = "iNGPlanSubType";
	public static final String RECCOMENDATION = "Reccomendation";
	public static final String YOUR_INCOME = "yourIncome";
	
	// Product Names Constants
	public static final String PRODUCT_NAME_WEALTH_PLUS = "Wealth Plus";
	public static final String PRODUCT_NAME_WEALTH_RISE_PLUS = "Wealth Rise Plus";
	public static final String PRODUCT_NAME_WEALTH_SECURE_PLUS = "Wealth Secure Plus";
	public static final String PRODUCT_NAME_GUARANTEED_SAVINGS_STAR = "Guaranteed Savings STAR";
	public static final String PRODUCT_NAME_CAPITAL_SECURE_PLUS = "Capital Secure Plus";
	public static final String PRODUCT_NAME_FLEXI_SAVINGS_PLAN = "Flexi Savings Plan";
	public static final String PRODUCT_NAME_GCAP = "GCAP";
	public static final String PRODUCT_NAME_ZINDAGI_PROTECT = "Zindagi Protect Plus";
	
	public static final String BASIC_INVESTING_FOR = "basicInvestingFor";
	public static final String IS_LITTLE_CHAMP_OPTED = "isLittleChampBenefitOpted";
	public static final String IS_RISING_STAR_OPTED = "isRisingStarBenefitOpted";
	public static final String CUSTOMER_FAMILY_DETAILS_ID = "customerFamilyDetailsId";
	public static final String CUSTOMER_POLICY_DETAILS_ID = "customerPolicyDetailsId";
	public static final String CUSTOMER_FUND_ALLOCATION_DETAILS_ID = "customerFundAllocationDetailId";
	public static final String CUSTOMER_ENQUIRY_ID = "customerEnquiryId";
	public static final String CUSTOMER_INVESTMENT_DETAILS_ID = "customerInvestmentDetailsId";
	public static final String PRODUCT_CODE_PARAM = "productCode";
	public static final String ASSURED_FULL_NAME = "assuranceFullName";
	public static final String ASSURED_DATE_OF_BIRTH = "assuranceDob";
	public static final String ASSURED_GENDER = "assuredGender";
	public static final String ASSURED_RELATION = "assuredRelation";
	public static final String REMOVE_LITTLE_CHAMP = "removeLittleChamp";
	public static final String NO_IPS = "No";
	public static final String REMOVE_RISING_STAR = "removeRisingStar";
	
	//Product Code Constants
	public static final String PRODUCT_CODE_WEALTH_GAIN_PLUS = "40030";
	public static final String PRODUCT_CODE_SARAL_JEEVAN_BIMA = "40039";
	
	//PGI product field 
	public static final String GET_INCOME_FOR_PICKLIST_EXTREF_CODE = "GET_INCOME_FOR";
	public static final String FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE = "FAMILY_INCOME_BENEFIT";
	public static final String LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE = "LUMP_SUM_BENEFIT";
	public static final String INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE = "INCOME_PAYOUT_FREQUENCY";
	public static final String IS_EDELWEISS_EMPLOYEE_PICKLIST_EXTREF_CODE = "IS_EDELWEISS_EMPLOYEE";
	public static final String MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE = "MATURITY_PAYOUT_OPTION";
	
	//GIS Product field
	public static final String GIS_INCOME_START_POINT_PICKLIST_EXTREF_CODE = "GIS_INCOME_START_POINT";
	public static final String GIS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE = "GIS_LUMP_SUM_BENEFIT";
	public static final String GIS_INCOME_DURATION_PICKLIST_EXTREF_CODE = "GIS_INCOME_DURATION";
	public static final String GIS_INCOME_BENEFIT_PAYOUT_TYPE_PICKLIST_EXTREF_CODE = "GIS_INCOME_BENEFIT_PAYOUT_TYPE";
	
	//IB Product fields
	public static final String IB_PAYOUT_OPTION_PICKLIST_EXTREF_CODE = "IB_PAYOUT_OPTION";
	public static final String IB_PAYOUT_PERIOD_PICKLIST_EXTREF_CODE = "IB_PAYOUT_PERIOD";
	
	//PGI picklist data
	public static final String MASTER_POLICY_OPTIONS = "masterPolicyOptionPicklist";
	public static final String FAMILY_INCOME_BENEFIT = "familyIncomeBenefitPicklist";
	public static final String LUMP_SUM_BENEFIT = "lumpSumbenefitPicklist";
	public static final String IS_EDELWEISS_EMPLOYEE = "isEdelweissEmployeePicklist";
	public static final String INCOME_PAYOUT_FREQUENCY = "incomePayoutFrequencyPicklist";
	public static final String MATURITY_PAYOUT_OPTION = "maturityPayoutOptionPicklist";
		
	public static final String LIFERAY_RESPONSE_MESSAGE_KEY = "liferayResponseMessage";
	public static final String PRODUCT_INVESTMENT_AMOUNT_RELS_DATA = "productInvestmentAmountRelsData";
	public static final String PRODUCT_INVESTMENT_AMOUNT_RELS = "productInvestmentAmountRels";
	
	public static final String STATUS_KEY = "status";
	public static final String STATUS_ERROR = "error";
	public static final String DATA_KEY = "data";
	public static final String MESSAGE_KEY = "message";
	public static final String IS_LEAD_ID_EXIST = "isLeadIdExist";
	public static final String MESSAGE_LEAD_NOT_EXIST = "Lead ID does not exist";
	
	public static final String PRODUCT_LIST_REQUEST_DATA_PARAM = "productListRequestData";
	public static final String PRODUCT_MODIFICATION_REQUEST_DATA_PARAM = "productModificationRequestData";

	/* Customize Screen Param START*/
	public static final String INVESTMENT_AMOUNT_PARAM = "investmentAmount";
	public static final String INVESTING_FOR_PARAM = "investingFor";
	public static final String POLICY_TERM_PARAM = "policyTerm";
	public static final String PAY_FOR_PARAM = "payFor";
	public static final String PAYMENT_OPTION_PARAM = "paymentOption";
	public static final String TOTAL_RETURNS_VALUE = "totalReturnsValue";
	public static final String PREMIUM_AMOUNT = "premiumAmount";
	/* Customize Screen Param START*/
	
	public static final String PRODUCT_METADATA_RELS_DATA = "productMetaDataRelsData";
	
	
	public static final String CUSTOMIZE_FEATURES_CARD = "customizeFeaturesCard";
	public static final String IMAGE_FIELD = "Image";
	public static final String CFC_NESTED_DATA = "cfcNestedData";
	
	// Customize Screen Contents URL Titles
	public static final String WEALTH_RISE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE = "wealth-rise-plus-product-customize-card";
	public static final String WEALTH_SECURE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE = "wealth-secure-plus-product-customize-card";
	public static final String WEALTH_PLUS_CUSTOMIZE_CONTENT_URL_TITLE = "wealth-plus-product-customize-card";
	public static final String WEALTH_GAIN_PLUS_CUSTOMIZE_CONTENT_URL_TITLE = "wealth-gain-plus-product-customize-card";
	public static final String CAPITAL_SECURE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE = "capital-secure-plus-product-customize-card";
	public static final String ACTIVE_INCOM_CUSTOMIZE_CONTENT_URL_TITLE = "active-income-product-customize-card";
	public static final String PREMIER_GUARANTEED_INCOME_CUSTOMIZE_CONTENT_URL_TITLE = "premium-guaranteed-income-product-customize-card";
	public static final String FLEXI_SAVINGS_PLAN_CUSTOMIZE_CONTENT_URL_TITLE = "flexi-savings-plan-product-customize-card";
	public static final String GUARANTEED_SAVINGS_STAR_CUSTOMIZE_CONTENT_URL_TITLE = "guaranteed-savings-star-product-customize-card";
	public static final String GUARANTEED_INCOME_STAR_CUSTOMIZE_CONTENT_URL_TITLE = "guaranteed-income-star-product-customize-card";
	public static final String GUARANTEED_FLEXI_STAR_CUSTOMIZE_CONTENT_URL_TITLE = "guaranteed-flexi-star-product-customize-card";
	public static final String GCAP_CUSTOMIZE_CONTENT_URL_TITLE = "gcap-product-customize-card";
	public static final String INCOM_BUILDER_CUSTOMIZE_CONTENT_URL_TITLE = "income-builder-product-customize-card";
	public static final String ZINDAGI_PROTECT_CUSTOMIZE_CONTENT_URL_TITLE = "zindagi-protect-product-customize-card";
	public static final String PREMIUM_GUARANTEED_STAR_CUSTOMIZE_CONTENT_URL_TITLE = "premium-guaranteed-star-product-customize-card";
	
	public static final String LIFERAY_NESTED_DATA = "liferayProductNestedData";
	public static final String LIFERAY_PRODUCT_JSON_CONF = "productJsonConfiguration";
	public static final String RIDERS = "riders";
	public static final String RIDER_VALIDATIONS = "riderValidations";
	public static final String PRODUCT_POLICY_FOR_REL = "productPolicyForRel";
	
	public static final String PAYOR_WAIVER_BENEFIT_OPTIONS_DATA = "payorWaiverBenefitOptions";
	public static final String LEAD_STATUS_RES_PARAM = "LeadStatus";
	
	public static final String ACTIVE_INCOME_PLAN_FAMILY_INCOME_BENEFIT = "activeIncomePlanFamilyBenefitIncome";
	public static final String ACTIVE_INCOME_PLAN_MATUIRTY_AGE = "activeIncomePlanMaturityAge";
	public static final String ACTIVE_INCOME_PLAN_INCOME_OPTION = "activeIncomePlanIncomeOption";
	
	public static final String GIS_INCOME_START_POINT = "gisIncomeStartPointPicklistData";
	public static final String GIS_LUMP_SUM_BENEFI = "gisLumpSumBenefitPicklistData";
	public static final String GIS_INCOME_DURATION = "gisIncomeDurationPicklistData";
	public static final String GIS_INCOME_BENEFIT = "gisIncomeBenefitPayoutTypePicklistData";
	
	public static final String IB_PAYOUT_PERIOD = "ibPayoutPeriodPicklistData";
	public static final String IB_PAYOUT_OPTION = "ibPayoutOptionPicklistData";
	
	public static final String DATE_REGEX = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
	
	public static final List UTM_CAMPAIGN_PARAMS = Arrays.asList("utm_source","utm_term","utm_creative","utm_campaign","utm_device","utm_content","utm_adgroup","utm_placement","utm_medium","gclid");
}