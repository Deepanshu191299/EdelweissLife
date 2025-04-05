package in.edelweiss.product.enquiry.web.portlet.action;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.enquiry.web.constants.EdelweissProductEnquiryWebPortletKeys;

@Component(immediate = true, property = "javax.portlet.name="
		+ EdelweissProductEnquiryWebPortletKeys.EDELWEISSPRODUCTENQUIRYWEB, service = ConfigurationAction.class)
public class ProductEnquiryConfigAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {

		return "/configuration.jsp";
	}

	/*
	 * @Override
	 * 
	 * @Reference(target =
	 * "(osgi.web.symbolicname=in.edelweiss.product.enquiry.web)", unbind = "-")
	 * public void setServletContext(ServletContext servletContext) {
	 * super.setServletContext(servletContext); }
	 */

}
