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
import in.edelweiss.pdf.generator.utility.PDFUtil;
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
"mvc.command.name=/cis/cishtmltopdf" }, service = MVCResourceCommand.class)
public class CISHtmlToPdfMVCResourceCommand extends BaseMVCResourceCommand{
	@Reference
	private ETIPCoreAPI etipCoreAPI;

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
	        throws Exception {
	    
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String applicationNumber = ParamUtil.getString(resourceRequest, "appId");
		logger.debug("Download CIS PDF MVC resource command Called By "+applicationNumber);
		
		JSONObject cusPolicyDetailsRes = getPolicyDetailsByApplicationNumber(applicationNumber, themeDisplay);
		String leadId = cusPolicyDetailsRes.getJSONArray("items").getJSONObject(0).getString("leadId");
		String customerInvestmentDetailsURL = themeDisplay.getPortalURL()  + PUT_CUSTOMER_INVESTMENT_DETAILS_URL;
		customerInvestmentDetailsURL = StringUtil.replace(customerInvestmentDetailsURL, CUSTOMER_INVESTMENT_DETAILS_ID_PARAM, String.valueOf(leadId));
		JSONObject cusInvestmentRes = getCustomerInvestmentDetailsByLead(leadId, themeDisplay);

	    CISHtmlContentReader cisHtmlContentReader = new CISHtmlContentReader();
	    if(!applicationNumber.isEmpty()) {
	    	String htmlContent = cisHtmlContentReader.getHtmlByAppId(applicationNumber,cusInvestmentRes, themeDisplay, etipCoreAPI);
			PDFUtil.generatePDFByResource(htmlContent, "CIS_"+applicationNumber, resourceResponse);
	    }
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

	private static final Log logger = LogFactoryUtil.getLog(CISHtmlToPdfMVCResourceCommand.class);
}
