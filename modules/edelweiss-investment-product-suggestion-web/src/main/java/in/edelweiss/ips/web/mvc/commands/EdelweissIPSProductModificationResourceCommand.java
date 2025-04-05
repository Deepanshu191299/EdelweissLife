package in.edelweiss.ips.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.ips.web.constants.EdelweissIPSWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
	        "mvc.command.name=/edelweiss-ips/product-modification"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissIPSProductModificationResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissIPSProductModificationResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("EdelweissIPSProductModificationResourceCommand >>> doServeResource >>> Invoking Product Modification API ...");
		
		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
		
		String productModificationRequestData = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.PRODUCT_MODIFICATION_REQUEST_DATA_PARAM);
		log.debug("EdelweissIPSProductModificationResourceCommand >>> doServeResource >>> Product Modification API Request : " + productModificationRequestData);
		
		PrintWriter writer = resourceResponse.getWriter();
		boolean isLeadIdExist = false;
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		try {
			
			if(Validator.isNotNull(leadId)) {
				isLeadIdExist = true;
				JSONObject productModificationResponseJSONObject = edelweissTokioCommonApi.invokeProductModification(productModificationRequestData);
				log.debug("EdelweissIPSProductModificationResourceCommand >>> doServeResource >>> Product Modification API Response : " + productModificationResponseJSONObject.toString());
				writer.write(productModificationResponseJSONObject.toString());
			} else {
				log.debug("EdelweissIPSProductModificationResourceCommand >>>> serveResource >>>> Lead ID does not exist .....");
				responseObject.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
				responseObject.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObject.put(EdelweissCommonConstants.MESSAGE_KEY, EdelweissCommonConstants.MESSAGE_LEAD_NOT_EXIST);
				writer.write(responseObject.toString());
			}
		} catch(Exception e) {
			responseObject.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
			responseObject.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			writer.write(responseObject.toString());
			log.debug("EdelweissIPSProductModificationResourceCommand >>> doServeResource >>> Error occured while Calling Product Modification API : " + e);
		}  finally {
			writer.close();
		}
		
	}
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
}
