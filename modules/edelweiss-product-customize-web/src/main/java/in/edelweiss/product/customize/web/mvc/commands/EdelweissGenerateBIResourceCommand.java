package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_ID_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FILTER_QUERY_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_INVESTMENT_DETAILS_URL;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
import in.edelweiss.product.customize.web.utilities.EdelweissCustomerInvestmentObjectUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.BIRequest;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsRel;
import in.edelweiss.tokio.common.model.BIRequest.AgentDetails;
import in.edelweiss.tokio.common.model.BIRequest.ComboDetails;
import in.edelweiss.tokio.common.model.BIRequest.CompanyDetails;
import in.edelweiss.tokio.common.model.BIRequest.CustomerDetails;
import in.edelweiss.tokio.common.model.BIRequest.LiDetails;
import in.edelweiss.tokio.common.model.BIRequest.Product;
import in.edelweiss.tokio.common.model.BIRequest.ProductDetails;
import in.edelweiss.tokio.common.model.BIRequest.ProposerDetails;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/edelweiss/generate-bi"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissGenerateBIResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissGenerateBIResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> Generating BI Request :::");
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		String productStr = ParamUtil.getString(resourceRequest, "product");

		Product product = objectMapper.readValue(productStr, Product.class);
		log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> Product : " + product);
		
		String liDetailsStr = ParamUtil.getString(resourceRequest, "liDetails");
		LiDetails liDetails = objectMapper.readValue(liDetailsStr, LiDetails.class);
		
		String proposerDetailsStr = ParamUtil.getString(resourceRequest, "proposerDetails");
		ProposerDetails proposerDetails = objectMapper.readValue(proposerDetailsStr, ProposerDetails.class);
		
		AgentDetails agentDetails = edelweissTokioCommonApi.getBIRequestAgentDetails(StringPool.BLANK, StringPool.BLANK, StringPool.BLANK, true);
		
		try {

			JSONObject partnerData = edelweissTokioCommonApi.getPartnerDataByLeadId(resourceRequest);
			log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> partnerData data :: " + partnerData);
			
				if (Validator.isNotNull(partnerData) && partnerData.getBoolean("isValidPartner")) {
					agentDetails.setAgentId(partnerData.getString("agentId"));
					product.setCategory(partnerData.getString("category"));
					product.setPrChannel(partnerData.getString("channel"));
				}

		} catch (Exception e1) {
			log.error(" EdelweissGenerateBIResourceCommand Partner error >>>>>>>>>>>>>>>>>>>>>>> ::::", e1);
		}
		
		
		CustomerDetails customerDetails = edelweissTokioCommonApi.getBIRequestCustomerDetails(liDetails, proposerDetails, agentDetails);

		CompanyDetails companyDetails = edelweissTokioCommonApi.getBIRequestCompanyDetails(0, "0", 0);
		ComboDetails comboDetails = new ComboDetails();
		ProductDetails productDetails = edelweissTokioCommonApi.getBIRequestProductDetails(product, comboDetails);
		
		BIRequest biRequest = edelweissTokioCommonApi.getBIRequest(productDetails, customerDetails, companyDetails);
		
		String biRequestJsonString = objectMapper.writeValueAsString(biRequest);
		log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> BIRequest JSON : " + biRequestJsonString);
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		String leadId = StringPool.BLANK;
		
		try {
			JSONObject biResponseJSONObject = edelweissTokioCommonApi.invokeRetailParnerGenerateBI(biRequestJsonString);
			log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> BIresponse JSON: "+biResponseJSONObject);
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, biResponseJSONObject);
		} catch(Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissGenerateBIResourceCommand >>> doServeResource >>> Error occured while genrating BI Request ::: " + e);
		}  finally {			
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
		
		try {
			leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
		} catch(Exception e) {
			log.error("EdelweissGenerateBIResourceCommand >>> doServeResource >>> Error occured while genrating BI Request ::: " + e.getMessage());
		}finally {
			if(!leadId.equalsIgnoreCase(StringPool.BLANK)) {
				JSONObject cusInvestmentRes = EdelweissCustomerInvestmentObjectUtil.getCustomerInvestmentDetailsByLead(etipCoreAPI, leadId, themeDisplay);
				JSONArray items = cusInvestmentRes.getJSONArray("items");
				JSONObject obj = items.getJSONObject(0);
				String customerInvestmentDetailsId = obj.getString("id");
				
				JSONObject customerInvestmentDetailsRequestBody = JSONFactoryUtil.createJSONObject();
				
				JSONObject biJSONAndInputValidation = JSONFactoryUtil.createJSONObject();
				
				biJSONAndInputValidation.put("bIJsonFirstIndex", responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONArray("BIJson").get(0));
				biJSONAndInputValidation.put("bIJsonLength", responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONArray("BIJson").length());
				biJSONAndInputValidation.put("biInputValidations",responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONArray("InputValidationStatus"));
				customerInvestmentDetailsRequestBody.put("biJSONAndInputValidation", biJSONAndInputValidation.toString());
				EdelweissCustomerInvestmentObjectUtil.updateCustomerInvestmentDetailsById(etipCoreAPI, customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			}
		}
		
	}
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
}
