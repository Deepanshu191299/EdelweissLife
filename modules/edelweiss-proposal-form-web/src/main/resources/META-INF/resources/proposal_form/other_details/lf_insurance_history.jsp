<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="../../init.jsp"%>

<style>
.proposer-img{
	width:31px;
	height:29px;
}

.proposerSpouse{
	overflow-wrap:anywhere;
}
</style>
<h5><liferay-ui:message key="label-insurance-history" /></h5>
<!-- LA And Proposer Insurance History Section -->
<div class="covind-inner-wrapper">
	<div class="covid-title-wrapper">
		<div class="row">
			<div class="col-6">
				<span class="title"> <img src="/o/edelweisstokio-theme/images/praposal/insure.png" alt="icon"> Insurer</span>
			</div>
			<c:if test="${isProposerApplicable || isSpouseApplicable}">
				<div class="col-6" style="display:flex">
					<img src="/o/edelweisstokio-theme/images/praposal/proposal.png" alt="icon" class="proposer-img">
					<span class="title proposerSpouse">Proposer/Spouse</span>
				</div>
			</c:if>
			</div> 
		<!-- row ends here -->	
		<div class="row">
			<div class="col-6">
				<label class="toggleSwitch nolabel">
					<input type="checkbox" id="la_otherDetailsNoToAll" name="la_otherDetailsNoToAll">
					<span>
						<span><liferay-ui:message key="label-not-to-all" /></span>
						<span><liferay-ui:message key="label-not-to-all" /></span>
					</span>
					<a></a>
				</label>
			</div>
			<c:if test="${isProposerApplicable || isSpouseApplicable}">
			<div class="col-6">
				<label class="toggleSwitch nolabel">
					<input type="checkbox" id="prsp_otherDetailsNoToAll" name="prsp_otherDetailsNoToAll">
					<span>
						<span><liferay-ui:message key="label-not-to-all" /></span>
						<span><liferay-ui:message key="label-not-to-all" /></span>
					</span>
					<a></a>
				</label>
			</div>
			</c:if>
		</div>
		<!-- add ques here -->
		<p class="p-wrap-class"><liferay-ui:message key="label-app-insurance-available" /></p>
		<div class="row">
			<div class="col-6">
				 <div class="radio_container">
					<aui:input name='la_app_insurance_available_yn' id="la_app_insurance_available_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
						type="radio" label=""  value="Y" 
						checked="${laInsuranceHistoryDetails.getAppInsuranceAvailableYn() == 'Y'}"
						onClick="showDiv(true, 'la_app_insurance_details_Wrapper')">
							<aui:validator name="required" errorMessage="other-details-message"/>
					</aui:input>
					<label for="<portlet:namespace/>la_app_insurance_available_yes">
						<liferay-ui:message key="label-yes" />
					</label>
					<aui:input name='la_app_insurance_available_yn' id="la_app_insurance_available_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
						type="radio" label="" value="N"
						checked="${laInsuranceHistoryDetails.getAppInsuranceAvailableYn() == 'N'}"
						onClick="showDiv(false, 'la_app_insurance_details_Wrapper')">
							<aui:validator name="required" errorMessage="other-details-message"/>
					</aui:input>
					<label for="<portlet:namespace/>la_app_insurance_available_no">
						<liferay-ui:message key="label-no" />
					</label>
				</div> 
				<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_app_insurance_available_yn_required"
							style="display: none;">
							<div class="form-feedback-item form-validator-stack help-block">
								<div role="alert" class="required">
									<liferay-ui:message key="this-field-is-required" />
								</div>
							</div>
						</label>
			</div>
			<c:if test="${isProposerApplicable || isSpouseApplicable}">
				<div class="col-6">
					<div class="radio_container">
						<aui:input name='pr_sp_app_insurance_available_yn' id="pr_sp_app_insurance_available_yes" cssClass="validate prsp_radioYes requiredRadio"
							type="radio" label=""  value="Y" 
							checked="${prSpInsuranceHistoryDetails.getAppInsuranceAvailableYn() == 'Y'}">
								<aui:validator name="required" errorMessage="other-details-message"/>
						</aui:input>
						<label for="<portlet:namespace/>pr_sp_app_insurance_available_yes">
							<liferay-ui:message key="label-yes" />
						</label>
						<aui:input name='pr_sp_app_insurance_available_yn' id="pr_sp_app_insurance_available_no" cssClass="validate prsp_radioNo requiredRadio"
							type="radio" label=""  value="N"
							checked="${prSpInsuranceHistoryDetails.getAppInsuranceAvailableYn() == 'N'}">
								<aui:validator name="required" errorMessage="other-details-message"/>
						</aui:input>
						<label for="<portlet:namespace/>pr_sp_app_insurance_available_no">
							<liferay-ui:message key="label-no" />
						</label>
					</div>
					<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_app_insurance_available_yn_required"
							style="display: none;">
							<div class="form-feedback-item form-validator-stack help-block">
								<div role="alert" class="required">
									<liferay-ui:message key="this-field-is-required" />
								</div>
							</div>
						</label> 
				</div>
			</c:if>
		</div>
		
		
		<div id="insurance_history_fields_container">
			<div id="app_insurance_available_details_fields_wrapper" class="medical-test m-0">
				<div class="radio-wrapper">
					<div class="location-field">
						<div class="row">
							<div class="col-md-6 col-12">
								<div id="la_app_insurance_details_Wrapper" style="display: none;">
								<p class="d-md-none">Life to be Insured</p>
									<div class="form-box">
										<div class="select-container">
											<aui:select title=" " name="la_app_insurance_name" label="" cssClass="validate select2-select"
												data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-company-name") %>">
												<aui:option>
													<liferay-ui:message key="label-insurance-company-name" />
												</aui:option>
												<c:forEach var="companyName" items="${masterMap['Insurance Company']}">
													<aui:option value="${companyName.getName()}"
														selected = "${laInsuranceHistoryDetails.getAppInsuranceName() == companyName.getName()}"
														label="${companyName.getName()}">
													</aui:option>
												</c:forEach>
												<aui:validator name="required" errorMessage="label-select-valid-option"/>
											</aui:select>
										</div>
									</div>
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_app_insurance_reason"
											onInput="validateRadioButtonsOnChange('other_details_form','la_app_insurance_reason')"
												placeholder="&nbsp;" label="" cssClass="validate valphanumwithspace" type="text"
												value= "${laInsuranceHistoryDetails.getAppInsuranceReason()}"
												maxLength="150" minLength="0" wrappedField="<%=true %>">
												<aui:validator name="required"  errorMessage="label-please-provide-valid-reason-error-message"/>
											</aui:input>
											<span class="placeholder">
												<liferay-ui:message key="label-reason" />
											</span>
										</label>
									</div>
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_app_insurance_date"
											onInput="validateRadioButtonsOnChange('other_details_form','la_app_insurance_date')"
												placeholder="&nbsp;" label="" cssClass="validate nominee-tab" type="text"
												value= "${laInsuranceHistoryDetails.getAppInsuranceDate()}"
												wrappedField="<%=true %>">
												<aui:validator name="required"  errorMessage="valid-date-error-message"/>
												<aui:validator name="custom" errorMessage="other-details-message">
											    function (val, fieldNode, ruleValue) {
											        return isValidDateInRangeNominee(val);
											    }
											</aui:validator>
											</aui:input>
											<span class="placeholder">
												<liferay-ui:message key="label-when" />
											</span>
											<!--<p class="pb-0 nominee-date-icon"> <img src="/o/edelweisstokio-theme/images/praposal/date.png" alt="icon"></p>-->
										</label>
									</div>
								</div>
							</div>
							<c:if test="${isProposerApplicable || isSpouseApplicable}">
								<div class="col-6">
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div id="app_dis_ci_benefits_deyails_fields_wrapper" class="medical-test mt-0">
				<div class="radio-wrapper">
					<div class="location-field">
						<p class="p-wrap-class"><liferay-ui:message key="label-have-you-recive-disability-critical-illness-benefits" /></p>
						<div class="row">
							<div class="col-6">
								<div class="radio_container">
									<aui:input name='la_app_dis_ci_benefits_yn' id="la_app_dis_ci_benefits_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
										type="radio" label="" value="Y"
										checked="${laInsuranceHistoryDetails.getAppDisCiBenefitsYn() == 'Y'}"
										onClick="showDiv(true, 'la_app_dis_ci_benefits_details_wrapper')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>la_app_dis_ci_benefits_yes">
										<liferay-ui:message key="label-yes" />
									</label>
									<aui:input name='la_app_dis_ci_benefits_yn' id="la_app_dis_ci_benefits_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
										type="radio" label=""  value="N"
										checked="${laInsuranceHistoryDetails.getAppDisCiBenefitsYn() == 'N'}"
										onClick="showDiv(false, 'la_app_dis_ci_benefits_details_wrapper')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>la_app_dis_ci_benefits_no">
										<liferay-ui:message key="label-no" />
									</label>
								</div> 
								<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_app_dis_ci_benefits_yn_required"
									style="display: none;">
									<div class="form-feedback-item form-validator-stack help-block">
										<div role="alert" class="required">
											<liferay-ui:message key="this-field-is-required" />
										</div>
									</div>
								</label>
							
							</div>
							<c:if test="${isProposerApplicable || isSpouseApplicable}">
								<div class="col-6">
								 	<div class="radio_container">
										<aui:input name='pr_sp_app_dis_ci_benefits_yn' id="pr_sp_app_dis_ci_benefits_yes" cssClass="validate prsp_radioYes requiredRadio"
											type="radio" label=""  value="Y"
											checked="${prSpInsuranceHistoryDetails.getAppDisCiBenefitsYn() == 'Y'}" >
												<aui:validator name="required" errorMessage="other-details-message"/>
										</aui:input>
										<label for="<portlet:namespace/>pr_sp_app_dis_ci_benefits_yes">
											<liferay-ui:message key="label-yes" />
										</label>
										<aui:input name='pr_sp_app_dis_ci_benefits_yn' id="pr_sp_app_dis_ci_benefits_no" cssClass="validate prsp_radioNo requiredRadio"
											type="radio" label=""  value="N"
											checked="${prSpInsuranceHistoryDetails.getAppDisCiBenefitsYn() == 'N'}" >
												<aui:validator name="required" errorMessage="other-details-message"/>
										</aui:input>
										<label for="<portlet:namespace/>pr_sp_app_dis_ci_benefits_no">
											<liferay-ui:message key="label-no" />
										</label>
									</div> 
									<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_app_dis_ci_benefits_yn_required"
										style="display: none;">
										<div class="form-feedback-item form-validator-stack help-block">
											<div role="alert" class="required">
											<liferay-ui:message key="this-field-is-required" />
										</div>
									</div>
								</label>
								</div>
							</c:if>
						</div>
						
						<!-- inputs here -->
						<div class="row">
						
						<div class="col-md-6 col-12">
								<div id="la_app_dis_ci_benefits_details_wrapper" style="display:none;">
								<p class="d-md-none">Life to be Insured</p>
									<div class="form-box">
										<div class="select-container">
											<aui:select title=" " name="la_app_dis_ci_name" label="" cssClass="validate select2-select"
													data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-company-name") %>">
												<aui:option selected="true">
													<liferay-ui:message key="label-insurance-company-name" />
												</aui:option>
												<c:forEach var="companyName" items="${masterMap['Insurance Company']}">
													<aui:option value="${companyName.getName()}"
														selected="${laInsuranceHistoryDetails.getAppDisCiName() == companyName.getName()}"
														label="${companyName.getName()}">
													</aui:option>
												</c:forEach>
												<aui:validator name="required" errorMessage="label-select-valid-option"/>
											</aui:select>
										</div>
									</div>
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_app_dis_ci_reason"
											oninput="validateRadioButtonsOnChange('other_details_form','la_app_dis_ci_reason')"
												placeholder="&nbsp;" label="" cssClass="validate valphanumwithspace" type="text"
												value= "${laInsuranceHistoryDetails.getAppDisCiReason()}"
												maxLength="150" minLength="0" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="label-please-provide-valid-reason-error-message"/>
											</aui:input>
											<span class="placeholder">
												<liferay-ui:message key="label-reason" />
											</span>
										</label>
									</div>
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_app_dis_ci_date"
											oninput="validateRadioButtonsOnChange('other_details_form','la_app_dis_ci_date')"
												placeholder="&nbsp;" label="" cssClass="validate nominee-tab" type="text"
												value= "${laInsuranceHistoryDetails.getAppDisCiDate()}"
												wrappedField="<%=true %>">
												<aui:validator name="required"  errorMessage="valid-date-error-message"/>
												<aui:validator name="custom" errorMessage="Please provide a valid date">
											    function (val, fieldNode, ruleValue) {
											        return isValidDateInRangeNominee(val);
											    }
											</aui:validator>
											</aui:input>
											<span class="placeholder">
												<liferay-ui:message key="label-when" />
											</span>
											<!--<p class="pb-0 nominee-date-icon"> <img src="/o/edelweisstokio-theme/images/praposal/date.png" alt="icon"></p>-->
										</label>
									</div>
								</div>
						</div>
						<div class="col-md-6 col-12">
							
						</div>
						</div>
					</div>
				</div>
			</div>
			<%-- <div class="row">
			<div class="col-6">
					<p class="d-md-none">Life to be Insured</p>
						<div class="radio_container">
							<aui:input name='la_e_policy_yn' id="la_e_policy_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
								type="radio" label="" value="Y"
								checked="${laInsuranceHistoryDetails.getePolicyYn() == 'Y'}"
								onClick="showDiv(true, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_e_policy_yes">
								<liferay-ui:message key="label-yes" />
							</label>
							<aui:input name='la_e_policy_yn' id="la_e_policy_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
								type="radio" label=""  value="N"
								checked="${laInsuranceHistoryDetails.getePolicyYn() == 'N'}"
								onClick="showDiv(false, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_e_policy_no">
								<liferay-ui:message key="label-no" />
							</label>
						</div>
			</div>
			<div class="col-6">
			<p class="d-md-none">Proposer/ Spouse</p>
				<div class="radio_container">
								<aui:input name='pr_sp_e_policy_yn' id="pr_sp_e_policy_yes" cssClass="validate hasDependentFields prsp_radioYes requiredRadio"
									type="radio" label=""  value="Y"
									checked="${prSpInsuranceHistoryDetails.getePolicyYn() == 'Y'}"
									onClick="showDiv(true, 'pr_sp_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_e_policy_yes">
									<liferay-ui:message key="label-yes" />
								</label>
								<aui:input name='pr_sp_e_policy_yn' id="pr_sp_e_policy_no" cssClass="validate hasDependentFields prsp_radioNo requiredRadio"
									type="radio" label=""  value="N"
									checked="${prSpInsuranceHistoryDetails.getePolicyYn() == 'N'}"
									onClick="showDiv(false, 'pr_sp_existing_insurance_history_fields_container')">
										<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_e_policy_no">
									<liferay-ui:message key="label-no" />
								</label>
							</div>
			</div>
			</div> --%>
			<div id="existing_insurance_history_fields_container">
				<div class="row">
					<div class="col-md-12 col-12">
						<p class="text-divide"><liferay-ui:message key="label-existing-insurance-policy-available" /></p>
					</div>
								
					<div class="col-6">
						<div class="radio_container">
							<aui:input name='la_e_policy_yn' id="la_e_policy_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
								type="radio" label="" value="Y"
								checked="${laInsuranceHistoryDetails.getePolicyYn() == 'Y'}"
								onClick="showDiv(true, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_e_policy_yes">
								<liferay-ui:message key="label-yes" />
							</label>
							<aui:input name='la_e_policy_yn' id="la_e_policy_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
								type="radio" label=""  value="N"
								checked="${laInsuranceHistoryDetails.getePolicyYn() == 'N'}"
								onClick="showDiv(false, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_e_policy_no">
								<liferay-ui:message key="label-no" />
							</label>
						</div> 
						<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_e_policy_yn_required"
							style="display: none;">
							<div class="form-feedback-item form-validator-stack help-block">
								<div role="alert" class="required">
									<liferay-ui:message key="this-field-is-required" />
								</div>
							</div>
						</label>
					</div>
					<c:if test="${isProposerApplicable || isSpouseApplicable}">
						<div class="col-6"> 
							 <div class="radio_container">
								<aui:input name='pr_sp_e_policy_yn' id="pr_sp_e_policy_yes" cssClass="validate hasDependentFields prsp_radioYes requiredRadio"
									type="radio" label=""  value="Y"
									checked="${prSpInsuranceHistoryDetails.getePolicyYn() == 'Y'}"
									onClick="showDiv(true, 'pr_sp_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_e_policy_yes">
									<liferay-ui:message key="label-yes" />
								</label>
								<aui:input name='pr_sp_e_policy_yn' id="pr_sp_e_policy_no" cssClass="validate hasDependentFields prsp_radioNo requiredRadio"
									type="radio" label=""  value="N"
									checked="${prSpInsuranceHistoryDetails.getePolicyYn() == 'N'}"
									onClick="showDiv(false, 'pr_sp_existing_insurance_history_fields_container')">
										<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_e_policy_no">
									<liferay-ui:message key="label-no" />
								</label>
							</div> 
							<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_e_policy_yn_required"
							style="display: none;">
							<div class="form-feedback-item form-validator-stack help-block">
								<div role="alert" class="required">
									<liferay-ui:message key="this-field-is-required" />
								</div>
							</div>
						</label>
						</div>
					</c:if>
				</div>
			
				<div id="la_existing_insurance_history_fields_container" style="display:none;">
					<div class="row" id="la_existing_insurance_available">
						<div class="col-md-12 col-12">
							<p class="text-divide"><liferay-ui:message key="label-la-existing-insurance-details-available" /></p>
						</div>
									
						<div class="col-12">
							<div class="radio_container">
								<aui:input name='la_details_ins_policy_available_yn' id="la_details_ins_policy_available_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
									type="radio" label="" value="Y"
									checked="${laInsuranceHistoryDetails.getDetailsInsPolicyAvailableYn() == 'Y'}"
									onClick="showDiv(true, 'la_existing_insurance_history_details_fields_container')">
										<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>la_details_ins_policy_available_yes">
									<liferay-ui:message key="label-yes" />
								</label>
								<aui:input name='la_details_ins_policy_available_yn' id="la_details_ins_policy_available_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
									type="radio" label=""  value="N"
									checked="${laInsuranceHistoryDetails.getDetailsInsPolicyAvailableYn() == 'N'}"
									onClick="showDiv(false, 'la_existing_insurance_history_details_fields_container')">
										<aui:validator name="required"/>
								</aui:input>
								<label for="<portlet:namespace/>la_details_ins_policy_available_no">
									<liferay-ui:message key="label-no" />
								</label>
							</div>
							<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_details_ins_policy_available_yn_required"
								style="display: none;">
								<div class="form-feedback-item form-validator-stack help-block">
								<div role="alert" class="required">
									<liferay-ui:message key="this-field-is-required" />
								</div>
							</div>
						</label>
						</div>
					</div>

					<div id="la_existing_insurance_history_details_fields_container" class="medical-test" style="display:none;">
						<div class="radio-wrapper" id="la_existing_insurance_history_details_fields_wrapper">
							<div class="location-field">
								<div class="row">
									<div class="col-md-4 col-12">
										<div class="input-box">
											<h4>
												<liferay-ui:message key="label-life-assured-insurance-history" />
											</h4>
										</div>
									</div>
								</div>
								<div id="la_existing_insurance_history_details_fields_wrapper_error" class="validateTableError" style="display: none;">
									<p class="text-danger"><liferay-ui:message key="label-provide-details-for-la-existance-insurance-error-message"/></p>
								</div>
								<div class="row">
									<div class="col-md-4 col-12">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="la_insurance_history_policy_number"
													oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');"
													placeholder="&nbsp;" label="" maxLength="20" cssClass="customValidate" type="numeric"
													value="" wrappedField="<%=true %>">
													<aui:validator name="alphanum" errorMessage="valid-policyno-error-message"/>
												</aui:input>
												<span class="placeholder">
													<liferay-ui:message key="label-policy-proposal-no" />
												</span>
											</label>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<div class="select-container">
												<aui:select title=" " name="la_insurance_history_insurance_company_data_id" label="" cssClass="customValidate select2-select"
														data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-company-name") %>">
													<aui:option selected="true">
														<liferay-ui:message key="label-insurance-company-name" />
													</aui:option>
													<c:forEach var="companyName" items="${masterMap['Insurance Company']}">
													<aui:option value="${companyName.getValue()}"
															label="${companyName.getName()}">
														</aui:option>
													</c:forEach>
												<aui:validator name="required" errorMessage="label-select-valid-option"/>
												</aui:select>
											</div>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="la_insurance_history_policy_issue_year"
												onkeyup="validateRadioButtonsOnChange('other_details_form','la_insurance_history_policy_issue_year')"
													placeholder="&nbsp;" label="" maxLength="4"  cssClass="customValidate myInput vnumber" type="text"
													value="" wrappedField="<%=true %>" onInput="validateRadioButtonsOnChange('other_details_form','la_insurance_history_policy_issue_year')">
													<aui:validator name="required" errorMessage="other-details-message"/>
													<aui:validator name="digits" errorMessage="valid-policyno-error-message"/>
												</aui:input>
												<span class="placeholder text-wrap-indotted">
													<liferay-ui:message key="label-insurance-issue-year" />
												</span>
												<div class="mt-1 error-for-year" style="color:#dc3545;font-size:11.2px;"></div>
											</label>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="la_insurance_history_annualised_premium"
												onkeyup="validateRadioButtonsOnChange('other_details_form','la_insurance_history_annualised_premium')"
													oninput="this.value = this.value.replace(/[^0-9]/g, '');"
													placeholder="&nbsp;" label="" cssClass="customValidate" type="text"
													value="" maxLength="10" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message"/>
													<aui:validator name="digits" errorMessage="valid-insurance-prem-error-message"/>
												</aui:input>
												<span class="placeholder">
													<liferay-ui:message key="label-annualized-premium" />
												</span>
											</label>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<div class="select-container">
												<aui:select title=" " name="la_insurance_history_policy_status_data_id" label="" cssClass="customValidate select2-select"
														data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-status") %>">
													<aui:option selected="true">
														<liferay-ui:message key="label-insurance-status" />
													</aui:option>
													<c:forEach var="companyName" items="${masterMap['Policy Status']}">
														<aui:option value="${companyName.getValue()}"
															label="${companyName.getName()}">
														</aui:option>
													</c:forEach>
													<aui:validator name="required" errorMessage="label-select-valid-option"/>
												</aui:select>
											</div>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="la_insurance_history_sum_assured"
												onkeyup="validateRadioButtonsOnChange('other_details_form','la_insurance_history_sum_assured')"
													oninput="this.value = this.value.replace(/[^0-9]/g, '');" maxLength="10"
													placeholder="&nbsp;" label="" cssClass="customValidate" type="text"
													value="" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message"/>
												</aui:input>
												<span class="placeholder">
													<liferay-ui:message key="label-sum-assured" />
												</span>
											</label>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<div class="select-container">
												<aui:select title=" " name="la_insurance_history_acceptance_terms" label="" cssClass="customValidate select2-select"
														data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-acceptance-terms") %>">
													<aui:option selected="true">
														<liferay-ui:message key="label-acceptance-terms" />
													</aui:option>
													<c:forEach var="companyName" items="${masterMap['Acceptance terms']}">
														<aui:option value="${companyName.getValue()}"
															label="${companyName.getName()}">
														</aui:option>
													</c:forEach>
													<aui:validator name="required" errorMessage="label-select-valid-option"/>
												</aui:select>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-12">
										<aui:button value="label-add-details" cssClass="mt-2 edto-btn-primary addNewRecord requiredTableData" id="addLaInsuranceHistory" 
											data-table-id = "la_existing_insurance_history_details_table" 
											data-fieldset-id="la_existing_insurance_history_details_fields_wrapper"/>
										<p class="p-wrap-class"><liferay-ui:message key="label-click-on-add-details-to-save-details" /></p>
									</div>
								</div>
							</div>
						</div>
						<!-- Table Here -->
						<div class="other-details-table-section" id="la_existing_insurance_history_details_table_wrapper">
							<div class="table-title-wrapper mt-4">
								<h5 class="pt-0">Member Details</h5>
								<div class="tbl-srol-btn">
								<button id="slideBack-la-insure1" type="button" disabled=""> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z" fill=""></path>
									</svg>                                                
									</span> </button>
								<button id="slide-la-insure1" type="button"> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z" fill=""></path>
									</svg>
									</span> </button>
								</div>
							</div> 
							<div class="edto-custom-table pb-0" id="other_details_table3">
								
									<table id="la_existing_insurance_history_details_table" class="table table-bordered w-100 other_details_table"
										data-param="la_insurance_history_list" data-index-param="ih_row_id">
										<thead>
											<tr>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_policy_number"
													data-param="policy_number">
													<span class="placeholder"><liferay-ui:message key="label-policy-proposal-no" /></span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_insurance_company_data_id"
													data-param="insurance_company_data_id" data-master="Insurance Company">
													<span class="placeholder"> <liferay-ui:message key="label-insurance-company-name"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_policy_issue_year"
													data-param="policy_issue_year">
													<span class="placeholder"> <liferay-ui:message key="label-year-policy-insurance"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_annualised_premium"
													data-param="annualised_premium">
													<span class="placeholder"> <liferay-ui:message key="label-annualized-premium"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_policy_status_data_id"
													data-param="policy_status_data_id" data-master="Policy Status">
													<span class="placeholder"> <liferay-ui:message key="label-insurance-status"  /> </span>	
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_sum_assured"
													data-param="sum_assured">
													<span class="placeholder"> <liferay-ui:message key="label-sum-assured"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_acceptance_terms"
													data-param="acceptance_terms" data-master="Acceptance terms">
													<span class="placeholder"> <liferay-ui:message key="label-acceptance-terms"  /> </span> 
												</th>
												<th><liferay-ui:message key="label-delete"/></th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
									</table>
								
							</div>
						</div>
					</div>
				</div>
				<c:if test="${isProposerApplicable || isSpouseApplicable}">
					<div id="pr_sp_existing_insurance_history_fields_container" style="display:none;">
						<div class="row" id="proposer_existing_insurance_available">
							<div class="col-md-12 col-12">
								<p class="text-divide"><liferay-ui:message key="label-proposer-existing-insurance-details-available" /></p>
							</div>
										
							<div class="col-md-6 col-12">
								<p class="d-md-none">Proposer/ Spouse</p>
								<div class="radio_container">
									<aui:input name='pr_sp_details_ins_policy_available_yn' id="pr_sp_details_ins_policy_available_yes" cssClass="validate hasDependentFields prsp_radioYes requiredRadio"
										type="radio" label=""  value="Y"
										checked="${prSpInsuranceHistoryDetails.getDetailsInsPolicyAvailableYn() == 'Y'}"
										onClick="showDiv(true, 'pr_sp_existing_insurance_history_details_fields_container')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>pr_sp_details_ins_policy_available_yes">
										<liferay-ui:message key="label-yes" />
									</label>
									<aui:input name='pr_sp_details_ins_policy_available_yn' id="pr_sp_details_ins_policy_available_no" cssClass="validate hasDependentFields prsp_radioNo requiredRadio"
										type="radio" label=""  value="N"
										checked="${prSpInsuranceHistoryDetails.getDetailsInsPolicyAvailableYn() == 'N'}"
										onClick="showDiv(false, 'pr_sp_existing_insurance_history_details_fields_container')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>pr_sp_details_ins_policy_available_no">
										<liferay-ui:message key="label-no" />
									</label>
								</div>
								<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_details_ins_policy_available_yn_required"
									style="display: none;">
									<div class="form-feedback-item form-validator-stack help-block">
										<div role="alert" class="required">
											<liferay-ui:message key="this-field-is-required" />
										</div>
									</div>
								</label>
							</div>
						</div>

						<div id="pr_sp_existing_insurance_history_details_fields_container" class="medical-test" style="display:none;">
							<div class="radio-wrapper" id="pr_sp_existing_insurance_history_details_fields_wrapper">
								<div class="location-field">
									<div class="row">
										<div class="col-md-4 col-12">
											<div class="input-box">
												<h4>
													<c:if test="${isProposerApplicable}">
														<liferay-ui:message key="label-proposer-insurance-history" />
													</c:if>
													
													<c:if test="${isSpouseApplicable}">
														<liferay-ui:message key="label-spouse-insurance-history" />
													</c:if>
												</h4>
											</div>
										</div>
									</div>
									<div id="pr_sp_existing_insurance_history_details_fields_wrapper_error" class="validateTableError" style="display: none;">
										<p class="text-danger"><liferay-ui:message key="label-provide-details-for-la-existance-insurance-error-message"/></p>
									</div>
									<div class="row">
										<div class="col-md-4 col-12">
											<div class="form-box">
												<label class="custom-field two"> 
													<aui:input name="pr_sp_insurance_history_policy_number"
														oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');"
														placeholder="&nbsp;" label="" maxLength="20"  cssClass="customValidate" type="text"
														value="" wrappedField="<%=true %>">
														<aui:validator name="alphanum" errorMessage="valid-policyno-error-message"/>
													</aui:input>
													<span class="placeholder">
														<liferay-ui:message key="label-policy-proposal-no" />
													</span>
												</label>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<div class="select-container">
													<aui:select title=" " name="pr_sp_insurance_history_insurance_company_data_id" label="" cssClass="customValidate select2-select"
															data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-company-name") %>">
														<aui:option selected="true">
															<liferay-ui:message key="label-insurance-company-name" />
														</aui:option>
														<c:forEach var="companyName" items="${masterMap['Insurance Company']}">
															<aui:option value="${companyName.getValue()}"
																label="${companyName.getName()}">
															</aui:option>
														</c:forEach>
														<aui:validator name="required" errorMessage="label-select-valid-option"/>
													</aui:select>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<label class="custom-field two"> 
													<aui:input name="pr_sp_insurance_history_policy_issue_year"
														onkeyup="validateRadioButtonsOnChange('other_details_form','pr_sp_insurance_history_policy_issue_year')"
														placeholder="&nbsp;" label="" maxLength="4"  cssClass="customValidate vnumber" type="text"
														value="" wrappedField="<%=true %>">
														<aui:validator name="required" errorMessage="other-details-message"/>
														<aui:validator name="digits" errorMessage="valid-policyno-error-message"/>
														<aui:validator name="custom" errorMessage="Please provide a valid year">
														function (val, fieldNode, ruleValue) {
															return isValidYear(val);
														}
													</aui:validator>
													</aui:input>
													<span class="placeholder text-wrap-indotted">
															<liferay-ui:message key="label-insurance-issue-year" />
													</span>
													<div class="mt-1 error-for-year" style="color:#dc3545;font-size:11.2px;"></div>
												</label>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<label class="custom-field two"> 
													<aui:input name="pr_sp_insurance_history_annualised_premium"
													onkeyup="validateRadioButtonsOnChange('other_details_form','pr_sp_insurance_history_annualised_premium')"
														oninput="this.value = this.value.replace(/[^0-9]/g, '');"
														placeholder="&nbsp;" label="" cssClass="customValidate" type="text"
														value="" maxLength="10"  wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message"/>
														<aui:validator name="digits" errorMessage="valid-insurance-prem-error-message"/>
													</aui:input>
													<span class="placeholder">
														<liferay-ui:message key="label-annualized-premium" />
													</span>
												</label>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<div class="select-container">
													<aui:select title=" " name="pr_sp_insurance_history_policy_status_data_id" label="" cssClass="customValidate select2-select"
															data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-insurance-status") %>">
														<aui:option selected="true">
															<liferay-ui:message key="label-insurance-status" />
														</aui:option>
														<c:forEach var="companyName" items="${masterMap['Policy Status']}">
															<aui:option value="${companyName.getValue()}"
																label="${companyName.getName()}">
															</aui:option>
														</c:forEach>
													<aui:validator name="required" errorMessage="label-select-valid-option"/>
													</aui:select>
												</div>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<label class="custom-field two"> 
													<aui:input name="pr_sp_insurance_history_sum_assured"
													onkeyup="validateRadioButtonsOnChange('other_details_form','pr_sp_insurance_history_sum_assured')"
														oninput="this.value = this.value.replace(/[^0-9]/g, '');"
														placeholder="&nbsp;" label="" cssClass="customValidate" type="text"
														value="" maxLength="10"  wrappedField="<%=true %>">
														<aui:validator name="required" errorMessage="other-details-message"/>
														<aui:validator name="digits" errorMessage="valid-sumassured-error-message"/>
													</aui:input>
													<span class="placeholder">
														<liferay-ui:message key="label-sum-assured" />
													</span>
												</label>
											</div>
										</div>
										<div class="col-md-4 col-12">
											<div class="form-box">
												<div class="select-container">
													<aui:select title=" " name="pr_sp_insurance_history_acceptance_terms" label="" cssClass="customValidate select2-select"
															data-placeholder="<%=LanguageUtil.get(themeDisplay.getLocale(), "label-acceptance-terms") %>">
														<aui:option selected="true">
															<liferay-ui:message key="label-acceptance-terms" />
														</aui:option>
														<c:forEach var="companyName" items="${masterMap['Acceptance terms']}">
															<aui:option value="${companyName.getValue()}"
																label="${companyName.getName()}">
															</aui:option>
														</c:forEach>
														<aui:validator name="required" errorMessage="label-select-valid-option"/>
													</aui:select>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 col-12">
											<aui:button value="label-add-details" cssClass="mt-2 edto-btn-primary addNewRecord requiredTableData" id="addProposerInsuranceHistory" 
												data-table-id = "pr_sp_existing_insurance_history_details_table" 
												data-fieldset-id="pr_sp_existing_insurance_history_details_fields_wrapper"/>
											<p class="p-wrap-class"><liferay-ui:message key="label-click-on-add-details-to-save-details" /></p>
										</div>
									</div>
								</div>
							</div>
							<div class="other-details-table-section" id="pr_sp_existing_insurance_history_details_table_wrapper">
								<div class="table-title-wrapper mt-4">
									<h5 class="pt-0">Member Details</h5>
									<div class="tbl-srol-btn">
									<button id="slideBack-la-insure2" type="button" disabled=""> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
										<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z" fill=""></path>
										</svg>                                                
										</span> </button>
									<button id="slide-la-insure2" type="button"> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
										<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z" fill=""></path>
										</svg>
										</span> </button>
									</div>
								</div> 
								<!-- Table Here -->
								<div class="edto-custom-table pb-0" id="other_details_table4">
									
										<table id="pr_sp_existing_insurance_history_details_table" class="table table-bordered w-100 other_details_table"
											data-param="pr_sp_insurance_history_list"  data-index-param="ih_row_id">
											<thead>
												<tr>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_policy_number"
														data-param="policy_number">
														<span class="placeholder"><liferay-ui:message key="label-policy-proposal-no" /></span>
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_insurance_company_data_id"
														data-param="insurance_company_data_id" data-master="Insurance Company">
														<span class="placeholder"> <liferay-ui:message key="label-insurance-company-name"  /> </span>
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_policy_issue_year"
														data-param="policy_issue_year">
														<span class="placeholder"> <liferay-ui:message key="label-year-policy-insurance"  /> </span>
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_annualised_premium"
														data-param="annualised_premium">
														<span class="placeholder"> <liferay-ui:message key="label-annualized-premium"  /> </span>
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_policy_status_data_id"
														data-param="policy_status_data_id" data-master="Policy Status">
														<span class="placeholder"> <liferay-ui:message key="label-insurance-status"  /> </span>	
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_sum_assured"
														data-param="sum_assured">
														<span class="placeholder"> <liferay-ui:message key="label-sum-assured"  /> </span>
													</th>
													<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_acceptance_terms"
														data-param="acceptance_terms" data-master="Acceptance terms">
														<span class="placeholder"> <liferay-ui:message key="label-acceptance-terms"  /> </span> 
													</th>
													<th><liferay-ui:message key="label-delete"/></th>
												</tr>
											</thead>
											<tbody>
												
											</tbody>
										</table>
									
								</div>
							</div>
						</div>
					</div>
				</c:if>
				
			</div>
		</div>

		<!-- LA Family Income and Insurance Details -->
		<liferay-util:include page="/proposal_form/other_details/la_family_details.jsp" servletContext="<%=application%>" />

		<!-- LA Family Details -->
		<liferay-util:include page="/proposal_form/other_details/la_family_history_details.jsp" servletContext="<%=application%>" />

		<!-- Proposer/Spouse Family Details -->
		<c:if test="${isProposerApplicable || isSpouseApplicable}">
			<liferay-util:include page="/proposal_form/other_details/pr_sp_family_history_details.jsp" servletContext="<%=application%>" />
		</c:if>
	</div>
