package in.edelweiss.product.quote.web.mvc.command;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.REDIRECT_URL;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.URL_FOR_META_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EXTERNAL_REFERENCE_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_CUSTOMER_RELATION_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
import in.edelweiss.product.quote.web.util.EdelweissProductQuoteUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"mvc.command.name=quote_journey/total_protect_plus/buy" }, service = MVCActionCommand.class)
public class TotalProtectPlusQuoteActionCommand extends BaseMVCActionCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	private static Log log = LogFactoryUtil.getLog(TotalProtectPlusQuoteActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);

		log.debug("Product Name: " + productName);
		log.debug("Product Code: " + productId);

		String maritalStatus = ParamUtil.getString(actionRequest, ParameterConstants.MARITAL_STATUS, StringPool.BLANK);
		String haveChild = ParamUtil.getString(actionRequest, ParameterConstants.CHILD, StringPool.BLANK);
		String gender = ParamUtil.getString(actionRequest, ParameterConstants.GENDER);

		Map<String, String> customerDetails = new HashMap<>();
		customerDetails.put(FULL_NAME, ParamUtil.getString(actionRequest, ParameterConstants.FULL_NAME));
		customerDetails.put(DATE_OF_BIRTH, ParamUtil.getString(actionRequest, ParameterConstants.DATE_OF_DEATH));
		customerDetails.put(MOBILE_NUMBER, ParamUtil.getString(actionRequest, ParameterConstants.MOBILE_NO));
		customerDetails.put(GENDER, gender);
		customerDetails.put(EMAIL, ParamUtil.getString(actionRequest, ParameterConstants.EMAIL));

		customerDetails.put(ParameterConstants.ANNUAL_INCOME_RANGE,
				ParamUtil.getString(actionRequest, ParameterConstants.ANNUAL_INCOME_RANGE));
		customerDetails.put(ParameterConstants.SMOKE, ParamUtil.getString(actionRequest, ParameterConstants.SMOKE));
		customerDetails.put(ParameterConstants.MARITAL_STATUS, maritalStatus);
		customerDetails.put(ParameterConstants.CHILD, haveChild);
		if (Validator.isNotNull(haveChild)) {
			customerDetails.put(ParameterConstants.CHILD_DOB, ParamUtil.getString(actionRequest,
					ParameterConstants.CHILD_DOB.replace(StringPool.SPACE, StringPool.BLANK)));
		}

		customerDetails.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(actionRequest, "investmentObjective"));
		customerDetails.put(LEAD_STATUS, "isOpen");
		customerDetails.put(PRODUCT_ID, productId);

		// Code to get the Campaign Id, C_ID,V_ID
		String urlForMetaData = themeDisplay.getPortalURL() + URL_FOR_META_DATA;
		
		urlForMetaData = StringUtil.replace(urlForMetaData, "{referenceCode}", productId);

		log.debug("URL for meta --- " + urlForMetaData);

		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
				.getEdelweissLRBasicAuthConfiguration();
		JSONObject metaDataResponse = etipCoreApi.callGetAPI(new HashMap<>(), urlForMetaData, false,
				edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

		EdelweissProductQuoteUtil edelweissProductQuoteUtil = new EdelweissProductQuoteUtil(etipCoreApi);
		JSONObject generatedLeadData = edelweissProductQuoteUtil.generatingLeadId(customerDetails, productName,
				actionRequest, metaDataResponse);

		if (Validator.isNull(generatedLeadData) || Validator.isNull(generatedLeadData.getJSONObject("responseData"))) {
			log.error("Unable to Generate Lead data");
			SessionErrors.add(actionRequest, "internalError");
			actionRequest.setAttribute("errorKey", "Unable to generate Lead Id");
			actionResponse.sendRedirect(themeDisplay.getURLCurrent().split("\\?")[0]);
			return;
		}

		String generatedLeadId = generatedLeadData.getJSONObject("responseData").getString("LMSId");

		if (Validator.isNull(generatedLeadId)) {
			log.error("Lead Id not found");
			SessionErrors.add(actionRequest, "internalError");
			actionRequest.setAttribute("errorKey", "Lead Id not found");
			actionResponse.sendRedirect(themeDisplay.getURLCurrent().split("\\?")[0]);
			return;
		}

		customerDetails.put(EXTERNAL_REFERENCE_CODE, generatedLeadId);
		customerDetails.put(LEAD_ID, generatedLeadId);

		// code for product enquiry to save
		JSONObject jsonLeadUpdateResponse = edelweissTokioCommonApi.updateLead(actionRequest, customerDetails,
				productName, metaDataResponse);

		log.debug(jsonLeadUpdateResponse);

		String productEnquiriesURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL;

		JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(customerDetails);
		
		JSONObject lrProductResponse = etipCoreApi.callPostAPI(liferayJSON, productEnquiriesURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(),
				edelweissLRBasicAuthConfiguration.getLRPassword());

		if (Validator.isNotNull(maritalStatus) && Validator.isNotNull(lrProductResponse)) {

			JSONObject spouseDetailsJSON = JSONFactoryUtil.createJSONObject();

			String spouseGender = gender.equalsIgnoreCase("Male") ? "Female" : "Male";
			spouseDetailsJSON.put(ParameterConstants.GENDER, spouseGender);
			spouseDetailsJSON.put(ParameterConstants.RELATION, "Spouse");
			spouseDetailsJSON.put(ParameterConstants.FULL_NAME,
					ParamUtil.getString(actionRequest, ParameterConstants.SPOUSE_NAME));
			spouseDetailsJSON.put(ParameterConstants.DATE_OF_DEATH,
					ParamUtil.getString(actionRequest, ParameterConstants.SPOUSE_DOB, StringPool.BLANK)
							.replace(StringPool.SPACE, StringPool.BLANK));
			spouseDetailsJSON.put(ParameterConstants.SMOKE,
					ParamUtil.getString(actionRequest, ParameterConstants.SPOUSE_SMOKE));

			spouseDetailsJSON.put("r_relatedLead_c_customerEnquiryId", lrProductResponse.get("id"));
			spouseDetailsJSON.put(LEAD_ID, generatedLeadId);

			String customerRelationURL = themeDisplay.getPortalURL() + LIFERAY_CUSTOMER_RELATION_URL;

			JSONObject lfrSpouseDetailsPostResponse = etipCoreApi.callPostAPI(spouseDetailsJSON, customerRelationURL,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			log.debug("EdelweissProductQuoteActionCommand >>> doProcessAction >>> lr product response  ::: "
					+ lfrSpouseDetailsPostResponse);
		}

		HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
		Cookie cookie = new Cookie("leadId", generatedLeadId);
		cookie.setPath("/");
		response.addCookie(cookie);

		String redirectURL = themeDisplay.getURLCurrent();
		redirectURL = redirectURL.split("/buy")[0];
		actionResponse.sendRedirect(redirectURL + REDIRECT_URL);
	}

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
