package in.edelweiss.product.quote.web.mvc.command;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_API;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.REDIRECT_URL;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ING_PLAN_SUB_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COLUMN_PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COUNTRY_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ISNRI;
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
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NRI_MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SUITABILITY_CONSENT;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETLIValidationException;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portlet.ResourceRequestFactory;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"mvc.command.name=quote_journey/submit/buy_form_2" }, service = MVCActionCommand.class)
public class BuyForm2QuoteActionCommand extends BaseMVCActionCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	private static Log log = LogFactoryUtil.getLog(BuyForm2QuoteActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		JSONObject validator = edelweissTokioCommonApi.validate(actionRequest);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(Validator.isNotNull(validator.get("message"))) {			
			actionResponse.sendRedirect(themeDisplay.getURLCurrent());		
			actionRequest.setAttribute("showErrorModal", true);
			throw new ETLIValidationException(validator.getString("message"));
		}
		
		try {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

		String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
		String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), COLUMN_PRODUCT_ID);
		log.debug("BuyForm2QuoteActionCommand >> doProcessAction >> prodcut name :: " + productName
				+ " product id :: " + productCode);

		// Step 1 : Getting lead value from cookie
		//ResourceRequest resourceRequest=(ResourceRequest) actionRequest;
			/*
			 * PortletSession portletSession = actionRequest.getPortletSession(); String
			 * value = (String) portletSession.getAttribute("LIFERAY_SHARED_sessionMessage",
			 * PortletSession.APPLICATION_SCOPE); log.debug("value2...."+value);
			 */
		String value= ParamUtil.getString(actionRequest, "fromProductPage");
		log.debug("value2...."+value);
		String leadId=null;
		if(Validator.isNotNull(value) && value.equalsIgnoreCase("yes")) {
			leadId = edelweissTokioCommonApi.getLeadIdFromCookie(actionRequest, LEAD_ID);
		}
		//portletSession.removeAttribute("LIFERAY_SHARED_sessionMessage", PortletSession.APPLICATION_SCOPE);
		
		Map<String, String> investmentObjectiveMap = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());

		// Step 2 : Code to get the Campaign Id, C_ID,V_ID
		String productMasterMetaDataURL = MessageFormat
				.format(GET_PRODUCT_MASTER_RELATIONAL_API + GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS, productId);

		log.debug("BuyForm2QuoteActionCommand >>>  doProcessAction >>> URL ::: " + productMasterMetaDataURL);

		// Step 4: Fetching metadata details like Campaign Id, C_ID,V_ID from Liferay
		// Objects
		JSONObject productMetadataResponse = etipCoreApi.callGetAPI(new HashMap<>(),
				themeDisplay.getPortalURL() + productMasterMetaDataURL, false, liferayUserName, liferayPassword);
		// Step 3 : Getting form field's values
		Map<String, String> mapformMap = new HashMap<>();
		
		//Set UTM Campaign
		mapformMap = edelweissTokioCommonApi.addUTMCampaignParams(actionRequest, mapformMap);
		
		mapformMap.put(FULL_NAME, ParamUtil.getString(actionRequest, FULL_NAME));
		mapformMap.put(DATE_OF_BIRTH, ParamUtil.getString(actionRequest, DATE_OF_BIRTH));
		mapformMap.put(MOBILE_NUMBER, ParamUtil.getString(actionRequest, MOBILE_NUMBER));
		mapformMap.put(GENDER, ParamUtil.getString(actionRequest, GENDER));
		mapformMap.put(EMAIL, ParamUtil.getString(actionRequest, EMAIL));
		mapformMap.put(RECOMMENDED_OBJECTIVE,
				investmentObjectiveMap.get(ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE)));
		mapformMap.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE, "2"));
		mapformMap.put(PRODUCT_ID, productCode);
		mapformMap.put(LEAD_FORM_TYPE, LEAD_FORM_TYPE_RECCOMENDATION);
		mapformMap.put(PRODUCT_CODE, productCode);
		mapformMap.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productCode);
		mapformMap.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);
		mapformMap.put(QUOTE_STAGE, SITE_SECTION_PRE_QUOTE);
		mapformMap.put(PRODUCT_NAME_QUOTE, productName);
		mapformMap.put(NDNC_CONSENT, String.valueOf(Boolean.TRUE));

		if (ParamUtil.getString(actionRequest, "suitabiltyMatrix").equalsIgnoreCase("on")) {
			mapformMap.put(SUITABILITY_CONSENT, "Yes");
		}
		
		//set partner source for LMS
		String partnerSource = ParamUtil.getString(actionRequest, EdelweissObjectConstants.PARTNER_SOURCE);
		JSONObject partner = edelweissTokioCommonApi.getPartnerData(actionRequest, productId, themeDisplay,partnerSource);
		
		if(partner.getBoolean("isValidPartner")) {
			mapformMap.put(EdelweissObjectConstants.PARTNER_SOURCE, partner.getString(EdelweissObjectConstants.PARTNER_SOURCE));
			edelweissTokioCommonApi.setPartnerCookie(true, actionRequest, actionResponse);
		}else {
			edelweissTokioCommonApi.setPartnerCookie(false, actionRequest, actionResponse);
		}

		// NRI Checkbox
		log.debug(ParamUtil.getString(actionRequest, "isNri"));
		if (ParamUtil.getString(actionRequest, "isNri").equalsIgnoreCase("on")) {
			mapformMap.put(ISNRI, "Yes");
			mapformMap.put(NRI_MOBILE_NUMBER, ParamUtil.getString(actionRequest, COUNTRY_CODE, StringPool.BLANK)
					+ ParamUtil.getString(actionRequest, "phoneNo", StringPool.BLANK));
			mapformMap.put(COUNTRY_CODE, ParamUtil.getString(actionRequest, COUNTRY_CODE));
		}

		// Step 4 (a) Checking if leadId exists ?
		if (Validator.isNotNull(leadId)) {
			// Step 4 (i) Setting lead id in request map
			mapformMap.put(LEAD_ID, leadId);

			// Step 4 (ii) Calling update method
			String leadUpdateRequestBody = edelweissTokioCommonApi.getUpdateLMSRequestBody(mapformMap);
			log.debug("BuyForm2QuoteActionCommand >>>> doProcessAction >>> updateRequesBody  :: "
					+ leadUpdateRequestBody);
			JSONObject updateLeadResponse = edelweissTokioCommonApi.updateLMSLead(leadUpdateRequestBody);
			log.debug("BuyForm2QuoteActionCommand >>>> doProcessAction >>>> Lead Update Successully..... "
					+ updateLeadResponse);

			mapformMap.put(LEAD_STATUS, updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
			mapformMap.put(LMS_INTEREST_ID,
					updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
			mapformMap.put(LMS_QUOTE_ID,
					updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));

			CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(themeDisplay.getPortalURL(), leadId);

			// Step 4 (iii) putting the value in customer enquiry
			String customerId = Validator.isNotNull(customerEnquiry)?String.valueOf(customerEnquiry.getId()):StringPool.BLANK;
			String urlForCustomerPutData = themeDisplay.getPortalURL() + PUT_CUSTOMER_DATA;
			urlForCustomerPutData = StringUtil.replace(urlForCustomerPutData, "{customerEnquiryId}", customerId);
			JSONObject lrJSON = LiferayObjectModel.createLiferayRequestJSON(mapformMap);
			
			// set partner
			lrJSON = edelweissTokioCommonApi.setPartner(lrJSON, partner,actionResponse);
			
			etipCoreApi.callPutAPI(lrJSON, urlForCustomerPutData, false, liferayUserName, liferayPassword);
			// Step 4 (iv): Redirecting to the page
			String redirectURL = themeDisplay.getURLCurrent();
			redirectURL = redirectURL.split("/buy")[0];
			actionResponse.sendRedirect(redirectURL + REDIRECT_URL);
		} else {
			if(Validator.isNull(productMetadataResponse)) {
				log.error("No response found for Product Meta data request: "+productMetadataResponse);
				actionRequest.setAttribute("showErrorModal", true);
				return;
			}
			productMetadataResponse.put(ING_PLAN_SUB_TYPE,
					investmentObjectiveMap.get(ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE)));
			productMetadataResponse.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);
			// Step 4 (b) Generating lead ID code
			JSONObject generatedLeadId = edelweissTokioCommonApi.generateLead(mapformMap, productName,
					productMetadataResponse, actionRequest.getUserAgent());
			log.debug("BuyForm2QuoteActionCommand >>>> doProcessAction >>> Lead Id Generated  :: "
					+ generatedLeadId);

			if(Validator.isNotNull(generatedLeadId) && generatedLeadId.getBoolean(STATUS)) {
			
				mapformMap.put(LEAD_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
				mapformMap.put(LEAD_STATUS, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
				mapformMap.put(LMS_INTEREST_ID,
						generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
				mapformMap.put(LMS_QUOTE_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));
				mapformMap.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE));
			
				String productEnquiriesURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL;

				JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(mapformMap);
				
				// set partner
				liferayJSON = edelweissTokioCommonApi.setPartner(liferayJSON, partner,actionResponse);
				
				JSONObject lrProductResponse = etipCoreApi.callPostAPI(liferayJSON, productEnquiriesURL, false,
						liferayUserName, liferayPassword);
				log.debug("BuyForm2QuoteActionCommand >>> doProcessAction >>> lr product response  ::: "
						+ lrProductResponse);
				
				// Step 4 (c) creating cookie code to set lead id and hide error and success
				// messages
				HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
				Cookie cookie = new Cookie("leadId", generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
				cookie.setPath("/");
				response.addCookie(cookie);
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);
				// Step 4 (iv) : Redirecting to the page
				String redirectURL = themeDisplay.getURLCurrent();
				redirectURL = redirectURL.split("/buy")[0];
				actionResponse.sendRedirect(redirectURL + REDIRECT_URL);
				actionRequest.setAttribute("showErrorModal", false);
				
			}else if(Validator.isNotNull(generatedLeadId) && Validator.isNotNull(generatedLeadId.getJSONArray(EdelweissCommonConstants.ERRORS))){
				log.error("BuyForm2QuoteActionCommand >>>  doProcessAction >>> Unable to Generate Lead data ::: " + generatedLeadId.getJSONArray(EdelweissCommonConstants.ERRORS));
				actionRequest.setAttribute("showErrorModal", true);
			}
		}
		} catch (Exception e) {
			log.error("Unable to Submit buy form 2: "+e);
			actionRequest.setAttribute("showErrorModal", true);
		}
	}
}
