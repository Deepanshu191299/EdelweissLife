package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/delete/familyDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissDeleteFamilyDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissDeleteFamilyDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			log.debug("EdelweissDeleteFamilyDetailsResourceCommand >>>> serveResource >>>> deleting family details...");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			Long customerInvestmentDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
			
			log.debug("EdelweissDeleteFamilyDetailsResourceCommand >>>> serveResource >>>> Fetching Customer Enquiry By Lead ID :  " + leadId);
			CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
			if(Validator.isNotNull(customerEnquiry) && Validator.isNotNull(customerEnquiry.getFamilyId())) {
				CustomerEnquiry customerEnquiryRequest = new CustomerEnquiry();
				customerEnquiryRequest.setFamilyId(StringPool.BLANK);
				
				// Update Family Id to blank in Customer Enquiry
				edelweissTokioCommonApi.updateCustomerEnquiryById(portalURL, customerEnquiryRequest, customerEnquiry.getId());
				
				//Delete Family Details
				edelweissTokioCommonApi.deleteFamilyDetailsById(portalURL, customerEnquiry.getFamilyId());
				
				boolean isFamilyFromPolicyOptions = ParamUtil.getBoolean(resourceRequest, EdelweissProductCustomizeConstants.PARAM_IS_FROM_POLICY_OPTIONS, false);
				log.debug("EdelweissDeleteFamilyDetailsResourceCommand >>>> serveResource >>>> Family From Policy Options : " + isFamilyFromPolicyOptions);
				if(!isFamilyFromPolicyOptions) {
					CustomerInvestmentDetails updateCustomerInvestmentDetails = new CustomerInvestmentDetails();
					updateCustomerInvestmentDetails.setLeadId(leadId);
					updateCustomerInvestmentDetails.setInvestingFor(EdelweissObjectConstants.MYSELF);
					edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, updateCustomerInvestmentDetails, customerInvestmentDetailsId);
				}
			} else {
				log.debug("EdelweissDeleteFamilyDetailsResourceCommand >>>> serveResource >>>> No Such Family Details found by lead ID : " + leadId);
			}
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
		} catch (ETIPSystemException e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissDeleteFamilyDetailsResourceCommand >>> serveResource >>> An error occurred deleting family details ::: " + e);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissDeleteFamilyDetailsResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
