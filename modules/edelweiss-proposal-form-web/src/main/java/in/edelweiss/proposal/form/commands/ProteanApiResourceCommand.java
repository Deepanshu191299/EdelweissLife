package in.edelweiss.proposal.form.commands;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
		"mvc.command.name=/protean/ekyc" }, service = MVCResourceCommand.class)
public class ProteanApiResourceCommand extends BaseMVCResourceCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {

		JSONObject wrapperApiRequest = JSONFactoryUtil.createJSONObject();
		JSONObject dataObj = JSONFactoryUtil.createJSONObject();
		
		String name = ParamUtil.getString(resourceRequest, "name","");
		String gender = ParamUtil.getString(resourceRequest, "gender","");
		String dob = ParamUtil.getString(resourceRequest, "dob","");
		String returnUrl = ParamUtil.getString(resourceRequest, "returnUrl","");
		String isLaPrSps = ParamUtil.getString(resourceRequest, "isLaPrSps","");
		UUID txnId = UUID.randomUUID();
		
		logger.debug("txnId   : "+txnId);
		
		wrapperApiRequest.put("txnId", txnId);
		wrapperApiRequest.put("source", "WEBSITE");
		wrapperApiRequest.put("redirectURL", returnUrl); 
		dataObj.put("name", name);
		dataObj.put("gender", gender);
		dataObj.put("dob", getDateInAPIFormat(dob));
		wrapperApiRequest.put("data", dataObj);

		logger.debug("wrapperApiRequest : "+wrapperApiRequest);

		//String apiResponse = EdelweissAPIUtil.getEKycLinkURLByAPI(wrapperApiRequest);
		String apiResponse = edelweissTokioCommonApi.getEKycLinkURLByAPI(wrapperApiRequest.toJSONString());
		logger.debug("apiResponse : "+apiResponse);
		
		logger.debug("Inside doServeResource of ProteanApiResourceCommand");
		
		PortletSession _session = resourceRequest.getPortletSession();
		_session.setAttribute("txnId", txnId.toString(), PortletSession.PORTLET_SCOPE);
		_session.setAttribute("isLaPrSps", isLaPrSps, PortletSession.PORTLET_SCOPE);
		resourceResponse.getWriter().write(apiResponse);
	}

	public String getDateInAPIFormat(String date) {
	
		if(null!=date && date.trim().length()!=0) {
			String[] dateArr = date.split("/");	
			date = dateArr[2]+"-"+dateArr[1]+"-"+dateArr[0];
		}
		
		return date;
	}

	private static final Log logger = LogFactoryUtil.getLog(ProteanApiResourceCommand.class);
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
