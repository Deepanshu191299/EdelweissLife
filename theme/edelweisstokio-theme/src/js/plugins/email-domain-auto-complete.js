
var domains = ["gmail.com", "yahoo.com", "yahoo.co.in", "hotmail.com", "rediffmail.com", "outlook.com", "indiatimes.com", "aol.com"];
  
// Using DropDown element to suggest static list of domains
var EmailDomainSuggester = function ($bindTo) {	
  var datalist = null;  
  
  var init = function () {    
  	addElements();
    bindEvents();
  };
  
  var addElements = function () {
    var datalistId = 'email_options_' + $bindTo.attr('id');
    
  	//create empty datalist
		datalist = $("<datalist />", {
			id: datalistId
		}).insertAfter($bindTo);    
    
    // correlate to input
		$bindTo.attr("list", datalistId);
  };
  
  var bindEvents = function () {
  	$bindTo.on("keyup", testValue);
  };
  
  var testValue = function (event) {
    var el = $(this),value = el.val();
            
    // email has @
    if (value.indexOf("@") != -1) {
    	value = value.split("@")[0];
      
      addDatalist(value);
    } else {
    	// empty list
      emptyDatalist();
    }
  };
  
  var emptyDatalist = function () {
    datalist.empty();
  };
  
  var addDatalist = function (value) {
  	var i,newOptionsString = '';
        
    // loop over all the domains in our array
    for (i=0; i<domains.length; i++) {
    	newOptionsString += "<option value='" +value + "@" +domains[i] + "'>";
    }
    
    // add all the <option>s to the datalist
    datalist.html(newOptionsString);
  };  
  
  init();
};

var prdQutEmail = new EmailDomainSuggester($('.email-auto-complete-et'));
var prdEnqEmail = new EmailDomainSuggester($('#generalForm #email'));

