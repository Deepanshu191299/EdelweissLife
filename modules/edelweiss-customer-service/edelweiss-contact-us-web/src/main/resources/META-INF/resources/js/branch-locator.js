//Portlet branchNamespace
var branchNamespace=$("#branchPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateSMSLocationForm(branchValDivId, branchValidateCompleteDiv, branchLiferayFormId)
{
	var branchValidationDiv = $("#"+branchNamespace+branchValDivId);
	var branchValidDiv = true;
	var	branchFormValidator;
	try {
		branchFormValidator=Liferay.Form.get(branchNamespace+branchLiferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	branchFormValidator.resetAllFields();
	var branchCurrentInputList=branchValidationDiv.find(".validate");
	for (i = 0; i < branchCurrentInputList.length; i++) {
		var branchCurrentInputId=branchCurrentInputList[i].id;
		var branchCurrentInputValue=branchCurrentInputList[i].value;

		if(branchValidateCompleteDiv || (branchCurrentInputValue!=undefined && branchCurrentInputValue!=''))
		{
			branchFormValidator.validateField(branchCurrentInputId);
		}
		else if($("#"+branchCurrentInputId).hasClass("isMandatory"))
		{
			branchValidDiv=false;
		}
	}
	if(branchValidDiv && !branchFormValidator.hasErrors())
	{
		$('#'+ branchNamespace + 'branch-loader').show();
		submitSMSLocationForm(branchLiferayFormId);
	}
	else
	{
		if(branchFormValidator.hasErrors())
		{
			try {
				branchFormValidator.focusInvalidField();
			} catch (error) {
				console.debug(error);
			}
		}
		return;
	}
}

function submitSMSLocationForm(branchLiferayFormId){

	var smsform = new FormData(document.querySelector("#"+branchNamespace+branchLiferayFormId));
	var smsUrl = $("#"+branchNamespace+"submitSMSLocationFormURL").val()

	Liferay.Util.fetch(smsUrl, {
		body: smsform,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showSMSPopUp(data);
	})
	
	.catch(function(error) {
		console.error(error);
		$("#branchResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#branchCloseBtn").attr("onclick","closeBranchLocatorModal(true)");
		openBranchLocatorModal();
	});
}

function showSMSPopUp(branchResponse){

	console.debug(branchResponse)
	$("#branchResponseMessage").html('');
	
	if(branchResponse != null && branchResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#branchResponseMessage").html(Liferay.Language.get("label-thank-you"));
		openBranchLocatorModal();
	}
	else if(branchResponse != null && !branchResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#branchResponseMessage").html(branchResponse.responseData);
		openBranchLocatorModal();
		return false;
	}
	else if(branchResponse != null && branchResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#branchResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openBranchLocatorModal();
		return false;
	}
}

function populateOptions(inputId, fieldName){
	
	if(fieldName != "area"){
		$("#"+branchNamespace+"city").find('option').remove().end()
		.append("<option selected='true' value=''>"+ Liferay.Language.get("label-select-city") +"</option>");
	}
	
	$("#"+branchNamespace+"area").find('option').remove().end()
	.append("<option selected='true' value=''>"+ Liferay.Language.get("label-select-area") +"</option>");	
	
	clearDetails();
	
	$("#"+branchNamespace+"confirmSubmit").prop('disabled', true);
	
	var branchFormData = new FormData();
	branchFormData.append(branchNamespace + "selectedId", inputId);
	branchFormData.append(branchNamespace + "fieldName", fieldName);
	
	Liferay.Util.fetch($("#"+branchNamespace+"populateOptions").val(), {
		body: branchFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.debug(validationResponse);
		if(validationResponse != null && validationResponse.isOptionsFetched){
			
			var options = validationResponse.options;
			
			const dropdownOptions = [];
			options.forEach((option) => {
				dropdownOptions.push(
						"<option value='" + option.key + "'>" + option.value + "</option>"
				);   
			})
			
			const inputField = document.getElementById(branchNamespace + fieldName);
			inputField.innerHTML = inputField.innerHTML + dropdownOptions.join('');
			
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#branchResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openBranchLocatorModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.error(error);
		$("#branchResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closeBranchLocatorModal(true)");
		openBranchLocatorModal();
	});
}

function populateBranchDetails(inputId){
	clearDetails();
	var complaintFormData = new FormData();
	complaintFormData.append(branchNamespace + "selectedId", inputId);
	complaintFormData.append(branchNamespace + "cmd", "details");
	
	Liferay.Util.fetch($("#"+branchNamespace+"populateOptions").val(), {
		body: complaintFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.debug(validationResponse);
		if(validationResponse != null && validationResponse.isOptionsFetched){
			$("#address").html(validationResponse.address);
			$("#addressDiv").show();
			$("#emailId").html(validationResponse.email);
			$("#tollFreeNo").html(validationResponse.tollFreeNo);
			
			$("#"+branchNamespace+"email").val(validationResponse.email);
			$("#"+branchNamespace+"address").val(validationResponse.address);
			$("#"+branchNamespace+"contactNo").val(validationResponse.tollFreeNo);
			$("#"+branchNamespace+"mapLink").val(validationResponse.maplink);
			
			$("#"+branchNamespace+"confirmSubmit").removeClass('disabled');
			$("#"+branchNamespace+"confirmSubmit").prop('disabled', false);
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#branchResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openBranchLocatorModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.error(error);
		$("#branchResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#branchCloseBtn").attr("onclick","closeBranchLocatorModal(true)");
		openBranchLocatorModal();
	});
}

function clearDetails(){
	$("#address").html('');
	$("#addressDiv").hide();
	$("#emailId").html('');
	$("#tollFreeNo").html('');

	$("#"+branchNamespace+"email").val('');
	$("#"+branchNamespace+"address").val('');
	$("#"+branchNamespace+"contactNo").val('');
	$("#"+branchNamespace+"mapLink").val('');
}

function openSMSField(){
	$("#smsForm").slideToggle("slow");
}

function closeSMSField(){
	$("#smsForm").slideToggle("slow");
}

function openBranchLocatorModal(){
	$('#'+ branchNamespace + 'branch-loader').hide();
	$('#branchLocatorBackdrop').modal('show');
}

function closeBranchLocatorModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#branchLocatorBackdrop').modal('hide');
	}
}