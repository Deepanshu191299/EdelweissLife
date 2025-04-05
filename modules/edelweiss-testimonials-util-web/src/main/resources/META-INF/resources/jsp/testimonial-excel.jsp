<%@ include file="../init.jsp"%>
<%
	String acceptedExtensions = StringPool.BLANK;
	if (Validator.isNotNull(renderRequest.getAttribute("documentExtensions"))) {
		acceptedExtensions = (String) renderRequest.getAttribute("documentExtensions");
	}
%>
<liferay-portlet:actionURL var="submitExcelForTestimonialsFormURL"
	name="<%=TestimonialsUtilPortletKeys.SUBMIT_EXCEL_FOR_TESTIMONIALS_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL
	id="<%=TestimonialsUtilPortletKeys.FETCH_TASK_STATUS_MVC_RESOURCE_COMMAND%>"
	var="taskStatusFetchURL" copyCurrentRenderParameters="false" />

<aui:button-row cssClass="multi-btn">
	<aui:button cssClass="edto-btn-primary" id="submitExcel"
		value="label-submit-excel" onClick="toggleDiv('submitExcelDiv')"></aui:button>
	<aui:button cssClass="edto-btn-primary" id="checkStatus"
		value="label-check-status" onClick="toggleDiv('checkStatusDiv')"></aui:button>
</aui:button-row>

<div id="submitExcelDiv">
	<aui:form name="claim-tracking-form"
		action="<%=submitExcelForTestimonialsFormURL%>" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="trackerPortletNamespace"
			value="<portlet:namespace/>" />
		<aui:input type="hidden" value="${submitExcelForTestimonialsFormURL}"
			name="submitExcelForTestimonialsFormURL" />
		<aui:input type="hidden" value="${taskStatusFetchURL}"
			name="taskStatusFetchURL" />
		<div class="application_step"
			id='<portlet:namespace/>claim_tracking_step'>
			<div class="edto-claim-pilosophy-wrapper">
				<div class="form-tiles-main-wrappper mt-0">
					<div class="row">
						<div class="col-md-6 col-12">
							<liferay-ui:message
								key="label-please-select-excel-sheet-for-upload" />
						</div>
						<div class="col-md-6 col-12">
							<label class="custom-field two"> <aui:input
									name="excelSheet" label="" cssClass="validate" type="file"
									value="" multiple="false" accept="<%=acceptedExtensions%>">
									<aui:validator name="acceptFiles">
								'<%=acceptedExtensions%>'
							</aui:validator>
									<aui:validator name="required"
										errorMessage='error-label-file-required' />
								</aui:input>
							</label>
						</div>
						<div class="col-md-6 col-12">
							<liferay-ui:message key="label-please-select-sheet-no" />
						</div>
						<div class="col-md-6 col-12">
							<label class="custom-field two"><aui:select
									name="sheetNo" label="" cssClass="validate">
									<aui:validator name="required"
										errorMessage='error-label-sheet-no' />
									<aui:option value="0" label="1"></aui:option>
									<aui:option value="1" label="2"></aui:option>
									<aui:option value="2" label="3"></aui:option>
									<aui:option value="3" label="4"></aui:option>
									<aui:option value="4" label="5"></aui:option>
								</aui:select> </label>
						</div>
					</div>
					<aui:button-row cssClass="multi-btn">
						<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
							value="label-submit-now"
							onClick="validateExcelForTestimonialForm('claim_tracking_step', true, 'claim-tracking-form');"></aui:button>
					</aui:button-row>
				</div>
			</div>
		</div>
	</aui:form>
</div>

<div id="checkStatusDiv" style="display: none">
	<aui:form name="status-form" action="#" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<div class="row">
			<div class="col-md-6 col-12">
				<liferay-ui:message key="label-please-enter-task-id" />
			</div>
			<div class="col-md-6 col-12">
				<label class="custom-field two"> <aui:input name="taskId"
						label="" placeholder="&nbsp;" wrappedField="<%=true%>"
						cssClass="validate" type="text" value="" maxLength="5"
						minLength="1">
						<aui:validator name="number" />
						<aui:validator name="required" errorMessage='error-label-task-id' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-five">5</aui:validator>
						<aui:validator name="minLength"
							errorMessage="error-label-min-length-one">1</aui:validator>
					</aui:input>
				</label>
			</div>
		</div>
		<div class="row" id="statusUpdate" style="display: none">
			<div class="col-md-6 col-12">
				<liferay-ui:message key="label-current-task-id" />
				: <span id="taskId"></span>
			</div>
			<div class="col-md-6 col-12">
				<liferay-ui:message key="label-current-status" />
				: <span id="taskStatus"></span>
			</div>
		</div>
		<aui:button-row cssClass="multi-btn">
			<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
				value="label-submit-now" onClick="validateTaskIdForm('status-form');"></aui:button>
		</aui:button-row>
	</aui:form>
</div>

<!-- Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="trackerBackdrop" aria-labelledby="trackerBackdropModal"
	aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header h-auto">
					<button type="button" id="trackerCloseBtn"
						class="close close-right" data-dismiss="modal"
						onClick="closeTrackerModal(false);" aria-label="Close">
						<span aria-hidden="true"> <svg
								xmlns="http://www.w3.org/2000/svg" width="15" height="15"
								viewBox="0 0 15 15">
						<path fill="#999" fill-rule="nonzero"
									d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
					</svg>
						</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-4">
						<div class="text-center mb-4">
							<h3 id="successMessage" class="fs18 fontbold  w-100"
								style="display: none">
								<liferay-ui:message
									key="label-your-excel-has-been-submitted-successfully-for-testimonial-bulk-upload" />
								<liferay-ui:message key="label-current-task-id" />
								: <span id="successMessageTaskId"></span>
								<liferay-ui:message key="label-current-status" />
								: <span id="successMessageStatus"></span>
								<liferay-ui:message
									key="label-please-note-task-id-to-check-status-of-testimonial-upload-in-future" />
							</h3>
							<h3 id="trackerResponseMessage" class="fs18 fontbold  w-100"></h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>tracker-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/testimonial-util.js?v=1.1"></script>
<script src="<%=request.getContextPath()%>/js/xlsx-core-min.js?v=1.1"></script>
<script src="<%=request.getContextPath()%>/js/xls-core-min.js?v=1.1"></script>
<script>
var taskId = $("#<portlet:namespace/>"+"taskId");
taskId.bind("keypress", function(e) {
	var taskIdAscii = (e.which) ? e.which : e.keyCode;
		if(taskIdAscii > 31 && (taskIdAscii < 48 || taskIdAscii > 57))
	     return false;
	   	return true;
});
</script>