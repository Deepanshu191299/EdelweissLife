package in.edelweiss.product.quote.web.constants;

/**
 * @author naitik.datta
 */
public class EdelweissProductQuoteWebPortletKeys {
	
	 private EdelweissProductQuoteWebPortletKeys() {
		    throw new IllegalStateException("EdelweissProductQuoteWebPortletKeys >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	 }

	public static final String EDELWEISSPRODUCTQUOTEWEB =
		"in_edelweiss_product_quote_web_EdelweissProductQuoteWebPortlet";
	
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
	
	public static final String URL_FOR_META_DATA = "/o/c/productmetadatas/by-external-reference-code/{referenceCode}";

	public static final String GENERATE_LEAD_API_URL = "generate.lead.api.url";
	
	public static final String REDIRECT_URL = "/customize";
	public static final String INVESTMENT_OBJECTIVE_URL = "/investment-product-suggestion";
	
	public static final String PREMIER_GUARANTEED_INCOME = "/product_jsps/premier-guaranteed-income/buy_form.jsp";
	public static final String PREMIUM_GUARANTEED_STAR = "/product_jsps/premier-guaranteed-star/buy_form.jsp";

	public static final String TOTAL_PROTECT_PLUS_BUY_FORM_JSP = "/product_jsps/total-protect-plus/buy_form.jsp";
	
	public static final String GET_CUSTOMER_METADATA_BY_LEAD_ID_URL = "/o/c/customerenquiries";
	public static final String GET_CUSTOMER_ENQUIRY_BY_ID= "/o/c/customerenquiries/{0}";
	public static final String GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS = "?filter=";
	
	public static final String GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS = "?nestedFields=productMetaDataRelationship";
	public static final String GET_PRODUCT_MASTER_RELATIONAL_API = "/o/c/productsmasters/{0}";
	
	public static final String SUITABILITY_FULL_NAME = "suitabilityFullName";
	public static final String SUITABILITY_DATE_OF_BIRTH = "suitabilityDateOfBirth";
	public static final String SUITABILITY_MOBILE_NUMBER = "suitablityMobileNumber";
	public static final String SUITABILITY_EMAIL = "suitabilityEmail";
	public static final String SUITABILITY_GENDER = "suitabilityGender";
	
	
}