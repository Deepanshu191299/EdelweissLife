package in.edelweiss.resume.application.actions;

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
import in.edelweiss.resume.application.constants.ResumeApplicationPortletKeys;
import in.edelweiss.resume.application.util.ResumeApplicationUtil;

/**
 * 
 * @author Abhijit Ande
 *
 * This Class is used to Submit the Resume Application Request to Resume API.
 */

@Component(immediate = true,
property = {
		"javax.portlet.name=" + ResumeApplicationPortletKeys.RESUMEAPPLICATION,
		"mvc.command.name=" + ResumeApplicationPortletKeys.SUBMIT_RESUME_APPLICATION_FORM_MVC_ACTION_COMMAND 
},
service = MVCActionCommand.class
		)
public class SubmitResumeApplicationFormMVCActionCommand extends BaseMVCActionCommand {

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
		
		String responseData = StringPool.BLANK;
		String redirectURL = StringPool.BLANK;
		boolean isURLFetched = false;
		
		//User Submitted Form Details.
		String policyNumber = ParamUtil.getString(actionRequest, "policyNumber");
		String dateOfBirth = ParamUtil.getString(actionRequest, "dateOfBirth");
	
		try {

			if (Validator.isNotNull(policyNumber) && Validator.isNotNull(dateOfBirth)) {

				Map<String, Object> responseMap = ResumeApplicationUtil.resumeApplication(policyNumber, dateOfBirth);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
					
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && (jsonObject.has("status"))) {
							boolean resStatus = jsonObject.getBoolean("status");
							
							if(resStatus) {
								isURLFetched = true;
								redirectURL = jsonObject.getString("responseData");
							}else {
								responseData = jsonObject.getJSONArray("errors").getString(0);
							}
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {
						responseData = jsonObject.getString("message");
					}
				}
			}

			
			formSubmissionReponse.put("isURLFetched", isURLFetched);
			formSubmissionReponse.put("redirectURL", redirectURL);
			formSubmissionReponse.put("responseData", responseData);
			
		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Error while calling Resume Application Action Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}