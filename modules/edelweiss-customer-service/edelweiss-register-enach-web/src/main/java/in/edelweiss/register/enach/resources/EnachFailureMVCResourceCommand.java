package in.edelweiss.register.enach.resources;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys;
import in.edelweiss.register.enach.util.RegisterEnachUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Resource class is used to submit the enach failure to the API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"mvc.command.name="
				+ RegisterEnachPortletKeys.ENACH_FAILURE_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class EnachFailureMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * This method is used to used to submit the enach failure to the API.
	 * 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		String response = StringPool.BLANK;
		String mandateId = ParamUtil.getString(resourceRequest, "mandateId");
		String errorMessage = ParamUtil.getString(resourceRequest, "errorMessage");

		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("mandateId", mandateId);
		detailsJSON.put("enachStatus", false);
		detailsJSON.put("errMsg", errorMessage);
		logger.info("....detailsJSON inside EnachFailureMVC......"+ detailsJSON);
		try {

			if (Validator.isNotNull(mandateId) && Validator.isNotNull(errorMessage)) {
				
				Map<String, Object> responseMap = RegisterEnachUtil.reportEnachFailure(detailsJSON.toString());
				logger.info("responseMap inside EnachFailureMVC...."+ responseMap);
				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					//logger.info("...status from reportEnachFailure..."+ status);
					//logger.info("........jsonObject from EnanchFailure......"+ jsonObject);
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("status")) {
							boolean jsonStatus = jsonObject.getBoolean("status");
							if (jsonStatus == true) {
								response = jsonObject.getString("responseData");
								logger.info("insid if JSON...."+ response);
							}
						}
					} else if (Validator.isNotNull(jsonObject) && jsonObject.has("title")) {
						response = jsonObject.getString("title");
						logger.info("insid else JSON...."+ response);
					}
				}
			}

			documenUploadReponse.put("response", response);
			logger.debug("....documenUploadReponse..."+ documenUploadReponse.toString());
			
		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Enach Failure Resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
