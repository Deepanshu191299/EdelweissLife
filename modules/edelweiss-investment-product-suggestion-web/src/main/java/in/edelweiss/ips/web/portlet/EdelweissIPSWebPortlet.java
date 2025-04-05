package in.edelweiss.ips.web.portlet;

import in.edelweiss.ips.web.constants.EdelweissIPSWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ravi
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.etli",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.inputmask.min.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/basic_detail.js",
		"com.liferay.portlet.footer-portlet-javascript=/js/investment-product-suggestion.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Investment Product Suggestion",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissIPSWebPortlet extends MVCPortlet {
}