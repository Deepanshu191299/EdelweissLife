<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitCheckStatusFormURL"
	name="<%=ContactUsPortletKeys.SUBMIT_CHECK_STATUS_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<aui:form cssClass="contact-form-tb" name="check-status-form"
	action="<%=submitCheckStatusFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="statusPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitCheckStatusFormURL}"
		name="submitCheckStatusFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>check_status_step'>
		<div class="edto-claim-pilosophy-wrapper complainFormDiv">
			<div class="container">
				<div class="form-tiles-main-wrappper">
					<div>
						 <h2 class="fontbold fs28 mb-3"> <liferay-ui:message
								key="label-check-status-here" />
						</h2>
					</div>
					<div class="row">
						<div class="col-md-12 col-12 form-group">
								<aui:input
									name="complaintReferenceNo" label="" placeholder="label-complaint-reference-no"
									wrappedField="<%=true%>" cssClass="validate document-validate valphanum"
									style="text-transform: uppercase" type="text" value="" maxLength="20">
									<aui:validator name="alphanum" />
									<aui:validator name="required"
										errorMessage='error-label-complaint-reference-no' />
									<aui:validator name="maxLength"
										errorMessage="error-label-max-length-twenty">20</aui:validator>
								</aui:input>
								<span>*</span>
						</div>
					<div class="mt-2 ml-2 text-center text-md-left w-100">
						<aui:button-row>
							<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
								value="label-submit-now"
								onClick="validateCheckStatusForm('check_status_step', true, 'check-status-form');"></aui:button>
						</aui:button-row>
					</div>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<div class="edto-claim-pilosophy-wrapper complainDetailsDiv"
	style='display: none;'>
	<div class="container">
		<div class="form-tiles-main-wrappper check-status-block">
			<div class="row">
				<div class="col-md-6 col-12 t-b-margin">
					<h4>
						<liferay-ui:message key="label-category" />
					</h4>
					<p id="category"></p>
				</div>
				<div class="col-md-6 col-12 t-b-margin">
					<h4>
						<liferay-ui:message key="label-policy-number" />
					</h4>
					<p id="policyNumber"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-12 t-b-margin">
					<h4>
						<liferay-ui:message key="label-irda-token-number" />
					</h4>
					<p id="irdaTokenNumber"></p>
				</div>
				<div class="col-md-6 col-12 t-b-margin">
					<h4>
						<liferay-ui:message key="label-sr-id" />
					</h4>
					<p id="srNumber"></p>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-12 t-b-margin">
					<h4>
						<liferay-ui:message key="label-status" />
					</h4>
					<p id="status"></p>
				</div>
			</div>
			<div class="center-wealth-btn">
				<button id="backBtn" type="button" class="edto-btn-primary"
					onClick="closeComplaintDetailsDiv();">
					<liferay-ui:message key="label-back" />
				</button>
			</div>
		</div>
	</div>
</div>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="checkStatusBackdrop"
	aria-labelledby="checkStatusBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="statusCloseBtn" class="close" data-dismiss="modal"
						aria-label="Close" onClick="closeCheckStatusModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="statusResponseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="statusOkayBtn" type="button" class="edto-btn-primary"
							onClick="closeCheckStatusModal(false);">
							<liferay-ui:message key="label-done" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>status-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/check-status.js?v=1.1"></script>
<style>

.t-b-margin{
	margin-top: 1rem !important;
	margin-bottom: 1rem !important;
}

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
.check-status-block p{font-size: 12px;
    color: #8e8e8e;
    line-height: 20px;}
    
 .check-status-block h4{
 	font-size: 14px
 }
</style>