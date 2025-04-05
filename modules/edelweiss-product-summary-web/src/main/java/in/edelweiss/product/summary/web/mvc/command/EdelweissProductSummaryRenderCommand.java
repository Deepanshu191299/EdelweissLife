package in.edelweiss.product.summary.web.mvc.command;

import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS;
import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL;
import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.PRODUCT_RELATIONS_DATA;
import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.URL_FOR_FETCHING_BASIC_DETAILS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUSTOMER_INVESTMENT_DETAILS_BASE_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FILTER_QUERY_PARAM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ITEMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;
import in.edelweiss.product.summary.web.util.EdelweissProductSummaryUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.ApplicationSummaryData;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetailsRel;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductSummaryWebPortletKeys.EDELWEISSPRODUCTSUMMARYWEB,
		"mvc.command.name=/" }, service = MVCRenderCommand.class)
public class EdelweissProductSummaryRenderCommand implements MVCRenderCommand {

	@Reference
	ETIPCoreAPI etipCoreAPI;
	@Reference 
	EdelweissTokioCommonApi edelweissTokioCommonAPI ; 
	Log log = LogFactoryUtil.getLog(EdelweissProductSummaryRenderCommand.class);
   
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String summaryJsp = "/summary.jsp";
		
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		String portalURL = themeDisplay.getPortalURL();
		
