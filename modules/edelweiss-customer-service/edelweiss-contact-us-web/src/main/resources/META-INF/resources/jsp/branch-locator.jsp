<%@ include file="../init.jsp"%>
<%
Map<String, String> branchStates = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("branchStates"))){
	branchStates = (TreeMap<String, String>) renderRequest.getAttribute("branchStates");
}
	
	boolean displaySMSIndicator = (boolean)renderRequest.getAttribute("displaySMSIndicator");
%>
<liferay-portlet:actionURL var="submitSMSLocationFormURL"
	name="<%=ContactUsPortletKeys.SUBMIT_SMS_LOCATION_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<liferay-portlet:resourceURL var="populateOptions" copyCurrentRenderParameters="false" />

<aui:input type="hidden" value="${populateOptions}" name="populateOptions" />
<input type="hidden" id="branchPortletNamespace" value="<portlet:namespace/>" />
<div class="edto-claim-pilosophy-wrapper contact-form-tb">
	<div class="container">
		<div class="form-tiles-main-wrappper">
			<div>
				
					<h2 class="fontbold fs28 pb-3">
						<liferay-ui:message key="label-branch-locator" />
					</h2>
				
			</div>
			<div class="row">
				<div class="col-md-4 col-12">
					<div class=" ">
						<aui:select name="state" label="" placeholder="label-state"
							wrappedField="<%=true%>" cssClass="validate"
							onChange="populateOptions(this.value, 'city')">
							<aui:option selected="true">
								<liferay-ui:message key="label-select-state" />
							</aui:option>
							<%
								for (Map.Entry<String, String> entry : branchStates.entrySet()) {
							%>
							<aui:option value="<%=entry.getKey()%>"
								label="<%=entry.getValue()%>"></aui:option>
							<%
								}
							%>
						</aui:select>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class=" ">
						<aui:select name="city" label="" placeholder="label-city"
							wrappedField="<%=true%>" cssClass="validate"
							onChange="populateOptions(this.value, 'area')">
							<aui:option selected="true">
								<liferay-ui:message key="label-select-city" />
							</aui:option>
						</aui:select>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class=" ">
						<aui:select name="area" label="" placeholder="label-area"
							wrappedField="<%=true%>" cssClass="validate"
							onChange="populateBranchDetails(this.value)">
							<aui:option selected="true">
								<liferay-ui:message key="label-select-area" />
							</aui:option>
						</aui:select>
					</div>
				</div>
			</div>
			<div id="addressDiv" class="pb-3" style='display: none;'>
				<span id="address"></span>
			</div>
			<div class="row">
				<div class="col-md-6 col-12 mb-2">
				<svg class="mr-2" xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14">
				<g fill="none" fill-rule="evenodd" transform="translate(1 1)">
        <circle cx="6.015" cy="6.015" r="6.015" fill="#7ED321" stroke="#7ED321" stroke-width="1.157"></circle>
        <path fill="#FFF" d="M4.94 8.592L2.255 5.907l.752-.752L4.94 7.09l4.082-4.082.752.752z"></path> </g></svg>
				<b></b>	<liferay-ui:message key="label-email-id" /></b>
					: <span id="emailId"></span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-12 mb-2">
				<svg class="mr-2" xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14">
				<g fill="none" fill-rule="evenodd" transform="translate(1 1)">
        <circle cx="6.015" cy="6.015" r="6.015" fill="#7ED321" stroke="#7ED321" stroke-width="1.157"></circle>
        <path fill="#FFF" d="M4.94 8.592L2.255 5.907l.752-.752L4.94 7.09l4.082-4.082.752.752z"></path> </g></svg>
					<b><liferay-ui:message key="label-toll-free-no" /></b>
					: <span id="tollFreeNo"></span>
				</div>
			</div>
			<% if(displaySMSIndicator){%>
			<div class="row">
				<div class="col-md-6 col-12 mb-2">
				<svg class="mr-2" xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 14 14">
				<g fill="none" fill-rule="evenodd" transform="translate(1 1)">
        <circle cx="6.015" cy="6.015" r="6.015" fill="#7ED321" stroke="#7ED321" stroke-width="1.157"></circle>
        <path fill="#FFF" d="M4.94 8.592L2.255 5.907l.752-.752L4.94 7.09l4.082-4.082.752.752z"></path> </g></svg>
					<b><liferay-ui:message key="label-sms-indicator" /></b>
					: <span><a onClick="openSMSField();" class="sms-click"><liferay-ui:message
								key="label-sms-me-the-location" /></a></span>
				</div>
			</div>
			
			<!--Mobile Number Field Form open on SMS Indicator Click Button-->
			<div id="smsForm" class="sms-box-wrapper" style='display: none;'>
				<aui:form name="branch-locator-form"
					action="<%=submitSMSLocationFormURL%>" method="post"
					enctype="multipart/form-data" onSubmit="event.preventDefault();">
					<aui:input type="hidden" value="${submitSMSLocationFormURL}"
						name="submitSMSLocationFormURL" />
						
					<aui:input name="email" value="" type="hidden" />
					<aui:input name="address" value="" type="hidden" />
					<aui:input name="contactNo" value="" type="hidden" />
					<aui:input name="mapLink" value="" type="hidden" />
					
					<div class="application_step"
						id='<portlet:namespace/>branch_locator_step'>
						<div id="smsField">
							<div class="">
								<p class="fs12 pb-3"><liferay-ui:message key="label-please-enter-your-mobile-number" /> *</p>
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
							</div>
							<div class="claim-form-btn">
								<aui:button-row>
									<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
										value="label-submit" disabled="true"
										onClick="validateSMSLocationForm('branch_locator_step', true, 'branch-locator-form');"></aui:button>
									<aui:button cssClass="edto-btn-primary" id="cancel"
										value="label-cancel"
										onClick="closeSMSField()"></aui:button>
								</aui:button-row>
							</div>
						</div>
				</aui:form>
			</div>
			
			<%} %>
		</div>
	</div>
</div>


<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="branchLocatorBackdrop"
	aria-labelledby="branchLocatorBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="branchCloseBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeBranchLocatorModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="branchResponseMessage" class="fs22 fontbold w-100"></h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>branch-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/branch-locator.js?v=1.1"></script>
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
 
.sms-click {
	cursor: pointer;
}
</style>
