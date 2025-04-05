	<%@ include file="../../init.jsp"%>

<!-- Insurance Reposetory Details -->
<div id="insurance_repository_fields_container" class="medical-test">
	<div class="radio-wrapper">
		<div class="location-field">
			<h4>
				<liferay-ui:message key="label-insurance-repository-details" />
			</h4>
			<p>
				<liferay-ui:message key="label-have-eInsurance-account-number" />
			</p>
			<div class="radio-wrapper">
				<div class="radio_container">
					<aui:input name='la_eia_account_available_yn' id="la_eia_account_available_yes" cssClass="validate hasDependentFields requiredRadio la_radioYes"
						type="radio" label="" required="true" value="Y"
						onClick="showDiv(true, 'eInsuranceAccountNumberWrapper');showDiv(false, 'insuranceRepositoryNameWrapper')" 
						checked="${laPersonalDetails.eiaAccountAvailableYn=='Y'?true:false }">
						
					</aui:input>
					<label for="<portlet:namespace/>la_eia_account_available_yes">
						<liferay-ui:message key="label-yes" />
					</label>
					<aui:input name='la_eia_account_available_yn' id="la_eia_account_available_no" cssClass="validate hasDependentFields requiredRadio la_radioNo"
						type="radio" label="" required="true" value="N"
						onClick="showDiv(false, 'eInsuranceAccountNumberWrapper');showDiv(true, 'insuranceRepositoryNameWrapper')" 
						checked="${laPersonalDetails.eiaAccountAvailableYn=='N'?true:false }" >
					</aui:input>
					<label for="<portlet:namespace/>la_eia_account_available_no">
						<liferay-ui:message key="label-no" />
					</label>
				</div>

				<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_eia_account_available_yn_required" 
					style="display: none;">
					<div class="form-feedback-item form-validator-stack help-block">
						<div role="alert" class="required">
							<liferay-ui:message key="this-field-is-required" />
						</div>
					</div>
				</label>
			</div>
	
			<div class="row">
				<div id="eInsuranceAccountNumberWrapper" class="col-md-6 col-12" style="display: none;">
					<div class="form-box">
						<label class="custom-field two"> 
							<aui:input name="la_eia_account_number"
							onkeyup="validateRadioButtonsOnChange('other_details_form','la_eia_account_number')"
								placeholder="&nbsp;" label="" cssClass="validate valphanumwithspace" type="text"
								value="${laPersonalDetails.eiaAccountNumber}" maxLength="15" minLength="0" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="valid-acc-number-error-message"/>
							</aui:input>
							<span class="placeholder">
								<liferay-ui:message key="label-eInsurance-account-number" />
							</span>
						</label>
					</div>
				</div>
				
				<div id="insuranceRepositoryNameWrapper" class="col-md-6 col-12" style="display: none;">
					<div class="form-box">
						<div class="select-container">
							<aui:select name="la_eia_repository_data_id" label="" cssClass="validate select2-select" title=" " 
									data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-repository-name") %>">
								<aui:option>
									<liferay-ui:message key="label-insurance-repository-name" />
								</aui:option>
								<c:forEach var="insuranceRepository" items="${masterMap['EIA Creation']}">
									<aui:option value="${insuranceRepository.getValue()}"
										label="${insuranceRepository.getName()}" selected="${laPersonalDetails.eiaRepositoryDataId == insuranceRepository.getValue()}">
									</aui:option>
								</c:forEach>
								<aui:validator name="required" errorMessage="label-select-valid-option"/>
							</aui:select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Insurance Reposetory Ends Here -->