package in.edelweiss.ips.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.ips.web.constants.EdelweissIPSWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
	        "mvc.command.name=/update/LMSLead"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissUpdateIPSLMSLeadResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissUpdateIPSLMSLeadResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		PrintWriter writer = resourceResponse.getWriter();
		boolean isLeadIdExist = false;
		try {
			log.debug("EdelweissUpdateIPSLMSLeadResourceCommand >>>> serveResource >>>> LMS Updating...");
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL = themeDisplay.getPortalURL();
			
			if(Validator.isNotNull(leadId)) {
				isLeadIdExist = true;
				HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
				Enumeration<String> enumeration = request.getParameterNames();
				Map<String, String> updateLMSRquestMapData = new  HashMap<>();
				
				updateLMSRquestMapData.put(LEAD_ID, leadId);
				while(enumeration.hasMoreElements()){
		            String parameterName = enumeration.nextElement();
		            updateLMSRquestMapData.put(parameterName, ParamUtil.getString(resourceRequest, parameterName));
		        }
				log.debug("EdelweissUpdateIPSLMSLeadResourceCommand >>>> serveResource >>>> LMS Updating..." + updateLMSRquestMapData.toString());
				
				updateLMSRquestMapData.put(LEAD_ID, leadId);
				updateLMSRquestMapData.put(EdelweissCommonConstants.LEAD_FORM_TYPE, EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION);
				updateLMSRquestMapData.put(EdelweissCommonConstants.QUOTE_STAGE, EdelweissCommonConstants.QUOTE_STAGE_IPS);
				updateLMSRquestMapData.put(EdelweissCommonConstants.STAGE_INTEREST_SESSION, EdelweissCommonConstants.STAGE_INTEREST_SESSION_PRE_QUOTE);
				updateLMSRquestMapData.put(EdelweissCommonConstants.SITE_SECTION, EdelweissCommonConstants.SITE_SECTION_PRODUCT_PAGE);
				
				String leadUpdateRequestBody = edelweissTokioCommonApi.getUpdateLMSRequestBody(updateLMSRquestMapData);
				log.debug("EdelweissUpdateIPSLMSLeadResourceCommand >>>> serveResource >>>> LMS Lead Update Request Body prepared Successully.....");
	
				JSONObject responseLeadUpdateJSON = edelweissTokioCommonApi.updateLMSLead(leadUpdateRequestBody);
				log.debug("EdelweissUpdateLMSLeadResourceCommand >>>> serveResource >>>> Lead Updated Successully.....");
				
				String customerEnquiryId = updateLMSRquestMapData.getOrDefault(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID, StringPool.BLANK);
				if(Validator.isNotNull(customerEnquiryId)) {
					edelweissTokioCommonApi.updateCustomerEnquiryLMSResponse(portalURL, responseLeadUpdateJSON, customerEnquiryId);
				}
				
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
				responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObj.put(EdelweissCommonConstants.DATA_KEY, responseLeadUpdateJSON);
			} else {
				log.debug("EdelweissUpdateIPSLMSLeadResourceCommand >>>> serveResource >>>> Lead ID does not exist .....");
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
				responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObj.put(EdelweissCommonConstants.MESSAGE_KEY, EdelweissCommonConstants.MESSAGE_LEAD_NOT_EXIST);
			}
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
			log.error("EdelweissUpdateIPSLMSLeadResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
