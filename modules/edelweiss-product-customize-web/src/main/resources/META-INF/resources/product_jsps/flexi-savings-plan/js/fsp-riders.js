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
		if(selectedRidersList.has(riderId)){
			document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable = true;
			return;
		}
		var isInvestingForValid = validateRiderInvestingFor(riderValidation,riderId);
		var isCriticalIllnessAgeValid = validateCriticalIllnessEntryAge(riderId);
		var isWOPAgeValid = validateWOPEntryAge(riderId);
		
		var isValidMaturityAge = true;
		var age = calculateAge($(dateOfBirthJEl).val());
		var riderPT = document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt ? 
			Number(document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPt) : Number($("#"+namespace+"policyTerm").find("option:selected").text());
		
		riderPT = riderId=="50007" ? Number($("#"+namespace+"payFor").val()) : riderPT;
				
		if(riderValidation.maximumMaturityAge && riderValidation.maximumMaturityAge < age + riderPT){
			isValidMaturityAge = false;
		}
		
		document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isValidMaturityAge && isCriticalIllnessAgeValid && isWOPAgeValid;
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

validateWOPEntryAge = function(riderId){
    console.log(' validateWOPEntryAge >> riderId ', riderId);
    var isRiderAvailable=false;
    var dateOfBirthInputVal = $(dateOfBirthJEl).val();
    if(riderId=="50007"){
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
    $("#"+namespace+"WOPAgeError_"+riderId).hide();
    if(!isRiderAvailable){
        $("#"+namespace+"WOPAgeError_"+riderId).show();
        document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
        addRemoveRider(namespace+"selectedRider_"+riderId, false);
}
    return isRiderAvailable;
}