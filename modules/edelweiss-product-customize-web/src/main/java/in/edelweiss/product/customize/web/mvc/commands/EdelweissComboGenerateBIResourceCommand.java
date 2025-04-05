package in.edelweiss.product.customize.web.mvc.commands;

import com.edelweiss.http.core.api.ETIPCoreAPI;
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

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

/**
 * capital Secure Plus Generate BI
 */
@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/combo/generate-bi"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissComboGenerateBIResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissComboGenerateBIResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("EdelweissComboGenerateBIResourceCommand >>> doServeResource >>> Generating BI Request :::");

		String biRequestJsonString = ParamUtil.getString(resourceRequest, "biRequestData");
		
		try {
			
			JSONObject biJson = JSONFactoryUtil.createJSONObject(biRequestJsonString);

			JSONObject partnerData = edelweissTokioCommonApi.getPartnerDataByLeadId(resourceRequest);
			log.debug("EdelweissComboGenerateBIResourceCommand >>> doServeResource >>> partnerData data :: " + partnerData);
			
				if (Validator.isNotNull(partnerData) && partnerData.getBoolean("isValidPartner")) {
					
					biJson.getJSONObject("customerDetails").getJSONObject("agentDetails").put("AGENT_ID", partnerData.getString("agentId"));
					biJson.getJSONObject("productDetails").getJSONArray("product").getJSONObject(0).put("category", partnerData.getString("category"));
					biJson.getJSONObject("productDetails").getJSONArray("product").getJSONObject(0).put("PR_CHANNEL", partnerData.getString("channel"));
					biRequestJsonString = biJson.toString();
				}

		} catch (Exception e1) {
			log.error(" EdelweissComboGenerateBIResourceCommand Partner error >>>>>>>>>>>>>>>>>>>>>>> ::::", e1);
		}
		
		log.debug("EdelweissComboGenerateBIResourceCommand >>> doServeResource >>> BiRequest JSON String ::: " + biRequestJsonString);
		
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			JSONObject biResponseJSONObject = edelweissTokioCommonApi.invokeComboGenerateBi(biRequestJsonString);
			//log.debug("EdelweissComboGenerateBIResourceCommand >>> doServeResource >>> BiResponse JSON String ::: " + biResponseJSONObject.toJSONString());
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, biResponseJSONObject);
			log.debug(" EdelweissComboGenerateBIResourceCommand >>>>>>> End :::");
		} catch(Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissComboGenerateBIResourceCommand >>> doServeResource >>> Error occured while generating BI Request ::: " + e);
		}  finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
		
	}
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
}
