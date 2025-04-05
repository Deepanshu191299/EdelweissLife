package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.ACTIVE_INCOME_JSP;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.CAPITAL_SECURE_PLUS_JSP;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.URL_FOR_SAVING_INVESTMENT_DETAILS;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.WELATHPLUS_CUSTOMIZE_FORM_JSP;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.WELATH_GAIN_PLUS_CUSTOMIZE_FORM_JSP;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ACTIVE_INCOME_PAY_FOR;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.AIP_FAMILY_INCOME_BENEFIT;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.AIP_INCOME_OPTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.AIP_MATURITY_AGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.GCAP_PAYOUT_OPTION_PICKLIST_EXTREF_CODE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_CAPITAL_SECURE_PLUS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_GCAP;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_GUARANTEED_SAVINGS_STAR;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_WEALTH_PLUS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_WEALTH_RISE_PLUS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_ZINDAGI_PROTECT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ITEMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFE_COVER_CONTINUATION_BENEFIT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PAYOR_WAIVER_BENEFIT_OPTIONS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PAYOUT_OPTION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.POLICY_FOR;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.product.customize.web.helper.EdelweissProductCustomizeHelper;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomizeFeaturesCard;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.AIPFamilyIncomeBenefit;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.FamilyIncomeBenefit;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.FundManagement;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.FundValueToBeWithdrawn;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.GISIncomeBenefitPayoutType;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.GISIncomeDuration;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.GISIncomeStartPoint;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.GISLumpSumBenefit;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.GuaranteedIncome;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.IncomeOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.IncomeStartYear;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.InvestingFor;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.LifeCoverContinuationBenefit;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.MaturityAge;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PaymentOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PayoutOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PlanOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PolicyOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PolicyYearFromWhichSWPPayable;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductDefaultValueRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductInvestingForRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductInvestmentAmountRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductPolicyForRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductPolicyOptionRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.SWP;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.SWPFrequency;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	immediate = true,
	property = {
			"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
			"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class EdelweissProductCustomizeRenderCommand implements MVCRenderCommand {
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissProductCustomizeRenderCommand.class);

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		log.debug("***** Rendering Product Customize screen ********");
		String customizeFormJSP = "/view.jsp";

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		String productId = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_ID);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_CODE);
		String productName = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_NAME);
		log.debug("Product configured for the Page is : " +  productName + " , Product Code : " + productCode  + " , Product ID : " + productId);
		String webEngageCustomizePageURL = ConfigurationUtil.getEdelweissWebengageURLConfiguration().getCustomizePageURL();
		Map<String, String> productMetaData = new HashMap<>();
		productMetaData.put("productId", productId);
		productMetaData.put(EdelweissCommonConstants.PRODUCT_CODE_PARAM, productCode);
		productMetaData.put("productName", productName);
		
		renderRequest.setAttribute("webEngageURL", webEngageCustomizePageURL);
		renderRequest.setAttribute("productMetaData", productMetaData);
		
		EdelweissProductCustomizeHelper.hideSuccessAndErrorMessage(renderRequest);
		
		if(Validator.isNotNull(productId)) {
			
			renderRequest.setAttribute("isProductSelected", true);
			String portalURL = themeDisplay.getPortalURL();
			
			JSONObject productRelObjectResponse = edelweissTokioCommonApi.getProductNestedFieldDataByProductId(portalURL + EdelweissProductCustomizeConstants.PRODUCT_REL_SERVICE_URL, productId);
			log.debug(productRelObjectResponse);
			Map<String, Object> productCustomizeSummaryData = EdelweissProductCustomizeHelper.getProductCustomizeSummaryData(productRelObjectResponse);
			
			log.debug("productCustomizeSummaryData..."+productCustomizeSummaryData);
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(renderRequest, LEAD_ID);
			
			JSONObject investmentDetailsValues = renderInvestmentDetails(renderRequest,leadId);
				
			if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
				ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
				if(Validator.isNotNull(investmentDetailsValues) && Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS)) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
					JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
					renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
					renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
					if(Validator.isNotNull(investmentDetailsJSON.getString("rate"))) {
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					} else {
						InvestingFor investingForObject = new InvestingFor();
						investingForObject.setName(investmentDetailsJSON.getString("investingFor"));
						investmentValues.setInvestingFor(investingForObject);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, investmentValues);
					}
				}
			} else {
				log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> Product Default Value is not configured");
			}
			
			log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> productCustomizeSummaryData values >>>>> "+productCustomizeSummaryData);
			renderRequest.setAttribute("productCustomizeSummaryData", productCustomizeSummaryData);

			if(productCustomizeSummaryData.containsKey(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)) {
				List<ProductInvestmentAmountRel> productInvestmentAmountRels = (List<ProductInvestmentAmountRel>) productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS);
				renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS_DATA, EdelweissProductCustomizeHelper.getJsonStringFromObject(productInvestmentAmountRels));
			}
			
			String articleURLTitle = StringPool.BLANK;
			if(productName.equalsIgnoreCase(PRODUCT_NAME_WEALTH_RISE_PLUS)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.WEALTH_RISE_PLUS_CUSTOMIZE_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.WEALTH_RISE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE;
				renderPTPPTForWealthRisePlus(renderRequest, themeDisplay);
				//LDXP-51 -> Adding SWP Fund WithdrawalMaster data to fix the BI PDF issue
				setSWPFundWithdrawalMasterData(renderRequest, companyId);
			} else if(productName.equalsIgnoreCase(PRODUCT_NAME_WEALTH_PLUS)) {
				articleURLTitle = EdelweissCommonConstants.WEALTH_PLUS_CUSTOMIZE_CONTENT_URL_TITLE;
				Map<String, String>payForDataMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.WEALTH_PLUS_PAY_FOR, themeDisplay.getCompanyId());
				Map<String, String>policyTermDataMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.WEALTH_PLUS_POLICY_TERM, themeDisplay.getCompanyId());
				ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				try {
					String payForJSONString = objectMapper.writeValueAsString(payForDataMap);
					String policyTermJSONString = objectMapper.writeValueAsString(policyTermDataMap);
					renderRequest.setAttribute("payForDataMap", JSONFactoryUtil.createJSONObject(payForJSONString));
					renderRequest.setAttribute("policyTermDataMap", JSONFactoryUtil.createJSONObject(policyTermJSONString));
				} catch (JsonProcessingException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while processing JSON ::: " + e);
				} catch (JSONException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while converting String to JSON ::: " + e);
				}
			} else if (productName.equalsIgnoreCase(EdelweissCommonConstants.PRODUCT_NAME_WEALTH_SECURE_PLUS)) {
				articleURLTitle = EdelweissCommonConstants.WEALTH_SECURE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE;
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.WEALTH_SECURE_PLUS_CUSTOMIZE_FORM_JSP;
				
				if(productCustomizeSummaryData.containsKey("policyOptionsData")) {
					List<ProductPolicyOptionRel> productPolicyOptionRels = (List<ProductPolicyOptionRel>) productCustomizeSummaryData.get("policyOptionsData");
					renderRequest.setAttribute("productPolicyOptionRelData", EdelweissProductCustomizeHelper.getJsonStringFromObject(productPolicyOptionRels));
				}
				
				if(productCustomizeSummaryData.containsKey("investingForData")) {
					List<ProductInvestingForRel> productInvestingForRels = (List<ProductInvestingForRel>) productCustomizeSummaryData.get("investingForData");
					renderRequest.setAttribute("productInvestingForRelData", EdelweissProductCustomizeHelper.getJsonStringFromObject(productInvestingForRels));
				}
				
				Map<String, String> wspPolicyTermPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.WSP_POLICY_TERM_PICKLIST_EXTREF_CODE, companyId);
				renderRequest.setAttribute("wspPolicyTermPicklistData", EdelweissProductCustomizeHelper.getJsonStringFromObject(wspPolicyTermPicklistData));
				
				Map<String, String> wspPayForPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.WSP_PAY_FOR_PICKLIST_EXTREF_CODE, companyId);
				renderRequest.setAttribute("wspPayForPicklistData", EdelweissProductCustomizeHelper.getJsonStringFromObject(wspPayForPicklistData));
				
				Map<String, String> fundWithdrawalPercentPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FUND_WITHDRAWAL_PERCENTAGE_MASTER_PICKLIST_EXTREF_CODE, companyId);
				renderRequest.setAttribute("fundWithdrawalPercentPicklistData", fundWithdrawalPercentPicklistData);
				
				Map<String, String> swpWithdrawalYearPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SWP_WITHDRAWAL_YEAR_MASTER_PICKLIST_EXTREF_CODE, companyId);
				renderRequest.setAttribute("swpWithdrawalYearPicklistData", swpWithdrawalYearPicklistData);
				
				setSWPFundWithdrawalMasterData(renderRequest, companyId);
			}else if(productName.equalsIgnoreCase(PRODUCT_NAME_CAPITAL_SECURE_PLUS)) {
				articleURLTitle = EdelweissCommonConstants.CAPITAL_SECURE_PLUS_CUSTOMIZE_CONTENT_URL_TITLE;
				customizeFormJSP = CAPITAL_SECURE_PLUS_JSP;
				Map<String, String>payForDataMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.CAPITAL_SECURE_PLUS_PAY_FOR, themeDisplay.getCompanyId());
				Map<String, String>policyTermDataMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.CAPITAL_SECURE_PLUS_POLICY_TERM, themeDisplay.getCompanyId());
				ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				try {
					String payForJSONString = objectMapper.writeValueAsString(payForDataMap);
					String policyTermJSONString = objectMapper.writeValueAsString(policyTermDataMap);
					renderRequest.setAttribute("capitalSecurePayForDataMap", JSONFactoryUtil.createJSONObject(payForJSONString));
					renderRequest.setAttribute("capitalSecurePolicyTermDataMap", JSONFactoryUtil.createJSONObject(policyTermJSONString));
				}catch (JsonProcessingException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while processing JSON Data ::: " + e);
				} catch (JSONException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while converting String to JSON Data ::: " + e);
				}
			}else if(productName.equalsIgnoreCase("Premier Guaranteed Income")) {
				
				articleURLTitle = EdelweissCommonConstants.PREMIER_GUARANTEED_INCOME_CUSTOMIZE_CONTENT_URL_TITLE ;
				renderPolicyOptionPolicyTermPayForGetIncomeFor(renderRequest, themeDisplay);
				renderRiderDetails(productId, renderRequest);
				
				Map<String, String> familyIncomBenefitrPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.PGI_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> lumpSumBenefitPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(
						EdelweissProductCustomizeConstants.LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> isEdelweissEmployeePicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IS_EDELWEISS_EMPLOYEE_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> incomePayoutFrequencyPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> maturityPayoutOptionPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
									.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, companyId);
				Map<String, String>payForDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGI_PAY_FOR_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String>policyTermDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGI_POLICY_TERM_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String>getIncomeForDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGI_GET_INCOME_FOR_PICKLIST_EXTREF_CODE, companyId);
				
				renderRequest.setAttribute("familyIncomBenefitrPicklistData", familyIncomBenefitrPicklistData);
				renderRequest.setAttribute("lumpSumBenefitPicklistData", lumpSumBenefitPicklistData);
				renderRequest.setAttribute("isEdelweissEmployeePicklistData", isEdelweissEmployeePicklistData);
				renderRequest.setAttribute("incomePayoutFrequencyPicklistData", incomePayoutFrequencyPicklistData);
				renderRequest.setAttribute("maturityPayoutOptionPicklistData", maturityPayoutOptionPicklistData);
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
				renderRequest.setAttribute("payForDataMap", payForDataMap);
				renderRequest.setAttribute("policyTermDataMap", policyTermDataMap);
				renderRequest.setAttribute("getIncomeForDataMap", getIncomeForDataMap);

				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.PREMIER_GUARANTEED_INCOME_FORM_JSP;
				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
					
					if(Validator.isNotNull(investmentDetailsValues) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					}
				}
}else if(productName.equalsIgnoreCase("Premier Guaranteed Star")) {
				
				articleURLTitle = EdelweissCommonConstants.PREMIUM_GUARANTEED_STAR_CUSTOMIZE_CONTENT_URL_TITLE ;
				renderPolicyOptionPolicyTermPayForGetStarFor(renderRequest, themeDisplay);
				renderRiderDetails(productId, renderRequest);
				
				Map<String, String> familyIncomBenefitrPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.PGS_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> lumpSumBenefitPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(
						EdelweissProductCustomizeConstants.PGS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				log.debug("lumpSumBenefitPicklistData..."+lumpSumBenefitPicklistData);
				Map<String, String> isEdelweissEmployeePicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IS_EDELWEISS_EMPLOYEE_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> incomePayoutFrequencyPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE, companyId);
				log.debug("incomePayoutFrequencyPicklistData..."+incomePayoutFrequencyPicklistData);
				Map<String, String> maturityPayoutOptionPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
									.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, companyId);
				Map<String, String>payForDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGS_PAY_FOR_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String>policyTermDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGS_POLICY_TERM_PICKLIST_EXTREF_CODE, companyId);
				log.debug("policyTermDataMap..."+policyTermDataMap);
				Map<String, String>getIncomeForDataMap = edelweissTokioCommonApi.
						getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.PGI_GET_INCOME_FOR_PICKLIST_EXTREF_CODE, companyId);
				
				renderRequest.setAttribute("familyIncomBenefitrPicklistData", familyIncomBenefitrPicklistData);
				renderRequest.setAttribute("lumpSumBenefitPicklistData", lumpSumBenefitPicklistData);
				renderRequest.setAttribute("isEdelweissEmployeePicklistData", isEdelweissEmployeePicklistData);
				renderRequest.setAttribute("incomePayoutFrequencyPicklistData", incomePayoutFrequencyPicklistData);
				renderRequest.setAttribute("maturityPayoutOptionPicklistData", maturityPayoutOptionPicklistData);
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
				renderRequest.setAttribute("payForDataMap", payForDataMap);
				renderRequest.setAttribute("policyTermDataMap", policyTermDataMap);
				renderRequest.setAttribute("getIncomeForDataMap", getIncomeForDataMap);
				

				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.PREMIER_GUARANTEED_STAR_FORM_JSP;
				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
					
					if(Validator.isNotNull(investmentDetailsValues) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					}
				}
				log.debug("productCustomizeSummaryData..."+productCustomizeSummaryData);
			}
			else if(productName.equalsIgnoreCase("Guaranteed Income STAR")) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.GUARANTEED_INCOME_STAR_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.GUARANTEED_INCOME_STAR_CUSTOMIZE_CONTENT_URL_TITLE;
				
				renderPayForIncomeStartOption(renderRequest, themeDisplay);
				renderRiderDetails(productId, renderRequest);
				
				Map<String, String> gisPayForPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.GIS_PAY_FOR_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> gisIncomeStartPointPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.GIS_INCOME_START_POINT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> gisIncomeDurationPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.GIS_INCOME_DURATION_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> gisFamilyIncomBenefitPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.GIS_FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> gisLumpSumBenefitPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(
						EdelweissProductCustomizeConstants.GIS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> incomePayoutFrequencyPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> gisIncomeBenefitPayoutTypePicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.GIS_INCOME_BENEFIT_PAYOUT_TYPE_PICKLIST_EXTREF_CODE, companyId);
				
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
									.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());
				
				renderRequest.setAttribute("gisPayForPicklistData", gisPayForPicklistData);
				renderRequest.setAttribute("gisIncomeStartPointPicklistData", gisIncomeStartPointPicklistData);
				renderRequest.setAttribute("gisIncomeDurationPicklistData", gisIncomeDurationPicklistData);
				renderRequest.setAttribute("gisFamilyIncomBenefitPicklistData", gisFamilyIncomBenefitPicklistData);
				renderRequest.setAttribute("gisLumpSumBenefitPicklistData", gisLumpSumBenefitPicklistData);
				renderRequest.setAttribute("incomePayoutFrequencyPicklistData", incomePayoutFrequencyPicklistData);
				renderRequest.setAttribute("gisIncomeBenefitPayoutTypePicklistData", gisIncomeBenefitPayoutTypePicklistData);
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
				
				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
					
					if(Validator.isNotNull(investmentDetailsValues) &&Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&  investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					
						log.info("GIS productDefaultValues ::: "+productDefaultValues);
					}
				}
				
			}
			else if(productName.equalsIgnoreCase("Income Builder")) {
				customizeFormJSP =EdelweissProductCustomizeWebPortletKeys.INCOME_BUILDER_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.INCOM_BUILDER_CUSTOMIZE_CONTENT_URL_TITLE;
				
				renderIBRelatedFieldsData(renderRequest, themeDisplay);
				renderRiderDetails(productId, renderRequest);
				
				Map<String, String> ibPayForPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IB_PAY_FOR_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> ibPolicyTermPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IB_POLICY_TERM_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> ibPayoutOptionPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IB_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> ibPayoutPeriodPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.IB_PAYOUT_PERIOD_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> incomePayoutFrequencyPicklistData = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(
								EdelweissProductCustomizeConstants.INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());
				
				renderRequest.setAttribute("ibPayForPicklistData", ibPayForPicklistData);
				renderRequest.setAttribute("ibPolicyTermPicklistData", ibPolicyTermPicklistData);
				renderRequest.setAttribute("ibPayoutOptionPicklistData", ibPayoutOptionPicklistData);
				renderRequest.setAttribute("ibPayoutPeriodPicklistData", ibPayoutPeriodPicklistData);
				renderRequest.setAttribute("incomePayoutFrequencyPicklistData", incomePayoutFrequencyPicklistData);
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
				
				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
					
					if(Validator.isNotNull(investmentDetailsValues) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					}
				}
				
			}
			else if(productName.equalsIgnoreCase("Active Income")) {
				
				customizeFormJSP = ACTIVE_INCOME_JSP;
				articleURLTitle = EdelweissCommonConstants.ACTIVE_INCOM_CUSTOMIZE_CONTENT_URL_TITLE;
				renderRiderDetails(productId, renderRequest);
				
				Map<String, String> payForPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(ACTIVE_INCOME_PAY_FOR, themeDisplay.getCompanyId());
				Map<String, String> incomeOptionPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(AIP_INCOME_OPTION, themeDisplay.getCompanyId());
				Map<String, String> aipFamilyIncomeBenefitPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(AIP_FAMILY_INCOME_BENEFIT, themeDisplay.getCompanyId());
				Map<String, String> aipMaturityAgePicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(AIP_MATURITY_AGE, themeDisplay.getCompanyId());
				Map<String, String> aipMaturityPayoutPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, themeDisplay.getCompanyId());
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());
				
				ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				try {
					String payForJSONString = objectMapper.writeValueAsString(payForPicklistData);
					renderRequest.setAttribute("aipPayForValues", JSONFactoryUtil.createJSONObject(payForJSONString));
				}catch (JsonProcessingException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while processing JSON ::: " + e);
				} catch (JSONException e) {
					log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred while converting String to JSON ::: " + e);
				}
				
				renderRequest.setAttribute("incomeOptionsData", incomeOptionPicklistData);
				renderRequest.setAttribute("aipFamilyIncomeBenefitData", aipFamilyIncomeBenefitPicklistData);
				renderRequest.setAttribute("aipMaturityAgeData", aipMaturityAgePicklistData);
				renderRequest.setAttribute("aipMaturityPayoutData", aipMaturityPayoutPicklistData);
				renderRequest.setAttribute(EdelweissCommonConstants.PAYOR_WAIVER_BENEFIT_OPTIONS_DATA, payorWaiverBenefitOptions);
				
			}else if(productName.equalsIgnoreCase(PRODUCT_NAME_GCAP)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.GCAP_CUSTOMIZE_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.GCAP_CUSTOMIZE_CONTENT_URL_TITLE;
				renderGCAPData(productId ,productCode, productCustomizeSummaryData, investmentDetailsValues, renderRequest);
			}else if(productName.equalsIgnoreCase(PRODUCT_NAME_ZINDAGI_PROTECT)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.ZP_CUSTOMIZE_FORM_JSP;

				Map<String, String> payForPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.ZP_PAY_FOR_PICKLIST, companyId);
				Map<String, String> paymentFrequencyPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.PAYMENT_OPTIONS_MASTER_PICKLIST_EXTREF_CODE, companyId);
				Map<String, String> incomeTypePicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.ZP_INCOME_TYPE_PICKLIST, companyId);
				Map<String, String> occupationPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.OCCUPATION_MASTER, companyId);
				Map<String, String> educationPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_EDUCATIONAL_QUALIFICATION, companyId);
				Map<String, String> childBenefitSAPercentagePicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.CHILD_BENEFIT_SA_PERCENT_PICKLIST, companyId);

				renderRequest.setAttribute("payForPicklistData", payForPicklistData);
				renderRequest.setAttribute("paymentFrequencyPicklistData", paymentFrequencyPicklistData);
				renderRequest.setAttribute("incomeTypePicklistData", incomeTypePicklistData);
				renderRequest.setAttribute("occupationPicklistData", occupationPicklistData);
				renderRequest.setAttribute("educationPicklistData", educationPicklistData);
				renderRequest.setAttribute("childBenefitSAPercentagePicklistData", childBenefitSAPercentagePicklistData);

				renderRiderDetails(productId, renderRequest);

				JSONObject familyDetailsResponseJson = edelweissTokioCommonApi.getFamilyDetailsByLeadId(portalURL, leadId);
				renderRequest.setAttribute("familyDetailsResponseJson", familyDetailsResponseJson);

				CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
				Map<String, String> customerEnquiryMap = new HashMap<String, String>();

				try {
					customerEnquiryMap.put("annualIncome", customerEnquiry.getAnnualIncome());
					customerEnquiryMap.put("occupation", customerEnquiry.getOccupation());
					customerEnquiryMap.put("educationQualification", customerEnquiry.getEducationQualification());
				} catch(NullPointerException npe) {
					log.info(npe.getMessage());
				}	
				
				renderRequest.setAttribute("customerEnquiryMap", customerEnquiryMap);

				try {
					EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
					JSONObject productEligibilityRelJson = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+ EdelweissObjectConstants.PRODUCT_ELIGIBILITY_RELATIONS, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
					renderRequest.setAttribute("productEligibilityRelJson", productEligibilityRelJson);
				} catch (ETIPSystemException e) {
					log.error(e.getMessage());
					if(log.isDebugEnabled()) {
						e.printStackTrace();
					}
				}

				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);

					if(Validator.isNotNull(investmentDetailsValues) && Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
					}
				}

			}else if(productName.equalsIgnoreCase("Guaranteed Flexi STAR")) {
				log.info("Inside GFS ========== 40071");
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.GUARANTEED_FLEXI_STAR_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.GUARANTEED_FLEXI_STAR_CUSTOMIZE_CONTENT_URL_TITLE;
				Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
						.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, companyId);
				renderPayForIncomeStartOption(renderRequest, themeDisplay);
				//renderRiderDetails("421659", renderRequest);
				renderDirectRiderDetails(productId,renderRequest);
				renderProductJsonConfig(productCode, renderRequest);
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
				/*
				 * Update Default investment values with Customer Selected Values
				 */
				if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
					ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
					
					if(Validator.isNotNull(investmentDetailsValues) &&Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&  investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
						JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
						renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
						renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
						ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
						productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
						
						log.info("GFS productDefaultValues ::: "+productDefaultValues);
					}
				}
				
			}
			
			renderRequest.setAttribute("currentTimeStamp", new Date().getTime());
			Map<String, String> swpMasterPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissProductCustomizeConstants.SWP_MASTER_PICKLIST_EXTREF_CODE, companyId);
			renderRequest.setAttribute("swpMasterPicklistData", swpMasterPicklistData);
			edelweissTokioCommonApi.renderBasicDetails(renderRequest);
			
			/*
			 * Product Specific JSP page selection and related data rendering 
			 */
			if (productName.equalsIgnoreCase("Total Protect Plus")) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.TOTAL_PROTECT_PLUS_CUSTOMIZE_FORM_JSP;
				renderTotalProtectPlusData(productId ,productCode, renderRequest);
			}else if(productName.equalsIgnoreCase(PRODUCT_NAME_WEALTH_PLUS)) {
				customizeFormJSP = WELATHPLUS_CUSTOMIZE_FORM_JSP;
			}else if (productName.equalsIgnoreCase(PRODUCT_NAME_GUARANTEED_SAVINGS_STAR)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.GUARANTEED_SAVINGS_STAR_CUSTOMIZE_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.GUARANTEED_SAVINGS_STAR_CUSTOMIZE_CONTENT_URL_TITLE;
				renderGuaranteedSavingsStarData(productId ,productCode, productCustomizeSummaryData,investmentDetailsValues, renderRequest);
			}else if (productName.equalsIgnoreCase(EdelweissCommonConstants.PRODUCT_NAME_FLEXI_SAVINGS_PLAN)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.FLEXI_SAVINGS_PLAN_CUSTOMIZE_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.FLEXI_SAVINGS_PLAN_CUSTOMIZE_CONTENT_URL_TITLE;
				renderFlexiSavingsPlanData(productId ,productCode, productCustomizeSummaryData, investmentDetailsValues, renderRequest);
			}else if(productCode.equalsIgnoreCase(EdelweissCommonConstants.PRODUCT_CODE_WEALTH_GAIN_PLUS)) {
				customizeFormJSP = WELATH_GAIN_PLUS_CUSTOMIZE_FORM_JSP;
				articleURLTitle = EdelweissCommonConstants.WEALTH_GAIN_PLUS_CUSTOMIZE_CONTENT_URL_TITLE;
				renderWealthGainPluData(productId ,productCode, productCustomizeSummaryData, investmentDetailsValues, renderRequest);
			}else if(productCode.equalsIgnoreCase(EdelweissCommonConstants.PRODUCT_CODE_SARAL_JEEVAN_BIMA)) {
				customizeFormJSP = EdelweissProductCustomizeWebPortletKeys.SARAL_JEEVAN_BIMA_FORM_JSP;
				renderSaralJeevanBimaData(productId ,productCode, productCustomizeSummaryData, investmentDetailsValues, leadId, renderRequest);
			}
			
			if(Validator.isNotNull(articleURLTitle)) {
				CustomizeFeaturesCard customizeFeaturesCard = edelweissTokioCommonApi.getCustomizeFeatureCardContentByURLTitle(themeDisplay.getScopeGroupId(), articleURLTitle);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMIZE_FEATURES_CARD, customizeFeaturesCard);
			}

		} else {
			renderRequest.setAttribute("isProductSelected", false);
		}
		
		log.debug(customizeFormJSP);
		
		return customizeFormJSP;
	}

	/**
	 * Render Wealth Gain Plus Ptoduct customize page data
	 * 
	 * @param productCustomizeSummaryData
	 */
	private void renderWealthGainPluData(String productId, String productCode,
			Map<String, Object> productCustomizeSummaryData, JSONObject investmentDetailsValues,
			RenderRequest renderRequest) {
		
		renderRequest.setAttribute("productCode", productCode);

		/*
		 * Update Default investment values with Customer Selected Values
		 */
		if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
			ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
			
			if(Validator.isNotNull(investmentDetailsValues) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
				JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
				ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
			}
		} 

		log.debug(productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL));
		if (Validator
				.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS))) {
			renderRequest.setAttribute(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL,
					JSONFactoryUtil.createJSONArray((List<ProductInvestmentAmountRel>) productCustomizeSummaryData
							.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)));
		}

		if (Validator.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL))) {
			renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL, JSONFactoryUtil.createJSONArray(
					(List<ProductPolicyForRel>) productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL)));
		}
		
	}
	
	/**
	 * Render Saral Jeevan Bima Ptoduct customize page data
	 * 
	 * @param productCustomizeSummaryData
	 * @param leadId 
	 */
	private void renderSaralJeevanBimaData(String productId, String productCode,
			Map<String, Object> productCustomizeSummaryData, JSONObject investmentDetailsValues,
			String leadId, RenderRequest renderRequest) {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		
		/*
		 * Get Product Plans
		 */
		renderProductPlans(renderRequest, themeDisplay);
		
		Map<String, String> youroccupationPicklist = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_OCCUPATION, companyId);
		Map<String, String> youreducationalqualification = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SJB_EDUCATIONQULIFICATION, companyId);
		Map<String, String> yournatureofduty = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_NATURE_OF_DUTY, companyId);
		Map<String, String> yourspouse = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_SPOUSE ,companyId);
		Map<String, String> spouseoccupation = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SPOUSE_OCCUPATION, companyId);
		
		renderRequest.setAttribute("youroccupationPicklist", youroccupationPicklist);
		renderRequest.setAttribute("youreducationalqualification", youreducationalqualification);
		renderRequest.setAttribute("yournatureofduty", yournatureofduty);
		renderRequest.setAttribute("yourspouse", yourspouse);
		renderRequest.setAttribute("spouseoccupation", spouseoccupation);
		
		/*
		 * update the default value according to the customer choose
		 */
		if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
			ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
			
			if(Validator.isNotNull(investmentDetailsValues) &&Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&  investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
				JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
				ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
			}
		}
		
		/*
		 * Get Family Details
		 */
		JSONObject familyDetailsResponseJson = edelweissTokioCommonApi.getFamilyDetailsByLeadId(themeDisplay.getPortalURL(), leadId);
		renderRequest.setAttribute("familyDetailsJSON", familyDetailsResponseJson);
		
	}

	private void setSWPFundWithdrawalMasterData(RenderRequest renderRequest, long companyId) {
		Map<String, String> fundWithdrawalFrequencyPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FUND_WITHDRAWAL_FREQUENCY_MASTER_PICKLIST_EXTREF_CODE, companyId);
		renderRequest.setAttribute("fundWithdrawalFrequencyPicklistData", fundWithdrawalFrequencyPicklistData);
	}
	
	/**
	 * Render Policy Term and Pay For Relation Data
	 * 
	 * @param renderRequest
	 * @param themeDisplay
	 */
	private void renderPTPPTForWealthRisePlus(RenderRequest renderRequest, ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();

		Map<String, String> params = new HashMap<>();
		try {
			JSONObject wrpPolicyTermPolicyForResponse = etipCoreAPI.callGetAPI(params, themeDisplay.getPortalURL() + EdelweissProductCustomizeConstants.WRP_GET_PT_PPT_REL_SERVICE_URL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			JSONArray wrpPolicyTermPolicyForItems = wrpPolicyTermPolicyForResponse.getJSONArray(ITEMS);
			Map<String, List<String>> policyTermPolicyForRelData = new HashMap<>();
			List<String> policyFors = null;
			JSONObject policyTermJSONObject = null;
			JSONArray policyForJSONArray = null;
			List<String> policyTerms = new ArrayList<>();
			for (int i = 0; i < wrpPolicyTermPolicyForItems.length(); i++) {
				policyTermJSONObject = wrpPolicyTermPolicyForItems.getJSONObject(i).getJSONObject(EdelweissProductCustomizeConstants.POLICY_TERM);
				policyForJSONArray = wrpPolicyTermPolicyForItems.getJSONObject(i).getJSONArray(EdelweissProductCustomizeConstants.POLICY_FOR);
				policyFors = new ArrayList<>();
				for (int j = 0; j < policyForJSONArray.length(); j++) {
					policyFors.add(policyForJSONArray.getJSONObject(j).getString("name"));
				}
				policyTerms.add(policyTermJSONObject.getString("name"));
				policyTermPolicyForRelData.put(policyTermJSONObject.getString("name"), policyFors);
			}
			
			String policyTermPolicyFormRelDataStr = EdelweissProductCustomizeHelper.getJsonStringFromObject(policyTermPolicyForRelData);
			renderRequest.setAttribute("policyTermPolicyForRelData", policyTermPolicyFormRelDataStr);
		} catch (Exception e) {
			log.error("Error while loading Policy Term and Policy For : " + e);
		}
	}
	
	/**
	 * Details related to Premier guaranteed income fields relationship
	 * 
	 * @param productCustomizeSummaryData
	 */
	@SuppressWarnings("unchecked")
	private void renderRiderDetails(String productId, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject liferayProductNestedData = edelweissTokioCommonApi
				.getProductAndLinkedRidersData(themeDisplay.getPortalURL(), productId);
	
		log.debug(liferayProductNestedData);

		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_NESTED_DATA, liferayProductNestedData);

		if (Validator.isNull(liferayProductNestedData.getJSONArray("riderValidations"))) {
			return;
		}
		
		JSONObject riderValidations = JSONFactoryUtil.createJSONObject();
		liferayProductNestedData.getJSONArray(EdelweissCommonConstants.RIDER_VALIDATIONS).forEach(riderValidationObj ->{
			if(Validator.isNull(riderValidationObj)) {
				return;
			}
			JSONObject riderValidation = (JSONObject)riderValidationObj;
			if(riderValidation.getBoolean("isActive")) {
				riderValidations.put(riderValidation.getString("riderId"), riderValidation);
			}
		});
		renderRequest.setAttribute(EdelweissCommonConstants.RIDER_VALIDATIONS, riderValidations);
		
		log.debug("List of Rider validations based on the Selected Product");
		log.debug(renderRequest.getAttribute(EdelweissCommonConstants.RIDER_VALIDATIONS));
	}
	
	@SuppressWarnings("unchecked")
	private void renderDirectRiderDetails(String productId, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject liferayProductNestedData = edelweissTokioCommonApi
				.getProductAndLinkedDirectRidersData(themeDisplay.getPortalURL(), productId);
	
		log.debug(liferayProductNestedData);

		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_NESTED_DATA, liferayProductNestedData);

		if (liferayProductNestedData.getInt("totalCount") == 0) {
			return;
		}
		
		JSONObject riderValidations = JSONFactoryUtil.createJSONObject();
		liferayProductNestedData.getJSONArray("items").forEach(riderValidationObj ->{
			if(Validator.isNull(riderValidationObj)) {
				return;
			}
			JSONObject riderValidation = (JSONObject)riderValidationObj;
			if(riderValidation.getBoolean("isActive")) {
				String riderId = riderValidation.getString("riderId");
				String r_riderValidations_c_productRiderId = riderValidation.getString("r_riderValidations_c_productRiderId");
				JSONObject getProductRidersData = edelweissTokioCommonApi
						.getProductRidersData(themeDisplay.getPortalURL(), r_riderValidations_c_productRiderId);
				riderValidation.put("productRider", getProductRidersData);
				riderValidations.put(riderId, riderValidation);
			}
		});
		renderRequest.setAttribute(EdelweissCommonConstants.RIDER_VALIDATIONS, riderValidations);
	}
	
	private void renderPolicyOptionPolicyTermPayForGetStarFor(RenderRequest renderRequest,
			ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		Map<String, String> params = new HashMap<>();
		try {
			JSONObject pgiRelFieldValuesResponse = etipCoreAPI.callGetAPI(params,
					themeDisplay.getPortalURL()
							+ EdelweissProductCustomizeConstants.PGS_GET_PPT_PT_REL_SERVICE_URL,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

			log.debug("pgiRelFieldValuesResponse..."+pgiRelFieldValuesResponse);
			JSONArray wrpPolicyTermPolicyForItems = pgiRelFieldValuesResponse.getJSONArray(ITEMS);
			renderRequest.setAttribute("pgiRelFieldValues", wrpPolicyTermPolicyForItems);
		} catch (Exception e) {
			log.error("Error while loading Policy Term and Policy For : " + e);
		}
	}
	private void renderIBRelatedFieldsData(RenderRequest renderRequest,
			ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		Map<String, String> params = new HashMap<>();
		try {
			JSONObject ibRelatedFieldsDataResponse = etipCoreAPI.callGetAPI(params,
					themeDisplay.getPortalURL()
							+ EdelweissProductCustomizeConstants.IB_RELATIONAL_FIELDS_DATA_SERVICE_URL,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

			JSONArray ibRelatedFieldsData = ibRelatedFieldsDataResponse.getJSONArray(ITEMS);
			renderRequest.setAttribute("ibRelatedFieldsData", ibRelatedFieldsData);
	
		} catch (Exception e) {
			log.error("Error while loading IB Dependent fields : " + e);
		}
	}
	
	/**
	 * Details related to Premier guaranteed income fields relationship
	 * 
	 * @param productCustomizeSummaryData
	 */
	private void renderPolicyOptionPolicyTermPayForGetIncomeFor(RenderRequest renderRequest,
			ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		Map<String, String> params = new HashMap<>();
		try {
			JSONObject pgiRelFieldValuesResponse = etipCoreAPI.callGetAPI(params,
					themeDisplay.getPortalURL()
							+ EdelweissProductCustomizeConstants.PGI_GET_PO_PPT_GIF_PT_REL_SERVICE_URL,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

			JSONArray wrpPolicyTermPolicyForItems = pgiRelFieldValuesResponse.getJSONArray(ITEMS);
			renderRequest.setAttribute("pgiRelFieldValues", wrpPolicyTermPolicyForItems);

		} catch (Exception e) {
			log.error("Error while loading Policy Term and Policy For : " + e);
		}
	}
	
	private void renderPayForIncomeStartOption(RenderRequest renderRequest,
			ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		Map<String, String> params = new HashMap<>();
		try {
			JSONObject gisPayForISPFieldValuesResponse = etipCoreAPI.callGetAPI(params,
					themeDisplay.getPortalURL()
							+ EdelweissProductCustomizeConstants.GIS_GET_PAY_FOR_INCOME_START_OPTION_REL_SERVICE_URL,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

			JSONArray gisPayForISPRelItems = gisPayForISPFieldValuesResponse.getJSONArray(ITEMS);
			renderRequest.setAttribute("gisPayForISPRelItems", gisPayForISPRelItems);

		} catch (Exception e) {
			log.error("Error while loading GIS Pay for and ISP Items : " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void renderProductJsonConfig(String productId, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject productJsonConfiguration = edelweissTokioCommonApi
				.getProductJsonConfigurationByProductId(themeDisplay.getPortalURL(), productId);
		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_PRODUCT_JSON_CONF, productJsonConfiguration.getJSONArray(ITEMS).get(0));
	}
	
	// render method for getting the objects of product plans!!!!!!!!!!!!!!!!!!!
	private void renderProductPlans(RenderRequest renderRequest, ThemeDisplay themeDisplay) {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		Map<String, String> params = new HashMap<>();
		try {
			JSONObject sjbProductPlansResponse = etipCoreAPI.callGetAPI(params,
					themeDisplay.getPortalURL()
							+ EdelweissProductCustomizeConstants.SJB_GET_PRODUCT_PLANS,
					false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());

			JSONArray sjbProductPlansItems = sjbProductPlansResponse.getJSONArray(ITEMS);
			renderRequest.setAttribute("sjbProductPlansItems", sjbProductPlansItems);
		} catch (Exception e) {
			log.error("Error while loading SJB Product Plans Items : " + e);
		}
	}
	
	
	private JSONObject renderInvestmentDetails(RenderRequest renderRequest, String cookieLeadId) {
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		JSONObject investmentRespoonseObject= JSONFactoryUtil.createJSONObject();
		try {
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
			String parameterURL = EdelweissProductCustomizeHelper.getLeadMetaDataURL(cookieLeadId);
			
			String urlToGetInvestmentDetails = themeDisplay.getPortalURL()+ URL_FOR_SAVING_INVESTMENT_DETAILS+GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS+parameterURL;
			
			log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> url to get basic details >>>>> "+urlToGetInvestmentDetails);
			
			investmentRespoonseObject = etipCoreAPI.callGetAPI(new  HashMap<>(), urlToGetInvestmentDetails, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
			
			log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> investmentRespoonseObject >>>>> "+investmentRespoonseObject);
			
		} catch(ETIPSystemException e) {
			log.error("EdelweissProductCustomizeRenderCommand >>> render invoked >>> An error occurred getting the investment details ::: " + e);
		}
		return investmentRespoonseObject;
	}
	
	private ProductDefaultValueRel getInvestmentValueFromObject(JSONObject investmentDetailsObject, ProductDefaultValueRel productDefaultValueRel){
		
		log.debug(investmentDetailsObject);
		// Projected Returns Set
		productDefaultValueRel.setProjectedReturnsData(investmentDetailsObject.getString("projectedReturnsData"));

		//Set Value From Default Product Value if it is not updated in IPS
		if(Validator.isNotNull(investmentDetailsObject.getString("policyOption"))) {
			// PolicyOption start
			PolicyOption policyOption = new PolicyOption();
			policyOption.setKey(investmentDetailsObject.getString("policyOption"));
			productDefaultValueRel.setPolicyOption(policyOption);
			// PolicyOption End
		}
		//set get Income for
		productDefaultValueRel.setGetIncomeFor(investmentDetailsObject.getString("incomeFor"));
		
			if (Validator.isNotNull(investmentDetailsObject.getString("incomeOption"))) {
				IncomeOption incomeOption = new IncomeOption();
				incomeOption.setKey(investmentDetailsObject.getString("incomeOption"));
				productDefaultValueRel.setIncomeOption(incomeOption);
		}
		// Policy Term Set
		productDefaultValueRel.setPolicyTerm(investmentDetailsObject.getString("policyTerm"));
				
		// Investing Amount Set
		productDefaultValueRel.setInvestmentAmount(investmentDetailsObject.getString("investmentAmount"));

		// Setting Life Cover
		productDefaultValueRel.setLifeCover(investmentDetailsObject.getString("lifeCover"));
		
		// Set value of Pay For (Premium Paying Term) 
		productDefaultValueRel.setPolicyFor(investmentDetailsObject.getString("premiumPayingTerm"));
		
		if (Validator.isNotNull(investmentDetailsObject.getString("incomeDuration"))) {
				GISIncomeDuration gISIncomeDuration = new GISIncomeDuration();
				gISIncomeDuration.setKey(investmentDetailsObject.getString("incomeDuration"));
				productDefaultValueRel.setgISIncomeDuration(gISIncomeDuration);
		}
		
		if (Validator.isNotNull(investmentDetailsObject.getString("incomeStartPoint"))) {
					GISIncomeStartPoint gISIncomeStartPoint = new GISIncomeStartPoint();
					gISIncomeStartPoint.setKey(investmentDetailsObject.getString("incomeStartPoint"));
					productDefaultValueRel.setgISIncomeStartPoint(gISIncomeStartPoint);
		}
		
		if (Validator.isNotNull(investmentDetailsObject.getString("gISIncomeBenefitPayoutType"))) {
			GISIncomeBenefitPayoutType gISIncomeBenefitPayoutType = new GISIncomeBenefitPayoutType();
			gISIncomeBenefitPayoutType.setKey(investmentDetailsObject.getString("gISIncomeBenefitPayoutType"));
			productDefaultValueRel.setgISIncomeBenefitPayoutType(gISIncomeBenefitPayoutType);
		}
		
		if (Validator.isNotNull(investmentDetailsObject.getString("lumpSumBenefits"))) {
			GISLumpSumBenefit gISLumpSumBenefit = new GISLumpSumBenefit();
			gISLumpSumBenefit.setKey(investmentDetailsObject.getString("lumpSumBenefits"));
			productDefaultValueRel.setgISLumpSumBenefit(gISLumpSumBenefit);
		}
		
		// Set Systematic Withdrawl Plan (SWP) starts
		String systematicWithdrawalPlan = investmentDetailsObject.getString("systematicWithdrawalPlan");
		
		//Set Value From Default Product Value if it is not updated in IPS
		if(Validator.isNotNull(systematicWithdrawalPlan)) {
			SWP swp = new SWP();
			swp.setKey(investmentDetailsObject.getString("systematicWithdrawalPlan"));
			productDefaultValueRel.setSwp(swp);
		}
		// Set Systematic Withdrawl Plan (SWP) ends
		
		// Payment Option (Payment Frequency) Starts
		PaymentOption paymentOption = new PaymentOption();
		paymentOption.setKey(investmentDetailsObject.getString("paymentFrequency"));
		productDefaultValueRel.setPaymentOption(paymentOption);
		// Payment Option (Payment Frequency) ends
		
		// Ivesting For Start
		InvestingFor investingForObject = new InvestingFor();
		investingForObject.setName(investmentDetailsObject.getString("investingFor"));
		productDefaultValueRel.setInvestingFor(investingForObject);
		// Ivesting For End	
		
		// Set Fund Management (Investment Stratergy) Starts
		if(Validator.isNotNull(investmentDetailsObject.getString("investmentStrategy"))) {
			FundManagement fundManagement = new FundManagement();
			fundManagement.setKey(investmentDetailsObject.getString("investmentStrategy"));
			productDefaultValueRel.setFundManagement(fundManagement);
		}
		// Set Fund Management (Investment Stratergy) Ends		
		if(Validator.isNotNull(systematicWithdrawalPlan) && systematicWithdrawalPlan.equalsIgnoreCase(EdelweissCommonConstants.SWP_YES_VALUE)) {
			FundValueToBeWithdrawn fundValueToBeWithdrawn = new FundValueToBeWithdrawn();
			fundValueToBeWithdrawn.setKey(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PARAM_FUND_VALUE_TOBE_WITHDRAWN));
			productDefaultValueRel.setFundValueToBeWithdrawn(fundValueToBeWithdrawn);
			
			PolicyYearFromWhichSWPPayable policyYearFromWhichSWPPayable = new PolicyYearFromWhichSWPPayable();
			policyYearFromWhichSWPPayable.setKey(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PARAM_POLICY_YEAR_FROM_WHICH_SWP_PAYABLE));
			productDefaultValueRel.setPolicyYearFromWhichSWPPayable(policyYearFromWhichSWPPayable);
			
			SWPFrequency swpFrequency = new SWPFrequency();
			swpFrequency.setKey(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PARAM_SWP_FREQUENCY));
			productDefaultValueRel.setSwpFrequency(swpFrequency);
		}
		
		log.debug(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION));
		if (Validator.isNotNull(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION))) {
			PayoutOption payoutOption = new PayoutOption();
			payoutOption.setKey(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION));
			productDefaultValueRel.setPayoutOption(payoutOption);
		}
		
		log.debug(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION));
		if (Validator.isNotNull(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION))) {
			PayoutOption gcapPayoutOption = new PayoutOption();
			gcapPayoutOption.setKey(investmentDetailsObject.getString(ParameterConstants.PAYOUT_OPTION));
			productDefaultValueRel.setGcapPayoutOption(gcapPayoutOption);
		}

		log.debug(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.FAMILY_INCOME_BENEFITS));
		if (Validator.isNotNull(
				investmentDetailsObject.getString(EdelweissProductCustomizeConstants.FAMILY_INCOME_BENEFITS))) {
			FamilyIncomeBenefit familyIncomeBenefit = new FamilyIncomeBenefit();
			familyIncomeBenefit.setKey(
					investmentDetailsObject.getString(EdelweissProductCustomizeConstants.FAMILY_INCOME_BENEFITS));
			productDefaultValueRel.setFamilyIncomeBenefit(familyIncomeBenefit);
		}
		
		log.debug(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PLAN_OPTION));
		if (Validator.isNotNull(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PLAN_OPTION))) {
			PlanOption planOption = new PlanOption();
			planOption.setKey(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.PLAN_OPTION));
			productDefaultValueRel.setPlanOption(planOption);
		}

		log.debug(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.INCOME_START_YEAR));
		if (Validator
				.isNotNull(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.INCOME_START_YEAR))) {
			IncomeStartYear incomeStartYear = new IncomeStartYear();
			incomeStartYear
					.setKey(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.INCOME_START_YEAR));
			productDefaultValueRel.setIncomeStartYear(incomeStartYear);
		}

		log.debug(
				investmentDetailsObject.getString(EdelweissProductCustomizeConstants.LIFE_COVER_CONTINUATION_BENEFIT));
		if (Validator.isNotNull(investmentDetailsObject
				.getString(EdelweissProductCustomizeConstants.LIFE_COVER_CONTINUATION_BENEFIT))) {
			LifeCoverContinuationBenefit lifeCoverContinuationBenefit = new LifeCoverContinuationBenefit();
			lifeCoverContinuationBenefit.setKey(investmentDetailsObject
					.getString(EdelweissProductCustomizeConstants.LIFE_COVER_CONTINUATION_BENEFIT));
			productDefaultValueRel.setLifeCoverContinuationBenefit(lifeCoverContinuationBenefit);
		}
		
		log.debug(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.SELECTED_RIDERS_IDS));
		if (Validator
				.isNotNull(investmentDetailsObject.getString(EdelweissProductCustomizeConstants.SELECTED_RIDERS_IDS))) {
			productDefaultValueRel.setSelectedRidersIds(
					investmentDetailsObject.getString(EdelweissProductCustomizeConstants.SELECTED_RIDERS_IDS));
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("guaranteedIncomeType"))) {
			log.debug("GIT Invoked::::");
			GuaranteedIncome guaranteedIncome = new GuaranteedIncome();
			guaranteedIncome.setKey(
					investmentDetailsObject.getString("guaranteedIncomeType"));
			productDefaultValueRel.setGuaranteedIncome(guaranteedIncome);
			
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("maturityAge"))) {
			log.debug("GIT Invoked::::");
			MaturityAge maturityAge = new MaturityAge();
			maturityAge.setKey(
					investmentDetailsObject.getString("maturityAge"));
			productDefaultValueRel.setMaturityAge(maturityAge);
			
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("familyIncomeBenefits"))) {
			log.debug("GIT Invoked::::");
			AIPFamilyIncomeBenefit aipFIB = new AIPFamilyIncomeBenefit();
			aipFIB.setKey(
					investmentDetailsObject.getString("familyIncomeBenefits"));
			productDefaultValueRel.setAipFamilyIncomeBenefit(aipFIB);
			
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("incomePeriod"))) {
			productDefaultValueRel.setIncomePeriod(investmentDetailsObject.getString("incomePeriod"));
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("incomePercentage"))) {
			productDefaultValueRel.setIncomePercentage(investmentDetailsObject.getString("incomePercentage"));
		}
		
		if(Validator.isNotNull(
				investmentDetailsObject.getString("premiumBreakBenefit"))) {
			productDefaultValueRel.setPremiumBreakBenefit(investmentDetailsObject.getString("premiumBreakBenefit"));
		}

		productDefaultValueRel.setInvestingForApplicable(true);
		
		return productDefaultValueRel;
	} 
	

	/**
	 * Details related to Total Protect plus Product
	 */
	@SuppressWarnings("unchecked")
	private void renderTotalProtectPlusData(String productId, String productCode, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		// Get Current User Data from Cookies - The below method is removed. Please use the latest code as per the new approach. 
		if (Validator.isNull(renderRequest.getAttribute("cookieLeadValue"))) {
			log.error("No Lead data found");
			return;
		}

		Map<String, String> cookieLeadValue = (Map<String, String>) renderRequest.getAttribute("cookieLeadValue");
		if (Validator.isNotNull(cookieLeadValue.get(ParameterConstants.DATE_OF_DEATH))) {
			String dob = cookieLeadValue.getOrDefault(ParameterConstants.DATE_OF_DEATH, StringPool.BLANK)
					.replace(StringPool.SPACE, StringPool.BLANK);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dobDate = LocalDate.parse(dob, formatter);
			String age = String.valueOf(Period.between(dobDate, LocalDate.now()).getYears());
			renderRequest.setAttribute("age", age);
		}

		// Fetch related product details form External Server
		JSONArray recommendedProductList = edelweissTokioCommonApi.getRecommendedProductList(productCode,
				cookieLeadValue);
		if (Validator.isNull(recommendedProductList)) {
			log.error("No Recommended Product details found from external server");
			return;
		}

		// Fetch Product Detals along with Linked Plans and Riders
		JSONObject liferayProductNestedData = edelweissTokioCommonApi
				.getProductDataWithLinkedPlansAndRiders(themeDisplay.getPortalURL(), productId);
		log.debug(liferayProductNestedData);
		if (Validator.isNull(liferayProductNestedData)
				&& Validator.isNull(liferayProductNestedData.getJSONArray("plans"))) {
			log.warn("No Recommended Plans details found from Liferay");
			return;
		}

		// Link the fetced Product list details with Liferay Plans data
		JSONArray recommendedPlansList = liferayProductNestedData.getJSONArray("plans");
		for (int i = 0; i < recommendedPlansList.length(); i++) {
			JSONObject recommendedPlan = recommendedPlansList.getJSONObject(i);
			String uniqueKey = recommendedPlan.getString("uniqueKey");
			recommendedProductList.forEach(recommendedProductObject -> {
				JSONObject recommendedProduct = (JSONObject) recommendedProductObject;
				if (recommendedProduct.getString("UniqueKey", StringPool.BLANK).equalsIgnoreCase(uniqueKey)) {
					recommendedPlan.put("additionalInformation", recommendedProduct);
				}
			});
		}

		log.debug(recommendedPlansList);

		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_NESTED_DATA, liferayProductNestedData);
		renderRequest.setAttribute("recommendedPlansList", recommendedPlansList);

		// Get required picklist
		Map<String, String> planOptions = edelweissTokioCommonApi.getPicklistByExternalReferenceCode("planOption",
				themeDisplay.getCompanyId());
		Map<String, String> paymentPeriods = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode("paymentPeriod", themeDisplay.getCompanyId());
		Map<String, String> paymentFrequencies = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode("paymentFrequency", themeDisplay.getCompanyId());
		
		renderRequest.setAttribute("planOptions", planOptions);
		renderRequest.setAttribute("paymentPeriods", paymentPeriods);
		renderRequest.setAttribute("paymentFrequencies", paymentFrequencies);
	}
	
	/**
	 * Details related to Guaranteed Savings StarData Product
	 * 
	 * @param productCustomizeSummaryData
	 */
	private void renderGuaranteedSavingsStarData(String productId, String productCode,
			Map<String, Object> productCustomizeSummaryData, JSONObject investmentDetailsValues, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		renderRequest.setAttribute("productCode", productCode);

		/*
		 * Fetch Product Detals along with Linked Riders
		 */
		renderRiderDetails(productId, renderRequest);
		JSONObject liferayProductNestedData = edelweissTokioCommonApi
				.getProductAndLinkedRidersData(themeDisplay.getPortalURL(), productId);
		log.debug(liferayProductNestedData);

		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_NESTED_DATA, liferayProductNestedData);

		Map<String, String> payoutOptionMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(PAYOUT_OPTION,
				themeDisplay.getCompanyId());
		Map<String, String> familyIncomeBenefitMap = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode("FAMILY_INCOME_BENEFIT", themeDisplay.getCompanyId());
		Map<String, String> payForMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(POLICY_FOR,
				themeDisplay.getCompanyId());
		Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());

		renderRequest.setAttribute("payoutOptionMap", payoutOptionMap);
		renderRequest.setAttribute("familyIncomeBenefitMap", familyIncomeBenefitMap);
		renderRequest.setAttribute("payForMap", payForMap);
		renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
		
		/*
		 * Update Default investment values with Customer Selected Values
		 */
		if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
			ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
			
			if(Validator.isNotNull(investmentDetailsValues) &&Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&  investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
				JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
				ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
			}
		} 

		log.debug(productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL));
		if (Validator
				.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS))) {
			renderRequest.setAttribute(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL,
					JSONFactoryUtil.createJSONArray((List<ProductInvestmentAmountRel>) productCustomizeSummaryData
							.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)));
		}

		if (Validator.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL))) {
			renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL, JSONFactoryUtil.createJSONArray(
					(List<ProductPolicyForRel>) productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL)));
		}
	}
	
	/**
	 * Render Details for  to Flexi Savings Plan Product
	 * 
	 * @param productCustomizeSummaryData
	 */
	private void renderFlexiSavingsPlanData(String productId, String productCode,
			Map<String, Object> productCustomizeSummaryData, JSONObject investmentDetailsValues, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		renderRequest.setAttribute("productCode", productCode);

		/*
		 * Fetch Product Detals along with Linked Riders
		 */
		renderRiderDetails(productId, renderRequest);
		JSONObject liferayProductNestedData = edelweissTokioCommonApi
				.getProductAndLinkedRidersData(themeDisplay.getPortalURL(), productId);
		log.debug(liferayProductNestedData);

		renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_NESTED_DATA, liferayProductNestedData);

	
		
		Map<String, String> lifeCoverContinuationBenefitOptionsMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(LIFE_COVER_CONTINUATION_BENEFIT,
				themeDisplay.getCompanyId());
		Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());

		renderRequest.setAttribute("lifeCoverContinuationBenefitOptionsMap", lifeCoverContinuationBenefitOptionsMap);
		renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
		
		/*
		 * Update Default investment values with Customer Selected Values
		 */
		if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
			ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
			
			if(Validator.isNotNull(investmentDetailsValues) && Validator.isNotNull(investmentDetailsValues.getJSONArray(ITEMS))&&investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
				JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
				ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
			}
		} 

		log.debug(productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL));
		if (Validator
				.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS))) {
			renderRequest.setAttribute(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL,
					JSONFactoryUtil.createJSONArray((List<ProductInvestmentAmountRel>) productCustomizeSummaryData
							.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)));
		}

		if (Validator.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL))) {
			renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL, JSONFactoryUtil.createJSONArray(
					(List<ProductPolicyForRel>) productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL)));
		}
	}
	
	/**
	 * Render Details for GCAP Product
	 * 
	 * @param productId
	 * @param productCode
	 * @param productCustomizeSummaryData
	 * @param investmentDetailsValues
	 * @param renderRequest
	 */
	private void renderGCAPData(String productId, String productCode,
			Map<String, Object> productCustomizeSummaryData, JSONObject investmentDetailsValues, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		//Set Rider Details for the GCAP Product through Render
		renderRiderDetails(productId, renderRequest);
		
		//Set GCAP Payout Master Option Details for the GCAP Product through Render
		Map<String, String> gcapPayoutOption = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(GCAP_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, themeDisplay.getCompanyId());
		renderRequest.setAttribute("gcapPayoutOption", gcapPayoutOption);
		
		/*
		 * Update Default investment values with Customer Selected Values
		 */
		if(productCustomizeSummaryData.containsKey(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
			ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
			
			if(Validator.isNotNull(investmentDetailsValues) && investmentDetailsValues.getJSONArray(ITEMS).length() > 0) {
				JSONObject investmentDetailsJSON = investmentDetailsValues.getJSONArray(ITEMS).getJSONObject(0);
				renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, investmentDetailsJSON.getString(ID));
				renderRequest.setAttribute(EdelweissProductCustomizeConstants.CUSTOMER_INVESTMENT_DETAILS, investmentDetailsJSON);
				ProductDefaultValueRel productDefaultValues = getInvestmentValueFromObject(investmentDetailsJSON, investmentValues);
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
			}
		} 
		
		//Set Product Investment Amount Relation for Min Amount based on Payment Frequency Details for the GCAP Product through Render
		log.debug(productCustomizeSummaryData.get(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL));
		if (Validator.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS))) {
			renderRequest.setAttribute(EdelweissProductCustomizeConstants.PRODUCT_INVESTMENT_AMOUNT_REL,
					JSONFactoryUtil.createJSONArray((List<ProductInvestmentAmountRel>) productCustomizeSummaryData
							.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)));
		}

		//Set Product Policy For Relation With Policy Term Details for the GCAP Product through Render
		if (Validator.isNotNull(productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL))) {
			renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL, JSONFactoryUtil.createJSONArray(
					(List<ProductPolicyForRel>) productCustomizeSummaryData.get(EdelweissCommonConstants.PRODUCT_POLICY_FOR_REL)));
		}
		
		//Set Payor Waiver Benefit Rider Dropdown Options Details for the GCAP Product through Render
		Map<String, String> payorWaiverBenefitOptions = edelweissTokioCommonApi
				.getPicklistByExternalReferenceCode(PAYOR_WAIVER_BENEFIT_OPTIONS, themeDisplay.getCompanyId());
		renderRequest.setAttribute(EdelweissProductCustomizeConstants.PAY_OR_WAIVER_BENEFIT_OPTIONS, payorWaiverBenefitOptions);
		
	}

	

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
}