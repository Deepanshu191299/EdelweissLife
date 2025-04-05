var namespace=$("#portletNamespace").val();

$(document).ready(function() {
  
  /**
   * Intialize Date Fields 
   */
  document.getElementById(namespace+'dateOfBirth').addEventListener('input', function(e) {
    this.type = 'text';
    var input = this.value;
    if (/\D\/$/.test(input)) input = input.substr(0, input.length - 3);
    var values = input.split('/').map(function(v) {
      return v.replace(/\D/g, '')
    });
    if (values[0]) values[0] = checkValue(values[0], 31);
    if (values[1]) values[1] = checkValue(values[1], 12);
    var output = values.map(function(v, i) {
      return v.length == 2 && i < 2 ? v + ' / ' : v;
    });
    this.value = output.join('').substr(0, 14);
  });
  
  document.getElementById(namespace+'spouseDob').addEventListener('input', function(e) {
	    this.type = 'text';
	    var input = this.value;
	    if (/\D\/$/.test(input)) input = input.substr(0, input.length - 3);
	    var values = input.split('/').map(function(v) {
	      return v.replace(/\D/g, '')
	    });
	    if (values[0]) values[0] = checkValue(values[0], 31);
	    if (values[1]) values[1] = checkValue(values[1], 12);
	    var output = values.map(function(v, i) {
	      return v.length == 2 && i < 2 ? v + ' / ' : v;
	    });
	    this.value = output.join('').substr(0, 14);
	  });
  
  document.getElementById(namespace+'childDob').addEventListener('input', function(e) {
	    this.type = 'text';
	    var input = this.value;
	    if (/\D\/$/.test(input)) input = input.substr(0, input.length - 3);
	    var values = input.split('/').map(function(v) {
	      return v.replace(/\D/g, '')
	    });
	    if (values[0]) values[0] = checkValue(values[0], 31);
	    if (values[1]) values[1] = checkValue(values[1], 12);
	    var output = values.map(function(v, i) {
	      return v.length == 2 && i < 2 ? v + ' / ' : v;
	    });
	    this.value = output.join('').substr(0, 14);
	  });
  
  
  /**
   * Show/Hide Spouse details
   */
  $('input[name="'+namespace+'maritalStatus"]').on("change",function(){
    if($('input[name="'+namespace+'maritalStatus"]:checked').val()=="1")
    {
      $("#"+namespace+"spouseDetailsContainer").show();
    }
    else{
      $("#"+namespace+"spouseDetailsContainer").hide();
    }
  });
  
  $('input[name="'+namespace+'child"]').on("change",function(){
    if($('input[name="'+namespace+'child"]:checked').val()=="1")
    {
      $("#"+namespace+"childDetails").show();
    }
    else{
      $("#"+namespace+"childDetails").hide();
    }
  });
  
  $('input[name="'+namespace+'maritalStatus"]').trigger("change");
  $('input[name="'+namespace+'child"]').trigger("change");
});

/**
 * Used by the date input field initializer
 */
function checkValue(str, max) {
  if (str.charAt(0) !== '0' || str == '00') {
    var num = parseInt(str);
    if (isNaN(num) || num <= 0 || num > max) num = 1;
    str = num > parseInt(max.toString().charAt(0)) && num.toString().length == 1 ? '0' + num : num.toString();
  };
  return str;
};