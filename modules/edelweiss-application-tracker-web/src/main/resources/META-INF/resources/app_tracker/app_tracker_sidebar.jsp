<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<%
JSONObject policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");
JSONObject verificationJSONObject = (JSONObject) renderRequest.getAttribute("verificationJSONObject");

if(Validator.isNotNull(policyFormJSONObject)){
	request.setAttribute("userName", GetterUtil.getString(policyFormJSONObject.getString("person_name"),"User,"));
	request.setAttribute("isValidPolicy", GetterUtil.getBoolean(policyFormJSONObject.getBoolean("status"),false));
}

boolean autoDebitPending = false, docSubmissionPending = false, videoPending = false, medicalschedulingPending = false;

if(Validator.isNotNull(verificationJSONObject)){
	if(Validator.isNotNull(verificationJSONObject.getString("enachStatus")) 
			&& verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Pending")){
		autoDebitPending = true;
	}else if(Validator.isNotNull(verificationJSONObject.getString("documentUploadStatus")) 
			&& verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Pending")){
		docSubmissionPending = true;
	}else if(Validator.isNotNull(verificationJSONObject.getString("pivcStatus")) 
			&& verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Pending")){
		videoPending = true;
	}else if(Validator.isNotNull(verificationJSONObject.getString("tpaStatus")) 
			&& verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Pending")){
		medicalschedulingPending = true;
	}
}

request.setAttribute("autoDebitPending", autoDebitPending);
request.setAttribute("docSubmissionPending", docSubmissionPending);
request.setAttribute("videoPending", videoPending);
request.setAttribute("medicalschedulingPending", medicalschedulingPending);

%>

