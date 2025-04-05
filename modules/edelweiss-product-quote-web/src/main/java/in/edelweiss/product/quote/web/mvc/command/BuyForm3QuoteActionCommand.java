package in.edelweiss.product.quote.web.mvc.command;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_API;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.REDIRECT_URL;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ING_PLAN_SUB_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COLUMN_PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EDUCATION_QUALIFICATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.IS_SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_JOURNEY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NATURE_OF_DUTY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NDNC_CONSENT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PINCODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;

import com.edelweiss.http.core.api.ETIPCoreAPI;
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
//import com.sun.org.apache.bcel.internal.generic.LNEG;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

//import aQute.bnd.annotation.component.Reference;
import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;;;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"mvc.command.name=add/buy_journey" }, service = MVCActionCommand.class)

public class BuyForm3QuoteActionCommand extends BaseMVCActionCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	private static Log log = LogFactoryUtil.getLog(BuyForm3QuoteActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

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

			log.debug("BuyForm3QuoteActionCommand >> doProcessAction >> prodcut name :: " + productName
					+ " product id :: " + productCode);

			String value= ParamUtil.getString(actionRequest, "fromProductPage");
			String leadId=null;
			if(Validator.isNotNull(value) && value.equalsIgnoreCase("yes")) {
				leadId = edelweissTokioCommonApi.getLeadIdFromCookie(actionRequest, LEAD_ID);
			}

			Map<String, String> smokerPicklistMap = edelweissTokioCommonApi
					.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SMOKER, themeDisplay.getCompanyId());

			String customerEnquiryAPIURL = MessageFormat.format(
					GET_PRODUCT_MASTER_RELATIONAL_API + GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS, productId);
			JSONObject apiResponseObject = etipCoreApi.callGetAPI(new HashMap<>(),
					themeDisplay.getPortalURL() + customerEnquiryAPIURL, false, liferayUserName, liferayPassword);

			Map<String, String> mapformMap = new HashMap<>();

			mapformMap = edelweissTokioCommonApi.addUTMCampaignParams(actionRequest, mapformMap);
			mapformMap.put(PRODUCT_ID, productCode);
			mapformMap.put(LEAD_FORM_TYPE, LEAD_FORM_TYPE_RECCOMENDATION);
			mapformMap.put(PRODUCT_CODE, productCode);
			mapformMap.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productCode);
			mapformMap.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);
			mapformMap.put(QUOTE_STAGE, SITE_SECTION_PRE_QUOTE);
			mapformMap.put(PRODUCT_NAME_QUOTE, productName);
			mapformMap.put(NDNC_CONSENT, String.valueOf(Boolean.TRUE));

			mapformMap.put(FULL_NAME, ParamUtil.getString(actionRequest, FULL_NAME));
			mapformMap.put(DATE_OF_BIRTH, ParamUtil.getString(actionRequest, DATE_OF_BIRTH));
			mapformMap.put(MOBILE_NUMBER, ParamUtil.getString(actionRequest, MOBILE_NUMBER));
			mapformMap.put(GENDER, ParamUtil.getString(actionRequest, GENDER));
			mapformMap.put(EMAIL, ParamUtil.getString(actionRequest, EMAIL));
			mapformMap.put(OCCUPATION, ParamUtil.getString(actionRequest, OCCUPATION));
			mapformMap.put(EDUCATION_QUALIFICATION, ParamUtil.getString(actionRequest, EDUCATION_QUALIFICATION));
			mapformMap.put(NATURE_OF_DUTY, ParamUtil.getString(actionRequest, NATURE_OF_DUTY));
			mapformMap.put(ANNUAL_INCOME, ParamUtil.getString(actionRequest, ANNUAL_INCOME, StringPool.BLANK).replaceAll(StringPool.COMMA, StringPool.BLANK));
			mapformMap.put(PINCODE, ParamUtil.getString(actionRequest, PINCODE));
			mapformMap.put(SMOKER, ParamUtil.getString(actionRequest, SMOKER));

			/*
			 * set partner source for LMS
			 */
			String partnerSource = ParamUtil.getString(actionRequest, EdelweissObjectConstants.PARTNER_SOURCE);
			JSONObject partner = edelweissTokioCommonApi.getPartnerData(actionRequest, productId, themeDisplay,
					partnerSource);

			if (partner.getBoolean("isValidPartner")) {
				mapformMap.put(EdelweissObjectConstants.PARTNER_SOURCE,
						partner.getString(EdelweissObjectConstants.PARTNER_SOURCE));
				edelweissTokioCommonApi.setPartnerCookie(true, actionRequest, actionResponse);
			} else {
				edelweissTokioCommonApi.setPartnerCookie(false, actionRequest, actionResponse);
			}

			if (Validator.isNotNull(leadId)) {
				mapformMap.put(LEAD_ID, leadId);

				String leadUpdateRequestBody = edelweissTokioCommonApi.getUpdateLMSRequestBody(mapformMap);
				log.debug("BuyForm3QuoteActionCommand >>>> doProcessAction >>> updateRequestBody  ::: "
						+ leadUpdateRequestBody);

				JSONObject updateLeadResponse = edelweissTokioCommonApi.updateLMSLead(leadUpdateRequestBody);
				log.debug("BuyForm3QuoteActionCommand >>>> doProcessAction >>>> Lead Update Successully ::: "
						+ updateLeadResponse);

				mapformMap.put(LEAD_STATUS, updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
				mapformMap.put(LMS_INTEREST_ID,
						updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
				mapformMap.put(LMS_QUOTE_ID,
						updateLeadResponse.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));

				CustomerEnquiry customerEnquiry = edelweissTokioCommonApi
						.getCustomerEnquiryByLeadId(themeDisplay.getPortalURL(), leadId);

				String customerId = String.valueOf(customerEnquiry.getId());
				String urlForCustomerPutData = themeDisplay.getPortalURL() + PUT_CUSTOMER_DATA;
				urlForCustomerPutData = StringUtil.replace(urlForCustomerPutData, "{customerEnquiryId}", customerId);
				JSONObject lrJSON = LiferayObjectModel.createLiferayRequestJSON(mapformMap);

				// set partner
				lrJSON = edelweissTokioCommonApi.setPartner(lrJSON, partner, actionResponse);

				JSONObject customerEnquiryResponse = etipCoreApi.callPutAPI(lrJSON, urlForCustomerPutData, false,
						liferayUserName, liferayPassword);

				log.debug(customerEnquiryResponse);
				
				if(Validator.isNull(customerEnquiryResponse)) {
					throw new Exception("Unable to update Lead data in Liferay Object. Got Null response with lead Id: "+leadId);
				}

				/*
				 * Add/Update/remove FamilyDetails
				 */
				if (Validator.isNotNull(ParamUtil.getString(actionRequest, EdelweissObjectConstants.SPOUSE_PARENT_RELATION))) {

					Map<String, String> familyDetailsparam = new HashMap<>();
					familyDetailsparam.put(EdelweissObjectConstants.ASSURED_RELATION, ParamUtil.getString(actionRequest,
							EdelweissObjectConstants.SPOUSE_PARENT_RELATION, StringPool.BLANK));
					familyDetailsparam.put(EdelweissObjectConstants.FAMILY_OCCUPATION, ParamUtil
							.getString(actionRequest, EdelweissObjectConstants.SPOUSE_OCCUPATION, StringPool.BLANK));
					familyDetailsparam.put(EdelweissObjectConstants.FAMILY_TOTAL_SUM_ASSURED, ParamUtil
							.getString(actionRequest, EdelweissObjectConstants.SPOUSE_SUMASSURED, StringPool.BLANK)
							.replaceAll(StringPool.COMMA, StringPool.BLANK));
					familyDetailsparam.put(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,
							customerEnquiryResponse.getString("id"));

					CustomerFamilyDetailsRel customerFamilyDetails = edelweissTokioCommonApi
							.getRequestCustomerFamilyDetails(familyDetailsparam, leadId);

					edelweissTokioCommonApi.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails,
							null);
				} else {
					JSONObject familyDetailsResponse = edelweissTokioCommonApi.getFamilyDetailsByLeadId(themeDisplay.getPortalURL(), leadId);
					if(Validator.isNotNull(familyDetailsResponse) && Validator.isNotNull(familyDetailsResponse.getString(EdelweissObjectConstants.ID))) {
						edelweissTokioCommonApi.deleteFamilyDetailsById(themeDisplay.getPortalURL(), familyDetailsResponse.getString(EdelweissObjectConstants.ID));
					}
				}

			} else {

				apiResponseObject.put(ING_PLAN_SUB_TYPE,
						smokerPicklistMap.get(ParamUtil.getString(actionRequest, IS_SMOKE)));

				apiResponseObject.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);

				JSONObject generatedLeadId = edelweissTokioCommonApi.generateLead(mapformMap, productName,
						apiResponseObject, actionRequest.getUserAgent());
				log.debug(
						"BuyForm3QuoteActionCommand >>>> doProcessAction >>> Lead Id Generated  :: " + generatedLeadId);

				if (Validator.isNull(generatedLeadId) || !generatedLeadId.getBoolean(STATUS)
						|| Validator.isNotNull(generatedLeadId.getJSONArray(EdelweissCommonConstants.ERRORS))) {
					log.error("BuyForm3QuoteActionCommand >>>  doProcessAction >>> Unable to Generate Lead data ::: "
							+ generatedLeadId.getJSONArray(EdelweissCommonConstants.ERRORS));
					actionRequest.setAttribute("showErrorModal", true);
				}

				mapformMap.put(LEAD_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
				mapformMap.put(LEAD_STATUS, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
				mapformMap.put(LMS_INTEREST_ID,
						generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
				mapformMap.put(LMS_QUOTE_ID,
						generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));
				mapformMap.put(SMOKER, ParamUtil.getString(actionRequest, SMOKER));

				String productEnquiriesURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL;

				JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(mapformMap);

				/*
				 * set partner
				 */
				liferayJSON = edelweissTokioCommonApi.setPartner(liferayJSON, partner, actionResponse);

				JSONObject lrProductResponse = etipCoreApi.callPostAPI(liferayJSON, productEnquiriesURL, false,
						liferayUserName, liferayPassword);
				log.debug("BuyForm3QuoteActionCommand >>> doProcessAction >>> lr product response  ::: "
						+ lrProductResponse);
				
				if(Validator.isNull(lrProductResponse)) {
					throw new Exception("Unable to add Lead data to Liferay Object.");
				}

				/*
				 * Add FamilyDetails
				 */
				if (Validator.isNotNull(ParamUtil.getString(actionRequest, EdelweissObjectConstants.SPOUSE_PARENT_RELATION))) {

					Map<String, String> familyDetailsparam = new HashMap<>();
					familyDetailsparam.put(EdelweissObjectConstants.ASSURED_RELATION,
							ParamUtil.getString(actionRequest, EdelweissObjectConstants.SPOUSE_PARENT_RELATION, StringPool.BLANK));
					familyDetailsparam.put(EdelweissObjectConstants.FAMILY_OCCUPATION, ParamUtil
							.getString(actionRequest, EdelweissObjectConstants.SPOUSE_OCCUPATION, StringPool.BLANK));
					familyDetailsparam.put(EdelweissObjectConstants.FAMILY_TOTAL_SUM_ASSURED, ParamUtil
							.getString(actionRequest, EdelweissObjectConstants.SPOUSE_SUMASSURED, StringPool.BLANK));
					familyDetailsparam.put(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,
							lrProductResponse.getString("id"));

					CustomerFamilyDetailsRel customerFamilyDetails = edelweissTokioCommonApi
							.getRequestCustomerFamilyDetails(familyDetailsparam, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));

					edelweissTokioCommonApi.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails,
							null);
				}
				
				/*
				 * creating cookie code to set lead id and hide error and success messages
				 */
				HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
				Cookie cookie = new Cookie("leadId", generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
				cookie.setPath("/");
				cookie.setSecure(true);
				response.addCookie(cookie);
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);
			}

			String redirectURL = themeDisplay.getURLCurrent();
			redirectURL = redirectURL.split("/buy")[0];
			actionResponse.sendRedirect(redirectURL + REDIRECT_URL);
			actionRequest.setAttribute("showErrorModal", false);
		} catch (Exception e) {
			log.error("Unable to Submit buy form 3: " + e);
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
			actionRequest.setAttribute("showErrorModal", true);
		}
	}
}
