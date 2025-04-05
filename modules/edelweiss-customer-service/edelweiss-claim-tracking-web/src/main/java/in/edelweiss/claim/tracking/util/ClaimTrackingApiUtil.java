package in.edelweiss.claim.tracking.util;

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
 * The class will contain the API Implementation of Claim Tracking related APIs.
 */
public class ClaimTrackingApiUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private ClaimTrackingApiUtil() {

	}

	/**
	 *This method is used to call the Claim Tracking API to get the status of the claim.
	 *
	 *@param trackingNumber
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> claimTracking(String trackingNumber) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();
			
			String endpointURL = apiURLConfiguration.getClaimTrackingFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnachGet();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);
			
			Map<String, String> params = new HashMap<>();

			if(Validator.isNotNull(accessToken)) {
				params.put("claimReferenceNumber", trackingNumber);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, params);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Claim Tracking API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(ClaimTrackingApiUtil.class);
}

