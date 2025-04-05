var namespace = portletNamespace;
var pgiRelFieldValues;
var totalReturnsAmountRef;
var amtInWordsJEl = "#amt-in-word";
var primaryWord = ";one ;two ;three ;four ;five ;six ;seven ;eight ;nine ;ten ;eleven ;twelve ;thirteen ;fourteen ;fifteen ;sixteen ;seventeen ;eighteen ;nineteen ".split(";");
var secondaryWord = "  twenty thirty forty fifty sixty seventy eighty ninety".split(" ");

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
 * Fetch Life Cover without Comma
 */
getLifeCover = function() {
	var curLifeCover = $(lifeCoverEl).val();
	curLifeCover = curLifeCover.replace(/,/g, "");
	return curLifeCover;
}
/**
 * Update Life Cover field to Locale IN String
 */
updateLifeCoverLocaleIN = function() {
	var curLifeCover = Number(getLifeCover());
	var localeINLifeCover = curLifeCover.toLocaleString("en-IN");
	$(lifeCoverEl).val(localeINLifeCover);
}	
		
/**
 * Polulate Amount in Words
 */
populateAmountInWord = function() {
	console.log("----- (3) ----");
	var curLifeCover = getLifeCover();
	console.log("---- curr life cover ----");
	console.log(curLifeCover);
	var amountInWords = getAmountInWords(curLifeCover);
	console.log(amountInWords);
	$(amtInWordsJEl).html(rupeeSignLabel + "&nbsp;" + amountInWords);
}		
		
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
	
	$(lifeCoverEl).on("keyup", populateAmountInWord);

};

/**--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------**/
		
init = async function(){
	initEvents();
};

$(function() {
    init();
});

showLoader = function(){
	$("#preloader").addClass("preloader");
}

hideLoader = function(){
	$("#preloader").removeClass("preloader");
}
