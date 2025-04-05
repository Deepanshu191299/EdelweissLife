package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerFundAllocationDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/edelweiss/customize-funds"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissCustomFundsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissCustomFundsResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("EdelweissCustomFundsResourceCommand >>> doServeResource >>> Loading customize funds :::");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portalURL =  themeDisplay.getPortalURL();
		boolean hasFundAllocated =  false;
		String fundAllocationDetailsStr = StringPool.BLANK;
		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);

		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			JSONObject cagrResponse = edelweissTokioCommonApi.getCAGRData();
			JSONArray fundReponseJSONArray = cagrResponse.getJSONArray(EdelweissProductCustomizeConstants.CAGR_RESPONSE);
			CustomerFundAllocationDetailsRel customerFundAllocationDetailsRel = edelweissTokioCommonApi.getFundAllocationDetailsByLeadId(portalURL, leadId);
			if(Validator.isNotNull(customerFundAllocationDetailsRel) && Validator.isNotNull(customerFundAllocationDetailsRel.getId())) {
				fundAllocationDetailsStr = customerFundAllocationDetailsRel.getFundDetails();
				hasFundAllocated = true;
			} 
			
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, fundReponseJSONArray);
			responseObj.put(EdelweissProductCustomizeConstants.HAS_FUND_ALLOCATED, hasFundAllocated);
			if(hasFundAllocated) {
				responseObj.put(EdelweissProductCustomizeConstants.FUND_DETAILS, fundAllocationDetailsStr);
			}
		} catch(ETIPSystemException e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissCustomFundsResourceCommand >>> doServeResource >>> Error occured while fetching CAGR Response ::: " + e);
		} catch(Exception e) {
			log.error("EdelweissCustomFundsResourceCommand >>> doServeResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
