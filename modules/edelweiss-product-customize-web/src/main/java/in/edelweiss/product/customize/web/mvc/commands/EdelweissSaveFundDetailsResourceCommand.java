package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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
import in.edelweiss.tokio.common.model.CustomerFundAllocationDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/save/fundDetails"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissSaveFundDetailsResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissSaveFundDetailsResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		try {
			log.debug("EdelweissSaveFundDetailsResourceCommand >>>> serveResource >>>> Saving Customize Fund Details :::");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			Long fundAllocationDetailsId = ParamUtil.getLong(resourceRequest, "fundAllocationDetailsId", 0L);
			log.debug("EdelweissSaveFundDetailsResourceCommand >>>> serveResource >>>> Fund Allocation Id ::: " +  fundAllocationDetailsId);
			String fundDetails = ParamUtil.getString(resourceRequest, EdelweissProductCustomizeConstants.FUND_DETAILS, StringPool.BLANK);
			String productCode = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.PRODUCT_CODE_PARAM, StringPool.BLANK);
			boolean saveDefaultFundAllocation = ParamUtil.getBoolean(resourceRequest, "saveDefaultFundAllocation", false);

			log.debug("EdelweissSaveFundDetailsResourceCommand >>>> serveResource >>>> Save Default Funds ::: " +  saveDefaultFundAllocation);
			log.debug("EdelweissSaveFundDetailsResourceCommand >>> serveResource >>> Fund Details ::: " + fundDetails);
			
			boolean hasFundAllocated =  false;
			String fundAllocationDetailsStr = StringPool.BLANK;
			CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
			if(Validator.isNotNull(customerEnquiry) && Validator.isNotNull(customerEnquiry.getId())) {
				CustomerFundAllocationDetailsRel customerFundAllocationDetails = new CustomerFundAllocationDetailsRel();
				customerFundAllocationDetails.setLeadId(leadId);
				customerFundAllocationDetails.setFundDetails(fundDetails);
				customerFundAllocationDetails.setProductCode(productCode);
				customerFundAllocationDetails.setrCustomerFundAllocationDetailsRelCCustomerEnquiryId(customerEnquiry.getId());
				
				CustomerFundAllocationDetailsRel customerFundAllocationDetailsRes = null;
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				customerFundAllocationDetailsRes = getFundAllocationDetails(portalURL, leadId, fundAllocationDetailsId,
						saveDefaultFundAllocation, customerFundAllocationDetails);
				
				hasFundAllocated = true;
				if(Validator.isNotNull(customerFundAllocationDetailsRes)) {
					fundAllocationDetailsStr = mapper.writeValueAsString(customerFundAllocationDetailsRes);
					responseObj.put(EdelweissProductCustomizeConstants.HAS_FUND_ALLOCATED, hasFundAllocated);
					responseObj.put(EdelweissProductCustomizeConstants.FUND_ALLOCATION_DETAILS, fundAllocationDetailsStr);
				}
			} else {
				log.debug("EdelweissSaveFundDetailsResourceCommand >>> serveResource >>> No Such entry found in Customer Enquiry By Lead ID ::: " + leadId);
			}

			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissSaveFundDetailsResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	private CustomerFundAllocationDetailsRel getFundAllocationDetails(String portalURL, String leadId, Long fundAllocationDetailsId,
			boolean saveDefaultFundAllocation, CustomerFundAllocationDetailsRel customerFundAllocationDetails)
			throws Exception {
		CustomerFundAllocationDetailsRel customerFundAllocationDetailsRes;
		if(Validator.isNotNull(fundAllocationDetailsId)) {
			//Update Fund Allocation Existing Data
			customerFundAllocationDetailsRes = edelweissTokioCommonApi.updateFundAllocationDetailsById(portalURL, customerFundAllocationDetails, fundAllocationDetailsId);
		} else if(saveDefaultFundAllocation) {
			log.debug("EdelweissSaveFundDetailsResourceCommand >>> serveResource >>> Saving Default Fund Allocation By Lead ID :::  " + leadId);
			JSONObject cagrReponse = edelweissTokioCommonApi.getCAGRData();
			
			JSONArray fundReponseJSONArray = cagrReponse.getJSONArray(EdelweissProductCustomizeConstants.CAGR_RESPONSE);
			JSONArray fundDetailsJSONArray = JSONFactoryUtil.createJSONArray();
			JSONObject fundDetailsJSONObject = null;
			for (int i = 0; i < fundReponseJSONArray.length(); i++) {
				JSONObject curFundJSON = fundReponseJSONArray.getJSONObject(i);
				fundDetailsJSONObject = JSONFactoryUtil.createJSONObject();
				fundDetailsJSONObject.put(EdelweissProductCustomizeConstants.FUND_NAME_KEY, curFundJSON.getString(EdelweissProductCustomizeConstants.FUND_NAME_KEY));
				fundDetailsJSONObject.put(EdelweissProductCustomizeConstants.FUND_INCEPTION_DATE, curFundJSON.getString(EdelweissProductCustomizeConstants.FUND_INCEPTION_DATE));
				fundDetailsJSONObject.put(EdelweissProductCustomizeConstants.FUND_ALLOCATION_KEY, curFundJSON.getBoolean(EdelweissProductCustomizeConstants.RECOMMENDED_FUND_KEY) ? EdelweissProductCustomizeConstants.RECOMMENDED_FUND_DEFAULT_ALLOCATION : EdelweissProductCustomizeConstants.OTHER_FUND_DEFAULT_ALLOCATION);
				fundDetailsJSONArray.put(fundDetailsJSONObject);
			}
			customerFundAllocationDetails.setFundDetails(fundDetailsJSONArray.toString());
			//Add New Entry with Default Fund Allocation
			customerFundAllocationDetailsRes = edelweissTokioCommonApi.updateFundAllocationDetailsById(portalURL, customerFundAllocationDetails, 0L);
		} else {
			//Add New Entry in Fund Allocation as per updated in Customize Fund Table
			customerFundAllocationDetailsRes = edelweissTokioCommonApi.updateFundAllocationDetailsById(portalURL, customerFundAllocationDetails, 0L);
		}
		return customerFundAllocationDetailsRes;
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
