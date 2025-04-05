$(document).ready(function() {
	 
	var fullNameValidator = portletNamespace + "fullName";
	var mobileNoValidator = portletNamespace + "mobileNumber";
	var dobValidator = portletNamespace + "dateOfBirth";
	var emailIdValidator = portletNamespace + "email";
	var annualIncomeValidator = portletNamespace + "annualIncome";
	var riskAppetiteValidator = portletNamespace + "riskAppetite";
	var investmentObjectiveValidator = portletNamespace + "investmentObjective";
	var totalSumAssuredValidator = portletNamespace + "totalSumAssured";
	var otherInsuranceValidator = portletNamespace + "otherInsurance";
	var suitabilityGender = portletNamespace + "suitabilityGender";
	var suitabilityFullName = portletNamespace + "suitabilityFullName";
	var suitablityMobileNumber = portletNamespace + "suitablityMobileNumber";
	var suitabilityDateOfBirth = portletNamespace + "suitabilityDateOfBirth";
	var suitabilityEmail = portletNamespace + "suitabilityEmail";
	var genderValidator = portletNamespace + "gender";
	var nriMobileNoValidator = portletNamespace + "phoneNo";
	var nriCountryCodeValidator = portletNamespace + "countryCode";
	var otherInsuranceNoValidator = portletNamespace + "otherInsuranceNo";
	
	var incomeValidator = portletNamespace + "Income";
	var smokeValidator = portletNamespace + "smoke";
	var maritalStatusValidator = portletNamespace + "MaritalStatusId";
	var spouseNameValidator = portletNamespace + "SpouseName";
	var spouseDobValidator = portletNamespace + "SpouseDob";
	var spouseSmokeValidator = portletNamespace + "SpouseSmoke";
	var childValidator = portletNamespace + "ChildName";	
	var childDobValidator = portletNamespace + "ChildDob";
	var annualIncomeRangeValidator = portletNamespace + "Income";
	
	var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
	var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");


	var maritalRadio = $("input[name='" + maritalStatusValidator + "']:checked").val();
	if(maritalRadio == "true"){
		$("#"+portletNamespace+"marriageDetails").show();	
	}
	
	var childRadio = $("input[name='" + childValidator + "']:checked").val();	
	if(childRadio == "yes"){
		$("#"+portletNamespace+"childDetails").show();	
	}
	
	var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
	var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
	var ruppeSignLang = Liferay.Language.get("rupee-sign");
	var date = document.getElementById(dobValidator);
	// new validator for the saral jeevan bima
	var yourAnnualIncomeValidator = portletNamespace + "annualIncome";
	var yourPincodeValidator = portletNamespace + "pincode";
	var yourOccupationValidator = portletNamespace + "occupation";
	var educationalQualificationValidator = portletNamespace + "educationQualification";
	var natureofDutyValidator = portletNamespace + "natureOfDuty";
	var spouseValidator = portletNamespace + "spouseParentRelation";
	var spouseoccupationsValidator = portletNamespace + "spouseParentOccupation";
	var spousesumassuredValidator = portletNamespace + "spouseParentSumassured";
	
	$('#' +dobValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	$('#' +spouseDobValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	$('#' +childDobValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy",  prefillYear: false});
	
	 /*  CODE FOR MINIMUM AND MAXIMUM AGE */
	$.validator.addMethod("minAge", function(value, element, min) {
	    var today = moment(new Date(), 'DD/MM/YYYY').toDate();
	    
	    var birthDate = moment(value, 'DD/MM/YYYY').toDate();
	    var age = today.getFullYear() - birthDate.getFullYear();		 	
	    if (age > min+1) { return true; }
	 
	    var m = today.getMonth() - birthDate.getMonth();
	 
	    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) { age--; }
	 
	    return age >= min;
	}, "The age should be between 18 to 60");
	
	$.validator.addMethod("validDateFormat",function(value, element) {
		
		 return /^\d{2}\/\d{2}\/\d{4}$/.test(value);
		
	},'Please Enter a valid date');
	
	$.validator.addMethod("maxAge", function(value, element, max) {
		var maxAllowedDob = new Date();
		maxAllowedDob.setHours(0);
		maxAllowedDob.setMinutes(0);
		maxAllowedDob.setSeconds(0);
		maxAllowedDob.setFullYear(maxAllowedDob.getFullYear() - (Number(max) + 1));
		
		var birthDate = moment(value, 'DD/MM/YYYY').toDate();
	    
		return maxAllowedDob < birthDate ;
	}, "The age should be between 18 to 60");
		
	jQuery.validator.addMethod('fullName',function(value, element) {
	      return /^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value);
	    },);
	
	jQuery.validator.addMethod("mobileNumber",function(value, element) {
		return /^(?!1\d{9}|2\d{9}|3\d{9}|4\d{9}|(\d)\1{4})[5-9]\d{9}$/.test(value);
	},'Please Enter Valid Mobile Number');
	
	jQuery.validator.addMethod("nriMobileNumber",function(value, element) {
		return /^(?!.*(\d)\1{6,})(?=.{7,15}$)\d+$/.test(value);
	},'Please Enter Valid Mobile Number');
	
	jQuery.validator.addMethod("countryCode",function(value, element) {
		return /^([0-9]-?){1,7}$/.test(value);
	},'Please Enter Valid Country Code');
	
	jQuery.validator.addMethod("customEmail",function(value, element) {
		return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
				.test(value);
	},'');
		
		
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
	
	$.validator.addMethod("withinTenYears", function(value, element) {
	    var husbandDOB = new Date($('#'+dobValidator).val());
	    var spouseDOB = new Date(value);
	    var ageGap = Math.abs(calculateAge(husbandDOB) - calculateAge(spouseDOB));
	    return !(ageGap >= 10);
	}, LANG_MESSAGES["spouse-dob-within-10-of-your-age"]);
	

	// new method for saraljeevanbima
	jQuery.validator.addMethod('pincode',function(value, element) {
		 return /^\d{6}$/.test(value);
	    },'Enter Your Pincode');
	jQuery.validator.addMethod('annualIncome',function(value, element) {
		return /^\d{4,}$/.test(value);	    
		},'Enter Your Spouse/Parent annual Income');
		
	
	var buyFormRuleFields = new Object({}), buyFormMessageFields = new Object({});
	var suitabilityMatrixRuleFields = new Object({}), suitabilityMatrixMessageFields = new Object({});
	var nriFormRuleFields = new Object({}), nriFormMessageFields = new Object({});
	var date = document.getElementById(dobValidator);
	
	buyFormRuleFields[fullNameValidator] = {
		required : true,
		fullName : true
	};
	buyFormRuleFields[mobileNoValidator] = {
			required : true,
			mobileNumber:true
		};
	buyFormRuleFields[dobValidator] = {
			required : true,
			validDateFormat: true,
			minAge:18,
			maxAge:function() {
				if(productName == 'Wealth Plus' || productName == 'GCAP' || productName == 'Income Builder'){
					var maximumAge = 55;
					return maximumAge;
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					var maximumAge = 60;
					return maximumAge;
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star' 
					||  productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star' || productName ==  'Zindagi Protect Plus' || productCode == "40039"){
					var maximum = 65;
					return maximum;
				}else if(productCode == "40030"){
					var maximum = 50;
					return maximum;
				}else if(productCode == "40071"){
					var maximum = 70;
					return maximum;
				}
			}
		};
	
	buyFormRuleFields[emailIdValidator] = {
			required : true,
			customEmail: true
		};
	//new for saraljeevan bima
	buyFormRuleFields[yourOccupationValidator] ={
			required: true,
	};
	// three extra field when click to housewife and student
	buyFormRuleFields[spouseValidator] ={
			required:  true,
			
	};
	buyFormRuleFields[spouseoccupationsValidator] ={
			required: true,
			
	};
	buyFormRuleFields[spousesumassuredValidator] ={
			required: true,
			
	};
	buyFormRuleFields[educationalQualificationValidator] ={
			required: true,
	};
	buyFormRuleFields[natureofDutyValidator] ={
			required: true,
	};
	buyFormRuleFields[yourAnnualIncomeValidator] ={
			required: true,
	};
	buyFormRuleFields[yourPincodeValidator] = {
			required: true,
			pincode: true
	};
	
	suitabilityMatrixRuleFields[riskAppetiteValidator] = {
			required : true
		};
	suitabilityMatrixRuleFields[annualIncomeValidator] = {
			required : true
		};
	suitabilityMatrixRuleFields[investmentObjectiveValidator] = {
			required : true
		};
	suitabilityMatrixRuleFields[totalSumAssuredValidator] = {
			required : true
		};
	
	buyFormRuleFields[nriMobileNoValidator] = {
		required : true,
		nriMobileNumber:true
	};
	
	buyFormRuleFields[nriCountryCodeValidator] = {
			required : true,
			countryCode:true
	};
	
	buyFormRuleFields[spouseNameValidator] = {
			required: true,
			fullName : true
	};
	buyFormRuleFields[spouseDobValidator] = {
			required: true,
			validDateFormat: true,
			minAge:18,
			maxAge:65
	};
	buyFormRuleFields[childDobValidator] = {
			required: true,
			validDateFormat: true,
			minAge:0,
			maxAge:18
	};
	
	buyFormRuleFields[annualIncomeRangeValidator] = {
			required : true,
	};
	
	buyFormMessageFields[fullNameValidator] = {
		required : Liferay.Language.get('please-enter-your-full-name'),
		fullName : Liferay.Language.get('please-enter-your-full-name')
	};

	buyFormMessageFields[mobileNoValidator] = {
			required : LANG_MESSAGES["please-enter-valid-mobile-number"],
			mobileNumber: LANG_MESSAGES["please-enter-valid-mobile-number"]
		};
	buyFormMessageFields[dobValidator] = {
			required : LANG_MESSAGES["enter-your-date-of-birth"],
			validDateFormat: 'Enter a valid date',
			minAge: function(){
			if(productName == 'Wealth Plus' || productName == 'GCAP' || productName == 'Income Builder'){  								
					return Liferay.Language.get('age-should-be-between-18-to-55');
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					return Liferay.Language.get('age-should-be-between-18-to-60');
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star'
					||  productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star' || productName ==  'Zindagi Protect Plus' || productCode == "40039"){
					return LANG_MESSAGES["age-should-be-between-18-to-65"];
				}else if(productCode == "40030"){
					return Liferay.Language.get('age-should-be-between-18-to-50');
				}else if(productCode == "40071"){
					return Liferay.Language.get('age-should-be-between-18-to-70');
				}
			},
			maxAge : function(){
				if(productName == 'Wealth Plus' || productName == 'GCAP' || productName == 'Income Builder'){  								
					return Liferay.Language.get('age-should-be-between-18-to-55');
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					return Liferay.Language.get('age-should-be-between-18-to-60');
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star'
					||  productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star' || productName ==  'Zindagi Protect Plus' || productCode == "40039"){
					return LANG_MESSAGES["age-should-be-between-18-to-65"];
				}else if(productCode == "40030"){
					return Liferay.Language.get('age-should-be-between-18-to-50');
				}else if(productCode == "40071"){
					return Liferay.Language.get('age-should-be-between-18-to-70');
				}
			}
		};
	
	buyFormMessageFields[emailIdValidator] = {
			required : LANG_MESSAGES["enter-a-valid-email-address"],
			customEmail : LANG_MESSAGES["enter-a-valid-email-address"]
		};
	
	// new messsage for saral jeevanbima
	buyFormMessageFields[yourOccupationValidator] ={
			required: Liferay.Language.get('your-occupation'),
	};
	buyFormMessageFields[spouseValidator] ={
			required: Liferay.Language.get('select-your-relationship'),
	};
	buyFormMessageFields[spouseoccupationsValidator]={
			required: Liferay.Language.get('select-your-spouse-occupation'),
	};
	buyFormMessageFields[spousesumassuredValidator] ={
			required: Liferay.Language.get('enter-your-spouse-sum-assure'),
	};
	buyFormMessageFields[educationalQualificationValidator] ={
			required: Liferay.Language.get('your-educational-qualification'),
	};
	buyFormMessageFields[natureofDutyValidator] ={
			required: Liferay.Language.get('your-nature-of-duty'),
	};
	buyFormMessageFields[yourAnnualIncomeValidator] ={
			required: Liferay.Language.get('enter-your-parents-annual-income'),
	};
	buyFormMessageFields[yourPincodeValidator] ={
			required: Liferay.Language.get('please-enter-pincode'),
	};
	
	suitabilityMatrixMessageFields[riskAppetiteValidator] = {
			required : Liferay.Language.get('please-select-your-risk-level')
		};
	suitabilityMatrixMessageFields[annualIncomeValidator] = {
			required : Liferay.Language.get('please-select-your-income-range')
		};
	suitabilityMatrixMessageFields[investmentObjectiveValidator] = {
			required : Liferay.Language.get('please-select-an-objective')
		};
	
	buyFormMessageFields[nriMobileNoValidator] = {
			required : Liferay.Language.get('please-enter-valid-mobile-number'),
			nriMobileNumber: Liferay.Language.get('please-enter-valid-mobile-number')
	};
	
	buyFormMessageFields[nriCountryCodeValidator] = {
			required : Liferay.Language.get('please-enter-the-country-code'),
			countryCode: Liferay.Language.get('please-enter-valid-country-code')
	};
	
	buyFormMessageFields[spouseNameValidator] = {
			required: LANG_MESSAGES["please-enter-your-spouse-name"],
			fullName : LANG_MESSAGES["please-enter-your-full-name"]
	};
	buyFormMessageFields[spouseDobValidator] = {
			required: LANG_MESSAGES["please-enter-spouse-dob"],
			validDateFormat: LANG_MESSAGES["enter-valid-date"],
			minAge: LANG_MESSAGES["spouse-age-should-be-between-18-to-65"],
			maxAge: LANG_MESSAGES["spouse-age-should-be-between-18-to-65"]
	};
	buyFormMessageFields[childDobValidator] = {
			required: LANG_MESSAGES["please-enter-child-dob"],
			validDateFormat: LANG_MESSAGES["enter-valid-date"],
			minAge: LANG_MESSAGES["child-age-should-be-between-0-to-18"],
			maxAge: LANG_MESSAGES["child-age-should-be-between-0-to-18"]
	};
	
	buyFormMessageFields[annualIncomeRangeValidator] = {
			required : LANG_MESSAGES["select-your-annual-income-range"]
	};
	
		
	$("input[name='" + maritalStatusValidator + "']").on("click",function(){
		if($("input[name='" + maritalStatusValidator + "']:checked").val() == "true"){
			$("#"+portletNamespace+"marriageDetails").show();
		}else{
			$("#"+portletNamespace+"marriageDetails").hide();
		}
	});
	
	$("input[name='" + childValidator + "']").on("click",function(){
		if($("input[name='" + childValidator + "']:checked").val() == "yes"){
			$("#"+portletNamespace+"childDetails").show();
		}else{
			$("#"+portletNamespace+"childDetails").hide();
		}
	});
	
	$('#sutabilityId').change(function() {
		if($("#sutabilityId").prop('checked') == false){
			if($('#generalForm').validate().form()) {
              $('#suitabilityModal').modal("show");
                $("#"+suitabilityFullName).val($('#'+fullNameValidator).val());
                $("#"+suitabilityGender).val($('input[name="'+genderValidator+'"]:checked').val());
                $("#"+suitablityMobileNumber).val($('#'+mobileNoValidator).val());
                $("#"+suitabilityDateOfBirth).val($('#'+dobValidator).val());
                $("#"+suitabilityEmail).val($('#'+emailIdValidator).val());
                $("#"+suitabilityPincode).val($('#'+yourPincodeValidator).val());
                $("#"+annualIncomeValidator+"-error").remove();
                $("#"+riskAppetiteValidator+"-error").remove();
                $("#"+investmentObjectiveValidator+"-error").remove();
                $("#"+totalSumAssuredValidator+"-error").remove();
                $("#"+otherInsuranceNoValidator).attr('checked','checked');
                $("#amt-in-word").hide();
                $("#suitabilityForm").trigger("reset");
                $("#totalSumAssuredDiv").hide();
            }
		}
	});

	$("#generalForm").validate({
		rules : buyFormRuleFields,
		messages : buyFormMessageFields,
		onfocusout: function(element) {
		    this.element(element);
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$("#suitabilityForm").validate({
		rules : suitabilityMatrixRuleFields,
		messages : suitabilityMatrixMessageFields,
		onfocusout: function(element) {
		    this.element(element);
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	$('#isNriId').change(function() {
		
		if($("#isNriId").prop('checked') == true){
			$("#isNriId").prop('checked',false);
			$('#nriModal').modal("show");
		}
	});
	
	$("#isNriSubmitBtn").on("click", function(e){
	    e.stopPropagation();
	    if(!$("#"+portletNamespace+"countryCode").val() || !$("#"+portletNamespace+"phoneNo").val() || $("#"+portletNamespace+"phoneNo").hasClass("error")){
	        e.stopPropagation();
	        return;
	    }
	    $("#isNriId").prop('checked',true);
	    $('#nriModal').modal("hide");
	});
	
	$('#suitabilityModal').hide();
	$('#totalSumAssuredDiv').hide();
		$("#closeModal").click(function(){
	        $("#suitabilityModal").modal("hide");
	        window.location.href="/investment-product-suggestion";
	    });
		
		$('input[type=radio][name= '+otherInsuranceValidator+']').change(function(){
			var inputValue = $('input[type=radio][name= '+otherInsuranceValidator+']:checked').val();
			if (inputValue =="Yes") {
                $("#totalSumAssuredDiv").show();
                $('#amt-in-word').show();
            }
			else {
				$("#totalSumAssuredDiv").hide();
				$('#amt-in-word').hide();
			}
		});
		$('#amt-in-word').hide();
		 $('#' + totalSumAssuredValidator).keyup(function(){
			 var totalSumAssured = $('#' +totalSumAssuredValidator).val();
			 totalSumAssured=totalSumAssured.replace(/,/g, "");
			var amountInWords =getAmountInWords(totalSumAssured);
			var roundOfValue=Math.round(totalSumAssured);
			$('#'+ totalSumAssuredValidator).val(roundOfValue.toLocaleString("en-IN"));
			
            $('#amt-in-word').show();
			$('#amt-in-word').html(ruppeSignLang + "&nbsp;" + amountInWords);
		});
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
		
		/*
		 * Formate Amount
		 */
		$("input.formateAmount").keyup(function(){
			var currentAmount = $(this).val().replaceAll(",","");
			$(this).val(Math.round(currentAmount).toLocaleString("en-IN"));
		});
		
		
		$("input.amt-in-word[value!=''], input.formateAmount[value!='']").trigger("keyup");
});
		
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