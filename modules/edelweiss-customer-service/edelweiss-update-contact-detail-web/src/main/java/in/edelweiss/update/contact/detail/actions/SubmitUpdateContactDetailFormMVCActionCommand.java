package in.edelweiss.update.contact.detail.actions;

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

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.update.contact.detail.constants.UpdateContactDetailPortletKeys;
import in.edelweiss.update.contact.detail.util.UpdateContactDetailUtil;

/**
 * 
 * @author Abhijit Ande
 *
 * This Class is used to Submit the Update Contact Detail Request to Contact Detail API.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + UpdateContactDetailPortletKeys.UPDATECONTACTDETAIL,
		"mvc.command.name=" + UpdateContactDetailPortletKeys.SUBMIT_UPDATE_CONTACT_DETAIL_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitUpdateContactDetailFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to submit the user entered details to Contact Detail API.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isFormSubmittedSuccessfully = false;
		String responseData = StringPool.BLANK;

		//User Submitted Form Details.
		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String policyNumber = ParamUtil.getString(actionRequest, "policyNumber");
		String email = ParamUtil.getString(actionRequest, "email");
		String mobileNo = ParamUtil.getString(actionRequest, "mobileNo");
		String nomineeName = ParamUtil.getString(actionRequest, "nomineeName");
		String dateOfBirth = ParamUtil.getString(actionRequest, "dateOfBirth");
		String sumAssured = ParamUtil.getString(actionRequest, "sumAssured");
		
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("fullName", fullName);
		detailsJSON.put("policyNumber", policyNumber);
		detailsJSON.put("emailId", email);
		detailsJSON.put("phoneNumber", mobileNo);
		detailsJSON.put("nomineeName", nomineeName);
		detailsJSON.put("dob", dateOfBirth);
		detailsJSON.put("sumAssured", sumAssured);
		logger.debug(detailsJSON);
		
		try 
		{
			if(Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = UpdateContactDetailUtil.updateContactDetail(detailsJSON.toString());

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");

					if(status == 200) {
						if(Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							boolean jsonStatus = jsonObject.getBoolean("status");

							if(jsonStatus == true) {
								responseData = jsonObject.getString("responseData");
								isFormSubmittedSuccessfully = true;
							}
						}
					}else if(Validator.isNotNull(jsonObject) && jsonObject.has("errors")) {
						responseData = jsonObject.getString("errors");
					}else if(Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);
			
		}catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting update contact detail form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}