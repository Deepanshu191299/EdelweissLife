<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitResumeApplicationFormURL"
	name="<%=ResumeApplicationPortletKeys.SUBMIT_RESUME_APPLICATION_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<aui:form name="resume-application-form"
	action="<%=submitResumeApplicationFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="resumePortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitResumeApplicationFormURL}"
		name="submitResumeApplicationFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>resume_application_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="container">
			<div class="row justify-content-center">
			<div class="col-12 col-md-8 col-lg-6 ">
				<div class="form-tiles-main-wrappper">
					<div>
						<h1 class="resumeapp-Form-heading"><liferay-ui:message
								key="label-please-complete-your-application" />
						</h1>
					</div>
					<div class="row">
						<div class="col-md-12 col-12">
							<label class="custom-field two"> <aui:input
									name="policyNumber" label="" placeholder="&nbsp;"
									wrappedField="<%=true%>" cssClass="validate document-validate valphanum"
									type="text" value="" maxLength="10" minLength="10">
									<aui:validator name="alphanum" />
									<aui:validator name="required"
										errorMessage='error-label-policy-number' />
									<aui:validator name="maxLength"
										errorMessage="error-label-max-length-ten">10</aui:validator>
									<aui:validator name="minLength"
										errorMessage="error-label-min-length-ten">10</aui:validator>
									<aui:validator name="custom"
										errorMessage="error-label-invalid-policy-number">
		                    function(val, fieldNode, ruleValue) {
			                    
			                    var inputType;
			                    var inputValid = false;
			                    
			                    var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
			                    
			                    if(policyRegex.test(val)){
			                    	inputType = "policyNumber";
			                    	inputValid = true;
			                    }else{
			                    	inputValid = false;
			                    }
			                    return inputValid;
		                    }
		                </aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-policy-number" /></span>
							</label>
						</div>
						<div class="col-md-12 col-12">
							<label class="custom-field two"> <aui:input
									name="dateOfBirth" label="" placeholder="&nbsp;"
									wrappedField="<%=true%>" cssClass="validate vdate" type="text"
									value="" maxLength="10">
									<aui:validator name="required"
										errorMessage='error-label-date-of-birth' />
									<aui:validator name="custom" errorMessage="error-label-date">
                        function(val, fieldNode, ruleValue) {

                        var parts = val.split("/");

                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
                        	return false;
                        }else{
                        	if(parts[0] > 31){
                        		return false;
                        	}
                        	if(parts[1] > 12){
                        		return false;
                        	}
                        	if(parts[2].length!=4){
                        		return false;
                        	}
                        	else if(parts[0].length!=2 && parts[1].length!=2){
                        		return false;
                        	}
                        	return true;
                        	}
                        }
                    </aui:validator>
									<aui:validator name="custom"
										errorMessage="error-label-date-must-be-in-past">
	                        function(val, fieldNode, ruleValue) {
	
	                        var parts = val.split("/");
	                        var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	
	                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
	                        	return false;
	                        }else{
	                        	var now = new Date();
							   	if(selectedDate > now) {
							    	return false;
							   	}
	                        	return true;
	                        	}
	                        }
	                    </aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-date-of-birth" /></span>
							</label>
						</div>
					</div>
					<div class="claim-form-btn m-0">
						<aui:button-row>
							<aui:button cssClass="edto-btn-primary edto-btn-primary1" id="confirmSubmit"
								value="label-continue"
								onClick="validateResumeApplicationForm('resume_application_step', true, 'resume-application-form');"></aui:button>
						</aui:button-row>
					</div>
				</div>
			</div>
			</div>
			</div>
		</div>
	</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="resumeApplicationBackdrop"
	aria-labelledby="resumeApplicationBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close" data-dismiss="modal"
						aria-label="Close" onClick="closeApplicationModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-3">
						<div class="text-center">
							<h5 id="responseMessage"></h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>resume-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script
	src="<%=request.getContextPath()%>/js/resume-application.js?v=1.1"></script>
<style>
.claim-form-btn {
	display: flex;
	justify-content: center;
	width: 100%;
	margin-top: 15px;
}

.td-heading {
	background-color: transparent !important;
	border: 0;
	color: #212529 !important;
	font-weight: bold !important;
}
</style>
