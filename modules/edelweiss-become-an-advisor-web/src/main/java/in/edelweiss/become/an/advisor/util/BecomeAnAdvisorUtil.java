package in.edelweiss.become.an.advisor.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.ApiKeyConfiguration;
import in.edelweiss.system.configurations.ApiURLConfiguration;

/**
 * @author Abhijit AA
 * 
 * The class will contain the API Implementation & Some API Related Constants Methods.
 */
public class BecomeAnAdvisorUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private BecomeAnAdvisorUtil() {

	}

	/**
	 *This method is used to call the Become An Advisor API to submit the application using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> becomeAnAdvisor(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();
			
			String endpointURL = apiURLConfiguration.getBecomeAnAdvisorFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Become an Advisor API : " + exception);
			if (logger.isDebugEnabled()) {
				logger.error("Exception", exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(BecomeAnAdvisorUtil.class);
}
