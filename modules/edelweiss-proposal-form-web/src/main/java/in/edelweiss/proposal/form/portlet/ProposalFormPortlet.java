package in.edelweiss.proposal.form.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;

import java.util.Map;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import in.edelweiss.proposal.form.company.model.CompanyList;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.proposal.form.dropdown.model.DropdownMaster;

/**
 * @author Abhijit AA
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=/js/main.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=ProposalForm",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/proposal_form/details_of_la.jsp",
		"javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ProposalFormPortlet extends MVCPortlet {
	
	@Activate
	protected void activate(Map<String, Object> properties) {
		logger.info(" ProposalFormPortlet .... deployed");
		WebCachePoolUtil.remove(CompanyList.class.getName());
		WebCachePoolUtil.remove(DropdownMaster.class.getName());
	}	
	
	private static final Log logger = LogFactoryUtil.getLog(ProposalFormPortlet.class);
}