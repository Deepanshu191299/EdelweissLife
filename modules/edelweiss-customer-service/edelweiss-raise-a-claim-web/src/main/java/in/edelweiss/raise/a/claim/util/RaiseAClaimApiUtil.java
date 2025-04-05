package in.edelweiss.raise.a.claim.util;

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
 * The class will contain the API Implementation & Some API Related Constants Methods.
 */
public class RaiseAClaimApiUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private RaiseAClaimApiUtil() {

	}

	/**
	 *This method is used to call the get document url api to get the url of the document and send it to Put Document API.
	 *
	 *@param documentJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> getDocumentURL(String documentJSON) {

		Map<String, Object> responseMap = new HashMap<>();
		
		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getDMSGenerateUploadURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForEnach();
			String accessToken = EdelweissAPIUtil.getOAuthToken(true);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, documentJSON);
			}
		
		}catch(Exception exception) {
			logger.error("Error while calling Get Document URL API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	/**
	 *This method is used to call the put document API where response URL of get Document URL api is used.
	 *
	 *@param documentURL
	 *
	 *@return Integer 
	 */
	public static Integer putDocument(String documentURL) {

		try {

			Map<String, Object> responseMap = EdelweissAPIUtil.callPutAPI(documentURL, 
					StringPool.BLANK, false, StringPool.BLANK, StringPool.BLANK, StringPool.BLANK);

			if(Validator.isNotNull(responseMap)) {
				int status = (Integer)responseMap.get("status");
				if(status == 200) {
					return status;
				}
			}
			return null;
		}catch(Exception exception) {
			logger.error("Error while calling Put Document API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return null;
	}

	/**
	 *This method is used to call the Claim Intimation API to submit the claim application using accessToken.
	 *
	 *@param detailsJSON
	 *
	 *@return Map<String, Object> 
	 */
	public static Map<String, Object> claimIntimation(String detailsJSON) {

		Map<String, Object> responseMap = new HashMap<>();

		try {

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getClaimFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			if(Validator.isNotNull(accessToken)) {
				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, 
						EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, detailsJSON);
			}

		}catch(Exception exception) {
			logger.error("Error while calling Claim Intimation API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return responseMap;
	}

	private static Log logger = LogFactoryUtil.getLog(RaiseAClaimApiUtil.class);
}
