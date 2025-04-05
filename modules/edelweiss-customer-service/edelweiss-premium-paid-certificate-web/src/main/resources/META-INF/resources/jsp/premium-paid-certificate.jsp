<%@ include file="../init.jsp"%>

<liferay-portlet:resourceURL
	id="<%=PremiumPaidCertificatePortletKeys.DOWNLOAD_URL_MVC_RESOURCE_COMMAND%>"
	var="downloadURLFromID" copyCurrentRenderParameters="false" />

<%
	Map<String, String> financialYears = new TreeMap<String, String>();
	if(Validator.isNotNull(renderRequest.getAttribute("financialYears"))){
		financialYears = (TreeMap<String, String>) renderRequest.getAttribute("financialYears");
	}

	JSONObject customerJSONObject = JSONFactoryUtil.createJSONObject();
	int status = 0;
	if (Validator.isNotNull((Integer) renderRequest.getAttribute("status"))) {
		status = (Integer) renderRequest.getAttribute("status");
	}

	if (Validator.isNotNull(status) && status == 200) {
		JSONArray customerJSONArray = (JSONArray) renderRequest.getAttribute("customerJSONArray");
		if (Validator.isNotNull(customerJSONArray) && customerJSONArray.length() > 0) {
			for(int i = 0; i < customerJSONArray.length(); i++){
				customerJSONObject = customerJSONArray.getJSONObject(i);
				String policyNumber = customerJSONObject.getString("policyNumber");
				String formName = policyNumber + "-premium-paid-certificate-form";
				String formStep = policyNumber + "_premium_paid_certificate_step";
%>
<liferay-portlet:actionURL var="submitDownloadPremiumPaidCertificateFormURL"
	name="<%=PremiumPaidCertificatePortletKeys.SUBMIT_DOWNLOAD_PREMIUM_PAID_CERTIFICATE_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
<div class="fieldWrap">
	<aui:form name='<%=formName%>'
		action="<%=submitDownloadPremiumPaidCertificateFormURL%>" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="premiumPortletNamespace"
			value="<portlet:namespace/>" />
		<aui:input type="hidden" value="${submitDownloadPremiumPaidCertificateFormURL}"
			name="submitDownloadPremiumPaidCertificateFormURL" />
		<aui:input type="hidden" value="${downloadURLFromID}"
			name="downloadURLFromID" />
		<aui:input type="hidden" name="customerJSONObject"
					value="<%=String.valueOf(customerJSONObject)%>" />
		<div class="application_step ppc-fixd" id='<%=formStep%>'>
			<div class="edto-covid-teat-wrappper pt-3 pb-3">
					<div class="summary-main-wrapper mb-0">
						<div class="summary-title"><%=customerJSONObject.getString("planDescription")%></div>
						<div class="edto-summary-tiles">
							<form>
								<div class="row">
									<div class="col-md-9">
										<div class="row">
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-policy-number" /></p>
													<span> <%=customerJSONObject.getString("policyNumber")%></span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel align-items-start">
													<p><liferay-ui:message key="label-financial-year" /></p>
													<span>
														<aui:select name='<%=policyNumber + "financialYear"%>' label=""
															cssClass="validate" >
															<aui:validator name="required"
																errorMessage='error-label-financial-year' />
															<aui:option selected="true">
																<liferay-ui:message key="label-select" />
															</aui:option>
															<%
																for (Map.Entry<String, String> entry : financialYears.entrySet()) {
															%>
															<aui:option value="<%=entry.getKey()%>"
																label="<%=entry.getValue()%>"></aui:option>
															<%
																}
															%>
														</aui:select>
													</span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-date-of-birth" /></p>
													<span><%=DateFormatterUtil.parseDateToSpecificFormat(customerJSONObject.getString("dob"), DateConstants.SLASH_DD_MM_YYYY)%></span>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-3 align-self-center">
										<aui:button-row cssClass="d-flex justify-content-center">
											<aui:button
												cssClass="edto-btn-primary"
												id='<%=policyNumber + "confirmSubmit"%>' value="label-download"
												onClick='<%="validateDownloadRecieptForm('"+formStep+"', true ,'"+formName+"');" %>' >
											</aui:button>
										</aui:button-row>
									</div>
									
								</div>
							</form>
						</div>
					</div>
					<!--  -->
				 
			</div>

		</div>
	</aui:form>
</div>

<%
			}
		}
	}else {
		customerJSONObject = (JSONObject) renderRequest.getAttribute("customerJSONObject");
	}
%>
<div class="modal wealth-modal fade" tabindex="-1" role="dialog" id="premiumBackdrop"  aria-labelledby="premiumBackdrop" aria-hidden="true">
	<div class="modal-dialog" role="document">
	   <div class="modal-content">
		   <div class="modal-body">
			  <div class="modal-header">
				  <h2></h2>
				 <button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onClick="closePremiumModal(false);"> 
				 <span aria-hidden="true">&times;</span>
				 </button>
			  </div>
			  <div class="wealth-form-box">
				 <span id="modal_error" class="error"></span>
				 <div class="cusscess-icon mb-5">
				   <div class="text-center">
					<h5 id="responseMessage"></h5>
				  </div>
				 </div>
				 <div class="center-wealth-btn">
					<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closePremiumModal(false);" >
						<liferay-ui:message key="label-done" />
					</button>
				 </div>
			  </div>	         
		   </div>
	   </div>
	</div>
 </div>
 
<!-- Image loader -->
<div id='<portlet:namespace/>premium-loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<!-- Image loader -->
<script src="<%=request.getContextPath()%>/js/premium-paid-certificate.js?v=1.1"></script>
<script>
	$(document).ready(
			function() {

				var status = '<%=status%>';
				var customerJSONObject = JSON.parse('<%=customerJSONObject%>');

				if (status != null && status != 200) {
					console.debug("false - API Exception");
					$("#responseMessage").html(customerJSONObject.message);
					openPremiumModal();
					return false;
				} else if (status != null && status == 0) {
					console.debug("false - Internal Error Occured");
					$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
					openPremiumModal();
					return false;
				}
			});
</script>
<style>
	.flabel{align-items: center;}
	.ppc-fixd .edto-covid-teat-wrappper .edto-summary-tiles .flabel .input-select-wrapper select {
		padding: 0;
    	height: 35px;
	}
</style>