package in.edelweiss.soft.login.util;

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
 * The class will contain the API Implementation of Soft Login Related APIs.
 */
public class FilterApiUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private FilterApiUtil() {

	}

	/**
	 *This method is used to call the Send OTP API using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> sendOTP(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration ApiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = ApiURLConfiguration.getOTPSendURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnachOTP();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Send OTP API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 *This method is used to call the Validate OTP API using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> validateOTP(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getOTPValidateURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnachOTP();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Validate OTP API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(FilterApiUtil.class);
}

