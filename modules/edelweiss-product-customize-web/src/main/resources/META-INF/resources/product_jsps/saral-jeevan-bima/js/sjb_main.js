var occuaptionValidator = portletNamespace+"occupation";
var stage = "Customise";
var occupationValidatorJEl = "#"+occuaptionValidator;
var educationQualificationValidator = portletNamespace+"educationQualification";
var educationQualificationValidatorJEl = "#"+ educationQualificationValidator;
var spouseParentOccupationValidator = portletNamespace+"spouseParentOccupation";
var spouseParentOccupationvalidatorJEl = "#" + spouseParentOccupationValidator;
var dateOfBirthValidator = portletNamespace + "dateOfBirth";
var dateOfBirthValidatorJEl = "#" + dateOfBirthValidator;
var annualIncomeValidator = portletNamespace + "annualIncome";
var annualIncomeValidatorJEl = "#"+ annualIncomeValidator;
var gender = portletNamespace + "gender";
var smoker = portletNamespace + "smoker";
var ProductListRequestBody = {};
var ProductModificationRequestBody = {};
var policyTermJEl = "#" + portletNamespace + "policyTerm";
var transactions = [];

var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");

var familyRadioEl = portletNamespace + "familyRadio";
var investmentObjectiveEl = portletNamespace + "investmentObjective";
var investingForFullNameEl = portletNamespace + "investingForFullName";
var investingForFullNameJEl = "#" + investingForFullNameEl;
var investingForDOBEl = portletNamespace + "investingForDOB";
var investingForDOBJEl = "#" + investingForDOBEl;
var productNameJEl = "#" + portletNamespace + "productName";
var assuredRelationEl = portletNamespace + "assuredRelation";

var activeDivElement = activeDiv;
var sequence1;
var totalGuaranteedBenefitAmountVal = document.getElementById(portletNamespace+"totalGuaranteedBenefitAmount");
var preiumAmountVal = document.getElementById("premium-amount");
var gstVal = document.getElementById("gst");
var totalPremiumVal = document.getElementById("total-premium");




$(document).ready(function () {
	init();
});


init = function(){
	
	/*
	 * Initializing the Event Listeners
	 */
	initEvent();
	
	/*
	 * on page load
	 */
	updateMinLifeCover();
	populateLifeCoverInWord();
	updatePolicyTermDropdown();
	showCardValues();
	initSavedProductPlan();
	formateProductAmounts()

	/*
	 * Call the required ETLI APIs to render the product details
	 */
	initCustomizePage();
	
	/*
	 * Formate Investment Amount
	 */
	$(lifeCoverJEl).val(Math.round($(lifeCoverJEl).val().replaceAll(",","")).toLocaleString("en-IN"));
	
	/*
	 * Sort dropdown fields
	 */
	sortEducationDrodownOptions();
	sortNatureOfDutyDrodownOptions();
}

/*
 * initializing the event listner
 */
initEvent = function(){
	
	$(policyTermJEl).on("change", UpdateCustomizePage);
	$(paymentOptionJEl).on("change",UpdateCustomizePage);
	
	$(lifeCoverJEl).blur(function(){
		this.value = this.value.replaceAll(",","");

		updateMinLifeCover();
		populateLifeCoverInWord();
		UpdateCustomizePage();
		
		this.value = Math.round(this.value.replaceAll(",","")).toLocaleString("en-IN");

	});

	$(".planProceedBtn").on("click",submitCustomizePage);
}

/*
 * Sort Edication dropdown
 */
sortEducationDrodownOptions = function(){
	var educationQualificationValue = $("#"+portletNamespace+"educationQualification").val();
	var educationQualificationDropdown = $("#"+portletNamespace+"educationQualification");
	var dropdownOptions = educationQualificationDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  var aValue = parseInt($(a).val());
	  var bValue = parseInt($(b).val());

	  return bValue - aValue;
	});
	educationQualificationDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"educationQualification").val(educationQualificationValue);
}

/*
 * Sort Nature of Duty Dropdown
 */
sortNatureOfDutyDrodownOptions = function(){
	var natureOfDutyValue = $("#"+portletNamespace+"natureOfDuty").val();
	var natureOfDutyDropdown = $("#"+portletNamespace+"natureOfDuty");
	var dropdownOptions = natureOfDutyDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  return a.textContent.localeCompare(b.textContent)
	});
	natureOfDutyDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"natureOfDuty").val(natureOfDutyValue);
}

/**
 * Update Customize From Feild: Update Min. Investment Amount Value
 */
