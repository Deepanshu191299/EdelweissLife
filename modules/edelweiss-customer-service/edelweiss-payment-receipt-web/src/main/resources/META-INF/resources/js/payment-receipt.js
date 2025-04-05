//Portlet Namespace
var namespace=$("#paymentPortletNamespace").val();
var timer;

//Validate the requested div inputs and Enable/Diable based on the validation
function validatePaymentReceiptForm(validationDivId,validateCompleteDiv,liferayFormId)
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
		$('#'+ namespace + 'payment-loader').show();
		submitPaymentReceiptForm(liferayFormId);
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

function submitPaymentReceiptForm(liferayFormId){
	var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
	var url = $("#"+namespace+"submitDownloadPaymentReceiptFormURL").val()

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
		$("#closeBtn").attr("onclick","closePaymentModal(true)");
		$("#okayBtn").attr("onclick","closePaymentModal(true)");
		openPaymentModal();
	});
}

function showPopUp(validationResponse){

	console.debug(validationResponse)
	$("#responseMessage").html('');
	
	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		
		if(validationResponse.documentID != null || validationResponse.documentID != "" 
			&& validationResponse.documentData != null || validationResponse.documentData != ""){
			var link = document.createElement('a');
			link.href = validationResponse.documentData;
			link.download = validationResponse.documentID;
			link.dispatchEvent(new MouseEvent('click'));
			$('#'+ namespace + 'payment-loader').hide();
			
		}else if(validationResponse.documentID != null || validationResponse.documentID != ""){
			console.debug("True - Successfully Sumitted");
			var documentId = validationResponse.documentID;
			var timesRun = 0;
			timer = setInterval(function() {
				timesRun += 1;
				console.debug(timesRun);
				if(timesRun === 7){
					clearInterval(timer);
					console.debug("Document Not Found");
					$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
					openPaymentModal();
					return false;
				}
				getDownloadURL(documentId, validationResponse.policyNumber);
			}, 15000);
		}
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		openPaymentModal();
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-something-went-wrong-please-try-again-later-or-call-us-on"));
		openPaymentModal();
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
		console.debug(validationResponse);
		if(validationResponse != null && validationResponse.isURLFetched){
			console.debug(validationResponse.documentURL);
			$('#'+ namespace + 'payment-loader').hide();
			console.debug("false - Document Upload is successful.");
			clearInterval(timer);
			return window.open(validationResponse.documentURL, "_blank");
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
			openPaymentModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#closeBtn").attr("onclick","closePaymentModal(true)");
		$("#okayBtn").attr("onclick","closePaymentModal(true)");
		openPaymentModal();
	});
}

function openPaymentModal(){
	$('#'+ namespace + 'payment-loader').hide();
	$('#paymentBackdrop').modal('show');
}

function closePaymentModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#paymentBackdrop').modal('hide');
	}
}