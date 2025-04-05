<%@ include file="../init.jsp"%>
<%
	List<AssetCategory> complaintTypes = new ArrayList<AssetCategory>();
	if (Validator.isNotNull(renderRequest.getAttribute("complaintTypeList"))) {
		complaintTypes = (List<AssetCategory>) renderRequest.getAttribute("complaintTypeList");
	}
%>
<liferay-portlet:actionURL var="submitFileAComplaintFormURL"
	name="<%=ContactUsPortletKeys.SUBMIT_FILE_A_COMPLAINT_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="subComplaintBasedOnComplaintTypeURL"
	copyCurrentRenderParameters="false" />

<aui:form cssClass="contact-form-tb" name="file-a-complaint-form"
	action="<%=submitFileAComplaintFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="complaintPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitFileAComplaintFormURL}"
		name="submitFileAComplaintFormURL" />
	<aui:input type="hidden" value="${subComplaintBasedOnComplaintTypeURL}"
		name="subComplaintBasedOnComplaintTypeURL" />
	<div class="application_step"
		id='<portlet:namespace/>file_a_complaint_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="container">
				<div class="form-tiles-main-wrappper">
					<div>
						  <h2 class="fontbold fs28">
								<liferay-ui:message key="label-file-a-complaint" />
							</h2>
						 <p class="pb-3">
								<liferay-ui:message key="label-please-fill-in-the-below-details" />
							</p>
						</span>
					</div>
					<div class="row">
						<div class="col-md-4 col-12 form-group">
							<aui:input name="policyNumber" label=""
								placeholder="label-policy-number" wrappedField="<%=true%>"
								cssClass="validate document-validate valphanum" type="text" value=""
								maxLength="10" minLength="10">
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
							</aui:input>
							<span>*</span>
						</div>
						<div class="col-md-4 col-12 form-group">
							<aui:input name="mobileNo" label="" pattern = "[0-9]*" inputmode="numeric"
								placeholder="label-mobile-number" wrappedField="<%=true%>"
								cssClass="validate vnumber" type="text" value="" maxLength="10"
								minLength="10">
								<aui:validator name="number" />
								<aui:validator name="required"
									errorMessage='error-label-mobile-number' />
								<aui:validator name="maxLength"
									errorMessage="error-label-max-length-ten">10</aui:validator>
								<aui:validator name="minLength"
									errorMessage="error-label-min-length-ten">10</aui:validator>
							</aui:input>
							<span>*</span>
						</div>
						<div class="col-md-4 col-12 form-group">
							<aui:input name="email" label=""
								placeholder="label-email-id" wrappedField="<%=true%>"
								cssClass="validate" type="text">
								<aui:validator name="email" />
								<aui:validator name="required" errorMessage='error-label-email' />
							</aui:input>
							<span>*</span>
						</div>
						<div class="col-md-6 col-12">
							<div class="form-group1 ">
								<aui:select name="complaintType" label="" placeholder="&nbsp;"
									wrappedField="<%=true%>" cssClass="validate"
									onChange="populateComplaintSubTypes(this.value)">
									<aui:validator name="required"
										errorMessage='error-label-complaint-types' />
									<aui:option selected="true">
										<liferay-ui:message key="label-select-complaint-type" />
									</aui:option>
									<%
										for (AssetCategory complaintTypeCategory : complaintTypes) {
									%>
									<aui:option value="<%=complaintTypeCategory.getCategoryId()%>"
										label="<%=complaintTypeCategory.getTitle(themeDisplay.getLocale())%>">
									</aui:option>
									<%
										}
									%>
								</aui:select>
								<span>*</span>
							</div>
						</div>
						<div class="col-md-6 col-12">
							<div class="form-group1">
								<aui:select name="complaintSubtypes" label=""
									placeholder="&nbsp;" wrappedField="<%=true%>"
									cssClass="validate">
									<aui:validator name="required"
										errorMessage='error-label-complaint-sub-types' />
									<aui:option selected="true">
										<liferay-ui:message key="label-select-sub-complaint-type" />
									</aui:option>
								</aui:select>
								<span>*</span>
							</div>
						</div>
						<div class="col-md-12 col-12 form-group">
							<aui:input name="comments" label=""
								placeholder="label-description" type="textarea" wrappedField="<%=true%>"
								cssClass="validate">
								<aui:validator name="required"
									errorMessage='error-label-description-required' />
							</aui:input>
						</div>
						<div class="ml-2 mt-2 text-center text-md-left w-100">
							<aui:button-row>
								<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
									value="label-submit-now"
									onClick="validateFileAComplaintForm('file_a_complaint_step', true, 'file-a-complaint-form');"></aui:button>
							</aui:button-row>
						</div>
					</div>
				</div>
			</div>
		</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="fileAComplaintBackdrop"
	aria-labelledby="fileAComplaintBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="complaintCloseBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeFileAComplaintModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="successMessage" class="fs22 fontbold  w-100" style='display: none;'>
								<liferay-ui:message key="label-your-complaint-reference-no" />
							</h3>
							<h3 id="complaintResponseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="complaintOkayBtn" type="button" class="edto-btn-primary"
							onClick="closeFileAComplaintModal(false);">
							<liferay-ui:message key="label-done" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>complaint-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/file-a-complaint.js?v=1.1"></script>

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
