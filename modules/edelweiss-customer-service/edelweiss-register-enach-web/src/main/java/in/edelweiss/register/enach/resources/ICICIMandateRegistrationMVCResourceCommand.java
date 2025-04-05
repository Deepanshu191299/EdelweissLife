package in.edelweiss.register.enach.resources;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys;
import in.edelweiss.register.enach.util.RegisterEnachUtil;
import in.edelweiss.system.configurations.URLRelatedConfiguration;

/**
 * 
 * @author Akash 
 *
 *         This Resource class is used to Call ICICI Mandate Registration API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"mvc.command.name="
				+ RegisterEnachPortletKeys.ICICI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class ICICIMandateRegistrationMVCResourceCommand  extends BaseMVCResourceCommand {

	@Reference
	Portal portal;
	private Log logger = LogFactoryUtil.getLog(this.getClass());

	/**
	 * This method is used to Call ICICI Mandate Registration API.
	 * 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		String responseData = StringPool.BLANK;
		boolean status = false;
		JSONObject iciciRegstrationReponse = JSONFactoryUtil.createJSONObject();

		try {

			URLRelatedConfiguration urlConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
			boolean restrictICICIUrl = urlConfiguration.getICICIURLRestrictionOn();
			if(restrictICICIUrl) {
				
			DateFormat targetFormat = new SimpleDateFormat(DateConstants.SLASH_DD_MM_YYYY);
			String startDate = targetFormat.format(new Date());

			String bankBranchIFSCCode = ParamUtil.getString(resourceRequest,"bankBranchIFSCCode");
			String customerName = ParamUtil.getString(resourceRequest,"customerName");
			String policyNumber = ParamUtil.getString(resourceRequest, "polpolicyNo");
			String mandateAmount = ParamUtil.getString(resourceRequest, "policymaxAmount");
			String emailId = ParamUtil.getString(resourceRequest, "policyEmail");
			String dateOfBirth = ParamUtil.getString(resourceRequest, "policyDOB");
			String phoneNumber = ParamUtil.getString(resourceRequest, "policyMobile");
			String debitAccountNumber = ParamUtil.getString(resourceRequest, "debitAccountNumber");
			String policyTerm = ParamUtil.getString(resourceRequest, "policyTerm");
			String returnURL = urlConfiguration.getICICIMandateRedirectURL();

			// Get the current date
			Date currentDate = new Date();

			// Create a Calendar instance and set it to the current date
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);

			// Add policy Term years
			calendar.add(Calendar.YEAR, Integer.parseInt(policyTerm));

			// Get the End date
			String endDate =  targetFormat.format(calendar.getTime());
			String dob =   DateFormatterUtil.parseDateToSpecificFormat(dateOfBirth, DateConstants.SLASH_DD_MM_YYYY);

			JSONObject iciciMandateJSON = JSONFactoryUtil.createJSONObject();
			iciciMandateJSON.put("policy_number", policyNumber);
			iciciMandateJSON.put("dob", dob);
			iciciMandateJSON.put("mobile", phoneNumber);
			iciciMandateJSON.put("debit_account_number", debitAccountNumber);
			iciciMandateJSON.put("mandate_amount", mandateAmount);
			iciciMandateJSON.put("email", emailId);
			iciciMandateJSON.put("startdate", startDate);
			iciciMandateJSON.put("enddate", endDate); 
			iciciMandateJSON.put("retrun_url", returnURL);
			iciciMandateJSON.put("Customer_Name", customerName);
			iciciMandateJSON.put("IFSC_Code", bankBranchIFSCCode);

			if (Validator.isNotNull(policyNumber)) {

				Map<String, Object> responseMap = RegisterEnachUtil.iciciMandateRegistration(iciciMandateJSON.toString());

				if (Validator.isNotNull(responseMap)) {
					
					int statusCode = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					logger.debug(jsonObject);
					
					if (statusCode == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							status = jsonObject.getBoolean("status");
							responseData = jsonObject.getString("responseData");
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			iciciRegstrationReponse.put("status", status);
			iciciRegstrationReponse.put("responseData", responseData);
		}
		else {
			iciciRegstrationReponse.put("status", false);
		}
	}

		catch (Exception exception) {
			logger.error("Error while calling ICICI Mandate  resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		resourceResponse.getWriter().write(iciciRegstrationReponse.toString());


	}

}