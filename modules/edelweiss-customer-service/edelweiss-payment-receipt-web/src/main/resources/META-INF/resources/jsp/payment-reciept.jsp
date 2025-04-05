<%@ include file="../init.jsp"%>

<liferay-portlet:resourceURL
	id="<%=PaymentReceiptPortletKeys.DOWNLOAD_URL_MVC_RESOURCE_COMMAND%>"
	var="downloadURLFromID" copyCurrentRenderParameters="false" />
	
<liferay-portlet:resourceURL
	id="<%=PaymentReceiptPortletKeys.RECEIPT_NUMBERS_MVC_RESOURCE_COMMAND%>"
	var="receiptNumbersFromPolicy" copyCurrentRenderParameters="false" />
	
	<div class="application_step container">
			<div class="edto-covid-teat-wrappper pb-0">
			<h1 class="sum-heading mt-4 mb-3"><liferay-ui:message key="label-download-your-premium-renewal-receipt" /></h1>
	
<%
	String policyStr = StringPool.BLANK;
	if(Validator.isNotNull(renderRequest.getAttribute("policyStr"))){
		policyStr = (String) renderRequest.getAttribute("policyStr");
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
				String formName = policyNumber + "-payment-receipt-form";
				String formStep = policyNumber + "_payment_receipt_step";
%>
<liferay-portlet:actionURL var="submitDownloadPaymentReceiptFormURL"
	name="<%=PaymentReceiptPortletKeys.SUBMIT_DOWNLOAD_PAYMENT_RECIEPT_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
<div class="fieldWrap">
	<aui:form name='<%=formName%>'
		action="<%=submitDownloadPaymentReceiptFormURL%>" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="paymentPortletNamespace"
			value="<portlet:namespace/>" />
		<aui:input type="hidden" value="${submitDownloadPaymentReceiptFormURL}"
			name="submitDownloadPaymentReceiptFormURL" />
		<aui:input type="hidden" value="${downloadURLFromID}"
			name="downloadURLFromID" />
		<aui:input type="hidden" value="${receiptNumbersFromPolicy}"
			name="receiptNumbersFromPolicy" />
		<aui:input type="hidden" name="customerJSONObject"
					value="<%=String.valueOf(customerJSONObject)%>" />
					<div class="mb-4" id='<%=formStep%>'>
					<div class="summary-main-wrapper mb-0">
						<div class="summary-title"><%=customerJSONObject.getString("planDescription")%></div>
						<div class="edto-summary-tiles  select-input-fixed">
							<form>
								<div class="row">
									<div class="col-12 col-lg-9 col-md-12 pr-lg-0">
										<div class="row">
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-policy-number" /></p>
													<span> <%=customerJSONObject.getString("policyNumber")%></span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-policy-holders-dob" /></p>
													<span><%=DateFormatterUtil.parseDateToSpecificFormat(customerJSONObject.getString("dob"), DateConstants.SLASH_DD_MM_YYYY)%></span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-receipt-number" /></p>
													<span>
														<aui:select name='<%=policyNumber + "receiptNumber"%>' label=""
															cssClass="validate" >
															<aui:validator name="required"
																errorMessage='error-label-receipt-number' />
															<aui:option selected="true">
																<liferay-ui:message key="label-select" />
															</aui:option>
														</aui:select>
													</span>
												</div>
											</div>
										</div>
						
									</div>
											<div class="col-12 col-lg-3 pr-lg-0 align-self-center">
												<div class="">
													<aui:button-row>
														<aui:button
															cssClass="edto-btn-primary btn-outline-primary"
															id='<%=policyNumber + "confirmSubmit"%>' value="label-download-receipt"
															onClick='<%="validatePaymentReceiptForm('"+formStep+"', true ,'"+formName+"');" %>' >
															</aui:button>
													</aui:button-row>
												</div>
											</div>
										
								</div>
							</form>
						</div>
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
</div>
</div>
<div class="modal wealth-modal fade" tabindex="-1" role="dialog" id="paymentBackdrop"  aria-labelledby="paymentBackdrop" aria-hidden="true">
	<div class="modal-dialog" role="document">
	   <div class="modal-content">
		   <div class="modal-body">
			  <div class="modal-header">
				  <h2></h2>
				 <button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onClick="closePaymentModal(false);"> 
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
					<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closePaymentModal(false);" >
						<liferay-ui:message key="label-done" />
					</button>
				 </div>
			  </div>	         
		   </div>
	   </div>
	</div>
 </div>
 
<!-- Image loader -->
<div id='<portlet:namespace/>payment-loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<!-- Image loader -->
<script src="<%=request.getContextPath()%>/js/payment-receipt.js?v=1.1"></script>
<script>
	$(document).ready(
			function() {

				var status = '<%=status%>';
				var customerJSONObject = JSON.parse('<%=customerJSONObject%>');

				if (status != null && status != 200) {
					console.debug("false - API Exception");
					$("#responseMessage").html('<liferay-ui:message key="label-policy-not-eligible" />');
					openPaymentModal();
					return false;
				} else if (status != null && status == 0) {
					console.debug("false - Internal Error Occured");
					$("#responseMessage").html('<liferay-ui:message key="label-internal-error-occured" />');
					openPaymentModal();
					return false;
				}
				
				var policyList = '<%=policyStr%>';
				var policyArray = policyList.split(",");
				if(policyArray.length != 0){
					for(var i = 0; i < policyArray.length; i++) {
						getReceiptNumbers(policyArray[i]);
					} 	
				}
			});
	
	function getReceiptNumbers(policyNumber){
		
		var receiptNumbersFormData = new FormData();
		receiptNumbersFormData.append(namespace + "policyNumber", policyNumber);

		Liferay.Util.fetch($("#"+namespace+"receiptNumbersFromPolicy").val(), {
			body: receiptNumbersFormData,
			method: 'POST',
		})

		.then((response) => {
			return response.json();
		})

		.then((validationResponse) => {
			if(validationResponse != null && validationResponse.isReceiptListFetched){
				var receiptList = validationResponse.receiptList;
				for(var i = 0; i < receiptList.length; i++){
					$("#"+ namespace + receiptList[i].policyNumber + "receiptNumber").append(new Option(receiptList[i].rcptNumber, receiptList[i].rcptNumber));
				}
			}
		})
	}
</script>
<style>
	.form-group select{
		height: calc(1.5em + 0.75rem + 2px) !important;
		padding: 0.375rem 0.75rem !important;
	}
	.flabel{align-items: center;}
</style>