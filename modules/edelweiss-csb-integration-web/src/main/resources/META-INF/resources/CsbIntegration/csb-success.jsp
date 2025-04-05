<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ include file="../init.jsp"%>


<%
String renewalURL = (String)renderRequest.getAttribute("renewalURL");
HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
String transactionId = httpReq.getParameter("transactionId");
String amount = httpReq.getParameter("amount");
%>

<div class="edto-renew-success-wrapper">
	<div class="container">
		<div class="renew-bloc-wrapper">
			<%-- <h1 class="fontbold fs28 text-center pb-3"><liferay-ui:message key="label-your-payment-is-complete" /><%=transactionId%>,<%=amount%></h1>
			<h5 class="fs16 text-center pb-3 textsub-title"><liferay-ui:message key="label-thank-you-for-choosing-edelweiss-tokio-life-as-your-preferred-life-insurance-partner" /></h5>
		 --%>
		<h5 class="fs16 pb-3 textsub-title">Dear Customer,</h5>
		<h5 class="fs16 pb-3 textsub-title-amount">Thank you for your payment of <%=LanguageUtil.get(Locale.US, "label-rupee")%><%=amount%></h5>
		<h5 class="fs16 pb-3 textsub-title-success">Your transaction was successful.</h5>
		<input id ="transaction-Id"type="hidden" name="transactionId" value="<%=transactionId%>"></input>
		<h5 class="fs16 pb-3 textsub-title-thanks">Thank you for choosing Edelweiss Life as your preferred Insurance Partner.</h5>
		 </div>
	</div>
</div>
