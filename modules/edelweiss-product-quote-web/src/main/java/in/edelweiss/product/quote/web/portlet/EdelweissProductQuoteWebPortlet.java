package in.edelweiss.product.quote.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import javax.portlet.Portlet;
import org.osgi.service.component.annotations.Component;
import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
/**
 * @author jahanvi.doshi
 */
@Component(immediate = true, property = { 
		"com.liferay.portlet.display-category=category.etli",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/jquery.inputmask.min.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Product Pre-Quote", 
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/wealth_rise_plus.jsp", 
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.private-session-attributes=false"
}, service = Portlet.class)
public class EdelweissProductQuoteWebPortlet extends MVCPortlet {

}
