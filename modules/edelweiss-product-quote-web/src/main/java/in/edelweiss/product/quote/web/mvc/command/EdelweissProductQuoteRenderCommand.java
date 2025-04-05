package in.edelweiss.product.quote.web.mvc.command;

import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS;
import static in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys.GET_CUSTOMER_METADATA_BY_LEAD_ID_URL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ANNUAL_INCOME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.BUY_FORM_JSP;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.COUNTRY_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.CUST_MARRIED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.DATE_OF_BIRTH;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EDUCATION_QUALIFICATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.EMAIL;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_CHILD;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_CHILD_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_DOB;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FAMILY_SPOUSE_SMOKE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.FULL_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.GENDER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.INVESTMENT_OBJECTIVE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ISNRI;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.ITEMS;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NATURE_OF_DUTY;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.NRI_MOBILE_NUMBER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PINCODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_CODE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SMOKER;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_OCCUPATION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SPOUSE_SUMASSURED;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.SUITABILITY_CONSENT;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.product.quote.web.constants.EdelweissProductQuoteWebPortletKeys;
import in.edelweiss.product.quote.web.util.EdelweissProductQuoteUtil;
import in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.util.EdelweissConfigurationUtil;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductQuoteWebPortletKeys.EDELWEISSPRODUCTQUOTEWEB,
		"mvc.command.name=/" }, service = MVCRenderCommand.class)
public class EdelweissProductQuoteRenderCommand implements MVCRenderCommand {

	@Reference
	ETIPCoreAPI etipCoreApi;
	@Reference
	EdelweissTokioCommonApi edelweissTokioCommonApi;
	Log log = LogFactoryUtil.getLog(EdelweissProductQuoteRenderCommand.class);

	public static final String BUY_FORM_JSP_PAGE="/buy_form_1.jsp";
	public static final String BUY_FORM_JSP_PAGE2="/buy_form_2.jsp";
	public static final String BUY_FORM_JSP_PAGE3="/buy_form_3.jsp";
	public static final String BUY_FORM_JSP_PAGE4="/buy_form_4.jsp";
	public static final String BUY_FORM_JSP_GFS="/products/gfs.jsp";

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

