package in.edelweiss.personality.quiz.util;

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
public class PersonalityQuizUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private PersonalityQuizUtil() {

	}

	/**
	 *This method is used to call the Lead Generation API to submit the lead details using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> personalityQuiz(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getLeadGenerateFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGenerateLead();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Lead Generation API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(PersonalityQuizUtil.class);
}
