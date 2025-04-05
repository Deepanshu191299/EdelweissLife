<%@ include file="../../init.jsp"%>

<div id='address_of_proposer' class="communication-details border-bottom-0 mb-0 pb-0">
	<h5><liferay-ui:message key="label-permanent-address-of-proposer" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<!-- Permanent Address -->
			<div class="form-check largeCheckbox">
				<aui:input cssClass="field form-check-input" placeholder="&nbsp;" label=""
				id="proposer_copyLaAddress" onClick="copyLAAddress(this.id, 'proposer_');"
				name="proposer_copyLaAddress" type="checkbox" wrappedField="<%=true %>"/> 
				<label for="<portlet:namespace/>proposer_copyLaAddress">
					<liferay-ui:message key="label-same-as-life-assured" />
				</label>
			</div>

			<div id="proposer_permanent_address">
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_pa_address_line_one')"
										name="proposer_pa_address_line_one" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.paAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_pa_address_line_two')"
										name="proposer_pa_address_line_two" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.paAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_pa_address_line_three')"
										name="proposer_pa_address_line_three" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.paAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container copy-input-select">
									<aui:select title=" " name="proposer_pa_state" label=""
										cssClass="validate select2-select" data-placeholder="Select State*" required="true">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${proposerCommunicationDetails.paState == stateData.name ? true : false}"
												label="${stateData.name}">
											</aui:option>
										</c:forEach>
										<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
									</aui:select>
									<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
								</div>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="proposer_pa_city"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_pa_city')"
										placeholder="&nbsp;" label="" cssClass="copy-input validate valpha" type="text"
										value="${proposerCommunicationDetails.paCity}" maxLength="50" minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="city-error-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="proposer_pa_pincode"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_pa_pincode')"
										placeholder="&nbsp;" label="" cssClass="copy-input validate vnumber" type="text"
										value="${proposerCommunicationDetails.paPincode}" maxLength="6" minLength="6" wrappedField="<%=true %>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>
		
				<p style="white-space:wrap;">
					<liferay-ui:message
						key="label-is-the-permanent-address-and-communication-address-same" />
				</p>
				<div class="radio_container">
					<aui:input name='proposer_isAddressSame' id="proposer_yes" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(false, 'proposer_current_address');"
						checked="${(proposerCommunicationDetails.isCaPaSameYn == 'Y') || 
						(proposerCommunicationDetails.isCaPaSameYn == null) ||
						(proposerCommunicationDetails.isCaPaSameYn == '') ? true : false}"
						value="Y" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_isAddressSame')">
					</aui:input>
					<label for="<portlet:namespace/>proposer_yes"><liferay-ui:message
							key="label-yes" /></label>
		
					<aui:input name='proposer_isAddressSame' id="proposer_no" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(true, 'proposer_current_address');"
						checked="${proposerCommunicationDetails.isCaPaSameYn == 'N' ? true : false}"
						value="N" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_isAddressSame')">
					</aui:input>
					<label for="<portlet:namespace/>proposer_no"><liferay-ui:message
							key="label-no" /></label>
		
				</div>
			</div>
			
			<!-- Current Address -->
			<div id="proposer_current_address">
				<h5><liferay-ui:message key="label-current-address-of-proposer" /></h5>
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_ca_address_line_one')"
										name="proposer_ca_address_line_one" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.caAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_ca_address_line_two')"
										name="proposer_ca_address_line_two" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.caAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_ca_address_line_three')"
										name="proposer_ca_address_line_three" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${proposerCommunicationDetails.caAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="proposer_ca_pincode"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_ca_pincode')"
										placeholder="&nbsp;" label="" cssClass="copy-input vnumber" type="text"
										value="${proposerCommunicationDetails.caPincode}" maxLength="6" minLength="6" wrappedField="<%=true %>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="proposer_ca_city"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_ca_city')"
										placeholder="&nbsp;" label="" cssClass="copy-input valpha" type="text"
										value="${proposerCommunicationDetails.caCity}" maxLength="50" minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="city-error-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container copy-input-select">
									<aui:select title=" " name="proposer_ca_state" label=""
										cssClass="select2-select" data-placeholder="Select State" required="true">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${proposerCommunicationDetails.caState == stateData.name ? true : false}"
												label="${stateData.name}">
											</aui:option>
										</c:forEach>
										<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
									</aui:select>
									<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		
				<p style="white-space: wrap;">
					<liferay-ui:message
						key="label-which-of-the-above-address-is-your-correspondence-address" />
				</p>
				<div class="radio_container">
					<aui:input name='proposer_correspondingAddress' id="proposer_permanent"
						cssClass="" type="radio" label="" required="required"
						checked="${proposerCommunicationDetails.correspondanceAdd == 'P' ? true : false}"
						value="P" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_correspondingAddress')">
						<aui:validator name="required" errorMessage="please-select-one-error-message" />
					</aui:input>
					<label for="<portlet:namespace/>proposer_permanent"><liferay-ui:message
							key="label-permanent" /></label>
		
					<aui:input name='proposer_correspondingAddress' id="proposer_current"
						cssClass="" type="radio" label="" required="required"
						checked="${proposerCommunicationDetails.correspondanceAdd == 'C' ? true : false}"
						value="C" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_correspondingAddress')">
					</aui:input>
					<label for="<portlet:namespace/>proposer_current"><liferay-ui:message
							key="label-current" /></label>
		
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	loadAddressFields(portletNamespace, "proposer_");
});
</script>