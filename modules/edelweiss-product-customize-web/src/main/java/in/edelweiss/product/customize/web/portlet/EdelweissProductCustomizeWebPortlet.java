package in.edelweiss.product.customize.web.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.etli",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.inputmask.min.js",
		"com.liferay.portlet.header-portlet-javascript=/js/common.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Product Customized Investment",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissProductCustomizeWebPortlet extends MVCPortlet {
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissProductCustomizeWebPortlet.class);
	
}