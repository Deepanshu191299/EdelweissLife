<%@ include file="../../init.jsp"%>

<div class="other-details" id="medical-details-type2">
	<div class="medical-test">
		<div class="radio-wrapper mt-0">
			<div class="location-field">
				<div class="form-header mb-2">
					<div class="left">
						<h5>
							<liferay-ui:message key="medical-details" />
						</h5>
					</div>
					<%-- <div class="right">
						<div class="form-check">
							<aui:input cssClass="form-check-input" name="setallNo2" label="" type="checkbox" value="" id="exampleCheck2" wrappedField="<%=true %>"></aui:input>
							<label class="form-check-label" for="<portlet:namespace/>exampleCheck2">Set all as "No"</label>
						</div>
					</div> --%>
				</div>
				<div class="covid-inner-wrapper mt-4">
					<div class="covid-title-wrapper">
						<div class="row">
							<div class="col-6">
								<span class="title d-block pb-4"> <img
									src="/o/edelweisstokio-theme/images/praposal/insure.png"
									alt="icon"> Insurer
								</span> <label class="toggleSwitch nolabel" onclick=""> <input
									type="checkbox" id="set-all-no"> <span> <span>NO
											TO ALL</span> <span>NO TO ALL</span>
								</span> <a></a>
								</label>
							</div>
							<c:if
								test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
								<div class="col-6">
									<span class="title d-block pb-4"> <img
										src="/o/edelweisstokio-theme/images/praposal/proposal.png"
										alt="icon"> Proposer/Spouse
									</span> <label class="toggleSwitch nolabel" onclick=""> <input
										type="checkbox" id="set-all-no1"> <span> <span>NO
												TO ALL</span> <span>NO TO ALL</span>
									</span> <a></a>
									</label>
								</div>
							</c:if>
						</div>
					</div>
				</div>

				<%-- <div class="row">
					<div class="col-md-6 col-12">
					</div>
					<div class="col-md-3 col-12">
						<div class="input-box">
							<span><liferay-ui:message key="life-to-be-insured" /></span>
						</div>
					</div>
					<div class="col-md-3 col-12">
						<div class="input-box">
							<span><liferay-ui:message key="proposer-spouse" /></span>
						</div>
					</div>
				</div> --%>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<h6>
								1)
								<liferay-ui:message key="label-q-taking-any-medication-yn-short" />
							</h6>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_taking_any_medication_yn"
								type="radio" value="Y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_taking_any_medication_yn')"
								cssClass="setOff setOpen validate la-check" label=""
								id="q_taking_any_medication_y"
								checked="${laLifeStyleDetails.qTakingAnyMedicationYn=='Y'?true:false }">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>q_taking_any_medication_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_taking_any_medication_yn" type="radio"
								value="N" cssClass="setClose validate la-check"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_taking_any_medication_yn')"
								label="" id="q_taking_any_medication_n"
								checked="${laLifeStyleDetails.qTakingAnyMedicationYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>q_taking_any_medication_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_taking_any_medication_yn"
									type="radio" value="Y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_taking_any_medication_yn')"
									cssClass="setOff1 setOpen validate pr-sp-check" label=""
									id="pr_q_taking_any_medication_y"
									checked="${insurerDetails.qTakingAnyMedicationYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_taking_any_medication_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_taking_any_medication_yn" type="radio"
									value="N" cssClass="setClose1 pr-sp-check" label=""
									id="pr_q_taking_any_medication_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_taking_any_medication_yn')"
									checked="${insurerDetails.qTakingAnyMedicationYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_taking_any_medication_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<h6>
								<liferay-ui:message key="label-new-q-thyroid-disorder-goitre-yn" />
							</h6>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_thyroid_disorder_goitre_yn"
								type="radio" value="Y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_thyroid_disorder_goitre_yn')"
								cssClass="setOff setOpen validate la-check" label=""
								id="la_q_thyroid_disorder_goitre_y"
								checked="${laLifeStyleDetails.qThyroidDisorderGoitreYn=='Y'?true:false }">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_thyroid_disorder_goitre_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_thyroid_disorder_goitre_yn" type="radio"
								value="N" cssClass="setClose validate la-check" label=""
								id="la_q_thyroid_disorder_goitre_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_thyroid_disorder_goitre_yn')"
								checked="${laLifeStyleDetails.qThyroidDisorderGoitreYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_q_thyroid_disorder_goitre_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_thyroid_disorder_goitre_yn" type="radio" value="Y"
									cssClass="setOff1 setOpen validate pr-sp-check" label=""
									id="pr_q_thyroid_disorder_goitre_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_thyroid_disorder_goitre_yn')"
									checked="${insurerDetails.qThyroidDisorderGoitreYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_thyroid_disorder_goitre_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_thyroid_disorder_goitre_yn" type="radio"
									value="N" cssClass="setClose1 validate pr-sp-check" label=""
									id="pr_q_thyroid_disorder_goitre_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_thyroid_disorder_goitre_yn')"
									checked="${insurerDetails.qThyroidDisorderGoitreYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_thyroid_disorder_goitre_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<h6>3) Have you ever undergone/ been advised to undergo any
								tests/investigations/surgery/hospitalisation or been diagnosed
								with/treated for/currently receiving a treatment for any of the
								following?</h6>
							<p>
								<liferay-ui:message key="label-new-q-any-disorder-heart-yn" />
							</p>
						</div>
					</div>
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_any_disorder_heart_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate la-check"
								label="" id="la_q_any_disorder_heart_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_disorder_heart_yn')"
								checked="${laLifeStyleDetails.qAnyDisordeHheartYn=='Y'?true:false }">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_any_disorder_heart_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_any_disorder_heart_yn" type="radio"
								value="N" cssClass="setClose la-check" label=""
								id="la_q_any_disorder_heart_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_disorder_heart_yn')"
								checked="${laLifeStyleDetails.qAnyDisordeHheartYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_q_any_disorder_heart_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_any_disorder_heart_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate pr-sp-check"
									label="" id="pr_q_any_disorder_heart_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_disorder_heart_yn')"
									checked="${insurerDetails.qAnyDisordeHheartYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_any_disorder_heart_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_any_disorder_heart_yn" type="radio"
									value="N" cssClass="setClose1 validate pr-sp-check" label=""
									id="pr_q_any_disorder_heart_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_disorder_heart_yn')"
									checked="${insurerDetails.qAnyDisordeHheartYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_any_disorder_heart_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message
									key="label-new-q-nervous-neurological-disorder-yn" />
							</p>
						</div>
					</div>
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true"
								name="la_q_nervous_neurological_disorder_yn" type="radio"
								value="Y" cssClass="setOff setOpen validate la-check" label=""
								id="la_q_nervous_neurological_disorder_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_nervous_neurological_disorder_yn')"
								checked="${laLifeStyleDetails.qNervousNeurologicalDisorderYn=='Y'?true:false }">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label
								for="<portlet:namespace/>la_q_nervous_neurological_disorder_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_nervous_neurological_disorder_yn"
								type="radio" value="N" cssClass="setClose validate la-check" label=""
								id="la_q_nervous_neurological_disorder_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_nervous_neurological_disorder_yn')"
								checked="${laLifeStyleDetails.qNervousNeurologicalDisorderYn=='N'?true:false }"></aui:input>
							<label
								for="<portlet:namespace/>la_q_nervous_neurological_disorder_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<div class="radio_container setAll2">
								<aui:input name="pr_q_nervous_neurological_disorder_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate pr-sp-check"
									label="" id="pr_q_nervous_neurological_disorder_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_nervous_neurological_disorder_yn')"
									checked="${insurerDetails.qNervousNeurologicalDisorderYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_nervous_neurological_disorder_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_nervous_neurological_disorder_yn"
									type="radio" value="N" cssClass="setClose1 validate pr-sp-check" label=""
									id="pr_q_nervous_neurological_disorder_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_nervous_neurological_disorder_yn')"
									checked="${insurerDetails.qNervousNeurologicalDisorderYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_nervous_neurological_disorder_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-new-q-test-hiv-aids-yn" />
							</p>
						</div>
					</div>
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_test_hiv_aids_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate la-check"
								label="" id="la_q_test_hiv_aids_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_test_hiv_aids_yn')"
								checked="${laLifeStyleDetails.qTestHivAidsYn=='Y'?true:false }">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_test_hiv_aids_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_test_hiv_aids_yn" type="radio" value="N"
								cssClass="setClose validate la-check" label="" id="la_q_test_hiv_aids_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_test_hiv_aids_yn')"
								checked="${laLifeStyleDetails.qTestHivAidsYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_q_test_hiv_aids_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_test_hiv_aids_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate pr-sp-check"
									label="" id="pr_q_test_hiv_aids_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_test_hiv_aids_yn')"
									checked="${insurerDetails.qTestHivAidsYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_test_hiv_aids_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_test_hiv_aids_yn" type="radio" value="N"
									cssClass="setClose1 validate pr-sp-check" label=""
									id="pr_q_test_hiv_aids_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_test_hiv_aids_yn')"
									checked="${insurerDetails.qTestHivAidsYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_test_hiv_aids_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<h6>4) For Female Lives:</h6>
						</div>
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-pregnant-yn" />
							</p>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_pregnant_yn" type="radio"
								value="Y" cssClass="setOff setOpen validate la-check" label=""
								disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								id="la_q_pregnant_y"
								checked="${laLifeStyleDetails.qPregnantYn=='Y'?true:false }"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_pregnant_yn')">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_pregnant_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_pregnant_yn" type="radio" value="N"
								cssClass="setClose validate la-check" label=""
								disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								id="la_q_pregnant_n"
								checked="${laLifeStyleDetails.qPregnantYn=='N'?true:false }"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_pregnant_yn')"></aui:input>
							<label for="<portlet:namespace/>la_q_pregnant_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_pregnant_yn" type="radio"
									value="Y" cssClass="setOff1 setOpen validate pr-sp-check" label=""
									disabled="${insurerHealth.genderDataId==4?false:true}"
									id="pr_q_pregnant_y"
									checked="${insurerDetails.qPregnantYn=='Y'?true:false }"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pregnant_yn')">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>

								<label for="<portlet:namespace/>pr_q_pregnant_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_pregnant_yn" type="radio" value="N"
									cssClass="setClose1 validate pr-sp-check" label=""
									disabled="${insurerHealth.genderDataId==4?false:true}"
									id="pr_q_pregnant_n"
									checked="${insurerDetails.qPregnantYn=='N'?true:false }"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pregnant_yn')"></aui:input>
								<label for="<portlet:namespace/>pr_q_pregnant_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>
					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_pregnant" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="pack-wrapper signle-button-section mb-4">
								<p><liferay-ui:message
									key="label-no-of-week-pregnant" /></p>
								<div class="number-wrapaper with-input">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="la_q_pregnant_weeks" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','la_q_pregnant_weeks')"
											cssClass="numberField vnumber range-medical-section" type="text"
											value="${laLifeStyleDetails.qPregnantWeeks==null? 0:laLifeStyleDetails.qPregnantWeeks}"
											maxLength="3" minLength="1" wrappedField="<%=true %>">
											<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var radioValue = $('input[name="<portlet:namespace />la_q_pregnant_yn"]:checked').val();
												if(radioValue == "Y"){
													return true;
												}else{
													return false;
												}
											}
										</aui:validator>
										</aui:input>
										<span class="plus plus-health-range">+</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="hiddenField" id="pr_pregnant" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="pack-wrapper signle-button-section mb-4">
								<p><liferay-ui:message
									key="label-no-of-week-pregnant" /></p>
								<div class="number-wrapaper with-input">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="pr_q_pregnant_weeks" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','pr_q_pregnant_weeks')"
											cssClass="numberField vnumber range-medical-section" type="text"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qPregnantWeeks==null? 0:spouseLifeStyleDetails.qPregnantWeeks) : 
											(ProposerLifeStyleDetails.qPregnantWeeks==null? 0:ProposerLifeStyleDetails.qPregnantWeeks)}"
											maxLength="3" minLength="1" wrappedField="<%=true %>">
											<aui:validator name="required"
											errorMessage="other-details-message">
												function requiedField(val){
													var radioValue = $('input[name="<portlet:namespace />pr_q_pregnant_yn"]:checked').val();
													if(radioValue == "Y"){
														return true;
													}else{
														return false;
													}
												}
											</aui:validator>
										</aui:input>
										<span class="plus plus-health-range">+</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-8 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-disorder-female-organ-yn" />
							</p>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_disorder_female_organ_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate la-check"
								label=""
								disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								id="la_q_disorder_female_organ_y"
								checked="${laLifeStyleDetails.qDisorderFemaleOrganYn=='Y'?true:false }"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_yn')">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_disorder_female_organ_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_disorder_female_organ_yn" type="radio"
								value="N" cssClass="setClose validate la-check" label=""
								disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								id="la_q_disorder_female_organ_n"
								checked="${laLifeStyleDetails.qDisorderFemaleOrganYn=='N'?true:false }"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_yn')"></aui:input>
							<label for="<portlet:namespace/>la_q_disorder_female_organ_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>
					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_disorder_female_organ_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate pr-sp-check"
									label="" disabled="${insurerHealth.genderDataId==4?false:true}"
									id="pr_q_disorder_female_organ_y"
									checked="${insurerDetails.qDisorderFemaleOrganYn=='Y'?true:false }"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_yn')">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_female_organ_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_disorder_female_organ_yn" type="radio"
									value="N" cssClass="setClose1 validate pr-sp-check" label=""
									disabled="${insurerHealth.genderDataId==4?false:true}"
									id="pr_q_disorder_female_organ_n"
									checked="${insurerDetails.qDisorderFemaleOrganYn=='N'?true:false }"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_yn')"></aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_female_organ_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_femaleOrgan" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_details')"
										name="la_q_disorder_female_organ_details" cssClass=""
										maxlength="150" type="text" placeholder="&nbsp;" label=""
										wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDisorderFemaleOrganDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var radioValue = $('input[name="<portlet:namespace />la_q_disorder_female_organ_yn"]:checked').val();
												if(radioValue == "Y"){
													return true;
												}else{
													return false;
												}
											}
										</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-if-yes-mention-details" /></span>
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="hiddenField" id="pr_femaleOrgan" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_details')"
										name="pr_q_disorder_female_organ_details" 
										maxlength="150" type="text" placeholder="&nbsp;" label=""
										wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDisorderFemaleOrganDetails:ProposerLifeStyleDetails.qDisorderFemaleOrganDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
												function requiedField(val){
													var radioValue = $('input[name="<portlet:namespace />pr_q_disorder_female_organ_yn"]:checked').val();
													if(radioValue == "Y"){
														return true;
													}else{
														return false;
													}
												}
											</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-if-yes-mention-details" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>

				<!-- Medical History Section -->
				<div class="row">
					<div class="col-md-12 col-12">
						<div class="input-box">
							<h4>
								<liferay-ui:message
									key="label-medical-history-details-for-life-assured" />
							</h4>
						</div>
						<div class="medical-table-error-la hide">
							<p class="text-danger">Please provide Medical History</p>
						</div>
					</div>

					<div class="col-md-12 col-12">
						<div class="row la_medicalHistoryForm">
							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two mt-0"> <aui:input
											type="text" name="la_diseaseName" wrappedField="<%=true%>"
											placeholder="&nbsp;" label=""></aui:input> <span
										class="placeholder"><liferay-ui:message
												key="label-disease-name" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two mt-0"> <aui:input
											type="text" cssClass="nominee-tab"
											oninput="validateRadioButtonsOnChange('health_details_form','la_diagnosisDate')"
											name="la_diagnosisDate" wrappedField="<%=true%>"
											placeholder="&nbsp;" label="">
											<aui:validator name="custom" errorMessage="Please provide a valid date">
											    function (val, fieldNode, ruleValue) {
											        return isValidDateInRangeNominee(val);
											    }
											</aui:validator>
										</aui:input> <span
										class="placeholder"><liferay-ui:message
												key="label-diagnosis-date" /></span>
										<!--<p class="pb-0 nominee-date-icon">
											<img src="/o/edelweisstokio-theme/images/praposal/date.png"
												alt="icon">
										</p>-->
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two mt-0"> <aui:input
											type="text" name="la_treatmentDetails"
											wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
										<span class="placeholder"><liferay-ui:message
												key="label-treatment-details" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two mt-0"> <aui:input
											type="text" name="la_dosageDetails" wrappedField="<%=true%>"
											placeholder="&nbsp;" label=""></aui:input> <span
										class="placeholder"><liferay-ui:message
												key="label-dosage-details" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input type="text"
											name="la_doctorName" wrappedField="<%=true%>"
											placeholder="&nbsp;" label=""></aui:input> <span
										class="placeholder"><liferay-ui:message
												key="label-doctor-name" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input type="text"
											cssClass="further-test-date" name="la_furtherTestDate"
											oninput="validateRadioButtonsOnChange('health_details_form','la_furtherTestDate')"
											wrappedField="<%=true%>" placeholder="&nbsp;" label="">
											<aui:validator name="custom" errorMessage="Please provide a valid date">
												function (val, fieldNode, ruleValue) {
													return isValidDateInRangeForFurther(val);
												}
											</aui:validator>
										</aui:input>
										<span class="placeholder"><liferay-ui:message
												key="label-further-test-date" /></span>
										<!--<p class="pb-0 nominee-date-icon">
											<img src="/o/edelweisstokio-theme/images/praposal/date.png"
												alt="icon">
										</p>-->
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input type="text"
											name="la_anyComplications" wrappedField="<%=true%>"
											placeholder="&nbsp;" label=""></aui:input> <span
										class="placeholder"><liferay-ui:message
												key="label-any-complications" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-3 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input type="text"
											name="la_additionalRemarks" wrappedField="<%=true%>"
											placeholder="&nbsp;" label=""></aui:input> <span
										class="placeholder"> <liferay-ui:message
												key="label-additional-remarks" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-12 col-12 mb-4 mb-4">
								<aui:button value="label-add-medical-history"
									cssClass="mb-2 edto-btn-primary" id="addLaMedicalHistory" />
								<p>*Please click on Add Medical History Button once you fill
									the information to save the details</p>
							</div>
						</div>
					</div>
				</div>
				<!-- Table Here -->
				<div class="communication-details border-0"
					id="medicalHistoryTable_LA" style="display: none;">
					<div class="covind-inner-wrapper border-0 m-0 pb-0">
						<div class="table-title-wrapper mt-4">
							<h5 class="pt-0">Medical History Details for Life Assured</h5>
							<div class="tbl-srol-btn d-flex">
								<button id="slideBack-medicaltable1" type="button" disabled=""
									class="scroll-button">
									<span><svg width="25" height="25" viewBox="0 0 25 25"
											fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path
												fill-rule="evenodd" clip-rule="evenodd"
												d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z"
												fill=""></path>
                                                </svg> </span>
								</button>
								<button id="slide-medicaltable1" type="button"
									class="scroll-button">
									<span><svg width="25" height="25" viewBox="0 0 25 25"
											fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path
												fill-rule="evenodd" clip-rule="evenodd"
												d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z"
												fill=""></path>
                                                </svg> </span>
								</button>
							</div>
						</div>
						<div class="edto-custom-table pb-0" style="overflow: scroll;"
							id="mediacal_history_table_LA">

							<table class="table table-bordered w-100"
								style="white-space: nowrap" id='la_table_data'>
								<thead>
									<tr>
										<th>Disease Name</th>
										<th>Diagnosis Date</th>
										<th>Treatment Details</th>
										<th>Dosage Details</th>
										<th>Doctor Name</th>
										<th>Further Test Date</th>
										<th>Any Complications Name</th>
										<th>Additional Remarks</th>
										<th>Delete</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>

						</div>
					</div>
				</div>
				<c:if
					test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
					<div class="row">
						<div class="col-md-12 col-12">
							<div class="input-box">
								<c:if test="${commonDetails.isLaPrSameYn == 'N'}">
									<h4>
										<liferay-ui:message
											key="label-medical-history-details-for-proposer" />
									</h4>
								</c:if>
								<c:if test="${commonDetails.spouseExistYn == 'Y'}">
									<h4>
										<liferay-ui:message
											key="label-medical-history-details-for-spouse" />
									</h4>
								</c:if>
							</div>
							<div class="medical-table-error-pr hide">
								<p class="text-danger">Please provide Medical History</p>
							</div>
						</div>
						<div class="col-md-12 col-12">
							<div class="row pr_medicalHistoryForm">
								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two mt-0"> <aui:input
												type="text" name="proposer_diseaseName"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-disease-name" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two mt-0"> <aui:input
												type="text" cssClass="nominee-tab"
												oninput="validateRadioButtonsOnChange('health_details_form','proposer_diagnosisDate')	"
												name="proposer_diagnosisDate" wrappedField="<%=true%>"
												placeholder="&nbsp;" label="">
												<aui:validator name="custom" errorMessage="Please provide a valid date">
													function (val, fieldNode, ruleValue) {
														return isValidDateInRangeNominee(val);
													}
												</aui:validator>
											</aui:input> <span
											class="placeholder"><liferay-ui:message
													key="label-diagnosis-date" /></span>
											<!--<p class="pb-0 nominee-date-icon">
												<img src="/o/edelweisstokio-theme/images/praposal/date.png"
													alt="icon">
											</p>-->
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two mt-0"> <aui:input
												type="text" name="proposer_treatmentDetails"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-treatment-details" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two mt-0"> <aui:input
												type="text" name="proposer_dosageDetails"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-dosage-details" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two"> <aui:input
												type="text" name="proposer_doctorName"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-doctor-name" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two"> <aui:input
												type="text" cssClass="further-test-date"
												oninput="validateRadioButtonsOnChange('health_details_form','proposer_furtherTestDate')"
												name="proposer_furtherTestDate" wrappedField="<%=true%>"
												placeholder="&nbsp;" label="">
												<aui:validator name="custom" errorMessage="Please provide a valid date">
													function (val, fieldNode, ruleValue) {
														return isValidDateInRangeForFurther(val);
													}
												</aui:validator>
											</aui:input> <span
											class="placeholder"><liferay-ui:message
													key="label-further-test-date" /></span>
											<!--<p class="pb-0 nominee-date-icon">
												<img src="/o/edelweisstokio-theme/images/praposal/date.png"
													alt="icon">
											</p>-->
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two"> <aui:input
												type="text" name="proposer_anyComplications"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-any-complications" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-3 col-12">
									<div class="form-box">
										<label class="custom-field two"> <aui:input
												type="text" name="proposer_additionalRemarks"
												wrappedField="<%=true%>" placeholder="&nbsp;" label=""></aui:input>
											<span class="placeholder"><liferay-ui:message
													key="label-additional-remarks" /></span>
										</label>
									</div>
								</div>

								<div class="col-md-12 col-12 mb-4">
									<aui:button value="label-add-medical-history"
										cssClass="mb-2 edto-btn-primary" id="addPrMedicalHistory" />
									<p>*Please click on Add Medical History Button once you
										fill the information to save the details</p>
								</div>
							</div>
						</div>
					</div>
					<!-- Table Here -->
					<div class="communication-details border-0"
						id="medicalHistoryTable_PS" style="display: none;">
						<div class="covind-inner-wrapper border-0 m-0 pb-0">
							<div class="table-title-wrapper mt-4">
								<h5 class="pt-0">Medical Details for Proposer</h5>
								<div class="tbl-srol-btn d-flex">
									<button id="slideBack-medicaltable2" type="button" disabled="">
										<span><svg width="25" height="25" viewBox="0 0 25 25"
												fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path
													fill-rule="evenodd" clip-rule="evenodd"
													d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z"
													fill=""></path>
                                                </svg> </span>
									</button>
									<button id="slide-medicaltable2" type="button">
										<span><svg width="25" height="25" viewBox="0 0 25 25"
												fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path
													fill-rule="evenodd" clip-rule="evenodd"
													d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z"
													fill=""></path>
                                                </svg> </span>
									</button>
								</div>
							</div>
							<div class="edto-custom-table pb-0" style="overflow: scroll;"
								id="mediacal_history_table_PS">

								<table class="table table-bordered w-100"
									style="white-space: nowrap" id="pr_table_data">
									<thead>
										<tr>
											<th>Disease Name</th>
											<th>Diagnosis Date</th>
											<th>Treatment Details</th>
											<th>Dosage Details</th>
											<th>Doctor Name</th>
											<th>Further Test Date</th>
											<th>Any Complications Name</th>
											<th>Additional Remarks</th>
											<th>Delete</th>
										</tr>
									</thead>
									<tbody>

									</tbody>
								</table>

							</div>
						</div>
					</div>

					<!-- </div> -->
			</div>
			</c:if>
		</div>
	</div>
