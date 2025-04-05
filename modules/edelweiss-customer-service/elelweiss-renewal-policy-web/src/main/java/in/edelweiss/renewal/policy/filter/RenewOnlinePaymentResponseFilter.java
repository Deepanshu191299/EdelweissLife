package in.edelweiss.renewal.policy.filter;

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

import in.edelweiss.common.util.ConfigurationUtil;

/**
 * The filter is used to redirect User to Renewal Success or Failure Page 
 * after getting response from PayU.
 * 
 * @author Abhijit AA
 */

@Component(
		immediate = true, 
		property = { 
		"servlet-context-name=", 
		"servlet-filter-name=RenewOnlinePaymentResponseFilter",
		"url-pattern=/web/guest/paymentresponse",
		"dispatcher=FORWARD",
		"dispatcher=REQUEST",
		}, 
service = Filter.class)
public class RenewOnlinePaymentResponseFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		logger.info("init RenewOnlinePaymentResponseFilter123::::::");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		logger.info("inside do Filter RenewalOnline Policy>>>>>Executing filter for URL: " + ((HttpServletRequest)request).getRequestURI());
		try {
			processFilter(httprequest, httpresponse, chain);
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	@Override
	public void destroy() {
		logger.info("destroy Renewal Payment Response Filter");
	}

	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws Exception {

		logger.info("inside process Filter method");
		logger.info(">>>>>>>>>>>>>>>Inside Process Filter of RenewOnline Policy>>>>>>>>>>>>>>>>");
		if(Validator.isNotNull(httpServletRequest.getParameter("type")) && 
				Validator.isNotNull(httpServletRequest.getParameter("status"))) {
			
			String redirectURL = StringPool.BLANK;
			String type = httpServletRequest.getParameter("type");
			String status = httpServletRequest.getParameter("status");
			String transactionId = httpServletRequest.getParameter("transactionId");
			String amount = httpServletRequest.getParameter("amount");
			logger.info("transactionId..."+transactionId+", amount..."+amount);
			/*
			 * httpServletResponse.setAttribute("transactionId", transactionId);
			 * httpServletResponse.setAttribute("amount", amount);
			 */
			
			logger.info("type is -------->="+type);

			logger.info("status is -------->="+status);
			logger.info("transactionId is -------->="+transactionId);

			logger.info("amount is -------->="+amount);
			
			if(Validator.isNotNull(type) && type.equalsIgnoreCase("Renewal")) {
				logger.info("inside RenewalPolicy Condition check>>>>>>>>>>>>>>>>>");
				if(Validator.isNotNull(status) && status.equalsIgnoreCase("true")) {

					redirectURL = "/renew-success?transactionId="+transactionId+"&amount="+amount;	
				}else {
					redirectURL = "/renew-failure";
				}
			}
			if(Validator.isNotNull(type) && type.equalsIgnoreCase("SI")) {
				logger.info("inside Renewal Condition>>>>>>>>>>>>>>>>>SI");
				if(Validator.isNotNull(status) && status.equalsIgnoreCase("true")) {

					redirectURL = "/si-success?transactionId="+transactionId+"&amount="+amount;	
				}else {
					redirectURL = "/si-failure";
				}
			}
			logger.info("Type>>>"+type+">>>Status>>>"+status+">>>redirectURL>>>"+redirectURL);
			httpServletResponse.sendRedirect(redirectURL);
		}
		logger.info(">>>>>>>>>>>>>>>>Exiting Process Filter>>>>>>>>>>>>>>>");
	}

	private static final Log logger = LogFactoryUtil.getLog(RenewOnlinePaymentResponseFilter.class);
}