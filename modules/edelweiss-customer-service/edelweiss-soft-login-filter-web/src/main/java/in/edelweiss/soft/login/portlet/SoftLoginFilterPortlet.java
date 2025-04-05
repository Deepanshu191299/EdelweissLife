package in.edelweiss.soft.login.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.soft.login.constants.SoftLoginFilterPortletKeys;
import in.edelweiss.soft.login.util.FilterApiUtil;
import in.edelweiss.system.configurations.URLRelatedConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

/**
 * The portlet will be used to render the Soft Login Form & Actions needs to be taken on input submission.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Soft Login Filter",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/soft-login.jsp",
		"javax.portlet.name=" + SoftLoginFilterPortletKeys.SOFTLOGINFILTER,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user,guest"
	},
	service = Portlet.class
)
public class SoftLoginFilterPortlet extends MVCPortlet {
	
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String currentURL = themeDisplay.getURLCurrent();
		boolean isHomeRenewal = false;
		
		if(currentURL.equals("/") || currentURL.equals("/home") || 
		   currentURL.equals("/web/guest") || currentURL.equals("/web/guest/home")) {
			isHomeRenewal = true;
		}
		String currDate = new Date().toString();
		renderRequest.setAttribute("currTime", currDate);
		
		renderRequest.setAttribute("isHomeRenewal", isHomeRenewal);
		super.render(renderRequest, renderResponse);
	}
	
	/**
	 * This method is used to generate & validate OTP.
	 * Redirect User to the required page if input is added.
	 * 
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @return void
	 */

	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		JSONObject formSubmissionReponse = JSONFactoryUtil.createJSONObject();
		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));
		
		if(Validator.isNotNull(request.getSession(false).getAttribute("policyJSON"))) {
			request.getSession(false).removeAttribute("policyJSON");	
		}
		
				
		int sessionTimeout = Integer.parseInt(ConfigurationUtil.getCommonConfiguration().getSessionTimeoutInMinutes());
		boolean isFormSubmittedSuccessfully = false;
		boolean isOTPRequired = false;
		String id = StringPool.BLANK;
		String redirectURL = StringPool.BLANK;
		String responseData = StringPool.BLANK;

		if(Validator.isNotNull(request.getSession().getAttribute("isOTPRequired"))) {
			isOTPRequired = (Boolean)request.getSession().getAttribute("isOTPRequired");
		}
		
		// User Submitted Form Details.
		String inputType = ParamUtil.getString(actionRequest, "inputType");
		String inputValue = ParamUtil.getString(actionRequest, "inputValue");
		String createDate = ParamUtil.getString(actionRequest, "createDate");
		String dateOfBirth = ParamUtil.getString(actionRequest, "dateOfBirth");
		boolean isHomeRenewal = ParamUtil.getBoolean(actionRequest, "isHomeRenewal");
		logger.debug("createDate:::::"+ createDate);
		
		HttpSession session = request.getSession();
		session.setAttribute("inputValueRenewal", inputValue);
		
		String otpId = ParamUtil.getString(actionRequest, "otpId");
		String otp = ParamUtil.getString(actionRequest, "otp");
		String otpResend = ParamUtil.getString(actionRequest, "otpResend");
		
		JSONObject policyJson = JSONFactoryUtil.createJSONObject();
		policyJson.put("inputType", inputType);
		policyJson.put(inputType, inputValue);
		policyJson.put("dob", dateOfBirth);
		policyJson.put("loginCreateDate", createDate);

		try {
			if (isOTPRequired) {

				if (Validator.isNotNull(otpId) && Validator.isNotNull(otp)) {

					JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
					detailsJSON.put("id", otpId);
					detailsJSON.put("otp", otp);

					if (Validator.isNotNull(detailsJSON)) {
						Map<String, Object> responseMap = FilterApiUtil.validateOTP(detailsJSON.toString());

						if (Validator.isNotNull(responseMap)) {
							int status = (Integer) responseMap.get("status");
							JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");

							if (status == 200) {
								if (Validator.isNotNull(jsonObject) && jsonObject.has("customerDataResponse")) {
									request.getSession().setAttribute("policyJSON", policyJson.toString());
									request.getSession().setMaxInactiveInterval(sessionTimeout*60);
									redirectURL = (String)request.getSession().getAttribute("redirectURL");
									isFormSubmittedSuccessfully = true;
								}
							} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
								responseData = jsonObject.getString("message");
							}
						}
					}
				} else if (Validator.isNotNull(inputType) && Validator.isNotNull(inputValue)
						&& Validator.isNotNull(dateOfBirth)) {

					policyJson.put(inputType, inputValue);
					policyJson.put("dob", dateOfBirth);

					if (Validator.isNotNull(policyJson)) {
						Map<String, Object> responseMap = FilterApiUtil.sendOTP(policyJson.toString());

						if (Validator.isNotNull(responseMap)) {
							int status = (Integer) responseMap.get("status");
							JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");

							if (status == 200) {
								if (Validator.isNotNull(jsonObject) && jsonObject.has("id")) {
									id = jsonObject.getString("id");
									responseData = jsonObject.getString("responseData");
									isFormSubmittedSuccessfully = true;
								}
							} else if (Validator.isNotNull(jsonObject) && jsonObject.has("message")) {
								responseData = jsonObject.getString("message");
							}
						}
					}
				}
			} else {
				request.getSession().setAttribute("policyJSON", policyJson.toString());
				request.getSession().setMaxInactiveInterval(sessionTimeout*60);
				if(isHomeRenewal) {
					URLRelatedConfiguration urlRelatedConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
					redirectURL = urlRelatedConfiguration.getRenewYourPolicyPageURL();
				}else {
					redirectURL = (String)request.getSession().getAttribute("redirectURL");
				}
				
				isFormSubmittedSuccessfully = true;
			}
			
			
			  ThemeDisplay themeDisplay = (ThemeDisplay)
			  actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			  logger.debug("input type: "+inputType+", input value: "
			  +inputValue+", cerateDate: "+createDate);
			  
			  MultipleLoginBlock multipleLoginBlock = new MultipleLoginBlock();
			  multipleLoginBlock.setPolicyNumber(inputValue);
			  multipleLoginBlock.setAppName(redirectURL);
			  multipleLoginBlock.setLogInDate(createDate);
			  if(Validator.isNotNull(inputValue)&&!inputValue.isEmpty()) {
			  MultipleLoginBlock multipleLoginBlockResponse =
			  edelweissTokioCommonApi.updateMultipleLoginBlock(themeDisplay.getPortalURL(),
			  multipleLoginBlock, null);
			  logger.debug("multipleLoginBlockResponse..."+multipleLoginBlockResponse); }
			 
			
			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("redirectURL", redirectURL);
			formSubmissionReponse.put("otpId", id);
			formSubmissionReponse.put("otpResend", otpResend);
			formSubmissionReponse.put("responseData", responseData);
			 
			
		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting soft login input form or redirection : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}