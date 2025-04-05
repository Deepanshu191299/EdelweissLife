const getCKycURL = $("#getCKycURL").val();
const postCKycURL = $("#postCKycURL").val();
const isLaMinor = ($("#isLaMinor").val() === 'true');
const isInfant = ($("#isInfant").val() === 'true');
const isLaPrSame = ($("#isLaPrSame").val() === 'true');
const isLongForm = ($("#isLongForm").val() === 'true');
const isSpouseExist = ($("#isSpouseExist").val() === 'true');
const displayABHANoField = ($("#displayABHANoField").val() === 'true');


/**
 * Show & Hide Current Address Fields based on user selection
 */
function loadAddressFields(portletNamespace, userPrefix){
	
	if($('input[name="'+portletNamespace + userPrefix +'isAddressSame"]:checked').val() == "Y"){
		showPDDiv(false, userPrefix + "current_address");		
	}else{
		showPDDiv(true, userPrefix + "current_address");
	}	
}

/**
 * Show & Hide Fields which are required based on user selection in Employment Type
 */
function educationDiv(userPrefix, inputValue){
	
	addRemoveValidate(false, userPrefix + "qualificationOther");
	document.getElementById(userPrefix + "qualificationOther").style.display = 'none';
	
	$("#" + userPrefix + "qualificationOther :input").each(function(i) {          
	    this.value="";
	});
	
	addRemoveValidate(false, userPrefix + "qualificationStudent");
	document.getElementById(userPrefix + "qualificationStudent").style.display = 'none';
	
	$("#" + userPrefix + "qualificationStudent :input").each(function(i) {          
	    this.value="";
	});
	
	if(inputValue == 23){
		addRemoveValidate(true, userPrefix + "qualificationStudent");
		document.getElementById(userPrefix + "qualificationStudent").style.display = 'block';
	} 
	
	if(inputValue == 24){
		addRemoveValidate(true, userPrefix + "qualificationOther");
		document.getElementById(userPrefix + "qualificationOther").style.display = 'block';
	}
}

/**
 * Show & Hide Detail Field of GST & Income based on user selection
 */
function loadGSTIncomeFields(portletNamespace, userPrefix){
	
	if($('input[name="'+portletNamespace + userPrefix +'gstBenefit"]:checked').val() == "Y"){
		showPDDiv(true, userPrefix + "gstAvailed");		
	}else{
		showPDDiv(false, userPrefix + "gstAvailed");
	}
	
	if($('input[name="'+portletNamespace + userPrefix +'otherIncomeSource"]:checked').val() == "Y"){
		showPDDiv(true, userPrefix + "otherIncomeSource");		
	}else{
		showPDDiv(false, userPrefix + "otherIncomeSource");
	}
}

/**
 * Show & Hide Fields which are required based on user selection in Employment Type
 */
function employementDiv(userPrefix, inputValue,onclick){
	
	addRemoveValidate(false, userPrefix + "nameOfEmployer");
	document.getElementById(userPrefix + "nameOfEmployer").style.display = 'none';  
	
//	$("#" + userPrefix + "nameOfEmployer select").each(function(i) {          
//	    $("#"+this.id).select2("val", "").trigger("click");
//	});
	addRemoveValidate(false, userPrefix + "nameOfEmployer_others");
	document.getElementById(userPrefix + "nameOfEmployer_others").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "designationPosition");
	document.getElementById(userPrefix + "designationPosition").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "workExperienceYear");
	document.getElementById(userPrefix + "workExperienceYear").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "workExperienceMonth");
	document.getElementById(userPrefix + "workExperienceMonth").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "addressEmployerBusiness");
	document.getElementById(userPrefix + "addressEmployerBusiness").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "occupationIndustry");
	document.getElementById(userPrefix + "occupationIndustry").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "numberEmployeesCompany");
	document.getElementById(userPrefix + "numberEmployeesCompany").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "businessNature");
	document.getElementById(userPrefix + "businessNature").style.display = 'none';
	
	addRemoveValidate(false, userPrefix + "jobDescription");
	document.getElementById(userPrefix + "jobDescription").style.display = 'none';
	
	if(onclick){
		/*$("#" + userPrefix + "nameOfEmployer :input").each(function(i) {          
		    this.value="";
		});*/
		$("#" + userPrefix + "designationPosition :input").each(function(i) {          
		    this.value="";
		});
		$("#" + userPrefix + "workExperienceYear :input").each(function(i) {          
		    this.value="0";
		});
		$("#" + userPrefix + "workExperienceMonth :input").each(function(i) {          
		    this.value="0";
		});
		$("#" + userPrefix + "addressEmployerBusiness :input").each(function(i) {          
		    this.value="";
		});
		/*$("#" + userPrefix + "occupationIndustry :input").each(function(i) {          
		    this.value="";
		});*/
		/*$("#" + userPrefix + "numberEmployeesCompany :input").each(function(i) {          
		    this.value="";
		});*/
		$("#" + userPrefix + "businessNature :input").each(function(i) {          
		    this.value="";
		});
		/*$("#" + userPrefix + "jobDescription :input").each(function(i) {          
		    this.value="";
		});*/
	}
	
	if(inputValue == 25 || inputValue == 26 || 
	   inputValue == 27 || inputValue == 31){
		
		addRemoveValidate(true, userPrefix + "nameOfEmployer");
		document.getElementById(userPrefix + "nameOfEmployer").style.display = 'block';
		
		var emplrName = $('#'+namespace+ userPrefix +'nameofEmployerBusiness option:selected').text().trim();
		if(emplrName == "Others"){
			addRemoveValidate(true, userPrefix + "nameOfEmployer_others");
			document.getElementById(userPrefix + "nameOfEmployer_others").style.display = '';
		}else{
			document.getElementById(userPrefix + "nameOfEmployer_others").style.display = 'none';
		}
		addRemoveValidate(true, userPrefix + "designationPosition");
		document.getElementById(userPrefix + "designationPosition").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "workExperienceYear");
		document.getElementById(userPrefix + "workExperienceYear").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "workExperienceMonth");
		document.getElementById(userPrefix + "workExperienceMonth").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "addressEmployerBusiness");
		document.getElementById(userPrefix + "addressEmployerBusiness").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "occupationIndustry");
		document.getElementById(userPrefix + "occupationIndustry").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "numberEmployeesCompany");
		document.getElementById(userPrefix + "numberEmployeesCompany").style.display = 'block';
		
		addRemoveValidate(true, userPrefix + "jobDescription");
		document.getElementById(userPrefix + "jobDescription").style.display = 'block';
		
		if(inputValue == 27){
			addRemoveValidate(true, userPrefix + "businessNature");
			document.getElementById(userPrefix + "businessNature").style.display = 'block';
		}
	}	
	if(inputValue == 28 || inputValue == 30 || inputValue == 1805){
		addRemoveValidate(true, userPrefix + "jobDescription");
		document.getElementById(userPrefix + "jobDescription").style.display = 'block';
	}
}

