//Portlet statusNamespace
var statusNamespace=$("#statusPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateCheckStatusForm(statusValDivId, statusValidateCompleteDiv, statusLiferayFormId)
{
	var statusValidationDiv=$("#"+statusNamespace+statusValDivId);
	var statusValidDiv=true;
	var	statusFormValidator;
	try {
		statusFormValidator=Liferay.Form.get(statusNamespace+statusLiferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	statusFormValidator.resetAllFields();
	var statusCurrentInputList=statusValidationDiv.find(".validate");
	for (i = 0; i < statusCurrentInputList.length; i++) {
		var statusCurrentInputId=statusCurrentInputList[i].id;
		var statusCurrentInputValue=statusCurrentInputList[i].value;

		if(statusValidateCompleteDiv || (statusCurrentInputValue!=undefined && statusCurrentInputValue!=''))
		{
			statusFormValidator.validateField(statusCurrentInputId);
		}
		else if($("#"+statusCurrentInputId).hasClass("isMandatory"))
		{
			statusValidDiv=false;
		}
	}
	if(statusValidDiv && !statusFormValidator.hasErrors())
	{
		$('#'+ statusNamespace + 'status-loader').show();
		submitCheckStatusForm(statusLiferayFormId);
	}
	else
	{
		if(statusFormValidator.hasErrors())
		{
			try {
				statusFormValidator.focusInvalidField();
			} catch (error) {
				console.error(error);
			}
		}
		return;
	}
}

function submitCheckStatusForm(statusLiferayFormId){

	var form = new FormData(document.querySelector("#"+statusNamespace+statusLiferayFormId));
	var url = $("#"+statusNamespace+"submitCheckStatusFormURL").val()

	Liferay.Util.fetch(url, {
		body: form,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showStatusPopUp(data);
	})
	
	.catch(function(error) {
		console.error(error);
		$("#statusResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#statusCloseBtn").attr("onclick","closeCheckStatusModal(true)");
		$("#statusOkayBtn").attr("onclick","closeCheckStatusModal(true)");
		openCheckStatusModal();
	});
}

function showStatusPopUp(statusResponse){

	console.debug(statusResponse)
	$("#statusResponseMessage").html('');
	removeComplaintDetails();
	closeComplaintDetailsDiv();
	
	if(statusResponse != null && statusResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		setComplaintDetails(statusResponse.complaintJson);
		openComplaintDetailsDiv();
	}
	else if(statusResponse != null && !statusResponse.isFormSubmittedSuccessfully){
		console.debug("false - API Exception");
		$("#statusResponseMessage").html(statusResponse.responseData);
		openCheckStatusModal();
		return false;
	}
	else if(statusResponse != null && statusResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#statusResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openCheckStatusModal();
		return false;
	}
}

function openComplaintDetailsDiv(){
	$('#'+ statusNamespace + 'status-loader').hide();
	$('.complainFormDiv').hide();
	$('.complainDetailsDiv').show();
}

function closeComplaintDetailsDiv(){
	$('.complainDetailsDiv').hide();
	$('.complainFormDiv').show();
}

function setComplaintDetails(complaintJson){
	$("#category").html(complaintJson.category);
	$("#policyNumber").html(complaintJson.policyNumber);
	$("#irdaTokenNumber").html((complaintJson.complaintDetails.irdaTokenNumber == null) ? "null" : complaintJson.complaintDetails.irdaTokenNumber);
	$("#srNumber").html(complaintJson.srNumber);
	$("#status").html(complaintJson.status);
}

function removeComplaintDetails(){
	$("#category").html();
	$("#policyNumber").html();
	$("#irdaTokenNumber").html();
	$("#srNumber").html();
	$("#status").html();
}

function openCheckStatusModal(){
	$('#'+ statusNamespace + 'status-loader').hide();
	$('#checkStatusBackdrop').modal('show');
}

function closeCheckStatusModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#checkStatusBackdrop').modal('hide');
	}
}