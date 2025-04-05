<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<%
JSONObject verificationJSONObject = (JSONObject) renderRequest.getAttribute("verificationJSONObject");
%>

<div class="app-inner-content-wrapper">
	<div class="tab-content" id="pills-tabContent">

		<liferay-util:include page="/app_tracker/submission_application.jsp"
			servletContext="<%=application%>" />
			
	  <c:if test="${isValidPolicy }">
	
	<% 	if(Validator.isNotNull(verificationJSONObject) 
		&& Validator.isNotNull(verificationJSONObject.getString("enachStatus")) 
		&& !(verificationJSONObject.getString("enachStatus").equalsIgnoreCase("NA") 
			|| verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Skip")
			|| verificationJSONObject.getString("enachStatus").isBlank())){ %>

		<liferay-util:include page="/app_tracker/auto_debit_setup.jsp"
			servletContext="<%=application%>" />
			
	<% }if(Validator.isNotNull(verificationJSONObject) 
		&& Validator.isNotNull(verificationJSONObject.getString("documentUploadStatus")) 
		&& !(verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("NA") 
			|| verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Skip")
			|| verificationJSONObject.getString("documentUploadStatus").isBlank())){ %>

		<liferay-util:include page="/app_tracker/documents_proof.jsp"
			servletContext="<%=application%>" />
			
	<%} if(Validator.isNotNull(verificationJSONObject) 
		&& Validator.isNotNull(verificationJSONObject.getString("pivcStatus")) 
		&& !(verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("NA")
			|| verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Skip") 
			|| verificationJSONObject.getString("pivcStatus").isBlank())){ %>

		<liferay-util:include page="/app_tracker/video_verification.jsp"
			servletContext="<%=application%>" />

	<% } if(Validator.isNotNull(verificationJSONObject) 
		&& Validator.isNotNull(verificationJSONObject.getString("tpaStatus")) 
		&& !(verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("NA") 
			|| verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Skip")
			|| verificationJSONObject.getString("tpaStatus").isBlank())){ %>
		
		<liferay-util:include page="/app_tracker/medical_scheduling.jsp"
			servletContext="<%=application%>" />
	<%} %>
			
		<liferay-util:include page="/app_tracker/additional_documents.jsp"
			servletContext="<%=application%>" />

		<liferay-util:include page="/app_tracker/policy_status.jsp"
			servletContext="<%=application%>" />
      </c:if>
	</div>
</div>