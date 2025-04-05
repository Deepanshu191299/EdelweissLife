var namespace = portletNamespace;
var selectedRidersList = new Map();
var generateBIRequestBody = {};
var basicInvestingFor = document.getElementById(namespace+"investingFor");
var editSummaryFormValidator = portletNamespace + "editSummaryForm";
var indiaSTD = "+91 ";

var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");

/*
 * Rider Related variable
 */

var riderPremiumAmountPrefixEl = namespace + "riderPremiumAmount_",
riderPremiumAmountPrefixJEl = "#" + riderPremiumAmountPrefixEl,
payorWaiverBenefitOptionJEl = "#" + namespace + "payorWaiverBenefitOption";

$(document).ready(function () {
	init();
});

/**
 * Customize Page Initilazation
 */
init = function(){
	
	/*
	 * Initializing the Event Listeners
	 */
	initEvent();
	
	/*
	 * Render Policy Term Option Dropdown and other dependent fields
	 */
	updatePolicyTermDropdown();
	
	/*
	 * Render Pay For Option Dropdown and other dependent fields
	 */
	updatePolicyForDropdown();
	
	/*
	 * Load User Selected Riders
	 */
	loadCustomersSavedRiders();
	
	/*
	 * Family Details Form pop-up initialization
	 */
	initFamilyModal();
	initFamilyModalData();
	initFamilyModalFormValidation();

	/*
	 * Validate and Update Customize page feilds and other details
	 */
	updateCustomizePage(selectedRidersList, true);
	
	/*
	 * Formate Investment Amount
	 */
	$("#"+namespace+"investmentAmount").val(Math.round($("#"+namespace+"investmentAmount").val().replaceAll(",","")).toLocaleString("en-IN"));
	
	/*
	 *Render Investment Amount in words
	 */
	populateInvestmentAmountInWord();
	
	/*
	 * Parse Amount values
	 */
	formateProductAmounts();
}

/**
 * Initialization of the Event Listeners
 */
