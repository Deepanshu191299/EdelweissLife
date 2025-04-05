package in.edelweiss.product.customize.web.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;

@Component(immediate = true, property = "javax.portlet.name="
		+ EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB, service = ConfigurationAction.class)
public class Configuration extends DefaultConfigurationAction{

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {

		return "/configuration.jsp";
	}
}