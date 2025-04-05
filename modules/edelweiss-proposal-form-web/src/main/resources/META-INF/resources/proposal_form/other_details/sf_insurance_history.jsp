<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ include file="../../init.jsp"%>
<style>
.proposerSpouse{
	overflow-wrap:anywhere;
}


.proposer-img{
	width:31px;
	height:29px;
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
				<div class="col-6" style="display:flex;">
					<img src="/o/edelweisstokio-theme/images/praposal/proposal.png" alt="icon" class="proposer-img">  
					<span class="title proposerSpouse"> Proposer/Spouse</span>
				</div>
			</c:if>
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
		
		<div id="insurance_history_fields_container">
			<div id="ih_sf_rejected_details_fields_wrapper" class="medical-test">
				<div class="radio-wrapper">
					<div class="location-field">
						<p style="white-space:wrap;"><liferay-ui:message key="label-proposal-for-li-hi-rejected" /></p>
						<div class="row">
							<div class="col-6">
								<div class="radio_container">
									<aui:input name='la_ih_sf_rejected_yn' id="la_ih_sf_rejected_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
										type="radio" label=""  value="Y" 
										checked="${laInsuranceHistoryDetails.getIhSfRejectedYn() == 'Y'}"
										onClick="showDiv(true, 'la_ih_sf_rejected_details_Wrapper')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>la_ih_sf_rejected_yes">
										<liferay-ui:message key="label-yes" />
									</label>
									<aui:input name='la_ih_sf_rejected_yn' id="la_ih_sf_rejected_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
										type="radio" label="" value="N"
										checked="${laInsuranceHistoryDetails.getIhSfRejectedYn() == 'N'}"
										onClick="showDiv(false, 'la_ih_sf_rejected_details_Wrapper')">
											<aui:validator name="required" errorMessage="other-details-message"/>
									</aui:input>
									<label for="<portlet:namespace/>la_ih_sf_rejected_no">
										<liferay-ui:message key="label-no" />
									</label>
								</div>
								<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_ih_sf_rejected_yn_required" 
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
										<aui:input name='pr_sp_ih_sf_rejected_yn' id="pr_sp_ih_sf_rejected_yes" cssClass="validate hasDependentFields prsp_radioYes requiredRadio"
											type="radio" label=""  value="Y" 
											checked="${prSpInsuranceHistoryDetails.getIhSfRejectedYn() == 'Y'}"
											onClick="showDiv(true, 'pr_sp_ih_sf_rejected_details_Wrapper')">
												<aui:validator name="required" errorMessage="other-details-message"/>
										</aui:input>
										<label for="<portlet:namespace/>pr_sp_ih_sf_rejected_yes">
											<liferay-ui:message key="label-yes" />
										</label>
										<aui:input name='pr_sp_ih_sf_rejected_yn' id="pr_sp_ih_sf_rejected_no" cssClass="validate hasDependentFields prsp_radioNo requiredRadio"
											type="radio" label=""  value="N"
											checked="${prSpInsuranceHistoryDetails.getIhSfRejectedYn() == 'N'}"
											onClick="showDiv(false, 'pr_sp_ih_sf_rejected_details_Wrapper')">
												<aui:validator name="required" errorMessage="other-details-message"/>
										</aui:input>
										<label for="<portlet:namespace/>pr_sp_ih_sf_rejected_no">
											<liferay-ui:message key="label-no" />
										</label>
									</div>
									<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_ih_sf_rejected_yn_required" 
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
				<!-- add input here -->
				<div class="row">
					<div class="col-md-6 col-12">
						<div id="la_ih_sf_rejected_details_Wrapper" style="display: none;">
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_ih_sf_rejected_reason"
											oninput="validateRadioButtonsOnChange('other_details_form','la_ih_sf_rejected_reason')"
												placeholder="&nbsp;" label="" cssClass="validate valphanumwithspace" type="text"
												value= "${laInsuranceHistoryDetails.getIhSfRejectedReason()}"
												maxLength="150" minLength="0" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="label-please-provide-valid-reason-error-message"/>
											</aui:input>
											<span class="placeholder text-wrap-indotted">
												<liferay-ui:message key="label-reason-lifeassured" />
											</span>
										</label>
									</div>
								</div>
					</div>
					<div class="col-md-6 col-12">
						<div id="pr_sp_ih_sf_rejected_details_Wrapper" style="display: none;">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="pr_sp_ih_sf_rejected_reason"
												oninput="validateRadioButtonsOnChange('other_details_form','pr_sp_ih_sf_rejected_reason')"
													placeholder="&nbsp;" label="" cssClass="validate valphanumwithspace" type="text"
													value= "${prSpInsuranceHistoryDetails.getIhSfRejectedReason()}"
													maxLength="150" minLength="0" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="label-please-provide-valid-reason-error-message"/>
												</aui:input>
												<span class="placeholder text-wrap-indotted">
													<liferay-ui:message key="label-reason-proposer" />
												</span>
											</label>
										</div>
									</div>
					</div>
				</div>
					</div>
				</div>
			</div>
			
			<div id="existing_insurance_history_fields_container">
				<div class="row">
					<div class="col-md-12 col-12">
						<p class="text-divide"><liferay-ui:message key="label-sf-special-rate-policy-available" /></p>
					</div>
								
					<div class="col-6">
						<div class="radio_container">
							<aui:input name='la_ih_sf_special_rate_policy_yn' id="la_sf_special_rate_policy_yes" cssClass="validate hasDependentFields la_radioYes requiredRadio"
								type="radio" label="" value="Y"
								checked="${laInsuranceHistoryDetails.getIhSfSpecialRatePolicyYn() == 'Y'}"
								onClick="showDiv(true, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_sf_special_rate_policy_yes">
								<liferay-ui:message key="label-yes" />
							</label>
							<aui:input name='la_ih_sf_special_rate_policy_yn' id="la_sf_special_rate_policy_no" cssClass="validate hasDependentFields la_radioNo requiredRadio"
								type="radio" label=""  value="N"
								checked="${laInsuranceHistoryDetails.getIhSfSpecialRatePolicyYn() == 'N'}"
								onClick="showDiv(false, 'la_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
							</aui:input>
							<label for="<portlet:namespace/>la_sf_special_rate_policy_no">
								<liferay-ui:message key="label-no" />
							</label>
						</div>
						<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_ih_sf_special_rate_policy_yn_required" 
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
								<aui:input name='pr_sp_ih_sf_special_rate_policy_yn' id="pr_sp_sf_special_rate_policy_yes" cssClass="validate hasDependentFields prsp_radioYes requiredRadio"
									type="radio" label=""  value="Y"
									checked="${prSpInsuranceHistoryDetails.getIhSfSpecialRatePolicyYn() == 'Y'}"
									onClick="showDiv(true, 'pr_sp_existing_insurance_history_fields_container')">
									<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_sf_special_rate_policy_yes">
									<liferay-ui:message key="label-yes" />
								</label>
								<aui:input name='pr_sp_ih_sf_special_rate_policy_yn' id="pr_sp_sf_special_rate_policy_no" cssClass="validate hasDependentFields prsp_radioNo requiredRadio"
									type="radio" label=""  value="N"
									checked="${prSpInsuranceHistoryDetails.getIhSfSpecialRatePolicyYn() == 'N'}"
									onClick="showDiv(false, 'pr_sp_existing_insurance_history_fields_container')">
										<aui:validator name="required" errorMessage="other-details-message"/>
								</aui:input>
								<label for="<portlet:namespace/>pr_sp_sf_special_rate_policy_no">
									<liferay-ui:message key="label-no" />
								</label>
							</div>
							<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>pr_sp_ih_sf_special_rate_policy_yn_required" 
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
			
				<div id="la_existing_insurance_history_fields_container" class="medical-test" style="display: none;">
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
											<aui:input name="la_insurance_history_sum_assured" onkeyup="validateRadioButtonsOnChange('other_details_form','la_insurance_history_sum_assured')"
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
								<div class="col-md-4 col-12">
									<div class="form-box">
										<label class="custom-field two"> 
											<aui:input name="la_insurance_history_reason_for_extra_premium"
											oninput="validateRadioButtonsOnChange('other_details_form','la_insurance_history_reason_for_extra_premium')"
												placeholder="&nbsp;" label="" cssClass="customValidate valphanumwithspace" type="text"
												value="" maxLength="100" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message"/>
											</aui:input>
											<span class="placeholder">
												<liferay-ui:message key="label-extra-premium-reason" />
											</span>
										</label>
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
							<button id="slideBack-lasf1" type="button" disabled=""> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z" fill=""></path>
								</svg>                                                
								</span> </button>
							<button id="slide-lasf1" type="button"> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z" fill=""></path>
								</svg>
								</span> </button>
							</div>
						</div> 
						<div class="edto-custom-table pb-0" id="la_existing_insurance_table_container" >
							
								<table id="la_existing_insurance_history_details_table" class="table table-bordered w-100 other_details_table"
									data-param="la_insurance_history_list" data-index-param="ih_row_id">
									<thead>
										<tr>
											<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_insurance_company_data_id"
												data-param="insurance_company_data_id" data-master="Insurance Company">
												<span class="placeholder"> <liferay-ui:message key="label-insurance-company-name"  /> </span>
											</th>
											<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_sum_assured"
												data-param="sum_assured">
												<span class="placeholder"> <liferay-ui:message key="label-sum-assured"  /> </span>
											</th>
											<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_acceptance_terms"
												data-param="acceptance_terms" data-master="Acceptance terms">
												<span class="placeholder"> <liferay-ui:message key="label-acceptance-terms"  /> </span> 
											</th>
											<th class="userdata" data-input-name="<portlet:namespace/>la_insurance_history_reason_for_extra_premium"
												data-param="reason_for_extra_premium">
												<span class="placeholder"> <liferay-ui:message key="label-extra-premium-reason"  /> </span> 
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

				<c:if test="${isProposerApplicable || isSpouseApplicable}">
					<div id="pr_sp_existing_insurance_history_fields_container" class="medical-test" style="display: none;">
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
								<div class="row">
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
									<div class="col-md-4 col-12">
										<div class="form-box">
											<label class="custom-field two"> 
												<aui:input name="pr_sp_insurance_history_reason_for_extra_premium"
												onInput="validateRadioButtonsOnChange('other_details_form','pr_sp_insurance_history_reason_for_extra_premium')"
													placeholder="&nbsp;" label="" cssClass="customValidate valphanumwithspace" type="text"
													value="" maxLength="100" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message"/>
												</aui:input>
												<span class="placeholder">
													<liferay-ui:message key="label-extra-premium-reason" />
												</span>
											</label>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-4 col-12">
										<aui:button value="label-add-details" cssClass="mt-2 edto-btn-primary addNewRecord" id="addProposerInsuranceHistory" 
											data-table-id = "pr_sp_existing_insurance_history_details_table" 
											data-fieldset-id="pr_sp_existing_insurance_history_details_fields_wrapper"/>
										<p class="p-wrap-class"><liferay-ui:message key="label-click-on-add-details-to-save-details" /></p>
									</div>
								</div>
							</div>
						</div>
						<!-- Table Here -->
						<div class="other-details-table-section" id="pr_sp_existing_insurance_history_details_table_wrapper">
							<div class="table-title-wrapper mt-4">
								<h5 class="pt-0">Member Details</h5>
								<div class="tbl-srol-btn">
								<button id="slideBack-lasf2" type="button" disabled=""> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z" fill=""></path>
									</svg>                                                
									</span> </button>
								<button id="slide-lasf2" type="button"> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z" fill=""></path>
									</svg>
									</span> </button>
								</div>
							</div> 
							<div class="edto-custom-table pb-0" id="pr_sp_table_container">
									<table id="pr_sp_existing_insurance_history_details_table" class="table table-bordered w-100 other_details_table"
										data-param="pr_sp_insurance_history_list"  data-index-param="ih_row_id">
										<thead>
											<tr>
												<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_insurance_company_data_id"
													data-param="insurance_company_data_id" data-master="Insurance Company">
													<span class="placeholder"> <liferay-ui:message key="label-insurance-company-name"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_sum_assured"
													data-param="sum_assured">
													<span class="placeholder"> <liferay-ui:message key="label-sum-assured"  /> </span>
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_acceptance_terms"
													data-param="acceptance_terms" data-master="Acceptance terms">
													<span class="placeholder"> <liferay-ui:message key="label-acceptance-terms"  /> </span> 
												</th>
												<th class="userdata" data-input-name="<portlet:namespace/>pr_sp_insurance_history_reason_for_extra_premium"
													data-param="reason_for_extra_premium">
													<span class="placeholder"> <liferay-ui:message key="label-extra-premium-reason"  /> </span> 
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
				</c:if>
				
			</div>
		</div>

		<!-- LA Family Income and Insurance Details -->
		<liferay-util:include page="/proposal_form/other_details/la_family_details.jsp" servletContext="<%=application%>" />
	</div>
</div>
<!-- Insurance History Ends Here -->
<script>
$(document).ready(function () {
    var containers = document.querySelectorAll('#la_existing_insurance_table_container, #pr_sp_table_container');
    var buttons = document.querySelectorAll('#slide-lasf1, #slide-lasf2');
    var backs = document.querySelectorAll('#slideBack-lasf1, #slideBack-lasf2');

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