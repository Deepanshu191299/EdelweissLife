package in.edelweiss.contact.us.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.contact.us.constants.ContactUsPortletKeys;
import in.edelweiss.contact.us.util.ContactUsUtil;

/**
 * This portlet will be used to submit the Check Status Request form and call the API Required.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Check Status",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/check-status.jsp",
		"javax.portlet.name=" + ContactUsPortletKeys.CHECKSTATUS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CheckStatusPortlet extends MVCPortlet {
	
	/**
	 * This method will get the values of the form and 
	 * submit the details to the Customer Service Request API 
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		boolean isFormSubmittedSuccessfully = false;
		String responseData = StringPool.BLANK;
		int responseCode = 0;
		
		// User Submitted Form Details.
		String complaintReferenceNo = ParamUtil.getString(actionRequest, "complaintReferenceNo").toUpperCase();
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("srNumber", complaintReferenceNo);
		detailsJSON.put("requestType", EdelweissAPIConstants.REQUEST_TYPE_VIEW_SR);
		
		try {
			if (Validator.isNotNull(detailsJSON)) {
				
				String endpoinURL = ConfigurationUtil.getApiURLConfiguration().getRequestFormSubmitURL();
				Map<String, Object> responseMap = ContactUsUtil.customerServiceRequest(detailsJSON.toString(), endpoinURL);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					logger.debug(jsonObject);
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("responseData")) {
							isFormSubmittedSuccessfully = true;
							responseCode = Integer.valueOf(jsonObject.getJSONObject("responseData").getJSONObject("context").getString("responseCode"));
							if(responseCode == 200) {
								JSONObject complaintJson = (JSONObject)jsonObject.getJSONObject("responseData").getJSONArray("data").get(0);
								formSubmissionReponse.put("complaintJson", complaintJson);
							}
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
						responseData = jsonObject.getString("message");
					}
				}
			}
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);
			
		} catch (Exception exception) {
			
			formSubmissionReponse.put("internalError", true);
			
			logger.error("Exception occured while submitting check status form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}