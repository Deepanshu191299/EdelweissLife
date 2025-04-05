package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.CUSTOMIZE_URL;
import static in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants.SUMMARY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.product.customize.web.helper.EdelweissProductCustomizeHelper;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	        "javax.portlet.name="+ EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/save/investmentDetails"
	    },
	    service = MVCActionCommand.class
	)
public class EdelweissProductCustomizeActionCommand extends BaseMVCActionCommand {

	private static Log log = LogFactoryUtil.getLog(EdelweissProductCustomizeActionCommand.class);

	@Reference
	private ETIPCoreAPI eTIPCoreAPI;
	
	@Reference
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
	
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		log.debug("EdelweissProductCustomizeActionCommand >>> doProcessAction >>> Saving Customize Screen Data ::: ");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portalURL = themeDisplay.getPortalURL();
		
		Long customerInvestmentDetailsId = ParamUtil.getLong(actionRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
		Long customerPolicyDetailsId = ParamUtil.getLong(actionRequest, EdelweissCommonConstants.CUSTOMER_POLICY_DETAILS_ID, 0);
		
		HttpServletRequest request = PortalUtil.getHttpServletRequest(actionRequest);
		Enumeration<String> enumeration = request.getParameterNames();
		Map<String, String> requestParametersMap = new  HashMap<>();
		while(enumeration.hasMoreElements()){
            String parameterName = enumeration.nextElement();
            requestParametersMap.put(parameterName, ParamUtil.getString(actionRequest, parameterName));
            log.debug(parameterName+": "+ParamUtil.getString(actionRequest, parameterName));
        }
		
		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(actionRequest, LEAD_ID);
		log.debug("leadId: "+leadId);
		
		CustomerInvestmentDetails customerInvestmentDetails = EdelweissProductCustomizeHelper.getRequestInvestmentDetails(requestParametersMap, leadId);
		log.debug("customerInvestmentDetails: "+customerInvestmentDetails.toString());
		
		CustomerPolicyDetailsRel customerPolicyDetailsRel = edelweissTokioCommonApi.getRequestPolicyDetails(requestParametersMap, leadId);
		log.debug("customerPolicyDetailsRel: "+customerPolicyDetailsRel.toString());
		
		edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, customerInvestmentDetails, customerInvestmentDetailsId);
		
		edelweissTokioCommonApi.updatePolicyDetailsById(portalURL, customerPolicyDetailsRel, customerPolicyDetailsId);
		
		try {
			
			String productId = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
					Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
			log.debug("productId: "+productId);
			String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
					Layout.class.getName(), themeDisplay.getPlid(), EdelweissObjectConstants.PRODUCT_NAME);
			log.debug("productName: "+productName);
			
			Map<String, Serializable> values = new HashMap<>();
			values.put("quoteStage", EdelweissCommonConstants.QUOTE_STAGE_CUSTOMISE);
			values.put("productId", productId);
			values.put("productName", productName);

			edelweissTokioCommonApi.updateCustomerQuoteStage(values,
					Long.valueOf(customerInvestmentDetails.getrCustomerInvestmentDetailsRelCCustomerEnquiryId()),
					ServiceContextFactory.getInstance(actionRequest));
		} catch (Exception e) {
		}

		String redirectURL = themeDisplay.getURLCurrent();
		redirectURL = redirectURL.split(CUSTOMIZE_URL)[0];
		redirectURL = redirectURL + SUMMARY_URL;
		hideDefaultErrorMessage(actionRequest);
		hideDefaultSuccessMessage(actionRequest);
		actionResponse.sendRedirect(redirectURL);
	}
}
