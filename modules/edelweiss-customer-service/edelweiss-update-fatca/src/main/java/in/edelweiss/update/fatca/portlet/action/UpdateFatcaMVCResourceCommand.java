package in.edelweiss.update.fatca.portlet.action;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.system.configurations.ApiURLConfiguration;
import in.edelweiss.system.configurations.ApiKeyConfiguration;
import in.edelweiss.update.fatca.constants.EdelweissUpdateFatcaPortletKeys;

/**
 * @author krishna
 *
 */
@Component(property = { "javax.portlet.name=" + EdelweissUpdateFatcaPortletKeys.EDELWEISSUPDATEFATCA,
		"mvc.command.name=/update_fatca" }, service = MVCResourceCommand.class)
public class UpdateFatcaMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		JSONObject response = JSONFactoryUtil.createJSONObject();

		HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);
		try {

			String json = ParamUtil.getString(resourceRequest, "json");
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(json);

			log.debug("jsonObject>>>>>" + jsonObject);

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getFATCAFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			Map<String, Object> responseMap = EdelweissAPIUtil.callPostAPI(endpointURL,
					EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, jsonObject.toString());

			response.put("status", responseMap.get("status"));
			JSONObject responseData = (JSONObject) responseMap.get("jsonObject");

			log.debug(" responseData........" + responseData);

			if (Validator.isNotNull(responseData)) {
				if (responseData.has("errors")) {
					response.put("errors", responseData.getJSONArray("errors"));
				} else {
					response.put("jsonObject", responseData.getJSONArray("responseData"));
				}

			}

			ServletResponseUtil.write(httpServletResponse, response.toString());

			log.debug(" responseMap........" + responseMap);
			log.debug(" responseMap........" + response);
		} catch (Exception exception) {
			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_NOT_FOUND));
			response.put("status", 400);
			log.error("Exception in UpdateFatcaMVCResourceCommand >>>> " + exception.getMessage());
			if (log.isDebugEnabled()) {
				log.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		ServletResponseUtil.write(httpServletResponse, response.toString());

	}

	@Reference
	private Portal _portal;

	private Log log = LogFactoryUtil.getLog(UpdateFatcaMVCResourceCommand.class);

}
