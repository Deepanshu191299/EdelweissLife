package in.edelweiss.product.quote.web.util;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.ACCESS_TOKEN;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.AUTHORIZATION_PASSWORD;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.AUTHORIZATION_URL;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.AUTHORIZATION_USERNAME;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.EDELWEISS_WEB_SALES;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GENERATE_LEAD_API_URL;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.WEB_URL;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CAMPAIGN_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CP_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.C_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INDIAN;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.VISITOR_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.V_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Reference;

import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.LMSRequest;
import in.edelweiss.tokio.common.model.Lmslead;
import in.edelweiss.tokio.common.model.Quote;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
public class EdelweissProductQuoteUtil {
	static Log log = LogFactoryUtil.getLog(EdelweissProductQuoteUtil.class);
     private ETIPCoreAPI etipCoreAPI;
	
	public EdelweissProductQuoteUtil(ETIPCoreAPI etipCoreAPI) {
		this.etipCoreAPI = etipCoreAPI;
	}
	@Reference 
	EdelweissTokioCommonApi edelweissTokioCommonApi ;
	
	public JSONObject generatingLeadId(Map<String, String> formMap , String productName,ActionRequest actionRequest , JSONObject metaDataResponse) throws JSONException, IOException, ETIPSystemException {

		String urlForLead = PropsUtil.get(GENERATE_LEAD_API_URL);
		String xApiKey = "rsFyf6I6d92KiIyXqZ1mP7scJtd4Vdeh7bFCCRJz";
		String tokenURL = PropsUtil.get(AUTHORIZATION_URL);
		String userName = PropsUtil.get(AUTHORIZATION_USERNAME);
		String password = PropsUtil.get(AUTHORIZATION_PASSWORD);
		JSONObject authorizations = JSONFactoryUtil.createJSONObject(etipCoreAPI.generateOauthToken(tokenURL, userName, password));
		
		String authorization=authorizations.getString(ACCESS_TOKEN);
		
		Lmslead lmsLead = new Lmslead(); 
		Quote quote = new Quote();
		lmsLead.setcId(metaDataResponse.getString(C_ID));
		lmsLead.setCampaignId(metaDataResponse.getString(CAMPAIGN_ID));
		lmsLead.setCpId(metaDataResponse.getString(CP_ID));
		lmsLead.setDevice(StringPool.BLANK);
		lmsLead.setDob(formMap.get(DATE_OF_BIRTH).trim());
		lmsLead.setEmail(formMap.get(EMAIL));
		lmsLead.setGender(formMap.get(GENDER));
		lmsLead.setName(formMap.get(FULL_NAME));
		lmsLead.setNationality(INDIAN);
		lmsLead.setPhone(formMap.get(MOBILE_NUMBER));
		lmsLead.setProduct(productName);
		lmsLead.setSiteSection(SITE_SECTION_PRE_QUOTE);
		lmsLead.setSource(EDELWEISS_WEB_SALES);
		lmsLead.setUserBrowser(actionRequest.getUserAgent());
		lmsLead.setvId(metaDataResponse.getString(V_ID));
		lmsLead.setVisitorId(VISITOR_ID);
		lmsLead.setWebUrl(WEB_URL);
		lmsLead.setQuote(quote);
		LMSRequest lmsRequest = new LMSRequest();
		lmsRequest.setLmslead(lmsLead);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(lmsRequest);
		
		JSONObject leadJson = JSONFactoryUtil.createJSONObject(jsonString);
		
		return etipCoreAPI.callPostAPI(leadJson, urlForLead, true, xApiKey, authorization);
	}

	public static String getLeadMetaDataURL(String leadId) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(EdelweissObjectConstants.LEAD_ID);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + leadId + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}
	 
	 
	 public static void hideSuccessAndErrorMessage(PortletRequest portletRequest) {
		 SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		 SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	 }
}
