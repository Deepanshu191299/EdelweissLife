package edelweiss.csb.integration.web.portlet;

import edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys;

import edelweiss.csb.integration.web.model.CsbIntegrationResponseData;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.encryptor.EncryptorException;
import com.liferay.portal.kernel.encryptor.EncryptorUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_CSB_INTEGRATION_DETAILS_ID_PARAM;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author razeena.p
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EdelweissCsbIntegrationWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissCsbIntegrationWebPortletKeys.EDELWEISSCSBINTEGRATIONWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissCsbIntegrationWebPortlet extends MVCPortlet {
	
	private static final Log logger = LogFactoryUtil.getLog(EdelweissCsbIntegrationWebPortlet.class);

	@Reference
	ETIPCoreAPI etipCoreApi;
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	@Override
		public void render(RenderRequest renderRequest, RenderResponse renderResponse)
				throws java.io.IOException, PortletException {
			// TODO Auto-generated method stub
		logger.info("inside the render command::::::");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		HttpServletRequest request = PortalUtil.getHttpServletRequest(renderRequest);
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(request);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		
		String objectCsbIb = ParamUtil.getString(originalRequest, "appId");
		
		// Decrypt data
		String key1 = "ccDL0pxQmjqZEh4HRF3+sQ==";
		 
		// Decrypt data
		String decryptedText ="";
		try {
			logger.info(">>>>>>objectCsbIb " + objectCsbIb);
			decryptedText = EncryptorUtil.decrypt(EncryptorUtil.deserializeKey(key1), objectCsbIb);
			decryptedText = URLDecoder.decode(decryptedText ,"UTF-8");
			logger.info("Decrypted Text: " + decryptedText);
		} catch (EncryptorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
 
		 logger.info("Decrypted Text: " + decryptedText);
		
		String csbCurrentURL = themeDisplay.getURLCurrent();
		logger.info("csbCurrentURL -------- "+ csbCurrentURL);
		
		if(csbCurrentURL.contains("/reports")) {
			renderRequest.setAttribute("isReportPage", true);
		}else {
			renderRequest.setAttribute("isReportPage", false);
		}
		
		logger.info("requestID:::"+ decryptedText);
		
		Map<String, String> mapformMap1 = new HashMap<>();
		mapformMap1.put("id", decryptedText);
		logger.info("mapformMap1::::"+ mapformMap1);
		//JSONObject requestJson1 = JSONFactoryUtil.createJSONObject(mapformMap1);
		String getCsbIntegrationUrl = themeDisplay.getPortalURL() + GET_CSB_INTEGRATION_DETAILS_URL;
		logger.info("getCsbIntegrationUrl::::::" + getCsbIntegrationUrl);
		
		 String getCsbIntegratedURL = StringUtil.replace(getCsbIntegrationUrl, GET_CSB_INTEGRATION_DETAILS_ID_PARAM, decryptedText);
		 logger.info("getCsbIntegratedURL:::"+ getCsbIntegratedURL);
		
		try {
			JSONObject getCsbResponse = etipCoreApi.callGetAPI(mapformMap1, getCsbIntegratedURL, false, liferayUserName, liferayPassword);
			//logger.info("csbIntegration >>> doProcessAction >>> Liferay response  ::: "+ getCsbResponse);
			logger.info("isnide rendercommand:::::" + getCsbResponse.getString("id"));
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

			CsbIntegrationResponseData csbIntegrationResponseData = objectMapper.readValue(getCsbResponse.toString(), CsbIntegrationResponseData.class);
			
			renderRequest.setAttribute("getCsbResponse", csbIntegrationResponseData);
			logger.info("csbIntegrationResponseData.getcSBAccountNo() "+ csbIntegrationResponseData.getcSBAccountNo());
			
		} catch (ETIPSystemException e) {
			logger.error(e.getMessage());
		}
		
		renderRequest.setAttribute("appId", decryptedText);
		
		
			super.render(renderRequest, renderResponse);
		}
	
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws java.io.IOException, PortletException {
		// TODO Auto-generated method stub
		super.processAction(actionRequest, actionResponse);
	}

}
