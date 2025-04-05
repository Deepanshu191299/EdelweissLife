package edelweiss.campaign.pages.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import edelweiss.campaign.pages.web.constants.EdelweissCampaignPagesWebPortletKeys;

/**
 * @author Anupam
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.etli",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Product Campaign Pages",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.config-template=/portlet-configurations/configuration.jsp",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EdelweissCampaignPagesWebPortletKeys.EDELWEISSCAMPAIGNPAGESWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EdelweissCampaignPagesWebPortlet extends MVCPortlet {}