initEvent = function(){
	
	/*
	 * Initialize Date Input formater
	 */
	$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy", prefillYear: false });
	
	/*
	 * On change event Listeners for the Customize page inputs
	 */
	$(".customizeInput").on("change",function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * Investment Options: Update other related fields on change of Policy Term
	 */
	$("#"+namespace+"policyTerm").on("change",function(){
		updatePolicyForDropdown();
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * Update Rider  for the selected payment option / Payment Mode 
	 */
	$("#"+namespace+"payFor").on("change",function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * Update Min. Investment Amount for the selected payment option / Payment Mode 
	 */
	$("#"+namespace+"paymentOption").on("change",function(){
		updateMinInvestmentAmount();
		populateInvestmentAmountInWord();
		updateCustomizePage(selectedRidersList, false);
	});
	
	$("#"+namespace+"investmentAmount").blur(function(){
		this.value = Math.round(this.value.replaceAll(",","")).toLocaleString("en-IN");
		updateMinInvestmentAmount();
		populateInvestmentAmountInWord();
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * Show Benefit Amount Breakup table model
	 */
	$("#"+namespace+"benefitAmountBreakup").on("click", function(){
		$("#planMaturityBenefitsBreakup").modal('show');
	});
	
	/*
	 * To display the Family Details Popup on change if Investing for selected to Family
	 */
	$("#"+namespace+"investingFor").on("change", function(){
		
		var investingFor = $(this).val();
		if(investingFor == "Family") {
			initFamilyModal();
			$(familyModalJEl).modal("show");
			$(this).val("Myself");
		} else {
			$(investingForFullNameJEl).val("");
			$(investingForDOBJEl).val("");
			
			$(assuranceFullNameJEl).val("");
			$(assuranceDobJEl).val("");
			
			$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
			$("input[type=radio][name='" + basicInvestingForEl + "'][value='Myself']").prop('checked', true);
			$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
			
			updateYourDetailsHeader();
			deleteFamilyDetails();
			updatePolicyTermDropdown();
			updatePolicyForDropdown();
			updateCustomizePage(selectedRidersList, false);
		}
	});
	
	/*
	 * Update Family Details input feilds Label
	 */
	$(investingForWrapperJEl).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
	
	/*
	 * Submit Customize plan details form
	 */
	$("#"+namespace+"proceedBtn").on("click", async function(){
		var generateBIRequestBody= getGenerateBIRequestBody(selectedRidersList, false)
		var hasValidationErrors = await gcapGenerateBI(generateBIRequestBody);
		if(!hasValidationErrors){
			$("#"+namespace+"customizeForm").submit();
		}
	});
	
	/*
	 * Submit Family Details
	 */
	$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
	
	/*
	 * Display selected Plan Features details 
	 */
	$(".tablinks").on("click",function(){
		 $(".tablinks").removeClass("active");
		 $(this).addClass("active");
	});
	
	/*
	 * Submit investment details and redirect to Summary Page
	 */
	$("#"+namespace+"premiumPayDetails").on("click", async function(){
		var generateBIRequestBody= getGenerateBIRequestBody(selectedRidersList, false)
		var hasValidationErrors = await gcapGenerateBI(generateBIRequestBody);
		if(!hasValidationErrors){
			$("#"+namespace+"customizeForm").submit();
		}
	});
}

/**
 * Display the Product Riders and hide product Features
 */
showProductRiders = function() {
	$("#"+namespace+"productFeatures").hide();
	$("#"+namespace+"custome-plan").show();
}

/**
 * Display the product Features and hide Product Riders
 */
showProductFeatures = function () {
	
	$("#"+namespace+"productFeatures").show();
	$("#"+namespace+"custome-plan").hide();

};

/**
 * Formate avilable product amounts 
 */
formateProductAmounts = function(){
	$(".productAmounts").each(function(i, element){
		if(!$(this).data() || !$(this).data().amount){
			$(this).html(LANG_MESSAGES["rupee-sign"].concat(Math.round(0).toLocaleString("en-IN")));
			return;
		}
	    $(this).html(LANG_MESSAGES["rupee-sign"].concat(Math.round($(this).data().amount).toLocaleString("en-IN")));
	});
}

/**
 * Polulate Investment Amount in words
 */
populateInvestmentAmountInWord = function() {
	var curInvestmentAmount = document.getElementById(namespace+"investmentAmount").value.replaceAll(",","");
	var amountInWords = getAmountInWords(curInvestmentAmount);
	$("#amt-in-word").html(rupeeSignLabel + "&nbsp;" + amountInWords);
};

/**
 * get Amount in words
 */ 
getAmountInWords = function(amount) {
    if (9 < (amount = amount.toString()).length)
        return "Overflow";
    if (n = ("000000000" + amount).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/))
        return amount = 0 != n[1] ? (primaryWord[Number(n[1])] || secondaryWord[n[1][0]] + " " + primaryWord[n[1][1]]) + "crore " : "",
        amount += 0 != n[2] ? (primaryWord[Number(n[2])] || secondaryWord[n[2][0]] + " " + primaryWord[n[2][1]]) + "lakh " : "",
        amount += 0 != n[3] ? (primaryWord[Number(n[3])] || secondaryWord[n[3][0]] + " " + primaryWord[n[3][1]]) + "thousand " : "",
        amount += 0 != n[4] ? (primaryWord[Number(n[4])] || secondaryWord[n[4][0]] + " " + primaryWord[n[4][1]]) + "hundred " : "",
        amount += 0 != n[5] ? ("" != amount ? "and " : "") + (primaryWord[Number(n[5])] || secondaryWord[n[5][0]] + " " + primaryWord[n[5][1]]) + "only " : "",
        amount.replace(/\w\S*/g, function(amount) {
            return amount.charAt(0).toUpperCase() + amount.substr(1).toLowerCase()
        });
};

/**
 * Update Customize From Feild: Update Min. Investment Amount Value
 */
updateMinInvestmentAmount = function(){
	var paymentOption = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"";
	var currentInvestmentAmount = document.getElementById(namespace+"investmentAmount").value.replaceAll(",","");
	
	if(!productInvestmentAmountRel || !productInvestmentAmountRel.length){
		return;
	}
	
	productInvestmentAmountRel.forEach(investmentAmountOption=>{
	    if(investmentAmountOption.paymentOption.key == paymentOption && Number(currentInvestmentAmount) < Number(investmentAmountOption.minimumInvestmentAmount)){
	    	document.getElementById(namespace+"investmentAmount").value = Number(investmentAmountOption.minimumInvestmentAmount).toLocaleString("en-IN");
	    }
	})
}

/**
 * Update Customize From Feild: Update policy For dropdown field
 */
updatePolicyForDropdown = function(){
	var policyForMap = new Map();
	var currentPolicyTerm = document.getElementById(namespace+"policyTerm").value;
	
	productPolicyForRel.forEach(function(policyForOption){
	    if(policyForOption.policyTerm.key == currentPolicyTerm){
	    	policyForOption.policyFor.forEach(function(policyFor){
	    		policyForMap.set(policyFor.key, policyFor);	
	    	})
	    }
	})
	
	/*
	 * Sort the Map entries
	 */
	const sortedPolicyForArray = Array.from(policyForMap).sort(([keyA, valueA], [keyB, valueB]) => {
		if (Number(keyA) < Number(keyB)) {
			return -1;
		} else if (Number(keyA) > Number(keyB)) {
			return 1;
		} else {
			return 0;
		}
	});
	
	policyForMap = new Map(sortedPolicyForArray);
	var policyForDropdownElement = document.getElementById(namespace+"payFor");
	policyForDropdownElement.innerHTML="";
	policyForMap.forEach(function(policyFor, key){
	
		const policyForOption = document.createElement("option");
		policyForOption.setAttribute("value", policyFor.key);
		policyForOption.appendChild(document.createTextNode(policyFor.name));
		
		policyForDropdownElement.appendChild(policyForOption);
    });
	
	if(policyForMap.has(defaultValues.get("policyFor"))){
		document.getElementById(namespace+"payFor").value = defaultValues.get("policyFor");
	}
}

/**
 * Update Customize From Feild: Update policy term dropdown field
 */
updatePolicyTermDropdown = function(){
	var policyTermMap = new Map();
	var age;
	
	var basicInvestingFor = $(investingForJEl).val();
	if(basicInvestingFor == "Family") {
		var investingForDOBInputVal = $(investingForDOBJEl).val();
		age = calculateAge(investingForDOBInputVal);
	}else{
		var dateOfBirthInputVal = $(dateOfBirthJEl).val();
		age = calculateAge(dateOfBirthInputVal);
	}

	productPolicyForRel.forEach(function(policyTermOption){
		if(basicInvestingFor == "Family") {
			if((Number(policyTermOption.policyTerm.key) + age) >= 18){
				policyTermMap.set(policyTermOption.policyTerm.key, policyTermOption.policyTerm.key);
		    }
		}else{
			if((Number(policyTermOption.policyTerm.key) + age) <= 70){
				policyTermMap.set(policyTermOption.policyTerm.key, policyTermOption.policyTerm.key);
		    }
		}
	})
	
	/*
	 * Sort the Map entries
	 */
	const sortedPolicyTermArray = Array.from(policyTermMap).sort(([keyA, valueA], [keyB, valueB]) => {
		if (Number(keyA) < Number(keyB)) {
			return -1;
		} else if (Number(keyA) > Number(keyB)) {
			return 1;
		} else {
			return 0;
		}
	});
	
	policyTermMap = new Map(sortedPolicyTermArray);
	var policyTermDropdownElement = document.getElementById(namespace+"policyTerm");
	policyTermDropdownElement.innerHTML="";
	policyTermMap.forEach(function(key, value){
	
		const policyTermOption = document.createElement("option");
		policyTermOption.setAttribute("value", key);
		policyTermOption.appendChild(document.createTextNode(value));
		
		policyTermDropdownElement.appendChild(policyTermOption);
    });
	
	if(policyTermMap.has(defaultValues.get("policyTerm"))){
		document.getElementById(namespace+"policyTerm").value = defaultValues.get("policyTerm");
	}
}

/**
 * Resource Call to Generate BI API for Guaranteed  Savings Star
 */
gcapGenerateBI = async function(biRequestBody) {

	showLoader();
	var hasValidationErrors = false;
	var biRequestJson = new FormData();

	biRequestJson.append(namespace + "biRequestData", JSON.stringify(biRequestBody));
	await Liferay.Util.fetch(customizePageDetails.generateBiUrl, {
		body: biRequestJson,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {

		var biResponse = response.data;
		console.log(biResponse);
		var biResponseJson = JSON.parse(biResponse["JsonOutput"]);
		hasValidationErrors = handleInputValidationStatus(biResponseJson);
		console.log(biResponseJson);
		/*
		 * If no validation error present, update Plan Premium Details and Calculate Total Premium Amount
		 */
		if(hasValidationErrors) {
			hideLoader();
			document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(0));
			return hasValidationErrors;
		}

		/*
		 * Update Riders data
		 */
		renderRiderPremiumAmount(biResponseJson);
		updateSelectedRiderPrice();
		updatePlanPremiumDetails();
		updateRidersListHiddenInput();
		updateRiderPremiumDropdowns(JSON.parse(biResponseJson.BIJson)[0].SA);

		renderMaturityBenefitsBreakup(JSON.parse(biResponseJson.BIJson));
		gssRenderIncomeBenefitsBreakup(JSON.parse(biResponseJson.BIJson));
		
		var productObj = biResponseJson.InputValidationStatus[0];
		if(productObj && productObj.BasePremium && productObj.Tax){
			document.getElementById(namespace+"investmentAmountWithTax").value = productObj.BasePremium + productObj.Tax;	
		}
		
		document.getElementById(namespace+"illustrationURL").value=biResponseJson.Url;
		$(quotationIdJEl).val(biResponseJson.QuotationId);
		hideLoader();
		
	}).catch(function(exception) {
		console.error("Error in Generate BI API");
		console.error(exception);
		document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(0));
		$("#"+namespace+"proceedBtn").prop('disabled', true);
 		$("#"+namespace+"premiumPayDetails").addClass('disabledbutton');
 		$("#errorMessageModal").modal('show');
		hideLoader();
	});
	
	return hasValidationErrors;
};

/**
 * Render Maturity breakup details table
 */
renderMaturityBenefitsBreakup = function(productBIJson){

	var maturityAmount=0;
	
	var tableBody = document.getElementById("planMaturityBenefitsBreakupTableBody");
	tableBody.innerHTML = "";
	
	if(!productBIJson || !productBIJson.length){
		return;
	}
	
	productBIJson.forEach(biJson=>{
		
		if(biJson.SAM != 0){
			maturityAmount += biJson.TOTAL_SB_G;

			const tableRecord = document.createElement("tr");
			
			var policyYearData = document.createElement("td");
			policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
			tableRecord.appendChild(policyYearData);
			
			var maturityBenefitData = document.createElement("td");
			maturityBenefitData.appendChild(document.createTextNode(biJson.TOTAL_SB_G!=0?rupeeSignLabel.concat(Math.round(biJson.TOTAL_SB_G).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(maturityBenefitData);
			
			tableBody.appendChild(tableRecord);
		}
	});
	
	document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(maturityAmount).toLocaleString("en-IN"));
	document.getElementById(namespace+"totalMaturityAmount").value=Math.round(maturityAmount);
}

/**
 * Render Plan Feature details table
 */
gssRenderIncomeBenefitsBreakup = function(productBIJson){
	
	var tableBody = document.getElementById(namespace+"planIncomeBenefitsBreakupTableBody");
	
	tableBody.innerHTML = "";
	
	if(!productBIJson || !productBIJson.length){
		return;
	}
	
	productBIJson.forEach(biJson=>{
		
		if(biJson.CUM_GA != 0 || biJson.TOTAL_SB_G!=0){
			
			const tableRecord = document.createElement("tr");
			
			var policyYearData = document.createElement("td");
			policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
			tableRecord.appendChild(policyYearData);
			
			var guaranteedAccrualBenefitData = document.createElement("td")
			guaranteedAccrualBenefitData.appendChild(document.createTextNode(biJson.CUM_GA!=0?rupeeSignLabel.concat(Math.round(biJson.CUM_GA).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(guaranteedAccrualBenefitData);
			
			var largePremiumBenefitData = document.createElement("td")
			largePremiumBenefitData.appendChild(document.createTextNode(biJson.LARGE_PREM_BEN!=0?rupeeSignLabel.concat(Math.round(biJson.LARGE_PREM_BEN).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(largePremiumBenefitData);
			
			var sumAssuredOnBenefitData = document.createElement("td")
			sumAssuredOnBenefitData.appendChild(document.createTextNode(biJson.SB_G!=0?rupeeSignLabel.concat(Math.round(biJson.SB_G).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(sumAssuredOnBenefitData);
			
			var maturityBenefitData = document.createElement("td")
			maturityBenefitData.appendChild(document.createTextNode(biJson.TOTAL_SB_G!=0?rupeeSignLabel.concat(Math.round(biJson.TOTAL_SB_G).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(maturityBenefitData);
			
			tableBody.appendChild(tableRecord);
		}
	});
}

/**
 * Generate-BI Request Body
 */
getGenerateBIRequestBody = function(requestedRidersList, loadDefaultRiderValues){
	
	//Adding Product Metadata to Request Body.
	generateBIRequestBody["PR_ID"]=productMetaData.productCode;
	
	//Adding PaymentOption, payFor & policyTerm to Request Body.
	var paymentOptionVal = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"1";
	var payFor = document.getElementById(namespace+"payFor")?document.getElementById(namespace+"payFor").value:"5";
	var policyTerm = document.getElementById(namespace+"policyTerm")?document.getElementById(namespace+"policyTerm").value:"40";
	var payoutOptionVal = document.getElementById(namespace+"payoutOption")?document.getElementById(namespace+"payoutOption").value:"1";
	
	generateBIRequestBody["PayoutOptions"]=payoutOptionVal;
	generateBIRequestBody["INPUT_MODE"]=paymentOptionVal;
	generateBIRequestBody["PR_PPT"]=payFor;
	generateBIRequestBody["PR_PT"]=policyTerm;	
	
	//Calculation related to Annual Premium & Adding it in Request Body
	var investmentAmount = Number(document.getElementById(namespace+"investmentAmount")?document.getElementById(namespace+"investmentAmount").value.replaceAll(",",""):"0");
	var annualPremium = investmentAmount;
	switch (paymentOptionVal) {
	case "2":
		annualPremium = 2 * investmentAmount / 1.024;
		break;
	case "3":
		annualPremium = 4 * investmentAmount / 1.04;
		break;
	case "4":
		annualPremium = 12 * investmentAmount / 1.056
	}
	
	generateBIRequestBody["PR_ANNPREM"]=Math.round(annualPremium);
	
	//Full name of Proposer
	var fullName = $(fullNameJEl).val();
	
	//To Calculate Age
	var dateOfBirthInputVal = $(dateOfBirthJEl).val();
	var age = calculateAge(dateOfBirthInputVal);

	//Date of Birth in String
	var dateOfBirth = moment(dateOfBirthInputVal, "DD/MM/YYYY");
	var proposerDOBStr = moment(dateOfBirth).format('DD MMM YYYY');
	
	//To assign gender
	var gender = $("input[name='" + genderEl + "']:checked").val();
	var genderShorName = gender == "Male" ? "M" : "F";

	//Mobile Number, Email & proposer is same or not.
	var mobileNumber = $(mobileNumberJEl).val();
	var email = $(emailJEl).val();
	var sameProposer = "true";
	
	generateBIRequestBody["LI_NAME"] = fullName;
	generateBIRequestBody["LI_ENTRY_AGE"] = age;
	generateBIRequestBody["LI_DOB"] = proposerDOBStr;
	generateBIRequestBody["LI_GENDER"] = genderShorName;
	generateBIRequestBody["LI_MOBILENO"] = mobileNumber;
	generateBIRequestBody["LI_EMAILID"] = email;
	generateBIRequestBody["LI_STATE"] = "0";
	
	generateBIRequestBody["PROPOSER_NAME"] = fullName;
	generateBIRequestBody["PROPOSER_AGE"] = age;
	generateBIRequestBody["PROPOSER_DOB"] = proposerDOBStr;
	generateBIRequestBody["PROPOSER_GENDER"] = genderShorName;

	var basicInvestingFor = $(investingForJEl).val();

	if(basicInvestingFor == "Family") {
		sameProposer = "false";
		var assuredRelationVal = $("input[name='" + assuredRelationEl + "']:checked").val();
		var liGenderShortName = assuredRelationVal == "Son" ? "M" : "F";
		var assuranceFullName = $(assuranceFullNameJEl).val();
		var assuranceDobInputVal = $(assuranceDobJEl).val();
		var assuranceAge = calculateAge(assuranceDobInputVal);
		
		var assuranceDob = moment(assuranceDobInputVal, "DD/MM/YYYY");
		var assuranceDobStr = moment(assuranceDob).format('DD MMM YYYY');
		
		generateBIRequestBody["LI_NAME"] = assuranceFullName;
		generateBIRequestBody["LI_ENTRY_AGE"] = assuranceAge;
		generateBIRequestBody["LI_GENDER"] = liGenderShortName;
		generateBIRequestBody["LI_DOB"] = assuranceDobStr;
	}
	
	//Update proposer value based on Investing For
	generateBIRequestBody["SameProposer"] = sameProposer;
	
	//Default values set to Request Body
	generateBIRequestBody["CashFlow"]="N";
	generateBIRequestBody["COMPANY_STATE"]="0";
	generateBIRequestBody["GSTIN"]="0";
	generateBIRequestBody["GSTIN_Number"]="0";
	generateBIRequestBody["AGENT_ID"] = "TW9001";
	generateBIRequestBody["PR_MI"]="0";
	generateBIRequestBody["PR_SA"]="";
	generateBIRequestBody["PR_SAMF"]="0";
	generateBIRequestBody["PR_ModalPrem"]="";
	generateBIRequestBody["category"]="7";
	generateBIRequestBody["PR_CHANNEL"]="11";
	generateBIRequestBody["flat_rate"]="0";
	generateBIRequestBody["emr_rate"]="1.00";
	generateBIRequestBody["REQUESTSOURCE"]="1";
	
	document.getElementsByName(namespace+"selectedRiders").forEach(availableRider=>{

		if(!availableRider.dataset){
			return;
		}
		
	    if(availableRider.dataset.isAvailable == "false" || !(loadDefaultRiderValues || requestedRidersList.has(availableRider.value))){
	    	generateBIRequestBody[availableRider.dataset.riderKey]="";
	        return;
	    }
	    
	    if(document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value)){
	    	generateBIRequestBody[availableRider.dataset.riderKey]=document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value).value;
	    }else{
	    	generateBIRequestBody[availableRider.dataset.riderKey]="1";
	    }
	});
	
	if(document.getElementById(namespace+"payorWaiverBenefitOption") && document.getElementById(namespace+"payorWaiverBenefitOption").value){
		generateBIRequestBody["PWB"]=document.getElementById(namespace+"payorWaiverBenefitOption").value;
	}
	else{
		generateBIRequestBody["PayorWaiverBenefitRider"] = "";
		generateBIRequestBody["PWB"]="";
	}
	
	generateBIRequestBody["TermRider"]="";
	generateBIRequestBody["isPdfByte"]="Y";

	console.debug(generateBIRequestBody);
	return generateBIRequestBody;
}

/**
 * Generate-BI Request Body processing: Show Validation message
 */
handleInputValidationStatus = function(biResponseData) {
	var hasErrorsInProduct = false;
	if(!biResponseData){
		return;
	}
	
	var inputValidationStatusArray = biResponseData.InputValidationStatus;
	
	var errorMessage = "";
	
	if(!biResponseData.Status == "Fail" || !inputValidationStatusArray){
		$("#"+namespace+"proceedBtn").prop('disabled', false);
		$("#"+namespace+"premiumPayDetails").removeClass('disabledbutton');
		return;
	}
	
	inputValidationStatusArray.forEach(function(inputValidation, index){
		
		/*
		 * To get the error message
		 */
		if(!errorMessage && inputValidation.ErrorMessage.length){
			errorMessage = inputValidation.ErrorMessage[0].Value;
		}else if(!errorMessage && inputValidation.GeneralError){
			errorMessage = inputValidation.GeneralError;
		}
		
		/*
		 * handle the eorrers as per the product or rider 
		 */
		if(inputValidation.ErrorMessage.length && inputValidation.ProductId == $(productCodeJEl).val()){
			document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(0));
			hasErrorsInProduct = true;
		}else if(inputValidation.ErrorMessage.length && document.getElementById(namespace+"selectedRider_"+inputValidation.ProductId)){
			 addRemoveRider(namespace+"selectedRider_"+inputValidation.ProductId,false);
		}
		
	});
	
	
	if(errorMessage){
 		$("#errorMessageModal").find("#errorMessageContainer").empty();
 		$("#errorMessageModal").find("#errorMessageContainer").html(errorMessage);
 		$("#errorMessageModal").modal('show');
	}
	
	if(hasErrorsInProduct){
		$("#"+namespace+"proceedBtn").prop('disabled', true);
 		$("#"+namespace+"premiumPayDetails").addClass('disabledbutton');
	} else {
		$("#"+namespace+"proceedBtn").prop('disabled', false);
		$("#"+namespace+"premiumPayDetails").removeClass('disabledbutton');
	}
	
	return hasErrorsInProduct;
}

/**
 * Update API: Push latest Customer details 
 */
updateLMSLead = function() {
	var proposerGender = $("input[type=radio][name="+genderEl+"]:checked").val();
	var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
	var paymentFrequencyDisplayName = $(paymentOptionJEl).find(':selected').data("name");
	var payoutOptionDisplayName = $(payoutOptionJEl).find(':selected').data("name");
	var investmentObjectiveDisplayName = $('input[name="' + investmentObjectiveEl + '"]:checked').data("name");
	var updateLMSLeadData = new FormData();
	updateLMSLeadData.append(namespace + "ChildName", $(investingForFullNameJEl).val());
	updateLMSLeadData.append(namespace + "ChildDob", $(investingForDOBJEl).val());
	updateLMSLeadData.append(genderEl, $("input[type=radio][name="+genderEl+"]:checked").val());
	updateLMSLeadData.append(fullNameEl, $(fullNameJEl).val());
	updateLMSLeadData.append(dateOfBirthEl, $(dateOfBirthJEl).val());
	updateLMSLeadData.append(investmentObjectiveEl, $("input[type=radio][name="+investmentObjectiveEl+"]:checked").val());
	updateLMSLeadData.append(emailEl, $(emailJEl).val());
	updateLMSLeadData.append(mobileNumberEl, $(mobileNumberJEl).val());
	updateLMSLeadData.append(namespace + "SumAssured", $("#"+namespace+"totalMaturityAmount").val());
	updateLMSLeadData.append(namespace + "Product_Code", $(productCodeJEl).val());
	updateLMSLeadData.append(namespace + "BIQuotationNumber", $("#"+namespace+"quotationId").val());
	updateLMSLeadData.append(namespace + "BI_PDF_PATH", $("#"+namespace+"illustrationURL").val());
	updateLMSLeadData.append(namespace + "TotalPremiumAmount", $(investmentAmountWithTaxJEl).val());
	updateLMSLeadData.append(namespace + "TotalPremiumAmountWoTax", $(investmentAmountJEl).val().replaceAll(",",""));
	updateLMSLeadData.append(namespace + "BasePremiumAmount", $(investmentAmountWithTaxJEl).val());
	updateLMSLeadData.append(namespace + "BasePremiumAmountWoTax", $(investmentAmountJEl).val().replaceAll(",",""));
	updateLMSLeadData.append(namespace + "PolicyTerm", $(policyTermJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentTerm", $("#"+namespace+"payFor").val());
	updateLMSLeadData.append(namespace + "ProductName", $(productNameJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentFrequency", paymentFrequencyDisplayName);
	updateLMSLeadData.append(namespace + "Recommended_Objective", investmentObjectiveDisplayName);
	updateLMSLeadData.append(namespace + "PayoutOptions", payoutOptionDisplayName);
	updateLMSLeadData.append(namespace + "AlternatePhone", "");
	
	if(document.getElementById(namespace+"familyIncomeBenefit") && document.getElementById(namespace+"familyIncomeBenefit").value!=3){
		updateLMSLeadData.append(namespace + "FamilyIncomeBenefit_YN", "Y");
	}else{
		updateLMSLeadData.append(namespace + "FamilyIncomeBenefit_YN", "N");
	}
	
	var proposerLARelation = "";
	var assuredRelVal = $("input[name='" + assuredRelationEl + "']:checked").val();
	if(assuredRelVal) {
		if(proposerGender == "Male" && (assuredRelVal == "Son" || assuredRelVal == "Daughter")) {
			proposerLARelation = "Father";
		} else if(proposerGender == "Female" && (assuredRelVal == "Son" || assuredRelVal == "Daughter")) {
			proposerLARelation = "Mother";
		}
		updateLMSLeadData.append(namespace + "proposerLARelation", proposerLARelation);
		updateLMSLeadData.append(namespace + "LAProposerRelation", proposerLARelation);
		updateLMSLeadData.append(namespace + "IS_LA_PR_Same_YN", "N");
	}else{
		updateLMSLeadData.append(namespace + "IS_LA_PR_Same_YN", "Y");
	}
	
	// Rider Deatails START
	var additionalBenefitVal = "No";
	if($(".riderSelectionInput").length > 0) {
		$(".riderSelectionInput").each(function(){
			var isAvailable = $(this).data("isAvailable");
			var isRiderSelected = $(this).prop("checked");
			if(isAvailable && isRiderSelected) {
				additionalBenefitVal = "Yes";
				var riderName = $(this).data("riderName");
				var riderPriceWoTax = Math.round($(this).data("riderPriceWoTax"));
				var riderPrice = Math.round($(this).data("riderPrice"));
				var riderKey = $(this).data("riderKey");
				var riderPT = $(this).data("riderPt");
				var riderPPT = $(this).data("riderPpt");
				var riderProductCode = $(this).val();
				updateLMSLeadData.append(namespace + riderKey +  "_YN", "Y");
				updateLMSLeadData.append(namespace + riderKey +  "PT", riderPT);
				updateLMSLeadData.append(namespace + riderKey +  "PPT", riderPPT);
				updateLMSLeadData.append(namespace + riderKey +  "Amount", riderPrice);
				updateLMSLeadData.append(namespace + riderKey +  "AmountWoTax", riderPriceWoTax);
				
				if($(riderPremiumAmountPrefixJEl + riderProductCode).length > 0) {
					var selectedRiderPremiumAmount = $(riderPremiumAmountPrefixJEl + riderProductCode).val();
					updateLMSLeadData.append(namespace + riderKey +  "SA", selectedRiderPremiumAmount ? selectedRiderPremiumAmount : 0);
				}
				
				if(riderKey.trim() == "PayorWaiverBenefitRider") {
					if($(payorWaiverBenefitOptionJEl).length > 0) {
						var payorWaiverBenefitOptionVal = $(payorWaiverBenefitOptionJEl).val();
						if(payorWaiverBenefitOptionVal == "1") {
							updateLMSLeadData.append(namespace + "PWbRiderOnDeath_YN", "Y");
						} else if(payorWaiverBenefitOptionVal == "2") {
							updateLMSLeadData.append(namespace +  "PWbRiderCIATPD_YN", "Y");
						} else if(payorWaiverBenefitOptionVal == "3") {
							updateLMSLeadData.append(namespace +  "PWBRiderDeathCIATPD_YN", "Y");
						}
					}
				}
			}else{
				var riderKey = $(this).data("riderKey");
				updateLMSLeadData.append(namespace + riderKey +  "_YN", "N");
			}
		});
	}
	updateLMSLeadData.append(namespace + "Additional_Benefits", additionalBenefitVal);
	// Rider Deatails END
	
	Liferay.Util.fetch(updateLMSLeadURL, {
		body: updateLMSLeadData,
		method: 'POST',
		dataType: "json",
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		console.debug(response);
	}).catch(function(exception) {
		console.error("Error in Update API");
		console.error(exception);
	});
}

/**
 * Save Investment Details and Policy Details for Customize Form Field Changes
 */
saveCustomerInvestmentData = function() {
	var customizeFormData = document.getElementById(namespace+"customizeForm");
	Liferay.Util.fetch(saveCustomerInvestmentDataURL, {
		body: new FormData(customizeFormData),
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		$("#"+namespace+"customerInvestmentDetailsId").val(response.customerInvestmentDetailsId)
		$("#"+namespace+"customerPolicyDetailsId").val(response.customerPolicyDetailsId);
	}).catch(function(exception) {
		console.error("Error in Update API");
		console.error(exception);
	});
}

/**
 * update customize page on changes of user inputs
 */
updateCustomizePage = async function(requestedRidersList, loadDefaultRiderValues){
	
	validateProductRiders();
	var hasvalidationErrors = await gcapGenerateBI(getGenerateBIRequestBody(requestedRidersList, loadDefaultRiderValues));
	if(!hasvalidationErrors){
		updateLMSLead();
		saveCustomerInvestmentData();
	}
}

/**
 * Show the Loader
 */
showLoader = function(){
	$("#preloader").addClass("preloader");
	$("#"+namespace+"tracker-loader").show();
}

/**
 * Hide the Loader
 */
hideLoader = function(){
	$("#preloader").removeClass("preloader");
	$("#"+namespace+"tracker-loader").hide();
}

/**
 * Customize Form Submit Hanlder
 */
processSubmitHandler = async function(e) {
	e.preventDefault();

	var hasValidationErrors = await generateBIRequest();
	if(!hasValidationErrors) {
		saveFundDetails();
	}
}

/************************************************************Family Details Related Java Script*********************************************************************/

/**
 * Update Your Details Header
 */
updateYourDetailsHeader = function() {
	$(personalDetailsJEl).empty()
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(fullNameJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" +  $('input[type=radio][name=' + genderEl + ']:checked').val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(dateOfBirthJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + indiaSTD + $(mobileNumberJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(emailJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + $('input[name="' + investmentObjectiveEl + '"]:checked').data("name") + "</li>");
	
	customerDob = $(dateOfBirthJEl).val();
	
	var selectedInvestingFor = $(investingForJEl).val();
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(investingForJEl).val() + "</li>");
	if(selectedInvestingFor == "Family") {
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceFullNameJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceDobJEl).val() + "</li>");
		
		customerDob = $(assuranceDobJEl).val();
	} 
}

/**
 * Init Family Modal Saved Data
 */
initFamilyModalData = function() {
	if(assuredRelationVal) {
		$("input[type=radio][name='" + familyRadioEl + "']").removeAttr("checked");
		$("input[type=radio][name='" + familyRadioEl + "'][value='" + assuredRelationVal + "']").prop('checked', true);
	}
}

/**
 * Add GrandSon and Grand Daughter for age > 40
 */
initFamilyModal = function() {
	$(investingForFullNameJEl + "-error").remove();
	$(investingForDOBJEl + "-error").remove();
	
	updateFamilyInvestingForLabels(assuredRelationVal);
}


/**
 * Initialize Family Details Modal Form Validation
 */
initFamilyModalFormValidation = function() {
	jQuery.validator.addMethod("customFullname", function(value, element) {
		if (/^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value)) {
			return true;
		} else {
			return false;
		};
	}, enterYourXFullNameErrorMsg);
	
	
	$(familyDetailsFormJEl).validate({
		errorClass: "error",
		errorElement: "div",
		onfocusout: function(element) {
           this.element(element);
        }
	});
	
	initFamilyDetailsModalValidationRules();
}

/**
 * Update Investing For Label
 */
updateFamilyInvestingForLabels = function(selectedFamilyRadioVal) {
	switch (selectedFamilyRadioVal) {
    	case "Son":
    		$(investingForFullNameLableJEl).html(sonsFullNameLabel);
    		$(investingForDOBLabelJEl).html(sonsDobLabel);
    		break;
    	case "Daughter":
    		$(investingForFullNameLableJEl).html(daughtersFullNameLabel);
    		$(investingForDOBLabelJEl).html(daughtersDobLabel);
    		break;
    }
}

/**
 * Family Pop-up Radio Buttons Change event
 */
onFamilyRadioChange = function(event) {
	var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
	initFamilyDetailsModalValidationRules();
	updateFamilyInvestingForLabels(selectedFamilyRadioVal);
}


/**
 * Validate Family Details Model Inputs
 */
initFamilyDetailsModalValidationRules = function() {
	var selectedFamilyRadioParam = $('input[type=radio][name=' + familyRadioEl + ']:checked').data("name") + "'s";
	var investingForFullNameDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", selectedFamilyRadioParam);
	var investingForDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", selectedFamilyRadioParam);

	//Remove Rules if exist
	if($(investingForFullNameJEl).rules()) {
		$(investingForFullNameJEl).rules("remove");
	}
	if($(investingForDOBJEl).rules()) {
		$(investingForDOBJEl).rules("remove");
	}
	
	$(investingForFullNameJEl + "-error").remove();
	$(investingForDOBJEl + "-error").remove();
	
	// Add Rule with updated error messages
	$(investingForFullNameJEl).rules( "add", {
		required: true,
		customFullname: true,
		messages: {
		    required: investingForFullNameDisplayMsg,
		    customFullname: investingForFullNameDisplayMsg
		}
	});
	
	$(investingForDOBJEl).rules( "add", {
		required: true,
		minDays:91,
		maxAge:17,
		messages: {
		    required: investingForDOBErrDisplayMsg,
		    minDays: ageBetween91daysto17ErrorMsg,
  			maxAge: ageBetween91daysto17ErrorMsg
		}
	});
}

/**
 * Delete Family Details If any
 */
deleteFamilyDetails = function() {
	$.ajax({
		url:deleteFamilyDetailsURL,
		type:"post",
		dataType: "json",
		success: function(response) {
			if(response.status == "success") {
				$(customerFamilyDetailsIdJEl).val("");
			}
		}
	});	
}

/**
 * Reset to My Self
 */
familyModalCloseHandler = function() {
	$(investingForJEl).val("Myself");
}

/**
 * Reset Family Modal Pop-up
 */
resetFamilyModalData = function() {
	$(investingForFullNameJEl).val("");
	$(investingForDOBJEl).val("");
	
	$("input[type=radio][name='" + familyRadioEl + "']").prop("checked", false);
	$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", true);
}

/**
 * Show Congratulation Modal if form is valid
 */
saveFamilyDetailsHanlder = function(e) {
	e.preventDefault();
	$(familyDetailsFormJEl).validate().form();
	if($(familyDetailsFormJEl).valid()) {
		saveFamilyDetails();
	} 
}

/**
 * Add or Update Family Details 
 */
saveFamilyDetails = async function() {
	showLoader();
	var selectedFamilyRadioElement = $('input[type=radio][name=' + familyRadioEl + ']:checked');
	var familyDetailsData = new FormData();
	familyDetailsData.append(namespace + "assuranceFullName", $(investingForFullNameJEl).val());
	familyDetailsData.append(namespace + "assuranceDob", $(investingForDOBJEl).val());
	familyDetailsData.append(namespace + "assuredRelation", selectedFamilyRadioElement.val());
	familyDetailsData.append(namespace + "assuredGender", selectedFamilyRadioElement.data("gender"));
	familyDetailsData.append(namespace + "customerInvestmentDetailsId", $(customerInvestmentDetailsIdJEl).val());
	familyDetailsData.append(namespace + "customerFamilyDetailsId", $(customerFamilyDetailsIdJEl).val());
	
	$(assuranceFullNameJEl).val($(investingForFullNameJEl).val());
	$(assuranceDobJEl).val($(investingForDOBJEl).val());
	
	$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
	$("input[type=radio][name='" + basicInvestingForEl + "'][value='Family']").prop('checked', true);
	
	$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
	$("input[type=radio][name='" + assuredRelationEl + "'][value='" + selectedFamilyRadioElement.val() + "']").prop('checked', true);
	
	Liferay.Util.fetch(saveFamilyDetailsURL, {
		body: familyDetailsData,
		method: 'POST',
		dataType: "json",
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		if(response.customerInvestmentDetailsId) {
			$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId);
		}
		if(response.customerFamilyDetailsId) {
			$(customerFamilyDetailsIdJEl).val(response.customerFamilyDetailsId);
		}
		
		/*
		 * Update Investing for dropdown option.
		 */
		$("#"+namespace+"investingFor").val("Family");
		
		/*
		 * Update Your Details Header after Saving Family Details
		 */
		updateYourDetailsHeader();
		
		/*
		 * Render Policy Term Option Dropdow and other dependent fields
		 */
		updatePolicyTermDropdown();
		
		/*
		 * Render Policy Term Option Dropdow and other dependent fields
		 */
		updatePolicyForDropdown();
		
		/*
		 * Call Generate BI and Update API
		 */
		updateCustomizePage(selectedRidersList, false);
		
	}).catch(function(exception) {
		console.error("Error in Save family Details Resource Call");
		console.error(exception);
	});

	$(familyModalJEl).modal("hide");
}

/**
 * Calculate Age
 */
calculateAge = function(dateOfBirthStr) {
	try {
		var dateOfBirth = moment(dateOfBirthStr, "DD/MM/YYYY");
		var dateOfBirthFormatted = moment(dateOfBirth).format('YYYY-MM-DD');
		var age = moment().diff(dateOfBirthFormatted, "years");
		return age;
	} catch(e) {
		return null;
	}
}

generateBIRequest = async function(){
	return await gcapGenerateBI(getGenerateBIRequestBody(selectedRidersList, false));
}