</div>

<script>
var showLa = document.getElementById('medicalHistoryTable_LA');
var showPa = document.getElementById('medicalHistoryTable_PS');
    $(document).ready(function() {
   
    	// for autopopulate the tables
        var pfResponse = JSON.parse('${pfResponse}');
        let temp = pfResponse?.response_data?.medical_question_details;
        let lara = temp.la_details;
       	let para = temp.proposer_details.length === 0 ?temp.spouse_details:temp.proposer_details;
		if(lara.length != 0){
			showLa.style.display= "block";
		}
		if ((temp?.proposer_details && temp.proposer_details.length !== 0) || (temp?.spouse_details && temp.spouse_details.length !== 0)) {
		    showPa.style.display = "block";
		}

      lara.forEach(function(item) {
          var row = document.createElement("tr");
          var td = '<td>'+item.disease_name +'</td>'+'<td>'+item.diagnosis_date +'</td>'+'<td>'+item.treatment_details +'</td>'+'<td>'+item.dosage_details +'</td>'+'<td>'+item.doctor_name +'</td>'+'<td>'+item.further_test_date +'</td>'+'<td>'+item.any_complications +'</td>'+'<td>'+item.additional_remarks +'</td>'+'<td>'+'<button class="removeEntry badge w-100 d-block"><i class="far fa-trash-alt"></i></button>'+'</td>';
          row.innerHTML = td;
  		$('#medicalHistoryTable_LA tbody').append(row);  
      });

      if(para!=null && para.length>0){
    	  para.forEach(function(item) {
              var row = document.createElement("tr");
              var td = '<td>'+item.disease_name +'</td>'+'<td>'+item.diagnosis_date +'</td>'+'<td>'+item.treatment_details +'</td>'+'<td>'+item.dosage_details +'</td>'+'<td>'+item.doctor_name +'</td>'+'<td>'+item.further_test_date +'</td>'+'<td>'+item.any_complications +'</td>'+'<td>'+item.additional_remarks +'</td>'+'<td>'+'<button class="removeEntry badge w-100 d-block"><i class="far fa-trash-alt"></i></button>'+'</td>';
              row.innerHTML = td;
      		$('#medicalHistoryTable_PS tbody').append(row);  
          });
      }
    });
