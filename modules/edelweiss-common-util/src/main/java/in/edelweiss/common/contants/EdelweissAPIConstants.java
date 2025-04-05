package in.edelweiss.common.contants;

/**
 * This class contains constants required for the API implementation.
 *
 * @author Abhijit AA
 */
public class EdelweissAPIConstants {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private EdelweissAPIConstants() {

	}
	
	public static final String APPLICATION_JSON = "application/json";
	public static final String APPLICATION_X_WWW_FORM_URLENCODED_CHARSET_UTF_8 
									= "application/x-www-form-urlencoded; charset=UTF-8";
	
	public static final String X_API_KEY = "x-api-key";
	public static final String ACCESS_TOKEN = "access_token";
	public static final String REQUEST_TYPE_ENACH = "enach";
	public static final String REQUEST_TYPE_PPC = "PPC";
	public static final String REQUEST_TYPE_RPC = "RPC";
	public static final String REQUEST_TYPE_BRIEFCASE = "briefcase";
	public static final String REQUEST_TYPE_DOWNLOAD_PPC_DMS = "download_ppc_dms";
	public static final String REQUEST_TYPE_FUND_STATEMENT_DMS = "fund_statement_dms";
	public static final String REQUEST_TYPE_GET_RECEIPT_ID = "get_receipt_id";
	public static final String REQUEST_TYPE_DOWNLOAD_RECEIPT = "download_receipt";
	public static final String REQUEST_TYPE_VIEW_SR = "view_sr";
	
	public static final String IFSC_VALIDATE_URL = "/bank/ifsc/";
}
