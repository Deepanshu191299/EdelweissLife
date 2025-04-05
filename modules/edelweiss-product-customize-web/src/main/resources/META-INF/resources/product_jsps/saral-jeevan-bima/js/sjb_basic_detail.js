$(document).ready(function() {
	const chooseSpouse = {
		h: ['1327'],
		st: ['1328', '1329'],
	};
	
	var fullNameValidator = portletNamespace + "fullName";
	var fullNameValidatorJEl = "#" + fullNameValidator;
	var dateOfBirthValidator = portletNamespace + "dateOfBirth";
	var dateOfBirthValidatorJEl = "#" + dateOfBirthValidator;
	var mobileNumberValidator = portletNamespace + "mobileNumber";
	var mobileNumberValidatorJEl = "#" + mobileNumberValidator;
	var emailValidator = portletNamespace + "email";
	var emailValidatorJEl = "#" + emailValidator;
	var occuaptionValidator = portletNamespace+"occupation";
	var occupationValidatorJEl = "#"+occuaptionValidator;
	var educationQualificationValidator = portletNamespace+"educationQualification";
	var educationQualificationValidatorJEl = "#"+ educationQualificationValidator;
	var natureOfDutyValidator = portletNamespace+ "natureOfDuty";
	var natureOfDutyValidatorJEl = "#"+ natureOfDutyValidator;
	var spouseParentRelationValidator = portletNamespace+"spouseParentRelation";
	var spouseParentRelationValidatorJEl = "#"+ spouseParentRelationValidator;
	var spouseParentOccupationValidator = portletNamespace+"spouseParentOccupation";
	var spouseParentOccupationvalidatorJEl = "#" + spouseParentOccupationValidator;
	var spouseParentSumassuredValidator = portletNamespace+"spouseParentSumassured";
	var spouseParentSumassuredValidatorJEl = "#"+spouseParentSumassuredValidator;
	var annualIncomeValidator = portletNamespace + "annualIncome";
	var annualIncomeValidatorJEl = "#"+ annualIncomeValidator;
	var pincodeValidator = portletNamespace+"pincode";
	var pincodeValidatorJEl = "#"+pincodeValidator;
	var editSummaryFormValidator = portletNamespace + "editSummaryForm";
	var editSummaryFormValidatorJEl = "#" + editSummaryFormValidator;
	var gender = portletNamespace + "gender";
	var smoker = portletNamespace + "smoker";
	var ageValue="";
	var annualIncomeVal = document.getElementById("annualIncomes");

	/**
	 * Buy Form 3 Initilazation
	 */
	initBasicDetailsForm = function(){
		initBasicDetailsFormEvent();
		
		/*
		 * Custom Validation methods
		 */
		jQuery.validator.addMethod('fullName',function(value, element) {
			return /^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value);
		}, 'Please enter the Full Name');
		jQuery.validator.addMethod("mobileNumber",function(value, element) {
			return /^(?!1\d{9}|2\d{9}|3\d{9}|4\d{9}|(\d)\1{4})[5-9]\d{9}$/.test(value);
		},'Please Enter Valid Mobile Number');
		jQuery.validator.addMethod("customEmail",function(value, element) {
			return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
			.test(value);
		},'');
		jQuery.validator.addMethod('pincode',function(value, element) {
			return /^\d{6}$/.test(value);
		},'Enter Your Pincode');
		jQuery.validator.addMethod('annualIncome',function(value, element) {
			return /^\d{4,}$/.test(value);	    
		},'Enter Your Spouse/Parent annual Income');
		$.validator.addMethod("dateOfBirth",function(value, element) {
			return /^\d{2}\/\d{2}\/\d{4}$/.test(value);
		},'Please Enter Valid Date');
		
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

		/*
		 * Validations for Form fields
		 */
		var summaryFormRuleFields = new Object({}), summaryFormMessageFields = new Object({});
		summaryFormRuleFields[fullNameValidator] = {
			required: true,
			fullName: true
		};
		summaryFormRuleFields[dateOfBirthValidator] = {
			required: true,
			dateOfBirth: true,
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

		summaryFormRuleFields[spouseParentRelationValidator] = {
			required: true
		};

		summaryFormRuleFields[spouseParentOccupationValidator] = {
			required: true
		};
		summaryFormRuleFields[spouseParentSumassuredValidator] = {
			required: true
		};
		summaryFormRuleFields[occuaptionValidator] = {
			required: true
		};
		summaryFormRuleFields[educationQualificationValidator] = {
			required: true
		};
		summaryFormRuleFields[natureOfDutyValidator] = {
			required: true
		};
		summaryFormRuleFields[annualIncomeValidator] = {
			required: true
		};
		summaryFormRuleFields[pincodeValidator] = {
			required: true,
			pincode: true
		};
		
		/*
		 * Validation messages
		 */
		summaryFormMessageFields[fullNameValidator] = {
			required: 'Enter your full name',
			fullName : 'Enter your full name'
		};
		summaryFormMessageFields[dateOfBirthValidator] = {
			required: 'Enter your Date Of Birth',
			dateOfBirth: 'Enter a valid date',
			minAge: 'Age should be between 18 to 65',
		    maxAge: 'Age should be between 18 to 65'

		};
		summaryFormMessageFields[mobileNumberValidator] = {
			required: 'Please enter valid mobile number',
			mobileNumber: 'Please enter valid mobile number'
		};
		summaryFormMessageFields[emailValidator] = {
			required: 'Please enter valid email address',
			customEmail : 'Please enter valid email address'
		};
		
		summaryFormMessageFields[spouseParentRelationValidator] = {
			required: 'Select Your Relationship'

		};
		summaryFormMessageFields[spouseParentOccupationValidator] = {
			required: 'Select Your Spouse/parent Occupation'

		};
		summaryFormMessageFields[spouseParentSumassuredValidator] = {
			required: 'Enter Your Total Sum Assured',
		};
		summaryFormMessageFields[occuaptionValidator] = {
			required: 'Enter Your Total Sum Assured',
		};
		summaryFormMessageFields[educationQualificationValidator] = {
			required: 'Enter Your Total Sum Assured',
		};
		summaryFormMessageFields[natureOfDutyValidator] = {
			required: 'Enter Your Total Sum Assured',
		};
		summaryFormMessageFields[annualIncomeValidator] = {
			required: 'Enter Annual Income'
		};
		summaryFormMessageFields[pincodeValidator] = {
			required: 'Please Enter Pincode',
			pincode: 'Please Enter Pincode'
		};
		
		/*
		 * Initialization of the Form Validator
		 */
		$("#"+ portletNamespace+"editSummaryForm").validate({
			rules : summaryFormRuleFields,
			messages : summaryFormMessageFields,
			onfocusout: function(element) {
				this.element(element);
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		
		/*
		 * Update Spouse Parent relation dropdown options
		 */
		updateChooseSpouse();
		
		/*
		 * Hide or show Spouse/Parent fields on load
		 */
		var occupationElement = document.getElementById(portletNamespace+"occupation");
		var selectedValue = occupationElement.value;
		if (selectedValue === 'h' || selectedValue=== 'st') {
			$('#extraFields').show();
			annualIncomeVal.innerText = "Parent/Spouse’s Annual Income";
		}
		
		$(dateOfBirthValidatorJEl).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	}

	/**
	 * Initialization of the Event Listeners
	 */
	initBasicDetailsFormEvent = function(){
		
		
		/*
		 * On Click of basic details Edit button 
		 */
		$("#editDetails").click(function(){
			$("#editModal").modal("show");
			initEditBasicDetailFormValues();
		});
		
		/*
		 * Save the user basic details
		 */
		$("#save").click(saveBasicDetailsHandler);
		
		/*
		 * On change of occupation value
		 */
		document.getElementById(occuaptionValidator).addEventListener('change', updateChooseSpouse);
		document.getElementById(occuaptionValidator).addEventListener('change', toggleExtraFields);
		document.getElementById(occuaptionValidator).addEventListener('change',function(){
			setNatureDuty($("#"+portletNamespace+"occupation").val());
		});
		
	}
	
	
	/*
	 * Remove Validation messages
	 */
	removeValidationErrorMessages = function() {
		$(dateOfBirthValidatorJEl + "-error").remove();
		$(fullNameValidatorJEl + "-error").remove();
		$(emailValidatorJEl + "-error").remove();
		$(mobileNumberValidatorJEl + "-error").remove();
		$(annualIncomeValidatorJEl+ "-error").remove();
		$(pincodeValidatorJEl+ "-error").remove();
		$(spouseParentRelationValidatorJEl + "-error").remove();
		$(spouseParentOccupationvalidatorJEl + "-error").remove();
		$(spouseParentSumassuredValidatorJEl + "-error").remove();
		$(occupationValidatorJEl + "-error").remove();
		$(educationQualificationValidatorJEl + "-error").remove();
		$(natureOfDutyValidatorJEl + "-error").remove();

	}
	
	/*
	 * Reset the form with current user basci details
	 */
	initEditBasicDetailFormValues = function(){
		
		removeValidationErrorMessages();

		const form = document.getElementById(portletNamespace+'editSummaryForm');
		const inputFields = form.querySelectorAll('input, select');

		inputFields.forEach((field) => {
			if(!field.dataset.submittedValue){
				return;
			}
			if (field.type === 'radio') {
				const submittedValue = field.dataset.submittedValue;
				field.checked = (submittedValue === 'checked');
			} else {
				const submittedValue = field.dataset.submittedValue;
				field.value = submittedValue;
			}
		});
		
		updateChooseSpouse();
		toggleExtraFields();
		setNatureDuty($("#"+portletNamespace+"occupation").val());
		$("input.formateAmount").each(function() {
			  let inputValue = $(this).val();
			  const numericValue = parseFloat(inputValue.replaceAll(",", ""));
			  if (!isNaN(numericValue)) {
				    const roundedValue = Math.round(numericValue);
				    $(this).val(roundedValue.toLocaleString("en-IN"));
			  }
			  else {
				  $(this).val("");
			  }
			});
	}
	
	/*
	 * Update the current default data with Submitted Data
	 */
	updateDefaultValuesWithSubmittedValues = function(){
		const form = document.getElementById(portletNamespace+'editSummaryForm');
		const inputFields = form.querySelectorAll('input, select');

		inputFields.forEach((field) => {
			
			if (field.type === 'radio') {
				field.checked ? field.dataset.submittedValue = 'checked' : field.dataset.submittedValue = "";
			} else {
				const submittedValue = field.value;
				field.dataset.submittedValue = submittedValue;
			}
		});
	}
	
	/*
	 * Save basic details
	 */
	async function saveBasicDetailsHandler() {
		$(editSummaryFormValidatorJEl).validate().form();

		if(!$(editSummaryFormValidatorJEl).valid()) {
			console.warn("Edit Basic Form has some errors");
			return;
		}
		showLoader();
		$("#editModal").modal("hide");
		
		$("input.formateAmount").each(function() {
			$(this).val($(this).val().replaceAll(",",""));
		});
		
		updateYourDetailsHeader();
		
		var basicDetailRequestData = {};
		basicDetailRequestData[portletNamespace + "productCode"] = $("#" + portletNamespace + "productCode").val();
		basicDetailRequestData[portletNamespace + "customerEnquiryId"] = $("#" + portletNamespace + "customerEnquiryId").val();
		basicDetailRequestData[portletNamespace + "customerFamilyDetailsId"] = $("#" + portletNamespace + "customerFamilyDetailsId").val();

		basicDetailRequestData[portletNamespace + "gender"] = $("input[type=radio][name="+gender+"]:checked").val();
		basicDetailRequestData[portletNamespace + "fullName"] = $(fullNameValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "dateOfBirth"] = $(dateOfBirthValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "mobileNumber"] = $(mobileNumberValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "email"] = $(emailValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "annualIncome"] = $(annualIncomeValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "pincode"] = $(pincodeValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "occupation"] = $(occupationValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "educationQualification"] = $(educationQualificationValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "natureOfDuty"] = $(natureOfDutyValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "spouseParentRelation"] = $(spouseParentRelationValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "spouseParentOccupation"] = $(spouseParentOccupationvalidatorJEl).val();
		basicDetailRequestData[portletNamespace + "spouseParentSumassured"] = $(spouseParentSumassuredValidatorJEl).val();
		basicDetailRequestData[portletNamespace + "smoker"] = $("input[type=radio][name="+smoker+"]:checked").val();
		
		try {
			await $.ajax({
				url:saveBasicDetailsURL,
				type:"post",
				data:basicDetailRequestData,
				dataType: 'json',
				success: function(response) {
					var responseJson = JSON.parse(JSON.stringify(response.data));
					
					/*
					 * Update Family Details Id
					 */
					$("#" + portletNamespace + "customerFamilyDetailsId").val(response.customerFamilyDetailsId);
						
					/*
					 * Update the submitted data to teh dataset
					 */
					updateDefaultValuesWithSubmittedValues()
				}
			});

			/*
			 * calling the product listing api
			 */
			initCustomizePage();
		} catch(e) {
			console.error(e);
			hideLoader();
		}
	}

	/*
	 * update the basic details header
	 */
	updateYourDetailsHeader = function() {
		var smokeValue = $('input[type=radio][name=' + smokerEl + ']:checked').val();
		var smokeDescription="";
		if(smokeValue==="1"){
			smokeDescription="Smoker";
		}
		else if(smokeValue==="0"){
			smokeDescription="Non-Smoker";
		}
		var genderValue = $('input[type=radio][name=' + genderEl + ']:checked').val();
		var genderDescription = "";
		if(genderValue==="3"){
			genderDescription="Male";
		}
		else if(genderValue==="4"){
			genderDescription="Female";
		}
		else{
			genderDescription="Transgender";
		}
	
		customerDob = $(dateOfBirthJEl).val();
		var age = calculateAge(customerDob);

		$(personalDetailsJEl).empty()
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(fullNameJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" +  genderDescription + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(dateOfBirthJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + smokeDescription+ "</li>");			
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(mobileNumberJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + $(emailJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item amount-with-rupee' data-amount='"+$(annualIncomeJEl).val()+"'>" + $(annualIncomeJEl).val() + "</li>");
		$(personalDetailsJEl).append("<li class='nav-item'>" + age + " Years</li>");			
		
		updatePolicyTermDropdown();
	}

	showLoader = function(){
		$("#preloader").addClass("preloader");
		$("#"+portletNamespace+"tracker-loader").show();
	}

	hideLoader = function(){
		$("#preloader").removeClass("preloader");
		$("#"+portletNamespace+"tracker-loader").hide();
	}

	function toggleExtraFields() {
		
		var currentOccupation = document.getElementById(occuaptionValidator);
		if(!currentOccupation){
			return;
		}
		
		var extraFields = document.getElementById("extraFields");
		if (currentOccupation.value === "h" || currentOccupation.value==="st") {
			extraFields.style.display = "flex";
			annualIncomeVal.innerText = "Parent/Spouse’s Annual Income";
			console.log("entering the motion function");
			$( "#extraFields input,#extraFields select " ).each(function() {
				if($(this).val()==="" || $(this).val()===null ){
				$(this).parent().children('.motion-label').removeClass("motion-label-heading")
			        console.log($(this).parent().children('.motion-label'));
				}
				else{
				$(this).parent().children('.motion-label').addClass("motion-label-heading")
				}
				});
			$( "#extraFields input" ).click(function() {
				  $(this).parent().children('.motion-label').addClass("motion-label-heading")
				});
			$( "#extraFields select" ).mousedown(function() {
				  $(this).parent().children('.motion-label').addClass("motion-label-heading")
				}); 
		} else {
			extraFields.style.display = "none";
			annualIncomeVal.innerText = "Your Annual Income";

			/*
			 * Reset the fields
			 */
			const extraFieldsDiv = document.getElementById('extraFields');
			const inputFields = extraFieldsDiv.querySelectorAll('input, select');

			inputFields.forEach((field) => {
				field.value = '';
			});
		}
	} 

	/*
	 * Update Nature Of Duty Drodpwn based on Occupation
	 */
	function setNatureDuty(selectedOccupationValue){
		var selectedOccupation = $("#"+portletNamespace+"occupation option[value='"+selectedOccupationValue+"']").text().toLowerCase().trim();
		var matchednatureOfDutyOption = $("#"+portletNamespace+"natureOfDuty option").filter(function() {
		    return $(this).text().toLowerCase().trim() == selectedOccupation
		  });
		if(matchednatureOfDutyOption && matchednatureOfDutyOption.length>0 && (selectedOccupationValue=="st" || selectedOccupationValue=="h")){
		    $("#"+portletNamespace+"natureOfDuty option").hide();
		    $("#"+portletNamespace+"natureOfDuty option[value='"+matchednatureOfDutyOption.val()+"']").show();
		    $("#"+portletNamespace+"natureOfDuty").val(matchednatureOfDutyOption.val());
		}else{
			$("#"+portletNamespace+"natureOfDuty option").show();
		}
	}

	function setNatureValue(dropdownName, selectedValue){
		
		for (var i = 0; i < dropdownName.options.length; i++) {
			if (dropdownName.options[i].text === selectedValue) {
				dropdownName.selectedIndex = i;
				break;
			}
		}
		
	} 

	function updateChooseSpouse() {
		const occupation = document.getElementById(occuaptionValidator);
		const spouse = document.getElementById(spouseParentRelationValidator);
		const selectedOccupation = occupation.value;
		
		const selectedValue = spouse.value;
		if (selectedOccupation in chooseSpouse) {
			const allowedOptions = chooseSpouse[selectedOccupation];
			for (let option of spouse.options) {
				option.style.display = allowedOptions.includes(option.value) ? 'block' : 'none'; 
				option.disabled  = allowedOptions.includes(option.value) ? false : true;
			}
			
			if( spouse.querySelector("option[value='"+selectedValue+"']") && spouse.querySelector("option[value='"+selectedValue+"']").style.display == "none"){
				spouse.value = chooseSpouse[selectedOccupation][0];
			}
			
		} 
		else {
			for (let option of spouse.options) {
				option.style.display = 'block';
				option.disabled  = false;
			}
		}
		
	}
	
	initBasicDetailsForm();
	
	/*
	 * Formate Amount
	 */
	$("input.formateAmount").keyup(function(){
		var currentAmount = $(this).val().replaceAll(",","");
		$(this).val(Math.round(currentAmount).toLocaleString("en-IN"));
	});
});