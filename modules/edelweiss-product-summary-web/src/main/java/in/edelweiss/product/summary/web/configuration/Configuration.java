package in.edelweiss.product.summary.web.configuration;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;

@Component(immediate = true, property = "javax.portlet.name="
		+ EdelweissProductSummaryWebPortletKeys.EDELWEISSPRODUCTSUMMARYWEB, service = ConfigurationAction.class)
public class Configuration extends DefaultConfigurationAction{

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {

		return "/configuration/configuration.jsp";
	}
}