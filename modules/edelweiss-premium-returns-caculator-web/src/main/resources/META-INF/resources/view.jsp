<%@ include file="init.jsp" %>

<liferay-journal:journal-article  articleId="<%= articleId%>" groupId="<%= themeDisplay.getScopeGroupId()%>"/>	

<c:if test="<%=!showGraph %>">
	<h5>Graph is not enabled, please enable from the configuration</h5>
</c:if>
<aui:script>

document.addEventListener("DOMContentLoaded", function(){

var generateBI =  (requestJson) => {

var data = {};
data.<portlet:namespace />requestJson = JSON.stringify(requestJson);
data.<portlet:namespace />apiType = premiumCalAPIType.value;

return Liferay.Util.fetch('<portlet:resourceURL id="/calculate_premium_return"></portlet:resourceURL>', {
			body: Liferay.Util.objectToFormData(data),
			method: 'POST',
       })
}


var premiumCalBuyNow = document.getElementById('premiumCalBuyNow');
var premiumCalPT = document.getElementById('premiumCalPT');
var premiumCalRequestJson = document.getElementById('premiumCalRequestJson');
var premiumCalAnnualPremium = document.getElementById('premiumCalAnnualPremium');
var premiumCalPPT = document.getElementById('premiumCalPPT');
var premiumCalFinalValue = document.getElementById('premiumCalFinalValue');
var premiumCalFundPercentage = document.getElementById('premiumCalFundPercentage');
var premiumCalDAnnualPremium = document.getElementById('premiumCalDAnnualPremium');
var premiumCalDPPT = document.getElementById('premiumCalDPPT');
var premiumCalFinalAmount = document.getElementById('premiumCalFinalAmount');
var premiumCalAPIType = document.getElementById('premiumCalAPIType');
var premiumCalProdId = document.getElementById('premiumCalProdId');
var premiumCalOverlay = document.getElementById('premiumCalOverlay');
var loader = document.getElementById("premiumCalGraphLoadrer");
var premiumCalGraph =  document.getElementById('premiumCalGraph');

 function validate(event) {
      var key = event.which || event.keyCode || 0;
      return ((key == 44) || (key >= 48 && key <= 57))
    }

premiumCalAnnualPremium && premiumCalAnnualPremium.addEventListener('blur',function(){
       var value = premiumCalAnnualPremium.value.trim().replace(/,/g, "");
       if( (typeof value !== 'undefined' || value !== null || value !== '') && Number(value) > 0) {
          premiumCalAnnualPremium.value = Number(value).toLocaleString("en-IN");
       }else{
         premiumCalAnnualPremium.value = premiumCalDAnnualPremium.value;
       }	
			 premiumCalOverlay.style.display = 'block';
       callProductCal();
})

/*
var toggelDotYear = (e) => {
  	$(".dot-year").removeClass("active");
	$(".dot-year").removeClass("right-active");
	
	$(".first-year").removeClass("active");
	$(".first-year").removeClass("right-active");
	
	$(".last-year").removeClass("active");
	$(".last-year").removeClass("right-active");
	
	$(e.currentTarget).addClass('active');
	
	let dotIndex = $("#ul_graph li.active").index();
    if(dotIndex > 8) {
        $("#ul_graph li.active").addClass("right-active");
    } 
} */

premiumCalFundPercentage && $(premiumCalFundPercentage).on('change',function(e){
    buildGraph(null, e.currentTarget.value);
})

$(premiumCalPPT).on('change', function(e) {
     premiumCalOverlay.style.display = 'block';
     callProductCal();
})

function callProductCal() {
    let formobj = {};
    let annualPremium = premiumCalAnnualPremium.value.replace(/,/g, "");

		formobj.biRequestJson = JSON.parse(premiumCalRequestJson.value);
    formobj.ppt = premiumCalPPT.value;
		formobj.pt = premiumCalPT.value;
		formobj.investmentAmt = annualPremium;
		premiumCalProdId.value = formobj.biRequestJson.PR_ID;
    premiumCalAnnualPremium.value = Number(annualPremium).toLocaleString("en-IN");		
    productCal(formobj)
}

function productCal(requestJson) {
    switch (requestJson.biRequestJson.PR_ID) {
	    case "40003":
		callIncomeBuilderProdCal(requestJson);
		break;
	    case "40035":
		callActiveIncomeProdCal(requestJson);
		break;
	    case "40007":
		callGcapProdCal(requestJson);
		break;
	    case "40009":
		callWealthUtilmaProdCal(requestJson);
		break;
	    case "40023":
		callWealthPlusProdCal(requestJson);
		break;
	    case "40030":
		callWealthGainPlusProdCal(requestJson);
		break;
	    case "40031":
		callWealthSecurePlusProdCal(requestJson)
		break;
		case "40002":
    case "40012":
    case "40014":
        callTermProdCal(requestJson);
	    }
}

function setCustomerAge(biJsonRequestJson){
    let age = 30;
    let dob = (new Date).getFullYear() - age;
		dob = "01 Jan " +dob;
		biJsonRequestJson.PROPOSER_AGE = biJsonRequestJson.LI_ENTRY_AGE = age;
    biJsonRequestJson.PROPOSER_DOB = biJsonRequestJson.LI_DOB = dob;
}

function callTermProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = item.investmentAmt;

    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
    biJsonRequestJson.PR_SA = investmentAmt;
    setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT = item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callGcapProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 15E3 > item.investmentAmt ? 15E3 : item.investmentAmt;
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT = item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callWealthUtilmaProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    let investmentAmt = 48E3 > item.investmentAmt ? 48E3 : item.investmentAmt;
		let age = Number(biJsonRequestJson.LI_ENTRY_AGE);
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
		let sa = 10;		
    44 >= age ? (sa = item.pt / 2, sa = 10 < sa ? sa : 10) : sa;
    biJsonRequestJson.PR_SA = biJsonRequestJson.PR_ANNPREM * sa;
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT = item.ppt;
    callPostAJAXProductCal(biJsonRequestJson);
}

function callWealthPlusProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 36E3 > item.investmentAmt ? 36E3 : item.investmentAmt
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
    biJsonRequestJson.PR_SA = 10 * biJsonRequestJson.PR_ANNPREM;
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT =item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callWealthSecurePlusProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 12E3 > item.investmentAmt ? 12E3 : item.investmentAmt
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
    biJsonRequestJson.PR_SA = 10 * biJsonRequestJson.PR_ANNPREM;
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT = item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callWealthGainPlusProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 5 <= item.ppt && 9 >= item.ppt ? 36E3 > item.investmentAmt ? 36E3 : item.investmentAmt : 3E4 > item.investmentAmt ? 3E4 : item.investmentAmt
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_PT = item.pt;
    biJsonRequestJson.PR_PPT = item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callActiveIncomeProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 5E4 > item.investmentAmt ? 5E4 : item.investmentAmt
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
    biJsonRequestJson.PR_ModalPrem = investmentAmt;
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PR_PT = item.pt + "-@li_entry_age";
    biJsonRequestJson.PR_PPT =item.ppt;
    callPostAJAXProductCal(biJsonRequestJson)
}

