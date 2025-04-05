var namespace = portletNamespace;
var selectedRidersList = new Map();
var generateBIRequestBody = {};
var basicInvestingFor = document.getElementById(namespace+"investingFor");
var editSummaryFormValidator = portletNamespace + "editSummaryForm";

var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");

/*
 * Family validation variable
 */

var childSpouseIndividualDetailsFormJEl = "#" + namespace + "childSpouseIndividualDetailsForm",
childSpouseJointDetailsFormJEl = "#" + namespace + "childSpouseJointDetailsForm",
spouseChildFullNameJEl = "#" + spouseChildFullNameEl,
spouseChildDOBJEl = "#" + spouseChildDOBEl,
policyOptionJEl = "#" + policyOptionEl,
childSpouseHeaderJEl = "#childSpouseHeader",
spouseChildFullNameEl = namespace + "spouseChildFullName",
spouseChildDOBEl = namespace + "spouseChildDOB",
spouseFullNameJEl = "#" + namespace + "spouseFullName",
spouseDOBJEl = "#" + namespace + "spouseDOB",
childFullNameJEl = "#" + namespace + "childFullName",
childDOBJEl = "#" + namespace + "childDOB",
lifePartnerKey = "lifePartner",
childIndividualLifeKey = "childIndividualLife",
riderPremiumAmountPrefixEl = namespace + "riderPremiumAmount_",
riderPremiumAmountPrefixJEl = "#" + riderPremiumAmountPrefixEl,
payorWaiverBenefitOptionJEl = "#" + namespace + "payorWaiverBenefitOption";

/*
 * GSS Product dropdown option value map
 */
var gssInvestmentObjectiveOptionsMap = {
		"growMoney": "2",
		"retirement": "5",
		"childEducation": "3",
		"taxSaving": "4"
};

