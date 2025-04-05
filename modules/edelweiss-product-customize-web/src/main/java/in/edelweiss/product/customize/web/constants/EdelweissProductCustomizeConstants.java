package in.edelweiss.product.customize.web.constants;

import java.util.Date;

public class EdelweissProductCustomizeConstants {
	
	 private EdelweissProductCustomizeConstants() {
		    throw new IllegalStateException("EdelweissProductCustomizeConstants >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	 }
	 
	public static final long CURRENT_TIME_STAMP = new Date().getTime();

	public static final String WRP_GET_PT_PPT_REL_SERVICE_URL = "/o/c/wrppolicytermpolicyforrels/";
	
	public static final String PGI_GET_PO_PPT_GIF_PT_REL_SERVICE_URL = "/o/c/pgipoptpfgifrelations/?pageSize=-1";
	
	public static final String PGS_GET_PPT_PT_REL_SERVICE_URL = "/o/c/pgsptpfrelations/?pageSize=-1";
	
	public static final String PGS_GET_PO_PPT_GIF_PT_REL_SERVICE_URL = "/o/c/pgspoptpfgifrelations/?pageSize=-1";
	
	public static final String SJB_GET_PRODUCT_PLANS = "/o/c/productplans/";
	
	public static final String GIS_GET_PAY_FOR_INCOME_START_OPTION_REL_SERVICE_URL = "/o/c/gispayforincomestartpointrels/?pageSize=-1";
	
	public static final String IB_RELATIONAL_FIELDS_DATA_SERVICE_URL = "/o/c/ibdependentfieldsrelations/?pageSize=-1";
	
	public static final String GET_PRODUCT_POLICY_OPTIONS_REL_SERVICE_URL = "/o/c/productpolicyoptionrels/";

	public static final String GET_PRODUCT_INVESTING_FOR_REL_SERVICE_URL = "/o/c/productinvestingforrels/";

	public static final String GET_PRODUCT_PAYMENT_REL_SERVICE_URL = "/o/c/productpaymentrels/";

	public static final String GET_PRODUCT_FUND_MANAGEMENT_REL_SERVICE_URL = "/o/c/productfundmanagementrels/";

	public static final String GET_PRODUCTS_SERVICE_URL = "/o/c/productsmasters/";

	public static final String PRODUCT_REL_SERVICE_URL = "/o/c/productsmasters/{PRODUCT_ID}?nestedFields=productPolicyOptionRel,productInvestingForRel,productFundManagementRel,productPaymentOptionRel,productDefaultValueRel,productInvestmentAmountRel,productPolicyForRel,familyIncomeBenefitOptions,isyPtMapping,productValidations";

	public static final String DEFAULT_PRODUCT_VALUE_REL_SERVICE_URL = "/o/c/productsmasters/{PRODUCT_ID}?nestedFields=productDefaultValueRel";

	public static final String SUMMARY_REDIRECTION_URL = "/summary/";

	// Product Default Value Relation Object Fields
	public static final String PRODUCT_DEFAULT_VALUE_NESTED_FIELD_NAME = "productDefaultValueRel";

	// Product Policy Option Relation Object Fields
	public static final String POLICY_OPTIONS_NESTED_FIELD_NAME = "productPolicyOptionRel";
	public static final String POLICY_OPTIONS_FIELD_NAME = "policyOption";
	public static final String VALUE_FIELD_KEY = "name";

	// Product Investing For Relation Object Fields
	public static final String INVESTING_FOR_NESTED_FIELD_NAME = "productInvestingForRel";
	public static final String INVESTING_FOR_FIELD_NAME = "investingFor";

	// Product Payment Relation Object Fields
	public static final String PAYMENT_OPTION_NESTED_FIELD_NAME = "productPaymentOptionRel";
	public static final String PAYMENT_OPTIONS_FIELD_NAME = "paymentOptions";

	// Product FundManagement Relation Object Fields
	public static final String FUND_MANAGEMENT_NESTED_FIELD_NAME = "productFundManagementRel";
	public static final String FUND_MANAGEMENT_FIELD_NAME = "fundManagementOptions";

	//PGI product field 
	public static final String GET_INCOME_FOR_PICKLIST_EXTREF_CODE = "GET_INCOME_FOR";
	public static final String FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE = "FAMILY_INCOME_BENEFIT";
	public static final String PGI_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE = "PGI_FAMILY_INCOME_BENEFIT";
	public static final String LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE = "LUMP_SUM_BENEFIT";
	public static final String INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE = "INCOME_PAYOUT_FREQUENCY";
	public static final String IS_EDELWEISS_EMPLOYEE_PICKLIST_EXTREF_CODE = "IS_EDELWEISS_EMPLOYEE";
	public static final String MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE = "MATURITY_PAYOUT_OPTION";
	public static final String PGI_PAY_FOR_PICKLIST_EXTREF_CODE = "PGI_PAY_FOR";
	public static final String PGI_POLICY_TERM_PICKLIST_EXTREF_CODE = "PGI_POLICY_TERM";
	public static final String PGI_GET_INCOME_FOR_PICKLIST_EXTREF_CODE = "GET_INCOME_FOR";
	public static final String PGS_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE = "PGS_FAMILY_INCOME_BENEFIT";
	public static final String PGS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE = "LUMP_SUM_BENEFIT_PGS";
	public static final String PGS_PAY_FOR_PICKLIST_EXTREF_CODE = "PGS_PAY_FOR";
	public static final String PGS_POLICY_TERM_PICKLIST_EXTREF_CODE = "PGS_POLICY_TERM";
	// Policy Term and Policy For Relation Object Fields
	
	//GIS product field 
	public static final String GIS_PAY_FOR_PICKLIST_EXTREF_CODE = "GIS_PAY_FOR";
	public static final String GIS_INCOME_START_POINT_PICKLIST_EXTREF_CODE = "GIS_INCOME_START_POINT";
	public static final String GIS_INCOME_DURATION_PICKLIST_EXTREF_CODE = "GIS_INCOME_DURATION";
	public static final String GIS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE = "GIS_LUMP_SUM_BENEFIT";
	public static final String GIS_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE = "GIS_FAMILY_INCOME_BENEFIT";
	public static final String GIS_INCOME_BENEFIT_PAYOUT_TYPE_PICKLIST_EXTREF_CODE = "GIS_INCOME_BENEFIT_PAYOUT_TYPE";
		
	//IB Product custimise fields 
	public static final String IB_PAY_FOR_PICKLIST_EXTREF_CODE = "IB_PAY_FOR";
	public static final String IB_POLICY_TERM_PICKLIST_EXTREF_CODE = "IB_POLICY_TERM";
	public static final String IB_PAYOUT_OPTION_PICKLIST_EXTREF_CODE = "IB_PAYOUT_OPTION";
	public static final String IB_PAYOUT_PERIOD_PICKLIST_EXTREF_CODE = "IB_PAYOUT_PERIOD";
	
	public static final String SWP = "swp";
	
	//saral jeevan bima
	public static final String SJB_POLICY_TERM_EXTREF_CODE_STRING = "SJB_POLICY_TERM";
	
	
	public static final String IS_INVESTMENT_AMOUNT_APPLICABLE = "isInvestmentAmountApplicable";
	public static final String IS_POLICY_OPTION_APPLICABLE = "isPolicyOptionApplicable";
	public static final String IS_INVESTING_FOR_APPLICABLE = "isInvestingForApplicable";
	public static final String IS_POLICY_TERM_APPLICABLE = "isPolicyTermApplicable";
	public static final String IS_POLICY_FOR_APPLICABLE = "isPolicyForApplicable";
	public static final String IS_PAYMENT_OPTIONS_APPLICABLE = "isPaymentOptionApplicable";
	public static final String IS_FUND_MANAGEMENT_APPLICABLE = "isFundManagementApplicable";
	public static final String IS_SWP_APPLICABLE = "isSWPApplicable";
	public static final String SWP_MASTER_PICKLIST_EXTREF_CODE = "SWP_MASTER";

	// URL constants for liferay Object :: Start ::
	public static final String URL_FOR_FETCHING_BASIC_DETAILS = "/o/c/customerenquiries/";
	public static final String GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS = "?filter=";
	public static final String URL_FOR_SAVING_INVESTMENT_DETAILS = "/o/c/customerinvestmentdetails/";
	public static final String URL_FOR_SAVING_POLICY_DETAILS = "/o/c/customerpolicydetails/";
	public static final String CUSTOMIZE_URL = "/customize";
	public static final String SUMMARY_URL = "/summary/";
	// URL constants for liferay Object :: End ::

	// Default Value and parameter name constants for liferay Object :: Start ::
	public static final String DEFAULT_CREATOR_VALUE = "Test Test";
	public static final String DEFAULT_STATUS = "Approved";
	public static final String INVESTMENT_AMOUNT = "investmentAmount";
	public static final String EDELWEISS_EMPLOYEE = "edelweissEmployee";
	public static final String INCOME_PAYOUT_TIME = "incomePayoutTime";
	public static final String INCOME_PAYOUT_TYPE = "incomePayoutType";
	public static final String ANNUAL_PREM2 = "annualPrem2";
	public static final String PAYOUT_INCREASING_PERCENTAGE = "payoutIncreasingPercentage";
	public static final String SUM_ASSURED_MULTIPLE = "sumAssuredMultiple";
	public static final String POLICY_OPTION = "policyOption";
	public static final String POLICY_TERM = "policyTerm";
	public static final String CUSTOMER_POLICY_TERM = "custPolicyTerm";
	public static final String PAYMENT_OPTION = "paymentOption";
	public static final String POLICY_FOR = "policyFor";
	public static final String PAY_FOR = "payFor";
	public static final String FUND_MANAGEMENT = "fundManagement";
	public static final String INVESTING_FOR = "investingFor";
	public static final String SYSTEMATIC_WITHDRAWL_PLAN = "swp";
	public static final String TOTAL_RETURNS = "totalReturns";
	public static final String TOTAL_RETURNS_VALUE = "totalReturnsValue";
	public static final String ILLUSTRATION_URL = "illustrationURL";
	public static final String QUOTATION_ID = "quotationId";
	public static final String PROJECTED_RETURNS_DATA = "projectedReturnsData";
	// Default Value and parameter name constants for liferay Object :: End ::

	public static final String FAMILY = "Family";
	
	public static final String RISING_STAR_BENEFIT = "Rising Star Benefit";
	public static final String BENEFIT_NAME = "benefitName";
	
	
	public static final String LITTLE_CHAMP_BENEFIT = "Little Champ Benefit";
	
	public static final String FUND_DETAILS = "fundDetails";
	public static final String HAS_FUND_ALLOCATED = "hasFundAllocated";
	public static final String CAGR_RESPONSE = "responseData";
	public static final String FUND_NAME_KEY = "name";
	public static final String FUND_INCEPTION_DATE = "inceptionDate";
	public static final String FUND_ALLOCATION_KEY = "allocation";
	public static final String RECOMMENDED_FUND_KEY = "recommendedFund";
	public static final String RECOMMENDED_FUND_DEFAULT_ALLOCATION = "100";
	public static final String OTHER_FUND_DEFAULT_ALLOCATION = "0";
	public static final String FUND_ALLOCATION_DETAILS = "fundAllocationDetails";
	
	public static final String PARAM_SPOUSE_NAME = "spouseFullName";
	public static final String PARAM_SPOUSE_DOB = "spouseDOB";
	public static final String PARAM_IS_FROM_POLICY_OPTIONS = "isFamilyFromPolicyOptions";
	public static final String PARAM_IS_SPOUSE = "isSpouse";
	
	public static final String PARAM_FUND_VALUE_TOBE_WITHDRAWN = "fundValuetobeWithdrawn";
	public static final String PARAM_POLICY_YEAR_FROM_WHICH_SWP_PAYABLE = "policyYearFromWhichSWPPayable";
	public static final String PARAM_SWP_FREQUENCY = "swpFrequency";
	public static final String FAMILY_INCOME_BENEFITS = "familyIncomeBenefits";
	public static final String SELECTED_RIDERS_IDS = "riderId";
	public static final String CUSTOMER_ENQUIRY_ID = "customerEnquiryId";
	public static final String PLAN_OPTION = "planOption";
	public static final String INCOME_START_YEAR = "incomeStartYear";
	public static final String LIFE_COVER_CONTINUATION_BENEFIT = "lifeCoverContinuationBenefit";

	public static final String LIFE_COVER = "lifeCover";
	public static final String INCOME_PERIOD = "incomePeriod";
	public static final String INCOME_PERCENTAGE = "incomePercentage";
	public static final String PREMIUM_BREAK_BENEFIT = "premiumBreakBenefit";
	
	public static final String YES = "Yes";
	
	// JSP Page Constant
	public static final String WELATHPLUS_CUSTOMIZE_FORM_JSP = "/product_jsps/wealth-plus/wealth-plus-customize.jsp";
	public static final String WELATH_GAIN_PLUS_CUSTOMIZE_FORM_JSP = "/product_jsps/wealth-gain-plus/wealth-gain-plus-customize.jsp";

	public static final String PRODUCT_DEFAULT_VALUE_DATA_KEY = "productDefaultValueData";
	
	public static final String CAPITAL_SECURE_PLUS_JSP = "/product_jsps/capital-secure-plus/capital-secure-plus-customize.jsp";
	public static final String ACTIVE_INCOME_JSP = "/product_jsps/active-income-plan/active-income-customize.jsp";
	public static final String CUSTOMER_INVESTMENT_DETAILS = "customerInvestmentDetails";
	public static final String PAY_OR_WAIVER_BENEFIT_OPTIONS = "payorWaiverBenefitOptions";
	public static final String PRODUCT_INVESTMENT_AMOUNT_REL = "productInvestmentAmountRel";
}