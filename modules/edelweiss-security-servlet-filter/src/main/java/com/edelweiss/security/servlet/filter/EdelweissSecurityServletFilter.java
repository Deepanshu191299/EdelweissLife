package com.edelweiss.security.servlet.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
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

/**
 * @author Anupam Shrivastava
 */

@Component(enabled = true, immediate = true, property = { "dispatcher=REQUEST", "dispatcher=FORWARD",
		"servlet-context-name=", "servlet-filter-name=Edelweiss-Security-Filter",
		"url-pattern=/*" }, service = Filter.class)
public class EdelweissSecurityServletFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		String hstsIsEnabled = PropsUtil.get(_hstsIsEnabled);
		String cspIsEnabled = PropsUtil.get(_cspIsEnabled);
		String rpIsEnabled = PropsUtil.get(_rpIsEnabled);
		String fpIsEnabled = PropsUtil.get(_fpIsEnabled);
		String xxssIsEnabled = PropsUtil.get(_xxssIsEnabled);
		String corsIsEnabled = PropsUtil.get(_corsIsEnabled);

		if (Validator.isNotNull(hstsIsEnabled)) {
			if (hstsIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: HSTS is enabled.");
			} else if (hstsIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: HSTS is disabled.");
			} else {
				_log.error("Invalid value for hsts.isEnabled in portal-ext.properties");
			}
		}

		if (Validator.isNotNull(cspIsEnabled)) {
			if (cspIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: CSP is enabled.");
			} else if (cspIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: CSP is disabled.");
			} else {
				_log.error("Invalid value for csp.isEnabled in portal-ext.properties");
			}
		}

		if (Validator.isNotNull(rpIsEnabled)) {
			if (rpIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: Referrer-Policy is enabled.");
			} else if (rpIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: Referrer-Policy is disabled.");
			} else {
				_log.error("Invalid value for rp.isEnabled in portal-ext.properties");
			}
		}

		if (Validator.isNotNull(fpIsEnabled)) {
			if (fpIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: Feature-Policy is enabled.");
			} else if (fpIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: Feature-Policy is disabled.");
			} else {
				_log.error("Invalid value for fp.isEnabled in portal-ext.properties");
			}
		}

		if (Validator.isNotNull(xxssIsEnabled)) {
			if (xxssIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: X-XSS-Protection is enabled.");
			} else if (xxssIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: X-XSS-Protection is disabled.");
			} else {
				_log.error("Invalid value for xxss.isEnabled in portal-ext.properties");
			}
		}

		if (Validator.isNotNull(corsIsEnabled)) {
			if (corsIsEnabled.equals("true")) {
				_log.debug("Edelweiss Security Header: CORS is enabled.");
			} else if (corsIsEnabled.equals("false")) {
				_log.warn("Edelweiss Security Header: CORS is disabled.");
			} else {
				_log.error("Invalid value for cors.isEnabled in portal-ext.properties");
			}
		}

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		try {
			//Adding logs of request url		
			HttpServletRequest httprequest = (HttpServletRequest) servletRequest;
			String requestPath = PortalUtil.getCurrentURL(httprequest);
			_log.debug("Request Path >>>>>>>"+requestPath);
		} catch (Exception e) {
			_log.error("Exception ...........");
		}

		String hstsIsEnabled = PropsUtil.get(_hstsIsEnabled);
		String cspIsEnabled = PropsUtil.get(_cspIsEnabled);
		String rpIsEnabled = PropsUtil.get(_rpIsEnabled);
		String fpIsEnabled = PropsUtil.get(_fpIsEnabled);
		String xxssIsEnabled = PropsUtil.get(_xxssIsEnabled);
		String corsIsEnabled = PropsUtil.get(_corsIsEnabled);

		String hstsMaxAgeSeconds = PropsUtil.get(_hstsMaxAgeSeconds);
		String hstsIncludeSubDomains = PropsUtil.get(_hstsIncludeSubDomains);
		String cspResources = PropsUtil.get(_cspResources);
		String rpDirective = PropsUtil.get(_rpDirective);

		String fpDirective = PropsUtil.get(_fpDirective);
		String[] fpDirectiveArr = null;

		if (Validator.isNotNull(fpDirective)) {
			fpDirectiveArr = fpDirective.split(",");
		}

		String xxssDirective = PropsUtil.get(_xxssDirective);
		String corsDirective = PropsUtil.get(_corsDirective);

		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

		String directives = null;

		/*
		 * 
		 * Set the following custom property to true if you want to enable the HTTP
		 * Strict Transport Security(HSTS). hsts.isEnabled=true
		 * 
		 * Configure the following custom property value to the number of seconds that
		 * you want to instruct the client browser to purely use https to connect to
		 * this domain. hsts.maxAgeSeconds=31536000
		 * 
		 * Set the following custom property value to true to indicate that the HSTS
		 * policy applies to this HSTS host as well as any subdomains of the host's
		 * domain name. hsts.includeSubDomains=true
		 * 
		 */

		if (Validator.isNotNull(hstsIsEnabled)) {
			if (hstsIsEnabled.equals("true")) {

				/*
				 * Note that the HSTS header must not be included in HTTP responses conveyed
				 * over non-secure transport.
				 */

				if (servletRequest.isSecure() && servletResponse instanceof HttpServletResponse) {

					directives = String.format(MAX_AGE_DIRECTIVE, hstsMaxAgeSeconds);

					if (Boolean.valueOf(hstsIncludeSubDomains)) {
						directives += (";" + INCLUDE_SUB_DOMAINS_DIRECTIVE);
					}

					httpServletResponse.setHeader(HSTS_HEADER_NAME, directives);

				}
			}
		}

		/*
		 * 
		 * Set the following custom property to true if you want to enable the Content
		 * Security Policy(CSP). csp.isEnabled=true
		 * 
		 * Configure the following custom property value separated by space that you
		 * want to instruct the client browser to whitelist the api's/cdn/scripts from
		 * these resource(s), else api's from outside remains blocked.
		 * csp.resources=script-src 'self' 'unsafe-inline' 'unsafe-eval'
		 * *.googleapis.com *.cloudflare.com;
		 * 
		 */

		if (Validator.isNotNull(cspIsEnabled)) {
			if (cspIsEnabled.equals("true")) {
				httpServletResponse.setHeader(CSP_HEADER_NAME, cspResources);
			}
		}

		/*
		 * Set the following custom property to true if you want to enable the
		 * Referrer-Policy. rp.isEnabled=true
		 * 
		 * Configure the following custom property value which determines what
		 * information will be sent along with the requests. rp.directive=strict-origin
		 */

		if (Validator.isNotNull(rpIsEnabled)) {
			if (rpIsEnabled.equals("true")) {
				httpServletResponse.setHeader(REFERRER_POLICY_HEADER_NAME, rpDirective);
			}
		}

		/*
		 * Set the following custom property to true if you want to enable the
		 * Feature-Policy. fp.isEnabled=true
		 * 
		 * Configure the following custom property value allows you to opt-in or opt-out
		 * set of "policies" for the browser to enforce on specific features used
		 * throughout your site. These policies restrict what APIs the site can access
		 * or modify the browser's default behavior for certain features.
		 * fp.directive=display-capture 'none',geolocation 'self',fullscreen
		 * 'self',picture-in-picture 'self',usb 'none',sync-xhr 'none',midi
		 * 'none',camera 'none',magnetometer 'none',gyroscope 'none',speaker
		 * 'self',payment 'none',microphone 'none'
		 */

		if (Validator.isNotNull(fpIsEnabled)) {
			if (fpIsEnabled.equals("true")) {
				httpServletResponse.setHeader(FEATURE_POLICY_HEADER_NAME, fpDirectiveArr[0]);
				int counter = 1;
				while (counter < fpDirectiveArr.length) {
					httpServletResponse.addHeader(FEATURE_POLICY_HEADER_NAME, fpDirectiveArr[counter]);
					counter++;
				}
			}
		}

		/*
		 * Set the following custom property to true if you want to enable the
		 * X-XSS-Protection. xxss.isEnabled=true
		 * 
		 * The X-XSS-Protection header is designed to enable the cross-site scripting
		 * (XSS) filter built into modern web browsers. This is usually enabled by
		 * default, but using it will enforce it. The recommended configuration is to
		 * set this header to the following value, which will enable the XSS protection
		 * and instruct the browser to block the response in the event that a malicious
		 * script has been inserted from user input instead of sanitizing.
		 * X-XSS-Protection: 1; mode=block
		 * 
		 */

		if (Validator.isNotNull(xxssIsEnabled)) {
			if (xxssIsEnabled.equals("true")) {
				httpServletResponse.setHeader(XXSS_PROTECTION_HEADER_NAME, xxssDirective);
			}
		}

		/*
		 * Set the following custom property to true if you want to enable the
		 * Cross-Origin Resource Sharing(CORS) header. cors.isEnabled=true
		 * 
		 * CORS is a browser mechanism which enables controlled access to resources
		 * located outside of a given domain.
		 */
		if (Validator.isNotNull(corsIsEnabled)) {
			if (corsIsEnabled.equals("true")) {
				httpServletResponse.setHeader(CORS_HEADER_NAME, corsDirective);
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);

	}

	@Override
	public void destroy() {
		_log.debug("Edelweiss Security Filter is Closed.");
	}

	private static final String _cspIsEnabled = "csp.isEnabled";
	private static final String CSP_HEADER_NAME = "Content-Security-Policy";
	private static final String _cspResources = "csp.resources";

	private static final String _rpIsEnabled = "rp.isEnabled";
	private static final String REFERRER_POLICY_HEADER_NAME = "Referrer-Policy";
	private static final String _rpDirective = "rp.directive";

	private static final String _fpIsEnabled = "fp.isEnabled";
	private static final String FEATURE_POLICY_HEADER_NAME = "Feature-Policy";
	private static final String _fpDirective = "fp.directive";

	private static final String _xxssIsEnabled = "xxss.isEnabled";
	private static final String XXSS_PROTECTION_HEADER_NAME = "X-XSS-Protection";
	private static final String _xxssDirective = "xxss.directive";

	private static final String _corsIsEnabled = "cors.isEnabled";
	private static final String CORS_HEADER_NAME = "Access-Control-Allow-Origin";
	private static final String _corsDirective = "cors.directive";

	private static final String _hstsIsEnabled = "hsts.isEnabled";
	private static final String HSTS_HEADER_NAME = "Strict-Transport-Security";
	private static final String _hstsMaxAgeSeconds = "hsts.maxAgeSeconds";
	private static final String _hstsIncludeSubDomains = "hsts.includeSubDomains";
	private static final String MAX_AGE_DIRECTIVE = "max-age=%s";
	private static final String INCLUDE_SUB_DOMAINS_DIRECTIVE = "includeSubDomains";

	private static final Log _log = LogFactoryUtil.getLog(EdelweissSecurityServletFilter.class);

}