var gssPolicyOptionsMap = {
		"baseCover": "1",
		"enhancedCover": "2"
};



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
	 * Hide & Show Family Income benefit Option
	 */
	hideShowFamilyBenefitIncome();
	
	/*
	 * Load payfor dropdown options
	 */
	updatePayForDropdown();

	/*
	 * Validate and Update Customize page feilds and other details
	 */
	updateCustomizePage(selectedRidersList, true);
	
	/*
	 * Formate Investment Amount
	 */
	$("#"+namespace+"investmentAmount").val(Math.round($("#"+namespace+"investmentAmount").val().replaceAll(",","")).toLocaleString("en-IN"));
	
	/*
	 * Render Investment Amount in words
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
	$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
	
	/*
	 * On click event Listeners to add or remove a rider
	 */
	$(".riderSelectionInput").on("click", function(){
		if(this.dataset.riderKey =="PayorWaiverBenefitRider" && document.getElementById(namespace+"payorWaiverBenefitOption") && 
		   !document.getElementById(namespace+"payorWaiverBenefitOption").value){
			// TODO: Show Error in popup
			$("#errorMessageModal").find("#errorMessageContainer").empty();
			$("#errorMessageModal").find("#errorMessageContainer").html("Select a value before adding Payor Waiver Benefit");
			$("#errorMessageModal").modal('show');
			this.checked=false;
			return;
		}
		addRemoveRider(this.id, this.checked);
		updateCustomizePage(selectedRidersList, false);
	});
	
	
	/*
	 * On change event Listeners for the Customize page inputs
	 */
	$(".customizeInput").on("change",function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * On change event Listeners for the Rider Premium options
	 */
	$(".riderPremiumInput").on("change",function(){
		var requestedRidersList=new Map(selectedRidersList);
		
		var curRequestedRider = document.getElementById(namespace+"selectedRider_"+this.dataset.riderId);
		var curRequestedRiderDetails={};
		curRequestedRiderDetails.id=curRequestedRider.value;
		curRequestedRiderDetails.name=curRequestedRider.dataset.riderName;
		curRequestedRiderDetails.price=curRequestedRider.dataset.riderPrice;
		curRequestedRiderDetails.key=curRequestedRider.dataset.riderKey;
		
		requestedRidersList.set(curRequestedRider.value, curRequestedRiderDetails);
		updateCustomizePage(requestedRidersList, false);
	});
	
	/*
	 * Update Min. Investment Amount for the selected payment option / Payment
	 * Mode
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
	 * Update Pay For Dropdown Options
	 */
	$("#"+namespace+"policyOption").on("change",function(){
		updatePayForDropdown();
		updateMinInvestmentAmount();
		updateCustomizePage(selectedRidersList, false);
	});
	
	/*
	 * Hide or Show Family Income Benefit Field on change of Pay For
	 */
	$("#"+namespace+"payFor").on("change", function(){
		hideShowFamilyBenefitIncome();
		updateCustomizePage(selectedRidersList, false);
	})
	
	/*
	 * Show Benefit Amount Breakup table model
	 */
	$("#"+namespace+"benefitAmountBreakup").on("click", function(){
		$("#planMaturityBenefitsBreakup").modal('show');
	});
	
	/*
	 * To display the Family Details Popup on change if Investing for selected
	 * to Family
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
		var hasValidationErrors = await guaranteedSavingsStarGenerateBI(generateBIRequestBody);
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
		var hasValidationErrors = await guaranteedSavingsStarGenerateBI(generateBIRequestBody);
		if(!hasValidationErrors){
			$("#"+namespace+"customizeForm").submit();
		}
	});
	
	/*
	 * Family Details Validation
	 */
	initChildSpouseValidation();
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

hideShowFamilyBenefitIncome = function(){
	var currentPayFor = $("#"+namespace+"payFor").val();
	if(currentPayFor != "5"){
		$("#"+namespace+"familyIncomeBenefitContainer").parent().show();
	}else{
		$("#"+namespace+"familyIncomeBenefitContainer").parent().hide();
		$("#"+namespace+"familyIncomeBenefit").val("3");
	}
}

/**
 * auto-selecte selected riders on page load
 */
loadCustomersSavedRiders = function(){
	if(!selectedRidersIds || !selectedRidersIds.length){
		return;
	}
	selectedRidersIds.forEach(function(rider){
	    updateRiderData(rider);
	    addRemoveRider(namespace+"selectedRider_"+rider.id,true);
	});
}

/**
 * Pre-populate rider premium amount
 * 
 */
updateRiderData = function(rider){
	var riderElement = document.getElementById(namespace+"selectedRider_"+rider.id);
	
	riderElement.checked=true;
	riderElement.dataset.isAvailable = true;
	
	if(rider.pt && rider.ppt){
		riderElement.dataset.riderPt = rider.pt;
		riderElement.dataset.riderPpt = rider.ppt;
	}
	
	if(document.getElementById(namespace+"riderPremiumAmount_"+rider.id)){
		var riderPremiumDropdown = document.getElementById(namespace+"riderPremiumAmount_"+rider.id);
	}else if(document.getElementById(namespace+"payorWaiverBenefitOption")){
		var riderPremiumDropdown = document.getElementById(namespace+"payorWaiverBenefitOption");
	}
	riderPremiumDropdown.value=rider.premium;
}

/**
 * Update Customize From Feild: Update Min. Investment Amount Value
 */
updateMinInvestmentAmount = function(){
	var paymentOption = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"";
	var policyOption = document.getElementById(namespace+"policyOption")?document.getElementById(namespace+"policyOption").value:"";
	var currentInvestmentAmount = document.getElementById(namespace+"investmentAmount").value.replaceAll(",","");
	
	if(!productInvestmentAmountRel || !productInvestmentAmountRel.length){
		return;
	}
	
	productInvestmentAmountRel.forEach(investmentAmountOption=>{
	    if(investmentAmountOption.paymentOption.key == paymentOption && investmentAmountOption.policyOption.key == policyOption && Number(currentInvestmentAmount) < Number(investmentAmountOption.minimumInvestmentAmount)){
	    	document.getElementById(namespace+"investmentAmount").value = Number(investmentAmountOption.minimumInvestmentAmount).toLocaleString("en-IN");
	    }
	})
}

/**
 * Update Customize From Feild: Update Payfor dropdown field
 */

updatePayForDropdown = function(){
	var policyForList;
	var currentPayFor = document.getElementById(namespace+"payFor").value;
	
    var curPolicyOption = document.getElementById(namespace+"policyOption")?document.getElementById(namespace+"policyOption").value:"";
    
    if(!productPolicyForRel || !productPolicyForRel.length){
		return;
	}
    
    productPolicyForRel.forEach(productPolicyForOption=>{

        policyOptionValue=productPolicyForOption.policyOption && productPolicyForOption.policyOption.key ? productPolicyForOption.policyOption.key : "";
        
        if(curPolicyOption == policyOptionValue){
        	policyForList = productPolicyForOption.policyFor;
            return;
        }
    });
    
    if(!policyForList || !policyForList.length){
		return;
	}

    var payForDropdownElement = document.getElementById(namespace+"payFor");
    payForDropdownElement.innerHTML="";
    policyForList.forEach(policyFor=>{
    	const policyForOption = document.createElement("option");
    	policyForOption.setAttribute("value", policyFor.key);
    	policyForOption.appendChild(document.createTextNode(policyFor.name));
		
    	payForDropdownElement.appendChild(policyForOption);
    });
    
    if(policyForList.find(payForOption => payForOption.key === currentPayFor)){
    	document.getElementById(namespace+"payFor").value = currentPayFor;
    };
    
    hideShowFamilyBenefitIncome();
    
}


/**
 * Resource Call to Generate BI API for Guaranteed Savings Star
 */
guaranteedSavingsStarGenerateBI = async function(biRequestBody) {
	showLoader();
	var hasValidationErrors = false;
	var biRequestJson = new FormData();
	biRequestJson.append(namespace + "biRequestJsonString", JSON.stringify(biRequestBody));
	await Liferay.Util.fetch(customizePageDetails.generateBiUrl, {
		body: biRequestJson,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {

		var biResponseJson = response.data;
		hasValidationErrors = handleInputValidationStatus(biResponseJson);

		/*
		 * If no validation error present, update Plan Premium Details and
		 * Calculate Total Premium Amount
		 */
		if(hasValidationErrors) {
			hideLoader();
			return hasValidationErrors;
		}

		/*
		 * Update Riders data
		 */
		renderRiderPremiumAmount(biResponseJson);
		updateSelectedRiderPrice();
		updatePlanPremiumDetails();
		updateRidersListHiddenInput();
		updateRiderPremiumDropdowns(biResponseJson["BIJson"][0].SA);
		
		biResponseJson.InputValidationStatus.forEach(riderObj=>{
			
			if(!selectedRidersList.has(riderObj.ProductId.toString())){
				return;
			}
			
			var riderJSON = selectedRidersList.get(riderObj.ProductId.toString());
			riderJSON.price = riderObj.BasePremium + riderObj.Tax;
			riderJSON.ppt = riderObj.PPT;
			riderJSON.pt = riderObj.PT;
			
		});
		
		renderMaturityBenefitsBreakup(biResponseJson.BIJson);
		gssRenderIncomeBenefitsBreakup(biResponseJson.BIJson);
		
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
 * Update Rider Premium Dropdown Field
 */
updateRiderPremiumDropdowns = function(sumAssured){
	var dropdownPeakLimit = sumAssured;
	
	riderValidations.forEach((riderValidation,riderId)=>{
		var dropdownList = [];
		var minPremiumAmt = riderValidation.minimumPremiumAmount;
		
		if(dropdownPeakLimit != 0 && minPremiumAmt != 0){
			while(minPremiumAmt <= dropdownPeakLimit){
				dropdownList.push(minPremiumAmt);
				minPremiumAmt= minPremiumAmt+ riderValidation.frequencyOfPremium;
				if(minPremiumAmt > dropdownPeakLimit || (riderValidation.maximumPremiumAmount && minPremiumAmt > riderValidation.maximumPremiumAmount && riderValidation.maximumPremiumAmount > 0)){
					break;
				}
			}	
		}
		
		if(document.getElementById(namespace+"riderPremiumAmount_"+riderId) && dropdownList.length > 0){
			var riderPremiumDropdown = document.getElementById(namespace+"riderPremiumAmount_"+riderId);
			var oldDropdownValue = riderPremiumDropdown.value;
			riderPremiumDropdown.innerHTML="";
			dropdownList.forEach(function(value){
				const premiumOption = document.createElement("option");
				premiumOption.setAttribute("class", "productAmounts");
				premiumOption.setAttribute("value", value);
				premiumOption.setAttribute("data-amount", value);
				premiumOption.appendChild(document.createTextNode(rupeeSignLabel.concat(Math.round(value).toLocaleString("en-IN"))));
				riderPremiumDropdown.appendChild(premiumOption);
		    });
			
			if(dropdownList.includes(Number(oldDropdownValue))){
				document.getElementById(namespace+"riderPremiumAmount_"+riderId).value = oldDropdownValue;
			}
		}
	});
}

/**
 * Render Rider Premium Amount
 */
renderRiderPremiumAmount = function(biResponseJson){
	if(!biResponseJson || !biResponseJson.InputValidationStatus || !biResponseJson.InputValidationStatus.length){
		return;
	}
	biResponseJson.InputValidationStatus.forEach(function(productRider){
	    console.debug(productRider);
	    const riderId=productRider.ProductId;
	    const riderPremiumAmount = productRider.BasePremium + productRider.Tax;
	    
	    /*
		 * Set Base plan price
		 */
	    if(productMetaData.productCode == riderId && document.getElementById(namespace+"basePlanPrice") && document.getElementById(namespace+"basePlanPriceAmount")){
	    	document.getElementById(namespace+"basePlanPriceAmount").innerHTML = rupeeSignLabel.concat(Math.round(riderPremiumAmount).toLocaleString("en-IN"));
	    	document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice = Math.round(riderPremiumAmount);
	    	document.getElementById(namespace+"basePremiumAmount").value=Math.round(riderPremiumAmount);
	    }
	    
	    /*
		 * Set rider Amount
		 */
	    if(document.getElementById(namespace+"riderPriceAmount_"+riderId))
    	{
	    	document.getElementById(namespace+"riderPriceAmount_"+riderId).innerHTML = rupeeSignLabel.concat(Math.round(riderPremiumAmount).toLocaleString("en-IN"));
	    	
    	}
	    
	    /*
		 * Add Price Amount to the riders data-set
		 */
	    if(document.getElementById(namespace+"selectedRider_"+riderId))
    	{
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPrice = riderPremiumAmount;
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt = productRider.PT;
            document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPpt = productRider.PPT;
            document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderSa = productRider.SA;
    	}
	    
	})
}

/**
 * Render Maturity breakup details table
 */
renderMaturityBenefitsBreakup = function(productBIJson){
	
	var totalMaturitybenefitAmount=0;
	
	var tableBody = document.getElementById("planMaturityBenefitsBreakupTableBody");
	tableBody.innerHTML = "";
	
	if(!productBIJson || !productBIJson.length){
		return;
	}
	
	productBIJson.forEach(biJson=>{
		
		totalMaturitybenefitAmount+=biJson.MB_G;
		
		const tableRecord = document.createElement("tr");
		
		var policyYearData = document.createElement("td");
		policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
		tableRecord.appendChild(policyYearData);
		
		var annualizedPremiumData = document.createElement("td");
		annualizedPremiumData.appendChild(document.createTextNode(biJson.ANN_PREM_YEARLY!=0?rupeeSignLabel.concat(Math.round(biJson.ANN_PREM_YEARLY).toLocaleString("en-IN")):"-"));
		tableRecord.appendChild(annualizedPremiumData);
		
		var maturityBenefitData = document.createElement("td")
		maturityBenefitData.appendChild(document.createTextNode(biJson.MB_G!=0?rupeeSignLabel.concat(Math.round(biJson.MB_G).toLocaleString("en-IN")):"-"));
		tableRecord.appendChild(maturityBenefitData);
		
		var deathBenefit = document.createElement("td")
		deathBenefit.appendChild(document.createTextNode(biJson.DB_G!=0?rupeeSignLabel.concat(Math.round(biJson.DB_G).toLocaleString("en-IN")):"-"));
		tableRecord.appendChild(deathBenefit);
		
		tableBody.appendChild(tableRecord);
	});
	
	document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(totalMaturitybenefitAmount).toLocaleString("en-IN"));
	document.getElementById(namespace+"totalMaturityAmount").value=Math.round(totalMaturitybenefitAmount);
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
		
		if(biJson.MB_G != 0){
			
			const tableRecord = document.createElement("tr");
			
			var policyYearData = document.createElement("td");
			policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
			tableRecord.appendChild(policyYearData);
			
			var maturityBenefitData = document.createElement("td")
			maturityBenefitData.appendChild(document.createTextNode(biJson.MB_G!=0?rupeeSignLabel.concat(Math.round(biJson.MB_G).toLocaleString("en-IN")):"-"));
			tableRecord.appendChild(maturityBenefitData);
			
			tableBody.appendChild(tableRecord);
		}
	});
}

/**
 * Add or Remove a rider from the Plan
 */
addRemoveRider = function(selectedRiderId, isAdded){
	var selectedRider = document.getElementById(selectedRiderId);
	
	if(!selectedRider || !selectedRider.dataset){
		return;
	}
	
	if(isAdded && selectedRider.dataset.isAvailable=="true"){
		var selectedRiderDetails={};
		selectedRiderDetails.id=selectedRider.value;
		selectedRiderDetails.name=selectedRider.dataset.riderName;
		selectedRiderDetails.price=selectedRider.dataset.riderPrice;
		selectedRiderDetails.key=selectedRider.dataset.riderKey;
		selectedRiderDetails.pt=selectedRider.dataset.riderPt;
		selectedRiderDetails.ppt=selectedRider.dataset.riderPpt;
		
		selectedRider.checked=true;
		if(document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value)){
			selectedRiderDetails.premium=document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).value;
			document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).disabled=true;
		}else if(selectedRider.dataset.riderKey =="PayorWaiverBenefitRider" && document.getElementById(namespace+"payorWaiverBenefitOption")){
			selectedRiderDetails.premium=document.getElementById(namespace+"payorWaiverBenefitOption").value;
			document.getElementById(namespace+"payorWaiverBenefitOption").disabled=true;
		}else if(selectedRider.dataset.riderKey =="WaiverOfPremiumRider"){
			selectedRiderDetails.premium = selectedRider.dataset.riderSa;
		}

		selectedRidersList.set(selectedRider.value, selectedRiderDetails);
	}else{
		selectedRidersList.delete(selectedRider.value);
		if(document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value)){
			document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).disabled=false;
		}else if(selectedRider.dataset.riderKey =="PayorWaiverBenefitRider" && document.getElementById(namespace+"payorWaiverBenefitOption")){
			document.getElementById(namespace+"payorWaiverBenefitOption").disabled=false;
		}
		selectedRider.checked=false;
	}
	
	/*
	 * Update Riders List Hidden Form input value
	 */
	updateRidersListHiddenInput();
}

