package in.edelweiss.update.contact.detail.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.update.contact.detail.constants.UpdateContactDetailPortletKeys;

/**
 * @author Abhijit AA
 * 
 * The portlet will render the Update Contact Detail Form.
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=UpdateContactDetail",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/update-contact-detail.jsp",
		"javax.portlet.name=" + UpdateContactDetailPortletKeys.UPDATECONTACTDETAIL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class UpdateContactDetailPortlet extends MVCPortlet {
}