$(document).ready(function () {
	initRiders();
});

/**
 * Customize Page Initilazation
 */
initRiders = function(){
	/*
	 * Initializing the Event Listeners
	 */
	initRiderEvents();
}

/**
 * Initialization of the Event Listeners
 */
initRiderEvents = function(){
	/*
	 * On click event Listeners to add or remove a rider
	 */
	$(".riderSelectionInput").on("click", function(){
		this.dataset.isAvailable="true";
		addRemoveRider(this.id, this.checked);
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
}

/**
 *auto-selecte selected riders on page load
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
 */
updateRiderData = function(rider){
	var riderElement = document.getElementById(namespace+"selectedRider_"+rider.id);

	if(riderElement){
		riderElement.checked=true;
		riderElement.dataset.isAvailable = true;
		
		if(rider.pt && rider.ppt){
			riderElement.dataset.riderPt = rider.pt;
			riderElement.dataset.riderPpt = rider.ppt;
		}
	}

	if(document.getElementById(namespace+"riderPremiumAmount_"+rider.id)){
		var riderPremiumDropdown = document.getElementById(namespace+"riderPremiumAmount_"+rider.id);
	}else if(document.getElementById(namespace+"payorWaiverBenefitOption")){
		var riderPremiumDropdown = document.getElementById(namespace+"payorWaiverBenefitOption");
		if(rider.premium){
			riderPremiumDropdown.value=rider.premium;
		}	
	}
}

/**
 * Render Rider Premium Amount
 */
renderRiderPremiumAmount = function(biResponseJson){
	
	if(!biResponseJson || !biResponseJson.InputValidationStatus || !biResponseJson.InputValidationStatus.length){
		return;
	}

	var basePremiumAmount = 0;
	var basePlanPriceAmount = 0;
	var riderPremiumAmount = 0;
	
	if(biResponseJson.BIJson){
		basePremiumAmount = biResponseJson.BIJson[0].MODAL_FYP;
        document.getElementById(namespace+"basePremiumAmount").value=Math.round(basePremiumAmount);
        document.getElementById(namespace+"basePlanPriceAmount").innerHTML = rupeeSignLabel.concat(Math.round(biResponseJson.BIJson[0].MODAL_DISPLAY_TAX).toLocaleString("en-IN"));
        document.getElementById(namespace+"basePlanPriceAmountMob").innerHTML = rupeeSignLabel.concat(Math.round(biResponseJson.BIJson[0].MODAL_DISPLAY_TAX).toLocaleString("en-IN"));
	}

	biResponseJson.InputValidationStatus.forEach(function(productRider){
            const riderId=productRider.ProductId;
            const riderPremiumAmount = productRider.BasePremium + productRider.Tax;
            const basePremiumAmount = productRider.ModalPremium;

           /*
             * Set Base plan price
             */
            if(productMetaData.productCode == riderId && document.getElementById(namespace+"basePlanPrice")){
                document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice = Math.round(basePremiumAmount);
            }

            /*
             * Set rider Amount
             */
            if(document.getElementById(namespace+"riderPriceAmount_"+riderId)) {
                document.getElementById(namespace+"riderPriceAmount_"+riderId).innerHTML = rupeeSignLabel.concat(Math.round(riderPremiumAmount).toLocaleString("en-IN"));
            }

            /*
             * Add Price Amount to the riders data-set
             */
            if(document.getElementById(namespace+"selectedRider_"+riderId)) {
                document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPrice = riderPremiumAmount;
                document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt = productRider.PT;
                document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPpt = productRider.PPT;
                document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderSa = productRider.SA;
                
            }
	    
	})
}

/**
 * Render Benefits Premium Amount
 */
renderBenefitsPremiumAmount = function(biResponseJson){
	if(!biResponseJson || biResponseJson.BIJson == null || biResponseJson.Status==="Fail"){
		return;
	}

	var bhbMinModalPrem = biResponseJson.BIJson[0].BHB_MODAL_FYP;
	var payFrequency = $(paymentOptionJEl).val();
	var childRiderPriceAmount = biResponseJson.BIJson[0].CHILD_MODAL_PREM + biResponseJson.BIJson[0].CHILD_TAX;
	var bhbRiderPriceAmount = biResponseJson.BIJson[0].BHB_MODAL_PREM + biResponseJson.BIJson[0].BHB_TAX;
	
	var childRiderBaseAmount = biResponseJson.BIJson[0].CHILD_MODAL_FYP;
	var bhbRiderBaseAmount = biResponseJson.BIJson[0].BHB_MODAL_FYP;

	if(document.getElementById(namespace+"riderPriceAmount_CHILD")) {
		document.getElementById(namespace+"riderPriceAmount_CHILD").innerHTML = rupeeSignLabel.concat(Math.round(childRiderPriceAmount).toLocaleString("en-IN"));
		document.getElementById(namespace+"selectedRider_CHILD").dataset.riderPrice = Math.round(childRiderPriceAmount);
		document.getElementById(namespace+"selectedRider_CHILD").dataset.riderPt = biResponseJson.BIJson[0].CHILD_TERM;
        document.getElementById(namespace+"selectedRider_CHILD").dataset.riderPpt = biResponseJson.BIJson[0].CHILD_PPT;
        document.getElementById(namespace+"selectedRider_CHILD").dataset.riderSa = biResponseJson.BIJson[0].CHILD_SA;
	}
	
	if(document.getElementById(namespace+"riderPriceAmount_BHB")) {
		document.getElementById(namespace+"riderPriceAmount_BHB").innerHTML = rupeeSignLabel.concat(Math.round(bhbRiderPriceAmount).toLocaleString("en-IN"));
		document.getElementById(namespace+"selectedRider_BHB").dataset.riderPrice = Math.round(bhbRiderPriceAmount);
		document.getElementById(namespace+"selectedRider_BHB").dataset.riderPt = biResponseJson.BIJson[0].BHB_TERM;
        document.getElementById(namespace+"selectedRider_BHB").dataset.riderPpt = biResponseJson.BIJson[0].BHB_PPT_DISP;
        document.getElementById(namespace+"selectedRider_BHB").dataset.riderSa = biResponseJson.BIJson[0].BHB_SA;
	}

}

/**
 * Add or Remove a rider from the Plan
 */
addRemoveRider = function(selectedRiderId, isAdded){
	console.log("addRemoveRider function::::" + selectedRiderId);
	var selectedRider = document.getElementById(selectedRiderId);
	console.log("addRemoveRider function::::" + selectedRider + "IsAdded::: " + isAdded);
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
			console.log("inside if:::");
			selectedRiderDetails.premium=document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).value;
			document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).disabled=true;
		}else if(selectedRider.dataset.riderKey =="CHILD" && document.getElementById(namespace+"childFutureProtectBenefit")){
			//selectedRiderDetails.premium=document.getElementById(namespace+"childFutureProtectBenefit").value;
			selectedRiderDetails.premium=selectedRider.dataset.riderSa;
			document.getElementById(namespace+"childFutureProtectBenefit").disabled=true;
		}else if(selectedRider.dataset.riderKey =="BHB"){
			selectedRiderDetails.premium=selectedRider.dataset.riderSa;
		}else if(selectedRider.dataset.riderKey =="WaiverOfPremiumRider"){
			selectedRiderDetails.premium = selectedRider.dataset.riderSa;
		}
		console.log("selectedRiderDetails::::" + JSON.stringify(selectedRiderDetails));
		selectedRidersList.set(selectedRider.value, selectedRiderDetails);
	}else{
		selectedRidersList.delete(selectedRider.value);
		if(document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value)){
			document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).disabled=false;
		}else if(selectedRider.dataset.riderKey =="CHILD" && document.getElementById(namespace+"childFutureProtectBenefit")){
			document.getElementById(namespace+"childFutureProtectBenefit").disabled=false;
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
 * Update Your Plan Premium Details - Add or Remove riders from the plan premium details
 */
updatePlanPremiumDetails = function(biResponseJson){

		if(!biResponseJson || biResponseJson.BIJson == null || biResponseJson.Status==="Fail"){
            return;
		}

		const totalPremIncTax = biResponseJson.BIJson[0].TOTAL_PREM_TAX_DISPLAY;
		
		document.getElementById(namespace+"selectedRidersListContainer").innerHTML="";
		document.getElementById(namespace+"selectedRidersListContainerMob").innerHTML="";
		
		if(!selectedRidersList || !selectedRidersList.size){
			$("#"+namespace+"selectedRidersList").hide();
			
			/*
			 * Set Premium total premium amount to Base plan price
			 */
			if(document.getElementById(namespace+"totalPayPremiumAmount")){
				document.getElementById(namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(Math.round(biResponseJson.BIJson[0].MODAL_DISPLAY_TAX).toLocaleString("en-IN"));
				document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML = rupeeSignLabel.concat(Math.round(biResponseJson.BIJson[0].MODAL_DISPLAY_TAX).toLocaleString("en-IN"));
				document.getElementById(namespace+"premiumAmount").value = Math.round(totalPremIncTax);
			}
			return;
		}
	
		$("#"+namespace+"selectedRidersList").show();
	
		const selectedRidersListDiv = document.getElementById(namespace+"selectedRidersListContainer");
		const selectedRidersListDivMob = document.getElementById(namespace+"selectedRidersListContainerMob");

	selectedRidersList.forEach(function(rider){
		
		/*
		 * Remove Unchecked riders
		 */
		if(!$("#"+namespace+"selectedRider_"+rider.id).prop("checked")){
			selectedRidersList.delete(rider.id);
			return;
		}
		
		/*
		 * To display Rider Name
		 */
		const selectedRiderNameElement = document.createElement("span");
		selectedRiderNameElement.setAttribute("id", namespace+"selectedRiderName"+rider.id);
		selectedRiderNameElement.appendChild(document.createTextNode(rider.name));

		/*
		 * To display Price
		 */

		const riderObject = biResponseJson.InputValidationStatus.find(obj => obj['ProductId'] === Number(rider.id));


        var riderPremiumAmount = 0;
        if(riderObject){
            riderPremiumAmount = riderObject.BasePremium + riderObject.Tax;
        }

        if(rider.id === "BHB") {
            riderPremiumAmount = biResponseJson.BIJson[0].BHB_MODAL_PREM + biResponseJson.BIJson[0].BHB_TAX;
        }

        if(rider.id === "CHILD") {
            riderPremiumAmount = biResponseJson.BIJson[0].CHILD_MODAL_PREM + biResponseJson.BIJson[0].CHILD_TAX;
        }

		const selectedRiderPriceElement = document.createElement("span");
		selectedRiderPriceElement.setAttribute("id", namespace+"selectedRiderPrice"+rider.id);
		selectedRiderPriceElement.appendChild(document.createTextNode(rupeeSignLabel.concat(Math.round(riderPremiumAmount).toLocaleString("en-IN"))));

		/*
		 * Rider Premium Div
		 */
		var selectedRider = document.createElement("div");
		selectedRider.setAttribute("id", namespace+"selectedRider"+rider.id);
		selectedRider.setAttribute("data-rider-price", rider.price);
		selectedRider.setAttribute("data-rider-id", rider.price);
		selectedRider.classList.add("d-flex");
		selectedRider.classList.add("justify-content-between");
		selectedRider.classList.add("mb-3");
		selectedRider.appendChild(selectedRiderNameElement);
		selectedRider.appendChild(selectedRiderPriceElement);

		selectedRidersListDiv.appendChild(selectedRider);
		
		/*
		 * Rider Premium Div for Mobile
		 */
		var selectedRiderMob = selectedRider.cloneNode(true);
		selectedRidersListDivMob.appendChild(selectedRiderMob);
	});
	
	/*
	 * Calculate Total Premium Amount afetr addition of the Riders
	 */
	if(document.getElementById(namespace+"totalPayPremiumAmount")){
		var riderPremAmtTotal = calculateTotalPremiumAmount(biResponseJson);
		document.getElementById(namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(Math.round(riderPremAmtTotal).toLocaleString("en-IN"));
		document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML = rupeeSignLabel.concat(Math.round(riderPremAmtTotal).toLocaleString("en-IN"));
		document.getElementById(namespace+"premiumAmount").value = Math.round(totalPremIncTax);
	}
}

/**
 * Calculate Total Premium Amount
 */
calculateTotalPremiumAmount = function(biResponseJson){

	var totalPremiumAmount = biResponseJson.BIJson[0].MODAL_DISPLAY_TAX;
	
	if(!selectedRidersList || !selectedRidersList.size){
		return totalPremiumAmount;
	}
	
	selectedRidersList.forEach(function(rider){
        const riderObject = biResponseJson.InputValidationStatus.find(obj => obj['ProductId'] === Number(rider.id));
		
        totalPremiumAmount = riderObject?totalPremiumAmount+Math.round(riderObject.BasePremium + riderObject.Tax):totalPremiumAmount;

		if(rider.id === "BHB") {
			totalPremiumAmount = totalPremiumAmount + Math.round(biResponseJson.BIJson[0].BHB_MODAL_PREM + biResponseJson.BIJson[0].BHB_TAX);
        }

        if(rider.id === "CHILD") {
        	totalPremiumAmount = totalPremiumAmount + Math.round(biResponseJson.BIJson[0].CHILD_MODAL_PREM + biResponseJson.BIJson[0].CHILD_TAX);
        }
		
	});
	
	
	return totalPremiumAmount;
}


/**
 * Rider Validation
 */
validateProductRiders = function(){

	riderValidations.forEach((riderValidation,riderId)=>{

		/**
		 * Skip the Benefits from the Object
		 */
		if(riderValidation.isBenefit){
			return;
		}
		
		if(selectedRidersList.has(riderId)){
			document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable = true;
			return;
		}
		
		var isInvestingForValid = validateRiderInvestingFor(riderValidation,riderId);
		var isEntryAgeValid = validateEntryAge(riderId);
		var isCriticalIllnessAgeValid = validateCriticalIllnessEntryAge(riderId);

		document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isEntryAgeValid && isCriticalIllnessAgeValid;
	
	});
	
}


validateEntryAge = function(riderId){
	var isRiderAvailable=false;
	const spouseName = familyDetailsResponseJson && familyDetailsResponseJson.spouseName;
	const childDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.childDateOfBirth;	
	var age = calculateAge($(dateOfBirthJEl).val());

	if(riderId==="50007"){
		var age = calculateAge($(dateOfBirthJEl).val());
		var PPT = Number($(policyForJEl).val());	
		if(PPT+age>70|| age>60){
			isRiderAvailable= false;
		}
		else{
			isRiderAvailable= true;
		}
	}
	else{
		isRiderAvailable= true;
	}

	if (!isRiderAvailable && age>60){
    	$("#"+namespace+"WOPAgeError_"+riderId).show();
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
    }else if(!isRiderAvailable){
    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
		$('#' + namespace + 'rider_' + riderId).hide();
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