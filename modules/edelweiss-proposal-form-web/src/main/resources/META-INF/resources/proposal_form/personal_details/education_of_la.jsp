<%@ include file="../../init.jsp"%>

<div id='education_of_la' class="communication-details">
<h5><liferay-ui:message key="label-education-details-for-life-assured" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p style="white-space: wrap;"> 
				<liferay-ui:message key="label-education" />
			</p>
			<div class="radio_container">
				<c:forEach var="educationData" items="${masterMap['Education']}">
					<c:if
						test="${isLaMinor && (educationData.value == 23 || educationData.value == 24)}">
						<aui:input name='la_educationQualification'
							id="la_educationQualification_${educationData.name}"
							cssClass="validate" type="radio" label="" required="required"
							value="${educationData.value}"
							onclick="educationDiv('la_', this.value);"
							checked="${laPersonalDetails.qualificationDataId == educationData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','la_educationQualification')">
							<aui:validator name="required"
								errorMessage="education-error-message"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>la_educationQualification_${educationData.name}'>
							${educationData.name} </label>
					</c:if>
					<c:if test="${!isLaMinor}">
						<aui:input name='la_educationQualification'
							id="la_educationQualification_${educationData.name}"
							cssClass="validate educationQualification_la-Input" type="radio" label="" required="required"
							value="${educationData.value}"
							onclick="educationDiv('la_', this.value);"
							checked="${laPersonalDetails.qualificationDataId == educationData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','la_educationQualification')">
							<aui:validator name="required"
								errorMessage="education-error-message"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>la_educationQualification_${educationData.name}'>
							${educationData.name} </label>
					</c:if>
				</c:forEach>
			</div>

			<div class="location-field">
				<div class="row">
					<div id="la_qualificationOther" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_otherQualification" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text"
									value="${laPersonalDetails.qualificationOther}" maxLength="150"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-please-specify-others-education-details" /></span>
							</label>
						</div>
					</div>

					<div id="la_qualificationStudent" class="col-md-12 col-12">
						<div class="row">
							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','la_pursuingCourse')"
											name="la_pursuingCourse" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text"
											value="${laPersonalDetails.coursePursued}" maxLength="150"
											minLength="0" wrappedField="<%=true%>">
											<aui:validator name="required" errorMessage="which-course-error-message"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-course-are-you-pursuing" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','la_courseDuration')"
											name="la_courseDuration" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text"
											value="${laPersonalDetails.courseDuration}" maxLength="4"
											minLength="0" wrappedField="<%=true%>">
											<aui:validator name="required" errorMessage="duration-of-course-error"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-is-the-duration-of-the-course" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										onkeyup="validateRadioButtonsOnChange('personal_details_form','la_currentYearSemStd')"
											name="la_currentYearSemStd" placeholder="&nbsp;" label=""
											cssClass="vnumber validate" type="text"
											value="${laPersonalDetails.courseYear}" maxLength="4"
											minLength="0" wrappedField="<%=true%>">
											<aui:validator name="required" errorMessage="current-year-error-message"></aui:validator>
										</aui:input> <span class="placeholder text-wrap-indotted"><liferay-ui:message
												key="label-currently-in-which-year-semester-standard-you-are-studying" /></span>
									</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','la_nameOfCollegeSchool')"
									name="la_nameOfCollegeSchool" placeholder="&nbsp;" label=""
									cssClass="validate valphanumwithspace" type="text"
									value="${laPersonalDetails.courseCollege}" maxLength="250"
									minLength="0" wrappedField="<%=true%>">
									<aui:validator name="required"
										errorMessage="name-of-last-college-school-error-message" />
								</aui:input> <span class="placeholder" style="white-space: wrap;"><liferay-ui:message
										key="label-name-of-last-college-school-location" /></span>
							</label>
						</div>
					</div>

					<div class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','la_highesteducation')"
									name="la_highesteducation" placeholder="&nbsp;" label=""
									cssClass="validate valphanumwithspace" type="text"
									value="${laPersonalDetails.highestQualification}"
									maxLength="150" minLength="0" wrappedField="<%=true%>">
									<aui:validator name="required"
										errorMessage="highest-education-error-message" />
								</aui:input> <span class="placeholder" style="white-space: wrap;"><liferay-ui:message
										key="label-highest-educational-professional-qualification" /></span>
							</label>
						</div>
					</div>

					<c:if test="${(isInfant || isLaMinor) && isLongForm}">
						<div class="col-md-6 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:select
									oninput="validateRadioButtonsOnChange('personal_details_form','la_standard')"
										name="la_standard" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text"
										maxLength="150"
										minLength="0" >
											<c:forEach var="curCourse" items="${courseDataMap}">
												<option value="${curCourse.key}" ${laPersonalDetails.coursePursued == curCourse.value ? 'selected' : ''}>${curCourse.value}</option>
											</c:forEach>
											<aui:validator name="required"
											errorMessage="standard-error-message" />
									</aui:select> <span class="placeholder" id="la_stan_label"><liferay-ui:message
											key="label-in-which-standard-life-insured-is-studying" /></span>
								</label>
							</div>
						</div>
					</c:if>
					
					<c:if test="${isInfant && isLongForm}">
						<div class="col-md-6 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','la_weight')"
										name="la_weight" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text"
										value="${laPersonalDetails.minorWeight}" maxLength="3"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required"
										errorMessage="infant-weight-error-message" />
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-what-is-the-weight-of-life-insured-at-the-time-of-birth" /></span>
								</label>
							</div>
						</div>

						<div class="col-md-12 col-12">
							<p>
								<liferay-ui:message
									key="label-are-all-vaccinations-done-for-life-insured" />
							</p>
							<div class="radio_container">
								<aui:input name='la_vaccinationDone' id="la_vaccinationYes"
									cssClass="validate" type="radio" label="" required="required"
									checked="${laPersonalDetails.vaccinationDoneYn == 'Y' ? true : false}"
									value="Y" onChange="validateRadioButtonsOnChange('personal_details_form','la_vaccinationDone')">
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:input>
								<label for="<portlet:namespace/>la_vaccinationYes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input name='la_vaccinationDone' id="la_vaccinationNo"
									cssClass="validate" type="radio" label="" required="required"
									checked="${laPersonalDetails.vaccinationDoneYn == 'N' ? true : false}"
									value="N" onChange="validateRadioButtonsOnChange('personal_details_form','la_vaccinationDone')">
								</aui:input>
								<label for="<portlet:namespace/>la_vaccinationNo"><liferay-ui:message
										key="label-no" /></label>

							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

