package in.edelweiss.product.customize.web.mvc.commands;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
		"mvc.command.name=/edelweiss/saral-jeevan-bima/generate-pl" }, service = MVCResourceCommand.class)
public class SaralJeevanBimaGenerateProductListResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		String plRequestJsonString = ParamUtil.getString(resourceRequest, "plRequestJsonString");
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();

		try {
			JSONObject plResponseJSONObject = edelweissTokioCommonApi.invokeProductList(plRequestJsonString);
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY,
					plResponseJSONObject.getJSONObject(EdelweissCommonConstants.DATA_KEY));
			responseObj.put("plResponse", plResponseJSONObject);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error(e.getMessage());
			if(log.isDebugEnabled()) {
				e.printStackTrace();
			}
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();

		}

	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	private static final Log log = LogFactoryUtil.getLog(SaralJeevanBimaGenerateProductListResourceCommand.class);
}
