package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
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

/**
 * Wealth Plus, Wealth Secure Plus and Active Income Buy Journey Generate BI
 */
@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/buyJourney/generate-bi"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissBuyJourneyGenerateBIResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissBuyJourneyGenerateBIResourceCommand.class);
	
	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		log.debug("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> Generating BI Request :::");

		String biRequestJsonString = ParamUtil.getString(resourceRequest, "biRequestData");
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		String leadId = StringPool.BLANK;
		
		try {
			JSONObject biJson = JSONFactoryUtil.createJSONObject(biRequestJsonString);

			JSONObject partnerData = edelweissTokioCommonApi.getPartnerDataByLeadId(resourceRequest);
			log.debug("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> partnerData data :: " + partnerData);

			if (Validator.isNotNull(partnerData) && partnerData.getBoolean("isValidPartner")) {
				biJson.put("AGENT_ID", partnerData.getString("agentId"));
				biJson.put("category", partnerData.getString("category"));
				biJson.put("PR_CHANNEL", partnerData.getString("channel"));
				biRequestJsonString = biJson.toString();
			}
		} catch (Exception e1) {
			log.error(" EdelweissBuyJourneyGenerateBIResourceCommand Partner error >>>>>>>>>>>>>>>>>>>>>>> ::::", e1);
		}
		
		log.debug("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> BiRequest JSON String ::: " + biRequestJsonString);
		
		try {
			JSONObject biResponseJSONObject = edelweissTokioCommonApi.invokeBuyJourneyGenerateBi(biRequestJsonString);
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
			responseObj.put(EdelweissCommonConstants.DATA_KEY, biResponseJSONObject);
		} catch(Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.debug("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> Error occured while generating BI Request ::: " + e.getMessage());
		}  finally {
			log.info("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> responseObj "+responseObj);
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
				JSONObject buyJourneyBiResponseObject = JSONFactoryUtil.createJSONObject();
				
				String jsonOutput = responseObj.getJSONObject(EdelweissCommonConstants.DATA_KEY).getString("JsonOutput");
				JSONObject paresedJsonOutput = JSONFactoryUtil.createJSONObject(jsonOutput);
				
				String bIJson = paresedJsonOutput.getString("BIJson");
				JSONArray bIJsonParse = JSONFactoryUtil.createJSONArray(bIJson);
				
				String InputValidationStatus = paresedJsonOutput.getString("InputValidationStatus");
				JSONArray InputValidationStatusParse = JSONFactoryUtil.createJSONArray(InputValidationStatus);
				
				buyJourneyBiResponseObject.put("bIJsonLength", bIJsonParse.length());
				buyJourneyBiResponseObject.put("bIJsonFirstIndex", bIJsonParse.get(0));
				buyJourneyBiResponseObject.put("biInputValidations", InputValidationStatusParse);

				log.info("EdelweissBuyJourneyGenerateBIResourceCommand >>> doServeResource >>> buyJourneyBiResponseObject "+buyJourneyBiResponseObject);
				customerInvestmentDetailsRequestBody.put("biJSONAndInputValidation", buyJourneyBiResponseObject.toString());
				EdelweissCustomerInvestmentObjectUtil.updateCustomerInvestmentDetailsById(etipCoreAPI, customerInvestmentDetailsId, themeDisplay, customerInvestmentDetailsRequestBody);
			}
		}
		
	}
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreAPI;
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
}
