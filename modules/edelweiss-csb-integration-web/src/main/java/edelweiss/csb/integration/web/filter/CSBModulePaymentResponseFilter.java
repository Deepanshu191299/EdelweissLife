package edelweiss.csb.integration.web.filter;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(
		immediate = true, 
		property = { 
		"servlet-context-name=", 
		"servlet-filter-name=CSBModulePaymentResponseFilter",
		"url-pattern=/web/guest/csb-thankyou",
		"dispatcher=FORWARD",
		"dispatcher=REQUEST",
		}, 
service = Filter.class)

public class CSBModulePaymentResponseFilter implements Filter{
	@Reference
	ETIPCoreAPI etipCoreApi;
	
	private static final Log logger = LogFactoryUtil.getLog(CSBModulePaymentResponseFilter.class);
	public static final String baseURL  = "https://uat.edelweisslife.in/";
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("init CSBModulePaymentResponseFilter");
		logger.info("inside the init method?>>>>>>>>>>>>>>>>>>");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		logger.info("inside do Filter CSB Page>>>>>Executing filter for URL: " + ((HttpServletRequest)request).getRequestURI());
		try {
			processFilter(httprequest, httpresponse, chain);
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}


	@Override
	public void destroy() {
		logger.info("destroy CSB Payment Response Filter");
	}
	
	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {
		logger.info("inside CSB process Filter method");
		logger.info(">>>>>>>>>>>>>>>Inside CSB Process Filter>>>>>>>>>>>>>>>>");
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		ThemeDisplay themeDisplay = (ThemeDisplay) httpServletRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		logger.info("liferayUserName:::::::"+ liferayUserName);
		logger.info("liferayPassword::::::::"+ liferayPassword);
		
		if(Validator.isNotNull(httpServletRequest.getParameter("type")) && 
				Validator.isNotNull(httpServletRequest.getParameter("status"))) {
			
			String redirectURL = StringPool.BLANK;
			String type = httpServletRequest.getParameter("type");
			String status = httpServletRequest.getParameter("status");
			String transactionId = httpServletRequest.getParameter("transactionId");
			String amount = httpServletRequest.getParameter("amount");
			String applicationnumber = httpServletRequest.getParameter("applicationnumber");
			
			
			logger.info("type is -------->="+type);
			logger.info("status is -------->="+status);
			logger.info("transactionId is -------->="+transactionId);
			logger.info("amount is -------->="+amount);
			logger.info("application No:::"+ applicationnumber);
			
			if(Validator.isNotNull(type) && type.equalsIgnoreCase("CSBLANDING") && Validator.isNotNull(applicationnumber)   ) {
				logger.info("inside CSBLANDING Condition>>>>>>>>>>>>>>>>>");
				
				Map<String, String> getCsbIntegration = new HashMap<>();
				getCsbIntegration.put("id", applicationnumber);
				
				String getCsbIntegrationUrl = baseURL + GET_CSB_INTEGRATION_DETAILS_URL;
				logger.info("getCsbIntegrationUrl"+ getCsbIntegrationUrl);
				String getCsbIntegratedURL = StringUtil.replace(getCsbIntegrationUrl, GET_CSB_INTEGRATION_DETAILS_ID_PARAM, applicationnumber);
				logger.info("URL in paymnet:::::::"+ getCsbIntegratedURL);
				JSONObject getCsbResponse = etipCoreApi.callGetAPI(getCsbIntegration, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
				
				logger.info("getCsbResponse::::::PaymentFilter:::::::"+getCsbResponse);
				String csbResponseId = getCsbResponse.getString("id");
				logger.info("getCsbResponseId::::::::"+csbResponseId);
				
				Date transactionCaptureDate = new Date();
				String convertedTransactionCaptureDate = convertToIST(String.valueOf(transactionCaptureDate));
				logger.info("transactionCaptureDate:::"+ transactionCaptureDate);
				String transactionCaptureMonth = getMonthName(transactionCaptureDate);
				logger.info("transactionCaptureMonth:::"+ transactionCaptureMonth);
				
				if(Validator.isNotNull(csbResponseId)&& Validator.isNotNull(status) && status.equalsIgnoreCase("true") ) {

					redirectURL = "/csb-success?transactionId="+transactionId+"&amount="+amount;
					
					Map<String, Object> updateCsb = new HashMap<>();
					updateCsb.put("csbIntegrationStatus", "Payment Success");
					updateCsb.put("transactionDate", convertedTransactionCaptureDate);
					updateCsb.put("transactionMonth", transactionCaptureMonth);
					
								        
			        JSONObject updateCsbRequestJson = JSONFactoryUtil.createJSONObject(updateCsb);
					JSONObject updateCsbIntegrationStatusRes = etipCoreApi.callPutAPI(updateCsbRequestJson, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
					logger.info("updateCsbIntegrationStatusRes:::"+ updateCsbIntegrationStatusRes);
					
					
				}else {
					redirectURL = "/csb-failure";
					Map<String, String> updateCsb = new HashMap<>();
					updateCsb.put("csbIntegrationStatus", "Payment Failure");
								        
			        JSONObject updateCsbRequestJson = JSONFactoryUtil.createJSONObject(updateCsb);
					JSONObject updateCsbIntegrationStatusRes = etipCoreApi.callPutAPI(updateCsbRequestJson, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
					logger.info("updateCsbIntegrationStatusRes:::"+ updateCsbIntegrationStatusRes);
				}
			}
			logger.info("Type>>>"+type+">>>Status>>>"+status+">>>redirectURL>>>"+redirectURL);
			httpServletResponse.sendRedirect(redirectURL);
		}
		
		
		
		logger.info(">>>>>>>>>>>>>>>>Exiting CSB Process Filter>>>>>>>>>>>>>>>");
	}
	public static String convertToIST(String dateStr) throws ParseException {
        // Define the input date format
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
 
        // Parse the input date string to a Date object
        Date date = null;
		try {
			date = inputFormat.parse(dateStr);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Define the output date format
        SimpleDateFormat istFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        istFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
 
        // Format the Date object to the IST date string
        return istFormat.format(date);
    }
	
	public static String getMonthName (Date date) {        
	// Define the format to extract the month name
	SimpleDateFormat monthFormat =new SimpleDateFormat("MMMM");        
	// Format the Date object to get the month name
	return monthFormat.format(date); 
	}
}


