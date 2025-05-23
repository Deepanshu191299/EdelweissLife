package in.edelweiss.resume.application.portlet;

import in.edelweiss.resume.application.constants.ResumeApplicationPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ResumeApplication",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/resume-application.jsp",
		"javax.portlet.name=" + ResumeApplicationPortletKeys.RESUMEAPPLICATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ResumeApplicationPortlet extends MVCPortlet {
}