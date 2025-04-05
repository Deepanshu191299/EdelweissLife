//Portlet Namespace
var namespace=$("#fundPortletNamespace").val();
var timer;

//Validate the requested div inputs and Enable/Diable based on the validation
function validateFundStatementForm(validationDivId,validateCompleteDiv,liferayFormId)
{
	var validationDiv=$("#"+validationDivId);
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
		$('#'+ namespace + 'fund-loader').show();
		submitDownloadFundStatementForm(liferayFormId);
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

function submitDownloadFundStatementForm(liferayFormId){
	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitDownloadFundStatementFormURL").val()

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
		$("#closeBtn").attr("onclick","closeFundModal(true)");
		$("#okayBtn").attr("onclick","closeFundModal(true)");
		openFundModal();
	});
}

function showPopUp(validationResponse){

	console.log(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		console.log("True - Successfully Sumitted");
		var documentId = validationResponse.documentID;
		var timesRun = 0;
		timer = setInterval(function() {
			timesRun += 1;
			console.log(timesRun);
			if(timesRun === 7){
				clearInterval(timer);
				console.debug("Document Not Found");
				$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
				openFundModal();
				return false;
			}
			getDownloadURL(documentId, validationResponse.policyNumber);
		}, 15000);
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.log("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openFundModal();
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openFundModal();
		return false;
	}
}

function getDownloadURL(documentId, policyNumber){
	
	var documentIdFormData = new FormData();
	documentIdFormData.append(namespace + "documentId", documentId);
	
	Liferay.Util.fetch($("#"+namespace+"downloadURLFromID").val(), {
		body: documentIdFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.log(validationResponse);
		if(validationResponse != null && validationResponse.isURLFetched){
			console.log(validationResponse.documentURL);
			$('#'+ namespace + 'fund-loader').hide();
			console.log("false - Document Upload is successful.");
			clearInterval(timer);
			return window.open(validationResponse.documentURL, "_blank");
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openFundModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.log(error);
		$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closeFundModal(true)");
		$("#okayBtn").attr("onclick","closeFundModal(true)");
		openFundModal();
	});
}

function openFundModal(){
	$('#'+ namespace + 'fund-loader').hide();
	$('#fundBackdrop').modal('show');
}

function closeFundModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#fundBackdrop').modal('hide');
	}
}