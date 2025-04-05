package in.edelweiss.contact.us.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.contact.us.constants.ContactUsPortletKeys;
import in.edelweiss.contact.us.util.ContactUsUtil;

/**
 * This portlet will be used to render the Ask Us Anything Form.
 * This portlet will be used to submit the ask us anything form and call the API Required.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Ask Us Anything",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/ask-us-anything.jsp",
		"javax.portlet.name=" + ContactUsPortletKeys.ASKUSANYTHING,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AskUsAnythingPortlet extends MVCPortlet {
	
	/**
	 * This method will get the list Product Related 
	 * Picklist values & render the form along with it.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return void
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		String productRelatedPickListRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getAskForAnythingPickListExternalReferenceCode().trim();
		TreeMap<String, String> productRelatedOptions = new TreeMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), productRelatedPickListRefCode));
		
		renderRequest.setAttribute("productRelatedOptions", productRelatedOptions);
		
		super.render(renderRequest, renderResponse);
	}
	
	
	/**
	 * This method will get the values of the form and submit the details to the service enquiry API 
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
		
		// User Submitted Form Details.
		String fullName = ParamUtil.getString(actionRequest, "fullName");
		String emailId = ParamUtil.getString(actionRequest, "email");
		String phoneNumber = ParamUtil.getString(actionRequest, "mobileNo");
		String category = ParamUtil.getString(actionRequest, "postPurchaseFeedback");
		String subject = ParamUtil.getString(actionRequest, "subject");
		String message = ParamUtil.getString(actionRequest, "message");
		
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("fullName", fullName);
		detailsJSON.put("emailId", emailId);
		detailsJSON.put("phoneNumber", phoneNumber);
		detailsJSON.put("category", category);
		detailsJSON.put("subject", subject);
		detailsJSON.put("message", message);
		logger.debug(detailsJSON.toString());
		
		try {
			if (Validator.isNotNull(detailsJSON)) {
				
				String endpoinURL = ConfigurationUtil.getApiURLConfiguration().getEnquireUsFormSubmitURL();
				Map<String, Object> responseMap = ContactUsUtil.customerServiceRequest(detailsJSON.toString(), endpoinURL);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("status") && jsonObject.getBoolean("status")) {
							isFormSubmittedSuccessfully = true;
							responseData = jsonObject.getString("responseData");
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
			logger.error("Exception occured while submitting Ask Us Anything form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}