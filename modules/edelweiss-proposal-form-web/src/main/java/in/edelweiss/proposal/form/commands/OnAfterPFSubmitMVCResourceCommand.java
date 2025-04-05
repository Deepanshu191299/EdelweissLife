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
import java.util.HashMap;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.pf.model.CommunicationDetails;
import in.edelweiss.proposal.form.pf.model.ProposalJourney;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
"mvc.command.name=/pf/onAfterSubmit" }, service = MVCResourceCommand.class)
public class OnAfterPFSubmitMVCResourceCommand extends BaseMVCResourceCommand{

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws Exception {
	    
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject response = JSONFactoryUtil.createJSONObject();
		String resultContent = StringPool.BLANK;
		String message = StringPool.BLANK;
		boolean status = false;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		String appId = ParamUtil.getString(resourceRequest, "appId");
	    logger.info("OnAfterPFSubmitMVCResourceCommand Called :"+ appId);
	    
	    
	    if (Validator.isNotNull(appId)|| !appId.equals(StringPool.BLANK)) {
	    	//after paymentdate updating in customer invetment detail
			String pfURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPFDetailsURL()
					+ "?applicationNumber=" + appId;
			logger.info("pfURL :::::::::::::: "+pfURL);
			
			String pfResponse = EdelweissAPIUtil.callPJGetAPI(pfURL);
			
			logger.info("OnAfterPFSubmitMVCResourceCommand pfResponse : AppId : "+appId+" " +pfResponse);
			
			ProposalJourney proposalJourney = objectMapper.readValue(pfResponse, ProposalJourney.class);
			JSONObject cusPolicyDetailsRes = getPolicyDetailsByApplicationNumber(appId, themeDisplay);
			String leadId = cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId");
			String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
			customerInvestmentDetailsURL = StringUtil.replace(customerInvestmentDetailsURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(leadId));
			JSONObject cusInvestmentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);
			
			JSONArray items = cusInvestmentRes.getJSONArray("items");
			JSONObject obj = items.getJSONObject(0);
			String customerInvestmentDetailsId = obj.getString("id");
			String place = StringPool.BLANK;
			CommunicationDetails communicationDetails = proposalJourney.getResponseData().getCommunicationDetails();
			logger.info("OnAfterPFSubmitMVCResourceCommand Appid : "+appId+" customerInvestmentDetailsId : "+customerInvestmentDetailsId);
			//LA Case
			if(communicationDetails != null && communicationDetails.getLaDetails() != null) {
				place = communicationDetails.getLaDetails().getPaCity() != null ? communicationDetails.getLaDetails().getPaCity() : StringPool.BLANK;
			}
			//Proposer Case
			if(communicationDetails != null && communicationDetails.getProposerDetails() != null) {
				place = communicationDetails.getProposerDetails().getPaCity() != null ? communicationDetails.getProposerDetails().getPaCity() : StringPool.BLANK;
			}
			//Spouse Case
			if(communicationDetails != null && communicationDetails.getSpouseDetails() != null) {
				place = communicationDetails.getSpouseDetails().getPaCity() != null ? communicationDetails.getSpouseDetails().getPaCity() : StringPool.BLANK;
			}
			
			JSONObject customerInvestmentDetailsRequestBody = JSONFactoryUtil.createJSONObject();
			
			logger.info("OnAfterPFSubmitMVCResourceCommand Appid : "+appId+" Place : "+place);
			customerInvestmentDetailsRequestBody.put("cisPlace", place);

			updateCustomerInvestmentDetailsByLead(customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			
	    	status = true;
        	message = "On After Submission Triggred";
	    }else {
	    	status = false;
        	message = "Application ID Should not be NULL";
	    }
	    
	    logger.info("OnAfterPFSubmitMVCResourceCommand Appid : "+appId+" message : "+message);
        response.put("status", status);
        response.put("message", message);
		response.put("htmlContent", resultContent); 

        resourceResponse.setContentType("application/json");
        PrintWriter writer = resourceResponse.getWriter();
        writer.print(response);
	    
	}
	
	private JSONObject getCustomerInvestmentDetailsByLead(String leadId, ThemeDisplay themeDisplay) {
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
	
	private JSONObject updateCustomerInvestmentDetailsByLead(String customerInvestmentDetailsId, ThemeDisplay themeDisplay, JSONObject params) {
		logger.info("insdie updateCustomerInvestmentDetailsByLead :::::");
		logger.info("customerInvestmentDetailsId :::::::::: "+customerInvestmentDetailsId);
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String customerInvestmentDetailsUpdateURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL ;
		customerInvestmentDetailsUpdateURL = customerInvestmentDetailsUpdateURL.replace(CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, customerInvestmentDetailsId);
		logger.info("customerInvestmentDetailsUpdateURL :::::::::::::: "+customerInvestmentDetailsUpdateURL);
		try {
			responseObject = etipCoreAPI.callPutAPI(params, customerInvestmentDetailsUpdateURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			logger.info("responseObject :::::::::::::: "+responseObject);
		} catch (ETIPSystemException e) {
			logger.error("ProposalServices customerInvestmentDetailsURL :::: "+e.getMessage());
		}
		return responseObject;
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

	@Reference
	private ETIPCoreAPI etipCoreAPI;

	private static final Log logger = LogFactoryUtil.getLog(OnAfterPFSubmitMVCResourceCommand.class);
}
