package in.edelweiss.register.enach.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys;
import in.edelweiss.system.configurations.URLRelatedConfiguration;

/**
 * This portlet will land the user to failure page of Enach registration.
 * 
 * @author Abhijit AA
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EnachFailure", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/enach-failure.jsp",
		"javax.portlet.name=" + RegisterEnachPortletKeys.ENACHFAILURE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class EnachFailurePortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		URLRelatedConfiguration urlRelatedConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
		String enachURL = themeDisplay.getURLHome() + urlRelatedConfiguration.getRegisterEnachPageURL();
		renderRequest.setAttribute("enachURL", enachURL);

		super.render(renderRequest, renderResponse);
	}
	
}