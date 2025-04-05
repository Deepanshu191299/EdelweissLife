jQuery(document).ready(function() {
	var enterYourXFullNameErrorMsg = LANG_MESSAGES["enter-your-x-full-name"];
	var enterYourXDateOfBirthErrorMsg = LANG_MESSAGES["enter-your-x-date-of-birth"];
	var ageBetween0to17ErrorMsg = LANG_MESSAGES["age-between-0-to-17-years"];
	
	var sonFullNameLabel = LANG_MESSAGES["sons-full-name"];
	var sonDOBLabel = LANG_MESSAGES["sons-dob"];
	var daughterFullNameLabel =LANG_MESSAGES["daughters-full-name"];
	var daughterDOBLabel = LANG_MESSAGES["daughters-dob"];
	var grandsonFullNameLabel = LANG_MESSAGES["grandsons-full-name"];
	var grandsonDOBLabel = LANG_MESSAGES["grandsons-dob"];
	var granddaughterFullNameLabel = LANG_MESSAGES["granddaughter-full-name"];
	var granddaughterDOBLabel = LANG_MESSAGES["granddaughter-dob"];
	
	var editSummaryFormValidator = portletNamespace + "editSummaryForm";
	var editSummaryFormValidatorJEl = "#" + editSummaryFormValidator;
	
	var fullNameValidator = portletNamespace + "fullName";
	var fullNameValidatorJEl = "#" + fullNameValidator;
	var dateOfBirthValidator = portletNamespace + "dateOfBirth";
	var dateOfBirthValidatorJEl = "#" + dateOfBirthValidator;
	var mobileNumberValidator = portletNamespace + "mobileNumber";
	var mobileNumberValidatorJEl = "#" + mobileNumberValidator;
	var emailValidator = portletNamespace + "email";
	var emailValidatorJEl = "#" + emailValidator;
	var assuranceNameValidator = portletNamespace + "assuranceFullName";
	var assuranceDobValidator = portletNamespace + "assuranceDob";
	var assuranceNameValidatorJEl = "#" + assuranceNameValidator;
	var assuranceDobValidatorJEl = "#" + assuranceDobValidator;
	var basicInvestingForValidator = portletNamespace + "basicInvestingFor";
	var investForValidator = portletNamespace + "investFor";
	var gender = portletNamespace + "gender",
		investmentObjective = portletNamespace + "investmentObjective",
		assuredRelation = portletNamespace + "assuredRelation";
		investingForJEl = "#" + portletNamespace + "investingFor";
	var basicDetailsForm = portletNamespace + "editSummaryForm";
	var littleChampModal = "#" + portletNamespace + "littleChampModal";
	var isLittleChampBenOpted = "#" + portletNamespace + "isLittleChampBenefitOpted";
	var selectedProductName = $("#" + portletNamespace + "productName").val().trim();
	var personalDetailJEl = "#personal-details";

	$('#' +dateOfBirthValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	$('#' +spouseDobJE).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	$('#' +childDobJE).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});

	var maritalStatusJE = portletNamespace + "MaritalStatusId";
	var childJE = portletNamespace + "ChildName";

	if($("input[name='" + maritalStatusJE + "']:checked").val() == "true"){
		$("#"+portletNamespace+"marriageDetails").show();
	}

	if($("input[name='" + childJE + "']:checked").val() == "yes"){
		$("#"+portletNamespace+"childDetails").show();
	}

	$("input[name='" + maritalStatusJE + "']").on("click",function(){
		if($("input[name='" + maritalStatusJE + "']:checked").val() == "true"){
			$("#"+portletNamespace+"marriageDetails").show();
		}else{
			$("#"+portletNamespace+"marriageDetails").hide();
		}
	});


	$("#"+annualIncomeRangeJE).blur(function(){
		var annInc = $("#"+annualIncomeRangeJE).val().replaceAll(",","");
		$("#"+annualIncomeRangeJE).val(Number(annInc).toLocaleString("en-IN"));
	});

	$("input[name='" + childJE + "']").on("click",function(){
		if($("input[name='" + childJE + "']:checked").val() == "yes"){
			$("#"+portletNamespace+"childDetails").show();
		}else{
			$("#"+portletNamespace+"childDetails").hide();
		}
	});

	var basicInvestingForValue= $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
	if(basicInvestingForValue == 'Myself'){
		hideAssuredFields();
	} else if(basicInvestingForValue == 'Family'){
		showAssuredFields();

		var dateOfBirth = moment($(dateOfBirthValidatorJEl).val(), 'DD/MM/YYYY').toDate();
		var today = new Date();
		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
		if(age >= 40){
			updateAssuredRelationRadioButtons();
		}
		$("input[type=radio][name='" + assuredRelation + "']").prop("checked", false);
		$("input[type=radio][name='" + assuredRelation + "'][value='"+assuredRelationValue+"']").prop('checked', true);
	}

	$(".basicInvestingForWarpper").on("change", 'input[type=radio][name='+basicInvestingForValidator+']', function(event){
		var investingForValue= $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
		if(investingForValue == 'Myself') {
			hideAssuredFields();
			$(assuranceNameValidatorJEl).val("");
			$(assuranceDobValidatorJEl).val("");
		} else if(investingForValue == 'Family') {
			$("input[type=radio][name='" + assuredRelation + "']").prop("checked", false);
			$("input[type=radio][name='" + assuredRelation + "'][value='Son']").prop('checked', true);

			var dateOfBirth = moment($(dateOfBirthValidatorJEl).val(), 'DD/MM/YYYY').toDate();

			var today = new Date();
			var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
			if(age >= 40){
				updateAssuredRelationRadioButtons();
			}else {
				removeAdditionalRadioButtons();
			}
			updateAssuredRelationRadioLabels();

			initEditBasicDetailModalFormValidationRules();

			showAssuredFields();
		}
	});

	$("#investingFormFamily").on("change", 'input[type=radio][name='+assuredRelation+']', function(event){
		var relationValue = $('input[type=radio][name='+assuredRelation+']:checked').val();

		$(assuranceNameValidatorJEl).val("");
		$(assuranceDobValidatorJEl).val("");

		updateAssuredRelationRadioLabels();

		var investingForValue= $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
		if(investingForValue == "Family") {
			initEditBasicDetailModalFormValidationRules();
		}
	});

	var buyFormRuleFields = new Object({}), buyFormMessageFields = new Object({});

	buyFormRuleFields[annualIncomeRangeJE] = {
			required : true,
	};

	buyFormRuleFields[occupationJE] = {
			required : true,
	};

	buyFormRuleFields[educationJE] = {
			required : true,
	};


	buyFormMessageFields[annualIncomeRangeJE] = {
			required : LANG_MESSAGES["please-enter-the-annual-income"],
	};

	buyFormMessageFields[occupationJE] = {
			required : LANG_MESSAGES["please-select-the-occupation"],
	};

	buyFormMessageFields[educationJE] = {
			required : LANG_MESSAGES["please-select-the-education"],
	};

	initEditBasicDetailModalFormValidation();

	function initEditBasicDetailModalFormValidation(){

		var summaryFormRuleFields = new Object({}), summaryFormMessageFields = new Object({});
		summaryFormRuleFields[fullNameValidator] = {
		    required: true,
		    CustomFullName: true
		};
		summaryFormRuleFields[dateOfBirthValidator] = {
		    required: true,
		    validDateFormat: true,
		    minAge: 18,
		    maxAge: 65
		};
		summaryFormRuleFields[mobileNumberValidator] = {
		    required: true,
		    mobileNumber:true
		};
		summaryFormRuleFields[emailValidator] = {
		    required: true,
		    customEmail : true
		};
		summaryFormRuleFields[annualIncomeRangeJE] = {
				required : true,
		};

		summaryFormRuleFields[spouseNameJE] = {
				required: true,
				CustomFullName : true
		};
		summaryFormRuleFields[spouseDobJE] = {
				required: true,
				validDateFormat: true,
				minAge:18,
				maxAge:65
		};
		summaryFormRuleFields[childDobJE] = {
				required: true,
				validDateFormat: true,
				minAge:0,
				maxAge:18
		};

		summaryFormMessageFields[fullNameValidator] = {
		    required: 'Enter your full name',
		    CustomFullName : 'Enter your full name'
		};
		summaryFormMessageFields[dateOfBirthValidator] = {
		    required: 'Enter your Date Of Birth',
		    validDateFormat: 'Enter a valid date',
		    minAge: 'Age should be between 18 to 65',
		    maxAge: 'Age should be between 18 to 65'
		};
		summaryFormMessageFields[mobileNumberValidator] = {
		    required: 'Please enter valid mobile number'
		};
		summaryFormMessageFields[emailValidator] = {
		    required: 'Please enter valid email address',
		    customEmail : 'Please enter valid email address'
		};

		summaryFormMessageFields[annualIncomeRangeJE] = {
				required : LANG_MESSAGES["select-your-annual-income-range"]
		};

		summaryFormMessageFields[spouseNameJE] = {
				required: LANG_MESSAGES["please-enter-your-spouse-name"],
				CustomFullName : LANG_MESSAGES["please-enter-your-full-name"]
		};
		summaryFormMessageFields[spouseDobJE] = {
				required: LANG_MESSAGES["please-enter-spouse-dob"],
				validDateFormat: LANG_MESSAGES["enter-valid-date"],
				minAge: LANG_MESSAGES["spouse-age-should-be-between-18-to-65"],
				maxAge: LANG_MESSAGES["spouse-age-should-be-between-18-to-65"]
		};
		summaryFormMessageFields[childDobJE] = {
				required: LANG_MESSAGES["please-enter-child-dob"],
				validDateFormat: LANG_MESSAGES["enter-valid-date"],
				minAge: LANG_MESSAGES["child-age-should-be-between-0-to-18"],
				maxAge: LANG_MESSAGES["child-age-should-be-between-0-to-18"]
		};

		$("#verifyCustEligibility").validate({
			rules : buyFormRuleFields,
			messages : buyFormMessageFields,
			onfocusout: function(element) {
			    this.element(element);
			},
			submitHandler : function(form) {
				form.submit();
			}
		});

		$.validator.addMethod("withinTenYears", function(value, element) {
		    var husbandDOB = new Date($(dateOfBirthValidatorJEl).val());
		    var spouseDOB = new Date(value);
		    var ageGap = Math.abs(calculateAge(husbandDOB) - calculateAge(spouseDOB));
		    return !(ageGap >= 10);
		}, LANG_MESSAGES["spouse-dob-within-10-of-your-age"]);

		jQuery.validator.addMethod("CustomFullName", function(value, element) {
			if (/^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value)) {
				return true;
			} else {
				return false;
			};
		}, enterYourXFullNameErrorMsg);

		jQuery.validator.addMethod("mobileNumber",function(value, element) {
			return /^(?!1\d{9}|2\d{9}|3\d{9}|4\d{9}|(\d)\1{4})[5-9]\d{9}$/.test(value);
		},'Please Enter Valid Mobile Number');

		$(editSummaryFormValidatorJEl).validate({
			errorClass: "error",
			errorElement: "div",
			rules : summaryFormRuleFields,
			messages : summaryFormMessageFields,
			onfocusout: function(element) {
	           this.element(element);
	        }
		});

		initEditBasicDetailModalFormValidationRules();
	}

	function initEditBasicDetailModalFormValidationRules() {

		var relationValue = $('input[type=radio][name='+assuredRelation+']:checked').data("name");
		var relationValueParam = relationValue + "'s";
		var assuranceFullNameErrMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", relationValueParam);
		var assuranceDOBErrMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", relationValueParam);

		//Remove Rules if exist
		if($(assuranceNameValidatorJEl).rules()) {
			$(assuranceNameValidatorJEl).rules("remove");
		}
		if($(assuranceDobValidatorJEl).rules()) {
			$(assuranceDobValidatorJEl).rules("remove");
		}

		$(assuranceNameValidatorJEl + "-error").remove();
		$(assuranceDobValidatorJEl + "-error").remove();

		// Add Rule with updated error messages
		$(assuranceNameValidatorJEl).rules( "add", {
			required: true,
			CustomFullName : true,
			messages: {
			    required: assuranceFullNameErrMsg,
			    CustomFullName : assuranceFullNameErrMsg
			}
		});

		$(assuranceDobValidatorJEl).rules( "add", {
			required: true,
			validDateFormat: true,
			minAge:0,
			maxAge:17,
			messages: {
			    required: assuranceDOBErrMsg,
			    validDateFormat: 'Enter a valid date',
			    minAge: ageBetween0to17ErrorMsg,
	  			maxAge: ageBetween0to17ErrorMsg
			}
		});
	}

	function updateAssuredRelationRadioButtons() {
		removeAdditionalRadioButtons();

		var htmlGrandSonDaughter = "";
		htmlGrandSonDaughter += "<li class='btn form-btn address-radio additional-assured-relation-radio p-0'>";
		htmlGrandSonDaughter += "<input type='radio' class='grandson' data-name='Grandson' name='" + portletNamespace + "assuredRelation' id='investForGrandSon' value='GrandSon'>";
		htmlGrandSonDaughter += "<label class='grandSon' for='investForGrandSon'>GrandSon</label>";
		htmlGrandSonDaughter += "</li>";

		htmlGrandSonDaughter += "<li class='btn form-btn address-radio additional-assured-relation-radio p-0'>";
		htmlGrandSonDaughter += "<input type='radio' class='granddaughter' data-name='Granddaughter' name='" + portletNamespace + "assuredRelation' id='investForGrandDaughter' value='GrandDaughter'>";
		htmlGrandSonDaughter += "<label class='grandSon' for='investForGrandDaughter'>GrandDaughter</label>";
		htmlGrandSonDaughter += "</li>";

		$("#investingFormFamily .radio_container").append(htmlGrandSonDaughter);
	}

	updateAssuredRelationRadioLabels();

	function updateAssuredRelationRadioLabels() {
		var relationValue = $('input[type=radio][name='+assuredRelation+']:checked').val();

		if(relationValue == 'Daughter'){
			$('#assurancename').text(daughterFullNameLabel);
			$('#assurancedob').text(daughterDOBLabel);
		}else if(relationValue == 'Son'){
	        $('#assurancename').text(sonFullNameLabel);
	        $('#assurancedob').text(sonDOBLabel);
	    }else if(relationValue == 'GrandSon'){
	    	$('#assurancename').text(grandsonFullNameLabel);
	    	$('#assurancedob').text(grandsonDOBLabel);
	    }else if(relationValue == 'GrandDaughter'){
			$('#assurancename').text(granddaughterFullNameLabel);
			$('#assurancedob').text(granddaughterDOBLabel);
		}
	}
	// code to set default assured relation
	function setDefaultAssuredRelation(){
		$("#investForGrandSon").removeAttr('checked');
		$("#investForGrandDaughter").removeAttr('checked');
		$("#investForSon").prop('checked',true);
		$('#'+assuranceNameValidator+'-error').remove();
		$('#'+assuranceDobValidator+'-error').remove();
		$('#assurancename').text("Son's Name ");
        $('#assurancedob').text("Son's Date Of Birth (dd/mm/yyyy)");
        $('#'+assuranceNameValidator).val('');
		$('#'+assuranceDobValidator).val('');
	}
	function removeAdditionalRadioButtons() {

		$(".additional-assured-relation-radio").remove();

	}

	function showAssuredFields() {
		$("#investingFormFamily").show();
		$("#assuranceNameForm").show();
	}

	function hideAssuredFields() {
		$("#investingFormFamily").hide();
		$("#assuranceNameForm").hide();
	}

	function removeValidationErrorMessages() {
		 $(dateOfBirthValidatorJEl + "-error").remove();
		 $(fullNameValidatorJEl + "-error").remove();
		 $(emailValidatorJEl + "-error").remove();
		 $(assuranceNameValidatorJEl + "-error").remove();
		 $(assuranceDobValidatorJEl + "-error").remove();
		 $(mobileNumberValidatorJEl + "-error").remove();
	}

	$.validator.addMethod("validDateFormat",function(value, element) {

		 return /^\d{2}\/\d{2}\/\d{4}$/.test(value);

	},'Please Enter Valid Date');

	/*  CODE FOR MINIMUM AND MAXIMUM AGE */
	$.validator.addMethod("minAge", function(value, element, min) {
	    var today = moment(new Date(), 'DD/MM/YYYY').toDate();

	    var birthDate = moment(value, 'DD/MM/YYYY').toDate();

	    var age = today.getFullYear() - birthDate.getFullYear();
	    if (age > min+1) { return true; }

	    var m = today.getMonth() - birthDate.getMonth();

	    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) { age--; }

	    return age >= min;
	}, "The age should be between 18 to 65");

	$.validator.addMethod("maxAge", function(value, element, max) {
		var maxAllowedDob = new Date();
		maxAllowedDob.setHours(0);
		maxAllowedDob.setMinutes(0);
		maxAllowedDob.setSeconds(0);
		maxAllowedDob.setFullYear(maxAllowedDob.getFullYear() - (Number(max) + 1));

		var birthDate = moment(value, 'DD/MM/YYYY').toDate();

		return maxAllowedDob < birthDate ;
	}, "The age should be between 18 to 65");

	jQuery.validator.addMethod("customEmail",function(value, element) {
		return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
				.test(value);
	},'');

	$("#editBasicDetails").click(function() {
		$("#editZPBasicDetailsModal").modal("show");
		removeValidationErrorMessages();
		var basicDetailRequestData = {};
		basicDetailRequestData[portletNamespace + "customerEnquiryId"] = $("#" + portletNamespace + "customerEnquiryId").val();
	});

	$("#closeModal").click(function(){
        $("#editZPBasicDetailsModal").modal("hide");
    });

	$('.save-and-eligibility-btn').on("click", function(){
	    saveBasicDetailsHandler();
	});

	$('.save-n-eligibility').on("change", function(){
         $(".zp-eligibility-success-msg").addClass("d-none");
         $('.save-and-eligibility-btn').removeClass('d-none');
         $('.save-close-btn').addClass('d-none');
	});

	function saveBasicDetailsHandler() {
		$(editSummaryFormValidatorJEl).validate().form();
		if($(editSummaryFormValidatorJEl).valid()) {
			var isValidEntry = verifyZPEligibility("save-basics");
            if(isValidEntry){
                $(".zp-eligibility-success-msg").removeClass("d-none");
                $(".zp-eligibility-sorry-msg").addClass("d-none");
                createCookieForZPEligibility("isEligibleforZP", "true", 1);
                $('.save-and-eligibility-btn').addClass('d-none');
                $('.save-close-btn').removeClass('d-none');
            }else{
                 $(".zp-eligibility-success-msg").addClass("d-none");
                 $(".zp-eligibility-sorry-msg").removeClass("d-none");
            }
        }
	}

	async function saveBasicDetails() {

		showLoader();

        var basicDetailRequestData = {};
        basicDetailRequestData[portletNamespace + "productCode"] = $("#" + portletNamespace + "productCode").val();
        basicDetailRequestData[portletNamespace + "customerEnquiryId"] = $("#" + portletNamespace + "customerEnquiryId").val();
        basicDetailRequestData[portletNamespace + "customerInvestmentDetailsId"] = $("#" + portletNamespace + "customerInvestmentDetailsId").val();
        basicDetailRequestData[portletNamespace + "gender"] = $("input[type=radio][name="+gender+"]:checked").val();
        basicDetailRequestData[portletNamespace + "fullName"] = $(fullNameValidatorJEl).val();
        basicDetailRequestData[portletNamespace + "dateOfBirth"] = $(dateOfBirthValidatorJEl).val();
        basicDetailRequestData[portletNamespace + "mobileNumber"] = $(mobileNumberValidatorJEl).val();
        basicDetailRequestData[portletNamespace + "email"] = $(emailValidatorJEl).val();
        basicDetailRequestData[portletNamespace + "Income"] = $("#"+annualIncomeRangeJE).val().replaceAll(",","");
        basicDetailRequestData[portletNamespace + "smoke"] =  $('input[name="'+smokeJE+'"]:checked').val();
        basicDetailRequestData[portletNamespace + "occupation"] = $("#"+occupationJE).val();
        basicDetailRequestData[portletNamespace + "education"] = $("#"+educationJE).val();
        basicDetailRequestData[portletNamespace + "MaritalStatusId"] = $('input[name="'+maritalStatusJE+'"]:checked').val();
        basicDetailRequestData[portletNamespace + "ChildName"] = $('input[name="'+ childJE +'"]:checked').val();

        if($('input[name="'+ childJE +'"]:checked').val() === "no"){
            $("#"+childDobJE).val("");
            basicDetailRequestData[portletNamespace + "ChildDob"] = "";
        }else {
            basicDetailRequestData[portletNamespace + "ChildDob"] = $("#"+childDobJE).val();
        }

        if($('input[name="'+maritalStatusJE+'"]:checked').val() === "false"){
            $("#"+spouseNameJE).val("");
            $("#"+spouseDobJE).val("");
            $('input[name="' + spouseSmokeJE + '"]:checked').val("137");
            basicDetailRequestData[portletNamespace + "SpouseName"] = "";
            basicDetailRequestData[portletNamespace + "SpouseDob"] = "";
            basicDetailRequestData[portletNamespace + "SpouseSmoke"] = 	"137";
        }else {
            basicDetailRequestData[portletNamespace + "SpouseName"] = $("#"+spouseNameJE).val();
            basicDetailRequestData[portletNamespace + "SpouseDob"] = $("#"+spouseDobJE).val();
            basicDetailRequestData[portletNamespace + "SpouseSmoke"] = $('input[name="' + spouseSmokeJE + '"]:checked').val();
        }

        try {
            await $.ajax({
                url:saveBasicDetailsURL,
                type:"post",
                data:basicDetailRequestData,
                dataType: 'json',
                success: function(response) {
                    var responseJson = JSON.parse(JSON.stringify(response.data));
                    if(response.customerInvestmentDetailsId) {
                        $("#" + portletNamespace + "customerInvestmentDetailsId").val(response.customerInvestmentDetailsId);
                    }
                    if(response.customerFamilyDetailsId) {
                        $("#" + portletNamespace + "customerFamilyDetailsId").val(response.customerFamilyDetailsId);
                    }
                    
                    /*
                     * Update Cusdtomer Details JSON Object
                     */
                    if(response.updateCustomerDetails) {
                    	var updateBasicDetailsMap = JSON.parse(response.updateCustomerDetails);
                    	
                    	basicDetailsMap.annualIncome = updateBasicDetailsMap.annualIncome.replaceAll(",","");
                    	basicDetailsMap.dateOfBirth = updateBasicDetailsMap.dateOfBirth;
                    	basicDetailsMap.age = calculateAge(updateBasicDetailsMap.dateOfBirth);
                    	basicDetailsMap.educationQualification = updateBasicDetailsMap.educationQualification;
                    	basicDetailsMap.email = updateBasicDetailsMap.email;
                    	basicDetailsMap.fullName = updateBasicDetailsMap.fullName;
                    	basicDetailsMap.gender = updateBasicDetailsMap.gender;
                    	basicDetailsMap.mobileNumber = updateBasicDetailsMap.mobileNumber;
                    	basicDetailsMap.occupation = updateBasicDetailsMap.occupation;
                    }
                    
                    /*
                     * Update Family Details JSON Object
                     */
                    if(response.updateFamilyDetails) {
                    	familyDetailsResponseJson =  JSON.parse(response.updateFamilyDetails);
                    }
                }
            });
        } catch(e) {
            console.log(e);
        }

        
        /*
         * Update Your Header Details, dropdown & Cards after Saving
         */
        updateYourDetailsHeader();

        updatePolicyTermDropdown();
        updatePayForDropdown();
        selectDefaultPayForOption();
        showHideWOPCard();
        showHideBHBCard();
        showHideCHILDCard();
        
        $("#editZPBasicDetailsModal").modal("hide");
        
        await updateCustomizePage(selectedRidersList, false);
        
        

	}

	    /*
    	 * Post eligibility check verification: Submit product details and redirect to Summary Page
    	 */
    	$('.save-close-btn').on("click", function(){
             saveBasicDetails();
             $(".zp-eligibility-success-msg").removeClass("d-none");
             $('.save-and-eligibility-btn').addClass('d-none');
             $('.save-close-btn').removeClass('d-none');

    	});


	/**
	 * Update Your Details Header
	 */
	updateYourDetailsHeader = function() {
		$(personalDetailsJEl).empty()
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(fullNameJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $('input[type=radio][name=' + genderEl + ']:checked').val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(dateOfBirthJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>&nbsp;" + $("#"+smokeJE).val() == 1 ? 'Smoker' : 'Non-Smoker' + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(mobileNumberJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(emailJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $("#"+annualIncomeRangeJE).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + calculateAge($(dateOfBirthJEl).val()) +"&nbsp;Years</li>");
	}
	
	$(dateOfBirthValidatorJEl).blur(function(){
		var investingForValue= $('input[type=radio][name='+basicInvestingForValidator+']:checked').val();
		var relationValue = $('input[type=radio][name='+assuredRelation+']:checked').val();
		var dateOfBirth= moment($(dateOfBirthValidatorJEl).val(), 'DD/MM/YYYY').toDate();
		
		var today = new Date();
		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));

	});
	
});
