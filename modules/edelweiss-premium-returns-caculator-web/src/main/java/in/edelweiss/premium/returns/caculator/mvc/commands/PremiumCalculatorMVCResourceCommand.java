package in.edelweiss.premium.returns.caculator.mvc.commands;

import com.edelweiss.http.core.api.ETIPCoreAPI;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.premium.returns.caculator.web.constants.EdelweissPremiumReturnsCaculatorWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissBJGenerateBIConfiguration;
import in.edelweiss.system.configurations.EdelweissOauth2GenerateTokenConfiguration;
import in.edelweiss.system.configurations.EdelweissRPGenerateBIConfiguration;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissPremiumReturnsCaculatorWebPortletKeys.EDELWEISSPREMIUMRETURNSCACULATORWEB,
		"mvc.command.name=/calculate_premium_return" }, service = MVCResourceCommand.class)
public class PremiumCalculatorMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {

		JSONObject biJson = JSONFactoryUtil.createJSONObject();
		HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(resourceResponse);
		try {

			EdelweissBJGenerateBIConfiguration edelweissBJGenerateBIConfiguration = ConfigurationProviderUtil
					.getSystemConfiguration(EdelweissBJGenerateBIConfiguration.class);

			EdelweissOauth2GenerateTokenConfiguration oauth2GenerateTokenConfiguration = ConfigurationProviderUtil
					.getSystemConfiguration(EdelweissOauth2GenerateTokenConfiguration.class);

			int apiType = ParamUtil.getInteger(resourceRequest, "apiType", 0);
			String biAPI = edelweissBJGenerateBIConfiguration.getBJGenerateBIURL();
			String xAPI = edelweissBJGenerateBIConfiguration.getBJGenerateBIXAPIKEY();
			String requestJson = ParamUtil.getString(resourceRequest, "requestJson");

			JSONObject biRequestJSONObject = JSONFactoryUtil.createJSONObject(requestJson);

			if (apiType == 1) {

				EdelweissRPGenerateBIConfiguration rpGenerateBIConfiguration = ConfigurationProviderUtil
						.getSystemConfiguration(EdelweissRPGenerateBIConfiguration.class);
				biAPI = rpGenerateBIConfiguration.getRPGenerateBIURL();
				xAPI = rpGenerateBIConfiguration.getRPGenerateBIXAPIKEY();

			}

			log.debug(" >>>>>>>>biRequestJSONObject>>>>>>>... " + biRequestJSONObject);

			JSONObject authorizations = JSONFactoryUtil
					.createJSONObject(etipCoreAPI.generateOauthToken(oauth2GenerateTokenConfiguration.getOAuth2QJURL(),
							oauth2GenerateTokenConfiguration.getOAuth2QJUsername(),
							oauth2GenerateTokenConfiguration.getOAuth2QJPassword()));

			String authToken = authorizations.getString(EdelweissCommonConstants.ACCESS_TOKEN);

			log.debug("PremiumCalculatorMVCActionCommand >>> doServeResource >>> authToken : " + authToken);

			JSONObject biResponseJSONObject = etipCoreAPI.callPostAPI(biRequestJSONObject, biAPI, true, xAPI,
					authToken);

			biJson.put("status", 200);

			biJson.put("biData", biResponseJSONObject);

			log.debug("PremiumCalculatorMVCActionCommand >>> doServeResource >>> BIResponse : " + biResponseJSONObject);

		} catch (Exception e) {
			biJson.put("status", 400);
			log.debug(
					"PremiumCalculatorMVCActionCommand >>> doServeResource >>> Error occured while genrating BI Request : "
							+ e.getMessage());
		}

		ServletResponseUtil.write(httpServletResponse, biJson.toJSONString());

	}

	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;

	private static final Log log = LogFactoryUtil.getLog(PremiumCalculatorMVCResourceCommand.class);
}