/**
 * Update Riders data in hidden Input
 */
updateRidersListHiddenInput = function(){
	
	var ridersIdsList=[];
	if(!selectedRidersList || !selectedRidersList.size){
		document.getElementById(namespace+"selectedRidersIds").value=JSON.stringify(ridersIdsList);
		return;
	}
	selectedRidersList.forEach(function(selectedRider){
		ridersIdsList.push(selectedRider);
	});
	document.getElementById(namespace+"selectedRidersIds").value=JSON.stringify(ridersIdsList);
}

/**
 * Update the Selected Riders price
 */
updateSelectedRiderPrice = function(){
	if(!selectedRidersList || !selectedRidersList.size){
		return;
	}
	
	selectedRidersList.forEach(function(rider){
		rider.price = document.getElementById(namespace+"selectedRider_"+rider.id).dataset.riderPrice;
		rider.pt = document.getElementById(namespace+"selectedRider_"+rider.id).dataset.riderPt;
		rider.ppt = document.getElementById(namespace+"selectedRider_"+rider.id).dataset.riderPpt;
		rider.premium = document.getElementById(namespace+"selectedRider_"+rider.id).dataset.riderSa;
	});
}

/**
 * Update Your Plan Premium Details - Add or Remove riders from the plan premium
 * details
 */
