package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.MessageFormat;
import java.util.HashMap;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/get/BasicDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissGetBasicDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissGetBasicDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws PortletException {

		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String customerEnquiryId = ParamUtil.getString(resourceRequest, "customerEnquiryId");
		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
		if(Validator.isNotNull(leadId)) {
			try {
				String customerEnquiryAPIURL = themeDisplay.getPortalURL() + MessageFormat.format(EdelweissObjectConstants.GET_CUSTOMER_ENQUIRY_RELATIONAL_API + EdelweissObjectConstants.GET_CUSTOMER_ENQUIRY_INVESTMENT_RELATIONAL_DATA_QUERY_PARAM, String.valueOf(customerEnquiryId));
				
				log.debug("EdelweissGetBasicDetailsResourceCommand >>> serveResource >>>> url to get basic details ::: " + customerEnquiryAPIURL);
				
				JSONObject basicDetailsObject = etipCoreAPI.callGetAPI(new  HashMap<>(), customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
				resourceResponse.getWriter().write(basicDetailsObject.toString());
			} catch (ETIPSystemException e) {
				log.error("EdelweissGetBasicDetailsResourceCommand >>> serveResource >>> An error occurred getting the storing Basic details ::: " + e);
			} catch (Exception e) {
				log.error("EdelweissGetBasicDetailsResourceCommand >>> serveResource >>> IOException ::: " + e);
			}
		}
		
	}

	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	@Reference
	private ETIPCoreAPI etipCoreAPI;
}