<div class="app-sidebar-main-wrapper">
		<div class="filter-main-wrapper">

		   	<c:if test="<%=Validator.isNotNull(policyFormJSONObject) 
					&& Validator.isNotNull(policyFormJSONObject.getString("status")) 
					&& policyFormJSONObject.getBoolean("status") %>">
			   	<p><liferay-ui:message key="label-policy-number" /></p>
				<h4><%=policyFormJSONObject.getString("policy_number") + " (" + policyFormJSONObject.getString("product_name") + ")" %></h4>
		   	</c:if>
			
		</div>
		<div class="sidebar-tabs-wrapper">
			<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
				
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="pills-home-tab" data-toggle="pill"
						data-target="#pills-home" type="button" role="tab"
						aria-controls="pills-home" aria-selected="false">
						<div class="icon-tab">
							<span class="complete"> 
								<img src="/o/edelweisstokio-theme/images/check-tick.png" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-submission-of-application" /></h4>
							<c:choose>
							   	<c:when test="<%=Validator.isNotNull(policyFormJSONObject) 
										&& Validator.isNotNull(policyFormJSONObject.getString("status")) 
										&& policyFormJSONObject.getBoolean("status") %>">
								   	<p class="text-green"><liferay-ui:message key="label-completed" /></p>
							   	</c:when>
							   	<c:otherwise>
							   		<p class="text-red"><liferay-ui:message key="label-not-found" /></p>
							   	</c:otherwise>
							 </c:choose>
						</div>
					</button>
				</li>
				
				<%if(Validator.isNotNull(policyFormJSONObject) 
					&& Validator.isNotNull(policyFormJSONObject.getString("status")) 
					&& policyFormJSONObject.getBoolean("status")){ %>
									
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="pills-policy-tab" data-toggle="pill"
						data-target="#pills-policy" type="button" role="tab"
						aria-controls="pills-policy" aria-selected="false">
						<div class="icon-tab">
							<span class="ps-status-icon"> <img src="/o/edelweisstokio-theme/images/policy.png" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-at-policy-status" /></h4>
							<p class="ps-status-text text-grey"><liferay-ui:message key="label-at-under-review" /></p>
						</div>
					</button>
				</li>
				<%} %>
				
				<% 	
				if(Validator.isNotNull(verificationJSONObject) 
					&& Validator.isNotNull(verificationJSONObject.getString("enachStatus")) 
					&& !(verificationJSONObject.getString("enachStatus").equalsIgnoreCase("NA") 
						|| verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Skip")
						|| verificationJSONObject.getString("enachStatus").isBlank())){ %>
						
				<li class="nav-item" role="presentation">
					<button class="<%= autoDebitPending ? "active" : StringPool.BLANK %> nav-link" id="pills-profile-tab"
						data-toggle="pill" data-target="#pills-profile" type="button"
						role="tab" aria-controls="pills-profile" aria-selected="true">
						<div class="icon-tab">
							<span class="<%= verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Completed") ? "complete" : StringPool.BLANK %>">
								<img src="<%= verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Completed") ? 
										"/o/edelweisstokio-theme/images/check-tick.png" : "/o/edelweisstokio-theme/images/auto-debit.png"%>" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-auto-debit-setup" /></h4>
							<% if(verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Completed")){%>
								<p class="text-green"><liferay-ui:message key="label-completed" /></p>
							<% }else if(verificationJSONObject.getString("enachStatus").equalsIgnoreCase("InProcess")){%>
								<p class="text-red"><liferay-ui:message key="label-in-progress" /></p>
							<% }else if(verificationJSONObject.getString("enachStatus").equalsIgnoreCase("Pending")){%>
								<p class="text-red"><liferay-ui:message key="label-pending" /></p>
							<% }%>
						</div>
					</button>
				</li>
				<% }
				
				if(Validator.isNotNull(verificationJSONObject) 
					&& Validator.isNotNull(verificationJSONObject.getString("documentUploadStatus")) 
					&& !(verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("NA") 
						|| verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Skip")
						|| verificationJSONObject.getString("documentUploadStatus").isBlank())){ %>
						
				<li class="nav-item" role="presentation">
					<button class="<%= docSubmissionPending ? "active" : StringPool.BLANK %> nav-link" id="pills-contact-tab" data-toggle="pill"
						data-target="#pills-contact" type="button" role="tab"
						aria-controls="pills-contact" aria-selected="false">
						<div class="icon-tab">
							<span class="<%= verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Completed") ? "complete" : StringPool.BLANK %>">
								<img src="<%= verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Completed") ? 
										"/o/edelweisstokio-theme/images/check-tick.png" : "/o/edelweisstokio-theme/images/doc.png"%>" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-documents-proof" /></h4>
							<% if(verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Completed")){%>
								<p class="text-green"><liferay-ui:message key="label-completed" /></p>
							<% }else if(verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("InProcess")){%>
								<p class="text-red"><liferay-ui:message key="label-in-progress" /></p>
							<% }else if(verificationJSONObject.getString("documentUploadStatus").equalsIgnoreCase("Pending")){%>
								<p class="text-red"><liferay-ui:message key="label-pending" /></p>
							<% }%>
						</div>
					</button>
				</li>
				<%} if(Validator.isNotNull(verificationJSONObject) 
					&& Validator.isNotNull(verificationJSONObject.getString("pivcStatus")) 
					&& !(verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("NA") 
						|| verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Skip")
						|| verificationJSONObject.getString("pivcStatus").isBlank())){ %>
				<li class="nav-item" role="presentation">
					<button class="<%= videoPending ? "active" : StringPool.BLANK %> nav-link" id="pills-video-tab" data-toggle="pill"
						data-target="#pills-video" type="button" role="tab"
						aria-controls="pills-video" aria-selected="false">
						<div class="icon-tab">
							<span> <img src="/o/edelweisstokio-theme/images/doc.png" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-at-pivc-title" /></h4>
							<% if(verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Completed")){%>
								<p class="text-green"><liferay-ui:message key="label-completed" /></p>
							<% }else if(verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("InProgress")){%>
								<p class="text-red"><liferay-ui:message key="label-in-progress" /></p>
							<% }else if(verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Pending")){%>
								<p class="text-red"><liferay-ui:message key="label-pending" /></p>
							<% }%>
						</div>
					</button>
				</li>
				
				<% } if(Validator.isNotNull(verificationJSONObject) 
					&& Validator.isNotNull(verificationJSONObject.getString("tpaStatus")) 
					&& !(verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("NA") 
						|| verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Skip") 
						|| verificationJSONObject.getString("tpaStatus").isBlank())){ %>
				
				<li class="nav-item" role="presentation">
					<button class="<%= medicalschedulingPending ? "active" : StringPool.BLANK %> nav-link" id="pills-medical-tab" data-toggle="pill"
						data-target="#pills-medical" type="button" role="tab"
						aria-controls="pills-medical" aria-selected="false">
						<div class="icon-tab">
							<span class="<%= verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Completed") ? "complete" : StringPool.BLANK %>">
								<img src="<%= verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Completed") ? 
										"/o/edelweisstokio-theme/images/check-tick.png" : "/o/edelweisstokio-theme/images/doc.png"%>" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-medical-scheduling" /></h4>
							<% if(verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Completed")){%>
								<p class="text-green"><liferay-ui:message key="label-completed" /></p>
							<% }else if(verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("InProcess")){%>
								<p class="text-red"><liferay-ui:message key="label-in-progress" /></p>
							<% }else if(verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Pending")){%>
								<p class="text-red"><liferay-ui:message key="label-pending" /></p>
							<% }%>
						</div>
					</button>
				</li>
				<%} %>

				<%if(Validator.isNotNull(policyFormJSONObject) 
					&& Validator.isNotNull(policyFormJSONObject.getString("status")) 
					&& policyFormJSONObject.getBoolean("status")){ %>
									
				<li class="nav-item" role="presentation" id="additional-doc-li">
					<button class="nav-link" id="pills-additionaldoc-tab" data-toggle="pill"
						data-target="#pills-additionaldoc" type="button" role="tab"
						aria-controls="pills-additionaldoc" aria-selected="false">
						<div class="icon-tab">
							<span> <img src="/o/edelweisstokio-theme/images/policy.png" alt="icon">
							</span>
						</div>
						<div class="text-tab">
							<h4><liferay-ui:message key="label-additional-documents" /></h4>
							<p class="additional-doc-status-block text-red"><liferay-ui:message key="label-pending" /></p>
						</div>
					</button>
				</li>
				
				<%} %>
			</ul>
		</div>
	</div>