function callIncomeBuilderProdCal(item) {
    let biJsonRequestJson = item.biRequestJson;
    var investmentAmt = 3E4 > item.investmentAmt ? 3E4 : item.investmentAmt
    premiumCalAnnualPremium.value = Number(investmentAmt).toLocaleString("en-IN");
    biJsonRequestJson.PR_ANNPREM = investmentAmt;
		setCustomerAge(biJsonRequestJson)
    biJsonRequestJson.PayoutPeriodInYears = "10" == item.ppt ? "4" : "7" == item.ppt ? "2" : "5";
    biJsonRequestJson.PR_PT = item.ppt;
    biJsonRequestJson.PR_PPT = item.ppt;
    document.getElementById('p_PT').innerText = item.ppt + ' Years'
    callPostAJAXProductCal(biJsonRequestJson)
}



function callPostAJAXProductCal(biJsonRequestJson){
     // generateBI function call is written in jsp module
		 loader.innerHTML = '';

     generateBI(biJsonRequestJson)
		 .then(function (response) {
		 			premiumCalOverlay.style.display = 'none';
			    if(response.ok){
			         return response.json();
			    }
				
			})
			.then((response) => {					
				 if(response.status === 200 && (response?.biData?.JsonOutput || response?.biData?.data)){
				   
				    response = response.biData.JsonOutput ? JSON.parse(response.biData.JsonOutput) : response.biData.data;

	                sessionStorage.setItem("BIResponseData", JSON.stringify(response));
									if(premiumCalFundPercentage){
									   buildGraph(response,8);
									}else{
									  buildGraph(response,4);
									}
		                  premiumCalGraph.style.display = 'block';
	             }else {
	                console.log('error..')
								loader.innerHTML = '<div class="error-graph p-3 text-center"><p class="pb-2"> Some thing went wrong, click refresh to try again </p><button class="edto-btn-primary" type="button" onclick="refreshPortlet()">Refresh</button></div>';
								premiumCalGraph.style.display = 'none';
	            }
			})
		.catch((e) => {
		  premiumCalOverlay.style.display = 'none';
			premiumCalGraph.style.display = 'none';
			loader.innerHTML = '<div class="error-graph p-3 text-center"><p class="pb-2"> Some thing went wrong, Please change the Input. </p></div>';
			console.log('error...',e)
		});
}

