package edelweiss.csb.integration.web.resources;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.edelweiss.http.core.exception.ETLIValidationException;
import com.liferay.document.library.repository.cmis.Session;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys;
import edelweiss.csb.integration.web.util.CsbIntegrationUtil;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissCsbIntegrationWebPortletKeys.EDELWEISSCSBINTEGRATIONWEB,
	        "mvc.command.name="+ EdelweissCsbIntegrationWebPortletKeys.VALIDATE_OTP_CSB_INTEGRATION_MVC_RESOURCE_COMMAND
	    },
	    service = MVCResourceCommand.class
	)

public class CsbIntegrationMVCResourceCommand extends BaseMVCResourceCommand {

	private Log logger = LogFactoryUtil.getLog(this.getClass());
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("inside doserve Resource:::");
		
		String appId = ParamUtil.getString(resourceRequest, "ApplicationNumber");
        String mobileNo = ParamUtil.getString(resourceRequest, "Mobileno");
        String clientName = ParamUtil.getString(resourceRequest, "clientName");
        String emailAddress = ParamUtil.getString(resourceRequest, "emailAddress");
        String currentPreURL = ParamUtil.getString(resourceRequest, "currentPreURL");
        String locationURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getSendOTPURL();
        
		String action = ParamUtil.getString(resourceRequest, "action");
		logger.info("action here is:::"+ action);
		logger.info("currentPreURL:::::::::::"+ currentPreURL);
		
		
		

		//JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject();
        if ("verifyOTP".equals(action)) {
        	logger.info("inside VerifyOTP-----------1");
            verifyOTP(resourceRequest, resourceResponse);
        } else if ("createPayment".equals(action)) {
            createPayment(resourceRequest, resourceResponse);
        }else if ("reSendOtp".equals(action)) {

    		Map<String, String> tinyURLRequest = new HashMap<>();
    		tinyURLRequest.put("url", currentPreURL);
    		String tinyURLEndpoint = "https://devapi.edelweissfin.com/tinyurl";
    		String tinyURLKey = "fEWWLzicH64UEhZne9GH724zSGvDmrzB3yk7KRH0";
    		String tinyURLUsername = "13sej7smuiuiov55ecsntgvvp7";
    		String tinyURLPassword = "nd51isdlla2bol8tkr62aorte21mug1nchl6c0s0jgij2tojtfq";
    		
    		
    		JSONObject tinyURLRequestJson = JSONFactoryUtil.createJSONObject(tinyURLRequest);
    		logger.info("tinyURLRequestJson123 ::::"+ tinyURLRequestJson);
    		JSONObject authorizations = JSONFactoryUtil.createJSONObject(etipCoreApi.generateOauthToken("https://devapi.edelweisslife.in/oauth2/token", tinyURLUsername, tinyURLPassword));
    		logger.info("tinyURLAuthorization::::"+ authorizations);
    		String authorization=authorizations.getString("access_token");
    		logger.info("tinyURLAuthorization AccessToken::::"+ authorization);
    		
    		JSONObject generateTinyUrlResponse = etipCoreApi.callPostAPI(tinyURLRequestJson,tinyURLEndpoint, true, tinyURLKey, authorization);
    		logger.info("<<<<<<<<generateTinyUrlResponse:::::::"+ generateTinyUrlResponse.getString("tinyUrl")); 
    		String tinyURL ="https://"+generateTinyUrlResponse.getString("tinyUrl");
        	
        	CsbIntegrationUtil.sendCsbOtp(appId, clientName, "Y", "CSBOTP", mobileNo, emailAddress, tinyURL,locationURL);
        } else {
            logger.info("Unknown action requested: " + action);
        }

