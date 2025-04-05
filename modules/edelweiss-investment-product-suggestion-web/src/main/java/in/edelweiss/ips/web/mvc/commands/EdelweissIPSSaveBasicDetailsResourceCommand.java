package in.edelweiss.ips.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.ips.web.constants.EdelweissIPSWebPortletKeys;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
	        "mvc.command.name=/save_ips/basicDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissIPSSaveBasicDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissIPSSaveBasicDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws PortletException {

		try {
			JSONObject responseObj = JSONFactoryUtil.createJSONObject();
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
			
			String investingFor = ParamUtil.getString(resourceRequest, "investingFor");
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL = themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			
			Long customerEnquiryId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID, 0);
			String customerFamilyDetailsId = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, StringPool.BLANK);
			Long customerInvestmentDetailsId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, 0);
			
			HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
			Enumeration<String> enumeration = request.getParameterNames();
			Map<String, String> requestParametersMap = new  HashMap<>();

			while(enumeration.hasMoreElements()){
	            String parameterName = enumeration.nextElement();
	            requestParametersMap.put(parameterName, ParamUtil.getString(resourceRequest, parameterName));
	        }
			
			CustomerEnquiry customerEnquiryRequest = new CustomerEnquiry();
			if (investingFor.equalsIgnoreCase(FAMILY)) {
				CustomerFamilyDetailsRel customerFamilyDetails = edelweissTokioCommonApi.getRequestCustomerFamilyDetails(requestParametersMap, leadId);

				// Add/Update Family
				CustomerFamilyDetailsRel updatedCustomerFamilyDetails = edelweissTokioCommonApi.updateFamilyDetails(portalURL, customerFamilyDetails, customerFamilyDetailsId);
				responseObj.put(EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, updatedCustomerFamilyDetails.getId());
				
				//Update Family Id to Customer Enquiry Table is not exist
				if(Validator.isNull(customerFamilyDetailsId)) {
					log.debug("EdelweissIPSSaveBasicDetailsResourceCommand >>> doServeResource >>> Updating Family ID to Customer Enquiry ::: " + updatedCustomerFamilyDetails.getId());
					// Family Id update in Customer Enquiry Liferay Object
					customerEnquiryRequest.setFamilyId(String.valueOf(updatedCustomerFamilyDetails.getId()));
					
				}
			} else {
				// My Self
				if(Validator.isNotNull(customerFamilyDetailsId)) {
					log.debug("EdelweissIPSSaveBasicDetailsResourceCommand >>> doServeResource >>> Deleting Family Details By Id : " + customerFamilyDetailsId);
					edelweissTokioCommonApi.deleteFamilyDetailsById(portalURL, customerFamilyDetailsId);
				}
				customerEnquiryRequest.setFamilyId(StringPool.BLANK);
			}
			
			customerEnquiryRequest.setFullname(requestParametersMap.get(EdelweissObjectConstants.FULL_NAME));
			customerEnquiryRequest.setDateOfBirth(requestParametersMap.get(EdelweissObjectConstants.DATE_OF_BIRTH));
			customerEnquiryRequest.setMobilenumber(requestParametersMap.get(EdelweissObjectConstants.MOBILE_NUMBER));
			customerEnquiryRequest.setEmail(requestParametersMap.get(EdelweissObjectConstants.EMAIL));
			customerEnquiryRequest.setGender(requestParametersMap.get(EdelweissObjectConstants.GENDER));
			customerEnquiryRequest.setInvestmentObjective(requestParametersMap.get(EdelweissObjectConstants.INVESTMENT_OBJECTIVE));
			customerEnquiryRequest.setProductId(requestParametersMap.get(EdelweissCommonConstants.PRODUCT_CODE_PARAM));
			
			//Update Basic Details
			edelweissTokioCommonApi.updateCustomerEnquiryById(portalURL, customerEnquiryRequest, customerEnquiryId);
			
			CustomerInvestmentDetails updateCustomerInvestmentDetails = new CustomerInvestmentDetails();
			updateCustomerInvestmentDetails.setInvestingFor(investingFor);
			updateCustomerInvestmentDetails.setLeadId(leadId);
			updateCustomerInvestmentDetails.setrCustomerInvestmentDetailsRelCCustomerEnquiryId(String.valueOf(customerEnquiryId));
			CustomerInvestmentDetails updatedCustomerInvestmentDetails = edelweissTokioCommonApi.updateInvestmentDetailsById(portalURL, updateCustomerInvestmentDetails, customerInvestmentDetailsId);
			
			// Nested Code Starts
			String customerEnquiryAPIURL = themeDisplay.getPortalURL() + MessageFormat.format(EdelweissObjectConstants.GET_CUSTOMER_ENQUIRY_RELATIONAL_API + EdelweissObjectConstants.GET_CUSTOMER_ENQUIRY_RELATIONAL_DATA_QUERY_PARAMS, String.valueOf(customerEnquiryId));
			log.debug("EdelweissIPSSaveBasicDetailsResourceCommand >>> doServeResource >>> locationURL ::: " + customerEnquiryAPIURL);
			
			JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
			
			
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, apiResponseObject);
			
			if(Validator.isNotNull(updatedCustomerInvestmentDetails)) {
				responseObj.put(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, updatedCustomerInvestmentDetails.getId());
			}
			resourceResponse.getWriter().write(responseObj.toString());
		} catch (ETIPSystemException e) {
			log.error("EdelweissIPSSaveBasicDetailsResourceCommand >>> serveResource >>> An error occurred getting the storing Basic details ::: "+e);
		} catch (Exception e) {
			log.error("EdelweissIPSSaveBasicDetailsResourceCommand >>> serveResource >>> IOException ::: "+e);
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
}
