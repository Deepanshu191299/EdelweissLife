<%@ include file="../init.jsp"%>
<%
String enachURL = (String)renderRequest.getAttribute("enachURL");
%>
<div class="edto-renew-success-wrapper">
	<div class="container">
		<div class="renew-bloc-wrapper">
			<h1 class="fontbold fs28 text-center pb-3"><liferay-ui:message key="label-payment-aborted-failed" /></h1>
			<h5 class="fs16 text-center pb-3 textsub-title"><liferay-ui:message key="label-in-case-of-payment-failure-please-check-your-bank-cc-statement-before-re-attempting-payment" /></h5>
		</div>
		<div class="make-pay">
			<a href="<%=enachURL%>"><liferay-ui:message key="label-try-again" /></a>
		</div>
	</div>
</div>