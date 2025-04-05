// Portlet Namespace
var namespace=$("#portletNamespace").val();

// Validate the requested div inputs and Enable/Diable based on the validation
function validateSoftLoginForm(validationDivId,validateCompleteDiv,liferayFormId)
{
	var createDateLogin=document.getElementById(namespace+"createDate-login").value;
	console.log("createDateLoginin...JS::::::"+createDateLogin);
//	sessionStorage.setItem("createDateLogin", createDateLogin);
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
		$('#'+ namespace + 'login-loader').show();
		submitSoftLoginDetailsForm(liferayFormId);
	}
	else
	{
		if(currentFormValidator.hasErrors())
		{
			try {
				currentFormValidator.focusInvalidField();
			} catch (error) {
				console.log(error);
			}
		}
		return;
	}
}

function submitSoftLoginDetailsForm(liferayFormId){

	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitSoftLoginDetailsFormURL").val();

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
		$('#'+ namespace + 'login-loader').hide();
		$("#closeBtn").attr("onclick","closeLoginModal(true)");
		$("#okayBtn").attr("onclick","closeLoginModal(true)");
		$('.fieldWrap').hide();
		$('#loginBackdrop').modal('show');
	});
}

function showPopUp(validationResponse){

	console.debug(validationResponse);
	$("#responseMessage").html('');
	$('#'+ namespace + 'otpResend').val("false");
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		
		if(validationResponse.redirectURL != null && validationResponse.redirectURL != ""){
			window.location.href = validationResponse.redirectURL;
		}else{
			console.debug("True - Successfully Sumitted");
			/*$('#'+ namespace + 'otpId').val(validationResponse.otpId);
			$('#'+ namespace + 'otp').addClass("validate");
			$('.otpWrap').show();
			$('#'+ namespace + 'login-loader').hide();*/
			window.location.href = "/renew-online";
		}
		
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		$('#'+ namespace + 'login-loader').hide();
		
		if(validationResponse.otpResend != null && validationResponse.otpResend == "true"){
			$('.otpWrap').hide();
		}
		$('#loginBackdrop').modal('show');
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		$('#'+ namespace + 'login-loader').hide();
		$('.fieldWrap').hide();
		$('#loginBackdrop').modal('show');
		return false;
	}
}

function closeLoginModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#loginBackdrop').modal('hide');
		$('.fieldWrap').show();
	}
}

function resentOTP(validationDivId,validateCompleteDiv,liferayFormId){
	$('#'+ namespace + 'otpId').val("");
	$('#'+ namespace + 'otp').removeClass("validate");
	$('#'+ namespace + 'otpResend').val("true");
	
	validateSoftLoginForm(validationDivId,validateCompleteDiv,liferayFormId);
}