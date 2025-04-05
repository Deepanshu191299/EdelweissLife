package in.edelweiss.application.tracker.mvc.command;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.application.tracker.constants.ApplicationTrackerPortletKeys;
import in.edelweiss.application.tracker.util.AppTrackerUtil;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;

@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + ApplicationTrackerPortletKeys.APPLICATIONTRACKER,
				"mvc.command.name=/" 
		},
		service = MVCRenderCommand.class
		)
public class ApplicationTrackerMVCRenderCommand implements MVCRenderCommand {

	private static final Log logger = LogFactoryUtil.getLog(ApplicationTrackerMVCRenderCommand.class);
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		logger.info("inside the application render command123::" );
		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		try {
			JSONObject policyJSON = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(request.getSession().getAttribute("policyJSON"))) {
				policyJSON = JSONFactoryUtil
						.createJSONObject(request.getSession().getAttribute("policyJSON").toString());
			}
				logger.info("PolicyJSON::::"+ policyJSON );
				renderRequest.setAttribute("policyNumber", policyJSON.getString("policyNumber"));
				logger.debug("policyNumber in application:::"+ policyJSON.getString("policyNumber"));
			if (Validator.isNotNull(policyJSON) && policyJSON.length() > 1) {
				
				String inputType = policyJSON.getString("inputType");
				JSONObject policyFormJSONObject = renderProposalFormPolicyDetails(inputType, policyJSON.getString(inputType), policyJSON.getString("dob"));
				logger.info("policyFormJSONObject:::"+policyFormJSONObject);
				if(policyFormJSONObject.getBoolean("status")) {
					JSONObject verificationJSONObject = renderVerificationPolicyDetails(policyFormJSONObject.getString("application_number"), policyJSON.getString(inputType));	
					logger.info("verificationJSONObject.........."+verificationJSONObject);
					renderRequest.setAttribute("verificationJSONObject", verificationJSONObject);
					if(verificationJSONObject.getBoolean("status")) {
						JSONObject communicationJSONObject = renderCommunicationDetails(inputType, policyJSON.getString(inputType), verificationJSONObject.getString("tpaStatus"));	
						renderRequest.setAttribute("communicationJSONObject", communicationJSONObject);
						logger.info("communicationJSONObject:::"+communicationJSONObject);
						
						String stateObjectExternalRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getStateObjectExternalReferenceCode().trim();
						TreeMap<String, String> branchStates = new TreeMap<>(EdelweissAPIUtil.getObjectEntries(themeDisplay, stateObjectExternalRefCode));
						renderRequest.setAttribute("branchStates", branchStates);
					}
				}
				
				renderRequest.setAttribute("policyFormJSONObject", policyFormJSONObject);
			}
			//request.getSession().removeAttribute("policyJSON");
			HttpSession session = request.getSession(); 
			String currDateRenew = new Date().toString();
			renderRequest.setAttribute("uniqueDateObj", currDateRenew);
			 MultipleLoginBlock multipleLoginBlock = new MultipleLoginBlock();
			 String inputValSess = (String) session.getAttribute("inputValueRenewal");
			 logger.info("inputTypeValue from softLogin ApplicationTracker::::::"+ inputValSess);
			 String inputValSess1 = policyJSON.getString("policyNumber");
			 logger.info("inputTypeValue from ThankYouApplicationTracker::::::"+ inputValSess1);
			 if(inputValSess != null) {
			  multipleLoginBlock.setPolicyNumber(inputValSess);
			  }else {
				  logger.info("inside thankyou!!!:::::::");
				  multipleLoginBlock.setPolicyNumber(inputValSess1);  
			  }
			  multipleLoginBlock.setAppName(null);
			  multipleLoginBlock.setLogInDate(currDateRenew);
			  if(Validator.isNotNull(inputValSess)&&!inputValSess.isEmpty() || Validator.isNotNull(inputValSess1)&&!inputValSess1.isEmpty()) {
			  MultipleLoginBlock multipleLoginBlockResponse;
				try {
					multipleLoginBlockResponse = edelweissTokioCommonApi.updateMultipleLoginBlock(themeDisplay.getPortalURL(),  multipleLoginBlock, null);
					logger.info("multipleLoginBlockResponse..."+multipleLoginBlockResponse);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		}catch (Exception exception) {
			logger.error("Exception occured in app tracker render : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
				exception.printStackTrace();
			}
		}

		return "/app_tracker/application_tracker.jsp";
	}


	private JSONObject renderProposalFormPolicyDetails(String inputType, String inputValue, String dateOfBirth) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {

			if (Validator.isNotNull(inputType) && Validator.isNotNull(inputValue)
					&& Validator.isNotNull(dateOfBirth)) {

				Map<String, Object> responseMap = AppTrackerUtil.proposalFormPolicyDetails(inputType, inputValue,
						dateOfBirth);
				logger.debug("responseMap in application Tracker:::"+ responseMap);
				if (Validator.isNotNull(responseMap)) {
					if(JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")) {
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getJSONArray("response_data").getJSONObject(0);
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}else if(!JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")){
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}else {
						jsonObject = JSONFactoryUtil.createJSONObject();
						jsonObject.put("status", false);
					}					
				}
			}
		} catch (Exception exception) {
			logger.error("Exception occured while fetching customer data : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		logger.debug("jsonObject:::"+ jsonObject);
		return jsonObject;
	}
	
	private JSONObject renderVerificationPolicyDetails(String applicatioNumber, String policyNumber) {
		logger.debug("inside the renderVerificationPolicyDetails:::" );
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {

			if (Validator.isNotNull(applicatioNumber) && Validator.isNotNull(policyNumber)) {

				Map<String, Object> responseMap = AppTrackerUtil.verificationDetails(applicatioNumber, policyNumber);
				logger.debug("responseMap of renderVerificationPolicyDetails"+ responseMap);
				if (Validator.isNotNull(responseMap)) {
					if(JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")) {
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getJSONObject("responseData");
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}else if(!JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")){
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}
				}
			}
		} catch (Exception exception) {
			logger.error("Exception occured while fetching customer data : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return jsonObject;
	}
	
	private JSONObject renderCommunicationDetails(String inputType, String inputValue, String isTPARequired) {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {

			if (Validator.isNotNull(inputType) && Validator.isNotNull(inputValue) 
					&& Validator.isNotNull(isTPARequired) && (!isTPARequired.isBlank() || !isTPARequired.equalsIgnoreCase("NA"))) {

				Map<String, Object> responseMap = AppTrackerUtil.communicationDetails(inputType, inputValue, "1");

				if (Validator.isNotNull(responseMap)) {
					if(JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")) {
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getJSONObject("responseData");
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}else if(!JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status")){
						jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
						jsonObject.put("status", JSONFactoryUtil.createJSONObject(responseMap.get("content").toString()).getBoolean("status"));
					}
				}
			}
		} catch (Exception exception) {
			logger.error("Exception occured while fetching customer data : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		return jsonObject;
	}
}
