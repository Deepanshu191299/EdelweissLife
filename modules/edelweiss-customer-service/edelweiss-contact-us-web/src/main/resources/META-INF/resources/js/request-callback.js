//Portlet callbackNamespace
var callbackNamespace=$("#callbackPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateRequestACallbackForm(callbackValDivId, callbackValidateCompleteDiv, callbackLiferayFormId)
{
	var callbackValidationDiv=$("#"+callbackNamespace+callbackValDivId);
	var callbackValidDiv=true;
	var	callbackFormValidator;
	try {
		callbackFormValidator=Liferay.Form.get(callbackNamespace+callbackLiferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	callbackFormValidator.resetAllFields();
	var callbackCurrentInputList=callbackValidationDiv.find(".validate");
	for (i = 0; i < callbackCurrentInputList.length; i++) {
		var callbackCurrentInputId=callbackCurrentInputList[i].id;
		var callbackCurrentInputValue=callbackCurrentInputList[i].value;

		if(callbackValidateCompleteDiv || (callbackCurrentInputValue!=undefined && callbackCurrentInputValue!=''))
		{
			callbackFormValidator.validateField(callbackCurrentInputId);
		}
		else if($("#"+callbackCurrentInputId).hasClass("isMandatory"))
		{
			callbackValidDiv=false;
		}
	}
	if(callbackValidDiv && !callbackFormValidator.hasErrors())
	{
		$('#'+ callbackNamespace + 'callback-loader').show();
		submitRequestCallbackForm(callbackLiferayFormId);
	}
	else
	{
		if(callbackFormValidator.hasErrors())
		{
			try {
				callbackFormValidator.focusInvalidField();
			} catch (error) {
				console.error(error);
			}
		}
		return;
	}
}

function submitRequestCallbackForm(callbackLiferayFormId){

	var form = new FormData(document.querySelector("#"+callbackNamespace+callbackLiferayFormId));
	var url = $("#"+callbackNamespace+"submitRequestACallbackFormURL").val()

	Liferay.Util.fetch(url, {
		body: form,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showCallbackPopUp(data);
	})
	
	.catch(function(error) {
		console.error(error);
		$("#callbackResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#callbackCloseBtn").attr("onclick","closeRequestCallbackModal(true)");
		$("#callbackOkayBtn").attr("onclick","closeRequestCallbackModal(true)");
		openRequestCallbackModal();
	});
}

function showCallbackPopUp(callbackResponse){

	console.debug(callbackResponse)
	$("#callbackResponseMessage").html('');
	
	if(callbackResponse != null && callbackResponse.isStatusApproved){
		console.debug("True - Successfully Sumitted");
		$("#callbackResponseMessage").html(callbackResponse.responseData);
		openRequestCallbackModal();
	}
	else if(callbackResponse != null && !callbackResponse.isStatusApproved){
		console.debug("false - API Exception");
		$("#callbackResponseMessage").html(callbackResponse.responseData);
		openRequestCallbackModal();
		return false;
	}
	else if(callbackResponse != null && callbackResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#callbackResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openRequestCallbackModal();
		return false;
	}
}

function openRequestCallbackModal(){
	$('#'+ callbackNamespace + 'callback-loader').hide();
	$('#requestCallbackBackdrop').modal('show');
}

function closeRequestCallbackModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#requestCallbackBackdrop').modal('hide');
	}
}