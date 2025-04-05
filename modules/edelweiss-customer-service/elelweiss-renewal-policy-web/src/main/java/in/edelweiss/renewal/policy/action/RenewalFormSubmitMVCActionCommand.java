package in.edelweiss.renewal.policy.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.renewal.policy.web.constants.EdelweissRenewalPolicyWebPortletKeys;
import in.edelweiss.system.configurations.ApiKeyConfiguration;
import in.edelweiss.system.configurations.ApiURLConfiguration;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissRenewalPolicyWebPortletKeys.EDELWEISSRENEWALPOLICYWEB,
		"mvc.command.name="	+ EdelweissRenewalPolicyWebPortletKeys.SUBMIT_RENEWAL_FORM_MVC_ACTION_COMMAND }, 
		service = MVCActionCommand.class)


public class RenewalFormSubmitMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		logger.debug("inside doProcessactin:::::");
		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		
		Map<String, Object> responseMap = new HashMap<>();
		String policyNumber = ParamUtil.getString(actionRequest, "policyNumber");
		String dateOfBirth = ParamUtil.getString(actionRequest, "dateOfBirth");
		boolean isStatusApproved = false;
		
		JSONObject customerPolicyObj=JSONFactoryUtil.createJSONObject();
		customerPolicyObj.put("dob", DateFormatterUtil.parseDateToSpecificFormat(dateOfBirth, DateConstants.HYPHEN_DD_MM_YYYY));
		customerPolicyObj.put("policyNumber", policyNumber);
		customerPolicyObj.put("type", "RENEWAL");
		customerPolicyObj.put("source", "website");
		logger.debug("customerPolicyObj in proseeAction:::::::"+customerPolicyObj);
		try {
			
			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getRenewalFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);
			logger.debug("xApiKey::::"+ xApiKey);
			logger.debug("accessToken:::::::"+ accessToken);
			logger.debug("endpointURL:::::"+ endpointURL);
			
			if (Validator.isNotNull(accessToken)) {

				responseMap = EdelweissAPIUtil.callPostAPI(endpointURL, EdelweissAPIConstants.APPLICATION_JSON, true,
						accessToken, xApiKey, customerPolicyObj.toJSONString());
				logger.debug("response in processaction::::"+ responseMap);
				if(Validator.isNotNull(responseMap)) {
					JSONObject responseObj=JSONFactoryUtil.createJSONObject(responseMap.get("jsonObject").toString());
					if( (Integer)responseMap.get("status")== 200) {
						isStatusApproved=true;
						formSubmissionReponse.put("responseData", responseObj.get("responseData"));
					}else {
						
						formSubmissionReponse.put("responseData", responseObj.get("errors"));
					}
					formSubmissionReponse.put("isStatusApproved", isStatusApproved);
				
				}
			}
		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("error while submit renewal pay api calling " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}
	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
