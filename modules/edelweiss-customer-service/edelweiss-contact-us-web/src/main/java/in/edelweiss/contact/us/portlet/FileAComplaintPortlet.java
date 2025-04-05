package in.edelweiss.contact.us.portlet;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.CategoryUtil;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.contact.us.constants.ContactUsPortletKeys;
import in.edelweiss.contact.us.util.ContactUsUtil;

/**
 * This portlet will be used to render the File A Complaint Form.
 * This portlet will be used to submit the File A Complaint form and call the API Required.
 * This portlet will be used to populate complaint subtype option through resource call.
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=File A Complaint",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/file-a-complaint.jsp",
		"javax.portlet.name=" + ContactUsPortletKeys.FILEACOMPLAINT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FileAComplaintPortlet extends MVCPortlet {
	
	/**
	 * This method will get the Complaint Category list & 
	 * render the form along with it.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return void
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
			
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			String complaintTypeVocabularyName = ConfigurationUtil.getCommonConfiguration().getComplaintVocabularyName().trim();
			List<AssetCategory> complaintTypeList = CategoryUtil.fetchCategoriesbyVocabularyNameAndParentId(themeDisplay.getScopeGroupId(), complaintTypeVocabularyName, 0);
			renderRequest.setAttribute("complaintTypeList", complaintTypeList);
		
		} catch (Exception exception) {
			logger.error("Exception occured while rendering File A Complaint form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		super.render(renderRequest, renderResponse);
	}
	
	/**
	 * This method will get the values of the form and 
	 * submit the details to the Complaint API 
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
		String policyNumber = ParamUtil.getString(actionRequest, "policyNumber");
		//Commenting following fields as these are there in the form but not used in the API.
//		String phoneNumber = ParamUtil.getString(actionRequest, "mobileNo");
//		String emailId = ParamUtil.getString(actionRequest, "email");
//		String complaintType = ParamUtil.getString(actionRequest, "complaintType");
		String complaintSubtypes = ParamUtil.getString(actionRequest, "complaintSubtypes");
		String comments = ParamUtil.getString(actionRequest, "comments");
		
		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("category", "Complaint");
		detailsJSON.put("channel", "CUSTPORT");
		detailsJSON.put("comments", comments);
		detailsJSON.put("isPortalStp", "N");
		detailsJSON.put("policyNumber", policyNumber);
		detailsJSON.put("raisedBy","Client");
		detailsJSON.put("requestType", "create_sr");
		detailsJSON.put("severity", "low");
		detailsJSON.put("subType", complaintSubtypes);
		logger.debug(detailsJSON.toString());

		try {
			if (Validator.isNotNull(detailsJSON)) {
				String endpoinURL = ConfigurationUtil.getApiURLConfiguration().getRequestFormSubmitURL();
				Map<String, Object> responseMap = ContactUsUtil.customerServiceRequest(detailsJSON.toString(), endpoinURL);

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					JSONObject jsonObject = (JSONObject) responseMap.get("jsonObject");
					if (status == 200) {
						if (Validator.isNotNull(jsonObject) && jsonObject.has("responseData")) {
							isFormSubmittedSuccessfully = true;
							responseData = jsonObject.getJSONObject("responseData").getJSONObject("data").getString("ServiceRequestId");
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
			logger.error("Exception occured while submitting File A Complaint form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}
	
	/**
	 * This method will get the values from category and 
	 * populate the options & field in the form 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException{

		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		JSONArray complaintSubtypes = JSONFactoryUtil.createJSONArray();
		boolean isCategoryFetched = false;
		
		try {
			
			long complaintTypeKey = ParamUtil.getLong(resourceRequest, "complaintTypeId");

			if (Validator.isNotNull(complaintTypeKey)) {
				List<AssetCategory> complaintSubType = CategoryUtil.fetchCategoriesbyCategoryId(complaintTypeKey);
				
				if(!complaintSubType.isEmpty()) {
					isCategoryFetched = true;
					for(AssetCategory subType : complaintSubType) {
						JSONObject subTypeJson = JSONFactoryUtil.createJSONObject();
						subTypeJson.put("key", subType.getTitle(Locale.US));
						subTypeJson.put("value", subType.getDescription(Locale.US));
						complaintSubtypes.put(subTypeJson);
					}
				}
			}
			
			documenUploadReponse.put("isCategoryFetched", isCategoryFetched);
			documenUploadReponse.put("complaintSubtypes", complaintSubtypes);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Category List resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());

}