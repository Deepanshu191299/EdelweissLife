package in.edelweiss.tokio.common.util;

import com.liferay.portal.kernel.log.Log;

import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;

import in.edelweiss.system.configurations.CommonConfiguration;
import in.edelweiss.system.configurations.EdelweissBJCagrConfiguration;
import in.edelweiss.system.configurations.EdelweissBJGenerateBIConfiguration;
import in.edelweiss.system.configurations.EdelweissBJGeneratePolicyNoConfiguration;
import in.edelweiss.system.configurations.EdelweissBJProductListConfiguration;
import in.edelweiss.system.configurations.EdelweissBJProductModificationConfiguration;
import in.edelweiss.system.configurations.EdelweissBJRestartJourneyConfiguration;
import in.edelweiss.system.configurations.EdelweissGenerateApplicationNoConfiguration;
import in.edelweiss.system.configurations.EdelweissGenerateLeadConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.system.configurations.EdelweissOauth2GenerateTokenConfiguration;
import in.edelweiss.system.configurations.EdelweissRPGenerateBIComboConfiguration;
import in.edelweiss.system.configurations.EdelweissRPGenerateBIConfiguration;
import in.edelweiss.system.configurations.EdelweissUpdateLeadConfiguration;
import in.edelweiss.system.configurations.EdelweissWebengageURLConfiguration;
import in.edelweiss.system.configurations.ProposalFormAPIURLConfiguration;

/**
 * This class contains the code related to systm configuration module.
 */
public class EdelweissConfigurationUtil {
	
	private static Log log = LogFactoryUtil.getLog(EdelweissConfigurationUtil.class);

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private EdelweissConfigurationUtil() {

	}
	
	
	/**
	 * This method is used to get Liferay Basic Authentication Username and Password by Configuration for Calling Liferay OOB Object Headless Serices
	 *
	 * @return edelweissLRBasicAuthConfiguration
	 */
	public static EdelweissLRBasicAuthConfiguration getEdelweissLRBasicAuthConfiguration() {
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = null;
		try {
			edelweissLRBasicAuthConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissLRBasicAuthConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJCagrConfiguration >>>> Error while getting EdelweissLRBasicAuthConfiguration :  " + ex);
		}
		return edelweissLRBasicAuthConfiguration;
	
	}
	
