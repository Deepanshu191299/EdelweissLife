jQuery(document).ready(function() {
	var editSummaryFormValidator = portletNamespace + "editSummaryForm";
	var fullNameValidator = portletNamespace + "fullName";
	var dateOfBirthValidator = portletNamespace + "dateOfBirth";
	var mobileNumberValidator = portletNamespace + "mobileNumber";
	var emailValidator = portletNamespace + "email";
	var assuranceNameValidator = portletNamespace + "assuranceFullName";
	var assuranceDobValidator = portletNamespace + "assuranceDob";
	var investingForValidator = portletNamespace + "investingFor";
	var assuredRelationValidator = portletNamespace + "assuredRelation";
	var gender = portletNamespace + "gender",
	investmentObjective = portletNamespace + "investmentObjective";
	var basicDetailsForm = portletNamespace + "editSummaryForm";
	var hiddenFieldValue = $('#inputHidden').val();	
	var risingStarVal = portletNamespace + "risingStar";
	var removeRisingStarBenefitLabel = portletNamespace + "removeRisingStarBenefit";
	
	var removeLittleChampVal = portletNamespace + "littleChamp";
	var removeLittleChampBenefitLabel = portletNamespace + "removeLittleChampBenefit";

	var maritalStatusValidator = portletNamespace + "MaritalStatusId";
	var childValidator = portletNamespace + "ChildName";
	
	var annualIncomeJEl = "#" + portletNamespace + "Income";
	
	$(annualIncomeJEl).blur(function(){
		var annInc = $(annualIncomeJEl).val().replaceAll(",","");
		$(annualIncomeJEl).val(Number(annInc).toLocaleString("en-IN"));
	});
	
	if($("input[name='" + maritalStatusValidator + "']:checked").val() == "true"){
		$("#"+portletNamespace+"marriageDetails").show();	
	}
	
	if($("input[name='" + childValidator + "']:checked").val() == "yes"){
		$("#"+portletNamespace+"childDetails").show();	
	}
	
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

	/*
	 * Display Double Premium Amount alrt message and Terms & Condition if applicable
	 */
	if(paymentFrequency != 'Monthly'){
		$("#doublePremiumAmountAlrtMessage").addClass("d-none");
		$("#termsAndCondWrapper").addClass("d-none");
	}
	
	$("#sjbTermsConditions").on("change", function(){
		if($(this).prop("checked")){
			$("button#save").prop("disabled", "");
			$("button#save").removeClass("disabled-btn")
		}else{
			$("button#save").prop("disabled", "true");
			$("button#save").addClass("disabled-btn")
		}
	})
	
	
	// code for rising star benefit 
	var risingStarBenefitOptedVal = $('#' +risingStarVal).val();
	$("#removeRisingStarWrapper").hide();
	$('#' +removeRisingStarBenefitLabel).click(function(){
		
		$('#'+risingStarVal).val("true");
		$("#removeRisingStarWrapper").hide();
	});
	

    // code for little champ benefit 
	var isLittleChampBenefitOptedVal = $('#' +removeLittleChampVal).val();
	$("#removeLittleChampWrapper").hide();
	 $('#' +removeLittleChampBenefitLabel).click(function() {
		    $('#' +removeLittleChampVal).val("true"); 
		    $("#removeLittleChampWrapper").hide();
	});
	
   
	// code foreach loop for json response  
	var jsonObj = hiddenFieldValue;
	
    var value;
    var selectValue = $('#rates').val();
    if(jsonObj){
	 $.each(JSON.parse(jsonObj), function(propName, propVal) {
	        var key = propName;
	         var value = propVal;
	         let roundOfValue=Math.round(value);
	         if(selectValue=='4' && key == '4'){
				 value = propVal;
				$('#valueOfRates').show();
				$('#valueOfRates').text(LANG_MESSAGES["rupee-sign"] + " " +  roundOfValue.toLocaleString("en-IN"));
			}
			else if (selectValue=='8' && key== '8'){
					value = propVal;
					$('#valueOfRates').show();
					$('#valueOfRates').text(LANG_MESSAGES["rupee-sign"] + " " + roundOfValue.toLocaleString("en-IN"));
			}
	    });
    }
   
    if(productName === 'Wealth Rise Plus' || productName === 'Wealth Plus' || productName === 'Wealth Secure Plus' || productCode == "40030"){
    	$("#"+portletNamespace+"premiumAmount").append(Math.round(investmentAmount).toLocaleString("en-IN")+"<sup> Inc. of GST</sup>");
    	$("#"+portletNamespace+"planPremiumAmount").append(Math.round(investmentAmount).toLocaleString("en-IN"));
    }else if(productName === 'Capital Secure Plus'){
    	$("#"+portletNamespace+"premiumAmount").append(Math.round(premiumAmount).toLocaleString("en-IN")+"<sup> Inc. of GST</sup>");
    	$("#"+portletNamespace+"planPremiumAmount").append(Math.round(investmentAmount).toLocaleString("en-IN"));
    }else{
    	$("#"+portletNamespace+"premiumAmount").append(Math.round(premiumAmount).toLocaleString("en-IN")+"<sup> Inc. of GST</sup>");
    }
    
    $("#premiumAmountCol").append(Math.round(investmentAmount).toLocaleString("en-IN"));
    
    if(productName === 'Wealth Rise Plus' || productName === 'Wealth Plus' || productName === 'Wealth Secure Plus' || productCode == "40030"){
    	$("#totalPremium").append(Math.round(investmentAmount).toLocaleString("en-IN"));
    }else{
    	$("#totalPremium").append(Math.round(premiumAmount).toLocaleString("en-IN"));
    }
    
    if(productName === 'Wealth Plus' && paymentFrequency == 'Monthly'){
    	$("#"+portletNamespace+"premiumAmount").html(Number(Math.round(investmentAmount)*2).toLocaleString("en-IN")+"<sup> Inc. of GST</sup>");
    	$("#premiumAmountCol").html(LANG_MESSAGES["rupee-sign"]+" "+Number(Math.round(investmentAmount)*2).toLocaleString("en-IN"));
    	$("#totalPremium").html(LANG_MESSAGES["rupee-sign"]+" "+Number(Math.round(investmentAmount)*2).toLocaleString("en-IN"));
    }
    
	// Code for date of birth
	var date = document.getElementById(dateOfBirthValidator);
	var assuranceDOB = document.getElementById(assuranceDobValidator);
	$('#'+dateOfBirthValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
	$('#'+assuranceDobValidator).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
    
	
  

  //Code for changeing 8%,4% rates 
    $('#rates').change(function(){
		var selectValue = $('#rates').val();
		$.each(JSON.parse(jsonObj), function(propName, propVal) {
          var key = propName;
           var value = propVal;
           let roundOfValue=Math.round(value);
	if(selectValue=='4' && key == '4'){
			 value = propVal;
			
			$('#valueOfRates').show();
			$('#valueOfRates').text(LANG_MESSAGES["rupee-sign"] + " " + roundOfValue.toLocaleString("en-IN"));
	}
	else if (selectValue=='8' && key== '8'){
			value = propVal;
			$('#valueOfRates').show();
			$('#valueOfRates').text(LANG_MESSAGES["rupee-sign"] + " " + roundOfValue.toLocaleString("en-IN"));
		}
	});
	});
	
    // code on load default selected value for investing for and its details
    if(productName=="Wealth Plus"){
    	if(basicInvestingFor == 'Family' && risingStarBenefitOptedVal=="Yes"){
    		
    		$("#removeRisingStarWrapper").show();
    	}
    	else{
    		$("#removeRisingStarWrapper").hide();
    	} 
    }
    if(productName=="Wealth Rise Plus"){
    	if(basicInvestingFor == 'Family' && isLittleChampBenefitOptedVal=="Yes"){
    		console.log("Product Name :: " + productName);
    		$("#removeLittleChampWrapper").show();
    	}
    	else{
    		$("#removeLittleChampWrapper").hide();
    	} 
    }
 
    if(basicInvestingFor == 'Family'){
  		$('#myself').removeAttr('checked');
  		$('#family').attr('checked', 'checked');
  		$('#'+assuranceNameValidator).val(assuranceFullName);
  		$('#'+assuranceDobValidator).val(assuranceDob);
  		
  		var dateOfBirth= moment($("#"+dateOfBirthValidator).val(), 'DD/MM/YYYY').toDate();
  		var today = new Date();
  		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
  			if(age >=40 && productName!="GCAP"){
  			$('#investingFormFamily .radio_container').append('<li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="grandson" name="' + portletNamespace + 'assuredRelation" id="investForGrandSon" value="GrandSon" /><label class="grandSon" for="investForGrandSon">Grandson</label> </li>');
  			$('#investingFormFamily .radio_container').append('<li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="granddaughter" name="' + portletNamespace + 'assuredRelation" id="investForGrandDaughter" value="GrandDaughter" /><label class="grandDaughter" for="investForGrandDaughter">Granddaughter</label> </li>');
  		}
  		
  		if(assuredRelationValue == 'Son'){
  			$("#investForSon").attr('checked','ckecked');
  		}else if(assuredRelationValue == 'Daughter'){
  			$("#investForDaughter").attr('checked','checked');
  		}else if(assuredRelationValue == "GrandSon"){
  			$("#investForGrandSon").attr('checked','checked');
  		}else if(assuredRelationValue == "GrandDaughter"){
  			$("#investForGrandDaughter").attr('checked','checked');
  		}
  		
  		$("#investingFormFamily").show();
  		$("#assuranceNameForm").show();
  	}else{
  		$("#investingFormFamily").hide();
  		$("#assuranceNameForm").hide();
  	}
    
    /*  CODE FOR MINIMUM AGE IN DAYS */
	$.validator.addMethod("minDays", function(value, element, min) {
		var parts = value.split("/");
        var birthDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
		var today = new Date();
		
		if(birthDate > today){
			return false;
		}
		var days = Math.abs(birthDate-today) / (1000 * 3600 * 24);
	    return days >= min;
	}, "The age should be between 91 days to 17 years");
		
     // code for min and max age
	$.validator.addMethod("minAge", function(value, element, min) {
	    var today = moment(new Date(), 'DD/MM/YYYY').toDate();
	    
	    var birthDate = moment(value, 'DD/MM/YYYY').toDate();	
	    var age = today.getFullYear() - birthDate.getFullYear();
	    console.log(" age value --- "+age);
	    console.log("Min age value --- "+min);
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
	
	// code for fullname validator 
	$.validator.addMethod('fullName',function(value, element) {
	 return /^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value);
    },);
	// code for mobile number  validator 
	jQuery.validator.addMethod("mobileNumber",function(value, element) {
		return /^(?!1\d{9}|2\d{9}|3\d{9}|4\d{9}|(\d)\1{4})[5-9]\d{9}$/.test(value);
	},'Please Enter Valid Mobile Number');
	
	jQuery.validator.addMethod("customEmail",function(value, element) {
		return /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
				.test(value);
	},'');
	
		updateAssuredRelationRadioLabels();
	// defining validations rules and messages 
	var summaryFormRuleFields = new Object({}), summaryFormMessageFields = new Object({});
	summaryFormRuleFields[fullNameValidator] = {
			required : true,
			fullName : true
		};
	summaryFormRuleFields[dateOfBirthValidator] = {
			required : true,
			validDateFormat: true,
			minAge:18,
			maxAge:function() {
				if(productName == 'Wealth Plus' || productName == 'GCAP'){
					var maximumAge = 55;
					return maximumAge;
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					var maximumAge = 60;
					return maximumAge;
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star' || productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star'){
					var maximum = 65;
					return maximum;
				}
				else if(productCode == "40030"){
					var maximum = 50;
					return maximum;
				}
			}
		};
	summaryFormRuleFields[mobileNumberValidator] = {
			required : true,
			mobileNumber:true
		};
	summaryFormRuleFields[emailValidator] = {
			required : true,
			customEmail : true
		};
	summaryFormRuleFields[assuranceNameValidator] = {
 			required : true,
 			fullName : true
 		};
	summaryFormRuleFields[assuranceDobValidator] = {
 			required : true,
 			minDays: function(){
				if(productName == 'GCAP'){            								
					var minimumDays = 91;
					return minimumDays;
				}
			},
 			minAge:function(){
				if(productName != 'GCAP'){            								
					var minimumAge = 0;
					return minimumAge;
				}
			},
 			maxAge:17
 		};
     summaryFormMessageFields[assuranceNameValidator] = {
 			required : function(){
 				var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
 				if(relationValue == 'Son'){
					return Liferay.Language.get('enter-your-son-full-name');
				}
 				else if(relationValue == 'Daughter'){
 					return Liferay.Language.get('enter-your-daughter-full-name');
 				}
 				else if(relationValue == 'GrandSon'){
 					return Liferay.Language.get('enter-your-grand-son-full-name');
 				}
 				else if(relationValue == 'GrandDaughter'){
 					return Liferay.Language.get('enter-your-grand-daughter-full-name');
 				}
 			},
 			fullName : function(){
 				var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
 				if(relationValue == 'Son'){
					return Liferay.Language.get('enter-your-son-full-name');
				}
 				else if(relationValue == 'Daughter'){
 					return Liferay.Language.get('enter-your-daughter-full-name');
 				}
 				else if(relationValue == 'GrandSon'){
 					return Liferay.Language.get('enter-your-grand-son-full-name');
 				}
 				else if(relationValue == 'GrandDaughter'){
 					return Liferay.Language.get('enter-your-grand-daughter-full-name');
 				}
 			}
 		};
     summaryFormMessageFields[assuranceDobValidator] = {
  			required : function(){
  				var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
  				if(relationValue == 'Son'){
					return Liferay.Language.get('enter-your-son-date-Of-birth');
				}else if(relationValue == 'Daughter'){
 					return Liferay.Language.get('enter-your-daughter-date-Of-birth');
 				}
 				else if(relationValue == 'GrandSon'){
 					return Liferay.Language.get('enter-your-grand-son-date-of-birth');
 				}
 				else if(relationValue == 'GrandDaughter'){
 					return Liferay.Language.get('enter-your-grand-daughter-date-of-birth');
 				}
  			},
  			minDays:function (){
  				if(productName == 'GCAP'){
  					return Liferay.Language.get('age-between-91-days-to-17-years');
  				}
  			},
  			minAge:function (){
  				if(productName != 'GCAP'){
  					return Liferay.Language.get('age-should-be-between-0-to-17');
  				}
  			},
  			maxAge:function (){
  				if(productName == 'GCAP'){
  					return Liferay.Language.get('age-between-91-days-to-17-years');
  				}else{
  					return Liferay.Language.get('age-should-be-between-0-to-17');
  				}
  			}
  		};
	
     summaryFormMessageFields[emailValidator] = {
 			required : Liferay.Language.get('enter-a-valid-email-address'), 
 			customEmail : Liferay.Language.get('enter-a-valid-email-address')
 		};
	
	summaryFormMessageFields[fullNameValidator] = {
		required : Liferay.Language.get('enter-your-full-name'),
		fullName : Liferay.Language.get('enter-your-full-name')
	};
	
	summaryFormMessageFields[dateOfBirthValidator] = {
			required : Liferay.Language.get('enter-your-date-of-birth'),
			validDateFormat: 'Enter a valid date',
			minAge: function(){
				if(productName == 'Wealth Plus' || productName == 'GCAP'){            								
					return Liferay.Language.get('age-should-be-between-18-to-55');
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					return Liferay.Language.get('age-should-be-between-18-to-60');
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star' || productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star'){
					return Liferay.Language.get('age-should-be-between-18-to-65');
				}else if(productCode == "40030"){
					return Liferay.Language.get('age-should-be-between-18-to-50');
				}
			},
			maxAge : function(){
				if(productName == 'Wealth Plus' || productName == 'GCAP'){            								
					return Liferay.Language.get('age-should-be-between-18-to-55');
				}else if(productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'|| productName == 'Capital Secure Plus'){
					return Liferay.Language.get('age-should-be-between-18-to-60');
				}else if(productName == 'Active Income' || productName ==  'Flexi Savings Plan' || productName ==  'Guaranteed Savings Star' || productName ==  'Guaranteed Income STAR' || productName ==  'Premier Guaranteed Income' || productName ==  'Premier Guaranteed Star'){
					return Liferay.Language.get('age-should-be-between-18-to-65');
				}else if(productCode == "40030"){
					return Liferay.Language.get('age-should-be-between-18-to-50');
				}
			}
		}; 
	summaryFormMessageFields[mobileNumberValidator] = {
			required : Liferay.Language.get('please-enter-valid-mobile-number')
		};
	//handling form 
	$('#'+editSummaryFormValidator).validate({
		rules : summaryFormRuleFields,
		messages : summaryFormMessageFields,
		submitHandler : function(form) {
			form.submit();
		}
	});
	// Code for investing for whom
	var basicInvestingForValue= $("input[type=radio][name="+investingForValidator+"]:checked").val();
	$('#editBasicDetails').click(function(){
		$(this).data('clicked', true);
	    if(productName == 'Zindagi Protect Plus'){
		     $('#editBasicDetailsModal').modal("show");
		     $('#basicDetailSummaryFm').trigger("reset");
			 $('#'+dateOfBirthValidator+'-error').remove();
			 $('#'+fullNameValidator+'-error').remove();
			 $('#'+emailValidator+'-error').remove();
			 $('#'+mobileNumberValidator+'-error').remove();
	    }else{
	    	$('#editModal').modal("show");
		     $('#'+basicDetailsForm).trigger("reset");
			 $('#'+dateOfBirthValidator+'-error').remove();
			 $('#'+fullNameValidator+'-error').remove();
			 $('#'+emailValidator+'-error').remove();
			 $('#'+assuranceNameValidator+'-error').remove();
			 $('#'+assuranceDobValidator+'-error').remove();
			 $('#'+mobileNumberValidator+'-error').remove();
			 
			 if(basicInvestingForValue == 'Myself'){
					$("#investingFormFamily").hide();
					$("#assuranceNameForm").hide();
					$("#removeLittleChampWrapper").hide();
			 }else{
					$("#investingFormFamily").show();
					$("#assuranceNameForm").show();
					$("#removeLittleChampWrapper").show();
				}
	    }
	});
	// code for closing modal 
	$("#closeModal").click(function(){
        $("#editModal").modal("hide");
    });
	
	// code for sjb edit form in summary page
	$('#editBasicDetailsSjb').click(function(){
		
		$("input.formateAmount").each(function() {
			  let inputValue = $(this).val();
			  const numericValue = parseFloat(inputValue.replaceAll(",", ""));
			  if (!isNaN(numericValue)) {
				    const roundedValue = Math.round(numericValue);
				    $(this).val(roundedValue.toLocaleString("en-IN"));
				  } else {
				    $(this).val("");
				  }
			});
		
		$('#editModals').modal("show");
		/*$(this).data('clicked', true);
	    if(true){
	    $('#editModals').modal("show");
	    $('#'+basicDetailsForm).trigger("reset");
		 $('#'+dateOfBirthValidator+'-error').remove();
		 $('#'+fullNameValidator+'-error').remove();
		 $('#'+emailValidator+'-error').remove();
		 $('#'+assuranceNameValidator+'-error').remove();
		 $('#'+assuranceDobValidator+'-error').remove();
		 $('#'+mobileNumberValidator+'-error').remove();
		 
		 
	    }*/
	});
	// code for closing modal 
  $("#closeModal").click(function(){
        $("#abcd").modal("hide");
    });
	
	
	//Code for dob and grand son and grand daughter
	$('#'+dateOfBirthValidator).keyup(function(){
		var investingForValue= $('input[type=radio][name='+investingForValidator+']:checked').val();
		var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
		var dateOfBirth= moment($("#"+dateOfBirthValidator).val(), 'DD/MM/YYYY').toDate();
		
		var today = new Date();
		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
		console.log("InvestingFor -- "+investingForValue);
		if(age >=40 && investingForValue == 'Family' && productName!= "GCAP"){
			
			$("#investForGrandSon").remove()
			$('label[for=investForGrandSon]').remove(); 
			
			$("#investForGrandDaughter").remove()
			$('label[for=investForGrandDaughter]').remove(); 
			
			$(".additional-assured-relation-radio").remove();
			
			$('#investingFormFamily .radio_container').append('<li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="grandson" name="' + portletNamespace + 'assuredRelation" id="investForGrandSon" value="GrandSon" /><label class="grandSon" for="investForGrandSon">Grandson</label> </li> <li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="granddaughter" name="' + portletNamespace + 'assuredRelation" id="investForGrandDaughter" value="GrandDaughter" /><label class="grandDaughter" for="investForGrandDaughter">Granddaughter</label> </li>');
			
			$("input[type=radio][name="+assuredRelationValidator+"][value="+relationValue+"]").prop("checked", true);

		}else if(investingForValue == 'Family'){
			$("#investForGrandSon").remove()
			$('label[for=investForGrandSon]').remove(); 
			
			$("#investForGrandDaughter").remove()
			$('label[for=investForGrandDaughter]').remove();
			removeAdditionalRadioButtons();
			
			if(relationValue == "Son" || relationValue == "Daughter"){
				$("input[type=radio][name="+assuredRelationValidator+"][value="+relationValue+"]").prop("checked", true);
				
			}else if(relationValue == "GrandSon" || relationValue == "GrandDaughter"){
				setDefaultAssuredRelation();
				$("input[type=radio][name="+assuredRelationValidator+"][value=Son]").prop("checked", true);
			}
			
		}else {
			$("#investForGrandSon").remove()
			$('label[for=investForGrandSon]').remove(); 
			
			$("#investForGrandDaughter").remove()
			$('label[for=investForGrandDaughter]').remove();
			removeAdditionalRadioButtons();
			setDefaultAssuredRelation();
		}
		
	});
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
	function removeAdditionalRadioButtons(){
		$(".additional-assured-relation-radio").remove();
	}
	// code for on change event invesing for myself , family
	$('input[type=radio][name='+investingForValidator+']').change(function(){
	var investingForValue= $('input[type=radio][name='+investingForValidator+']:checked').val();
	
	if(productName=="Wealth Plus"){
		if(investingForValue == 'Family' && risingStarBenefitOptedVal=="Yes"){
			
			$("#removeRisingStarWrapper").show();
		}
		else{
			$("#removeRisingStarWrapper").hide();
		}
	}
	if(productName=="Wealth Rise Plus"){
		if(investingForValue == 'Family' && isLittleChampBenefitOptedVal=="Yes"){
			
			$("#removeLittleChampWrapper").show();
		}
		else{
			$("#removeLittleChampWrapper").hide();
		}
	}
	
	if(investingForValue == 'Family'){
		$("#investingFormFamily").show();
		$("#assuranceNameForm").show();
		
		var dateOfBirth= moment($("#"+dateOfBirthValidator).val(), 'DD/MM/YYYY').toDate();
		console.log("dateofbirth -- "+dateOfBirth);
		var today = new Date();
		var age = Math.floor((today-dateOfBirth) / (365.25 * 24 * 60 * 60 * 1000));
		if(age >=40 && productName!="GCAP"){
			
			$("#investForGrandSon").remove()
			$('label[for=investForGrandSon]').remove(); 
			
			$("#investForGrandDaughter").remove()
			$('label[for=investForGrandDaughter]').remove(); 
			$(".additional-assured-relation-radio").remove();
			
			$('#investingFormFamily .radio_container').append('<li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="grandson" name="' + portletNamespace + 'assuredRelation" id="investForGrandSon" value="GrandSon" /><label class="grandSon" for="investForGrandSon">Grandson</label> </li> <li class="btn form-btn address-radio additional-assured-relation-radio p-0"> <input type="radio" class="granddaughter" name="' + portletNamespace + 'assuredRelation" id="investForGrandDaughter" value="GrandDaughter" /><label class="grandDaughter" for="investForGrandDaughter">Granddaughter</label> </li>');

		}else {
			$("#investForGrandSon").remove()
			$('label[for=investForGrandSon]').remove(); 
			
			$("#investForGrandDaughter").remove()
			$('label[for=investForGrandDaughter]').remove(); 
		}
		$("#investingFormFamily").show();
		$("#assuranceNameForm").show();
	}else{
	
		$("#investingFormFamily").hide();
		$("#assuranceNameForm").hide();
		
		setDefaultAssuredRelation();
	}
});
	// Code on change of son and daughter 
	$("#investingFormFamily").on("change", 'input[type=radio][name='+assuredRelationValidator+']', function(event){
		var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
		$('#' +assuranceNameValidator +'-error').remove();
		$('#' +assuranceDobValidator + '-error').remove();
		$('#' +assuranceNameValidator).val('');
		$('#' +assuranceDobValidator).val('');
		updateAssuredRelationRadioLabels();
		/*if(relationValue == 'Daughter'){
		$('#assurancename').text("Daughter's Name");
		$('#assurancedob').text(Liferay.Language.get('daughter-dob'));
	}
		else if(relationValue == 'Son'){
	        $('#assurancename').text("Son's Name ");
	         $('#assurancedob').text(Liferay.Language.get('son-dob'));
	 }else if(relationValue == 'GrandSon'){
		        $('#assurancename').text("Grandson's Name ");
		         $('#assurancedob').text(Liferay.Language.get('grand-son-dob'));
		  
		    }else if(relationValue == 'GrandDaughter'){
				$('#assurancename').text("Granddaughter's Name ");
				$('#assurancedob').text(Liferay.Language.get('grand-daughter-dob'));
			}*/
	});
	function updateAssuredRelationRadioLabels(){
		var relationValue = $('input[type=radio][name='+assuredRelationValidator+']:checked').val();
		if(relationValue == 'Daughter'){
			$('#assurancename').text("Daughter's Name");
			$('#assurancedob').text(Liferay.Language.get('daughter-dob'));
		}
			else if(relationValue == 'Son'){
		        $('#assurancename').text("Son's Name ");
		         $('#assurancedob').text(Liferay.Language.get('son-dob'));
		 }else if(relationValue == 'GrandSon'){
			        $('#assurancename').text("Grandson's Name ");
			         $('#assurancedob').text(Liferay.Language.get('grand-son-dob'));
			  
			    }else if(relationValue == 'GrandDaughter'){
					$('#assurancename').text("Granddaughter's Name ");
					$('#assurancedob').text(Liferay.Language.get('grand-daughter-dob'));
				}
	}
	
	$('#editPlanDetails').click(function(){
		if(productName === 'Wealth Rise Plus'){
			window.location.href="/wealth-rise-plus/customize";
		}else if(productName === 'Wealth Plus'){
			window.location.href="/wealth-plus/customize";
		}else if(productName === 'Wealth Secure Plus'){
			window.location.href="/wealth-secure-plus/customize";
		}else if(productName === 'Capital Secure Plus'){
			window.location.href="/capital-secure-plus/customize";
		}else if(productName === 'Active Income'){
			window.location.href="/active-income/customize";
		}else if(productCode == "40030"){
			window.location.href="/wealth-gain/customize";
		}else if(productName == "Zindagi Protect Plus"){
			window.location.href="/zindagi-protect-plus/customize";
		}
		else{
			//Redirect to LMS page
			console.log("Redirecting to LMS page...");
		}
	});
	
	if(productName === 'Wealth Rise Plus' || productName === 'Wealth Plus' || productName === 'Wealth Secure Plus' || productName === 'Capital Secure Plus' || productCode == "40030"){
		$('#'+portletNamespace+'premiumBreakUpAnchor').click(function (){
				
			if ($("#breakupDiv").hasClass("none")) {
				    $("#breakupDiv").removeClass("none");
					$("#breakupDiv").show();
					
				} else {
					 $("#breakupDiv").removeClass("block");
					 $("#breakupDiv").addClass("none");
					$("#breakupDiv").hide();
					
				}
		});
	}
	
	$('#'+portletNamespace+'generatePolicyForm').on('submit', function(event) {
		console.log("summary-for::",productName);
		waitOnClick().then(() => {
            var req = new XMLHttpRequest();
    		var pdf_url = $(".pdf a").attr('href');
    		var filename = pdf_url.split("?")[0];
    		var filename = filename.substring(filename.lastIndexOf('/')+1);
    		  req.open("GET", pdf_url, true);
    		  req.responseType = "blob";
    		  req.onload = function (event) {
    		    var blob = req.response;
    		    console.log(blob.size);
    		    var link=document.createElement('a');
    		    link.href=window.URL.createObjectURL(blob);
    		    link.download=filename;
    		    link.click();
    		  };
    		  req.send();
        });  
    });
	
	$('#generatePolicyForm, #'+namespace+'generatePolicyForm').submit(function(event) {
		$(this).find('button[type="submit"]').prop('disabled',true);
		$(this).find('button[type="submit"]').text('Submitting...');
	});
	
});
		
function waitOnClick() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(); // resolve promise when the action is done
        }, 2000); // Simulating a 2-second delay
    });
}
