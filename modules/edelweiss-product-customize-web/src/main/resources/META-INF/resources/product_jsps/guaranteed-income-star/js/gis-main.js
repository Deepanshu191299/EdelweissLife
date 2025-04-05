(function($, edelweissProductCustomizePortlet) {
	
	function viewProductCustomize(config){
		var namespace = config.namespace,
		
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
		
		
		gisRelFieldValues=config.gisRelFieldValues,
		
		
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
		
		var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
		var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
		var isOnLoad;
		
		var basicInvestingForValidator = portletNamespace + "basicInvestingFor",
		assuredRelation = portletNamespace + "assuredRelation";
		
		updatePolicyOptionRelFieldValues =function(){
			
			var incomeStartPointDiv=document.getElementById(namespace+"incomeStartPointDiv");
			var incomeDurationdiv=document.getElementById(namespace+"incomeDurationdiv");
			var incomeBenefitPayoutTypeDiv=document.getElementById(namespace+"incomeBenefitPayoutTypeDiv"); 
			var incomePayoutFrequencyDiv=document.getElementById(namespace+"incomePayoutFrequencyDiv"); 
			
			if($(policyOptionJEl).val()=="6"){
				$(incomeStartPointDiv).parent().hide();
				$(incomeDurationdiv).parent().hide();
				
				$(incomePayoutFrequencyDiv).parent().hide();
				$(incomePayoutFrequencyJEl).val("1");
				
				if(!$(incomeBenefitPayoutTypeJEl).val()){
					document.getElementById(incomeBenefitPayoutTypeEl).selectedIndex=0;
				}
				$(incomeBenefitPayoutTypeDiv).parent().show();
			}
			else{
				$(incomeStartPointDiv).parent().show();
				$(incomeDurationdiv).parent().show();
				$(incomePayoutFrequencyDiv).parent().show();
				if(!$(incomeDurationJEl).val()){
					document.getElementById(incomeDurationEl).selectedIndex=0;
				}
				$(incomeBenefitPayoutTypeDiv).parent().hide();
			}
			updateFamilyIncomeBenefit();
		}
		/**
		 * render Payfor value based on policy option value
		 */			
			updatePayForFieldValue =function(isOnLoad){
				var currentPayForValue=$(policyForJEl).val();
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var age = calculateAge(dateOfBirthInputVal);
				var payForList = new Map();
			    var policyOptionValue=document.getElementById(policyOptionEl).value
			    var isfamilyIncomebnftApplied=false;
			    var islumpSumBnftApplied =false;
			   
			    if($(lumpSumBenefitJEl).val()!="23"){
			    	islumpSumBnftApplied=true;
			    }
			    if($(familyIncomeBenefitJEl).val()!="3"){
			    	isfamilyIncomebnftApplied=true;
			    	islumpSumBnftApplied=true;
			    }
			    
			    gisRelFieldValues.forEach(field=>{
			    		if($(policyOptionJEl).val()=="5"){
			    			if(age<=field.age &&  isfamilyIncomebnftApplied==field.familyIncomeBenefitApplied && islumpSumBnftApplied==field.lumpSumBenefitApplied && field.incomeStartPoint){
								payForList.set(field.payFor.key,field.payFor);
			                }
			    		}else{
			    			if(age<=field.age &&  isfamilyIncomebnftApplied==field.familyIncomeBenefitApplied && islumpSumBnftApplied==field.lumpSumBenefitApplied && field.incomeStartPoint.length==0){
								payForList.set(field.payFor.key,field.payFor);
			                }
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
			
			//var selectedPayFor =  payForList.filter(payForOption => payForOption["name"] == currentPayForValue);
								if(payForList.has(currentPayForValue)) {
									$(policyForJEl).val(currentPayForValue);
								}
								
								
			if(isOnLoad){
				$(policyForJEl).val(currentPayForValue);
			}
		  }
			updateISPFieldValues =function(isOnload){
					var currentISPValue=$(incomeStartPointJEl).val();
					var dateOfBirthInputVal = $(dateOfBirthJEl).val();
					var age = calculateAge(dateOfBirthInputVal);
					var ISPList = new Map();
					var payForValue=document.getElementById(policyForEl).value;
				    
				    var isfamilyIncomebnftApplied=false;
				    var islumpSumBnftApplied =false;
				    
				    if($(lumpSumBenefitJEl).val()!="23"){
				    	islumpSumBnftApplied=true;
				    }
				    if($(familyIncomeBenefitJEl).val()!="3"){
				    	isfamilyIncomebnftApplied=true;
				    	islumpSumBnftApplied=true;
				    }
				    gisRelFieldValues.forEach(field=>{
								if(age<=field.age &&  isfamilyIncomebnftApplied==field.familyIncomeBenefitApplied &&
										islumpSumBnftApplied==field.lumpSumBenefitApplied && field.payFor.key==payForValue && field.incomeStartPoint){
									field.incomeStartPoint.forEach(innerField=>{
										ISPList.set(innerField.key,innerField);
									} )
									
				                }
				            })
									      
					const sortedISP = Array.from(ISPList).sort(([keyA, valueA], [keyB, valueB]) => {
							if (Number(keyA) < Number(keyB)) {
								return -1;
							} else if (Number(keyA) > Number(keyB)) {
								return 1;
							} else {
								return 0;
							}
						});
				    ISPList = new Map(sortedISP);
						
				    var ISPdiv=document.getElementById(incomeStartPointEl);
				    ISPdiv.innerHTML="";
				    ISPList.forEach(function(option, key){
				   
				    const ISPOptionElement = document.createElement("option");
				    	ISPOptionElement.setAttribute("value",key );
				    	ISPOptionElement.appendChild(document.createTextNode(option.name));
				    ISPdiv.appendChild(ISPOptionElement);
				})
				if(ISPList.has(currentISPValue)) {
									$(incomeStartPointJEl).val(currentISPValue);
								}
				if(isOnload){
					$(incomeStartPointJEl).val(currentISPValue);
				}
			}
			updatePolicyTerm =function(){
				
				var ISPValue=Number($(incomeStartPointJEl).find("option:selected").text());
				var incomeDurationValue=Number($(incomeDurationJEl).find("option:selected").text());
				
				if($(policyOptionJEl).val()=="5"){
					$(policyTermJEl).val(ISPValue+incomeDurationValue-1) ;
					$("#"+namespace+"policyTermValue").html(ISPValue+incomeDurationValue-1) ;
					document.getElementById(namespace+"policyTermValue").dataset.policyTerm=ISPValue+incomeDurationValue-1;
				}
				else{
					$(policyTermJEl).val("40") ;
					$("#"+namespace+"policyTermValue").html("40") ;
					document.getElementById(namespace+"policyTermValue").dataset.policyTerm="40";
				}
			}
			/**
			 * update family income benefit  based on codition
			 */
			updateFamilyIncomeBenefit =function(){
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var age = calculateAge(dateOfBirthInputVal);
				var familyIncomeBenefitDiv=document.getElementById(namespace+"familyIncomeBenefitDiv"); 
				
				if($(policyOptionJEl).val()=="5" && age>50){
					$(familyIncomeBenefitJEl).val("3");
					$(familyIncomeBenefitDiv).parent().hide();
				}
				else{
					$(familyIncomeBenefitDiv).parent().show();
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
					updatePolicyOptionRelFieldValues()
					updatePayForFieldValue();
					updateISPFieldValues();
					updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);
					
				});
				
				$(policyForJEl).on("change", function(){
					updateISPFieldValues();
					updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investmentAmountJEl).blur(function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				/*$(investmentAmountJEl).on("keyup", populateAmountInWord);*/
				
				$(paymentOptionJEl).on("change", function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomeDurationJEl).on("change", function(){
					updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomeStartPointJEl).on("change", function(){
					//updateMinimumInvestmentAmount();
					updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investingForJEl).on("change", onInvestingForChange);
						
				$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
		
				$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
				
				$(familyModalCloseJEl).click(familyModalCloseHanlder);
				
				$(familyIncomeBenefitJEl).on("change", function(){
					updatePayForFieldValue();
					updateISPFieldValues();
					updatePolicyTerm();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomePayoutFrequencyJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomeBenefitPayoutTypeJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(lumpSumBenefitJEl).on("change", function(){
					updatePayForFieldValue();
					updateISPFieldValues();
					updatePolicyTerm();
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
				
				updateCustomizePage(selectedRidersList, false);
				$(familyModalJEl).modal("hide");
				
				//Update Your Details Header after Saving Family Details
				updateYourDetailsHeader();
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
				console.log(liDetails);
				console.log(proposerDetails);
				return {
					"liDetails" : liDetails,
					"proposerDetails" : proposerDetails
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
					biRequestData[namespace + "liDetails"] = JSON.stringify(basicDetails["liDetails"]);
					biRequestData[namespace + "proposerDetails"] = JSON.stringify(basicDetails["proposerDetails"]);
					biRequestData[namespace + "product"] =  JSON.stringify(getBIRequestProduct(requestedRidersList, loadDefaultValues));
					try {
						const result = await $.ajax({
							url : edelweissGenerateBIURL,
							dataType : "json",
							data : biRequestData,
							type : "POST"
						});
							if(result.status = "success") {
								var biResponse = result.data;
								var biResponseData = biResponse["data"];
								
								console.log("-------responsejsonBi-----"+biResponseData);
								var hasValidationErrors = handleInputValidationStatus(biResponseData);
								if(!hasValidationErrors) {
									renderRiderPremiumAmount(biResponseData);
									updateSelectedRiderPrice();
									updatePlanPremiumDetails();
									renderMaturityBenefitsBreakup(biResponseData);
									renderIncomeBenefitsBreakup(biResponseData);
									
									updateRiderPremiumDropdowns(biResponseData["BIJson"][0].SA);
									
									$(illustrationURLJEl).val(biResponseData["Url"]);
									
									if(biResponseData["QuotationId"]) {
										$(quotationIdJEl).val(biResponseData["QuotationId"])
									}
									
									var inputValidationStatusArray = biResponseData["InputValidationStatus"];
									//updateLMSLead();
									
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
				
				var productBIJson = biResponseData["BIJson"];
				var tableBody = document.getElementById("planMaturityBenefitsBreakupTableBody");
				tableBody.innerHTML = "";
				productBIJson.forEach(biJson=>{
						
					const tableRecord = document.createElement("tr");
					var policyYearData = document.createElement("td");
					var annualPremiumData = document.createElement("td");
					var incomeBenefitPayoutData = document.createElement("td");
					var maturityBenefitData = document.createElement("td");
					var deathBenefitData = document.createElement("td");
						
					policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
					tableRecord.appendChild(policyYearData);
					
					annualPremiumData.appendChild(document.createTextNode(biJson.ANN_PREM_YEARLY!=0?rupeeSignLabel.concat(Math.ceil(biJson.ANN_PREM_YEARLY).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(annualPremiumData);
						
					incomeBenefitPayoutData.appendChild(document.createTextNode(biJson.INCOME_BENEFIT_FINAL!=0?rupeeSignLabel.concat(Math.ceil(biJson.INCOME_BENEFIT_FINAL).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(incomeBenefitPayoutData);
						
					maturityBenefitData.appendChild(document.createTextNode(biJson.MB_G!=0?rupeeSignLabel.concat(Math.ceil(biJson.MB_G).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(maturityBenefitData);
					
					deathBenefitData.appendChild(document.createTextNode(biJson.DB_G!=0?rupeeSignLabel.concat(Math.ceil(biJson.DB_G).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(deathBenefitData);
						
					tableBody.appendChild(tableRecord);
				});
			}
			/**
			 * Set total guaranteed income amount 
			 * populate incomeBenefits breakup table 
			 */
			function renderIncomeBenefitsBreakup(biResponseData){
				
				var productBIJson = biResponseData["BIJson"];
				
				var benefitAmount = 0;
				var benefitAmountSpan = document.getElementById(namespace + "totalGuaranteedBenefitAmount");
				
				var tableBody = document.getElementById("planIncomeBenefitsBreakupTableBody");
				
				tableBody.innerHTML = "";
				productBIJson.forEach(biJson=>{
					
					if(biJson.INCOME_BEN_PAY_OUT != 0 || biJson.MATURITY_BEN !=0){	
						
						const tableRecord = document.createElement("tr");
						var policyYearData = document.createElement("td");
						var incomeBenefitPayoutData = document.createElement("td");
						var maturityBenefitData = document.createElement("td");
						
						policyYearData.appendChild(document.createTextNode(biJson.POLICYYEAR));
						tableRecord.appendChild(policyYearData);
					
						incomeBenefitPayoutData.appendChild(document.createTextNode(biJson.INCOME_BENEFIT_FINAL!=0?rupeeSignLabel.concat(Math.ceil(biJson.INCOME_BENEFIT_FINAL).toLocaleString("en-IN")):'-'))
						tableRecord.appendChild(incomeBenefitPayoutData);
							
						maturityBenefitData.appendChild(document.createTextNode(biJson.MB_G!=0?rupeeSignLabel.concat(Math.ceil(biJson.MB_G).toLocaleString("en-IN")):'-'))
						tableRecord.appendChild(maturityBenefitData);
						
						tableBody.appendChild(tableRecord);
						
						benefitAmount = benefitAmount + biJson.INCOME_BENEFIT_FINAL +biJson.MB_G ;
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
				var annualPremium = Number(getInvestmentAmount());
				var  paymentOptionVal= $(paymentOptionJEl).val();
				
				
				switch (paymentOptionVal) {
				case "2":
					annualPremium = 2 * annualPremium / 1.024;
					break;
				case "3":
					annualPremium = 4 * annualPremium / 1.04;
					break;
				case "4":
					annualPremium = 12 * annualPremium / 1.056
				}
				var NriDeclaration="N";
				var ZP_NATIONALITY="1";
				if(isNRI == 'true'){
					NriDeclaration="Y" ,
					ZP_NATIONALITY = "2";
				}
				var incomePayoutFrequency=$(incomePayoutFrequencyJEl).val() ;
				switch (incomePayoutFrequency) {
				case "1":
					incomePayoutFrequency = "32";
					break;
				case "2":
					incomePayoutFrequency = "33";
					break;
				case "3":
					incomePayoutFrequency = "34";
					break;
				case "4":
					incomePayoutFrequency = "35";
				}
				var product = {
					"category": "1",
					"FamilyIncomeBenefitOption": $(familyIncomeBenefitJEl).val() ,
					"IncomeBenefitPayoutFrequency": incomePayoutFrequency ,
					"INPUT_MODE": $(paymentOptionJEl).val() ,
					"PR_ANNPREM": annualPremium ,
					"PR_ID": $(productCodeJEl).val() ,
					"PR_PPT": payFor,
					"PR_PT": "0",
					
					
					"NriDeclaration": NriDeclaration ,
					"ZP_NATIONALITY" : ZP_NATIONALITY,
					
					
					//Default values
					"REQUESTSOURCE": "4" ,
					"PR_SAMF": "0" ,
					"PR_ModalPrem": "0" ,
					"PR_SA":"0" ,
					"PR_CHANNEL": "1" ,
					"CashFlow": "N" ,
					"PR_MI": "0" ,
					"isPdfByte":  "Y",
					
					"policyOPtions" : getBIRequestPolicyOptions(),
					//"riderDetails" : JSON.parse('{"ADBRider":"","ATPDRider":"","CriticalIllnessRider":"","HospitalityCashBenefitRider":"","PayorWaiverBenefitRider":"","PWB":"","WaiverOfPremiumRider":""}')
				
					"riderDetails" : generateSelectedRidersRequestBody(requestedRidersList, loadDefaultValues)
					
				}
				console.log(product);
				return product;
			}
			/**
			 * Generate BI Request Policy Options
			 */
			getBIRequestPolicyOptions = function() {
				
				if($(policyOptionJEl).val()=="5"){
					$(incomeBenefitPayoutTypeJEl).val("");;
				}
				if($(policyOptionJEl).val()=="6"){
					$(incomeDurationJEl).val("");
					if($(policyForJEl).val()!="12"){
						
						const ISPOptionElement = document.createElement("option");
						ISPOptionElement.setAttribute("value","13" );
						ISPOptionElement.appendChild(document.createTextNode("10"));
						
						document.getElementById(incomeStartPointEl).appendChild(ISPOptionElement);
						
						$(incomeStartPointJEl).val("13");
					}else{
						const ISPOptionElement = document.createElement("option");
						ISPOptionElement.setAttribute("value","18" );
						ISPOptionElement.appendChild(document.createTextNode("15"));
						
						document.getElementById(incomeStartPointEl).appendChild(ISPOptionElement);
						
						$(incomeStartPointJEl).val("18");
					}
				}
				var policyOptions = {
					"IncomeDuration":  $(incomeDurationJEl).val(),
					"IncomeBenefitPayoutType":$(incomeBenefitPayoutTypeJEl).val(),
					"LumpsumBenefit": $(lumpSumBenefitJEl).val(),
					"PlanOption":"4",
					"IncomeOption":$(policyOptionJEl).val(),
					"IncomeStartOption":$(incomeStartPointJEl).val()
				}
				return policyOptions;
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
					if (/^([a-zA-Z]{2,}\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)/.test(value)) {
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
				
				var updateLMSLeadData = {};
				updateLMSLeadData[namespace + "assuranceFullName"] = $(investingForFullNameJEl).val();
				updateLMSLeadData[namespace + "assuranceDob"] = $(investingForDOBJEl).val();
				updateLMSLeadData[namespace + "assuredRelation"] = selectedFamilyRadioVal;
				updateLMSLeadData[genderEl] = $("input[type=radio][name="+genderEl+"]:checked").val();
				updateLMSLeadData[fullNameEl] = $(fullNameJEl).val();
				updateLMSLeadData[dateOfBirthEl] = $(dateOfBirthJEl).val();
				updateLMSLeadData[investmentObjectiveEl] = $("input[type=radio][name="+investmentObjectiveEl+"]:checked").val();
				updateLMSLeadData[emailEl] = $(emailJEl).val();
				updateLMSLeadData[mobileNumberEl] = $(mobileNumberJEl).val();
				updateLMSLeadData[namespace + "SumAssured"] = $(totalReturnsJEl).val();
				updateLMSLeadData[namespace + "Product_Code"] = $(productCodeJEl).val();
				updateLMSLeadData[namespace + "BIQuotationNumber"] = $(quotationIdJEl).val();
				updateLMSLeadData[namespace + "BI_PDF_Path"] = $(illustrationURLJEl).val();
				updateLMSLeadData[namespace + "TotalPremiumAmount"] = $(investmentAmountJEl).val();
				updateLMSLeadData[namespace + "PolicyTerm"] = $(policyTermJEl).val();
				updateLMSLeadData[namespace + "PremiumPaymentTerm"] = $(policyForJEl).val();
				updateLMSLeadData[namespace + "ProductName"] = $(productNameJEl).val();
				updateLMSLeadData[namespace + "PolicyOption"] = policyOptionDisplayName;
				updateLMSLeadData[namespace + "Recommended_Objective"] = investmentObjectiveDisplayName;
				
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
						console.log(response);
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
					var isEntryAgeValid = validateEntryAge(riderId);
					var isCriticalIllnessAgeValid = validateCriticalIllnessEntryAge(riderId);

					document.getElementById(namespace+"selectedRider_"+riderId).dataset.isAvailable=isInvestingForValid && isFamilyIncomeBenefitValid && isEntryAgeValid && isCriticalIllnessAgeValid;
				});
			}
			
			
			validateEntryAge = function(riderId){
				var isRiderAvailable=false;
				if(riderId=="50007"){
					var dateOfBirthInputVal = $(dateOfBirthJEl).val();
					var age = calculateAge(dateOfBirthInputVal);
					var PPT = Number($(policyForJEl).val());
					console.log("PPT+age="+PPT+age);
					if(PPT+age>70|| age>60){
						isRiderAvailable= false;
					}
					else{
						isRiderAvailable= true;
					}
				}
				else{
					isRiderAvailable= true
				}
				$("#"+namespace+"WOPEntryAgeError_"+riderId).hide();

				if (!isRiderAvailable && age>60){
			    	$("#"+namespace+"WOPAgeError_"+riderId).show();
			    	document.getElementById(namespace+"selectedRider_"+riderId).checked=false;
			    	addRemoveRider(namespace+"selectedRider_"+riderId, false);
			    }else if(!isRiderAvailable){
			    	$("#"+namespace+"WOPEntryAgeError_"+riderId).show();
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
			 * Rider Validation: Family Income benefit 
			 */
			validateFamilyIncomeBenefit = function(riderValidation,riderId){
				if(!document.getElementById(namespace+"familyIncomeBenefit")){
					return;
				}
				var investingFor=document.getElementById(namespace+"familyIncomeBenefit").value;

			    $("#"+namespace+"familyIncomeBenefitError_"+riderId).hide();
			    
			    var isRiderAvailable = false;
			    if(investingFor==3 || riderValidation.isAvailableWithFib){
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
					
				    if(availableRider.dataset.isAvailable == "false" || !(loadDefaultValues || requestedRidersList.has(availableRider.value))){
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
				updatePolicyOptionRelFieldValues();
				updatePayForFieldValue(true);
				updateISPFieldValues(true);
				updatePolicyTerm();
				
				// Load User Selected Riders
				 
				loadCustomersSavedRiders();
				populateAmountInWord();

				updateCustomizePage(selectedRidersList, true);
				
				updateInvestmentAmountLocaleIN();
				initFamilyModal();
				initFamilyModalData();
				initFamilyModalFormValidation();
				$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});
				
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



