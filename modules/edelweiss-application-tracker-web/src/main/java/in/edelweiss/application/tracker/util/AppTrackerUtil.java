package in.edelweiss.application.tracker.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.AppTrackerAPIURLConfiguration;

/**
 * @author Abhijit AA
 * 
 *         The class will contain the API Implementation of Premium Paid Certificate Related APIs.
 */
public class AppTrackerUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private AppTrackerUtil() {

	}

	/**
	 * This method is used to call the Proposal Form Policy Details API to get the details of the policy
	 *
	 * @param inputType
	 * @param inputValue
	 * @param dateOfBirth
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> proposalFormPolicyDetails(String inputType, String inputValue, String dateOfBirth) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			AppTrackerAPIURLConfiguration appTrackerAPIURLConfiguration = ConfigurationUtil.getAppTrackerAPIURLConfiguration();

			String endpointURL = appTrackerAPIURLConfiguration.getProposalFormPolicyDetailsURL();
			
			if (Validator.isNotNull(endpointURL)) {
				params.put(inputType, inputValue);
				params.put("dob", dateOfBirth);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						StringPool.BLANK, StringPool.BLANK, params);
				logger.debug("responseMap in applicationUtil:::"+ responseMap);
			}

		} catch (Exception exception) {
			logger.error("Error while calling proposal form policy details API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 * This method is used to call the Verification Policy Details API to get the details of the policy
	 *
	 * @param applicatioNumber
	 * @param policyNumber
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> verificationDetails(String applicatioNumber, String policyNumber) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			AppTrackerAPIURLConfiguration appTrackerAPIURLConfiguration = ConfigurationUtil.getAppTrackerAPIURLConfiguration();

			String endpointURL = appTrackerAPIURLConfiguration.getVerificationDetailsURL();
			
			if (Validator.isNotNull(endpointURL)) {
				params.put("ApplicationNumber", applicatioNumber);
				params.put("PolicyNumber", policyNumber);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						StringPool.BLANK, StringPool.BLANK, params);
			}

		} catch (Exception exception) {
			logger.error("Error while calling verification policy details API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 * This method is used to call the Proposal Form Policy Details API to get the details of the policy
	 *
	 * @param inputType
	 * @param inputValue
	 * @param dateOfBirth
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> communicationDetails(String inputType, String inputValue, String isTPARequired) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			AppTrackerAPIURLConfiguration appTrackerAPIURLConfiguration = ConfigurationUtil.getAppTrackerAPIURLConfiguration();

			String endpointURL = appTrackerAPIURLConfiguration.getCommunicationsDetailsURL();
			
			if (Validator.isNotNull(endpointURL)) {
				params.put(inputType, inputValue);
				params.put("IsTPARequired", isTPARequired);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						StringPool.BLANK, StringPool.BLANK, params);
			}

		} catch (Exception exception) {
			logger.error("Error while calling proposal form policy details API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(AppTrackerUtil.class);
}
