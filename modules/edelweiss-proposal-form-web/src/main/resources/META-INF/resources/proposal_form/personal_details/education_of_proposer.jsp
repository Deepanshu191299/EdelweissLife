<%@ include file="../../init.jsp"%>

<div id='education_of_proposer' class="communication-details">
<h5><liferay-ui:message key="label-education-details-for-proposer" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p>
				<liferay-ui:message key="label-education" />
			</p>
			<div class="radio_container">
				<c:forEach var="educationData" items="${masterMap['Education']}">
					<aui:input name='proposer_educationQualification'
						id="proposer_educationQualification_${educationData.name}" 
						cssClass="validate educationQualification_proposer-Input" 
						type="radio"
						label="" required="required" value="${educationData.value}"
						onclick="educationDiv('proposer_', this.value);"
						checked="${proposerPersonalDetails.qualificationDataId == educationData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','proposer_educationQualification')">
						<aui:validator name="required" errorMessage="education-error-message"></aui:validator>
					</aui:input>
					<label for='<portlet:namespace/>proposer_educationQualification_${educationData.name}'>
						${educationData.name} </label>
				</c:forEach>
			</div>

			<div class="location-field">
				<div class="row">
					<div id="proposer_qualificationOther" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_otherQualification" placeholder="&nbsp;"
									label="" cssClass="valphanumwithspace" type="text"
									value="${proposerPersonalDetails.qualificationOther}"
									maxLength="150" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-please-specify-others-education-details" /></span>
							</label>
						</div>
					</div>

					<div id="proposer_qualificationStudent" class="col-md-12 col-12">
						<div class="row">
							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','proposer_pursuingCourse')"
											name="proposer_pursuingCourse" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text"
											value="${proposerPersonalDetails.coursePursued}"
											maxLength="150" minLength="0" wrappedField="<%=true %>">
											<aui:validator name="required" errorMessage="which-course-error-message"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-course-are-you-pursuing" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										oninput="validateRadioButtonsOnChange('personal_details_form','proposer_courseDuration')"
											name="proposer_courseDuration" placeholder="&nbsp;" label=""
											cssClass="valphanumwithspace validate" type="text"
											value="${proposerPersonalDetails.courseDuration}"
											maxLength="4" minLength="0" wrappedField="<%=true %>">
											<aui:validator name="required" errorMessage="duration-of-course-error"></aui:validator>
										</aui:input> <span class="placeholder"><liferay-ui:message
												key="label-which-is-the-duration-of-the-course" /></span>
									</label>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="form-box">
									<label class="custom-field two"> <aui:input
										onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_currentYearSemStd')"
											name="proposer_currentYearSemStd" placeholder="&nbsp;"
											label="" cssClass="vnumber validate" type="text"
											value="${proposerPersonalDetails.courseYear}" maxLength="4"
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
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_nameOfCollegeSchool')"
									name="proposer_nameOfCollegeSchool" placeholder="&nbsp;"
									label="" cssClass="validate valphanumwithspace" type="text"
									value="${proposerPersonalDetails.courseCollege}" maxLength="250"
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
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_highesteducation')"
									name="proposer_highesteducation" placeholder="&nbsp;" label=""
									cssClass="validate valphanumwithspace" type="text"
									value="${proposerPersonalDetails.highestQualification}"
									maxLength="150" minLength="0" wrappedField="<%=true %>">
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
	educationDiv("proposer_", $('input[name="'+portletNamespace +'proposer_educationQualification"]:checked').val());
});

$(document).ready(function() {
	$(".educationQualification_proposer-Input").each(function () {
		
		var educationQualificationInput = $(this).val();
		var educationQualificationDataId = 22;
		if ($(this).prop("checked") && educationQualificationInput == educationQualificationDataId){
			//if checked as doctor make as true
			//checked as doctor
			proposerHandleFieldDisabled("proposer_title-val-1295");
			$("#isProposerEducationFieldActive").val('true');
		}
	});
});

$(".educationQualification_proposer-Input").click(function(){
	var educationQualificationInput = $(this).val();
	
	var educationQualificationDataId = 22;
	
	var proposerGenderDataId = ${proposerPersonalDetails.genderDataId};
	var maleGenderDataId = 3;
	var feMaleGenderDataId = 4;
	var transGenderDataId = 1802;
	var companyGenderDataId = 1970;
	
	var maritalStatSingle = 13;
	var maritalStatMarried = 14;
	var maritalStatWidow = 15;
	var maritalStatDivorcee = 16;
	
	var maritalStatusInput = 0;
	
	$(".proposer_maritalStatus-Input").each(function () {
		if ($(this).prop("checked")){
			//if checked then set checked value
			maritalStatusInput = $(this).val();
		}
	});
	
	console.log("proposer_maritalStatus-Input -------------- checked value "+maritalStatusInput);
	
	if(educationQualificationInput == educationQualificationDataId){
		//checked as doctor
		proposerHandleFieldDisabled("proposer_title-val-1295");
		$("#isProposerEducationFieldActive").val('true');
	}else if(proposerGenderDataId == maleGenderDataId){
		//checked as Mr.
		proposerHandleFieldDisabled("proposer_title-val-1");
		$("#isProposerEducationFieldActive").val('false');
	}else if(proposerGenderDataId == feMaleGenderDataId && maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee){
		//checked as Mrs.
		proposerHandleFieldDisabled("proposer_title-val-2");
		$("#isProposerEducationFieldActive").val('false');
	}else if(proposerGenderDataId == feMaleGenderDataId && maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow){
		//checked as Mrs.
		proposerHandleFieldDisabled("proposer_title-val-124");
		$("#isProposerEducationFieldActive").val('false');
	}else if(proposerGenderDataId == transGenderDataId){
		//checked as Mx.
		proposerHandleFieldDisabled("proposer_title-val-1803");
		$("#isProposerEducationFieldActive").val('false');
	}else if(proposerGenderDataId == companyGenderDataId){
		//checked as M/S.
		proposerHandleFieldDisabled("proposer_title-val-1879");
		$("#isProposerEducationFieldActive").val('false');
	}
});
</script>