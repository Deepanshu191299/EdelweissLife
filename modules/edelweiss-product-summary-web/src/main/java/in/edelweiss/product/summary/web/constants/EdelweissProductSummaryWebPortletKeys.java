package in.edelweiss.product.summary.web.constants;

public class EdelweissProductSummaryWebPortletKeys {
	
	 private EdelweissProductSummaryWebPortletKeys() {
		    throw new IllegalStateException("EdelweissProductSummaryWebPortletKeys >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	 }


	public static final String EDELWEISSPRODUCTSUMMARYWEB = "edelweiss_product_summary_web_EdelweissProductSummaryWebPortlet";

	public static final String PRODUCT = "product";
	public static final String FORMVIEW = "formView";
	public static final String AUTHORIZATION_URL = "authorization.url";
	public static final String AUTHORIZATION_USERNAME = "authorization.username";
	public static final String AUTHORIZATION_PASSWORD = "authorization.password";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String EDELWEISS_WEB_SALES = "EdelweissWebsales";
	public static final String WEB_URL = "https://www.edelweisslife.in/";
	public static final String BUY_URL = "/buy/";
	public static final String RESPONSE_DATA = "responseData";
	public static final String LMS_ID = "LMSId";

	public static final String URL_FOR_CUSTOMER_ENQUIRY = "/o/c/customerenquiries/";

	public static final String LMS_SITE_SECTION = "Pre-Quote";

	public static final String EDELWEISSPRODUCTSUMMARYCONTROLLER = "in_edelweiss_product_summary_web_EdelweissProductSummaryControllerPortlet";

	public static final String GET_CUSTOMER_ENQUIRY_RELATIONAL_DATA_QUERY_PARAMS = "?nestedFields=customerInvestmentDetailsRel,customerFamilyDetailsRel,customerPolicyDetailsRel";
	public static final String GET_CUSTOMER_ENQUIRY_RELATIONAL_API = "/o/c/customerenquiries/{0}";

	
	public static final String GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS = "?filter=";

	public static final String PUT_CUSTOMER_POLICY_DETAILS = "/o/c/customerpolicydetails/{0}";
	public static final String GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL = "/o/c/customerpolicydetails";
	public static final String GET_CUSTOMER_DETAILS_BY_LEAD_ID_QUERY_PARAMS = "?filter=";

	public static final String URL_FOR_FETCHING_BASIC_DETAILS = "/o/c/customerenquiries/";
	
	public static final String GET_CUSTOMER_METADATA_BY_LEAD_ID_URL = "/o/c/customerenquiries";
	public static final String GET_FAMILY_DETAILS = "/o/c/customerfamilydetailses/";
	public static final String GET_INVESTMENT_DETAILS ="/o/c/customerinvestmentdetails";
	public static final String GET_INVESTMENT_DETAILS_BY_LEAD_ID_QUERY_PARAMS ="?filter=";
	
	public static final String CUSTOMER_ENQUIRY_ID = "customerEnquiryId";
	public static final String IS_LITTLE_CHAMP_OPTED = "isLittleChampBenefitOpted";
	public static final String BENEFIT_NAME = "benefitName";
	public static final String REMOVE_LITTLE_CHAMP = "removeLittleChamp";
	public static final String RISING_STAR = "risingStar";
	
	public static final String YES = "Yes";
	public static final String NO = "No";
	
	public static final String PRODUCT_RELATIONS_DATA = "productRelationsData";
}