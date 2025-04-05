<%@ include file="../../init.jsp"%>

<div id='address_of_spouse' class="communication-details border-bottom-0 mb-0 pb-0">
<h5><liferay-ui:message key="label-permanent-address-of-spouse" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<!-- Permanent Address -->
			<div class="form-check largeCheckbox">
				<aui:input cssClass="field form-check-input" placeholder="&nbsp;" label=""
				id="spouse_copyLaAddress" onClick="copyLAAddress(this.id, 'spouse_');"
				name="spouse_copyLaAddress" type="checkbox" wrappedField="<%=true %>"/> 
				<label for="<portlet:namespace/>spouse_copyLaAddress">
					<liferay-ui:message key="label-same-as-life-assured" />
				</label>
			</div>
			
			<div id="spouse_permanent_address">
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','spouse_pa_address_line_one')"
										name="spouse_pa_address_line_one" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.paAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','spouse_pa_address_line_two')"
										name="spouse_pa_address_line_two" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.paAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','spouse_pa_address_line_three')"
										name="spouse_pa_address_line_three" placeholder="&nbsp;" label=""
										cssClass="copy-input validate valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.paAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container copy-input-select">
									<aui:select title=" " name="spouse_pa_state" label=""
										cssClass="validate select2-select" data-placeholder="Select State" required="true">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${spouseCommunicationDetails.paState == stateData.name ? true : false}"
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
								<label class="custom-field two"> <aui:input name="spouse_pa_city" onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_pa_city')"
										placeholder="&nbsp;" label="" cssClass="copy-input validate valpha" type="text"
										value="${spouseCommunicationDetails.paCity}" maxLength="50" minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="city-error-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="spouse_pa_pincode" onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_pa_pincode')"
										placeholder="&nbsp;" label="" cssClass="copy-input validate vnumber" type="text"
										value="${spouseCommunicationDetails.paPincode}" maxLength="6" minLength="6" wrappedField="<%=true %>">
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
					<aui:input name='spouse_isAddressSame' id="spouse_yes" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(false, 'spouse_current_address');"
						checked="${(spouseCommunicationDetails.isCaPaSameYn == 'Y') || 
						(spouseCommunicationDetails.isCaPaSameYn == null) ||
						(spouseCommunicationDetails.isCaPaSameYn == '') ? true : false}"
						value="Y" onChange="validateRadioButtonsOnChange('personal_details_form','spouse_isAddressSame')" >
					</aui:input>
					<label for="<portlet:namespace/>spouse_yes"><liferay-ui:message
							key="label-yes" /></label>
		
					<aui:input name='spouse_isAddressSame' id="spouse_no" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(true, 'spouse_current_address');"
						checked="${spouseCommunicationDetails.isCaPaSameYn == 'N' ? true : false}"
						value="N" onChange="validateRadioButtonsOnChange('personal_details_form','spouse_isAddressSame')">
					</aui:input>
					<label for="<portlet:namespace/>spouse_no"><liferay-ui:message
							key="label-no" /></label>
		
				</div>
			</div>
			
			<!-- Current Address -->
			<div id="spouse_current_address">
				<h5><liferay-ui:message key="label-current-address-of-spouse" /></h5>
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','spouse_ca_address_line_one')"
										name="spouse_ca_address_line_one" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.caAddressLine1}" maxLength="50"
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
									oninput="validateRadioButtonsOnChange('personal_details_form','spouse_ca_address_line_two')"
										name="spouse_ca_address_line_two" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.caAddressLine2}" maxLength="50"
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
									oninput="validateRadioButtonsOnChange('personal_details_form','spouse_ca_address_line_three')"
										name="spouse_ca_address_line_three" placeholder="&nbsp;" label=""
										cssClass="copy-input valphanumwithspace" type="text" 
										value="${spouseCommunicationDetails.caAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true %>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="spouse_ca_pincode"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_ca_pincode')"
										placeholder="&nbsp;" label="" cssClass="copy-input vnumber" type="text"
										value="${spouseCommunicationDetails.caPincode}" maxLength="6" minLength="6" wrappedField="<%=true %>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input name="spouse_ca_city"
									onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_ca_city')"
										placeholder="&nbsp;" label="" cssClass="copy-input valpha" type="text"
										value="${spouseCommunicationDetails.caCity}" maxLength="50" minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="city-error-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container copy-input-select">
									<aui:select title=" " name="spouse_ca_state" label=""
										cssClass=" select2-select" data-placeholder="Select State*" required="true">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${spouseCommunicationDetails.caState == stateData.name ? true : false}"
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
					<aui:input name='spouse_correspondingAddress' id="spouse_permanent"
						cssClass="" type="radio" label="" required="required"
						checked="${spouseCommunicationDetails.correspondanceAdd == 'P' ? true : false}"
						value="P" onChange="validateRadioButtonsOnChange('personal_details_form','spouse_correspondingAddress')">
						<aui:validator name="required" errorMessage="please-select-one-error-message" />
					</aui:input>
					<label for="<portlet:namespace/>spouse_permanent"><liferay-ui:message
							key="label-permanent" /></label>
		
					<aui:input name='spouse_correspondingAddress' id="spouse_current"
						cssClass="" type="radio" label="" required="required"
						checked="${spouseCommunicationDetails.correspondanceAdd == 'C' ? true : false}"
						value="C" onChange="validateRadioButtonsOnChange('personal_details_form','spouse_correspondingAddress')">
					</aui:input>
					<label for="<portlet:namespace/>spouse_current"><liferay-ui:message
							key="label-current" /></label>
		
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	loadAddressFields(portletNamespace, "spouse_");
});
</script>