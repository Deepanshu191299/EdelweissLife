package edelweiss.campaign.pages.web.configurations;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import edelweiss.campaign.pages.web.constants.EdelweissCampaignPagesWebPortletKeys;

@Component(immediate = true, property = "javax.portlet.name="
		+ EdelweissCampaignPagesWebPortletKeys.EDELWEISSCAMPAIGNPAGESWEB, service = ConfigurationAction.class)
public class CampaignPagesConfigAction extends DefaultConfigurationAction {

	@Override
	public String getJspPath(HttpServletRequest httpServletRequest) {
		return "/portlet-configurations/configuration.jsp";
	}

	/*
	 * @Override
	 * 
	 * @Reference(target = "(osgi.web.symbolicname=edelweiss.campaign.pages.web)",
	 * unbind = "-") public void setServletContext(ServletContext servletContext) {
	 * super.setServletContext(servletContext); }
	 */

}