/**
 * Show & Hide Detail Field of Political & Criminal based on user selection
 */
function loadPoliticalCriminalFields(portletNamespace){
	
	if($('input[name="'+portletNamespace +'politicallyExposed"]:checked').val() == "Y"){
		showPDDiv(true, "politicallyExposed");
	} else {
		showPDDiv(false, "politicallyExposed");
	}

	if(isLongForm){
		if($('input[name="'+portletNamespace +'criminalProceedings"]:checked').val() == "Y"){
			showPDDiv(true, "criminalProceedingsDetail");
		} else {
			showPDDiv(false, "criminalProceedingsDetail");
		}
	}
}

/**
 * Show & Hide Fields which are required based on user selection
 */
function showPDDiv(display, divId) {
	addRemoveValidate(display, divId);
	if (display) {
		document.getElementById(divId).style.display = 'block';
	} else {
		document.getElementById(divId).style.display = 'none';
	}	
}

/**
 * Add Remove Validate Class from the field based on user selection
 */
function addRemoveValidate(display, divId){
	
	const inputList = document.getElementById(divId).querySelectorAll('input, select');
	for (i = 0; i < inputList.length; i++) {
		if(display){
			$("#"+inputList[i].id).addClass("validate");	
		}else{
			$("#"+inputList[i].id).removeClass("validate");	
		}
	}	
}

/**
 * Validate Fields & Send Personal Details to SavePF API on Save & Continue Button Click
 */
function savePersonalDetails(validationDivId, validateCompleteDiv, liferayFormId, doSubmit){
	
	var isEKYCDone = $("#ekycSuccess_la").val();
	var isEkycFailed = $("#isEkycFailed").val();
	var isIndian = $("input[name='"+portletNamespace+"la_nationality']:checked").val();
	var isRunEkycForLa= $("#run-ekyc-for-la").text();
	console.log("savePersonalDetails btn clicked");
	if(isRunEkycForLa == "la" && isIndian == 5 && isEKYCDone == "false" && isEkycFailed == "false"){
		console.log("validate run ekyc pop modal");
		$("#ekycUserValidation").text('Life Assured');
		openEkycValidationModal();
		return false;
	}
	
	var validationDiv=$("#" + portletNamespace + validationDivId);
	var validDiv=true;
	var	currentFormValidator;
	try {
		currentFormValidator=Liferay.Form.get(portletNamespace + liferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	currentFormValidator.resetAllFields();
	var currentInputList = validationDiv.find(".validate");
	for (i = 0; i < currentInputList.length; i++) {
		var currentInputId=currentInputList[i].id;
		var currentInputValue=currentInputList[i].value;
		if(validateCompleteDiv || (currentInputValue!=undefined && currentInputValue!=''))
		{
			currentFormValidator.validateField(currentInputId);
		}
	}
	
//	var expInputList = validationDiv.find(".validateExp");
//	for (i = 0; i < expInputList.length; i++) {
//		var currentInputId=expInputList[i].id;
//		var currentInputValue=expInputList[i].value;
//
//		if(validateCompleteDiv || (currentInputValue!=undefined && currentInputValue!=''))
//		{
//			validateExperience(currentInputId);
//		}
//	}
	
	//console.log("isEkycCompleted : "+isEkycCompleted());
	var isEkycCompltd = isEkycCompleted();
	if(validDiv && !currentFormValidator.hasErrors() && isEkycCompltd)
	{	
		if(doSubmit){
			$('#'+ portletNamespace + 'loader').show();
			savePF();
			console.debug("Form Submitted by Button");
			$('#'+ portletNamespace + 'loader').hide();
			openNextStep(2);
		}
		return true;
	}
	else
	{
		if(currentFormValidator.hasErrors())
		{
			try {
				currentFormValidator.focusInvalidField();
			} catch (error) {
				console.debug(error);
			}
		}
		return false;
	}
}

/**
 * Custom Validation Function for Experience Fields
 */
function validateExperience(inputId){
	
	$("#"+inputId+"_Error").html('');
	
	const regex = new RegExp("^[0-9]+$");
	const inputValue = $("#"+inputId).val();
	
	if(!regex.test(inputValue)){
		$("#"+inputId+"_Error").html("Please Enter Valid Experience Year");
		return false;
	}else if(inputId.includes("Years") && inputValue > 57){
		$("#"+inputId+"_Error").html("Please Enter Experience Year Between 0 to 57");
		return false;
	}else if(inputId.includes("Months") && inputValue > 12){
		$("#"+inputId+"_Error").html("Please Enter Experience Month Between 0 to 12");
		return false;
	}
	
	return true;
}

/**
 * Gets Details from C KYC API of PAN
 */
function populatePANDetails(userPrefix, dateOfBirth){

	hidePAN("panSuccess");
	hidePAN("panFailure");
	showLoader();

	const panNo = $('#'+ portletNamespace + userPrefix +'panNo').val().toUpperCase();
	const getCKycDetailsURL = getCKycURL + "?PANNo="+panNo+"&DOB="+dateOfBirth;
	
	if(userPrefix == 'la_'){
		isLaCkycProceed =true;
		console.log("isLaCkycProceed "+isLaCkycProceed);
	}
	
	if(userPrefix == 'proposer_'){
		isProposerCkycProceed =true;
		console.log("isProposerCkycProceed "+isProposerCkycProceed);
	}
	
	if(userPrefix == 'spouse_'){
		isSpouseCkycProceed =true;
		console.log("isSpouseCkycProceed "+isSpouseCkycProceed);
	}
	
	postCKycTriggerForStatus(panNo,dateOfBirth,userPrefix);

}

/**
 * POST Details of C KYC to API
 */
function postCKycDetails(){

	hidePAN("panSuccess");
	hidePAN("panFailure");
	showLoader();

	const userPrefix = $("#userPrefix").val();
	const reqJSON = JSON.stringify({
		"ApplicationNumber": $("#"+portletNamespace+"applicationNumber").val(), 
		"PolicyNumber": $("#"+portletNamespace+"policyNumber").val(),
		"PANNumber": $("#"+portletNamespace + userPrefix + "panNo").val(),
		"DOB": $("#"+portletNamespace + userPrefix + "dateOfBirth").val(),
		"DetailTypeOf": 1
	});

	Liferay.Util.fetch(postCKycURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "POST",
		body : reqJSON
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.info(validationResponse);

		if(validationResponse != null && validationResponse.status){

			const panJSON = JSON.parse($("#panJSON").val()).responseData;
			$("#"+portletNamespace + userPrefix +"fatherName").val(panJSON.fatheR_FULLNAME);
			$("#"+portletNamespace + userPrefix +"motherName").val(panJSON.motheR_FULLNAME);
			$("#"+portletNamespace + userPrefix +"pa_address_line_one").val(panJSON.perM_LINE1);
			$("#"+portletNamespace + userPrefix +"pa_address_line_two").val(panJSON.perM_LINE2);
			$("#"+portletNamespace + userPrefix +"pa_pincode").val(panJSON.perM_PIN);
			$("#"+portletNamespace + userPrefix +"pa_city").val(panJSON.perM_CITY);
			closeKycModal(false);

		}else if(validationResponse && !validationResponse.status){
			$("#responseMessage").html(validationResponse.errors[0]);
			showPAN("panFailure");
			openKycModal();
		}else{
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			showPAN("panFailure");
			openKycModal();
		}
		hideLoader();
	})

	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#closeBtn").attr("onclick","closeKycModal(false)");
		showPAN("panFailure");
		hideLoader();
		openKycModal();
	});
}