</script>
<script>
$(document).ready(function () {	
	
    var containers = document.querySelectorAll('#mediacal_history_table_LA, #mediacal_history_table_PS');
    var buttons = document.querySelectorAll('#slide-medicaltable1, #slide-medicaltable2');
    var backs = document.querySelectorAll('#slideBack-medicaltable1, #slideBack-medicaltable2');

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
function isValidDateInRangeForFurther(val) {
    // Split the input date into day, month, and year components
    var dateParts = val.split("/");
    if (dateParts.length !== 3) {
        return false; // Date format is not correct
    }
    var day = parseInt(dateParts[0], 10);
    var month = parseInt(dateParts[1], 10);
    var year = parseInt(dateParts[2], 10);

    // Check if the month and day values are within valid ranges
    if (year < 0 || month < 1 || month > 12 || day < 1 || day > 31) {
        return false; // Invalid month, day, or year
    }

    // Create a new Date object with the parsed components
    var date = new Date(year, month - 1, day); // Subtract 1 from month since it's zero-based
    
    var currentDate = new Date(new Date().toDateString()); // Get the current date
    var maxDate = new Date(); // Set the maximum date to 10 years in the future
    maxDate.setFullYear(currentDate.getFullYear() + 10);

    // Check if the date is a valid date and falls within the range
    if (!isNaN(date) && date >= currentDate && date <= maxDate) {
        return true; // Valid date within the range
    }

    return false; // Invalid date or out of range
}

</aui:script>