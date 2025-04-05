package in.edelweiss.application.tracker.portlet;

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
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.application.tracker.constants.ApplicationTrackerPortletKeys;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.MultipleLoginBlock;

/**
 * @author Abhijit AA
 */
@Component(
		immediate = true,
		property = {
				"com.liferay.portlet.display-category=category.sample",
				"com.liferay.portlet.header-portlet-css=/css/main.css",
				"com.liferay.portlet.instanceable=false",
				"javax.portlet.display-name=ApplicationTracker",
				"javax.portlet.init-param.template-path=/",
				"javax.portlet.init-param.view-template=/view.jsp",
				"javax.portlet.name=" + ApplicationTrackerPortletKeys.APPLICATIONTRACKER,
				"javax.portlet.resource-bundle=content.Language",
				"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
		)
public class ApplicationTrackerPortlet extends MVCPortlet {

	/**
	 * This method will get the values from objects and 
	 * populate the options & field in the form 
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @return void
	 */
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException{
		logger.debug("inside serveRespurce of application tracker::");
		
		String cmd = ParamUtil.getString(resourceRequest, "cmd");
		logger.debug("cmd value::::::"+ cmd);
		if(cmd.equalsIgnoreCase("multipleLoginData1")) {
			logger.debug("inside cmd:::::::::::");
			String loginCreateDate=StringPool.BLANK;
			JSONObject responseObj = JSONFactoryUtil.createJSONObject();
			try {
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String policyNumber=ParamUtil.getString(resourceRequest, "policyNumberTest");
			logger.debug("policyNumber...."+ policyNumber);
			if(Validator.isNotNull(policyNumber) && !policyNumber.isEmpty()) {
				MultipleLoginBlock multipleLoginBlockResponse =
						  edelweissTokioCommonApi.getMultipleLoginByPolicyNumber(portalURL, policyNumber);
				logger.debug("multipleLoginBlockResponse...."+multipleLoginBlockResponse);
				loginCreateDate=multipleLoginBlockResponse.getLogInDate();
				logger.debug("loginCreateDate...."+loginCreateDate);
				responseObj.put("loginCreateDate", loginCreateDate);
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				PrintWriter writer = resourceResponse.getWriter();
				writer.write(responseObj.toString());
				writer.close();
			}
		}
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject documenUploadReponse = JSONFactoryUtil.createJSONObject();
		JSONArray options = JSONFactoryUtil.createJSONArray();
		boolean isOptionsFetched = false;

		try {

			String fieldName = ParamUtil.getString(resourceRequest, "fieldName");
			String relationshipName = StringPool.BLANK;
			String objectExternalRefCode = StringPool.BLANK;
			long selectedId = ParamUtil.getLong(resourceRequest, "selectedId");
			long objectDefinitionId = 0; 
			logger.debug("field name and selectedID::"+ fieldName +" ..." + selectedId);
			if(fieldName.equalsIgnoreCase("dcCity") || fieldName.equalsIgnoreCase("addressCity")) {
				objectExternalRefCode = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getStateObjectExternalReferenceCode().trim();
				objectDefinitionId = ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(objectExternalRefCode, themeDisplay.getCompanyId()).getObjectDefinitionId();
				relationshipName = "stateToCity";
			}

			if (Validator.isNotNull(selectedId)) {

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
			logger.debug("documenUploadReponse:::"+ documenUploadReponse);

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