//This method will use to call before getCkyc API
function postCKycTriggerForStatus(panNumber,dob,userPrefix){

	const reqJSON = JSON.stringify({
		"ApplicationNumber": $("#"+portletNamespace+"applicationNumber").val(), 
		"PolicyNumber": $("#"+portletNamespace+"policyNumber").val(),
		"PANNumber": panNumber,
		"DOB": dob,
		"DetailTypeOf": 1
	});

	Liferay.Util.fetch(postCKycURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "POST",
		body : reqJSON
	})
	.then((response) => {
		return response.json();
	})
	.then((validationResponse) => {
		console.info(validationResponse);
		const responseDataJson = validationResponse.responseData;
		
		console.log("responseData "+ JSON.stringify(responseDataJson));
		
		console.log("responseData typeof "+ typeof responseDataJson);
		
		if(validationResponse != null && validationResponse.status && typeof responseDataJson == 'object'){
			
			$("#panFullName").hide();
			$("#panGender").hide();
			$("#panDob").hide();
			$("#panMobile").hide();
			$("#panEmail").hide();
			
			console.log("fatheR_FULLNAME "+responseDataJson.fatheR_FULLNAME);
			console.log("fatheR_FULLNAME "+responseDataJson.motheR_FULLNAME);
			console.log("fatheR_FULLNAME "+responseDataJson.perM_LINE1);
			console.log("fatheR_FULLNAME "+responseDataJson.perM_LINE2);
			console.log("fatheR_FULLNAME "+responseDataJson.perM_PIN);
			console.log("fatheR_FULLNAME "+responseDataJson.perM_CITY);
			
			$("#"+portletNamespace + userPrefix +"fatherName").val(responseDataJson.fatheR_FULLNAME);
			$("#"+portletNamespace + userPrefix +"motherName").val(responseDataJson.motheR_FULLNAME);
			$("#"+portletNamespace + userPrefix +"pa_address_line_one").val(responseDataJson.perM_LINE1);
			$("#"+portletNamespace + userPrefix +"pa_address_line_two").val(responseDataJson.perM_LINE2);
			$("#"+portletNamespace + userPrefix +"pa_pincode").val(responseDataJson.perM_PIN);
			$("#"+portletNamespace + userPrefix +"pa_city").val(responseDataJson.perM_CITY);
			
			if(responseDataJson.fullname){
				$("#panFullName").children(".panFullName").html(responseDataJson.fullname);	
				$("#panFullName").show();
			}
			
			if(responseDataJson.gender){
				$("#panGender").children(".panGender").html(responseDataJson.gender);	
				$("#panGender").show();
			}
			
			if(responseDataJson.dob){
				$("#panDob").children(".panDob").html(responseDataJson.dob);	
				$("#panDob").show();
			}
			
			if(responseDataJson.moB_NUM){
				$("#panMobile").children(".panMobile").html(responseDataJson.moB_NUM);	
				$("#panMobile").show();
			}
			
			if(responseDataJson.email){
				$("#panEmail").children(".panEmail").html(responseDataJson.email);	
				$("#panEmail").show();
			}
			
			$("#userPrefix").val(userPrefix);
			$("#panJSON").val(JSON.stringify(validationResponse));
			showPAN("panSuccess");
			openKycModal();
		}else if(typeof responseDataJson == 'string' && responseDataJson != ''){
			$("#responseMessage").html(responseDataJson);
			showPAN("panFailure");
			openKycModal();
		}else if(validationResponse && !validationResponse.status){
			$("#responseMessage").html(validationResponse.errors[0]);
			showPAN("panFailure");
			openKycModal();
		}else{
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			showPAN("panFailure");
			openKycModal();
		}
		hideLoader();
	}).catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#closeBtn").attr("onclick","closeKycModal(false)");
		showPAN("panFailure");
		hideLoader();
		openKycModal();
	});
}


