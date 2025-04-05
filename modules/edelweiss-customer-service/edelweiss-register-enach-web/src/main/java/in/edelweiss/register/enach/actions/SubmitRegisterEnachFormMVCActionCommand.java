package in.edelweiss.register.enach.actions;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys;
import in.edelweiss.register.enach.util.RegisterEnachUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Class is used to Submit the Register Enach Form to Mandate API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"mvc.command.name="
				+ RegisterEnachPortletKeys.SUBMIT_REGISTER_ENACH_FORM_MVC_ACTION_COMMAND }, service = MVCActionCommand.class)
public class SubmitRegisterEnachFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to submit the user entered details to Claim Intimation
	 * API.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		logger.info("inside doProcessAction of SubmitRegisterEnachForm::::");
		
		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isFormSubmittedSuccessfully = false;
		String responseData = StringPool.BLANK;
		String mandateId = StringPool.BLANK;
		String authBy = StringPool.BLANK;

		DateFormat targetFormat = new SimpleDateFormat(DateConstants.SLASH_DD_MM_YYYY);
		String startDate = targetFormat.format(new Date());

		// User Submitted Form Details.
		JSONObject customerJSONObject = JSONFactoryUtil
				.createJSONObject(ParamUtil.getString(actionRequest, "customerJSONObject"));
		String policyNumber = customerJSONObject.getString("policyNumber");
		String authenticationMode = ParamUtil.getString(actionRequest, policyNumber + "authenticationMode");
		String factorAuthentication = ParamUtil.getString(actionRequest, policyNumber + "factorAuthentication");
		String bankBranchIFSCCode = ParamUtil.getString(actionRequest, policyNumber + "bankBranchIFSCCode");
		String authenticationType = ParamUtil.getString(actionRequest, policyNumber + "authenticationType");
		String bankName = ParamUtil.getString(actionRequest, policyNumber + "bankName");
		
		logger.debug("RegisterEnach Sumbit Clicked::: "+ policyNumber );
		
		// LDXP-722
		String customerName = customerJSONObject.get("firstName") + " "+ customerJSONObject.get("middleName") + " "+ customerJSONObject.get("lastName");
		logger.debug("CustomerName = "+customerName);
		if (customerName.contains("null")) {
			logger.debug("ClientName = "+customerJSONObject.getString("clientName"));
			customerName = customerJSONObject.getString("clientName");
		}
		
		double maxAmount = Double.parseDouble(ParamUtil.getString(actionRequest, "maxAmount"));
		
		String contractIssueDate = customerJSONObject.getString("contractIssueDate");
		if(Validator.isNotNull(contractIssueDate)) {
			contractIssueDate = DateFormatterUtil.parseDateToSpecificFormat(contractIssueDate, DateConstants.SLASH_DD_MM_YYYY);
		}else {
			contractIssueDate = DateFormatterUtil.parseDateToSpecificFormat("01/01/1970", DateConstants.SLASH_DD_MM_YYYY);
		}
		
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("policyNo", policyNumber);
		detailsJSON.put("dob", DateFormatterUtil.parseDateToSpecificFormat(customerJSONObject.getString("dob"), DateConstants.SLASH_DD_MM_YYYY));
		detailsJSON.put("email", customerJSONObject.get("emailId"));
		detailsJSON.put("mobile", customerJSONObject.get("phoneNumber"));
		detailsJSON.put("maxAmount", maxAmount);
		detailsJSON.put("debitFrequency", customerJSONObject.get("frequency"));
		detailsJSON.put("expireinDays", 30);
		detailsJSON.put("type", "create-api");
		detailsJSON.put("destinationBank", bankName);
		detailsJSON.put("instrumentType", "debit");
		detailsJSON.put("frequency", "ADHO");
		detailsJSON.put("startDate", startDate);
		detailsJSON.put("customerName", customerName);
		detailsJSON.put("ifscCode", bankBranchIFSCCode);
		detailsJSON.put("issuedDate", contractIssueDate);
		detailsJSON.put("modalPremium", customerJSONObject.get("premium"));
		detailsJSON.put("planId", customerJSONObject.get("planId"));
		detailsJSON.put("identifier", factorAuthentication);
		detailsJSON.put("authMode", authenticationMode);
		detailsJSON.put("authType", authenticationType);
		logger.info("RequestBody for submitRegisterEnach::::::: "+ detailsJSON.toString());

		try {
			if (Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = RegisterEnachUtil.registerEnach(detailsJSON.toString());
				logger.info("ResponseMap in SubmitRegister:::" + responseMap);
				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					logger.info("status here::"+ status);
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					logger.info("jsonObject..."+jsonObject);
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							boolean jsonStatus = jsonObject.getBoolean("status");
							if (jsonStatus == true) {
								isFormSubmittedSuccessfully = true;
								 JSONObject responseObj = jsonObject.getJSONObject("responseData");
								 if(Validator.isNotNull(responseObj) && responseObj.has("mandateID")) {
									 mandateId = responseObj.getString("mandateID");
									 logger.debug("SubmitRegisterEnach is Success::: " + policyNumber + "mandateId:: " +mandateId);
									 authBy = responseObj.getString("authBy");
								 }
							}else if (Validator.isNotNull(jsonObject) && jsonObject.has("errors")) {
								logger.debug("SubmitRegisterEnach is Failed:::" + policyNumber);
								 responseData = jsonObject.getJSONArray("errors").toString();
							}
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						logger.debug("SubmitRegisterEnach is Failed::: " + policyNumber);
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("policyNumber", policyNumber);
			formSubmissionReponse.put("mandateId", mandateId);
			formSubmissionReponse.put("authBy", authBy);
			formSubmissionReponse.put("responseData", responseData);
			logger.info("formSubmissionReponse......"+ formSubmissionReponse);
			
		} catch (Exception exception) {
			logger.debug("Internal Error::SubmitRegisterEnachFormMVCActionCommand:::");
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting register enach form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}