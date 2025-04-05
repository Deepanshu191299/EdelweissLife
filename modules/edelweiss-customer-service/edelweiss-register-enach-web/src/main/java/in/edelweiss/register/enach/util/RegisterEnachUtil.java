package in.edelweiss.register.enach.util;

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
import in.edelweiss.system.configurations.ApiURLConfiguration;
import in.edelweiss.system.configurations.ApiKeyConfiguration;

/**
 * @author Abhijit AA
 * 
 *         The class will contain the API Implementation of Register Enach Related APIs.
 */
public class RegisterEnachUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private RegisterEnachUtil() {

	}

	/**
	 * This method is used to call the Customer Policy Data API to get the status of
	 * the claim.
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
			
			/*logger.info("....endpointURL...."+ endpointURL);
			logger.info("....xApiKey...."+ xApiKey);
			logger.info("....accessToken...."+ accessToken); */
			
			if (Validator.isNotNull(accessToken)) {
				params.put(inputType, inputValue);
				params.put("dob", dateOfBirth);
				params.put("requestType", requestType);
				
				//logger.debug("requestJSON::"+ params);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
				//logger.debug("responseMap:::"+ responseMap);
				
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
	 * This method is used to call the IFSC API to get the Bank Name.
	 *
	 * @param bankBranchIFSCCode
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> bankNameFromIFSC(String bankBranchIFSCCode) {
		logger.info("inside bankNameFromIFSC method....." );
		Map<String, Object> responseMap = new HashMap<>();

		try {

			Map<String, String> params = new HashMap<>();
			
			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getIFSCDetailsURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForIFSC();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);
			
			logger.info("....endpointURL...."+ endpointURL);
			logger.info("....xApiKey...."+ xApiKey);
			logger.info("....accessToken...."+ accessToken);
						
			if (Validator.isNotNull(accessToken)) {
				params.put("ifsccode", bankBranchIFSCCode);
				logger.info("....requestJSON...."+params);
				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
				logger.info("...response bankNameFromIFSC..."+ responseMap );
			}

		} catch (Exception exception) {
			logger.error("Error while calling ifsc API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	
	/** Added By Akash
	 * 
	 * This method is used to call the ICICI Mandate API.
	 *
	 * @param iciciMandateJSON
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> iciciMandateRegistration(String iciciMandateJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			String endpointURL = apiURLConfiguration.getICICIMandateRegistrationURL();

			responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, false,
						StringPool.BLANK, StringPool.BLANK, iciciMandateJSON);

		} catch (Exception exception) {
			logger.error("Error while calling ICICI Mandate API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return responseMap;
	}
	
	/** Added By Razina
	 * 
	 * This method is used to call the SBI Mandate API.
	 *
	 * @param sbiiMandateJSON
	 *
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> sbiMandateRegistration(String sbiMandateJSON) {
		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			String endpointURL = apiURLConfiguration.getSBIMandateRegistrationURL();
			
			responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, false,
						StringPool.BLANK, StringPool.BLANK, sbiMandateJSON);
			
			logger.info("responsemap in RegisterEnachUtil" + responseMap);
			
		} catch (Exception exception) {
			logger.error("Error while calling SBI Mandate API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return responseMap;
	}
	
	/**
	 *This method is used to call the register enach API to submit the enach request using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> registerEnach(String detailsJSON) {
		logger.info("inside the registerEnach Util....");
		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getEnachMandateFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnachRegister();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);
			
			/*logger.info("....endpointURL...."+ endpointURL);
			logger.info("....xApiKey...."+ xApiKey);
			logger.info("....accessToken...."+ accessToken);
			logger.info("....requestJSON...."+detailsJSON); */
			

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
				//logger.info("responseMapfor RegisterEnach:::"+ responseMap);
			}

		}catch(Exception exception) {
			logger.error("Error while calling register enach API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}
	
	/**
	 *This method is used to call the report enach failure API to submit the failure using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> reportEnachFailure(String detailsJSON) {
		logger.info("inside the reportEnachFailure Method.......");
		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getEnachFailureFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnachRegister();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);
			
			/*logger.info("....endpointURL...."+ endpointURL);
			logger.info("....xApiKey...."+ xApiKey);
			logger.info("....accessToken...."+ accessToken);
			logger.info("....requestJSON...."+detailsJSON); */

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
				//logger.info("responseMap from reportEnachFailure:::::::"+ responseMap);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Report Enach Failure API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(RegisterEnachUtil.class);
}
