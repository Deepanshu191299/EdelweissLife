/*var namespace = portletNamespace;
var selectedRidersList = new Map();*/

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
		if(this.dataset.riderKey =="PayorWaiverBenefitRider" && document.getElementById(namespace+"payorWaiverBenefitOption") && 
		   !document.getElementById(namespace+"payorWaiverBenefitOption").value){
			//TODO: Show Error in popup
			$("#errorMessageModal").find("#errorMessageContainer").empty();
			$("#errorMessageModal").find("#errorMessageContainer").html("Select a value before adding Payor Waiver Benefit");
			$("#errorMessageModal").modal('show');
			this.checked=false;
			return;
		}
		
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
	var riderPremiumDropdown;
	riderElement.checked=true;
	riderElement.dataset.isAvailable = true;
	
	if(rider.pt && rider.ppt){
		riderElement.dataset.riderPt = rider.pt;
		riderElement.dataset.riderPpt = rider.ppt;
	}

	if(document.getElementById(namespace+"riderPremiumAmount_"+rider.id)){
		riderPremiumDropdown = document.getElementById(namespace+"riderPremiumAmount_"+rider.id);
	
	//To Add Rider Sum Assured Dynamically in the Dropdown which was selected earlier
		riderPremiumDropdown.innerHTML="";
		const premiumOption = document.createElement("option");
		premiumOption.setAttribute("class", "productAmounts");
		premiumOption.setAttribute("value", rider.premium);
		premiumOption.setAttribute("data-amount", rider.premium);
		premiumOption.appendChild(document.createTextNode(rupeeSignLabel.concat(Math.round(rider.premium).toLocaleString("en-IN"))));
		riderPremiumDropdown.appendChild(premiumOption);
	
	}else if(document.getElementById(namespace+"payorWaiverBenefitOption")){
		riderPremiumDropdown = document.getElementById(namespace+"payorWaiverBenefitOption");
	}
	riderPremiumDropdown.value=rider.premium;
}

/**
 * Render Rider Premium Amount
 */
renderRiderPremiumAmount = function(biResponseJson){
	if(!biResponseJson || !biResponseJson.InputValidationStatus || !biResponseJson.InputValidationStatus.length){
		return;
	}
	biResponseJson.InputValidationStatus.forEach(function(productRider){
	    const riderId=productRider.ProductId;
	    const riderPremiumAmountWoTax = productRider.BasePremium;
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
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPriceWoTax = riderPremiumAmountWoTax;
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPrice = riderPremiumAmount;
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt = productRider.PT;
	    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPpt = productRider.PPT;
    	}
	    
	})
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
	});
}

/**
 * Update Your Plan Premium Details - Add or Remove riders from the plan premium details
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
		totalPremiumAmount = (rider.price)?totalPremiumAmount+Math.round(rider.price):totalPremiumAmount;
	});
	
	return totalPremiumAmount;
}

/**
 * Rider Validation
 */
validateProductRiders = function(){
	riderValidations.forEach((riderValidation,riderId)=>{
		
		var isInvestingForValid = validateRiderInvestingFor(riderValidation,riderId);
		
		if(selectedRidersList.has(riderId) && isInvestingForValid){
			document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable = true;
			return;
		}
		
		
		var isValidMaturityAge = true;
		var age = calculateAge($(dateOfBirthJEl).val());
		var riderPT = document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt ? 
			Number(document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt) : Number($("#"+namespace+"policyTerm").find("option:selected").text());
		
		riderPT = riderId=="50007" ? Number($("#"+namespace+"payFor").val()) : riderPT;
				
		if(riderValidation.maximumMaturityAge && riderValidation.maximumMaturityAge < age + riderPT){
			isValidMaturityAge = false;
		}
		
		document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isValidMaturityAge;
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
 * Update Rider Premium Dropdown Field
 */
updateRiderPremiumDropdowns = function(sumAssured){
	
	riderValidations.forEach((riderValidation,riderId)=>{
		var dropdownList = [];
		var dropdownPeakLimit = sumAssured;
		var minPremiumAmt = riderValidation.minimumPremiumAmount;
		var maxPremiumAmt = riderValidation.maximumPremiumAmount;
		
		if(dropdownPeakLimit != 0 && minPremiumAmt != 0){
			
			if(maxPremiumAmt != 0 && dropdownPeakLimit > maxPremiumAmt){
				dropdownPeakLimit = maxPremiumAmt;
			}
			
			while(minPremiumAmt <= dropdownPeakLimit){
				dropdownList.push(minPremiumAmt);
				minPremiumAmt += riderValidation.frequencyOfPremium;
				
				if(minPremiumAmt > dropdownPeakLimit){
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

calculateSumAssured = function (riderValidation){
	
	if(document.getElementById(namespace+"sumAssured").value){
		return document.getElementById(namespace+"sumAssured").value;
	}else {
		var payFor = document.getElementById(namespace+"payFor")?document.getElementById(namespace+"payFor").value:"5";
		var paymentOptionVal = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"1";
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
		
		if(payFor < riderValidation.payForYear){
			return annualPremium * riderValidation.multiplierIfLessThanPayFor;
		}else{
			return annualPremium * riderValidation.multiplierIfGreaterThanPayFor;
		}
	}
}