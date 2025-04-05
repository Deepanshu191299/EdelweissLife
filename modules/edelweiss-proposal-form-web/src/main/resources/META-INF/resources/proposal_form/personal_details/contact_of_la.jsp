<%@ include file="../../init.jsp"%>

<div id='contact_of_la' class="communication-details">
	<h5><liferay-ui:message key="label-contact-details-for-life-assured" /></h5>
	<div class="medical-test mt-0">
		<div class="radio-wrapper">
			<div class="location-field">
				<div class="row">
					<div class="col-md-4 col-12 my-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_mobileNumber" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text" readOnly="readOnly"
									value="${laCommunicationDetails.mobileNumber}" maxLength="10"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="mobile-number-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-mobile-number" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12 my-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_emailId" placeholder="&nbsp;" label=""
									cssClass="validate" type="text" readOnly="readOnly"
									value="${laCommunicationDetails.emailId}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="email"></aui:validator>	
									<aui:validator name="required" errorMessage="email-error-message"></aui:validator>		
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-email-id" />*</span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>