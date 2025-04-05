package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/load/familyDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissLoadFamilyDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissLoadFamilyDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			log.debug("EdelweissLoadFamilyDetailsResourceCommand >>>> serveResource >>>> Loading family details...");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			
			JSONObject familyDetailsResponse = edelweissTokioCommonApi.getFamilyDetailsByLeadId(portalURL, leadId);

			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			if(Validator.isNotNull(familyDetailsResponse)) {
				responseObj.put("hasFamilyDetails", true);
				responseObj.put(EdelweissCommonConstants.DATA_KEY, familyDetailsResponse);
			} else {
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
				responseObj.put("hasFamilyDetails", false);
			}
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissLoadFamilyDetailsResourceCommand >>> serveResource >>> Error occure while fecthing Family Details ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
