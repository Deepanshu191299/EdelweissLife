//Portlet Namespace
var namespace=$("#portletNamespace").val();

//Validate the requested div inputs and Enable/Diable based on the validation
function validateRegisterEnachForm(policyNumber,validationDivId,validateCompleteDiv,liferayFormId, isAppTracker)
{
	console.log("inside validateRegisterEnachForm check::::::");
	console.log("policyNumber:::::" + policyNumber);
	
	var restrictICICIUrl = $("#"+namespace+"restrictICICIUrl").val();
	var restrictSBIUrl = $("#"+namespace+"restrictSBIUrl").val();
	console.log("restrictSBIUrl::"+ restrictSBIUrl);
	var bankBranchIFSCCode = "";
	
	if(policyNumber != ''){
		bankBranchIFSCCode = $("#"+ namespace + policyNumber + "bankBranchIFSCCode").val().toUpperCase();
		$("#"+ namespace + policyNumber + "bankBranchIFSCCode").val(bankBranchIFSCCode);
	}

	console.log("restrictICICIUrl::"+ restrictICICIUrl +"........."+"bankBranchIFSCCode:::::" + bankBranchIFSCCode );
	/*if (restrictICICIUrl=="true" &&  bankBranchIFSCCode != "" && bankBranchIFSCCode.substring(0, 4) === "ICIC") {
		openICICIPopup(policyNumber);
	}else if(restrictSBIUrl=="true" &&  bankBranchIFSCCode != "" && bankBranchIFSCCode.substring(0, 3) === "SBI"){
		console.debug("SBI is true::");
		openSBIPopup(policyNumber);
	}else*/ 
	if(bankBranchIFSCCode != "") {
		console.log("In general Digio::::");
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
			$('#'+ namespace + 'enach-loader').show();
			submitRegisterEnachForm(liferayFormId, isAppTracker);
			/*if(liferayFormId =="iciciForm"){
				console.log("inside iciciForm:::" + policyNumber);
				iciciProceedClick();
			}else if(liferayFormId =="sbiForm"){
				sbiProceedClick();
			}else{
				$('#'+ namespace + 'enach-loader').show();
				submitRegisterEnachForm(liferayFormId, isAppTracker);	
			}*/
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
}

//function added by Akash for ICICI PopUp
function openICICIPopup(policyNumber){

	var bankBranchIFSCCode = $("#"+ namespace + policyNumber + "bankBranchIFSCCode").val();
	var dob = $("#"+ policyNumber + "dob").html();
	var emailId= $("#"+ policyNumber + "emailId").html();
	var mobileNumber= $("#"+ policyNumber + "mobileNumber").html();
	var policymaxAmount= $("#"+ policyNumber + "policymaxAmount").html();

	$("#"+ namespace +"polpolicyNo").val(policyNumber);
	$("#"+ namespace +"policyDOB").val(dob);
	$("#"+ namespace +"policyMobile").val(mobileNumber);
	$("#"+ namespace +"policyEmail").val(emailId);
	$("#"+ namespace +"policymaxAmount").val(policymaxAmount);
	$("#"+ namespace +"bankIFSC").val(bankBranchIFSCCode);

	$("#iciciModal").modal("show");
}

//function added by Razina for SBI PopUp
function openSBIPopup(policyNumber){
	console.debug("inside openSBIPopup::");
	var bankBranchIFSCCode = $("#"+ namespace + policyNumber + "bankBranchIFSCCode").val();
	var dob = $("#"+ policyNumber + "dob").html();
	var emailId= $("#"+ policyNumber + "emailId").html();
	var mobileNumber= $("#"+ policyNumber + "mobileNumber").html();
	var policymaxAmount= $("#"+ policyNumber + "policymaxAmount").html();
	
	$("#"+ namespace +"polpolicyNosbi").val(policyNumber);
	$("#"+ namespace +"policyDOBsbi").val(dob);
	$("#"+ namespace +"policyMobilesbi").val(mobileNumber);
	$("#"+ namespace +"policyEmailsbi").val(emailId);
	$("#"+ namespace +"policymaxAmountsbi").val(policymaxAmount);
	$("#"+ namespace +"bankIFSC").val(bankBranchIFSCCode);
	
	$("#sbiModal").modal("show");
}

//function added by Akash for ICICI PopUp Proceed 
function iciciProceedClick(){
	
				closeIciciModal();
				$('#'+ namespace + 'enach-loader').show();
			
				var bankBranchIFSCCode = ($("#"+ namespace + "bankIFSC").val());
				var  polpolicyNo = $("#"+ namespace +"polpolicyNo").val();
				var policyDOB  = $("#"+ namespace +"policyDOB").val();
				var policyMobile = $("#"+ namespace +"policyMobile").val();
				var policyEmail = $("#"+ namespace +"policyEmail").val();
				var debitAccountNumber = $("#"+ namespace + "debitAccountNumber").val();
				var policymaxAmount =$("#"+ namespace + "policymaxAmount").val();
				var policyTerm = $("#"+ namespace +"policyTerm").val();
				var customerName =$("#"+ namespace + "customerName").val();
			
				var url = $("#"+namespace+"iciciMandateRegistrationURL").val();
				var ifscFormData = new FormData();
				
				ifscFormData.append(namespace +"polpolicyNo",polpolicyNo);
				ifscFormData.append(namespace +"policyDOB",policyDOB);
				ifscFormData.append(namespace +"policyMobile",policyMobile);
				ifscFormData.append(namespace +"policyEmail",policyEmail);
				ifscFormData.append(namespace +"debitAccountNumber",debitAccountNumber);
				ifscFormData.append(namespace +"policymaxAmount",policymaxAmount);
				ifscFormData.append(namespace +"policyTerm",policyTerm);
				ifscFormData.append(namespace +"bankBranchIFSCCode",bankBranchIFSCCode);
				ifscFormData.append(namespace +"customerName",customerName);
			
				Liferay.Util.fetch(url, {
					body: ifscFormData,
					method: 'POST'
				})
			
				.then((response) => {
					return response.json();
				})
			
				.then((data) => {
					console.debug(data);
					if(data.status){
						window.location.href=data.responseData;
					}else{
						$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
						openEnachModal();
					}
				})
				.catch(function(error) {
					console.debug(error);
					$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
					openEnachModal();
				});	  
}

//function added by Razina for SBI PopUp Proceed 
function sbiProceedClick(){
				closeSbiModal();
				$('#'+ namespace + 'enach-loader').show();
			
				var bankBranchIFSCCode = ($("#"+ namespace + "bankIFSC").val());
				var  polpolicyNo = $("#"+ namespace +"polpolicyNosbi").val();
				var policyDOB  = $("#"+ namespace +"policyDOBsbi").val();
				var policyMobile = $("#"+ namespace +"policyMobilesbi").val();
				var policyEmail = $("#"+ namespace +"policyEmailsbi").val();
				var debitAccountNumber = $("#"+ namespace + "debitAccountNumbersbi").val();
				var policymaxAmount =$("#"+ namespace + "policymaxAmountsbi").val();
				var policyTerm = $("#"+ namespace +"policyTerm").val();
				var customerName =$("#"+ namespace + "customerName").val();
				var url = $("#"+namespace+"sbiMandateRegistrationURL").val();
				console.log("sbiMandateRegistrationURL:::"+ url);
				var ifscFormData = new FormData();
			
				ifscFormData.append(namespace +"polpolicyNosbi",polpolicyNo);
				ifscFormData.append(namespace +"policyDOBsbi",policyDOB);
				ifscFormData.append(namespace +"policyMobilesbi",policyMobile);
				ifscFormData.append(namespace +"policyEmailsbi",policyEmail);
				ifscFormData.append(namespace +"debitAccountNumbersbi",debitAccountNumber);
				ifscFormData.append(namespace +"policymaxAmountsbi",policymaxAmount);
				ifscFormData.append(namespace +"policyTerm",policyTerm);
				ifscFormData.append(namespace +"bankBranchIFSCCode",bankBranchIFSCCode);
				ifscFormData.append(namespace +"customerName",customerName);
			
				console.debug("ifscFormData" + ifscFormData);
				Liferay.Util.fetch(url, {
					body: ifscFormData,
					method: 'POST'
				})
			
				.then((response) => {
					return response.json();
				})
			
				.then((data) => {
					console.debug(data);
					if(data.status){
						if(data.source=="sbi"){
							console.debug("encData" + data.encdata);
							console.debug("endpointURL" + data.endPointURL);
							$("#sbiMandateSIteRedirect_encdata").val(data.encdata);
							$("#sbiMandateSIteRedirect_merchant_code").val(data.merchantCode);
							$("#sbiMandateSIteRedirect").attr("action", data.endPointURL);
							
							$("#sbiMandateSIteRedirect").submit();
							
						} else{
							window.location.href=data.responseData;
						}
						
					}else{
						$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
						openEnachModal();
					}
				})
				.catch(function(error) {
					console.debug(error);
					$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
					openEnachModal();
				});	  
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

function submitRegisterEnachForm(liferayFormId, isAppTracker){
	console.log("..inside submitRegisterEnachForm... ");
			var form = new FormData(document.querySelector("#"+namespace+liferayFormId));
			var url = $("#"+namespace+"submitRegisterEnachFormURL").val()
		
			Liferay.Util.fetch(url, {
				body: form,
				method: 'POST',
			})
		
			.then((response) => {
				console.log(".......response submitRegisterEnachForm....",response);
				return response.json();
			})
		
			.then((data) => {
				showPopUp(data, isAppTracker);
			})
		
			.catch(function(error) {
				console.error(error);
				$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
				$("#closeBtn").attr("onclick","closeEnachModal(true)");
				$("#okayBtn").attr("onclick","closeEnachModal(true)");
				openEnachModal();
			});
}

function showPopUp(validationResponse, isAppTracker){

	console.log("validationResponse inside showPopup:::",validationResponse);
	console.log("isApptracker ;;;;;;"+ isAppTracker);
	$("#responseMessage").html('');

	if(validationResponse != null && validationResponse.isFormSubmittedSuccessfully){
		console.log("True - Successfully Sumitted");
		callDigio(validationResponse.policyNumber, validationResponse.mandateId, validationResponse.authBy, isAppTracker);
	}
	else if(validationResponse != null && !validationResponse.isFormSubmittedSuccessfully){
		console.log("false - API Exception");
		if(validationResponse?.redirected){
			console.log("session expired:::");
			$("#responseMessage").html("Session Expired, Please Re-Login");
		}
		if(validationResponse.responseData){
			callDigio(validationResponse.policyNumber, validationResponse.mandateId, validationResponse.authBy);
		}else{
			$("#responseMessage").html(validationResponse.responseData);
			openEnachModal();
			return false;
		}
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html('Internal\x20Error\x20Occured');
		openEnachModal();
		return false;
	}
}


function callDigio(policyNumber, mandateId, authby, isAppTracker){
	console.log("inside calldigio::::::");
	console.log("....policyNumber..."+ policyNumber +"...mandateId..."+ mandateId +".....authBy.."+ authby +"...isAppTracker...."+ isAppTracker);

	$('#'+ namespace + 'enach-loader').hide();

	var environmentLogo = $("#"+namespace+"environmentLogo").val();
	var environment = $("#"+namespace+"environmentName").val();

	if (!environment) {
		environment = "stage";
	}

	var options = {
			"environment": environment,
			"callback": function(t) {
				if (t.error_code != undefined) {
					reportFailureEnach(mandateId, t.message, isAppTracker);
				} else {
					if(isAppTracker == "true"){
						window.location.href="/application-tracker";
					}else{
						window.location.href="/enach-success";
					}
				}
			},
			"logo": environmentLogo
	};

	var digio = new Digio(options);
	digio.init();
	digio.esign(mandateId, authby);
}

function reportFailureEnach(mandateId, errorMessage, isAppTracker){

	console.log("isnide reportFailureEnach......."+ mandateId + "...errorMessage..."+ errorMessage + "...isApptracker.."+ isAppTracker);
	$("#responseMessage").html("failed to sign with error : " + errorMessage);
	openEnachModal();

	var failureEnachData = new FormData();
	failureEnachData.append(namespace + "mandateId", mandateId);
	failureEnachData.append(namespace + "errorMessage", errorMessage);

	Liferay.Util.fetch($("#"+namespace+"enachFailureURL").val(), {
		body: failureEnachData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.log("Failure Response" , validationResponse);
		if(isAppTracker == "true"){
			window.location.href="/application-tracker";
		}else{
			window.location.href = "/failureenach";
		}
	})

	.catch(function(error) {
		console.debug(error.errorMessage);
		$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
		$("#closeBtn").attr("onclick","closeEnachModal(true)");
		$("#okayBtn").attr("onclick","closeEnachModal(true)");
		openEnachModal();
	});
}

function populateBankName(inputId, policyNumber, liferayFormId){

	clean(policyNumber);
	var currentInputList = [];
	var bankBranchIFSCCode = $("#"+inputId);

	currentInputList.push(bankBranchIFSCCode);

	for (i = 0; i < currentInputList.length; i++) {

		var currentInputId = currentInputList[i].attr('id');
		var currentInputValue = currentInputList[i].val();

		if(!validateField(currentInputId, currentInputValue, liferayFormId)){
			return;
		}
	}

	$('#'+ namespace + 'enach-loader').show();
	var ifscFormData = new FormData();
	ifscFormData.append(namespace + "policyNumber", policyNumber);
	ifscFormData.append(bankBranchIFSCCode.attr('id'), bankBranchIFSCCode.val().toUpperCase());

	Liferay.Util.fetch($("#"+namespace+"bankNameFromIFSCURL").val(), {
		body: ifscFormData,
		method: 'POST',
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.debug(validationResponse);
		if(validationResponse != null && validationResponse.isBankNameFetched){

			$("#"+ namespace + policyNumber + "bankName").val(validationResponse.bankName);
			var premium = Number($("#"+ namespace + policyNumber + "premium").val());
			var frequency = $("#"+ namespace + policyNumber + "frequency").val();

			if(frequency.toLowerCase() == "monthly"){
				premium = premium * 12;
			}else if(frequency.toLowerCase() == "quarterly"){
				premium = premium * 4;
			}else if(frequency.toLowerCase() == "half yearly"){
				premium = premium * 2;
			}

			if(premium > 100000){
				$("input[name=" + namespace + policyNumber + "authenticationType][value=api]").prop('checked', true);
				$("input[name=" + namespace + policyNumber + "authenticationType][value=esign]").prop('disabled', true);
			}else{
				if(validationResponse.esign || validationResponse.authenticationType == "esign"){
					$("input[name=" + namespace + policyNumber + "authenticationType][value=esign]").prop('checked', true);
				}else{
					$("input[name=" + namespace + policyNumber + "authenticationType][value=api]").prop('checked', true);
					$("input[name=" + namespace + policyNumber + "authenticationType][value=esign]").prop('disabled', true);
				}
			}

			if(validationResponse.authenticationMode == "Debit Card"){
				$("input[name=" + namespace + policyNumber + "authenticationMode][value=debitcard]").prop('checked', true);
				$("input[name=" + namespace + policyNumber + "authenticationMode][value=net]").prop('disabled', true);
			}else{
				$("input[name=" + namespace + policyNumber + "authenticationMode][value=net]").prop('checked', true);
				$("input[name=" + namespace + policyNumber + "authenticationMode][value=debitcard]").prop('disabled', true);
			}

			$('#'+ namespace + 'enach-loader').hide();
			console.debug("false - Document Upload is successful.");
		}
		else if(validationResponse != null && !validationResponse.isBankNameFetched )
		{
			console.debug("false - Document Upload is not successful.");
			$('#'+ namespace + 'enach-loader').hide();
			$('#'+ namespace + 'ifscError').show();
			return false;
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#responseMessage").html('Internal\x20Error\x20Occured');
			openEnachModal();
			return false;
		}
	})

	.catch(function(error) {
		console.debug(error);
		$("#responseMessage").html('Please\x20Try\x20After\x20Some\x20Time');
		$("#closeBtn").attr("onclick","closeEnachModal(true)");
		$("#okayBtn").attr("onclick","closeEnachModal(true)");
		openEnachModal();
	});
}

function openEnachModal(){
	$('#'+ namespace + 'enach-loader').hide();
	$('#enachBackdrop').modal('show');
}

function closeEnachModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#enachBackdrop').modal('hide');
	}
}

