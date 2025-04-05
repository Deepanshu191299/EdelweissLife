package in.edelweiss.product.summary.web.mvc.command;

import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS;
import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL;
import static in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys.URL_FOR_FETCHING_BASIC_DETAILS;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.BI_PDF_PATH;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.ILLUSTRATION_URL;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.INTEREST_SESSION_STAGE_GET_STARTED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_OPTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.POLICY_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_FREQUENCY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PREMIUM_PAYMENT_TERM;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.PRODUCT_NAME_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE_CUSTOMISE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.RECOMMENDED_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_SUMMARY;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.STAGE_INTEREST_SESSION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SUM_ASSURED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SWP_YN;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.TOTAL_PREMIUM_AMOUNT;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ASSURED_DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ASSURED_FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CHILD_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ITEMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MARITAL_STATUS_IS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MYSELF;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PREMIUM_PAYING_TERM;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.TOBACCO;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.TOTAL_RETURNS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NATURE_OF_DUTY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EDUCATION_QUALIFICATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PINCODE;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.summary.web.constants.EdelweissProductSummaryWebPortletKeys;
import in.edelweiss.product.summary.web.util.EdelweissProductSummaryUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.common.model.CustomerInvestmentDetails;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;
@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductSummaryWebPortletKeys.EDELWEISSPRODUCTSUMMARYWEB,
		"mvc.command.name=/edit/basicDetailsURL" + "" }, service = MVCActionCommand.class)
public class EdelweissProductSummaryEditDetailsActionCommand extends BaseMVCActionCommand {

	@Reference
	EdelweissTokioCommonApi edelweissTokioCommonAPI;

	@Reference
	ETIPCoreAPI etipCoreAPI;
	
	private static final Log log = LogFactoryUtil.getLog(EdelweissProductSummaryEditDetailsActionCommand.class);

	@Override
	public void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
		String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
		String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
		