function buildGraph(biJsonRes, selectedReturns){
selectedReturns = selectedReturns ? selectedReturns : premiumCalFundPercentage.value;
var returns = {
        4: "BIJson",
        8: "BIJson2",
        NAV: "BIJsonCustom"
    };

   biJsonRes = biJsonRes ? biJsonRes : JSON.parse(sessionStorage.getItem("BIResponseData"));
   
   var id = 40031;
	 let productId = Number(premiumCalProdId.value);

	  switch (productId) {
		     case 40031:
         case 40009:
				        selectedReturns = selectedReturns ? returns[selectedReturns] : "BIJson2",

	       biJsonRes = typeof biJsonRes[selectedReturns] == "object" ? biJsonRes[selectedReturns] : JSON.parse(biJsonRes[selectedReturns]),
	       biJsonRes = biJsonRes.map(function(biJsonRes) {
	            
	            biJsonRes.finalAmount = biJsonRes.FINAL_FV;
	            biJsonRes.annualPremium = biJsonRes.ALLOCATED_AMOUNT;
	            biJsonRes.totalReturn = biJsonRes.FINAL_FV;
	            biJsonRes.premiumPayingTerm = biJsonRes.PolicyYear;
	            biJsonRes.lis = [];
	            biJsonRes.lis[0] = {
	                label: "Total Premium",
	                value: biJsonRes.CUM_PREM,
	                header: true
	            };
	            biJsonRes.lis[1] = {
	                label: "Additional Allocation",
	                value: biJsonRes.LA ? biJsonRes.LA : "-"
	            };
							if(40031 == productId){
							  biJsonRes.lis[2] = {
	                label: "Applicable Taxes",
	                value: biJsonRes.BOOSTER ? biJsonRes.BOOSTER : "-"
	               };
							}else if(40009 == productId){
							
							  biJsonRes.lis[2] = {
	                label: "Booster Premium",
	                value: biJsonRes.BOOSTER ? biJsonRes.BOOSTER : "-"
	              };
							
							}

	            biJsonRes.lis[3] = {
	                label: "Fund Value",
	                value: biJsonRes.FINAL_FV,
	                footer: true
	            }
	            return biJsonRes;
	        }),

        drawProductCalPlotingData(biJsonRes);
         break;
			case 40003:	 
	       biJsonRes = typeof biJsonRes.BIJson == "object" ? biJsonRes.BIJson : JSON.parse(biJsonRes.BIJson);
				
		   let maturityAmt = 0;
	       biJsonRes = biJsonRes.map(function(biJsonRes) {
	            
	            biJsonRes.finalAmount = biJsonRes.INCOME_DISPLAY;
	            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
	            biJsonRes.totalReturn = biJsonRes.INCOME_DISPLAY;
	            biJsonRes.premiumPayingTerm = biJsonRes.POLICYYEAR;
							maturityAmt += biJsonRes.finalAmount;
				biJsonRes.totalMaturity = maturityAmt;			
	            biJsonRes.lis = [];
	            biJsonRes.lis[0] = {
	                label: "Maturity Benefit",
	                value: maturityAmt ? maturityAmt : "-",
	            };
	            biJsonRes.lis[1] = {
	                label: "Life Cover",
	                value: biJsonRes.SA ? biJsonRes.SA : "-"
	            };
	            return biJsonRes;
	        }),

        drawProductCalPlotingData(biJsonRes);
			break;
			case 40035:	 
			   selectedReturns = selectedReturns ? returns[selectedReturns] : "BIJson2",
	       biJsonRes = typeof biJsonRes.BIJson == "object" ? biJsonRes.BIJson : JSON.parse(biJsonRes.BIJson);
				
				 let SB_G = 0, TOTAL_MB_G_BS_1 = 0, TOTAL_MB_G_BS_2 = 0;
				 
	       biJsonRes = biJsonRes.map(function(biJsonRes) {
	            
	            biJsonRes.finalAmount = biJsonRes.SB_G;
	            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
	            biJsonRes.totalReturn = biJsonRes.SB_G;
	            biJsonRes.premiumPayingTerm = biJsonRes.POLICYYEAR;
	            biJsonRes.lis = [];
							SB_G += Math.round(biJsonRes.SB_G);
							4 == selectedReturns ? (TOTAL_MB_G_BS_1 += Math.round(biJsonRes.CASH_BONUS_BS_1),
              TOTAL_MB_G_BS_2 += Math.round(biJsonRes.TOTAL_MB_G_BS_1)) : (TOTAL_MB_G_BS_1 += Math.round(biJsonRes.CASH_BONUS_BS_2),
              TOTAL_MB_G_BS_2 += Math.round(biJsonRes.TOTAL_MB_G_BS_2));
							
	            biJsonRes.lis[0] = {
	                label: "Age Attained",
	                value: biJsonRes.LI_ATTAINED_AGE ? biJsonRes.LI_ATTAINED_AGE : "-",
									 header: !0
	            };
	            biJsonRes.lis[1] = {
	                label: "Guaranteed Income",
	                value: SB_G ? SB_G : "-"
	            };
							biJsonRes.lis[2] = {
	                label: "Cash Bonus (If Declared)",
	                value: TOTAL_MB_G_BS_1 ? TOTAL_MB_G_BS_1 : "-"
	            };
							biJsonRes.lis[3] = {
	                label: "Maturity Value",
	                value: TOTAL_MB_G_BS_2 ? TOTAL_MB_G_BS_2 : "-",
									footer: !0
	            };
	            return biJsonRes;
	        });


        drawProductCalPlotingData(biJsonRes);
			break;
			case 40030:
				 selectedReturns = selectedReturns ? returns[selectedReturns] : "BIJson2",

	       biJsonRes = typeof biJsonRes[selectedReturns] == "object" ? biJsonRes[selectedReturns] : JSON.parse(biJsonRes[selectedReturns]),
	       biJsonRes = biJsonRes.map(function(biJsonRes) {
	            
	            biJsonRes.finalAmount = biJsonRes.FINAL_FV;
	            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
	            biJsonRes.totalReturn = biJsonRes.FINAL_FV;
	            biJsonRes.premiumPayingTerm = biJsonRes.PolicyYear;
	            biJsonRes.lis = [];
	            biJsonRes.lis[0] = {
                label: "Total Premium",
                value: biJsonRes.CUM_PREM,
                header: !0
            };
            biJsonRes.lis[1] = {
                label: "Additional Allocation",
                value: biJsonRes.LA ? biJsonRes.LA : "-"
            };
            biJsonRes.lis[2] = {
                label: "Booster Premium",
                value: biJsonRes.BOOSTER_ADD ? biJsonRes.BOOSTER_ADD : "-"
            };
            biJsonRes.lis[3] = {
                label: "Return of Mortality",
                value: biJsonRes.RETURN_OF_MORT ? biJsonRes.RETURN_OF_MORT : "-"
            };
            biJsonRes.lis[4] = {
                label: "Fund Value",
                value: biJsonRes.FINAL_FV,
                footer: !0
            }
	            return biJsonRes;
	        }),

        drawProductCalPlotingData(biJsonRes);
         break;
				case 40007:
				 biJsonRes = typeof biJsonRes.BIJson == "object" ? biJsonRes.BIJson : JSON.parse(biJsonRes.BIJson);
				 let maturityBenefit = 0;
				 let length = biJsonRes.length - 1;
				 let finalAmount = 0;
	       biJsonRes = biJsonRes.map(function(biJsonRes,index) {	            
	            biJsonRes.lis = [];
						if(biJsonRes.POLICYYEAR === 20){
						   finalAmount = biJsonRes.TOTAL_SB_G;
						}
						
						if(index === length){
						    biJsonRes.finalAmount = finalAmount;
						}
	          
            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
            maturityBenefit += Number(biJsonRes.TOTAL_SB_G);
            biJsonRes.premiumPayingTerm = biJsonRes.POLICYYEAR;
            biJsonRes.lis = [];
            biJsonRes.lis[0] = {
                label: "Guaranteed Additions",
                value: biJsonRes.CUM_GA ? biJsonRes.CUM_GA : "-",
                header: !0
            };
            biJsonRes.lis[1] = {
                label: "Large Premium Benefit",
                value: biJsonRes.LARGE_PREM_BEN ? biJsonRes.LARGE_PREM_BEN : "-"
            };
            biJsonRes.lis[2] = {
                label: "Sum Assured on Benefit",
                value: biJsonRes.SB_G ? biJsonRes.SB_G : "-"
            };
            biJsonRes.lis[3] = {
                label: "Maturity Benefit",
                value: maturityBenefit ? maturityBenefit : "-",
                footer: !0
            }
	            return biJsonRes;
	        }),
        
        drawProductCalPlotingData(biJsonRes);
         break; 
				case 40023:
				    selectedReturns = selectedReturns ? returns[selectedReturns] : "BIJson2";
             biJsonRes = JSON.parse(biJsonRes[selectedReturns]);
             biJsonRes = biJsonRes.map(function(biJsonRes) {
            biJsonRes.finalAmount = biJsonRes.FINAL_FV;
            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
            biJsonRes.totalReturn = biJsonRes.FINAL_FV;
            biJsonRes.premiumPayingTerm = biJsonRes.PolicyYear;
            biJsonRes.lis = [];
            biJsonRes.lis[0] = {
                label: "Total Premium",
                value: biJsonRes.CUM_PREM,
                header: !0
            };
            biJsonRes.lis[1] = {
                label: "Additional Allocation",
                value: biJsonRes.EXTRA_ALLOC_CHARGE ? biJsonRes.EXTRA_ALLOC_CHARGE : "-"
            };
            biJsonRes.lis[2] = {
                label: "Booster Premium",
                value: biJsonRes.addition ? biJsonRes.addition : "-"
            };
            biJsonRes.lis[3] = {
                label: "Fund Value",
                value: biJsonRes.FINAL_FV ? biJsonRes.FINAL_FV : "-",
                footer: !0
            }
            return biJsonRes;
        });
        drawProductCalPlotingData(biJsonRes);
        break; 
				case 40014:
				    biJsonRes = typeof biJsonRes.BIJson == "object" ? biJsonRes.BIJson : JSON.parse(biJsonRes.BIJson);
             biJsonRes = biJsonRes.map(function(biJsonRes) {
            biJsonRes.finalAmount = biJsonRes.MODAL_PREM_TAX;
            biJsonRes.annualPremium = biJsonRes.ANN_PREM;
            biJsonRes.totalReturn = biJsonRes.MODAL_PREM_TAX * biJsonRes.PolicyYear;
            biJsonRes.premiumPayingTerm = biJsonRes.PolicyYear;
            biJsonRes.lis = [];
            biJsonRes.lis[0] = {
                label: "",
                value: "-",
                header: !1
            };
            biJsonRes.lis[1] = {
                label: "Total Premium Paid",
                value: biJsonRes.totalReturn ? biJsonRes.totalReturn : "-"
            };
            biJsonRes.lis[2] = {
                label: "Death Benefit for LA",
                value: biJsonRes.CI_SA ? biJsonRes.CI_SA : "-"
            };
            biJsonRes.lis[3] = {
                label: "",
                value: "-",
                footer: !1
            }
            return biJsonRes;
        });
        drawProductCalPlotingData(biJsonRes);
        break; 
				
	 
		}

} 

