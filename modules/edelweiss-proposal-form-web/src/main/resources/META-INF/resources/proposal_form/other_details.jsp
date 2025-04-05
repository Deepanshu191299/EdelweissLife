<%@ include file="../init.jsp"%>

<div class="card">
	<div class="card-header" id="headingFour">
		<!--<h2 class="mb-0 title-tabs">
			<span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span>
			<liferay-ui:message key="label-other-details" />
		</h2>-->
		<h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
			   <p><liferay-ui:message key="label-other-details" /></p>
			</button>
		 </h2>
	</div>
	<div id="collapseFour" class="collapse" aria-labelledby="headingFour" data-parent="#accordionExample">
		<div class="card-body">
			<div class="edto-income-plan-tiles-wrapper">
				<aui:form name="other_details_form" action="#" method="post"
					enctype="multipart/form-data" onSubmit="event.preventDefault();">
					<div id='<portlet:namespace/>other_details_step'>
						<div class="communication-details border-0 pb-0 mb-0" id='insurance_history'>
							<!-- LA And Proposer Insurance History Section -->
							<c:if test="${isLongForm}">
								<liferay-util:include page="/proposal_form/other_details/lf_insurance_history.jsp"
									servletContext="<%=application%>" />
							</c:if>
							<c:if test="${!isLongForm}">
								<liferay-util:include page="/proposal_form/other_details/sf_insurance_history.jsp"
									servletContext="<%=application%>" />
							</c:if>
								
							<!-- Tax Residency Section -->
							<liferay-util:include page="/proposal_form/other_details/tax_residency.jsp"
								servletContext="<%=application%>" />
								
							<!-- Bank Account Details Section -->
							<liferay-util:include page="/proposal_form/other_details/bank_account.jsp"
								servletContext="<%=application%>" />
								
							<!-- Insurance Reposetory Details -->
							<liferay-util:include page="/proposal_form/other_details/insurance _repository.jsp"
								servletContext="<%=application%>" />
								
							<!-- Go Green Initiative Details -->
							<liferay-util:include page="/proposal_form/other_details/go_green_initiative.jsp"
								servletContext="<%=application%>" />
							
						</div>
						<a class="edto-btn-primary saveBtn" href="javascript:;" id="otherDetailsSaveBtn">Save & Continue</a>
					</div>
				</aui:form>
			</div>
		</div>
	</div>
</div>

<style>
#insurance_history.communication-details .covind-inner-wrapper .medical-test .radio-wrapper .location-field .form-box .custom-field {
	margin-top: 0;
}
#insurance_history.communication-details .covind-inner-wrapper .edto-custom-table table thead th {
	background: none;
}

.saveBtn{
	margin:10px 0px 0px -7px;
}
</style>

<script>
	   
    var masterJson = {};
	try {
		masterJson = JSON.parse('${masterJson}');
    } catch (error) {
    	console.warn("Master JSON couldn't be parsed");
    	console.warn('${masterJson}');
    }

	var proposalFormData = {};
	try {
		proposalFormData = JSON.parse('${pfResponse}');
		proposalFormData = proposalFormData.response_data;
    } catch (error) {
    	console.warn("PF Data JSON couldn't be parsed");
    	console.warn('${pfResponse}');
    }
    
    var isProposerApplicable = "${isProposerApplicable}";

</script>

<script src="<%=request.getContextPath()%>/js/other_details.js?t=<%=ProposalFormConstants.CURRENT_TIME_STAMP%>"></script>