/**
 * Personal Details Request Creation
 */
const setPersonalDetails = (userPrefix, namespace, pfResponse) =>{
	console.log("setPersonalDetails pfResponse"+pfResponse.response_data.communication_details.la_details);
	const personalDetails = {};
	
	personalDetails.personal_details_id = '';
	personalDetails.lead_id = '';

	if(userPrefix == "la_"){

		if(pfResponse.response_data.personal_details.la_details != null && pfResponse.response_data.communication_details.la_details != null){
			
			personalDetails.personal_details_id = pfResponse.response_data.personal_details.la_details.personal_details_id;
			personalDetails.lead_id = pfResponse.response_data.personal_details.la_details.lead_id;	
			personalDetails.mobile_number=  pfResponse.response_data.communication_details.la_details.mobile_number;
			personalDetails.email_address=  pfResponse.response_data.communication_details.la_details.email_id;
			personalDetails.is_smoker_yn=  pfResponse.response_data.personal_details.la_details.is_smoker_yn;
		}
		
	}else if(userPrefix == "proposer_"){

		if(isLaPrSame){
			return null;
		} 
			
		if(pfResponse.response_data.personal_details.proposer_details != null){
			personalDetails.personal_details_id = pfResponse.response_data.personal_details.proposer_details.personal_details_id;
			personalDetails.lead_id = pfResponse.response_data.personal_details.proposer_details.lead_id;	
		}
		
	}else if(userPrefix == "spouse_"){

		if(!isSpouseExist){
			return null;	
		} 
			
		if(pfResponse.response_data.personal_details.spouse_details != null){
			personalDetails.personal_details_id = pfResponse.response_data.personal_details.spouse_details.personal_details_id;
			personalDetails.lead_id = pfResponse.response_data.personal_details.spouse_details.lead_id;
		}

	}

	//Personal Details - Abhijit
	personalDetails.title_data_id = $('input[name="'+ namespace + userPrefix + 'title"]:checked').val();
	personalDetails.first_name = $('#'+namespace+ userPrefix +'firstName').val();
	personalDetails.middle_name = $('#'+namespace+ userPrefix +'middleName').val();
	personalDetails.last_name = $('#'+namespace+ userPrefix +'lastName').val();
	personalDetails.full_name = $('#'+namespace+ userPrefix +'fullName').val();
	
	personalDetails.dob = slashToHyphenDate($('#'+namespace+ userPrefix +'dateOfBirth').val());
	personalDetails.gender_data_id = $('input[name="'+ namespace + userPrefix + 'gender"]:checked').val();
	personalDetails.marital_status_data_id = $('input[name="'+ namespace + userPrefix + 'maritalStatus"]:checked').val();
	personalDetails.abha_number = null;
	
	if(displayABHANoField){
		personalDetails.abha_number = $('#'+namespace+ userPrefix +'abhaNo').val();
	}
	
	personalDetails.pan_number = ($('#'+namespace+ userPrefix +'panNo').val() || "").toUpperCase();
	
	if(userPrefix == "la_" && (isInfant || isLaMinor)){
		personalDetails.pan_number = null;
		personalDetails.marital_status_data_id = null;
	}
	/* LDXP-1180 related changes start*/
	if( personalDetails.marital_status_data_id == '14' && userPrefix == "proposer_"){
		console.log("if and married:::" + personalDetails.marital_status_data_id + "userprefix::" + userPrefix);
		personalDetails.spouse_name = $('#'+namespace+ userPrefix +'fatherName').val();
	}else if(personalDetails.marital_status_data_id == '13' && userPrefix == "proposer_"){
		console.log("else and Single::" + personalDetails.marital_status_data_id + "userprefix::"+ userPrefix );
		personalDetails.father_name = $('#'+namespace+ userPrefix +'fatherName').val();
	} 
	
	if( personalDetails.marital_status_data_id == '14'){
		personalDetails.spouse_name = $('#'+namespace+ userPrefix +'fatherName').val();
	}else{
		personalDetails.father_name = $('#'+namespace+ userPrefix +'fatherName').val();
	}
	/* LDXP-1180 related changes end*/
	/*personalDetails.father_name = $('#'+namespace+ userPrefix +'fatherName').val();
	personalDetails.spouse_name = $('#'+namespace+ userPrefix +'fatherName').val();*/
	personalDetails.mother_name = $('#'+namespace+ userPrefix +'motherName').val();
	personalDetails.nationality_data_id = $('input[name="'+ namespace + userPrefix + 'nationality"]:checked').val();
	personalDetails.nationality_other = null; 
	personalDetails.age_proof_data_id = $('input[name="'+ namespace + userPrefix + 'ageProof"]:checked').val();
	personalDetails.age_proof_other = null;

	//Education Details - Abhijit
	personalDetails.qualification_data_id = $('input[name="'+ namespace + userPrefix + 'educationQualification"]:checked').val();
	personalDetails.qualification_other =  $('#'+namespace+ userPrefix +'otherQualification').val();
	personalDetails.course_pursued = $('#'+namespace+ userPrefix +'pursuingCourse').val(); 
	personalDetails.course_duration = $('#'+namespace+ userPrefix +'courseDuration').val();
	personalDetails.course_year = $('#'+namespace+ userPrefix +'currentYearSemStd').val();
	personalDetails.course_college = $('#'+namespace+ userPrefix +'nameOfCollegeSchool').val();
	personalDetails.highest_qualification = $('#'+namespace+ userPrefix +'highesteducation').val();
	
	personalDetails.minor_studying = '';
	personalDetails.minor_weight = '';
	personalDetails.vaccination_done_yn = '';
	
	
	if(userPrefix == "la_" && isLongForm && (isInfant || isLaMinor)){
		
		personalDetails.minor_studying = $('#'+namespace+ userPrefix +'standard').val();

	}
	
	if(userPrefix == "la_" && isLongForm && isInfant){
		
		personalDetails.minor_weight = $('#'+namespace+ userPrefix +'weight').val();
		personalDetails.vaccination_done_yn = $('input[name="'+ namespace + userPrefix + 'vaccinationDone"]:checked').val();
		
	}

	//Employement Details - Abhijit
	personalDetails.gstin_benefit = $('input[name="'+ namespace + userPrefix + 'gstBenefit"]:checked').val();
	personalDetails.gstin_number = null;
	
	if($('input[name="'+ namespace + userPrefix + 'gstBenefit"]:checked').val() == "Y"){
		
		personalDetails.gstin_number=$('#'+namespace+ userPrefix +'gstNumber').val();
		
	}else if($('input[name="'+ namespace + userPrefix + 'gstBenefit"]:checked').val() == "N"){
		
		personalDetails.gstin_number="N";
	
	}
    
	//Other Details in Personal Details - Abhijit
	personalDetails.politically_exposed_yn = $('input[name="'+ namespace + 'politicallyExposed"]:checked').val();
	personalDetails.politically_exposed_specify = $('#'+namespace +'politicalDetails').val();
	personalDetails.criminal_proceedings_yn = $('input[name="'+ namespace + 'criminalProceedings"]:checked').val();
	personalDetails.criminal_proceedings_details = $('#'+namespace +'criminalDetails').val();
	personalDetails.photo_proof_data_id = $('#'+namespace +'identityProof').val();
	personalDetails.photo_proof_other = null;
	personalDetails.address_proof_data_id = $('#'+namespace +'addressProof').val();
	personalDetails.address_proof_other = null;
	personalDetails.income_proof_data_id = $('#'+namespace +'incomeProof').val();
	personalDetails.income_proof_other = null;

	//Below Bank Details Present Under Insurance_History.JSP 
	personalDetails.bank_account_number=  $('#'+namespace+ userPrefix +'bank_account_number').val() ;  
	personalDetails.ifsc_code=  $('#'+namespace+ userPrefix +'ifsc_code').val()?$('#'+namespace+ userPrefix +'ifsc_code').val().toUpperCase():$('#'+namespace+ userPrefix +'ifsc_code').val() ; 
	personalDetails.account_holder_name= $('#'+namespace+ userPrefix +'account_holder_name').val() ; 
	personalDetails.bank_name= $('#'+namespace+ userPrefix +'bank_name').val() ; 
	personalDetails.bank_branch_location= $('#'+namespace+ userPrefix +'bank_branch_location').val() ; 

	//Present Under Insurance_History.JSP 
	personalDetails.tax_residence_declaration_yn= $('*[name="'+ namespace + userPrefix + 'tax_residence_declaration_yn"]:checked').val();   
	personalDetails.eia_account_available_yn= $('*[name="'+ namespace + userPrefix + 'eia_account_available_yn"]:checked').val();
	personalDetails.eia_account_number= $('#'+namespace+ userPrefix +'eia_account_number').val() ;   
	personalDetails.eia_repository_data_id= $('#'+namespace+ userPrefix +'eia_repository_data_id').val() ;     //Not Getting Revisit  not avaiable in JSP 
	personalDetails.eia_apply_yn= null; //Not Getting Revisit  not avaiable in JSP  

	//Not Getting Details in JSP - Need to check how to deal
	personalDetails.is_la_pr_same_yn= isLaPrSame ? "Y" : "N";     //Not Getting Revisit   coming as API Response 
	personalDetails.la_pr_relation_data_id=  null ; //Not Getting Revisit coming as API Response 
	personalDetails.is_smoker_yn=  pfResponse.response_data.personal_details.la_details.is_smoker_yn; //"N"    //Not Getting Revisit
	console.log("personalDetails.is_smoker_yn",personalDetails.is_smoker_yn);
	personalDetails.aadhar_number=   $("#aadharUuidRndr").val();   //Not Getting Revisit   coming as API Response 
	// personalDetails.nri_gst_applicable_yn= "N"; //Not Getting Revisit  not avaiable in JSP
	personalDetails.nri_gst_applicable_yn=$('input[name="'+ namespace + 'la_tax_residence_declaration_yn"]:checked').val();

	personalDetails.parents_ins_cover=  null   //Not Getting Revisit   coming as API Response 
	personalDetails.parents_annual_income=   null   //Not Getting Revisit   coming as API Response 
	personalDetails.siblings_ins_cover=   null   //Not Getting Revisit   coming as API Response 
	personalDetails.policy_categorisation_data_id=   null   //Not Getting Revisit   coming as API Response  
	personalDetails.policy_categorisation_other=   null   //Not Getting Revisit   coming as API Response  
	personalDetails.pr_relationship_data_id=   null   //Not Getting Revisit   coming as API Response 
	personalDetails.pr_relationship_other=   null   //Not Getting Revisit   coming as API Response 

	//Present Under medical_details_type1.jsp file.
	var healthPrefix;
	if(userPrefix=='la_'){
		healthPrefix=userPrefix;
	}
	if(userPrefix=='proposer_' || userPrefix=='spouse_'){
		healthPrefix='pr_'
	}
	personalDetails.family_physician_name= $('#'+namespace+ healthPrefix +'physicianName').val() ;  
	personalDetails.family_physician_address_line1= $('#'+namespace+ healthPrefix +'physicianAddress').val() ;  
	personalDetails.family_physician_address_line2= null; //Not Getting Revisit  not avaiable in JSP  
	personalDetails.family_physician_contact_number= $('#'+namespace+ healthPrefix +'physicianContactNumber').val() ; 
	personalDetails.family_physician_mobile_number= $('#'+namespace+ healthPrefix +'physicianMobileNumber').val() ;

	return personalDetails;
}

