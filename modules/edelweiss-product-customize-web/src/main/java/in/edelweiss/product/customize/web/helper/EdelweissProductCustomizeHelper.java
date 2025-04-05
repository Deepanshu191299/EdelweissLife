package in.edelweiss.product.customize.web.helper;

import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.FUND_MANAGEMENT;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INVESTING_FOR;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INVESTMENT_AMOUNT;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.LIFE_COVER;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PAYMENT_OPTION;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PAY_FOR;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.POLICY_FOR;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.POLICY_OPTION;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.POLICY_TERM;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.CUSTOMER_POLICY_TERM;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PROJECTED_RETURNS_DATA;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.SYSTEMATIC_WITHDRAWL_PLAN;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.TOTAL_RETURNS;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.TOTAL_RETURNS_VALUE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PLAN_OPTION;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INCOME_PERIOD;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INCOME_PERCENTAGE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PREMIUM_BREAK_BENEFIT;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.EDELWEISS_EMPLOYEE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INCOME_PAYOUT_TIME;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.SUM_ASSURED_MULTIPLE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.INCOME_PAYOUT_TYPE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.PAYOUT_INCREASING_PERCENTAGE;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.ANNUAL_PREM2;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

public class EdelweissProductCustomizeHelper {
	
	private EdelweissProductCustomizeHelper() {
	    throw new IllegalStateException("EdelweissProductCustomizeHelper >>> Constructor Invoked >>> Utility class invoked at an illegal or inappropriate time ::: ");
	}


	private static final Log log = LogFactoryUtil.getLog(EdelweissProductCustomizeHelper.class);
	
	public static String getLeadMetaDataURL(String leadId) {
		StringBuilder leadURL = new StringBuilder();
		leadURL.append(LEAD_ID);
		leadURL.append(StringPool.SPACE);
		leadURL.append("eq");
		leadURL.append(StringPool.SPACE);
		leadURL.append("'" + leadId + "'");
		return URLEncoder.encode(leadURL.toString(), StandardCharsets.UTF_8).replace(" ", "%20");
	}
	