		String customizepageURL = themeDisplay.getURLCurrent().split("/summary")[0];
		renderRequest.setAttribute("customizepageURL", customizepageURL.concat("/customize"));
		String webEngageSummaryPageURL = ConfigurationUtil.getEdelweissWebengageURLConfiguration().getSummaryPageURL();
		renderRequest.setAttribute("webEngageURL", webEngageSummaryPageURL);
		EdelweissProductSummaryUtil.hideSuccessAndErrorMessage(renderRequest);
		// Step 1: Get LeadId from Cookie
		String leadId = edelweissTokioCommonAPI.getLeadIdFromCookie(renderRequest, EdelweissObjectConstants.LEAD_ID);
		if(Validator.isNotNull(leadId)) {
			String productName = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
			String productId = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_ID);
			String productCode = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_CODE);
			log.debug("EdelweissProductSummaryRenderCommand >>> render invoked >>> Lead Id ::: " + leadId);
			
			JSONObject productMasterData = edelweissTokioCommonAPI.getProductMasterByProductCode(themeDisplay.getPortalURL(), productCode);
			renderRequest.setAttribute("productMasterData", productMasterData);
			
			try {
				long companyId = themeDisplay.getCompanyId();
				String parameterURL = EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
				String getCustomerEnquiryURL = themeDisplay.getPortalURL() + URL_FOR_FETCHING_BASIC_DETAILS + GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS + parameterURL;
				JSONObject customerEnquiryData = etipCoreAPI.callGetAPI(new HashMap<>(), getCustomerEnquiryURL, false, liferayUserName, liferayPassword);
				// Step 2: Invoke LR Headless API (CustomerEnquiry, CustomerPolicyDetails, CustomerFamilyDetails, CustomerInvestmentDetails) to fetch the personal details of Customer
				String customerEnquiryAPIURL = MessageFormat.format(EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_ENQUIRY_RELATIONAL_API + EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_ENQUIRY_RELATIONAL_DATA_QUERY_PARAMS, customerEnquiryData.getJSONArray(EdelweissObjectConstants.ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.ID));
				log.debug("EdelweissProductSummaryRenderCommand >>> render invoked >>> locationURL ::: " + customerEnquiryAPIURL);
				Map<String , String> selectivePicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.INVESTMENT_OBJECTIVE_PICKLIST_ERC, companyId);
				JSONObject apiResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiryAPIURL, false, liferayUserName, liferayPassword);
				
				String locationURL = GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL+GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS+parameterURL;
				
				JSONObject illustrationResponseObject = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+locationURL, false, liferayUserName, liferayPassword);
				
				if(Validator.isNotNull(illustrationResponseObject) && Validator.isNotNull(illustrationResponseObject.getJSONArray(EdelweissObjectConstants.ITEMS))) {
					
					String illustrationUrl = (illustrationResponseObject.getJSONArray(EdelweissObjectConstants.ITEMS).length()>0)?illustrationResponseObject.getJSONArray(EdelweissObjectConstants.ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.ILLUSTRATION_URL):StringPool.BLANK;
					renderRequest.setAttribute(EdelweissCommonConstants.ILLUSTRATION_URL, illustrationUrl);
				}

				// Step 3: Map the response to the class using object mapper
				 ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				 ApplicationSummaryData summaryData =  mapper.readValue(apiResponseObject.toJSONString(), ApplicationSummaryData.class);
				 
				 log.debug(summaryData);
			
				 JSONObject biJSONAndInputValidation = JSONFactoryUtil.createJSONObject(summaryData.getCustomerInvestmentDetailsRel().get(0).getBiJSONAndInputValidation());
				 JSONArray biInputValidationsArray = biJSONAndInputValidation.getJSONArray("biInputValidations");
				 JSONArray allRidersArray = extractRiderDetails(getAllRiders(themeDisplay));
				// Convert JSONArray to List using ObjectMapper
				 ObjectMapper biInputObjectMapper = new ObjectMapper();

				// Check for null or empty biInputValidationsArray
				List<Map<String, Object>> biInputValidationsList = Collections.emptyList();
				if (biInputValidationsArray != null && biInputValidationsArray.length() > 0) {
				    biInputValidationsList = biInputObjectMapper.readValue(
				        biInputValidationsArray.toString(),
				        biInputObjectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)
				    );
				}

				// Check for null or empty allRidersArray
				List<Map<String, Object>> ridersList = Collections.emptyList();
				if (allRidersArray != null && allRidersArray.length() > 0) {
				    ridersList = biInputObjectMapper.readValue(
				        allRidersArray.toString(),
				        biInputObjectMapper.getTypeFactory().constructCollectionType(List.class, Map.class)
				    );
				}

				// Set attributes for rendering
				renderRequest.setAttribute("biInputValidationsList", biInputValidationsList);
				renderRequest.setAttribute("ridersList", ridersList);
				 // Step 4: Pass the required data to jsp
				 renderRequest.setAttribute(EdelweissCommonConstants.SUMMARY_DATA, summaryData);
				 renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_NAME, productName);
				 renderRequest.setAttribute(EdelweissCommonConstants.PRODUCT_CODE_PARAM, productCode);
				 renderRequest.setAttribute(EdelweissCommonConstants.SELECTIVE_PICKLIST, selectivePicklist);
				 if(Validator.isNotNull(summaryData.getCustomerInvestmentDetailsRel()) && summaryData.getCustomerInvestmentDetailsRel().size()>0) {
					 CustomerInvestmentDetailsRel customerInvestmentDetails = summaryData.getCustomerInvestmentDetailsRel().get(0);
					 
					 JSONArray selectedRidersList= JSONFactoryUtil.createJSONArray(Validator.isNotNull(customerInvestmentDetails.getRiderId())?customerInvestmentDetails.getRiderId():StringPool.BLANK);
					 renderRequest.setAttribute("selectedRidersList", selectedRidersList);
				 }
				 
				 JSONObject productRelDataResponse = edelweissTokioCommonAPI.getProductNestedFieldDataByProductId(portalURL + EdelweissObjectConstants.PRODUCT_RELS_SERVICE_URL, productId);
				 LiferayResponseMessage productRelationsData = EdelweissProductSummaryUtil.getProductRelationsData(productRelDataResponse);
				 if(Validator.isNotNull(productRelationsData)) {
					 renderRequest.setAttribute(PRODUCT_RELATIONS_DATA, productRelationsData);
				 }
				
				 // Picklist Investing For Master, Payment Option Master, SWP Master, WRP Policy For, Policy Options Master
				 Map<String, String> paymentFrequencyPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.PAYMENT_OPTIONS_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> swpMasterPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SWP_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> fundManagementPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FUND_MANAGEMENT_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> investingForPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.INVESTING_FOR_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> fundWithdrawalPercentPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FUND_WITHDRAWAL_PERCENTAGE_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> swpWithdrawalYearPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SWP_WITHDRAWAL_YEAR_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> fundWithdrawalFrequencyPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FUND_WITHDRAWAL_FREQUENCY_MASTER_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> gcapPayoutOption = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.GCAP_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				 
				 Map<String, String> policyOptionMasterPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.MASTER_POLICY_OPTIONS_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> familyIncomBenefitrPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.FAMILY_INCOME_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> lumpSumBenefitPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> isEdelweissEmployeePicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.IS_EDELWEISS_EMPLOYEE_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> incomePayoutFrequencyPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.INCOME_PAYOUT_FREQUENCY_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> maturityPayoutOptionPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.MATURITY_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> activeIncomePlanFamilyBenefitIncome = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.AIP_FAMILY_INCOME_BENEFIT, companyId);
				 Map<String, String> activeIncomePlanMaturityAge = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.AIP_MATURITY_AGE, companyId);
				 Map<String, String> activeIncomePlanIncomeOption = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.AIP_INCOME_OPTION, companyId);
				 
				 Map<String, String> gisIncomeStartPointPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.GIS_INCOME_START_POINT_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> gisLumpSumBenefitPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.GIS_LUMP_SUM_BENEFIT_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> gisIncomeDurationPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.GIS_INCOME_DURATION_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> gisIncomeBenefitPayoutTypePicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.GIS_INCOME_BENEFIT_PAYOUT_TYPE_PICKLIST_EXTREF_CODE, companyId);
				 
				 Map<String, String> ibPayoutOptionPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.IB_PAYOUT_OPTION_PICKLIST_EXTREF_CODE, companyId);
				 Map<String, String> ibPayoutPeriodPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.IB_PAYOUT_PERIOD_PICKLIST_EXTREF_CODE, companyId);
				 
				 if(productName.equals("Zindagi Protect Plus")) {
					 Map<String, String> annualIncomeRangePeriodPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.ZP_ANNUAL_INCOME_MASTER_PICKLIST_ERC, companyId);
					 Map<String, String> occupationPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.OCCUPATION_MASTER, companyId);
					 Map<String, String> educationQualificationPicklistData = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_EDUCATIONAL_QUALIFICATION, companyId);
					 renderRequest.setAttribute("annualIncomeRangePeriodPicklistData", annualIncomeRangePeriodPicklistData);
					 renderRequest.setAttribute("occupationPicklistData", occupationPicklistData);
					 renderRequest.setAttribute("educationQualificationPicklistData", educationQualificationPicklistData);
				 }
				 
				 /*
				  *  picklist data for saral jeevan bima
				  */
				 if(productCode.equalsIgnoreCase("40039")) {
					 Map<String, String> youroccupationPicklist = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_OCCUPATION, companyId);
					 Map<String, String> youreducationalqualification = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SJB_EDUCATIONQULIFICATION, companyId);
					 Map<String, String> yournatureofduty = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_NATURE_OF_DUTY, companyId);
					 Map<String, String> yourspouse = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_SPOUSE ,companyId);
					 Map<String, String> spouseoccupation = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SPOUSE_OCCUPATION, companyId);
					 
					 renderRequest.setAttribute("youroccupationPicklist", youroccupationPicklist);
					 renderRequest.setAttribute("youreducationalqualification", youreducationalqualification);
					 renderRequest.setAttribute("yournatureofduty", yournatureofduty);
					 renderRequest.setAttribute("yourspouse", yourspouse);
					 renderRequest.setAttribute("spouseoccupation", spouseoccupation);
				 }
				 
				 /*
				  *  load needed data of GFS
				  */
				 if(productCode.equalsIgnoreCase("40071")) {
					 String customizePageProductJsonString = renderProductJsonConfig(productCode, renderRequest);
					 
					 ObjectMapper objectMapper = new ObjectMapper();
					    Map<String, Object> customizePageProductJson = null;
						try {
							customizePageProductJson = objectMapper.readValue(
							    customizePageProductJsonString, new TypeReference<Map<String, Object>>() {}
							);
						} catch (JsonProcessingException e) {
							log.error("error ::: "+e);
						}
						// Set the Map as a request attribute
						renderRequest.setAttribute("customizePageProductJson", customizePageProductJson);
				 }
				 
				
				 renderRequest.setAttribute(EdelweissCommonConstants.FAMILY_INCOME_BENEFIT, familyIncomBenefitrPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.LUMP_SUM_BENEFIT, lumpSumBenefitPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.IS_EDELWEISS_EMPLOYEE, isEdelweissEmployeePicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.INCOME_PAYOUT_FREQUENCY, incomePayoutFrequencyPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.MATURITY_PAYOUT_OPTION, maturityPayoutOptionPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.MASTER_POLICY_OPTIONS, policyOptionMasterPicklist);
				 
				 renderRequest.setAttribute(EdelweissCommonConstants.PAYMENT_FREQUENCY_PICKLIST, paymentFrequencyPicklist);
				 renderRequest.setAttribute(EdelweissCommonConstants.SWP_MASTER_PICKLIST, swpMasterPicklist);
				 renderRequest.setAttribute(EdelweissCommonConstants.FUND_MANAGEMENT_PICKLIST, fundManagementPicklist);
				 renderRequest.setAttribute(EdelweissCommonConstants.INVESTING_FOR_PICKLIST, investingForPicklist);
				 renderRequest.setAttribute(EdelweissCommonConstants.FUND_WITHDRAWAL_PER_PICKLIST_DATA, fundWithdrawalPercentPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.SWP_WITHDRAWAL_YEAR_PICKLIST_DATA, swpWithdrawalYearPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.FUND_WITHDRAWAL_FREQ_PICKLIST_DATA, fundWithdrawalFrequencyPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.GCAP_PAYOUT_OPTION_PICKLIST, gcapPayoutOption);
				 
				 renderRequest.setAttribute(EdelweissCommonConstants.ACTIVE_INCOME_PLAN_FAMILY_INCOME_BENEFIT, activeIncomePlanFamilyBenefitIncome);
				 renderRequest.setAttribute(EdelweissCommonConstants.ACTIVE_INCOME_PLAN_MATUIRTY_AGE, activeIncomePlanMaturityAge);
				 renderRequest.setAttribute(EdelweissCommonConstants.ACTIVE_INCOME_PLAN_INCOME_OPTION, activeIncomePlanIncomeOption);
				 
				 renderRequest.setAttribute(EdelweissCommonConstants.GIS_INCOME_START_POINT, gisIncomeStartPointPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.GIS_LUMP_SUM_BENEFI, gisLumpSumBenefitPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.GIS_INCOME_DURATION, gisIncomeDurationPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.GIS_INCOME_BENEFIT, gisIncomeBenefitPayoutTypePicklistData);
				 
				 renderRequest.setAttribute(EdelweissCommonConstants.IB_PAYOUT_OPTION, ibPayoutOptionPicklistData);
				 renderRequest.setAttribute(EdelweissCommonConstants.IB_PAYOUT_PERIOD, ibPayoutPeriodPicklistData);
				 
			} catch (ETIPSystemException e) {
				log.error("EdelweissProductSummaryRenderCommand >>> render invoked >>> An error occurred getting the customer details ::: " + e);
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}catch (JsonMappingException e) {
				log.error("EdelweissProductSummaryRenderCommand >>> render invoked >>> An error occurred while mapping the response to the object ::: " + e);
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}catch (JsonProcessingException e) {
				log.error("EdelweissProductSummaryRenderCommand >>> render invoked >>> An error occurred while processing the reponse to the object ::: " + e);
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
			} catch (JSONException e) {
				log.error("EdelweissProductSummaryRenderCommand >>> render invoked >>> An error occurred while Parsering Selected Riders List ::: " + e);
				if(log.isDebugEnabled()) {
					e.printStackTrace();
				}
			}
			
			/*
			 * Set customize page 
			 */
			if(productCode.equalsIgnoreCase("40071")) {
				summaryJsp = "/gfs/gfs-summary.jsp";
			}
			if(productCode.equalsIgnoreCase("40039")) {
				summaryJsp = "/sjb/sjb-summary.jsp";
			}
		}
		return summaryJsp;
	}
		
	private JSONObject getAllRiders(ThemeDisplay themeDisplay) {
		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		
		String productRidersURL = themeDisplay.getPortalURL()  + "/o/c/productriders/?page=1&pageSize=20";
		try {
			responseObject = etipCoreAPI.callGetAPI(new HashMap<>(), productRidersURL, false, edelweissLRBasicAuthConfiguration.getLRUsername(), edelweissLRBasicAuthConfiguration.getLRPassword());
		} catch (ETIPSystemException e) {
			log.error("EdelweissGenerateBIResourceCommand >> Error :::: "+e.getMessage());
		}
		return responseObject;
	}
	
	private JSONArray extractRiderDetails(JSONObject responseObject) {
	    JSONArray filteredArray = JSONFactoryUtil.createJSONArray();
	    
	    if (responseObject.has("items")) {
	        JSONArray items = responseObject.getJSONArray("items");
	        
	        for (int i = 0; i < items.length(); i++) {
	            JSONObject item = items.getJSONObject(i);
	            
	            JSONObject filteredItem = JSONFactoryUtil.createJSONObject();
	            filteredItem.put("title", item.getString("title", ""));
	            filteredItem.put("riderId", item.getString("riderId", ""));
	            filteredItem.put("uin", item.getString("uin", ""));
	            filteredArray.put(filteredItem);
	        }
	    }
	    
	    return filteredArray;
	}
	
	@SuppressWarnings("unchecked")
	private String renderProductJsonConfig(String productId, RenderRequest renderRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject productJsonConfiguration = edelweissTokioCommonApi
				.getProductJsonConfigurationByProductId(themeDisplay.getPortalURL(), productId);
		
		
		// Extract `customizePageProductJson` as a string
		
		JSONObject customizePageProductJson1 =  (JSONObject) productJsonConfiguration.getJSONArray(ITEMS).get(0);

		String customizePageProductJsonString = customizePageProductJson1.getString("customizePageProductJson");
		//renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_PRODUCT_JSON_CONF, productJsonConfiguration.getJSONArray(ITEMS).get(0));
		return customizePageProductJsonString;
	}
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
}