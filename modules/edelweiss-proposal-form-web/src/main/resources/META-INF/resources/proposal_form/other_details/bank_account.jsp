<%@ include file="../../init.jsp"%>

<!-- Bank Account Details Section -->
<div id="bank_account_fields_container" class="medical-test">
	<input type="hidden" id="getBankDetailsIFSCURL" value="${getBankDetailsIFSCURL}" />
	<div class="radio-wrapper">
		<div class="location-field">
			<div class="row">
				<div class="col-md-6 col-12">
					<div class="input-box">
						<h4>
							<liferay-ui:message key="label-bank-account-details" />
						</h4>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="20" name="la_bank_account_number" onkeyup="validateRadioButtonsOnChange('other_details_form','la_bank_account_number')"
								placeholder="&nbsp;" label="" cssClass="validate vnumber" type="text"
								value="${laPersonalDetails.bankAccountNumber}" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="valid-acc-number-error-message"/>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-bank-account-Number" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="20" name="la_ifsc_code" onInput="validateRadioButtonsOnChange('other_details_form','la_ifsc_code')"
								placeholder="&nbsp;" label="" cssClass="validate valphanum" type="text"
								 data-valid="true"
								value="${laPersonalDetails.ifscCode}" onChange="populateBankDetails(this);" wrappedField="<%=true %>" style="text-transform: uppercase;">
								<aui:validator name="required" errorMessage="please-provide-a-valid-ifsc"/>
								<aui:validator name="custom" errorMessage='please-provide-a-valid-ifsc'>
									function(val) {
										return $("#<portlet:namespace/>la_ifsc_code").data('valid').toString() == "true";
									}
								</aui:validator>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-ifsc-code" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input name="la_account_holder_name" onkeyup="validateRadioButtonsOnChange('other_details_form','la_account_holder_name')"
								placeholder="&nbsp;" label="" cssClass="validate valphawithspace" type="text"
								 maxLength="150"
								value="${laPersonalDetails.accountHolderName}" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-account-holder-name" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="90" name="la_bank_name" onkeyup="validateRadioButtonsOnChange('other_details_form','la_bank_name')"
								placeholder="&nbsp;" label="" cssClass="validate valphawithspace" type="text"
								value="${laPersonalDetails.bankName}" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-bank-name" />*
							</span>
						</label>
					</div>
				</div>
				<div class="col-md-4 col-12">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input maxlength="90" name="la_bank_branch_location" onkeyup="validateRadioButtonsOnChange('other_details_form','la_bank_branch_location')"
								placeholder="&nbsp;" label="" cssClass="validate valphawithspace" type="text"
								value="${laPersonalDetails.bankBranchLocation}" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="other-details-message"/>
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
<!-- Bank Account Details Ends Here -->