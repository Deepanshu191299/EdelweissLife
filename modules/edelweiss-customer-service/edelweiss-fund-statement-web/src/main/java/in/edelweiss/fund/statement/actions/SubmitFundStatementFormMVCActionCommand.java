package in.edelweiss.fund.statement.actions;

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

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.fund.statement.constants.FundStatementPortletKeys;
import in.edelweiss.fund.statement.util.FundStatementUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Class is used to Submit the Details to Get the Request ID from Customer Service Request API.
 */

@Component(immediate = true, property = {
		"javax.portlet.name=" + FundStatementPortletKeys.FUNDSTATEMENT, "mvc.command.name="
				+ FundStatementPortletKeys.SUBMIT_FUND_STATEMENT_FORM_MVC_ACTION_COMMAND
				}, service = MVCActionCommand.class)
public class SubmitFundStatementFormMVCActionCommand extends BaseMVCActionCommand {

	/**
	 * This method is used to Submit the Details to Get the Request ID from Customer Service Request API.
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isFormSubmittedSuccessfully = false;
		String documentID = StringPool.BLANK;
		String responseData = StringPool.BLANK;

		// User Submitted Form Details.
		JSONObject customerJSONObject = JSONFactoryUtil
				.createJSONObject(ParamUtil.getString(actionRequest, "customerJSONObject"));
		String policyNumber = customerJSONObject.getString("policyNumber");
		String fromDate = ParamUtil.getString(actionRequest, policyNumber + "dateFrom");
		String toDate = ParamUtil.getString(actionRequest, policyNumber + "dateTo");

		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("fromDate", DateFormatterUtil.parseDateToSpecificFormat(fromDate, DateConstants.HYPHEN_YYYY_MM_DD));
		detailsJSON.put("policyNumber", policyNumber);
		detailsJSON.put("requestType", EdelweissAPIConstants.REQUEST_TYPE_FUND_STATEMENT_DMS);
		detailsJSON.put("toDate", DateFormatterUtil.parseDateToSpecificFormat(toDate, DateConstants.HYPHEN_YYYY_MM_DD));
		logger.debug(detailsJSON);

		try {
			if (Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = FundStatementUtil
						.customerServiceRequest(detailsJSON.toString());

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("id")) {
							documentID = jsonObject.getString("id");
							isFormSubmittedSuccessfully = true;
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("policyNumber", policyNumber);
			formSubmissionReponse.put("documentID", documentID);
			formSubmissionReponse.put("responseData", responseData);
			
		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting download reciept form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}