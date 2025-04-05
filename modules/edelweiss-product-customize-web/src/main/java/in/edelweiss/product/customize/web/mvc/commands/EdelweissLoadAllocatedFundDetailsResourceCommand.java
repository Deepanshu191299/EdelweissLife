package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
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
import in.edelweiss.tokio.common.model.CustomerFundAllocationDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/load/allocatedFunds"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissLoadAllocatedFundDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissLoadAllocatedFundDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		try {
			log.debug("EdelweissLoadAllocatedFundDetailsResourceCommand >>>> serveResource >>>> Loading Allocated Customize Fund Details :::");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			
			boolean hasFundAllocated =  false;
			String fundAllocationDetailsStr = StringPool.BLANK;
			CustomerFundAllocationDetailsRel customerFundAllocationDetailsRel = edelweissTokioCommonApi.getFundAllocationDetailsByLeadId(portalURL, leadId);
			if(Validator.isNotNull(customerFundAllocationDetailsRel) && Validator.isNotNull(customerFundAllocationDetailsRel.getId())) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				fundAllocationDetailsStr = mapper.writeValueAsString(customerFundAllocationDetailsRel);
				log.debug("EdelweissLoadAllocatedFundDetailsResourceCommand >>>> serveResource >>>> Allocated Fund Details ::: " + fundAllocationDetailsStr);
				hasFundAllocated = true;
			} 
			
			
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put("hasFundAllocated", hasFundAllocated);
			if(hasFundAllocated) {
				responseObj.put("fundAllocationDetails", fundAllocationDetailsStr);
			}
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissLoadAllocatedFundDetailsResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
