package in.edelweisslife.sitemap.component.portlet;

import in.edelweisslife.sitemap.component.constants.EdelweisslifeSitemapComponentPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author deepanshu.verma
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EdelweisslifeSitemapComponent",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweisslifeSitemapComponentPortletKeys.EDELWEISSLIFESITEMAPCOMPONENT,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweisslifeSitemapComponentPortlet extends MVCPortlet {
}