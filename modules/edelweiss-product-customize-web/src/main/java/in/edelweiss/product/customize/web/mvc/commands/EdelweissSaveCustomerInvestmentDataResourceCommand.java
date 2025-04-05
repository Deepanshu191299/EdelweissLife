package in.edelweiss.product.customize.web.mvc.commands;

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

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.product.customize.web.helper.EdelweissProductCustomizeHelper;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/save/customerInvestmentData"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissSaveCustomerInvestmentDataResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissSaveCustomerInvestmentDataResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			log.debug("EdelweissSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Saving Customer Investment Data :::");
			boolean isLeadIdExist = false;
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			if(Validator.isNotNull(leadId)) {
				HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
				Enumeration<String> enumeration = request.getParameterNames();
				Map<String, String> requestParametersMap = new  HashMap<>();
				Long customerInvestmentDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
				Long customerPolicyDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_POLICY_DETAILS_ID, 0);
				while(enumeration.hasMoreElements()){
		            String parameterName = enumeration.nextElement();
		            requestParametersMap.put(parameterName, ParamUtil.getString(resourceRequest, parameterName));
		        }
				log.debug("EdelweissSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Customer Investment Data ::: " + requestParametersMap);
				
				CustomerInvestmentDetails customerInvestmentDetails = EdelweissProductCustomizeHelper.getRequestInvestmentDetails(requestParametersMap, leadId);
				
				CustomerPolicyDetailsRel customerPolicyDetailsRel = edelweissTokioCommonApi.getRequestPolicyDetails(requestParametersMap, leadId);
							
				CustomerInvestmentDetails updatedCustomerInvestmentDetails = edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, customerInvestmentDetails, customerInvestmentDetailsId);
				
				CustomerPolicyDetailsRel updatedCustomerPolicyDetailsRel = edelweissTokioCommonApi.updatePolicyDetailsById(portalURL, customerPolicyDetailsRel, customerPolicyDetailsId);
				
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
				responseObj.put(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, updatedCustomerInvestmentDetails.getId());
				responseObj.put(EdelweissCommonConstants.CUSTOMER_POLICY_DETAILS_ID, updatedCustomerPolicyDetailsRel.getId());
			} else {
				log.debug("EdelweissSaveCustomerInvestmentDataResourceCommand >>>> serveResource >>>> Lead ID does not exist :::");
				responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
				responseObj.put(EdelweissCommonConstants.IS_LEAD_ID_EXIST, isLeadIdExist);
				responseObj.put(EdelweissCommonConstants.MESSAGE_KEY, EdelweissCommonConstants.MESSAGE_LEAD_NOT_EXIST);
			}
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissCustomerInvetmentDataResourceCommand >>> serveResource >>> ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