		// Step :1 Getting value for whom you are investing & required metadata
		String investingFamilyValue = ParamUtil.getString(actionRequest, EdelweissCommonConstants.INVESTING_FOR);
		//Step 2 : Getting lead value from cookie
		String leadId = edelweissTokioCommonAPI.getLeadIdFromCookie(actionRequest, LEAD_ID);
		String productName = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getPlid(), EdelweissObjectConstants.PRODUCT_NAME);
		//Step 3 : Getting customer enquiry data details
		String customerEnquiriesDataURL = EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_URL + EdelweissProductSummaryWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
		JSONObject customerEnquiryData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerEnquiriesDataURL, false, liferayUserName, liferayPassword);
		// Step 4 : Getting investment data details 
		String investmentDetailsURL = EdelweissProductSummaryWebPortletKeys.GET_INVESTMENT_DETAILS + EdelweissProductSummaryWebPortletKeys.GET_INVESTMENT_DETAILS_BY_LEAD_ID_QUERY_PARAMS + EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
		JSONObject investmentData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+investmentDetailsURL, false, liferayUserName, liferayPassword);
		// Step 5 : Getting value of picklist policy options
		Map<String, String > policyOptionMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(EdelweissCommonConstants.MASTER_POLICY_OPTIONS_PICKLIST_EXTREF_CODE, themeDisplay.getCompanyId());
		String policyOptionValue = policyOptionMap.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("policyOption"));
		// Step 6 : Getting value of SWP options
		Map<String, String> swpValueMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode("SWP_MASTER", themeDisplay.getCompanyId());
		String systematicWithdrawlPlanValue = swpValueMap.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("systematicWithdrawalPlan"));
		// Getting value of PAYMENT_OPTIONS_MASTER
		Map<String, String> paymentOptionsMaster = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode("PAYMENT_OPTIONS_MASTER", themeDisplay.getCompanyId());
		String paymentOptions = paymentOptionsMaster.get(investmentData.getJSONArray(ITEMS).getJSONObject(0).getString("paymentFrequency"));
				
		// Step 7 ; Getting customer Policy Details 
		String customerPolicyURL = GET_CUSTOMER_POLICY_METADATA_BY_LEAD_ID_URL+GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS+EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
		JSONObject customerPolicyDetailsData = etipCoreAPI.callGetAPI(new HashMap<>(), themeDisplay.getPortalURL()+customerPolicyURL, false, liferayUserName, liferayPassword);
		String biPDFPath = customerPolicyDetailsData.getJSONArray(ITEMS).getJSONObject(0).getString(ILLUSTRATION_URL);
		// Step 8 : Getting value of investment objective  options
		Map<String, String> investmentObjectiveMap = edelweissTokioCommonAPI.getPicklistByExternalReferenceCode(INVESTMENT_OBJECTIVE, themeDisplay.getCompanyId());
		String investmentObjValue = investmentObjectiveMap.get(customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString("investmentObjective"));
		
		boolean maritalStatus = ParamUtil.getBoolean(actionRequest, MARITAL_STATUS_IS);
		boolean hasChild = ParamUtil.getString(actionRequest, CHILD_NAME).equalsIgnoreCase("yes") ? true : false;

		// Step 9 (i): Checking if investing for is family 
		if (investingFamilyValue.equalsIgnoreCase(FAMILY)) {
			String parameterURL = EdelweissProductSummaryUtil.getLeadMetaDataURL(leadId);
			String getCustomerEnquiryURL = themeDisplay.getPortalURL() + URL_FOR_FETCHING_BASIC_DETAILS
					+ GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS + parameterURL;
			JSONObject basicDetailsObject = etipCoreAPI.callGetAPI(new HashMap<>(), getCustomerEnquiryURL, false, liferayUserName, liferayPassword);
			//Step 9 (a) :  Calling get family details method 
			Map<String, String> familyFormMap = edelweissTokioCommonAPI.getFamilyDetails(actionRequest,
					basicDetailsObject.getJSONArray(EdelweissObjectConstants.ITEMS).getJSONObject(0));
			//Step 9(b) : Calling edit basic details 
			Map<String, String> editMapForm = edelweissTokioCommonAPI.getEditBasicDetails(actionRequest);
			editMapForm.put(EdelweissCommonConstants.CHILD_NAME, familyFormMap.get(ASSURED_FULL_NAME));
			editMapForm.put(EdelweissCommonConstants.CHILD_DOB, familyFormMap.get(ASSURED_DATE_OF_BIRTH));
			editMapForm.put(FAMILY_ID, familyFormMap.get(FAMILY_ID));
			editMapForm.put(PRODUCT_NAME_QUOTE, productName);
			String productCode = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
			editMapForm.put(PRODUCT_CODE, productCode);
			editMapForm.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productCode);
			editMapForm.put(QUOTE_STAGE, QUOTE_STAGE_CUSTOMISE);
			editMapForm.put(STAGE_INTEREST_SESSION, INTEREST_SESSION_STAGE_GET_STARTED);
			editMapForm.put(TOTAL_PREMIUM_AMOUNT, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.INVESTMENT_AMOUNT_PARAM));
			editMapForm.put(POLICY_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.POLICY_TERM_PARAM));
			editMapForm.put(PREMIUM_PAYMENT_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(PREMIUM_PAYING_TERM));
			editMapForm.put(SUM_ASSURED, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(TOTAL_RETURNS));
			editMapForm.put(SITE_SECTION, SITE_SECTION_SUMMARY);
			editMapForm.put(BI_PDF_PATH,biPDFPath);
			editMapForm.put(RECOMMENDED_OBJECTIVE, investmentObjValue);
			editMapForm.put(POLICY_OPTION, policyOptionValue);
			editMapForm.put(SWP_YN, systematicWithdrawlPlanValue);
			editMapForm.put(LEAD_FORM_TYPE, RECCOMENDATION);
			editMapForm.put(PREMIUM_PAYMENT_FREQUENCY, paymentOptions);
			editMapForm.put(ID, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(ID));
			// Code for removing little champ benefits 
			String littleChampValue = ParamUtil.getString(actionRequest, "littleChamp", StringPool.BLANK);
			if(Validator.isNotNull(littleChampValue)) {
				familyFormMap.put("removeLittleChamp", littleChampValue);
				CustomerFamilyDetailsRel customerFamilyDetailsRel = EdelweissProductSummaryUtil.getRequestCustomerFamilyDetails(familyFormMap, leadId);
			    edelweissTokioCommonAPI.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetailsRel, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.FAMILY_ID));
			}
			String risingStarValue = ParamUtil.getString(actionRequest, "risingStar", StringPool.BLANK);
			
		    if(Validator.isNotNull(risingStarValue)) {
		    	familyFormMap.put("risingStar", risingStarValue);
		    	CustomerFamilyDetailsRel customerFamilyDetails = EdelweissProductSummaryUtil.getRequestCustomerFamilyDetails(familyFormMap, leadId);
			    edelweissTokioCommonAPI.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.FAMILY_ID));
		    }
		    //Code to get investment details
			CustomerInvestmentDetails customerInvestmentDetails = edelweissTokioCommonAPI.getInvestmentDetailsLeadId(themeDisplay.getPortalURL(), leadId);
			CustomerInvestmentDetails updatedCustomerInvestmentDetail = new CustomerInvestmentDetails();
			updatedCustomerInvestmentDetail.setInvestingFor(FAMILY);
			edelweissTokioCommonAPI.updateInvestmentDetailsById(themeDisplay.getPortalURL(), updatedCustomerInvestmentDetail, customerInvestmentDetails.getId());
			// Code for updating in lr 
			edelweissTokioCommonAPI.updateDetails(actionRequest, editMapForm);
			String updateReqBody=edelweissTokioCommonAPI.getUpdateLMSRequestBody(editMapForm);
			log.debug("EdelweissProductSummaryEditDetailsActionCommand>>>> doProcessAction >>>> lead request Body ::: " + updateReqBody);
			// Calling external update response 
			JSONObject updateResponse = edelweissTokioCommonAPI.updateLMSLead(updateReqBody);
			log.debug("EdelweissProductSummaryEditDetailsActionCommand>>>> doProcessAction >>>>  lead update ::: " + updateResponse);
			
		} else {
			
			Map<String, String> editMapForm = edelweissTokioCommonAPI.getEditBasicDetails(actionRequest);
			editMapForm.put(PRODUCT_NAME_QUOTE, productName);
			String productCode = edelweissTokioCommonAPI.getCustomFieldValue(themeDisplay.getCompanyId(), Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
			editMapForm.put(PRODUCT_CODE, productCode);
			editMapForm.put(EdelweissCommonConstants.PRODUCT_CODE_KEY, productCode);
			editMapForm.put(STAGE_INTEREST_SESSION, INTEREST_SESSION_STAGE_GET_STARTED);
			editMapForm.put(TOTAL_PREMIUM_AMOUNT, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.INVESTMENT_AMOUNT_PARAM));
			editMapForm.put(POLICY_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissCommonConstants.POLICY_TERM_PARAM));
			editMapForm.put(PREMIUM_PAYMENT_TERM, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(PREMIUM_PAYING_TERM));
			editMapForm.put(SUM_ASSURED, investmentData.getJSONArray(ITEMS).getJSONObject(0).getString(TOTAL_RETURNS));
			editMapForm.put(SITE_SECTION, SITE_SECTION_SUMMARY);
			editMapForm.put(QUOTE_STAGE, QUOTE_STAGE_CUSTOMISE);
			editMapForm.put(BI_PDF_PATH,biPDFPath);
			editMapForm.put(RECOMMENDED_OBJECTIVE, investmentObjValue);
			editMapForm.put(POLICY_OPTION, policyOptionValue);
			editMapForm.put(SWP_YN, systematicWithdrawlPlanValue);
			editMapForm.put(LEAD_FORM_TYPE, RECCOMENDATION);
			editMapForm.put(PREMIUM_PAYMENT_FREQUENCY, paymentOptions);
			editMapForm.put(ID, customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(ID));
			
			if(productName.equals("Zindagi Protect Plus")) {
				String annualIncome = ParamUtil.getString(actionRequest, INCOME);
				annualIncome = annualIncome.replaceAll(",","");
				editMapForm.put(INCOME, annualIncome);
				editMapForm.put(TOBACCO, ParamUtil.getString(actionRequest, SMOKE));
				editMapForm.put(MARRIED, ParamUtil.getString(actionRequest, MARITAL_STATUS_IS));
				editMapForm.put(SPOUSE_NAME, ParamUtil.getString(actionRequest, SPOUSE_NAME));
				editMapForm.put(SPOUSE_DOB, ParamUtil.getString(actionRequest, SPOUSE_DOB));
				editMapForm.put(SPOUSE_SMOKE, ParamUtil.getString(actionRequest, SPOUSE_SMOKE));
				editMapForm.put(CHILD_DOB, ParamUtil.getString(actionRequest, CHILD_DOB));
			}
			
			/*
			 *  new entry for the saral jeevan bima
			 */
			if(productCode.equals("40039")) {
				editMapForm.put(OCCUPATION, ParamUtil.getString(actionRequest, OCCUPATION));
				editMapForm.put(EDUCATION_QUALIFICATION, ParamUtil.getString(actionRequest, EDUCATION_QUALIFICATION));
				editMapForm.put(NATURE_OF_DUTY, ParamUtil.getString(actionRequest, NATURE_OF_DUTY));
				editMapForm.put(ANNUAL_INCOME, ParamUtil.getString(actionRequest, ANNUAL_INCOME));
				editMapForm.put(PINCODE, ParamUtil.getString(actionRequest, PINCODE));
				editMapForm.put(EdelweissObjectConstants.SMOKER, ParamUtil.getString(actionRequest, EdelweissObjectConstants.SMOKER));
			}
			
			if(Validator.isNotNull(customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.FAMILY_ID))) {
				edelweissTokioCommonAPI.deleteFamilyDetailsById(themeDisplay.getPortalURL(), customerEnquiryData.getJSONArray(ITEMS).getJSONObject(0).getString(EdelweissObjectConstants.FAMILY_ID));
				editMapForm.put(FAMILY_ID, StringPool.BLANK);
			}
			//Code to get investment details
			CustomerInvestmentDetails customerInvestmentDetails = edelweissTokioCommonAPI.getInvestmentDetailsLeadId(themeDisplay.getPortalURL(), leadId);
			CustomerInvestmentDetails updatedCustomerInvestmentDetail = new CustomerInvestmentDetails();
			updatedCustomerInvestmentDetail.setInvestingFor(MYSELF);
			edelweissTokioCommonAPI.updateInvestmentDetailsById(themeDisplay.getPortalURL(), updatedCustomerInvestmentDetail, customerInvestmentDetails.getId());
			
			
			edelweissTokioCommonAPI.updateDetails(actionRequest, editMapForm);
			String updateReqBody=edelweissTokioCommonAPI.getUpdateLMSRequestBody(editMapForm);
			log.debug("EdelweissProductSummaryEditDetailsActionCommand>>>> doProcessAction >>>> lead request Body ::: " + updateReqBody);
			// Calling external update response 
			JSONObject updateResponse = edelweissTokioCommonAPI.updateLMSLead(updateReqBody);
			log.debug("EdelweissProductSummaryEditDetailsActionCommand>>>> doProcessAction >>>>  lead update ::: " + updateResponse);
		
			CustomerEnquiry customerEnquiry = edelweissTokioCommonAPI.getCustomerEnquiryByLeadId(themeDisplay.getPortalURL(), leadId);
			if(maritalStatus || hasChild) {
				if(Validator.isNotNull(customerEnquiry)) {
					
					CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
					customerFamilyDetails.setLeadId(leadId);
					customerFamilyDetails.setrCustomerFamilyDetailsRelCCustomerEnquiryId(customerEnquiry.getId());
					
					customerFamilyDetails.setSpouseName(ParamUtil.getString(actionRequest, SPOUSE_NAME));
					customerFamilyDetails.setSpouseDateOfBirth(ParamUtil.getString(actionRequest, SPOUSE_DOB));
					customerFamilyDetails.setSpouseSmoke(ParamUtil.getString(actionRequest, SPOUSE_SMOKE));
					customerFamilyDetails.setChildDateOfBirth(ParamUtil.getString(actionRequest, CHILD_DOB));

					if(!maritalStatus) {
						customerFamilyDetails.setSpouseName(StringPool.BLANK);
						customerFamilyDetails.setSpouseDateOfBirth(StringPool.BLANK);
						customerFamilyDetails.setSpouseSmoke(StringPool.BLANK);
					}else if(!hasChild) {
						customerFamilyDetails.setChildDateOfBirth(StringPool.BLANK);
					}
					
					log.info("EdelweissProductSummaryEditDetailsActionCommand >>> doProcessAction >>> Customer Family Details ::: " + customerFamilyDetails.toString());

					// Add/Update Family
					CustomerFamilyDetailsRel updatedCustomerFamilyDetails = edelweissTokioCommonAPI.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails, StringPool.BLANK);

					//Update Family Id to Customer Enquiry Table is not exist
					if(Validator.isNull(customerEnquiry.getFamilyId())) {
						// Family Id update in Customer Enquiry Liferay Object
						editMapForm.put(FAMILY_ID, String.valueOf(updatedCustomerFamilyDetails.getId()));
					}
				}
			}else if (Validator.isNotNull(ParamUtil.getString(actionRequest, EdelweissObjectConstants.SPOUSE_PARENT_RELATION))) {
				
				Map<String, String> familyDetailsparam = new HashMap<>();
				familyDetailsparam.put(EdelweissObjectConstants.ASSURED_RELATION,
						ParamUtil.getString(actionRequest, EdelweissObjectConstants.SPOUSE_PARENT_RELATION, StringPool.BLANK));
				familyDetailsparam.put(EdelweissObjectConstants.FAMILY_OCCUPATION, ParamUtil
						.getString(actionRequest, EdelweissObjectConstants.SPOUSE_OCCUPATION, StringPool.BLANK));
				familyDetailsparam.put(EdelweissObjectConstants.FAMILY_TOTAL_SUM_ASSURED, ParamUtil
						.getString(actionRequest, EdelweissObjectConstants.SPOUSE_SUMASSURED, StringPool.BLANK));
				familyDetailsparam.put(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID,
						Validator.isNotNull(customerEnquiry)?String.valueOf(customerEnquiry.getId()):StringPool.BLANK);

				CustomerFamilyDetailsRel customerFamilyDetails = edelweissTokioCommonAPI
						.getRequestCustomerFamilyDetails(familyDetailsparam, leadId);
			
				CustomerFamilyDetailsRel updatedCustomerFamilyDetails = edelweissTokioCommonAPI
						.updateFamilyDetails(themeDisplay.getPortalURL(), customerFamilyDetails, StringPool.BLANK);
				
				if(Validator.isNull(customerEnquiry.getFamilyId())) {
					editMapForm.put(FAMILY_ID, String.valueOf(updatedCustomerFamilyDetails.getId()));
				}
				
			} else {
				JSONObject familyDetailsResponse = edelweissTokioCommonAPI.getFamilyDetailsByLeadId(themeDisplay.getPortalURL(), leadId);
				if(Validator.isNotNull(familyDetailsResponse)) {
					log.info("Get Family Details>>>>"+familyDetailsResponse);	
					edelweissTokioCommonAPI.deleteFamilyDetailsById(themeDisplay.getPortalURL(), familyDetailsResponse.getString(EdelweissObjectConstants.ID));
				}
			}
		}
		// Hide success a nd error messages
		hideDefaultErrorMessage(actionRequest);
		hideDefaultSuccessMessage(actionRequest);
		// Step 10: Redirect to Customize page so that user can see the updated premium on basis of the information changed by user
		if(productName.equalsIgnoreCase("Wealth Rise Plus")) {
			actionResponse.sendRedirect("/wealth-rise-plus/customize");
		}else if(productName.equalsIgnoreCase("Wealth Plus")) {
			actionResponse.sendRedirect("/wealth-plus/customize");
		}else if(productName.equalsIgnoreCase("Wealth Secure Plus")) {
			actionResponse.sendRedirect("/wealth-secure-plus/customize");
		}else if(productName.equalsIgnoreCase("Capital Secure Plus")) {
			actionResponse.sendRedirect("/capital-secure-plus/customize");
		}else if(productName.equalsIgnoreCase("Active Income")) {
			actionResponse.sendRedirect("/active-income/customize");
		}else {
			String redirectURL = themeDisplay.getURLCurrent();
			redirectURL = redirectURL.split("/summary")[0];
			actionResponse.sendRedirect(redirectURL + "/customize");
		}
		

	}

}