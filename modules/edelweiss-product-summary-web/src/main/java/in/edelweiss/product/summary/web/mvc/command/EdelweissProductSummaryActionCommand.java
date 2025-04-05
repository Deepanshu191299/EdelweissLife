package in.edelweiss.product.summary.web.mvc.command;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BI_PDF_PATH;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.CHILD_NAME;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ILLUSTRATION_URL;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.INTEREST_SESSION_STAGE_GET_STARTED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_OPTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_FREQUENCY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE_PROPOSAL_FORM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_SUMMARY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.STAGE_INTEREST_SESSION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SUM_ASSURED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SWP_YN;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.TOTAL_PREMIUM_AMOUNT;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ADDITIONAL_BENEFIT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ITEMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PREMIUM_PAYING_TERM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.TOTAL_RETURNS;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;
import in.edelweiss.product.summary.web.util.EdelweissProductSummaryUtil;
import in.edelweiss.system.configurations.EdelweissBJGeneratePolicyNoConfiguration;
import in.edelweiss.system.configurations.EdelweissGenerateApplicationNoConfiguration;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerPolicyDetailsLrRel;
import in.edelweiss.tokio.common.model.GeneratePolicyResponse;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductSummaryWebPortletKeys.EDELWEISSPRODUCTSUMMARYWEB,
		"mvc.command.name=/save/GeneratePolicy" + "" }, service = MVCActionCommand.class)
public class EdelweissProductSummaryActionCommand extends BaseMVCActionCommand {
	
	@Reference 
	EdelweissTokioCommonApi edelweissTokioCommonAPI;
	
