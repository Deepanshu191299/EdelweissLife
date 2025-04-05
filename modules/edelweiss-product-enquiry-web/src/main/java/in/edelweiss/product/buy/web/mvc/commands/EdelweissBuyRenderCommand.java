package in.edelweiss.product.buy.web.mvc.commands;

import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_FORM_JSP;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.LEAD_ID;
import static in.edelweiss.tokio.constants.EdelweissObjectConstants.PRODUCT_NAME;

import com.edelweiss.http.core.api.ETIPCoreAPI;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.product.buy.util.EdelweissProductEnquiryUtil;
import in.edelweiss.product.enquiry.web.constants.EdelweissProductEnquiryWebPortletKeys;
import in.edelweiss.tokio.common.api.EdelweissTokioCommonApi;
import in.edelweiss.tokio.common.model.CustomerEnquiry;
import in.edelweiss.tokio.common.model.FormView;
import in.edelweiss.tokio.common.model.LiferayResponseMessage;
import in.edelweiss.tokio.constants.EdelweissCommonConstants;

@Component(
	    immediate = true,
	    property = {
	       "javax.portlet.name=" +EdelweissProductEnquiryWebPortletKeys.EDELWEISSPRODUCTENQUIRYWEB,
	       "mvc.command.name=/"
	    },
	    service = MVCRenderCommand.class
	)
public class EdelweissBuyRenderCommand implements MVCRenderCommand  {

	private static Log log = LogFactoryUtil.getLog(EdelweissBuyRenderCommand.class);
	public static final String VIEW = "/view.jsp";
	public static final String DEFAULT="/default.jsp";
	public static final String LEAD_FORM_JSP_PAGE="lead_form";
	public static final String NP_LEAD_FORM_JSP_PAGE1="np_lead_form"; // np stands for no product configured for this form type
	
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		
		log.debug("EdelweissBuyRenderCommand >>>  render invoked >>> ");
		
		//Step 1: Fetch the metadata
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String portalURL = themeDisplay.getPortalURL();
		long companyId = themeDisplay.getCompanyId();
		//Step 2: Fetch the productName and leadFormJSP from Expando so that the form with similar name and jsp can be fetched. 
		String productName = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getLayout().getPlid(), PRODUCT_NAME);
		String productCode = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getLayout().getPlid(), EdelweissCommonConstants.PAGE_CUSTOM_FIELD_PRODUCT_CODE);
		String leadFormJsp = edelweissTokioCommonApi.getCustomFieldValue(companyId, Layout.class.getName(), themeDisplay.getLayout().getPlid(), LEAD_FORM_JSP);
		
		renderRequest.setAttribute("productName", productName);
		renderRequest.setAttribute("productCode", productCode);
		String webEngageBuyPageURL = ConfigurationUtil.getEdelweissWebengageURLConfiguration().getProductLandingPageURL();
		renderRequest.setAttribute("webEngageURL", webEngageBuyPageURL);
		EdelweissProductEnquiryUtil.hideSuccessAndErrorMessage(renderRequest);
		log.debug("EdelweissBuyRenderCommand >>> render >>> productName >>> " + leadFormJsp);
		if (Validator.isNull(productName)) {
			log.error("Unable to get the Product Name");
			return DEFAULT;
		}
		//Step 3: Creating Form View dynamically based on the JSON stored in the Liferay Database
		FormView formView = edelweissTokioCommonApi.getFormData(renderRequest, leadFormJsp);
		log.debug("EdelweissBuyRenderCommand >>> render >>> formView >>> " + formView.toString());
		
		if(Validator.isNull(formView) || Validator.isNull(formView.getFields()) || formView.getFields().isEmpty())
		{
			log.error("Unable to fetch the Form with the given Product Name");
			return DEFAULT;
		}
		
		renderRequest.setAttribute("formView", formView);

		String leadId = edelweissTokioCommonApi.getLeadIdFromCookie(renderRequest, LEAD_ID); 
		
		int productId = 0;
		
		productId = Integer.valueOf(productCode);
		
		boolean showDownloadBrochure = GetterUtil.getBoolean(renderRequest.getPreferences().getValue("showDownloadBrochure","false"));
		String brochureURL = GetterUtil.getString(renderRequest.getPreferences().getValue("brochureURL",""));
		
		if(showDownloadBrochure && Validator.isNull(brochureURL) && productId != 0) {
			log.debug("brochure URL is not configured. Fetching from Product Master");
			LiferayResponseMessage liferayResponseMessage = edelweissTokioCommonApi.getProductMasterBeanByProductCode(portalURL, productCode);
			if(Validator.isNotNull(liferayResponseMessage)) {
				renderRequest.setAttribute(EdelweissCommonConstants.LIFERAY_RESPONSE_MESSAGE_KEY, liferayResponseMessage);
			}
		}
		
		log.debug("EdelweissBuyRenderCommand >>> render >>> Lead ID Cookie Value ::: " + leadId);
		if(Validator.isNotNull(leadId) && productId != 0) {
			try {
				CustomerEnquiry customerEnquiry = edelweissTokioCommonApi.getCustomerEnquiryByLeadId(portalURL, leadId);
				renderRequest.setAttribute("lmsLeadDataMap", EdelweissProductEnquiryUtil.getCustomerEnquiryMap(customerEnquiry));
			} catch (Exception e) {
				log.error("EdelweissBuyRenderCommand >>> render >>> Response for Lead API ::: " + e);
			}
		} 
		if(leadFormJsp.startsWith(LEAD_FORM_JSP_PAGE)) {
			return "/lead_form_1.jsp";
		}else {
			return "/np_lead_form_1.jsp";
		}
		
	}
	
	@Reference(unbind = "-")
	private EdelweissTokioCommonApi edelweissTokioCommonApi; 
	
	@Reference(unbind = "-")
	private ETIPCoreAPI etipCoreApi;
}
