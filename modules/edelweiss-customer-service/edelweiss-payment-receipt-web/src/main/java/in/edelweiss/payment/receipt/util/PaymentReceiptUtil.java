package in.edelweiss.payment.receipt.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.ApiURLConfiguration;
import in.edelweiss.system.configurations.ApiKeyConfiguration;

/**
 * @author Abhijit AA
 * 
 *         The class will contain the API Implementation of Payment Receipt Related APIs.
 */
public class PaymentReceiptUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private PaymentReceiptUtil() {

	}

	/**
	 * This method is used to call the Customer Policy Data API to get the details of the customer
	 *
	 * @param inputType
	 * @param inputValue
	 * @param dateOfBirth
	 * @param requestType
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> customerPolicyData(String inputType, String inputValue, String dateOfBirth, String requestType) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getCustomerPolicyDataURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);
			
			if (Validator.isNotNull(accessToken)) {
				params.put(inputType, inputValue);
				params.put("dob", dateOfBirth);
				params.put("requestType", requestType);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
			}

		} catch (Exception exception) {
			logger.error("Error while calling customer policy data API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 *This method is used to call the customer service request API to get the request id using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> customerServiceRequest(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getRequestFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Customer Service Request API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 * This method is used to call generate document URL API to get the document URL.
	 *
	 * @param documentID
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> generateDocumentURL(String documentID) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getDMSGenerateDownloadURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnach();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);
			
			if (Validator.isNotNull(accessToken)) {
				params.put("id", documentID);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
			}

		} catch (Exception exception) {
			logger.error("Error while calling Generate Document URL API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(PaymentReceiptUtil.class);
}
