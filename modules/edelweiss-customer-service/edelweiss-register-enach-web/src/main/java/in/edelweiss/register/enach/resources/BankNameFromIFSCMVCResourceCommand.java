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
 *         This Resource class is used to get the bank name based on IFSC from the API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"mvc.command.name="
				+ RegisterEnachPortletKeys.BANK_NAME_FROM_IFSC_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class BankNameFromIFSCMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * This method is used to get the bank name based on IFSC from the API.
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
		String bankName = StringPool.BLANK;
		String authenticationType = StringPool.BLANK;
		String authenticationMode = StringPool.BLANK;
		boolean esign = false;
		boolean isBankNameFetched = false;

		try {

			String policyNumber = ParamUtil.getString(resourceRequest, "policyNumber");
			String bankBranchIFSCCode = ParamUtil.getString(resourceRequest, policyNumber + "bankBranchIFSCCode");
			logger.info("...bankBranchIFSCCode.."+bankBranchIFSCCode);

			if (Validator.isNotNull(bankBranchIFSCCode)) {
				Map<String, Object> responseMap = RegisterEnachUtil.bankNameFromIFSC(bankBranchIFSCCode);
				logger.debug("responseMap from BankNameFromIFSC API"+responseMap.toString());
				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
					logger.info("..status..."+status);
					logger.info("...jsonObject.."+ jsonObject);
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && 
								jsonObject.has("status") && jsonObject.getBoolean("status")) {
							if(jsonObject.has("responseData") && 
									Validator.isNotNull(jsonObject.getJSONObject("responseData"))) {
								bankName = jsonObject.getJSONObject("responseData").getString("bank");
								authenticationMode = jsonObject.getJSONObject("responseData").getString("authenticationMode");
								authenticationType = jsonObject.getJSONObject("responseData").getString("authenticationType");
								esign = jsonObject.getJSONObject("responseData").getBoolean("authenticationType");
								isBankNameFetched = true;
							}
						}else if (Validator.isNotNull(jsonObject) && (jsonObject.has("responseData"))) {
							response = jsonObject.getString("responseData");
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {
						response = jsonObject.getString("message");
					}
				}
			}

			documenUploadReponse.put("isBankNameFetched", isBankNameFetched);
			documenUploadReponse.put("esign", esign);
			documenUploadReponse.put("bankName", bankName);
			documenUploadReponse.put("authenticationMode", authenticationMode);
			documenUploadReponse.put("authenticationType", authenticationType);
			documenUploadReponse.put("response", response);
			
			logger.info("...documenUploadReponse......."+ documenUploadReponse);
		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Bank Name IFSC resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		logger.info("documenUploadReponse"+documenUploadReponse.toString());
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
