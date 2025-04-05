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
"mvc.command.name=/cis/cisPushPdfFlag" }, service = MVCResourceCommand.class)
public class CISPushPdfFlagMVCResourceCommand extends BaseMVCResourceCommand{
	@Reference
	private ETIPCoreAPI etipCoreAPI;

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		_log.info("CISPushPdfFlagMVCResourceCommand Called :::");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject response = JSONFactoryUtil.createJSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		String result = StringPool.BLANK;
		String message = StringPool.BLANK;
		boolean status = false;
		String appId = ParamUtil.getString(resourceRequest, "appId");
		String msg = ParamUtil.getString(resourceRequest, "msg");
		
		_log.info("CISPushPdfFlagMVCResourceCommand AppId:::: "+appId);
		_log.info("CISPushPdfFlagMVCResourceCommand Msg:::: "+msg);
		
		if(msg.equals("true")) {
			_log.info("CISPushPdfFlagMVCResourceCommand CIS PDF Pushed to D365 ::::: "+appId);
			
			JSONObject cusPolicyDetailsRes = getPolicyDetailsByApplicationNumber(appId, themeDisplay);
			String leadId = cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId");
			String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
			customerInvestmentDetailsURL = StringUtil.replace(customerInvestmentDetailsURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(leadId));
			JSONObject cusInvestmentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);
			JSONObject customerInvestmentDetailsRequestBody = JSONFactoryUtil.createJSONObject();
			
			JSONArray items = cusInvestmentRes.getJSONArray("items");
			JSONObject obj = items.getJSONObject(0);
			String customerInvestmentDetailsId = obj.getString("id");
			customerInvestmentDetailsRequestBody.put("isCISPDFPushed", true);
			
			updateCustomerInvestmentDetailsByLead(customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			
		}else{
			_log.info("CISPushPdfFlagMVCResourceCommand CIS PDF Not Pushed to D365 ::::: "+appId);
		}
		
		response.put("status", status);
        response.put("message", message);
		response.put("result", result);

        resourceResponse.setContentType("application/json");
        PrintWriter writer = resourceResponse.getWriter();
        writer.print(response);
	}
	
	private JSONObject updateCustomerInvestmentDetailsByLead(String customerInvestmentDetailsId, ThemeDisplay themeDisplay, JSONObject params) {
		_log.info("insdie updateCustomerInvestmentDetailsByLead :::::");
    	_log.info("customerInvestmentDetailsId :::::::::: "+customerInvestmentDetailsId);
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String customerInvestmentDetailsUpdateURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL ;
		customerInvestmentDetailsUpdateURL = customerInvestmentDetailsUpdateURL.replace(CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, customerInvestmentDetailsId);
		_log.info("customerInvestmentDetailsUpdateURL :::::::::::::: "+customerInvestmentDetailsUpdateURL);
		try {
			responseObject = etipCoreAPI.callPutAPI(params, customerInvestmentDetailsUpdateURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			_log.info("responseObject :::::::::::::: "+responseObject);
		} catch (ETIPSystemException e) {
			_log.error("ProposalServices customerInvestmentDetailsURL :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	private JSONObject getCustomerInvestmentDetailsByLead(String leadId, ThemeDisplay themeDisplay) {
		_log.info("insdie getCustomerInvestmentDetailsByLead :::::");
    	_log.info("applicationNumber :::::::::: "+leadId);
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
		_log.info("customerPolicyDetailsURL :::::::::::::: "+customerInvestmentDetailsURL);
		_log.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
		_log.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerInvestmentDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			_log.info("responseObject :::::::::::::: "+responseObject);
		} catch (ETIPSystemException e) {
			_log.error("ProposalServices customerInvestmentDetailsURL :::: "+e.getMessage());
		}
		return responseObject;
	}

	public JSONObject getPolicyDetailsByApplicationNumber(String applicationNumber, ThemeDisplay themeDisplay) {
		_log.info("insdie getPolicyDetailsByApplicationNumber :::::");
		_log.info("applicationNumber :::::::::: "+applicationNumber);
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
		_log.info("customerPolicyDetailsURL :::::::::::::: "+customerPolicyDetailsURL);
		_log.info("liferayUserName :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRUsername());
		_log.info("liferayPassword :::::::::::::: "+edelweissLRBasicAuthConfiguration.getLRPassword());
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerPolicyDetailsURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			_log.info("responseObject :::::::::::::: "+responseObject);
		} catch (ETIPSystemException e) {
			_log.error("ProposalServices getPolicyDetailsByApplicationNumber :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(CISPushPdfFlagMVCResourceCommand.class);
}
