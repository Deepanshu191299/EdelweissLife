var namespace = portletNamespace;
var stage = "Customise";
var selectedRidersList = new Map();
var generateBIRequestBody = {};
var basicInvestingFor = document.getElementById(namespace+"investingFor");
var editSummaryFormValidator = portletNamespace + "editSummaryForm";
var customerDob = $(dateOfBirthJEl).val();
var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
familyDetailsResponseJson = JSON.parse(familyDetailsResponseJson);
var policyOptionJEl = "#" + policyOptionEl,
riderPremiumAmountPrefixEl = namespace + "riderPremiumAmount_",
riderPremiumAmountPrefixJEl = "#" + riderPremiumAmountPrefixEl,
payorWaiverBenefitOptionJEl = "#" + namespace + "payorWaiverBenefitOption";
var productEligibilityRelJson = JSON.parse(productEligibilityRelJson);
var eligibilityCookie = document.cookie.split('; ').find(row => row.startsWith('isEligibleforZP' + '='))?.split('=')[1];
var isEligibleforZP = false;
var isCHILDAvailable = false;
var isBHBAvailable = false;
if(eligibilityCookie!=undefined){
	isEligibleforZP = eligibilityCookie;
}

var setDefaultBHB = true;

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
	 * Formate Life Cover
	 */
	$(lifeCoverJEl).val(Math.round($("#"+namespace+"lifeCover").val().replaceAll(",","")).toLocaleString("en-IN"));

    /*
	 *Update Plan Option based on LI/Spouse Age
	 */
    updatePlanOptionDropdown();

	/*
	 *Render Life Cover in Words
	 */
	populateLifeCoverInWords();
	
	/*
	 *Render Policy Term dropdown
	 */
	updatePolicyTermDropdown();
	
	/*
	 *Render Policy PayFor dropdown
	 */
	setPayForDropdown();
	updatePayForDropdown();
	
	/*
	 * Load Selected/default values
	 */
	populateSelectedValue();
	
	/*
	 *Render Policy PayFor dropdown
	 */

	showHideWOPCard();

	/*
	 * Show Hide Payout Fields
	 */
	showHidePayoutFields();
	
	/*
	 * Show Hide Premium Break Benefit
	 */	
	showHidePremiumBreakBenefit();

	/*
	 * Load User Selected Riders
	 */
	loadCustomersSavedRiders();
	
	/*
     * Show Hide CHILD Benefit
     */
	showHideBHBCard();
	
	/*
	 * Show Hide CHILD Benefit
	 */	
	showHideCHILDCard();

	/*
	 * Validate and Update Customize page fields and other details
	 */
	updateCustomizePage(selectedRidersList, true);

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

	$(lifeCoverJEl).blur(function(){
		updateMinimumLifeCoverAmount();
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(lifeCoverJEl).on("keyup", populateLifeCoverInWords);
	$(lifeCoverJEl).on("input", allowOnlyNumbersHandler);

	$(incomeTypeJEl).on("change", function(){
		showHidePayoutFields();		
	});
	
	
	/*
	 * Update changes on selection of Plan Option
	 */
	$(planOptionJEl).on("change", function(){
		updatePolicyTermDropdown();
		updatePayForDropdown();
		selectDefaultPayForOption();
		showHideCHILDCard();
		premiumBreakBenefitRelField();
		updateCustomizePage(selectedRidersList, false);
	});
	premiumBreakBenefitRelField();
	
	$(policyTermJEl).on("change", function(){
		onPolicyTermChange($(this).val());
		showHidePremiumBreakBenefit();
		showHideCHILDCard();
		updateCustomizePage(selectedRidersList, false);
	});
	

	$(policyForJEl).on("change", function(){
		showHidePremiumBreakBenefit();
		showHideWOPCard();
		updateCustomizePage(selectedRidersList, false);
	});

	$(paymentOptionJEl).on("change", function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(incomeTypeJEl).on("change", function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(incomePeriodJEl).on("change", function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(incomePercentageJEl).on("change", function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(premiumBreakBenefitJEl).on("change", function(){
		updateCustomizePage(selectedRidersList, false);
	});
	
	$(".modal-Income").blur(function(){
		var annInc = $(".modal-Income").val().replaceAll(",","");
		$(".modal-Income").val(Number(annInc).toLocaleString("en-IN"));
	});

	/*
	 * Submit product details and redirect to Summary Page
	 */
	$(premiumPayDetailsJEl).on("click", async function(){
		if(isEligibleforZP) {
		    if($("#"+namespace+"selectedRider_CHILD").prop("checked") === false){
                isCHILDAvailable = false;
		    }
		    if($("#"+namespace+"selectedRider_BHB").prop("checked") === false){
                isBHBAvailable = false;
            }
		    
		    /*
		     * Actuall BHB Status will be set in Generate BI
		     */
		    setDefaultBHB = false;
			
		    var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
			if(!hasValidationErrors){
				/*
				 * Set Applicate Stage to Summary
				 */
				stage="Summary";
				
				updateLMSLead();
				await saveCustomerInvestmentData();
				//callZPWebEngage();
				$("#"+namespace+"customizeForm").submit();
			}
		}else {
			$("#verifyCustEligibilityModal").modal("show");
			$(".verify-eligibility-btn").removeClass("d-none");
			$(".eligibility-success-btn").addClass("d-none");
			$(".zp-eligibility-success-msg").addClass("d-none");
		}
	});
	
	/*
	 * Submit product details and redirect to Summary Page
	 */
	$('.eligibility-proceed-btn').on("click", async function(){
		if(isEligibleforZP) {
		     if($("#"+namespace+"selectedRider_CHILD").prop("checked") === false){
                isCHILDAvailable = false;
             }
            if($("#"+namespace+"selectedRider_BHB").prop("checked") === false){
                isBHBAvailable = false;
            }
            
            /*
		     * Actuall BHB Status will be set in Generate BI
		     */
		    setDefaultBHB = false;
            
			var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
			if(!hasValidationErrors){
				
				/*
				 * Set Applicate Stage to Summary
				 */
				stage="Summary";
				
				updateLMSLead();
				await saveCustomerInvestmentData();
				//callZPWebEngage();
				$("#"+namespace+"customizeForm").submit();
			}

		}else {
			$("#verifyCustEligibilityModal").modal("show");
			$(".verify-eligibility-btn").removeClass("d-none");
			$(".eligibility-success-btn").addClass("d-none");
			$(".zp-eligibility-success-msg").addClass("d-none");
		}
	});
	
	
	/*
	 * Post eligibility check verification: Submit product details and redirect to Summary Page
	 */
	$(verifyEligibilityBtnJEl).on("click", function(){
        var isValidEntry = verifyZPEligibility("pay-premium");
        if(isValidEntry){
            $(".verify-eligibility-btn").addClass("d-none");
            $(".eligibility-success-btn").removeClass("d-none");
            $(".zp-eligibility-success-msg").removeClass("d-none");
            $(".zp-eligibility-sorry-msg").addClass("d-none");
            createCookieForZPEligibility("isEligibleforZP", "true", 1);
            $(".eligibility-failure-btn").addClass("d-none");
        }else{
        	$(".verify-eligibility-btn").addClass("d-none");
            $(".eligibility-failure-btn").removeClass("d-none");
            $(".zp-eligibility-success-msg").addClass("d-none");
            $(".zp-eligibility-sorry-msg").removeClass("d-none");
        }
	});
	
	
	/*
	 * Eligibility check confirmed: Submit product details and redirect to Summary Page
	 */
	$(eligibilityOkBtnJEl).on("click", async function(){
	     if($("#"+namespace+"selectedRider_CHILD").prop("checked") === false){
            isCHILDAvailable = false;
         }
         if($("#"+namespace+"selectedRider_BHB").prop("checked") === false){
            isBHBAvailable = false;
         }
         
         /*
          * Actuall BHB Status will be set in Generate BI
          */
         setDefaultBHB = false;
         
		var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
		if(!hasValidationErrors){
			
			/*
			 * Set Applicate Stage to Summary
			 */
			stage="Summary";
			
			updateLMSLead();
			await saveCustomerInvestmentData();
			//callZPWebEngage();
			$("#"+namespace+"customizeForm").submit();			
		}
	});
	
	/*
	*   if not eligible then close the modal
	*/
	$(eligibilityNotOkBtnJEl).on("click", async function(){
		$("#verifyCustEligibilityModal").modal("hide");
		$(".eligibility-failure-btn").addClass("d-none");
		$(".zp-eligibility-sorry-msg").addClass("d-none");
	});
	
	$(".eligibility-modal").on("change", function(){
		$(".verify-eligibility-btn").removeClass("d-none");
		$(".eligibility-success-btn").addClass("d-none");
		$(".zp-eligibility-success-msg").addClass("d-none");
		$(".zp-eligibility-sorry-msg").addClass("d-none");
		$(".eligibility-failure-btn").addClass("d-none");
	});
	
	
	$("#closeModal").click(function(){
        $("#verifyCustEligibilityModal").modal("hide");
    });
}

allowOnlyNumbersHandler = function(event) {
	this.value = this.value.replace(/[^0-9]/g, '');
}

$("label.motion-label").siblings(".form-control").on("click",function(){ 
	$(this).siblings(".motion-label").addClass("motion-label-heading");
	$(this).focus();
	
});

$("label.motion-label").siblings(".form-control").focus(function(){ 
	$(this).siblings(".motion-label").addClass("motion-label-heading");
});

$("label.motion-label").siblings(".form-control").blur(function(){ 
	if(!$(this).val()){
		$(this).siblings(".motion-label").removeClass("motion-label-heading")
	}
});	

showHidePayoutFields = function(){
	if ($(incomeTypeJEl).val() === "25") {
		$(".income-period-div").parent().removeClass("d-none")
		  $(".income-period-div").removeClass('d-none');
		  $(".income-percentage-div").parent().addClass("d-none")
		  $(".income-percentage-div").addClass('d-none');
		  $(incomePeriodJEl).val($(incomePeriodJEl +" option").eq(0).val());
	} else if ($(incomeTypeJEl).val() === "26") {
		 $(".income-period-div").parent().removeClass("d-none");
		  $(".income-period-div").removeClass('d-none');
		  $(".income-percentage-div").parent().removeClass("d-none")
		  $(".income-percentage-div").removeClass('d-none');
		  $(incomePeriodJEl).val($(incomePeriodJEl +" option").eq(0).val());
		  updateIncomePercentageDropdown();
	} else {
		$(".income-period-div").parent().addClass("d-none");
		$(".income-percentage-div").parent().addClass("d-none");
		  $(".income-period-div").addClass('d-none');
		  $(".income-percentage-div").addClass('d-none');
		  $(incomePeriodJEl).val("");
		  $(incomePercentageJEl).val("");
	}
}

/*
 * Load Default/User selected Values
 */
populateSelectedValue = function(){
	var selectedPolicyTerm = $(policyTermJEl).data() ? $(policyTermJEl).data().selectedValue : 0;
	var selectedPolicyFor = $(policyForJEl).data() ? $(policyForJEl).data().selectedValue : 0;
	
	if(selectedPolicyTerm){
		var selectedPolicyTermOption = $(policyTermJEl+" option[value='"+selectedPolicyTerm+"']");
		if(selectedPolicyTermOption && selectedPolicyTermOption.length>0){
			$(policyTermJEl).val(selectedPolicyTerm);
			onPolicyTermChange(selectedPolicyTerm);
		}
	}
	
	if(selectedPolicyFor){
		var selectedPolicyForOption = $(policyForJEl+" option[value='"+selectedPolicyFor+"']");
		if(selectedPolicyForOption && selectedPolicyForOption.length>0){
			$(policyForJEl).val(selectedPolicyFor);
		}
	}
}

/**
 * Verify ZP Product Eligibility
 */
verifyZPEligibility = function(action) {
    console.debug("DEBUG:: productEligibilityRelJson::");
    console.debug(productEligibilityRelJson);

    var annIncome = 0;
    var eduKey = "";
    var occupationKey = "";
    var isValidEntry = false;

    if(action==="pay-premium"){
        annIncome = $('.modal-Income').val();
        eduKey = $('.modal-educationQualification').val();
        occupationKey = $('.modal-occupation').val();
    }else{
        annIncome = $('#'+annualIncomeRangeJE).val();
        eduKey = $('#'+educationJE).val();
        occupationKey = $('#'+occupationJE).val();
    }
    
    var keyValuesToValidate = {
          "annualIncome": Number(annIncome.replaceAll(",","")),
          "education": {
            "key": String(eduKey)
          },
          "occupation": {
                "key": String(occupationKey)
          }
    };

    isValidEntry = filterValidObjects(productEligibilityRelJson.items, keyValuesToValidate);

    return isValidEntry;

}

/**
 * Show/Hide Premium Break Benefit
 */
showHidePremiumBreakBenefit = function() {
    var curPayFor = $(policyForJEl).val();
    if(Number(curPayFor) < 10){
        $('.prem-bb').addClass("d-none");
        $('.prem-bb').parent().addClass("d-none");
    }else{
        $('.prem-bb').removeClass("d-none");
        $('.prem-bb').parent().removeClass("d-none");
    }
}

showHideWOPCard = function() {
	var age = calculateAge($(dateOfBirthJEl).val());
	var PPT = Number($(policyForJEl).val());	
	var riderId = "50007";
	if(PPT+age>70 || age>60){
		$('#' + namespace + 'rider_' + riderId).hide();
	}else{
		$('#' + namespace + 'rider_' + riderId).show();
	}
}

showHideBHBCard = function(biResponseJson) {
	var age = calculateAge($(dateOfBirthJEl).val());
    var spouseDateOfBirth = $('#'+spouseDobJE).val();
    var spouseName = $('#'+spouseNameJE).val();
    var spouseAge = calculateAge(spouseDateOfBirth);
	var ageGap = Math.abs(age - spouseAge);

	if(spouseDateOfBirth && spouseName){
		if(ageGap <= 10){
    		isBHBAvailable = true;
     		$('#' + namespace + 'rider_BHB').removeClass("d-none");         		
		}
	}else{
		isBHBAvailable = false;
 		$('#' + namespace + 'rider_BHB').addClass("d-none");
 		addRemoveRider(namespace+"selectedRider_BHB",false);
	}
	
	if(biResponseJson && biResponseJson.BIJson != null){
        var bhbBaseAmount = 0;
        var threshold = 0;
        var bhbBaseAmount = 0;
        var paymentFreq = $(paymentOptionJEl).val();
        if(Number(paymentFreq) === 1) {
            threshold = 15;
        }else if(Number(paymentFreq) === 2){
            threshold = 10;
        }else if(Number(paymentFreq) === 3){
            threshold = 6;
        }else{
            threshold = 3;
        }

        if(document.getElementById(namespace+'selectedRider_BHB')){
        	document.getElementById(namespace+"riderPriceAmount_BHB").innerHTML = LANG_MESSAGES["rupee-sign"].concat(Math.round(biResponseJson.BIJson[0].BHB_MODAL_PREM_TAX).toLocaleString("en-IN"));
        	bhbBaseAmount = document.getElementById(namespace+'selectedRider_BHB').getAttribute("data-rider-price");
        	if(biResponseJson.BIJson){
        		bhbBaseAmount = biResponseJson.BIJson[0].BHB_PREM;
        		if(Number(Math.round(bhbBaseAmount)) > Number(threshold)){
        			isBHBAvailable = true;
        			$('#' + namespace + 'rider_BHB').removeClass("d-none");
        		}else {
        			isBHBAvailable = false;
        			$('#' + namespace + 'rider_BHB').addClass("d-none");
        			addRemoveRider(namespace+"selectedRider_BHB",false);
        		}
        	}
        }
	}
}

showHideCHILDCard = function() {
	var childDateOfBirth = $('#'+childDobJE).val();
	var LAage = calculateAge($(dateOfBirthJEl).val());
	var childAge = calculateAge(childDateOfBirth);
	var pt = $(policyTermJEl).val();

	if(Number(LAage) <= 50) {
	    if(childDateOfBirth){
	        if(pt >= 25-Number(childAge)+10) {
                isCHILDAvailable = true;
            }else {
                isCHILDAvailable = false;
            }
        }else{
            isCHILDAvailable = false;
        }
	}else{
	    isCHILDAvailable = false;
	}
	if(isCHILDAvailable){
		$('#' + namespace + 'rider_CHILD').removeClass("d-none");
	}else{
		$('#' + namespace + 'rider_CHILD').addClass("d-none");
		addRemoveRider(namespace+"selectedRider_CHILD",false);
	}
}


/**
 * Updates the Minimum Life Cover Amount
 */
updateMinimumLifeCoverAmount = function() {
	
	var curLifeCoverAmt = $(lifeCoverJEl).val();

	if(Number(curLifeCoverAmt) == Number(10000000)){
		$(lifeCoverJEl).val(10001000);
	}
	
	if(Number(curLifeCoverAmt) < Number(5000000)){
		$(lifeCoverJEl).val(5000000);
	}

	populateLifeCoverInWords();
	formateProductAmounts();
	updateLifeCoverAmountLocaleIN();
	
	
}

updateLifeCoverAmountLocaleIN  = function() {
	var curLifeCoverAmount = $(lifeCoverJEl).val().replaceAll(',','');
	var localeINLifeCoverAmount = curLifeCoverAmount.toLocaleString("en-IN");
	$(lifeCoverJEl).val(localeINLifeCoverAmount);
}

createCookieForZPEligibility = function(cookieName, cookieValue, expirationDays) {
	  var date = new Date();
	  date.setTime(date.getTime() + (expirationDays * 24 * 60 * 60 * 1000));
	  var expires = "expires=" + date.toUTCString();
	  document.cookie = cookieName + "=" + cookieValue + ";" + expires + ";path=/";
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
 * Formate available product amounts
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
 * Polulate LifeCover Amount in words
 */
populateLifeCoverInWords = function() {
	var curLifeCover = document.getElementById(namespace+"lifeCover").value.replaceAll(",","");
	var amountInWords = getAmountInWords(curLifeCover);
	$("#amt-in-word").html(rupeeSignLabel + "&nbsp;" + amountInWords);
};

/**
 * Get Amount in Words
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
 * Verify the eligibility of customer for ZP
 */
function validateKeyValues(json, keyValues) {
  var isValid = true;
  for (var key in keyValues) {
    if(json.hasOwnProperty(key)) {
    if (key === "annualIncome") {
        if (typeof json[key] !== "number" || json[key] > keyValues[key]) {
          isValid = false;
          break;
        }
      } else if (typeof json[key] === "object") {
        if (!validateKeyValues(json[key], keyValues[key])) {
          isValid = false;
          break;
        }
      } else if (json[key] !== keyValues[key]) {
        isValid = false;
        break;
      }
    } else {
      isValid = false;
      break;
    }
  }
  return isValid;
}

function filterValidObjects(jsonObject, keyValues) {
  var filteredArray = jsonObject.filter(function(obj) {
    return validateKeyValues(obj, keyValues);
  });
  if (filteredArray.length > 0) {
    return true;
  } else {
    return false;
  }
}


/**
 * Load 'PolicyFor' Based on PolicyTerm Selection
 */
onPolicyTermChange = async function(selectedPolicyTerm){
	setPayForDropdown();
	updatePayForDropdown(selectedPolicyTerm);
};

/**
 * Update 'Policy Term' options based on Age Selection
 */
updatePolicyTermDropdown = function() {

		var currentPolicyTerm = $(policyTermJEl).val();

		$(policyTermJEl).html("");

		var customerAge = calculateAge($(dateOfBirthJEl).val());

		var selectedValue = 0;
		if(Number(customerAge) === 65){
		    selectedValue = 85 - Number(customerAge);
		}else if(Number(customerAge) === 60){
		    selectedValue = 80 - Number(customerAge);
		}else{
		    selectedValue = 20;
		}

		$(policyTermJEl).append('<option value="' + (100 - customerAge) + '">Upto Age 100</option>');
		$(policyTermJEl).append('<option value="' + (85 - customerAge) + '" ' + (Number(85 - customerAge) === selectedValue ? "selected" : "") + '>Upto Age 85</option>');
		$(policyTermJEl).append('<option value="' + (80 - customerAge) + '" ' + (Number(80 - customerAge) === selectedValue ? "selected" : "") + '>Upto Age 80</option>');

		// Based on type of selected Plan Option
		if($(planOptionJEl).val()==130){
			/*Applicable for Life Cover*/
			if((customerAge >= 55 && customerAge <= 65)){
				ptStartsWith = 10;
			}else{
				ptStartsWith = 5; 
			}
		}else{
			/*Applicable for Return of Premium*/
			ptStartsWith = 10;
		}

		// PT options will be updated based on the LI_Age
		ptEndsWith = (99-customerAge); 
		for(;ptEndsWith>=ptStartsWith;ptEndsWith--){
			if(ptEndsWith == (85-customerAge) || ptEndsWith == (80-customerAge)) continue;
			$(policyTermJEl).append('<option value="' + ptEndsWith + '" ' + (ptEndsWith === selectedValue ? "selected" : "") + '>' + ptEndsWith + '</option>');
		}
}

setPayForDropdown = function() {
	
	$(policyForJEl).html("");

    payForPicklistData = payForPicklistData.replaceAll('=',':');
    const trimmedStr = payForPicklistData.slice(1, -1);
    const keyValuePairs = trimmedStr.split(', ');

	
	var selectedPolicyTerm = $(policyTermJEl).val();
	var customerAge = calculateAge($(dateOfBirthJEl).val());
	var spouseDateOfBirth = $('#'+spouseDobJE).val();
	var spouseAge = spouseDateOfBirth ? calculateAge(spouseDateOfBirth) : 0;
    var planOptionVal = $(planOptionJEl).val();
	var selectedPTandAge = Number(selectedPolicyTerm) + Number(customerAge);
	console.log("selectedPTandAge::" + selectedPTandAge);

if (Number(selectedPolicyTerm) !== 10 && Number(selectedPolicyTerm) !== 11) {
	keyValuePairs.forEach(pair => {
		  const [key, value] = pair.split(':');
		  if (Number(key) <= Number(selectedPolicyTerm-5)) {
			 if(planOptionVal==="130"){
			     if (Number(customerAge) >= 18 && Number(customerAge) <= 55 && Number(key) === 20) {
                       $(policyForJEl).append('<option value="' + key + '" selected>' + value + '</option>');
                  } else if (Number(customerAge) >= 56 && Number(customerAge) <= 60 && Number(key) === 5) {
                       $(policyForJEl).append('<option value="' + key + '" selected>' + value + '</option>');
                  } else if (Number(customerAge) >= 61 && Number(customerAge) <= 65 && Number(key) === 10) {
                	  $(policyForJEl).append('<option value="' + key + '" selected>' + value + '</option>');
                  } else {
                       $(policyForJEl).append('<option value="' + key + '">' + value + '</option>');
                  }
			 }
			 if(planOptionVal==="131"){
			  if (Number(customerAge) >= 18 && Number(customerAge) <= 50 && Number(key) === 20) {
                    $(policyForJEl).append('<option value="' + key + '" selected>' + value + '</option>');
              } else if (Number(customerAge) >= 51 && Number(customerAge) <= 60 && Number(key) === 5) {
                    $(policyForJEl).append('<option value="' + key + '" selected>' + value + '</option>');
              } else {
                    $(policyForJEl).append('<option value="' + key + '">' + value + '</option>');
              }
			 }
		 }
	  });
	}else{
         $(policyForJEl).append('<option value="5">5</option>');
         $(policyForJEl).append('<option value="7">7</option>');
  }

  	const notApplicableAge = [40, 45, 50];
  	if(planOptionVal==="130"){
          if(Number(customerAge)<50 && Number(spouseAge)<50 && Number(selectedPTandAge)>=65){
      		if(!notApplicableAge.includes(Number(customerAge))){
      			$(policyForJEl).append('<option value="60-@LI_ENTRY_AGE">To Age 60</option>');
              }
  	   }
  	}
  	if(planOptionVal==="131"){
        if(Number(customerAge)<50 && Number(spouseAge)<50 && Number(selectedPTandAge)>=65){
    		if(!notApplicableAge.includes(Number(customerAge))){
    			$(policyForJEl).append('<option value="60-@LI_ENTRY_AGE">To Age 60</option>');
            }
	   }
	}
  	removeInvalidPayForOptions();
  	removeDuplicatePayForOptions();
}

/**
 * Update 'PayFor' options based on PolicyTerm Selection
 */
updatePayForDropdown = function(selectedPolicyTerm) {
	
		var currentPolicyFor= $(policyForJEl).val();
		$(policyForJEl+' option.regular-pay-option').remove();

		selectedPolicyTerm = selectedPolicyTerm?selectedPolicyTerm:20;

		var currentPolicyFor = $(policyForJEl).val();
		
		payForPicklistData = payForPicklistData.replaceAll('=',':');
		const trimmedStr = payForPicklistData.slice(1, -1);
		const keyValuePairs = trimmedStr.split(', ');

		var planOptionVal = $(planOptionJEl).val();
		var customerAge = calculateAge($(dateOfBirthJEl).val());
		const spouseDateOfBirth = $('#'+spouseDobJE).val();
		var spouseAge = spouseDateOfBirth ? calculateAge(spouseDateOfBirth) : 0;

		//const maxKeyValue = Math.max(...keyValuePairs.map(pair => Number(pair.split(':')[0].trim())));

        /*Regular Pay*/
		if (planOptionVal === "130" || (planOptionVal === "131" && (customerAge <= Number(50) || (Number(spouseAge)!=0 && Number(spouseAge) <= Number(50))))) {
			if(Number(customerAge) <= Number(55) && Number(spouseAge) <= Number(55)){
				if(Number(selectedPolicyTerm) === Number(100-customerAge)){
					$(policyForJEl).append('<option value="'+(100-customerAge)+'" class="regular-pay-option" selected>Upto Age 100</option>');
				}else if(Number(selectedPolicyTerm) === Number(85-customerAge)){
					$(policyForJEl).append('<option value="'+(85-customerAge)+'"  class="regular-pay-option" selected>Upto Age 85</option>');
				}else if(Number(selectedPolicyTerm) === Number(80-customerAge)){
					$(policyForJEl).append('<option value="'+(80-customerAge)+'"  class="regular-pay-option" selected>Upto Age 80</option>');
				}else {
                        if(!document.getElementById(policyForEl).querySelector('option[value="'+selectedPolicyTerm+'"]')) {
                            $(policyForJEl).append('<option value="' + selectedPolicyTerm + '" class="regular-pay-option" selected>' + selectedPolicyTerm + '</option>');
					}
				}
			}
		}
		
		removeInvalidPayForOptions();
		removeDuplicatePayForOptions();
}

/*
 * Remove invalid Pay For options w.r.t Policy Term
 */
removeInvalidPayForOptions = function(){
	$(policyForJEl+" option").each(function(){
	    var curVal = $(this).val();
	    if(curVal.includes("@LI_ENTRY_AGE")){
	        curVal = Number(curVal.replace("-@LI_ENTRY_AGE","")) - calculateAge($(dateOfBirthJEl).val());
	    }
	    if(curVal>Number($(policyTermJEl).val())){
	        $(this).remove();
	    }
	});
}

/*
 * Remove duplicate options
 */
removeDuplicatePayForOptions = function(){
	$(policyForJEl+" option").each(function(){
		var curVal = $(this).val();
	    if(!curVal.includes("@LI_ENTRY_AGE")){
	    	return;
	    }

	    curVal = Number(curVal.replace("-@LI_ENTRY_AGE","")) - calculateAge($(dateOfBirthJEl).val());
       
        var duplicateOptions = $(policyForJEl+" option[value='"+curVal+"']");
	    if(duplicateOptions && duplicateOptions.length>0){
	        $(this).remove();
	    }
	});
}

/*
 * Load Default Pay For values
 */
selectDefaultPayForOption = function(){
	var curPlanOption = $(planOptionJEl).val();
	var curPolicyTerm = $(policyTermJEl).val();
	
	var defaultPayForValue = curPolicyTerm;
	
	var customerAge = calculateAge($(dateOfBirthJEl).val());
	if(curPlanOption == "131"){
		if(Number(customerAge) > 50 ){
		    selectedValue = 5;
		} else{
		    selectedValue = 20;
		}
	} else{
		if(Number(customerAge) > 60 ){
		    selectedValue = 10;
		}else if(Number(customerAge) > 55){
		    selectedValue = 5;
		}else{
		    selectedValue = 20;
		}
	}
	
	$(policyForJEl).val(selectedValue)
}

/**
 * Remove Premium Breakup Benerefit for ROP
 */
premiumBreakBenefitRelField = function() {
	var planOptionVal = $(planOptionJEl).val();
	console.log("planOptionVal::::premiumBreakBenefitRelField" + planOptionVal);
	
	$("#"+namespace+"premiumBreakBenefitDiv").parent().show();
	if(planOptionVal == "131"){
		$("#"+namespace+"premiumBreakBenefitDiv").parent().hide();
		$("#"+namespace+"rider_BHB").hide();
		$("#"+namespace+"rider_CHILD").hide();
	}
}

/**
 * Update 'Income Percentage' options
 */
updateIncomePercentageDropdown = function() {
		var key=31;
		$(incomePercentageJEl).html("");
		for(i=1;i<=99;i++){
			$(incomePercentageJEl).append('<option value="' + key + '" ' + '>' + i + '%</option>');
			key++;
		}
}

/**
 * Update Customize From Feild: Update Plan Option dropdown field
 */
updatePlanOptionDropdown = function(){
	
	var customerAge = calculateAge($(dateOfBirthJEl).val());
	
	const spouseDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.spouseDateOfBirth;
	var spouseAge = spouseDateOfBirth ? calculateAge(spouseDateOfBirth):0;

    if(Number(customerAge) > Number(60)){
        $(planOptionJEl+" option[value='131']").remove();
    }

     if(Number(spouseAge) > Number(60)){
        $(planOptionJEl+" option[value='131']").remove();
     }
}

/**
 * Resource Call to Generate BI API for Zindagi Protect Plus
 */
zindagiProtectGenerateBI = async function(biRequestBody, ignoreDefaultRiderError) {
	showLoader();
	var hasValidationErrors = false;
	var biRequestJson = new FormData();
	
	var age = calculateAge($(dateOfBirthJEl).val());
	if(age > 60){
		biRequestBody.productDetails.product.riderDetails.CriticalIllnessRider = "";
	}
	console.log("zindagiProtectGenerateBI ++++++++++++ "+JSON.stringify(biRequestBody));
	
	biRequestJson.append(namespace + "biRequestJsonString", JSON.stringify(biRequestBody));

	await Liferay.Util.fetch(customizePageDetails.generateBiUrl, {
		body: biRequestJson,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
		var biResponseJson = response.data;
		generateBIResponseObj = response.data;

		console.debug(" ::::: DEBUG: biResponseJson :::::");
		console.debug(biResponseJson);
		
		hasValidationErrors = handleInputValidationStatus(biResponseJson, ignoreDefaultRiderError);

		/*
		 * If no validation error present, update Plan Premium Details and Calculate Total Premium Amount
		 */
		if(hasValidationErrors) {
			hideLoader();
			return hasValidationErrors;
		}

		/*
	     * Show Hide BHB Benefits
	     */
		showHideBHBCard(biResponseJson);

		/*
		 * Update Riders data
		 */
		renderRiderPremiumAmount(biResponseJson);
		renderBenefitsPremiumAmount(biResponseJson);

		updateSelectedRiderPrice();
		updatePlanPremiumDetails(biResponseJson);
		updateRidersListHiddenInput();


		if(biResponseJson){
			document.getElementById(namespace+"illustrationURL").value=biResponseJson.Url;
			$(quotationIdJEl).val(biResponseJson.QuotationId);
			$(anualPrem2JE1).val(biResponseJson.BIJson[0].ANN_PREM_2);
		}
		
		hideLoader();

	}).catch(function(exception) {
		console.error("Error in Generate BI API");
		console.error(exception);
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
 * Generate-BI Request Body: Selected Rider Request Body
 */
generateSelectedRidersRequestBody = function(requestedRidersList, loadDefaultValues){

	var riderDetailsRequestBody={
	     "ADBRider": "",
	     "ATPDRider": "",
	     "WaiverOfPremiumRider": "",
	     "CriticalIllnessRider": ""
	};
	
	document.getElementsByName(namespace+"selectedRiders").forEach(availableRider=>{

		if(!availableRider.dataset){
			return;
		}

		var age = calculateAge($(dateOfBirthJEl).val());
		var PPT = Number($(policyForJEl).val());

		/**
		 * Skip the Benefits from the Object
		 */
		if(availableRider.value==='CHILD' || availableRider.value==='BHB'){
			return;
		}
		
		if(PPT+age>70 && availableRider.value==='50007'){
			return;
		}

		if(availableRider.dataset.isAvailable == "false" || !(loadDefaultValues || requestedRidersList.has(availableRider.value))){
	        riderDetailsRequestBody[availableRider.dataset.riderKey]="";
	        return;
	    }
	    
	    if((document.getElementsByName(namespace+"selectedRiders").value == "50003" || document.getElementsByName(namespace+"selectedRiders").value == "50007") && age > 60){
	    	 riderDetailsRequestBody[availableRider.dataset.riderKey]="";
	    	 return;
	    }

	     if(document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value)){
            riderDetailsRequestBody[availableRider.dataset.riderKey]=document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value).value;
         }else{
            riderDetailsRequestBody[availableRider.dataset.riderKey]="1";
         }

	});

	return riderDetailsRequestBody;
}

/**
 * Generate-BI Request Body: Policy Options Request Body
 */
generatePolicyOptionsRequestBody = function(loadDefaultRiderValues){

	const spouseDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.spouseDateOfBirth;
	const spouseName = familyDetailsResponseJson && familyDetailsResponseJson.spouseName;
	const childDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.childDateOfBirth;
	var maritalStatus = "";

	if(!spouseName && !spouseDateOfBirth){
		maritalStatus = "1";
	}else{
		maritalStatus = "2";
	}

	var policyOptionsRequestBody={};
	
	var selectedIncomeType = $(incomeTypeJEl).val();

	policyOptionsRequestBody["PlanOption"] = document.getElementById(namespace+"planOption")?document.getElementById(namespace+"planOption").value:"";
    policyOptionsRequestBody["MaritalStatus"] = maritalStatus;
    policyOptionsRequestBody["PremiumBreakBenefit"] = $(premiumBreakBenefitJEl).val();
    policyOptionsRequestBody["DeathBenefitPayoutOption"] = selectedIncomeType;
    
    if(selectedIncomeType == "24"){
    	policyOptionsRequestBody["PayoutOptions"] = "29";
    policyOptionsRequestBody["PayoutPeriodMonths"] = "";
    } else if(selectedIncomeType == "25"){
    	policyOptionsRequestBody["PayoutOptions"] = "30";
    	policyOptionsRequestBody["PayoutPeriodMonths"] = $(incomePeriodJEl).val();
    } else if(selectedIncomeType == "26"){
    	policyOptionsRequestBody["PayoutOptions"] = $(incomePercentageJEl).val()?$(incomePercentageJEl).val():"30"; 
    	policyOptionsRequestBody["PayoutPeriodMonths"] = $(incomePeriodJEl).val();
    }

    /*
	 * Proposer Benefits Attributes Data
	 */
    var age = calculateAge($(dateOfBirthJEl).val());
    var spouseAge = calculateAge(spouseDateOfBirth);
	var ageGap = Math.abs(age - spouseAge);
	
	/*
	 * Condition 1: If the user is not married or the age gap is more than 10 years, set it to 4 (BHB Not Opted).
	 * Condition 2: If setDefaultBHB set true or BHB is selected, set it to 3 (BHB Opted).
	 * Condition 3: Otherwise, set it to 4 (BHB not opted).
	 */
    if(maritalStatus === "1" || ageGap > 10){
        policyOptionsRequestBody["BetterHalfBenefit"]="4";
    } else if(setDefaultBHB || $("#"+namespace+"selectedRider_BHB").prop("checked")){
        policyOptionsRequestBody["BetterHalfBenefit"]="3";
    } else {
    	policyOptionsRequestBody["BetterHalfBenefit"]="4";
    }

    if(isCHILDAvailable){
    	policyOptionsRequestBody["ChildsFutureProtectBenefit"]="9";
    	policyOptionsRequestBody["ChildsFutureProtectBenefitSAPercentOption"]=$(childBenefitSelectJEl).val()?$(childBenefitSelectJEl).val():"12";
    }else{
    	policyOptionsRequestBody["ChildsFutureProtectBenefit"]="10";
    	policyOptionsRequestBody["ChildsFutureProtectBenefitSAPercentOption"]="";
    }

    var curPlanOption = $(planOptionJEl).val();
	console.log("curPlanOption::" + curPlanOption);
    if(curPlanOption == "131"){
		console.log("isnide curPlanOption if::::::");
    	policyOptionsRequestBody["BetterHalfBenefit"]="4";
		policyOptionsRequestBody["ChildsFutureProtectBenefit"]="10";
    }
    
	return policyOptionsRequestBody;
}

/**
 * Generate-BI Request Body: Product Request Body
 */
generateProductRequestBody = function(requestedRidersList, loadDefaultRiderValues){

    /*
    * Setting Product Details
    */
	var productRequestBody = {};
	var paymentOptionVal = document.getElementById(namespace+"paymentOption")?document.getElementById(namespace+"paymentOption").value:"1";
	var lifeCover = Number(document.getElementById(namespace+"lifeCover")?document.getElementById(namespace+"lifeCover").value.replaceAll(",",""):"0");
	var payFor = document.getElementById(namespace+"payFor")?document.getElementById(namespace+"payFor").value:"5";
	var policyTerm = document.getElementById(namespace+"custPolicyTerm")?document.getElementById(namespace+"custPolicyTerm").value:"20";
	var annualIncome = $('#'+namespace+'Income').val();
	var annualPremium = lifeCover;

	switch (paymentOptionVal) {
	case "2":
		annualPremium = 2 * lifeCover / 1.024;
		break;
	case "3":
		annualPremium = 4 * lifeCover / 1.04;
		break;
	case "4":
		annualPremium = 12 * lifeCover / 1.056;
	}
	
	var annIncome = "";
	if($('.modal-Income').val() ){
		annIncome = $('.modal-Income').val();
	}else if($('.save-income').val()){
		annIncome = $('.save-income').val();
	}else{
		annIncome = basicDetailsMap.annualIncome.includes("-")?"10000000":basicDetailsMap.annualIncome;
	}
	
	annIncome = annIncome.replaceAll(",","");

	productRequestBody["PR_ID"] = productMetaData.productCode;
	productRequestBody["PR_PT"] = $('#'+namespace+"custPolicyTerm")?String($('#'+namespace+"custPolicyTerm").val()):"20",
	productRequestBody["PR_PPT"] = $(policyForJEl).val()?$(policyForJEl).val():5;
	productRequestBody["PR_ANNPREM"] = "0";
	productRequestBody["PR_SA"] = String(lifeCover);
	productRequestBody["INPUT_MODE"] = paymentOptionVal;	
	productRequestBody["ZP_ANNINCOME"] = annIncome;
	
	if(isCustomerNri == 'true'){
		productRequestBody["ZP_NATIONALITY"]="2";
		productRequestBody["NriDeclaration"]="Y";

	}else{
		productRequestBody["ZP_NATIONALITY"]="1";
		productRequestBody["NriDeclaration"]="N";
	}

	var eduQualification = "";
	if(basicDetailsMap.educationQualification!=""){
		eduQualification = basicDetailsMap.educationQualification;
	}
	if($('#'+educationJE).val()!=""){
		eduQualification = $('#'+educationJE).val();
	}
	if($('.modal-educationQualification').val()!=""){
		eduQualification = $('.modal-educationQualification').val();
	}
	
	var occupationVal = "";
	if(basicDetailsMap.occupation!=""){
		occupationVal = basicDetailsMap.occupation;
	}
	if($('#'+occupationJE).val()!=""){
		occupationVal = $('#'+occupationJE).val();
	}
	if($('.modal-occupation').val()!=""){
		occupationVal = $('.modal-occupation').val();
	}
	
	productRequestBody["ZP_OCCUPATION"] = occupationVal?occupationVal.toUpperCase():"SA";
	productRequestBody["ZP_EDUCATION"] = eduQualification?eduQualification:"4";

	 /*
	 * Setting Default Attributes Data
	 */
	productRequestBody["ZP_PREFERREDLIST"]="1";
	productRequestBody["Underwriting"]="1";
	productRequestBody["emr_rate"]="1.00";
	productRequestBody["flat_rate"]="0";
	productRequestBody["CashFlow"]="N";
	productRequestBody["category"]="1";
	productRequestBody["isPdfByte"]="Y";
	productRequestBody["PR_CHANNEL"]="1";
	productRequestBody["PR_MI"]="0";
	productRequestBody["PR_ModalPrem"]="0";
	productRequestBody["PR_SAMF"]="0";
	productRequestBody["REQUESTSOURCE"]="4";
	productRequestBody["comboDetails"]={};

    /*
	 * Setting Policy Options
	 */
	productRequestBody["policyOptions"] = generatePolicyOptionsRequestBody(loadDefaultRiderValues);

    /*
	 * Setting Rider details
	 */
	productRequestBody["riderDetails"] = generateSelectedRidersRequestBody(requestedRidersList, loadDefaultRiderValues);

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
	
	console.debug("DEBUG[js]:: Basic Details:");
	console.debug(basicDetailsMap);
	
	console.debug("##DEBUG[js]:: Family Details ##");
	console.debug(familyDetailsResponseJson);

	var dateOfBirth = moment(basicDetailsMap.dateOfBirth, "DD/MM/YYYY");
	dateOfBirth = moment(dateOfBirth).format('DD MMM YYYY');
	var age = calculateAge(basicDetailsMap.dateOfBirth);
	var genderShortName = (basicDetailsMap.gender == "Male") ? "M" : "F";
	var sameProposer = "True";
	
	const spouseDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.spouseDateOfBirth;
	const spouseName = familyDetailsResponseJson && familyDetailsResponseJson.spouseName;
	const childDateOfBirth = familyDetailsResponseJson && familyDetailsResponseJson.childDateOfBirth;

	var maritalStatus = "";
	if(!spouseName && !spouseDateOfBirth){
		maritalStatus = "1";
	}else{
		maritalStatus = "2";
	}

	var customerDetailsRequestBody={};

	customerDetailsRequestBody["agentDetails"]={
			"AGENT_ID": "TW9001",
			"AgentLocation": "",
			"AgentName": ""
	};

	customerDetailsRequestBody["liDetails"] = {
			"LI_NAME": basicDetailsMap.fullName,
			"LI_ENTRY_AGE": String(age),
			"LI_DOB": dateOfBirth,
			"LI_GENDER": genderShortName,
			"LI_STATE": 0,
			"LI_MOBILENO": basicDetailsMap.mobileNumber,
			"LI_EMAILID": basicDetailsMap.email,
			"LI_SMOKE": basicDetailsMap.smoker
	};
	
	customerDetailsRequestBody["proposerDetails"] = {
		"SameProposer": sameProposer,
		"PROPOSER_NAME": basicDetailsMap.fullName,
		"PROPOSER_AGE": basicDetailsMap.age,
		"PROPOSER_DOB": dateOfBirth,
		"PROPOSER_GENDER": genderShortName
	};

	switch (maritalStatus) {
	case "1":
		customerDetailsRequestBody["spouseDetails"] = {
		    "SpouseEntryAge": "",
		    "SpouseGender": genderShortName==="M"?"8":"7"
		};
		break;
	case "2":
		customerDetailsRequestBody["spouseDetails"] = {
			"SpouseName": spouseName ? spouseName : "",
		    "SpouseGender": genderShortName==="M"?"8":"7",
			"SpouseEntryAge": spouseDateOfBirth ? String(calculateAge(spouseDateOfBirth)) : ""
		};
		break;
	}
	
	customerDetailsRequestBody["childDetails"] = {
            "ChildEntryAge": childDateOfBirth ? calculateAge(childDateOfBirth) : ""
    };

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
handleInputValidationStatus = function(biResponseData, ignoreDefaultRiderError) {
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
		 * handle the errors as per the product or rider
		 */
		if(inputValidation.ErrorMessage.length && inputValidation.ProductId == $(productCodeJEl).val()){
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

	var annIncome = "";
	if($('.modal-Income').val()){
		annIncome = $('.modal-Income').val();
	}else if($('.save-income').val()){
		annIncome = $('.save-income').val();
	}else{
		annIncome = basicDetailsMap.annualIncome?basicDetailsMap.annualIncome:"10000000";
	}
	annIncome = annIncome.replaceAll(",","");
	
	
	
	var eduQualification = "";
	if(basicDetailsMap.educationQualification!=""){
		eduQualification = basicDetailsMap.educationQualification;
	}
	if($('#'+educationJE).val()!=""){
		eduQualification = $('#'+educationJE).val();
	}
	if($('.modal-educationQualification').val()!=""){
		eduQualification = $('.modal-educationQualification').val();
	}
	
	var occupationVal = "";
	if(basicDetailsMap.occupation!=""){
		occupationVal = basicDetailsMap.occupation;
	}
	if($('#'+occupationJE).val()!=""){
		occupationVal = $('#'+occupationJE).val();
	}
	if($('.modal-occupation').val()!=""){
		occupationVal = $('.modal-occupation').val();
	}
	
	var selectedPayForValue = $("#"+namespace+"payFor").val();
	if(selectedPayForValue.includes("@LI_ENTRY_AGE")){
    	selectedPayForValue = Number(selectedPayForValue.replace("-@LI_ENTRY_AGE","")) - calculateAge($(dateOfBirthJEl).val());
    }

	var updateLMSLeadData = new FormData();
	
    updateLMSLeadData.append(namespace + "customerEnquiryId", $("#" + portletNamespace + "customerEnquiryId").val());
	
	updateLMSLeadData.append(genderEl, $("input[type=radio][name="+genderEl+"]:checked").val());
	updateLMSLeadData.append(fullNameEl, $(fullNameJEl).val());
	updateLMSLeadData.append(dateOfBirthEl, $(dateOfBirthJEl).val());
	updateLMSLeadData.append(emailEl, $(emailJEl).val());
	updateLMSLeadData.append(mobileNumberEl, $(mobileNumberJEl).val());
	updateLMSLeadData.append(namespace + "Income", annIncome);
	updateLMSLeadData.append(namespace + "educationQualification", eduQualification?eduQualification:"SA");
	updateLMSLeadData.append(namespace + "occupation", occupationVal?occupationVal:"4");
	updateLMSLeadData.append(namespace + "SumAssured", $(lifeCoverJEl).val().replaceAll(",",""));
	updateLMSLeadData.append(namespace + "Product_Code", $(productCodeJEl).val());
	updateLMSLeadData.append(namespace + "PolicyTerm", $(policyTermJEl).val());
	updateLMSLeadData.append(namespace + "ProductName", $(productNameJEl).val());
	updateLMSLeadData.append(namespace + "PremiumPaymentTerm", selectedPayForValue);
	updateLMSLeadData.append(namespace + "PremiumPaymentFrequency", paymentFrequencyDisplayName);
	updateLMSLeadData.append(namespace + "TotalPremiumAmount", $("#"+namespace+"premiumAmount").val());
	updateLMSLeadData.append(namespace + "BasePremiumAmount", $("#"+namespace+"premiumAmount").val());

	var selectedIncomeType = $(incomeTypeJEl).val();
	updateLMSLeadData.append(namespace + "PayoutOptions", selectedIncomeType);
	
	if(selectedIncomeType == "24"){
		updateLMSLeadData.append(namespace + "PayoutPeriodYears", "");
    } else {
    	updateLMSLeadData.append(namespace + "PayoutPeriodYears", $(incomePeriodJEl).val());
    }
	
	/*
	 * Quote Details
	 */
	updateLMSLeadData.append(namespace + "BI_PDF_PATH", $("#"+namespace+"illustrationURL").val());
	updateLMSLeadData.append(namespace + "BIQuotationNumber", $(quotationIdJEl).val());
	updateLMSLeadData.append(namespace + "QuoteStage", stage);
	
	/*
	 *Spouse Details  
	 */
	const spouseDateOfBirth = (familyDetailsResponseJson && familyDetailsResponseJson.spouseDateOfBirth) ? familyDetailsResponseJson.spouseDateOfBirth : "";
	const spouseName = (familyDetailsResponseJson && familyDetailsResponseJson.spouseName) ? familyDetailsResponseJson.spouseName : "";
	const childDateOfBirth = (familyDetailsResponseJson && familyDetailsResponseJson.childDateOfBirth) ? familyDetailsResponseJson.childDateOfBirth : "";
	updateLMSLeadData.append(namespace + "Married", $("input[name='"+portletNamespace+"MaritalStatusId']:checked").val());
	updateLMSLeadData.append(namespace + "SpouseDob", spouseDateOfBirth);
	updateLMSLeadData.append(namespace + "SpouseName", spouseName);
	updateLMSLeadData.append(namespace + "ChildDob", childDateOfBirth);
	
	updateLMSLeadData.append(namespace + "ChildName", "no");
	updateLMSLeadData.append(namespace + "IS_LA_PR_Same_YN", "Y");

	// Rider Deatails START
	var additionalBenefitVal = "No";
	if($(".riderSelectionInput").length > 0) {
		$(".riderSelectionInput").each(function(){
			var isAvailable = $(this).data("isAvailable");
			var isRiderSelected = $(this).prop("checked");
			
			if(isAvailable && isRiderSelected) {
				additionalBenefitVal = "Yes";
				var riderName = $(this).data("riderName");
				var riderPrice = $(this).data("riderPrice");
				var riderBase = "12";
				var riderKey = $(this).data("riderKey");
				var riderPT = $(this).data("riderPt");
				var riderPPT = $(this).data("riderPpt");
				var riderProductCode = $(this).val();
				
				updateLMSLeadData.append(namespace + riderKey +  "_YN", "Y");
				updateLMSLeadData.append(namespace + riderKey +  "PT", riderPT);
				updateLMSLeadData.append(namespace + riderKey +  "PPT", riderPPT);
				updateLMSLeadData.append(namespace + riderKey +  "SA", riderPrice);
				updateLMSLeadData.append(namespace + riderKey +  "BASE", riderBase);
				
				if($(riderPremiumAmountPrefixJEl + riderProductCode).length > 0) {
					var selectedRiderPremiumAmount = $(riderPremiumAmountPrefixJEl + riderProductCode).val();
					updateLMSLeadData.append(namespace + riderKey +  "Amount", selectedRiderPremiumAmount ? selectedRiderPremiumAmount : 0);
				}
				
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
saveCustomerInvestmentData = async function() {
	var customizeFormData = document.getElementById(namespace+"customizeForm");
	await Liferay.Util.fetch(saveCustomerInvestmentDataURL, {
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
	var hasvalidationErrors = await zindagiProtectGenerateBI(getGenerateBIRequestBody(requestedRidersList, loadDefaultRiderValues), loadDefaultRiderValues);
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

/************************************************************Family Details Related Java Script*********************************************************************/


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

generateBIRequest = async function(selectedRidersList, loadDefaultRiderValues){
	return await zindagiProtectGenerateBI(getGenerateBIRequestBody(selectedRidersList, loadDefaultRiderValues), false);
}

/************************************************************WebEngage Integration*********************************************************************************/
callZPWebEngage = function() {
	
	var eventname = "Zindagi Protect";
    var returnurl = window.location.href;
	
    var productName = productMetaData.productName;
	console.debug("product-details::",productName);
	
	var premiumAmount = "";
	var sumAssured = "";
	var guaranteedBenefit = "";
	var planOption = "";
	var premiumPaymentTerm = "";
	var policyTerm = "";
	var paymentFrequency = "";
	var benefitFrequency = "";
	var edelweissEmployee = false;
	var fundManagement = "";
	var adbRider = "";
	var atpdRider = "";
	var ciRider = "";
	var wopRider = false;
	var bhb = "false";
	var cfpb = "";
	
	premiumAmount = $(lifeCoverJEl).val();
	premiumAmount = premiumAmount.replaceAll(',','');
	
	var payOption = $(paymentOptionJEl).find(":selected").text();
	if(payOption==='Annual'){
		sumAssured = premiumAmount;
	}else if(payOption==='Semi-Annual'){
		sumAssured = premiumAmount*2;
	}else if(payOption==='Quarterly'){
		sumAssured = premiumAmount*4;
	}else if(payOption==='Monthly'){
		sumAssured = premiumAmount*12;
	}
	
	planOption = $(policyOptionJEl).find(":selected").text();
	premiumPaymentTerm = $(policyForJEl).find(":selected").text();
	paymentFrequency =  $(paymentOptionJEl).find(":selected").text();
	policyTerm = $(policyTermJEl).find(":selected").text();
	
	prodRidersList.forEach(prodRidersRef => {
		var refKey = prodRidersRef["key"];
		var riderId = prodRidersRef["riderId"];
		if(refKey==='CriticalIllnessRider'){
			if($(selectedRiderJEl+riderId).is(":checked")===true){
				ciRider = $(riderPremiumAmountJEl+riderId).find(":selected").val();
			}
			if(ciRider===undefined) {ciRider='';}
		}else if(refKey==='ADBRider'){
			if($(selectedRiderJEl+riderId).is(":checked")===true){
				adbRider = $(riderPremiumAmountJEl+riderId).find(":selected").val();
			}
			if(adbRider===undefined) {adbRider='';}
		}else if(refKey==='WaiverOfPremiumRider'){
			if($(selectedRiderJEl+riderId).is(":checked")===true){
				wopRider = $(selectedRiderJEl+riderId).prop('checked')==true?'True':'False';
			}else{
				wopRider = '';
			}
		}else if(refKey==='ATPDRider'){
			if($(selectedRiderJEl+riderId).is(":checked")===true){
				atpdRider = $(riderPremiumAmountJEl+riderId).find(":selected").val();
			}
			if(atpdRider===undefined) {atpdRider='';}
		}
	});
	
	 if($("#"+namespace+"selectedRider_BHB").prop("checked")){
		 bhb = "true";
	 }
	 
	 if($("#"+namespace+"selectedRider_CHILD").prop("checked")){
		 cfpb = $("#"+namespace+"childFutureProtectBenefit :selected").text();
	 }
	
	
    webengage.track(eventname, {
 	    "Product_Name" : "Zindagi Protect",
 	   	"Premium Amount" : Number(premiumAmount),
 	  	"Sum Assured" : Number(sumAssured),
 	  	"Guaranteed Benefit" : Number(guaranteedBenefit),
 	  	"Plan Option" : planOption.toString(),
 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
 	  	"Policy Term" : Number(policyTerm),
 	  	"Payment Frequency" : paymentFrequency.toString(),
 	  	"Edelweiss Employee" : edelweissEmployee,
 	  	"ADB Rider" : Number(adbRider),
 	  	"ATPD Rider" : Number(atpdRider),
 	  	"CI Rider" : Number(ciRider),
 	  	"WOP Rider" : wopRider,
 	  	"Better Half Benefit" : bhb,
 	  	"Child Future Protect Benefit" : cfpb,
 	    "URL": returnurl
       });	
}