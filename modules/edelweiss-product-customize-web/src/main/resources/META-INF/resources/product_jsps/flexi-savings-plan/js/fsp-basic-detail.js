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
	
	$("#editBasicDetails").click(function(){
		$("#editModal").modal("show");
		
		removeValidationErrorMessages();
		
		showLoader();
		
		var basicDetailRequestData = {};
		basicDetailRequestData[portletNamespace + "customerEnquiryId"] = $("#" + portletNamespace + "customerEnquiryId").val();
		
		$.ajax({
			
			url:getBasicDetailsURL,
			type:"post",
			data:basicDetailRequestData,
			dataType: 'json',
			success: function(response) {
				
				hideLoader();
				
				$(fullNameValidatorJEl).val(response.fullName);
				$(dateOfBirthValidatorJEl).val(response.dateOfBirth);
				$(mobileNumberValidatorJEl).val(response.mobileNumber);
				$(emailValidatorJEl).val(response.email);
				
				//Remove Prop Checked From Gender and Update checked to from LR OBject
				$("input[type=radio][name='" + gender + "']").prop("checked", false);
				$("input[type=radio][name='" + gender + "'][value='" + response.gender + "']").prop('checked', true);
				
				//Remove Prop Checked From Investment Objective and Update checked to from LR OBject
				$("input[type=radio][name='" + investmentObjective + "']").prop("checked", false);
				$("input[type=radio][name='" + investmentObjective + "'][value='" + response.investmentObjective + "']").prop('checked', true);
				
				if(response.customerInvestmentDetailsRel.length > 0){
				 
					//Reset Investing For Based on Saved Value in LR Object
					$("input[type=radio][name='" + basicInvestingForValidator + "']").prop("checked", false);
					$("input[type=radio][name='" + basicInvestingForValidator + "'][value='" + response.customerInvestmentDetailsRel[0].investingFor + "']").prop('checked', true);
					
					if(response.customerInvestmentDetailsRel[0].investingFor == "Myself"){
						hideAssuredFields();
						removeAdditionalRadioButtons();
					} else if(response.customerInvestmentDetailsRel[0].investingFor == "Family"){
						
						$(assuranceNameValidatorJEl).val(response.customerFamilyDetailsRel[0].assuranceFullName);
						$(assuranceDobValidatorJEl).val(response.customerFamilyDetailsRel[0].assuranceDob);
						
						var age = calculateAge(response.dateOfBirth);
						
						if(age >= 40) {
							updateAssuredRelationRadioButtons();
						} else {
							removeAdditionalRadioButtons();
						}
						
						$("input[type=radio][name='" + assuredRelation + "']").prop("checked", false);
						$("input[type=radio][name='" + assuredRelation + "'][value='" + response.customerFamilyDetailsRel[0].assuredRelation + "']").prop('checked', true);
						
						updateAssuredRelationRadioLabels();
						
						initEditBasicDetailModalFormValidationRules();
						
						showAssuredFields();
					}
				}
			}, 
			error : function(data) {
				hideLoader();
			}
		});
	});
	
	$("#closeModal").click(function(){
        $("#editModal").modal("hide");
    });
	
	$("#save").click(saveBasicDetailsHandler);
	
	async function saveBasicDetailsHandler() {
		$(editSummaryFormValidatorJEl).validate().form();
		if($(editSummaryFormValidatorJEl).valid()) {
			showLoader();
			
			var assuranceNameVal = $(assuranceNameValidatorJEl).val();
			var assuranceDobVal = $(assuranceDobValidatorJEl).val();
			var assuredRelationVal = $("input[type=radio][name="+assuredRelation+"]:checked").val();
			var investingForValue = $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
			var removeLittleChampVal = $("#" + portletNamespace + "removeLittleChamp").val();
			
			$(investingForJEl).val(investingForValue);
			
			/*
			 * Update Your Details Header after Saving Family Details
			 */
			updateYourDetailsHeader();
			
			/*
			 * Update Policy Term drodown & Life Cover Continuation Benefit: As These are dependent on the customer age
			 */
			updatePolicyTermDropdown();
			hideShowLifeCoverContinuationBenefit();
			
			var basicDetailRequestData = {};
			basicDetailRequestData[portletNamespace + "productCode"] = $("#" + portletNamespace + "productCode").val();
			basicDetailRequestData[portletNamespace + "customerEnquiryId"] = $("#" + portletNamespace + "customerEnquiryId").val();
			basicDetailRequestData[portletNamespace + "customerInvestmentDetailsId"] = $("#" + portletNamespace + "customerInvestmentDetailsId").val();

			basicDetailRequestData[portletNamespace + "customerFamilyDetailsId"] = $("#" + portletNamespace + "customerFamilyDetailsId").val();
			basicDetailRequestData[portletNamespace + "gender"] = $("input[type=radio][name="+gender+"]:checked").val();
			basicDetailRequestData[portletNamespace + "fullName"] = $(fullNameValidatorJEl).val();
			basicDetailRequestData[portletNamespace + "dateOfBirth"] = $(dateOfBirthValidatorJEl).val();
			basicDetailRequestData[portletNamespace + "mobileNumber"] = $(mobileNumberValidatorJEl).val();
			basicDetailRequestData[portletNamespace + "email"] = $(emailValidatorJEl).val();
			basicDetailRequestData[portletNamespace + "investmentObjective"] = $("input[type=radio][name="+investmentObjective+"]:checked").val();
			basicDetailRequestData[portletNamespace + "investingFor"] = $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
			basicDetailRequestData[portletNamespace + "assuredRelation"] = assuredRelationVal;
			basicDetailRequestData[portletNamespace + "assuranceFullName"] = assuranceNameVal;
			basicDetailRequestData[portletNamespace + "assuranceDob"] = assuranceDobVal;
			
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

						//Sync Data to Family Radio Modal Pop-up
						if(investingForValue == "Family") {
							initFamilyRadioButtons();
							$("input[type=radio][name='" + portletNamespace + "familyRadio'][value='" + assuredRelationVal + "']").prop("checked", true).trigger("change");
							
							$("#" + portletNamespace + "investingForFullName").val(assuranceNameVal);
							$("#" + portletNamespace + "investingForDOB").val(assuranceDobVal);
						} else if(investingForValue == "Myself") {
							$("#" + portletNamespace + "customerFamilyDetailsId").val("");
							resetFamilyModalData();
						}
					}
				});	
			} catch(e) {
				console.log(e);
			}
			
			$("#editModal").modal("hide");
			
			updateCustomizePage(selectedRidersList, false);
		}
	}
	
	/**
	 * Update Your Details Header
	 */
	updateBasicDetailsHeader = function() {
		$(personalDetailJEl).empty()
		$(personalDetailJEl).append("<li class='nav-item'>" + $(fullNameValidatorJEl).val() + "</li>");
		$(personalDetailJEl).append("<li class='nav-item'>" +  $('input[type=radio][name=' + gender + ']:checked').val() + "</li>");
		$(personalDetailJEl).append("<li class='nav-item'>" + $(dateOfBirthValidatorJEl).val() + "</li>");
		$(personalDetailJEl).append("<li class='nav-item'>" + $(mobileNumberValidatorJEl).val() + "</li>");
		$(personalDetailJEl).append("<li class='nav-item'>" + $(emailValidatorJEl).val() + "</li>");
		$(personalDetailJEl).append("<li class='nav-item'>" + $('input[name="' + investmentObjective + '"]:checked').data("name") + "</li>");
		var selectedInvestingFor = $("input[type=radio][name="+basicInvestingForValidator+"]:checked").val();
		$(personalDetailJEl).append("<li class='nav-item'>" + selectedInvestingFor + "</li>");
		if(selectedInvestingFor == "Family") {
			$(personalDetailJEl).append("<li class='nav-item'>" + $(assuranceNameValidatorJEl).val() + "</li>");
			$(personalDetailJEl).append("<li class='nav-item'>" + $(assuranceDobValidatorJEl).val() + "</li>");
		}
	}
	
	$(dateOfBirthValidatorJEl).blur(function(){
		var investingForValue= $('input[type=radio][name='+basicInvestingForValidator+']:checked').val();
		var relationValue = $('input[type=radio][name='+assuredRelation+']:checked').val();
		var dateOfBirth= moment($(dateOfBirthValidatorJEl).val(), 'DD/MM/YYYY').toDate();
		
		var today = new Date();
		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
		if(age >=40 && investingForValue == "Family"){
			updateAssuredRelationRadioButtons();
			$("input[type=radio][name="+assuredRelation+"][value="+relationValue+"]").prop("checked", true);
		}else if(investingForValue == 'Family'){
			removeAdditionalRadioButtons();
			
			if(relationValue == "Son" || relationValue == "Daughter"){
		        $("input[type=radio][name="+assuredRelation+"][value="+relationValue+"]").prop("checked", true);
		        
		    }else if(relationValue == "GrandSon" || relationValue == "GrandDaughter"){
		        setDefaultAssuredRelation();
		        $("input[type=radio][name="+assuredRelation+"][value=Son]").prop("checked", true);
		    }
			
			
		}else {
			removeAdditionalRadioButtons();
			setDefaultAssuredRelation();
			initEditBasicDetailModalFormValidationRules();
		}
	});
	
});
