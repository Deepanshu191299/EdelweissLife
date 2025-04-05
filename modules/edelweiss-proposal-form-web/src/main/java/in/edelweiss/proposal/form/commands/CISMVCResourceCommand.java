package in.edelweiss.proposal.form.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_POLICY_DETAILS_BASE_URL;


import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FILTER_QUERY_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.APP_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_BASE_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.pf.model.CommonData;
import in.edelweiss.proposal.form.pf.model.CommunicationDetails;
import in.edelweiss.proposal.form.pf.model.PersonalDetails;
import in.edelweiss.proposal.form.pf.model.ProductDetails;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.proposal.form.util.CISHtmlContentReader;
import in.edelweiss.proposal.form.util.CISUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
"mvc.command.name=/cis/generatepdf" }, service = MVCResourceCommand.class)
public class CISMVCResourceCommand extends BaseMVCResourceCommand{
	@Reference
	private ETIPCoreAPI etipCoreAPI;

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws Exception {
	    
		
		JSONObject response = JSONFactoryUtil.createJSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
	    logger.debug("Inside the CISMVC resource command");
	    
	    String appId = ParamUtil.getString(resourceRequest, "appId");
	    logger.debug("application NO::::"+ appId);
	    String checkPage = ParamUtil.getString(resourceRequest, "checkPage");
	    logger.debug("Check Page::::"+ checkPage);
	    
	    ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject cusPolicyDetailsRes = getPolicyDetailsByApplicationNumber(appId, themeDisplay);
		logger.debug("policyDetails ::::::::::::::; "+ cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId"));
		String leadId = cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId");
		
		String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
		customerInvestmentDetailsURL = StringUtil.replace(customerInvestmentDetailsURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(leadId));
		logger.debug("customerInvestmentDetailsURL::: " + customerInvestmentDetailsURL);
		
		JSONObject cusInvestmentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);
		
		logger.debug("Customer Policy Details by AppID : " + cusInvestmentRes.toString());
		
		JSONArray items = cusInvestmentRes.getJSONArray("items");
		JSONObject cusInvestmentObj = items.getJSONObject(0);
		logger.debug("cusInvestmentObj :::::::::: "+cusInvestmentObj);
		String riderID = cusInvestmentObj.getString("riderId");
		String policyTerm1 = cusInvestmentObj.getString("policyTerm");
		String premiumPayingTerm = cusInvestmentObj.getString("premiumPayingTerm");
		
		logger.debug("policyTerm1:::::: " + policyTerm1);
		logger.debug("premiumPayingTerm::::::" + premiumPayingTerm);
		String paymentCreatedDate = StringPool.BLANK;

		
		
		try {
            paymentCreatedDate = cusInvestmentObj.getString("paymentCreatedDate",StringPool.BLANK);
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = inputFormat.parse(paymentCreatedDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            paymentCreatedDate = outputFormat.format(date);
        } catch (Exception e) {
        	paymentCreatedDate = StringPool.BLANK;
        }
		
		
		String sumassuredMaturity = CISUtil.formatIndianNumber(cusInvestmentObj.getString("totalReturns",StringPool.BLANK));
		// For storing the rider details in session
		
		/*PortletSession session = resourceRequest.getPortletSession();
		String riderID = (String) session.getAttribute("riderId",PortletSession.APPLICATION_SCOPE);
		logger.debug("riderID from session:::"+riderID);*/
		
		JSONArray riderList =  JSONFactoryUtil.createJSONArray(riderID);
		logger.debug("JSONARRAY::::::"+ riderList);
		
		String data = riderTableTr(riderList,policyTerm1,premiumPayingTerm);
		logger.debug("data"+data);;
		
		logger.debug("oriderIDbj"+riderID);
		logger.debug("incomePayopurFeq:: " +cusInvestmentObj.getString("incomePayoutFrequency") );
		
		
		
		
	    String resultContent = StringPool.BLANK;
		String message = StringPool.BLANK;
		boolean status = false;
	    
	    if (Validator.isNotNull(appId)|| !appId.equals(StringPool.BLANK)) {
		    String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL() + "?applicationNumber="
					+ appId;
			logger.debug("pfURL... "+pfURL);
			
			String pfResponseCis = EdelweissAPIUtil.callPJGetAPI(pfURL);
			logger.debug("pfResponse under CIS ... "+pfResponseCis);
			
			String policyNumer = StringPool.BLANK;
			String installmentPremium = StringPool.BLANK;
			String sumassuredDeath = StringPool.BLANK;
			String premiumPaymentFreq = StringPool.BLANK;
			String premiumPaymentTerm = StringPool.BLANK;
			String policyTerm = StringPool.BLANK;
			String place = StringPool.BLANK;
			String isCISVerified = StringPool.BLANK;
			String mobileNumber = StringPool.BLANK;
			String emailId = StringPool.BLANK;
			String productName = StringPool.BLANK;
			
			ProposalJourney proposalJourney = objectMapper.readValue(pfResponseCis, ProposalJourney.class);
		
			CommonData commonDetails = proposalJourney.getResponseData().getCommonData(); // ProductDetails
			ProductDetails productDetails = proposalJourney.getResponseData().getProductDetails();
			CommunicationDetails communicationDetails = proposalJourney.getResponseData().getCommunicationDetails();
			PersonalDetails personalDetails = proposalJourney.getResponseData().getPersonalDetails();
			
			/*logger.debug("getPolicyNumber::: " + commonDetails.getPolicyNo());
			 logger.debug("getPremiuPaymentFrequency name::: " + productDetails.getPremiuPaymentFrequency());
			 logger.debug("getPremiumPaymentTerm name::: " + productDetails.getPremiumPaymentTerm());
			 logger.debug("getPolicyTerm name::: " + productDetails.getPolicyTerm());*/
		 
			
			 if(communicationDetails != null && communicationDetails.getLaDetails().getPaCity() != null ) {
				 place = communicationDetails.getLaDetails().getPaCity() != null ? communicationDetails.getLaDetails().getPaCity() : "";
			 }else {
				 place = cusInvestmentObj.getString("cisPlace",StringPool.BLANK);
			 }
			 if(commonDetails != null && commonDetails.getPolicyNo() != null ) {
				 policyNumer = commonDetails.getPolicyNo() != null ? commonDetails.getPolicyNo() : "";
				 productName = commonDetails.getProductName() != null ? commonDetails.getProductName() : "";;
			 }
			 if(productDetails != null && productDetails.getTotalPremiumAmount() > 0) {
				 installmentPremium = String.valueOf(productDetails.getTotalPremiumAmount()) != null ? CISUtil.formatIndianNumber(String.valueOf(productDetails.getTotalPremiumAmount())) : "";
			 }
			 if(productDetails != null && productDetails.getSumAssured() > 0) {
				sumassuredDeath = String.valueOf(productDetails.getSumAssured()) != null ? CISUtil.formatIndianNumber(String.valueOf(productDetails.getSumAssured())) : "";
			 }
			 if(productDetails != null && productDetails.getPremiuPaymentFrequency() != null ) {
				 premiumPaymentFreq = productDetails.getPremiuPaymentFrequency() != null ? productDetails.getPremiuPaymentFrequency() : "";
			 }
			 if(productDetails != null && productDetails.getPremiumPaymentTerm() > 0 ) {
				 premiumPaymentTerm = String.valueOf(productDetails.getPremiumPaymentTerm()) != null ? String.valueOf(productDetails.getPremiumPaymentTerm()) : "";
			 }
			 if(productDetails != null && productDetails.getPolicyTerm() > 0 ) {
				 policyTerm = String.valueOf(productDetails.getPolicyTerm()) != null ? String.valueOf(productDetails.getPolicyTerm()) : "";
			 }
			 if(personalDetails != null && personalDetails.getLaDetails().getMobileNumber() != null) {
				 mobileNumber = personalDetails.getLaDetails().getMobileNumber() != null ? personalDetails.getLaDetails().getMobileNumber() : "";
			 }
			 if(personalDetails != null && personalDetails.getLaDetails().getEmailAddress() != null) {
				 emailId = personalDetails.getLaDetails().getEmailAddress() != null ? personalDetails.getLaDetails().getEmailAddress() : "";
				 emailId = "/ Email ID "+ emailId;
			 }
			 
			logger.debug("PLACE ::::::::"+place);
			 
			Map<String, String> replaceHolderMap = new HashMap<>();
			replaceHolderMap.put("$[POLICYNO]", policyNumer);
			replaceHolderMap.put("$[INSTALLMENT_PREMIUM]", installmentPremium);
			replaceHolderMap.put("$[SUM_ASSURED_DEATH]", sumassuredDeath);
			replaceHolderMap.put("$[SUM_ASSURED_MATURITY]", sumassuredMaturity);
			replaceHolderMap.put("$[PREMIUM_PAYMENT_FREQUENCY]", premiumPaymentFreq);
			replaceHolderMap.put("$[PREMIUM_PAYMENT_TERM]", premiumPaymentTerm);
			replaceHolderMap.put("$[POLICY_TERM]", policyTerm);
			replaceHolderMap.put("$[PLACE]", place);
			replaceHolderMap.put("$[IS_CISVERIFIED]","");
			replaceHolderMap.put("$[MOBILE_NUMBER]", "");
			replaceHolderMap.put("$[EMAIL_ID]", "");
			replaceHolderMap.put("$[RiderTable]", data);
			replaceHolderMap.put("$[PAYMENT_CREATED_DATE]", paymentCreatedDate);
			
		    if(checkPage.equalsIgnoreCase("isThankYouPage")){
		    	logger.debug("Yes IsThankYou Page::::::::");
		        isCISVerified = "Validated through the OTP sent to registered mobile no";
		        replaceHolderMap.put("$[IS_CISVERIFIED]",isCISVerified);
				replaceHolderMap.put("$[MOBILE_NUMBER]", mobileNumber);
				replaceHolderMap.put("$[EMAIL_ID]", emailId);
		    }
			
		    
		    if(productName.equalsIgnoreCase("Premier Guaranteed STAR")) {
		    	logger.debug("CIS for PGS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-pgs", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Flexi-Savings Plan")) {
		    	logger.debug("CIS for FSP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-fsp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Guaranteed Income STAR")) {
		    	logger.debug("CIS for GIS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-gis", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Guaranteed Savings STAR")) {
		    	logger.debug("CIS for GSS Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-gss", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Saral Jeevan Bima")) {
		    	logger.debug("CIS for SJB Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-sjb", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Wealth Plus")) {
		    	logger.debug("CIS for WP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-wp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Wealth Rise+")) {
		    	logger.debug("CIS for WRP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-wrp", replaceHolderMap);
		    }else if(productName.equalsIgnoreCase("Zindagi Protect Plus")) {
		    	logger.debug("CIS for ZPP Product:::::::::");
		    	resultContent = CISHtmlContentReader.getArticleByUrlTitle(themeDisplay, "cis-zpp", replaceHolderMap);
		    }
		    
			//logger.debug("resultContent is CIS resource"+ resultContent);
			
	        if(!resultContent.equals(StringPool.BLANK)) {
	        	status = true;
	        	message = "PDF Genrated Successfully";
	        }else {
	        	message = "Internal Server Error";
	        } 
	    }else {
	    	status = false;
        	message = "Application ID Should not be NULL";
	    }
	        response.put("status", status);
	        response.put("message", message);
			response.put("htmlContent", resultContent); 
	
	        resourceResponse.setContentType("application/json");
	        PrintWriter writer = resourceResponse.getWriter();
	        writer.print(response);
	    
	}
	
	

	    private JSONObject getCustomerInvestmentDetailsByLead(String leadId, ThemeDisplay themeDisplay) {
		// TODO Auto-generated method stub
	    	logger.info("insdie getCustomerInvestmentDetailsByLead :::::");
			logger.info("applicationNumber :::::::::: "+leadId);
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
			logger.info("customerPolicyDetailsURL :::::::::::::: "+customerInvestmentDetailsURL);
			logger.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
			logger.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
			try {
				responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
				logger.info("responseObject :::::::::::::: "+responseObject);
			} catch (ETIPSystemException e) {
				logger.error("ProposalServices customerInvestmentDetailsURL :::: "+e.getMessage());
			}
			return responseObject;
	}
	
	public String riderTableTr(JSONArray riderList,String policyTerm1,String premiumPayingTerm) {
        StringBuilder tableRows = new StringBuilder();
        long total = 0;
        for (int i = 0; i < riderList.length(); i++) {
            JSONObject rider = riderList.getJSONObject(i);
            String nameWithUIN = rider.getString("name", "");
            
            // Extract only the name if "(" is present; otherwise, use the full name
            String onlyName = nameWithUIN.contains("(") ? 
                nameWithUIN.substring(0, nameWithUIN.indexOf("(")) : 
                nameWithUIN;
                logger.debug("namewithUIN:::::" + onlyName);

            // Extract UIN if present
            String uin = extractUIN(nameWithUIN.replace("\n", "").trim());
            logger.debug("uin is::::::"+ uin);
            if (uin == null) {
                uin = ""; // Set UIN to empty string if not found
            }
            logger.debug("uin here is::::::"+ uin);
            /*String onlyNmme = nameWithUIN.substring(0, nameWithUIN.indexOf("("));
            String uin = extractUIN(nameWithUIN.replace("\n", "").trim());*/
            
         // Get the price and format it
            String priceString = rider.getString("price", "0");
            String formattedPrice;
            try {
                double price = Double.parseDouble(priceString);
                formattedPrice = String.format("%.0f", price);
            } catch (NumberFormatException e) {
                formattedPrice = "0"; // Handle any parsing error
            }
            
            String tableRow = "<tr style=\"page-break-inside: avoid; \">"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + onlyName + "</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + uin + "</td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + CISUtil.formatIndianNumber(rider.getString("premium", "")) + "</td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + CISUtil.formatIndianNumber(formattedPrice) + "</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + rider.getString("pt", policyTerm1) + "</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"
                + rider.getString("ppt", premiumPayingTerm) + "</td>"
                + "</tr>";
            tableRows.append(tableRow);
            
           total = total +  rider.getLong("price");
        }

        String tableRow = "<tr style=\"page-break-inside: avoid; \">"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px;\">Total</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px;\"></td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "<td style=\"width: 20%; border: 1px solid black; padding: 8px; word-wrap: break-word;\">"+ CISUtil.formatIndianNumber(String.valueOf(total)) + "</td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "<td style=\"width: 15%; border: 1px solid black; padding: 8px; word-wrap: break-word;\"></td>"
                + "</tr>";
            tableRows.append(tableRow);
        return tableRows.toString();
    }
	
	 private static String extractUIN(String input) {
	        // Regular expression to find the UIN
		 Pattern pattern = Pattern.compile("UIN No:\\s*([\\w]+)");
	        Matcher matcher = pattern.matcher(input);
	        
	        if (matcher.find()) {
	            return matcher.group(1); // Return the first capturing group
	        }
	        
	        return null; // Return null if no match found
	    }
	
	public JSONObject getPolicyDetailsByApplicationNumber(String applicationNumber, ThemeDisplay themeDisplay) {
		logger.info("insdie getPolicyDetailsByApplicationNumber :::::");
		logger.info("applicationNumber :::::::::: "+applicationNumber);
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		StringBuilder paramBuilder = new StringBuilder();
		paramBuilder.append(APP_NUMBER);
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("eq");
		paramBuilder.append(StringPool.SPACE);
		paramBuilder.append("'" + applicationNumber + "'");
		String parameterURL = URLEncoder.encode(paramBuilder.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
		
		String customerPolicyDetailsURL = themeDisplay.getPortalURL()  + CUSTOMER_POLICY_DETAILS_BASE_URL + FILTER_QUERY_PARAM + parameterURL;
		logger.info("customerPolicyDetailsURL :::::::::::::: "+customerPolicyDetailsURL);
		logger.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
		logger.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerPolicyDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			logger.info("responseObject :::::::::::::: "+responseObject);
		} catch (ETIPSystemException e) {
			logger.error("ProposalServices getPolicyDetailsByApplicationNumber :::: "+e.getMessage());
		}
		return responseObject;
	}

	private static final Log logger = LogFactoryUtil.getLog(CISMVCResourceCommand.class);
}
