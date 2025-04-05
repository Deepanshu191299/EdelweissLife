<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitRequestACallbackFormURL"
	name="<%=ContactUsPortletKeys.SUBMIT_REQUEST_CALL_BACK_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<aui:form cssClass="contact-form-tb" name="request-a-callback-form"
	action="<%=submitRequestACallbackFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="callbackPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitRequestACallbackFormURL}"
		name="submitRequestACallbackFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>request_a_callback_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="container">
				<div class="form-tiles-main-wrappper">
					<div>
						<h2 class="fontbold fs28">
							<liferay-ui:message key="label-request-a-callback" />
						</h2>
						<p class="mb-3">
							<liferay-ui:message
								key="label-for-a-scheduled-call-back-please-share" />
						</p>

					</div>
					<div class="row">
						<div class="col-md-6 col-12 form-group">
							<aui:input name="fullName" label="" placeholder="label-full-name"
								wrappedField="<%=true%>" cssClass="validate valpha">
								<aui:validator name="required" errorMessage='error-label-name' />
								<aui:validator name="custom"
									errorMessage="error-label-full-name">
										function(val, fieldNode, ruleValue) {
										var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
										return regex.test(val);
										}
									</aui:validator>
							</aui:input>
							<span>*</span>
						</div>
						<div class="col-md-6 col-12 form-group">
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
						<div class="mt-2 ml-2 text-center text-md-left w-100">
							<aui:button-row>
								<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
									value="label-submit-now"
									onClick="validateRequestACallbackForm('request_a_callback_step', true, 'request-a-callback-form');"></aui:button>
							</aui:button-row>
						</div>
					</div>
				</div>
			</div>
		</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="requestCallbackBackdrop"
	aria-labelledby="requestCallbackBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="callbackCloseBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeRequestCallbackModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="callbackResponseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="callbackOkayBtn" type="button" class="edto-btn-primary"
							onClick="closeRequestCallbackModal(false);">
							<liferay-ui:message key="label-done" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>callback-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/request-callback.js?v=1.1"></script>
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