	/**
	 * Parse Relation Data from JSON Response
	 * @param productRelObjectResponse
	 * @return productCustomizeSummaryData
	 */
	public static Map<String, Object> getProductCustomizeSummaryData(JSONObject productRelObjectResponse) {
		Map<String, Object> productCustomizeSummaryData = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			log.debug(productRelObjectResponse);
			LiferayResponseMessage liferayResponseMessage = objectMapper.readValue(productRelObjectResponse.toString(), LiferayResponseMessage.class);
			
			productCustomizeSummaryData.put("iNGPlanSubType", liferayResponseMessage.getiNGPlanSubType());
			
			if(Validator.isNotNull(liferayResponseMessage.getProductPolicyOptionRels()) && !liferayResponseMessage.getProductPolicyOptionRels().isEmpty()) {
				productCustomizeSummaryData.put("policyOptionsData", liferayResponseMessage.getProductPolicyOptionRels().get(0).getPolicyOptions());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductInvestingForRels()) && !liferayResponseMessage.getProductInvestingForRels().isEmpty()) {
				productCustomizeSummaryData.put("investingForData", liferayResponseMessage.getProductInvestingForRels().get(0).getInvestingFors());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductPaymentOptionRels()) && !liferayResponseMessage.getProductPaymentOptionRels().isEmpty()) {
				productCustomizeSummaryData.put("paymentOptionsData", liferayResponseMessage.getProductPaymentOptionRels().get(0).getPaymentOptions());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductPaymentOptionRels()) && !liferayResponseMessage.getProductPaymentOptionRels().isEmpty()) {
				productCustomizeSummaryData.put("paymentOptionRelations", JSONFactoryUtil.createJSONArray(liferayResponseMessage.getProductPaymentOptionRels()));
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductFundManagementRels()) && !liferayResponseMessage.getProductFundManagementRels().isEmpty()) {
				productCustomizeSummaryData.put("fundManagementData", liferayResponseMessage.getProductFundManagementRels().get(0).getFundManagementOptions());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductDefaultValueRels()) && !liferayResponseMessage.getProductDefaultValueRels().isEmpty()) {
				productCustomizeSummaryData.put(EdelweissProductCustomizeConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, liferayResponseMessage.getProductDefaultValueRels().get(0));
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductInvestmentAmountRels())) {
				productCustomizeSummaryData.put(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS, liferayResponseMessage.getProductInvestmentAmountRels());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductPolicyForRel())) {
				productCustomizeSummaryData.put("productPolicyForRel", liferayResponseMessage.getProductPolicyForRel());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getBrochure())) {
				productCustomizeSummaryData.put("productBrochure", liferayResponseMessage.getBrochure());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getFamilyIncomeBenefitOptions()) && !liferayResponseMessage.getFamilyIncomeBenefitOptions().isEmpty()) {
				productCustomizeSummaryData.put("familyIncomeBenefitOptions", liferayResponseMessage.getFamilyIncomeBenefitOptions().get(0).getFamilyIncomeBenefitOptions());
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductIsyandPtRelationship())) {
				productCustomizeSummaryData.put("productIsyandPtRelationship", JSONFactoryUtil.createJSONArray(liferayResponseMessage.getProductIsyandPtRelationship()));
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductValidations())) {
				productCustomizeSummaryData.put("productValidations", JSONFactoryUtil.createJSONArray(liferayResponseMessage.getProductValidations()));
			}
			
			log.debug("EdelweissProductCustomizeHelper >>> getProductCustomizeSummaryData >>> productCustomizeSummaryData ::: " + productCustomizeSummaryData);
			
		} catch(Exception e) {
			log.error("EdelweissProductCustomizeHelper >>> getProductCustomizeSummaryData >>> Error while parsing Product Relation Response ::: " + e);
		}
		
		return productCustomizeSummaryData;
	}
	
	
	/**
	 * Get Customer Investment Details Request Bean for Saving to Liferay Object
	 * @param requestParametersMap
	 * @param leadId
	 * @return customerInvestmentDetails
	 */
	public static CustomerInvestmentDetails getRequestInvestmentDetails(Map<String, String> requestParametersMap, String leadId) {
		
		log.debug("Product Summary Page >> Investment Plan details Form >> Form parameters: "+requestParametersMap);
		
		CustomerInvestmentDetails customerInvestmentDetails = new CustomerInvestmentDetails();
		customerInvestmentDetails.setEdelweissEmployee(requestParametersMap.getOrDefault(EDELWEISS_EMPLOYEE,StringPool.BLANK));
		customerInvestmentDetails.setIncomePayoutTime(requestParametersMap.getOrDefault(INCOME_PAYOUT_TIME,StringPool.BLANK));
		customerInvestmentDetails.setSumAssuredMultiple(requestParametersMap.getOrDefault(SUM_ASSURED_MULTIPLE,StringPool.BLANK));
		customerInvestmentDetails.setIncomePayoutType(requestParametersMap.getOrDefault(INCOME_PAYOUT_TYPE,StringPool.BLANK));
		customerInvestmentDetails.setPayoutIncreasingPercentage(requestParametersMap.getOrDefault(PAYOUT_INCREASING_PERCENTAGE,StringPool.BLANK));
		customerInvestmentDetails.setAnnualPrem2(requestParametersMap.getOrDefault(ANNUAL_PREM2,StringPool.BLANK));
		
		if(Validator.isNotNull(requestParametersMap.get(INVESTMENT_AMOUNT))) {
			customerInvestmentDetails.setInvestmentAmount(requestParametersMap.get(INVESTMENT_AMOUNT).replace(StringPool.COMMA, StringPool.BLANK));
		}else if(Validator.isNotNull(requestParametersMap.get(LIFE_COVER))) {
			customerInvestmentDetails.setInvestmentAmount(requestParametersMap.get(LIFE_COVER).replace(StringPool.COMMA, StringPool.BLANK));
		}
		
		customerInvestmentDetails.setPolicyOption(requestParametersMap.get(POLICY_OPTION));
		
		if(Validator.isNotNull(requestParametersMap.get(PLAN_OPTION))) {
			customerInvestmentDetails.setPolicyOption(requestParametersMap.get(PLAN_OPTION));
		}

		if(Validator.isNotNull(requestParametersMap.get(POLICY_TERM))) {
			customerInvestmentDetails.setPolicyTerm(requestParametersMap.get(POLICY_TERM));
		}else {
			customerInvestmentDetails.setPolicyTerm(requestParametersMap.get(CUSTOMER_POLICY_TERM));
		}
		
		customerInvestmentDetails.setPaymentFrequency(requestParametersMap.get(PAYMENT_OPTION));
		if(requestParametersMap.containsKey(POLICY_FOR)) {
			customerInvestmentDetails.setPremiumPayingTerm(requestParametersMap.get(POLICY_FOR));
		}
		if(requestParametersMap.containsKey(PAY_FOR)) {
			customerInvestmentDetails.setPremiumPayingTerm(requestParametersMap.get(PAY_FOR));
		}
		customerInvestmentDetails.setInvestmentStrategy(requestParametersMap.get(FUND_MANAGEMENT));
		customerInvestmentDetails.setInvestingFor(requestParametersMap.get(INVESTING_FOR));
		String swp = requestParametersMap.getOrDefault(SYSTEMATIC_WITHDRAWL_PLAN, StringPool.BLANK);
		customerInvestmentDetails.setSystematicWithdrawalPlan(swp);
		if (Validator.isNotNull(requestParametersMap.get(TOTAL_RETURNS))) {
			customerInvestmentDetails.setTotalReturns(requestParametersMap.get(TOTAL_RETURNS));
		} else if (Validator.isNotNull(requestParametersMap.get(ParameterConstants.PREMIUM_AMOUNT))) {
			customerInvestmentDetails.setTotalReturns(requestParametersMap.get(ParameterConstants.TOTAL_MATURITY_AMOUNT));
		}
		customerInvestmentDetails.setRate(requestParametersMap.getOrDefault(TOTAL_RETURNS_VALUE,StringPool.BLANK));
		customerInvestmentDetails.setProjectedReturnsData(requestParametersMap.getOrDefault(PROJECTED_RETURNS_DATA,StringPool.BLANK));
		customerInvestmentDetails.setrCustomerInvestmentDetailsRelCCustomerEnquiryId(requestParametersMap.getOrDefault(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,StringPool.BLANK));
		customerInvestmentDetails.setProductId(requestParametersMap.getOrDefault(EdelweissCommonConstants.PRODUCT_CODE_PARAM,StringPool.BLANK));
		customerInvestmentDetails.setPayoutOption(requestParametersMap.getOrDefault(ParameterConstants.PAYOUT_OPTION,StringPool.BLANK));
		if(requestParametersMap.get(EdelweissCommonConstants.PRODUCT_NAME).equals("Premier Guaranteed Income")) {
			customerInvestmentDetails.setPayoutOption(requestParametersMap.getOrDefault(ParameterConstants.MATURITY_PAYOUT_OPTION, StringPool.BLANK));
		}
		customerInvestmentDetails.setFamilyIncomeBenefits((requestParametersMap.getOrDefault(ParameterConstants.FAMILY_INCOME_BENEFIT,StringPool.BLANK)));
		customerInvestmentDetails.setRiderId(requestParametersMap.getOrDefault(ParameterConstants.SELECTED_RIDERS_IDS,StringPool.BLANK));
		customerInvestmentDetails.setBasePremiumAmount(requestParametersMap.getOrDefault(ParameterConstants.BASE_PREMIUM_AMOUNT,StringPool.BLANK));
		customerInvestmentDetails.setPremiumAmount(requestParametersMap.getOrDefault(ParameterConstants.PREMIUM_AMOUNT,StringPool.BLANK));
		
		customerInvestmentDetails.setEdelweissEmployee(requestParametersMap.getOrDefault(ParameterConstants.IS_EDELWEISS_EMPLOYEE,StringPool.BLANK));
		customerInvestmentDetails.setLumpSumBenefits(requestParametersMap.getOrDefault(ParameterConstants.LUMP_SUM_BENEFIT,StringPool.BLANK));
		customerInvestmentDetails.setIncomePayoutFrequency(requestParametersMap.getOrDefault(ParameterConstants.INCOME_PAYOUT_FREQUENCY,StringPool.BLANK));
		customerInvestmentDetails.setIncomeFor(requestParametersMap.getOrDefault(ParameterConstants.GET_INCOME_FOR,StringPool.BLANK));
		customerInvestmentDetails.setMaturityAge(requestParametersMap.getOrDefault(ParameterConstants.MATURITY_AGE, StringPool.BLANK));
		customerInvestmentDetails.setIncomeOption(requestParametersMap.getOrDefault(ParameterConstants.INCOME_OPTION, StringPool.BLANK));
		customerInvestmentDetails.setGuaranteedIncomeType(requestParametersMap.getOrDefault(ParameterConstants.GUARANTED_INCOME, StringPool.BLANK));
		customerInvestmentDetails.setPayoutPeriod(requestParametersMap.getOrDefault(ParameterConstants.PAYOUT_PERIOD,StringPool.BLANK));
		customerInvestmentDetails.setPlanOption(requestParametersMap.getOrDefault(ParameterConstants.PLAN_OPTION, StringPool.BLANK));
		customerInvestmentDetails.setIncomeStartYear(requestParametersMap.getOrDefault(ParameterConstants.INCOME_START_YEAR, StringPool.BLANK));
		customerInvestmentDetails.setIncomeStartPoint(requestParametersMap.getOrDefault(ParameterConstants.INCOME_START_POINT, StringPool.BLANK));
		customerInvestmentDetails.setLifeCoverContinuationBenefit(requestParametersMap.getOrDefault(ParameterConstants.LIFE_COVER_CONTINUATION_BENEFIT, StringPool.BLANK));

		customerInvestmentDetails.setIncomeDuration(requestParametersMap.getOrDefault(ParameterConstants.INCOME_DURATION, StringPool.BLANK));
		if(requestParametersMap.get(EdelweissCommonConstants.PRODUCT_NAME).equals("Guaranteed Income STAR")) {
			customerInvestmentDetails.setGuaranteedIncomeType(requestParametersMap.getOrDefault(ParameterConstants.INCOME_BENEFIT_PAYOUT_TYPE, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(requestParametersMap.get(ParameterConstants.LIFE_COVER))) {
			customerInvestmentDetails.setLifeCover(requestParametersMap.get(ParameterConstants.LIFE_COVER).replaceAll(StringPool.COMMA, StringPool.BLANK));
		}
		
		if(Validator.isNotNull(requestParametersMap.get(INCOME_PERIOD))) {
			customerInvestmentDetails.setIncomePeriod(requestParametersMap.get(INCOME_PERIOD));
		}
		if(Validator.isNotNull(requestParametersMap.get(INCOME_PERCENTAGE))) {
			customerInvestmentDetails.setIncomePercentage(requestParametersMap.get(INCOME_PERCENTAGE));
		}
		if(Validator.isNotNull(requestParametersMap.get(PREMIUM_BREAK_BENEFIT))) {
			customerInvestmentDetails.setPremiumBreakBenefit(requestParametersMap.get(PREMIUM_BREAK_BENEFIT));
		}

		customerInvestmentDetails.setLeadId(leadId);
		
		//SWP 5 Means Yes and 6 Mean No
		if(Validator.isNotNull(swp) && swp.equalsIgnoreCase(EdelweissCommonConstants.SWP_YES_VALUE)) {
			customerInvestmentDetails.setFundValuetobeWithdrawn(requestParametersMap.getOrDefault(EdelweissProductCustomizeConstants.PARAM_FUND_VALUE_TOBE_WITHDRAWN, StringPool.BLANK));
			customerInvestmentDetails.setPolicyYearFromWhichSWPPayable(requestParametersMap.getOrDefault(EdelweissProductCustomizeConstants.PARAM_POLICY_YEAR_FROM_WHICH_SWP_PAYABLE, StringPool.BLANK));
			customerInvestmentDetails.setSwpFrequency(requestParametersMap.getOrDefault(EdelweissProductCustomizeConstants.PARAM_SWP_FREQUENCY, StringPool.BLANK));
		} else if(Validator.isNotNull(swp) && swp.equalsIgnoreCase(EdelweissCommonConstants.SWP_NO_VALUE)) {
			customerInvestmentDetails.setFundValuetobeWithdrawn(StringPool.BLANK);
			customerInvestmentDetails.setPolicyYearFromWhichSWPPayable(StringPool.BLANK);
			customerInvestmentDetails.setSwpFrequency(StringPool.BLANK);
		}
		
		customerInvestmentDetails.setGst(requestParametersMap.get(ParameterConstants.GST));
		
		log.debug("EdelweissProductCustomizeHelper >>> getRequestInvestmentDetails >>> Customer Investment Details ::: " + customerInvestmentDetails.toString());
		return customerInvestmentDetails;
	}
	
	/**
	 * Object to JSON String conversion
	 * @param requestData
	 * @return
	 */
	public static String getJsonStringFromObject(Object requestData) {
		String jsonString = StringPool.BLANK;
		try {
			jsonString = objectMapper.writeValueAsString(requestData);
		} catch (Exception e) {
			log.error("EdelweissProductCustomizeHelper >>> getMapToJson >>> Error occured while converting object to json ::: " + e);
		}
		return jsonString;
	}
	
	
	/**
	 * Create Rider Validation Map
	 * 
	 * @param productRiders
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject createRiderValidationJSONObject(JSONArray productRiders) {
		JSONObject riderValidations = JSONFactoryUtil.createJSONObject();
		productRiders.forEach((riderObject) -> {
			JSONObject rider = (JSONObject) riderObject;
			if (Validator.isNull(rider.getJSONArray("riderValidations"))) {
				return;
			}
			rider.getJSONArray("riderValidations").forEach((riderValidationObject) -> {
				JSONObject riderValidation = (JSONObject) riderValidationObject;
				log.debug(riderValidation);
				if (riderValidation.getBoolean("isActive")) {
					log.debug(riderValidation);
					riderValidations.put(rider.getString("riderId"), riderValidation);
				}
			});
		});
		log.debug(riderValidations);
		return riderValidations;
	}
	
	public static void hideSuccessAndErrorMessage(PortletRequest portletRequest) {
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
	
	private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
}
