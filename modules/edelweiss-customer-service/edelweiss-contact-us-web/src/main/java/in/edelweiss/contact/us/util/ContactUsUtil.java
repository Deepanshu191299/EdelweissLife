package in.edelweiss.contact.us.util;

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
 *         The class will contain the API Implementation of Contact Us Related APIs.
 */
public class ContactUsUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private ContactUsUtil() {

	}
	
	/**
	 *This method is used to call the customer service request API to get the response using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> customerServiceRequest(String detailsJSON, String endpointURL) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();
			
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
	 *This method is used to call the Send SMS API using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> sendSMS(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();
			
			String endpointURL = apiURLConfiguration.getSMSSendURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForSendSMS();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callSMSPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}
			
		}catch(Exception exception) {
			logger.error("Error while calling Send SMS API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	private static Log logger = LogFactoryUtil.getLog(ContactUsUtil.class);
}