updateMinLifeCover = function(){
	var lifeCover = $(lifeCoverJEl).val() ? parseInt($(lifeCoverJEl).val()) : 0;
	lifeCover = 5E4 * Math.ceil(Number(lifeCover) / 5E4);
	if (lifeCover < 500000) {
		lifeCover = "500000";
	} else if(lifeCover > 2500000){
		lifeCover = "2500000";
	}
	$(lifeCoverJEl).val(lifeCover);
}

/**
 * Polulate Investment Amount in words
 */
populateLifeCoverInWord = function() {
	var curLifeCover = document.getElementById(lifeCoverEl).value.replaceAll(",","");
	var amountInWords = getAmountInWords(curLifeCover);
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

/*
 * Polulate Amount in words
 */
$("input.amt-in-word").keyup(function(){
	var currentAmount = $(this).val().replaceAll(",","");
	if(!currentAmount || !$(this).data() || !$(this).data().amtInWords){
		$("#"+$(this).data().amtInWords).html("");
		return;
	}
	
	var amountInWords = getAmountInWords(currentAmount);
	$("#"+$(this).data().amtInWords).html(rupeeSignLabel + "&nbsp;" + amountInWords);
});
$("input.amt-in-word[value!='']").trigger("keyup");

/**
 * Load Policy Terms
 */	
updatePolicyTermDropdown = function(){
	
	var selectedPolicyTerm = $(policyTermJEl).val()?$(policyTermJEl).val():defaultPolicyTerm;
	
	if(!productValidation){
		return;
	}
	var age = calculateAge(customerDob);
	
	$(policyTermJEl).find("option").remove();
	for (let curPolicyTerm = Number(productValidation.maxPT); 
	curPolicyTerm >= Number(productValidation.minPT); curPolicyTerm--) {
		if(curPolicyTerm+age>productValidation.maxMaturityAge || curPolicyTerm+age<productValidation.minMaturityAge){
			continue;
		}
		var selected = curPolicyTerm == selectedPolicyTerm ? " selected" : "";
	 	$(policyTermJEl).append('<option value="' + curPolicyTerm + '" ' + selected + '>' + curPolicyTerm + '</option>');
	}
	
};

/*
 * Call Liferay & ETLI APIs on page load
 */
initCustomizePage = async function(){
	var hasErrors = await fetchSJBProductListing(getProductListRequestBody());
	if(hasErrors){
		console.error("error in Product Listing API call");
		return;
	}
	
	hasErrors = await fetchSJBProductModification(getProductModificationRequestBody());
	if(hasErrors){
		console.error("error in Product Modification API call");
		return;
	}
	
	/*
	 * Save user inputs in Liferay
	 */
	updateLMSLead();
	saveCustomerInvestmentData();
}


/*
 * Call Liferay & ETLI APIs on user input changes
 */
UpdateCustomizePage = async function(){
	var hasErrors = await fetchSJBProductModification(getProductModificationRequestBody());
	if(hasErrors){
		console.error("error in Product Modification API call");
		return;
	}
	
	/*
	 * Save user inputs in Liferay
	 */
	updateLMSLead();
	saveCustomerInvestmentData();
	
	formateProductAmounts();
	
}

/*
 * Submit Customize Form
 */
submitCustomizePage = async function(){
	var hasErrors = await fetchSJBProductModification(getProductModificationRequestBody());
	if(hasErrors){
		console.error("error in Product Modification API call");
		return;
	}
	
	var generateBIRequestBody= getSJBGenerateBIRequestBody()
	hasErrors = await sjbGenerateBI(generateBIRequestBody);
	if(hasErrors){
		console.error("error in Generate BI API call");
		return;
	}
	
	/*
	 * Submit Data and Redirect to Summary page
	 */
	stage="Summary";
	updateLMSLead();
	formateProductAmounts();
	//callSJBWebEngage();
	
	$("#"+namespace+"customizeForm").submit();
	
}

/*
 * generate Request body for Product Listing API
 */
getProductListRequestBody = function(){
	var gender = $("input[type=radio][name="+genderEl+"]:checked").val();
	var genderValue = "O";
	if(gender==="3"){
		genderValue="M";
	}
	else if(gender==="4"){
		genderValue="F";
	}
	else{
		genderValue="O";
	}
	
	ProductListRequestBody["AnnualIncome"] = parseInt($(annualIncomeValidatorJEl).val());
	ProductListRequestBody["Education"] = $(educationQualificationValidatorJEl).val();
	ProductListRequestBody["FilterProductID"] ="40039";
	ProductListRequestBody["GoalId"] ="1";
	ProductListRequestBody["LifeAssuredAge"] = calculateAge(customerDob);
	ProductListRequestBody["LifeAssuredGender"] = genderValue;
	ProductListRequestBody["MaritalStatusId"] = 1;
	ProductListRequestBody["ModeInput"] ="4";
	ProductListRequestBody["Occupation"] = $(occupationValidatorJEl).val();
	ProductListRequestBody["SecondaryOccupation"] = $(spouseParentOccupationvalidatorJEl).val() != "" ? $(spouseParentOccupationvalidatorJEl).val() :  $(occupationValidatorJEl).val();
	ProductListRequestBody["SpouseAge"] = -1;
	ProductListRequestBody["SpouseGender"] ="";
	ProductListRequestBody["isSmoker"] = $("input[type=radio][name="+smoker+"]:checked").val();
	ProductListRequestBody["isSpouseSmoker"] = -1;
	return ProductListRequestBody;
}

/*
 * Product listing API
 */
fetchSJBProductListing = async function(plRequestbody) {
	showLoader();
	
	if(activeDiv==="productForSingle"){
		sequence1 = 4;
	}
	else if(activeDiv==="productForRegular"){
		sequence1 = 1
	}
	else if(activeDiv === "productFor10"){
		sequence1 = 3
	}
	else if( activeDiv=== "productFor5"){
		sequence1 = 2
	}
	var hasValidationErrors = false;
	var plRequestJson = new FormData();
	plRequestJson.append(namespace + "plRequestJsonString", JSON.stringify(plRequestbody));
	await Liferay.Util.fetch(saralJeevanBimaPLURL, {
		body: plRequestJson,
		method: 'POST',
		async: false
	}).then(function(response) {
		return response.json();
	}).then(function(response) {

		var plResponseJson = JSON.stringify(response);
		console.log(plResponseJson);
		console.log("-----------------------------------");
		var plResponseObj = JSON.parse(plResponseJson);
		const productDetails = plResponseObj.plResponse.ProdDetails;
		console.log("productDetails is "+productDetails);
		
		productDetails.forEach((productDetail, index) => {
			const biresponse = JSON.parse(productDetail.BIJson);
			var sequence = productDetail.Sequence;
			var transactionId = productDetail.transactionid;
			transactions.push(transactionId);
			console.log(sequence);
			console.log(biresponse);
			if (biresponse && Array.isArray(biresponse)) {
				const bi = biresponse[0];
				const modalPremTax = bi.MODAL_PREM_TAX;
				const modalPremium = bi.MODAL_PREM;
				const gst = bi.TAX_MP;
				const totalPremium = bi.TOTAL_MODAL_PREM_TAX;
				console.log("the amount is"+ modalPremTax);

				if(sequence == sequence1){
					totalGuaranteedBenefitAmountVal.textContent = Math.round(modalPremTax).toLocaleString("en-IN");
					preiumAmountVal.textContent = Math.round(modalPremium).toLocaleString("en-IN");
					gstVal.textContent = Math.round(gst).toLocaleString("en-IN");
					totalPremiumVal.textContent = Math.round(totalPremium).toLocaleString("en-IN");

				}
				let spanId;
				let premiumId;
				let gstId;
				let totalId;

				if (sequence === 1) {
					spanId = "forRegular";
					premiumId = "premiumRegular";
					gstId = "gstRegular";
					totalId = "totalRegular";
				} else if (sequence === 2) {
					spanId = "for5";
					premiumId = "premium5";
					gstId = "gst5";
					totalId = "total5";
				} else if (sequence === 3) {
					spanId = "for10";
					premiumId = "premium10";
					gstId = "gst10";
					totalId = "total10";
				} else if (sequence === 4) {
					spanId = "forSingle";
					premiumId = "premiumSingle";
					gstId = "gstSingle";
					totalId = "totalSingle";
				}

				const spanElement = document.getElementById(spanId);
				const premiumElement = document.getElementById(premiumId);
				const gstElement = document.getElementById(gstId);
				const totalElement = document.getElementById(totalId);

				if (spanElement) {
					spanElement.textContent = Math.round(modalPremTax).toLocaleString("en-IN");
					premiumElement.textContent = modalPremium;
					gstElement.textContent = gst;
					totalElement.textContent = totalPremium;

					activeDivElement = document.getElementById(activeDiv);
					if (activeDivElement) {
						spanElements = activeDivElement.querySelectorAll('span');
						if (spanElements.length >= 3) {
							span1Value = spanElements[0].innerText;

							span3Value = spanElements[2].innerText;
							console.log('Span 1 Value:', span1Value);
							console.log('Span 3 Value:', span3Value);
						}
					}
				} else {
					console.log(`Span element not found for ID ${spanId}`);
				}
			} else {
				console.log("error");
			}
		});
	}).catch(function(exception) {
		console.error("Error in Generate PL API");
		console.error(exception);
		hideLoader();
	});
	
	return hasValidationErrors;
};

/**
 * Update API: Push latest Customer details 
 */
updateLMSLead = function() {
	showLoader();
	
	var proposerGender = $("input[type=radio][name="+genderEl+"]:checked").val();
	var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
	var investmentObjectiveDisplayName = $('input[name="' + investmentObjectiveEl + '"]:checked').data("name");
	var paymentFrequency = $(payForJEl).val() != '1'?$(paymentOptionJEl).find(':selected').data("name"):"Single";
	var updateLMSLeadData = new FormData();
	
	updateLMSLeadData.append(namespace + "ChildName", $(investingForFullNameJEl).val());
	updateLMSLeadData.append(namespace + "ChildDob", $(investingForDOBJEl).val());
	updateLMSLeadData.append(genderEl, $("input[type=radio][name="+genderEl+"]:checked").val());
	updateLMSLeadData.append(fullNameEl, $(fullNameJEl).val());
	updateLMSLeadData.append(dateOfBirthEl, $(dateOfBirthJEl).val());
	updateLMSLeadData.append(investmentObjectiveEl, $("input[type=radio][name="+investmentObjectiveEl+"]:checked").val());
	updateLMSLeadData.append(emailEl, $(emailJEl).val());
	updateLMSLeadData.append(mobileNumberEl, $(mobileNumberJEl).val());
	updateLMSLeadData.append(namespace + "SumAssured", $(lifeCoverJEl).val().replaceAll(",",""));
	updateLMSLeadData.append(namespace + "Product_Code", $(productCodeJEl).val());
	updateLMSLeadData.append(namespace + "BIQuotationNumber", $("#"+namespace+"quotationId").val());
	updateLMSLeadData.append(namespace + "BI_PDF_PATH", $("#"+namespace+"illustrationURL").val());
	updateLMSLeadData.append(namespace + "TotalPremiumAmount", $(investmentAmountJEl).val());
	updateLMSLeadData.append(namespace + "PolicyTerm", $(policyTermJEl).val());
	updateLMSLeadData.append(namespace + "ProductName", $(productNameJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentFrequency", paymentFrequency);
	updateLMSLeadData.append(namespace + "PolicyOption", paymentFrequency);
	
	updateLMSLeadData.append(namespace + "Education", $(educationQualificationJEl).val());
	updateLMSLeadData.append(namespace + "Income", $(annualIncomeJEl).val());
	updateLMSLeadData.append(namespace + "PermanentPinCode", $(pinCodeJEl).val());
	updateLMSLeadData.append(namespace + "LA_Smoker_YN",$("input[name="+smokerEl+"]").val());
	updateLMSLeadData.append(namespace + "Tobacco", $("input[name="+smokerEl+"]").val());
	updateLMSLeadData.append(namespace + "Relation", $(spouseParentRelationJEl).val());
	updateLMSLeadData.append(namespace + "Total_Sum_Assured", $(lifeCoverJEl).val().replaceAll(",",""));
	updateLMSLeadData.append(namespace + "PremiumPaymentTerm", $("#"+namespace+"payFor").val());
	
	updateLMSLeadData.append(namespace + "QuoteStage", stage);
	
	var proposerLARelation = "";
	var assuredRelVal = $("input[name='" + assuredRelationEl + "']:checked").val();
	if(assuredRelVal) {
		if(proposerGender == "Male" && (assuredRelVal == "Son" || assuredRelVal == "Daughter")) {
			proposerLARelation = "Father";
		} else if(proposerGender == "Male" && (assuredRelVal == "GrandSon" || assuredRelVal == "GrandDaughter")) {
			proposerLARelation = "Grandfather";
		} else if(proposerGender == "Female" && (assuredRelVal == "Son" || assuredRelVal == "Daughter")) {
			proposerLARelation = "Mother";
		} else if(proposerGender == "Female" && (assuredRelVal == "GrandSon" || assuredRelVal == "GrandDaughter")) {
			proposerLARelation = "Grandmother";
		}
		updateLMSLeadData.append(namespace + "proposerLARelation", proposerLARelation);
		updateLMSLeadData.append(namespace + "LAProposerRelation", proposerLARelation);
		updateLMSLeadData.append(namespace + "IS_LA_PR_Same_YN", "N");
	}else{
		updateLMSLeadData.append(namespace + "IS_LA_PR_Same_YN", "Y");
	}
	
	Liferay.Util.fetch(updateLMSLeadURL, {
		body: updateLMSLeadData,
		method: 'POST',
		dataType: "json",
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		console.debug(response);
		hideLoader();
	}).catch(function(exception) {
		console.error("Error in Update API");
		console.error(exception);
		hideLoader();
	});
}

/*
 * Product Modification Request Body
 */
getProductModificationRequestBody = function (){
	console.log(transactions.join(','));
	var ProductModificationRequestBody ={
		TransactionIds:transactions.join(','),	
		ApiInput: {
			formInputs: [
				{ key: "@PR_SA", value: $(lifeCoverJEl).val().replaceAll(",", "")},
				{ key: "@PR_PT", value: $(policyTermJEl).val()},
				{ key: "@INPUT_MODE", value: $(paymentOptionJEl).val()}
				],
			funds: [],
			riders: [],
			inputOptions: [],
			inputPartialWithdrawal: []
		}
	};
	return ProductModificationRequestBody;
}

fetchSJBProductModification = async function(pmRequestBody){
	showLoader();
	
	console.log("the sequence for aount transfer is"+sequence1);
	console.log(pmRequestBody);
	var hasModificationError = false;
	var pmRequestJson = new FormData();
	pmRequestJson.append(namespace + "pmRequestJsonString", JSON.stringify(pmRequestBody));
	await Liferay.Util.fetch(saralJeevanBimaPMURL, {
		body: pmRequestJson,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		
		var pmResponseJson = JSON.stringify(response);
		console.log(pmResponseJson);
		var pmResponseObj = JSON.parse(pmResponseJson);
		const productDetails = pmResponseObj.pmResponse.ProdDetails;
		console.log("productDetails is "+productDetails);
		
		productDetails.forEach((productDetail, index) => {
			const biresponse = JSON.parse(productDetail.BIJson);
			console.log(biresponse);
			const sequence = productDetail.Sequence;
			if (biresponse && Array.isArray(biresponse)) {
				if(activeDiv==="productForSingle"){
					sequence1 = 4;
				}
				else if(activeDiv==="productForRegular"){
					sequence1 = 1
				}
				else if(activeDiv === "productFor10"){
					sequence1 = 3
				}
				else if(activeDiv === "productFor5"){
					sequence1 = 2
				}
				const bi = biresponse[0];
				const modalPremTax = bi.MODAL_PREM_TAX;
				const modalPremium = bi.MODAL_PREM;
				const gst = bi.TAX_MP;
				const totalPremium = bi.TOTAL_MODAL_PREM_TAX;

				if(sequence == sequence1){
					totalGuaranteedBenefitAmountVal.textContent = Math.round(modalPremTax).toLocaleString("en-IN");
					preiumAmountVal.textContent = Math.round(modalPremium).toLocaleString("en-IN");
					gstVal.textContent = Math.round(gst).toLocaleString("en-IN");
					totalPremiumVal.textContent = Math.round(totalPremium).toLocaleString("en-IN");
				}
				console.log("the amount is"+ modalPremTax);
				let spanId;
				let divId;
				let premiumId;
				let gstId;
				let totalId
				let pptId;
				if (sequence === 1) {

					spanId = "forRegular";
					divId = "productForRegular";
					premiumId = "premiumRegular";
					gstId = "gstRegular";
					totalId = "totalRegular";
					pptId = "pptRegular";

				} else if (sequence === 2) {

					spanId = "for5";
					divId = "productFor5";
					premiumId = "premium5";
					gstId = "gst5";
					totalId = "total5";
					pptId = "ppt5";
				} else if (sequence === 3) {
					spanId = "for10";
					divId = "productFor10"
					premiumId = "premium10";
					gstId = "gst10";
					totalId = "total10";
					pptId = "ppt10";
				} else if (sequence === 4) {
					spanId = "forSingle";
					divId = "productForSingle"
					premiumId = "premiumSingle";
					gstId = "gstSingle";
					totalId = "totalSingle";
					pptId = "pptSingle";
				} 

				const divElement = document.getElementById(divId);
				const spanElement = document.getElementById(spanId);
				const premiumElement = document.getElementById(premiumId);
				const gstElement = document.getElementById(gstId);
				const totalElement = document.getElementById(totalId);
				const pptElement = document.getElementById(pptId);
				
				if(divId != "productForSingle" && divId != "productForRegular" && pptElement && Number(pptElement.innerText) >= Number($(policyTermJEl).val())){
					divElement.style.display = "none";
					return;
				}
				
				if (spanElement) {
					console.log("entered into span codition");
					divElement.style.display = "block";
					spanElement.textContent = Math.round(modalPremTax).toLocaleString("en-IN");
					premiumElement.textContent = modalPremium;
					gstElement.textContent = gst;
					totalElement.textContent = totalPremium;

					activeDivElement = document.getElementById(activeDiv);
					if (activeDivElement) {
						spanElements = activeDivElement.querySelectorAll('span');
						if (spanElements.length >= 3) {
							span1Value = spanElements[0].innerText;

							span3Value = spanElements[2].innerText;
							console.log('Span 1 Value:', span1Value);
							console.log('Span 3 Value:', span3Value);
						}
					}
				} 

			} else {
				let divId1;
				var firstActive  = "productForRegular";
				var secondActive = "productForSingle";
				if (sequence === 1) {
					divId1 = "productForRegular";
				} else if(sequence === 2){
					divId1 = "productFor5";
				}
				else if(sequence === 3){
					divId1 = "productFor10"
				}else if(sequence === 4){
					divId1 = "productForSingle"
				}
				console.log("entered into the div condition");
				const divElement1 = document.getElementById(divId1);
				console.log(divElement1);
				if(activeDiv===divId1 && divId1!="productForRegular"){
					localStorage.setItem("activeDivId",firstActive);
					initSavedProductPlan();
				}else if(activeDiv===divId1 && divId1==="productForRegular"){
					localStorage.setItem("activeDivId",secondActive);
					initSavedProductPlan();
				}
				divElement1.style.display = "none";
			}
		});
		selectProductPlan(localStorage.getItem('activeDivId'));
	}).catch(function(exception) {
		console.error("Error in Generate PM API");
		console.error(exception);
		hideLoader();
	});
	
	return hasModificationError;
};

/**
 * Generate-BI Request Body
 */
getSJBGenerateBIRequestBody = function(){
	var generateBIRequestBody = {};
	//Adding Product Metadata to Request Body.
	generateBIRequestBody["PR_ID"]=productMetaData.productCode;
	
	//Adding PaymentOption, payFor & policyTerm to Request Body.
	var paymentOptionVal = document.getElementById(namespace+"payFor").value != '1'?document.getElementById(namespace+"paymentOption").value:"5";
	var payFor = document.getElementById(namespace+"payFor").value;
	var policyTerm = document.getElementById(namespace+"policyTerm").value;
	
	/*
	 * Policy Details
	 */
	generateBIRequestBody["INPUT_MODE"]=paymentOptionVal;
	generateBIRequestBody["PR_PPT"]=payFor;
	generateBIRequestBody["PR_PT"]=policyTerm;	
	generateBIRequestBody["PR_SA"]=$(lifeCoverJEl).val().replaceAll(",", "");
	
	/*
	 * Customer Details
	 */
	var fullName = $(fullNameJEl).val();
	var dateOfBirthInputVal = $(dateOfBirthJEl).val();
	var age = calculateAge(dateOfBirthInputVal);
	var dateOfBirth = moment(dateOfBirthInputVal, "DD/MM/YYYY");
	var proposerDOBStr = moment(dateOfBirth).format('DD MMM YYYY');
	var gender = $("input[type=radio][name="+genderEl+"]:checked").val();
	var genderValue = "O";
	if(gender==="3"){
		genderValue="M";
	}
	else if(gender==="4"){
		genderValue="F";
	}
	else{
		genderValue="O";
	}
	var mobileNumber = $(mobileNumberJEl).val();
	var email = $(emailJEl).val();
	var sameProposer = "True";
	generateBIRequestBody["LI_NAME"] = fullName;
	generateBIRequestBody["LI_ENTRY_AGE"] = age;
	generateBIRequestBody["LI_DOB"] = proposerDOBStr;
	generateBIRequestBody["LI_GENDER"] = genderValue;
	generateBIRequestBody["LI_MOBILENO"] = mobileNumber;
	generateBIRequestBody["LI_EMAILID"] = email;
	generateBIRequestBody["PROPOSER_NAME"] = fullName;
	generateBIRequestBody["PROPOSER_AGE"] = age;
	generateBIRequestBody["PROPOSER_DOB"] = proposerDOBStr;
	generateBIRequestBody["PROPOSER_GENDER"] = genderValue;
	generateBIRequestBody["SameProposer"] = sameProposer;
	generateBIRequestBody["ZP_ANNINCOME"]=$(annualIncomeValidatorJEl).val().replaceAll(",", "");
	generateBIRequestBody["ZP_OCCUPATION"]= $(occupationValidatorJEl).val();
	generateBIRequestBody["ZP_EDUCATION"]=$(educationQualificationValidatorJEl).val();
	generateBIRequestBody["ZP_SECONDARY_OCCUPATION"]=$(spouseParentOccupationvalidatorJEl).val() != "" ? $(spouseParentOccupationvalidatorJEl).val() :  $(occupationValidatorJEl).val();
	
	/*
	 * Default values set to Request Body
	 */
	generateBIRequestBody["LI_STATE"] = "0";
	generateBIRequestBody["CashFlow"]="N";
	generateBIRequestBody["COMPANY_STATE"]="0";
	generateBIRequestBody["GSTIN"]="0";
	generateBIRequestBody["GSTIN_Number"]="0";
	generateBIRequestBody["AGENT_ID"] = "";
	generateBIRequestBody["PR_MI"]="0";
	generateBIRequestBody["PR_SAMF"]="0";
	generateBIRequestBody["PR_ModalPrem"]="";
	generateBIRequestBody["category"]="10";
	generateBIRequestBody["PR_CHANNEL"]="1";
	generateBIRequestBody["REQUESTSOURCE"]="4";
	generateBIRequestBody["isPdfByte"]="Y";
	generateBIRequestBody["PR_ANNPREM"]="";
	
	/*
	 * Product Details
	 */
	generateBIRequestBody["PR_ID"]=$("#" + portletNamespace + "productCode").val();

	console.debug(generateBIRequestBody);
	return generateBIRequestBody;
}

/**
 * Resource Call to Generate BI API for Guaranteed  Savings Star
 */
sjbGenerateBI = async function(biRequestBody) {

	showLoader();
	var hasValidationErrors = false;
	var biRequestJson = new FormData();

	biRequestJson.append(namespace + "biRequestData", JSON.stringify(biRequestBody));
	await Liferay.Util.fetch(edelweissGenerateBIURL, {
		body: biRequestJson,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {

		var biResponse = response.data;
		console.debug(biResponse);
		
		var biResponseJson = JSON.parse(biResponse["JsonOutput"]);
		console.log(biResponseJson);
		
		var productObj = biResponseJson.InputValidationStatus[0];
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

/*
 * Dispaly the Product Plan Cards
 */
function showCardValues(){
	for (const item of sjbProductPlansValues) {
		const title = item.title;
		const descriptionRawText = item.descriptionRawText;
		const image = item.uploadImage.link.href;
		const isRecommended = item.isRecommended;
		const ppt = item.ppt?item.ppt:$(policyTermJEl).val();

		console.log("Title:", title);
		console.log("Description Raw Text:", descriptionRawText);
		console.log("Image:", image);
		if (title === "Single Pay") {
			document.getElementById("titleForSingle").textContent =  title;
			document.getElementById("descriptionForSingle").textContent =  descriptionRawText;
			document.getElementById("imageForSingle").src =  image;
			document.getElementById("pptSingle").textContent =  ppt;
			if(isRecommended){
				$("#productForSingle .recommend-tag").show();
				recommendedDiv = "productForSingle";
			}else{
				$("#productForSingle .recommend-tag").hide();
			}
		} else if (title === "Regular Pay") {
			document.getElementById("titleForRegular").textContent =  title;
			document.getElementById("descriptionForRegular").textContent =  descriptionRawText;
			document.getElementById("ImageForRegular").src =  image;
			document.getElementById("pptRegular").textContent =  ppt;
			if(isRecommended){
				$("#productForRegular .recommend-tag").show();
				recommendedDiv = "productForRegular";
			}else{
				$("#productForRegular .recommend-tag").hide();
			}

		} else if (title === "Limited Pay for 10 years") {
			document.getElementById("titleFor10").textContent =  title;
			document.getElementById("descriptionFor10").textContent =  descriptionRawText;
			document.getElementById("imageFor10").src =  image;
			document.getElementById("ppt10").textContent =  ppt;
			if(isRecommended){
				$("#productFor10 .recommend-tag").show();
				recommendedDiv = "productFor10";
			}else{
				$("#productFor10 .recommend-tag").hide();
			}

		} else if (title === "Limited Pay for 5 years") {
			document.getElementById("titleFor5").textContent =  title;
			document.getElementById("descriptionFor5").textContent =  descriptionRawText;
			document.getElementById("imageFor5").src =  image;
			document.getElementById("ppt5").textContent =  ppt;
			if(isRecommended){
				$("#productFor5 .recommend-tag").show();
				recommendedDiv = "productFor5";
			}else{
				$("#productFor5 .recommend-tag").hide();
			}
		}	


	}

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


/**
 * Save Investment Details and Policy Details for Customize Form Field Changes
 */
saveCustomerInvestmentData = function() {
	var customizeFormData = document.getElementById(customizeFormEl);
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
		console.error("Error in Save Customer Investment Data ");
		console.error(exception);
	});
}

/*
 * Update Product Premium Details in Break-up modal
 */
function handleProductBreakup(premiumId,gstId,totalPremiumId, pptId){
	const premiumId1 = document.getElementById(premiumId);
	const premiumVal = premiumId1.textContent;
	const gstId1 = document.getElementById(gstId);
	const gstVal = gstId1.textContent;
	const totalPremiumId1 = document.getElementById(totalPremiumId);
	const totalPremiumVal = totalPremiumId1.textContent;

	const premiumAmount = document.getElementById('premium-amount');
	const gstAmount = document.getElementById('gst');
	const totalPremiumAmount = document.getElementById('total-premium');
	
	premiumAmount.textContent = Math.round(premiumVal).toLocaleString("en-IN");
	gstAmount.textContent = Math.round(gstVal).toLocaleString("en-IN");
	totalPremiumAmount.textContent = Math.round(totalPremiumVal).toLocaleString("en-IN");
	
	document.getElementById(premiumAmountEl).value = totalPremiumVal;
	document.getElementById(basePremiumAmountEl).value = premiumVal;
	document.getElementById(gstEl).value = gstVal;
	document.getElementById(payForEl).value = document.getElementById(pptId).textContent;
	document.getElementById(investmentAmountEl).value = totalPremiumVal;
}

/*
 * Check if there is an activeDivId stored in localStorage
 */
function handleProductSelection(selectedPlan, productId, selCardTitle) {
	const selectedProductSpan = document.getElementById(productId);
	selectedProductValue = selectedProductSpan.textContent;
	const totalGuaranteedBenefitAmountSpan = document.getElementById(portletNamespace+"totalGuaranteedBenefitAmount");
	selectedCardTitle = document.getElementById(selCardTitle).innerHTML;
	totalGuaranteedBenefitAmountSpan.textContent = selectedProductValue;
	
	$(".productPlan").removeClass("recomend-card");
	$("#"+selectedPlan).addClass("recomend-card");
	
} 

initSavedProductPlan = function(){
	activeDiv = !activeDiv?recommendedDiv:localStorage.getItem('activeDivId');
	activeDivElement = activeDiv;
	if (activeDiv) {
		const divToActivate = document.getElementById(activeDiv);
		if (divToActivate) {
			containers.forEach((c) => c.classList.remove('recomend-card'));
			divToActivate.classList.add('recomend-card');
			spanElements = divToActivate.querySelectorAll('span');
			$(payForJEl).val(spanElements[5].innerText);
		}
	}
	
	containers.forEach((container) => {
		container.addEventListener('click', () => {
			containers.forEach((c) => c.classList.remove('recomend-card'));
			container.classList.add('recomend-card');
			activeDiv = container.id;
			localStorage.setItem('activeDivId', activeDiv);
			console.log(activeDiv);

			activeDivElement = document.getElementById(activeDiv);
			if (activeDivElement) {
				spanElements = activeDivElement.querySelectorAll('span');
				if (spanElements.length >= 3) {
					span1Value = spanElements[0].innerText;
					span3Value = spanElements[2].innerText;
					console.log('Span 1 Value:', span1Value);
					console.log('Span 3 Value:', span3Value);
				}
			}
		});
	});
}

showLoader = function(){
	$("#preloader").addClass("preloader");
	$("#"+portletNamespace+"tracker-loader").show();
}

hideLoader = function(){
	$("#preloader").removeClass("preloader");
	$("#"+portletNamespace+"tracker-loader").hide();
}


/**
 * Formate avilable product amounts 
 */
formateProductAmounts = function(){
	$(".amount-with-rupee").each(function(i, element){
		if(!$(this).data() || !$(this).data().amount){
			$(this).html(LANG_MESSAGES["rupee-sign"].concat(" ").concat(Math.round(0).toLocaleString("en-IN")));
			return;
		}
	    $(this).html(LANG_MESSAGES["rupee-sign"].concat(" ").concat(Math.round($(this).data().amount).toLocaleString("en-IN")));
	})
	
	$(".amount").each(function(i, element){
		if(!$(this).data() || !$(this).data().amount){
			$(this).html(LANG_MESSAGES["rupee-sign"].concat(" ").concat(Math.round(0).toLocaleString("en-IN")));
			return;
		}
	    $(this).html(Math.round($(this).data().amount).toLocaleString("en-IN"));
	})
}

/*
 * Select product plan
 */
selectProductPlan = function(requestedPlanId){
	var requestedPlan = $("#"+requestedPlanId);
	if(requestedPlan && requestedPlan.is(":visible")){
		requestedPlan.trigger("click");
		return;
	}
	
	var recommendedPlan = $("#"+recommendedDiv);
	if(recommendedPlan && recommendedPlan.is(":visible")){
		recommendedPlan.trigger("click");
		return;
	}
	
	$("#"+portletNamespace+"productPlans .productPlan:visible").eq(0).trigger("click");
}