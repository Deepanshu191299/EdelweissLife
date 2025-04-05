var pgiRelFieldValues;
var totalReturnsAmountRef;
(function($, edelweissProductCustomizePortlet) {
	
	function viewProductCustomize(config){
		var namespace = config.namespace,
		
		selectedRidersList = new Map(),
		
		customizeFormJEl = config.customizeFormJEl,
		policyOptionJEl=config.policyOptionJEl,	
		policyTermJEl=config.policyTermJEl ,
		policyForJEl=config.policyForJEl ,
		dateOfBirthJEl=config.dateOfBirthJEl,
		genderJEl=config.genderJEl,
		emailJEl=config.emailJEl,
		mobileNumberJEl=config.mobileNumberJEl,
		fullNameJEl=config.fullNameJEl,
		fullNameEl = namespace + "fullName",
		dateOfBirthEl = namespace + "dateOfBirth",
		emailEl = namespace + "email",
		mobileNumberEl = namespace + "mobileNumber",
		customerInvestmentDetailsIdJEl = "#" + namespace + "customerInvestmentDetailsId",
		customerPolicyDetailsIdJEl = "#" + namespace + "customerPolicyDetailsId",
		customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
		totalMaturityAmountJEl = "#" + namespace + "totalMaturityAmount",
		projectedReturnsDataJEl = "#" + namespace + "projectedReturnsData",
		incomeOptionJEl = "#" + namespace + "incomeOption",
		personalDetailsJEl = "#personal-details",
		amtInWordsJEl = "#amt-in-word",
		guarantedIncomeJEL = "#"+namespace+"guarantedIncome",
		maturityAgeJEl = "#"+namespace+"maturityAge",
		selectedRiderPrefixEl = namespace + "selectedRider_",
		selectedRiderPrefixJEl = "#" + selectedRiderPrefixEl,
		riderPremiumAmountPrefixEl = namespace + "riderPremiumAmount_",
		riderPremiumAmountPrefixJEl = "#" + riderPremiumAmountPrefixEl,
		payorWaiverBenefitOptionJEl = "#" + namespace + "payorWaiverBenefitOption",
		totalReturnsValueJEl = "#"+namespace+"totalReturnsValue",
		planMaturityBenefitsBreakupJEl = "#planMaturityBenefitsBreakup",
		planMaturityBenefitsBreakupTableBodyJEl = "#planMaturityBenefitsBreakupTableBody",
		premiumAmountJEl="#"+namespace+"premiumAmount",
		allowAlphaSpaceJEl = ".edelweiss-allow-aplha-space",
		familyRadioEl = config.familyRadioEl,
		biResponseJSON={},
		investingForFullNameEl = namespace + "investingForFullName",
		investingForDOBEl = namespace + "investingForDOB",
		investingForFullNameJEl = "#" + investingForFullNameEl,
		investingForDOBJEl = "#" + investingForDOBEl,
		assuranceFullNameJEl=config.assuranceFullNameJEl,
		assuranceDobJEl=config.assuranceDobJEl,
		investmentAmountJEl=config.investmentAmountJEl,
		productCodeJEl=config.productCodeJEl,
		paymentOptionJEl=config.paymentOptionJEl,
		familyModalJEl = config.familyModalJEl,
	 	familyDetailsFormJEl = config.familyDetailsFormJEl,
	 	investingForVal = config.investingForVal,
	 	investingForGrandsonEl = config.investingForGrandsonEl,
	 	investingForGrandDaughterEl = config.investingForGrandDaughterEl,
	 	familyRadioEl = config.familyRadioEl,
	 	investingForWrapperJEl = config.investingForWrapperJEl,
	 	assuredRelationVal = config.assuredRelationVal,
	 	investingForFullNameLableJEl = config.investingForFullNameLableJEl,
	 	investingForDOBLabelJEl = config.investingForDOBLabelJEl,
	 	investmentObjectiveEl = namespace + "investmentObjective",
	 	deleteFamilyDetailsURL = config.deleteFamilyDetailsURL,
	 	customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
	 	payForJEl = "#"+ namespace +"policyTerm",
	 	investingForJEl = config.investingForJEl,
	 	proceedSubmitBtnJEl = config.proceedSubmitBtnJEl,
	 	illustrationURLJEl = config.illustrationURLJEl,
	 	quotationIdJEl = config.quotationIdJEl,
	 	updateLMSLeadURL = config.updateLMSLeadURL,
	 	saveCustomerInvestmentDataURL = config.saveCustomerInvestmentDataURL,
	 	errorMessageModalJEl = config.errorMessageModalJEl,
	 	defaultPolicyFor = config.defaultPolicyFor,
	 	totalReturnsAmount={},
	 	productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
		familyIncomeBenefitJEl=config.familyIncomeBenefitJEl,
		incomePayoutFrequencyJEl=config.incomePayoutFrequencyJEl,
		cookieLeadId = config.cookieLeadId,
		edelweissGenerateBIURL = config.edelweissGenerateBIURL,
		basicInvestingForEl=config.basicInvestingForEl,
		assuredRelationEl=config.assuredRelationEl,
		genderEl=config.genderEl,
		policyOptionEl=config.policyOptionEl,	
		policyTermEl=config.policyTermEl ,
		policyForEl=config.policyForEl ,
		getIncomeForEl=config.getIncomeForEl,
		
		pgiRelFieldValues=config.pgiRelFieldValues,
		productMetaData=config.productMetaData;
		
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
		rupeeSignLabel =  LANG_MESSAGES["rupee-sign"];
		
		totalReturnsAmountRef = totalReturnsAmount;
		
		var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
		var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
		
		/**
		 * render Payfor value based on policy option value
		 */		
		
			updatePGIRelFieldValue = function(){
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var age = calculateAge(dateOfBirthInputVal);
				var payForList = new Map();
			    var payFordiv=document.getElementById(policyForEl);
			    payFordiv.innerHTML="";
			    $.each(pgiRelFieldValues,function(option, key){
			   
			    var selected = key == defaultPolicyFor ? "selected":"";
			    const payForOptionElement = document.createElement("option");
					payForOptionElement.setAttribute("value",option);
					if(option == defaultPolicyFor){
						payForOptionElement.setAttribute("selected",selected );
					}
					
					payForOptionElement.appendChild(document.createTextNode(key +" Years" ));
			payFordiv.appendChild(payForOptionElement);
			})
			
		  }
			/**
			 * Save Investment Details and Policy Details for Customize Form Field Changes
			 */
			saveCustomerInvestmentDataHanlder = function() {
				showLoader(); 
				$.ajax({
					url:saveCustomerInvestmentDataURL,
					type:"post",
					data: $(customizeFormJEl).serialize(),
					dataType: "json",
					async: true,
					success: function(response) {
						if(response.status == "success") {
							$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId)
							$(customerPolicyDetailsIdJEl).val(response.customerPolicyDetailsId);
						}
						hideLoader();

					}
				});	
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
			
			allowOnlyNumbersHandler = function(event) {
				 this.value = this.value.replace(/[^0-9]/g, '');
			}
			
			allowOnlyAlphaAndSpaceHandler = function(event) {
				 this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');
			}
			
			/**
			 * Updates the Minimum Investment Amount By Payment Frequency, Policy Option as Configured in Object
			 */
			updateMinimumInvestmentAmount = function() {
				
				var paymentOptionVal = $(paymentOptionJEl).val();
				
				var currentInvestmentAmount = Number(getInvestmentAmount());
				
				if(productInvestmentAmountRelsData && productInvestmentAmountRelsData.length > 0) {
					productInvestmentAmountRelsData.forEach(curProductInvestmentAmountRel => {
						var curMinimumInvestmentAmount = Number(curProductInvestmentAmountRel.minimumInvestmentAmount);
					    if(curProductInvestmentAmountRel.paymentOption.key == paymentOptionVal && currentInvestmentAmount < curMinimumInvestmentAmount) {
					    	$(investmentAmountJEl).val(curProductInvestmentAmountRel.minimumInvestmentAmount);
					    	return;
					    }
					});
					populateAmountInWord();
					updateInvestmentAmountLocaleIN();
				} else {
					updateInvestmentAmountLocaleIN();
				}
			}
			
			/**
			 * Calling Generate BI Request on Change of Select and On blur of Input element
			 * Save Customer Investment Data
			 */
			onInvestmentAmountBlur = async function() {
				updateMinimumInvestmentAmount();
				var hasValidationErrors = await generateBIRequest();
				if(!hasValidationErrors) {
					saveCustomerInvestmentDataHanlder();
				}
			}
			
			/**
			 * Initializing JQuery Events
			 */
			initEvents = function(){
				
				$(totalReturnsValueJEl).on("change", totalReturnsValueChange);
				$(policyForJEl).on("change", function(){
					
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomeOptionJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
					hideGuarantedIncomeOption();
				});
				
				$(investmentAmountJEl).blur(function(){
					onInvestmentAmountBlur();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(policyTermJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(paymentOptionJEl).on("change", function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investingForJEl).on("change", onInvestingForChange);
				
				$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
				$(familyModalCloseJEl).click(familyModalCloseHanlder);
				
				$(investmentAmountJEl).on("keyup", populateAmountInWord);
				$(investmentAmountJEl).on("input", allowOnlyNumbersHandler);
				$(allowAlphaSpaceJEl).on("input", allowOnlyAlphaAndSpaceHandler);
				
				$(familyIncomeBenefitJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomePayoutFrequencyJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(guarantedIncomeJEL).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(maturityAgeJEl).on("change",  function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investingForWrapperJEl).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
				/*
				 * On click event Listeners to add or remove a rider
				 */
				$(".riderSelectionInput").on("click", function(){
					addRemoveRider(this.id, this.checked);
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(proceedSubmitBtnJEl).click(processSubmitHandler);
				
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
				/*$(investmentAmountJEl).blur(onInvestmentAmountBlur);
				$(policyOptionJEl).on("change", onPolicyOptionChange);
				$(investingForJEl).on("change", onInvestingForChange);
				$(policyForJEl).on("change", onPolicyForChange);
				$(paymentOptionJEl).on("change", onPaymentOptionChange);*/
				
				
				$("#"+namespace+"showProductRidersBtn").on("click", function(){
					showProductRiders();
				});
				
				$("#"+namespace+"showProductFeaturesBtn").on("click", function(){
					showProductFeatures();
				});
				
				$(".tablinks").on("click",function(){
					 $(".tablinks").removeClass("active");
					 $(this).addClass("active");
				});
				
				$("#"+namespace+"premiumPayDetails").on("click", processSubmitHandler);
			};
			
			/**
			 * Show Congratulation Modal if form is valid
			 */
			saveFamilyDetailsHanlder = function(e) {
				e.preventDefault();
				$(familyDetailsFormJEl).validate().form();
				if($(familyDetailsFormJEl).valid()) {
					saveFamilyDetails();
				} 
			}
			/**
			 * On option change of investing for 
			 */
			onInvestingForChange = async function() {
				var investingFor = $(this).val();

				if(investingFor == "Family") {
					initFamilyModal();
					$(familyModalJEl).modal("show");
					$(this).val("Myself");
				} else {
					$(investingForFullNameJEl).val("");
					$(investingForDOBJEl).val("");
					
					$(assuranceFullNameJEl).val("");
					$(assuranceDobJEl).val("");
					
					$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
					$("input[type=radio][name='" + basicInvestingForEl + "'][value='Myself']").prop('checked', true);
					$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
					
					updateYourDetailsHeader()
					
					deleteFamilyDetails();
					var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
					if(!hasValidationErrors) {
						saveCustomerInvestmentDataHanlder();
					}
					
				}
				validateProductRiders();
			}
			
			/**
			 * Formate avilable product amounts 
			 */
			formateProductAmounts = function(){
				$(".productAmounts").each(function(i, element){
					if(!$(this).data() || !$(this).data().amount){
						$(this).html(rupeeSignLabel.concat(" ").concat(Math.round(0).toLocaleString("en-IN")));
						return;
					}
				    $(this).html(rupeeSignLabel.concat(" ").concat(Math.round($(this).data().amount).toLocaleString("en-IN")));
				})
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
			 * Reset to My Self
			 */
			familyModalCloseHanlder = function() {
				$(investingForJEl).val("Myself");
			}
			//Add/Update Family Details
			saveFamilyDetails = async function() {
				showLoader();
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
				$("#"+namespace+"investingFor").val("Family");
				updateYourDetailsHeader();
				
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
								validateProductRiders();
							}
						}
					});	
				} catch(e){
					console.log(e);
				}
				
				
				var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
				$(familyModalJEl).modal("hide");
				
			}
			
			resetFamilyModalData = function() {
				$(investingForFullNameJEl).val("");
				$(investingForDOBJEl).val("");
				
				$("input[type=radio][name='" + familyRadioEl + "']").prop("checked", false);
				$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", true);
			}
			/**
			 * Delete Family Details If any
			 */
			deleteFamilyDetails = function() {
				$.ajax({
					url:deleteFamilyDetailsURL,
					type:"post",
					dataType: "json",
					success: function(response) {
						if(response.status == "success") {
							$(customerFamilyDetailsIdJEl).val("");
						}
					}
				});	
			}
			/**
			 * Customize Form Submit Hanlder
			 */
			processSubmitHandler = async function(e) {
				e.preventDefault();

				//$(isPdfByteJEl).val("Y");
				
				var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
				if(!hasValidationErrors) {
					$(customizeFormJEl).submit();
				}
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
					return null;
				}
			}
			
			/**
			 * Show Validation message from Generate BI Api response
			 */
			handleInputValidationStatus = function(biResponseData) {
				
				if(biResponseData["Status"] && biResponseData["Status"] == "Fail") {
					disableProceedButton();
					var errorMessage = "Please enter a valid Input";
					$("#errorMessageContainer").empty();
					$("#errorMessageContainer").html(errorMessage);
					$(errorMessageModalJEl).modal('show');
					handleTotalReturnOnBIError();
					return true;
				}else {
					$(proceedSubmitBtnJEl).removeClass("disabled-btn");
					$(proceedSubmitBtnJEl).prop('disabled', false);
				}
				return false;
			}
			
			
			handleTotalReturnOnBIError = function() {
				document.getElementById(namespace + "totalGuaranteedBenefitAmount").innerHTML = 0;
			}
			
			
			/**
			 * Disable Proceed Button On Error
			 */
			disableProceedButton = function() {
				$(proceedSubmitBtnJEl).addClass("disabled-btn");
				$(proceedSubmitBtnJEl).prop("disabled", true);
			}
			/**
			 * Generate BI Request Basic Details
			 */
			getBIRequestBasicDetails = function() {
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var dateOfBirth = moment(dateOfBirthInputVal, "DD/MM/YYYY");
				//var dateOfBirthStr = moment(dateOfBirth).format('YYYY-MM-DD');
				//var age = moment().diff(dateOfBirthStr, "years");
				var age = calculateAge(dateOfBirthInputVal);
				
				var proposerDOBStr = moment(dateOfBirth).format('DD MMM YYYY');
				var gender = $("input[name='" + genderEl + "']:checked").val();
				var email = $(emailJEl).val();
				var mobileNumber = $(mobileNumberJEl).val();
				var basicInvestingFor = $("input[name='" + basicInvestingForEl + "']:checked").val();
				var fullName = $(fullNameJEl).val();

				var genderShorName = gender == "Male" ? "M" : "F";
				var sameProposer = "True";
				
				var liDetails = {
					"LI_NAME": fullName,
					"LI_ENTRY_AGE": age,
					"LI_DOB": proposerDOBStr,
					"LI_GENDER": genderShorName,
					"LI_STATE": "0",
					"LI_MOBILENO": mobileNumber,
					"LI_EMAILID": email
				}
				
				if(basicInvestingFor == "Family") {
					sameProposer = "False";
					var assuredRelationVal = $("input[name='" + assuredRelationEl + "']:checked").val();
					var liGenderShortName = assuredRelationVal == "Son" ? "M" : "F";
					var assuranceFullName = $(assuranceFullNameJEl).val();
					var assuranceDobInputVal = $(assuranceDobJEl).val();
					var assuranceAge = calculateAge(assuranceDobInputVal);
					
					var assuranceDob = moment(assuranceDobInputVal, "DD/MM/YYYY");
					var assuranceDobStr = moment(assuranceDob).format('DD MMM YYYY');
					
					liDetails["LI_NAME"] = assuranceFullName;
					liDetails["LI_ENTRY_AGE"] = assuranceAge;
					liDetails["LI_GENDER"] = liGenderShortName;
					liDetails["LI_DOB"] = assuranceDobStr;
				}
				
				var proposerDetails = {
					"SameProposer": sameProposer,
					"PROPOSER_NAME": fullName,
					"PROPOSER_AGE": age,
					"PROPOSER_DOB": proposerDOBStr,
					"PROPOSER_GENDER": gender == "Male" ? "M" : "F",
					"AGENT_ID": "TW9001",
					"category": "12",
					"COMPANY_STATE": "0",
					"emr_rate": "1",
					"flat_rate": "0",
				    "GSTIN": "0",
				    "GSTIN_Number": "0",
				}
				
				return $.extend(true,liDetails,proposerDetails);
			}
			
			hideGuarantedIncomeOption = function(){
				if($(incomeOptionJEl).val() == "3"){
					$(".guarantedIncome-Increasing").hide();
				}else{
					$(".guarantedIncome-Increasing").show();
				}
			}
			
			/**
			 * Generate BI Request 
			 */
			generateBIRequest = async function(requestedRidersList, loadDefaultValues) {
					showLoader();
					//Showing Loader
					//$("#spinner-div").show();
					
					var biRequestData = new Object({});

					var basicDetails = getBIRequestBasicDetails(); 
					var productDetails = getBIRequestProduct(requestedRidersList, loadDefaultValues);
					
					biRequestData[namespace+"biRequestData"]=JSON.stringify($.extend(true,basicDetails,productDetails))
					try {
						const result = await $.ajax({
							url : edelweissGenerateBIURL,
							dataType : "json",
							data : biRequestData,
							type : "POST"
						});
							if(result.status = "success") {
								var biResponse = result.data;
								var biResponseData = JSON.parse(biResponse["JsonOutput"]);
								
								var hasValidationErrors = handleInputValidationStatus(biResponseData);
								if(!hasValidationErrors) {
									renderRiderPremiumAmount(biResponseData);
									updateSelectedRiderPrice();
									updatePlanPremiumDetails();
									biResponseJSON = JSON.parse(biResponseData["BIJson"]);
									renderMaturityBenefitsBreakup(biResponseJSON);
									renderIncomeBenefitsBreakup(biResponseData);
									updateRiderPremiumDropdowns(biResponseJSON[0].SA);
									
									$(illustrationURLJEl).val(biResponseData["Url"]);
									
									if(biResponseData["QuotationId"]) {
										$(quotationIdJEl).val(biResponseData["QuotationId"])
									}
									updateLMSLead();
									
								}
							} else {
								$(errorMessageModalJEl).modal("show");
							}
							hideLoader();
							return hasValidationErrors;
						} catch(error) {
							disableProceedButton();
							hideLoader();
							$(errorMessageModalJEl).modal("show");
							console.log(error);
							handleTotalReturnOnBIError();
							return true;
						}
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
			 * populate Maturity income Benefits breakup table 
			 */
			function renderMaturityBenefitsBreakup(biResponseData){
				
				
				var tableBody = document.getElementById("planMaturityBenefitsBreakupTableBody");
				tableBody.innerHTML = "";
				var id = "0";
				if($(totalReturnsValueJEl).val() == "8"){
					id="2";
				}else{
					id="1";
				}
				
				$(planMaturityBenefitsBreakupTableBodyJEl).empty();
				biResponseJSON.forEach(biJson=>{
						
					const tableRecord = document.createElement("tr");
					var ageAttainedData = document.createElement("td");
					var policyYearData = document.createElement("td");
					var guaranteedIncomeData = document.createElement("td");
					var cashBonusData = document.createElement("td");
					var maturityValueData = document.createElement("td");
					
					ageAttainedData.appendChild(document.createTextNode(biJson.LI_ATTAINED_AGE));
					tableRecord.appendChild(ageAttainedData);
					
					policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
					tableRecord.appendChild(policyYearData);
					
					guaranteedIncomeData.appendChild(document.createTextNode(biJson.SB_G!=0?rupeeSignLabel.concat(" ").concat(Math.floor(biJson.SB_G).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(guaranteedIncomeData);
					
					if(id=="1"){
						cashBonusData.appendChild(document.createTextNode(biJson.CASH_BONUS_BS_1!=0?rupeeSignLabel.concat(" ").concat(Math.floor(biJson.CASH_BONUS_BS_1).toLocaleString("en-IN")):'-'))
					}else{
						cashBonusData.appendChild(document.createTextNode(biJson.CASH_BONUS_BS_2!=0?rupeeSignLabel.concat(" ").concat(Math.floor(biJson.CASH_BONUS_BS_2).toLocaleString("en-IN")):'-'))
					}
					
					tableRecord.appendChild(cashBonusData);
					
					if(id == "1"){
						maturityValueData.appendChild(document.createTextNode(biJson.TOTAL_MB_G_BS_1!=0?rupeeSignLabel.concat(" ").concat(Math.floor(biJson.TOTAL_MB_G_BS_1).toLocaleString("en-IN")):'-'))
					}else{
						maturityValueData.appendChild(document.createTextNode(biJson.TOTAL_MB_G_BS_2!=0?rupeeSignLabel.concat(" ").concat(Math.floor(biJson.TOTAL_MB_G_BS_2).toLocaleString("en-IN")):'-'))
					}
					
					tableRecord.appendChild(maturityValueData);
					
					tableBody.appendChild(tableRecord);
				});
			}
			storeTotalReturnsValue= function(biJson,id){
				var total = 0;
				
				biJson.forEach(curBiJSON => {
					total += Math.round(curBiJSON.SB_G) + Math.round(curBiJSON["CASH_BONUS_BS_"+id]) + Math.round(curBiJSON["TOTAL_MB_G_BS_"+id]);
				});
				if(id == "1"){
					totalReturnsAmount["4"] = total;
				}else{
					totalReturnsAmount["8"] = total
				}
				$(projectedReturnsDataJEl).val(JSON.stringify(totalReturnsAmount));
			};
			
			/**
			 * Update Total Return On Change of Return Percentage (4% or 8%)
			 */
			totalReturnsValueChange = function() {
				
				var currentReturnPercent = this.value;
				if ($("#" +namespace + "proceedBtn").hasClass("disabled-btn")) {
					$(planMaturityBenefitsBreakupJEl).modal("hide");
				}else{
					if(currentReturnPercent == "4"){
						var benefitAmountSpan = document.getElementById(namespace + "totalGuaranteedBenefitAmount");
						benefitAmountSpan.innerText= totalReturnsAmount["4"]?Math.round(totalReturnsAmount["4"]).toLocaleString("en-IN"):"0";
					}else{
						var benefitAmountSpan = document.getElementById(namespace + "totalGuaranteedBenefitAmount");
						benefitAmountSpan.innerText= totalReturnsAmount["8"]?Math.round(totalReturnsAmount["8"]).toLocaleString("en-IN"):"0";
					}
					renderMaturityBenefitsBreakup(biResponseJSON);
					$(planMaturityBenefitsBreakupJEl).modal("show");
				}
			};
			
			/**
			 * Set total guaranteed income amount 
			 * populate incomeBenefits breakup table 
			 */
			function renderIncomeBenefitsBreakup(biResponseData){
				var productBIJson = JSON.parse(biResponseData["BIJson"]);
				var id = "0";
				if($(totalReturnsValueJEl).val() == "8"){
					id = "2";
				}else{
					id = "1";
				}
				storeTotalReturnsValue(productBIJson,"1");
				storeTotalReturnsValue(productBIJson,"2");
				var total = 0;
				productBIJson.forEach(curBiJSON => {
					total += Math.round(curBiJSON.SB_G) + Math.round(curBiJSON["CASH_BONUS_BS_"+id]) + Math.round(curBiJSON["TOTAL_MB_G_BS_"+id]);
				});
				
				
				var benefitAmount = 0;
				var benefitAmountSpan = document.getElementById(namespace + "totalGuaranteedBenefitAmount");
				
				benefitAmountSpan.innerText= total?Math.round(total).toLocaleString("en-IN"):"0";
				
				document.getElementById(namespace+"totalMaturityAmount").value=Math.round(total);
			}
			/**
			 * Generate BI Request Product 
			 */
			getBIRequestProduct = function(requestedRidersList, loadDefaultValues) {
				var payFor = $(policyForJEl).val();
				var annualPremium = Number(getInvestmentAmount());
				var paymentOptionVal= $(paymentOptionJEl).val();
				
				var NriDeclaration="N";
				var ZP_NATIONALITY="1";
				if(isNRI == 'true'){
					NriDeclaration="Y" ,
					ZP_NATIONALITY = "2";
				}
				$(payForJEl).val($(maturityAgeJEl).val()+"-@li_entry_age");
				var product = {
					"FamilyIncomeBenefit": $(familyIncomeBenefitJEl).val() ,
					"IncomeBenefitPayoutFrequency": $(incomePayoutFrequencyJEl).val(),
					"GuaranteedIncomeType": $(guarantedIncomeJEL).val(),
					"INPUT_MODE": $(paymentOptionJEl).val() ,
					"PR_ANNPREM": "",
					"PR_ID": $(productCodeJEl).val() ,
					"PR_PPT": payFor,
					"PR_PT": $(maturityAgeJEl).val()+"-@li_entry_age",
					"PR_SA": " " ,
					"PWB": "",
					"IncomeOption": $(incomeOptionJEl).val(),
					//Default values
					"REQUESTSOURCE": "1" ,
					"PR_SAMF": "0" ,
					"PR_ModalPrem": annualPremium,
					"PR_CHANNEL": "1" ,
					"CashFlow": "N" ,
					"PR_MI": "0" ,
					"isPdfByte":  "Y",
				}
				
				var ridersRequestBody = generateSelectedRidersRequestBody(requestedRidersList, loadDefaultValues);
				return $.extend(true,product,ridersRequestBody);
			}
			
			/**
			 * Add GrandSon and Grand Daughter for age > 40
			 */
			initFamilyModal = function() {
				$(investingForFullNameJEl + "-error").remove();
				$(investingForDOBJEl + "-error").remove();
				
				//Mask Field with DD/MM/YYYY format
				$(investingForDOBJEl).inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
				
				initFamilyRadioButtons();
				
				updateFamilyInvestingForLabels(assuredRelationVal);
			}
			
			initFamilyRadioButtons = function() {
				if($(investingForJEl).val() == "Family") {
					var age = calculateAge($(dateOfBirthJEl).val());
					if(age >= 40) {
						if($("#" + investingForGrandsonEl).length == 0) {
							var grandsonFamilyRadio = '<input type="radio" data-gender="Male" data-name="Grandson" value="GrandSon" name="' + familyRadioEl + '" id="' + investingForGrandsonEl + '">';
							grandsonFamilyRadio += '<label for="' + investingForGrandsonEl + '">' + grandSonLabel + '</label>';

							$(investingForWrapperJEl).append(grandsonFamilyRadio);
						}
						
						if($("#" + investingForGrandDaughterEl).length == 0) {
							var grandDaughterFamilyRadio = '<input type="radio" data-gender="Female" data-name="Granddaughter" value="GrandDaughter" name="' + familyRadioEl + '" id="' + investingForGrandDaughterEl + '">';
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
			 * Init Family Modal Saved Data
			 */
			initFamilyModalData = function() {
				if(assuredRelationVal) {
					$("input[type=radio][name='" + familyRadioEl + "']").removeAttr("checked");
					$("input[type=radio][name='" + familyRadioEl + "'][value='" + assuredRelationVal + "']").prop('checked', true);
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
			 * Update LMS Lead
			 */
			updateLMSLead = function() {
				var proposerGender = $("input[type=radio][name="+genderEl+"]:checked").val();
				var selectedFamilyRadioVal = $('input[type=radio][name=' + familyRadioEl + ']:checked').val();
				var paymentFrequencyDisplayName = $(paymentOptionJEl).find(':selected').data("name");
				var policyOptionDisplayName = $(policyOptionJEl).find(':selected').data("name");
				var investmentObjectiveDisplayName = $('input[name="' + investmentObjectiveEl + '"]:checked').data("name");
				var investingForValue = $('input[type=radio][name=' + basicInvestingForEl + ']:checked').val();
				
				var updateLMSLeadData = {};
				updateLMSLeadData[namespace + "customerEnquiryId"] = $("#" + namespace + "customerEnquiryId").val();
				updateLMSLeadData[namespace + "ChildName"]  = $(assuranceFullNameJEl).val();
				updateLMSLeadData[namespace + "ChildDob"] = $(assuranceDobJEl).val();
				updateLMSLeadData[genderEl] = $("input[type=radio][name="+genderEl+"]:checked").val();
				updateLMSLeadData[fullNameEl] = $(fullNameJEl).val();
				updateLMSLeadData[dateOfBirthEl] = $(dateOfBirthJEl).val();
				updateLMSLeadData[investmentObjectiveEl] = $("input[type=radio][name="+investmentObjectiveEl+"]:checked").val();
				updateLMSLeadData[emailEl] = $(emailJEl).val();
				updateLMSLeadData[mobileNumberEl] = $(mobileNumberJEl).val();
				updateLMSLeadData[namespace + "SumAssured"] = $(totalMaturityAmountJEl).val();
				updateLMSLeadData[namespace + "Product_Code"] = $(productCodeJEl).val();
				updateLMSLeadData[namespace + "BIQuotationNumber"] = $(quotationIdJEl).val();
				updateLMSLeadData[namespace + "BI_PDF_Path"] = $(illustrationURLJEl).val();
				updateLMSLeadData[namespace + "TotalPremiumAmount"] = getInvestmentAmount();
				updateLMSLeadData[namespace + "PolicyTerm"] = $(policyTermJEl).val();
				updateLMSLeadData[namespace + "PremiumPaymentTerm"] = $(policyForJEl).val();
				updateLMSLeadData[namespace + "ProductName"] = $(productNameJEl).val();
				updateLMSLeadData[namespace + "PolicyOption"] = policyOptionDisplayName;
				updateLMSLeadData[namespace + "Recommended_Objective"] = investmentObjectiveDisplayName;
				
				updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "Y";
				if(investingForValue == "Family") {
					updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "N";
				} 
				
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
				
				// Rider Deatails START
				var additionalBenefitVal = "No";
				if($(".riderSelectionInput").length > 0) {
					$(".riderSelectionInput").each(function(){
						//console.log($(this).attr("data-rider-name"));
						var isAvailable = $(this).data("isAvailable");
						var isRiderSelected = $(this).prop("checked");
						//console.log("isAvailable : " + isAvailable +  " ,  isRiderSelected : " + isRiderSelected);
						if(isAvailable && isRiderSelected) {
							additionalBenefitVal = "Yes";
							var riderName = $(this).data("riderName");
							var riderPrice = $(this).data("riderPrice");
							var riderKey = $(this).data("riderKey");
							var riderPT = $(this).data("riderPt");
							var riderPPT = $(this).data("riderPpt");
							var riderProductCode = $(this).val();
							updateLMSLeadData[namespace + riderKey +  "_YN"] = "Y";
							updateLMSLeadData[namespace + riderKey +  "PT"] = riderPT;
							updateLMSLeadData[namespace + riderKey +  "PPT"] = riderPPT;
							updateLMSLeadData[namespace + riderKey +  "SA"] = riderPrice;
							if($(riderPremiumAmountPrefixJEl + riderProductCode).length > 0) {
								var selectedRiderPremiumAmount = $(riderPremiumAmountPrefixJEl + riderProductCode).val();
								updateLMSLeadData[namespace + riderKey +  "Amount"] = selectedRiderPremiumAmount ? selectedRiderPremiumAmount : 0;
							} 
							if(riderKey.trim() == "PayorWaiverBenefitRider") {
								if($(payorWaiverBenefitOptionJEl).length > 0) {
									var payorWaiverBenefitOptionVal = $(payorWaiverBenefitOptionJEl).val();
									if(payorWaiverBenefitOptionVal == "1") {
										updateLMSLeadData[namespace + "PWbRiderOnDeath_YN"] = "Y";
									} else if(payorWaiverBenefitOptionVal == "2") {
										updateLMSLeadData[namespace +  "PWbRiderCIATPD_YN"] = "Y";
									} else if(payorWaiverBenefitOptionVal == "3") {
										updateLMSLeadData[namespace +  "PWBRiderDeathCIATPD_YN"] = "Y";
									}
								}
							}
						}
					});
				}
				updateLMSLeadData[namespace + "Additional_Benefits"] = additionalBenefitVal;
				// Rider Deatails END
				
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
/**--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------**/			
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
			 *Auto-selecte selected riders on page load
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

				    const riderId=productRider.ProductId;
				    const riderPremiumAmount = productRider.BasePremium + productRider.Tax;
				    
				    /*
				     * Set Base plan price  
				     */
				    if(productMetaData.productCode == riderId && document.getElementById(namespace+"basePlanPrice") && document.getElementById(namespace+"basePlanPriceAmount")){
				    	document.getElementById(namespace+"basePlanPriceAmount").innerHTML = rupeeSignLabel.concat(" ").concat(Math.round(riderPremiumAmount).toLocaleString("en-IN"));
				    	document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice = riderPremiumAmount;
				    	document.getElementById(namespace+"basePremiumAmount").value=Math.round(riderPremiumAmount);
				    }
				    
				    /*
				     * Set rider Amount
				     */
				    if(document.getElementById(namespace+"riderPriceAmount_"+riderId))
			    	{
				    	document.getElementById(namespace+"riderPriceAmount_"+riderId).innerHTML = " " + rupeeSignLabel + " " + Math.round(riderPremiumAmount).toLocaleString("en-IN");
			    	}
				    
				    /*
				     * Add Price Amount to the riders data-set
				     */
				    if(document.getElementById(selectedRiderPrefixEl + riderId)) {
				    	document.getElementById(selectedRiderPrefixEl + riderId).dataset.riderPrice = riderPremiumAmount;
				    	document.getElementById(selectedRiderPrefixEl + riderId).dataset.riderPt = productRider.PT;
				    	document.getElementById(selectedRiderPrefixEl + riderId).dataset.riderPpt = productRider.PPT;
			    	}
				    
				});
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
					}

					selectedRidersList.set(selectedRider.value, selectedRiderDetails);
				}else{
					selectedRidersList.delete(selectedRider.value);
					if(document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value)){
						document.getElementById(namespace+"riderPremiumAmount_"+selectedRider.value).disabled=false;
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
				
				if(!selectedRidersList || selectedRidersList.size == 0){
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
						document.getElementById (namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(" ").concat(Math.round(document.getElementById(namespace+"basePlanPrice").dataset.basePlanPrice).toLocaleString("en-IN"));
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
					selectedRiderPriceElement.appendChild(document.createTextNode(rupeeSignLabel.concat(" ").concat(Math.round(rider.price).toLocaleString("en-IN"))));

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
					document.getElementById (namespace+"totalPayPremiumAmount").innerHTML = rupeeSignLabel.concat(" ").concat(Math.round(calculateTotalPremiumAmount()).toLocaleString("en-IN"));
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
					totalPremiumAmount = (rider.price)?totalPremiumAmount+parseInt(rider.price):totalPremiumAmount;
				});
				
				return totalPremiumAmount;
			}

			/**
			 * Rider Validation
			 */
			validateProductRiders = function(){
				riderValidations.forEach((riderValidation,riderId)=>{
					var isInvestingForValid = validateRiderInvestingFor(riderValidation,riderId);
					var isFamilyIncomeBenefitValid = validateFamilyIncomeBenefit(riderValidation,riderId);
					var isCriticalIllnessAgeValid = validateCriticalIllnessEntryAge(riderId);
					var isWOPAgeValid = validateWOPAge(riderId);

					document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isFamilyIncomeBenefitValid && isCriticalIllnessAgeValid && isWOPAgeValid;
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
			    	if(document.getElementById(namespace+"selectedRider_"+riderId)) {
			    		document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
				    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
			    	}
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
			
			validateWOPAge = function(riderId){
				console.log(' validateWOPAge >> riderId ', riderId);
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


			/**
			 * Rider Validation: Family Income benefit 
			 */
			validateFamilyIncomeBenefit = function(riderValidation,riderId){
				if(!document.getElementById(namespace+"familyIncomeBenefit")){
					return;
				}
				var investingFor=document.getElementById(namespace+"familyIncomeBenefit").value;

			    $("#"+namespace+"familyIncomeBenefitError_"+riderId).hide();
			    
			    var isRiderAvailable = false;
			    if(investingFor==2 || riderValidation.isAvailableWithFib){
			    	isRiderAvailable = true;
			    }
			    
			    document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isRiderAvailable;
			    
			    if(!isRiderAvailable){
			    	$("#"+namespace+"familyIncomeBenefitError_"+riderId).show();
			    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
			    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
			    	
			    }
			    
			    return isRiderAvailable;
			}
			

			/**
			 * Generate-BI Request Body: Selected Rider Request Body
			 */
			generateSelectedRidersRequestBody = function(requestedRidersList, loadDefaultValues){
				var riderDetailsRequestBody={};
				
				document.getElementsByName(namespace+"selectedRiders").forEach(availableRider=>{
				    
					if(!availableRider.dataset){
						return;
					}
					
				    if(availableRider.dataset.isAvailable == "false" || !(loadDefaultValues || (requestedRidersList && requestedRidersList.has(availableRider.value)))){
				        riderDetailsRequestBody[availableRider.dataset.riderKey]="";
				        return;
				    }
				    
				    if((document.getElementsByName(namespace+"selectedRiders").value == "50003" || document.getElementsByName(namespace+"selectedRiders").value =="50007") && age > 60){
				    	 riderDetailsRequestBody[availableRider.dataset.riderKey]="";
				    	 return;
				    }
				    
				    if(document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value)){
				        riderDetailsRequestBody[availableRider.dataset.riderKey]=document.getElementById(namespace+"riderPremiumAmount_"+availableRider.value).value;
				    }else{
				         riderDetailsRequestBody[availableRider.dataset.riderKey]="1";
				    }
				});

				if(document.getElementById(namespace+"payorWaiverBenefitOption") && document.getElementById(namespace+"payorWaiverBenefitOption").value){
					riderDetailsRequestBody["PWB"]=document.getElementById(namespace+"payorWaiverBenefitOption").value;
				}
				else{
					riderDetailsRequestBody["PayorWaiverBenefitRider"] = "";
				}
				
				return riderDetailsRequestBody;
			}
			
			/**
			 * update customize page on changes of user inputs
			 */
			updateCustomizePage = async function(requestedRidersList, loadDefaultRiderValues){
				validateProductRiders();
				
				var hasValidationErrors = await generateBIRequest(requestedRidersList, loadDefaultRiderValues);
				if(!hasValidationErrors) {
					console.log("no error");
					saveCustomerInvestmentDataHanlder();
				}
			}
/**--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------**/
			
			init = async function(){
				initEvents();
				updatePGIRelFieldValue();
				hideGuarantedIncomeOption();
				formateProductAmounts();
				//updateGetIncomeForValue();
				//updatePolicyTermValue();
				updateCustomizePage(selectedRidersList, true);
				
				populateAmountInWord();
				
				updateInvestmentAmountLocaleIN();
				
				if(cookieLeadId) {
					loadCustomersSavedRiders();
				} else {
					//Redirect to Product LMS Form
					location.href = "/active-income";
				}
				initFamilyModal();
				initFamilyModalData();
				initFamilyModalFormValidation();
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
	
	edelweissProductCustomizePortlet.viewProductCustomize = viewProductCustomize;
})($, (window.edelweissProductCustomizePortlet = window.edelweissProductCustomizePortlet || {}));



