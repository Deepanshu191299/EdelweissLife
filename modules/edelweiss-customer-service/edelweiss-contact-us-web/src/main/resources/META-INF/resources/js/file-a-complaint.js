//Portlet complaintNamespace
var complaintNamespace=$("#complaintPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateFileAComplaintForm(complaintValDivId, complaintValidateCompleteDiv, complaintLiferayFormId)
{
	var complaintValidationDiv=$("#"+complaintNamespace+complaintValDivId);
	var complaintValidDiv=true;
	var	complaintFormValidator;
	try {
		complaintFormValidator=Liferay.Form.get(complaintNamespace+complaintLiferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	complaintFormValidator.resetAllFields();
	var complaintCurrentInputList=complaintValidationDiv.find(".validate");
	for (i = 0; i < complaintCurrentInputList.length; i++) {
		var complaintCurrentInputId=complaintCurrentInputList[i].id;
		var complaintCurrentInputValue=complaintCurrentInputList[i].value;

		if(complaintValidateCompleteDiv || (complaintCurrentInputValue!=undefined && complaintCurrentInputValue!=''))
		{
			complaintFormValidator.validateField(complaintCurrentInputId);
		}
		else if($("#"+complaintCurrentInputId).hasClass("isMandatory"))
		{
			complaintValidDiv=false;
		}
	}
	if(complaintValidDiv && !complaintFormValidator.hasErrors())
	{
		$('#'+ complaintNamespace + 'complaint-loader').show();
		submitFileAComplaintForm(complaintLiferayFormId);
	}
	else
	{
		if(complaintFormValidator.hasErrors())
		{
			try {
				complaintFormValidator.focusInvalidField();
			} catch (error) {
				console.error(error);
			}
		}
		return;
	}
}

function submitFileAComplaintForm(complaintLiferayFormId){

	var form = new FormData(document.querySelector("#"+complaintNamespace+complaintLiferayFormId));
	var url = $("#"+complaintNamespace+"submitFileAComplaintFormURL").val()
	
	Liferay.Util.fetch(url, {
		body: form,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showComplaintPopUp(data);
	})
	
	.catch(function(error) {
		console.error(error);
		$("#complaintResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#complaintCloseBtn").attr("onclick","closeFileAComplaintModal(true)");
		$("#complaintOkayBtn").attr("onclick","closeFileAComplaintModal(true)");
		openFileAComplaintModal();
	});
}

function showComplaintPopUp(complaintResponse){

	console.debug(complaintResponse)
	$("#complaintResponseMessage").html('');
	$("#successMessage").hide();
	
	if(complaintResponse != null && complaintResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#successMessage").show();
		$("#complaintResponseMessage").html(complaintResponse.responseData);
		openFileAComplaintModal();
	}
	else if(complaintResponse != null && !complaintResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#complaintResponseMessage").html(complaintResponse.responseData);
		openFileAComplaintModal();
		return false;
	}
	else if(complaintResponse != null && complaintResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#complaintResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openFileAComplaintModal();
		return false;
	}
}

function populateComplaintSubTypes(complaintTypeId){
	
	const inputField = document.getElementById(complaintNamespace + "complaintSubtypes");
	
		$("#"+complaintNamespace+"complaintSubtypes").find('option')
		.remove()
		.end()
		.append("<option selected='true' value=''>"+ Liferay.Language.get("label-select-sub-complaint-type") +"</option>");
	
	var complaintFormData = new FormData();
	complaintFormData.append(complaintNamespace + "complaintTypeId", complaintTypeId);
	
	Liferay.Util.fetch($("#"+complaintNamespace+"subComplaintBasedOnComplaintTypeURL").val(), {
		body: complaintFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.log(validationResponse);
		if(validationResponse != null && validationResponse.isCategoryFetched){
			
			var complaintSubtypes = validationResponse.complaintSubtypes;
			console.log(complaintSubtypes);
			
			const dropdownOptions = [];
			complaintSubtypes.forEach((subtype) => {
				dropdownOptions.push(
						"<option value='" + subtype.key + "'>" + subtype.value + "</option>"
				);   
			})

			console.log(dropdownOptions);
			inputField.innerHTML = inputField.innerHTML + dropdownOptions.join('');
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#complaintResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openFileAComplaintModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.error(error);
		$("#complaintResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#complaintCloseBtn").attr("onclick","closeFileAComplaintModal(true)");
		$("#complaintOkayBtn").attr("onclick","closeFileAComplaintModal(true)");
		openFileAComplaintModal();
	});
}

function openFileAComplaintModal(){
	$('#'+ complaintNamespace + 'complaint-loader').hide();
	$('#fileAComplaintBackdrop').modal('show');
}

function closeFileAComplaintModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#fileAComplaintBackdrop').modal('hide');
	}
}