package in.edelweiss.product.buy.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ASSURED_DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.GET_STARTED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE_IPS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.REQUEST_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRODUCT_PAGE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EXTERNAL_REFERENCE_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INDIAN;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE_CATEGORY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_JOURNEY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.O_PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.O_QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CATEGORY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RISK_APPETITE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.STATUS;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.enquiry.web.constants.EdelweissProductEnquiryWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissGenerateLeadConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.InterestSessions;
import in.edelweiss.tokio.common.model.LMSRequest;
import in.edelweiss.tokio.common.model.Lmslead;
import in.edelweiss.tokio.common.model.Quote;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductEnquiryWebPortletKeys.EDELWEISSPRODUCTENQUIRYWEB,
		"javax.portlet.name=" + EdelweissProductEnquiryWebPortletKeys.EDELWEISSFUNDSENQUIRYWEBPORTLET,
		"javax.portlet.name=" + AssetPublisherPortletKeys.ASSET_PUBLISHER,
		"mvc.command.name=/add_lead" }, service = MVCResourceCommand.class)
public class EdelweissLMSResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		log.debug("EdelweissLMSActionCommand >>>  doServeResource >>> start ");
		
		JSONObject validator = edelweissTokioCommonApi.validate(resourceRequest);
		
		HttpServletResponse httpServletResponse = portal.getHttpServletResponse(resourceResponse);
		
		if(Validator.isNotNull(validator.get("message"))) {
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			validator.put(STATUS, HttpServletResponse.SC_BAD_REQUEST);
			ServletResponseUtil.write(httpServletResponse, validator.toString());
			return;
		}

		JSONObject responseJSON = JSONFactoryUtil.createJSONObject();

		JSONObject response = JSONFactoryUtil.createJSONObject();

		try {
			EdelweissGenerateLeadConfiguration edelweissGenerateLeadConfiguration = EdelweissConfigurationUtil
					.getEdelweissGenerateLeadConfiguration();
			String authorization = edelweissTokioCommonApi.getOAuthToken(edelweissGenerateLeadConfiguration.getOAuth2QJURL(),
					edelweissGenerateLeadConfiguration.getOAuth2QJUsername(),
					edelweissGenerateLeadConfiguration.getOAuth2QJPassword());

			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			String requestType = ParamUtil.getString(resourceRequest, REQUEST_TYPE);

			String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
					Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
			
			String cId = GetterUtil.getString(ParamUtil.getString(resourceRequest, "cId"), "2");
			String vId = GetterUtil.getString(ParamUtil.getString(resourceRequest, "vId"), "4");
			String cPId = GetterUtil.getString(ParamUtil.getString(resourceRequest, "cPId"), "13");
			productName = GetterUtil.getString(ParamUtil.getString(resourceRequest, O_PRODUCT_NAME), productName);

			Lmslead lmsLead = new Lmslead();

			lmsLead.setcId(cId);
			lmsLead.setCpId(cPId);
			lmsLead.setvId(vId);
			lmsLead.setDob(ParamUtil.getString(resourceRequest, DATE_OF_BIRTH));
			lmsLead.setGender(ParamUtil.getString(resourceRequest, GENDER));
			lmsLead.setLeadFormType("Reccomendation");
			lmsLead.setName(ParamUtil.getString(resourceRequest, FULL_NAME));
			lmsLead.setNationality(INDIAN);
			lmsLead.setPhone(ParamUtil.getString(resourceRequest, MOBILE_NUMBER));
			lmsLead.setEmail(ParamUtil.getString(resourceRequest, EMAIL));
			lmsLead.setProduct(productName);
			lmsLead.setSource("EdelweissWebsales");
			lmsLead.setUserBrowser(resourceRequest.getUserAgent());
			lmsLead.setUtmSource("EdelweissWebsales");
			
			Map<String, String> formValues = new HashMap<>();
			formValues = edelweissTokioCommonApi.addUTMCampaignParams(resourceRequest, formValues);
			
			//Set UTM
			lmsLead.setUtmSource(GetterUtil.getString(formValues.get("Utm_source"), StringPool.BLANK));
			lmsLead.setUtmMedium(GetterUtil.getString(formValues.get("Utm_medium"), StringPool.BLANK));
			lmsLead.setUtmCampaign(GetterUtil.getString(formValues.get("Utm_campaign"), StringPool.BLANK));
			lmsLead.setUtmTerm(GetterUtil.getString(formValues.get("Utm_term"), StringPool.BLANK));
			lmsLead.setUtmContent(GetterUtil.getString(formValues.get("Utm_content"), StringPool.BLANK));
			lmsLead.setuTMPlacement(GetterUtil.getString(formValues.get("Utm_Placement"), StringPool.BLANK));
			lmsLead.setUtmCreative(GetterUtil.getString(formValues.get("Utm_creative"), StringPool.BLANK));
			lmsLead.setAdgroupId(GetterUtil.getString(formValues.get("adgroup_id"), StringPool.BLANK));
			lmsLead.setDevice(GetterUtil.getString(formValues.get("device"), StringPool.BLANK));
			lmsLead.setGclid(GetterUtil.getString(formValues.get("Gclid"), StringPool.BLANK));

			String siteSection = themeDisplay.getURLCurrent().substring(0, themeDisplay.getURLCurrent().indexOf("?"));
			lmsLead.setWebUrl(siteSection);
			
			if(siteSection.contains("/b/")) {
				siteSection = "Blogs Page";
			}else {
				siteSection = siteSection.substring(siteSection.lastIndexOf("/") + 1);
				siteSection = siteSection.trim().concat(" Page");
				siteSection = StringUtil.getTitleCase(siteSection, false, "");
			}
			
			lmsLead.setSiteSection(siteSection);

			String investmentObjective = ParamUtil.getString(resourceRequest, INVESTMENT_OBJECTIVE);
			
			Map<String, String> yourIncomeMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC, themeDisplay.getCompanyId());
			
			if (Validator.isNotNull(requestType) && requestType.equals("ISP")) {

				lmsLead.setRiskAppetiteSuitability(ParamUtil.getString(resourceRequest, RISK_APPETITE));

				Map<String, String> investmentObjectiveMap = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());


				InterestSessions interestSessions = new InterestSessions();
				interestSessions.setLeadFormProductCategory(ParamUtil.getString(resourceRequest, PRODUCT_CATEGORY,"Ulips"));
				interestSessions.setRecommendedObjective(investmentObjectiveMap.get(investmentObjective));
				interestSessions.setRecommendedObjectiveCategory(
						ParamUtil.getString(resourceRequest, INVESTMENT_OBJECTIVE_CATEGORY,"Investment"));
				interestSessions.setRecommendedAnnualIncome(yourIncomeMap.get(ParamUtil.getString(resourceRequest, ANNUAL_INCOME)));
				interestSessions.setSiteSection(siteSection);
				interestSessions.setStage(GET_STARTED);
				lmsLead.setInterestSessions(interestSessions);

				Quote quote = new Quote();

				if (Validator.isNotNull(investmentObjective) && investmentObjective.equalsIgnoreCase("Retirement")) {
					quote.setPayoutOptions(ParamUtil.getString(resourceRequest, "payoutOptions"));
					lmsLead.setOccupation(ParamUtil.getString(resourceRequest, OCCUPATION));
				}

				quote.setStage(QUOTE_STAGE_IPS);
				lmsLead.setQuote(quote);

			}else {
				InterestSessions interestSessions = new InterestSessions();
				if(Validator.isNotNull(ParamUtil.getString(resourceRequest, ANNUAL_INCOME))) {
					interestSessions.setRecommendedAnnualIncome(yourIncomeMap.get(ParamUtil.getString(resourceRequest, ANNUAL_INCOME)));
				}
				
				lmsLead.setInterestSessions(interestSessions);
			}

			LMSRequest lmsRequest = new LMSRequest();
			lmsRequest.setLmslead(lmsLead);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString = mapper.writeValueAsString(lmsRequest);

			log.debug("EdelweissLMSActionCommand >>>  generateLead >>> request Body ::: " + jsonString);

			JSONObject requestJson = JSONFactoryUtil.createJSONObject(jsonString);

			responseJSON = etipCoreAPI.callPostAPI(requestJson,
					edelweissGenerateLeadConfiguration.getGenerateLeadQJURL(), true,
					edelweissGenerateLeadConfiguration.getGenerateLeadQJXAPIKEY(), authorization);

			log.debug("EdelweissLMSActionCommand >>>  generateLead >>> responseJSON ::: " + responseJSON);

			if (Validator.isNotNull(responseJSON) && responseJSON.getBoolean(STATUS)) {
				addCustomerData(resourceRequest, resourceResponse, responseJSON, themeDisplay);
				response.put(STATUS, 200);
				if(Validator.isNotNull(responseJSON.getJSONObject(RESPONSE_DATA))) {
				response.put(LEAD_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
				}
			} else {
				response.put(STATUS, 400);
			}

		} catch (Exception e) {

			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_NOT_FOUND));

			response.put(STATUS, 400);

			log.error(
					"EdelweissLMSActionCommand >>>  doProcessAction >>> An exception occurred while submitting the data :::  "
							+ e);
		}

		ServletResponseUtil.write(httpServletResponse, response.toString());

	}

	private void addCustomerData(ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			JSONObject responseJSON, ThemeDisplay themeDisplay) throws Exception {

		try {

			JSONObject formValues = JSONFactoryUtil.createJSONObject();

			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil
					.getEdelweissLRBasicAuthConfiguration();

			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();

			String investmentObjective = ParamUtil.getString(resourceRequest, INVESTMENT_OBJECTIVE);

			String leadId = responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_ID);

			formValues.put(DATE_OF_BIRTH, ParamUtil.getString(resourceRequest, DATE_OF_BIRTH));
			formValues.put(GENDER, ParamUtil.getString(resourceRequest, GENDER));
			formValues.put(FULL_NAME, ParamUtil.getString(resourceRequest, FULL_NAME));
			formValues.put(MOBILE_NUMBER, ParamUtil.getString(resourceRequest, MOBILE_NUMBER));
			formValues.put(EMAIL, ParamUtil.getString(resourceRequest, EMAIL));
			formValues.put(INVESTMENT_OBJECTIVE, investmentObjective);
			if (Validator.isNotNull(investmentObjective) && investmentObjective.equalsIgnoreCase("retirement")) {
				formValues.put(OCCUPATION, ParamUtil.getString(resourceRequest, OCCUPATION));
			}
			formValues.put(ANNUAL_INCOME, ParamUtil.getString(resourceRequest, ANNUAL_INCOME));

			String risk = ParamUtil.getString(resourceRequest, RISK_APPETITE);
			if (Validator.isNotNull(risk) && !risk.isEmpty()) {
				formValues.put(RISK_APPETITE, risk.toLowerCase());
			}

			formValues.put(LEAD_ID, leadId);
			formValues.put(LEAD_STATUS, responseJSON.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
			formValues.put(LMS_INTEREST_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
			formValues.put(LMS_QUOTE_ID, responseJSON.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));
			formValues.put(EXTERNAL_REFERENCE_CODE, leadId);
			formValues.put(PRODUCT_ID, GetterUtil.getString(ParamUtil.getString(resourceRequest, PRODUCT_ID), "0"));
			formValues.put(O_PRODUCT_NAME, GetterUtil.getString(ParamUtil.getString(resourceRequest, O_PRODUCT_NAME), StringPool.BLANK));
			formValues.put(O_QUOTE_STAGE, GetterUtil.getString(ParamUtil.getString(resourceRequest, O_QUOTE_STAGE), SITE_SECTION_PRODUCT_PAGE));

			String externalRefCodeUrl = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL;

			log.debug("EdelweissLMSActionCommand >>>  doProcessAction >>> liferayJSON ::: " + formValues);

			JSONObject lifreayResponse = etipCoreAPI.callPostAPI(formValues, externalRefCodeUrl, false, liferayUserName,
					liferayPassword);
			
			log.debug("lifreayResponse >>>  doProcessAction >>> lifreayResponse ::: " + lifreayResponse);
			
			if(Validator.isNotNull(investmentObjective) && investmentObjective.equalsIgnoreCase("childEducation")) {
				CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
				
				customerFamilyDetails.setAssuranceDob(ParamUtil.getString(resourceRequest, ASSURED_DATE_OF_BIRTH));
				customerFamilyDetails.setLeadId(leadId);
				customerFamilyDetails.setrCustomerFamilyDetailsRelCCustomerEnquiryId(lifreayResponse.getLong("id"));
				
				edelweissTokioCommonApi.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails, null);
				
				CustomerInvestmentDetails updateCustomerInvestmentDetails = new CustomerInvestmentDetails();
				updateCustomerInvestmentDetails.setInvestingFor(EdelweissObjectConstants.FAMILY);
				updateCustomerInvestmentDetails.setLeadId(leadId);
				updateCustomerInvestmentDetails.setrCustomerInvestmentDetailsRelCCustomerEnquiryId(String.valueOf(lifreayResponse.getLong("id")));
				
				edelweissTokioCommonApi.updateInvestmentDetailsById(themeDisplay.getPortalURL(), updateCustomerInvestmentDetails, null);
			}
			
			
			log.debug(
					"EdelweissLMSActionCommand >>>  doProcessAction >>> LR Invocation Response ::: " + lifreayResponse);

			edelweissTokioCommonApi.createCookie(LEAD_ID, leadId, PortalUtil.getHttpServletResponse(resourceResponse));

		} catch (ETIPSystemException e) {
			log.error("Unable to insert record into liferay DB ::: " + e);
		}

	}

	@Reference
	private Portal portal;

	@Reference
	private ETIPCoreAPI etipCoreAPI;

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	private static final Log log = LogFactoryUtil.getLog(EdelweissLMSResourceCommand.class);
}
