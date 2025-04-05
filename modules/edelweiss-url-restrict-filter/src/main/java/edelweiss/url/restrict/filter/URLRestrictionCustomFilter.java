package edelweiss.url.restrict.filter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

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
 * The filter is used to restrict URL's which has specific pattern defined in the System Config.
 *  
 * @author Abhijit AA
 */

@Component(
		immediate = true, 
		property = { 
				"servlet-context-name=", 
				"servlet-filter-name=URLRestrictionFilter",
				"url-pattern=/web/guest/*",
				"url-pattern=/group/*",
				"dispatcher=FORWARD",
				"dispatcher=REQUEST",
		}, 
		service = Filter.class)
public class URLRestrictionCustomFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		logger.debug("init URLRestrictionCustomFilter");
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
		logger.debug("destroy URLRestrictionCustomFilter");
	}

	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {

		String requestPath = PortalUtil.getCurrentURL(httpServletRequest);
		logger.debug("Request Path >>>>>>>"+requestPath);

		if(ConfigurationUtil.getURLFilterConfiguration().getIsURLRestrictionOn()){
			if(ConfigurationUtil.getIsURLRestricted(requestPath)) {
				httpServletResponse.sendRedirect("/error-page-404");
			}else {
				filterChain.doFilter(httpServletRequest, httpServletResponse);
			}
		}else {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	private static final Log logger = LogFactoryUtil.getLog(URLRestrictionCustomFilter.class);
}