updatePlanPremiumDetails = function(){
	if(!selectedRidersList || !selectedRidersList.size){
		$("#"+namespace+"selectedRidersList").hide();
		
		/*
		 * Set Premium total premium amount to Base plan price
		 */
		if(document.getElementById (namespace+"totalPayPremiumAmount")){
			document.getElementById (namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(Math.round(document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice).toLocaleString("en-IN"));
			document.getElementById (namespace+"premiumAmount").value =  document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice;
		}
		document.getElementById(namespace+"selectedRidersListContainer").innerHTML="";
		return;
	}
	
	$("#"+namespace+"selectedRidersList").show();
	
	document.getElementById(namespace+"selectedRidersListContainer").innerHTML="";
	const selectedRidersListDiv = document.getElementById(namespace+"selectedRidersListContainer");
	selectedRidersList.forEach(function(rider){
		/*
		 * To display Rider Name
		 */
		const selectedRiderNameElement = document.createElement("span");
		selectedRiderNameElement.setAttribute("id", namespace+"selectedRiderName"+rider.id);
		selectedRiderNameElement.appendChild(document.createTextNode(rider.name));
		
		/*
		 * To display Price
		 */
		const selectedRiderPriceElement = document.createElement("span");
		selectedRiderPriceElement.setAttribute("id", namespace+"selectedRiderPrice"+rider.id);
		selectedRiderPriceElement.appendChild(document.createTextNode(rupeeSignLabel.concat(Math.round(rider.price).toLocaleString("en-IN"))));

		/*
		 * Rider Premium Div
		 */
		const selectedRider = document.createElement("div");
		selectedRider.setAttribute("id", namespace+"selectedRider"+rider.id);
		selectedRider.setAttribute("data-rider-price", rider.price);
		selectedRider.setAttribute("data-rider-id", rider.price);
		selectedRider.classList.add("d-flex");
		selectedRider.classList.add("justify-content-between");
		selectedRider.classList.add("mb-3");
		selectedRider.appendChild(selectedRiderNameElement);
		selectedRider.appendChild(selectedRiderPriceElement);
		
		selectedRidersListDiv.appendChild(selectedRider);

	});
	
	/*
	 * Calculate Total Premium Amount afetr addition of the Riders
	 */
	if(document.getElementById (namespace+"totalPayPremiumAmount")){
		document.getElementById (namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(Math.round(calculateTotalPremiumAmount()).toLocaleString("en-IN"));
		document.getElementById (namespace+"premiumAmount").value = calculateTotalPremiumAmount();
	}
}

/**
 * Calculate Total Premium Amount
 */
calculateTotalPremiumAmount = function(){
	if(!document.getElementById(namespace+"basePlanPrice") || !document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice){
		return 0;
	}
	var totalPremiumAmount = parseInt(document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice);
	if(!selectedRidersList || !selectedRidersList.size){
		return;
	}
	selectedRidersList.forEach(function(rider){
		totalPremiumAmount = (rider.price)?totalPremiumAmount+parseInt(rider.price):totalPremiumAmount;
	});
	
	return totalPremiumAmount;
}

/**
 * Rider Validation
 */
validateProductRiders = function(){
	riderValidations.forEach((riderValidation,riderId)=>{
		var isInvestingForValid = validateRiderInvestingFor(riderValidation,riderId);
		var isFamilyIncomeBenefitValid = validateFamilyIncomeBenefit(riderValidation,riderId);
		var isCriticalIllnessAgeValid = validateCriticalIllnessEntryAge(riderId);
		
		document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isFamilyIncomeBenefitValid && isCriticalIllnessAgeValid;
	});
}

/**
 * Rider Validation: Investing for
 */
validateRiderInvestingFor = function(riderValidation,riderId){
	if(!document.getElementById(namespace+"investingFor")){
		return;
	}
	var investingFor=document.getElementById(namespace+"investingFor").value;

    $("#"+namespace+"forMySelf_"+riderId).hide();
    $("#"+namespace+"forFamily_"+riderId).hide();
    
    var isRiderAvailable=false;
    if(riderValidation.availableFor){
        riderValidation.availableFor.forEach((investingForObj, count)=>{
            if(investingForObj.name==investingFor){
                isRiderAvailable = true;
            }
        });
    }
    
    if(!isRiderAvailable && investingFor=="Myself"){
    	$("#"+namespace+"forFamily_"+riderId).show();
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
    	
    }else if(!isRiderAvailable && investingFor=="Family"){
    	$("#"+namespace+"forMySelf_"+riderId).show();
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
    }
    
    return isRiderAvailable;
}

/**
 * Critical Illness Rider Conditions 
 */
validateCriticalIllnessEntryAge = function(riderId){
	console.log(' validateCriticalIllnessEntryAge >> riderId ', riderId);
	var isRiderAvailable=false;
	var dateOfBirthInputVal = $(dateOfBirthJEl).val();
	if(riderId=="50003"){
		var age = calculateAge(dateOfBirthInputVal);
		//var PPT = Number($(policyForJEl).val());
		//console.log("PPT+age="+PPT+age);
		console.log(':: age :: ', +age);
		if(age>60){
			isRiderAvailable= false;
		}
		else{
			isRiderAvailable= true;
		}
	}
	else{
		isRiderAvailable= true
	}
	$("#"+namespace+"criticalIllnessRiderError_"+riderId).hide();

	if(!isRiderAvailable){
    	$("#"+namespace+"criticalIllnessRiderError_"+riderId).show();
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);

    }
	return isRiderAvailable;
}

/**
 * Rider Validation: Family Income benefit
 */
validateFamilyIncomeBenefit = function(riderValidation,riderId){
	if(!document.getElementById(namespace+"familyIncomeBenefit")){
		return;
	}
	var investingFor=document.getElementById(namespace+"familyIncomeBenefit").value;

    $("#"+namespace+"familyIncomeBenefitError_"+riderId).hide();
    
    var isRiderAvailable = false;
    if(investingFor==3 || riderValidation.isAvailableWithFib){
    	isRiderAvailable = true;
    }
    
    document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isRiderAvailable;
    
    if(!isRiderAvailable){
    	$("#"+namespace+"familyIncomeBenefitError_"+riderId).show();
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
    	
    }
    
    return isRiderAvailable;
}

/**
 * Generate-BI Request Body: Selected Rider Request Body
 */
generateSelectedRidersRequestBody = function(requestedRidersList, loadDefaultValues){
	var riderDetailsRequestBody={};
	document.getElementsByName(namespace+"selectedRiders").forEach(availableRider=>{
	    
		if(!availableRider.dataset){
			return;
		}
		
	    if(availableRider.dataset.isAvailable == "false" || !(loadDefaultValues || requestedRidersList.has(availableRider.value))){
	        riderDetailsRequestBody[availableRider.dataset.riderKey]="";
	        return;
	    }
	    if((document.getElementsByName(namespace+"selectedRiders").value == "50003" || document.getElementsByName(namespace+"selectedRiders").value =="50007") && age > 60){
	    	 riderDetailsRequestBody[availableRider.dataset.riderKey]="";
	    	 return;
	    }
	    
	    if(document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value)){
	        riderDetailsRequestBody[availableRider.dataset.riderKey]=document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value).value;
	    }else{
	         riderDetailsRequestBody[availableRider.dataset.riderKey]="1";
	    }
	});

	if(document.getElementById(namespace+"payorWaiverBenefitOption") && document.getElementById(namespace+"payorWaiverBenefitOption").value){
		riderDetailsRequestBody["PWB"]=document.getElementById(namespace+"payorWaiverBenefitOption").value;
	}
	else{
		riderDetailsRequestBody["PayorWaiverBenefitRider"] = "";
		riderDetailsRequestBody["PWB"]="";
	}
	
	return riderDetailsRequestBody;
}

