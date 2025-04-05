package edelweiss.campaign.pages.web.mvc.command;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.BUY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CHILD_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COLUMN_PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUST_MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_PRODUCT_MASTER_RELATIONAL_API;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_FORM_JSP;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_JOURNEY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NDNC_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SUITABILITY_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.WEB_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.asset.publisher.constants.AssetPublisherPortletKeys;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.campaign.pages.web.constants.EdelweissCampaignPagesWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.Field;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name="+EdelweissCampaignPagesWebPortletKeys.EDELWEISSCAMPAIGNPAGESWEB,
	        "javax.portlet.name="+AssetPublisherPortletKeys.ASSET_PUBLISHER,
	        "mvc.command.name=add_lead"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissGenerateLeadResourceCommand extends BaseMVCResourceCommand {

	@Reference
	private ETIPCoreAPI etipCoreAPI;
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissGenerateLeadResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		log.debug("EdelweissGenerateLeadResourceCommand >>>  doServeResource invoked ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);

		HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		JSONObject validator = edelweissTokioCommonApi.validate(resourceRequest);
		
		if(Validator.isNotNull(validator.get("message"))) {
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			ServletResponseUtil.write(httpServletResponse, validator.toString());
			return;
		}
		
		//Step 1: Fetch the metadata
		JSONObject response = JSONFactoryUtil.createJSONObject();
		PortletPreferences preferences = resourceRequest.getPreferences();
		//Long campaignPageFormId = Long.valueOf(preferences.getValue("campaignPageForm", StringPool.BLANK));
		//DDMFormInstance ddmFormInstance = DDMFormInstanceServiceUtil.getFormInstance(campaignPageFormId);

		String redirectionURL = preferences.getValue("redirectionURL", "");
		boolean showThankYouMessage =   GetterUtil.getBoolean(preferences.getValue("showThankYouMessage", "false"),false);

		//Step 2: Get the required custom fields value 
		String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), COLUMN_PRODUCT_ID);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
		String leadFormJsp = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), LEAD_FORM_JSP);

		Map<String, String> formValues = new HashMap<>();
		
		//Set UTM Campaign
		formValues = edelweissTokioCommonApi.addUTMCampaignParams(resourceRequest, formValues);
		
		// get the value of the life cover
		int lifeCover = ParamUtil.getInteger(resourceRequest, EdelweissObjectConstants.LIFE_COVER, 0);
		if(lifeCover==1 ){
			lifeCover=2500000;
		}else if(lifeCover>=32 && lifeCover<=35){
			lifeCover=5000000;
		} else if(lifeCover>=62 && lifeCover<=67){
			lifeCover=10000000;
		} else if(lifeCover==100){
			lifeCover=100000000;
		}else if(lifeCover>1 && lifeCover<32){
			lifeCover=3500000;
		}else if(lifeCover>35 && lifeCover<62){
			lifeCover=7500000;
		}else if(lifeCover>67 && lifeCover<100){
			lifeCover= 60000000;
		}
		String lifeCoverValue = Integer.toString(lifeCover);

		//Step 3: Creating Form View dynamically based on the JSON stored in the Liferay Database
		FormView formViews = edelweissTokioCommonApi.getFormData(resourceRequest, leadFormJsp);
		for(Field fields : formViews.getFields()) {
			if(Validator.isNull(fields.getHidden()) || fields.getHidden().equalsIgnoreCase(String.valueOf(Boolean.FALSE))) {
				 formValues.put(fields.getFieldReference(), ParamUtil.getString(resourceRequest, fields.getFieldReference()));
				 log.info(fields.getFieldReference() + ">>>" + ParamUtil.getString(resourceRequest, fields.getFieldReference()));
			}
		}
		
		if(Validator.isNotNull(ParamUtil.getString(resourceRequest, SMOKER)) && !ParamUtil.getString(resourceRequest, SMOKER).isBlank()) {
			formValues.put(SMOKER, ParamUtil.getString(resourceRequest, SMOKER));
		}
		
		if(Validator.isNotNull(ParamUtil.getString(resourceRequest, MARRIED)) && !ParamUtil.getString(resourceRequest, MARRIED).isBlank()) {
			formValues.put(MARRIED, ParamUtil.getString(resourceRequest, MARRIED));
		} else if(Validator.isNotNull(ParamUtil.getString(resourceRequest, CUST_MARRIED)) && !ParamUtil.getString(resourceRequest, CUST_MARRIED).isBlank()) {
			formValues.put(CUST_MARRIED, ParamUtil.getString(resourceRequest, CUST_MARRIED));
		}
		
		if(Validator.isNotNull(ParamUtil.getString(resourceRequest, CHILD_NAME)) && !ParamUtil.getString(resourceRequest, CHILD_NAME).isBlank()) {
			formValues.put(CHILD_NAME, ParamUtil.getString(resourceRequest, CHILD_NAME));
		}
		
		if(Validator.isNotNull(ParamUtil.getString(resourceRequest, ANNUAL_INCOME)) && !ParamUtil.getString(resourceRequest, ANNUAL_INCOME).isBlank()) {					 
			 formValues.put(ANNUAL_INCOME, ParamUtil.getString(resourceRequest, ANNUAL_INCOME));
		}
		
		// lifeCover field is added to oisumassured
		if(Validator.isNotNull(lifeCoverValue)) {
			formValues.put(EdelweissObjectConstants.OI_SUM_ASSURED, lifeCoverValue);
		}

		formValues.put(EdelweissObjectConstants.PARTNER_SOURCE, "Campaign");

		try {
			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

			String customerEnquiryAPIURL = MessageFormat.format(GET_PRODUCT_MASTER_RELATIONAL_API + GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS, productId);
			
			log.debug("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> CustomerEnquiryAPI::URL ::: " + customerEnquiryAPIURL);
			
			// Step 4: Fetching metadata details like Campaign Id, C_ID,V_ID from Liferay Objects
			JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
			

			String siteSection = themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"));						
			apiResponseObject.put(WEB_URL,siteSection);

			siteSection = siteSection.replace("/", StringPool.BLANK);
			int startIndex = Math.max(siteSection.length() - 50, 0);
			siteSection = siteSection.substring(startIndex);
			apiResponseObject.put(SITE_SECTION, StringUtil.getTitleCase(siteSection, false, ""));

			formValues.put(QUOTE_STAGE, EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE);

			//Step 5: Calling LMS API to add the customer data
			JSONObject responseJSON = edelweissTokioCommonApi.generateLead(formValues, productName, apiResponseObject, resourceRequest.getUserAgent());

			if(Validator.isNotNull(responseJSON) && responseJSON.getBoolean(STATUS)) {

				//Step 6: Call Liferay to update the lead data in LR Objects
				String leadId = responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_ID);
				log.info("EdelweissGenerateLeadResourceCommand >>>  leadId >>> " + leadId);

				formValues.put(LEAD_ID, leadId);
				formValues.put(LEAD_STATUS, responseJSON.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
				formValues.put(LMS_INTEREST_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
				formValues.put(LMS_QUOTE_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));
				formValues.put(PRODUCT_ID, productCode);
				formValues.put(SUITABILITY_CONSENT, String.valueOf(Boolean.TRUE));
				formValues.put(NDNC_CONSENT, String.valueOf(Boolean.TRUE));
				formValues.put(PRODUCT_NAME_QUOTE, productName);

				String externalRefCodeUrl = themeDisplay.getPortalURL()+LIFERAY_PRODUCT_ENQUIRY_URL;
				JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(formValues);
				
				log.info("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> liferayJSON ::: " + liferayJSON);
				
				JSONObject lifreayResponse = etipCoreAPI.callPostAPI(liferayJSON, externalRefCodeUrl, false, liferayUserName, liferayPassword);
				
				log.info("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> LR Invocation Response ::: " + lifreayResponse);
				
				//Step 7: Create Cookie by LeadId
				edelweissTokioCommonApi.createCookie(LEAD_ID, leadId, PortalUtil.getHttpServletResponse(resourceResponse));
				
				String url = themeDisplay.getURLCurrent();
				url = url.split("\\?")[0];
				
				if(Validator.isNotNull(redirectionURL) && !redirectionURL.isBlank()) {
					url = redirectionURL;
				}else {
					url = url.concat(BUY_URL);
				}
				
				log.info("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> showThankYouMessage ::: " + showThankYouMessage);
				log.info("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> redirectionURL ::: " + redirectionURL);
				
				response.put("redirectURL", url);
				response.put("showThankYouMessage", showThankYouMessage);
				response.put(STATUS, HttpServletResponse.SC_OK);
				response.put(LEAD_ID, leadId);

			}else if(Validator.isNotNull(responseJSON) && Validator.isNotNull(responseJSON.getJSONArray(EdelweissCommonConstants.ERRORS))){
				log.error("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> Unable to Generate Lead data ::: " + responseJSON.getJSONArray(EdelweissCommonConstants.ERRORS));
				resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE, String.valueOf(HttpServletResponse.SC_NOT_FOUND));
				response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			}else {
				log.error("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> Unable to Generate Lead data ::: ");
				resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,String.valueOf(HttpServletResponse.SC_NOT_FOUND));
				response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			}	
		}catch(Exception e) {
			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE, String.valueOf(HttpServletResponse.SC_NOT_FOUND));
			response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);			
			log.error("EdelweissGenerateLeadResourceCommand >>>  doServeResource >>> An exception occurred while submitting the data :::  " + e);
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		
		ServletResponseUtil.write(httpServletResponse, response.toString());
		
	}

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
