package in.edelweiss.become.an.advisor.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.become.an.advisor.constants.BecomeAnAdvisorPortletKeys;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.system.configurations.URLRelatedConfiguration;

/**
 * This portlet is used to render the become and advisor form
 * 
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Become An Advisor",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/become-an-advisor.jsp",
		"javax.portlet.name=" + BecomeAnAdvisorPortletKeys.BECOMEANADVISOR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BecomeAnAdvisorPortlet extends MVCPortlet {
	
	/**
	 * This method is used to render the become and advisor form & login URL.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * 
	 * @return void
	 * 
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		URLRelatedConfiguration urlRelatedConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
		String advisorLoginURL = urlRelatedConfiguration.getAdvisorLoginURL();
		
		renderRequest.setAttribute("advisorLoginURL", advisorLoginURL);
		
		super.render(renderRequest, renderResponse);
	}
}