/**
 * Communication Details Request Creation
 */
const setCommunicationDetails = (userPrefix, namespace, pfResponse) =>{

	const communicationDetails = {};

	communicationDetails.communication_details_id = '';
	
	if(userPrefix == "la_"){

		if(pfResponse.response_data.communication_details.la_details != null){
			
			communicationDetails.communication_details_id = pfResponse.response_data.communication_details.la_details.communication_details_id;
		}

	}else if(userPrefix == "proposer_"){

		if(isLaPrSame){
			return null;	
		}
			
		if(pfResponse.response_data.communication_details.proposer_details != null){
			communicationDetails.communication_details_id = pfResponse.response_data.communication_details.proposer_details.communication_details_id;	
		}
		

	}else if(userPrefix == "spouse_"){

		if(!isSpouseExist){
			return null;	
		} 
			
		if(pfResponse.response_data.communication_details.spouse_details != null){
			communicationDetails.communication_details_id = pfResponse.response_data.communication_details.spouse_details.communication_details_id;	
		}
		
	}

	//Address Details
	communicationDetails.pa_address_line1 = $('#'+namespace+ userPrefix +'pa_address_line_one').val(); 
	communicationDetails.pa_address_line2 =$('#'+namespace+ userPrefix +'pa_address_line_two').val(); 
	communicationDetails.pa_address_line3 =$('#'+namespace+ userPrefix +'pa_address_line_three').val(); 
	communicationDetails.pa_address_line4 = null;
	communicationDetails.pa_pincode = $('#'+namespace+ userPrefix +'pa_pincode').val();
	communicationDetails.pa_city = $('#'+namespace+ userPrefix +'pa_city').val();
	communicationDetails.pa_state = $('#'+namespace+ userPrefix +'pa_state').find(':selected').data("name") || null;

	communicationDetails.is_ca_pa_same_yn = $('input[name="'+ namespace + userPrefix + 'isAddressSame"]:checked').val();
	communicationDetails.ca_address_line1 = $('#'+namespace+ userPrefix +'ca_address_line_one').val();
	communicationDetails.ca_address_line2 = $('#'+namespace+ userPrefix +'ca_address_line_two').val();
	communicationDetails.ca_address_line3 = $('#'+namespace+ userPrefix +'ca_address_line_three').val();
	communicationDetails.ca_address_line4 = null;
	communicationDetails.ca_pincode =  $('#'+namespace+ userPrefix +'ca_pincode').val();
	communicationDetails.ca_city = $('#'+namespace+ userPrefix +'ca_city').val();
	communicationDetails.ca_state =  $('#'+namespace+ userPrefix +'ca_state').find(':selected').data("name") || null;
	communicationDetails.correspondance_add = $('input[name="'+ namespace + userPrefix + 'correspondingAddress"]:checked').val() || null;

	//Contact Details
	communicationDetails.mobile_number = $('#'+namespace+ userPrefix +'mobileNumber').val();
	communicationDetails.email_id = $('#'+namespace+ userPrefix +'emailId').val();

	if(userPrefix == 'la_'){
		
		if(isInfant || isLaMinor){
			if(pfResponse.response_data.communication_details.la_details != null){
				communicationDetails.mobile_number = pfResponse.response_data.communication_details.la_details.mobile_number;
				communicationDetails.email_id = pfResponse.response_data.communication_details.la_details.email_id;
			}else{
				communicationDetails.mobile_number = '';
				communicationDetails.email_id = '';
			}
		}
		
		communicationDetails.phone_number_home = null;
		communicationDetails.phone_number_office = null;
		communicationDetails.facebook_id = null;
		communicationDetails.linked_in_id = null;
		communicationDetails.corporate_email_id = null;	
	}else{
		communicationDetails.phone_number_home = $('#'+namespace+ userPrefix +'residencePhone').val();
		communicationDetails.phone_number_office = $('#'+namespace+ userPrefix +'officePhone').val();
		communicationDetails.facebook_id = $('#'+namespace+ userPrefix +'facebookId').val();
		communicationDetails.linked_in_id = $('#'+namespace+ userPrefix +'linkedInId').val();
		communicationDetails.corporate_email_id = $('#'+namespace+ userPrefix +'corporateId').val();
	}

	communicationDetails.phone_number_home_std = null;
	communicationDetails.phone_number_office_std = null;

	return communicationDetails;
}


