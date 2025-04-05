package in.edelweiss.claim.tracking.upload.mvc;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.util.List;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.claim.tracking.constants.ClaimTrackingPortletKeys;
import in.edelweiss.claim.tracking.modals.ClaimTrackerModel;
import in.edelweiss.claim.tracking.modals.ClaimTrackerServiceImpl;

@Component(
		immediate = true, 
		property = {
		"javax.portlet.name=" + ClaimTrackingPortletKeys.CLAIMTRACKING,
		"mvc.command.name=/tracking/submit/claimTracker"
		},
		service = MVCResourceCommand.class)
public class SubmitClaimTrackerResourceCommand extends BaseMVCResourceCommand {
	private static final Log log = LogFactoryUtil.getLog(SubmitClaimTrackerResourceCommand.class);
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		log.info("SubmitClaimTrackerResourceCommand called");
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String claimReferenceNo = ParamUtil.getString(resourceRequest, "trackingNumber").toUpperCase();
		JSONObject response = JSONFactoryUtil.createJSONObject();
		boolean status = false;
		String message = StringPool.BLANK;
		
		log.info("claimReferenceNo "+claimReferenceNo);
		
		String[] selectedObjectFieldNames = null;
		List<ClaimTrackerModel> entries = ClaimTrackerServiceImpl.findClaimTrackerDataBySearchTerm(claimReferenceNo, selectedObjectFieldNames);

		if(entries.size() == 0) {
    		status = false;
    		message = "Given claim reference number not found";
 	    }
		
		for(ClaimTrackerModel entry : entries) {
			status = true;
    		message = entry.getFinalDecision();
    		log.info("getObjectEntryId "+entry.getObjectEntryId());
    		log.info("getClaimReferenceNo "+entry.getClaimReferenceNo());
    		log.info("getClaimType "+entry.getClaimType());
    		log.info("getFinalDecision "+entry.getFinalDecision());
	    }
		
		log.info("message "+message);
		response.put("isStatusApproved", status);
        response.put("responseData", message);
		resourceResponse.setContentType("application/json");
        PrintWriter writer = resourceResponse.getWriter();
        writer.print(response);
	}
}
