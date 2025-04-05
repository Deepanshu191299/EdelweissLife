package in.edelweiss.raise.a.claim.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.TreeMap;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.raise.a.claim.constants.RaiseAClaimPortletKeys;

/**
 * @author Abhijit AA
 * 
 * The portlet will render the Raise a Claim Form.
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Raise A Claim",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/raise-claim-form.jsp",
		"javax.portlet.name=" + RaiseAClaimPortletKeys.RAISEACLAIM,
		"javax.portlet.version=3.0",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RaiseAClaimPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		
		try {
		
			ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
			
			String claimType = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getClaimTypePickListExternalReferenceCode().trim();
			TreeMap<String, String> claimTypes = new TreeMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), claimType));
			
			String validIDProof = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getValidIDProofsExternalReferenceCode().trim();
			TreeMap<String, String> validIDProofs = new TreeMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), validIDProof));
			
		    String addressType = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getAddressTypePickListExternalReferenceCode().trim();
			TreeMap<String, String> addressTypes = new TreeMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), addressType));
			
			
			String documentExtensions = ConfigurationUtil.getCommonConfiguration().getDocumentExtensions();
			
			String recentPhotoExtensions = ".jpg,.jpeg,.png";
			
			renderRequest.setAttribute("validIDProofs", validIDProofs);
			renderRequest.setAttribute("recentPhotoExtensions", recentPhotoExtensions);
			renderRequest.setAttribute("documentExtensions", documentExtensions);
			renderRequest.setAttribute("claimTypes", claimTypes);
			renderRequest.setAttribute("addressTypes", addressTypes);
		
		}catch (Exception exception) {
			logger.error("Exception occured while rendering raise a claim form : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		
		super.render(renderRequest, renderResponse);
	}
	
	private Log logger = LogFactoryUtil.getLog(this.getClass());
}