<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="in.edelweiss.common.util.ConfigurationUtil"%>
<%@ include file="/init.jsp"%>

<%
	
	JSONObject policyFormJSONObject = JSONFactoryUtil.createJSONObject();
	if(Validator.isNotNull(renderRequest.getAttribute("policyFormJSONObject"))){
		policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");
		
		if(!policyFormJSONObject.getBoolean("status")){
			HttpServletRequest httpRequest = PortalUtil
					.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
			httpRequest.getSession(false).removeAttribute("policyJSON");
		}
	}

	String documentMasterOptionsURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration()
			.getDocumentMasterOptionsURL();
	String documentFetchURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration().getDocumentFetchURL();
	String saveDocumentURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration().getSaveDocumentURL();
	String deleteDocumentURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration().getDeleteDocumentURL();
	String dcListURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration().getDCListURL();
	String medicalSchedulingURL = ConfigurationUtil.getAppTrackerAPIURLConfiguration().getTPARegisterCaseURL();
	
%>

<script>
 var portletNamespace = "<portlet:namespace/>";
 var applicationNumber = "<%=policyFormJSONObject.getString("application_number")%>"
 var documentMasterOptionsURL = "<%=documentMasterOptionsURL%>";
 var documentFetchURL = "<%=documentFetchURL%>";
 var saveDocumentURL = "<%=saveDocumentURL%>";
 var deleteDocumentURL = "<%=deleteDocumentURL%>";
 var dcListURL = "<%=dcListURL%>";
 var medicalSchedulingURL = "<%=medicalSchedulingURL%>";
</script>

<div class="edto-application-inner-wrapper">
	<liferay-util:include page="/app_tracker/app_tracker_sidebar.jsp"
		servletContext="<%=application%>" />
	<liferay-util:include page="/app_tracker/app_tracker_main.jsp"
		servletContext="<%=application%>" />
</div>

<div class="modal team-modal wealth-modal fade" id="appTrackerBackdrop"
	tabindex="-1" role="dialog" aria-labelledby="appTrackerBackdrop"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<button type="button" id="closeBtn" class="close" 
					data-dismiss="modal" aria-label="Close"
					onClick="closeModal(false);">
						<span aria-hidden="true">
						    <svg
						        xmlns="http://www.w3.org/2000/svg" width="15" height="15"
						        viewBox="0 0 15 15">
						        <path fill="#999" fill-rule="nonzero"
						            d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
						    </svg>
						</span>
					</button>
				</div>
				<div class="wealth-form-box text-center w-100">
					<h4 id="responseMessage" class="text-center mb-3 vsucc-error-msg"></h4>
				</div>
				<div class="text-center">
					<button id="okayBtn" type="button" class="edto-btn-primary"
						onClick="closeModal(false);">
						<liferay-ui:message key="label-done" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal team-modal wealth-modal fade" id="ATModal"
	tabindex="-1" role="dialog" aria-labelledby="lmsTitle"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">
						    <svg
						        xmlns="http://www.w3.org/2000/svg" width="15" height="15"
						        viewBox="0 0 15 15">
						        <path fill="#999" fill-rule="nonzero"
						            d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
						    </svg>
						</span>
					</button>
				</div>
				<div class="mb-2 d-flex justify-content-center align-items-center hide">
					<svg xmlns="http://www.w3.org/2000/svg" width="54" height="54"
					    viewBox="0 0 54 54">
					    <g fill="none" fill-rule="evenodd" transform="translate(1 1)">
					        <circle cx="26" cy="26" r="26" fill="#FFF" stroke="#7ED321"
					            stroke-width="1.6" />
					        <path fill="#7ED321" fill-opacity=".92"
					            d="M20.303 35.103L13.4 28.2a1.98 1.98 0 0 1 2.8-2.8l4.103 4.103a2.4 2.4 0 0 0 3.394 0L35.8 17.4a1.98 1.98 0 0 1 2.8 2.8L23.697 35.103a2.4 2.4 0 0 1-3.394 0z" />
					    </g>
					</svg>
				</div>
				<div class="wealth-form-box text-center w-100">
					<h4 class="text-center mb-3 vsucc-error-msg">No Record Found</h4>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>loader' style='display: none;'>
	<div class="preloader"></div>
</div>


<style>
.app-inner-content-wrapper #pills-contact .app-file-upload-main-wrapper .file-upload-inner-wrapper .file-details .drag-drop-wrapper .file__input--label {
    margin-bottom: 0.875rem;
}

.app-inner-content-wrapper #pills-contact .app-file-upload-main-wrapper .file-upload-inner-wrapper .file-details .drag-drop-wrapper .file__input {
	margin-bottom: 0;
}

#pills-medical .accordion .card .card-body .edto-income-plan-tiles-wrapper form .form-campign-wrapper .location-field .form-box .select-container .error {
	padding-bottom: 10px;
}

.wealth-modal.modal .modal-dialog {
    transform: none;
    margin: auto;
}

.select2-container--open .select2-dropdown--above {
	top: unset;
}

@media screen and (min-width: 991px) {
	.app-sidebar-main-wrapper .filter-main-wrapper {
		min-height: 128px;
	}
}
</style>

<script>
	$(document).ready(function() {

		var status = '<%=policyFormJSONObject.getBoolean("status")%>';
		if (status != null && status.toLowerCase()=="false") {
			$("#responseMessage").html("Customer Not Found");
			openModal();
			return false;
		}
		
		$(".select2-dropClass").select2({
		   	minimumResultsForSearch: Infinity,
		   	placeholder: function(){
		        $(this).data('placeholder');
		    }
		});
		
		$("#document").select2({
            minimumResultsForSearch: Infinity
         });
	});
	
	/**
	 * Show the Loader
	 */
	showLoader = function(){
		$("#"+portletNamespace+"loader").show();
	}

	/**
	 * Hide the Loader
	 */
	hideLoader = function(){
		$("#"+portletNamespace+"loader").hide();
	}

	/**
	 * Open the Modal
	 */
	function openModal(){
		hideLoader();
		$('#appTrackerBackdrop').modal('show');
	}

	/**
	 * Close the Modal
	 */
	function closeModal(redirect){
		if(redirect){
			window.location.href="/";
		}else{
			$('#appTrackerBackdrop').modal('hide');
		}
	}
	
</script>