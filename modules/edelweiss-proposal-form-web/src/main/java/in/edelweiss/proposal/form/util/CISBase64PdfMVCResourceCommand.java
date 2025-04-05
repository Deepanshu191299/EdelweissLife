package in.edelweiss.proposal.form.util;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.StringPool;
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

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.proposal.form.constants.ProposalFormPortletKeys;

@Component(property = { "javax.portlet.name=" + ProposalFormPortletKeys.PROPOSALFORM,
"mvc.command.name=/cis/sendpdf" }, service = MVCResourceCommand.class)
public class CISBase64PdfMVCResourceCommand extends BaseMVCResourceCommand{
	@Reference
	ETIPCoreAPI etipCoreApi;
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug("insideCISBase64PdfMVCResourceCommand:::: " );
		
		String applicationNumber = ParamUtil.getString(resourceRequest, "applicationNumber");
		String cisdata = ParamUtil.getString(resourceRequest, "cisdata");
		logger.debug("applicationNUmber::::::::"+ applicationNumber);
		logger.debug("cisdata::::::" + cisdata);
		
		if ((Validator.isNotNull(applicationNumber)|| !applicationNumber.equals(StringPool.BLANK)) && (Validator.isNotNull(cisdata)|| !applicationNumber.equals(StringPool.BLANK)) ) {
			String cisPushURL = ConfigurationUtil.getProposalFormAPIURLConfiguration().getPostCisDocument();
			logger.debug("pfURL... "+cisPushURL);
			
			JSONObject cisPushDocRequestJson = JSONFactoryUtil.createJSONObject();
			cisPushDocRequestJson.put("applicationNumber", applicationNumber);
			cisPushDocRequestJson.put("cisdata", cisdata);
			
    		JSONObject cisPushDocumentResponse = etipCoreApi.callPostAPI(cisPushDocRequestJson,cisPushURL, true, "", "");
    		logger.debug("cisPushDocumentResponse::::::::::: " + cisPushDocumentResponse);
    		
    		resourceResponse.setContentType("application/json");
 		    PrintWriter out = resourceResponse.getWriter();
 		    out.println(cisPushDocumentResponse.toString());
 		 }
		
	}
	private static final Log logger = LogFactoryUtil.getLog(CISBase64PdfMVCResourceCommand.class);
}
