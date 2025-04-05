<%@ include file="/init.jsp"%>

<div class="app-inner-content-wrapper mob-res-inner-content">
	<p class="policy-no"><liferay-ui:message key="label-pr-policy-no" /><span>${commonDetails.policyNo }</span></p>
	<h2 class="pad-left"><liferay-ui:message key="proposal-form-heading-text" /></h2>
	<p class="color-text"><span><i class="fas fa-star"></i></span><liferay-ui:message key="label-tip-your-choice-will-help-us-understand-you-better-and-we-can-guide-you-choose-the-best-offer" /></p>
	<div class="custom-accord-wrappper">
   		<div class="accordion" id="accordionExample">
   			<!-- Personal Details -->
   			<liferay-util:include page="/proposal_form/personal_details.jsp"
					servletContext="<%=application%>" />
			<!-- Health Details -->
			<liferay-util:include page="/proposal_form/health_details.jsp"
					servletContext="<%=application%>" />
			<!-- Nominee Details -->
			<c:choose>         
				<c:when test = "${isWSP}">
					<c:if test="${isLaPrSame || isSpouseExist || isChildBenefit}">
						<liferay-util:include page="/proposal_form/nominee_details.jsp"
						servletContext="<%=application%>" />
					</c:if>					
				</c:when>
				
				<c:when test = "${isZP}">
					<c:if test="${isLaPrSame || isSpouseExist || isChildBenefit}">
						<liferay-util:include page="/proposal_form/nominee_details.jsp"
							servletContext="<%=application%>" />
					</c:if>
				</c:when>
				
				<c:otherwise>
					<c:if test="${isLaPrSame}">
						<liferay-util:include page="/proposal_form/nominee_details.jsp"
							servletContext="<%=application%>" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<!-- Other Details -->
			<liferay-util:include page="/proposal_form/other_details.jsp"
					servletContext="<%=application%>" />
			<!-- Covid Details -->
			<c:if test="${commonDetails.showCovidFormYn == 'Y'}">
				<liferay-util:include page="/proposal_form/covid_questions.jsp"
						servletContext="<%=application%>" />
			</c:if>	
		       <%@ include file="/proposal_form/payment.jsp"%>
		       <%@ include file="/proposal_form/terms_and_condition.jsp"%>
		        <%@ include file="/proposal_form/error_modal.jsp"%>
             
		</div>
	</div>
	
	<div class="center-btn">
		<button class="edto-btn-primary m-auto" id="finalSubmitBtn" onclick="finalSubmission()"> Final Submission </button>
     </div>
     <input type="hidden" id="appId" value="${appid}">
</div>

