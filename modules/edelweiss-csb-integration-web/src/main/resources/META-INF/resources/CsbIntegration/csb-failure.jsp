<%@ include file="../init.jsp"%>
<%
String csbURL = (String)renderRequest.getAttribute("csbURL");
String message = (String)renderRequest.getAttribute("message");
%>

<div class="edto-renew-success-wrapper">
	<div class="container">
		<div class="renew-bloc-wrapper">
			<%-- <h1 class="fontbold fs28 text-center pb-3"><liferay-ui:message key="label-payment-aborted-failed" /></h1>
			<h5 class="fs16 text-center pb-3 textsub-title"><liferay-ui:message key="label-in-case-of-payment-failure-please-check-your-bank-cc-statement-before-re-attempting-payment" /></h5>
			<% 
				if(Validator.isNotNull(message)){
			%>	
				<h5 class="fs16 text-center pb-3 textsub-title"><%=message%></h5>
			<%
				}
			%> --%>
<h5 class="fs16 pb-3 textsub-title-oops">Oops! It seems there was an issue processing your payment.</h5>
<h5 class="fs16 pb-3 textsub-title-amount">Here's what you can do!</h5>
<h5 class="fs16 pb-3 textsub-title-success" style="padding-left: 20px;">1. Retry Payment - You can attempt to complete the payment again. Ensure that the provided information is accurate.</h5>
<h5 class="fs16 pb-3 textsub-title-sms" style="padding-left: 20px;">2. Check Card Details - Review your card details and try again. Make sure the information is correct, and the card is valid.</h5>
<h5 class="fs16 pb-3 textsub-title-sms" style="padding-left: 20px;">3. Contact Support - If the issue persists, please reach out to our support team for assistance.</h5>
<h5 class="fs16 pb-3 textsub-title-thanks">We apologize for any inconvenience. If you continue to experience problems, our support team is here to help. Thank you for your understanding.</h5>
		</div>
		<div class="make-pay">
			<a href="<%=csbURL%>"><liferay-ui:message key="label-try-again" /></a>
		</div>
	</div>
</div>