	@Reference
	ETIPCoreAPI etipCoreAPI;
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissProductSummaryActionCommand.class);

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
	
		log.debug("EdelweissProductSummaryActionCommand >>> processAction invoked :::" );
		// Step 1: Initialize the objects/Data points
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		
		EdelweissBJGeneratePolicyNoConfiguration edelweissBJGeneratePolicyNoConfiguration = EdelweissConfigurationUtil.getEdelweissBJGeneratePolicyNoConfiguration();
		EdelweissGenerateApplicationNoConfiguration edelweissGenerateApplicationNoConfiguration = EdelweissConfigurationUtil.getEdelweissGenerateApplicationNoConfiguration();
		
		try { 
			Map<String, String> updateLMSRquestMapData = new HashMap<>();
			String authorization = edelweissTokioCommonAPI.getOAuthToken(edelweissBJGeneratePolicyNoConfiguration.getOAuth2QJURL(),
					edelweissBJGeneratePolicyNoConfiguration.getOAuth2QJUsername(),
					edelweissBJGeneratePolicyNoConfiguration.getOAuth2QJPassword());
			
			//Step 2: Getting required metadata
			String productId = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
			String productName = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getPlid(), EdelweissObjectConstants.PRODUCT_NAME);
			
			// Step 3: Get LeadId from Cookie
			String leadId = edelweissTokioCommonAPI.getLeadIdFromCookie(actionRequest, EdelweissObjectConstants.LEAD_ID);
			log.debug("EdelweissProductSummaryActionCommand >>> processAction invoked >>> Lead Id ::: " + leadId);
			// Step 4 : Getting customer enquiry data 
			String customerEnquiriesDataURL = EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_URL + EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
			JSONObject customerEnquiryData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiriesDataURL, false, liferayUserName, liferayPassword);
			String customerMobileNumber = customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.MOBILE_NUMBER);
			String familyId = customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString("familyId");	
			String customerfamilyURL="";
			
			//Step 5 : Getting family details for update we need to pass
			if(Validator.isNotNull(familyId)) {
				customerfamilyURL = EdelweissProductSummaryWebPortletKeys.GET_FAMILY_DETAILS + familyId;
				JSONObject customerFamilyData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerfamilyURL, false, liferayUserName, liferayPassword);
				updateLMSRquestMapData.put(CHILD_DOB, customerFamilyData.getString("assuranceDob"));
				updateLMSRquestMapData.put(CHILD_NAME, customerFamilyData.getString("assuranceFullName"));
				updateLMSRquestMapData.put(EdelweissObjectConstants.SPOUSE_DOB, customerFamilyData.getString("spouseDateOfBirth"));
				updateLMSRquestMapData.put(EdelweissObjectConstants.SPOUSE_NAME, customerFamilyData.getString("spouseName"));
				
			}
			// Step 6 : getting investment details 
			String investmentDetailsURL = EdelweissProductSummaryWebPortletKeys.GET_INVESTMENT_DETAILS + EdelweissProductSummaryWebPortletKeys.GET_INVESTMENT_DETAILS_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
			JSONObject investmentData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+investmentDetailsURL, false, liferayUserName, liferayPassword);
			updateLMSRquestMapData.put(TOTAL_PREMIUM_AMOUNT, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.PREMIUM_AMOUNT));
			updateLMSRquestMapData.put(POLICY_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.POLICY_TERM_PARAM));
			updateLMSRquestMapData.put(PREMIUM_PAYMENT_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(PREMIUM_PAYING_TERM));
			updateLMSRquestMapData.put(SUM_ASSURED, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(TOTAL_RETURNS));
			
			//For ZP 
			if(!Validator.isNotNull(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(TOTAL_RETURNS))) {
				updateLMSRquestMapData.put(SUM_ASSURED, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.INVESTMENT_AMOUNT_PARAM));
			}
			// Step 7: Construct API endpoints for Customer Policy Details and invoke the API - LR Object - CustomerPolicyDetails 
			String customerPolicyURL  =  EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL + EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_DETAILS_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
			String customerPolicyMetaDataURL = MessageFormat.format(customerPolicyURL, leadId);
			JSONObject customerPolicyRes = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerPolicyMetaDataURL, false, liferayUserName, liferayPassword);
			log.debug("EdelweissProductSummaryActionCommand >>> processAction >>> leadCustomerPolicyRes ::: " + customerPolicyRes);
			
			// Step 8 : Getting value of picklist policy options
			Map<String, String > policyOptionMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.MASTER_POLICY_OPTIONS_PICKLIST_EXTREF_CODE, themeDisplay.getCompanyId());
			String policyOptionValue = policyOptionMap.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("policyOption"));
			// Step 9 : Getting value of SWP options
			Map<String, String> swpValueMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode("SWP_MASTER", themeDisplay.getCompanyId());
			String systematicWithdrawlPlanValue = swpValueMap.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("systematicWithdrawalPlan"));
			
			Map<String, String> paymentOptionsMaster = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode("PAYMENT_OPTIONS_MASTER", themeDisplay.getCompanyId());
			String paymentOptions = paymentOptionsMaster.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("paymentFrequency"));
			updateLMSRquestMapData.put(PREMIUM_PAYMENT_FREQUENCY, paymentOptions);
			
			JSONArray riderArray= JSONFactoryUtil.createJSONArray(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.RIDER_ID));
			if(riderArray.length()>0) {
				updateLMSRquestMapData.put(ADDITIONAL_BENEFIT, "Yes");
				for(Object rider:riderArray) {
					JSONObject riderobj=JSONFactoryUtil.createJSONObject(rider.toString());
					
					//String riderName = riderobj.getString("riderName");
					String riderPremium = riderobj.getString("price");
					String riderSA = riderobj.getString("premium");
					String riderKey = riderobj.getString("key");
					updateLMSRquestMapData.put(riderKey+"_YN", "Y");
					updateLMSRquestMapData.put(riderKey+"PT", investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.POLICY_TERM_PARAM));
					updateLMSRquestMapData.put(riderKey+"PPT", investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(PREMIUM_PAYING_TERM));
					updateLMSRquestMapData.put(riderKey+"SA", riderSA);
					updateLMSRquestMapData.put(riderKey+"Amount", riderPremium);
					
				}
			}
			//Step 10 : getting customer policy details 
			String quotationId = customerPolicyRes.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.QUOTATION_ID);
			String biPDFPath = customerPolicyRes.getJSONArray(ITEMS).getJSONObject(0).getString(ILLUSTRATION_URL);
			Map<String, String> investmentObjectiveMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());
			String investmentObjValue = investmentObjectiveMap.get(customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString("investmentObjective"));
			//Step 11: Constructing  Req Body of Policy Number 
			String generatePolicyReqBody = EdelweissProductSummaryUtil.getPolicyNoRequestBody(objectMapper, "", leadId,quotationId);
			log.debug("EdelweissProductSummaryActionCommand >>> processAction >>> generate Req body ::: " + generatePolicyReqBody);
			// Step 12 : Invoke the Generate Policy No API and Mapping with Object Mapper
			JSONObject generatePolicyResponse = etipCoreAPI.callPostAPI(JSONFactoryUtil.createJSONObject(generatePolicyReqBody), edelweissBJGeneratePolicyNoConfiguration.getBJGeneratePolicyURL(), true, edelweissBJGeneratePolicyNoConfiguration.getBJGeneratePolicyXAPIKEY(), authorization);
			log.info("EdelweissProductSummaryActionCommand >>> processAction >>> generate Policy Response " + generatePolicyResponse);
			GeneratePolicyResponse policyResponse = objectMapper.readValue(generatePolicyResponse.toJSONString(), GeneratePolicyResponse.class);
			log.debug("EdelweissProductSummaryActionCommand >>> processAction >>> Policy Response " + policyResponse);
			
			if(Validator.isNull(policyResponse.getJourneyURL())) {
				String redirectURL = themeDisplay.getURLCurrent();
				if(redirectURL.contains("?")) {
					redirectURL = redirectURL.split("\\?")[0];
				}	
				log.debug("EdelweissProductSummaryActionCommand >>> processAction >>> redirectURL " + redirectURL);
				actionResponse.sendRedirect(redirectURL);
				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);
				return;
			}
			
			String policyNumber = generatePolicyResponse.getString(EdelweissCommonConstants.POLICY_NUMBER);
			String applicationNumber = generatePolicyResponse.getString(EdelweissCommonConstants.APPLICATION_NUMBER);
			String status = generatePolicyResponse.getString(EdelweissCommonConstants.STATUS);
			// Step 13 : Updating Policy Number into the customer policy details of LR object  
			String customerPolicyDetailsURL = EdelweissProductSummaryWebPortletKeys.PUT_CUSTOMER_POLICY_DETAILS;
			customerPolicyDetailsURL = MessageFormat.format(customerPolicyDetailsURL, customerPolicyRes.getJSONArray(EdelweissObjectConstants.ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.ID));
			log.debug("EdelweissProductSummaryActionCommand >>> processAction >>> customerPolicyDetailsURL ::: " + customerPolicyDetailsURL);
			      
			// Step 14: Setting values in request body 
			CustomerPolicyDetailsLrRel customerPolicyDetailRel = new CustomerPolicyDetailsLrRel();
			customerPolicyDetailRel.setPolicyNumber(policyNumber);
			customerPolicyDetailRel.setApplicationNumber(applicationNumber);
			String customerPolicyJson = objectMapper.writeValueAsString(customerPolicyDetailRel);
			
			//Step 15 : Putting into lr object of customer policy detail
			JSONObject customerPolicyJsonReq = JSONFactoryUtil.createJSONObject(customerPolicyJson);
			JSONObject customerPolicyJsonRes = etipCoreAPI.callPutAPI(customerPolicyJsonReq, themeDisplay.getPortalURL()+ customerPolicyDetailsURL, false, liferayUserName, liferayPassword);
			
			log.info("CustomerPolicyJsonRes "+customerPolicyJsonRes);
			
			log.info("EdelweissProductSummaryActionCommand leadId "+leadId+" AppId "+applicationNumber+ " PolicyNumber "+policyNumber);
			
			log.info("EdelweissProductSummaryActionCommand AppId "+applicationNumber+" leadId "+leadId+ " PolicyNumber "+policyNumber);
			
			log.info("EdelweissProductSummaryActionCommand PolicyNumber "+policyNumber+" leadId "+leadId+ " AppId "+applicationNumber);
			
			//Step 16 : Setting data in map that need to pass on update request body
			updateLMSRquestMapData.put(LEAD_ID, leadId);
			updateLMSRquestMapData.put(FULL_NAME, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.FULL_NAME));
			updateLMSRquestMapData.put(MOBILE_NUMBER, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.MOBILE_NUMBER));
			updateLMSRquestMapData.put(EMAIL, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.EMAIL));
			updateLMSRquestMapData.put(GENDER, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.GENDER));
			updateLMSRquestMapData.put(DATE_OF_BIRTH, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.DATE_OF_BIRTH));
			updateLMSRquestMapData.put(PRODUCT_CODE, productId);
			updateLMSRquestMapData.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productId);
			
			updateLMSRquestMapData.put(SITE_SECTION, SITE_SECTION_SUMMARY);
			updateLMSRquestMapData.put(QUOTE_STAGE, QUOTE_STAGE_PROPOSAL_FORM);
			updateLMSRquestMapData.put(PRODUCT_NAME_QUOTE, productName);
			updateLMSRquestMapData.put(STAGE_INTEREST_SESSION, INTEREST_SESSION_STAGE_GET_STARTED);
			
			updateLMSRquestMapData.put(QUOTE_STAGE_PROPOSAL_FORM, QUOTE_STAGE_PROPOSAL_FORM);
			updateLMSRquestMapData.put(BI_PDF_PATH, biPDFPath);
			updateLMSRquestMapData.put(RECOMMENDED_OBJECTIVE, investmentObjValue);
			updateLMSRquestMapData.put(POLICY_OPTION, policyOptionValue);
			updateLMSRquestMapData.put(SWP_YN, (Validator.isNotNull(systematicWithdrawlPlanValue) && systematicWithdrawlPlanValue.equalsIgnoreCase("Yes"))?"Y":"N");
			updateLMSRquestMapData.put(LEAD_FORM_TYPE, RECCOMENDATION);
			
			//Step 17: constructing request body for appliation 
			
			String updateReqBody=edelweissTokioCommonAPI.getUpdateLMSRequestBody(updateLMSRquestMapData);
			
			log.debug("updateReqBody"+updateReqBody);
			
			JSONObject updateResponse = edelweissTokioCommonAPI.updateLMSLead(updateReqBody);
			
			log.debug("updateResponse"+updateResponse);
			
			/*CODE FOR GENERATE APPLICATION */
			//Step 18: Check if above response is success then call other API
			
			if(status.equalsIgnoreCase(EdelweissCommonConstants.SUCCESS)) {
				String applicationAuthorization = edelweissTokioCommonAPI.getOAuthToken(edelweissGenerateApplicationNoConfiguration.getOAuth2QJURL(),
						edelweissGenerateApplicationNoConfiguration.getOAuth2QJUsername(),
						edelweissGenerateApplicationNoConfiguration.getOAuth2QJPassword());
				log.debug("updateResponse"+updateResponse);
				String applicationRequestBody = EdelweissProductSummaryUtil.getPolicyApplicationRequestBody(objectMapper, leadId, Long.parseLong(applicationNumber), customerMobileNumber, policyNumber,quotationId, updateResponse.getJSONObject(EdelweissObjectConstants.RESPONSE_DATA).getString(EdelweissCommonConstants.LMS_QUOTE_ID));
				log.debug("applicationRequestBody"+applicationRequestBody);
				etipCoreAPI.callPostAPI(JSONFactoryUtil.createJSONObject(applicationRequestBody), edelweissGenerateApplicationNoConfiguration.getGenerateApplicationURL(), true, edelweissGenerateApplicationNoConfiguration.getGenerateApplicationXAPIKEY(), applicationAuthorization);
			}
			// Hide success and error messages
			hideDefaultErrorMessage(actionRequest);
			hideDefaultSuccessMessage(actionRequest);
			
			try {
				Map<String, Serializable> values = new HashMap<>();
				values.put("quoteStage", EdelweissCommonConstants.SITE_SECTION_SUMMARY);
				edelweissTokioCommonAPI.updateCustomerQuoteStage(values,
						Long.valueOf(customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString("id")),
						ServiceContextFactory.getInstance(actionRequest));
			} catch (Exception e) {
			}
			
			// Step 19: Redirecting to external domain
			actionResponse.sendRedirect(policyResponse.getJourneyURL());
		} catch (ETIPSystemException e) {
			log.error("EdelweissProductSummaryActionCommand >>> processAction >>> An error occurred getting the customer details ::: " + e);
		}catch (JsonMappingException e) {
			log.error("EdelweissProductSummaryActionCommand >>> processAction >>> An error occurred while mapping the response to the object ::: " + e);
		}catch (JsonProcessingException e) {
			log.error("EdelweissProductSummaryActionCommand >>> processAction >>> An error occurred while processing the reponse to the object ::: " + e);
		}catch (UnsupportedEncodingException e) {
			log.error("EdelweissProductSummaryActionCommand >>> processAction >>> An error occurred while encoding the URL ::: " + e);
		}
	}

}
