//Portlet Namespace
var namespace=$("#resumePortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateResumeApplicationForm(validationDivId,validateCompleteDiv,liferayFormId)
{
	var validationDiv=$("#"+namespace+validationDivId);
	var validDiv=true;
	var	currentFormValidator;
	try {
		currentFormValidator=Liferay.Form.get(namespace+liferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	currentFormValidator.resetAllFields();
	var currentInputList=validationDiv.find(".validate");
	for (i = 0; i < currentInputList.length; i++) {
		var currentInputId=currentInputList[i].id;
		var currentInputValue=currentInputList[i].value;

		if(validateCompleteDiv || (currentInputValue!=undefined && currentInputValue!=''))
		{
			currentFormValidator.validateField(currentInputId);
		}
		else if($("#"+currentInputId).hasClass("isMandatory"))
		{
			validDiv=false;
		}
	}
	if(validDiv && !currentFormValidator.hasErrors())
	{
		$('#'+ namespace + 'resume-loader').show();
		submitUpdateContactDetailForm(liferayFormId);
	}
	else
	{
		if(currentFormValidator.hasErrors())
		{
			try {
				currentFormValidator.focusInvalidField();
			} catch (error) {
				console.error(error);
			}
		}
		return;
	}
}

function submitUpdateContactDetailForm(liferayFormId){

	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitResumeApplicationFormURL").val()

	Liferay.Util.fetch(url, {
		body: form,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showPopUp(data);
	})
	
	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closeApplicationModal(true)");
		openApplicationModal();
	});
}


function showPopUp(validationResponse){

	console.debug(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isURLFetched){
		console.debug("True - Successfully Sumitted");
		window.location.href = validationResponse.redirectURL;
		
	}else if(validationResponse != null && !validationResponse.isURLFetched){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openApplicationModal();
		return false;
		
	}else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openApplicationModal();
		return false;
	}
}

function openApplicationModal(){
	$('#'+ namespace + 'resume-loader').hide();
	$('#resumeApplicationBackdrop').modal('show');
}

function closeApplicationModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#resumeApplicationBackdrop').modal('hide');
	}
}