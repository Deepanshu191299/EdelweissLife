const chooseSpouse = {
	h: ['1327'],
	st: ['1328', '1329'],
};
var annualIncomeVal = document.getElementById("annualIncomes");
$(document).ready(function() {
	init();
})

/**
 * Buy Form 3 Initilazation
 */
init = function(){
	initEvent();
	
	updateChooseSpouse();
	
	/*
	 * Hide or show Spouse/Parent fields on load
	 */
	var occupationElement = document.getElementById(portletNamespace+"occupation");
	var selectedValue = occupationElement.value;
	if (selectedValue === 'h' || selectedValue=== 'st') {
		$('#extraFields').show();
		annualIncomeVal.innerText = "Parent/Spouse’s Annual Income";
	}
	
	setNatureDuty($("#"+portletNamespace+"occupation").val());

	/*
	 * Sort dropdown fields
	 */
	sortEducationDrodownOptions();
	sortNatureOfDutyDrodownOptions();
	
	
}

/*
 * Sort Edication dropdown
 */
sortEducationDrodownOptions = function(){
	var educationQualificationValue = $("#"+portletNamespace+"educationQualification").val();
	var educationQualificationDropdown = $("#"+portletNamespace+"educationQualification");
	var dropdownOptions = educationQualificationDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  var aValue = parseInt($(a).val());
	  var bValue = parseInt($(b).val());

	  return bValue - aValue;
	});
	educationQualificationDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"educationQualification").val(educationQualificationValue);
}

/*
 * Sort Nature of Duty Dropdown
 */
sortNatureOfDutyDrodownOptions = function(){
	var natureOfDutyValue = $("#"+portletNamespace+"natureOfDuty").val();
	var natureOfDutyDropdown = $("#"+portletNamespace+"natureOfDuty");
	var dropdownOptions = natureOfDutyDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  return a.textContent.localeCompare(b.textContent)
	});
	natureOfDutyDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"natureOfDuty").val(natureOfDutyValue);
}

/**
 * Initialization of the Event Listeners
 */
initEvent = function(){
	document.getElementById(portletNamespace+'occupation').addEventListener('change', updateChooseSpouse);
}

function toggleExtraFields(value) {
	var extraFields = document.getElementById("extraFields");
	if (value === "h" || value==="st") {
		extraFields.style.display = "flex";
		annualIncomeVal.innerText = "Parent/Spouse’s Annual Income";
	} else {
		extraFields.style.display = "none";
		annualIncomeVal.innerText = "Your Annual Income";
		/*
		 * Reset the fields
		 */
		const extraFieldsDiv = document.getElementById('extraFields');
		const inputFields = extraFieldsDiv.querySelectorAll('input, select');

		inputFields.forEach((field) => {
			field.value = '';
		});
	}
} 



function setNatureDuty(selectedOccupationValue){
	console.log(selectedOccupationValue);
	if(!selectedOccupationValue){
		return;
	}
	var selectedOccupation = $("#"+portletNamespace+"occupation option[value='"+selectedOccupationValue+"']").text().toLowerCase().trim();
	var matchednatureOfDutyOption = $("#"+portletNamespace+"natureOfDuty option").filter(function() {
		return $(this).text().toLowerCase().trim() == selectedOccupation
	});
	if(matchednatureOfDutyOption && matchednatureOfDutyOption.length>0 && (selectedOccupationValue=="st" || selectedOccupationValue=="h")){
	    $("#"+portletNamespace+"natureOfDuty option").hide();
	    $("#"+portletNamespace+"natureOfDuty option[value='"+matchednatureOfDutyOption.val()+"']").show();
	    $("#"+portletNamespace+"natureOfDuty").val(matchednatureOfDutyOption.val());
	    $("#"+portletNamespace+"natureOfDuty").parent().children('.motion-label').addClass("motion-label-heading");
	}else{
		$("#"+portletNamespace+"natureOfDuty option").show();
	}
}

function setNatureValue(dropdownName, selectedValue){
	
	for (var i = 0; i < dropdownName.options.length; i++) {
		if (dropdownName.options[i].text === selectedValue) {
			dropdownName.selectedIndex = i;
			break;
		}
	}
	
} 

function updateChooseSpouse() {
	const occupation = document.getElementById(portletNamespace+'occupation');
	const spouseParentRelation = document.getElementById(portletNamespace+'spouseParentRelation');
	const selectedSpouseParentRelation = spouseParentRelation.value;
	const selectedOccupation = occupation.value;
	if (selectedOccupation in chooseSpouse) {
		const allowedOptions = chooseSpouse[selectedOccupation];
		for (let option of spouseParentRelation.options) {
			option.style.display = allowedOptions.includes(option.value) ? 'block' : 'none'; 
			option.disabled  = allowedOptions.includes(option.value) ? false : true; 
		}
		if (selectedOccupation === 'st' || selectedOccupation === 'h' ) {
			spouseParentRelation.value = ''; 
		    }
	} else {
		for (let option of spouseParentRelation.options) {
			option.style.display = 'block';
			option.disabled  = false;
		}
	}
	
	if($("#"+portletNamespace+"spouseParentRelation option[value='"+selectedSpouseParentRelation+"']")[0] 
		&& $("#"+portletNamespace+"spouseParentRelation option[value='"+selectedSpouseParentRelation+"']")[0].style.display != "none"){
		$("#"+portletNamespace+"spouseParentRelation").val(selectedSpouseParentRelation);
		
	}
	
}