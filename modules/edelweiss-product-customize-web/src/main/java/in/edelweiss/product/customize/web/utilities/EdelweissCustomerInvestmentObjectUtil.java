package in.edelweiss.product.customize.web.utilities;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FILTER_QUERY_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_INVESTMENT_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

public class EdelweissCustomerInvestmentObjectUtil {

	public static JSONObject getCustomerInvestmentDetailsByLead(ETIPCoreAPI etipCoreAPI, String leadId, ThemeDisplay themeDisplay) {
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append(LEAD_ID);
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("eq");
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("'" + leadId + "'");
		String parameterURL = URLEncoder.encode(paramBuilder.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
		
		String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + CUSTOMER_INVESTMENT_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		} catch (ETIPSystemException e) {
			log.error("EdelweissGenerateBIResourceCommand >> Error :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	public static JSONObject updateCustomerInvestmentDetailsById(ETIPCoreAPI etipCoreAPI, String customerInvestmentDetailsId, ThemeDisplay themeDisplay, JSONObject params) {
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String customerInvestmentDetailsUpdateURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL ;
		customerInvestmentDetailsUpdateURL = customerInvestmentDetailsUpdateURL.replace(CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, customerInvestmentDetailsId);
		try {
			responseObject = etipCoreAPI.callPutAPI(params, customerInvestmentDetailsUpdateURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		} catch (ETIPSystemException e) {
			log.error("EdelweissGenerateBIResourceCommand >> Error :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissCustomerInvestmentObjectUtil.class);
}