package in.edelweiss.resume.application.util;

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
 * The class will contain the API Implementation & Some API Related Constants Methods.
 */
public class ResumeApplicationUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private ResumeApplicationUtil() {

	}

	/**
	 * This method is used to call the Resume Application API to get the URL.
	 *
	 * @param inputType
	 * @param inputValue
	 * @param dateOfBirth
	 * @param requestType
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> resumeApplication(String policyNumber, String dateOfBirth) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getResumeApplicationFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForBuyJourney();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);
			
			Map<String, String> params = new HashMap<>();
			
			if (Validator.isNotNull(accessToken)) {
				params.put("policyNumber", policyNumber);
				params.put("dob", dateOfBirth);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
			}

		} catch (Exception exception) {
			logger.error("Error while calling Resume Application API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	private static Log logger = LogFactoryUtil.getLog(ResumeApplicationUtil.class);
}
