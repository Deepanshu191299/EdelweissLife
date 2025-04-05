var percentReturnAmountObj = {};
(function($, edelweissProductCustomizePortlet) {
	
	function viewProductCustomize(config){
		var namespace = config.namespace,
			customizeFormJEl = config.customizeFormJEl,
			policyOptionEl = config.policyOptionEl,
			investingForJEl = config.investingForJEl,
			policyTermEl = config.policyTermEl,
			policyForEl = config.policyForEl,
			investmentAmountEl = config.investmentAmountEl,
			swpEl = config.swpEl,
			policyOptionJEl = config.policyOptionJEl,
			policyTermJEl = config.policyTermJEl,
			policyForJEl = config.policyForJEl,
			paymentOptionEl = config.paymentOptionEl,
			paymentOptionJEl = config.paymentOptionJEl,
			fundManagementJEl = config.fundManagementJEl,
			swpJEl = config.swpJEl,
			investmentAmountJEl = config.investmentAmountJEl,
			benchMarkAndReturnSelectJEl = config.benchMarkAndReturnSelectJEl,
			returnAmtValJEl = config.returnAmtValJEl,
			featuresLinkJEl = "#featuresLink",
			totalReturnsValueJEl = config.totalReturnsValueJEl,
			projectedReturnFundAllocation = config.projectedReturnFundAllocation,
			fundDataTableJEl = config.fundDataTableJEl,
			customizeFundLinkJEl = config.customizeFundLinkJEl,
			customizeFundTableJEl = config.customizeFundTableJEl,
			productInvestmentAmountRelsData = JSON.parse(config.productInvestmentAmountRelsData),
			defaultPolicyTerm = config.defaultPolicyTerm,
			defaultPolicyFor = config.defaultPolicyFor,
			edelweissCustomizeFundsURL = config.edelweissCustomizeFundsURL,
			benchMarkAndReturnsObj = {},
			edelweissGenerateBIURL = config.edelweissGenerateBIURL,
			errorMessageModalJEl = config.errorMessageModalJEl,
			chartLinkJEl = config.chartLinkJEl,
			fundTableLinkJEl = config.fundTableLinkJEl,
			highchartContainerEl = config.highchartContainerEl,
			highchartContainerJEl = config.highchartContainerJEl,
			fundDataTableJEl = config.fundDataTableJEl,
			totalReturnsJEl = config.totalReturnsJEl,
			illustrationURLJEl = config.illustrationURLJEl,
			quotationIdJEl = config.quotationIdJEl,
			projectedReturnsDataJEl = config.projectedReturnsDataJEl,
			cookieLeadId = config.cookieLeadId,
			fullNameJEl = config.fullNameJEl,
			dateOfBirthJEl = config.dateOfBirthJEl,
			emailJEl = config.emailJEl,
			assuranceFullNameJEl = config.assuranceFullNameJEl,
			assuranceDobJEl = config.assuranceDobJEl,
			genderJEl = config.genderJEl,
			genderEl = config.genderEl,
			mobileNumberJEl = config.mobileNumberJEl,
			assuredRelationEl = config.assuredRelationEl,
			proceedSubmitBtnJEl = config.proceedSubmitBtnJEl,
			loaderJEl = config.loaderJEl,
		 	productCodeJEl = config.productCodeJEl,
		 	familyModalJEl = config.familyModalJEl,
		 	investingForVal = config.investingForVal,
		 	investingForWrapperJEl = config.investingForWrapperJEl,
		 	investingForGrandsonEl = config.investingForGrandsonEl,
		 	investingForGrandDaughterEl = config.investingForGrandDaughterEl,
		 	familyRadioEl = config.familyRadioEl,
		 	investingForFullNameEl = config.investingForFullNameEl,
		 	investingForDOBEl = config.investingForDOBEl,
		 	investingForFullNameJEl = config.investingForFullNameJEl,
		 	investingForDOBJEl = config.investingForDOBJEl,
		 	investingForFullNameLableJEl = config.investingForFullNameLableJEl,
		 	investingForDOBLabelJEl = config.investingForDOBLabelJEl,
		 	saveFamilyDetailsURL = config.saveFamilyDetailsURL,
		 	saveFamilyBtnJEl = config.saveFamilyBtnJEl,
		 	familyDetailsFormJEl = config.familyDetailsFormJEl,
			doNotaddOnJEl = config.doNotaddOnJEl,
			assuredRelationVal = config.assuredRelationVal,
			saveCustomerInvestmentDataURL = config.saveCustomerInvestmentDataURL,
			updateLMSLeadURL = config.updateLMSLeadURL,
			fullNameEl = namespace + "fullName",
			dateOfBirthEl = namespace + "dateOfBirth",
			emailEl = namespace + "email",
			investmentObjectiveEl = namespace + "investmentObjective",
			mobileNumberEl = namespace + "mobileNumber",
			productNameJEl= config.productNameJEl,
			investmentObjectiveEl = config.investmentObjectiveEl,
			deleteFamilyDetailsURL = config.deleteFamilyDetailsURL,
			familyModalCloseJEl = config.familyModalCloseJEl,
			amtInWordsJEl = "#amt-in-word",
			personalDetailsJEl = "#personal-details",
			errorMessageContainerJEl = config.errorMessageContainerJEl,
			investingForCurVal = config.investingForCurVal,
			saveFundDetailsURL = config.saveFundDetailsURL,
			loadAllocatedFundsURL = config.loadAllocatedFundsURL,
			customerFundAllocationDetailIdJEl = "#" + namespace + "customerFundAllocationDetailId",
			customerAllocatedFundDetailsJEl = "#" + namespace + "customerAllocatedFundDetails",
			customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
			isFormSubmitPendingJEl = "#" + namespace + "isFormSubmitPending",
			customerInvestmentDetailsIdJEl = "#" + namespace + "customerInvestmentDetailsId",
			customerPolicyDetailsIdJEl = "#" + namespace + "customerPolicyDetailsId",
			customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
			allowAlphaSpaceJEl = ".edelweiss-allow-aplha-space",
			pdfBytes = config.pdfBytes,
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
			rupeeSignLabel =  LANG_MESSAGES["rupee-sign"];
			
			percentReturnAmountObjRef = percentReturnAmountObj;
		
		var SWPfrequencyMap = {};
			
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
			
		/**
		 * Load Policy Terms
		 */	
		updatePolicyTermDropdown = function(){
			
			var selectedPolicyTerm = $(policyTermJEl).val()?$(policyTermJEl).val():defaultPolicyTerm;
			
			updateMinimumInvestmentAmount();
			if(!productValidation){
				return;
			}
			var age = calculateAge(customerDob);
			
			$(policyTermJEl).find("option").remove();
			for (let curPolicyTerm = Number(productValidation.minPT); 
			curPolicyTerm <= Number(productValidation.maxPT); curPolicyTerm++) {
				if(curPolicyTerm+age>productValidation.maxMaturityAge || curPolicyTerm+age<productValidation.minMaturityAge){
					continue;
				}
				var selected = curPolicyTerm == selectedPolicyTerm ? " selected" : "";
			 	$(policyTermJEl).append('<option value="' + curPolicyTerm + '" ' + selected + '>' + curPolicyTerm + '</option>');
			}
			
		};
		
		/**
		 * Load Policy For Based on Policy Term
		 */
		onPolicyTermChange = async function(){
			var policyTerm = $(policyTermJEl).val();
			updatePayForDropdown(policyTerm);
			updateMinimumInvestmentAmount();

			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		};
		
		updatePayForDropdown = function(selectedPolicyTerm) {
			
			if(!productValidation){
				return;
			}

			var selectedPolicyFor  = $(policyForJEl).val();
			var age = calculateAge($(dateOfBirthJEl).val());
			$(policyForJEl).find("option").remove();
			for (let curPolicyFor = Number(productValidation.minPPT); 
				curPolicyFor <= (productValidation.maxPPT>Number(selectedPolicyTerm)?Number(selectedPolicyTerm):Number(productValidation.maxPPT)); curPolicyFor++) {
				
				if(curPolicyFor < 10 && age>45){
					continue;
				}
				
				var selected = curPolicyFor == selectedPolicyFor ? " selected " : "";
				$(policyForJEl).append('<option value="' + curPolicyFor + '" ' + selected + '>' + curPolicyFor + '</option>');
			}
			
			//If curent selected option is not available, set it to Policy term value
			if(!$(policyForJEl+" option[value='"+selectedPolicyFor+"']").length && $(policyForJEl+" option[value='"+selectedPolicyTerm+"']").length){
				$(policyForJEl).val(selectedPolicyTerm);
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
				$(returnAmtValJEl).html(Math.round(totalReturnAmount).toLocaleString("en-IN"));
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
			            $("td[data-projectedreturns=" + curElement.getAttribute("data-allocation") + "]").html(rupeeSignLabel + "&nbsp;" + Math.round(curFundProjectedReturnAmt).toLocaleString("en-IN"));
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
		

		featuresLinksHanlder = function() {
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
				wealthGainPlusGenerateBIRequest();
				
				saveFundDetails();
			} 
		}
		
		/**
		 * Create Return Amount JSON by 4(BIJson) and 8(BIJson2) Percentage
		 */
		createReturnAmountByPercent = function(biResponseData) {
			var selectedPolicyTerm = $(policyTermJEl).val();
			var biJSON = JSON.parse(biResponseData["BIJson"]);
			var biJSON2 = JSON.parse(biResponseData["BIJson2"]);
			
			var selectedPolicyTermBiJson = $.grep(biJSON, function (curBiJSON, index) {
				return Number(curBiJSON.POLICYYEAR) == selectedPolicyTerm;
            });
			if(selectedPolicyTermBiJson.length > 0) {
				var fourPerReturnAmount = selectedPolicyTermBiJson[0]["FINAL_FV"];
				percentReturnAmountObj["4"] = fourPerReturnAmount;
			}
			
			var selectedPolicyTermBiJson2 = $.grep(biJSON2, function (curBiJSON2, index) {
				return Number(curBiJSON2.POLICYYEAR) == selectedPolicyTerm;
            });
			if(selectedPolicyTermBiJson2.length > 0) {
				var eightPerReturnAmount = selectedPolicyTermBiJson2[0]["FINAL_FV"];
				percentReturnAmountObj["8"] = eightPerReturnAmount;
				$(returnAmtValJEl).html(Math.round(eightPerReturnAmount).toLocaleString("en-IN"));
			}
			
			return percentReturnAmountObj;
		};

		/**
		 * Update Total Return to Zero on BI Error
		 */
		handleTotalReturnOnBIError = function() {
			percentReturnAmountObj["8"] = "0";
			percentReturnAmountObj["4"] = "0";
			$(returnAmtValJEl).html("0");
		}
		
		/**
		 * Show Validation message from Generate BI Api response
		 */
		handleInputValidationStatus = function(biResponse) {
			var biResponseData = JSON.parse(biResponse["JsonOutput"]);
			var inputValidationStatusArray = biResponseData["InputValidationStatus"];
			
			if(inputValidationStatusArray && inputValidationStatusArray[0] && inputValidationStatusArray[0]["ErrorMessage"].length > 0) {
				disableProceedButton();
				var errorMessage = inputValidationStatusArray[0]["ErrorMessage"][0].Value;
				$(errorMessageContainerJEl).empty();
				$(errorMessageContainerJEl).html(errorMessage);
				$(errorMessageModalJEl).modal('show');
				handleTotalReturnOnBIError();
				return true;
			} else if(biResponseData["Status"] && biResponseData["Status"] == "Failed") {
				disableProceedButton();
				var errorMessage = biResponseData["Step"];
				$(errorMessageContainerJEl).empty();
				$(errorMessageContainerJEl).html(errorMessage);
				$(errorMessageModalJEl).modal('show');
				handleTotalReturnOnBIError();
				return true;
			}else if(inputValidationStatusArray && inputValidationStatusArray[0] && inputValidationStatusArray[0]["GeneralError"]) {
				disableProceedButton();
				var errorMessage = inputValidationStatusArray[0]["GeneralError"];
				$(errorMessageContainerJEl).empty();
				$(errorMessageContainerJEl).html(errorMessage);
				$(errorMessageModalJEl).modal('show');
				handleTotalReturnOnBIError();
				return true;
			}else {
				$(proceedSubmitBtnJEl).removeClass("disabled-btn");
				$(proceedSubmitBtnJEl).prop("disabled", false);
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
				fundDetails["FundValuetobeWithdrawn"] = "131";
				$(customizeFundTableJEl + " .allocationAmt").each(function(d) {
					var fundDetailsKey = $(this).data("fundname").replace(/ /g,'');
					fundDetails[fundDetailsKey] = $(this).text();
	            });
				fundDetails["LongTermBondFund"] = 0;
				return fundDetails;
			} else if(fundAllocationDetailsId) {
				var customerAllocatedFundDetailsVal = $(customerAllocatedFundDetailsJEl).val();
				var fundDetails = new Object({});
				fundDetails["FUNDSTRATEGYID"] = fundStrategyId;
				fundDetails["FundValuetobeWithdrawn"] = "131";
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
	                "GILTFund": "0"
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
				return null;
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
				"PROPOSER_GENDER": gender == "Male" ? "M" : "F",
				"optionLevel1": "1",
				"optionValue1": "0",
				"PR_CHANNEL": "1",
				"INTEREST_RATES": "0.12928763122432407",
				"category": "4",
				"CashFlow": "Y",
				"COMPANY_STATE": "0",
				"AGENT_ID": "TW9001",
				"REQUESTSOURCE": "1",				
				"PR_MI": "0",
				"PR_ModalPrem": "",
				"isPdfByte":$(pdfBytes).val(),
				"GSTIN": "0",
				"GSTIN_Number": "0"				

			}
			return $.extend(true,liDetails,proposerDetails);
		}
		
		/**
		 * Generate BI Request Product
		 */
		getBIRequestProduct = function() {
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
			
			var policyOption = $(policyOptionJEl).val();
			var premPayTerm = $(policyForJEl).val();
			console.log("premPayTerm : " + premPayTerm);
			var prSAMF = "0";
			
			var product = {
				"PR_ID":  $(productCodeJEl).val(),
				"INPUT_MODE": $(paymentOptionJEl).val(),
				"PR_PT": $(policyTermJEl).val(),
				"PR_PPT": premPayTerm,
				"PR_ANNPREM" : annualPremium,
				"PR_SAMF" : prSAMF,
				"PR_SA": annualPremium,
				
			}
			product = $.extend(true,product,getBIRequestFundDetails());
			return product;
			
		}
		
		/**
		 * Update Form Data
		 */
		updateFormData = function(biResponseData, percentReturnAmountObj) {
			biResponseData = JSON.parse(JSON.stringify(biResponseData));
			var selectedReturn = $(totalReturnsValueJEl).val();
			if(percentReturnAmountObj) {
				var curProjectedReturn = Math.round(percentReturnAmountObj[selectedReturn]);
				$(returnAmtValJEl).html(curProjectedReturn.toLocaleString("en-IN"));
				$(totalReturnsJEl).val(curProjectedReturn);
				
				$(projectedReturnsDataJEl).val(JSON.stringify(percentReturnAmountObj));
			}
			if(biResponseData) {
				$(illustrationURLJEl).val(biResponseData["PdfPath"]);
				biResponseData = JSON.parse(biResponseData["JsonOutput"]);
				if(biResponseData["QuotationId"]) {
					$(quotationIdJEl).val(biResponseData["QuotationId"])
				}
			}
			
			populateAmountInWord();
		}
		
		/**
		 * Generate BI Request
		 */
		wealthGainPlusGenerateBIRequest = async function() {
			//Showing Loader
			showLoader();
			if(cookieLeadId) {
				var hasValidationErrors = false;
				
				
				percentReturnAmountObj = {};
				var biRequestData = new Object({});

				var basicDetails = getBIRequestBasicDetails(); 
				var productDetails = getBIRequestProduct();
				biRequestData[namespace + "biRequestData"] = JSON.stringify($.extend(true,basicDetails,productDetails));;
				try{
				const result = await $.ajax({
					url : edelweissGenerateBIURL,
					dataType : "json",
					data : biRequestData,
					type : "POST"
				});
				
				if(result.status = "success") {
					var biResponse = result.data;
					hasValidationErrors = handleInputValidationStatus(biResponse);
					if(!hasValidationErrors) {
						var biResponseData = JSON.parse(biResponse["JsonOutput"]);
						percentReturnAmountObj = createReturnAmountByPercent(biResponseData);
						
						updateFormData(biResponse, percentReturnAmountObj);
						
						// Update Projected Funds Column based on Allocation Percentage
						renderFunds($(projectedReturnFundAllocation).val());
						
						//Render Graph and Return Table
						renderAllReturnsTableAndGraph(biResponseData);
						
						//Update LMS Lead
						updateLMSLead();
					}
				} else {
					$(errorMessageModalJEl).modal("show");
				}
				hideLoader();
				return hasValidationErrors;
			} catch(error) {
				disableProceedButton();
				handleTotalReturnOnBIError();
				hideLoader();
				$(errorMessageModalJEl).modal("show");
				console.log(error);
				return true;
			}
			} else {
				hideLoader();
				console.log("Lead ID does not exist");
				return true;
			}
		};
		
		/**
		 * Render Graph and Return Table based on selected Return 4% or 8%
		 */
		renderAllReturnsTableAndGraph = function(biResponseData) {
			var selectedReturn = $(totalReturnsValueJEl).val();
			switch (selectedReturn) {
		    	case "4":
		    		var biJSONArray = JSON.parse(biResponseData.BIJson);
		    		break;
		    	case "8":
		    		biJSONArray = JSON.parse(biResponseData.BIJson2);
		    		break;
		    }
			var pt = $(policyTermJEl).val();
			var interval = 40 > biJSONArray.length ? 5 : 10;
			
			var boosterPremiumArray = [];
			var additionArray = [];
			var fundsArray = [];
			var yearsCategoryArray = [];
			var tableBodyData = "";
			$.each(biJSONArray, function( index, curBIJson) {
				var curPolicyYear = Math.round(curBIJson["POLICYYEAR"]);
				var cumPrem = curBIJson["CUM_PREM"] ? " ₹ " + parseInt(Math.round(curBIJson["CUM_PREM"])).toLocaleString("en-IN") : "-";
				var allocationCharge = curBIJson["LA"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["LA"])).toLocaleString("en-IN") : "-";
				var boosterPremium = curBIJson["BOOSTER_ADD"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["BOOSTER_ADD"])).toLocaleString("en-IN") : "-";
				var finalFV = curBIJson["FINAL_FV"] ? " ₹ " + Number.parseInt(Math.round(curBIJson["FINAL_FV"])).toLocaleString("en-IN") : "-";
			
				tableBodyData += "<tr><td>" + curPolicyYear + "</td><td>" + cumPrem + "</td><td>" + allocationCharge + "</td><td>" + boosterPremium + "</td><td>" + finalFV + "</td></tr>"; 
			
				var start = index + 1;
				if(start % interval == 0) {
					var curPolicyYear = Math.round(curBIJson["POLICYYEAR"]);
					yearsCategoryArray.push(curPolicyYear + " " + "Years");
					
					if(curBIJson["FINAL_FV"]) {
						fundsArray.push(curBIJson["FINAL_FV"]);
					}
					
					//if(curBIJson["BOOSTER_PREMIUM"]) {
						boosterPremiumArray.push(curBIJson["BOOSTER_PREMIUM"]);
					//}
					
					if(curBIJson["EXTRA_ALLOC_CHARGE"]) {
						additionArray.push(curBIJson["EXTRA_ALLOC_CHARGE"]);
					}
				}else if(start == pt){
					var curPolicyYear = Math.round(curBIJson["POLICYYEAR"]);
					yearsCategoryArray.push(curPolicyYear + " " + "Years");
					
					if(curBIJson["FINAL_FV"]) {
						fundsArray.push(curBIJson["FINAL_FV"]);
					}
					
					//if(curBIJson["BOOSTER_PREMIUM"]) {
						boosterPremiumArray.push(curBIJson["BOOSTER_PREMIUM"]);
					//}
					
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
		 * Updates the Minimum Investment Amount By Payment Frequency as Configured in Object
		 */
		updateMinimumInvestmentAmount = function() {
			var paymentOptionVal = $(paymentOptionJEl).val();
			var pptVal = $(policyForJEl).val();
			var ptVal = $(policyTermJEl).val();
			
			var currentInvestmentAmount = Number(getInvestmentAmount());
			
			if(!productInvestmentAmountRelsData || !productInvestmentAmountRelsData.length > 0) {
				return;
			}
			
			productInvestmentAmountRelsData.forEach(curProductInvestmentAmountRel => {
				var curMinimumInvestmentAmount = Number(curProductInvestmentAmountRel.minimumInvestmentAmount);
			  
				if(curProductInvestmentAmountRel.paymentOption.key == paymentOptionVal && 
						ptVal >= Number(curProductInvestmentAmountRel.minPT) && ptVal <= Number(curProductInvestmentAmountRel.maxPT) &&
						pptVal >= Number(curProductInvestmentAmountRel.minPPT) && pptVal <= Number(curProductInvestmentAmountRel.maxPPT) &&
						currentInvestmentAmount < curMinimumInvestmentAmount) {
			    	$(investmentAmountJEl).val(curProductInvestmentAmountRel.minimumInvestmentAmount);
			    	return;
			    }
			});
			
			populateAmountInWord();
			updateInvestmentAmountLocaleIN();
		}
		
		/**
		 * Calling Generate BI Request on Change of Select and On blur of Input element
		 */
		onInvestmentAmountBlur = async function() {
			updateMinimumInvestmentAmount();
			
			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
 		onPolicyOptionChange = async function() {
 			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
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
				
				updateYourDetailsHeader();

				deleteFamilyDetails();
				var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
				if(!hasValidationErrors) {
					saveCustomerInvestmentDataHanlder();
				}
			}
		}
		
		onPolicyForChange = async function() {
			updateMinimumInvestmentAmount();
			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		onPaymentOptionChange = async function() {
			updateMinimumInvestmentAmount();
			
			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
		}
		
		onFundManagementChange = async function() {
			resetFundAllocation();
			
			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(!hasValidationErrors) {
				saveCustomerInvestmentDataHanlder();
			}
			
		}
		
		processSubmitHanlder = async function(e) {
			e.preventDefault();
			$(pdfBytes).val('Y');
			var hasValidationErrors = await wealthGainPlusGenerateBIRequest();
			if(hasValidationErrors) {
				console.error("Error in generate BI API call");
				return;
			}

			saveFundDetails();
			$(customizeFormJEl).submit();
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
		
		resetFamilyModalData = function() {
			$(investingForFullNameJEl).val("");
			$(investingForDOBJEl).val("");
			
			$("input[type=radio][name='" + familyRadioEl + "']").prop("checked", false);
			$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", false);
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
				$("input[type=radio][name='" + familyRadioEl + "'][value='Son']").prop("checked", true);
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
		familyModalCloseHanlder = function() {
			$(investingForJEl).val("Myself");
		}
		
		/**
		 * Show Congratulation Modal if form is valid
		 */
		saveFamilyDetailsHanlder = function(e) {
			e.preventDefault();
			$(familyDetailsFormJEl).validate().form();
			if($(familyDetailsFormJEl).valid()) {
				saveFamilyDetails(true);
			} 
		}
		
		//Add/Update Family Details
		saveFamilyDetails = async function(showBenefitModal) {
			showLoader();
			var selectedFamilyRadioElement = $('input[type=radio][name=' + familyRadioEl + ']:checked');
			var familyDetailsData = {};
			familyDetailsData[namespace + "assuranceFullName"] = $(investingForFullNameJEl).val();
			familyDetailsData[namespace + "assuranceDob"] = $(investingForDOBJEl).val();
			familyDetailsData[namespace + "assuredRelation"] = selectedFamilyRadioElement.val();
			familyDetailsData[namespace + "assuredGender"] = selectedFamilyRadioElement.data("gender");
					
			$(assuranceFullNameJEl).val($(investingForFullNameJEl).val());
			$(assuranceDobJEl).val($(investingForDOBJEl).val());
			
			$("input[type=radio][name='" + basicInvestingForEl + "']").prop("checked", false);
			$("input[type=radio][name='" + basicInvestingForEl + "'][value='Family']").prop('checked', true);
			
			$("input[type=radio][name='" + assuredRelationEl + "']").prop("checked", false);
			$("input[type=radio][name='" + assuredRelationEl + "'][value='" + selectedFamilyRadioElement.val() + "']").prop('checked', true);
			
			//Update Your Details Header after Saving Family Details
			$(investingForJEl).val("Family");
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
						}
					}
				});	
			} catch(e) {
				console.log(e);
			}
			
			wealthGainPlusGenerateBIRequest();
			$(familyModalJEl).modal("hide");
		}
		
		/**
		 * Update Your Details Header
		 */
		updateYourDetailsHeader = function() {
			$(personalDetailsJEl).empty()
			$(personalDetailsJEl).append("<li class='nav-item'>" + $(fullNameJEl).val() + "</li>");
			$(personalDetailsJEl).append("<li class='nav-item'>" +  $('input[type=radio][name=' + genderEl + ']:checked').val() + "</li>");
			$(personalDetailsJEl).append("<li class='nav-item'>" + $(dateOfBirthJEl).val() + "</li>");
			$(personalDetailsJEl).append("<li class='nav-item'> +91 " + $(mobileNumberJEl).val() + "</li>");
			$(personalDetailsJEl).append("<li class='nav-item'>" + $(emailJEl).val() + "</li>");
			$(personalDetailsJEl).append("<li class='nav-item'>" + $('input[name="' + investmentObjectiveEl + '"]:checked').data("name") + "</li>");
			customerDob = $(dateOfBirthJEl).val();
			var selectedInvestingFor = $(investingForJEl).val();
			$(personalDetailsJEl).append("<li class='nav-item'>" + $(investingForJEl).val() + "</li>");
			if(selectedInvestingFor == "Family") {
				$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceFullNameJEl).val() + "</li>");
				$(personalDetailsJEl).append("<li class='nav-item'>" + $(assuranceDobJEl).val() + "</li>");
				customerDob = $(assuranceDobJEl).val();
			} 
			
			updatePolicyTermDropdown();
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
			var swpDisplayName = $(swpJEl).find(':selected').data("name");
			var investingForValue = $('input[type=radio][name=' + basicInvestingForEl + ']:checked').val();
			var updateLMSLeadData = {};
			updateLMSLeadData[namespace + "customerEnquiryId"] = $("#" + namespace + "customerEnquiryId").val();
			updateLMSLeadData[namespace + "ChildName"] = $(assuranceFullNameJEl).val();
			updateLMSLeadData[namespace + "ChildDob"] = $(assuranceDobJEl).val();
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
			updateLMSLeadData[namespace + "PolicyTerm"] = $(policyTermJEl).val();
			updateLMSLeadData[namespace + "PremiumPaymentTerm"] = $(policyForJEl).val();
			updateLMSLeadData[namespace + "ProductName"] = $(productNameJEl).val();
			updateLMSLeadData[namespace + "PremiumPaymentFrequency"] = paymentFrequencyDisplayName;
			updateLMSLeadData[namespace + "FundDetails"] = JSON.stringify(getBIRequestFundDetails());
			updateLMSLeadData[namespace + "PolicyOption"] = policyOptionDisplayName;
			updateLMSLeadData[namespace + "Recommended_Objective"] = investmentObjectiveDisplayName;
			updateLMSLeadData[namespace + "SWP_YN"] = swpDisplayName == "Yes" ? "Y" : "N" ;
			updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "Y";
			if(investingForValue == "Family") {
				updateLMSLeadData[namespace + "IS_LA_PR_Same_YN"] = "N";
			}
			
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
			
		/**
		 * Initializing JQuery Events
		 */
		initEvents = function(){
			$(policyTermJEl).on("change", onPolicyTermChange);
			$(proceedSubmitBtnJEl).click(processSubmitHanlder);
			
			$(totalReturnsValueJEl).on("change", totalReturnsValueChange);
			
			$(customizeFundTableJEl).on("click", ".increase", onFundAllocationIncreaseDecrease);
			$(customizeFundTableJEl).on("click", ".decrease", onFundAllocationIncreaseDecrease);
			
			$(investmentAmountJEl).blur(onInvestmentAmountBlur);
			$(investmentAmountJEl).on("keyup", populateAmountInWord);
			$(investmentAmountJEl).on("input", allowOnlyNumbersHandler);
			$(allowAlphaSpaceJEl).on("input", allowOnlyAlphaAndSpaceHandler);

			$(policyOptionJEl).on("change", onPolicyOptionChange);
			$(investingForJEl).on("change", onInvestingForChange);
			$(policyForJEl).on("change", onPolicyForChange);
			$(paymentOptionJEl).on("change", onPaymentOptionChange);
			$(fundManagementJEl).on("change", onFundManagementChange);
		
			$(customizeFundTableJEl).on("change", projectedReturnFundAllocation, onProjectedReturnFundAllocationChange);
			
			
			$(chartLinkJEl).click(showHighChart);
			$(fundTableLinkJEl).click(showFundTable);
			
			$(customizeFundLinkJEl).click(loadCustomizeFundsTable);
			$(featuresLinkJEl).click(featuresLinksHanlder);
			
			$(benchMarkAndReturnSelectJEl).on("change", onBenchMarkAndPastReturnChange);
			
			$(investingForWrapper).on("change", 'input[type=radio][name='+familyRadioEl+']', onFamilyRadioChange);
			
			$(familyDetailsFormJEl).submit(saveFamilyDetailsHanlder);
			$(familyModalCloseJEl).click(familyModalCloseHanlder);
		};
		
		wealthGainPlusInitDefaultProductValues = function() {
			console.log(defaultPolicyFor);
			console.log("Current Selected : " + $(policyForJEl).val());
			if(investingForCurVal) {
				$(investingForJEl).val(investingForCurVal);
			}
			
			if(defaultPolicyFor) {
				updatePayForDropdown($(policyTermJEl).val());
				if($(policyForJEl+" option[value='"+defaultPolicyFor+"']").length){
					$(policyForJEl).val(defaultPolicyFor);
				}
			}
			$(customizeFormJEl + " select option").filter(function() {
				return !this.value || $.trim(this.value).length == 0 || $.trim(this.text).length == 0;
			}).remove();
		}
	
		init = async function(){
			initEvents();
			updatePolicyTermDropdown();
			wealthGainPlusInitDefaultProductValues();

			populateAmountInWord();
			
			updateInvestmentAmountLocaleIN();
			
			initFamilyModal();
			initFamilyModalData();
			initFamilyModalFormValidation();
			
			$(".edelweiss-mask-date").inputmask({ alias:"datetime", inputFormat:"dd/mm/yyyy"});

			if(cookieLeadId) {
				wealthGainPlusGenerateBIRequest();
			} else {
				//Redirect to Product LMS Form
				location.href = "/wealth-gain";
			}
			
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