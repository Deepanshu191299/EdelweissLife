<%@ include file="../../init.jsp"%>

<div id='other_details_of_la' class="communication-details">
<h5><liferay-ui:message key="label-other-details" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<div class="row">
				<div class="col-md-6 col-12">
					<p style="white-space: wrap;">
						<liferay-ui:message
							key="label-are-you-a-politically-exposed-person" />
					</p>
					<div class="radio_container">
						<aui:input name='politicallyExposed' id="politicalYes"
							cssClass="validate" type="radio" label="" required="required"
							onclick="showPDDiv(true, 'politicallyExposed');"
							checked="${laPersonalDetails.politicallyExposedYn == 'Y' ? true : false}"
							value="Y" onChange="validateRadioButtonsOnChange('personal_details_form','politicallyExposed')">
							<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
						</aui:input>
						<label for="<portlet:namespace/>politicalYes"><liferay-ui:message
								key="label-yes" /></label>

						<aui:input name='politicallyExposed' id="politicalNo"
							cssClass="validate" type="radio" label="" required="required"
							onclick="showPDDiv(false, 'politicallyExposed');"
							checked="${laPersonalDetails.politicallyExposedYn == 'N' ? true : false}"
							value="N" onChange="validateRadioButtonsOnChange('personal_details_form','politicallyExposed')">
						</aui:input>
						<label for="<portlet:namespace/>politicalNo"><liferay-ui:message
								key="label-no" /></label>
					</div>
				</div>
				<div class="col-md-6 col-12">
					<div class="location-field">
						<div id="politicallyExposed">
							<div class="form-box">
								<label class="custom-field two"> <aui:input 
										onInput="validateRadioButtonsOnChange('personal_details_form','politicalDetails')"
										name="politicalDetails" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text"
										value="${laPersonalDetails.politicallyExposedSpecify}"
										maxLength="150" minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required"
											errorMessage="other-details-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-if-yes-mention-detailsPersonal" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${isLongForm}">
					<div class="col-md-6 col-12">
						<p>
							<liferay-ui:message
								key="label-are-there-any-conviction-criminal-proceedings-against-you" />
						</p>
						<div class="radio_container">
							<aui:input name='criminalProceedings' id="criminalYes"
							onChange="validateRadioButtonsOnChange('personal_details_form','criminalProceedings')"
								cssClass="validate" type="radio" label="" required="required"
								onclick="showPDDiv(true, 'criminalProceedingsDetail');"
								checked="${laPersonalDetails.criminalProceedingsYn == 'Y' ? true : false}"
								value="Y">
								<aui:validator name="required"
									errorMessage="please-select-one-error-message"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>criminalYes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name='criminalProceedings' id="criminalNo"
							onChange="validateRadioButtonsOnChange('personal_details_form','criminalProceedings')"
								cssClass="validate" type="radio" label="" required="required"
								onclick="showPDDiv(false, 'criminalProceedingsDetail');"
								checked="${laPersonalDetails.criminalProceedingsYn == 'N' ? true : false}"
								value="N">
							</aui:input>
							<label for="<portlet:namespace/>criminalNo"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="location-field">
							<div id="criminalProceedingsDetail">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','criminalDetails')"
											name="criminalDetails" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace" type="text"
											value="${laPersonalDetails.criminalProceedingDetails}"
											maxLength="150" minLength="0" wrappedField="<%=true %>">
											<aui:validator name="required"
												errorMessage="other-details-message"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-if-yes-mention-details" /></span>
									</label>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>

		<!-- Changes related to proposer's EKYC true status to show the div -->
			<div class="location-field">
				<div class="row">
				<c:choose>
				<c:when test="${ekycSuccess_la == 'true' || ekycSuccess_proposer == 'true'}">
				</c:when>
				<c:otherwise>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-identity-proof") %>" title=" " name="identityProof" label=""
									cssClass="validate select2-select custom-select" required="true">
									<aui:option></aui:option>
									<c:forEach var="identityProofData"
										items="${masterMap['Identity Proof']}">
										<c:if test="${identityProofData.value != 75 && identityProofData.value != 2033}">
											<aui:option data-name="${identityProofData.name}"
												value="${identityProofData.value}"
												selected="${laPersonalDetails.photoProofDataId == identityProofData.value ? true : false}"
												label="${identityProofData.name}">
											</aui:option>
										</c:if>
									</c:forEach>
									<aui:validator name="required"
										errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-address-proof") %>" title=" " name="addressProof" label=""
									cssClass="validate select2-select custom-select" required="true">
									<aui:option></aui:option>
									<c:forEach var="addressProofData"
										items="${masterMap['Address Proof']}">
										<c:if test="${addressProofData.value != 80 && addressProofData.value != 81 
										&& addressProofData.value != 1806 && addressProofData.value != 1982 && addressProofData.value != 2034}">
											<aui:option data-name="${addressProofData.name}"
												value="${addressProofData.value}"
												selected="${laPersonalDetails.addressProofDataId == addressProofData.value ? true : false}"
												label="${addressProofData.name}">
											</aui:option>
										</c:if>
									</c:forEach>
									<aui:validator name="required"
										errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
							</div>
						</div>
					</div>
					</c:otherwise>
					</c:choose>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-income-proof") %>" title=" " name="incomeProof" label=""
									cssClass="validate select2-select custom-select" required="true">
									<aui:option></aui:option>
									<c:forEach var="incomeProofData"
										items="${masterMap['Income Proof']}">
										<aui:option data-name="${incomeProofData.name}"
											value="${incomeProofData.value}"
											selected="${laPersonalDetails.incomeProofDataId == incomeProofData.value ? true : false}"
											label="${incomeProofData.name}">
										</aui:option>
									</c:forEach>
									<aui:validator name="required"
										errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	loadPoliticalCriminalFields(portletNamespace);
});
</script>