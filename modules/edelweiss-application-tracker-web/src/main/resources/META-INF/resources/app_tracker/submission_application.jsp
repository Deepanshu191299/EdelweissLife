<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="in.edelweiss.common.util.DateFormatterUtil"%>
<%@page import="in.edelweiss.common.contants.DateConstants"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<%

JSONObject policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");
NumberFormat formatter=NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

%>
<div class="${isValidPolicy ? 'tab-pane fade' : 'tab-pane fade active show' }" id="pills-home" role="tabpanel"
	aria-labelledby="pills-home-tab">
	<h2><liferay-ui:message key="label-submission-of-application" /></h2>
	<p><liferay-ui:message key="label-details-pertaining-to-your-application" /></p>
  <c:choose>
    <c:when test="${isValidPolicy}">
	<div class="application-form-tiles-wrapper">
		<div class="form-details-wrapper">
			<div class="form-field">
				<p>
					<liferay-ui:message key="label-application-no" />
					<span> <%=policyFormJSONObject.getString("application_number")%></span>
				</p>
				
				<p>
					<liferay-ui:message key="label-policy-number" />
					<span> <%=policyFormJSONObject.getString("policy_number")%></span>
				</p>

				<p>
					<liferay-ui:message key="label-proposer-name" />
					<span> <%=policyFormJSONObject.getString("person_name")%></span>
				</p>
			
				<%-- <p>
					<liferay-ui:message key="label-life-assured-name" />
					<span> <%=policyFormJSONObject.getString("person_name")%></span>
				</p> --%>
		
				<p>
					<liferay-ui:message key="label-life-cover" />
					<span> <%=String.valueOf(formatter.format(policyFormJSONObject.getDouble("sum_assured"))).replaceAll("\\.\\d+$", "")%></span>
				</p>
			</div>
			<div class="form-field">
				<p>
					<liferay-ui:message key="label-premium" />
					<span> <%=String.valueOf(formatter.format(policyFormJSONObject.getDouble("total_premium_amount"))).replaceAll("\\.\\d+$", "")%></span>
				</p>

				<p>
					<liferay-ui:message key="label-payment-mode" />
					<span> <%=policyFormJSONObject.getString("premium_payment_frequency")%></span>
				</p>

				<p>
					<liferay-ui:message key="label-policy-term" />
					<span> <%=policyFormJSONObject.getString("policy_term")%></span>
				</p>

				<p>
					<liferay-ui:message key="label-ppt" />
					<span> <%=policyFormJSONObject.getString("premium_payment_term")%></span>
				</p>

			</div>
		</div>
		<!--  -->
		<div class="form-details-total-wrapper">
			<div class="form-field">
				<p>
					<liferay-ui:message key="label-payment" />
				</p>
				<% if(policyFormJSONObject.getString("payment_status").equalsIgnoreCase("Y")){%>
				<span> 
					<img src="/o/edelweisstokio-theme/images/bullet.svg" alt="icon"> 
					<liferay-ui:message key="label-completed" />
				</span>
				<% }else{%>
				<span> 
					<img src="/o/edelweisstokio-theme/images/Pending.svg" width="23" alt="icon"> 
					<liferay-ui:message key="label-pending" />
				</span>
				<% }%>
			</div>

			<% if(policyFormJSONObject.getString("payment_status").equalsIgnoreCase("Y")){%>
			<div class="form-field">
				<p><%=DateFormatterUtil.parseDateToSpecificFormat(policyFormJSONObject.getString("payment_complete_date").split("\\.")[0], DateConstants.HYPHEN_YYYY_MM_DD)%></p>
				<span></span>
			</div>
			<% }else{%>
			<div class="form-field">
				<p></p>
				<span></span>
			</div>
			<% }%>
			<div class="form-field">
				<p>
					<liferay-ui:message key="label-proposal-form" />
				</p>
				<% if(policyFormJSONObject.getString("proposal_form_status").equalsIgnoreCase("Y")){%>
				<span> 
					<img src="/o/edelweisstokio-theme/images/bullet.svg" alt="icon"> 
					<liferay-ui:message key="label-completed" />
				</span>
				<% }else{%>
				<span> 
					<img src="/o/edelweisstokio-theme/images/Pending.svg" width="23" alt="icon"> 
					<liferay-ui:message key="label-pending" />
				</span>
				<% }%>
			</div>
			<% if(policyFormJSONObject.getString("proposal_form_status").equalsIgnoreCase("Y")){%>
			<div class="form-field">
				<p><%=DateFormatterUtil.parseDateToSpecificFormat(policyFormJSONObject.getString("proposal_form_complete_date").split("\\.")[0], DateConstants.HYPHEN_YYYY_MM_DD)%></p>
				<span></span>
			</div>
			<% }%>
		</div>
	</div>
	<div class="center-btn gap-btn">
		<% if(!policyFormJSONObject.getString("proposal_form_path").isBlank()){%>
		<a class="edto-btn-primary" target="_blank" href="<%=policyFormJSONObject.getString("proposal_form_path")%>"><liferay-ui:message key="label-download-pf" /></a> 
		<%}%>
		<% if(!policyFormJSONObject.getString("pdf_path").isBlank()){%>
		<a class="edto-btn-primary" target="_blank" href="<%=policyFormJSONObject.getString("pdf_path")%>" ><liferay-ui:message key="label-download-bi" /></a>
		<%}%>
	</div>
	</c:when>
	<c:otherwise>
	 <div class="application-form-tiles-wrapper">
		<div class="">
	  	   <h4><liferay-ui:message key="label-application-no-details-found" /></h4>
	  </div>
	 </div>
	</c:otherwise>
</c:choose>
</div>