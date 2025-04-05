<div id="bank_account_fields_container" class="medical-test">
	<input type="hidden" id="getNomineeBankDetailsIFSCURL" value="${getBankDetailsIFSCURL}" />
	<h5 class="my-4"><liferay-ui:message key="label-bank-account-details" /></h5>
	<div class="radio-wrapper">
		<div class="location-field">
			<div class="row">
				<div class="col-md-6 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="20" name="nominee_bank_account_number" placeholder="&nbsp;"
								label="" cssClass="validate vnumber" type="text"
								value="" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="valid-acc-number-error-message"> 
									function(val){						                                                   
									return !isMinor();
									}
								</aui:validator>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-bank-account-Number" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="20" name="nominee_ifsc_code" placeholder="&nbsp;"
								label="" cssClass="validate valphanum" type="text"
								data-valid="true" value=""
								onChange="populateNomineeBankDetails(this);" wrappedField="<%=true %>" style="text-transform: uppercase;">
								<aui:validator name="required" errorMessage="please-provide-a-valid-ifsc"> 
									function(val){						                                                   
									return !isMinor();
									}
								</aui:validator>
								<aui:validator name="custom" errorMessage='please-provide-a-valid-ifsc'>
									function(val) {
										return $("#<portlet:namespace/>nominee_ifsc_code").data('valid').toString() == "true";
									}
								</aui:validator>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-ifsc-code" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="90" name="nominee_bank_name" placeholder="&nbsp;"
								label="" cssClass="validate valphawithspace" type="text"
								value="" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="other-details-message"> 
									function(val){						                                                   
									return !isMinor();
									}
								</aui:validator>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-bank-name" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="90" name="nominee_bank_branch_location"
								placeholder="&nbsp;" label="" cssClass="validate valphawithspace" type="text"
								value="" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="other-details-message"> 
									function(val){						                                                   
									return !isMinor();
									}
								</aui:validator>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-bank-location" />*
							</span>
						</label>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>

const getNomineeBankDetailsIFSCURL = $("#getNomineeBankDetailsIFSCURL").val();

var nomineeBankNameJEl = "#" + portletNamespace + "nominee_bank_name";
var nomineeBankLocationJEl = "#" + portletNamespace + "nominee_bank_branch_location";
/**
 * Gets Details from IFSC Code
 */
function populateNomineeBankDetails(ifsc){

	showLoader();

	const getAccDetailsURL = getBankDetailsIFSCURL + "?IFSC_CODE="+ifsc.value;

	Liferay.Util.fetch(getAccDetailsURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "GET"
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.info(validationResponse);
		const responseDataJson = validationResponse.responseData;
		console.info(responseDataJson);
		if(validationResponse != null && validationResponse.status){
			$(nomineeBankNameJEl).val(responseDataJson.bank);
			$(nomineeBankNameJEl).keyup();
			$(nomineeBankLocationJEl).val(responseDataJson.bankBranchName);
			$(nomineeBankLocationJEl).keyup();
			$("#"+ifsc.id).data("valid", true);
		}else if(validationResponse && !validationResponse.status){
			$("#responseMessage").html(validationResponse.errors[0]);	
			$("#"+ifsc.id).data("valid", false);	
		}else{
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			$("#"+ifsc.id).data("valid", false);
		}
		hideLoader();
	})

	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#"+ifsc.id).data("valid", false);
		hideLoader();
	});
}
</script>