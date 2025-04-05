$(document).ready(function () {
	init();
});

/**
 * Customize Page Initilazation
 */
init = function(){
	
	/*
	 * Initializing the Event Listeners
	 */
	initEvent();
	
	/*
	 * Render Premium Amount breakup model
	 */
	//renderPremiumAmountBreakup();
	
	/*
	 * Parse Amount values
	 */
	formateProductAmounts();
}

/**
 * Initialization of the Event Listeners
 */
initEvent = function(){
	/*
	 * On click event Listeners to add or remove a rider
	 */
	$("#"+namespace+"premiumBreakUpAnchor").on("click", function(){
		$("#"+namespace+"planPremiumAmountBreakupTableModel").modal("show");
	});
	
	if(productCode === '40048' || productCode === pgsProductCode.toString()){
		showHidePGIFields();
		sessionStorage.setItem('previousURL', window.location.href );
	}
}
/**
 * show and hide fields of PGI based on conditions
 */
showHidePGIFields=function(){
	if(productCode != pgsProductCode.toString()){
	var policyOption =document.getElementById(namespace+"policyOption").innerHTML;
	var incomePayoutFrequencyDiv =document.getElementById(namespace+"incomePayoutFrequencyDiv");
	var maturityPayoutOptionDiv =document.getElementById(namespace+"maturityPayoutOptionDiv");
	maturityPayoutOptionDiv.style.display="none";
	
	console.log("--------policyOption----"+policyOption);
	if(policyOption=="Lumpsum"){
		incomePayoutFrequencyDiv.style.display =  "none";
	}
	else if(policyOption=="Retirement Income"){
		maturityPayoutOptionDiv.style.display="block";
	}
	}
	var incomePayoutFrequencyDiv =document.getElementById(namespace+"incomePayoutFrequencyDiv");

}
/**
 * Render Premium Amount Breakup table
 */
