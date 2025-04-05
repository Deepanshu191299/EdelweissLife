
<%@ include file="../init.jsp"%>

<%-- <liferay-portlet:actionURL var="submitClaimTrackingFormURL"
	name="<%=ClaimTrackingPortletKeys.SUBMIT_CLAIM_TRACKING_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL> --%>

<portlet:resourceURL id="/tracking/submit/claimTracker" var="submitClaimTrackerResourceURL"/>

<div class="fieldWrap">
	
	<aui:form name="claim-tracking-form"
		action="#" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="trackerPortletNamespace"
			value="<portlet:namespace/>" />
		<aui:input type="hidden" value="${submitClaimTrackerResourceURL}"
			name="submitClaimTrackingFormURL" />
		<div class="application_step"
			id='<portlet:namespace/>claim_tracking_step'>
			<div class="edto-document-sub-tabs-wrapper pt-0">
				<div class="container">
					<div class="document-tabs-wrapper">
						<div class="doc-tab-body">
							<div>
								<h2 class="fontbold fs28 text-center pt-0 pb-3">
									<liferay-ui:message key="label-track-your-claim" />
								</h2>
							</div>
							<div class="tracking-form-wrapper p-md-5">
								<aui:input name="trackingNumber"
									placeholder="label-tracking-number" label=""
									cssClass="validate valphanumslash" type="text" value="" maxLength="30"
									minLength="0" style="text-transform: uppercase" >
									<aui:validator name="required"
										errorMessage='error-label-tracking-number' />
									<aui:validator name="maxLength"
										errorMessage="error-label-max-length-thirty">30</aui:validator>
									<aui:validator name="minLength"
										errorMessage="error-label-min-length-zero">0</aui:validator>
								</aui:input>
								<aui:button-row>
									<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
										value="label-submit"
										onClick="validateClaimTrackingForm('claim_tracking_step', true, 'claim-tracking-form');">
									</aui:button>
								</aui:button-row>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</aui:form>
</div>

<c:if test="${themeDisplay.isSignedIn()}">
	<%@ include file="/jsp/upload/claim-data-upload.jsp"%>
</c:if>

<div class="statusDiv" style='display: none;'>
	<div class="edto-document-sub-tabs-wrapper">
		<div class="container">
			<div class="document-tabs-wrapper">
				<div class="doc-tab-body">
					<div>
						<h2 class="fontbold fs28 text-center pt-3 pb-3">
							<liferay-ui:message key="label-claim-status" />
						</h2>
					</div>
					<div class="tracking-form-wrapper d-block">
						<button type="button" class="close close-right" data-dismiss="modal"
							onClick="closeStatusDiv();" aria-label="Close">
							<span aria-hidden="true">
								<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
									<path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
								</svg>
							</span>
						</button>
						<div class="text-center">
							<div class=""><img src="/o/edelweisstokio-theme/images/claim-popup.jpg"/></div>
							<h3 id="responseStatus" class="fs18 fontbold  w-100"></h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog" id="trackerBackdrop"  aria-labelledby="trackerBackdropModal" 
aria-hidden="true" data-backdrop="static" data-keyboard="false">
   <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
	      <div class="modal-body">
	         <div class="modal-header h-auto">
	            <button type="button" id="trackerCloseBtn" class="close close-right" data-dismiss="modal" onClick="closeTrackerModal(false);" aria-label="Close"> 
	            <span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
						<path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
					</svg>
				</span>
	            </button>
	         </div>
	         <div class="wealth-form-box">
	         	<span id="modal_error" class="error"></span>
	            <div class="cusscess-icon mb-4">
	              <div class="text-center mb-4">
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

<%-- <script src="<%=request.getContextPath()%>/js/claim-tracking.js?v=1.1"></script> --%>
<script src="<%=request.getContextPath()%>/js/claim-tracking_v2.js?t=<%= new Date().getTime() %>"></script>
<script>
var claimTrackerPortletNamespace = "<portlet:namespace/>";
</script>
<style>
.tracking-form-wrapper{align-items: baseline !important;}
.tracking-form-wrapper .form-group {margin-bottom:0px;width:100%} 

</style>