/**
 * Generate-BI Request Body: Policy Options Request Body
 */
generatePolicyOptionsRequestBody = function(){
	var policyOptionsRequestBody={};
	
	policyOptionsRequestBody["FamilyIncomeBenefit"]=document.getElementById(namespace+"familyIncomeBenefit")?document.getElementById(namespace+"familyIncomeBenefit").value:"3";
	policyOptionsRequestBody["PlanOption"]=document.getElementById(namespace+"policyOption")?gssPolicyOptionsMap[document.getElementById(namespace+"policyOption").value]:"1";
	policyOptionsRequestBody["MaturityBenefitOption"]=document.getElementById(namespace+"payoutOption")?document.getElementById(namespace+"payoutOption").value:"1";
	
	return policyOptionsRequestBody;
}

/**
 * Generate-BI Request Body: Product Request Body
 */
generateProductRequestBody = function(requestedRidersList, loadDefaultRiderValues){
	var productRequestBody={};
	
	var paymentOptionVal = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"1";
	var investmentAmount = Number(document.getElementById(namespace+"investmentAmount")?document.getElementById(namespace+"investmentAmount").value.replaceAll(",",""):"0");
	var policyOption = document.getElementById(namespace+"policyOption")?gssPolicyOptionsMap[document.getElementById(namespace+"policyOption").value]:"1";
	var payFor = document.getElementById(namespace+"payFor")?document.getElementById(namespace+"payFor").value:"5";
	var policyTerm = document.getElementById(namespace+"policyTerm")?document.getElementById(namespace+"policyTerm").value:"40";
	
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
	
	productRequestBody["INPUT_MODE"]=paymentOptionVal;
	productRequestBody["PR_ANNPREM"]=Math.round(annualPremium);
	productRequestBody["PR_PPT"]=payFor;
	productRequestBody["policyOptions"]=generatePolicyOptionsRequestBody();
	productRequestBody["PR_PT"]=policyTerm;
	productRequestBody["riderDetails"]=generateSelectedRidersRequestBody(requestedRidersList, loadDefaultRiderValues);
	productRequestBody["PR_ID"]=productMetaData.productCode;
	
	
	if(isCustomerNri == 'true'){
		productRequestBody["NriDeclaration"]="Y";
		productRequestBody["ZP_NATIONALITY"]="2";

	}else{
		productRequestBody["NriDeclaration"]="N";
		productRequestBody["ZP_NATIONALITY"]="1";
	}

	/*
	 * Default attributes
	 */
	productRequestBody["CashFlow"]="N";
	productRequestBody["category"]="1";
	productRequestBody["comboDetails"]={};
	productRequestBody["isPdfByte"]="Y";
	productRequestBody["PR_CHANNEL"]="1";
	productRequestBody["PR_MI"]="0";
	productRequestBody["PR_ModalPrem"]="0";
	productRequestBody["PR_SA"]=0;
	productRequestBody["PR_SAMF"]="0";
	productRequestBody["emr_rate"]="1.00";
	productRequestBody["flat_rate"]="0";
	productRequestBody["REQUESTSOURCE"]="4";
	productRequestBody["LumpsumAmount"]="";
	productRequestBody["EqualAnnualInst"]="";

	return productRequestBody;
}

