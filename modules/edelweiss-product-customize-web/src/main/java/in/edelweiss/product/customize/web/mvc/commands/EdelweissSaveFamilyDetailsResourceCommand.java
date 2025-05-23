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
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/save/familyDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissSaveFamilyDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissSaveFamilyDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		try {
			log.debug("EdelweissSaveFamilyDetailsResourceCommand >>> serveResource >>> updating family details :::");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			
			Long customerInvestmentDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
			String customerFamilyDetailsId = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, StringPool.BLANK);
			
			CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
			
			if(Validator.isNotNull(customerEnquiry)) {
				CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
				String assuredRelationFullName = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.ASSURED_FULL_NAME, StringPool.BLANK);
				String assuredRelation = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.ASSURED_RELATION, StringPool.BLANK);
				String assuranceDob = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.ASSURED_DATE_OF_BIRTH, StringPool.BLANK);
				String assuranceGender = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.ASSURED_GENDER, StringPool.BLANK);
				customerFamilyDetails.setAssuranceFullName(assuredRelationFullName);
				customerFamilyDetails.setAssuranceDob(assuranceDob);
				customerFamilyDetails.setAssuredRelation(assuredRelation);
				customerFamilyDetails.setGender(assuranceGender);
				customerFamilyDetails.setLeadId(leadId);
				customerFamilyDetails.setrCustomerFamilyDetailsRelCCustomerEnquiryId(customerEnquiry.getId());
				
				String isFamilyFromPolicyOptions = ParamUtil.getString(resourceRequest, EdelweissProductCustomizeConstants.PARAM_IS_FROM_POLICY_OPTIONS, StringPool.BLANK);
				if(Validator.isNotNull(isFamilyFromPolicyOptions)) {
					customerFamilyDetails.setIsFamilyFromPolicyOption(isFamilyFromPolicyOptions);
				}
				boolean isSpouse = ParamUtil.getBoolean(resourceRequest, EdelweissProductCustomizeConstants.PARAM_IS_SPOUSE, false);
				
				if(isSpouse) {
					String spouseFullName = ParamUtil.getString(resourceRequest, EdelweissProductCustomizeConstants.PARAM_SPOUSE_NAME, StringPool.BLANK);
					String spouseDateOfBirth = ParamUtil.getString(resourceRequest, EdelweissProductCustomizeConstants.PARAM_SPOUSE_DOB, StringPool.BLANK);
					customerFamilyDetails.setSpouseName(spouseFullName);
					customerFamilyDetails.setSpouseDateOfBirth(spouseDateOfBirth);
				}
				
				log.debug("EdelweissSaveFamilyDetailsResourceCommand >>> serveResource >>> Customer Family Details ::: " + customerFamilyDetails.toString());
				
				// Add/Update Family
				CustomerFamilyDetailsRel updatedCustomerFamilyDetails = edelweissTokioCommonApi.updateFamilyDetails(portalURL, customerFamilyDetails, customerFamilyDetailsId);
				responseObj.put(EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, updatedCustomerFamilyDetails.getId());
				//Update Family Id to Customer Enquiry Table is not exist
				if(Validator.isNull(customerEnquiry.getFamilyId())) {
					CustomerEnquiry customerEnquiryRequest = new CustomerEnquiry();
					customerEnquiryRequest.setFamilyId(String.valueOf(updatedCustomerFamilyDetails.getId()));
					
					// Family Id update in Customer Enquiry Liferay Object
					edelweissTokioCommonApi.updateCustomerEnquiryById(portalURL, customerEnquiryRequest, customerEnquiry.getId());
				}
				
				if(Validator.isNull(isFamilyFromPolicyOptions)) {
					// Add Update Family While Saving Family From Basic Details and Investing For Dropdown
					CustomerInvestmentDetails updateCustomerInvestmentDetails = new CustomerInvestmentDetails();
					updateCustomerInvestmentDetails.setInvestingFor(EdelweissObjectConstants.FAMILY);
					updateCustomerInvestmentDetails.setLeadId(leadId);
					updateCustomerInvestmentDetails.setrCustomerInvestmentDetailsRelCCustomerEnquiryId(String.valueOf(customerEnquiry.getId()));
					
					CustomerInvestmentDetails updatedCustomerInvestmentDetails = edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, updateCustomerInvestmentDetails, customerInvestmentDetailsId);
					responseObj.put(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, updatedCustomerInvestmentDetails.getId());
				}
			}

			
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
		} catch (ETIPSystemException e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissSaveFamilyDetailsResourceCommand >>> serveResource >>> An error occurred while storing the family details ::: " + e);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissSaveFamilyDetailsResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;
}
