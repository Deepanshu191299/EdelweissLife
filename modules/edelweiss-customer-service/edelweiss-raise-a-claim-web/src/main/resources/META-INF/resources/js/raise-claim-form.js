//Portlet Namespace
var namespace=$("#portletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateRaiseAClaimForm(validationDivId,validateCompleteDiv,liferayFormId, doFormSubmit)
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
		if(doFormSubmit){
			$('#'+ namespace + 'loader').show();
			submitRaiseAClaimForm(liferayFormId);	
		}else{
			return true;
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

function validateField(inputId, inputValue, liferayFormId){

	var	formValidator;
	try {
		formValidator=Liferay.Form.get(namespace+liferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	
	formValidator.resetAllFields();
	formValidator.validateField(inputId);

	if(!formValidator.hasErrors()){
		return true;
	}else{
		if(formValidator.hasErrors()){
			try {
				formValidator.focusInvalidField();
			} catch (error) {
				console.debug(error);
			}
		}
		return false;
	}
}

function submitRaiseAClaimForm(liferayFormId){

	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitRaiseAClaimFormURL").val()

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
		$("#closeBtn").attr("onclick","closeModal(true)");
		$("#okayBtn").attr("onclick","closeModal(true)");
		openModal();
	});
}

function uploadDocument(files, inputId, liferayFormId){

	if(files.length < 1 ){
		$("#"+inputId).val("");
		$("#"+inputId + "Id").val("");
		$("#"+inputId + "Preview").hide();
		return;
	}
	
	$("#responseMessage").html('');
	var currentInputList = [];
	var policyInput = $("#"+namespace+"policyNumber");
	currentInputList.push(policyInput);
	var contentType = $("#"+inputId + "ContentType");
	currentInputList.push(contentType);
	var docInput = $("#"+inputId);
	currentInputList.push(docInput);
	console.debug(currentInputList);
	
	for (i = 0; i < currentInputList.length; i++) {
	
		var currentInputId = currentInputList[i].attr('id');
		var currentInputValue = currentInputList[i].val();

		if(!validateField(currentInputId, currentInputValue, liferayFormId)){
			if(currentInputId != inputId){
				$("#"+inputId).val('');
				$("#"+currentInputId).focus();
			}
			return;
		}
	}
	
	var fileName;

	for(var j = 0; j < files.length; j++){
		const file = files[j];
		fileName = file.name;
		console.debug(fileName);
	}   
	
	$('#'+ namespace + 'loader').show();
	console.debug(inputId);
	var documentType=$("#"+inputId + "DocumentType").val();
	var contentType=$("#"+inputId + "ContentType").val();
	var policyNumber=$("#"+namespace+"policyNumber").val();
	var documentTitle=fileName;

	var documentFormData = new FormData();
	documentFormData.append(namespace+"documentType", documentType);
	documentFormData.append(namespace+"contentType", contentType);
	documentFormData.append(namespace+"policyNumber", policyNumber);
	documentFormData.append(namespace+"documentTitle", documentTitle);
	
	Liferay.Util.fetch($("#"+namespace+"uploadDocumentURL").val(), {
		body: documentFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.debug(validationResponse);
		if(validationResponse != null && validationResponse.isDocumentUploadSuccess){
			console.debug(validationResponse.documentId);
			$("#"+inputId + "Id").val(validationResponse.documentId);
			$("#"+inputId + "Preview").show();
			$("#"+inputId + "Preview" + " span").html(fileName);
			$('#'+ namespace + 'loader').hide();
			console.debug("false - Document Upload is successful.");
		}
		else if(validationResponse != null && !validationResponse.isDocumentUploadSuccess ){
			console.debug("false - Document Upload is not successful.");
			$("#responseMessage").html(validationResponse.response);
			openModal();
			return false;
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.log(error);
		$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closeModal(true)");
		$("#okayBtn").attr("onclick","closeModal(true)");
		openModal();
	});
}

function showPopUp(validationResponse){

	console.debug(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#responseMessage").html(validationResponse.responseData);
		openModal();
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openModal();
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openModal();
		return false;
	}
}

function removeFile(inputId){
	fileInput = inputId.replace("Remove","");
	$("#"+fileInput).val("");
	$("#"+fileInput+"Id").val("");
	$("#"+fileInput+"Preview").hide();
}

function openModal(){
	$('#'+ namespace + 'loader').hide();
	$('#claimIntimationBackdrop').modal('show');
}

function closeModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#claimIntimationBackdrop').modal('hide');	
	}
	
}

function showClaimForm(id, shortFromDivId, fullFormDivId, validateCompleteDiv, liferayFormId){
	if(validateRaiseAClaimForm(shortFromDivId,validateCompleteDiv,liferayFormId, false)){
		$("#"+id).hide();
		$("#"+namespace+fullFormDivId).show();
	}
}

function showDeathClaim(id, deathClaimDivId){
	var	value = $("#"+id).val();
	var deathClaimDiv=$("#"+namespace+deathClaimDivId);
	console.debug(value);
	
	if(value == "death"){
		var currentInputList=deathClaimDiv.find(".deathClaim");
		for (i = 0; i < currentInputList.length; i++) {
			var currentInputId=currentInputList[i].id;
			$("#"+currentInputId).addClass("validate");
		}
		$("#"+namespace+deathClaimDivId).show();
	}else{
		var currentInputList=deathClaimDiv.find(".deathClaim");
		for (i = 0; i < currentInputList.length; i++) {
			var currentInputId=currentInputList[i].id;
			$("#"+currentInputId).removeClass("validate");
		}
		$("#"+namespace+deathClaimDivId).hide();
	}
}