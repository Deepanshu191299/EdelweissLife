//Portlet Namespace
var namespace=$("#quizPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validatePersonalityQuizForm(validationDivId, nextDivId, validateCompleteDiv, liferayFormId, finalStep)
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

		if(currentInputList[i].type=="radio")
		{
			currentInputValue=$("input[name="+currentInputList[i].name+"]:checked").val();
		}
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
		if(finalStep){
			$('#'+ namespace + 'quiz-loader').show();
			submitPersonalityQuizForm(liferayFormId);
		}else{
			const list = document.querySelectorAll('.quiz_step');
			Array.prototype.forEach.call(list,(e)=>{   
		        e.classList.add('quiz_step_hide');       
			})
			$("#"+namespace+nextDivId).removeClass('quiz_step_hide');
		}
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

function submitPersonalityQuizForm(liferayFormId){

	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	form.append(namespace + "browserDetails", navigator.appCodeName+"/"+navigator.appVersion);
	var url = $("#"+namespace+"submitPersonalityQuizFormURL").val()

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
		$("#closeBtn").attr("onclick","closePersonalityQuizModal(true)");
		$("#okayBtn").attr("onclick","closePersonalityQuizModal(true)");
		openPersonalityQuizModal();
	});
}


function showPopUp(validationResponse){

	console.debug(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		window.location.href = themeDisplay.getLayoutURL() + "/" + validationResponse.persona;
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openPersonalityQuizModal();
		return false;
	}else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openPersonalityQuizModal();
		return false;
	}
}

function openPersonalityQuizModal(){
	$('#'+ namespace + 'quiz-loader').hide();
	$('#personalityQuizBackdrop').modal('show');
}

function closePersonalityQuizModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#personalityQuizBackdrop').modal('hide');
	}
}