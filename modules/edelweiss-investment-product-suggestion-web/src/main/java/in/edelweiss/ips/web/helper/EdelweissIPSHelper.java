package in.edelweiss.ips.web.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;

import in.edelweiss.ips.web.constants.EdelweissIPSConstants;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.InvestingFor;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.PaymentOption;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductDefaultValueRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

public class EdelweissIPSHelper {

	private static final Log log = LogFactoryUtil.getLog(EdelweissIPSHelper.class);

	
	private EdelweissIPSHelper() {
		
	}
	
	/**
	 * Parse Relation Data from JSON Response
	 * 
	 * @param productRelObjectResponse
	 * @return productCustomizeSummaryData
	 */
	public static Map<String, Object> getIPSCustomizeData(JSONObject productRelObjectResponse) {
		Map<String, Object> productCustomizeSummaryData = new HashMap<>();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			log.debug("EdelweissIPSHelper >>> getIPSCustomizeData >>> Product Rel Response ::: " + productRelObjectResponse);
			LiferayResponseMessage liferayResponseMessage = objectMapper.readValue(productRelObjectResponse.toString(),
					LiferayResponseMessage.class);

			productCustomizeSummaryData.put("iNGPlanSubType", liferayResponseMessage.getiNGPlanSubType());
			
			if (Validator.isNotNull(liferayResponseMessage.getProductInvestingForRels()) && !liferayResponseMessage.getProductInvestingForRels().isEmpty()) {
				productCustomizeSummaryData.put("investingForData", liferayResponseMessage.getProductInvestingForRels().get(0).getInvestingFors());
			}

			if (Validator.isNotNull(liferayResponseMessage.getProductPaymentOptionRels()) && !liferayResponseMessage.getProductPaymentOptionRels().isEmpty()) {
				productCustomizeSummaryData.put("paymentOptionsData", liferayResponseMessage.getProductPaymentOptionRels().get(0).getPaymentOptions());
			}

			if (Validator.isNotNull(liferayResponseMessage.getProductDefaultValueRels()) && !liferayResponseMessage.getProductDefaultValueRels().isEmpty()) {
				productCustomizeSummaryData.put(EdelweissIPSConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, liferayResponseMessage.getProductDefaultValueRels().get(0));
			}
			
			if(Validator.isNotNull(liferayResponseMessage.getProductInvestmentAmountRels())) {
				productCustomizeSummaryData.put(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS, liferayResponseMessage.getProductInvestmentAmountRels());
			}
			
			if (Validator.isNotNull(liferayResponseMessage.getProductMetaDataRels()) && !liferayResponseMessage.getProductMetaDataRels().isEmpty()) {
				productCustomizeSummaryData.put(EdelweissCommonConstants.PRODUCT_METADATA_RELS_DATA, liferayResponseMessage.getProductMetaDataRels().get(0));
			}

			log.debug("EdelweissIPSHelper >>> getIPSCustomizeData >>> ipsCustomizeSummaryData ::: " + productCustomizeSummaryData);

		} catch (Exception e) {
			log.error("EdelweissIPSHelper >>> getIPSCustomizeData >>> Error while parsing Product Relation Response ::: " + e);
		}

		return productCustomizeSummaryData;
	}
	
	public static ProductDefaultValueRel getInvestmentValueFromObject(JSONObject investmentDetailsObject, ProductDefaultValueRel productDefaultValueRel){
		
		// Projected Returns Set
		productDefaultValueRel.setProjectedReturnsData(investmentDetailsObject.getString("projectedReturnsData"));

		// Policy Term Set
		productDefaultValueRel.setPolicyTerm(investmentDetailsObject.getString("policyTerm"));
				
		// Investing Amount Set
		productDefaultValueRel.setInvestmentAmount(investmentDetailsObject.getString("investmentAmount"));
		
		// Set value of Pay For (Premium Paying Term) 
		productDefaultValueRel.setPolicyFor(investmentDetailsObject.getString("premiumPayingTerm"));
		
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

		productDefaultValueRel.setInvestingForApplicable(true);
		
		return productDefaultValueRel;
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
			log.error("EdelweissIPSHelper >>> getMapToJson >>> Error occured while converting object to json ::: " + e);
		}
		return jsonString;
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
		if(Validator.isNotNull(requestParametersMap.get(EdelweissCommonConstants.INVESTMENT_AMOUNT_PARAM))) {
			customerInvestmentDetails.setInvestmentAmount(requestParametersMap.get(EdelweissCommonConstants.INVESTMENT_AMOUNT_PARAM).replace(StringPool.COMMA, StringPool.BLANK));
		}
		customerInvestmentDetails.setInvestingFor(requestParametersMap.get(EdelweissCommonConstants.INVESTING_FOR_PARAM));
		customerInvestmentDetails.setPolicyTerm(requestParametersMap.get(EdelweissCommonConstants.POLICY_TERM_PARAM));
		customerInvestmentDetails.setPremiumPayingTerm(requestParametersMap.get(EdelweissCommonConstants.PAY_FOR_PARAM));
		customerInvestmentDetails.setPaymentFrequency(requestParametersMap.get(EdelweissCommonConstants.PAYMENT_OPTION_PARAM));
		customerInvestmentDetails.setRate(requestParametersMap.get(EdelweissCommonConstants.TOTAL_RETURNS_VALUE));
		customerInvestmentDetails.setrCustomerInvestmentDetailsRelCCustomerEnquiryId(requestParametersMap.getOrDefault(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,StringPool.BLANK));
		customerInvestmentDetails.setProductId(requestParametersMap.getOrDefault(EdelweissCommonConstants.PRODUCT_CODE_PARAM,StringPool.BLANK));
		
		customerInvestmentDetails.setLeadId(leadId);
		
		log.debug("EdelweissIPSHelper >>> getRequestInvestmentDetails >>> Customer Investment Details ::: " + customerInvestmentDetails.toString());
		return customerInvestmentDetails;
	}
	
	public static void hideSuccessAndErrorMessage(PortletRequest portletRequest) {
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
		SessionMessages.add(portletRequest, PortalUtil.getPortletId(portletRequest) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}
	
	private static ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	

}
