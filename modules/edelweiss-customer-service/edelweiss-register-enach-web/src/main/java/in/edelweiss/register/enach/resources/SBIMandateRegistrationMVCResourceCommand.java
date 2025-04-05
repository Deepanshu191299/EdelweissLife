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
 * @author razeena.p
 * This Resource class is used to Call SBI Mandate Registration API.
 */
@Component(immediate = true, property = { "javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"mvc.command.name="
				+ RegisterEnachPortletKeys.SBI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class SBIMandateRegistrationMVCResourceCommand extends BaseMVCResourceCommand {
	
	@Reference
	Portal portal;
	private Log logger = LogFactoryUtil.getLog(this.getClass());
	
	
	/**
	 * This method is used to Call SBI Mandate Registration API.
	 * 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		// TODO Auto-generated method stub
		logger.info("inside the doserve resource of SBIMandate:::");
		String responseData = StringPool.BLANK;
		boolean status = false;
		JSONObject sbiRegstrationReponse = JSONFactoryUtil.createJSONObject();

		try {

			URLRelatedConfiguration urlConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
			boolean restrictSBIUrl = urlConfiguration.getSBIURLRestrictionOn();
			logger.info("restrictSBIUrl::"+ restrictSBIUrl);
			
			if(restrictSBIUrl) {
				logger.info("inside restrictSBIUrl");
			DateFormat targetFormat = new SimpleDateFormat(DateConstants.SLASH_DD_MM_YYYY);
			String startDate = targetFormat.format(new Date());

			String bankBranchIFSCCode = ParamUtil.getString(resourceRequest,"bankBranchIFSCCode");
			String customerName = ParamUtil.getString(resourceRequest,"customerName");
			String policyNumber = ParamUtil.getString(resourceRequest, "polpolicyNosbi");
			String mandateAmount = ParamUtil.getString(resourceRequest, "policymaxAmountsbi");
			String emailId = ParamUtil.getString(resourceRequest, "policyEmailsbi");
			String dateOfBirth = ParamUtil.getString(resourceRequest, "policyDOBsbi");
			String phoneNumber = ParamUtil.getString(resourceRequest, "policyMobilesbi");
			String debitAccountNumber = ParamUtil.getString(resourceRequest, "debitAccountNumbersbi");
			String policyTerm = ParamUtil.getString(resourceRequest, "policyTerm");
			String returnURL = urlConfiguration.getSBIMandateRedirectURL();
			
			logger.info(bankBranchIFSCCode +".."+ customerName+ ".." + policyNumber + mandateAmount + dateOfBirth + debitAccountNumber + policyTerm);
			logger.info("returnURL::::"+ returnURL);
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

			JSONObject sbiMandateJSON = JSONFactoryUtil.createJSONObject();
			sbiMandateJSON.put("policy_number", policyNumber);
			sbiMandateJSON.put("dob", dob);
			sbiMandateJSON.put("mobile", phoneNumber);
			sbiMandateJSON.put("debit_account_number", debitAccountNumber);
			sbiMandateJSON.put("mandate_amount", mandateAmount);
			sbiMandateJSON.put("email", emailId);
			sbiMandateJSON.put("startdate", startDate);
			sbiMandateJSON.put("enddate", endDate); 
			sbiMandateJSON.put("retrun_url", returnURL);
			sbiMandateJSON.put("Customer_Name", customerName);
			sbiMandateJSON.put("IFSC_Code", bankBranchIFSCCode);
			logger.info("sbiMandateJSON"+ sbiMandateJSON);
			
			String encdata="",merchantCode="",endPointURL="";
			
			if (Validator.isNotNull(policyNumber)) {
				Map<String, Object> responseMap = RegisterEnachUtil.sbiMandateRegistration(sbiMandateJSON.toString());
				logger.info("responseMap in SBIMandateRegMVCCoommand" + responseMap);
				
				if (Validator.isNotNull(responseMap)) {
					int statusCode = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					logger.debug("JSON object in SBIMandateResponse:::"+jsonObject);
					if (statusCode == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							status = jsonObject.getBoolean("status");
							responseData = jsonObject.getString("responseData");
							encdata = jsonObject.getJSONObject("responseData").getString("encdata");
							merchantCode = jsonObject.getJSONObject("responseData").getString("merchantCode");
							endPointURL = jsonObject.getJSONObject("responseData").getString("url");
							logger.info("ENCDATA" + encdata);
							
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			sbiRegstrationReponse.put("status", status);
			sbiRegstrationReponse.put("source", "sbi");
			sbiRegstrationReponse.put("encdata", encdata);
			sbiRegstrationReponse.put("merchantCode", merchantCode);
			sbiRegstrationReponse.put("endPointURL", endPointURL);
			sbiRegstrationReponse.put("responseData", responseData);
			
			/*  if(Validator.isNotNull(encdata)) {
				Map<String, Object> responseMandateMap = RegisterEnachUtil.sbiMandateResponseRegistration(sbiRegstrationReponse.toString());
				logger.info("Newcheck1" + responseMandateMap);
			} */
		}
		else {
			sbiRegstrationReponse.put("status", false);
		}
	}

		catch (Exception exception) {
			logger.error("Error while calling SBI Mandate  resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		resourceResponse.getWriter().write(sbiRegstrationReponse.toString());
	}

}