</div>
<!-- Insurance History Ends Here -->
<script>
$(document).ready(function () {
	
    // Get the input element by class
    var inputElements = document.getElementsByClassName("myInput");

    // Assuming you have only one input element with this class, you can access it like this
    var inputElement = inputElements[0];

    // Add an event listener to the input element to call validateValue() on input
    inputElement.addEventListener("input", validateValue);

    function validateValue() {
        // Get the value of the input element
        var inputValue = inputElement.value;

        // Get the current year
        var currentYear = new Date().getFullYear();

        // Calculate the range of allowed years (current year to 100 years ago)
        var minYear = currentYear - 100;

        // Parse the input value as an integer
        var inputYear = parseInt(inputValue);

        // Get the error element
        var errorElements = document.getElementsByClassName("error-for-year");
        var errorElement = errorElements[0];

        // Check if the input value is within the allowed range
        if ((isNaN(inputYear) || inputYear < minYear || inputYear > currentYear) && !inputYear == "") {
            // Display an error message
            errorElement.textContent = "Please provide valid details";
            
        } else {
            // Clear the error message
            errorElement.textContent = "";
        }
    }
	
    var containers = document.querySelectorAll('#other_details_table3, #other_details_table4');
    var buttons = document.querySelectorAll('#slide-la-insure1, #slide-la-insure2');
    var backs = document.querySelectorAll('#slideBack-la-insure1, #slideBack-la-insure2');

    // Loop through the NodeList and add event listeners to each button
    buttons.forEach(function (button) {
        button.onclick = function () {
            scrollTable('right', 25, 100, 10);
        };
    });

    // Loop through the NodeList and add event listeners to each back button
    backs.forEach(function (back) {
        back.onclick = function () {
            scrollTable('left', 25, 100, 10);
        };
    });

    containers.forEach(function (container) {
        container.addEventListener('scroll', function () {
            // Check if the table is fully scrolled to the right
            if (container.scrollLeft === container.scrollWidth - container.clientWidth) {
                // Enable or disable the corresponding button
                var buttonIndex = Array.from(containers).indexOf(container);
                buttons[buttonIndex].disabled = true;
            } else {
                var buttonIndex = Array.from(containers).indexOf(container);
                buttons[buttonIndex].disabled = false;
            }

            // Check if the table is fully scrolled to the left
            if (container.scrollLeft === 0) {
                // Enable or disable the corresponding back button
                var buttonIndex = Array.from(containers).indexOf(container);
                backs[buttonIndex].disabled = true;
            } else {
                var buttonIndex = Array.from(containers).indexOf(container);
                backs[buttonIndex].disabled = false;
            }
        });
    });

    function scrollTable(direction, speed, distance, step) {
        var scrollAmount = 0;
        var slideTimer = setInterval(function () {
            containers.forEach(function (container) {
                if (direction === 'left') {
                    container.scrollLeft -= step;
                } else {
                    container.scrollLeft += step;
                }
            });

            scrollAmount += step;
            if (scrollAmount >= distance) {
                clearInterval(slideTimer);
            }
        }, speed);
    }
    
});
</script>
<aui:script>
	function isValidYear(yearString) {
		if (/^\d{4}$/.test(yearString)) {
			const currentYear = new Date().getFullYear();
			const inputYear = parseInt(yearString, 10);

			if (inputYear >= currentYear - 100 && inputYear <= currentYear) {
				return true;
			}
		}
	
		return false;
	}
	
	function isValidDateInRangeNominee(val) {
   // Split the input date into day, month, and year components
   var dateParts = val.split("/");
   if (dateParts.length !== 3) {
       return false; // Date format is not correct
   }

   var day = parseInt(dateParts[0], 10);
   var month = parseInt(dateParts[1], 10);
   var year = parseInt(dateParts[2], 10);

   // Check for valid month and day values
   if (month < 1 || month > 12 || day < 1 || day > 31) {
       return false; // Invalid month or day
   }

   // Ensure the year is at least 1900 and not in the future
   var currentYear = new Date().getFullYear();

   if (year < 1900 || year > currentYear) {
       return false;
   }

   // Adjust two-digit years to the correct century
   if (year < 100) {
       if (year >= 0 && year <= 99) {
           year += (year < currentYear % 100) ? 2000 : 1900;
       } else {
           return false; // Invalid two-digit year
       }
   }

   // Create a new Date object with the parsed components
   var date = new Date(year, month - 1, day); // Subtract 1 from month since it's zero-based

   // Get the current date without time information
   var currentDate = new Date();
   currentDate.setHours(0, 0, 0, 0);

   // Check if the date is a valid date and not equal to the current date
   if (!isNaN(date) && date < currentDate) {
       return true; // Valid date within the range and not equal to the current date
   }

   return false; // Invalid date, out of range, or equal to the current date
}
</aui:script>