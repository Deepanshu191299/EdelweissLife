package in.edelweiss.product.enquiry.web.portlet;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalServiceUtil;
import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.product.enquiry.web.constants.EdelweissProductEnquiryWebPortletKeys;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

/**
 * @author krishna
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.Products",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Edelweiss Funds Enquiry Web", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/funds/view.jsp",
		"javax.portlet.name=" + EdelweissProductEnquiryWebPortletKeys.EDELWEISSFUNDSENQUIRYWEBPORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"com.liferay.portlet.private-session-attributes=false"		
}, service = Portlet.class)
public class EdelweissFundsEnquiryWebPortlet extends MVCPortlet {

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			ListTypeDefinition listTypeDefinition = ListTypeDefinitionLocalServiceUtil
					.fetchListTypeDefinitionByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC,
							PortalUtil.getCompanyId(renderRequest));

			List<ListTypeEntry> entries = ListTypeEntryLocalServiceUtil
					.getListTypeEntries(listTypeDefinition.getListTypeDefinitionId(), -1, -1);
			renderRequest.setAttribute("entries", entries);
		} catch (Exception e) {
			log.error(" Your Income Pick List Not found" + e.getMessage());
		}

		super.render(renderRequest, renderResponse);
	}

	private static final Log log = LogFactoryUtil.getLog(EdelweissFundsEnquiryWebPortlet.class);
}