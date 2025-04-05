package in.edelweiss.renewal.policy.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.ApiKeyConfiguration;
import in.edelweiss.system.configurations.ApiURLConfiguration;

public class RenewalPolicyApiUtil {

	public static Map<String, Object> getCustomerData(String id, String dob, String idType) {

		// JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Map<String, Object> responseMap = new HashMap<>();
		Map<String, String> params = new HashMap<>();
		try {
			params.put("dob", dob);
			params.put(idType, id);
			params.put("requestType", "renewal");

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getCustomerPolicyDataURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			if (Validator.isNotNull(accessToken)) {

				responseMap = EdelweissAPIUtil.callGetAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, params);
			}

			return responseMap;
		} catch (Exception exception) {
			logger.error("Error while calling Get Customer Data URL API : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return responseMap;
	}

	/**
	 * Check whether The date is before or after a date
	 * 
	 * @param dueDateStr
	 * @return boolean
	 */
	public static boolean isPolicyExpired(String dueDateStr, String planType) {

		try {
			int days = 181;
			logger.debug("planType>>>>>"+planType);
			Date currentDate = new Date();
			Date dueDate = DateFormatterUtil.parseDate(dueDateStr);
			logger.debug("dueDate>>>>>"+dueDate);
			Calendar minCalDate = Calendar.getInstance();
			minCalDate.setTime(dueDate);
			minCalDate.add(Calendar.DATE, -91);
			Date minDate = minCalDate.getTime();
			logger.debug("minDate>>>>>"+minDate);
			
			Calendar maxCalDate = Calendar.getInstance();
			maxCalDate.setTime(dueDate);
		 
			if(Validator.isNotNull(planType) && (planType.equalsIgnoreCase("Term") 
						|| planType.equalsIgnoreCase("Par") || planType.equalsIgnoreCase("Non Par"))){
				days = 31;	
			}else if(Validator.isNotNull(planType) && (planType.equalsIgnoreCase("ULIP") 
					|| planType.equalsIgnoreCase("Non Term") || planType.equalsIgnoreCase("Group"))) {
				days = 181;
			}
			
			maxCalDate.add(Calendar.DATE, days);
			Date maxDate = maxCalDate.getTime();
			logger.debug(">>>>>>>>Day added"+days+" maxDate>>>>>"+maxDate);
			if (currentDate.after(minDate) && currentDate.before(maxDate)) {
				return false;
			}
			return true;
			
		}catch(Exception exception) {
			logger.error("Error while checking due date of policy : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return true;
	}

	private static Log logger = LogFactoryUtil.getLog(RenewalPolicyApiUtil.class);
}