		String productFormJSP = "/view.jsp";

		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long companyId = themeDisplay.getCompanyId();
		String productName = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(),
				themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String buyFormJsp = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(),themeDisplay.getLayout().getPlid(),BUY_FORM_JSP);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_CODE);
		log.debug("EdelweissProductQuoteRenderCommand >>>> render >>> Product Name ::: " + productName);
		
		String webEngageProductLandingPageURL = ConfigurationUtil.getEdelweissWebengageURLConfiguration().getBuyPageURL();
		renderRequest.setAttribute("webEngageURL", webEngageProductLandingPageURL);
		if (Validator.isNull(productName)) {
			log.error("EdelweissProductQuoteRenderCommand >>>> render >>> Unable to get the Product Name ::: ");
			return productFormJSP;
		}
		
		Map<String, String> selectiveObjectiveMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.INVESTMENT_OBJECTIVE_PICKLIST_ERC, companyId);
		Map<String, String> suitabilityRiskAppetitieMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.RISK_APPETITE_PICKLIST_ERC, companyId);
		Map<String, String> suitabilityAnnualIncomeMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_INCOME_MASTER_PICKLIST_ERC, companyId);
		Map<String, String> genderMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(GENDER,themeDisplay.getCompanyId());
		
		if(BUY_FORM_JSP_PAGE.contains(buyFormJsp)) {
			productFormJSP = BUY_FORM_JSP_PAGE;
		}else if(BUY_FORM_JSP_PAGE2.contains(buyFormJsp)) {
			productFormJSP = BUY_FORM_JSP_PAGE2;
		}else if(BUY_FORM_JSP_PAGE3.contains(buyFormJsp)) {
			productFormJSP = BUY_FORM_JSP_PAGE3;

			log.info("Indise Buy form jsp 3");
			/*
			 * Saral Jeevan Bima
			 */
			Map<String, String> youroccupationPicklist = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_OCCUPATION, companyId);
			Map<String, String> youreducationalqualification = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SJB_EDUCATIONQULIFICATION, companyId);
			Map<String, String> yournatureofduty = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_NATURE_OF_DUTY, companyId);
			Map<String, String> yourspouse = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.YOUR_SPOUSE ,companyId);
			Map<String, String> spouseoccupation = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SPOUSE_OCCUPATION, companyId);
			Map<String, String> smokerPicklistMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SMOKER, companyId);
			 genderMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.SJB_GENDER, companyId);
			renderRequest.setAttribute("youroccupationPicklist", youroccupationPicklist);
			renderRequest.setAttribute("youreducationalqualification", youreducationalqualification);
			renderRequest.setAttribute("yournatureofduty", yournatureofduty);
			renderRequest.setAttribute("yourspouse", yourspouse);
			renderRequest.setAttribute("spouseoccupation", spouseoccupation);
			renderRequest.setAttribute("smokerPicklistMap", smokerPicklistMap);
		}else if(BUY_FORM_JSP_PAGE4.contains(buyFormJsp)) {
			productFormJSP = BUY_FORM_JSP_PAGE4;

			Map<String, String> zpAnnualIncomeMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.ZP_ANNUAL_INCOME_MASTER_PICKLIST_ERC, companyId);
			renderRequest.setAttribute("zpAnnualIncomeMap", zpAnnualIncomeMap);
		}

		renderRequest.setAttribute("genderMap", genderMap);
		renderRequest.setAttribute("selectiveObjectiveMap", selectiveObjectiveMap);
		renderRequest.setAttribute("suitabilityRiskAppetitieMap", suitabilityRiskAppetitieMap);
		renderRequest.setAttribute("suitabilityAnnualIncomeMap", suitabilityAnnualIncomeMap);
		renderRequest.setAttribute("productName", productName);
		renderRequest.setAttribute("productCode", productCode);

		if(productName.equals("Zindagi Protect Plus") || productCode.equals("40069")) {
			Map<String, String> zpAnnualIncomeMap = edelweissTokioCommonApi.getPicklistByExternalReferenceCode(EdelweissCommonConstants.ZP_ANNUAL_INCOME_MASTER_PICKLIST_ERC, companyId);
			renderRequest.setAttribute("zpAnnualIncomeMap", zpAnnualIncomeMap);
			
		}

		// check whether the data is there or not
		EdelweissProductQuoteUtil.hideSuccessAndErrorMessage(renderRequest);

		try {
			EdelweissLRBasicAuthConfiguration edelweissLRBasicAuthConfiguration = EdelweissConfigurationUtil.getEdelweissLRBasicAuthConfiguration();
			String liferayUserName = edelweissLRBasicAuthConfiguration.getLRUsername();
			String liferayPassword = edelweissLRBasicAuthConfiguration.getLRPassword();
			String  leadId = edelweissTokioCommonApi.getLeadIdFromCookie(renderRequest, LEAD_ID);
			if(Validator.isNotNull(leadId)) {
				String parameterURL = EdelweissProductQuoteUtil.getLeadMetaDataURL(leadId);
				
				String urlToGetBasicDetails = themeDisplay.getPortalURL()+ GET_CUSTOMER_METADATA_BY_LEAD_ID_URL+GET_CUSTOMER_METADATA_BY_LEAD_ID_QUERY_PARAMS+parameterURL;
				
				JSONObject basicDetailsObject = etipCoreApi.callGetAPI(new  HashMap<>(), urlToGetBasicDetails, false, liferayUserName, liferayPassword);
				
				if(Validator.isNull(basicDetailsObject) || Validator.isNull(basicDetailsObject.getJSONArray(ITEMS)) || basicDetailsObject.getJSONArray(ITEMS).length()<=0) {
					log.warn("No Lead data found with id: "+leadId);
					return productFormJSP;
				}
				
				basicDetailsObject = basicDetailsObject.getJSONArray(ITEMS).getJSONObject(0);
				log.debug("Customer Details: "+basicDetailsObject);
				Map<String, String> mapJsonData = new HashMap<>();
				mapJsonData.put(FULL_NAME, basicDetailsObject.getString(FULL_NAME));
				mapJsonData.put(DATE_OF_BIRTH, basicDetailsObject.getString(DATE_OF_BIRTH));
				mapJsonData.put(MOBILE_NUMBER, basicDetailsObject.getString(MOBILE_NUMBER));
				mapJsonData.put(GENDER, basicDetailsObject.getString(GENDER));
				mapJsonData.put(EMAIL, basicDetailsObject.getString(EMAIL));
				mapJsonData.put(INVESTMENT_OBJECTIVE, basicDetailsObject.getString(INVESTMENT_OBJECTIVE));
				mapJsonData.put(SUITABILITY_CONSENT, basicDetailsObject.getString(SUITABILITY_CONSENT));
				mapJsonData.put(ISNRI, basicDetailsObject.getString(ISNRI));
				mapJsonData.put(NRI_MOBILE_NUMBER, basicDetailsObject.getString(NRI_MOBILE_NUMBER));
				mapJsonData.put(COUNTRY_CODE, basicDetailsObject.getString(COUNTRY_CODE));
				mapJsonData.put(OCCUPATION, basicDetailsObject.getString(OCCUPATION));
				mapJsonData.put(EDUCATION_QUALIFICATION, basicDetailsObject.getString(EDUCATION_QUALIFICATION));
				mapJsonData.put(NATURE_OF_DUTY, basicDetailsObject.getString(NATURE_OF_DUTY));
				mapJsonData.put(SPOUSE, basicDetailsObject.getString(SPOUSE));
				mapJsonData.put(SPOUSE_OCCUPATION, basicDetailsObject.getString(SPOUSE_OCCUPATION));
				mapJsonData.put(SPOUSE_SUMASSURED, basicDetailsObject.getString(SPOUSE_SUMASSURED));	
				mapJsonData.put(PINCODE, basicDetailsObject.getString(PINCODE));	
				mapJsonData.put(SMOKER, basicDetailsObject.getString(SMOKER));
				mapJsonData.put(LEAD_ID, leadId);
				
				/*
				 * For Zindagi Protect Product Start
				 */
				if(BUY_FORM_JSP_PAGE3.contains(buyFormJsp) || BUY_FORM_JSP_PAGE4.contains(buyFormJsp)) {
					mapJsonData.put(CUST_MARRIED, basicDetailsObject.getString(CUST_MARRIED));					
					if(Validator.isNotNull(basicDetailsObject.getString(ANNUAL_INCOME))) {
						mapJsonData.put(ANNUAL_INCOME, basicDetailsObject.getString(ANNUAL_INCOME).replace("-", "to"));
					}
					mapJsonData.put(FAMILY_CHILD, basicDetailsObject.getString(FAMILY_CHILD));
					
					JSONObject familyDetailsObject = edelweissTokioCommonApi.getFamilyDetailsByLeadId(themeDisplay.getPortalURL(), leadId);
					log.debug("Family Details: "+familyDetailsObject);
					
					if(Validator.isNotNull(familyDetailsObject)) {
						mapJsonData.put(FAMILY_SPOUSE_NAME, familyDetailsObject.getString(FAMILY_SPOUSE_NAME));
						mapJsonData.put(FAMILY_SPOUSE_DOB, familyDetailsObject.getString(FAMILY_SPOUSE_DOB));
						mapJsonData.put(FAMILY_SPOUSE_SMOKE, familyDetailsObject.getString(FAMILY_SPOUSE_SMOKE));
						mapJsonData.put(FAMILY_CHILD_DOB, familyDetailsObject.getString(FAMILY_CHILD_DOB));

						mapJsonData.put(EdelweissObjectConstants.SPOUSE_PARENT_RELATION, familyDetailsObject.getString(EdelweissObjectConstants.ASSURED_RELATION));
						mapJsonData.put(EdelweissObjectConstants.FAMILY_OCCUPATION, familyDetailsObject.getString(OCCUPATION));
						mapJsonData.put(EdelweissObjectConstants.FAMILY_TOTAL_SUM_ASSURED, familyDetailsObject.getString(EdelweissObjectConstants.TOTAL_SUM_ASSURED));
					}

				}
				
				//For Zindagi Protect Product End
				renderRequest.setAttribute("cookieLeadValue", mapJsonData);
				PortletSession portletSession = renderRequest.getPortletSession();
				String value = (String) portletSession.getAttribute("LIFERAY_SHARED_sessionMessage", PortletSession.APPLICATION_SCOPE);
				log.debug("value...."+value);
				if(Validator.isNotNull(value)) {
					renderRequest.setAttribute("fromProductPage", value);
				}else {
					renderRequest.setAttribute("fromProductPage", "0");
				}
				portletSession.removeAttribute("LIFERAY_SHARED_sessionMessage", PortletSession.APPLICATION_SCOPE);
			}
		} catch (ETIPSystemException e) {
			log.error("EdelweissProductQuoteRenderCommand >>>> render >>> Error occured while fetching the data from LR Object ::: "+e);
		} catch (Exception e) {
			log.error("EdelweissProductQuoteRenderCommand >>>> render >>> Error occured while fetching the data from LR Object ::: "+e);
		}
		
		return productFormJSP;
	}
}