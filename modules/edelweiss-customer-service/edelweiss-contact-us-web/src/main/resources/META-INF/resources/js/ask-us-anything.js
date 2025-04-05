//Portlet askUsNamespace
var askUsNamespace=$("#askusPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateAskUsAnythingForm(askUsValidationDivId, askUsValidateCompleteDiv, askUsLiferayFormId)
{
	var ashUsValidationDiv =$("#"+askUsNamespace + askUsValidationDivId);
	var askUsValidDiv = true;
	var	askUsCurrentFormValidator;
	try {
		askUsCurrentFormValidator=Liferay.Form.get(askUsNamespace + askUsLiferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	askUsCurrentFormValidator.resetAllFields();
	var askUsCurrentInputList=ashUsValidationDiv.find(".validate");
	for (i = 0; i < askUsCurrentInputList.length; i++) {
		var askUsCurrentInputId = askUsCurrentInputList[i].id;
		var askUsCurrentInputValue = askUsCurrentInputList[i].value;

		if(askUsValidateCompleteDiv || (askUsCurrentInputValue!=undefined && askUsCurrentInputValue!=''))
		{
			askUsCurrentFormValidator.validateField(askUsCurrentInputId);
		}
		else if($("#"+askUsCurrentInputId).hasClass("isMandatory"))
		{
			askUsValidDiv=false;
		}
	}
	if(askUsValidDiv && !askUsCurrentFormValidator.hasErrors())
	{
		$('#'+ askUsNamespace + 'askus-loader').show();
		submitAskUsAnythingForm(askUsLiferayFormId);
	}
	else
	{
		if(askUsCurrentFormValidator.hasErrors())
		{
			try {
				askUsCurrentFormValidator.focusInvalidField();
			} catch (error) {
				console.debug(error);
			}
		}
		return;
	}
}

function submitAskUsAnythingForm(askUsLiferayFormId){

	var askUsForm = new FormData(document.querySelector("#"+askUsNamespace+askUsLiferayFormId));
	var askUsUrl = $("#"+askUsNamespace+"submitAskUsAnythingFormURL").val()

	Liferay.Util.fetch(askUsUrl, {
		body: askUsForm,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showAskUsPopUp(data);
	})
	
	.catch(function(error) {
		console.debug(error);
		$("#askUsResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#askUsCloseBtn").attr("onclick","closeAskUsAnythingModal(true)");
		$("#askUsOkayBtn").attr("onclick","closeAskUsAnythingModal(true)");
		openAskUsAnythingModal();
	});
}

function showAskUsPopUp(validationResponse){

	console.debug(validationResponse)
	$("#askUsResponseMessage").html('');
	
	if(validationResponse != null && validationResponse.isStatusApproved){
		console.debug("True - Successfully Sumitted");
		$("#askUsResponseMessage").html(validationResponse.responseData);
		openAskUsAnythingModal();
	}
	else if(validationResponse != null && !validationResponse.isStatusApproved){
		console.debug("false - API Exception");
		$("#askUsResponseMessage").html(validationResponse.responseData);
		openAskUsAnythingModal();
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#askUsResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openAskUsAnythingModal();
		return false;
	}
}

function openAskUsAnythingModal(){
	$('#'+ askUsNamespace + 'askus-loader').hide();
	$('#askusAnythingBackdrop').modal('show');
}

function closeAskUsAnythingModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#askusAnythingBackdrop').modal('hide');
	}
}