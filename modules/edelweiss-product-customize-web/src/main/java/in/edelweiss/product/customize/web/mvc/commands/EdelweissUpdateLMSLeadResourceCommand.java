package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissCommonConstants.INTEREST_SESSION_STAGE_GET_STARTED;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.LEAD_FORM_TYPE_RECCOMENDATION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.QUOTE_STAGE_CUSTOMISE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.SITE_SECTION_PRE_QUOTE;
import static in.edelweiss.tokio.constants.EdelweissCommonConstants.STAGE_INTEREST_SESSION;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;


import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;
import in.edelweiss.tokio.constants.EdelweissObjectConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/update/LMSLead"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissUpdateLMSLeadResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissUpdateLMSLeadResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		try {
			log.debug("EdelweissUpdateLMSLeadResourceCommand >>>> serveResource >>>> LMS Updating :::");
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL = themeDisplay.getPortalURL();
			String productName = edelweissTokioCommonApi.getCustomFieldValue(themeDisplay.getCompanyId(),
					Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
			
			Map<String, String> updateLMSRquestMapData = new HashMap<>();
			HttpServletRequest request = PortalUtil.getHttpServletRequest(resourceRequest);
			Enumeration<String> enumeration = request.getParameterNames();
			updateLMSRquestMapData.put(LEAD_ID, leadId);
			while(enumeration.hasMoreElements()){
	            String parameterName = enumeration.nextElement();
	            updateLMSRquestMapData.put(parameterName, ParamUtil.getString(resourceRequest, parameterName));
			}
			
			updateLMSRquestMapData.put(LEAD_ID, leadId);
			updateLMSRquestMapData.put(LEAD_FORM_TYPE, LEAD_FORM_TYPE_RECCOMENDATION);
			updateLMSRquestMapData.put(QUOTE_STAGE, Validator.isNotNull(ParamUtil.getString(resourceRequest, QUOTE_STAGE)) ?
					ParamUtil.getString(resourceRequest, QUOTE_STAGE):QUOTE_STAGE_CUSTOMISE);
			updateLMSRquestMapData.put(STAGE_INTEREST_SESSION, INTEREST_SESSION_STAGE_GET_STARTED);
			updateLMSRquestMapData.put(SITE_SECTION, SITE_SECTION_PRE_QUOTE);
			
			String fundDetailsStr = ParamUtil.getString(resourceRequest, "FundDetails", StringPool.BLANK);
			
			if(Validator.isNotNull(fundDetailsStr)) {
				JSONObject fundDetailsJSON = JSONFactoryUtil.createJSONObject(fundDetailsStr);
				
				Iterator<String> fundDetailsKeys = fundDetailsJSON.keys();
				while(fundDetailsKeys.hasNext()) {
				    String key = fundDetailsKeys.next();
				    updateLMSRquestMapData.put(key, fundDetailsJSON.getString(key));
				}
			}
			
			/*if(productName.equals("Zindagi Protect")) {
				Long enqId = ParamUtil.getLong(resourceRequest, EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID, 0);
				CustomerEnquiry customerEnquiryRequest = new CustomerEnquiry();
				String annualIncome = ParamUtil.getString(resourceRequest, EdelweissObjectConstants.INCOME);
				String educationQualification = ParamUtil.getString(resourceRequest, EdelweissObjectConstants.EDUCATION_QUALIFICATION);
				String occupation = ParamUtil.getString(resourceRequest, EdelweissObjectConstants.OCCUPATION);
				customerEnquiryRequest.setAnnualIncome(annualIncome);
				customerEnquiryRequest.setEducationQualification(educationQualification);
				customerEnquiryRequest.setOccupation(occupation);
				edelweissTokioCommonApi.updateCustomerEnquiryById(portalURL, customerEnquiryRequest, enqId);
			}*/
			
			String leadUpdateRequestBody = edelweissTokioCommonApi.getUpdateLMSRequestBody(updateLMSRquestMapData);
			log.debug("EdelweissUpdateLMSLeadResourceCommand >>>> serveResource >>>> LMS Lead Update Request Body prepared Successully ::: "+leadUpdateRequestBody);
			
			JSONObject responseLeadUpdateJSON = edelweissTokioCommonApi.updateLMSLead(leadUpdateRequestBody);
			log.debug("EdelweissUpdateLMSLeadResourceCommand >>>> serveResource >>>> Lead Updated Successully ::: "+responseLeadUpdateJSON);
			
			if(productName.equals("Zindagi Protect Plus")) {
				responseLeadUpdateJSON.put("zp_" + EdelweissObjectConstants.INCOME, ParamUtil.getString(resourceRequest, EdelweissObjectConstants.INCOME));
				responseLeadUpdateJSON.put("zp_" + EdelweissObjectConstants.EDUCATION_QUALIFICATION, ParamUtil.getString(resourceRequest, EdelweissObjectConstants.EDUCATION_QUALIFICATION));
				responseLeadUpdateJSON.put("zp_" + EdelweissObjectConstants.OCCUPATION, ParamUtil.getString(resourceRequest, EdelweissObjectConstants.OCCUPATION));
				responseLeadUpdateJSON.put("isZP", true);
			}
			
			String customerEnquiryId = updateLMSRquestMapData.getOrDefault(EdelweissCommonConstants.CUSTOMER_ENQUIRY_ID, StringPool.BLANK);
			if(Validator.isNotNull(customerEnquiryId)) {
				edelweissTokioCommonApi.updateCustomerEnquiryLMSResponse(portalURL, responseLeadUpdateJSON, customerEnquiryId);
			}
			
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, responseLeadUpdateJSON);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissUpdateLMSLeadResourceCommand >>> serveResource >>> An exception occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
