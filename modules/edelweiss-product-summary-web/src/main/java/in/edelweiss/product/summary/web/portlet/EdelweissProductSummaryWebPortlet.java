package in.edelweiss.product.summary.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;

/**
 * @author jahanvi.doshi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.etli",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.inputmask.min.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Product Investment Summary",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/summary.jsp",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"javax.portlet.name=" + EdelweissProductSummaryWebPortletKeys.EDELWEISSPRODUCTSUMMARYWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissProductSummaryWebPortlet extends MVCPortlet {
	
	
}