package in.edelweiss.register.enach.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.EdelweissAPIConstants;
import in.edelweiss.common.contants.ParameterConstants;
import in.edelweiss.common.util.ConfigurationUtil;
import in.edelweiss.common.util.EdelweissAPIUtil;
import in.edelweiss.register.enach.constants.RegisterEnachPortletKeys;
import in.edelweiss.register.enach.util.RegisterEnachUtil;
import in.edelweiss.system.configurations.URLRelatedConfiguration;

/**
 * This portlet will call the Customer Policy Data API and 
 * send the data to JSP and render the same.
 * 
 * @author Abhijit AA
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=RegisterEnach", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/jsp/register-enach.jsp",
		"javax.portlet.name=" + RegisterEnachPortletKeys.REGISTERENACH,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class RegisterEnachPortlet extends MVCPortlet {

	/**
	 * This method will call the Customer Policy Data API and 
	 * send the data to JSP and render the same.
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return void
	 */
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {

		logger.debug("enach page visit From Website::::::");
		HttpServletRequest request = PortalUtil
				.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		URLRelatedConfiguration urlConfiguration = ConfigurationUtil.getURLRelatedConfiguration();
		
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
		String customerName = StringPool.BLANK;
		
		HttpServletRequest enachRequest = PortalUtil.getHttpServletRequest(renderRequest);
		HttpServletRequest originalRequest = PortalUtil.getOriginalServletRequest(enachRequest);
		String enachSrc = ParamUtil.getString(originalRequest, "source");
		logger.debug("enach page visit with Source::::::"+ enachSrc);

		try {
			
		boolean restrictICICIUrl = urlConfiguration.getICICIURLRestrictionOn();
		boolean restrictSBIUrl = urlConfiguration.getSBIURLRestrictionOn();
		logger.debug("restrictICICIUrl"+restrictICICIUrl);
		logger.debug("restrictSBIUrl"+restrictSBIUrl);
			JSONObject policyJSON = JSONFactoryUtil.createJSONObject();
			
			if(Validator.isNotNull(request.getSession().getAttribute("policyJSON"))) {
				policyJSON = JSONFactoryUtil
						.createJSONObject(request.getSession().getAttribute("policyJSON").toString());
			}


			if (Validator.isNotNull(policyJSON) && policyJSON.length() > 1) {
				String inputType = policyJSON.getString("inputType");
				String inputValue = policyJSON.getString(inputType);
				String dateOfBirth = policyJSON.getString("dob");
				
				DateFormat originalFormat = new SimpleDateFormat(DateConstants.SLASH_DD_MM_YYYY);
				DateFormat targetFormat = new SimpleDateFormat(DateConstants.HYPHEN_DD_MM_YYYY);
				Date date = originalFormat.parse(dateOfBirth);
				String dateOfBirthFormatted = targetFormat.format(date);

				try {

					if (Validator.isNotNull(inputType) && Validator.isNotNull(inputValue)
							&& Validator.isNotNull(dateOfBirthFormatted)) {
						logger.debug("Enach PolicyType:::"+inputType);
						logger.debug("Enach PolicyNumber:::" +inputValue);
						
						Map<String, Object> responseMap = RegisterEnachUtil.customerPolicyData(inputType, inputValue,
								dateOfBirthFormatted, EdelweissAPIConstants.REQUEST_TYPE_ENACH);
						logger.debug("ResponseMap from customerPolicyData API:: "+responseMap.toString());
						
						if (Validator.isNotNull(responseMap)) {
							int status = (Integer) responseMap.get("status");
							renderRequest.setAttribute("status", status);
							
							if (status == 200) {
								jsonArray = JSONFactoryUtil.createJSONArray(responseMap.get("content").toString());
								JSONObject customerJSONObject = jsonArray.getJSONObject(0);
								customerName = customerJSONObject.getString("clientName");
							} else {
								logger.debug("Customer not Found:::"+inputValue);
								request.getSession(false).removeAttribute("policyJSON");
								jsonObject = JSONFactoryUtil.createJSONObject(responseMap.get("content").toString());
							}
						}
					}
				} catch (Exception exception) {
					logger.debug("Something went wrong under CustomerPolicy Website FrontEnd");
					logger.error("Exception occured while fetching customer data : " + exception.getMessage());
					if (logger.isDebugEnabled()) {
						logger.error(ParameterConstants.EXCEPTION, exception);
					}
				}
				
				String environmentLogo = ConfigurationUtil.getCommonConfiguration().getEnvironmentLogoURL().trim();
				String environmentName = ConfigurationUtil.getCommonConfiguration().getEnvironmentName().trim().toLowerCase();
				String bankName = ConfigurationUtil.getOOTBReferenceCodeConfiguration().getBankNamesPickListExternalReferenceCode().trim();
				TreeMap<String, String> bankNames = new TreeMap<>(EdelweissAPIUtil.getPickListKeyValues(themeDisplay.getCompanyId(), bankName));
				
				boolean isAppTracker = false;
				if(Validator.isNotNull(request.getSession().getAttribute("isAppTracker"))) {
					isAppTracker = (boolean)request.getSession().getAttribute("isAppTracker");
				}
				
				renderRequest.setAttribute("isAppTracker", isAppTracker);
				renderRequest.setAttribute("restrictICICIUrl", restrictICICIUrl);
				renderRequest.setAttribute("environmentLogo", environmentLogo);
				renderRequest.setAttribute("restrictSBIUrl", restrictSBIUrl);
				renderRequest.setAttribute("environmentName", environmentName);
				renderRequest.setAttribute("bankNames", bankNames);
				renderRequest.setAttribute("customerName", customerName);
				renderRequest.setAttribute("customerJSONArray", jsonArray);
				renderRequest.setAttribute("customerJSONObject", jsonObject);
			}
		} catch (Exception exception) {
			logger.error("Exception occured in render : " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}

		super.render(renderRequest, renderResponse);
	}

	private Log logger = LogFactoryUtil.getLog(this.getClass());
}