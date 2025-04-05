package in.edelweiss.claim.tracking.actions;

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

import in.edelweiss.claim.tracking.constants.ClaimTrackingPortletKeys;
import in.edelweiss.claim.tracking.util.ClaimTrackingApiUtil;
import in.edelweiss.common.contants.ParameterConstants;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Class is used to Submit the Claim Tracking Status Request to
 *         Claim Tracking API API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + ClaimTrackingPortletKeys.CLAIMTRACKING,
		"mvc.command.name="
				+ ClaimTrackingPortletKeys.SUBMIT_CLAIM_TRACKING_FORM_MVC_ACTION_COMMAND }, service = MVCActionCommand.class)
public class SubmitClaimTrackingFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to Submit the Claim Tracking Status Request to Claim
	 * Tracking API API.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isStatusApproved = false;
		String responseData = StringPool.BLANK;

		// User Submitted Form Details.
		String trackingNumber = ParamUtil.getString(actionRequest, "trackingNumber").toUpperCase();

		try {
			if (Validator.isNotNull(trackingNumber)) {
				Map<String, Object> responseMap = ClaimTrackingApiUtil.claimTracking(trackingNumber);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && (jsonObject.has("status"))) {
							responseData = jsonObject.getString("status");
							isStatusApproved = true;
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isStatusApproved", isStatusApproved);
			formSubmissionReponse.put("responseData", responseData);
			
		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while fetching claim status : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}