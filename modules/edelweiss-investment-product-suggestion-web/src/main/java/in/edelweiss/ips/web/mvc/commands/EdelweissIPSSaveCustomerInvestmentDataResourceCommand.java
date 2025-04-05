package in.edelweiss.ips.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

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
import in.edelweiss.ips.web.helper.EdelweissIPSHelper;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
	        "mvc.command.name=/save/customerInvestmentData"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissIPSSaveCustomerInvestmentDataResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissIPSSaveCustomerInvestmentDataResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		boolean isLeadIdExist = false;
		try {
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			if(Validator.isNotNull(leadId)) {
				log.debug("EdelweissIPSSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Saving Customer Investment Data ...");
				ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
				String portalURL =  themeDisplay.getPortalURL();
				HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
				Enumeration<String> enumeration = request.getParameterNames();
				Map<String, String> requestParametersMap = new  HashMap<>();
				Long customerInvestmentDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
				while(enumeration.hasMoreElements()){
		            String parameterName = enumeration.nextElement();
		            requestParametersMap.put(parameterName, ParamUtil.getString(resourceRequest, parameterName));
		        }
				log.debug("EdelweissIPSSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Customer Investment Data : " + requestParametersMap);
				
				CustomerInvestmentDetails customerInvestmentDetails = EdelweissIPSHelper.getRequestInvestmentDetails(requestParametersMap, leadId);
				
				CustomerInvestmentDetails updatedCustomerInvestmentDetails = edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, customerInvestmentDetails, customerInvestmentDetailsId);
				
				responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
				responseObj.put(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, updatedCustomerInvestmentDetails.getId());
			} else {
				log.debug("EdelweissIPSSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Lead ID does not exist .....");
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
				responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObj.put(EdelweissCommonConstants.MESSAGE_KEY, EdelweissCommonConstants.MESSAGE_LEAD_NOT_EXIST);
			}
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissIPSSaveCustomerInvestmentDataResourceCommand >>> serveResource >>> Error while saving Customer Investment Data : " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