$(document).ready(function() {
	$(".educationQualification_la-Input").each(function () {
		var educationQualificationInput = $(this).val();
		var educationQualificationDataId = 22;
		if ($(this).prop("checked") && educationQualificationInput == educationQualificationDataId){
			//if checked as doctor make as true
			//checked as doctor
			laHandleFieldDisabled("la_title-val-1295");
			$("#isLaEducationFieldActive").val('true');
		}
	});
});
$(".educationQualification_la-Input").click(function(){
	var educationQualificationInput = $(this).val();
	
	var educationQualificationDataId = 22;
	var laGenderDataId = ${laPersonalDetails.genderDataId};
	var maleGenderDataId = 3;
	var feMaleGenderDataId = 4;
	var transGenderDataId = 1802;
	var companyGenderDataId = 1970;
	
	var maritalStatSingle = 13;
	var maritalStatMarried = 14;
	var maritalStatWidow = 15;
	var maritalStatDivorcee = 16;
	
	var maritalStatusInput = 0;
		
	$(".maritalStatus-Input").each(function () {
		if ($(this).prop("checked")){
			//if checked then set checked value
			maritalStatusInput = $(this).val();
		}
	});
	
	console.log("maritalStatus-Input -------------- checked value "+maritalStatusInput);
	
	if(educationQualificationInput == educationQualificationDataId){
		//checked as doctor
		laHandleFieldDisabled("la_title-val-1295");
		$("#isLaEducationFieldActive").val('true');
	}else if(laGenderDataId == maleGenderDataId && educationQualificationInput != educationQualificationDataId){
		//checked as Mr.
		laHandleFieldDisabled("la_title-val-1");
		$("#isLaEducationFieldActive").val('false');
	}else if(laGenderDataId == feMaleGenderDataId && educationQualificationInput != educationQualificationDataId && maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee){
		//checked as Ms.
		laHandleFieldDisabled("la_title-val-2");
		$("#isLaEducationFieldActive").val('false');
	}else if(laGenderDataId == feMaleGenderDataId && educationQualificationInput != educationQualificationDataId && maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow){
		//checked as Mrs.
		laHandleFieldDisabled("la_title-val-124");
		$("#isLaEducationFieldActive").val('false');
	}else if(laGenderDataId == transGenderDataId && educationQualificationInput != educationQualificationDataId){
		//checked as Mx.
		laHandleFieldDisabled("la_title-val-1803");
		$("#isLaEducationFieldActive").val('false');
	}else if(laGenderDataId == companyGenderDataId && educationQualificationInput != educationQualificationDataId){
		//checked as M/S.
		laHandleFieldDisabled("la_title-val-1879");
		$("#isLaEducationFieldActive").val('false');
	}
});

$(document).ready(function() {
	educationDiv("la_", $('input[name="'+portletNamespace +'la_educationQualification"]:checked').val());
	$('#la_stan_label').css("top", "8px");
	$('#<portlet:namespace/>la_standard').css("border-left", "none");
	$('#<portlet:namespace/>la_standard').css("border-top", "none");
	$('#<portlet:namespace/>la_standard').css("border-right", "none");
	$('#<portlet:namespace/>la_standard').css("background-image", "url(../images/down-arrow.svg) !important");
	$('#<portlet:namespace/>la_standard').css("border-bottom", "1px solid #8e8e8e !important");
	
});
$('#<portlet:namespace/>la_standard').change(function () 
		{$('#la_stan_label').css("top", "8px");
		$('#<portlet:namespace/>la_standard').css("background-image", "url(../images/down-arrow.svg) !important");
		$('#<portlet:namespace/>la_standard').css("border-bottom", "1px solid #8e8e8e !important");

});
</script>
