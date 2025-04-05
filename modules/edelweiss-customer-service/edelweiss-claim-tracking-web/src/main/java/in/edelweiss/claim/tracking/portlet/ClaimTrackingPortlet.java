package in.edelweiss.claim.tracking.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.claim.tracking.constants.ClaimTrackingPortletKeys;

/**
 * @author Abhijit AA
 * 
 * The portlet will render the Claim Tracking Form.
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Claim Tracking",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/claim-tracking.jsp",
		"javax.portlet.name=" + ClaimTrackingPortletKeys.CLAIMTRACKING,
		"javax.portlet.version=3.0",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ClaimTrackingPortlet extends MVCPortlet {
	
}