(function($, edelweissProductCustomizePortlet) {
	
	function viewProductCustomize(config){
		var namespace = config.namespace,
		stage = "Customise",
		selectedRidersList = new Map(),
		
		customizeFormJEl = config.customizeFormJEl,
		policyOptionJEl=config.policyOptionJEl,	
		policyTermJEl=config.policyTermJEl ,
		policyForJEl=config.policyForJEl ,
		getIncomeForJEl=config.getIncomeForJEl,
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
		personalDetailsJEl = "#personal-details",
		amtInWordsJEl = "#amt-in-word",
		
		familyRadioEl = config.familyRadioEl,

		assuranceFullNameJEl=config.assuranceFullNameJEl,
		assuranceDobJEl=config.assuranceDobJEl,
		investmentAmountJEl=config.investmentAmountJEl,
		productCodeJEl=config.productCodeJEl,
		paymentOptionJEl=config.paymentOptionJEl,
		paymentOptionEl=config.paymentOptionEl,
		familyModalJEl = config.familyModalJEl,
	 	familyDetailsFormJEl = config.familyDetailsFormJEl,
	 	investingForVal = config.investingForVal,
	 	investingForGrandsonEl = config.investingForGrandsonEl,
	 	investingForGrandDaughterEl = config.investingForGrandDaughterEl,
	 	familyRadioEl = config.familyRadioEl,
	 	investingForWrapperJEl = config.investingForWrapperJEl,
	 	assuredRelationVal = config.assuredRelationVal,
	 	investingForFullNameLableJEl = config.investingForFullNameLableJEl,
	 	investingForFullNameEl = config.investingForFullNameEl,
	 	investingForDOBLabelJEl = config.investingForDOBLabelJEl,
	 	investingForDOBEl = config.investingForDOBEl,
	 	investmentObjectiveEl = namespace + "investmentObjective",
	 	deleteFamilyDetailsURL = config.deleteFamilyDetailsURL,
	 	customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
	 	investingForJEl = config.investingForJEl,
	 	proceedSubmitBtnJEl = config.proceedSubmitBtnJEl,
	 	illustrationURLJEl = config.illustrationURLJEl,
	 	quotationIdJEl = config.quotationIdJEl,
	 	updateLMSLeadURL = config.updateLMSLeadURL,
	 	saveCustomerInvestmentDataURL = config.saveCustomerInvestmentDataURL,
	 	errorMessageModalJEl = config.errorMessageModalJEl,

	 	
		isEdelweissEmployeeJEl=config.isEdelweissEmployeeJEl,
		familyIncomeBenefitJEl=config.familyIncomeBenefitJEl,
		incomePayoutFrequencyJEl=config.incomePayoutFrequencyJEl,
		maturityPayoutOptionJEl=config.maturityPayoutOptionJEl,
		lumpSumBenefitJEl=config.lumpSumBenefitJEl,
		
		cookieLeadId = config.cookieLeadId,
		edelweissGenerateBIURL = config.edelweissGenerateBIURL,
		
		basicInvestingForEl=config.basicInvestingForEl,
		assuredRelationEl=config.assuredRelationEl,
		genderEl=config.genderEl,
		policyOptionEl=config.policyOptionEl,	
		policyTermEl=config.policyTermEl ,
		policyForEl=config.policyForEl ,
		
		incomeStartPointEl=config.incomeStartPointEl,
		incomeStartPointJEl=config.incomeStartPointJEl,
		incomeDurationEl=config.incomeDurationEl,
		incomeDurationJEl=config.incomeDurationJEl,
		incomeBenefitPayoutTypeEl=config.incomeBenefitPayoutTypeEl;
		incomeBenefitPayoutTypeJEl=config.incomeBenefitPayoutTypeJEl;
		
		getIncomeForEl=config.getIncomeForEl,
		productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
		
		
		ibRelFieldValues=config.ibRelFieldValues,
		payoutPeriodJEl=config.payoutPeriodJEl,
		payoutPeriodEl=config.payoutPeriodEl,
		payoutOptionEl=config.payoutOptionEl,
		payoutOptionJEl=config.payoutOptionJEl,
		
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
		ageBetween6to17ErrorMsg = LANG_MESSAGES["age-between-6-to-17-years"],
		rupeeSignLabel =  LANG_MESSAGES["rupee-sign"];
		
		var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
		var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
		var isOnLoad;
		
		var basicInvestingForValidator = portletNamespace + "basicInvestingFor",
		assuredRelation = portletNamespace + "assuredRelation";
		
		/*
		 * Rider Related variable
		 */

		var riderPremiumAmountPrefixEl = namespace + "riderPremiumAmount_",
		riderPremiumAmountPrefixJEl = "#" + riderPremiumAmountPrefixEl,
		payorWaiverBenefitOptionJEl = "#" + namespace + "payorWaiverBenefitOption";
		
		/**
		 * render Payfor value based on policy option value
		 */			
			updatePayForFieldValue =function(){
				var currentPayForValue=$(policyForJEl).val();
				
				var payForList = new Map();
			    var policyOptionValue=document.getElementById(policyOptionEl).value
			  
			    ibRelFieldValues.forEach(field=>{
			    		if($(policyOptionJEl).val()==field.planOption.key && field.policyTerm.key==$(policyTermJEl).val()){
								payForList.set(field.payFor.key,field.payFor);
			    		}
			       })
			    
			           const sortedPayFor = Array.from(payForList).sort(([keyA, valueA], [keyB, valueB]) => {
							if (Number(keyA) < Number(keyB)) {
								return -1;
							} else if (Number(keyA) > Number(keyB)) {
								return 1;
							} else {
								return 0;
							}
						});
			    payForList = new Map(sortedPayFor);
				    
			    var payFordiv=document.getElementById(policyForEl);
			    payFordiv.innerHTML="";
			    payForList.forEach(function(option, key){
			   
			    const payForOptionElement = document.createElement("option");
					payForOptionElement.setAttribute("value",key );
					payForOptionElement.appendChild(document.createTextNode(option.name));
			payFordiv.appendChild(payForOptionElement);
			})
			
								if(payForList.has(currentPayForValue)) {
									$(policyForJEl).val(currentPayForValue);
								}
		  }
			/**
			 * update payment option values based on Pay for value
			 */
			updatePaymentOption =function(){
				var currentPaymentOptionValue=$(paymentOptionJEl).val();
				var paymentOptionList = new Map();
				
				ibRelFieldValues.forEach(field=>{
		    		if($(policyForJEl).val()==field.payFor.key ){
		    			field.paymentFrequency.forEach(innerField=>{
		    				paymentOptionList.set(innerField.key,innerField);
						} )
		    		}
		       })
		       
		       var paymentOptiondiv=document.getElementById(paymentOptionEl);
				paymentOptiondiv.innerHTML="";
				paymentOptionList.forEach(function(option, key){
				   
				    const paymentFrequencyOptionElement = document.createElement("option");
				    	paymentFrequencyOptionElement.setAttribute("value",key );
				    	paymentFrequencyOptionElement.setAttribute("data-name",option.name);
				    	paymentFrequencyOptionElement.appendChild(document.createTextNode(option.name));
				    paymentOptiondiv.appendChild(paymentFrequencyOptionElement);
				})
				
				if(paymentOptionList.has(currentPaymentOptionValue)) {
						$(paymentOptionJEl).val(currentPaymentOptionValue);
					}
				
			}
			/**
			 * update payment period based on policy term and payfor values
			 */
			updatePayoutPeriod =function(){
				
				var currentPayOutperiodValue=$(payoutPeriodJEl).val();
				var payOutperiodList = new Map();
							  
				ibRelFieldValues.forEach(field=>{
					if($(policyForJEl).val()==field.payFor.key && field.policyTerm.key==$(policyTermJEl).val()){
							field.payoutPeriod.forEach(innerField=>{
								payOutperiodList.set(innerField.key,innerField);
							} )
						}
					})
				
					var payoutPerioddiv=document.getElementById(payoutPeriodEl);
					payoutPerioddiv.innerHTML="";
					payOutperiodList.forEach(function(option, key){
				   
				    const payoutPeriodOptionElement = document.createElement("option");
				    	payoutPeriodOptionElement.setAttribute("value",key );
				    	payoutPeriodOptionElement.appendChild(document.createTextNode(option.name));
				    payoutPerioddiv.appendChild(payoutPeriodOptionElement);
				})
				if(payOutperiodList.has(currentPayOutperiodValue)) {
						$(payoutPeriodJEl).val(currentPayOutperiodValue);
					}
		   }
			
			updatePolicyTerm =function(){
				var policyTermList = new Map();
				var currentPolicyTermValue=$(policyTermJEl).val();
				 
				ibRelFieldValues.forEach(field=>{
					 if(field.planOption.key==$(policyOptionJEl).val()){
						 policyTermList.set(field.policyTerm.key,field.policyTerm);
					 }
				 })
				
				 var PolicyTermdiv=document.getElementById(policyTermEl);
				 PolicyTermdiv.innerHTML="";
				 
				 const sortedPolicyTermArray = Array.from(policyTermList).sort(([keyA, valueA], [keyB, valueB]) => {
					if (Number(keyA) < Number(keyB)) {
						return -1;
					} else if (Number(keyA) > Number(keyB)) {
						return 1;
					} else {
						return 0;
					}
				});
				 policyTermList = new Map(sortedPolicyTermArray);
				 
				 policyTermList.forEach(function(option, key){
				   
				    const PolicyTermElement = document.createElement("option");
				    	  PolicyTermElement.setAttribute("value",key );
				    	  PolicyTermElement.appendChild(document.createTextNode(option.name));
				     PolicyTermdiv.appendChild(PolicyTermElement);
				})
				if(policyTermList.has(currentPolicyTermValue)) {
						$(policyTermJEl).val(currentPolicyTermValue);
					}
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
							console.log("sucess ,saved data on change field")
							$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId)
							$(customerPolicyDetailsIdJEl).val(response.customerPolicyDetailsId);
						}
						hideLoader();

					}
				});	
			}
			/**
			 * Initializing JQuery Events
			 */
			initEvents = function(){
				
				$(policyOptionJEl).on("change", function(){
					updatePolicyTerm();
					updatePayForFieldValue();
					updatePayoutPeriod();
					updatePaymentOption();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(policyTermJEl).on("change", function(){
					updatePayForFieldValue();
					updatePayoutPeriod();
					updatePaymentOption();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(policyForJEl).on("change", function(){
					updatePaymentOption();
					updatePayoutPeriod();
					updateCustomizePage(selectedRidersList, false);
					/*updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);*/
				});
				
				$(investmentAmountJEl).blur(function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investmentAmountJEl).on("keyup", populateAmountInWord);
				
				$(paymentOptionJEl).on("change", function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(payoutPeriodJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomePayoutFrequencyJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(payoutOptionJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investingForJEl).on("change", onInvestingForChange);
						
				$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
		
				$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
				
				$(familyModalCloseJEl).click(familyModalCloseHanlder);
				
				$(investingForWrapperJEl).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
				/*
				 * On click event Listeners to add or remove a rider
				 */
				$(".riderSelectionInput").on("click", function(){
					if(this.dataset.riderKey =="PayorWaiverBenefitRider" && document.getElementById(namespace+"payorWaiverBenefitOption") && 
							   !document.getElementById(namespace+"payorWaiverBenefitOption").value){
								// TODO: Show Error in popup
								$("#errorMessageModal").find("#errorMessageContainer").empty();
								$("#errorMessageModal").find("#errorMessageContainer").html("Select a value before adding Payor Waiver Benefit");
								$("#errorMessageModal").modal('show');
								this.checked=false;
								return;
							}
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
				
				/*
				 * Validate Rider on chnage of User Family Details
				 */
				$(".basicInvestingForWarpper").on("change", 'input[type=radio][name='+basicInvestingForValidator+']', function(event){
					validateProductRiders();
				});
				
				$("#investingFormFamily").on("change", 'input[type=radio][name='+assuredRelation+']', function(event){
					
				});
				
				/*
				 * Parse Amount values
				 */
				formateProductAmounts();
			};
			/**
			 * Formate avilable product amounts 
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
			 * Updates the Minimum Investment Amount By Payment Frequency, Policy Option as Configured in Object
			 */
			updateMinimumInvestmentAmount = function() {
				var policyOptionVal = $(policyOptionJEl).val();
				var paymentOptionVal = $(paymentOptionJEl).val();
				
				var currentInvestmentAmount = Number(getInvestmentAmount());
				
				if(productInvestmentAmountRelsData && productInvestmentAmountRelsData.length > 0) {
					productInvestmentAmountRelsData.forEach(curProductInvestmentAmountRel => {
						var curMinimumInvestmentAmount = Number(curProductInvestmentAmountRel.minimumInvestmentAmount);
					    if(curProductInvestmentAmountRel.paymentOption.key == paymentOptionVal  && currentInvestmentAmount < curMinimumInvestmentAmount) {
					    	$(investmentAmountJEl).val(curProductInvestmentAmountRel.minimumInvestmentAmount);
					    	return;
					    }
					});
					
					populateAmountInWord();
					updateInvestmentAmountLocaleIN();
				}
			}
			/**
			 * Polulate Amount in Words
			 */
			populateAmountInWord = function() {
				var curInvestmentAmount = getInvestmentAmount();
				var amountInWords = getAmountInWords(curInvestmentAmount);
				$(amtInWordsJEl).html(rupeeSignLabel + "&nbsp;" + amountInWords);
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

				var familyIncomeBenefitDiv=document.getElementById(namespace+"familyIncomeBenefitDiv");
				if(investingFor == "Family") {
					$(familyIncomeBenefitJEl).val("3") ; //key 3 is for No
					$(familyIncomeBenefitDiv).parent().hide();
					
					initFamilyModal();
					$(familyModalJEl).modal("show");
					$(this).val("Myself");
					
					
				} else {
					$(familyIncomeBenefitDiv).parent().show();
					$(investingForFullNameJEl).val("");
					$(investingForDOBJEl).val("");
					
					$(assuranceFullNameJEl).val("");
					$(assuranceDobJEl).val("");
					
					$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
					$("input[type=radio][name='" + basicInvestingForEl + "'][value='Myself']").prop('checked', true);
					$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
					
					updateYourDetailsHeader()
					
					deleteFamilyDetails();
					updateCustomizePage(selectedRidersList, false);
					
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
			 * Reset to My Self
			 */
			familyModalCloseHanlder = function() {
				$(investingForJEl).val("Myself");
			}
			//Add/Update Family Details
			saveFamilyDetails = async function() {
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
				
				
				$.ajax({
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
						}
					}
				});	
				
				/*
				 * Update Investing for dropdown option.
				 */
				$("#"+namespace+"investingFor").val("Family");
				updateYourDetailsHeader();
				updateCustomizePage(selectedRidersList, false);
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
				stage="Summary";
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
					 * handle the eorrers as per the product or rider 
					 */
					if(inputValidation.ErrorMessage.length){
						document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=Math.round(0);
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
					"PROPOSER_GENDER": gender == "Male" ? "M" : "F"
				}
				
				return $.extend(true,liDetails,proposerDetails);
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
					/*biRequestData[namespace + "liDetails"] = JSON.stringify(basicDetails["liDetails"]);
					biRequestData[namespace + "proposerDetails"] = JSON.stringify(basicDetails["proposerDetails"]);
					biRequestData[namespace + "product"] =  JSON.stringify(getBIRequestProduct(requestedRidersList, loadDefaultValues));*/
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
								var biResponseData =JSON.parse(biResponse["JsonOutput"]);
								
								console.log("-------responsejsonBi-----"+biResponseData);
								var hasValidationErrors = handleInputValidationStatus(biResponseData);
								if(!hasValidationErrors) {
									renderRiderPremiumAmount(biResponseData);
									updateSelectedRiderPrice();
									updatePlanPremiumDetails();
									renderMaturityBenefitsBreakup(biResponseData);
									updateRidersListHiddenInput();
									updateRiderPremiumDropdowns(JSON.parse(biResponseData["BIJson"])[0].SA);
									
									$(illustrationURLJEl).val(biResponseData.Url);
									
									if(biResponseData["QuotationId"]) {
										$(quotationIdJEl).val(biResponseData["QuotationId"])
									}
									
									var inputValidationStatusArray = biResponseData["InputValidationStatus"];
									updateLMSLead();
									
								}
							} else {
								$(errorMessageModalJEl).modal("show");
							}
							hideLoader();
							return hasValidationErrors;
						} catch(error) {
							hideLoader();
							$(errorMessageModalJEl).modal("show");
							document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=Math.round(0);
							$("#"+namespace+"proceedBtn").prop('disabled', true);
					 		$("#"+namespace+"premiumPayDetails").addClass('disabledbutton');
							console.log(error);
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
				
				var productBIJson = JSON.parse(biResponseData["BIJson"]);
				var benefitAmount = 0;
				var benefitAmountSpan = document.getElementById(namespace + "totalGuaranteedBenefitAmount");
				var tableBody = document.getElementById("planMaturityBenefitsBreakupTableBody");
				
				tableBody.innerHTML = "";
				productBIJson.forEach(biJson=>{
					if(biJson.PAYOUT_PERIOD>0){
						const tableRecord = document.createElement("tr");
						var payoutYearData = document.createElement("td");
						var ageAttainedData = document.createElement("td");
						var policyYearData = document.createElement("td");
						var maturityValueData = document.createElement("td");
						
						payoutYearData.appendChild(document.createTextNode(biJson.PAYOUT_PERIOD));
						tableRecord.appendChild(payoutYearData);
						
						ageAttainedData.appendChild(document.createTextNode(biJson.LI_ATTAINED_AGE))
						tableRecord.appendChild(ageAttainedData);
							
						policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR))
						tableRecord.appendChild(policyYearData);
							
						maturityValueData.appendChild(document.createTextNode(biJson.INCOME_DISPLAY!=0?rupeeSignLabel.concat(Math.ceil(biJson.INCOME_DISPLAY).toLocaleString("en-IN")):'-'))
						tableRecord.appendChild(maturityValueData);
							
						tableBody.appendChild(tableRecord);
						
						benefitAmount = benefitAmount + biJson.INCOME_DISPLAY;
					}	
					
				});
						benefitAmountSpan.innerText= benefitAmount?Math.round(benefitAmount).toLocaleString("en-IN"):"0";
						if(benefitAmount && benefitAmount!=0){
							$(proceedSubmitBtnJEl).removeClass("disabled");
						}
						document.getElementById(namespace+"totalMaturityAmount").value=Math.round(benefitAmount);
			}
			/**
			 * Generate BI Request Product 
			 */
			getBIRequestProduct = function(requestedRidersList, loadDefaultValues) {
				var payFor = $(policyForJEl).val();
				var investmentAmount = Number(getInvestmentAmount());
				var  paymentOptionVal= $(paymentOptionJEl).val();
				var payoutPeriod = $(payoutPeriodJEl).val();
				var incomePayoutFrequency=$(incomePayoutFrequencyJEl).val() ;
				
				var annualPremium = investmentAmount;
				switch (paymentOptionVal) {
				case "2":
					annualPremium = 2 * investmentAmount;
					break;
				case "3":
					annualPremium = 4 * investmentAmount;
					break;
				case "4":
					annualPremium = 12 * investmentAmount;
				}
				
				switch (payoutPeriod) {
				case "6":
					payoutPeriod = "1";
					break;
				case "7":
					payoutPeriod = "2";
					break;
				case "8":
					payoutPeriod = "3";
					break;
				case "10":
					payoutPeriod = "4";
					break;
				case "12":
					payoutPeriod = "5";
					break;
				case "13":
					payoutPeriod = "6";
					break;
				case "15":
					payoutPeriod = "7";
					break;
				case "18":
					payoutPeriod = "8";
					break;
				case "20":
					payoutPeriod = "9";
					break;
				case "23":
					payoutPeriod = "10";
				}
				
				switch (incomePayoutFrequency) {
				case "1":
					incomePayoutFrequency = "15";
					break;
				case "2":
					incomePayoutFrequency = "17";
					break;
				case "3":
					incomePayoutFrequency = "18";
					break;
				case "4":
					incomePayoutFrequency = "19";
				}
				var product = {
					"category": "1",
					"FamilyIncomeBenefitOption": $(familyIncomeBenefitJEl).val() ,
					
					"INPUT_MODE": $(paymentOptionJEl).val() ,
					"PR_ANNPREM": annualPremium ,
					"PR_ID": $(productCodeJEl).val() ,
					"PR_PPT": payFor,
					"PR_PT": $(policyTermJEl).val(),
					"PayoutMode": incomePayoutFrequency,
					"PayoutPeriodInYears": payoutPeriod ,
					"MaturityBenefitOption": $(payoutOptionJEl).val() ,
					"OptionName": $(policyOptionJEl).val() ,
					
					//Default values
					"ProductType": "term",
					"PR_SAMF": "0",
					"PR_CHANNEL":"1",
					"REQUESTSOURCE": "1" ,
					"PR_ModalPrem": "" ,
					"PR_SA":"" ,
					"CashFlow": "N" ,
					"PR_MI": "0" ,
					"isPdfByte":  "Y",
					"COMPANY_STATE": "0",
					"GSTIN": "0",
					"GSTIN_Number": "0",
					"MODAL_INCOME": "123",
					"emr_rate": "1.00",
					"flat_rate": "0",
					/*
					"policyOPtions" : getBIRequestPolicyOptions(),
					//"riderDetails" : JSON.parse('{"ADBRider":"","ATPDRider":"","CriticalIllnessRider":"","HospitalityCashBenefitRider":"","PayorWaiverBenefitRider":"","PWB":"","WaiverOfPremiumRider":""}')
				
					"riderDetails" : generateSelectedRidersRequestBody(requestedRidersList, loadDefaultValues)*/
					
				}
				var ridersRequestBody = generateSelectedRidersRequestBody(requestedRidersList, loadDefaultValues);
				console.log(ridersRequestBody);
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
					minAge:6,
					maxAge:17,
					messages: {
					    required: investingForDOBErrDisplayMsg,
					    minAge: ageBetween6to17ErrorMsg,
			  			maxAge: ageBetween6to17ErrorMsg
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
				var payoutFrequencyDisplayName = $(incomePayoutFrequencyJEl).find(':selected').data("name");
				var policyOptionDisplayName = $(policyOptionJEl).find(':selected').data("name");
				var investmentObjectiveDisplayName = $('input[name="' + investmentObjectiveEl + '"]:checked').data("name");
				var investingForValue = $('input[type=radio][name=' + basicInvestingForEl + ']:checked').val();
				
				var updateLMSLeadData = {};
				updateLMSLeadData[namespace + "QuoteStage"] = stage;
				updateLMSLeadData[namespace + "customerEnquiryId"] = $("#" + namespace + "customerEnquiryId").val();
				updateLMSLeadData[namespace + "ChildName"]  = $(assuranceFullNameJEl).val();
				updateLMSLeadData[namespace + "ChildDob"] = $(assuranceDobJEl).val();
				updateLMSLeadData[genderEl] = $("input[type=radio][name="+genderEl+"]:checked").val();
				updateLMSLeadData[fullNameEl] = $(fullNameJEl).val();
				updateLMSLeadData[dateOfBirthEl] = $(dateOfBirthJEl).val();
				updateLMSLeadData[investmentObjectiveEl] = $("input[type=radio][name="+investmentObjectiveEl+"]:checked").val();
				updateLMSLeadData[emailEl] = $(emailJEl).val();
				updateLMSLeadData[mobileNumberEl] = $(mobileNumberJEl).val();
				updateLMSLeadData[namespace + "SumAssured"] = document.getElementById(namespace + "totalMaturityAmount").value;
				updateLMSLeadData[namespace + "Product_Code"] = $(productCodeJEl).val();
				updateLMSLeadData[namespace + "BIQuotationNumber"] = $(quotationIdJEl).val();
				updateLMSLeadData[namespace + "BI_PDF_Path"] = $(illustrationURLJEl).val();
				updateLMSLeadData[namespace + "TotalPremiumAmountWoTax"] = getInvestmentAmount();
				updateLMSLeadData[namespace + "TotalPremiumAmount"] = document.getElementById (namespace+"totalPayPremiumAmount").innerHTML.replaceAll(/\D/g, "");
				
				updateLMSLeadData[namespace + "BasePremiumAmountWoTax"] = getInvestmentAmount();
				updateLMSLeadData[namespace + "BasePremiumAmount"] =document.getElementById (namespace+"totalPayPremiumAmount").innerHTML.replaceAll(/\D/g, "");
				updateLMSLeadData[namespace + "PremiumPaymentFrequency"] = paymentFrequencyDisplayName;
				updateLMSLeadData[namespace + "PayoutFrequency"] = payoutFrequencyDisplayName;
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
				    	document.getElementById(namespace+"riderPriceAmount_"+riderId).innerHTML = "@".concat(rupeeSignLabel.concat(Math.round(riderPremiumAmount).toLocaleString("en-IN")));
				    	
			    	}
				    
				    /*
				     * Add Price Amount to the riders data-set
				     */
				    if(document.getElementById(namespace+"selectedRider_"+riderId))
			    	{
				    	document.getElementById(namespace+"selectedRider_"+riderId).dataset.riderPrice = riderPremiumAmount;
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
					var isValidWOPRider = validateWOPRider(riderId);
					var isValidPWBRider=  validatePWBRider(riderId);
					
					document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isValidWOPRider && isValidPWBRider;
				});
			}
			/**
			 * WOP rider validation based on pay for value
			 */
			validateWOPRider = function(riderId){
				var isRiderAvailable=false;
				if(riderId=="50007"){
					
					if($(policyForJEl).val()=="1" || $(policyOptionJEl).val()=="12"){
						isRiderAvailable= false;
					}
					else{
						isRiderAvailable= true;
					}
				}
				else{
					isRiderAvailable= true
				}
				$("#"+namespace+"WOPSinglePayError_"+riderId).hide();

				if(!isRiderAvailable){
			    	$("#"+namespace+"WOPSinglePayError_"+riderId).show();
			    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
			    	addRemoveRider(namespace+"selectedRider_"+riderId, false);

			    }
				return isRiderAvailable;
			}
			/**
			 * PWB rider validation based on pay for value
			 */
			validatePWBRider = function(riderId){
				var isRiderAvailable=false;
				if(riderId=="50008"){
					
					if($(policyForJEl).val()=="1" || $(policyOptionJEl).val()=="12"){
						isRiderAvailable= false;
					}
					else{
						isRiderAvailable= true;
					}
				}
				else{
					isRiderAvailable= true
				}
				$("#"+namespace+"PWBSinglePayError_"+riderId).hide();

				if(!isRiderAvailable){
			    	$("#"+namespace+"PWBSinglePayError_"+riderId).show();
			    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
			    	addRemoveRider(namespace+"selectedRider_"+riderId, false);

			    }
				return isRiderAvailable;
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
			 * Generate-BI Request Body: Selected Rider Request Body
			 */
			generateSelectedRidersRequestBody = function(requestedRidersList, loadDefaultValues){
				var riderDetailsRequestBody={};
				document.getElementsByName(namespace+"selectedRiders").forEach(availableRider=>{
				    
					if(!availableRider.dataset){
						return;
					}
					
				    if(availableRider.dataset.isAvailable == "false" || !(loadDefaultValues || requestedRidersList.has(availableRider.value))){
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
				updatePolicyTerm();
				updatePayForFieldValue();
				updatePayoutPeriod();
				updatePaymentOption();
				
				loadCustomersSavedRiders();
				populateAmountInWord();

				updateCustomizePage(selectedRidersList, true);
				
				updateInvestmentAmountLocaleIN();
				initFamilyModal();
				initFamilyModalData();
				initFamilyModalFormValidation();
				$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
				/*updatePolicyOptionRelFieldValues();
				
				// Load User Selected Riders
				 
				
				
				*/
				
				/*
				 *Render Investment Amount in words
				 */

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



