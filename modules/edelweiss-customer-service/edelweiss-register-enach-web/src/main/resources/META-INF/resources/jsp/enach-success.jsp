<%@ include file="../init.jsp"%>
<%
String enachURL = (String)renderRequest.getAttribute("enachURL");
%>
<div class="edto-renew-success-wrapper">
	<div class="container">
		<div class="renew-bloc-wrapper">
			<h1 class="fontbold fs28 text-center pb-3"><liferay-ui:message key="label-thank-you-for-e-nach-registration-request-for-your-policy" /></h1>
			<h5 class="fs16 text-center pb-3 textsub-title"><liferay-ui:message key="label-e-nach-activated-post-successful-verification-from-bank" /></h5>
		</div>
		<div class="make-pay">
			<a href="<%=enachURL%>"><liferay-ui:message key="label-make-another-payment" /></a>
		</div>
	</div>
</div>