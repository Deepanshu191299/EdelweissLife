<%@ include file="../../init.jsp"%>

<div id='address_of_la'
	class="communication-details border-bottom-0 mb-0 pb-0">
	<h5><liferay-ui:message key="label-permanent-address-of-life-assured" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<!-- Permanent Address -->
			<div id="la_permanent_address">
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','la_pa_address_line_one')"
										name="la_pa_address_line_one" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','la_pa_address_line_two')"
										name="la_pa_address_line_two" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','la_pa_address_line_three')"
										name="la_pa_address_line_three" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container">
									<aui:select title=" " name="la_pa_state" label=""
										cssClass="validate select2-select"
										data-placeholder="Select State*">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${laCommunicationDetails.paState == stateData.name ? true : false}"
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
								<label class="custom-field two"> <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_pa_city')"
										name="la_pa_city" placeholder="&nbsp;" label=""
										cssClass="validate valpha" type="text" 
										value="${laCommunicationDetails.paCity}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="city-error-meesage"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
					    <div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_pa_pincode')"
										name="la_pa_pincode" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text" 
										value="${laCommunicationDetails.paPincode}" maxLength="6"
										minLength="6" wrappedField="<%=true%>">
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
					<aui:input name='la_isAddressSame' id="la_yes" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(false, 'la_current_address');"
						checked="${(laCommunicationDetails.isCaPaSameYn == 'Y') || 
						(laCommunicationDetails.isCaPaSameYn == null) ||
						(laCommunicationDetails.isCaPaSameYn == '') ? true : false}"
						value="Y" onChange="validateRadioButtonsOnChange('personal_details_form','la_isAddressSame')" >
					<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
					</aui:input>
					<label for="<portlet:namespace/>la_yes"><liferay-ui:message
							key="label-yes" /></label>

					<aui:input name='la_isAddressSame' id="la_no" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(true, 'la_current_address');"
						checked="${laCommunicationDetails.isCaPaSameYn == 'N' ? true : false}"
						value="N" onChange="validateRadioButtonsOnChange('personal_details_form','la_isAddressSame')" >
					</aui:input>
					<label for="<portlet:namespace/>la_no"><liferay-ui:message
							key="label-no" /></label>

				</div>
			</div>

			<!-- Current Address -->
			<div id="la_current_address">
				<h5><liferay-ui:message key="label-current-address-of-life-assured" /></h5>
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','la_ca_address_line_one')"
										name="la_ca_address_line_one" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage" />
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','la_ca_address_line_two')"
										name="la_ca_address_line_two" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage" />
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','la_ca_address_line_three')"
										name="la_ca_address_line_three" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="select-container">
									<aui:select title=" " name="la_ca_state" label="" cssClass=" select2-select"
										data-placeholder="Select State" required="true">
										<aui:option>
										</aui:option>
										<c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}"
												selected="${laCommunicationDetails.caState == stateData.name ? true : false}"
												label="${stateData.name}">
											</aui:option>
										</c:forEach>
										<aui:validator name="required" errorMessage="state-error-meesage" />
									</aui:select>
									<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
								</div>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','la_ca_city')"
										name="la_ca_city" placeholder="&nbsp;" label=""
										cssClass="valpha" type="text" 
										value="${laCommunicationDetails.caCity}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="city-error-meesage" />
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','la_ca_pincode')"
										name="la_ca_pincode" placeholder="&nbsp;" label=""
										cssClass="vnumber" type="text" 
										value="${laCommunicationDetails.caPincode}" maxLength="6"
										minLength="6" wrappedField="<%=true%>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message" />
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>

				<p style="white-space:wrap">
					<liferay-ui:message
						key="label-which-of-the-above-address-is-your-correspondence-address" />
				</p>
				<div class="radio_container">
					<aui:input name='la_correspondingAddress' id="la_permanent"
						cssClass="" type="radio" label="" required="required"
						checked="${laCommunicationDetails.correspondanceAdd == 'P' ? true : false}"
						value="P" onChange="validateRadioButtonsOnChange('personal_details_form','la_correspondingAddress')">
						<aui:validator name="required" errorMessage="this-field-is-required" />
					</aui:input>
					<label for="<portlet:namespace/>la_permanent"><liferay-ui:message
							key="label-permanent" /></label>

					<aui:input name='la_correspondingAddress' id="la_current"
						cssClass="" type="radio" label="" required="required"
						checked="${laCommunicationDetails.correspondanceAdd == 'C' ? true : false}"
						value="C" onChange="validateRadioButtonsOnChange('personal_details_form','la_correspondingAddress')">
					</aui:input>
					<label for="<portlet:namespace/>la_current"><liferay-ui:message
							key="label-current" /></label>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	loadAddressFields(portletNamespace, "la_");
});
</script>