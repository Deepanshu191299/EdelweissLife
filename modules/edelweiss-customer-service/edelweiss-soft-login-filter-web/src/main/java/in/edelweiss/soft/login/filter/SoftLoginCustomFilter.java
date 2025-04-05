package in.edelweiss.soft.login.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import in.edelweiss.common.util.ConfigurationUtil;

/**
 * The filter is used to redirect Customer Service URL to Soft Login Feature.
 * 
 * @author Abhijit AA
 */

@Component(
		immediate = true, 
		property = { 
				"servlet-context-name=", 
				"servlet-filter-name=URLRedirectionFilter",
				"url-pattern=/web/guest/renew-online",
				"url-pattern=/web/guest/application-tracker",
				"url-pattern=/web/guest/register-e-nach",
				"url-pattern=/web/guest/update-contact-details",
				"url-pattern=/web/guest/customer-services-ppc",
				"url-pattern=/web/guest/customer-services-us",
				"url-pattern=/web/guest/customer-services-rpc",
				"url-pattern=/web/guest/updatefatcaandcrs",
				"url-pattern=/web/guest/renew-online/",
				"url-pattern=/web/guest/application-tracker/",
				"url-pattern=/web/guest/register-e-nach/",
				"url-pattern=/web/guest/update-contact-details/",
				"url-pattern=/web/guest/customer-services-ppc/",
				"url-pattern=/web/guest/customer-services-us/",
				"url-pattern=/web/guest/customer-services-rpc/",
				"url-pattern=/web/guest/updatefatcaandcrs/",
				"dispatcher=FORWARD",
				"dispatcher=REQUEST",
		}, 
		service = Filter.class)
public class SoftLoginCustomFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		logger.debug("init Soft Login Custom Filter");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;

		try {
			processFilter(httprequest, httpresponse, chain);
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	@Override
	public void destroy() {
		logger.debug("destroy Soft Login Custom Filter");
	}

	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {

		httpServletRequest.getSession(false).removeAttribute("isOTPRequired");
		httpServletRequest.getSession(false).removeAttribute("redirectURL");
		httpServletRequest.getSession(false).removeAttribute("isAppTracker");

		int sessionTimeout = Integer.parseInt(ConfigurationUtil.getCommonConfiguration().getSessionTimeoutInMinutes());
		String requestPath = PortalUtil.getCurrentURL(httpServletRequest);

		String redirectURL = StringPool.BLANK;
		boolean isOTPRequired = false;

		logger.debug(">>>>>>>>>>>>>>>>>>Request Path - "+requestPath);
		if(Validator.isNotNull(requestPath) && requestPath.contains("/web/guest")) {
			requestPath = requestPath.replace("/web/guest", "");
		}

		if(Validator.isNotNull(requestPath) && (requestPath.contains("/register-e-nach") ||  requestPath.contains("/application-tracker"))) {

			if(Validator.isNotNull(httpServletRequest.getParameter("policynumber"))) {
				
				String inputType = "policyNumber";
				String policyNumber = httpServletRequest.getParameter("policynumber");
				String dob = httpServletRequest.getParameter("dob");

				if(Validator.isNotNull(dob)) {
					dob = dob.substring(0, 2) +"/"+ dob.substring(2, 4) +"/"+ dob.substring(4, 8);	
				}

				if(Validator.isNotNull(policyNumber) && Validator.isNotNull(dob)) {
					JSONObject policyJson = JSONFactoryUtil.createJSONObject()
							.put("inputType", inputType).put(inputType, policyNumber)
							.put("dob", dob);

					httpServletRequest.getSession(false).removeAttribute("policyJSON");
					httpServletRequest.getSession().setAttribute("policyJSON", policyJson.toString());
					if(requestPath.contains("/register-e-nach")) {
						httpServletRequest.getSession().setAttribute("isAppTracker", true);
					}
					
					httpServletRequest.getSession().setMaxInactiveInterval(sessionTimeout*60);
					logger.debug(">>>>>>>>>>>>>>>>>>Policy Json - "+policyJson.toString());
				}	
			}
		}

		String policyJSON = (String) httpServletRequest.getSession(false).getAttribute("policyJSON");
		logger.debug(">>>>>>>>>>>>>>>>>>Request Path - "+requestPath + "policy Json -"+policyJSON);

		if (Validator.isNotNull(policyJSON)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		} else {
//			if (ConfigurationUtil.getSoftLoginOTPURL(requestPath)) {
//				isOTPRequired = true;
//			}
			redirectURL = requestPath;
			httpServletRequest.getSession().setAttribute("redirectURL", redirectURL);
			httpServletRequest.getSession().setAttribute("isOTPRequired", isOTPRequired);
			httpServletRequest.getSession().setMaxInactiveInterval(sessionTimeout*60);
			logger.debug(">>>>>>>>>>>>>>>>>>redirectURL - "+redirectURL+" isOTPRequired - "+isOTPRequired + 
					" Soft Login URL - "+ConfigurationUtil.getURLRelatedConfiguration().getSoftLoginPageURL());
			httpServletResponse.sendRedirect(ConfigurationUtil.getURLRelatedConfiguration().getSoftLoginPageURL());
		}
	}

	private static final Log logger = LogFactoryUtil.getLog(SoftLoginCustomFilter.class);
}
