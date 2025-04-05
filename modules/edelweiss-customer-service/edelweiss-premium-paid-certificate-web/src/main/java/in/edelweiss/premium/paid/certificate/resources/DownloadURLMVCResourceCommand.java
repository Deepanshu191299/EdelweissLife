package in.edelweiss.premium.paid.certificate.resources;

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
import in.edelweiss.premium.paid.certificate.constants.PremiumPaidCertificatePortletKeys;
import in.edelweiss.premium.paid.certificate.util.PremiumPaidCertificateUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Resource class is used to get the download URL based on download ID from the API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + PremiumPaidCertificatePortletKeys.PREMIUMPAIDCERTIFICATE,
		"mvc.command.name="
				+ PremiumPaidCertificatePortletKeys.DOWNLOAD_URL_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class DownloadURLMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * This method is used to get the download URL based on download ID from the API.
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
		String documentURL = StringPool.BLANK;
		boolean isURLFetched = false;
		
		String documentId = ParamUtil.getString(resourceRequest, "documentId");
	
		try {

			if (Validator.isNotNull(documentId)) {
				Map<String, Object> responseMap = PremiumPaidCertificateUtil.generateDocumentURL(documentId);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && (jsonObject.has("url"))) {
							documentURL = jsonObject.getString("url");
							isURLFetched = true;
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {
						response = jsonObject.getString("message");
					}
				}
			}

			documenUploadReponse.put("isURLFetched", isURLFetched);
			documenUploadReponse.put("documentURL", documentURL);
			documenUploadReponse.put("response", response);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Download URL Resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