/**
 * Employment Details Request Creation
 */
const setEmploymentDetails = (userPrefix, namespace, pfResponse) =>{

	const employmentDetails = {};
	
	//employmentDetails.employment_details_id = '';
	//employmentDetails.emp_row_id = '';
	//employmentDetails.organisation_type_other = null;
	
	if(userPrefix == "la_"){

		if(pfResponse.response_data.employment_details.la_details != null){
			//employmentDetails.employment_details_id = pfResponse.response_data.employment_details.la_details.employment_details_id;
			//employmentDetails.emp_row_id = pfResponse.response_data.employment_details.la_details.emp_row_id;
		}
		
		if(isInfant || isLaMinor){
			
			employmentDetails.employment_type_data_id = '';
			employmentDetails.employer_name = '';
			employmentDetails.designation_held = '';
			employmentDetails.duty_nature_type_data_id = '';
			employmentDetails.business_nature = '';
			employmentDetails.working_duration_year = '';
			employmentDetails.working_duration_mnth = '';
			employmentDetails.employer_address = '';
			employmentDetails.organisation_type_data_id = '';
			employmentDetails.employ_count_type_data_id = ''; 
			employmentDetails.annual_income = '';
			employmentDetails.source_income_other_yn = '';
			employmentDetails.source_income_other_details = '';
			
			return employmentDetails;
		}
		
	}else if(userPrefix == "proposer_"){

		if(isLaPrSame){
			return null;	
		}
		
		if(pfResponse.response_data.employment_details.proposer_details != null){
			//employmentDetails.employment_details_id = pfResponse.response_data.employment_details.proposer_details.employment_details_id;
			//employmentDetails.emp_row_id = pfResponse.response_data.employment_details.proposer_details.emp_row_id;
		}

	}else if(userPrefix == "spouse_"){

		if(!isSpouseExist){
			return null;	
		}
		
		if(pfResponse.response_data.employment_details.spouse_details != null){
			//employmentDetails.employment_details_id = pfResponse.response_data.employment_details.spouse_details.employment_details_id;
			//employmentDetails.emp_row_id = pfResponse.response_data.employment_details.spouse_details.emp_row_id;
		}

	}

	employmentDetails.employment_type_data_id = $('input[name="'+ namespace + userPrefix + 'employementType"]:checked').val();
	var emplrName = $('#'+namespace+ userPrefix +'nameofEmployerBusiness option:selected').text().trim();
	if(emplrName == "Others"){
		emplrName = $('#'+namespace+ userPrefix +'nameOfEmployer_others').val();
	}
	employmentDetails.employer_name = emplrName;
	//employmentDetails.employer_name = $('#'+namespace+ userPrefix +'nameofEmployerBusiness option:selected').text().trim();
	employmentDetails.designation_held = $('#'+namespace+ userPrefix +'designationPositionHeld').val();
	employmentDetails.duty_nature_type_data_id = $('#'+namespace+ userPrefix +'jobDescriptionNatureDuty').val();
	employmentDetails.business_nature = $('#'+namespace+ userPrefix +'natureOfBusiness').val();
	employmentDetails.working_duration_year = $('#'+namespace+ userPrefix +'workExperienceYears').val();
	employmentDetails.working_duration_mnth = $('#'+namespace+ userPrefix +'workExperienceMonths').val();
	employmentDetails.employer_address = $('#'+namespace+ userPrefix +'addressOfEmployerBusiness').val();
	employmentDetails.organisation_type_data_id = $('#'+namespace+ userPrefix +'occupationIndustryType').val();
	employmentDetails.employ_count_type_data_id = $('#'+namespace+ userPrefix +'numberOfEmployeesInCompany').val(); 
	employmentDetails.annual_income = $('#'+namespace+ userPrefix +'annualIncome').val();
	employmentDetails.source_income_other_yn = $('input[name="'+ namespace + userPrefix + 'otherIncomeSource"]:checked').val();
	employmentDetails.source_income_other_details = $('#'+namespace+ userPrefix +'provideIncomeDetails').val();

	return employmentDetails;
}

