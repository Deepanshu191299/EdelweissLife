<%@ include file="../../init.jsp"%>
<div class="other-details" id="medical-details">
	<div class="medical-test">
		<div class="radio-wrapper mt-0">
			<div class="location-field">
				<div class="form-header mb-2">
					<div class="left">
						<h5>
							<liferay-ui:message key="medical-details" />
						</h5>
					</div>
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

				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>1) Within past 5 years have you:*</h6>
							<p>
								<liferay-ui:message key="label-q-consulted-influenza-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_consulted_influenza_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_consulted_influenza_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_consulted_influenza_y"
								checked="${laLifeStyleDetails.qConsultedInfluenzaYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_consulted');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_consulted_influenza_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_consulted_influenza_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_consulted_influenza_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_consulted_influenza_n"
								checked="${laLifeStyleDetails.qConsultedInfluenzaYn=='N'?true:false }"
								onclick="showDiv(false, 'la_consulted');"></aui:input>
							<label for="<portlet:namespace/>la_q_consulted_influenza_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_consulted_influenza_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consulted_influenza_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_consulted_influenza_y"
									checked="${insurerDetails.qConsultedInfluenzaYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_consulted_influenza_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_consulted_influenza_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consulted_influenza_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_consulted_influenza_n"
									checked="${insurerDetails.qConsultedInfluenzaYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_consulted_influenza_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_consulted" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','la_q_consulted_influenza_details')"
										cssClass="valphanumwithspace validate" maxlength="150"
										name="la_q_consulted_influenza_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qConsultedInfluenzaDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />la_q_consulted_influenza_yn"]:checked').val();
											if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_consulted" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass=" " maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_consulted_influenza_details')"
										name="pr_q_consulted_influenza_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qConsultedInfluenzaDetails:ProposerLifeStyleDetails.qConsultedInfluenzaDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_consulted_influenza_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-had-ecg-x-ray-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_had_ecg_x_ray_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_had_ecg_x_ray_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_had_ecg_x_ray_yn')"
								checked="${laLifeStyleDetails.qHadEcgXRayYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_blood-test');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_had_ecg_x_ray_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_had_ecg_x_ray_yn" type="radio" value="N"
								cssClass="setClose validate" label="" id="la_q_had_ecg_x_ray_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_had_ecg_x_ray_yn')"
								checked="${laLifeStyleDetails.qHadEcgXRayYn=='N'?true:false }"
								onclick="showDiv(false, 'la_blood-test');"></aui:input>
							<label for="<portlet:namespace/>la_q_had_ecg_x_ray_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_had_ecg_x_ray_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label="" id="pr_q_had_ecg_x_ray_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_had_ecg_x_ray_yn')"
									checked="${insurerDetails.qHadEcgXRayYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_had_ecg_x_ray_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_had_ecg_x_ray_yn" type="radio" value="N"
									cssClass="setClose1 validate" label=""
									id="pr_q_had_ecg_x_ray_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_had_ecg_x_ray_yn')"
									checked="${insurerDetails.qHadEcgXRayYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_had_ecg_x_ray_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_blood-test" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_had_ecg_x_ray_details')"
										name="la_q_had_ecg_x_ray_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qHadEcgXRayDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_had_ecg_x_ray_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_blood-test" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										name="pr_q_had_ecg_x_ray_details" type="text"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_had_ecg_x_ray_details')"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qHadEcgXRayDetails:ProposerLifeStyleDetails.qHadEcgXRayDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_had_ecg_x_ray_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-admitted-any-hospital-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_admitted_any_hospital_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_admitted_any_hospital_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_admitted_any_hospital_y"
								checked="${laLifeStyleDetails.qAdmittedAnyHospitalYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_admitted_hospital');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_admitted_any_hospital_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_admitted_any_hospital_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_admitted_any_hospital_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_admitted_any_hospital_n"
								checked="${laLifeStyleDetails.qAdmittedAnyHospitalYn=='N'?true:false }"
								onclick="showDiv(false, 'la_admitted_hospital');"></aui:input>
							<label for="<portlet:namespace/>la_q_admitted_any_hospital_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_admitted_any_hospital_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_admitted_any_hospital_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_admitted_any_hospital_y"
									checked="${insurerDetails.qAdmittedAnyHospitalYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_admitted_any_hospital_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input required="true" name="pr_q_admitted_any_hospital_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_admitted_any_hospital_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_admitted_any_hospital_n"
									checked="${insurerDetails.qAdmittedAnyHospitalYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_admitted_any_hospital_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_admitted_hospital"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_admitted_any_hospital_details')"
										name="la_q_admitted_any_hospital_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qAdmittedAnyHospitalDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_admitted_any_hospital_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_admitted_hospital"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_admitted_any_hospital_details')"
										name="pr_q_admitted_any_hospital_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qAdmittedAnyHospitalDetails:ProposerLifeStyleDetails.qAdmittedAnyHospitalDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
												function requiedField(val){
													var addressOne = $('input[name="<portlet:namespace />pr_q_admitted_any_hospital_yn"]:checked').val();
													if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>2) Within past 5 years have you:</h6>
							<p>
								<liferay-ui:message key="label-q-taking-any-medication-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_taking_any_medication_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_taking_any_medication_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_taking_any_medication_y"
								checked="${laLifeStyleDetails.qTakingAnyMedicationYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_medication');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_taking_any_medication_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_taking_any_medication_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_taking_any_medication_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_taking_any_medication_n"
								checked="${laLifeStyleDetails.qTakingAnyMedicationYn=='N'?true:false }"
								onclick="showDiv(false, 'la_medication');"></aui:input>
							<label for="<portlet:namespace/>la_q_taking_any_medication_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_taking_any_medication_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_taking_any_medication_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_taking_any_medication_y"
									checked="${insurerDetails.qTakingAnyMedicationYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_taking_any_medication_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_taking_any_medication_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_taking_any_medication_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_taking_any_medication_n"
									checked="${insurerDetails.qTakingAnyMedicationYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_taking_any_medication_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_medication" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_taking_any_medication_details')"
										name="la_q_taking_any_medication_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qTakingAnyMedicationDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_taking_any_medication_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_medication" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','pr_q_taking_any_medication_details')"
										 maxlength="150"
										name="pr_q_taking_any_medication_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qTakingAnyMedicationDetails:ProposerLifeStyleDetails.qTakingAnyMedicationDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_taking_any_medication_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>3) Have you ever sought an advice for the following</h6>
							<p>
								<liferay-ui:message key="label-q-any-disorder-heart-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_any_disorder_heart_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_disorder_heart_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_any_disorder_heart_y"
								checked="${laLifeStyleDetails.qAnyDisordeHheartYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_heartDisorder');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_any_disorder_heart_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_any_disorder_heart_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_disorder_heart_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_any_disorder_heart_n"
								checked="${laLifeStyleDetails.qAnyDisordeHheartYn=='N'?true:false }"
								onclick="showDiv(false, 'la_heartDisorder');"></aui:input>
							<label for="<portlet:namespace/>la_q_any_disorder_heart_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_any_disorder_heart_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_disorder_heart_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_any_disorder_heart_y"
									checked="${insurerDetails.qAnyDisordeHheartYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_any_disorder_heart_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_any_disorder_heart_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_disorder_heart_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_any_disorder_heart_n"
									checked="${insurerDetails.qAnyDisordeHheartYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_any_disorder_heart_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_heartDisorder"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_any_disorder_heart_details')"
										name="la_q_any_disorder_heart_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qAnyDisorderHeartDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_any_disorder_heart_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_heartDisorder"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										name="pr_q_any_disorder_heart_details" type="text"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_any_disorder_heart_details')"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qAnyDisorderHeartDetails:ProposerLifeStyleDetails.qAnyDisorderHeartDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_any_disorder_heart_yn"]:checked').val();
											if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-history-high-blood-pressure-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true"
								name="la_q_history_high_blood_pressure_yn"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_history_high_blood_pressure_yn')"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_history_high_blood_pressure_y"
								checked="${laLifeStyleDetails.qHistoryHighBloodPressureYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_bloodPressure');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label
								for="<portlet:namespace/>la_q_history_high_blood_pressure_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_history_high_blood_pressure_yn"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_history_high_blood_pressure_yn')"
								type="radio" value="N" cssClass="setClose validate" label=""
								id="la_q_history_high_blood_pressure_n"
								checked="${laLifeStyleDetails.qHistoryHighBloodPressureYn=='N'?true:false }"
								onclick="showDiv(false, 'la_bloodPressure');"></aui:input>
							<label
								for="<portlet:namespace/>la_q_history_high_blood_pressure_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true"
									name="pr_q_history_high_blood_pressure_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_history_high_blood_pressure_yn')"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label="" id="pr_q_history_high_blood_pressure_y"
									checked="${insurerDetails.qHistoryHighBloodPressureYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_history_high_blood_pressure_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_history_high_blood_pressure_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_history_high_blood_pressure_yn')"
									type="radio" value="N" cssClass="setClose1 validate" label=""
									id="pr_q_history_high_blood_pressure_n"
									checked="${insurerDetails.qHistoryHighBloodPressureYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_history_high_blood_pressure_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_bloodPressure"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_history_high_blood_pressure_details')"
										name="la_q_history_high_blood_pressure_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qHistoryHighBloodPressureDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />la_q_history_high_blood_pressure_yn"]:checked').val();
											if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_bloodPressure"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_history_high_blood_pressure_details')"
										name="pr_q_history_high_blood_pressure_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qHistoryHighBloodPressureDetails:ProposerLifeStyleDetails.qHistoryHighBloodPressureDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
												function requiedField(val){
													var addressOne = $('input[name="<portlet:namespace />pr_q_history_high_blood_pressure_yn"]:checked').val();
													if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-respiratory-lung-trouble-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_respiratory_lung_trouble_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_respiratory_lung_trouble_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_respiratory_lung_trouble_y"
								checked="${laLifeStyleDetails.qRespiratoryLungTroubleYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_lungs');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_respiratory_lung_trouble_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_respiratory_lung_trouble_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_respiratory_lung_trouble_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_respiratory_lung_trouble_n"
								checked="${laLifeStyleDetails.qRespiratoryLungTroubleYn=='N'?true:false }"
								onclick="showDiv(false, 'la_lungs');"></aui:input>
							<label for="<portlet:namespace/>la_q_respiratory_lung_trouble_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true"
									name="pr_q_respiratory_lung_trouble_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_respiratory_lung_trouble_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_respiratory_lung_trouble_y"
									checked="${insurerDetails.qRespiratoryLungTroubleYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_respiratory_lung_trouble_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_respiratory_lung_trouble_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_respiratory_lung_trouble_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_respiratory_lung_trouble_n"
									checked="${insurerDetails.qRespiratoryLungTroubleYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_respiratory_lung_trouble_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_lungs" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_respiratory_lung_trouble_details')"
										name="la_q_respiratory_lung_trouble_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qRespiratoryLungTroubleDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_respiratory_lung_trouble_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_lungs" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_respiratory_lung_trouble_details')"
										name="pr_q_respiratory_lung_trouble_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qRespiratoryLungTroubleDetails:ProposerLifeStyleDetails.qRespiratoryLungTroubleDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_respiratory_lung_trouble_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-diabetes-urine-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_diabetes_urine_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_diabetes_urine_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_diabetes_urine_yn')"
								checked="${laLifeStyleDetails.qDiabetesUrineYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_urineSugar');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_diabetes_urine_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_diabetes_urine_yn" type="radio" value="N"
								cssClass="setClose validate" label="" id="la_q_diabetes_urine_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_diabetes_urine_yn')"
								checked="${laLifeStyleDetails.qDiabetesUrineYn=='N'?true:false }"
								onclick="showDiv(false, 'la_urineSugar');"></aui:input>
							<label for="<portlet:namespace/>la_q_diabetes_urine_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_diabetes_urine_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label="" id="pr_q_diabetes_urine_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_diabetes_urine_yn')"
									checked="${insurerDetails.qDiabetesUrineYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_diabetes_urine_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_diabetes_urine_yn" type="radio" value="N"
									cssClass="setClose1 validate" label=""
									id="pr_q_diabetes_urine_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_diabetes_urine_yn')"
									checked="${insurerDetails.qDiabetesUrineYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_diabetes_urine_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_urineSugar" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_diabetes_urine_details')"
										name="la_q_diabetes_urine_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDiabetesUrineDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_diabetes_urine_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_urineSugar" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_diabetes_urine_details')"
										name="pr_q_diabetes_urine_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDiabetesUrineDetails:ProposerLifeStyleDetails.qDiabetesUrineDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_diabetes_urine_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-disease-kidneys-bladder-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_disease_kidneys_bladder_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disease_kidneys_bladder_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_disease_kidneys_bladder_y"
								checked="${laLifeStyleDetails.qDiseaseKidneysBladderYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_kidneyDisorder');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_disease_kidneys_bladder_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_disease_kidneys_bladder_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disease_kidneys_bladder_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_disease_kidneys_bladder_n"
								checked="${laLifeStyleDetails.qDiseaseKidneysBladderYn=='N'?true:false }"
								onclick="showDiv(false, 'la_kidneyDisorder');"></aui:input>
							<label for="<portlet:namespace/>la_q_disease_kidneys_bladder_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_disease_kidneys_bladder_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disease_kidneys_bladder_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_disease_kidneys_bladder_y"
									checked="${insurerDetails.qDiseaseKidneysBladderYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_disease_kidneys_bladder_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_disease_kidneys_bladder_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disease_kidneys_bladder_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_disease_kidneys_bladder_n"
									checked="${insurerDetails.qDiseaseKidneysBladderYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_disease_kidneys_bladder_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_kidneyDisorder"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_disease_kidneys_bladder_details')"
										name="la_q_disease_kidneys_bladder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDiseaseKidneysBladderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />la_q_disease_kidneys_bladder_yn"]:checked').val();
											if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_kidneyDisorder"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_disease_kidneys_bladder_details')"
										name="pr_q_disease_kidneys_bladder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDiseaseKidneysBladderDetails:ProposerLifeStyleDetails.qDiseaseKidneysBladderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_disease_kidneys_bladder_yn"]:checked').val();
											if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-disorder-digestive-system-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true"
								name="la_q_disorder_digestive_system_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_digestive_system_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_disorder_digestive_system_y"
								checked="${laLifeStyleDetails.qDisorderDigestiveSystemYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_digestive');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_disorder_digestive_system_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_disorder_digestive_system_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_digestive_system_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_disorder_digestive_system_n"
								checked="${laLifeStyleDetails.qDisorderDigestiveSystemYn=='N'?true:false }"
								onclick="showDiv(false, 'la_digestive');"></aui:input>
							<label for="<portlet:namespace/>la_q_disorder_digestive_system_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true"
									name="pr_q_disorder_digestive_system_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_digestive_system_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_disorder_digestive_system_y"
									checked="${insurerDetails.qDisorderDigestiveSystemYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_disorder_digestive_system_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_disorder_digestive_system_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_digestive_system_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_disorder_digestive_system_n"
									checked="${insurerDetails.qDisorderDigestiveSystemYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_disorder_digestive_system_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_digestive" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_disorder_digestive_system_details')"
										name="la_q_disorder_digestive_system_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDisorderDigestiveSystemDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_disorder_digestive_system_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_digestive" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_digestive_system_details')"
										name="pr_q_disorder_digestive_system_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDisorderDigestiveSystemDetails:ProposerLifeStyleDetails.qDisorderDigestiveSystemDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_disorder_digestive_system_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-cancer-enlarged-gland-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_cancer_enlarged_gland_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_cancer_enlarged_gland_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_cancer_enlarged_gland_y"
								checked="${laLifeStyleDetails.qCancerEnlargedGlandYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_cancer');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_cancer_enlarged_gland_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_cancer_enlarged_gland_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_cancer_enlarged_gland_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_cancer_enlarged_gland_n"
								checked="${laLifeStyleDetails.qCancerEnlargedGlandYn=='N'?true:false }"
								onclick="showDiv(false, 'la_cancer');"></aui:input>
							<label for="<portlet:namespace/>la_q_cancer_enlarged_gland_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_cancer_enlarged_gland_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_cancer_enlarged_gland_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_cancer_enlarged_gland_y"
									checked="${insurerDetails.qCancerEnlargedGlandYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_cancer_enlarged_gland_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_cancer_enlarged_gland_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_cancer_enlarged_gland_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_cancer_enlarged_gland_n"
									checked="${insurerDetails.qCancerEnlargedGlandYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_cancer_enlarged_gland_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_cancer" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_cancer_enlarged_gland_details')"
										name="la_q_cancer_enlarged_gland_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qCancerEnlargedGlandDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_cancer_enlarged_gland_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_cancer" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','pr_q_cancer_enlarged_gland_details')"
										 maxlength="150"
										name="pr_q_cancer_enlarged_gland_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qCancerEnlargedGlandDetails:ProposerLifeStyleDetails.qCancerEnlargedGlandDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_cancer_enlarged_gland_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-tropical-diseases-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_tropical_diseases_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_tropical_diseases_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_tropical_diseases_y"
								checked="${laLifeStyleDetails.qTropicalDiseasesYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_malaria');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_tropical_diseases_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_tropical_diseases_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_tropical_diseases_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_tropical_diseases_n"
								checked="${laLifeStyleDetails.qTropicalDiseasesYn=='N'?true:false }"
								onclick="showDiv(false, 'la_malaria');"></aui:input>
							<label for="<portlet:namespace/>la_q_tropical_diseases_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_tropical_diseases_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_tropical_diseases_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_tropical_diseases_y"
									checked="${insurerDetails.qTropicalDiseasesYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_tropical_diseases_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_tropical_diseases_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_tropical_diseases_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_tropical_diseases_n"
									checked="${insurerDetails.qTropicalDiseasesYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_tropical_diseases_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_malaria" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_tropical_diseases_details')"
										name="la_q_tropical_diseases_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qTropicalDiseasesDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_tropical_diseases_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_malaria" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_tropical_diseases_details')"
										name="pr_q_tropical_diseases_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qTropicalDiseasesDetails:ProposerLifeStyleDetails.qTropicalDiseasesDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_tropical_diseases_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-thyroid-disorder-goitre-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input reuired="true" name="la_q_thyroid_disorder_goitre_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_thyroid_disorder_goitre_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_thyroid_disorder_goitre_y"
								checked="${laLifeStyleDetails.qThyroidDisorderGoitreYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_thyroid');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_thyroid_disorder_goitre_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_thyroid_disorder_goitre_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_thyroid_disorder_goitre_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_thyroid_disorder_goitre_n"
								checked="${laLifeStyleDetails.qThyroidDisorderGoitreYn=='N'?true:false }"
								onclick="showDiv(false, 'la_thyroid');"></aui:input>
							<label for="<portlet:namespace/>la_q_thyroid_disorder_goitre_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input reuired="true" name="pr_q_thyroid_disorder_goitre_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_thyroid_disorder_goitre_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_thyroid_disorder_goitre_y"
									checked="${insurerDetails.qThyroidDisorderGoitreYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_thyroid_disorder_goitre_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_thyroid_disorder_goitre_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_thyroid_disorder_goitre_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_thyroid_disorder_goitre_n"
									checked="${insurerDetails.qThyroidDisorderGoitreYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_thyroid_disorder_goitre_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_thyroid" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_thyroid_disorder_goitre_details')"
										name="la_q_thyroid_disorder_goitre_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qThyroidDisorderGoitreDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_thyroid_disorder_goitre_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_thyroid" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_thyroid_disorder_goitre_details')"
										name="pr_q_thyroid_disorder_goitre_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qThyroidDisorderGoitreDetails:ProposerLifeStyleDetails.qThyroidDisorderGoitreDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_thyroid_disorder_goitre_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-anaemia-bleeding-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_anaemia_bleeding_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_anaemia_bleeding_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_anaemia_bleeding_yn')"
								checked="${laLifeStyleDetails.qAnaemiaBleedingYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_anaemia');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_anaemia_bleeding_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_anaemia_bleeding_yn" type="radio" value="N"
								cssClass="setClose validate" label=""
								id="la_q_anaemia_bleeding_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_anaemia_bleeding_yn')"
								checked="${laLifeStyleDetails.qAnaemiaBleedingYn=='N'?true:false }"
								onclick="showDiv(false, 'la_anaemia');"></aui:input>
							<label for="<portlet:namespace/>la_q_anaemia_bleeding_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_anaemia_bleeding_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_anaemia_bleeding_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_anaemia_bleeding_y"
									checked="${insurerDetails.qAnaemiaBleedingYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_anaemia_bleeding_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_anaemia_bleeding_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_anaemia_bleeding_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_anaemia_bleeding_n"
									checked="${insurerDetails.qAnaemiaBleedingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_anaemia_bleeding_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_anaemia" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_anaemia_bleeding_details')"
										name="la_q_anaemia_bleeding_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qAnaemiaBleedingDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_anaemia_bleeding_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_anaemia" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_anaemia_bleeding_details')"
										name="pr_q_anaemia_bleeding_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qAnaemiaBleedingDetails:ProposerLifeStyleDetails.qAnaemiaBleedingDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_anaemia_bleeding_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message
									key="label-q-nervous-neurological-disorder-yn" />
							</p
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true"
								name="la_q_nervous_neurological_disorder_yn"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_nervous_neurological_disorder_yn')"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_nervous_neurological_disorder_y"
								checked="${laLifeStyleDetails.qNervousNeurologicalDisorderYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_nervous');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label
								for="<portlet:namespace/>la_q_nervous_neurological_disorder_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_nervous_neurological_disorder_yn"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_nervous_neurological_disorder_yn')"
								type="radio" value="N" cssClass="setClose validate" label=""
								id="la_q_nervous_neurological_disorder_n"
								checked="${laLifeStyleDetails.qNervousNeurologicalDisorderYn=='N'?true:false }"
								onclick="showDiv(false, 'la_nervous');"></aui:input>
							<label
								for="<portlet:namespace/>la_q_nervous_neurological_disorder_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_nervous_neurological_disorder_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_nervous_neurological_disorder_yn')"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label="" id="pr_q_nervous_neurological_disorder_y"
									checked="${insurerDetails.qNervousNeurologicalDisorderYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_nervous_neurological_disorder_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_nervous_neurological_disorder_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_nervous_neurological_disorder_yn')"
									type="radio" value="N" cssClass="setClose1 validate" label=""
									id="pr_q_nervous_neurological_disorder_n"
									checked="${insurerDetails.qNervousNeurologicalDisorderYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_nervous_neurological_disorder_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_nervous" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_nervous_neurological_disorder_details')"
										name="la_q_nervous_neurological_disorder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qNervousNeurologicalDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_nervous_neurological_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_nervous" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_nervous_neurological_disorder_details')"
										name="pr_q_nervous_neurological_disorder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qNervousNeurologicalDisorderDetails:ProposerLifeStyleDetails.qNervousNeurologicalDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_nervous_neurological_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-ear-eye-disorder-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_ear_eye_disorder_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_ear_eye_disorder_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_ear_eye_disorder_yn')"
								checked="${laLifeStyleDetails.qEarEyeDisorderYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_throat');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_ear_eye_disorder_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_ear_eye_disorder_yn" type="radio" value="N"
								cssClass="setClose validate" label=""
								id="la_q_ear_eye_disorder_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_ear_eye_disorder_yn')"
								checked="${laLifeStyleDetails.qEarEyeDisorderYn=='N'?true:false }"
								onclick="showDiv(false, 'la_throat');"></aui:input>
							<label for="<portlet:namespace/>la_q_ear_eye_disorder_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_ear_eye_disorder_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_ear_eye_disorder_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_ear_eye_disorder_y"
									checked="${insurerDetails.qEarEyeDisorderYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_ear_eye_disorder_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_ear_eye_disorder_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_ear_eye_disorder_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_ear_eye_disorder_n"
									checked="${insurerDetails.qEarEyeDisorderYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_ear_eye_disorder_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_throat" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_ear_eye_disorder_details')"
										name="la_q_ear_eye_disorder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qEarEyeDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_ear_eye_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_throat" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										name="pr_q_ear_eye_disorder_details" type="text"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_ear_eye_disorder_details')"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qEarEyeDisorderDetails:ProposerLifeStyleDetails.qEarEyeDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_ear_eye_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-disorder-muscle-bone-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_disorder_muscle_bone_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_muscle_bone_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_disorder_muscle_bone_y"
								checked="${laLifeStyleDetails.qDisorderMuscleBoneYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_muscle');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_disorder_muscle_bone_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_disorder_muscle_bone_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_muscle_bone_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_disorder_muscle_bone_n"
								checked="${laLifeStyleDetails.qDisorderMuscleBoneYn=='N'?true:false }"
								onclick="showDiv(false, 'la_muscle');"></aui:input>
							<label for="<portlet:namespace/>la_q_disorder_muscle_bone_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_disorder_muscle_bone_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_muscle_bone_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_disorder_muscle_bone_y"
									checked="${insurerDetails.qDisorderMuscleBoneYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_muscle_bone_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_disorder_muscle_bone_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_muscle_bone_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_disorder_muscle_bone_n"
									checked="${insurerDetails.qDisorderMuscleBoneYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_muscle_bone_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_muscle" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_disorder_muscle_bone_details')"
										name="la_q_disorder_muscle_bone_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDisorderMuscleBoneDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_disorder_muscle_bone_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_muscle" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_muscle_bone_details')"
										name="pr_q_disorder_muscle_bone_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDisorderMuscleBoneDetails:ProposerLifeStyleDetails.qDisorderMuscleBoneDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_disorder_muscle_bone_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-test-hiv-aids-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_test_hiv_aids_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_test_hiv_aids_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_test_hiv_aids_yn')"
								checked="${laLifeStyleDetails.qTestHivAidsYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_aids');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_test_hiv_aids_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_test_hiv_aids_yn" type="radio" value="N"
								cssClass="setClose validate" label="" id="la_q_test_hiv_aids_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_test_hiv_aids_yn')"
								checked="${laLifeStyleDetails.qTestHivAidsYn=='N'?true:false }"
								onclick="showDiv(false, 'la_aids');"></aui:input>
							<label for="<portlet:namespace/>la_q_test_hiv_aids_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_test_hiv_aids_yn"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label="" id="pr_q_test_hiv_aids_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_test_hiv_aids_yn')"
									checked="${insurerDetails.qTestHivAidsYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_test_hiv_aids_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_test_hiv_aids_yn" type="radio" value="N"
									cssClass="setClose1 validate" label=""
									id="pr_q_test_hiv_aids_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_test_hiv_aids_yn')"
									checked="${insurerDetails.qTestHivAidsYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_test_hiv_aids_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_aids" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_test_hiv_aids_details')"
										name="la_q_test_hiv_aids_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qTestHivAidsDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_test_hiv_aids_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_aids" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('health_details_form','pr_q_test_hiv_aids_details')"
										 maxlength="150"
										name="pr_q_test_hiv_aids_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qTestHivAidsDetails:ProposerLifeStyleDetails.qTestHivAidsDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_test_hiv_aids_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-excessive-alcohol-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_excessive_alcohol_yn"
								type="radio" value="Y" cssClass="setOff setOpen validate"
								label="" id="la_q_excessive_alcohol_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_excessive_alcohol_yn')"
								checked="${laLifeStyleDetails.qExcessiveAlcoholYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_excessiveAlcohol');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_excessive_alcohol_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_excessive_alcohol_yn" type="radio"
								value="N" cssClass="setClose validate" label=""
								id="la_q_excessive_alcohol_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_excessive_alcohol_yn')"
								checked="${laLifeStyleDetails.qExcessiveAlcoholYn=='N'?true:false }"
								onclick="showDiv(false, 'la_excessiveAlcohol');"></aui:input>
							<label for="<portlet:namespace/>la_q_excessive_alcohol_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_excessive_alcohol_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_excessive_alcohol_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_excessive_alcohol_y"
									checked="${insurerDetails.qExcessiveAlcoholYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_excessive_alcohol_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_excessive_alcohol_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_excessive_alcohol_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_excessive_alcohol_n"
									checked="${insurerDetails.qExcessiveAlcoholYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_excessive_alcohol_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_excessiveAlcohol"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_excessive_alcohol_details')"
										name="la_q_excessive_alcohol_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qExcessiveAlcoholDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_excessive_alcohol_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_excessiveAlcohol"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_excessive_alcohol_details')"
										name="pr_q_excessive_alcohol_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qExcessiveAlcoholDetails:ProposerLifeStyleDetails.qExcessiveAlcoholDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_excessive_alcohol_yn"]:checked').val();
											if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-any-other-illness-disorder-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true"
								name="la_q_any_other_illness_disorder_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_other_illness_disorder_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_any_other_illness_disorder_y"
								checked="${laLifeStyleDetails.qAnyOtherIllnessDisorderYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_illness');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label
								for="<portlet:namespace/>la_q_any_other_illness_disorder_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_any_other_illness_disorder_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_other_illness_disorder_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_any_other_illness_disorder_n"
								checked="${laLifeStyleDetails.qAnyOtherIllnessDisorderYn=='N'?true:false }"
								onclick="showDiv(false, 'la_illness');"></aui:input>
							<label
								for="<portlet:namespace/>la_q_any_other_illness_disorder_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_any_other_illness_disorder_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_other_illness_disorder_yn')"
									type="radio" value="Y" cssClass="setOff1 setOpen validate"
									label=" " id="pr_q_any_other_illness_disorder_y"
									checked="${insurerDetails.qAnyOtherIllnessDisorderYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_any_other_illness_disorder_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_any_other_illness_disorder_yn"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_other_illness_disorder_yn')"
									type="radio" value="N" cssClass="setClose1 validate" label=""
									id="pr_q_any_other_illness_disorder_n"
									checked="${insurerDetails.qAnyOtherIllnessDisorderYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_any_other_illness_disorder_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_illness" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_any_other_illness_disorder_details')"
										name="la_q_any_other_illness_disorder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qAnyOtherIllnessDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_any_other_illness_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_illness" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_any_other_illness_disorder_details')"
										name="pr_q_any_other_illness_disorder_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qAnyOtherIllnessDisorderDetails:ProposerLifeStyleDetails.qAnyOtherIllnessDisorderDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_any_other_illness_disorder_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>
								<liferay-ui:message key="label-q-deformity-p-abnormality-yn" />
							</h6>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_deformity_p_abnormality_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_deformity_p_abnormality_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_deformity_p_abnormality_y"
								checked="${laLifeStyleDetails.qDeformityPAbnormalityYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_deformity');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_deformity_p_abnormality_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_deformity_p_abnormality_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_deformity_p_abnormality_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_deformity_p_abnormality_n"
								checked="${laLifeStyleDetails.qDeformityPAbnormalityYn=='N'?true:false }"
								onclick="showDiv(false, 'la_deformity');"></aui:input>
							<label for="<portlet:namespace/>la_q_deformity_p_abnormality_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_deformity_p_abnormality_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_deformity_p_abnormality_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_deformity_p_abnormality_y"
									checked="${insurerDetails.qDeformityPAbnormalityYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_deformity_p_abnormality_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_deformity_p_abnormality_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_deformity_p_abnormality_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_deformity_p_abnormality_n"
									checked="${insurerDetails.qDeformityPAbnormalityYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_deformity_p_abnormality_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_deformity" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_deformity_p_abnormality_details')"
										name="la_q_deformity_p_abnormality_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDeformityPAbnormalityDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_deformity_p_abnormality_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_deformity" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_deformity_p_abnormality_details')"
										name="pr_q_deformity_p_abnormality_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDeformityPAbnormalityDetails:ProposerLifeStyleDetails.qDeformityPAbnormalityDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_deformity_p_abnormality_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>
								<liferay-ui:message key="label-q-health-symptoms-appetite-yn" />
							</h6>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true"
								name="la_q_health_symptoms_appetite_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_health_symptoms_appetite_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_health_symptoms_appetite_y"
								checked="${laLifeStyleDetails.qHealthSymptomsAppetiteYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_health');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_health_symptoms_appetite_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_health_symptoms_appetite_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_health_symptoms_appetite_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_health_symptoms_appetite_n"
								checked="${laLifeStyleDetails.qHealthSymptomsAppetiteYn=='N'?true:false }"
								onclick="showDiv(false, 'la_health');"></aui:input>
							<label for="<portlet:namespace/>la_q_health_symptoms_appetite_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_health_symptoms_appetite_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_health_symptoms_appetite_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_health_symptoms_appetite_y"
									checked="${insurerDetails.qHealthSymptomsAppetiteYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_health_symptoms_appetite_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_health_symptoms_appetite_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_health_symptoms_appetite_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_health_symptoms_appetite_n"
									checked="${insurerDetails.qHealthSymptomsAppetiteYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_health_symptoms_appetite_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_health" style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_health_symptoms_appetite_details')"
										name="la_q_health_symptoms_appetite_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qHealthSymptomsAppetiteDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_health_symptoms_appetite_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_health" style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_health_symptoms_appetite_details')"
										name="pr_q_health_symptoms_appetite_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qHealthSymptomsAppetiteDetails:ProposerLifeStyleDetails.qHealthSymptomsAppetiteDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_health_symptoms_appetite_yn"]:checked').val();
												if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>6) For Female Lives:</h6>
							<p>
								<liferay-ui:message key="label-q-pregnant-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_pregnant_yn" type="radio" value="Y" cssClass="setOff setOpen validate" disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								label="" id="la_q_pregnant_y" onChange="validateRadioButtonsOnChange('health_details_form','la_q_pregnant_yn')"
								checked="${laLifeStyleDetails.qPregnantYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_pregnent');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_pregnant_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="setClose validate" name="la_q_pregnant_yn" type="radio" value="N" disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								label="" id="la_q_pregnant_n" onChange="validateRadioButtonsOnChange('health_details_form','la_q_pregnant_yn')"
								checked="${laLifeStyleDetails.qPregnantYn=='N'?true:false }"
								onclick="showDiv(false, 'la_pregnent');"></aui:input>
							<label for="<portlet:namespace/>la_q_pregnant_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_pregnant_yn" type="radio"
									value="Y" cssClass="setOff1 setOpen validate"
									disabled="${insurerHealth.genderDataId==4?false:true}" label=""
									id="pr_q_pregnant_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pregnant_yn')"
									checked="${insurerDetails.qPregnantYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_pregnant_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="setClose1 validate" name="pr_q_pregnant_yn"
									type="radio" value="N"
									disabled="${insurerHealth.genderDataId==4?false:true}" label=""
									id="pr_q_pregnant_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pregnant_yn')"
									checked="${insurerDetails.qPregnantYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_pregnant_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_pregnent" style="display: none">
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
											cssClass="numberField vnumber validate range-medical-section" type="text"
											value="${laLifeStyleDetails.qPregnantWeeks==null? 0:laLifeStyleDetails.qPregnantWeeks}"
											maxLength="3" minLength="1" wrappedField="<%=true %>">
											<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_pregnant_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_pregnent" style="display: none">
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
											cssClass="numberField vnumber validate range-medical-section" type="text"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qPregnantWeeks==null? 0:spouseLifeStyleDetails.qPregnantWeeks) : 
											(ProposerLifeStyleDetails.qPregnantWeeks==null? 0:ProposerLifeStyleDetails.qPregnantWeeks)}"
											maxLength="3" minLength="1" wrappedField="<%=true %>">
											<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />pr_q_pregnant_yn"]:checked').val();
												if(addressOne == "Y"){
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
					<div class="col-md-6 col-12">
						<div class="form-box">
							<p>
								<liferay-ui:message key="label-q-disorder-female-organ-yn" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_disorder_female_organ_yn" type="radio" onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_yn')"
								value="Y" cssClass="setOff setOpen validate" label="" id="la_q_disorder_female_organ_y" disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								checked="${laLifeStyleDetails.qDisorderFemaleOrganYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_femaleDisorder');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_disorder_female_organ_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_disorder_female_organ_yn" type="radio" onChange="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_yn')"
								value="N" cssClass="setClose validate" label="" id="la_q_disorder_female_organ_n" disabled="${isLaMinor || laPersonalDetails.genderDataId!=4?true:false}"
								checked="${laLifeStyleDetails.qDisorderFemaleOrganYn=='N'?true:false }"
								onclick="showDiv(false, 'la_femaleDisorder');"></aui:input>
							<label for="<portlet:namespace/>la_q_disorder_female_organ_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_disorder_female_organ_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_disorder_female_organ_y"
									disabled="${insurerHealth.genderDataId==4?false:true}"
									checked="${insurerDetails.qDisorderFemaleOrganYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_female_organ_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_disorder_female_organ_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_disorder_female_organ_n"
									disabled="${insurerHealth.genderDataId==4?false:true}"
									checked="${insurerDetails.qDisorderFemaleOrganYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_disorder_female_organ_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_femaleDisorder"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_disorder_female_organ_details')"
										name="la_q_disorder_female_organ_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qDisorderFemaleOrganDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_disorder_female_organ_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_femaleDisorder"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_disorder_female_organ_details')"
										name="pr_q_disorder_female_organ_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qDisorderFemaleOrganDetails:ProposerLifeStyleDetails.qDisorderFemaleOrganDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_disorder_female_organ_yn"]:checked').val();
											if(addressOne == "Y"){
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


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>
								<liferay-ui:message key="label-q-hospitalized-condition-yn" />
							</h6>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true" name="la_q_hospitalized_condition_yn"
								type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_hospitalized_condition_yn')"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_hospitalized_condition_y"
								checked="${laLifeStyleDetails.qHospitalizedConditionYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_hospitalized');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_q_hospitalized_condition_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_hospitalized_condition_yn" type="radio"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_hospitalized_condition_yn')"
								value="N" cssClass="setClose validate" label=""
								id="la_q_hospitalized_condition_n"
								checked="${laLifeStyleDetails.qHospitalizedConditionYn=='N'?true:false }"
								onclick="showDiv(false, 'la_hospitalized');"></aui:input>
							<label for="<portlet:namespace/>la_q_hospitalized_condition_n"><liferay-ui:message
									key="label-no" /></label>
						</div>

					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true" name="pr_q_hospitalized_condition_yn"
									type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_hospitalized_condition_yn')"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_hospitalized_condition_y"
									checked="${insurerDetails.qHospitalizedConditionYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>pr_q_hospitalized_condition_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_hospitalized_condition_yn" type="radio"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_hospitalized_condition_yn')"
									value="N" cssClass="setClose1 validate" label=""
									id="pr_q_hospitalized_condition_n"
									checked="${insurerDetails.qHospitalizedConditionYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>pr_q_hospitalized_condition_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_hospitalized"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="validate datepicker-field"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_hospitalization_date')"
										name="la_q_hospitalization_date" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qHospitalizationDate}">
										<aui:validator name="required"
											errorMessage="please-provide-a-valid-date">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />la_q_hospitalized_condition_yn"]:checked').val();
											if(addressOne == "Y"){
												return true;
											}else{
												return false;
											}
										}
									</aui:validator>
									<aui:validator name="custom" errorMessage="Please provide a valid date">
											    function (val, fieldNode, ruleValue) {
											        return isValidDateInRange(val);
											    }
									</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="date-of-hospitalization" /></span>
									<!--<p class="pb-0 nominee-date-icon">
										<img src="/o/edelweisstokio-theme/images/praposal/date.png"
											alt="icon">-->
									</p>
								</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="hiddenField" id="pr_hospitalized"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass=" datepicker-field"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_hospitalization_date')"
										name="pr_q_hospitalization_date" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qHospitalizationDate:ProposerLifeStyleDetails.qHospitalizationDate}">
										<aui:validator name="required"
											errorMessage="please-provide-a-valid-date">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_hospitalized_condition_yn"]:checked').val();
											if(addressOne == "Y"){
												return true;
											}else{
												return false;
											}
										}
									</aui:validator>
									<aui:validator name="custom" errorMessage="Please provide a valid date">
											    function (val, fieldNode, ruleValue) {
											        return isValidDateInRange(val);
											    }
									</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="date-of-hospitalization" /></span>
									<!--<p class="pb-0 nominee-date-icon">
										<img src="/o/edelweisstokio-theme/images/praposal/date.png"
											alt="icon">-->
									</p>
								</label>
							</div>
						</div>
					</div>
				</div>


				<div class="row">
					<div class="col-md-6 col-12">
						<div class="form-box">
							<h6>
								<liferay-ui:message key="label-q-fully-recovered-medications-yn" />
							</h6>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 col-6">
						<div class="radio_container setAll1">
							<aui:input required="true"
								name="la_q_fully_recovered_medications_yn" type="radio"
								value="Y" cssClass="setOff setOpen validate" label=""
								id="la_q_fully_recovered_medications_y"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_fully_recovered_medications_yn')"
								checked="${laLifeStyleDetails.qFullyRecoveredMedicationsYn=='Y'?true:false }"
								onclick="showDiv(true, 'la_fullyRecovered');">
								<aui:validator name="required"
									errorMessage="this-field-is-required" />
							</aui:input>
							<label
								for="<portlet:namespace/>la_q_fully_recovered_medications_y"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_fully_recovered_medications_yn"
								type="radio" value="N" cssClass="setClose validate" label=""
								id="la_q_fully_recovered_medications_n"
								onChange="validateRadioButtonsOnChange('health_details_form','la_q_fully_recovered_medications_yn')"
								checked="${laLifeStyleDetails.qFullyRecoveredMedicationsYn=='N'?true:false }"
								onclick="showDiv(false, 'la_fullyRecovered');"></aui:input>
							<label
								for="<portlet:namespace/>la_q_fully_recovered_medications_n"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>

					<div class="col-md-6 col-6">
						<c:if
							test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
							<div class="radio_container setAll2">
								<aui:input required="true"
									name="pr_q_fully_recovered_medications_yn" type="radio"
									value="Y" cssClass="setOff1 setOpen validate" label=""
									id="pr_q_fully_recovered_medications_y"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_fully_recovered_medications_yn')"
									checked="${insurerDetails.qFullyRecoveredMedicationsYn=='Y'?true:false }">
									<aui:validator name="required"
										errorMessage="this-field-is-required" />
								</aui:input>
								<label
									for="<portlet:namespace/>pr_q_fully_recovered_medications_y"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name="pr_q_fully_recovered_medications_yn"
									type="radio" value="N" cssClass="setClose1 validate" label=""
									id="pr_q_fully_recovered_medications_n"
									onChange="validateRadioButtonsOnChange('health_details_form','pr_q_fully_recovered_medications_yn')"
									checked="${insurerDetails.qFullyRecoveredMedicationsYn=='N'?true:false }"></aui:input>
								<label
									for="<portlet:namespace/>pr_q_fully_recovered_medications_n"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</c:if>
					</div>

					<div class="col-md-6 col-12">
						<div class="hiddenField" id="la_fullyRecovered"
							style="display: none">
							<p class="d-md-none">Life to be Insured</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										cssClass="valphanumwithspace validate" maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','la_q_fully_recovered_medications_details')"
										name="la_q_fully_recovered_medications_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${laLifeStyleDetails.qFullyRecoveredMedicationsDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
											function requiedField(val){
												var addressOne = $('input[name="<portlet:namespace />la_q_fully_recovered_medications_yn"]:checked').val();
												if(addressOne == "Y"){
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
						<div class="hiddenField" id="pr_fullyRecovered"
							style="display: none">
							<p class="d-md-none">Proposer/ Spouse</p>
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										 maxlength="150"
										oninput="validateRadioButtonsOnChange('health_details_form','pr_q_fully_recovered_medications_details')"
										name="pr_q_fully_recovered_medications_details" type="text"
										placeholder="&nbsp;" label="" wrappedField="<%=true %>"
										value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qFullyRecoveredMedicationsDetails:ProposerLifeStyleDetails.qFullyRecoveredMedicationsDetails}">
										<aui:validator name="required"
											errorMessage="other-details-message">
										function requiedField(val){
											var addressOne = $('input[name="<portlet:namespace />pr_q_fully_recovered_medications_yn"]:checked').val();
											if(addressOne == "Y"){
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

			</div>
		</div>
	</div>
</div>


<!-- Family Physician Section  -->
<div class="communication-details">
	<div class="medical-test">
		<div class="radio-wrapper">
			<div class="location-field">
				<div class="row">
					<div class="col-md-12 col-12">
						<div class="input-box">
							<h4>
								<liferay-ui:message
									key="label-family-physician-for-life-assured" />
							</h4>
						</div>
					</div>
					<div class="col-md-3 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input type="text"
									cssClass="valpha" maxLength="150" name="la_physicianName"
									wrappedField="<%=true %>" placeholder="&nbsp;" label=""
									value="${laPersonalDetails.familyPhysicianName}">
									<aui:validator name="custom" errorMessage='only-alphabets'>
                                        function(val) {
                                        	return validateNameInput(val);
                                        }
                                    </aui:validator>
								</aui:input> <span class="placeholder">Name</span>
							</label>
						</div>
					</div>
					<div class="col-md-3 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input type="text"
									cssClass="valphanumwithspace" maxLength="90"
									name="la_physicianAddress" wrappedField="<%=true %>"
									placeholder="&nbsp;" label=""
									oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');"
									value="${laPersonalDetails.familyPhysicianAddressLine1}">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-family-physician-address" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-3 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input type="text"
									cssClass="vnumber" maxLength="10"
									name="la_physicianContactNumber" wrappedField="<%=true %>"
									placeholder="&nbsp;" label=""
									value="${laPersonalDetails.familyPhysicianContactNumber}">
									<aui:validator name="digits"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-family-physician-contact-number" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-3 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input type="text"
									cssClass="vnumber" maxLength="10"
									name="la_physicianMobileNumber" wrappedField="<%=true %>"
									placeholder="&nbsp;" label=""
									value="${laPersonalDetails.familyPhysicianMobileNumber}">
									<aui:validator name="digits"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-family-physician-mobile-number" /></span>
							</label>
						</div>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-12 col-12">
							<div class="input-box">
								<c:if test="${commonDetails.spouseExistYn == 'Y'}">
									<h4>
										<liferay-ui:message key="label-family-physician-for-spouse" />
									</h4>
								</c:if>
								<c:if test="${commonDetails.isLaPrSameYn == 'N'}">
									<h4>
										<liferay-ui:message key="label-family-physician-for-proposer" />
									</h4>
								</c:if>
							</div>
						</div>
						<div class="col-md-3 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input type="text"
										cssClass="valpha" maxLength="150" name="pr_physicianName"
										wrappedField="<%=true %>" placeholder="&nbsp;" label=""
										value="${commonDetails.spouseExistYn == 'Y' ? spousePersonalDetails.familyPhysicianName:proposerPersonalDetails.familyPhysicianName}">
										<aui:validator name="custom" errorMessage='only-alphabets'>
                                        function(val) {
                                        	return validateNameInput(val);
                                        }
                                    </aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-family-physician-name" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-3 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input type="text"
										cssClass="valphanumwithspace" maxLength="90"
										oninput="this.value = this.value.replace(/[^a-zA-Z0-9\s]/g, '');"
										name="pr_physicianAddress" wrappedField="<%=true %>"
										placeholder="&nbsp;" label=""
										value="${commonDetails.spouseExistYn == 'Y' ? spousePersonalDetails.familyPhysicianAddressLine1:proposerPersonalDetails.familyPhysicianAddressLine1}">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-family-physician-address" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-3 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input type="text"
										cssClass="vnumber" maxLength="10"
										name="pr_physicianContactNumber" wrappedField="<%=true %>"
										placeholder="&nbsp;" label=""
										value="${commonDetails.spouseExistYn == 'Y' ? spousePersonalDetails.familyPhysicianContactNumber:proposerPersonalDetails.familyPhysicianContactNumber}">
										<aui:validator name="digits"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-family-physician-contact-number" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-3 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input type="text"
										cssClass="vnumber" maxLength="10"
										name="pr_physicianMobileNumber" wrappedField="<%=true %>"
										placeholder="&nbsp;" label=""
										value="${commonDetails.spouseExistYn == 'Y' ? spousePersonalDetails.familyPhysicianMobileNumber:proposerPersonalDetails.familyPhysicianMobileNumber}">
										<aui:validator name="digits"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-family-physician-mobile-number" /></span>
								</label>
							</div>
						</div>
					</c:if>
				</div>

			</div>
		</div>
	</div>
</div>
<aui:script>
	function isValidDateInRange(val) {
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
	
		// Check if the date is a valid date and not in the future
		if (!isNaN(date) && date <= new Date()) {
			return true; // Valid date within the range and not in the future
		}
	
		return false; // Invalid date, out of range, or in the future
	}
</aui:script>