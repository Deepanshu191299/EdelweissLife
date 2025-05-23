var pgiRelFieldValues;
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

	 	employeeCodeJEl = config.employeeCodeJEl,
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
		getIncomeForEl=config.getIncomeForEl,
		productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
		pgiRelFieldValues=config.pgiRelFieldValues,
		pgiPolicyOptionValues=config.pgiPolicyOptionValues,
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
		
		updatePolicyOptionValue=function(isOnLoad){
			var dateOfBirthInputVal = $(dateOfBirthJEl).val();
			var age = calculateAge(dateOfBirthInputVal);
			var policyOptionList = new Map();
		    console.log(pgiPolicyOptionValues);
		    var currentplanOptionValue=$(policyOptionJEl).val();
		   
		    pgiPolicyOptionValues.forEach(field=>{
						if((field.key=="4" && age>=40 && age<=50 ) || field.key!="4"){
		                	
							policyOptionList.set(field.key,field);
		                }
		            })
		    var policyOptiondiv=document.getElementById(policyOptionEl);
		    policyOptiondiv.innerHTML="";
		    policyOptionList.forEach(function(option, key){
		   
		    const policyOptionListElement = document.createElement("option");
		    policyOptionListElement.setAttribute("value",key );
		    policyOptionListElement.appendChild(document.createTextNode(option.name));
		    policyOptiondiv.appendChild(policyOptionListElement);
		})	
			if(isOnLoad ){
				if(currentplanOptionValue=="4" && age>=40 && age<=50 || currentplanOptionValue!="4" ){
					$(policyOptionJEl).val(currentplanOptionValue);
				}
			}
		}
		/**
		 * render Family Income benefit based on age condition
		 */
		updateFIB =function(isOnload){
			var dateOfBirthInputVal = $(dateOfBirthJEl).val();
			var age = calculateAge(dateOfBirthInputVal);
			var currentFIBValue = $(familyIncomeBenefitJEl).val();
			var familyIncomeBenefitDiv=document.getElementById(namespace+"familyIncomeBenefitDiv");
			
			if(age>=51 && age<=65) {    
				$(familyIncomeBenefitJEl).val("3") ; //key 3 is for No
				$(familyIncomeBenefitDiv).parent().hide(); 
			} else {
				$(familyIncomeBenefitDiv).parent().show();
			}
			if(isOnLoad){
				$(familyIncomeBenefitJEl).val(currentFIBValue);
			}
		}
		/**
		 * render Payfor value based on policy option value
		 */			
			updatePGIRelFieldValue =function(isOnLoad){
				var currentPayForValue=$(policyForJEl).val();
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var age = calculateAge(dateOfBirthInputVal);
				var payForList = new Map();
			    var policyOptionValue=document.getElementById(policyOptionEl).value
			    console.log(pgiRelFieldValues);
			    pgiRelFieldValues.forEach(field=>{
							if(field.policyOption.key==policyOptionValue){
			                	
								payForList.set(field.payFor.key,field.payFor);
			                }
			            })
			    var payFordiv=document.getElementById(policyForEl);
			    payFordiv.innerHTML="";
			    payForList.forEach(function(option, key){
			   
			    const payForOptionElement = document.createElement("option");
					payForOptionElement.setAttribute("value",key );
					payForOptionElement.appendChild(document.createTextNode(option.name));
			payFordiv.appendChild(payForOptionElement);
			})
			if(isOnLoad){
				$(policyForJEl).val(currentPayForValue);
			}
			if($(policyOptionJEl).val()=="1"){
				var getIncomeForDiv=document.getElementById(namespace+"getIncomeForDiv");
				$(getIncomeForDiv).parent().hide();
				/*getIncomeForDiv.style.display =  "none";*/
				var incomePayoutFrequencyDiv=document.getElementById(namespace+"incomePayoutFrequencyDiv");
				$(incomePayoutFrequencyDiv).parent().hide();
				/*incomePayoutFrequencyDiv.style.display =  "none";*/
			}else{
				var getIncomeForDiv=document.getElementById(namespace+"getIncomeForDiv");
				$(getIncomeForDiv).parent().show();
				/*getIncomeForDiv.style.display =  "block";*/
				var incomePayoutFrequencyDiv=document.getElementById(namespace+"incomePayoutFrequencyDiv");
				$(incomePayoutFrequencyDiv).parent().show();
			}
			if($(policyOptionJEl).val()!="3"){
				var lumpSumBenefitDiv=document.getElementById(namespace+"lumpSumBenefitDiv");
				$(lumpSumBenefitDiv).parent().hide();
				/*lumpSumBenefitDiv.style.display =  "none";*/
				var maturityPayoutoption=document.getElementById(namespace+"maturityPayoutOptionDiv");
				$(maturityPayoutoption).parent().hide();
				/*maturityPayoutoption.style.display =  "none";*/
			}
			if($(policyOptionJEl).val()=="3"){
				var lumpSumBenefitDiv=document.getElementById(namespace+"lumpSumBenefitDiv");
				$(lumpSumBenefitDiv).parent().show();
				/*lumpSumBenefitDiv.style.display =  "block";*/
				var maturityPayoutoption=document.getElementById(namespace+"maturityPayoutOptionDiv");
				$(maturityPayoutoption).parent().hide();
				/*maturityPayoutoption.style.display =  "none";*/
			}
			if($(policyOptionJEl).val()=="4"){
				var maturityPayoutoption=document.getElementById(namespace+"maturityPayoutOptionDiv");
				$(maturityPayoutoption).parent().show();
				/*maturityPayoutoption.style.display =  "block";*/
			}
		  }
			/**
			 * render policy Term  value based on get Income For value
			 */		
			updateGetIncomeForValue =function(isOnLoad){
				var currentGIFValue=$(getIncomeForJEl).val();
				var getIncomeForList = new Map();
				var policyOptionValue=document.getElementById(policyOptionEl).value
			    var payForValue=document.getElementById(policyForEl).value
						pgiRelFieldValues.forEach(field=>{
			                if(field.payFor.key==payForValue && field.policyOption.key==policyOptionValue){
			                	getIncomeForList.set(field.getIncomeFor?field.getIncomeFor.key:'',field.getIncomeFor);
			                }
			            })
			    var getIncomeFordiv=document.getElementById(getIncomeForEl);
				getIncomeFordiv.innerHTML="";
				getIncomeForList.forEach(function(option, key){
			    
			    const getIncomeForElement = document.createElement("option");
			    	getIncomeForElement.setAttribute("value",key );
			    	getIncomeForElement.appendChild(document.createTextNode(option?option.name:''));
				getIncomeFordiv.appendChild(getIncomeForElement);
			})
			if(currentGIFValue && getIncomeForList.has(currentGIFValue)){
				$(getIncomeForJEl).val(currentGIFValue);
			}
		}
			/**
			 * render GetIncomeFor value based on Pay for value
			 */
			updatePolicyTermValue =function(isOnLoad){
					var currentPolicyTermValue=$(policyTermJEl).val();
					var policyTermList = new Map();
					var policyOptionValue=document.getElementById(policyOptionEl).value
				    var payForValue=document.getElementById(policyForEl).value
				    var getIncomeForValue=document.getElementById(getIncomeForEl).value
							pgiRelFieldValues.forEach(field=>{
				                if(field.payFor.key==payForValue && field.policyOption.key==policyOptionValue && (field.getIncomeFor?field.getIncomeFor.key==getIncomeForValue:true)){
				                	policyTermList.set(field.policyTerm.key,field.policyTerm);
				                }
				            })
				    var policyTermdiv=document.getElementById(policyTermEl);
					policyTermdiv.innerHTML="";
					policyTermList.forEach(function(option, key){
				    
				    const policyTermElement = document.createElement("option");
				    	policyTermElement.setAttribute("value",key );
				    	policyTermElement.appendChild(document.createTextNode(option.name));
				    policyTermdiv.appendChild(policyTermElement);
				})
				if($(policyOptionJEl).val()=="4"){
					
					var dateOfBirthInputVal = $(dateOfBirthJEl).val();
					var age = calculateAge(dateOfBirthInputVal);

					var policyTermdiv=document.getElementById(policyTermEl);
					policyTermdiv.innerHTML="";
					
					const PTOptionElement = document.createElement("option");
					PTOptionElement.setAttribute("value",80-age );
					PTOptionElement.appendChild(document.createTextNode(80-age));
					
					document.getElementById(policyTermEl).appendChild(PTOptionElement);
					
					$(policyTermJEl).val(80-age);
				}
				if(currentPolicyTermValue && policyTermList.has(currentPolicyTermValue)){
					$(policyTermJEl).val(currentPolicyTermValue);
				}
		}
			/**
			 * Save Investment Details and Policy Details for Customize Form Field Changes
			 */
			saveCustomerInvestmentDataHanlder = function() {

				showLoader(); 

				console.log("url of save "+saveCustomerInvestmentDataURL)
				$.ajax({
					url:saveCustomerInvestmentDataURL,
					type:"post",
					data: $(customizeFormJEl).serialize(),
					dataType: "json",
					async: true,
					success: function(response) {
						console.log(response);
						if(response.status == "success") {
							console.log("saving data on change field")
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
					updatePGIRelFieldValue();
					updateGetIncomeForValue();
					updatePolicyTermValue();
					updateCustomizePage(selectedRidersList, false);
					
				});
				
				$(policyForJEl).on("change", function(){
					updateGetIncomeForValue();
					updatePolicyTermValue();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(getIncomeForJEl).on("change", function(){
					updatePolicyTermValue();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investmentAmountJEl).blur(function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				/*$(investmentAmountJEl).on("keyup", populateAmountInWord);*/
				$(policyTermJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(paymentOptionJEl).on("change", function(){
					updateMinimumInvestmentAmount();
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(investingForJEl).on("change", onInvestingForChange);
				/*$(investingForJEl).on("change", function(){
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
						
						deleteFamilyDetails();
					}
				});
*/				
				$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
				$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
				$(familyModalCloseJEl).click(familyModalCloseHanlder);
				
				
				$(familyIncomeBenefitJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(isEdelweissEmployeeJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(incomePayoutFrequencyJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(maturityPayoutOptionJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$(lumpSumBenefitJEl).on("change", function(){
					updateCustomizePage(selectedRidersList, false);
				});
				
				$("#"+ namespace + "familyDetailsForm").submit(saveFamilyDetails);
				
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
				updateYourDetailsHeader();
				
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
				
				var hasValidationErrors = await generateBIRequest(selectedRidersList, false);
				console.log("submit called ......");
				var hasValidationErrorEmployeeCode = await validateInput();
				console.log("hasValidationErrorEmployeeCode---->",hasValidationErrorEmployeeCode);
				if(!hasValidationErrors && hasValidationErrorEmployeeCode) {
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
					if(inputValidation.ErrorMessage.length && inputValidation.ProductId == $(productCodeJEl).val()){
						document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=rupeeSignLabel.concat(Math.round(0));
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
								console.log("-------responsejsonBi-----",biResponseData);
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
									updateLMSLead();
									
									//updateFormData(biResponseData, percentReturnAmountObj);
								}
							} else {
								$(errorMessageModalJEl).modal("show");
							}
							hideLoader();
							return hasValidationErrors;
						} catch(error) {
							hideLoader();
							document.getElementById(namespace+"totalGuaranteedBenefitAmount").innerHTML=Math.round(0);
							$("#"+namespace+"proceedBtn").prop('disabled', true);
					 		$("#"+namespace+"premiumPayDetails").addClass('disabledbutton');
							$(errorMessageModalJEl).modal("show");
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
						
					incomeBenefitPayoutData.appendChild(document.createTextNode(biJson.INCOME_BEN_PAY_OUT!=0?rupeeSignLabel.concat(Math.ceil(biJson.INCOME_BEN_PAY_OUT).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(incomeBenefitPayoutData);
						
					maturityBenefitData.appendChild(document.createTextNode(biJson.MATURITY_BEN!=0?rupeeSignLabel.concat(Math.ceil(biJson.MATURITY_BEN).toLocaleString("en-IN")):'-'))
					tableRecord.appendChild(maturityBenefitData);
					
					deathBenefitData.appendChild(document.createTextNode(biJson.DEATH_BENEFIT!=0?rupeeSignLabel.concat(Math.ceil(biJson.DEATH_BENEFIT).toLocaleString("en-IN")):'-'))
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
					
						incomeBenefitPayoutData.appendChild(document.createTextNode(biJson.INCOME_BEN_PAY_OUT!=0?rupeeSignLabel.concat(Math.ceil(biJson.INCOME_BEN_PAY_OUT).toLocaleString("en-IN")):'-'))
						tableRecord.appendChild(incomeBenefitPayoutData);
							
						maturityBenefitData.appendChild(document.createTextNode(biJson.MATURITY_BEN!=0?rupeeSignLabel.concat(Math.ceil(biJson.MATURITY_BEN).toLocaleString("en-IN")):'-'))
						tableRecord.appendChild(maturityBenefitData);
						
						tableBody.appendChild(tableRecord);
						
						benefitAmount = benefitAmount + biJson.INCOME_BEN_PAY_OUT +biJson.MATURITY_BEN ;
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
				var PR_PT =$(policyTermJEl).val();
				
				if($(policyOptionJEl).val()=="4"){
					PR_PT="80-@LI_ENTRY_AGE";
				}
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
				var prsa=  Number(getInvestmentAmount())*10
				var NriDeclaration="N";
				var ZP_NATIONALITY="1";
				if(isNRI == 'true'){
					NriDeclaration="Y" ,
					ZP_NATIONALITY = "2";
				}
				var product = {
					"category": $(isEdelweissEmployeeJEl).val(),
					"EmployeeCode": $(employeeCodeJEl).val(),
					"FamilyIncomeBenefitOption": $(familyIncomeBenefitJEl).val() ,
					"IncomeBenefitPayoutFrequency": $(incomePayoutFrequencyJEl).val() ,
					"INPUT_MODE": $(paymentOptionJEl).val() ,
					"PR_ANNPREM": annualPremium ,
					"PR_ID": $(productCodeJEl).val() ,
					"PR_PPT": payFor,
					"PR_PT":PR_PT ,
					"PR_SA": prsa ,
					
					"NriDeclaration": NriDeclaration ,
					"ZP_NATIONALITY" : ZP_NATIONALITY,
					
					
					//Default values
					"REQUESTSOURCE": "4" ,
					"PR_SAMF": "0" ,
					"PR_ModalPrem": "0" ,
					"PR_CHANNEL": "1" ,
					"CashFlow": "N" ,
					"PR_MI": "0" ,
					"isPdfByte":  "N",
					
					"policyOPtions" : getBIRequestPolicyOptions(),
					"riderDetails" : generateSelectedRidersRequestBody(requestedRidersList, loadDefaultValues)
					
				}
				console.log(product);
				return product;
			}
			/**
			 * Generate BI Request Policy Options
			 */
			getBIRequestPolicyOptions = function() {
				var a =$(getIncomeForJEl).val();
				var IncomeBenefitPayoutDuration="";
				if(a=="8"){
					IncomeBenefitPayoutDuration="10";
				}else if(a=="10"){
					IncomeBenefitPayoutDuration="11";
				}else if(a=="12"){
					IncomeBenefitPayoutDuration="12";
				}else if(a=="15"){
					IncomeBenefitPayoutDuration="13";
				}else if(a=="20"){
					IncomeBenefitPayoutDuration="14";
				}else if(a=="25"){
					IncomeBenefitPayoutDuration="15";
				}else if(a=="30"){
					IncomeBenefitPayoutDuration="16";
				}
				
				var lumpsumBenifit=$(lumpSumBenefitJEl).val();
				if(lumpsumBenifit=="1"){
					lumpsumBenifit="7";
				}else if(lumpsumBenifit=="3"){
					lumpsumBenifit="8";
				}
				var policyOptions = {
					"IncomeBenefitPayoutDuration":  IncomeBenefitPayoutDuration,
					"IncomeBenefitPayoutType": $(maturityPayoutOptionJEl).val(),
					"LumpsumBenefit": lumpsumBenifit,
					"PlanOption":$(policyOptionJEl).val()
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
					if(PPT+age>70 || age>60){
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
				var dateOfBirthInputVal = $(dateOfBirthJEl).val();
				var age = calculateAge(dateOfBirthInputVal);
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
				updatePolicyOptionValue(true);
				updatePGIRelFieldValue(true);
				updateGetIncomeForValue(true);
				updatePolicyTermValue(true);
				updateFIB(true);
				/*
				 * Load User Selected Riders
				 */
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
			
	// validate emplyeeCode input field on oninput.
		function validateInput() {
			console.log("validateInput function called.....");
			let inputHasError = true;
			const errorMessage = $('#error-message');
			var value  = $('#'+namespace+'employeeCode').val();
			console.log("$(isEdelweissEmployeeJEl).val()------->",$(isEdelweissEmployeeJEl).val());
			var isEdelweissEmployee = $(isEdelweissEmployeeJEl).val();
			if(isEdelweissEmployee == 1 ){
				console.log("inputHasError -------->",inputHasError);
				return inputHasError;
			}
			console.log("value "+value);
			if (value.length > 6 || value.length <= 0 || value.length != 6) {
			errorMessage.show();
			inputHasError = false;
			} else {
				errorMessage.hide();
			}
			
			return inputHasError;
		 }
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