/**
 * Show the Loader
 */
showLoader = function(){
	$("#"+portletNamespace+"loader").show();
}

/**
 * Hide the Loader
 */
hideLoader = function(){
	$("#"+portletNamespace+"loader").hide();
}

/**
 * Show the PAN Related Modal Details
 */
showPAN = function(divId){
	$("#"+divId).show();
}

/**
 * Hide the PAN Related Modal Details
 */
hidePAN = function(divId){
	$("#"+divId).hide();
}


/**
 * Open the Modal
 */
openKycModal = function(){
	hideLoader();
	$('#kycModal').modal('show');
}

openEkycModal = function(){
	hideLoader();
	$('#ekycModal').modal('show');
}

openEkycValidationModal = function(){
	hideLoader();
	$('#ekycValidationModal').modal('show');
}

/**
 * Close the Modal
 */
closeKycModal = function(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#kycModal').modal('hide');
	}
}

closeEkycModal = function(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#ekycModal').modal('hide');
	}
}

closeEkycValidationModal = function(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#ekycValidationModal').modal('hide');
	}
}

/**
 * Convert 01/05/2000 Format to 2000-05-01 Format
 */
function slashToHyphenDate(date){
	var parts = date.split("/");
	if(parts.length!=3){
		return date;
	}
	var formattedDate = parts[2]+"-"+ parts[1] +"-"+parts[0];
	return formattedDate;
}

$(".largeCheckbox input").on("click", function() {
	$('#spouse_permanent_address, #proposer_permanent_address, #spouse_current_address, #proposer_current_address').find('.copy-input').trigger('input');
	$('#spouse_permanent_address, #proposer_permanent_address, #spouse_current_address, #proposer_current_address').find('.copy-input').trigger('keyup');
	$('#spouse_permanent_address, #proposer_permanent_address, #spouse_current_address, #proposer_current_address').find('.copy-input').trigger('change');
	$('.copy-input-select .form-feedback-item').addClass('hide');
});

/*$(".largeCheckbox input").on("click", function() {
    $('.personal-details-button').trigger('click');
});*/
  
/**
 * Function to Copy LA Address to Proposer & Spouse Address Fields
 */
function copyLAAddress(inputId, userPrefix){
	const isChecked = $("#" + inputId).is(":checked");
	if(isChecked){
		$("#"+ portletNamespace + userPrefix + "pa_address_line_one").val($("#"+ portletNamespace + "la_pa_address_line_one").val());
		$("#"+ portletNamespace + userPrefix + "pa_address_line_two").val($("#"+ portletNamespace + "la_pa_address_line_two").val());
		$("#"+ portletNamespace + userPrefix + "pa_address_line_three").val($("#"+ portletNamespace + "la_pa_address_line_three").val());
		$("#"+ portletNamespace + userPrefix + "pa_pincode").val($("#"+ portletNamespace + "la_pa_pincode").val());
		$("#"+ portletNamespace + userPrefix + "pa_city").val($("#"+ portletNamespace + "la_pa_city").val());
		$("#"+ portletNamespace + userPrefix + "pa_state").val($("#"+ portletNamespace + "la_pa_state").val()).trigger('change');
		
		var isLaAddressSame = $('input[name="'+ portletNamespace + 'la_isAddressSame"]:checked').val() === "Y";
		var isPermanentAddress = $('input[name="'+ portletNamespace + 'la_correspondingAddress"]:checked').val() === "P";
		var isCurrentAddress = $('input[name="'+ portletNamespace + 'la_correspondingAddress"]:checked').val() === "C";
		
		if(isLaAddressSame){
			$("#"+ portletNamespace + userPrefix + "yes").attr('checked', true).trigger("click");
		}
		
		if(!isLaAddressSame){
			$("#"+ portletNamespace + userPrefix + "no").attr('checked', true).trigger("click");
			$("#"+ portletNamespace + userPrefix + "ca_address_line_one").val($("#"+ portletNamespace + "la_ca_address_line_one").val());
			$("#"+ portletNamespace + userPrefix + "ca_address_line_two").val($("#"+ portletNamespace + "la_ca_address_line_two").val());
			$("#"+ portletNamespace + userPrefix + "ca_address_line_three").val($("#"+ portletNamespace + "la_ca_address_line_three").val());
			$("#"+ portletNamespace + userPrefix + "ca_pincode").val($("#"+ portletNamespace + "la_ca_pincode").val());
			$("#"+ portletNamespace + userPrefix + "ca_city").val($("#"+ portletNamespace + "la_ca_city").val());
			$("#"+ portletNamespace + userPrefix + "ca_state").val($("#"+ portletNamespace + "la_ca_state").val()).trigger('change');	
			
			if(isPermanentAddress){
				$("#"+ portletNamespace + userPrefix + "permanent").attr('checked', true).trigger("click");
			} 
			
			if(isCurrentAddress){
				$("#"+ portletNamespace + userPrefix + "current").attr('checked', true).trigger("click");
			}
		}
	}
}
/*LDXP-1013 Changes Starts*/
function callProteanEkycApi(isLaPrSps){
	showLoader();
	var proteanEkycApiRsourceURL = $("#"+portletNamespace+"proteanEkycApiRsourceURL").val();
	var name = $("#"+portletNamespace+isLaPrSps+"_firstName").val();
	var gender = document.getElementsByName(portletNamespace+isLaPrSps+'_gender');
	var dob = $("#"+portletNamespace+isLaPrSps+"_dateOfBirth").val();
	var returnUrl = window.location.href;
	var genderVal="";
	 
    for(i = 0; i < gender.length; i++) {
        if (gender[i].checked){
        	var id = gender[i].id
        	genderVal = id.substr(id.lastIndexOf('_')+1, id.length);
        	break
        }
    }
    	
	var locationFormData = new FormData();
	locationFormData.append(portletNamespace + "name", name);
	locationFormData.append(portletNamespace + "gender", genderVal);
	locationFormData.append(portletNamespace + "dob", dob);
	locationFormData.append(portletNamespace + "isLaPrSps", isLaPrSps);
	locationFormData.append(portletNamespace + "returnUrl", returnUrl);
	
	Liferay.Util.fetch(proteanEkycApiRsourceURL, {
		body: locationFormData,
		method: 'POST'
	})
	.then((response) => {
			return response.text();
		})
		.then((response) => {
	
			const panJSON = JSON.parse(response);
			
			if(panJSON.status=="true"){
				
				window.location.href=panJSON.link;
			}
			hideLoader();
			
		})
		.catch((e) => {
			console.log(e);
			hideLoader();
		})
	
}