	/**
	 * This method is used to get Edelweiss Buy Journey Cagr API Service URL and x-api-key by Configuration
	 * 
	 * @return edelweissBJCagrConfiguration
	 */
	public static EdelweissBJCagrConfiguration getEdelweissBJCagrConfiguration() {
		EdelweissBJCagrConfiguration edelweissBJCagrConfiguration = null;
		try {
			edelweissBJCagrConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJCagrConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJCagrConfiguration >>>> Error while getting EdelweissBJCagrConfiguration :  " + ex);
		}
		return edelweissBJCagrConfiguration;
	}
	
	
	/**
	 * This method is used to get Edelweiss Retail Partner Generate BI API Service URL and x-api-key by Configuration
	 * 
	 * @return edelweissRPGenerateBIConfiguration
	 */
	public static EdelweissRPGenerateBIConfiguration getEdelweissRPGenerateBIConfiguration() {
		EdelweissRPGenerateBIConfiguration edelweissRPGenerateBIConfiguration = null;
		try {
			edelweissRPGenerateBIConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissRPGenerateBIConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissRPGenerateBIConfiguration >>>> Error while getting EdelweissRPGenerateBIConfiguration :  " + ex);
		}
		return edelweissRPGenerateBIConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss OAuth2 Token Generation API Service URL, Username and password by Configuration
	 * 
	 * @return edelweissOauth2GenerateTokenConfiguration
	 */
	public static EdelweissOauth2GenerateTokenConfiguration getEdelweissOauth2GenerateTokenConfiguration() {
		EdelweissOauth2GenerateTokenConfiguration edelweissOauth2GenerateTokenConfiguration = null;
		try {
			edelweissOauth2GenerateTokenConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissOauth2GenerateTokenConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissOauth2GenerateTokenConfiguration >>>> Error while getting EdelweissOauth2GenerateTokenConfiguration :  " + ex);
		}
		return edelweissOauth2GenerateTokenConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss Update LMS Lead API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissUpdateLeadConfiguration
	 */
	public static EdelweissUpdateLeadConfiguration getEdelweissUpdateLeadConfiguration() {
		EdelweissUpdateLeadConfiguration edelweissUpdateLeadConfiguration = null;
		try {
			edelweissUpdateLeadConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissUpdateLeadConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissUpdateLeadConfiguration >>>> Error while getting EdelweissUpdateLeadConfiguration :  " + ex);
		}
		return edelweissUpdateLeadConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss LMS Lead Generate URL API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissGenerateLeadConfiguration
	 */
	public static EdelweissGenerateLeadConfiguration getEdelweissGenerateLeadConfiguration() {
		EdelweissGenerateLeadConfiguration edelweissGenerateLeadConfiguration = null;
		try {
			edelweissGenerateLeadConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissGenerateLeadConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissGenerateLeadConfiguration >>>> Error while getting EdelweissGenerateLeadConfiguration :  " + ex);
		}
		return edelweissGenerateLeadConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss Generate Policy Number API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissBJGeneratePolicyNoConfiguration
	 */
	public static EdelweissBJGeneratePolicyNoConfiguration getEdelweissBJGeneratePolicyNoConfiguration() {
		EdelweissBJGeneratePolicyNoConfiguration edelweissBJGeneratePolicyNoConfiguration = null;
		try {
			edelweissBJGeneratePolicyNoConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJGeneratePolicyNoConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJGeneratePolicyNoConfiguration >>>> Error while getting EdelweissBJGeneratePolicyNoConfiguration :  " + ex);
		}
		return edelweissBJGeneratePolicyNoConfiguration;
	}
	
	
	/**
	 * This method is used to get Edelweiss Generate Application Number API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissGenerateApplicationNoConfiguration
	 */
	public static EdelweissGenerateApplicationNoConfiguration getEdelweissGenerateApplicationNoConfiguration() {
		EdelweissGenerateApplicationNoConfiguration edelweissGenerateApplicationNoConfiguration = null;
		try {
			edelweissGenerateApplicationNoConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissGenerateApplicationNoConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJGeneratePolicyNoConfiguration >>>> Error while getting EdelweissGenerateApplicationNoConfiguration :  " + ex);
		}
		return edelweissGenerateApplicationNoConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss Buy Journey Generate BI API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissBJGenerateBIConfiguration
	 */
	public static EdelweissBJGenerateBIConfiguration getEdelweissBJGenerateBIConfiguration() {
		EdelweissBJGenerateBIConfiguration edelweissBJGenerateBIConfiguration = null;
		try {
			edelweissBJGenerateBIConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJGenerateBIConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJGenerateBIConfiguration >>>> Error while getting EdelweissBJGenerateBIConfiguration :  " + ex);
		}
		return edelweissBJGenerateBIConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss Restart Journey of Buy Journey API Service URL, x-api-key by Configuration
	 * 
	 * @return edelweissBJGenerateBIConfiguration
	 */
	public static EdelweissBJRestartJourneyConfiguration getEdelweissBJRestartJourneyConfiguration() {
		EdelweissBJRestartJourneyConfiguration edelweissBJRestartJourneyConfiguration = null;
		try {
			edelweissBJRestartJourneyConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJRestartJourneyConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJGenerateBIConfiguration >>>> Error while getting EdelweissBJRestartJourneyConfiguration :  " + ex);
		}
		return edelweissBJRestartJourneyConfiguration;
	}
	
	/**
	 * This method is used to get Edelweiss Retail Partner Generate BI Combo API Service URL and x-api-key by Configuration
	 * 
	 * @return edelweissRPGenerateBIComboConfiguration
	 */
	public static EdelweissRPGenerateBIComboConfiguration getEdelweissRPGenerateBIComboConfiguration() {
		EdelweissRPGenerateBIComboConfiguration edelweissRPGenerateBIComboConfiguration = null;
		try {
			edelweissRPGenerateBIComboConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissRPGenerateBIComboConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissRPGenerateBIComboConfiguration >>>> Error while getting EdelweissRPGenerateBIComboConfiguration :  " + ex);
		}
		return edelweissRPGenerateBIComboConfiguration;
	}
	
	
	/**
	 * This method is used to get Edelweiss Buy Journey Product List API Service URL and x-api-key by Configuration
	 * 
	 * @return edelweissBJProductListConfig
	 */
	public static EdelweissBJProductListConfiguration getEdelweissBJProductListConfiguration() {
		EdelweissBJProductListConfiguration edelweissBJProductListConfig = null;
		try {
			edelweissBJProductListConfig = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJProductListConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJProductListConfiguration >>>> Error while getting EdelweissBJProductListConfiguration :  " + ex);
		}
		return edelweissBJProductListConfig;
	}
	
	
	/**
	 * This method is used to get Edelweiss Buy Journey Product Modification API Service URL and x-api-key by Configuration
	 * 
	 * @return edelweissBJProductModificationConfig
	 */
	public static EdelweissBJProductModificationConfiguration getEdelweissBJProductModificationConfiguration() {
		EdelweissBJProductModificationConfiguration edelweissBJProductModificationConfig = null;
		try {
			edelweissBJProductModificationConfig = ConfigurationProviderUtil.getSystemConfiguration(EdelweissBJProductModificationConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissBJProductModificationConfiguration >>>> Error while getting EdelweissBJProductModificationConfiguration :  " + ex);
		}
		return edelweissBJProductModificationConfig;
	}
	
	/**
	 * This method is used to get Edelweiss common configurations
	 * 
	 * @return CommonConfiguration
	 */
	public static CommonConfiguration getCommonConfiguration() {
		CommonConfiguration commonConfiguration = null;
		try {
			commonConfiguration = ConfigurationProviderUtil.getSystemConfiguration(CommonConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getCommonConfiguration >>>> Error while getting CommonConfiguration :  " + ex);
		}
		return commonConfiguration;
	}
	
	public static ProposalFormAPIURLConfiguration getProposalFormAPIURLConfiguration(){
		ProposalFormAPIURLConfiguration proposalFormAPIURLConfiguration = null;
		try {
			proposalFormAPIURLConfiguration = ConfigurationProviderUtil.getSystemConfiguration(ProposalFormAPIURLConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getProposalFormAPIURLConfiguration >>>> Error while getting CommonConfiguration :  " + ex);
		}
		return proposalFormAPIURLConfiguration;
	}
	
	public static EdelweissWebengageURLConfiguration getEdelweissWebengageURLConfiguration(){
		EdelweissWebengageURLConfiguration edelweissWebengageURLConfiguration = null;
		try {
			edelweissWebengageURLConfiguration = ConfigurationProviderUtil.getSystemConfiguration(EdelweissWebengageURLConfiguration.class);
		} catch (Exception ex) {
			log.error("EdelweissConfigurationUtil >>>> getEdelweissWebengageURLConfiguration >>>> Error while getting CommonConfiguration :  " + ex);
		}
		return edelweissWebengageURLConfiguration;
	}
}