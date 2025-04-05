package in.edelweiss.contact.us.portlet;

import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil;
import com.liferay.object.service.ObjectEntryLocalServiceUtil;
import com.liferay.object.service.ObjectRelationshipLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import java.util.Map;
import java.util.TreeMap;

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
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.contact.us.constants.ContactUsPortletKeys;
import in.edelweiss.contact.us.util.ContactUsUtil;

/**
 * This portlet will be used to render the Branch Locator Form.
 * This portlet will be used to submit the Branch Locator form and call the API Required.
 * This portlet will be used to populate city & area option through resource call.
 * 
 * @author Abhijit AA
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=false",
				"javax.portlet.display-name=Branch Locator",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/jsp/branch-locator.jsp",
				"javax.portlet.name=" + ContactUsPortletKeys.BRANCHLOCATOR,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class BranchLocatorPortlet extends MVCPortlet {

	/**
	 * This method will get the State object entries & 
	 * render the form along with it.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return void
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String stateObjectExternalRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getStateObjectExternalReferenceCode().trim();
		TreeMap<String, String> branchStates = new TreeMap<>(EdelweissAPIUtil.getObjectEntries(themeDisplay, stateObjectExternalRefCode));
		
		boolean displaySMSIndicator = ConfigurationUtil.getContactUsConfiguration().getDisplaySMSIndicator();
		
		renderRequest.setAttribute("displaySMSIndicator", displaySMSIndicator);
		renderRequest.setAttribute("branchStates", branchStates);

		super.render(renderRequest, renderResponse);
	}

	/**
	 * This method will get the values of the form and 
	 * submit the details to the Send SMS API 
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
		String phoneNumber = ParamUtil.getString(actionRequest, "mobileNo");
		String email = ParamUtil.getString(actionRequest, "email");
		String address = ParamUtil.getString(actionRequest, "address");
		String contactNo = ParamUtil.getString(actionRequest, "contactNo");
		String mapLink = ParamUtil.getString(actionRequest, "mapLink");


		JSONObject substitutionsJSON = JSONFactoryUtil.createJSONObject();
		//substitutionsJSON.put("BRANCH_ADDRESS", address);
		//substitutionsJSON.put("BRANCH_CONTACTNO", contactNo);
		//substitutionsJSON.put("BRANCH_EMAIL", email);
		//substitutionsJSON.put("BRANCH_MAPLINK", mapLink);
		logger.debug("address"+address);
		if(address.length() > 72){
			substitutionsJSON.put("customerName", address.substring(0, 35));
			substitutionsJSON.put("address", address.substring(36, 72));
		} else if(address.length() > 36){
			substitutionsJSON.put("customerName", address.substring(0, 35));
			substitutionsJSON.put("address", address.substring(36, address.length()));
		} else {
			substitutionsJSON.put("customerName", address);
			substitutionsJSON.put("address", "");
		}
		
		logger.debug("substitutionsJSON"+substitutionsJSON.toString());
		
		JSONObject addressJSON = JSONFactoryUtil.createJSONObject();
		addressJSON.put("phoneNumber", phoneNumber);
		addressJSON.put("substitutions", substitutionsJSON);

		JSONArray destinationNoArray = JSONFactoryUtil.createJSONArray();
		destinationNoArray.put(addressJSON);

		JSONObject detailsJSON = JSONFactoryUtil.createJSONObject();
		detailsJSON.put("template", "Fiber_New_web_Branch_Locator_SMS");
		detailsJSON.put("DestinationNumbers", destinationNoArray);
		logger.debug(detailsJSON.toString());

		try {
			if (Validator.isNotNull(detailsJSON)) {
				Map<String, Object> responseMap = ContactUsUtil.sendSMS(detailsJSON.toString());

				if (Validator.isNotNull(responseMap)) {
					int status = (Integer) responseMap.get("status");
					if (status == 200) {

						JSONArray jsonArray = JSONFactoryUtil.createJSONArray(responseMap.get("content").toString());

						if (Validator.isNotNull(jsonArray) && jsonArray.length() > 0) {
							isFormSubmittedSuccessfully = true;
							responseData = jsonArray.getJSONObject(0).getString("deliveryStatus");
						}
					} else {
						JSONObject jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
						responseData = jsonObject.getString("message");
					}
				}
			}

			formSubmissionReponse.put("isFormSubmittedSuccessfully", isFormSubmittedSuccessfully);
			formSubmissionReponse.put("responseData", responseData);

		} catch (Exception exception) {
			formSubmissionReponse.put("internalError", true);
			logger.error("Exception occured while submitting Send SMS form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		hideDefaultSuccessMessage(actionRequest);
		JSONPortletResponseUtil.writeJSON(actionRequest, actionResponse, formSubmissionReponse);
	}

	/**
	 * This method will get the values from objects and 
	 * populate the options & field in the form 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException{

		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		JSONArray options = JSONFactoryUtil.createJSONArray();
		boolean isOptionsFetched = false;

		try {

			String fieldName = ParamUtil.getString(resourceRequest, "fieldName");
			String cmd = ParamUtil.getString(resourceRequest, "cmd");
			String relationshipName = StringPool.BLANK;
			String objectExternalRefCode = StringPool.BLANK;
			long selectedId = ParamUtil.getLong(resourceRequest, "selectedId");
			long objectDefinitionId = 0; 

			if(fieldName.equalsIgnoreCase("city")) {
				objectExternalRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getStateObjectExternalReferenceCode().trim();
				objectDefinitionId = ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(objectExternalRefCode, themeDisplay.getCompanyId()).getObjectDefinitionId();
				relationshipName = "stateToCity";
			}else {
				objectExternalRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getCityObjectExternalReferenceCode().trim();
				objectDefinitionId = ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(objectExternalRefCode, themeDisplay.getCompanyId()).getObjectDefinitionId();
				relationshipName = "cityToArea";
			}

			if (Validator.isNotNull(selectedId) && cmd.equalsIgnoreCase("details")) {
				ObjectEntry objectEntry = ObjectEntryLocalServiceUtil.getObjectEntry(selectedId);
				documenUploadReponse.put("address", objectEntry.getValues().get("address"));
				documenUploadReponse.put("email", objectEntry.getValues().get("email"));
				documenUploadReponse.put("tollFreeNo", objectEntry.getValues().get("tollFreeNo"));
				documenUploadReponse.put("maplink", objectEntry.getValues().get("mapURL"));
				isOptionsFetched = true;

			}else if (Validator.isNotNull(selectedId)) {

				long relationshipId = ObjectRelationshipLocalServiceUtil.getObjectRelationship(objectDefinitionId, relationshipName).getObjectRelationshipId();
				List<ObjectEntry> objectEntries = ObjectEntryLocalServiceUtil.getOneToManyObjectEntries(0, relationshipId, selectedId, true, "", QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				
				if(Validator.isNotNull(objectEntries) && !objectEntries.isEmpty()) {
					isOptionsFetched = true;
					for(ObjectEntry subType : objectEntries) {
						JSONObject option = JSONFactoryUtil.createJSONObject();
						option.put("key", subType.getObjectEntryId());
						option.put("value", subType.getValues().get("name").toString());
						options.put(option);
					}
				}
			}

			documenUploadReponse.put("isOptionsFetched", isOptionsFetched);
			documenUploadReponse.put("options", options);

		} catch (Exception exception) {
			documenUploadReponse.put("internalError", true);
			logger.error("Error while calling Object Value Populate resource Command : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		resourceResponse.getWriter().write(documenUploadReponse.toString());
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}