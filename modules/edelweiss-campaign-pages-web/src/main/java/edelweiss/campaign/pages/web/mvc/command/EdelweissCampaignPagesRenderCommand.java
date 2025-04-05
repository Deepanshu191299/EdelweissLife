package edelweiss.campaign.pages.web.mvc.command;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.campaign.pages.web.constants.EdelweissCampaignPagesWebPortletKeys;
import edelweiss.campaign.pages.web.portlet.EdelweissCampaignPagesWebPortlet;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissCampaignPagesWebPortletKeys.EDELWEISSCAMPAIGNPAGESWEB,
		"mvc.command.name=/" }, service = MVCRenderCommand.class)
public class EdelweissCampaignPagesRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		/*
		 * Current Product Details
		 */
		String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(),
				EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_ID);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(),
				EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_CODE);
		String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(),
				EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_NAME);
		renderRequest.setAttribute("productCode", productId);
		renderRequest.setAttribute("productCode", productCode);
		renderRequest.setAttribute("productName", productName);

		if (Validator.isNull(productCode)) {
			log.warn("Product Code is not configured");
			return DEFAULT;
		}

		/*
		 * Campaign Page Forma and Template Details
		 */
		PortletPreferences preferences = renderRequest.getPreferences();
		String campaignPageForm = preferences.getValue("campaignPageForm", StringPool.BLANK);
		String campaignPageTemplate = preferences.getValue("campaignPageTemplate", StringPool.BLANK);

		if (Validator.isNull(campaignPageForm) || Validator.isNull(campaignPageTemplate)) {
			log.warn("Campaign Page Form or Template details are not configured");
			return DEFAULT;
		}

		FormView formView = edelweissTokioCommonApi.getFormData(renderRequest, Long.valueOf(campaignPageForm));
		log.debug(formView);
		if (Validator.isNull(formView) || Validator.isNull(formView.getFields()) || formView.getFields().isEmpty()) {
			log.warn("No data availbe in the fetched form");
			return DEFAULT;
		}
		renderRequest.setAttribute("formView", formView);

		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(renderRequest, LEAD_ID);
		if (Validator.isNotNull(leadId)) {
			fetchLeadDetails(leadId, themeDisplay, renderRequest);
		}

		return campaignPageTemplate;

	}

	private void fetchLeadDetails(String leadId, ThemeDisplay themeDisplay, RenderRequest renderRequest) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

		String parameterURL = getLeadMetaDataURL(leadId);

		String urlToGetBasicDetails = themeDisplay.getPortalURL() + EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL
				+ EdelweissObjectConstants.FILTER_QUERY_PARAM + parameterURL;

		try {
			JSONObject customerBasicDetails = etipCoreApi.callGetAPI(new HashMap<>(), urlToGetBasicDetails, false,
					liferayUserName, liferayPassword);
			if(Validator.isNotNull(customerBasicDetails) && Validator.isNotNull(customerBasicDetails.getJSONArray("items")) && customerBasicDetails.getJSONArray("items").length()>0)
				renderRequest.setAttribute("customerBasicDetails",
						customerBasicDetails.getJSONArray("items").getJSONObject(0));
		} catch (ETIPSystemException e) {
			log.debug("Error while fetching the basic details with lead id: " + leadId);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}

	private static String getLeadMetaDataURL(String leadId) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(EdelweissObjectConstants.LEAD_ID);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + leadId + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}

	public static final String DEFAULT = "/default.jsp";

	private static Log log = LogFactoryUtil.getLog(EdelweissCampaignPagesWebPortlet.class);

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreApi;

}
