<%@ include file="../init.jsp"%>
<style>
.select-container .has-error .form-feedback-item{
	position: absolute;
    top: 38px;
    font-size: 11.2px;
}
#ekycModal .modal-dialog, #ekycValidationModal .modal-dialog{
	width: 510px;
    top: 15%;
}
#ekycModal .modal-content, #ekycValidationModal .modal-content{
	width: 510px;
}
a.eKYC-button.disable {
    pointer-events: none;
}
span:has(> a.eKYC-button.disable) {
    cursor: not-allowed;
}
</style>
<liferay-portlet:resourceURL
	id="/protean/ekyc" var="proteanEkycApiRsourceURL" copyCurrentRenderParameters="false" />
<div class="card">
	<div class="card-header" id="headingOne">
		<!--<h2 class="mb-0 title-tabs">
			<span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span>
			<liferay-ui:message key="label-personal-details" />
		</h2>-->
		<h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
			   <p><liferay-ui:message key="label-personal-details" /></p>
			</button>
		 </h2>
	</div>
	<div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
		<div class="card-body">
			<div class="edto-income-plan-tiles-wrapper">
				<h4>
					<liferay-ui:message
						key="label-lets-get-started-with-personal-details-of-life-assured-you-can-return-any-time-and-fill-the-remaining-details" />
				</h4>
	
				<aui:form name="personal_details_form" action="" method="post"
					enctype="multipart/form-data" onSubmit="event.preventDefault();">
					<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
					<input type="hidden" id="getCKycURL" value="${getCKycDetailsURL}" />
					<input type="hidden" id="postCKycURL" value="${postCKycDetailsURL}" />
					<input type="hidden" id="isLaMinor" value="${isLaMinor}" />
					<input type="hidden" id="isInfant" value="${isInfant}" />
					<input type="hidden" id="isLaPrSame" value="${isLaPrSame}" />
					<input type="hidden" id="isSpouseExist" value="${isSpouseExist}" />
					<input type="hidden" id="isChildBenefit" value="${isChildBenefit}" />
					<input type="hidden" id="isLongForm" value="${isLongForm}" />
					<input type="hidden" id="displayABHANoField" value="${displayABHANoField}" />
					<aui:input type="hidden" name="applicationNumber" value="${commonDetails.applicationNumber}" />
					<aui:input type="hidden" name="policyNumber" value="${commonDetails.policyNo}" />
										
					<input type="hidden" id="isChildBenefit" value="${isChildBenefit}" />
					<aui:input type="hidden" name="ekycDetailsByTxnId" value="${ekycDetailsByTxnId}" />
					<aui:input type="hidden" name="ekycPerformed" value="${ekycPerformed}" />
					<aui:input type="hidden" name="proteanEkycApiRsourceURL" value="${proteanEkycApiRsourceURL}"  />
					<input type="hidden" id="ekycSuccess_la" value="${ekycSuccess_la != null ? ekycSuccess_la : false}" />
					<input type="hidden" id="aadharUuidRndr" value="${aadharUuidRndr}" />
					<input type="hidden" id="isLaEducationFieldActive" value="false" />
					<input type="hidden" id="isSpouseEducationFieldActive" value="false" />
					<input type="hidden" id="isProposerEducationFieldActive" value="false" />
					<input type="hidden" id="ekycSuccess_proposer" value="${ekycSuccess_proposer}" />
					<c:choose>         
						<c:when test = "${isWSP}">
							<input type="hidden" id="isNomineeEnabled" value="${isLaPrSame || isSpouseExist || isChildBenefit}" />
						</c:when>
						
						<c:when test = "${isZP}">
							<input type="hidden" id="isNomineeEnabled" value="${isLaPrSame || isSpouseExist || isChildBenefit}" />
						</c:when>
						
						<c:otherwise>
							<input type="hidden" id="isNomineeEnabled" value="${isLaPrSame}"/>
						</c:otherwise>
					</c:choose>

					<div id='<portlet:namespace/>personal_details_step'>
						
						<!-- Basic Details of LA -->
						<liferay-util:include page="/proposal_form/personal_details/details_of_la.jsp"
						servletContext="<%=application%>" />
						
						<!-- Address Details of LA -->
						<liferay-util:include page="/proposal_form/personal_details/address_of_la.jsp"
						servletContext="<%=application%>" />
						
						<!-- Contact Details of LA -->
						<c:if test="${!(isInfant || isLaMinor)}">
							<liferay-util:include page="/proposal_form/personal_details/contact_of_la.jsp"
							servletContext="<%=application%>" />
						</c:if>
						
						<!-- Education Details of LA -->
						<liferay-util:include page="/proposal_form/personal_details/education_of_la.jsp"
						servletContext="<%=application%>" />
						
						<!-- Basic Details of Proposer -->
						<c:if test="${!isLaPrSame}">
							<liferay-util:include page="/proposal_form/personal_details/details_of_proposer.jsp"
								servletContext="<%=application%>" />
						</c:if>
						
						<!-- Address Details of Proposer -->
						<c:if test="${!isLaPrSame}">
							<liferay-util:include page="/proposal_form/personal_details/address_of_proposer.jsp"
								servletContext="<%=application%>" />
						</c:if>
						
						<!-- Contact Details of Proposer -->
						<c:if test="${!isLaPrSame}">
							<liferay-util:include page="/proposal_form/personal_details/contact_of_proposer.jsp"
							servletContext="<%=application%>" />
						</c:if>
						
						<!-- Education Details of Proposer -->
						<c:if test="${!isLaPrSame}">
							<liferay-util:include page="/proposal_form/personal_details/education_of_proposer.jsp"
							servletContext="<%=application%>" />
						</c:if>
						
						<!-- Basic Details of Spouse -->
						<c:if test="${isSpouseExist}">
							<liferay-util:include page="/proposal_form/personal_details/details_of_spouse.jsp"
								servletContext="<%=application%>" />
						</c:if>
						
						<!-- Address Details of Spouse -->
						<c:if test="${isSpouseExist}">
							<liferay-util:include page="/proposal_form/personal_details/address_of_spouse.jsp"
								servletContext="<%=application%>" />
						</c:if>
						
						<!-- Contact Details of Spouse -->
						<c:if test="${isSpouseExist}">
							<liferay-util:include page="/proposal_form/personal_details/contact_of_spouse.jsp"
							servletContext="<%=application%>" />
						</c:if>
							
						<!-- Education Details of Spouse -->
						<c:if test="${isSpouseExist}">
							<liferay-util:include page="/proposal_form/personal_details/education_of_spouse.jsp"
							servletContext="<%=application%>" />
						</c:if>
							
						<!-- Employement Details of LA -->
						<c:if test="${!(isInfant || isLaMinor)}">
							<liferay-util:include page="/proposal_form/personal_details/employment_of_la.jsp"
							servletContext="<%=application%>" />
						</c:if>
						
						<!-- Employement Details of Proposer -->
						<c:if test="${!isLaPrSame}">
							<liferay-util:include page="/proposal_form/personal_details/employment_of_proposer.jsp"
							servletContext="<%=application%>" />
						</c:if>
						
						<!-- Employement Details of Spouse -->
						<c:if test="${isSpouseExist}">
							<liferay-util:include page="/proposal_form/personal_details/employment_of_spouse.jsp"
							servletContext="<%=application%>" />
						</c:if>
							
						<!-- Other Details JSP -->
						<liferay-util:include page="/proposal_form/personal_details/other_details_of_la.jsp"
							servletContext="<%=application%>" />
					</div>
					
					<a class="edto-btn-primary mt-3 personal-details-button" href="javascript:;" onClick="savePersonalDetails('personal_details_step', true, 'personal_details_form', true);">
						<liferay-ui:message key="label-save-and-continue" />
					</a>
					
				</aui:form>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="kycModal" tabindex="-1" role="dialog" aria-labelledby="kycModalLabel" 
	aria-hidden="true" style="display: none;" data-backdrop="static">
  	<div class="modal-dialog" role="document" >
    	<div class="modal-content">
			<div id="panSuccess" class="modal-body">
				<input type="hidden" id="userPrefix" value="" />
				<input type="hidden" id="panJSON" value="" />
				<table class="table table-borderless kycDetailsTable mb-4">
				    <tbody>
				        <tr id="panFullName">
				            <td><p><liferay-ui:message key="label-pf-full-name" /></p></td>
				            <td class="panFullName"></td>
				        </tr>
				    	<tr id="panGender">
				            <td><p><liferay-ui:message key="label-pf-gender" /></p></td>
				            <td class="panGender"></td>
				        </tr>
				        <tr id="panDob">
				            <td><p><liferay-ui:message key="label-pf-date-of-birth" /></p></td>
				            <td class="panDob"></td>
				        </tr>
				        
				        <tr id="panMobile">
				            <td><p><liferay-ui:message key="label-pf-mobile-number" /></p></td>
				            <td class="panMobile"></td>
				        </tr>
				        
				        <tr id="panEmail">
				            <td><p><liferay-ui:message key="label-pf-email-id" /></p></td>
				            <td class="panEmail"></td>
				        </tr>
				        
			        </tbody>
				</table>
				<div class="center-kyc-btn">
					<button id="discard" class="btn linkBtn d-none" type="button" onclick="closeKycModal(false);"><liferay-ui:message key="label-discard" /></button>
					<button class="btn linkBtn" type="button" onclick="closeKycModal(false);">Close</button>
					<button id="confirm" class="edto-btn-primary d-none" type="button" onclick="postCKycDetails();"><liferay-ui:message key="label-confirm-proceed" /></button>
					<button id="edit" class="btn linkBtn d-none" type="button" onclick="postCKycDetails();"><liferay-ui:message key="label-edit-details" /></button>
				</div>
			</div>
			<div id="panFailure" class="modal-body">
				<div class="text-center mb-4">
					<h3 id="responseMessage" class="fs18 fontbold  w-100"></h3>
                 </div>
                 <div class="center-kyc-btn">
					<button class="btn linkBtn" type="button" onclick="closeKycModal(false);">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="ekycModal" tabindex="-1" role="dialog" aria-labelledby="ekycModalLabel" 
	aria-hidden="true" style="display: none;">
  	<div class="modal-dialog" role="document" >
    	<div class="modal-content">
			<div id="ekycSuccess" class="modal-body">
				<div class="text-center">
					<span>E-KYC Success</span>
				</div>
				<div class="center-kyc-btn">
					<button class="btn linkBtn" type="button" onclick="closeEkycModal(false);">Close</button>
				</div>
			</div>
			<div id="ekycFailed" class="modal-body">
				<div class="text-center">
					<span>E-KYC Failed. Please complete KYC with Run-CKYC option</span>
				</div>
				<div class="center-kyc-btn">
					<button class="btn linkBtn" type="button" onclick="closeEkycModal(false);">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="ekycValidationModal" tabindex="-1" role="dialog" aria-labelledby="ekycValidationModalLabel" 
	aria-hidden="true" style="display: none;">
  	<div class="modal-dialog" role="document" >
    	<div class="modal-content">
			<div id="ekycValidationBody" class="modal-body">
				<div class="text-center">
					<span>Please complete E-KYC process for <span id="ekycUserValidation"></span></span>
				</div>
				<div class="center-kyc-btn">
					<button class="btn linkBtn" type="button" onclick="closeEkycValidationModal(false);">Close</button>
				</div>
			</div>		
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/personal_details.js?t=<%=ProposalFormConstants.CURRENT_TIME_STAMP%>"></script>
