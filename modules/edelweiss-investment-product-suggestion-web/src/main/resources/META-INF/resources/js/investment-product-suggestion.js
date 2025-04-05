(function($, edelweissIPSPortlet) {
	
	function viewIPSCustomize(config){
		var namespace = config.namespace,
			customizeFormJEl = "#" + namespace + "customizeForm",
			investingForJEl = "#" + namespace + "investingFor",
			policyTermEl = namespace + "policyTerm",
			payForEl = namespace + "payFor",
			investmentAmountEl = namespace + "investmentAmount",
			policyTermJEl = "#" + policyTermEl,
			payForJEl = "#" + payForEl,
			paymentOptionEl = namespace + "paymentOption",
			paymentOptionJEl = "#" + paymentOptionEl,
			investmentAmountJEl =  "#" + investmentAmountEl,
			returnAmtValJEl = "#returnAmtVal",
			totalReturnsValueJEl = "#" + namespace + "totalReturnsValue",
			errorMessageModalJEl = "#errorMessageModal",
			totalReturnsJEl = "#" + namespace + "totalReturns",
			cookieLeadId = config.cookieLeadId,
			fullNameJEl = "#" + namespace + "fullName",
			dateOfBirthJEl = "#" + namespace + "dateOfBirth",
			emailJEl = "#" + namespace + "email",
			basicInvestingForEl = namespace + "basicInvestingFor",
			assuranceFullNameJEl = "#" + namespace + "assuranceFullName",
			assuranceDobJEl = "#" + namespace + "assuranceDob",
			genderEl = namespace + "gender",
			genderJEl = "#" + genderEl,
			mobileNumberJEl = "#" + namespace + "mobileNumber",
			assuredRelationEl = namespace + "assuredRelation",
			proceedSubmitBtnJEl = "#" + namespace + "proceedBtn",
			productCodeJEl = "#" + namespace + "productCode",
			familyModalJEl = "#" + namespace + "familyModal",
			investingForWrapperJEl = "#investingForWrapper",
			investingForGrandsonEl = namespace + "investingForGrandson",
			investingForGrandDaughterEl = namespace + "investingForGrandDaughter",
			familyRadioEl = namespace + "familyRadio",
			investingForFullNameEl = namespace + "investingForFullName",
			investingForDOBEl = namespace + "investingForDOB",
			investingForFullNameJEl = "#" + investingForFullNameEl,
			investingForDOBJEl = "#" + investingForDOBEl,
			investingForFullNameLableJEl = "#" + namespace + "investingForFullNameLable",
			investingForDOBLabelJEl = "#" + namespace + "investingForDOBLabel",
			saveFamilyDetailsURL = config.saveFamilyDetailsURL,
		 	saveFamilyBtnJEl = "#" + namespace + "saveFamilyBtn",
			familyDetailsFormJEl = "#" + namespace + "familyDetailsForm",
			updateLMSLeadURL = config.updateLMSLeadURL,
			fullNameEl = namespace + "fullName",
			dateOfBirthEl = namespace + "dateOfBirth",
			emailEl = namespace + "email",
			investmentObjectiveEl = namespace + "investmentObjective",
			riskAppetiteJEl = "#" + namespace + "riskAppetite",
			annualIncomeJEl = "#" + namespace + "annualIncome",
			productSuggestionRecommendedJEl = "#product-suggestion-recommended-products",
			scrollPopupMainWrapper = ".scroll-popupe-main-wrapper",
			popupInvestmentAmountJEl = "#popup-investment-amount",
			recommendedProductJEl = "#recommendedProduct_",
			modifyInputsProductJEl = "#modifyInputsProduct_",
			modifyInputOverlayJEl =".modify-input-overlay",
			buyNowLinkJEl = ".buy-now-link-",
			mobileNumberEl = namespace + "mobileNumber",
			productNameJEl = "#" + namespace + "productName",
			investmentObjectiveEl = namespace + "investmentObjective",
			familyModalCloseJEl = "#" + namespace + "familyModalClose",
			amtInWordsJEl = "#amt-in-word",
			errorMessageContainerJEl = "#errorMessageContainer",
			allowAlphaSpaceJEl = ".edelweiss-allow-aplha-space",
			saveFundDetailsURL = config.saveFundDetailsURL,
			saveCustomerInvestmentDataURL = config.saveCustomerInvestmentDataURL,
			deleteFamilyDetailsURL = config.deleteFamilyDetailsURL,
			customerInvestmentDetailsIdJEl = "#" + namespace + "customerInvestmentDetailsId",
			customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
			personalDetailsJEl = "#personal-details",
			productVIdJEl = "#" + namespace + "productVId",
			productCPIdJEl = "#" + namespace + "productCPId",
			productCIdJEl = "#" + namespace + "productCId",
			editBasicDetailsIconJEl = "#editBasicDetails",
			isInvestmentAmontUpdatedJEl = "#" + namespace + "isInvestmentAmontUpdated",
			assuredRelationVal = config.assuredRelationVal,
			wspPolicyTermPicklistData = JSON.parse(config.ipsPolicyTermPicklistData),
			wspPayForPicklistData = JSON.parse(config.ipsPayForPicklistData),
			productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
			defaultPolicyTerm = config.defaultPolicyTerm,
			defaultPayFor = config.defaultPayFor,
			loadFamilyDetailsURL = config.loadFamilyDetailsURL,
			productListURL = config.productListURL,
			productModificationURL = config.productModificationURL,
			productSuggestionReturnsArray = [],
			LANG_MESSAGES = config.LANG_MESSAGES,
			grandSonLabel = LANG_MESSAGES["grand-son"],
			grandDaughterLabel = LANG_MESSAGES["grand-daughter"],
			sonsFullNameLabel = LANG_MESSAGES["sons-full-name"],
			sonsDobLabel = LANG_MESSAGES["sons-dob"],
			daughtersFullNameLabel = LANG_MESSAGES["daughters-full-name"],
			daughtersDobLabel = LANG_MESSAGES["daughters-dob"],
			grandsonsFullNameLabel = LANG_MESSAGES["grandsons-full-name"],
			grandsonsDobLabel = LANG_MESSAGES["grandsons-dob"],
			grandDaughterFullNameLabel = LANG_MESSAGES["granddaughter-full-name"],
			grandDaughterDobLabel = LANG_MESSAGES["granddaughter-dob"],
			enterYourXFullNameErrorMsg = LANG_MESSAGES["enter-your-x-full-name"],
			enterYourXDateOfBirthErrorMsg = LANG_MESSAGES["enter-your-x-date-of-birth"],
			ageBetween0to17ErrorMsg = LANG_MESSAGES["age-between-0-to-17-years"],
			rupeeSignLabel =  LANG_MESSAGES["rupee-sign"],
			ageBetween18to60ErrorMsg = LANG_MESSAGES["age-between-18-to-60-years"],
			ageBetween0to15ErrorMsg = LANG_MESSAGES["age-between-0-to-15-years"];

		var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
		var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
	
		// Investment Objective Picklist Keys which will be stored in LR Object
		var growMoneyKey = "growMoney";
		var childEducationKey = "childEducation";
		var retirementKey = "retirement";
		var taxSavingKey = "taxSaving";
		
		//Actual Value Mapping Which Send in Product List API
		var investmentObjectiveData = {};
		investmentObjectiveData[growMoneyKey] = "2";
		investmentObjectiveData[childEducationKey] = "3";
		investmentObjectiveData[retirementKey] = "5";
		investmentObjectiveData[taxSavingKey] = "4";
		
		// Risk Appetite Picklist Keys which will be stored in LR Object
		var lowKey = "low";
		var mediumKey = "medium";
		var highKey = "high";
		
		// Risk Appetite Actual Value Mapping Which Send in Product List API
		var riskAppetiteData = {};
		riskAppetiteData[lowKey] = "1";
		riskAppetiteData[mediumKey] = "2";
		riskAppetiteData[highKey] = "3";
		
		// Annual Income Picklist Keys which will be stored in LR Object
		var lessThan5LacsKey = "lessThan5Lacs";
		var fiveLacTo799999Key = "5LacTo799999";
		var eightLacTo999999Key = "8LacTo999999";
		var tenLacTo1199999Key = "10LacTo1199999";
		var greaterThan12LacsKey = "greaterThan12Lacs";
		
		// Annual Income  Actual Value Mapping Which Send in Product List API
		var annualIncomeData = {};
		annualIncomeData[lessThan5LacsKey] = "499999";
		annualIncomeData[fiveLacTo799999Key] = "799999";
		annualIncomeData[eightLacTo999999Key] = "999999";
		annualIncomeData[tenLacTo1199999Key] = "1199999";
		annualIncomeData[greaterThan12LacsKey] = "1300000";
		
		/**
		 * Load Policy Terms
		 */	
		initPolicyTerm = function(){
			var wspPolicyTermKeys = Object.keys(wspPolicyTermPicklistData).map(key => Number(key));
			wspPolicyTermKeys.sort((num1, num2) => num1 - num2).reverse();
			
			var proposeAge = calculateAge($(dateOfBirthJEl).val());
			
			var wholeLifePT = 100 - proposeAge;
			$(policyTermJEl).append('<option value="' + wholeLifePT + '">' + wholeLifePT + '</option>');
			$.each(wspPolicyTermKeys, function( key, value ) {
				var selected = value == defaultPolicyTerm ? " selected" : "";
				$(policyTermJEl).append('<option value="' + value + '" ' + selected + '>' + value + '</option>');
			});
		};
		
		/**
		 * Load Pay For Based on Policy Term
		 */
		onPolicyTermChange = async function(){
			$(payForJEl).find("option").remove();
			
			updatePayForDropdown(this.value, true);
			
			invokeProductModificationAPI();
			
			saveCustomerInvestmentDataHandler();
		};
		
		
		updatePayForDropdown = function(selectedPolicyTerm, isPTUpdated) {
			var wspPayForKeys = Object.keys(wspPayForPicklistData).map(key => Number(key));
			wspPayForKeys.sort((num1, num2) => num1 - num2).reverse();
			
			var proposeAge = calculateAge($(dateOfBirthJEl).val());
			
			var wholeLifePT = 100 - proposeAge;
			var maxPayForPlusOne = wspPayForKeys[0] + 1;
			for (var i = maxPayForPlusOne; i <= wholeLifePT; i++) {
				wspPayForKeys.push(i);
		    }
			wspPayForKeys.sort((num1, num2) => num1 - num2).reverse();
			
			if(wholeLifePT == selectedPolicyTerm) {
				selectedPolicyTerm = wholeLifePT;
			}
		
			if(isPTUpdated) {
				defaultPayFor = selectedPolicyTerm;
			}
			
			$.each(wspPayForKeys, function( key, value ) {
				var selected = value == defaultPayFor ? " selected" : "";
				if(Number(selectedPolicyTerm) >= value) {
					$(payForJEl).append('<option value="' + value + '" ' + selected + '>' + value + '</option>');
				}
			});
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
				return 0;
			}
		}
		
		/**
		 * Update Investment Amount Based on Annual Income Selected
		 */
		updateMinimumInvestmentAmount = function() {
			var selectedAnnualIncomeVal = $(annualIncomeJEl).val();
			var isInvestmentAmontUpdatedVal = $(isInvestmentAmontUpdatedJEl).val();
			if(isInvestmentAmontUpdatedVal && isInvestmentAmontUpdatedVal != "true") {
				if(productInvestmentAmountRelsData && productInvestmentAmountRelsData.length > 0) {
					productInvestmentAmountRelsData.forEach(curProductInvestmentAmountRel => {
					    if(curProductInvestmentAmountRel.annualIncome.key == selectedAnnualIncomeVal) {
					    	$(investmentAmountJEl).val(curProductInvestmentAmountRel.minimumInvestmentAmount);
					    	
					    	populateAmountInWord();
							updateInvestmentAmountLocaleIN();
							
					    	return;
					    }
					});
				}
			}
		}
		
		/**
		 * Calling Generate BI Request on Change of Select and On blur of Input element
		 * Save Customer Investment Data
		 */
		onInvestmentAmountBlur = async function() {
			
			updateInvestmentAmountLocaleIN();
			
			updatePopupInvestmentAmount();
			
			invokeProductModificationAPI();
			
			saveCustomerInvestmentDataHandler();
		}
		
		
		onInvestingForChange = async function() {
			var investingFor = $(this).val();

			if(investingFor == "Family") {
				initFamilyModal();
				$(familyModalJEl).modal("show");
			} else {
				$(investingForFullNameJEl).val("");
				$(investingForDOBJEl).val("");
				
				$(assuranceFullNameJEl).val("");
				$(assuranceDobJEl).val("");
				
				$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
				$("input[type=radio][name='" + basicInvestingForEl + "'][value='Myself']").prop('checked', true);
				$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
				
				updateYourDetailsHeader();
				
				invokeProductListAPI();
				
				var deleteFamilyRequestData = {};
				deleteFamilyDetails(deleteFamilyRequestData);
				saveCustomerInvestmentDataHandler();
			}
		}
		
		onPayForChange = async function() {
			
			invokeProductModificationAPI();
			
			saveCustomerInvestmentDataHandler();
		}
		
		onPaymentOptionChange = async function() {
			
			invokeProductModificationAPI();
			
			saveCustomerInvestmentDataHandler();
		}
		
		/**
		 * Update Total Return On Change of Return Percentage (4% or 8%)
		 */
		totalReturnsValueChange = function() {
			updateSuggestedProductsReturnAmount(productSuggestionReturnsArray);
		};
		
		
		/**
		 * Save Investment Details and Policy Details for Customize Form Field Changes
		 */
		saveCustomerInvestmentDataHandler = async function() {
			if(cookieLeadId) {
				showLoader(); 
				await $.ajax({
					url:saveCustomerInvestmentDataURL,
					type:"post",
					data: $(customizeFormJEl).serialize(),
					dataType: "json",
					async: true,
					success: function(response) {
						if(response.status == "success") {
							$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId)
						}
						hideLoader();
					}
				});	
			}
		}
		
		resetFamilyModalData = function() {
			$(investingForFullNameJEl).val("");
			$(investingForDOBJEl).val("");
			
			$("input[type=radio][name='" + familyRadioEl + "']").prop("checked", false);
			$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", true);
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
					// Remove Grand Son and Grand Daughter Radio Button is exist for Propose Age < 40
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
		 * Init Family Modal Saved Data
		 */
		initFamilyModalData = function() {
			if(assuredRelationVal) {
				$("input[type=radio][name='" + familyRadioEl + "']").removeAttr("checked");
				$("input[type=radio][name='" + familyRadioEl + "'][value='" + assuredRelationVal + "']").prop('checked', true);
			}
		}
		
		initFamilyDetailsModalValidationRules = function() {
			var selectedFamilyRadioParam = $('input[type=radio][name=' + familyRadioEl + ']:checked').data("name") + "'s";
			var investingForFullNameDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", selectedFamilyRadioParam);
			var investingForDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", selectedFamilyRadioParam);

			//Remove Rules if exist
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
		 * Family Pop-up Radio Buttons Change event
		 */
		onFamilyRadioChange = function(event) {
			var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
			initFamilyDetailsModalValidationRules();
			updateFamilyInvestingForLabels(selectedFamilyRadioVal);
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
		 * Reset to My Self
		 */
		familyModalCloseHandler = function() {
			$(investingForJEl).val("Myself");
		}
		
		/**
		 * Show Congratulation Modal if form is valid
		 */
		saveFamilyDetailsHandler = function(e) {
			e.preventDefault();
			$(familyDetailsFormJEl).validate().form();
			if($(familyDetailsFormJEl).valid()) {
				
				var selectedFamilyRadioElement = $('input[type=radio][name=' + familyRadioEl + ']:checked');
				var familyDetailsData = {};
				familyDetailsData[namespace + "assuranceFullName"] = $(investingForFullNameJEl).val();
				familyDetailsData[namespace + "assuranceDob"] = $(investingForDOBJEl).val();
				familyDetailsData[namespace + "assuredRelation"] = selectedFamilyRadioElement.val();
				familyDetailsData[namespace + "assuredGender"] = selectedFamilyRadioElement.data("gender");
				familyDetailsData[namespace + "customerInvestmentDetailsId"] = $(customerInvestmentDetailsIdJEl).val();
				familyDetailsData[namespace + "customerFamilyDetailsId"] = $(customerFamilyDetailsIdJEl).val();
				
				$(assuranceFullNameJEl).val($(investingForFullNameJEl).val());
				$(assuranceDobJEl).val($(investingForDOBJEl).val());
				
				$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
				$("input[type=radio][name='" + basicInvestingForEl + "'][value='Family']").prop('checked', true);
				
				$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
				$("input[type=radio][name='" + assuredRelationEl + "'][value='" + selectedFamilyRadioElement.val() + "']").prop('checked', true);
				
				//Update Your Details Header after Saving Family Details
				updateYourDetailsHeader();
				
				saveFamilyDetails(familyDetailsData);
				
				$(familyModalJEl).modal("hide");
			} 
		}
		
		//Add/Update Family Details
		saveFamilyDetails = async function(familyDetailsData) {
			showLoader();
			try {
				await $.ajax({
					url:saveFamilyDetailsURL,
					type:"post",
					data: familyDetailsData,
					dataType: "json",
					async: true,
					success: function(response) {
						if(response.status == "success") {
							if(response.customerInvestmentDetailsId) {
								$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId);
							}
							if(response.customerFamilyDetailsId) {
								$(customerFamilyDetailsIdJEl).val(response.customerFamilyDetailsId);
							}
							
							invokeProductListAPI();
						}
						
					}
				});	
			} catch(e) {
				console.log(e);
			}
		}
		
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
		 * Invoke Product List API
		 */
		invokeProductListAPI = async function() {
			showLoader();
			var productListRequestDataJSON = {};
			var proposerDOB = $(dateOfBirthJEl).val();
			var proposerAgeVal = calculateAge(proposerDOB);
			var assuranceDob = $(assuranceDobJEl).val();
			var lifeAssuredAgeVal = calculateAge(assuranceDob);
			var investmentObjectiveVal = $('input[name="' + investmentObjectiveEl + '"]:checked').val();
			
			var gender = $("input[name='" + genderEl + "']:checked").val();
			var proposerGenderVal = "M";
			if(gender == "Female") {
				proposerGenderVal = "F";
			}
			var investingForValue = $("input[name='" + basicInvestingForEl + "']:checked").val();
			var sameProposerVal = "1";
			var lifeAssuredGenderVal = "M";
			if(investingForValue == "Family") {
				sameProposerVal = "0";
				var assuredRelVal = $("input[name='" + assuredRelationEl + "']:checked").val();
				if(assuredRelVal == "Daughter" || assuredRelVal == "GrandDaughter") {
					lifeAssuredGenderVal = "F";
				}
				var assuranceDob = $(assuranceDobJEl).val();
				lifeAssuredAgeVal = calculateAge(assuranceDob);
				productListRequestDataJSON["isSmoker"] = "0";
				productListRequestDataJSON["ChildAge"] = lifeAssuredAgeVal;
			} else {
				lifeAssuredGenderVal = proposerGenderVal;
				lifeAssuredAgeVal = proposerAgeVal;
			}
			
			productListRequestDataJSON["ProposerAge"] = proposerAgeVal;
			productListRequestDataJSON["ProposerGender"] = proposerGenderVal;
			productListRequestDataJSON["GoalId"] = investmentObjectiveData[investmentObjectiveVal];
			productListRequestDataJSON["LifeAssuredAge"] = lifeAssuredAgeVal;
			productListRequestDataJSON["LifeAssuredGender"] = lifeAssuredGenderVal;
			productListRequestDataJSON["AnnualIncome"] = annualIncomeData[$(annualIncomeJEl).val()];
			productListRequestDataJSON["InterestRate"] = "0.12579261656726115";
			productListRequestDataJSON["RiskId"] = riskAppetiteData[$(riskAppetiteJEl).val()];
			productListRequestDataJSON["SameProposer"] = sameProposerVal;
			
			var productListRequestData = {};
			productListRequestData[namespace + "productListRequestData"] = JSON.stringify(productListRequestDataJSON);
			
			try {
				const result = await $.ajax({
					url : productListURL,
					dataType : "json",
					data : productListRequestData,
					type : "POST",
					success: function(response) {
						//Init Empty Every Time while Calling Product List API
						productSuggestionReturnsArray = [];
						
						updatePopupInvestmentAmount();
						
						var prodDetailsJSONArray = response["ProdDetails"];
						if(prodDetailsJSONArray && prodDetailsJSONArray.length > 0) {
							
							$(productSuggestionRecommendedJEl).show();
							
							productSuggestionReturnsArray = renderProductReturnsData(prodDetailsJSONArray);
							//console.log(productSuggestionReturnsArray);
						}
						hideLoader();
						let assuranceFullName = $(assuranceFullNameJEl).val();
						if(assuranceDob && assuranceDob != '' && (assuranceFullName == '' || assuranceFullName == undefined || assuranceFullName == null)){
							$('#editModal').modal('show');
						}
					}
				});
			} catch(error) {
				hideLoader();
				$(errorMessageModalJEl).modal("show");
				return true;
			}
		}
		
		/**
		 * Render Product Return Data 4% and 8% by Product ID and update it to Suggestion Products
		 */
		renderProductReturnsData = function(prodDetailsJSONArray) {
			
			var selectedPercentage = $(totalReturnsValueJEl).val();
			prodDetailsJSONArray = sortJSONArrayByFieldName(prodDetailsJSONArray, "Sequence");
			
			prodDetailsJSONArray.forEach(curProductDetailsJSONObj => {
				if(curProductDetailsJSONObj) {
					try {
						var curProductReturnsData = {};
						//console.log(curProductDetailsJSONObj);
						var productId = curProductDetailsJSONObj["ProductId"];
						var recommendation = curProductDetailsJSONObj["Recommendation"];
						
						curProductReturnsData["productId"] = productId;
						curProductReturnsData["transactionid"] = curProductDetailsJSONObj["transactionid"];
						curProductReturnsData["Recommendation"] = curProductDetailsJSONObj["Recommendation"];
						curProductReturnsData["Sequence"] = curProductDetailsJSONObj["Sequence"];
						
						var percentReturnData = {};
						
						var inputValidationStatus = curProductDetailsJSONObj["InputValidationStatus"];
						if(inputValidationStatus && inputValidationStatus.length > 0 && inputValidationStatus[0]["ErrorMessage"] && inputValidationStatus[0]["ErrorMessage"].length > 0) {
							curProductReturnsData["percentReturnData"] = percentReturnData;
							$(modifyInputsProductJEl +  productId).show();
							
							disableBuyNowLink(productId);
						} else {
							//console.log("Product ID :" + productId + ", Sequence : " + curProductDetailsJSONObj["Sequence"]);
						
							switch (productId) {
						    	case 40031:
						    	case 40023:
						    	//case 40030: NOT PART OF Phase 1 Release
						    	//case 40009: NOT PART OF Phase 1 Release
						    		//Wealth Secure Plus, Wealth Plus, Wealth Gain+ and Weath Ultima
						    		var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
									var biJSON2 = JSON.parse(curProductDetailsJSONObj["BIJson2"]);
									//console.log(biJSON);
									//console.log(biJSON2);
									
									percentReturnData["4"] = biJSON[biJSON.length - 1]["FINAL_FV"];
									percentReturnData["8"] = biJSON2[biJSON2.length - 1]["FINAL_FV"];
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
						    		break;
						    	/*
						    	case 40007:
						    		//GCAP : NOT PART OF Phase 1 Release
									var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
									var returnAmount = 0;
			                        var curProductPT = curProductDetailsJSONObj.InputValidationStatus[0].PT;
									biJSON.forEach(function(curBIJSON) {
										if(parseInt(curBIJSON["POLICYYEAR"]) >= curProductPT) {
											returnAmount += curBIJSON["TOTAL_SB_G"];
										} 
									});
									console.log(biJSON);
									percentReturnData["4"] = returnAmount;
									percentReturnData["8"] = returnAmount;
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
						    		break;
						    	*/
						    	case 40035:
						    		//Life Active Income
									var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
									var fourPerReturnAmt = 0;
									var eightPerReturnAmt = 0;
									
									biJSON.forEach(function(curBIJSON) {
										fourPerReturnAmt += curBIJSON["SB_G"] + curBIJSON["CASH_BONUS_BS_1"] + curBIJSON["TOTAL_MB_G_BS_1"];
										eightPerReturnAmt += curBIJSON["SB_G"] + curBIJSON["CASH_BONUS_BS_2"] + curBIJSON["TOTAL_MB_G_BS_2"];
									});
									
									percentReturnData["4"] = fourPerReturnAmt;
									percentReturnData["8"] = eightPerReturnAmt;
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
									break;
						    	/* NOT PART OF Phase 1 Release
						    	case 40003:
						    		//Life Income Builder
						    		var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
									var returnAmount = biJSON[biJSON.length - 1]["INCOME_DISPLAY"];
									
									percentReturnData["4"] = returnAmount;
									percentReturnData["8"] = returnAmount;
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
									break;
								*/
						    	case 40048:
						    		// Premier Guaranteed Income
						    		var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
						    		var returnAmount = 0;
						    		biJSON.forEach(function(curBIJSON) {
						    			returnAmount += curBIJSON["INCOME_BEN_PAY_OUT"] + curBIJSON["MATURITY_BEN"];
				                    });
						    		
						    		percentReturnData["4"] = returnAmount;
									percentReturnData["8"] = returnAmount;
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
									break;
									
						    	case 40055:
						    		// Premier Guaranteed Star
						    		var biJSON = JSON.parse(curProductDetailsJSONObj["BIJson"]);
						    		var returnAmount = 0;
						    		biJSON.forEach(function(curBIJSON) {
						    			returnAmount += curBIJSON["INCOME_BEN_PAY_OUT"] + curBIJSON["MATURITY_BEN"];
				                    });
						    		
						    		percentReturnData["4"] = returnAmount;
									percentReturnData["8"] = returnAmount;
									
									curProductReturnsData["percentReturnData"] = percentReturnData;
									productSuggestionReturnsArray.push(curProductReturnsData);
									break;
						    }
							
							enableBuyNowLink(productId);
							
							updateProductSuggestedPrice(percentReturnData, productId);
							
							updateRecommendationTag(recommendation, productId);
							
							$(".suggested-product-box-" + productId).show();
						}
					} catch(e) {
						console.log(e);
					}
				}
			});
			
			return productSuggestionReturnsArray;
		}
		
		/**
		 * Update Return Amount to Suggested Products By Product ID
		 */
		updateSuggestedProductsReturnAmount = function(productSuggestionReturnsArray) {
			var selectedPercentage = $(totalReturnsValueJEl).val();
			if(productSuggestionReturnsArray && productSuggestionReturnsArray.length > 0 ) {
				
				productSuggestionReturnsArray.forEach(curProductSuggestionReturnsObj => {
					var productId = curProductSuggestionReturnsObj["productId"];
					var percentReturnData = curProductSuggestionReturnsObj["percentReturnData"];
					var recommendation = curProductSuggestionReturnsObj["Recommendation"];
					var failedCount = curProductSuggestionReturnsObj["FailedCount"];

					updateProductSuggestedPrice(percentReturnData, productId);
					
					updateRecommendationTag(recommendation, productId);
				});
			}
		}
		
		updateRecommendationTag = function(recommendation, productId) {
			if(recommendation == "True") {
				$(recommendedProductJEl + productId).show();
			} else {
				$(recommendedProductJEl + productId).hide();
			}
		}
		
		/**
		 * Updating Return Amount to Suggested Product List Box
		 */
		updateProductSuggestedPrice = function(productPercentReturnData, productId) {
			var selectedPercentage = $(totalReturnsValueJEl).val();
			var productElements = $(productSuggestionRecommendedJEl).find("[data-product-code='" + productId + "']");
			var amountWithRupee = rupeeSignLabel + " " + Math.round(productPercentReturnData[selectedPercentage]).toLocaleString("en-IN");
			if(productElements.length > 0) {
				productElements.html(amountWithRupee);
			} 
			var popupProductElements = $(scrollPopupMainWrapper).find("[data-product-code='" + productId + "']");
			if(popupProductElements.length > 0) {
				popupProductElements.html(amountWithRupee);
			} 
		}
		
		/**
		 * Show Product Suggestions Card
		 */
		showProductSuggestionsCards = function() {
			$(productSuggestionRecommendedJEl).show();
		}
		
		/**
		 * Update Pop-up Investment Amount
		 */
		updatePopupInvestmentAmount = function() {
			$(popupInvestmentAmountJEl).html(rupeeSignLabel + " " + $(investmentAmountJEl).val());
		}
		
		disableBuyNowLink = function(productId) {
			$(buyNowLinkJEl + productId).addClass("disabled-btn-hide");
		}
		
		enableBuyNowLink = function(productId) {
			$(buyNowLinkJEl + productId).removeClass("disabled-btn-hide");
		}
		
		/**
		 * Invoke Product List API
		 */
		invokeProductModificationAPI = async function() {
			showLoader();
			var productModificationRequestData = {};
			productModificationRequestData[namespace + "productModificationRequestData"] = JSON.stringify(getProductModificationAPIRequestData());
			
			try {
				const result = await $.ajax({
					url : productModificationURL,
					dataType : "json",
					data : productModificationRequestData,
					type : "POST",
					success: function(response) {
						//Init Empty Every Time while Calling Product Modification API
						productSuggestionReturnsArray = [];
						
						updatePopupInvestmentAmount();
						
						//console.log("Product Modification API Response");
						var prodDetailsJSONArray = response["ProdDetails"];
						if(prodDetailsJSONArray && prodDetailsJSONArray.length > 0) {
							productSuggestionReturnsArray = renderProductReturnsData(prodDetailsJSONArray);
							//console.log(productSuggestionReturnsArray);
						}
						
						hideLoader();
					}
				});
			} catch(error) {
				hideLoader();
				$(errorMessageModalJEl).modal("show");
				return true;
			}
		}

		/**
		 * Fetch Transaction Ids of First 3 Products Order by Sequence in ASC
		 */
		getTransactionIds = function() {
			var counter = 0;
			var transactionIdArray = [];
			productSuggestionReturnsArray.forEach(curProductSuggestionReturnsObj => {
				if(counter < 3) {
					counter++;
					//console.log("Sequence : " + curProductSuggestionReturnsObj["Sequence"]);
					transactionIdArray.push(curProductSuggestionReturnsObj["transactionid"]);
				}
			});
			return transactionIdArray.join(",");
		}
		
		/**
		 * Generate Product Modification API Request DATA
		 */
		getProductModificationAPIRequestData = function() {
			var productModificationRequestData = {};
			var apiInputJSON = {};
			
			apiInputJSON["formInputs"] = getProductModificationFormInputs();
			apiInputJSON["funds"] = [];
			apiInputJSON["riders"] = [];
			apiInputJSON["inputOptions"] = [];
			apiInputJSON["inputPartialWithdrawal"] = [];
			productModificationRequestData["TransactionIds"] = getTransactionIds();
			productModificationRequestData["ApiInput"] = apiInputJSON;
			
			//console.log(productModificationRequestData);
			
			return productModificationRequestData;
		}
		
		/**
		 * Generate Product Modification API Form Inputs
		 */
		getProductModificationFormInputs = function() {
			var proposerName = $(fullNameJEl).val();
			var proposerDOB = $(dateOfBirthJEl).val();
			var proposerAgeVal = calculateAge(proposerDOB);
			
			var dateOfBirthFormatted = moment(proposerDOB, "DD/MM/YYYY");
			var proposerDOBFormattedVal = moment(dateOfBirthFormatted).format("DD MMM YYYY");
			
			var gender = $("input[name='" + genderEl + "']:checked").val();
			var proposerGenderVal = "M";
			if(gender == "Female") {
				proposerGenderVal = "F";
			}

			var investingForValue = $("input[name='" + basicInvestingForEl + "']:checked").val();
			
			var sameProposerVal = "True";
			var liNameVal = proposerName;
			var liGenderVal = proposerGenderVal;
			var liEntryAgeVal = proposerAgeVal;
			var liDOBFormattedVal = proposerDOBFormattedVal;
			
			if(investingForValue == "Family") {
				sameProposerVal = "False";
				var assuredRelVal = $("input[name='" + assuredRelationEl + "']:checked").val();
				if(assuredRelVal == "Daughter" || assuredRelVal == "GrandDaughter") {
					liGenderVal = "F";
				} else {
					liGenderVal = "M";
				}
				liNameVal = $(assuranceFullNameJEl).val();
				var assuranceDob = $(assuranceDobJEl).val();
				liEntryAgeVal = calculateAge(assuranceDob);
			
				var liDOBFormatted = moment(assuranceDob, "DD/MM/YYYY");
				liDOBFormattedVal = moment(liDOBFormatted).format('DD MMM YYYY');
			} 
			
			var paymentOptionVal = $(paymentOptionJEl).val();
			
			var investmentAmount = Number(getInvestmentAmount());
			var annualPremium = investmentAmount;
			if(paymentOptionVal == "1") {
				annualPremium = investmentAmount;
			} else if(paymentOptionVal == "2") {
				annualPremium = investmentAmount * 2;
			} else if(paymentOptionVal == "3") {
				annualPremium = investmentAmount * 4;
			} else if(paymentOptionVal == "4") {
				annualPremium = investmentAmount * 12;
			}
			
			var pptVal = $(payForJEl).val();
			var ptVal = $(policyTermJEl).val();
			
			var formInputs = [];
			formInputs.push(getProductModificationFormInput("@INPUT_MODE", paymentOptionVal));
			formInputs.push(getProductModificationFormInput("@PR_PT", ptVal));
			formInputs.push(getProductModificationFormInput("@PR_PPT", pptVal));
			formInputs.push(getProductModificationFormInput("@PROPOSER_NAME", proposerName));
			formInputs.push(getProductModificationFormInput("@PROPOSER_AGE", proposerAgeVal));
			formInputs.push(getProductModificationFormInput("@PROPOSER_DOB", proposerDOBFormattedVal));
			formInputs.push(getProductModificationFormInput("@PROPOSER_GENDER", proposerGenderVal));
			formInputs.push(getProductModificationFormInput("@LI_NAME", liNameVal));
			formInputs.push(getProductModificationFormInput("@LI_STATE", "0"));
			formInputs.push(getProductModificationFormInput("@LI_GENDER", liGenderVal));
			formInputs.push(getProductModificationFormInput("@LI_ENTRY_AGE", liEntryAgeVal));
			formInputs.push(getProductModificationFormInput("@LI_DOB", liDOBFormattedVal));
			formInputs.push(getProductModificationFormInput("@SameProposer", sameProposerVal));
			formInputs.push(getProductModificationFormInput("@PR_ANNPREM", annualPremium));
			
			return formInputs;
		}
		
		
		/**
		 * Generate Product Modification API Form Input Individual
		 */
		getProductModificationFormInput = function(fieldKey, fieldValue) {
			return {
				"key": fieldKey,
				"value": fieldValue
			}
		}
		
		/**
		 * Sort JSON Array By Field Name with Type Interger
		 */
		sortJSONArrayByFieldName = function(jsonArray, fieldName) {
			var sortedJSONArray = jsonArray.sort((curJSONObj1, curJSONObj2) => {
				if (curJSONObj1[fieldName] < curJSONObj2[fieldName]) {
					return -1;
				}
			});
			return sortedJSONArray;
		}
	
		
		/**
		 * Update LMS Lead
		 */
		updateLMSLead = function() {
			var proposerGender = $("input[type=radio][name="+genderEl+"]:checked").val();
			var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
			var paymentFrequencyDisplayName = $(paymentOptionJEl).find(':selected').data("name");
			var investingForValue = $('input[type=radio][name=' + basicInvestingForEl + ']:checked').val();
			var policyTerm =  $(policyTermJEl).val();
			
			var updateLMSLeadData = {};
			updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "Y";
			if(investingForValue == "Family") {
				updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "N";
			} 
			
			var genderVal = "M";
			if(proposerGender == "Female") {
				genderVal = "F";
			}
			updateLMSLeadData[namespace + "customerEnquiryId"] = $("#" + namespace + "customerEnquiryId").val();
			updateLMSLeadData[namespace + "ChildName"] = $(assuranceFullNameJEl).val();
			updateLMSLeadData[namespace + "ChildDob"] = $(assuranceDobJEl).val();
			updateLMSLeadData[genderEl] = genderVal;
			updateLMSLeadData[fullNameEl] = $(fullNameJEl).val();
			updateLMSLeadData[dateOfBirthEl] = $(dateOfBirthJEl).val();
			updateLMSLeadData[emailEl] = $(emailJEl).val();
			updateLMSLeadData[mobileNumberEl] = $(mobileNumberJEl).val();
			updateLMSLeadData[namespace + "SumAssured"] = "0";
			updateLMSLeadData[namespace + "Product_Code"] = "";
			updateLMSLeadData[namespace + "TotalPremiumAmount"] = "";
			updateLMSLeadData[namespace + "PolicyTerm"] = policyTerm;
			updateLMSLeadData[namespace + "PremiumPaymentTerm"] = $(payForJEl).val();
			updateLMSLeadData[namespace + "PremiumPaymentFrequency"] = paymentFrequencyDisplayName;
			updateLMSLeadData[namespace + "vId"] = $(productVIdJEl).val();
			updateLMSLeadData[namespace + "cpId"] = $(productCPIdJEl).val();
			updateLMSLeadData[namespace + "cId"] = $(productCIdJEl).val();
			
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
			}
			updateLMSLeadData[namespace + "ProposerLARelation"] = proposerLARelation;
			
			$.ajax({
				url:updateLMSLeadURL,
				type:"post",
				data: updateLMSLeadData,
				dataType: "json",
				async: true,
				success: function(response) {
					
				}
			});	
		}
		
		/**
		 * Delete Family Details If any
		 */
		deleteFamilyDetails = function(deleteFamilyRequestData) {
			$.ajax({
				url:deleteFamilyDetailsURL,
				type:"post",
				dataType: "json",
				data : deleteFamilyRequestData,
				success: function(response) {
					if(response.status == "success") {
						$(customerFamilyDetailsIdJEl).val("");
					}
				}
			});	
		}
		
		/**
		 * Fetch Investment Amount without Comma
		 */
		getInvestmentAmount = function() {
			var curInvestmentAmount = $(investmentAmountJEl).val();
			curInvestmentAmount = curInvestmentAmount.replace(/,/g, "");
			return curInvestmentAmount;
		}
		/**
		 * Update Investment Amount field to Locale IN String
		 */
		updateInvestmentAmountLocaleIN = function() {
			var curInvestmentAmount = Number(getInvestmentAmount());
			var localeINInvestmentAmount = curInvestmentAmount.toLocaleString("en-IN");
			$(investmentAmountJEl).val(localeINInvestmentAmount);
		}
		
		/**
		 * Polulate Amount in Words
		 */
		populateAmountInWord = function() {
			var curInvestmentAmount = getInvestmentAmount();
			var amountInWords = getAmountInWords(curInvestmentAmount);
			$(amtInWordsJEl).html(rupeeSignLabel + "&nbsp;" + amountInWords);
		}
		
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
		
		allowOnlyNumbersHandler = function(event) {
			 this.value = this.value.replace(/[^0-9]/g, '');
		}
		
		allowOnlyAlphaAndSpaceHandler = function(event) {
			 this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');
		}
		
		initIPSProductListCards = function() {
			$(modifyInputOverlayJEl).hide();
			$(".product-main-wrapper").hide();
			$(".scroll-popupe-main-wrapper .scroll-box").hide();
		}
		
		/**
		 * Product Buy Now Click Handler
		 * Save Customer Investment Data and Redirect to Product Buy Now Link
		 */
		buyNowHandler = async function(e) {
			e.preventDefault();
			showLoader();
			var productBuyNowLink = $(this).attr("href");
			await saveCustomerInvestmentDataHandler();
			location.href = productBuyNowLink;
		}
		
		/**
		 * Initializing JQuery Events
		 */
		initEvents = function(){
			$(policyTermJEl).on("change", onPolicyTermChange);
			
			$(investmentAmountJEl).blur(onInvestmentAmountBlur);
			$(investmentAmountJEl).on("keyup", populateAmountInWord);
			$(investmentAmountJEl).on("input", allowOnlyNumbersHandler);
			$(investmentAmountJEl).on("input", allowOnlyNumbersHandler);
			$(allowAlphaSpaceJEl).on("input", allowOnlyAlphaAndSpaceHandler);
			
			$(".product-buy-now-link").click(buyNowHandler);

			$(investingForJEl).on("change", onInvestingForChange);
			$(payForJEl).on("change", onPayForChange);
			$(paymentOptionJEl).on("change", onPaymentOptionChange);
			
			$(totalReturnsValueJEl).on("change", totalReturnsValueChange);
			
			$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
			
			$(familyDetailsFormJEl).submit(saveFamilyDetailsHandler);
			$(familyModalCloseJEl).click(familyModalCloseHandler);
		};
		
		initDefaultProductValues = function() {
			if(defaultPayFor) {
				updatePayForDropdown($(policyTermJEl).val(), false);
				// Remove All Default Select Option which has blank value
				$(customizeFormJEl + " select option").filter(function() {
			        return !this.value || $.trim(this.value).length == 0 || $.trim(this.text).length == 0;
				}).remove();
				return true;
			}
			return false;
		}
	
		init = function(){
			initEvents();
			initPolicyTerm();
			
			initIPSProductListCards();
			
			var hasDefaultProdcutVal = initDefaultProductValues();
			
			populateAmountInWord();
			
			updateInvestmentAmountLocaleIN();
			
			initFamilyModal();
			initFamilyModalData();
			initFamilyModalFormValidation();
			
			updateMinimumInvestmentAmount();
			
			if(cookieLeadId) {
				$(editBasicDetailsIconJEl).show();
				invokeProductListAPI();
				updateLMSLead();
			} else {
				$(editBasicDetailsIconJEl).hide();
			}
			
			//Mask Field with DD/MM/YYYY format
			$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
		};
		
		init();
	};
	
	showLoader = function(){
		$("#preloader").addClass("preloader");
	}

	hideLoader = function(){
		$("#preloader").removeClass("preloader");
	}
	
	edelweissIPSPortlet.viewIPSCustomize = viewIPSCustomize;
})($, (window.edelweissIPSPortlet = window.edelweissIPSPortlet || {}));