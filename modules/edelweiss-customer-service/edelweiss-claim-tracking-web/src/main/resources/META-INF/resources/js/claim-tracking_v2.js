//Portlet claimNamespace
var claimNamespace=$("#trackerPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateClaimTrackingForm(trackerValDivId, trackerValidateCompleteDiv, claimliferayFormId)
{
	var trackerValidationDiv=$("#"+claimNamespace+trackerValDivId);
	var trackerValidDiv=true;
	var	claimTrackingFormValidator;
	try {
		claimTrackingFormValidator=Liferay.Form.get(claimNamespace+claimliferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	claimTrackingFormValidator.resetAllFields();
	var trackerCurrentInputList=trackerValidationDiv.find(".validate");
	for (i = 0; i < trackerCurrentInputList.length; i++) {
		var trackerCurrentInputId=trackerCurrentInputList[i].id;
		var trackerCurrentInputValue=trackerCurrentInputList[i].value;

		if(trackerValidateCompleteDiv || (trackerCurrentInputValue!=undefined && trackerCurrentInputValue!=''))
		{
			claimTrackingFormValidator.validateField(trackerCurrentInputId);
		}
		else if($("#"+trackerCurrentInputId).hasClass("isMandatory"))
		{
			trackerValidDiv=false;
		}
	}
	if(trackerValidDiv && !claimTrackingFormValidator.hasErrors())
	{
		$('#'+ claimNamespace + 'tracker-loader').show();
		submitClaimTrackingForm(claimliferayFormId);
	}
	else
	{
		if(claimTrackingFormValidator.hasErrors())
		{
			try {
				claimTrackingFormValidator.focusInvalidField();
			} catch (error) {
				console.debug(error);
			}
		}
		return;
	}
}

function submitClaimTrackingForm(claimliferayFormId){

	var trackerForm = new FormData(document.querySelector("#"+claimNamespace+claimliferayFormId));
	var trackerUrl = $("#"+claimNamespace+"submitClaimTrackingFormURL").val()

	Liferay.Util.fetch(trackerUrl, {
		body: trackerForm,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showTrackerPopUp(data);
	})
	
	.catch(function(error) {
		console.log(error);
		$("#trackerResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#trackerCloseBtn").attr("onclick","closeTrackerModal(true)");
		openTrackerModal();
	});
}

function showTrackerPopUp(trackerResponse){

	console.debug(trackerResponse)
	$("#trackerResponseMessage").html('');
	
	if(trackerResponse != null && trackerResponse.isStatusApproved){
		console.debug("True - Successfully Sumitted");
		$("#responseStatus").html(trackerResponse.responseData);
		openStatusDiv();
	}
	else if(trackerResponse != null && !trackerResponse.isStatusApproved){
		console.debug("false - API Exception");
		$("#trackerResponseMessage").html(trackerResponse.responseData);
		openTrackerModal();
		return false;
	}
	else if(trackerResponse != null && trackerResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#trackerResponseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		openTrackerModal();
		return false;
	}
}

function openStatusDiv(){
	$('#'+ claimNamespace + 'tracker-loader').hide();
	$('.fieldWrap').hide();
	$('.statusDiv').show();
}

function closeStatusDiv(){
	$('.statusDiv').hide();
	$('.fieldWrap').show();
}

function openTrackerModal(){
	$('#'+ claimNamespace + 'tracker-loader').hide();
	$('.fieldWrap').hide();
	$('#trackerBackdrop').modal('show');
}

function closeTrackerModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#trackerBackdrop').modal('hide');
		$('.fieldWrap').show();
	}
}