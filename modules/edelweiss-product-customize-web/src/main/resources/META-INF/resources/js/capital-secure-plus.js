var percentReturnAmountObj = {};
(function($, edelweissProductCustomizePortlet) {
	
	function viewWealthSecurePlusCustomize(config){
		var namespace = config.namespace,
			customizeFormJEl = "#" + namespace + "customizeForm",
			policyOptionEl = namespace + "policyOption",
			investingForJEl = "#" + namespace + "investingFor",
			policyTermEl = namespace + "policyTerm",
			payForEl = namespace + "payFor",
			investmentAmountEl = namespace + "investmentAmount",
			swpEl = namespace + "swp",
			policyOptionJEl = "#" + policyOptionEl,
			policyTermJEl = "#" + policyTermEl,
			payForJEl = "#" + payForEl,
			paymentOptionEl = namespace + "paymentOption",
			paymentOptionJEl = "#" + paymentOptionEl,
			fundManagementJEl = "#" + namespace + "fundManagement",
			swpJEl = "#" + swpEl,
			investmentAmountJEl =  "#" + investmentAmountEl,
			removeLittleChampWrapperJEl = "#removeLittleChampWrapper",
			benchMarkAndReturnSelectJEl = "#" + namespace + "benchMarkAndReturnSelect",
			returnAmtValJEl = "#returnAmtVal",
			totalReturnsValueJEl = "#" + namespace + "totalReturnsValue",
			addLittleChampBenefitJEl = "#" + namespace + "addLittleChampBenefit",
			doNotaddOnJEl = "#" + namespace + "doNotaddOn",
			projectedReturnFundAllocation = "#" + namespace + "projectedReturnFundAllocation";
			fundDataTableJEl = "#fundDataTable";
			customizeFundLinkJEl = "#customizeFundLink",
			featuresLinkJEl = "#featuresLink",
			customizeFundTableJEl = "#customizeFundTable",
			edelweissCustomizeFundsURL = config.edelweissCustomizeFundsURL,
			updateLittleChampBenefitURL = config.updateLittleChampBenefitURL,
			benchMarkAndReturnsObj = {},
			wrpAnnualPremium={},
			edelweissGenerateBIURL = config.edelweissGenerateBIURL,
			errorMessageModalJEl = "#errorMessageModal",
			chartLinkJEl = "#" + namespace + "chartLink",
			fundTableLinkJEl = "#" + namespace + "fundTableLink",
			removeLittleChampJEl = "#" + namespace + "removeLittleChamp",
			littleChampModalJEl = "#" + namespace + "littleChampModal",
			highchartContainerEl = "highchart-container"
			highchartContainerJEl = "#" + highchartContainerEl,
			fundDataTableJEl = "#fundDataTable",
			totalReturnsJEl = "#" + namespace + "totalReturns",
			illustrationURLJEl = "#" + namespace + "illustrationURL",
			quotationIdJEl = "#" + namespace + "quotationId",
			projectedReturnsDataJEl = "#" + namespace + "projectedReturnsData",
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
			breakUpBtnJEl = ".csp-breakUpBtn",
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
			mobileNumberEl = namespace + "mobileNumber",
			productNameJEl = "#" + namespace + "productName",
			breakUpBlockJEl = "#" + namespace + "breakUpBlock",
			investmentObjectiveEl = namespace + "investmentObjective",
			familyModalCloseJEl = "#" + namespace + "familyModalClose",
			amtInWordsJEl = "#amt-in-word",
			errorMessageContainerJEl = "#errorMessageContainer",
			saveFundDetailsURL = config.saveFundDetailsURL,
			saveCustomerInvestmentDataURL = config.saveCustomerInvestmentDataURL,
			deleteFamilyDetailsURL = config.deleteFamilyDetailsURL,
			customerFundAllocationDetailIdJEl = "#" + namespace + "customerFundAllocationDetailId",
			customerAllocatedFundDetailsJEl = "#" + namespace + "customerAllocatedFundDetails",
			customerInvestmentDetailsIdJEl = "#" + namespace + "customerInvestmentDetailsId",
			customerPolicyDetailsIdJEl = "#" + namespace + "customerPolicyDetailsId",
			customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
			swpWarpperJEl = "#" + namespace + "swpWarpper",
			personalDetailsJEl = "#personal-details",
			isPdfByteJEl = "#" + namespace + "isPdfByte",
			productTypeJEl = "#" + namespace + "productType",
			swpOptionsJEl = ".swp-options",
			fundValuetobeWithdrawnJEl = "#" + namespace + "fundValuetobeWithdrawn",
			policyYearFromWhichSWPPayableJEl = "#" + namespace + "policyYearFromWhichSWPPayable",
			swpFrequencyJEl = "#" + namespace + "swpFrequency",
			childSpouseIndividualModalJEl = "#" + namespace + "childSpouseIndividualModal",
 			childSpouseJointModalJEl = "#" + namespace + "childSpouseJointModal",
 			childSpouseIndividualDetailsFormJEl = "#" + namespace + "childSpouseIndividualDetailsForm",
			childSpouseJointDetailsFormJEl = "#" + namespace + "childSpouseJointDetailsForm",
			spouseChildFullNameEl = namespace + "spouseChildFullName",
			spouseChildDOBEl = namespace + "spouseChildDOB",
			spouseChildFullNameJEl = "#" + spouseChildFullNameEl,
			spouseChildDOBJEl = "#" + spouseChildDOBEl,
			spouseFullNameJEl = "#" + namespace + "spouseFullName",
			spouseDOBJEl = "#" + namespace + "spouseDOB",
			childFullNameJEl = "#" + namespace + "childFullName",
			childDOBJEl = "#" + namespace + "childDOB",
			childSpouseHeaderJEl = "#childSpouseHeader",
			childSpouseJointModalCloseJEl = "#childSpouseJointModalClose",
			childSpouseIndividualModalCloseJEl = "#childSpouseIndividualModalClose",
			saveJointFamilyButtonJEl = "#" + namespace + "saveJointFamilyButton",
			saveIndividualFamilyButtonJEl = "#" + namespace + "saveIndividualFamilyButton",
			savedPolicyOptionJEl = "#" + namespace + "savedPolicyOption",
			premiumAmountJEl = "#" + namespace +"premiumAmount", 
			editDetailsWrapperJEl = "#editDetailsWrapper",
			isLittleChampBenefitOptedJEl = "#" + namespace + "isLittleChampBenefitOpted",
			removeLittleChampBenefitLabelJEl = "#" + namespace + "removeLittleChampBenefit",
			breakUpContinueJEl = "#"+ namespace +"breakUpContinue";
			allowAlphaSpaceJEl = ".edelweiss-allow-aplha-space",
			assuredRelationVal = config.assuredRelationVal,
			wspPolicyTermPicklistData = JSON.parse(config.wspPolicyTermPicklistData),
			wspPayForPicklistData = JSON.parse(config.wspPayForPicklistData),
			productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
			productPolicyOptionRelData = config.productPolicyOptionRelData,
			productInvestingForRelData = config.productInvestingForRelData,
			defaultPolicyTerm = config.defaultPolicyTerm,
			defaultPayFor = config.defaultPayFor,
			loadFamilyDetailsURL = config.loadFamilyDetailsURL,
			isFormSubmitPendingJEl = config.isFormSubmitPendingJEl;
			LANG_MESSAGES = config.LANG_MESSAGES,
			boosterPremiumGraphLabel = LANG_MESSAGES["graph-label-booster-premium"],
			additionGraphLabel = LANG_MESSAGES["graph-label-addition"],
			fundsGraphLabel = LANG_MESSAGES["graph-label-funds"],
			benchmarkLabel = LANG_MESSAGES["benchmark"],
			sfinLable = LANG_MESSAGES["sfin-label"],
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
			ageBetween0to15ErrorMsg = LANG_MESSAGES["age-between-0-to-15-years"],
			yourChildsFullFetailsHearder = LANG_MESSAGES["your-childs-full-details"],
			yourSpousesFullFetailsHearder = LANG_MESSAGES["your-spouses-full-details"],
			enterYourSpouseNameLabel = LANG_MESSAGES["enter-your-spouse-name"],
			enterYourChildsNameLabel = LANG_MESSAGES["enter-your-childs-name"],
			yourSpousesDOBLabel = LANG_MESSAGES["your-spouses-date-of-birth"],
			yourChildsDOBLabel = LANG_MESSAGES["your-childs-date-of-birth"];
			
		var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
		var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
	
		var fundAllocationColTemplate = '<div class="allocation">' +
			'<a href="javascript:void(0);" class="decrease">-</a>' + 
			'<span class="allocationAmt-wrap">' +
				'<span class="allocationAmt" data-allocation="$[FUND_SHORTNAME]" data-fundname="$[FUND_NAME]">$[FUND_ALLOCATION]</span>' +
				'<span class="allocationAmt-percentSymb">%</span>' +
			'</span>' + 
			'<a href="javascript:void(0);" class="increase">+</a>' +
		'</div>';
		
		// Policy Option Picklist Keys which will be stored in LR Object
		var baseCoverKey = "baseCover";
		
		//Actual Value Mapping Which Send in LMS and BI
		var policyOptionData = {};
		policyOptionData[baseCoverKey] = "1";
		
		
		/**
		 * Load Policy Terms
		 */	
		initPolicyTerm = function(){
			
			for (var curPolicyTerm in wspPolicyTermPicklistData) {
				var policyOptionVal = $(policyOptionJEl).val();
				var selected = curPolicyTerm == defaultPolicyTerm ? " selected" : "";
			 	$(policyTermJEl).append('<option value="' + curPolicyTerm + '" ' + selected + '>' + curPolicyTerm + '</option>');
		  	}
			
		};
		
		/**
		 * Load Pay For Based on Policy Term
		 */
		onPolicyTermChange = async function(){
			$(payForJEl).find("option").remove();
			
			updatePayForDropdown(this.value);
			
			//initSWP();
			
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		};
		
		initSWP = function() {
			if(Number($(payForJEl).val()) >= 10) {
				$(swpWarpperJEl).show();
			} else {
				// Reset SWP to No
				$(swpJEl).val("6");
				$(swpWarpperJEl).hide();
			}
			
			//console.log("Selected SWP : " + $(swpJEl).find(":selected").data("name"));
			//console.log("Selected SWP : " + $(swpJEl).val());
			
			if($(swpJEl).find(":selected").data("name") == "Yes") {
				$(swpOptionsJEl).show();
			} else {
				$(swpOptionsJEl).hide();
			}
			$(fundValuetobeWithdrawnJEl).prop("selectedIndex", 0);
			$(policyYearFromWhichSWPPayableJEl).prop("selectedIndex", 0);
			$(swpFrequencyJEl).prop("selectedIndex", 0);
		}
		
		updatePayForDropdown = function(selectedPolicyTerm) {
			var wspPayForKeys = Object.keys(wspPayForPicklistData).map(key => Number(key));
			wspPayForKeys.sort((num1, num2) => num1 - num2);
			
			var proposeAge = calculateAge($(dateOfBirthJEl).val());
			
			var policyTermVal = $(policyTermJEl).val();
			
			if(proposeAge >= 56){
				defaultPayFor = 10;
			}
			if(policyTermVal > 10) {
				$.each(wspPayForKeys, function( key, value ) {
					
					var selected = value == defaultPayFor ? " selected" : "";
					
					
						
						$(payForJEl).append('<option value="' + value + '" ' + selected + '>' + value + '</option>');
					 
				});
			} else {
				$.each(wspPayForKeys, function( key, value ) {
					var selected = value == defaultPayFor ? " selected" : "";
					if(Number(selectedPolicyTerm) >= value && Number(selectedPolicyTerm) ) {
						$(payForJEl).append('<option value="' + value + '" ' + selected + '>' + value + '</option>');
					}
				});
			}
			
		}
		
		/**
		 * Show NA for if Fund not completed 5 or 7 Year
		 * Return true if fund completed 5 or 7 Years
		 */
		hasFundCompletedSelectedYear = function(inceptionDateStr, yearSelected) {
			if(yearSelected == "5" || yearSelected == "7") {
				var inceptionDateObj = moment(inceptionDateStr.trim(), "DD/MM/YYYY");
				const dateAfterSelectedYear = inceptionDateObj.add(yearSelected,"y").format('YYYY-MM-DD');
				var hasCompletedSelectedYear = moment().isAfter(dateAfterSelectedYear);
				return hasCompletedSelectedYear;
			} else {
				return true;
			}
		}
		
		/**
		 * Customize Fund Past Rerurns and Benchmark Retuns dropdown change event
		 */
		onBenchMarkAndPastReturnChange = function() {
			var yearSelected = this.value;
		
			$(customizeFundTableJEl + " tbody tr").each(function(d) {
				var curRowFundShortName = $(this).data("fundshortname");
				var inceptionDateStr = $(this).find("td:eq(1)").text();
				var isFundReturnEligible = hasFundCompletedSelectedYear(inceptionDateStr, yearSelected);
				
				var pastReturnCol5 = $(this).find("td:eq(4)");
				var benchmarkReturnCol6 = $(this).find("td:eq(5)");
				var curFundRowBenchMarkAndReturnsObj = benchMarkAndReturnsObj[curRowFundShortName];
                if(benchMarkAndReturnsObj && yearSelected && curFundRowBenchMarkAndReturnsObj) {
                	if(curFundRowBenchMarkAndReturnsObj.pastReturnData[yearSelected]) {
                		var curFundPastReturnVal = curFundRowBenchMarkAndReturnsObj.pastReturnData[yearSelected];
                		pastReturnCol5.text(isFundReturnEligible ? Number(curFundPastReturnVal).toFixed(2) : "NA");
                	}
                	if(curFundRowBenchMarkAndReturnsObj.benchMarkReturnData[yearSelected]) {
                		var curFundbanchMarkReturnVal = curFundRowBenchMarkAndReturnsObj.benchMarkReturnData[yearSelected];
                		benchmarkReturnCol6.text(isFundReturnEligible ? Number(curFundbanchMarkReturnVal).toFixed(2) : "NA");
                	}
                }
            });
		};
		
		/**
		 * Generating Benchmark and Retuns Object Based on Year and Since Inception
		 */
		createBenchMarkAndReturnObj = function(value) {
			var fundReturnsJSONObject = {};
			var benchMarkReturnsJSONObject = {};
			
			var fundReturns = value.fundReturns;
			var benchMarkReturns = value.benchMarkReturns;
			var fundReturnsArray = fundReturns.split(",");
			var benchMarkReturnsArray = benchMarkReturns.split(",");
			
			$.each(fundReturnsArray, function( key, curReturn) {
				var curFundReturnStrArray = curReturn.trim().split(":")
				var pastReturnTerm = curFundReturnStrArray[0].trim();
				var pastReturnVal = curFundReturnStrArray[1].trim();
				fundReturnsJSONObject[pastReturnTerm] = pastReturnVal;
			});
			
			$.each(benchMarkReturnsArray, function( key, curReturn) {
				var curBenchMarkReturnStrArray = curReturn.trim().split(":")
				var bechMarkTerm = curBenchMarkReturnStrArray[0].trim();
				var bechMarkTermVal = curBenchMarkReturnStrArray[1].trim();
				benchMarkReturnsJSONObject[bechMarkTerm] = bechMarkTermVal;
			});
			
			return {
		        benchMarkReturnData: benchMarkReturnsJSONObject,
		        pastReturnData: fundReturnsJSONObject
		    }
		};
		
		/**
		 * Show Total Return Amount By Selected Percentage
		 */
		updateTotalReturnAmount = function(selectedPercentage) {
			if(percentReturnAmountObj[selectedPercentage]) {
				var totalReturnAmount = percentReturnAmountObj[selectedPercentage];
				$(returnAmtValJEl).html(Math.ceil(totalReturnAmount).toLocaleString("en-IN"));
			}
		}
		
		/**
		 * Update Total Return On Change of Return Percentage (4% or 8%)
		 */
		totalReturnsValueChange = function() {
			var currentReturnPercent = this.value;
			
			updateTotalReturnAmount(currentReturnPercent);
			if(percentReturnAmountObj) {
				updateFormData(null, percentReturnAmountObj);
			}
			console.log("Return Value -- "+wrpAnnualPremium[currentReturnPercent]);
			$("#wrpAnnualAmount").empty(); 
			$("#wrpAnnualAmount").append(rupeeSignLabel+" "+wrpAnnualPremium[currentReturnPercent].toLocaleString("en-IN"));
			
			if(currentReturnPercent == "4"){
				$("h4[class='fontsemi fs16 mb-2 wrp']").each(function() {
					  var text = $(".wrp").html().replace("8","4");
					  $(this).html(text);
				});
			}else{
				$("h4[class='fontsemi fs16 mb-2 wrp']").each(function() {
					  var text = $(".wrp").html().replace("4","8");
					  $(this).html(text);
				});
			}
			
			if($(projectedReturnFundAllocation).length > 0) {
				$(projectedReturnFundAllocation).val(currentReturnPercent);
				renderFunds(currentReturnPercent);
			}
		};
		
		/**
		 * Render the Porjected Return amount based on percentage function parameter
		 * Also It updates the amount based on Plus and Minus click event
		 */
		renderFunds = function(selectedPercentage) {
			if($(customizeFundTableJEl).find(".allocationAmt").length > 0) {
				var projectedPerReturnAmount = percentReturnAmountObj[selectedPercentage];
				if(projectedPerReturnAmount) {
					$(customizeFundTableJEl).find(".allocationAmt").each(function(index, curElement) {
			            
			            var curFundProjectedReturnAmt = (projectedPerReturnAmount * parseInt(curElement.innerText))/ 100;
			            $("td[data-projectedreturns=" + curElement.getAttribute("data-allocation") + "]").html(Math.round(curFundProjectedReturnAmt).toLocaleString("en-IN"));
			        });
				}
			}
		} 
		
		/**
		 * Projected Return Percentage Change Event to update amount in Projected Return Column
		 */
		onProjectedReturnFundAllocationChange = function() {
			var selectedPercentage = this.value;
			renderFunds(selectedPercentage);
			
			$(totalReturnsValueJEl).val(selectedPercentage);
			updateTotalReturnAmount(selectedPercentage);
		} 
		

		resetFundAllocation = function() {
			var fundStrategyId = $(fundManagementJEl).val();
			// Disable + and - for Fund Management Life Stage
			if(fundStrategyId == "2") {
				$(customizeFundTableJEl).find(".increase, .decrease").hide();
				
				$(customizeFundTableJEl).find(".allocationAmt").each(function(d) {
					$(this).html("0");
	            });
			} else {
				$(customizeFundTableJEl).find(".increase, .decrease").show();
				
				$(customizeFundTableJEl).find(".allocationAmt").each(function(d) {
					if($(this).data("allocation") == "ELCFD") {
						$(this).html("100");
					} else {
						$(this).html("0");
					}
	            });
			}
		}
		
		featuresLinksHandler = function() {
			$(customizeFundLinkJEl).show();
			$(customizeFundLinkJEl).addClass("customize-btn");
			$(featuresLinkJEl).hide();
			$(featuresLinkJEl).removeClass("customize-btn");
			$(".customize-fund-wrapper").toggleClass("active");
		}

		/**
		 * Loads the Customized Funds Table
		 */
		loadCustomizeFundsTable = function() {
			var fundStrategyId = $(fundManagementJEl).val();
			// Customize Fund clicked show spinner
			showLoader();
			$.ajax({
				url : edelweissCustomizeFundsURL,
				dataType : "json",
				data : {},
				type : "POST",
				success : function(result) {
					benchMarkAndReturnsObj = {};
					if(result.status == "success") {
						
						var hasFundAllocated = result["hasFundAllocated"];
						var fundAllocationDetails = [];
						if(hasFundAllocated) {
							fundAllocationDetails = JSON.parse(result["fundDetails"]);
						}
						
						$(customizeFundTableJEl).find("tbody").empty();
						
						$.each(result.data, function( key, value ) {
							var fundName = value.name; 
							var shortName = value.shortName; 
							var inceptionDate = value.inceptionDate;
							var fundReturns = value.fundReturns;
							var benchMarkReturns = value.benchMarkReturns;
							var fundReturnsArray = fundReturns.split(",");
							var benchMarkReturnsArray = benchMarkReturns.split(",");
							var benchMark = value.benchMark;
							var sfni = value.sfin;
							
							var curBenchMarkAndReturnObj = createBenchMarkAndReturnObj(value);
							
							benchMarkAndReturnsObj[shortName] = curBenchMarkAndReturnObj;
							
							var fundAllocationValue = value.recommendedFund ? "100" : "0";
							if(hasFundAllocated) {
								var fundAllocatedJSONArray =  fundAllocationDetails.filter(curFundAllocationJSON => curFundAllocationJSON["name"] == fundName);
								if(fundAllocatedJSONArray.length > 0) {
									fundAllocationValue = fundAllocatedJSONArray[0]["allocation"];
								}
							}

							var allocationColHTML = fundAllocationColTemplate.replace("$[FUND_ALLOCATION]", fundAllocationValue);
							allocationColHTML = allocationColHTML.replace("$[FUND_NAME]", fundName);
							allocationColHTML = allocationColHTML.replace("$[FUND_SHORTNAME]", shortName);
							
							var fundNameColSecondLine = "<br/> <small>" + benchmarkLabel + ": " +  benchMark + "</small>";
							var fundNameColThirdLine = "<small>" + sfinLable + ": " +  sfni + "</small>";
							var fundNameColFourthLine = "<span class='tbl-line cs-color-" + shortName + "'></span>";
							var fundNameCol = "<td class='fund-name'>" + fundName + fundNameColSecondLine + fundNameColThirdLine + fundNameColFourthLine + "</td>";
							var fundInceptionDateCol = "<td>" + inceptionDate +  "</td>";
							var allocationCol = "<td>" + allocationColHTML + "</td>";
							var projectedReturnCol = "<td data-projectedreturns='" + shortName + "'></td>";
							var pastReturnCol = "<td data-pastreturns='" + shortName + "'>" + Number(curBenchMarkAndReturnObj.pastReturnData["Since Inception"]).toFixed(2) +  "</td>";
							var benchMarkReturnCol = "<td data-benchmarkreturns='" + shortName + "'>" + Number(curBenchMarkAndReturnObj.benchMarkReturnData["Since Inception"]).toFixed(2) +  "</td>";
							$(customizeFundTableJEl).find("tbody").append("<tr class='customize-fund-row' data-fundshortname='" + shortName + "'>" + fundNameCol + fundInceptionDateCol + allocationCol + projectedReturnCol + pastReturnCol + benchMarkReturnCol + "</tr>");
						});
						
						// Fund Management Life Stage
						if(fundStrategyId == "2") {
							resetFundAllocation();
						}
						
						renderFunds($(totalReturnsValueJEl).val());
						
						$(".customize-fund-wrapper").toggleClass("active");
						$(customizeFundTableJEl).show();

						hideLoader();
					}
				},
				error : function(data) {
					hideLoader();
				},
				complete: function () {
					$(customizeFundLinkJEl).hide();
					$(customizeFundLinkJEl).removeClass("customize-btn");
					$(featuresLinkJEl).show();
					$(featuresLinkJEl).addClass("customize-btn");
					//Request is complete so hide spinner
					hideLoader();
	            }
			});
		};

		saveFundDetails = function() {
			var fundDetailsRequestReqData = {};
			var fundStrategyId = $(fundManagementJEl).val();
			var fundAllocationDetailsId = $(customerFundAllocationDetailIdJEl).val();
			var isFundDetailsUpdated = false;
			
			if(fundStrategyId == "1") {
				var fundDetails = [];
				if($(customizeFundTableJEl).find(".customize-fund-row").length > 0) {
					// In Case of Fund Details updated from Customize Fund Table
					$(customizeFundTableJEl).find(".customize-fund-row").each(function(d) {
						var curRowFundShortName = $(this).data("fundshortname");
						var inceptionDateStr = $(this).find("td:eq(1)").text();
						var curFundName = $(this).find("td:eq(2)").find(".allocationAmt").data("fundname");
						var curFundAllocation = $(this).find("td:eq(2)").find(".allocationAmt").text();
						var curFundDetails = {
							"name": curFundName,
							"inceptionDate": inceptionDateStr,
							"allocation": curFundAllocation
						};
						fundDetails.push(curFundDetails);
		            });
					fundDetailsRequestReqData[namespace + "fundDetails"] = JSON.stringify(fundDetails);
					fundDetailsRequestReqData[namespace + "fundAllocationDetailsId"] = fundAllocationDetailsId;
					fundDetailsRequestReqData[namespace + "saveDefaultFundAllocation"] = false;
				} else if(fundAllocationDetailsId){
					// No Change in Fund Details
					isFundDetailsUpdated = true;
				} else {
					// Load Default Funds
					fundDetailsRequestReqData[namespace + "saveDefaultFundAllocation"] = true;
				}
				fundDetailsRequestReqData[namespace + "productCode"] = $(productCodeJEl).val();

				// Skip Saving Fund Details as it is already updated
				if(!isFundDetailsUpdated) {
					$.ajax({
						url:saveFundDetailsURL,
						type:"post",
						data: fundDetailsRequestReqData,
						dataType: "json",
						success: function(response) {
							if(response.status = "success" && response.hasFundAllocated) {
								var allocatedFundDetails = JSON.parse(response["fundAllocationDetails"]);
								$(customerFundAllocationDetailIdJEl).val(allocatedFundDetails.id);
								$(customerAllocatedFundDetailsJEl).val(allocatedFundDetails.fundDetails);
							}
						}
					});	
				} 
			}
		}
		
		/**
		 *  Plus and Minus Click event for Customize Fund Table to update/deduct fund by 10
		 *  Call Generate BI after 100 Fund Allocation
		 */
		onFundAllocationIncreaseDecrease = function(event) {
			var totalFundAllocation = 0;
			$(customizeFundTableJEl).find(".allocationAmt").each(function(d) {
				totalFundAllocation += parseInt($(this).text())
            });
			
			if (100 <= totalFundAllocation && $(this).hasClass("increase")) {
				return 0;
			}
			
			var currentFundAllocation = Number($(this).parents(".allocation").find(".allocationAmt").text());
			
			if($(this).hasClass("increase") && (100 > currentFundAllocation)) {
				$(this).parents(".allocation").find(".allocationAmt").text(currentFundAllocation + 10);
			}
			if($(this).hasClass("decrease") && (0 < currentFundAllocation)) {
				$(this).parents(".allocation").find(".allocationAmt").text(currentFundAllocation - 10);
			}
			
			var updatedTotalFundAllocation = 0;
			$(customizeFundTableJEl).find(".allocationAmt").each(function(d) {
				updatedTotalFundAllocation += parseInt($(this).text())
            });
			
			var fundAllocationLeft = 100 - updatedTotalFundAllocation;
			
			if(fundAllocationLeft == 0) {
				//Calling Generate BI after 100 Fund allocated
				generateBIRequest();
				
				saveFundDetails();
				
			} 
		}
		
		/**
		 * Create Return Amount JSON by 4(BIJson) and 8(BIJson2) Percentage
		 */
		createReturnAmountByPercent = function(biResponseData) {
			var selectedPolicyTerm = $(policyTermJEl).val();
			//var productResponse = biResponseData["ProductResponse"];
			console.log("Product id -- "+biResponseData[0].ProductID);
			var gssBiResponse;
			var wrpBiResponse;
			if(Number(biResponseData[0].ProductID) == gssProductCode){
				gssBiResponse = biResponseData[0].BiTable;
			}else if (Number(biResponseData[1].ProductID) == gssProductCode){
				gssBiResponse = biResponseData[1].BiTable;
			}
			
			if(Number(biResponseData[0].ProductID) == 40050){
				wrpBiResponse = biResponseData[0].BiTable;
			}else if(Number(biResponseData[1].ProductID) == 40050){
				wrpBiResponse = biResponseData[1].BiTable;
			}
			
			var biJSON = biResponseData["BIJson"];
			var biJSON2 = biResponseData["BIJson2"];
			
			
			 
			var selectedPolicyTermBiJson = $.grep(gssBiResponse.Table1, function (curBiJSON, index) {
				return Number(curBiJSON.POLICYYEAR) == selectedPolicyTerm;
            });
			var selectedPolicyTermWRPBiJson = $.grep(wrpBiResponse["0.04"], function (curBiJSON, index) {
				return Number(curBiJSON.POLICYYEAR) == selectedPolicyTerm;
            });
			if(selectedPolicyTermBiJson.length > 0) {
				var fourPerReturnAmount = selectedPolicyTermBiJson[0]["SAM"] + selectedPolicyTermWRPBiJson[0]["SAM"];
				percentReturnAmountObj["4"] = fourPerReturnAmount;
			}
			
			var selectedPolicyTermBiJson2 = $.grep(gssBiResponse.Table2, function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == selectedPolicyTerm;
            });
			var selectedPolicyTermWRPBiJson2 = $.grep(wrpBiResponse["0.08"], function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == selectedPolicyTerm;
            });
			if(selectedPolicyTermBiJson2.length > 0) {
				var eightPerReturnAmount = Math.round(selectedPolicyTermBiJson2[0]["SAM"]) + Math.round(selectedPolicyTermWRPBiJson2[0]["SAM"]) ;
				percentReturnAmountObj["8"] = eightPerReturnAmount;
				$(returnAmtValJEl).html(Math.round(eightPerReturnAmount).toLocaleString("en-IN"));
			}

			return percentReturnAmountObj;
		};
		
		/**
		 * Show Validation message from Generate BI Api response
		 */
		handleInputValidationStatus = function(biResponse) {
			
			var errorMessage;
			if(biResponse.StatusMsg && biResponse.StatusMsg == "Failed"){
				errorMessage = "Please enter a valid Input";
				$(errorMessageContainerJEl).empty();
				$(errorMessageContainerJEl).html(errorMessage);
				$(errorMessageModalJEl).modal('show');
				disableProceedButton();
				handleTotalReturnOnBIError();
				return true;
			}else {
				$(proceedSubmitBtnJEl).removeClass("disabled-btn");
				$(proceedSubmitBtnJEl).prop("disabled", false);
				$(breakUpBtnJEl).removeClass("disabled-btn");
				$(breakUpBtnJEl).prop("disabled", false);
			}
			return false;
		}
		
		/**
		 * Disable Proceed Button On Error
		 */
		disableProceedButton = function() {
			$(proceedSubmitBtnJEl).addClass("disabled-btn");
			$(proceedSubmitBtnJEl).prop("disabled", true);
		}
		
		/**
		 * Generate BI Request Fund Details
		 */
		getBIRequestFundDetails = function() {
			var fundStrategyId = $(fundManagementJEl).val();
			var fundAllocationDetailsId = $(customerFundAllocationDetailIdJEl).val();
			
			// Disable + and - for Fund Management Life Stage
			var defaultELCFFundAllocation = "100";
			if(fundStrategyId == "2") {
				defaultELCFFundAllocation = "0";
			}
			
			if($(customizeFundTableJEl).find(".allocationAmt").length > 0) {
				var fundDetails = new Object({});
				fundDetails["FUNDSTRATEGYID"] = fundStrategyId;
				$(customizeFundTableJEl).find(".allocationAmt").each(function(d) {
					var fundDetailsKey = $(this).data("fundname").replace(/ /g,'');
					fundDetails[fundDetailsKey] = $(this).text();
	            });
				fundDetails["LongTermBondFund"] = 0;
				fundDetails["FundValuetobeWithdrawn"]= "10";
				return fundDetails;
			}  else if(fundAllocationDetailsId) {
				var customerAllocatedFundDetailsVal = $(customerAllocatedFundDetailsJEl).val();
				var fundDetails = new Object({});
				fundDetails["FUNDSTRATEGYID"] = fundStrategyId;
				fundDetails["FundValuetobeWithdrawn"] = "10";
				var allocatedFundDetails = JSON.parse(customerAllocatedFundDetailsVal);
				
				if(allocatedFundDetails && allocatedFundDetails.length > 0) {
					allocatedFundDetails.forEach(curAllocatedFund => {
						var fundDetailsKey = curAllocatedFund["name"].replace(/ /g,'');
						var allocationVal = curAllocatedFund["allocation"];
						fundDetails[fundDetailsKey] = allocationVal;
					});
				}
				return fundDetails;
			} else {
				var defaultFundDetails = {
	                "FUNDSTRATEGYID": fundStrategyId,
	                "EquityLargeCapFund": defaultELCFFundAllocation,
	                "EquityTop250Fund": "0",
	                "BondFund": "0",
	                "ManagedFund": "0",
	                "EquityMidCapFund": "0",
	                "EquityBlueChipFund": "0",
	                "GILTFund": "0",
	                "FundValuetobeWithdrawn": "10",
	                "LongTermBondFund": 0
		         };
				return defaultFundDetails;
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
				return 0;
			}
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

			var genderShorName = "F";
			var spouseGender = "135"; // Male Spouse Gender 
			if(gender == "Male") {
				genderShorName = "M";
				spouseGender = "136"; // Female Spouse Gender 
			}
			
			var sameProposer = "True";
			
			var liDetails = {
				"LI_NAME": fullName,
				"LI_ENTRY_AGE": age,
				"LI_DOB": proposerDOBStr,
				"LI_GENDER": genderShorName,
				"LI_STATE": "0",
				"LI_MOBILENO": mobileNumber,
				"LI_EMAILID": email,
				"IsExistingCustomer":"2",
			}
			
			if(basicInvestingFor == "Family") {
				sameProposer = "False";
				var assuredRelVal = $("input[name='" + assuredRelationEl + "']:checked").val();
				var liGenderShortName = "F";
				if(assuredRelVal == "Son" || assuredRelVal == "GrandSon") {
					liGenderShortName = "M";
				}
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

			var spouseDetails = {
				"SpouseName": "",
				"SpouGender": "",
				"SpouseDB": ""
			}
			
			var agentDetails = {
					"AGENT_ID": "TW9001",
					"AgentLocation": "Pune",
					"AgentName": "Ranveer pratap"
			}
			
			var selectedPolicyOptionVal = $(policyOptionJEl).val();
			var customerDetails = {
					"agentDetails" : agentDetails,
					"liDetails" : liDetails,
					"proposerDetails" : proposerDetails
				};
			return {
				"customerDetails" : customerDetails
			}
		}
		
		
		/**
		 * Generate BI Request Policy Options
		 */
		getBIRequestPolicyOptions = function() {
			
			var SWPfrequency = "";
			var PolicyYearfromwhichSWPpayable = "";
			var FundValuetobeWithdrawn = "";
			if($(swpJEl).find(":selected").data("name") == "Yes") {
				FundValuetobeWithdrawn = $(fundValuetobeWithdrawnJEl).val();
				PolicyYearfromwhichSWPpayable = $(policyYearFromWhichSWPPayableJEl).val();
				SWPfrequency = $(swpFrequencyJEl).val();
			}
			
			var isLittleStartBenefitOpted = $(isLittleChampBenefitOptedJEl).val();
			var littleStarBenefitVal = "4";
			
			if(isLittleStartBenefitOpted && isLittleStartBenefitOpted == "Yes") {
				littleStarBenefitVal = "3";
			}
			
			var lifeStageVariantVal = policyOptionData[baseCoverKey];
			var selectedPolicyOptionVal = $(policyOptionJEl).val();
			
			
			var policyOptions = {
				"PlanOption" : lifeStageVariantVal,
				"PolicyYearfromwhichSWPpayable" : "",
				"SWPfrequency" : "",
				"SystematicWithdrawalPlan" : "6",
				"LittleStarBenefit" : littleStarBenefitVal
			}
			return policyOptions;
		}
		
		getBIRequestGSSPolicyOptions = function() {
			
			var lifeStageVariantVal = policyOptionData[baseCoverKey];
			var policyOptions = {
				"PlanOption" : lifeStageVariantVal,
				"FamilyIncomeBenefit": "3",
				"MaturityBenefitOption": "1"
			}
			return policyOptions;
		}
		
		getBIRequestCompanyDetails = function() {
			var companyDetails = {
				"COMPANY_STATE": 0,
				"GSTIN": "0",
				"GSTIN_Number": "0",
			}
			return {
				"companyDetails":companyDetails
			}
		}
		
		getBIRequestRidersDetails = function() {
			var riderDetails = {
					"ADBRider": "",
					"ATPDRider": "",
					"CriticalIllnessRider": "",
					"HospitalityCashBenefitRider": "",
					"PayorWaiverBenefitRider": "",
					"PWB": "",
					"WaiverOfPremiumRider": ""
				}
			return riderDetails;
		}
		
		/**
		 * Generate BI Request Product
		 */
		getBIRequestProduct = function() {
			var paymentOptionVal = $(paymentOptionJEl).val();
			var investmentAmount = getInvestmentAmount();
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
			
			var premPayTerm = $(payForJEl).val();
			var policyTerm = $(policyTermJEl).val();
			
			var productDetails = {
				"PR_ID":  "40050",
				"INPUT_MODE": $(paymentOptionJEl).val(),
				"PR_PT": policyTerm,
				"PR_PPT": premPayTerm,
				"PR_MI": "0",
				"PR_ANNPREM" : annualPremium,
				"PR_SA": "0",
				"PR_SAMF" : "10",
				"PR_ModalPrem": "",
				"PR_CHANNEL": "1",
				"category": "1",
				"flat_rate": "0",
				"FlatRatePH": "0",
				"emr_rate":"1.00",
				"EmrRatePH": "1",
				"REQUESTSOURCE": "1",
				"isPdfByte": "Y",
				"PrDeclineDecision1": "-1",
			    "PrDeclineDecisionReason1": "-1",
			    "fundDetails" : getBIRequestFundDetails(),
			    "policyOPtions" : getBIRequestPolicyOptions()
				
			}
			
			var guaranteedDetails = {
					"PR_ID":  gssProductCode.toString(),
					"INPUT_MODE": $(paymentOptionJEl).val(),
					"PR_PT": policyTerm,
					"PR_PPT": premPayTerm,
					"PR_MI": "0",
					"PR_ANNPREM" : annualPremium,
					"PR_SA": "0",
					"PR_SAMF" : "10",
					"PR_ModalPrem": "",
					"LumpsumAmount": "0",
					"NriDeclaration": "N",
					"PR_CHANNEL": "1",
					"category": "1",
					"flat_rate": "0",
					"EqualAnnualInst": "0",
					"emr_rate":"1.00",
					"ZP_NATIONALITY": "1",
					"riderDetails" :getBIRequestRidersDetails(),
				    "policyOptions" : getBIRequestGSSPolicyOptions()
					
				}
			
			// Merge Policy Option to Product JSON
			//$.extend(true, product, getBIRequestPolicyOptions());
			
			// Merge Fund Details Option to Product JSON
			//$.extend(true, product, getBIRequestFundDetails());
			var product = [];
			product.push(productDetails);
			product.push(guaranteedDetails);
			return {
				"productDetails":{
					"product": product
					}
			}
		}
		
		/**
		 * Update Form Data
		 */
		updateFormData = function(biResponse, percentReturnAmountObj) {
			var selectedReturn = $(totalReturnsValueJEl).val();
			if(percentReturnAmountObj) {
				var curProjectedReturn = Math.round(percentReturnAmountObj[selectedReturn]);
				$(returnAmtValJEl).html(curProjectedReturn.toLocaleString("en-IN"));
				$(totalReturnsJEl).val(curProjectedReturn);
				
				$(projectedReturnsDataJEl).val(JSON.stringify(percentReturnAmountObj));
			}
			if(biResponse) {
				var biResponseData = biResponse.ProductResponse;
				if(biResponse["SolutionPDFPath"]) {
					$(illustrationURLJEl).val(biResponse["SolutionPDFPath"]);
				}
				try{
					if(biResponse["SolutionQNum"] && biResponseData?.length == 2) {
						
					let QuotationNum = biResponseData[0].ProductID == gssProductCode ? (biResponseData[1].QuotationNum + "," + biResponseData[0].QuotationNum):(biResponseData[0].QuotationNum + "," + biResponseData[1].QuotationNum);
						
						$(quotationIdJEl).val(QuotationNum)
					}
				}catch(e){
					if(biResponse["SolutionQNum"]) {
						$(quotationIdJEl).val(biResponse["SolutionQNum"])
					}
				}	
			}
			
			populateAmountInWord();
		}
		
		renderBreakupAmount = function(biResponseData){
			var selectedPolicyTerm = $(policyTermJEl).val();
			var gssBiResponse;
			var wrpBiResponse;
			var annualPremium = {}; 
			if(Number(biResponseData[0].ProductID) == gssProductCode){
				gssBiResponse = biResponseData[0].BiTable;
			}else if (Number(biResponseData[1].ProductID) == gssProductCode){
				gssBiResponse = biResponseData[1].BiTable;
			}
			
			if(Number(biResponseData[0].ProductID) == 40050){
				wrpBiResponse = biResponseData[0].BiTable;
			}else if(Number(biResponseData[1].ProductID) == 40050){
				wrpBiResponse = biResponseData[1].BiTable;
			}
			
			var selectedPolicyTermBiJson = $.grep(gssBiResponse.Table1, function (curBiJSON, index) {
				return Number(curBiJSON.POLICYYEAR) == selectedPolicyTerm;
            });
			var selectedPolicyTermWRPBiJson = $.grep(wrpBiResponse["0.04"], function (curBiJSON, index) {
				return Number(curBiJSON.POLICYYEAR) == selectedPolicyTerm;
            });
			
			if(selectedPolicyTermBiJson.length > 0) {
				var fourPerReturnAmount = Math.ceil(selectedPolicyTermBiJson[0]["ANNUALISED_PREMIUM"]) + Math.ceil(selectedPolicyTermWRPBiJson[0]["ANN_PREM"]);
				annualPremium["4"] = fourPerReturnAmount;
				wrpAnnualPremium["4"] = Math.ceil(selectedPolicyTermWRPBiJson[0]["FINAL_FV"]);
			}
			
			var selectedPolicyTermBiJson2 = $.grep(gssBiResponse.Table2, function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == selectedPolicyTerm;
            });
			var selectedPolicyTermWRPBiJson2 = $.grep(wrpBiResponse["0.08"], function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == selectedPolicyTerm;
            });
			
			if(selectedPolicyTermBiJson2.length > 0) {
				var eightPerReturnAmount = Math.ceil(selectedPolicyTermBiJson2[0]["ANNUALISED_PREMIUM"]) + Math.ceil(selectedPolicyTermWRPBiJson2[0]["ANN_PREM"]) ;
				annualPremium["8"] = eightPerReturnAmount;
				wrpAnnualPremium["8"] = Math.ceil(selectedPolicyTermWRPBiJson2[0]["FINAL_FV"]);
			}
			
			if($(totalReturnsValueJEl).val() == "4"){
				$("#investmentAnnum").text(rupeeSignLabel+" "+annualPremium["4"].toLocaleString("en-IN"));
				$("#wrpAnnualAmount").text(rupeeSignLabel+" "+Math.ceil(selectedPolicyTermWRPBiJson[0]["FINAL_FV"]).toLocaleString("en-IN"));
				 
			}else{
				$("#investmentAnnum").text(rupeeSignLabel+" "+annualPremium["8"].toLocaleString("en-IN"));
				$("#wrpAnnualAmount").text(rupeeSignLabel+" "+Math.ceil(selectedPolicyTermWRPBiJson2[0]["FINAL_FV"]).toLocaleString("en-IN"));
				
			}
			
			var firstPolicyTerm = "1";
			var firstPolicyTermBiJson = $.grep(gssBiResponse.Table2, function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == firstPolicyTerm;
            });

			$(premiumAmountJEl).val(Math.round(getInvestmentAmount()) + Math.round(firstPolicyTermBiJson[0]["TAX_MP"]) + Math.round(firstPolicyTermBiJson[0]["TOTAL_RIDER_TAX"]));
			$("#gssAnnualAmount").text(rupeeSignLabel+" "+Math.round(selectedPolicyTermBiJson2[0]["SAM"]).toLocaleString("en-IN"));
			$("#wrpAmount").text(rupeeSignLabel+" "+Math.round(selectedPolicyTermWRPBiJson2[0]["BASE_PREM_CO"]).toLocaleString("en-IN"));
			$("#gssAmount").text(rupeeSignLabel+" "+Math.round(selectedPolicyTermBiJson2[0]["BASE_PREM"]).toLocaleString("en-IN"));
			$("#breakupPPT").text($(payForJEl).val()+" Years");
			$("#ppm").text($(paymentOptionJEl+" option:selected").text());
			
		}
		
		/**
		 * Generate BI Request
		 */
		generateBIRequest = async function() {
				var hasValidationErrors = false;
				//Showing Loader
				showLoader();
				
				percentReturnAmountObj = {};
				var biRequestData = new Object({});
				
				var wealthSecurePlusBIRequestData = getBIRequestBasicDetails();
				
				var productDetails = getBIRequestProduct();
				var companyDetails = getBIRequestCompanyDetails();
				
				$.extend(true, wealthSecurePlusBIRequestData, productDetails);
				$.extend(true, wealthSecurePlusBIRequestData, companyDetails);
				
				
				$.extend(true, biRequestData, wealthSecurePlusBIRequestData);
				
				biRequestData[namespace + "biRequestData"] = JSON.stringify(biRequestData);
				try {
					const result = await $.ajax({
						url : edelweissGenerateBIURL,
						dataType : "json",
						data : biRequestData,
						type : "POST"
					});
					
					if(result.status = "success") {
						var biResponse = result.data;
						generateBiRequestTemp = biResponse;
						hasValidationErrors = handleInputValidationStatus(biResponse);
						
						if(!hasValidationErrors) {
							var biResponseData = biResponse.ProductResponse;
							percentReturnAmountObj = createReturnAmountByPercent(biResponseData);
							
							updateFormData(biResponse, percentReturnAmountObj);
							
							// Update Projected Funds Column based on Allocation Percentage
							renderFunds($(projectedReturnFundAllocation).val());
							
							//Render Graph and Return Table
							renderAllReturnsTableAndGraph(biResponseData);
							
							//Render Breakup Amount
							renderBreakupAmount(biResponseData);
							
							//Update LMS Lead
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
					console.log(error);
					handleTotalReturnOnBIError();
					disableProceedButton();
					$(breakUpBtnJEl).addClass("disabled-btn");
					$(breakUpBtnJEl).prop("disabled", true);
					return hasValidationErrors;
				}
		};
		
		/**
		 * Render Graph and Return Table based on selected Return 4% or 8%
		 */
		renderAllReturnsTableAndGraph = function(biResponseData) {
			
			var selectedReturn = $(totalReturnsValueJEl).val();
			if(Number(biResponseData[0].ProductID) == 40050){
				biResponseData = biResponseData[0].BiTable;
			}else if(Number(biResponseData[1].ProductID) == 40050){
				biResponseData = biResponseData[1].BiTable;
			}
			switch (selectedReturn) {
		    	case "4":
		    		var biJSONArray = biResponseData["0.04"];
		    		break;
		    	case "8":
		    		biJSONArray = biResponseData["0.08"];
		    		break;
		    }
			
			var interval = 40 > biJSONArray.length ? 5 : 10;
			
			var boosterPremiumArray = [];
			var additionArray = [];
			var fundsArray = [];
			var yearsCategoryArray = [];
			var tableBodyData = "";
			$.each(biJSONArray, function( index, curBIJson) {
				var curPolicyYear = Math.round(curBIJson["POLICYYEAR"]);
				var cumPrem = curBIJson["CUM_PREM"] ? " ₹ " + parseInt(Math.round(curBIJson["CUM_PREM"])).toLocaleString("en-IN") : "NA";
				var allocationCharge = curBIJson["EXTRA_ALLOC_CHARGE"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["EXTRA_ALLOC_CHARGE"])).toLocaleString("en-IN") : "NA";
				var boosterPremium = curBIJson["EXTRA_ALLOC_CHARGE"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["BOOSTER_PREMIUM"])).toLocaleString("en-IN") : "NA";
				var finalFV = curBIJson["FINAL_FV"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["FINAL_FV"])).toLocaleString("en-IN") : "NA";
			
				tableBodyData += "<tr><td>" + curPolicyYear + "</td><td>" + cumPrem + "</td><td>" + allocationCharge + "</td><td>" + boosterPremium + "</td><td>" + finalFV + "</td></tr>"; 
			
				var start = index + 1;
				if(start % interval == 0) {
					var curPolicyYear = Math.round(curBIJson["POLICYYEAR"]);
					yearsCategoryArray.push(curPolicyYear + " " + "Years");
					
					if(curBIJson["FINAL_FV"]) {
						fundsArray.push(curBIJson["FINAL_FV"]);
					}
					
					if(curBIJson["BOOSTER_PREMIUM"]) {
						boosterPremiumArray.push(curBIJson["BOOSTER_PREMIUM"]);
					}
					
					if(curBIJson["EXTRA_ALLOC_CHARGE"]) {
						additionArray.push(curBIJson["EXTRA_ALLOC_CHARGE"]);
					}
				}
			});
			
			// Fund Table
			$(fundDataTableJEl).find("tbody").html(tableBodyData);
			
			// Init Highchart - Type : Stack Column
			Highcharts.chart(highchartContainerEl, {
				chart: {
					type: "column",
					width: 675,
					height: 300
				},
				title: {
					text: ''
				},
				xAxis: {
					categories: yearsCategoryArray,
					labels: {
						format: "{value}"
					}
				},
				yAxis: {
					gridLineWidth: 0,
					minorGridLineWidth: 0,
					title: {
						text: null
					},
					labels: {
						formatter: function() {
							return this.value / 1E5 + "L"
						}
					}
				},
				credits: {
					enabled: false
				},
				tooltip: {
					headerFormat: '<span style\x3d"font-size:10px">{point.key}</span><table>',
					pointFormat: '<tr><td style\x3d"color:{series.color};padding:0">{series.name}: </td><td style\x3d"padding:0"><b>\u20b9 {point.y:,.0f}</b></td></tr>',
					footerFormat: "</table>",
					shared: false,
					useHTML: true
				},
				plotOptions: {
					column: {
						stacking: "normal"
					}
				},
				colors: ["#124093", "#3a85ff", "#ff4c00"],
				series: [{
					name: boosterPremiumGraphLabel,
					data: boosterPremiumArray
				}, {
					name: additionGraphLabel,
					data: additionArray
				}, {
					name: fundsGraphLabel,
					data: fundsArray
				}]
			});
		}
		
		/**
		 * Calling Generate BI Request on Change of Select and On blur of Input element
		 * Save Customer Investment Data
		 */
		onInvestmentAmountBlur = async function() {
			updateMinimumInvestmentAmount();
			updateInvestmentAmountLocaleIN();
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
 		onPolicyOptionChange = async function() {
 			var selPolicyOption = $(policyOptionJEl).val();
 			//console.log("Selected Policy Option : " + selPolicyOption);
 			
 			if(selPolicyOption == baseCoverKey) {
 				//Base Cover Selected Deleting Family Individual or Joint Data if any exist......
 				hideEditDetailsLink();
 				
 				updateInvestingForDropdown(selPolicyOption);
 				
 				updateBasicDetailsInvestingRadio(selPolicyOption);
 				
 				//Reset Individual and Joint Life Modal Form Fields to blank
 				resetFamilyIndividualJointModalFormFields();
 				
 				//Delete Family Details if exist
 				var deleteFamilyRequestData = {};
 				deleteFamilyRequestData[namespace + "isFamilyFromPolicyOptions"] = true; 
				deleteFamilyDetails(deleteFamilyRequestData);
 				
 				var hasValidationErrors = await generateBIRequest();
 				if(!hasValidationErrors) {
 					saveCustomerInvestmentDataHanlder();
 				}
 				
 			}
		}
		
		onInvestingForChange = async function() {
			var investingFor = $(this).val();

			if(investingFor == "Family") {
				$(this).val("Myself");
				initFamilyModal();
				$(familyModalJEl).modal("show");
			} else {
				$(investingForFullNameJEl).val("");
				$(investingForDOBJEl).val("");
				
				$(assuranceFullNameJEl).val("");
				$(assuranceDobJEl).val("");
				$(isLittleChampBenefitOptedJEl).val("");
				
				$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
				$("input[type=radio][name='" + basicInvestingForEl + "'][value='Myself']").prop('checked', true);
				$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
				
				$(removeLittleChampWrapperJEl).hide();
				
				updateYourDetailsHeader();
				
				var deleteFamilyRequestData = {};
				deleteFamilyDetails(deleteFamilyRequestData);
				var hasValidationErrors = await generateBIRequest();
				if(!hasValidationErrors) {
					saveCustomerInvestmentDataHanlder();
				}
				
			}
		}
		
		onPayForChange = async function() {
			initSWP(); 
			
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		onPaymentOptionChange = async function() {
			updateMinimumInvestmentAmount();
			updateInvestmentAmountLocaleIN();
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		onFundManagementChange = async function() {
			resetFundAllocation();
			
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
			
		}
		
		onSWPChange = async function() {
			initSWP();
	
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		/**
		 * Applicable When SWP is Yes Only
		 */
		onFundValuetobeWithdrawnChange = async function() {
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		/**
		 * Applicable When SWP is Yes Only
		 */
		onPolicyYearFromWhichSWPPayableChange = async function() {
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		/**
		 * Applicable When SWP is Yes Only
		 */
		onSWPFrequencyChange = async function() {
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		/**
		 * Customize Form Submit Hanlder
		 */
		proceedSubmitHandler = async function(e) {
			e.preventDefault();
			$(isPdfByteJEl).val("Y");
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				saveFundDetails();
				
				/**
				 * Showing Little Champ Modal if Customer Doesn't Clicked on Dont want this add on after saving family details
				 */
				var isLittleChampBenefitOptedVal = $(isLittleChampBenefitOptedJEl).val();
				if(isLittleChampBenefitOptedVal != "No" && isLittleChampBenefitOptedVal != "Yes" && $(investingForJEl).val() == "Family") {
					$(isFormSubmitPendingJEl).val("true");
					$(littleChampModalJEl).modal("show");
				} else {
					$(customizeFormJEl).submit();
				}
			}
		}
		
		/**
		 * Update Investing For Dropdown based on Selected Policy Option
		 */
		updateInvestingForDropdown = function(selectedPolicyOption) {
			//productInvestingForRelData
			//productPolicyOptionRelData
			$(investingForJEl).find("option").remove();
			$.each(productInvestingForRelData, function(index, curProductInvestingForRel) {
				if(selectedPolicyOption == baseCoverKey) {
					$(investingForJEl).append('<option value="' + curProductInvestingForRel.name + '">' + curProductInvestingForRel.name + '</option>');
				} else if(selectedPolicyOption != baseCoverKey && curProductInvestingForRel.name == 'Myself') {
					$(investingForJEl).append('<option value="' + curProductInvestingForRel.name + '">' + curProductInvestingForRel.name + '</option>');
				}
			});
		}
		
		updateBasicDetailsInvestingRadio = function(selectedPolicyOption) {
			//productInvestingForRelData
			//productPolicyOptionRelData
			$(".basicInvestingForWarpper .radio_container").empty();
			var htmlBasicInvestingFor = "";
			$.each(productInvestingForRelData, function(index, curProductInvestingForRel) {
				if(selectedPolicyOption == baseCoverKey) {
					var checkedAttr = curProductInvestingForRel.name == $(investingForJEl).val() ? "checked" : "";
					htmlBasicInvestingFor += "<li class='btn form-btn address-radio'>";
					htmlBasicInvestingFor += "<input type='radio' name='" + namespace + "basicInvestingFor' id='" + curProductInvestingForRel.key + "' value='" + curProductInvestingForRel.name + "' "+ checkedAttr +">";
					htmlBasicInvestingFor += "<label for='" + curProductInvestingForRel.key + "'>" + curProductInvestingForRel.name + "</label>";
					htmlBasicInvestingFor += "</li>";
				} else if(selectedPolicyOption != baseCoverKey && curProductInvestingForRel.name == 'Myself') {
					htmlBasicInvestingFor += "<li class='btn form-btn address-radio'>";
					htmlBasicInvestingFor += "<input type='radio' name='" + namespace + "basicInvestingFor' id='" + curProductInvestingForRel.key + "' value='" + curProductInvestingForRel.name + "' checked >";
					htmlBasicInvestingFor += "<label for='" + curProductInvestingForRel.key + "'>" + curProductInvestingForRel.name + "</label>";
					htmlBasicInvestingFor += "</li>";
				}
			});
			$(".basicInvestingForWarpper .radio_container").append(htmlBasicInvestingFor);
		}
		
		/**
		 * Update Policy Option Dropdown based on selected Investing For
		 */
		updatePolicyOptionDropdown = function(selectedInvestingFor) {
			$(policyOptionJEl).find("option").remove();
			$.each(productPolicyOptionRelData, function( index, curProductPolicyOptionRel) {
				if(selectedInvestingFor == "Myself") {
					$(policyOptionJEl).append('<option data-name="' + curProductPolicyOptionRel.name + '" value="' + curProductPolicyOptionRel.key + '">' + curProductPolicyOptionRel.name + '</option>');
				} else if(selectedInvestingFor == "Family" && curProductPolicyOptionRel.key == baseCoverKey) {
					$(policyOptionJEl).append('<option data-name="' + curProductPolicyOptionRel.name + '" value="' + curProductPolicyOptionRel.key + '">' + curProductPolicyOptionRel.name + '</option>');
				}
			});
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
					//console.log(response);
					if(response.status == "success") {
						$(customerInvestmentDetailsIdJEl).val(response.customerInvestmentDetailsId)
						$(customerPolicyDetailsIdJEl).val(response.customerPolicyDetailsId);
					}
					hideLoader();
				}
			});	
		}
		
		/**
		 * Init Form Validation for Child and Spouse Individual and Joint Modal
		 */
		initChildSpouseValidation = function() {
			
			jQuery.validator.addMethod("customFullname", function(value, element) {
				if (/^[a-zA-Z]+ [a-zA-Z ]+[a-zA-Z ]*$/.test(value)) {
					return true;
				} else {
					return false;
				};
			}, enterYourXFullNameErrorMsg);
			
			//Child and Spouse Individual Modal
			$(childSpouseIndividualDetailsFormJEl).validate({
				errorClass: "error",
				errorElement: "div",
				onfocusout: function(element) {
		           this.element(element);
		        }
			});
			
			//Child and Spouse Joint Modal
			$(childSpouseJointDetailsFormJEl).validate({
				errorClass: "error",
				errorElement: "div",
				onfocusout: function(element) {
		           this.element(element);
		        }
			});
			
			initChildSpouseIndividualValidationRules();
		}
		
		initChildSpouseIndividualValidationRules = function() {
			
			$(spouseChildFullNameJEl + "-error").remove();
			$(spouseChildDOBJEl + "-error").remove();
			
			//Remove Rules if exist
			if($(spouseChildFullNameJEl).rules()) {
				$(spouseChildFullNameJEl).rules("remove");
			}
			if($(spouseChildDOBJEl).rules()) {
				$(spouseChildDOBJEl).rules("remove");
			}
			
			var selPolicyOption = $(policyOptionJEl).val();
			var spouseChildFullNameErrMsg = enterYourXFullNameErrorMsg;
			
			var spouseChildDOBErrMsg = enterYourXDateOfBirthErrorMsg;
			var ageMinMaxErrMsg = ageBetween18to60ErrorMsg;
			
			var childSpouseMinAge = 18;
			var childSpouseMaxAge = 60;
			
			
			
			// Add Rule with updated error messages
			$(spouseChildFullNameJEl).rules( "add", {
				required: true,
				customFullname: true,
				messages: {
				    required: spouseChildFullNameErrMsg,
				    customFullname: spouseChildFullNameErrMsg
				}
			});
			
			$(spouseChildDOBJEl).rules( "add", {
				required: true,
				minAge:childSpouseMinAge,
				maxAge:childSpouseMaxAge,
				messages: {
				    required: spouseChildDOBErrMsg,
				    minAge: ageMinMaxErrMsg,
		  			maxAge: ageMinMaxErrMsg
				}
			});
		}
		
		initChildSpouseJointValidationRules = function() {
			var spouseFullNameErrDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", "spouse's");
			var spouseDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", "spouse's");
			
			var childFullNameErrDisplayMsg = enterYourXFullNameErrorMsg.replaceAll("{0}", "child's");
			var childDOBErrDisplayMsg = enterYourXDateOfBirthErrorMsg.replaceAll("{0}", "child's");
			
			$(spouseFullNameJEl + "-error").remove();
			$(spouseDOBJEl + "-error").remove();
			$(childFullNameJEl + "-error").remove();
			$(childDOBJEl + "-error").remove();
			
			// Add Rule with updated error messages
			$(spouseFullNameJEl).rules( "add", {
				required: true,
				customFullname: true,
				messages: {
				    required: spouseFullNameErrDisplayMsg,
				    customFullname: spouseFullNameErrDisplayMsg
				}
			});
			
			$(spouseDOBJEl).rules( "add", {
				required: true,
				minAge:18,
				maxAge:60,
				messages: {
				    required: spouseDOBErrDisplayMsg,
				    minAge: ageBetween18to60ErrorMsg,
		  			maxAge: ageBetween18to60ErrorMsg
				}
			});
			
			// Add Rule with updated error messages
			$(childFullNameJEl).rules( "add", {
				required: true,
				customFullname: true,
				messages: {
				    required: childFullNameErrDisplayMsg,
				    customFullname: childFullNameErrDisplayMsg
				}
			});
			
			$(childDOBJEl).rules( "add", {
				required: true,
				minAge:0,
				maxAge:15,
				messages: {
				    required: childDOBErrDisplayMsg,
				    minAge: ageBetween0to15ErrorMsg,
		  			maxAge: ageBetween0to15ErrorMsg
				}
			});
		}
		
		/**
		 * Close Family Joint Close Hanlder
		 */
		childSpouseJointModalCloseHandler = function() {
			//console.log("Joint Life Modal Close Handler");
			var savedPolicyOptionVal = $(savedPolicyOptionJEl).val();
			$(policyOptionJEl).val(savedPolicyOptionVal);
		}
		
		/**
		 * Close Family Individual Close Hanlder
		 */
		childSpouseIndividualModalCloseHandler = function() {
			//console.log("Individual Life Modal Close Handler");
			var savedPolicyOptionVal = $(savedPolicyOptionJEl).val();
			$(policyOptionJEl).val(savedPolicyOptionVal);
		}
		
		resetFamilyIndividualJointModalFormFields = function() {
			$(spouseFullNameJEl).val("");
			$(spouseDOBJEl).val("");
			$(childFullNameJEl).val("");
			$(childDOBJEl).val("");
			$(spouseChildFullNameJEl).val("");
			$(spouseChildDOBJEl).val("");
		}
		
		loadFamilyDetails = function() {
			showLoader();
			
			var selPolicyOption = $(policyOptionJEl).val();

			$.ajax({
				url:loadFamilyDetailsURL,
				type:"post",
				data: {},
				dataType: "json",
				async: true,
				success: function(response) {
					if(response.status = "success" && response.hasFamilyDetails) {
						if(response.data) {
							var familyDetailsResponse = response.data;
							
						}
					} 
					hideLoader();
				}
			});	
		}
		

		/**
		 * Show Chart on Chart Link Click Event and Hide Fund Table 
		 */
		showHighChart = function() {
			$(highchartContainerJEl).show();
			$(fundDataTableJEl).hide();
		}
		
		/**
		 * Show Fund Table on Fund Table Link Click Event and Hide Fund Chart 
		 */
		showFundTable = function() {
			$(fundDataTableJEl).show();
			$(highchartContainerJEl).hide();
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
		
		resetFamilyModalData = function() {
			$(investingForFullNameJEl).val("");
			$(investingForDOBJEl).val("");
			
			$("input[type=radio][name='" + familyRadioEl + "']").prop("checked", false);
			$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", true);
		}
		
		/**
		 * Init Family Modal Saved Data
		 */
		initFamilyModalData = function() {
			if(assuredRelationVal) {
				$("input[type=radio][name='" + familyRadioEl + "']").removeAttr("checked");
				$("input[type=radio][name='" + familyRadioEl + "'][value='" + assuredRelationVal + "']").prop('checked', true);
			}
			var isLittleChampBenefitOptedVal = $(isLittleChampBenefitOptedJEl).val();
			
			if(isLittleChampBenefitOptedVal == "Yes" && $(investingForJEl).val() == "Family") {
				$(removeLittleChampWrapperJEl).show();
			} else {
				$(removeLittleChampWrapperJEl).hide();
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
				$(investingForJEl).val("Family");
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
				
				saveCustomerInvestmentDataHanlder();
			} 
		}
		
		/**
		 * Save Individual Life Family Details
		 */
		saveIndividualLifeHandler = function(e) {
			e.preventDefault();
			var familyDetailsData = {};
			$(childSpouseIndividualDetailsFormJEl).validate().form();
			if($(childSpouseIndividualDetailsFormJEl).valid()) {
				showLoader();
				//showEditDetailsLink();
				
				$(savedPolicyOptionJEl).val($(policyOptionJEl).val());
				var selPolicyOption = $(policyOptionJEl).val();
				
				familyDetailsData[namespace + "customerInvestmentDetailsId"] = $(customerInvestmentDetailsIdJEl).val();
				familyDetailsData[namespace + "customerFamilyDetailsId"] = $(customerFamilyDetailsIdJEl).val();
				
				familyDetailsData[namespace + "isFamilyFromPolicyOptions"] = "Yes";
				var spouseChildFullNameVal = $(spouseChildFullNameJEl).val();
				var spouseChildDOBVal = $(spouseChildDOBJEl).val();
				
				updateInvestingForDropdown(selPolicyOption);
				
				updateBasicDetailsInvestingRadio(selPolicyOption);
				
				saveFamilyDetails(familyDetailsData);
				
				$(childSpouseIndividualModalJEl).modal("hide");
				
 				saveCustomerInvestmentDataHanlder();
 				
			}
		}
		 
		/**
		 * Save Joint Life Family Details
		 */
		saveJointLifeHandler = function(e) {
			e.preventDefault();
			var familyDetailsData = {};
			$(childSpouseJointDetailsFormJEl).validate().form();
			if($(childSpouseJointDetailsFormJEl).valid()) {
				
				//showEditDetailsLink();
				
				$(savedPolicyOptionJEl).val($(policyOptionJEl).val());
				var selPolicyOption = $(policyOptionJEl).val();
				familyDetailsData[namespace + "customerInvestmentDetailsId"] = $(customerInvestmentDetailsIdJEl).val();
				familyDetailsData[namespace + "customerFamilyDetailsId"] = $(customerFamilyDetailsIdJEl).val();
				familyDetailsData[namespace + "isFamilyFromPolicyOptions"] = "Yes";
				if(selPolicyOption == childJointLifeKey) {
					familyDetailsData[namespace + "spouseFullName"] = $(spouseFullNameJEl).val();
					familyDetailsData[namespace + "spouseDOB"] = $(spouseDOBJEl).val();
					familyDetailsData[namespace + "assuranceFullName"] = $(childFullNameJEl).val();
					familyDetailsData[namespace + "assuranceDob"] = $(childDOBJEl).val();
					familyDetailsData[namespace + "isSpouse"] = true;
					
					updateInvestingForDropdown(selPolicyOption);
					updateBasicDetailsInvestingRadio(selPolicyOption);
					
					saveFamilyDetails(familyDetailsData);
					
					$(childSpouseJointModalJEl).modal("hide");
					
					saveCustomerInvestmentDataHanlder();
				}
			}
		}
		
		editDetailsHandler = function() {
			var selPolicyOption = $(policyOptionJEl).val();
			
		}
		
		//Add/Update Family Details
		saveFamilyDetails = async function(familyDetailsData) {
			showLoader();
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
					generateBIRequest();
					$(littleChampModalJEl).modal("show");
				}
			});	
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
		 * Close Little Champ Congratuation Modal
		 */
		doNotAddLittleChampBenefitHandler = async function() {
			$(familyModalJEl).modal("hide");
			$(isLittleChampBenefitOptedJEl).val("No");

			$(removeLittleChampJEl).val("true");
			$(removeLittleChampWrapperJEl).hide();
			
			showLoader();
			
			var requestData = {};
			requestData[namespace + "isLittleChampBenefitOpted"] = "No";
			requestData[namespace + "customerFamilyDetailsId"] = $(customerFamilyDetailsIdJEl).val();
			
			$.ajax({
				url:updateLittleChampBenefitURL,
				type:"post",
				dataType: "json",
				data : requestData,
				async: true,
				success: function(response) {
					hideLoader();
				}
			});	
			
			$(littleChampModalJEl).modal("hide");
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				var isFormSubmitPending = $(isFormSubmitPendingJEl).val();
				if(isFormSubmitPending && isFormSubmitPending == "true") {
					$(customizeFormJEl).submit();
				}
			}
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
			}
		}
		
		/**
		 * Remove Little Champ From Basic Details
		 */
		removeLittleChampBenefitHandler = function() {
			$(removeLittleChampJEl).val("true");
			$(removeLittleChampWrapperJEl).hide();
		}
		
		/**
		 * Close Little Champ Modal
		 */
		littleChampModalCloseHandler = function() {
			$(littleChampModalJEl).modal("hide");
			var isFormSubmitPending = $(isFormSubmitPendingJEl).val();
			if(isFormSubmitPending && isFormSubmitPending == "true") {
				$(customizeFormJEl).submit();
			}
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
			
			var swpDisplayName = $(swpJEl).find(':selected').data("name");
			var policyTerm =  $(policyTermJEl).val();
			var updateLMSLeadData = {};
			
			updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "Y";
			if(investingForValue == "Family") {
				updateLMSLeadData[namespace + "ChildName"] = $(assuranceFullNameJEl).val();
				updateLMSLeadData[namespace + "ChildDob"] = $(assuranceDobJEl).val();
				updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "N";
			}
			
			updateLMSLeadData[namespace + "customerEnquiryId"] = $("#" + namespace + "customerEnquiryId").val();
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
			updateLMSLeadData[namespace + "TotalPremiumAmount"] = getInvestmentAmount();
			updateLMSLeadData[namespace + "PolicyTerm"] = policyTerm.indexOf("Whole Life") > 0 ? "100" : policyTerm;
			updateLMSLeadData[namespace + "PremiumPaymentTerm"] = $(payForJEl).val();
			updateLMSLeadData[namespace + "ProductName"] = $(productNameJEl).val();
			updateLMSLeadData[namespace + "PremiumPaymentFrequency"] = paymentFrequencyDisplayName;
			updateLMSLeadData[namespace + "FundDetails"] = JSON.stringify(getBIRequestFundDetails());
			updateLMSLeadData[namespace + "PolicyOption"] = policyOptionDisplayName;
			updateLMSLeadData[namespace + "Recommended_Objective"] = investmentObjectiveDisplayName;
			updateLMSLeadData[namespace + "SpouseName"] = $(spouseFullNameJEl).val();
			updateLMSLeadData[namespace + "SpouseDob"] = $(spouseDOBJEl).val();
			
			if(swpDisplayName == "Yes") {
				updateLMSLeadData[namespace + "SWP_YN"] = "Y";
			} else {
				updateLMSLeadData[namespace + "SWP_YN"] = "N";
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
		 * Add Little Champ Benefit, Save to Liferay Object, Generate BI and LMS update
		 */
		addLittleChampBenefitHandler = async function() {
			$(isLittleChampBenefitOptedJEl).val("Yes");
			
			$(removeLittleChampJEl).val("false");
			$(removeLittleChampWrapperJEl).show();
			
			var requestData = {};
			requestData[namespace + "isLittleChampBenefitOpted"] = "Yes";
			requestData[namespace + "customerFamilyDetailsId"] = $(customerFamilyDetailsIdJEl).val();
			
			$.ajax({
				url:updateLittleChampBenefitURL,
				type:"post",
				dataType: "json",
				data : requestData,
				async: true,
				success: function(response) {
				}
			});	
			
			$(littleChampModalJEl).modal("hide");
			var hasValidationErrors = await generateBIRequest();
			if(!hasValidationErrors) {
				var isFormSubmitPending = $(isFormSubmitPendingJEl).val();
				if(isFormSubmitPending && isFormSubmitPending == "true") {
					$(customizeFormJEl).submit();
				}
			}
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
		
		/**
		 * Update Total Return to Zero on BI Error
		 */
		handleTotalReturnOnBIError = function() {
			percentReturnAmountObj["8"] = "0";
			percentReturnAmountObj["4"] = "0";
			$(returnAmtValJEl).html("0");
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
		
		showEditDetailsLink = function() {
			$(editDetailsWrapperJEl).show();
		}
		
		hideEditDetailsLink = function() {
			$(editDetailsWrapperJEl).hide();
		}
		
		breakUpHandler = function(){
			$(breakUpBlockJEl).toggle();
		}
		$(breakUpBlockJEl).hide();
		
		/**
		 * Initializing JQuery Events
		 */
		initEvents = function(){
			$(policyTermJEl).on("change", onPolicyTermChange);
			$(proceedSubmitBtnJEl).click(proceedSubmitHandler);
			$(breakUpBtnJEl).click(breakUpHandler);
			$(breakUpContinueJEl).click(breakUpHandler);
			$(totalReturnsValueJEl).on("change", totalReturnsValueChange);
			
			$(customizeFundTableJEl).on("click", ".increase", onFundAllocationIncreaseDecrease);
			$(customizeFundTableJEl).on("click", ".decrease", onFundAllocationIncreaseDecrease);
			
			
			$(investmentAmountJEl).blur(onInvestmentAmountBlur);
			$(investmentAmountJEl).on("input", allowOnlyNumbersHandler);
			$(investmentAmountJEl).on("keyup", populateAmountInWord);
			$(allowAlphaSpaceJEl).on("input", allowOnlyAlphaAndSpaceHandler);

			$(policyOptionJEl).on("change", onPolicyOptionChange);
			$(investingForJEl).on("change", onInvestingForChange);
			$(payForJEl).on("change", onPayForChange);
			$(paymentOptionJEl).on("change", onPaymentOptionChange);
			$(fundManagementJEl).on("change", onFundManagementChange);
			$(swpJEl).on("change", onSWPChange);
			$(fundValuetobeWithdrawnJEl).on("change", onFundValuetobeWithdrawnChange);
			$(policyYearFromWhichSWPPayableJEl).on("change", onPolicyYearFromWhichSWPPayableChange);
			$(swpFrequencyJEl).on("change", onSWPFrequencyChange);
		
			$(customizeFundTableJEl).on("change", projectedReturnFundAllocation, onProjectedReturnFundAllocationChange);
			
			
			$(chartLinkJEl).click(showHighChart);
			$(fundTableLinkJEl).click(showFundTable);
			
			$(customizeFundLinkJEl).click(loadCustomizeFundsTable);
			$(featuresLinkJEl).click(featuresLinksHandler);
			
			$(childSpouseJointModalCloseJEl).click(childSpouseJointModalCloseHandler);
			$(childSpouseIndividualModalCloseJEl).click(childSpouseIndividualModalCloseHandler);

			$(childSpouseIndividualDetailsFormJEl).submit(saveIndividualLifeHandler);
			$(childSpouseJointDetailsFormJEl).submit(saveJointLifeHandler);
			$("#editDetailsLink").click(editDetailsHandler);
			
			$(benchMarkAndReturnSelectJEl).on("change", onBenchMarkAndPastReturnChange);
			
			$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
			
			$(familyDetailsFormJEl).submit(saveFamilyDetailsHandler);
			$(familyModalCloseJEl).click(familyModalCloseHandler);
			
			$(addLittleChampBenefitJEl).click(addLittleChampBenefitHandler);
			$(doNotaddOnJEl).click(doNotAddLittleChampBenefitHandler);
			$(removeLittleChampBenefitLabelJEl).click(removeLittleChampBenefitHandler);
			
		};
		
		initDefaultProductValues = function() {
			console.log("Current Selected : " + $(payForJEl).val());
			
			
			//updateBasicDetailsInvestingRadio($(policyOptionJEl).val());
			
			if(defaultPayFor) {
				updatePayForDropdown($(policyTermJEl).val());
				
				initSWP();

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
			
			var hasDefaultProdcutVal = initDefaultProductValues();
			//console.log(hasDefaultProdcutVal);
			
			populateAmountInWord();
			updateInvestmentAmountLocaleIN();
			
			if(cookieLeadId) {
				generateBIRequest();
			} else {
				//Redirect to Product LMS Form
				location.href = "/capital-secure-plus";
			}
			
			initFamilyModal();
			initFamilyModalData();
			initFamilyModalFormValidation();
			
			//initChildSpouseValidation();
			//initChildSpouseJointValidationRules();
			
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
	
	edelweissProductCustomizePortlet.viewWealthSecurePlusCustomize = viewWealthSecurePlusCustomize;
})($, (window.edelweissProductCustomizePortlet = window.edelweissProductCustomizePortlet || {}));