function drawProductCalPlotingData(invetmentData){		
   var ul_graph = document.getElementById('ul_graph');
   ul_graph.innerHTML =  '';
    let length = invetmentData.length - 1;
		
		let productId = Number(premiumCalProdId.value);
    invetmentData.forEach(function(item, index, array) {
		if(index >= 29 && productId === 40035){		     
		    item = invetmentData[length];
				length = index;
				if(index > 29){				    
				    return true;
				}
		}
		
    var innerLi = '';
      for(let i=0; i < item.lis.length; i++){
          let css = '';
          if(item.lis[i].header){
              css = 'investment-amount';
          }else if(item.lis[i].footer){
              css = 'total-amount';
          }
          let amount = item.lis[i].value;
          
          if(amount === '-'){
              amount = '<span class="rupee"> - </span>';
          }else{
            amount = '<span class="rupee"> &#8377; </span>' + convertToLocalString(amount);
          }
          
         innerLi = innerLi + '<li class="'+css+'"><div class="lable">' + item.lis[i].label + '</div><div class="amount">' + amount + '</div></li>';
      }
      let css = 'dot-year';
			let active = '';
      if(index ==0 ){
        css = 'first-year1';
      }else if(index == length){
        css = 'last-year';
      }
			
			if(item.POLICYYEAR === Number(premiumCalPPT.value)){
        active = ' active';
      }
			
			css = css + active;
      
      ul_graph.insertAdjacentHTML('beforeend', '<li class="'+ css +'"><div class="card timelne-card"><div class="card-body"><ul class="breakup-amount">' + innerLi + '</ul></div></div></li>');
      
    })
    
    $('.premium-timeline .line li.first-year1 , .premium-timeline .line li.dot-year , .premium-timeline .line li.last-year').click(function () {
        var offset = $(this).offset();
        
        $(this).addClass('active').siblings().removeClass('active');
        if (offset.left >= 650) {
            $(this).addClass('right-active').siblings().removeClass('right-active');
        } else {
            $(this).removeClass('right-active').siblings().removeClass('right-active');
        }
    
    });
    
    /*$('#ul_graph li').click(toggelDotYear);
    
	if($(window).width() > 766) {
	    let dotIndex = $("#ul_graph li.active").index();
        if(dotIndex > 8) {
            $("#ul_graph li.active").addClass("right-active");
        }
    }*/
    document.getElementById('p_annmPrem').innerText = 'Amount: Rs ' + premiumCalAnnualPremium.value;
    let finalValue = convertToLocalString(invetmentData[invetmentData.length-1].finalAmount); 
    if(productId === 40003){
        document.getElementById('p_FV').innerText = 'Rs ' + convertToLocalString(invetmentData[invetmentData.length-1].totalMaturity);
    }else{
         document.getElementById('p_FV').innerText = 'Rs ' + finalValue;
    }
    document.getElementById('premiumCalFinalValue').value = finalValue;
    
    if(productId === 40035){
      invetmentData = invetmentData[invetmentData.length-1];
      finalValue = convertToLocalString(invetmentData.lis[2].value + invetmentData.lis[3].value + invetmentData.lis[1].value);      
       document.getElementById('p_FV').innerText = 'Rs ' + finalValue;
       document.getElementById('premiumCalFinalValue').value = finalValue;
    }else if(productId === 40007){
       invetmentData = invetmentData[invetmentData.length-1];
      finalValue = convertToLocalString(invetmentData.lis[3].value);
      document.getElementById('p_FV').innerText = 'Rs ' + finalValue;
      $('#gcapContent').html('and \u20b9 ' + convertToLocalString(invetmentData.lis[2].value) + ' for next 4 years.');
    }

		
}

function convertToLocalString(biJsonRes) {
    biJsonRes && "-" != biJsonRes && (biJsonRes = Math.round(biJsonRes));
    return biJsonRes.toLocaleString("en-IN", {
        currency: "INR"
    })
}


<% if(showGraph){%>
    callProductCal();
<% }%>


});


</aui:script>