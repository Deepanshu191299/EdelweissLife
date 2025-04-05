<%@ include file="../../init.jsp"%>

<div id='contact_of_proposer' class="communication-details">
	<h5><liferay-ui:message key="label-contact-details-for-proposer" /></h5>
	<div class="medical-test mt-0">
		<div class="radio-wrapper">
			<div class="location-field">
				<div class="row">
					<div class="col-md-4 col-12 my-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_mobileNumber')"
									name="proposer_mobileNumber" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text"
									value="${proposerCommunicationDetails.mobileNumber}" maxLength="10"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="mobile-number-error-message"></aui:validator>
									<aui:validator name="minLength" errorMessage="label-minimum-ten-error">10</aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-mobile-number" />*</span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12 my-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_emailId')"
									name="proposer_emailId" placeholder="&nbsp;" label=""
									cssClass="validate" type="text"
									value="${proposerCommunicationDetails.emailId}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="email"></aui:validator>	
									<aui:validator name="required" errorMessage="email-error-message"></aui:validator>	
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-email-id" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_residencePhone" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text" 
									value="${proposerCommunicationDetails.phoneNumberHome}" maxLength="10"
									minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-phone-residence" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_officePhone" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text" 
									value="${proposerCommunicationDetails.phoneNumberOffice}" maxLength="10"
									minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-phone-office" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_facebookId" placeholder="&nbsp;" label=""
									cssClass="validate" type="text" 
									value="${proposerCommunicationDetails.facebookId}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-facebook-id" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_linkedInId" placeholder="&nbsp;" label=""
									cssClass="validate" type="text" 
									value="${proposerCommunicationDetails.linkedInId}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-linkedIn-id" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_corporateId')"
									name="proposer_corporateId" placeholder="&nbsp;" label=""
									cssClass="validate" type="text" 
									value="${proposerCommunicationDetails.corporateEmailId != '' && 
									proposerCommunicationDetails.corporateEmailId != null ? 
									proposerCommunicationDetails.corporateEmailId : proposerCommunicationDetails.emailId}" 
									maxLength="30" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="email"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-corporate-id" /></span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
