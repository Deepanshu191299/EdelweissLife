package in.edelweiss.product.quote.web.mvc.command;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_API;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.INVESTMENT_OBJECTIVE_URL;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.SUITABILITY_DATE_OF_BIRTH;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.SUITABILITY_EMAIL;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.SUITABILITY_FULL_NAME;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.SUITABILITY_GENDER;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.SUITABILITY_MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ING_PLAN_SUB_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.YOUR_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COLUMN_PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EXTERNAL_REFERENCE_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_STATUS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LIFERAY_PRODUCT_ENQUIRY_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_INTEREST_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_QUOTE_JOURNEY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LMS_SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OI_SUM_ASSURED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OTHER_INSURANCE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PUT_CUSTOMER_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RESPONSE_DATA;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.RISK_APPETITE;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETLIValidationException;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
import in.edelweiss.product.quote.web.util.EdelweissProductQuoteUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.LiferayObjectModel;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"mvc.command.name=add/suitabilityForm" }, service = MVCActionCommand.class)
public class EdelweissSuitabilityMatrixActionCommand extends BaseMVCActionCommand{
	@Reference 
	EdelweissTokioCommonApi edelweissTokioCommonAPI;
	@Reference
	ETIPCoreAPI etipCoreAPI;
private static Log log = LogFactoryUtil.getLog(EdelweissSuitabilityMatrixActionCommand.class);
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		log.debug("EdelweissSuitabilityMatrixActionCommand >>> processAction >> invoked");
		
