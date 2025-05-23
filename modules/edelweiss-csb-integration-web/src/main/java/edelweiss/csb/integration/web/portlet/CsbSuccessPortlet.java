package edelweiss.csb.integration.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.system.configurations.URLRelatedConfiguration;



@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=CsbSuccess", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/CsbIntegration/csb-success.jsp",
		"javax.portlet.name=" + EdelweissCsbIntegrationWebPortletKeys.CSBSUCCESS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class CsbSuccessPortlet extends MVCPortlet {
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		URLRelatedConfiguration urlRelatedConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
		String csbURL = themeDisplay.getURLHome() + "/csb-integration";
		renderRequest.setAttribute("renewalURL", csbURL);

		super.render(renderRequest, renderResponse);
	}

}
