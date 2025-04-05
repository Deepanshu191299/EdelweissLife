//Portlet claimNamespace
var claimNamespace=$("#trackerPortletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateExcelForTestimonialForm(trackerValDivId, trackerValidateCompleteDiv, claimliferayFormId)
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
		convertExcelToJson(claimNamespace + "excelSheet", submitExcelForTestimonialsForm);
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

function submitExcelForTestimonialsForm(excelJSONArray){

	var trackerFormData = new FormData();
	trackerFormData.append(claimNamespace + "excelJSONArray", JSON.stringify(excelJSONArray));
	var trackerUrl = $("#"+claimNamespace+"submitExcelForTestimonialsFormURL").val();

	Liferay.Util.fetch(trackerUrl, {
		body: trackerFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((data) => {
		showTrackerPopUp(data);
	})
	
	.catch(function(error) {
		console.debug(error);
		$("#trackerResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
		$("#trackerCloseBtn").attr("onclick","closeTrackerModal(true)");
		openTrackerModal();
	});
}

function showTrackerPopUp(trackerResponse){

	console.debug(trackerResponse)
	clearResponseMessage();
	
	if(trackerResponse != null && trackerResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#successMessageStatus").html(trackerResponse.responseData);
		$("#successMessageTaskId").html(trackerResponse.responseId);
		$("#successMessage").show();
		openTrackerModal();
	}
	else if(trackerResponse != null && !trackerResponse.isFormSubmittedSuccessfully){
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

function openTrackerModal(){
	$('#'+ claimNamespace + 'tracker-loader').hide();
	$('#trackerBackdrop').modal('show');
}

function closeTrackerModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#trackerBackdrop').modal('hide');
	}
}

function convertExcelToJson(fileInputId, callbackFunction) {
	clearResponseMessage();
	var sheetNo = $("#"+claimNamespace+"sheetNo").val();
    var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.xlsx|.xls)$/;
    /*Checks whether the file is a valid excel file*/
    if (regex.test($("#"+fileInputId).val().toLowerCase())) {
        var xlsxflag = false;
        /*Flag for checking whether excel is .xls format or .xlsx format*/
        if ($("#"+fileInputId).val().toLowerCase().indexOf(".xlsx") > 0) {
            xlsxflag = true;
        }
        /*Checks whether the browser supports HTML5*/
        if (typeof (FileReader) != "undefined") {
            var reader = new FileReader();
            reader.onload = function(e) {
                var data = e.target.result;
                /*Converts the excel data in to object*/
                if (xlsxflag) {
                    var workbook = XLSX.read(data, {
                        type: 'binary'
                    });
                } else {
                    var workbook = XLS.read(data, {
                        type: 'binary'
                    });
                }
                /*Gets all the sheetnames of excel in to a variable*/
                var workbookTemp = workbook;
                var parsedExcelJsonData;
                console.debug(workbookTemp.SheetNames[sheetNo]);
                if(workbookTemp.SheetNames[sheetNo] != undefined){
                	if (xlsxflag) {
                        parsedExcelJsonData = XLSX.utils.sheet_to_json(workbook.Sheets[workbookTemp.SheetNames[sheetNo]]);
                    } else {
                        parsedExcelJsonData = XLS.utils.sheet_to_row_object_array(workbook.Sheets[workbookTemp.SheetNames[sheetNo]]);
                    }
                    console.debug(parsedExcelJsonData);
                    callbackFunction(parsedExcelJsonData);	
                    
                }else{
                	$("#trackerResponseMessage").html(Liferay.Language.get("label-please-select-correct-sheet-no"));
                	$('#'+ claimNamespace + 'tracker-loader').hide();
                	$('#trackerBackdrop').modal('show');
                	return;
                }
            }
            if (xlsxflag) {
                /*If excel file is .xlsx extension than creates a Array Buffer from excel*/
                reader.readAsArrayBuffer($("#"+fileInputId)[0].files[0]);
            } else {
                reader.readAsBinaryString($("#"+fileInputId)[0].files[0]);
            }
        } else {
        	$("#trackerResponseMessage").html(Liferay.Language.get("label-browser-does-not-support-HTML5"));
        	$('#'+ claimNamespace + 'tracker-loader').hide();
        	$('#trackerBackdrop').modal('show');
        }
    } else {
    	$("#trackerResponseMessage").html(Liferay.Language.get("label-please-select-valid-excel-file"));
    	$('#'+ claimNamespace + 'tracker-loader').hide();
    	$('#trackerBackdrop').modal('show');
    }
}

function validateField(inputId, inputValue, liferayFormId){

	var	formValidator;
	try {
		formValidator=Liferay.Form.get(claimNamespace+liferayFormId).formValidator;
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

function validateTaskIdForm(formId){
	
	var taskInputId = $("#"+claimNamespace+"taskId").attr('id');
	var taskIdVal = $("#"+claimNamespace+"taskId").val();
	
	if(!validateField(taskInputId, taskIdVal, formId)){
		return;
	}else{
		var statusFormData = new FormData();
		statusFormData.append(claimNamespace + "taskId", taskIdVal);
		var statusFetchUrl = $("#"+claimNamespace+"taskStatusFetchURL").val();

		Liferay.Util.fetch(statusFetchUrl, {
			body: statusFormData,
			method: 'POST',
		})

		.then((response) => {
			return response.json();
		})

		.then((data) => {
			showTaskStatus(data);
		})
		
		.catch(function(error) {
			console.debug(error);
			$("#trackerResponseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
			$("#trackerCloseBtn").attr("onclick","closeTrackerModal(true)");
			openTrackerModal();
		});
	}
}

function showTaskStatus(trackerResponse){

	console.debug(trackerResponse)
	clearResponseMessage();
	
	if(trackerResponse != null && trackerResponse.isFormSubmittedSuccessfully){
		console.debug("True - Successfully Sumitted");
		$("#taskId").html(trackerResponse.responseId);
		$("#taskStatus").html(trackerResponse.responseData);
		$("#statusUpdate").show();
	}
	else if(trackerResponse != null && !trackerResponse.isFormSubmittedSuccessfully){
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

function toggleDiv(div){
	if(div == "submitExcelDiv"){
		$("#checkStatusDiv").hide();
		$("#submitExcelDiv").show();
	}else if(div == "checkStatusDiv"){
		$("#submitExcelDiv").hide();
		$("#checkStatusDiv").show();
	}
}

function clearResponseMessage(){
	$("#statusUpdate").hide();
	$("#successMessage").hide();
	$("#trackerResponseMessage").html('');
}