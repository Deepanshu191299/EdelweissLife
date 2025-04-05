package in.edelweiss.premium.returns.caculator.web.portlet;

import in.edelweiss.premium.returns.caculator.web.constants.EdelweissPremiumReturnsCaculatorWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author krishna
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=EdelweissPremiumReturnsCaculatorWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissPremiumReturnsCaculatorWebPortletKeys.EDELWEISSPREMIUMRETURNSCACULATORWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissPremiumReturnsCaculatorWebPortlet extends MVCPortlet {
}