renderPremiumAmountBreakup = function(){
	
	var tableBody = document.getElementById(namespace+"planPremiumAmountBreakupTableBody");
	tableBody.innerHTML = "";
	
	/*
	 * Base Premium Details
	 */
	const tableRecord = document.createElement("tr");
	
	var riderNameColumn = document.createElement("td");
	riderNameColumn.appendChild(document.createTextNode("Base Premium"));
	riderNameColumn.classList.add("table-heading");
	tableRecord.appendChild(riderNameColumn);

	var paymentFrequencyCoulmn = document.createElement("td");
	paymentFrequencyCoulmn.appendChild(document.createTextNode(paymentFrequency));
	tableRecord.appendChild(paymentFrequencyCoulmn);
	
	var policyTermCoulmn = document.createElement("td");
	policyTermCoulmn.appendChild(document.createTextNode(policyTerm));
	tableRecord.appendChild(policyTermCoulmn);
	
	var payingTermCoulmn = document.createElement("td");
	payingTermCoulmn.appendChild(document.createTextNode(payingTerm));
	tableRecord.appendChild(payingTermCoulmn);

	var _basePremiumAmount=0;
	var _totalRiderPrice=0;
	var riderPriceCoulmn = document.createElement("td");
	if((paymentFrequency == 'Monthly' && productCode === fspProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === gisProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === '40048') || (paymentFrequency == 'Monthly' && productCode === pgsProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === '40035') || (paymentFrequency == 'Monthly' && productCode === '40007') || (paymentFrequency == 'Monthly' && productCode === ibProductCode.toString()) || (paymentFrequency=='Monthly' && productCode==='40039') || (paymentFrequency=='Monthly' && productCode==='40071')){
	    _basePremiumAmount = Math.round(basePremiumAmount*2);
	}else{
	    _basePremiumAmount = Math.round(basePremiumAmount);
	}
	riderPriceCoulmn.appendChild(document.createTextNode(LANG_MESSAGES["rupee-sign"].concat(" ").concat(_basePremiumAmount.toLocaleString("en-IN"))));
	
	riderPriceCoulmn.classList.add("text-right");
	tableRecord.appendChild(riderPriceCoulmn);
	
	tableBody.appendChild(tableRecord);

	if(selectedRidersList){
		selectedRidersList.forEach(selectedRider=>{
			
			const tableRecord = document.createElement("tr");
			
			var riderName = document.createElement("td");
			riderName.appendChild(document.createTextNode(selectedRider.name));
			riderName.classList.add("table-heading");
			tableRecord.appendChild(riderName);
			
			var paymentFrequencyCoulmn = document.createElement("td");
			paymentFrequencyCoulmn.appendChild(document.createTextNode(paymentFrequency));
			tableRecord.appendChild(paymentFrequencyCoulmn);
			
			var policyTermCoulmn = document.createElement("td");
			if(selectedRider.pt){
				policyTermCoulmn.appendChild(document.createTextNode(selectedRider.pt));
			}else{
				policyTermCoulmn.appendChild(document.createTextNode(policyTerm));
			}
			tableRecord.appendChild(policyTermCoulmn);
			
			var payingTermCoulmn = document.createElement("td");
			if(selectedRider.ppt){
				payingTermCoulmn.appendChild(document.createTextNode(selectedRider.ppt));
			}else{
				payingTermCoulmn.appendChild(document.createTextNode(payingTerm));
			}
			tableRecord.appendChild(payingTermCoulmn);

			var _selectedRiderPrice=0;
			var riderPrice = document.createElement("td");
			if((paymentFrequency == 'Monthly' && productCode === fspProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === gisProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === '40048') || (paymentFrequency == 'Monthly' && productCode === pgsProductCode.toString()) || (paymentFrequency == 'Monthly' && productCode === '40035') || (paymentFrequency == 'Monthly' && productCode === '40007') || (paymentFrequency == 'Monthly' && productCode === ibProductCode.toString()) || (paymentFrequency=='Monthly' && productCode==='40039') || (paymentFrequency=='Monthly' && productCode==='40071')){
			    _selectedRiderPrice = Number(Math.round(selectedRider.price)*2);
			}else{
			    _selectedRiderPrice = Math.round(selectedRider.price);
			}
			riderPrice.appendChild(document.createTextNode(LANG_MESSAGES["rupee-sign"].concat(" ").concat(_selectedRiderPrice.toLocaleString("en-IN"))));
			riderPrice.classList.add("text-right");
			tableRecord.appendChild(riderPrice);

			_totalRiderPrice = _totalRiderPrice + _selectedRiderPrice;
			
			tableBody.appendChild(tableRecord);
		});
	}

    
    if(gst && Math.round(gst)){
    	const gstTableRecord = document.createElement("tr");
    	
    	var gstNameColumn = document.createElement("td");
    	gstNameColumn.appendChild(document.createTextNode("GST"));
    	gstNameColumn.classList.add("table-heading");
    	gstTableRecord.appendChild(gstNameColumn);

    	var gstPaymentFrequencyCoulmn = document.createElement("td");
    	gstTableRecord.appendChild(gstPaymentFrequencyCoulmn);
    	
    	var gstPolicyTermCoulmn = document.createElement("td");
    	gstTableRecord.appendChild(gstPolicyTermCoulmn);
    	
    	var gstPayingTermCoulmn = document.createElement("td");
    	gstTableRecord.appendChild(gstPayingTermCoulmn);

    	
    	var gstAmount = Math.round(gst);
    	if((paymentFrequency == 'Monthly') && (productCode === fspProductCode.toString() || productCode === gisProductCode.toString() || productCode === '40048' || productCode === pgsProductCode.toString() || productCode === '40035' || productCode === '40007' || productCode === ibProductCode.toString() || productCode==='40039' || productCode==='40071')){
    		gstAmount = gstAmount*2;
		}
    	var gstPriceCoulmn = document.createElement("td");
    	gstPriceCoulmn.appendChild(document.createTextNode(LANG_MESSAGES["rupee-sign"].concat(" ").concat(gstAmount.toLocaleString("en-IN"))));
    	gstPriceCoulmn.classList.add("text-right");
    	gstTableRecord.appendChild(gstPriceCoulmn);
    	
    	tableBody.appendChild(gstTableRecord);
    }

}

/**
 * Parse and Render Premium Anounts
 */
renderPremiumAmountDetails = function(){
	document.getElementById(namespace+"totalMaturityAmount").innerHTML=Math.round(document.getElementById(namespace+"totalMaturityAmount").innerHTML).toLocaleString("en-IN");
	document.getElementById(namespace+"premiumAmount").innerHTML=Math.round(document.getElementById(namespace+"premiumAmount").dataset.amount).toLocaleString("en-IN")+"<sup> Inc. of GST</sup>";
	
}

/**
 * Formate avilable product amounts 
 */
formateProductAmounts = function(){
	$(".productAmounts").each(function(i, element){
		if(!$(this).data() || !$(this).data().amount){
			$(this).html(LANG_MESSAGES["rupee-sign"].concat(" ").concat(Math.round(0).toLocaleString("en-IN")));
			return;
		}
	    $(this).html(LANG_MESSAGES["rupee-sign"].concat(" ").concat(Math.round($(this).data().amount).toLocaleString("en-IN")));
	})
}

/*
 * Formate Amount
 */
$("input.formateAmount").keyup(function(){
	var currentAmount = $(this).val().replaceAll(",","");
	$(this).val(Math.round(currentAmount).toLocaleString("en-IN"));
});

/**
 * get Amount in words
 */ 
getAmountInWords = function(amount) {
	var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
	var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");
	
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
$("input.amt-in-word[value!='']").trigger("keyup");