$(document).ready(function () {
	populateLAAddressDetails();
});

function populateLAAddressDetails(){
	
	var ekycDetails = $("#"+portletNamespace+"ekycDetailsByTxnId").val();
	var ekycPerformed = $("#"+portletNamespace+"ekycPerformed").val();
	
	console.log("ekycPerformed : "+ekycPerformed);
	console.log("ekycDetails : "+ekycDetails);
	
	if(ekycDetails!="" && ekycDetails.length>20){
	
		var ekycDetailsJson = JSON.parse(ekycDetails);
		
		console.log("ekycMessage : "+ekycDetailsJson.ekycMessage);
		console.log("ekycStatus : "+ekycDetailsJson.ekycStatus);
		console.log("isLaPrSps : "+ekycDetailsJson.isLaPrSps);
		
		if(ekycDetailsJson.ekycStatus=='Y'){
			console.log("Ekyc Success");
			
			openEkycModal();
			$("#ekycSuccess").show();
			$("#ekycFailed").hide();
			
			var isLaPrSp = ekycDetailsJson.isLaPrSps;
			
			var addressLine1 = ekycDetailsJson.house+" "+ekycDetailsJson.lm;
			addressLine1 = addressLine1.substring(0, 50);
			
			var addressLine2 = ekycDetailsJson.loc+" "+ekycDetailsJson.po;
			addressLine2 = addressLine2.substring(0, 50);
			
			var addressLine3 = ekycDetailsJson.street+" "+ekycDetailsJson.subdist;
			addressLine3 = addressLine3.substring(0, 50);
			
			$("#"+ portletNamespace + isLaPrSp+"_pa_address_line_one").val(addressLine1);
			$("#"+ portletNamespace + isLaPrSp+"_pa_address_line_two").val(addressLine2);
			$("#"+ portletNamespace + isLaPrSp+"_pa_address_line_three").val(addressLine3);
			$("#"+ portletNamespace + isLaPrSp+"_pa_pincode").val(ekycDetailsJson.pc);
			$("#"+ portletNamespace + isLaPrSp+"_pa_city").val(ekycDetailsJson.vtc+" "+ekycDetailsJson.dist);
			$("#"+ portletNamespace + isLaPrSp+"_pa_state option[data-name='"+ekycDetailsJson.state+"']").prop('selected', true).trigger('change');
			
		} else{
			//Ekyc failed
			console.log("Ekyc Failed");
			openEkycModal();
			$("#ekycSuccess").hide();
			$("#ekycFailed").show();
		}
	} else if(ekycPerformed=="true"){
		//Ekyc failed
		console.log("Ekyc Failed");
		openEkycModal();
		$("#ekycSuccess").hide();
		$("#ekycFailed").show();
	}
}		

function validateLAEkycComplete(){
	
	if(isLaPrSame){
		var ekycSuccess_la = $("#ekycSuccess_la").val();
		if(ekycSuccess_la=='false' || ekycSuccess_la=='true'){
			return "completed";
		}else{
			return "not-completed";
		}
	}	
	return "NA";
}

function validateProposerEkycComplete(){
	
	if(!isLaPrSame){
		var ekycSuccess_proposer = $("#ekycSuccess_proposer").val();
		if(ekycSuccess_proposer=='false' || ekycSuccess_proposer=='true'){
			return "completed";
		}else{
			return "not-completed";
		}
	}	
	return "NA";
}

function isEkycCompleted(){
	var laEkyc = false;
	if(validateLAEkycComplete()!="not-completed"){
		laEkyc = true
	} else {
		console.log("LA EKYC is not completed");
		$("#ekycUserValidation").html("Life Assured");
		openEkycValidationModal();
		laEkyc = false;
	}
	var proposerEkyc = false;
	if(validateProposerEkycComplete()!="not-completed"){
		proposerEkyc = true
	} else {
		console.log("Proposer Ekycs EKYC is not completed");
		$("#ekycUserValidation").html("Proposer");
		openEkycValidationModal();
		proposerEkyc = false;
	}
	
	if(laEkyc && proposerEkyc){
		return true;
	} else{
		return false;
	}	
	
	return false;
}
/*LDXP-1013 Changes Ends*/