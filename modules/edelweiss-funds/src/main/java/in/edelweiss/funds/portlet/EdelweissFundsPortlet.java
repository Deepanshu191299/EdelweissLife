package in.edelweiss.funds.portlet;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.funds.constants.EdelweissFundsPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;

/**
 * @author krishna
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EdelweissFunds", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissFundsPortletKeys.EDELWEISSFUNDS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EdelweissFundsPortlet extends MVCPortlet {

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {

		try {
			JSONObject cagrResponse = edelweissTokioCommonApi.getCAGRData();
			JSONArray fundReponseJSONArray = cagrResponse.getJSONArray("responseData");
			ServletResponseUtil.write(PortalUtil.getHttpServletResponse(resourceResponse),
					fundReponseJSONArray.toString());
		} catch (Exception exception) {
			log.error("Exception in EdelweissFundsPortlet >>>> " + exception.getMessage());
			if (log.isDebugEnabled()) {
				log.error("Exception", exception);
			}
		}

		super.serveResource(resourceRequest, resourceResponse);
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	private Log log = LogFactoryUtil.getLog(EdelweissFundsPortlet.class);
}