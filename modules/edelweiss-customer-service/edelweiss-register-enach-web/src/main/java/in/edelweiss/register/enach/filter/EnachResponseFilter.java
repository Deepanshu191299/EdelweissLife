package in.edelweiss.register.enach.filter;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
 * The filter is used to redirect User to Enach Success or Failure Page 
 * after getting response from ICICI Mandate.
 * 
 * @author Akash
 */

@Component(
		immediate = true, 
		property = { 
				"servlet-context-name=", 
				"servlet-filter-name=EnachResponseFilter",
				"url-pattern=/web/guest/enachresponse",
				"dispatcher=FORWARD",
				"dispatcher=REQUEST",
		}, 
		service = Filter.class)
public class EnachResponseFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		logger.debug("init Enach Response Filter");
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
		logger.debug("destroy Enach Response Filter");
	}

	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {

		logger.debug(">>>>>>>>>>>>>>>Inside Process Filter>>>>>>>>>>>>>>>>");
		if(Validator.isNotNull(httpServletRequest.getParameter("status"))) {
			String redirectURL = StringPool.BLANK;
			String status = httpServletRequest.getParameter("status");

			if(Validator.isNotNull(status) && status.equalsIgnoreCase("true")) {
				logger.info("Enach Redirection - Success");
				redirectURL = "/enach-success";	
			}else if(Validator.isNotNull(status) && status.equalsIgnoreCase("false")) {
				logger.info("Enach Redirection - Failure");
				redirectURL = "/failureenach";
			}
			logger.debug(">>>Status>>>"+status+">>>redirectURL>>>"+redirectURL);
			httpServletResponse.sendRedirect(redirectURL);

		}
		logger.debug(">>>>>>>>>>>>>>>>Exiting Process Filter>>>>>>>>>>>>>>>");
	}

	private static final Log logger = LogFactoryUtil.getLog(EnachResponseFilter.class);
}