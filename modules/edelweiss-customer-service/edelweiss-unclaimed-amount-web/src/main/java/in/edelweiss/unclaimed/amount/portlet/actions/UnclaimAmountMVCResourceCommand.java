package in.edelweiss.unclaimed.amount.portlet.actions;

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
import in.edelweiss.unclaimed.amount.constants.EdelweissUnclaimedAmountWebPortletKeys;

/**
 * @author krishna
 *
 */
@Component(property = { "javax.portlet.name=" + EdelweissUnclaimedAmountWebPortletKeys.EDELWEISSUNCLAIMEDAMOUNTWEB,
		"mvc.command.name=/unclaim_amount" }, service = MVCResourceCommand.class)
public class UnclaimAmountMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		JSONObject response = JSONFactoryUtil.createJSONObject();
		try {
			String dob = ParamUtil.getString(resourceRequest, "dob");
			String email = ParamUtil.getString(resourceRequest, "email");
			String fullName = ParamUtil.getString(resourceRequest, "fullName");
			String mobile = ParamUtil.getString(resourceRequest, "mobile");
			String panNo = ParamUtil.getString(resourceRequest, "panNo");
			String policyNo = ParamUtil.getString(resourceRequest, "policyNo");

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			jsonObject.put("dob", dob);
			jsonObject.put("email", email);
			jsonObject.put("fullName", fullName);
			jsonObject.put("mobile", mobile);
			jsonObject.put("panNo", panNo);
			jsonObject.put("policyNo", policyNo);
			jsonObject.put("requestType", "unclaimed_amount");

			log.debug("jsonObject>>>>>" + jsonObject);

			ApiURLConfiguration apiURLConfiguration = ConfigurationUtil.getApiURLConfiguration();
			ApiKeyConfiguration apiKeyConfiguration = ConfigurationUtil.getApiKeyConfiguration();

			String endpointURL = apiURLConfiguration.getRequestFormSubmitURL();
			String xApiKey = apiKeyConfiguration.getXApiKeyForGeneral();
			String accessToken = EdelweissAPIUtil.getOAuthToken(false);

			Map<String, Object> responseMap = EdelweissAPIUtil.callPostAPI(endpointURL,
					EdelweissAPIConstants.APPLICATION_JSON, true, accessToken, xApiKey, jsonObject.toString());

			response.put("status", responseMap.get("status"));
			JSONObject responseData = (JSONObject)responseMap.get("jsonObject");
			if(Validator.isNotNull(responseData)) {
				if(responseData.has("message")) {
					response.put("jsonObject", responseData.getJSONArray("message"));
				}else {
					response.put("jsonObject", responseData.getJSONArray("responseData"));
				}
				
			}
			
			HttpServletResponse httpServletResponse = _portal.getHttpServletResponse(resourceResponse);

			ServletResponseUtil.write(httpServletResponse, response.toString());

			log.debug(" responseMap........" + responseMap);
		} catch (Exception exception) {
			resourceResponse.setProperty(ResourceResponse.HTTP_STATUS_CODE,
					String.valueOf(HttpServletResponse.SC_NOT_FOUND));
			if (log.isDebugEnabled()) {
				log.error(ParameterConstants.EXCEPTION, exception);
			}
		}

	}

	@Reference
	private Portal _portal;

	private Log log = LogFactoryUtil.getLog(UnclaimAmountMVCResourceCommand.class);

}
