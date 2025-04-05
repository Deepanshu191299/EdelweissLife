<%@ include file="../../init.jsp"%>

<div id='education_of_spouse' class="communication-details">
<h5><liferay-ui:message key="label-education-details-for-spouse" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p>
				<liferay-ui:message key="label-education" />
			</p>
			<div class="radio_container">
				<c:forEach var="educationData" items="${masterMap['Education']}">
					<aui:input name='spouse_educationQualification'
						id="spouse_educationQualification_${educationData.name}" 
						cssClass="validate educationQualification_spouse-Input" 
						type="radio"
						label="" required="required" value="${educationData.value}"
						onclick="educationDiv('spouse_',this.value);"
						checked="${spousePersonalDetails.qualificationDataId == educationData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','spouse_educationQualification')">
						<aui:validator name="required" errorMessage="education-error-message"></aui:validator>
					</aui:input>
					<label for='<portlet:namespace/>spouse_educationQualification_${educationData.name}'>
						${educationData.name} </label>
				</c:forEach>
			</div>
			<div class="location-field">
				<div class="row">
					<div id="spouse_qualificationOther" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="spouse_otherQualification" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${spousePersonalDetails.qualificationOther}" maxLength="150"
									minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-please-specify-others-education-details" /></span>
							</label>
						</div>
					</div>
					
					<div id="spouse_qualificationStudent" class="col-md-12 col-12">
						<div class="row">
							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','spouse_pursuingCourse')"
											name="spouse_pursuingCourse" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text" 
											value="${spousePersonalDetails.coursePursued}" maxLength="150"
											minLength="0" wrappedField="<%=true %>">
											<aui:validator name="required" errorMessage="which-course-error-message"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-course-are-you-pursuing" /></span>
									</label>
								</div>
							</div>
						
							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','spouse_courseDuration')"
											name="spouse_courseDuration" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text" 
											value="${spousePersonalDetails.courseDuration}" maxLength="4"
											minLength="0" wrappedField="<%=true %>">
											<aui:validator name="required" errorMessage="duration-of-course-error"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-is-the-duration-of-the-course" /></span>
									</label>
								</div>
							</div>
							
							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_currentYearSemStd')"
											name="spouse_currentYearSemStd" placeholder="&nbsp;" label=""
											cssClass="vnumber validate" type="text" 
											value="${spousePersonalDetails.courseYear}" maxLength="4"
											minLength="0" wrappedField="<%=true %>">
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
							<label class="custom-field two"> <aui:input onInput="validateRadioButtonsOnChange('personal_details_form','spouse_nameOfCollegeSchool')"
									name="spouse_nameOfCollegeSchool" placeholder="&nbsp;" label=""
									cssClass="validate valphanumwithspace" type="text" 
									value="${spousePersonalDetails.courseCollege}" maxLength="250"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"
													errorMessage="name-of-last-college-school-error-message" />
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-name-of-last-college-school-location" /></span>
							</label>
						</div>
					</div>
					
					<div class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input  onInput="validateRadioButtonsOnChange('personal_details_form','spouse_highesteducation')"
									name="spouse_highesteducation" placeholder="&nbsp;" label=""
									cssClass="validate valphanumwithspace" type="text" 
									value="${spousePersonalDetails.highestQualification}" maxLength="150"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"
													errorMessage="highest-education-error-message" />
								</aui:input> <span class="placeholder" style="white-space: wrap;"><liferay-ui:message
										key="label-highest-educational-professional-qualification" /></span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	educationDiv("spouse_", $('input[name="'+portletNamespace +'spouse_educationQualification"]:checked').val());
});

$(document).ready(function() {
	$(".educationQualification_spouse-Input").each(function () {
		var educationQualificationInput = $(this).val();
		var educationQualificationDataId = 22;
		if ($(this).prop("checked") && educationQualificationInput == educationQualificationDataId){
			//if checked as doctor make as true
			//checked as doctor
			spouseHandleFieldDisabled("spouse_title-val-1295");
			$("#isSpouseEducationFieldActive").val('true');
		}
	});
});

$(".educationQualification_spouse-Input").click(function(){
	
	var educationQualificationInput = $(this).val();
	
	var educationQualificationDataId = 22;
	
	var spouseGenderDataId = ${spousePersonalDetails.genderDataId};
	
	var maleGenderDataId = 3;
	var feMaleGenderDataId = 4;
	var transGenderDataId = 1802;
	var companyGenderDataId = 1970;
	
	var maritalStatSingle = 13;
	var maritalStatMarried = 14;
	var maritalStatWidow = 15;
	var maritalStatDivorcee = 16;
	
	var maritalStatusInput = 0;
	
	$(".spouse_maritalStatus-Input").each(function () {
		if ($(this).prop("checked")){
			//if checked then set checked value
			maritalStatusInput = $(this).val();
		}
	});
	
	console.log("spouse_maritalStatus-Input -------------- checked value "+maritalStatusInput);
	
	if(educationQualificationInput == educationQualificationDataId){
		//checked as doctor
		spouseHandleFieldDisabled("spouse_title-val-1295");
		$("#isSpouseEducationFieldActive").val('true');
	}else if(spouseGenderDataId == maleGenderDataId){
		//checked as Mr.
		spouseHandleFieldDisabled("spouse_title-val-1");
		$("#isSpouseEducationFieldActive").val('false');
	}else if(spouseGenderDataId == feMaleGenderDataId && maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee){
		//checked as Ms.
		spouseHandleFieldDisabled("spouse_title-val-2");
		$("#isSpouseEducationFieldActive").val('false');
	}else if(spouseGenderDataId == feMaleGenderDataId && maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow){
		//checked as Mrs.
		spouseHandleFieldDisabled("spouse_title-val-124");
		$("#isSpouseEducationFieldActive").val('false');
	}else if(spouseGenderDataId == transGenderDataId){
		//checked as Mx.
		spouseHandleFieldDisabled("spouse_title-val-1803");
		$("#isSpouseEducationFieldActive").val('false');
	}else if(spouseGenderDataId == companyGenderDataId){
		//checked as M/S.
		spouseHandleFieldDisabled("spouse_title-val-1879");
		$("#isSpouseEducationFieldActive").val('false');
	}
});
</script>