/**
 * Generate-BI Request Body: Product Details Request Body
 */
generateProductDetailsRequestBody = function(requestedRidersList, loadDefaultRiderValues){
	var productDetailsRequestBody={};
	
	productDetailsRequestBody["product"]=generateProductRequestBody(requestedRidersList, loadDefaultRiderValues);

	return productDetailsRequestBody;
}

/**
 * Generate-BI Request Body: Company Details Request Body
 */
generateCompanyDetailsRequestBody = function(){
	var companyDetailsRequestBody={};
	
	companyDetailsRequestBody["COMPANY_STATE"]=0;
	companyDetailsRequestBody["GSTIN"]="0";
	companyDetailsRequestBody["GSTIN_Number"]=0;

	return companyDetailsRequestBody;
}

/**
 * Generate-BI Request Body: Customer Details Request Body
 */
generateCustomerDetailsRequestBody = function(){
	var customerDetailsRequestBody={};
	
	customerDetailsRequestBody["agentDetails"]={
			"AGENT_ID": "TW9001"
	};
	
	var dateOfBirthInputVal = $(dateOfBirthJEl).val();
	var dateOfBirth = moment(dateOfBirthInputVal, "DD/MM/YYYY");
	
	var age = calculateAge(dateOfBirthInputVal);
	
	var proposerDOBStr = moment(dateOfBirth).format('DD MMM YYYY');
	var gender = $("input[name='" + genderEl + "']:checked").val();
	var email = $(emailJEl).val();
	var mobileNumber = $(mobileNumberJEl).val();
	var basicInvestingFor = $("input[name='" + basicInvestingForEl + "']:checked").val();
	var fullName = $(fullNameJEl).val();

	var genderShorName = gender == "Male" ? "M" : "F";
	var sameProposer = "true";
	
	var liDetails = {
		"IsExistingCustomer": "2",
		"LI_NAME": fullName,
		"LI_ENTRY_AGE": age,
		"LI_DOB": proposerDOBStr,
		"LI_GENDER": genderShorName,
		"LI_STATE": 0,
		"LI_MOBILENO": mobileNumber,
		"LI_EMAILID": email
	}
	
	if(basicInvestingFor == "Family") {
		sameProposer = "false";
		var assuredRelationVal = $("input[name='" + assuredRelationEl + "']:checked").val();
		var liGenderShortName = assuredRelationVal == "Son" ? "M" : "F";
		var assuranceFullName = $(assuranceFullNameJEl).val();
		var assuranceDobInputVal = $(assuranceDobJEl).val();
		var assuranceAge = calculateAge(assuranceDobInputVal);
		
		var assuranceDob = moment(assuranceDobInputVal, "DD/MM/YYYY");
		var assuranceDobStr = moment(assuranceDob).format('DD MMM YYYY');
		
		liDetails["LI_NAME"] = assuranceFullName;
		liDetails["LI_ENTRY_AGE"] = assuranceAge;
		liDetails["LI_GENDER"] = liGenderShortName;
		liDetails["LI_DOB"] = assuranceDobStr;
	}
	
	var proposerDetails = {
		"SameProposer": sameProposer,
		"PROPOSER_NAME": fullName,
		"PROPOSER_AGE": age,
		"PROPOSER_DOB": proposerDOBStr,
		"PROPOSER_GENDER": gender == "Male" ? "M" : "F"
	}
	
	customerDetailsRequestBody["liDetails"]=liDetails;
	customerDetailsRequestBody["proposerDetails"]=proposerDetails;
	
	return customerDetailsRequestBody;
}

/**
 * Generate-BI Request Body
 */
