//Portlet Namespace
var namespace=$("#advisorPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateBecomeAnAdvisorForm(validationDivId,validateCompleteDiv,liferayFormId)
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
		$('#'+ namespace + 'advisor-loader').show();
		submitBecomeAnAdvisorForm(liferayFormId);
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
		return;
	}
}

function submitBecomeAnAdvisorForm(liferayFormId){

	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitBecomeAnAdvisorFormURL").val()

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
		console.log(error);
		$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closeBecomeAnAdvisorModal(true)");
		$("#okayBtn").attr("onclick","closeBecomeAnAdvisorModal(true)");
		openBecomeAnAdvisorModal();
	});
}


function showPopUp(validationResponse){

	console.debug(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#responseMessage").html(Liferay.Language.get("label-thank-you"));
		openBecomeAnAdvisorModal();
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openBecomeAnAdvisorModal();
		return false;
	}else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openBecomeAnAdvisorModal();
		return false;
	}
}

function resetForm(validationDivId){
	
	var validationDiv=$("#"+namespace+validationDivId);
	var currentInputList=validationDiv.find(".validate");
	
	for (i = 0; i < currentInputList.length; i++) {
		currentInputList[i].value="";
	}
}

function openBecomeAnAdvisorModal(){
	$('#'+ namespace + 'advisor-loader').hide();
	$('#becomeAnAdvisorBackdrop').modal('show');
}

function closeBecomeAnAdvisorModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#becomeAnAdvisorBackdrop').modal('hide');
	}
}