function openBankModal(){
	$('#enachBankBackdrop').modal('show');
}

function closeBankModal(){
	$('#enachBankBackdrop').modal('hide');
}

//Function Added by akash for close model
function closeIciciModal(){
	$('#iciciModal').modal('hide');
}
//Function Ended by akash for close model

//Function Added by Razina for close model
function closeSbiModal(){
	$('#sbiModal').modal('hide');
}
function buttonReadOnly(id, policyNumber){

	if(!($("#"+ id).prop("checked"))){
		$("#"+namespace + policyNumber + "confirmSubmit").prop('disabled', true);
	}else{
		$("#"+namespace + policyNumber + "confirmSubmit").prop('disabled', false);
	}
}

function buttonReadOnly(id, policyNumber){

	if(!($("#"+ id).prop("checked"))){
		$("#"+namespace + policyNumber + "confirmSubmit").prop('disabled', true);
	}else{
		$("#"+namespace + policyNumber + "confirmSubmit").prop('disabled', false);
	}
}

function clean(policyNumber){
	$('#'+ namespace + 'ifscError').hide();
	$("#"+ namespace + policyNumber + "bankName").val('');
	$("input[name=" + namespace + policyNumber + "authenticationType][value=esign]").prop('disabled', false);
	$("input[name=" + namespace + policyNumber + "authenticationType][value=api]").prop('disabled', false);
	$("input[name=" + namespace + policyNumber + "authenticationMode][value=net]").prop('disabled', false);
	$("input[name=" + namespace + policyNumber + "authenticationMode][value=debitcard]").prop('disabled', false);
}