getGenerateBIRequestBody = function(requestedRidersList, loadDefaultRiderValues){
	
	generateBIRequestBody["companyDetails"] = generateCompanyDetailsRequestBody();
	generateBIRequestBody["customerDetails"] = generateCustomerDetailsRequestBody();
	generateBIRequestBody["productDetails"] = generateProductDetailsRequestBody(requestedRidersList, loadDefaultRiderValues);

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
	var policyOptionDisplayName = $(policyOptionJEl).find(':selected').data("name");
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
	updateLMSLeadData.append(namespace + "TotalPremiumAmount", $(investmentAmountJEl).val());
	updateLMSLeadData.append(namespace + "PolicyTerm", $(policyTermJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentTerm", $("#"+namespace+"payFor").val());
	updateLMSLeadData.append(namespace + "ProductName", $(productNameJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentFrequency", paymentFrequencyDisplayName);
	updateLMSLeadData.append(namespace + "PolicyOption", policyOptionDisplayName);
	updateLMSLeadData.append(namespace + "Recommended_Objective", investmentObjectiveDisplayName);
	updateLMSLeadData.append(namespace + "PayoutOptions", document.getElementById(namespace+"payoutOption").value);
	
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
	
	if(isCustomerNri == 'true'){
		updateLMSLeadData.append(namespace + "AlternatePhone", basicDetailsMap.nRIMobileNumber);

	}else{
		updateLMSLeadData.append(namespace + "AlternatePhone", "");
	}
	
	// Rider Deatails START
	var additionalBenefitVal = "No";
	if($(".riderSelectionInput").length > 0) {
		$(".riderSelectionInput").each(function(){
			var isAvailable = document.getElementById(this.id).dataset.isAvailable;
			var isRiderSelected = $(this).prop("checked");
			if(isAvailable && isRiderSelected) {
				additionalBenefitVal = "Yes";
				var riderName = $(this).data("riderName");
				var riderPrice = $(this).data("riderPrice");
				var riderKey = $(this).data("riderKey");
				var riderPT = $(this).data("riderPt");
				var riderPPT = $(this).data("riderPpt");
				var riderProductCode = $(this).val();
				updateLMSLeadData.append(namespace + riderKey +  "_YN", "Y");
				updateLMSLeadData.append(namespace + riderKey +  "PT", riderPT);
				updateLMSLeadData.append(namespace + riderKey +  "PPT", riderPPT);
				updateLMSLeadData.append(namespace + riderKey +  "SA", riderPrice);
				if($(riderPremiumAmountPrefixJEl + riderProductCode).length > 0) {
					var selectedRiderPremiumAmount = $(riderPremiumAmountPrefixJEl + riderProductCode).val();
					updateLMSLeadData.append(namespace + riderKey +  "Amount", selectedRiderPremiumAmount ? selectedRiderPremiumAmount : 0);
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
	var hasvalidationErrors = await guaranteedSavingsStarGenerateBI(getGenerateBIRequestBody(requestedRidersList, loadDefaultRiderValues));
	if(!hasvalidationErrors){
		await saveCustomerInvestmentData();
		updateLMSLead();
		
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
		// Showing Little Champ Modal if Customer Doesn't Clicked on Dont want
		// this add on after saving family details
		var isLittleChampBenefitOptedVal = $(isLittleChampBenefitOptedJEl).val();
		if(isLittleChampBenefitOptedVal != "No" && isLittleChampBenefitOptedVal != "Yes" && $(investingForJEl).val() == "Family") {
			// Set Form Submit Is Pending
			$(isFormSubmitPendingJEl).val("true");
			
			$(littleChampModalJEl).modal("show");
		} else {
			
		}
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
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(mobileNumberJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(emailJEl).val() + "</li>");
	$(personalDetailsJEl).append("<li class='nav-item'>" + $('input[name="' + investmentObjectiveEl + '"]:checked').data("name") + "</li>");
	var selectedInvestingFor = $(investingForJEl).val();
	$(personalDetailsJEl).append("<li class='nav-item'>" + $(investingForJEl).val() + "</li>");
	if(selectedInvestingFor == "Family") {
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceFullNameJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceDobJEl).val() + "</li>");
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
	
	initFamilyRadioButtons();
	
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
 * Render available family memeber options based on the user Age
 */
initFamilyRadioButtons = function() {
	if($(investingForJEl).val() == "Family") {
		var age = calculateAge($(dateOfBirthJEl).val());
		if(age >= 40) {
			if($("#" + investingForGrandsonEl).length == 0) {
				var grandsonFamilyRadio = '<input type="radio" data-gender="Male" data-name="GrandSon" value="GrandSon" name="' + familyRadioEl + '" id="' + investingForGrandsonEl + '">';
				grandsonFamilyRadio += '<label for="' + investingForGrandsonEl + '">' + grandSonLabel + '</label>';

				$(investingForWrapperJEl).append(grandsonFamilyRadio);
			}
			
			if($("#" + investingForGrandDaughterEl).length == 0) {
				var grandDaughterFamilyRadio = '<input type="radio" data-gender="Female" data-name="GrandDaughter" value="GrandDaughter" name="' + familyRadioEl + '" id="' + investingForGrandDaughterEl + '">';
				grandDaughterFamilyRadio += '<label for="' + investingForGrandDaughterEl + '">' + grandDaughterLabel + '</label>';
				
				$(investingForWrapperJEl).append(grandDaughterFamilyRadio);
			}
		} else {
			// Remove Grand Son and Grand Daughter Radio Button is exist for
			// Propose Age < 40
			if($("#" + investingForGrandsonEl).length > 0) {
				$("input[type=radio][name='" + familyRadioEl + "'][value='GrandSon']").remove();
				$("label[for='" + investingForGrandsonEl + "']").remove()
			}
			
			if($("#" + investingForGrandDaughterEl).length > 0) {
				$("input[type=radio][name='" + familyRadioEl + "'][value='GrandDaughter']").remove();
				$("label[for='" + investingForGrandDaughterEl + "']").remove()
			}
		}
	}
}

/**
 * Init Form Validation for Child and Spouse Individual and Joint Modal
 */
initChildSpouseValidation = function() {
	
	jQuery.validator.addMethod("customFullname", function(value, element) {
		if (/^([a-zA-Z]{2,}\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)/.test(value)) {
			return true;
		} else {
			return false;
		};
	}, enterYourXFullNameErrorMsg);
	
	// Child and Spouse Individual Modal
	$(childSpouseIndividualDetailsFormJEl).validate({
		errorClass: "error",
		errorElement: "div",
		onfocusout: function(element) {
           this.element(element);
        }
	});
	
	// Child and Spouse Joint Modal
	$(childSpouseJointDetailsFormJEl).validate({
		errorClass: "error",
		errorElement: "div",
		onfocusout: function(element) {
           this.element(element);
        }
	});
	
	initChildSpouseIndividualValidationRules();
}

initChildSpouseIndividualValidationRules = function() {
	
	$(spouseChildFullNameJEl + "-error").remove();
	$(spouseChildDOBJEl + "-error").remove();
	
	// Remove Rules if exist
	if($(spouseChildFullNameJEl).rules()) {
		$(spouseChildFullNameJEl).rules("remove");
	}
	if($(spouseChildDOBJEl).rules()) {
		$(spouseChildDOBJEl).rules("remove");
	}
	
	var selPolicyOption = $(policyOptionJEl).val();
	var spouseChildFullNameErrMsg = enterYourXFullNameErrorMsg;
	
	var spouseChildDOBErrMsg = enterYourXDateOfBirthErrorMsg;
	var ageMinMaxErrMsg = ageBetween18to60ErrorMsg;
	
	var childSpouseMinAge = 18;
	var childSpouseMaxAge = 60;
	
	if(selPolicyOption == lifePartnerKey) {
			spouseChildFullNameErrMsg = spouseChildFullNameErrMsg.replaceAll("{0}", "spouse's");
			spouseChildDOBErrMsg = spouseChildDOBErrMsg.replaceAll("{0}", "spouse's");
			ageMinMaxErrMsg = ageBetween18to60ErrorMsg;
			childSpouseMinAge = 18;
			childSpouseMaxAge = 60;
			
			$(childSpouseHeaderJEl).html(yourSpousesFullFetailsHearder);
			
			$("label[for='" + spouseChildFullNameEl + "']").html(enterYourSpouseNameLabel);
			$("label[for='" + spouseChildDOBEl + "']").html(yourSpousesDOBLabel);
		} else if(selPolicyOption == childIndividualLifeKey) {
			spouseChildFullNameErrMsg = spouseChildFullNameErrMsg.replaceAll("{0}", "child's");
			spouseChildDOBErrMsg = spouseChildDOBErrMsg.replaceAll("{0}", "child's");
			ageMinMaxErrMsg = ageBetween0to15ErrorMsg;
			childSpouseMinAge = 0;
			childSpouseMaxAge = 15;
			$(childSpouseHeaderJEl).html(yourChildsFullFetailsHearder);
			
			$("label[for='" + spouseChildFullNameEl + "']").html(enterYourChildsNameLabel);
			$("label[for='" + spouseChildDOBEl + "']").html(yourChildsDOBLabel);
		}
	
	// Add Rule with updated error messages
	$(spouseChildFullNameJEl).rules( "add", {
		required: true,
		customFullname: true,
		messages: {
		    required: spouseChildFullNameErrMsg,
		    customFullname: spouseChildFullNameErrMsg
		}
	});
	
	$(spouseChildDOBJEl).rules( "add", {
		required: true,
		minAge:childSpouseMinAge,
		maxAge:childSpouseMaxAge,
		messages: {
		    required: spouseChildDOBErrMsg,
		    minAge: ageMinMaxErrMsg,
  			maxAge: ageMinMaxErrMsg
		}
	});
}

initChildSpouseJointValidationRules = function() {
	var spouseFullNameErrDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", "spouse's");
	var spouseDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", "spouse's");
	
	var childFullNameErrDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", "child's");
	var childDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", "child's");
	
	$(spouseFullNameJEl + "-error").remove();
	$(spouseDOBJEl + "-error").remove();
	$(childFullNameJEl + "-error").remove();
	$(childDOBJEl + "-error").remove();
	
	// Add Rule with updated error messages
	$(spouseFullNameJEl).rules( "add", {
		required: true,
		customFullname: true,
		messages: {
		    required: spouseFullNameErrDisplayMsg,
		    customFullname: spouseFullNameErrDisplayMsg
		}
	});
	
	$(spouseDOBJEl).rules( "add", {
		required: true,
		minAge:18,
		maxAge:60,
		messages: {
		    required: spouseDOBErrDisplayMsg,
		    minAge: ageBetween18to60ErrorMsg,
  			maxAge: ageBetween18to60ErrorMsg
		}
	});
	
	// Add Rule with updated error messages
	$(childFullNameJEl).rules( "add", {
		required: true,
		customFullname: true,
		messages: {
		    required: childFullNameErrDisplayMsg,
		    customFullname: childFullNameErrDisplayMsg
		}
	});
	
	$(childDOBJEl).rules( "add", {
		required: true,
		minAge:0,
		maxAge:15,
		messages: {
		    required: childDOBErrDisplayMsg,
		    minAge: ageBetween0to15ErrorMsg,
  			maxAge: ageBetween0to15ErrorMsg
		}
	});
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
    	case "GrandSon":
    		$(investingForFullNameLableJEl).html(grandsonsFullNameLabel);
    		$(investingForDOBLabelJEl).html(grandsonsDobLabel);
    		break;
    	case "GrandDaughter":
    		$(investingForFullNameLableJEl).html(grandDaughterFullNameLabel);
    		$(investingForDOBLabelJEl).html(grandDaughterDobLabel);
    		break;
    }
}

/**
 * Family Pop-up Radio Buttons Change event
 */
onFamilyRadioChange = function(event) {
	var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
	$(investingForFullNameJEl).val("");
	$(investingForDOBJEl).val("");
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

	// Remove Rules if exist
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
		minAge:0,
		maxAge:17,
		messages: {
		    required: investingForDOBErrDisplayMsg,
		    minAge: ageBetween0to17ErrorMsg,
  			maxAge: ageBetween0to17ErrorMsg
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
	return await guaranteedSavingsStarGenerateBI(getGenerateBIRequestBody(selectedRidersList, false));
}