        /*PrintWriter pw = resourceResponse.getWriter();
        pw.print(responseJSONObject);
		pw.flush();
		pw.close();*/
        
    }

	@Reference
	ETIPCoreAPI etipCoreApi;
	 private void verifyOTP(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws IOException {
		 logger.info("inside the verifyOTP method:::2");
		 
		 EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
					.getEdelweissLRBasicAuthConfiguration();
		 	ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
			logger.info("liferayUserName:::::::"+ liferayUserName);
			logger.info("liferayPassword::::::::"+ liferayPassword);
			
		    String appId = ParamUtil.getString(resourceRequest, "ApplicationNumber");
	        String mobileNo = ParamUtil.getString(resourceRequest, "Mobileno");
	        String otpCode = ParamUtil.getString(resourceRequest, "OTPCode");

	        logger.info("appId::"+ appId );
	        logger.info("mobileNO::"+mobileNo);
	        logger.info("otpCode::"+ otpCode);
	        
	        String getCsbIntegrationUrl = themeDisplay.getPortalURL() + GET_CSB_INTEGRATION_DETAILS_URL;
			String getCsbIntegratedURL = StringUtil.replace(getCsbIntegrationUrl, GET_CSB_INTEGRATION_DETAILS_ID_PARAM, String.valueOf(appId));
			Map<String, String> updateCsb = new HashMap<>();
	        
	        Map<String, String> mapformMap = new HashMap<>();
	        mapformMap.put("appId", appId);
	        mapformMap.put("mobileNo", mobileNo);
	        mapformMap.put("otpCode", otpCode);
	        
	        JSONObject requestJson = JSONFactoryUtil.createJSONObject(mapformMap);
	        String endpointURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getValidateOTPURL();
	        
	        // Perform OTP verification logic
	        try {
				JSONObject otpVerificationResponse = etipCoreApi.callPostAPI(requestJson, endpointURL, false, liferayUserName, liferayPassword);
				logger.info("Response from VerifyOTP URL is ::: " + otpVerificationResponse);
				
				boolean verifyStatus = otpVerificationResponse.getBoolean("status");
				logger.info("status here:::::::+ verify OTP" +verifyStatus );
				if(verifyStatus) {
					updateCsb.put("csbIntegrationStatus", "Consent Received");
			        
			        JSONObject updateCsbRequestJson = JSONFactoryUtil.createJSONObject(updateCsb);
					JSONObject updateCsbIntegrationStatusRes = etipCoreApi.callPutAPI(updateCsbRequestJson, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
					logger.info("updateCsbIntegrationStatusRes:::"+ updateCsbIntegrationStatusRes);
				}
				// Send response back to client
		        sendJSONResponse(resourceResponse, otpVerificationResponse);
	        } catch (ETIPSystemException e) {
	            logger.error("Error while calling OTP verification API: " + e.getMessage());
	            e.printStackTrace();
	            // Handle exception and send error response
	            JSONObject errorResponse = JSONFactoryUtil.createJSONObject();
	            errorResponse.put("status", false);
	            errorResponse.put("error", "Failed to verify OTP. Please try again later.");
	            sendJSONResponse(resourceResponse, errorResponse);
	        }
	    }
	
		private void sendJSONResponse(ResourceResponse resourceResponse, JSONObject jsonResponse) throws IOException {
			logger.info("inside the sendJSONResponse::::::");
		    resourceResponse.setContentType("application/json");
		    PrintWriter out = resourceResponse.getWriter();
		    out.println(jsonResponse.toString());
		}

	private void createPayment(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws IOException, ETIPSystemException {
		// TODO Auto-generated method stub
		logger.info("isnide the createPayment method:::");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		logger.info("liferayUserName:::::::"+ liferayUserName);
		logger.info("liferayPassword::::::::"+ liferayPassword);
		
		  // Extract payment details from request
	    
	    String Source = ParamUtil.getString(resourceRequest, "Source");
	    String Type = ParamUtil.getString(resourceRequest, "Type");
	    String ApplicationNumber = ParamUtil.getString(resourceRequest, "ApplicationNumber");
	    String PolicyNumber = ParamUtil.getString(resourceRequest, "PolicyNumber");
	    String CustomerName = ParamUtil.getString(resourceRequest, "CustomerName");
	    String CustomerEmail = ParamUtil.getString(resourceRequest, "CustomerEmail");
	    String CustomerMobile = ParamUtil.getString(resourceRequest, "CustomerMobile");
	    Double Amount = ParamUtil.getDouble(resourceRequest, "Amount");
	    BigDecimal decimalFormat = BigDecimal.valueOf(Amount);
	    decimalFormat = decimalFormat.setScale(2, RoundingMode.HALF_UP);
	    
	    String Gateway = ParamUtil.getString(resourceRequest, "Gateway");
	    Double Payment_Term = ParamUtil.getDouble(resourceRequest, "Payment_Term");
	    String PaymentMode = ParamUtil.getString(resourceRequest, "PaymentMode");
	    String Payment_Description = ParamUtil.getString(resourceRequest, "Payment_Description");
	    String ShowInfoPage = ParamUtil.getString(resourceRequest, "ShowInfoPage");
	    Boolean siToBeRegistered = ParamUtil.getBoolean(resourceRequest, "siToBeRegistered");
	    Double RecurringAmount = ParamUtil.getDouble(resourceRequest, "RecurringAmount");
	    
	    String getCsbIntegrationUrl = themeDisplay.getPortalURL() + GET_CSB_INTEGRATION_DETAILS_URL;
		String getCsbIntegratedURL = StringUtil.replace(getCsbIntegrationUrl, GET_CSB_INTEGRATION_DETAILS_ID_PARAM, String.valueOf(ApplicationNumber));
		Map<String, String> updateCsb = new HashMap<>();
	    
	    logger.info("formattedAmount::::"+ decimalFormat);
	    
	    
	    Map<String, Object> mapformMap = new HashMap<>();
	    mapformMap.put("Source", Source);
	    mapformMap.put("Type", Type);
	    mapformMap.put("ApplicationNumber", ApplicationNumber);
	    mapformMap.put("PolicyNumber", PolicyNumber);
	    mapformMap.put("CustomerName", CustomerName);
	    mapformMap.put("CustomerEmail", CustomerEmail);
	    mapformMap.put("CustomerMobile", CustomerMobile);
	    mapformMap.put("Amount", decimalFormat.doubleValue());
	    mapformMap.put("Gateway", Gateway);
	    mapformMap.put("Payment_Term", Payment_Term);
	    mapformMap.put("PaymentMode", PaymentMode);
	    mapformMap.put("Payment_Description", Payment_Description);
	    mapformMap.put("ShowInfoPage", ShowInfoPage);
	    mapformMap.put("siToBeRegistered", siToBeRegistered);
	    mapformMap.put("RecurringAmount", RecurringAmount);
	    
	    /* IF consent recieved then only redirect to payment */
	    Map<String, String> checkVerifyStatusObject = new HashMap<>();
	    checkVerifyStatusObject.put("id", ApplicationNumber);
	    JSONObject checkVerifyOTPStatus = etipCoreApi.callGetAPI(checkVerifyStatusObject, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
	    logger.info("checkVerifyOTPStatusResponse:::::::"+checkVerifyOTPStatus.getString("csbIntegrationStatus"));
	    String checkStatus = checkVerifyOTPStatus.getString("csbIntegrationStatus");
	    
	    if(checkStatus.contentEquals("Consent Received") && checkVerifyOTPStatus.getString("mobileNo").equals(CustomerMobile)) {
	    	logger.info("Valid OTP....Please proceed:::::::");
			    JSONObject requestJson = JSONFactoryUtil.createJSONObject(mapformMap);
			    logger.info("RequestBody for CSB PaymentLink:::::::::"+ requestJson );
			    
			   String csbTranscationURL = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/createTransaction";
				logger.info("EndPointURL:::" +csbTranscationURL );
				
				try {
					JSONObject csbTranscationURLResponse = etipCoreApi.callPostAPI(requestJson, csbTranscationURL, false, liferayUserName, liferayPassword);
					logger.info("csbTranscationURL Response::::::::"+ csbTranscationURLResponse);
					// Send JSON response back to client-side JavaScript for further processing
					
					boolean verifyStatus = csbTranscationURLResponse.getBoolean("status");
		
					logger.info("status here:::::::+ verify OTP" +verifyStatus );
					if(verifyStatus) {
						
						String txnURL = csbTranscationURLResponse.getString("responseData");
						String[] txnURLSegments = txnURL.split("/");
						
						String txnId = txnURLSegments[txnURLSegments.length - 1];
						logger.info("txnId ::::::::::::: " +txnId );
						updateCsb.put("csbIntegrationStatus", "Consent Received");
						updateCsb.put("transactionId", txnId);
				        
				        JSONObject updateCsbRequestJson = JSONFactoryUtil.createJSONObject(updateCsb);
				        logger.info("updateCsbRequest::::::::::"+ updateCsbRequestJson);
						JSONObject updateCsbIntegrationStatusRes = etipCoreApi.callPutAPI(updateCsbRequestJson, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
						logger.info("updateCsbIntegrationStatusRes:::"+ updateCsbIntegrationStatusRes);
					}
			        sendJSONResponse(resourceResponse, csbTranscationURLResponse);
					
				 } catch (ETIPSystemException e) {
				        logger.error("Error while calling CSB Transaction API: " + e.getMessage());
				        e.printStackTrace();
				        // Handle exception and send error response
				        JSONObject errorResponse = JSONFactoryUtil.createJSONObject();
				        errorResponse.put("status", false);
				        errorResponse.put("error", "Failed to create payment transaction. Please try again later.");
				        sendJSONResponse(resourceResponse, errorResponse);
				    }
				}else {
					JSONObject errorResponse = JSONFactoryUtil.createJSONObject();
			        errorResponse.put("status", false);
			        errorResponse.put("error", "Transaction Fiailed. Please try again later.");
			        sendJSONResponse(resourceResponse, errorResponse);
				}
}
}