package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
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

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.product.customize.web.utilities.EdelweissCustomerInvestmentObjectUtil;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(immediate = true, property = {
		"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
"mvc.command.name=/edelweiss/guaranteed-savings-star/generate-bi" }, service = MVCResourceCommand.class)
public class GuaranteedSavingsStarGenerateBIResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(GuaranteedSavingsStarGenerateBIResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("GuaranteedSavingsStarGenerateBIResourceCommand >>> doServeResource >>> Generating BI Request ::: ");

		String biRequestJsonString = ParamUtil.getString(resourceRequest, "biRequestJsonString");
		String leadId = StringPool.BLANK;
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		/*
		//Removing WaiverOfPremiumRider field from object
		JSONObject pFResJSONObject = JSONFactoryUtil.createJSONObject(biRequestJsonString);

		if(pFResJSONObject.has("productDetails")) {
			if(pFResJSONObject.getJSONObject("productDetails").has("product")) {
				if(pFResJSONObject.getJSONObject("productDetails").getJSONObject("product").has("riderDetails")) {
					log.info("Is WaiverOfPremiumRider field Exist "+pFResJSONObject.has("productDetails"));
					if(pFResJSONObject.getJSONObject("productDetails").getJSONObject("product").getJSONObject("riderDetails").has("WaiverOfPremiumRider")) {
						pFResJSONObject.getJSONObject("productDetails").getJSONObject("product").getJSONObject("riderDetails").remove("WaiverOfPremiumRider");
						biRequestJsonString = pFResJSONObject.toString();
					}
				}
			}
		}
		*/
				
		log.debug("GuaranteedSavingsStarGenerateBIResourceCommand >>> doServeResource >>> BIRequest JSON ::: " + biRequestJsonString);
		
		try {

			JSONObject partnerObject = edelweissTokioCommonApi.getPartnerDataByLeadId(resourceRequest);
			log.debug("GuaranteedSavingsStarGenerateBIResourceCommand >>> doServeResource >>> partnerObject data :: " + partnerObject);

			if(Validator.isNotNull(partnerObject) && partnerObject.getBoolean("isValidPartner")) {
				JSONObject biRequestJSONObject = JSONFactoryUtil.createJSONObject(biRequestJsonString);
				biRequestJSONObject.getJSONObject("customerDetails").getJSONObject("agentDetails").put("AGENT_ID", partnerObject.getString("agentId"));
				biRequestJSONObject.getJSONObject("productDetails").getJSONObject("product").put("PR_CHANNEL", partnerObject.getString("channel"));
				biRequestJSONObject.getJSONObject("productDetails").getJSONObject("product").put("category", partnerObject.getString("category"));
				biRequestJsonString = biRequestJSONObject.toString();
				log.debug("EdelweissGenerateBIResourceCommand >>> doServeResource >>> BIRequest JSON : " + biRequestJsonString);
			}

		}catch (Exception e1) {
			log.error(" GuaranteedSavingsStarGenerateBIResourceCommand Partner error >>>>>>>>>>>>>>>>>>>>>>> ::::", e1);
		}
		
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();

		try {

			JSONObject biResponseJSONObject = edelweissTokioCommonApi.invokeRetailParnerGenerateBI(biRequestJsonString);
			log.debug("GuaranteedSavingsStarGenerateBIResourceCommand >>> doServeResource >>> BIResponse ::: "+ biResponseJSONObject.toJSONString());

			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, biResponseJSONObject.getJSONObject(EdelweissCommonConstants.DATA_KEY));

		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("GuaranteedSavingsStarGenerateBIResourceCommand >>> doServeResource >>> Error occured while genrating BI Request ::: "+ e);
		} finally {
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
				
				log.info("BI Response responseObj "+responseObj);
				JSONObject cusInvestmentRes = EdelweissCustomerInvestmentObjectUtil.getCustomerInvestmentDetailsByLead(etipCoreAPI, leadId, themeDisplay);
				JSONArray items = cusInvestmentRes.getJSONArray("items");
				JSONObject obj = items.getJSONObject(0);
				String customerInvestmentDetailsId = obj.getString("id");
				
				JSONObject customerInvestmentDetailsRequestBody = JSONFactoryUtil.createJSONObject();
				
				JSONObject biJSONAndInputValidation = JSONFactoryUtil.createJSONObject();
				
				biJSONAndInputValidation.put("bIJsonFirstIndex", responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONArray("BIJson").get(0));
				biJSONAndInputValidation.put("biInputValidations",responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getJSONArray("InputValidationStatus"));
				customerInvestmentDetailsRequestBody.put("biJSONAndInputValidation", biJSONAndInputValidation.toString());
				EdelweissCustomerInvestmentObjectUtil.updateCustomerInvestmentDetailsById(etipCoreAPI, customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			}
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
}
