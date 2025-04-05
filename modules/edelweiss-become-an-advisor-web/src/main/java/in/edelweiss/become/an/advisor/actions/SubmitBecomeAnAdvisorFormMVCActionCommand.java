package in.edelweiss.become.an.advisor.actions;

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

import in.edelweiss.become.an.advisor.constants.BecomeAnAdvisorPortletKeys;
import in.edelweiss.become.an.advisor.util.BecomeAnAdvisorUtil;
import in.edelweiss.common.contants.ParameterConstants;

/**
 * 
 * @author Abhijit AA
 *
 * This Class is used to Submit the Become An Advisor Request to Contact Detail API.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + BecomeAnAdvisorPortletKeys.BECOMEANADVISOR,
		"mvc.command.name=" + BecomeAnAdvisorPortletKeys.SUBMIT_BECOME_AN_ADVISOR_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitBecomeAnAdvisorFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to submit the user entered details to Become An Advisor API.
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
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String email = ParamUtil.getString(actionRequest, "email");
		String mobileNo = ParamUtil.getString(actionRequest, "mobileNo");
		
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("firstName", firstName);
		detailsJSON.put("lastName", lastName);
		detailsJSON.put("emailId", email);
		detailsJSON.put("phoneNumber", mobileNo);
		detailsJSON.put("byUserId", "1");
		detailsJSON.put("userIp", "0.0.0.0");
		
		logger.debug(detailsJSON);
		
		try 
		{
			if(Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = BecomeAnAdvisorUtil.becomeAnAdvisor(detailsJSON.toString());

				if(Validator.isNotNull(responseMap)) {
					int status = (Integer)responseMap.get("status");
					JSONObject jsonObject = (JSONObject)responseMap.get("jsonObject");
					if(status == 200) {
						
						isFormSubmittedSuccessfully = true;
					
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
			logger.error("Exception occured while submitting become an advisor form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}