		JSONObject validator = edelweissTokioCommonAPI.validate(actionRequest);
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		if(Validator.isNotNull(validator.get("message"))) {						
			throw new ETLIValidationException(validator.getString("message"));
		}
		
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		
		Map<String, String> investmentObjectiveMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());
		Map<String, String> yourIncomeMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC, themeDisplay.getCompanyId());
		String productName = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productId = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), COLUMN_PRODUCT_ID);
		String productCode = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(),
				Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
		// Step 1 :  get lead id from cookie and its respective data 
	    String leadId = edelweissTokioCommonAPI.getLeadIdFromCookie(actionRequest, LEAD_ID);
	    Map<String, String> suitabilityMap = new HashMap<>();
	    
	    suitabilityMap.put(RISK_APPETITE, ParamUtil.getString(actionRequest, RISK_APPETITE));
		suitabilityMap.put(ANNUAL_INCOME, ParamUtil.getString(actionRequest, ANNUAL_INCOME));
		suitabilityMap.put(INVESTMENT_OBJECTIVE, ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE));
		suitabilityMap.put(FULL_NAME, ParamUtil.getString(actionRequest,SUITABILITY_FULL_NAME));
		suitabilityMap.put(DATE_OF_BIRTH, ParamUtil.getString(actionRequest,SUITABILITY_DATE_OF_BIRTH));
		suitabilityMap.put(MOBILE_NUMBER, ParamUtil.getString(actionRequest,SUITABILITY_MOBILE_NUMBER));
		suitabilityMap.put(GENDER, ParamUtil.getString(actionRequest,SUITABILITY_GENDER));
		suitabilityMap.put(EMAIL, ParamUtil.getString(actionRequest,SUITABILITY_EMAIL));
		suitabilityMap.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);
		suitabilityMap.put(QUOTE_STAGE, SITE_SECTION_PRE_QUOTE);
		
		suitabilityMap.put(PRODUCT_NAME_QUOTE, productName);
		
	    if(Validator.isNotNull(leadId)) {
	    	// Step 2 : Getting values from suitability matrix 
		       
	        String otherInusrance = ParamUtil.getString(actionRequest, OTHER_INSURANCE);
			
			
			if (otherInusrance.equalsIgnoreCase("yes")) {
			 suitabilityMap.put(OI_SUM_ASSURED, ParamUtil.getString(actionRequest, "totalSumAssured"));
			}
			
			suitabilityMap.put(RECOMMENDED_OBJECTIVE, investmentObjectiveMap.get(ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE)));
			suitabilityMap.put(RECOMMENDED_ANNUAL_INCOME, yourIncomeMap.get(ParamUtil.getString(actionRequest, ANNUAL_INCOME)));
			
			suitabilityMap.put(PRODUCT_ID, productCode);
			suitabilityMap.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productCode);
			suitabilityMap.put(LEAD_FORM_TYPE, LEAD_FORM_TYPE_RECCOMENDATION);
			// Step 3:  Getting customer enquiry details & its Mandatory Fields  
			String customerEnquiriesDataURL = EdelweissProductQuoteWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_URL + EdelweissProductQuoteWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductQuoteUtil.getLeadMetaDataURL(leadId);
			JSONObject customerEnquiryData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiriesDataURL, false, liferayUserName, liferayPassword);
			String customerId = customerEnquiryData.getJSONArray("items").getJSONObject(0).getString("id");
			String urlForCustomerPutData = themeDisplay.getPortalURL() + PUT_CUSTOMER_DATA;
			urlForCustomerPutData = StringUtil.replace(urlForCustomerPutData, "{customerEnquiryId}", customerId);
			suitabilityMap.put(EXTERNAL_REFERENCE_CODE, leadId);
			suitabilityMap.put(LEAD_ID, leadId);
			suitabilityMap.put(OTHER_INSURANCE, otherInusrance);
			
			// Step 4 : Code to get details of product meta data
			String leadUpdateRequestBody = edelweissTokioCommonAPI.getUpdateLMSRequestBody(suitabilityMap);
			log.debug("EdelweissSuitabilityMatrixActionCommand >>>> doProcessAction >>> updateRequesBody  :: "+leadUpdateRequestBody);
			
			// Step 5 : Updating in lr object and hide error and success message
			
			JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(suitabilityMap);
			etipCoreAPI.callPutAPI(liferayJSON,urlForCustomerPutData, false, liferayUserName, liferayPassword);
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);
			//Step 6 : Redirecting to the another page  
			
			actionResponse.sendRedirect(INVESTMENT_OBJECTIVE_URL);
	    }else {
	    	
	    	String customerEnquiryAPIURL = MessageFormat.format(GET_PRODUCT_MASTER_RELATIONAL_API + GET_PRODUCT_MASTER_RELATIONAL_DATA_QUERY_PARAMS, productId);
			
			log.debug("EdelweissSuitabilityMatrixActionCommand >>>  doProcessAction >>> URL ::: " + customerEnquiryAPIURL);
			
			
			// Step 4: Fetching metadata details like Campaign Id, C_ID,V_ID from Liferay Objects
			JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
			apiResponseObject.put(ING_PLAN_SUB_TYPE, investmentObjectiveMap.get(ParamUtil.getString(actionRequest, INVESTMENT_OBJECTIVE)));
			apiResponseObject.put(SITE_SECTION, LMS_SITE_SECTION);
	    	JSONObject generatedLeadId = edelweissTokioCommonAPI.generateLead(suitabilityMap, productName, apiResponseObject, actionRequest.getUserAgent());
			log.debug("EdelweissSuitabilityMatrixActionCommand >>>> doProcessAction >>> Lead Id Generated  :: "
					+ generatedLeadId);

			suitabilityMap.put(EXTERNAL_REFERENCE_CODE, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
			suitabilityMap.put(LEAD_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
			suitabilityMap.put(LEAD_STATUS, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LEAD_STATUS));
			suitabilityMap.put(LMS_QUOTE_INTEREST_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_INTEREST_ID));
			suitabilityMap.put(LMS_QUOTE_JOURNEY_ID, generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_QUOTE_JOURNEY_ID));

			String productEnquiriesURL = themeDisplay.getPortalURL() + LIFERAY_PRODUCT_ENQUIRY_URL;
			
			JSONObject liferayJSON = LiferayObjectModel.createLiferayRequestJSON(suitabilityMap);
			JSONObject lrProductResponse = etipCoreAPI.callPostAPI(liferayJSON, productEnquiriesURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissSuitabilityMatrixActionCommand >>> doProcessAction >>> lr product response  ::: "+ lrProductResponse);
			//Step 4 (c) creating cookie code to set lead id and hide error and success messages
			HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
			Cookie cookie = new Cookie("leadId", generatedLeadId.getJSONObject(RESPONSE_DATA).getString(LMS_ID));
			cookie.setPath("/");
			response.addCookie(cookie);
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);
			// Step 4 (iv) :  Redirecting to the page
			actionResponse.sendRedirect(INVESTMENT_OBJECTIVE_URL);
	    }
	 
 
	
		   
	}
}
