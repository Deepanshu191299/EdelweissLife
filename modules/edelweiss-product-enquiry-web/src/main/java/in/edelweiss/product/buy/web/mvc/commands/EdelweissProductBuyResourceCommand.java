package in.edelweiss.product.buy.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.BUY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COLUMN_PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_PRODUCT_MASTER_RELATIONAL_API;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_FORM_JSP;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_JOURNEY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NDNC_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
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
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.enquiry.web.constants.EdelweissProductEnquiryWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.Field;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name="+EdelweissProductEnquiryWebPortletKeys.EDELWEISSPRODUCTENQUIRYWEB,
	        "javax.portlet.name="+AssetPublisherPortletKeys.ASSET_PUBLISHER,
	        "mvc.command.name=add_lead"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissProductBuyResourceCommand extends BaseMVCResourceCommand {

	@Reference
	private ETIPCoreAPI etipCoreAPI;
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissProductBuyResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction invoked ::: ");
		
		HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		
		JSONObject validator = edelweissTokioCommonApi.validate(resourceRequest);
		
		if(Validator.isNotNull(validator.get("message"))) {
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			ServletResponseUtil.write(httpServletResponse, validator.toString());
			return;
		}
		
		//Step 1: Fetch the metadata
		
		PortletPreferences preference = resourceRequest.getPreferences();
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		JSONObject response = JSONFactoryUtil.createJSONObject();
		
		//Step 2: Get the required custom field value 
		String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
		String leadFormJsp = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), LEAD_FORM_JSP);
		
		//Can be remove now
		String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), COLUMN_PRODUCT_ID);
		String redirectionURL = preference.getValue("redirectionURL", "");
		boolean showThankYouMessage =   GetterUtil.getBoolean(preference.getValue("showThankYouMessage", "false"),false);
		
		Map<String, String> formValues = new HashMap<>();
		
		//Set UTM Campaign
		formValues = edelweissTokioCommonApi.addUTMCampaignParams(resourceRequest, formValues);
		
		String requestType = ParamUtil.getString(resourceRequest, "requestType");
		
		log.debug(" requestType......"+requestType);
		
		if(requestType.equals("Home")) {	
			productName = ParamUtil.getString(resourceRequest, PRODUCT_NAME);
			productCode = ParamUtil.getString(resourceRequest, PRODUCT_CODE);
			
			log.debug(" productCode......"+productCode);
			
			formValues.put(DATE_OF_BIRTH, ParamUtil.getString(resourceRequest, DATE_OF_BIRTH));
			formValues.put(GENDER, ParamUtil.getString(resourceRequest, GENDER));
			formValues.put(FULL_NAME, ParamUtil.getString(resourceRequest, FULL_NAME));
			formValues.put(MOBILE_NUMBER, ParamUtil.getString(resourceRequest, MOBILE_NUMBER));
			formValues.put(EMAIL, ParamUtil.getString(resourceRequest, EMAIL));
			formValues.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(resourceRequest, INVESTMENT_OBJECTIVE));
			
		}else {		
			//Step 3: Creating Form View dynamically based on the JSON stored in the Liferay Database
			FormView formViews = edelweissTokioCommonApi.getFormData(resourceRequest, leadFormJsp);
			for(Field fields : formViews.getFields()) {
				if(Validator.isNull(fields.getHidden()) || fields.getHidden().equalsIgnoreCase(String.valueOf(Boolean.FALSE))) {
					 formValues.put(fields.getFieldReference(), ParamUtil.getString(resourceRequest, fields.getFieldReference()));
				}
				
			}
			
		}
		
		try {
			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
			

			String customerEnquiryAPIURL = MessageFormat.format(GET_PRODUCT_MASTER_RELATIONAL_API + GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS, productId);
			
			log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction >>> URL ::: " + customerEnquiryAPIURL);
			// Step 4: Fetching metadata details like Campaign Id, C_ID,V_ID from Liferay Objects
			JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
			log.debug("apiResponseObject....."+apiResponseObject);
			String siteSection = themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"));						
			log.debug("siteSection....."+siteSection);
			apiResponseObject.put(WEB_URL,siteSection);
			
			siteSection = siteSection.replace("/", StringPool.BLANK).concat(" Page");
			log.debug("siteSection....."+siteSection);
			apiResponseObject.put(SITE_SECTION, StringUtil.getTitleCase(siteSection, false, ""));
			//cp_id=13&v_id=22&c_id=87
			
			Map<String, String> yourIncomeMap = new HashMap<String, String>();
			
			if(Validator.isNotNull(formValues.get(ANNUAL_INCOME)) && !formValues.get(ANNUAL_INCOME).isBlank()) {
				 yourIncomeMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC, themeDisplay.getCompanyId());
				 log.debug("yourIncomeMap....."+yourIncomeMap);
				 formValues.put(ANNUAL_INCOME, yourIncomeMap.get(ANNUAL_INCOME));
			}
			
			formValues.put(QUOTE_STAGE, EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE);
			//Step 5: Calling LMS API to add the customer data
			log.debug("apiResponseObject..."+apiResponseObject);
			log.debug("formValues..."+formValues);
			log.debug("productName..."+productName);
			JSONObject responseJSON = edelweissTokioCommonApi.generateLead(formValues, productName, apiResponseObject, resourceRequest.getUserAgent());
			log.debug("responseJSON..."+responseJSON);
			PortletSession portletSession = resourceRequest.getPortletSession();
			portletSession.setAttribute("LIFERAY_SHARED_sessionMessage", "yes" ,PortletSession.APPLICATION_SCOPE);
			log.debug("Portlet session is set...."+(String) portletSession.getAttribute("LIFERAY_SHARED_sessionMessage",PortletSession.APPLICATION_SCOPE));

			if(Validator.isNotNull(responseJSON) && responseJSON.getBoolean(STATUS)) {
				//Step 6: Call Liferay to update the lead data in LR Objects
				String leadId = responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_ID);
				formValues.put(LEAD_ID, leadId);
				formValues.put(LEAD_STATUS, responseJSON.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
				formValues.put(LMS_INTEREST_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
				formValues.put(LMS_QUOTE_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));
				formValues.put(PRODUCT_ID, productCode);
				formValues.put(SUITABILITY_CONSENT, String.valueOf(Boolean.TRUE));
				formValues.put(NDNC_CONSENT, String.valueOf(Boolean.TRUE));
				formValues.put(PRODUCT_NAME_QUOTE, productName);
				if(Validator.isNotNull(ParamUtil.getString(resourceRequest, ANNUAL_INCOME)) && !ParamUtil.getString(resourceRequest, ANNUAL_INCOME).isBlank()) {					 
					 formValues.put(ANNUAL_INCOME, ParamUtil.getString(resourceRequest, ANNUAL_INCOME));
				}
				String externalRefCodeUrl = themeDisplay.getPortalURL()+LIFERAY_PRODUCT_ENQUIRY_URL;
				JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(formValues);
				log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction >>> liferayJSON ::: " + liferayJSON);
				
				JSONObject lifreayResponse = etipCoreAPI.callPostAPI(liferayJSON, externalRefCodeUrl, false, liferayUserName, liferayPassword);
				log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction >>> LR Invocation Response ::: " + lifreayResponse);
				
				//Step 7: Create Cookie by LeadId
				edelweissTokioCommonApi.createCookie(LEAD_ID, leadId, PortalUtil.getHttpServletResponse(resourceResponse));
				
				String url = themeDisplay.getURLCurrent();
				url = url.split("\\?")[0];
				
				if(Validator.isNotNull(redirectionURL) && !redirectionURL.isBlank()) {
					url = redirectionURL;
				}else {
					url = url.concat(BUY_URL);
				}
				
				log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction >>> showThankYouMessage ::: " + showThankYouMessage);
				log.debug("EdelweissProductBuyActionCommand >>>  doProcessAction >>> redirectionURL ::: " + redirectionURL);
				
				response.put("redirectURL", url);
				response.put("showThankYouMessage", showThankYouMessage);
				response.put(STATUS, HttpServletResponse.SC_OK);
				response.put(LEAD_ID, leadId);

			}else if(Validator.isNotNull(responseJSON) && Validator.isNotNull(responseJSON.getJSONArray(EdelweissCommonConstants.ERRORS))){
				log.error("EdelweissProductBuyActionCommand >>>  doProcessAction >>> Unable to Generate Lead data ::: " + responseJSON.getJSONArray(EdelweissCommonConstants.ERRORS));
				resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
						String.valueOf(HttpServletResponse.SC_NOT_FOUND));
				response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			}
			else {
				log.error("EdelweissProductBuyActionCommand >>>  doProcessAction >>> Unable to Generate Lead data ::: ");
				resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
						String.valueOf(HttpServletResponse.SC_NOT_FOUND));
				response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);

			}
		}catch(Exception e) {
			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_NOT_FOUND));
			response.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			log.error("EdelweissProductBuyActionCommand >>>  doProcessAction >>> An exception occurred while submitting the data :::  " + e);
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		
		ServletResponseUtil.write(httpServletResponse, response.toString());
		
	}

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
