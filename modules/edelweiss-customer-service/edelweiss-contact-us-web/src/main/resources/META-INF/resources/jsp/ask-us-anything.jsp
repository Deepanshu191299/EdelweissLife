<%@ include file="../init.jsp"%>
<%
Map<String, String> productRelatedOptions = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("productRelatedOptions"))){
	productRelatedOptions = (TreeMap<String, String>) renderRequest.getAttribute("productRelatedOptions");
}
%>
<liferay-portlet:actionURL var="submitAskUsAnythingFormURL"
	name="<%=ContactUsPortletKeys.SUBMIT_ASK_US_ANYTHING_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<aui:form cssClass="contact-form-tb" name="ask-us-anything-form"
	action="<%=submitAskUsAnythingFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="askusPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitAskUsAnythingFormURL}"
		name="submitAskUsAnythingFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>ask_us_anything_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="container">
				<div class="form-tiles-main-wrappper">
					<div>
				
							<h2 class="fontbold fs28 pb-3">
								<liferay-ui:message key="label-ask-us-anything" />
							</h2>
					
					</div>
					<div class="row">
						<div class="col-md-4 col-12 form-group">
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
						<div class="col-md-4 col-12 form-group">
							<aui:input name="email" label=""
								placeholder="label-email-id" wrappedField="<%=true%>"
								cssClass="validate" type="text">
								<aui:validator name="email" />
								<aui:validator name="required" errorMessage='error-label-email' />
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
						<div class="col-md-6 col-12">
							<div class="form-group1 ">
								<aui:select name="postPurchaseFeedback" label=""
									placeholder="&nbsp;" wrappedField="<%=true %>"
									cssClass="validate">
									<aui:validator name="required"
										errorMessage='error-label-post-purchase-feedback' />
									<%
										for (Map.Entry<String, String> entry : productRelatedOptions.entrySet()) {
									%>
									<aui:option value="<%=entry.getKey()%>"
										label="<%=entry.getValue()%>"></aui:option>
									<%
										}
									%>
								</aui:select>
								<span>*</span>
							</div>
						</div>
						<div class="col-md-6 col-12  form-group">
							<aui:input name="subject" label="" placeholder="label-subject"
								wrappedField="<%=true%>" cssClass="validate valpha" maxLength="50">
								<aui:validator name="required"
									errorMessage='error-label-subject-required' />
								<aui:validator name="maxLength"
									errorMessage="error-label-max-length-fifty">50</aui:validator>
								<aui:validator name="custom" errorMessage="error-label-subject">
										function(val, fieldNode, ruleValue) {
										var regex = new RegExp("^[a-zA-Z\\s]*$");
										return regex.test(val);
										}
									</aui:validator>
							</aui:input>
							<span>*</span>
						</div>
						<div class="col-md-12 col-12 form-group">
							<aui:input name="message" label="" type="textarea" placeholder="label-message"
								wrappedField="<%=true%>" cssClass="validate">
								<aui:validator name="required"
									errorMessage='error-label-message-required' />
							</aui:input>
							<span>*</span>				
						</div>
					<div class="ml-2 mt-2 text-center text-md-left w-100">
						<aui:button-row>
							<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
								value="label-submit-now"
								onClick="validateAskUsAnythingForm('ask_us_anything_step', true, 'ask-us-anything-form');"></aui:button>
						</aui:button-row>
					</div>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="askusAnythingBackdrop" aria-labelledby="askusAnythingBackdropModal"
	aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="askUsCloseBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeAskUsAnythingModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="askUsResponseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
					<button id="askUsOkayBtn" type="button" class="edto-btn-primary" onClick="closeAskUsAnythingModal(false);" >
						<liferay-ui:message key="label-done" />
					</button>
				 </div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>askus-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/ask-us-anything.js?v=1.1"></script>
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
