package in.edelweiss.raise.a.claim.resources;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.DateFormatterUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.raise.a.claim.constants.RaiseAClaimPortletKeys;
import in.edelweiss.raise.a.claim.util.RaiseAClaimApiUtil;

/**
 * 
 * @author Abhijit Ande
 *
 *         This Resource class is used to upload the document & get the document
 *         URL from the API.
 */

@Component(immediate = true, property = { "javax.portlet.name=" + RaiseAClaimPortletKeys.RAISEACLAIM,
		"mvc.command.name="
				+ RaiseAClaimPortletKeys.UPLOAD_DOCUMENT_URL_MVC_RESOURCE_COMMAND }, service = MVCResourceCommand.class)

public class DocumentUploadMVCResourceCommand extends BaseMVCResourceCommand {

	/**
	 * This method is used to upload the document & get the document URL from the
	 * API.
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		
		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		String documentId = StringPool.BLANK;
		String documentURL = StringPool.BLANK;
		int statusCode = 0;
		String response = StringPool.BLANK;

		String strDate = DateFormatterUtil.parseDate(new Date(), DateConstants.HYPHEN_YYYY_MM_DD_T_HH_MM_SS, StringPool.BLANK);
		boolean isDocumentUploadSuccess = false;

		String dmsClass = "ClaimServicing";
		String documentType = ParamUtil.getString(resourceRequest, ParameterConstants.DOCUMENT_TYPE_JSON);
		String contentType = ParamUtil.getString(resourceRequest, ParameterConstants.CONTENT_TYPE_JSON);
		String policyNo = ParamUtil.getString(resourceRequest, ParameterConstants.POLICY_NUMBER);
		String documentTitle = ParamUtil.getString(resourceRequest, ParameterConstants.DOCUMENT_TITLE_JSON);

		logger.info("documentType------->"+documentType);
		documentType = EdelweissAPIUtil.getDocumentType(documentType);
		contentType = EdelweissAPIUtil.getContentType(documentType);

		JSONObject documentJSON = JSONFactoryUtil.createJSONObject();
		documentJSON.put(ParameterConstants.DMS_CLASS_JSON, dmsClass);
		documentJSON.put(ParameterConstants.DOCUMENT_TYPE_JSON, documentType);
		documentJSON.put(ParameterConstants.CONTENT_TYPE_JSON, contentType);
		documentJSON.put(ParameterConstants.PROPOSAL_NUMBER_JSON, policyNo);
		documentJSON.put(ParameterConstants.POLICY_NUMBER_JSON, policyNo);
		documentJSON.put(ParameterConstants.CREATED_BY_JSON, "Website");
		documentJSON.put(ParameterConstants.CREATED_AT_JSON, strDate);
		documentJSON.put(ParameterConstants.SOURCE_SYSTEM_JSON, "Website");
		documentJSON.put(ParameterConstants.PROCESS_NAME_JSON, "claimIntimation");
		documentJSON.put(ParameterConstants.INDEX_KEY_JSON, ParameterConstants.POLICY_NUMBER_JSON);
		documentJSON.put(ParameterConstants.INDEX_VALUE_JSON, policyNo);
		documentJSON.put(ParameterConstants.IS_HISTORICAL_DOCUMENT_JSON, Boolean.FALSE);
		documentJSON.put(ParameterConstants.DOCUMENT_TITLE_JSON, documentTitle);
		documentJSON.put(ParameterConstants.DOCUMENT_COMMENT_JSON, "testing");
		documentJSON.put(ParameterConstants.CLAIM_TYPE_JSON, "DEATH CLAIM");

		logger.debug(documentJSON.toString());

		try {

			if (Validator.isNotNull(documentJSON)) {
				Map<String, Object> responseMap = RaiseAClaimApiUtil.getDocumentURL(documentJSON.toString());

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");

					if (status == 200) {

						if (Validator.isNotNull(jsonObject) && (jsonObject.has("id") && jsonObject.has("url"))) {
							documentId = jsonObject.getString("id");
							documentURL = jsonObject.getString("url");

							if (Validator.isNotNull(documentURL)) {
								statusCode = RaiseAClaimApiUtil.putDocument(documentURL);

								if (statusCode == 200) {
									isDocumentUploadSuccess = true;
								}
							}
						}
					} else if (Validator.isNotNull(jsonObject) && (jsonObject.has("message"))) {

						response = jsonObject.getString("message");
					}
				}
			}
			
			documenUploadReponse.put("isDocumentUploadSuccess", isDocumentUploadSuccess);
			documenUploadReponse.put("documentId", documentId);
			documenUploadReponse.put("response", response);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Document Resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}
