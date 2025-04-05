package in.edelweiss.product.customize.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;

import com.edelweiss.http.core.exception.ETIPSystemException;
import com.liferay.petra.string.StringPool;
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

import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants;
import in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.CustomerFamilyDetailsRel;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	    	"javax.portlet.name=" + EdelweissProductCustomizeWebPortletKeys.EDELWEISS_PRODUCT_CUSTOMIZE_WEB,
	        "mvc.command.name=/add/risingStarBenefit"
	    },
	    service = MVCResourceCommand.class
	)
public class EdelweissAddRisingStarBenefitResourceCommand extends BaseMVCResourceCommand {

	private static final Log log = LogFactoryUtil.getLog(EdelweissAddRisingStarBenefitResourceCommand.class);

	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)throws Exception {
		JSONObject responseObj = JSONFactoryUtil.createJSONObject();
		
		try {
			log.debug("EdelweissAddRisingStarBenefitResourceCommand >>>> serveResource >>>> Add Rising Star Benefit...");
			
			ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
			String portalURL =  themeDisplay.getPortalURL();
			String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(resourceRequest, LEAD_ID);
			
			CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
			
			String customerFamilyDetailsId = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.CUSTOMER_FAMILY_DETAILS_ID, StringPool.BLANK);
			
			if(Validator.isNotNull(customerEnquiry)) {
				String isLittleChampBenefitOpted = ParamUtil.getString(resourceRequest, EdelweissCommonConstants.IS_RISING_STAR_OPTED);
				CustomerFamilyDetailsRel customerFamilyDetails = new CustomerFamilyDetailsRel();
				customerFamilyDetails.setLeadId(leadId);
				customerFamilyDetails.setIsRisingStarBenefitOpted(isLittleChampBenefitOpted);
				customerFamilyDetails.setBenefitName(EdelweissProductCustomizeConstants.RISING_STAR_BENEFIT);
				
				edelweissTokioCommonApi.updateFamilyDetails(portalURL, customerFamilyDetails, customerFamilyDetailsId);
				log.debug("EdelweissAddRisingStarBenefitResourceCommand >>>> serveResource >>>> Rising Star Benefit Added ...");
			}
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.SUCCESS);
		} catch (ETIPSystemException e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissAddLittleChampBenefitResourceCommand >>> serveResource >>> An error occurred updating little champ benefit ::: " + e);
		} catch (Exception e) {
			responseObj.put(EdelweissCommonConstants.STATUS_KEY, EdelweissCommonConstants.STATUS_ERROR);
			log.error("EdelweissAddLittleChampBenefitResourceCommand >>> serveResource >>> An error occurred ::: " + e);
		} finally {
			PrintWriter writer = resourceResponse.getWriter();
			writer.write(responseObj.toString());
			writer.close();
		}
	}

	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi;

}
