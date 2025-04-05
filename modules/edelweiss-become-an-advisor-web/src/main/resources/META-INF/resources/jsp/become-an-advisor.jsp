<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitBecomeAnAdvisorFormURL"
	name="<%=BecomeAnAdvisorPortletKeys.SUBMIT_BECOME_AN_ADVISOR_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<%
String advisorLoginURL = (String)renderRequest.getAttribute("advisorLoginURL");
%>

<div class="form-title pb-2">
    <h4 class="fontbold fs18 pb-2"><liferay-ui:message key="label-be-a-part-of-our-advisors-team" /></h4>
    <p><liferay-ui:message key="label-already-an-advisor" /> <a href="<%=advisorLoginURL%>"><liferay-ui:message key="label-click-here" /></a></p>
</div>

<aui:form name="become-an-advisor-form"
	action="<%=submitBecomeAnAdvisorFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="advisorPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitBecomeAnAdvisorFormURL}"
		name="submitBecomeAnAdvisorFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>become_an_advisor_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="form-tiles-main-wrappper mt-0">
				<div class="row">
					<div class="col-md-6 col-12">
						<label class="custom-field two"> <aui:input
								name="firstName" label="" placeholder="&nbsp;"
								wrappedField="<%=true%>" cssClass="validate valpha">
								<aui:validator name="required"
									errorMessage='error-label-first-name' />
							</aui:input> <span class="placeholder"><liferay-ui:message
									key="label-first-name" /></span>
						</label>
					</div>
					<div class="col-md-6 col-12">
						<label class="custom-field two"> <aui:input
								name="lastName" label="" placeholder="&nbsp;"
								wrappedField="<%=true%>" cssClass="validate valpha">
								<aui:validator name="required"
									errorMessage='error-label-last-name' />
							</aui:input> <span class="placeholder"><liferay-ui:message
									key="label-last-name" /></span>
						</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-12">
						<label class="custom-field two"> <aui:input
								name="mobileNo" label="" placeholder="&nbsp;" pattern = "[0-9]*" inputmode="numeric"
								wrappedField="<%=true%>" cssClass="validate vnumber" type="text"
								value="" maxLength="10" minLength="10">
								<aui:validator name="number" />
								<aui:validator name="required"
									errorMessage='error-label-mobile-number' />
								<aui:validator name="maxLength"
									errorMessage="error-label-max-length-ten">10</aui:validator>
								<aui:validator name="minLength"
									errorMessage="error-label-min-length-ten">10</aui:validator>
							</aui:input> <span class="placeholder"><liferay-ui:message
									key="label-mobile-number" /></span>
						</label>
					</div>
					<div class="col-md-6 col-12">
						<label class="custom-field two"> <aui:input name="email"
								label="" placeholder="&nbsp;" wrappedField="<%=true%>"
								cssClass="validate" type="text">
								<aui:validator name="email" />
								<aui:validator name="required" errorMessage='error-label-email' />
							</aui:input> <span class="placeholder"><liferay-ui:message
									key="label-email-id" /></span>
						</label>
					</div>
				</div>
				<p class="fs12 pt-2"><liferay-ui:message
							key="label-by-clicking-submit-now" /></p>
				<div>
					<span> 
					</span>
				</div>
				<div class="mt-2">
					<aui:button-row cssClass="multi-btn">
						<aui:button cssClass="edto-btn-primary"
							id="confirmSubmit" value="label-submit-now"
							onClick="validateBecomeAnAdvisorForm('become_an_advisor_step', true, 'become-an-advisor-form');"></aui:button>
						<aui:button cssClass="edto-btn-primary"
							id="reset" value="label-reset" onClick="resetForm('become_an_advisor_step');"></aui:button>
					</aui:button-row>
				</div>
			</div>
		</div>
	</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="becomeAnAdvisorBackdrop"
	aria-labelledby="becomeAnAdvisorBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeBecomeAnAdvisorModal(false);">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="responseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="okayBtn" type="button" class="edto-btn-primary"
							onClick="closeBecomeAnAdvisorModal(false);">
							<liferay-ui:message key="label-okay" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>advisor-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script
	src="<%=request.getContextPath()%>/js/become-an-advisor.js?v=1.1"></script>
<style>
.td-heading {
	background-color: transparent !important;
	border: 0;
	color: #212529 !important;
	font-weight: bold !important;
}
</style>
