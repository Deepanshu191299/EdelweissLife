package in.edelweiss.ips.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.ips.web.constants.EdelweissIPSConstants;
import in.edelweiss.ips.web.constants.EdelweissIPSWebPortletKeys;
import in.edelweiss.ips.web.helper.EdelweissIPSHelper;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.InvestingFor;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductDefaultValueRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage.ProductInvestmentAmountRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;


@Component(
		immediate = true,
		property = {
				"javax.portlet.name=" + EdelweissIPSWebPortletKeys.EDELWEISS_IPS_WEB,
				"mvc.command.name=/"
		},
		service = MVCRenderCommand.class
	)
public class EdelweissIPSRenderCommand implements MVCRenderCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissIPSRenderCommand.class);
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		log.debug("EdelweissIPSRenderCommand >>> render invoked ::: ");
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		String productId = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_ID);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_CODE);
		String productName = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_NAME);
		log.debug("EdelweissIPSRenderCommand >>> Product configured for the Page is : " +  productName + " , Product Code : " + productCode  + " , Product ID : " + productId);
		
		Map<String, String> productMetaData = new HashMap<>();
		productMetaData.put("productId", productId);
		productMetaData.put("productCode", productCode);
		productMetaData.put("productName", productName);
		
		renderRequest.setAttribute("productMetaData", productMetaData);
		
		EdelweissIPSHelper.hideSuccessAndErrorMessage(renderRequest);
		
		if(Validator.isNotNull(productId)) {
			renderRequest.setAttribute("isProductSelected", true);
			String portalURL = themeDisplay.getPortalURL();
			JSONObject productRelObjectResponse = edelweissTokioCommonApi.getProductNestedFieldDataByProductId(portalURL + EdelweissIPSConstants.IPS_PRODUCT_REL_SERVICE_URL, productId);
			Map<String, Object> productIPSData = EdelweissIPSHelper.getIPSCustomizeData(productRelObjectResponse);
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(renderRequest, LEAD_ID);
			JSONObject customerInvestmentDetailsJSON = edelweissTokioCommonApi.getCustomerInvestmentDetailsLeadId(portalURL, leadId);
			
			if(productIPSData.containsKey(EdelweissIPSConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY)) {
				ProductDefaultValueRel investmentValues = (ProductDefaultValueRel) productIPSData.get(EdelweissIPSConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY);
				if(Validator.isNotNull(customerInvestmentDetailsJSON)) {
					renderRequest.setAttribute(EdelweissCommonConstants.CUSTOMER_INVESTMENT_DETAILS_ID, customerInvestmentDetailsJSON.getString(ID));
					if(Validator.isNotNull(customerInvestmentDetailsJSON.getString("rate"))) {
						ProductDefaultValueRel productDefaultValues = EdelweissIPSHelper.getInvestmentValueFromObject(customerInvestmentDetailsJSON, investmentValues);
						productIPSData.put(EdelweissIPSConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, productDefaultValues);
						renderRequest.setAttribute(EdelweissIPSConstants.IS_INVESTMENT_AMOUNT_UPDATED, true);
					} else {
						InvestingFor investingForObject = new InvestingFor();
						investingForObject.setName(customerInvestmentDetailsJSON.getString("investingFor"));
						investmentValues.setInvestingFor(investingForObject);
						productIPSData.put(EdelweissIPSConstants.PRODUCT_DEFAULT_VALUE_DATA_KEY, investmentValues);
					}
				}
			} else {
				log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> Product Default Value is not configured");
			}
			
			if(productIPSData.containsKey(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS)) {
				List<ProductInvestmentAmountRel> productInvestmentAmountRels = (List<ProductInvestmentAmountRel>) productIPSData.get(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS);
				renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_INVESTMENT_AMOUNT_RELS_DATA, EdelweissIPSHelper.getJsonStringFromObject(productInvestmentAmountRels));
			}
			
			log.debug("EdelweissProductCustomizeRenderCommand >>> render invoked >>>> productIPSData values >>>>> "+ productIPSData);
			renderRequest.setAttribute(EdelweissIPSConstants.PRODUCT_IPS_DATA, productIPSData);
			
			Map<String, String> ipsPolicyTermPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissIPSConstants.IPS_POLICY_TERM_ERC, companyId);
			renderRequest.setAttribute(EdelweissIPSConstants.IPS_PT_PICKLIST_DATA, EdelweissIPSHelper.getJsonStringFromObject(ipsPolicyTermPicklistData));
			
			Map<String, String> ipsPayForPicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissIPSConstants.IPS_PAY_FOR_ERC, companyId);
			renderRequest.setAttribute(EdelweissIPSConstants.IPS_PPT_PICKLIST_DATA, EdelweissIPSHelper.getJsonStringFromObject(ipsPayForPicklistData));
			
			Map<String, String> riskAppetitePicklistData = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.RISK_APPETITE_PICKLIST_ERC, companyId);
			renderRequest.setAttribute(EdelweissCommonConstants.RISK_APPETITE_PICKLIST_DATA, riskAppetitePicklistData);
			
		}else {
			renderRequest.setAttribute("isProductSelected", false);
		}
		
		edelweissTokioCommonApi.renderBasicDetails(renderRequest);
		
		return "/view.jsp";
	}
}
