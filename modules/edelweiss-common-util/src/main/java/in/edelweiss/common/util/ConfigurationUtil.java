package in.edelweiss.common.util;

import com.liferay.petra.string.StringPool;

import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.List;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.system.configurations.ApiKeyConfiguration;
import in.edelweiss.system.configurations.ApiURLConfiguration;
import in.edelweiss.system.configurations.AppTrackerAPIURLConfiguration;
import in.edelweiss.system.configurations.CommonConfiguration;
import in.edelweiss.system.configurations.ContactUsConfiguration;
import in.edelweiss.system.configurations.EdelweissGSTINOAuthConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.system.configurations.EdelweissWebengageURLConfiguration;
import in.edelweiss.system.configurations.OAuthCredentialsConfiguration;
import in.edelweiss.system.configurations.OOTBReferenceCodeConfiguration;
import in.edelweiss.system.configurations.ProposalFormAPIURLConfiguration;
import in.edelweiss.system.configurations.TestimonialConfiguration;
import in.edelweiss.system.configurations.URLFilterConfiguration;
import in.edelweiss.system.configurations.URLRelatedConfiguration;

/**
 * This class contains the code related to systme configuration module.
 *
 * @author Abhijit AA
 */
public class ConfigurationUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private ConfigurationUtil() {

	}


	/**
	 *This method is used to get ApiKeyConfiguration Object to fetch config values.
	 *
	 *@return ApiKeyConfiguration
	 */
	public static ApiKeyConfiguration getApiKeyConfiguration(){
		ApiKeyConfiguration apiKeyConfiguration = null;
		try {
			apiKeyConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(ApiKeyConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting ApiKeyConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return apiKeyConfiguration;
	}

	/**
	 *This method is used to get CommonConfiguration Object to fetch config values.
	 *
	 *@return CommonConfiguration
	 */
	public static CommonConfiguration getCommonConfiguration(){
		CommonConfiguration commonConfiguration = null;
		try {
			commonConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(CommonConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting commonConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return commonConfiguration;
	}
	
	/**
	 *This method is used to get URLFilterConfiguration Object to fetch config values.
	 *
	 *@return urlFilterConfiguration
	 */
	public static URLFilterConfiguration getURLFilterConfiguration(){
		URLFilterConfiguration urlFilterConfiguration = null;
		try {
			urlFilterConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(URLFilterConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting URLFilterConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return urlFilterConfiguration;
	}

	/**
	 *This method is used to get ContactUsConfiguration Object to fetch config values.
	 *
	 *@return contactUsConfiguration
	 */
	public static ContactUsConfiguration getContactUsConfiguration(){
		ContactUsConfiguration contactUsConfiguration = null;
		try {
			contactUsConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(ContactUsConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting ContactUsConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return contactUsConfiguration;
	}

	/**
	 *This method is used to get OAuthCredentialsConfiguration Object to fetch config values.
	 *
	 *@return oAuthCredentialsConfiguration
	 */
	public static OAuthCredentialsConfiguration getOAuthCredentialsConfiguration(){
		OAuthCredentialsConfiguration oAuthCredentialsConfiguration = null;
		try {
			oAuthCredentialsConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(OAuthCredentialsConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting OAuthCredentialsConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return oAuthCredentialsConfiguration;
	}

	/**
	 *This method is used to get OOTBReferenceCodeConfiguration Object to fetch config values.
	 *
	 *@return OOTBReferenceCodeConfiguration
	 */
	public static OOTBReferenceCodeConfiguration getOOTBReferenceCodeConfiguration(){
		OOTBReferenceCodeConfiguration ootbReferenceCodeConfiguration = null;
		try {
			ootbReferenceCodeConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(OOTBReferenceCodeConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting OOTBReferenceCodeConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return ootbReferenceCodeConfiguration;
	}

	/**
	 *This method is used to get URLRelatedConfiguration Object to fetch config values.
	 *
	 *@return URLRelatedConfiguration
	 */
	public static URLRelatedConfiguration getURLRelatedConfiguration(){
		URLRelatedConfiguration urlRelatedConfiguration = null;
		try {
			urlRelatedConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(URLRelatedConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting URLRelatedConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return urlRelatedConfiguration;
	}

	/**
	 *This method is used to get EdelweissLRBasicAuthConfiguration Object to fetch config values.
	 *
	 *@return EdelweissLRBasicAuthConfiguration
	 */
	public static EdelweissLRBasicAuthConfiguration getEdelweissLRBasicAuthConfiguration(){
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = null;
		try {
			edelweissLRBasicAuthConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(EdelweissLRBasicAuthConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting edelweissLRBasicAuthConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return edelweissLRBasicAuthConfiguration;
	}

	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return TestimonialConfiguration
	 */
	public static TestimonialConfiguration getTestimonialConfiguration(){
		TestimonialConfiguration testimonialConfiguration = null;
		try {
			testimonialConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(TestimonialConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting testimonialConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return testimonialConfiguration;
	}
	
	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return ApiURLConfiguration
	 */
	public static ApiURLConfiguration getApiURLConfiguration(){
		ApiURLConfiguration apiURLConfiguration = null;
		try {
			apiURLConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(ApiURLConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting ApiURLConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return apiURLConfiguration;
	}
	
	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return AppTrackerAPIURLConfiguration
	 */
	public static AppTrackerAPIURLConfiguration getAppTrackerAPIURLConfiguration(){
		AppTrackerAPIURLConfiguration appTrackerAPIURLConfiguration = null;
		try {
			appTrackerAPIURLConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(AppTrackerAPIURLConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting AppTrackerAPIURLConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return appTrackerAPIURLConfiguration;
	}
	
	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return ProposalFormAPIURLConfiguration
	 */
	public static ProposalFormAPIURLConfiguration getProposalFormAPIURLConfiguration(){
		ProposalFormAPIURLConfiguration proposalFormAPIURLConfiguration = null;
		try {
			proposalFormAPIURLConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(ProposalFormAPIURLConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting ProposalFormAPIURLConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return proposalFormAPIURLConfiguration;
	}

	/**
	 *This method is used to get Document Extensions from CommonConfiguration.
	 *
	 *@return String
	 */
	public static String getDocumentExtensions(){
		CommonConfiguration commonConfiguration = getCommonConfiguration();
		if(commonConfiguration != null) {
			String documentExtension = commonConfiguration.getDocumentExtensions();
			return StringUtil.merge(Arrays.asList(documentExtension.split(",")), 
					StringPool.COMMA_AND_SPACE);
		}
		return StringPool.BLANK;
	}

	/**
	 *This method is used to get Soft Login URL which requires OTP from CommonConfiguration & 
	 *check whether the method parameter url is present or not.
	 *
	 *@return boolean
	 */
	public static boolean getSoftLoginURL(String url) {
		URLRelatedConfiguration urlRelatedConfiguration = getURLRelatedConfiguration();
		if(urlRelatedConfiguration != null) {
			String softLoginURLs = urlRelatedConfiguration.getSoftLoginRequiredURL();
			List<String> urlList = Arrays.asList(softLoginURLs.split(","));

			if(Validator.isNotNull(urlList) && !urlList.isEmpty() && urlList.contains(url)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *This method is used to get Soft Login URL from CommonConfiguration & 
	 *check whether the method parameter url is present or not.
	 *
	 *@return boolean
	 */
	public static boolean getSoftLoginOTPURL(String url) {
		URLRelatedConfiguration urlRelatedConfiguration = getURLRelatedConfiguration();
		if(urlRelatedConfiguration != null) {
			String softLoginURLs = urlRelatedConfiguration.getSoftLoginOTPRequiredURL();
			List<String> urlList = Arrays.asList(softLoginURLs.split(","));

			if(Validator.isNotNull(urlList) && !urlList.isEmpty() && urlList.contains(url)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *This method is used to get URL Pattern from CommonConfiguration & 
	 *check whether the method parameter url is present or not.
	 *
	 *@return boolean
	 */
	public static boolean getIsURLRestricted(String url) {
		URLFilterConfiguration urlFilterConfiguration = getURLFilterConfiguration();
		if(urlFilterConfiguration != null) {
			String[] urlPattern = urlFilterConfiguration.getURLRestrictPattern();
			List<String> patternList = Arrays.asList(urlPattern);

			if(Validator.isNotNull(patternList) && !patternList.isEmpty()) {
				for(String pattern : patternList) {
					if(Validator.isNotNull(url) && Validator.isNotNull(pattern) && 
					   !pattern.isBlank() && !pattern.isEmpty() && url.contains(pattern)) {
						logger.debug("Matched : "+pattern);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return EdelweissGSTINOAuthConfiguration
	 */
	public static EdelweissGSTINOAuthConfiguration getEdelweissGSTINOAuthConfiguration(){
		EdelweissGSTINOAuthConfiguration edelweissGSTINOAuthConfiguration = null;
		try {
			edelweissGSTINOAuthConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(EdelweissGSTINOAuthConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting EdelweissGSTINOAuthConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return edelweissGSTINOAuthConfiguration;
	}
	
	/**
	 *This method is used to get Life Object to fetch config values.
	 *
	 *@return EdelweissWebengageURLConfiguration
	 */
	public static EdelweissWebengageURLConfiguration getEdelweissWebengageURLConfiguration(){
		EdelweissWebengageURLConfiguration edelweissWebengageURLConfiguration = null;
		try {
			edelweissWebengageURLConfiguration = 
					ConfigurationProviderUtil.getSystemConfiguration(EdelweissWebengageURLConfiguration.class);
		} catch (Exception exception) {
			logger.error("Error while getting edelweissWebengageURLConfiguration : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return edelweissWebengageURLConfiguration;
	}
	private static Log logger = LogFactoryUtil.getLog(ConfigurationUtil.class);
}

