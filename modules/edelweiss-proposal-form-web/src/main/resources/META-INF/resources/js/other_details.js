const getBankDetailsIFSCURL = $("#getBankDetailsIFSCURL").val();
var bankNameJEl = "#" + portletNamespace + "la_bank_name";
var bankLocationJEl = "#" + portletNamespace + "la_bank_branch_location";

/**
 * Other Details Table Data
 */
var la_pr_sp_other_details_list = {};
if(proposalFormData.insurance_history_details){
	la_pr_sp_other_details_list["la_insurance_history_list"] = (proposalFormData.insurance_history_details.la_details && proposalFormData.insurance_history_details.la_details.insurance_history)? proposalFormData.insurance_history_details.la_details.insurance_history : [];
	la_pr_sp_other_details_list["pr_sp_insurance_history_list"] = (proposalFormData.insurance_history_details.proposer_details && proposalFormData.insurance_history_details.proposer_details.insurance_history)? proposalFormData.insurance_history_details.proposer_details.insurance_history : [];
	la_pr_sp_other_details_list["pr_sp_insurance_history_list"] = (proposalFormData.insurance_history_details.spouse_details && proposalFormData.insurance_history_details.spouse_details.insurance_history)? proposalFormData.insurance_history_details.spouse_details.insurance_history : la_pr_sp_other_details_list["pr_sp_insurance_history_list"];
}

if(proposalFormData.family_history_details){
	la_pr_sp_other_details_list["la_family_history_list"] = (proposalFormData.family_history_details.la_details && proposalFormData.family_history_details.la_details.family_history)? proposalFormData.family_history_details.la_details.family_history : [];
	la_pr_sp_other_details_list["pr_sp_family_history_list"] = (proposalFormData.family_history_details.proposer_details && proposalFormData.family_history_details.proposer_details.family_history)? proposalFormData.family_history_details.proposer_details.family_history : [];
	la_pr_sp_other_details_list["pr_sp_family_history_list"] = (proposalFormData.family_history_details.spouse_details && proposalFormData.family_history_details.spouse_details.family_history)? proposalFormData.family_history_details.spouse_details.family_history : la_pr_sp_other_details_list["pr_sp_family_history_list"];
}

if(proposalFormData.family_details){
	la_pr_sp_other_details_list["la_family_details_list"] = (proposalFormData.family_details.la_details)? proposalFormData.family_details.la_details : [];
	la_pr_sp_other_details_list["pr_sp_family_details_list"] = (proposalFormData.family_details.proposer_details)? proposalFormData.family_details.proposer_details : [];
	la_pr_sp_other_details_list["pr_sp_family_details_list"] = (proposalFormData.family_details.spouse_details)? proposalFormData.family_details.spouse_details : la_pr_sp_other_details_list["pr_sp_family_details_list"];
}

$(document).ready(function () {
	init();
	changeRelationshipOptions();

	/**
	 * Load exisitng data into the respective tables
	 */
	populateOtherDetailsTableData();
});

/**
 * Form Initilization
 */
init = function(){
	initEvent();

	/**
	 * Trigger onclicn event to hide/show
	 */
	$('.hasDependentFields:checked').each(function() {
        $(this).click();
    });

	/**
	 * Hide Show Familt Memebr Deceased fields on page load
	 */
	$("#"+portletNamespace+"la_fh_health_status").change();
	$("#"+portletNamespace+"pr_sp_fh_health_status").change();

	/**
	 * Update Relationship Dropdowns on Page load
	 */
	$('.addNewRecord').each(function() {
		var fieldsetId = $(this).data("fieldsetId");
		var tableId = $(this).data("tableId");
		
		updateRelationshipDropdown(fieldsetId, tableId);
    });

	/**
	 * If user clicks Yes for Any Radio Button, uncheck No To All
	 */
	$(".la_radioYes").on("click", function(){
		$("#la_otherDetailsNoToAll").prop("checked", false);
	})
	
	$(".prsp_radioYes").on("click", function(){
		$("#prsp_otherDetailsNoToAll").prop("checked", false);
	})

}

/**
 * Initialization of the Event Listeners
 */
initEvent = function(){
	
	/*
	 * Add New Record in the respective Table
	 */
	$(".addNewRecord").on("click", function(event){

		var fieldsetId = $(this).data("fieldsetId");
		var tableId = $(this).data("tableId");

		/**
		 * Validate the fields
		 */
		if(!validateOtherDetailsFieldset(fieldsetId, "customValidate")){
			return;
		}
		
		/**
		 *  Add the new record
		 */
		addNewRecord(fieldsetId, tableId);
		setTimeout(function() {
			$("#"+ fieldsetId +" input, #"+ fieldsetId +" select").val("").change();
		}, 350);

		/**
		 * Update Relationship dropdown options (if applicable)
		 */
		updateRelationshipDropdown(fieldsetId, tableId);

		/**
		 * Validate Table Data
		 */
		$("#"+ $(this).data("fieldsetId") + "_error").hide();
		checkTableValidation();
	})

	/**
	 * Hide/Show Deceased Fields
	 */
	$("#"+portletNamespace+"pr_sp_fh_health_status").on("change", function(){
		if($(this).val() == 98){
			$(".prSPFhFieldsForDeceased").show();
			$(".prSPFhFieldsForNonDeceased").hide();
			$(".prSPFhFieldsForNonDeceased input, .prSPFhFieldsForNonDeceased select").val("");
		}else{
			$(".prSPFhFieldsForDeceased").hide();
			$(".prSPFhFieldsForDeceased input, .prSPFhFieldsForDeceased select").val("");
			$(".prSPFhFieldsForNonDeceased").show();
		}
	})

	$("#"+portletNamespace+"la_fh_health_status").on("change", function(){
		if($(this).val() == 98){
			$(".laFhFieldsForDeceased").show();
			$(".laFhFieldsForNonDeceased").hide();
			$(".laFhFieldsForNonDeceased input, .laFhFieldsForNonDeceased select").val("");
		}else{
			$(".laFhFieldsForDeceased").hide();
			$(".laFhFieldsForDeceased input, .laFhFieldsForDeceased select").val("");
			$(".laFhFieldsForNonDeceased").show();
		}
	})

	/**
	 * Validate and Save Other Section Details
	 */
	$("#otherDetailsSaveBtn").on("click", function(){
		
		/**
		 * Validate the fields and Call Save PF details API
		 */
		if (validateOtherDetails()) {
			$('#'+ portletNamespace + 'loader').show();
			savePF();
			openNextStep(5);
			$('#'+ portletNamespace + 'loader').hide();
		}
	})

	/**
	 * Custom Validation for Radio Buttons
	 */
	$(".requiredRadio").on("click", function(){
		var fieldName = $(this).attr("name");
		$("#"+ fieldName +"_required").hide();
	})
}

/**
 * Add new record into the respective table
 */
addNewRecord = function(fieldsetId, tableId){

	var table = $("#"+tableId);
	var tableWrapper = $("#"+tableId+"_wrapper");
	tableWrapper.show();

	$("#"+ $("#"+fieldsetId).data("fieldsetId") + "_error").hide();

	var recordsListParam = table.data("param");
	var recordsList = la_pr_sp_other_details_list[recordsListParam];

	var rowIdParam = table.data("index-param");
	var newRowId = (recordsList && recordsList.length>0)?Number(recordsList[recordsList.length - 1][rowIdParam]) + 1 : 1;

	var newRow = document.createElement("tr");
	newRow.setAttribute("data-index", newRowId);
	
	var newRecord = {};
	newRecord[rowIdParam] = newRowId;

	/**
	 * For each header get the value and addinto the respective column
	 */
	$("#"+tableId+" th.userdata").each(function() {
		var inputName = $(this).data("input-name");
		var inputField = $("#"+fieldsetId+" *[name='" + inputName + "']");
		var cellValue = inputField.val();

		/**
		 * Add the Record Data into new JSON object
		 */
		newRecord[$(this).data("param")] = cellValue;

		if (cellValue && $(this).data("master")) {
			var currentValueObj = masterJson[$(this).data("master")].find(function(item) {
				return item.value == cellValue;
			});

			cellValue = currentValueObj?currentValueObj.name:null;
		}

		if(!cellValue){
			cellValue = "-";
		}

		var cell = document.createElement("td");
		cell.textContent = cellValue;
		newRow.appendChild(cell);
	});

	/**
	 * Add Delete Button
	 */
	var deleteButton = document.createElement("button");
	deleteButton.className = "badge w-100 d-block";
	var deleteIcon = document.createElement("i");
	deleteIcon.className = "far fa-trash-alt";
	deleteButton.appendChild(deleteIcon);

	var deleteCell = document.createElement("td");
	deleteCell.appendChild(deleteButton);
	newRow.append(deleteCell);

	/**
	 * Add on click event to remove the respective row
	 */
	deleteButton.onclick = function() {
		var rowIdToRemove = $(this).closest("tr").data("index");
		
		var recordsList = la_pr_sp_other_details_list[recordsListParam];
		recordsList = recordsList.filter(item => item[rowIdParam] !== rowIdToRemove);
		la_pr_sp_other_details_list[recordsListParam] = recordsList;

		if(recordsList.length == 0){
			tableWrapper.hide();
		}
		
		$(this).closest("tr").remove();

		updateRelationshipDropdown(fieldsetId, tableId);
	};

	/**
	 * Add the new row to the table
	 */
	table.find("tbody").append(newRow);
	
	recordsList.push(newRecord);
	la_pr_sp_other_details_list[recordsListParam] = recordsList;
}

/**
 * Validate the Other Details Fieldset
 */
validateOtherDetailsFieldset = function(fieldsetId, fieldsClassName){
		
	var otherDetailsFormValidator = Liferay.Form.get(portletNamespace+'other_details_form').formValidator;
	otherDetailsFormValidator.resetAllFields();
	$(".requiredRadioMessage").hide();
	
	var fieldset = $("#" + fieldsetId);
	var inputs = fieldset.find('.' + fieldsClassName + ":visible");

	inputs.each(function() {
		var fieldName = $(this).attr("name");
		otherDetailsFormValidator.validateField(fieldName);
	});

	return !otherDetailsFormValidator.hasErrors();
}

/**
 * Validate the Other Details Fieldset
 */
validateOtherDetails = function(){

	/**
	 * Validate Other Section input fields
	 */
	var validOtherDetails = validateOtherDetailsFieldset(portletNamespace+'other_details_form', "validate");

	/**
	 * Validate Other Section Radio Buttons
	 */
	var fieldset = $("#" + portletNamespace+"other_details_form");
	var radioInputs = fieldset.find(".validate.requiredRadio");
	radioInputs.each(function() {
		if(!$(this).parent(".radio_container").is(":visible")){
			return;
		}
		var fieldName = $(this).attr("name");
		if(!$("*[name="+ fieldName +"]:checked").length){
			validOtherDetails = false;
			$("#"+ fieldName +"_required").show()
		}else{
			$("#"+ fieldName +"_required").hide()
		}
	});

	/**
	 * Validate Other Section Table data
	 */
	$(".validateTableError").hide();
	$(".requiredTableData:visible").each(function() {	
		var table = $("#"+$(this).data("tableId"));
		var recordsListParam = table.data("param");
		if(!la_pr_sp_other_details_list[recordsListParam] || la_pr_sp_other_details_list[recordsListParam].length == 0 ){
			console.log("No Data found for: "+recordsListParam);
			$("#"+ $(this).data("fieldsetId") + "_error").show();
			validOtherDetails = false;
		}
	});
	
	if(proposalFormData.common_data.form_type=="COM00"){
		var tableValidationSpouse= checkTableValidation();
		if(tableValidationSpouse==false){
			validOtherDetails = false;
		}
	}
	
	return validOtherDetails;
}

/**
 * Prepopulate Exisitng data into the repective tables
 */
populateOtherDetailsTableData = function(){
	$(".other_details_table").each(function() {
		var tableId = this.id;
		var fieldsetId = $(this).data("fieldsetId");

		var recordsListParam = $(this).data("param");
		var recordsList = la_pr_sp_other_details_list[recordsListParam];
	
		var rowIdParam =  $(this).data("index-param");

		if(!recordsList || recordsList.length == 0){
			console.log("no data present");
			$("#"+this.id+"_wrapper").hide();
			return;
		}
		$("#"+this.id+"_wrapper").show();
	
		console.log(recordsList);
		
		recordsList.forEach(function(record) {
			var table = $("#"+tableId);

			var newRow = document.createElement("tr");
			newRow.setAttribute("data-index", record[rowIdParam]);
			
			$("#"+tableId+" th.userdata").each(function() {
				var cellValue = record[$(this).data("param")];

				if (cellValue && $(this).data("master")) {
					var currentValueObj = masterJson[$(this).data("master")].find(function(item) {
						return item.value == cellValue;
					});
		
					cellValue = currentValueObj?currentValueObj.name:null;
				}
	
				if(!cellValue){
					cellValue = "-";
				}
	
				var cell = document.createElement("td");
				cell.textContent = cellValue;
				newRow.appendChild(cell);
			});
	
			/**
			 * Add Delete Button
			 */
			var deleteButton = document.createElement("button");
			deleteButton.className = "badge w-100 d-block";
			var deleteIcon = document.createElement("i");
			deleteIcon.className = "far fa-trash-alt";
			deleteButton.appendChild(deleteIcon);
		
			var deleteCell = document.createElement("td");
			deleteCell.appendChild(deleteButton);
			newRow.append(deleteCell);
		
			/**
			 * Add on click event to remove the respective row
			 */
			deleteButton.onclick = function() {
				
				var rowIdToRemove = $(this).closest("tr").data("index");

				var recordsList = la_pr_sp_other_details_list[recordsListParam];
				recordsList = recordsList.filter(item => item[rowIdParam] !== rowIdToRemove);
				la_pr_sp_other_details_list[recordsListParam] = recordsList;

				if(recordsList.length == 0){
					$("#"+tableId+"_wrapper").hide();
				}

				$(this).closest("tr").remove();

				updateRelationshipDropdown(fieldsetId, tableId);
			};
		
			/**
			 * Add the new row to the table
			 */
			table.find("tbody").append(newRow);
		});
		
	});
}

/**
 * Update Relationship Dropdown
 */
updateRelationshipDropdown = function(fieldsetId, tableId){
	var dropdown = $("#" + fieldsetId).find(".relationDropdown");

	if(dropdown && dropdown.length){
		var relationMaster = masterJson[dropdown.data("master")];
		
		var table = $("#"+ tableId)
		var tableData = la_pr_sp_other_details_list[table.data("param")]

		relationMaster = masterJson[dropdown.data("master")].filter(option =>
			!tableData.some(data => data[dropdown.data("param")] == option.value.toString())
		);

		dropdown.empty();
		$.each(relationMaster, function(index, option) {
			dropdown.append($('<option>', {
				value: option.value,
				text: option.name
			}));
		});
		dropdown.val("").change();
	}
	changeRelationshipOptions();
}

/**
 * Gets Details from IFSC Code
 */
function populateBankDetails(ifsc){

	showLoader();

	const getAccDetailsURL = getBankDetailsIFSCURL + "?IFSC_CODE="+ifsc.value;

	Liferay.Util.fetch(getAccDetailsURL, {
		headers : {
			"Content-Type": "application/json"
		},
		method : "GET"
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.info(validationResponse);
		const responseDataJson = validationResponse.responseData;
		console.info(responseDataJson);
		if(validationResponse != null && validationResponse.status){
			$(bankNameJEl).val(responseDataJson.bank);
			$(bankNameJEl).keyup();
			$(bankLocationJEl).val(responseDataJson.bankBranchName);
			$(bankLocationJEl).keyup();
			$("#"+ifsc.id).data("valid", true);
		}else if(validationResponse && !validationResponse.status){
			$("#responseMessage").html(validationResponse.errors[0]);	
			$("#"+ifsc.id).data("valid", false);	
		}else{
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			$("#"+ifsc.id).data("valid", false);
		}

		Liferay.Form.get(portletNamespace+'other_details_form').formValidator.validateField(ifsc.name);

		hideLoader();
	})

	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#"+ifsc.id).data("valid", false);
		Liferay.Form.get(portletNamespace+'other_details_form').formValidator.validateField(ifsc.name);
		hideLoader();
	});
}

/**
 Insurance History Details Request Object
 */
insuranceHistoryRequestObject = function(pfResponse){
	var insurance_history_details = {};

	insurance_history_details.la_details = userInsuranceHistoryRequestObject("la_");
	
	if(pfResponse.response_data.insurance_history_details.proposer_details != null){
		insurance_history_details.proposer_details = userInsuranceHistoryRequestObject("pr_sp_");
	}else {
		insurance_history_details.proposer_details = null;
	}
	
	if(pfResponse.response_data.insurance_history_details.spouse_details != null){
		insurance_history_details.spouse_details = userInsuranceHistoryRequestObject("pr_sp_");
	}else{
		insurance_history_details.spouse_details = null;
	}

	return insurance_history_details;
}

/**
 * No to All
 */
$("#la_otherDetailsNoToAll").on("click", function() {
	$(".la_radioNo").click();
});
$("#prsp_otherDetailsNoToAll").on("click", function() {
	$(".prsp_radioNo").click();
});

/**
 * LA/PR/SP Insurance History Request Object
 */
userInsuranceHistoryRequestObject = function(userPrefix){
	var insurance_history_details = {};

	insurance_history_details.app_insurance_available_yn = $("*[name=" + portletNamespace + userPrefix + "app_insurance_available_yn]:checked").val() || null;
	insurance_history_details.app_insurance_name = $("*[name=" + portletNamespace + userPrefix + "app_insurance_name]").val() || null;
	insurance_history_details.app_insurance_reason = $("*[name=" + portletNamespace + userPrefix + "app_insurance_reason]").val() || null;
	insurance_history_details.app_insurance_date = $("*[name=" + portletNamespace + userPrefix + "app_insurance_date]").val() || null;
	
	insurance_history_details.app_dis_ci_benefits_yn = $("*[name=" + portletNamespace + userPrefix + "app_dis_ci_benefits_yn]:checked").val() || null;
	insurance_history_details.app_dis_ci_name = $("*[name=" + portletNamespace + userPrefix + "app_dis_ci_name]").val() || null;
	insurance_history_details.app_dis_ci_reason = $("*[name=" + portletNamespace + userPrefix + "app_dis_ci_reason]").val() || null;
	insurance_history_details.app_dis_ci_date = $("*[name=" + portletNamespace + userPrefix + "app_dis_ci_date]").val() || null;
	
	insurance_history_details.e_policy_yn = $("*[name=" + portletNamespace + userPrefix + "e_policy_yn]:checked").val() || null;
	insurance_history_details.details_ins_policy_available_yn = $("*[name=" + portletNamespace + userPrefix + "details_ins_policy_available_yn]:checked").val() || null;
	
	insurance_history_details.ih_sf_rejected_yn = $("*[name=" + portletNamespace + userPrefix + "ih_sf_rejected_yn]:checked").val() || null;
	insurance_history_details.ih_sf_rejected_reason = $("*[name=" + portletNamespace + userPrefix + "ih_sf_rejected_reason]").val() || null;
	insurance_history_details.ih_sf_special_rate_policy_yn = $("*[name=" + portletNamespace + userPrefix + "ih_sf_special_rate_policy_yn]:checked").val() || null;
	

	
	insurance_history_list = [];
	
	la_pr_sp_other_details_list[userPrefix+"insurance_history_list"].forEach(function (user_insurance_history_record) {
		insurance_history_record = {};
		
		insurance_history_record.policy_number = user_insurance_history_record.policy_number ?? null
		insurance_history_record.insurance_company_data_id = user_insurance_history_record.insurance_company_data_id ?? null
		insurance_history_record.policy_issue_year = user_insurance_history_record.policy_issue_year ?? null
		insurance_history_record.annualised_premium = user_insurance_history_record.annualised_premium ?? null
		insurance_history_record.policy_status_data_id = user_insurance_history_record.policy_status_data_id ?? null
		insurance_history_record.sum_assured = user_insurance_history_record.sum_assured ?? null
		insurance_history_record.acceptance_terms = user_insurance_history_record.acceptance_terms ?? null
		insurance_history_record.reason_for_extra_premium = user_insurance_history_record.reason_for_extra_premium ?? null
		insurance_history_record.ih_row_id = user_insurance_history_record.ih_row_id ?? null
		
		insurance_history_list.push(insurance_history_record);
	});
	insurance_history_details.insurance_history = insurance_history_list;

	return insurance_history_details;
}


/**
 * Family Details Request Object
 */
 familyDetailsRequestObject = function(pfResponse){
	var familyDetails = {};
	familyDetails.proposer_details = pfResponse.response_data.family_details.proposer_details;
	familyDetails.spouse_details = pfResponse.response_data.family_details.spouse_details;

	var la_family_details = [];

	la_pr_sp_other_details_list["la_family_details_list"].forEach(function (la_family_details_record) {
		family_details_record = {};
		
		family_details_record.ih_row_id = la_family_details_record.ih_row_id ?? null
		family_details_record.relationship_data_id = la_family_details_record.relationship_data_id ?? null
		family_details_record.occupation = la_family_details_record.occupation ?? null
		family_details_record.annual_income = la_family_details_record.annual_income ?? null
		family_details_record.total_sum_assured = la_family_details_record.total_sum_assured ?? null
		family_details_record.can_delete = true;

		la_family_details.push(family_details_record);
	});

	familyDetails.la_details = la_family_details;

	return familyDetails;
}


/**
 * Family History Details Request Object
 */
 familyHistoryRequestObject = function(pfResponse){
	var family_history_details = {};

	family_history_details.la_details = userFamilyHistoryRequestObject("la_");
	
	if(pfResponse.response_data.family_history_details.proposer_details != null){
		family_history_details.proposer_details = userFamilyHistoryRequestObject("pr_sp_");
	}else {
		family_history_details.proposer_details = null;
	}
	
	if(pfResponse.response_data.family_history_details.spouse_details != null){
		family_history_details.spouse_details = userFamilyHistoryRequestObject("pr_sp_");
	}else{
		family_history_details.spouse_details = null;
	}

	return family_history_details;
}

/**
 * LA/PR/SP  Family History Request Object
 */
userFamilyHistoryRequestObject = function(userPrefix){
	var family_history_details = {};
	
	family_history_details.fh_any_disease_yn = $("*[name=" + portletNamespace + userPrefix + "fh_any_disease_yn]:checked").val() || null;
	family_history_details.fh_any_disease_details = $("*[name=" + portletNamespace + userPrefix + "fh_any_disease_details]").val() || null;
	family_history_details.fh_etli_policies_yn = $("*[name=" + portletNamespace + userPrefix + "fh_etli_policies_yn]:checked").val() || null;

	family_history_list = [];

	la_pr_sp_other_details_list[userPrefix+"family_history_list"].forEach(function (user_family_history_record) {
		family_history_record = {};
		
		family_history_record.fh_row_id = user_family_history_record.fh_row_id ?? null
		family_history_record.family_member_data_id = user_family_history_record.family_member_data_id ?? null
		family_history_record.health_status = user_family_history_record.health_status ?? null
		family_history_record.age = user_family_history_record.age ?? null
		family_history_record.age_on_death = user_family_history_record.age_on_death ?? null
		family_history_record.cause_of_death = user_family_history_record.cause_of_death ?? null
		
		family_history_list.push(family_history_record);
	});
	family_history_details.family_history = family_history_list;

	return family_history_details;
}
var laFmilyIncome=document.querySelector('select[name="' + portletNamespace + 'la_family_details_relationship_data_id"]');
var laFamilyDetails=document.querySelector('select[name="' + portletNamespace + 'la_fh_family_member_data_id"]');
var prSPFamilyDetails = document.querySelector('select[name="' + portletNamespace + 'pr_sp_fh_family_member_data_id"]');	
var radioLa=$("input[name='" + portletNamespace + "la_maritalStatus']");
var radioPoposer = $("input[name='" + portletNamespace + "proposer_maritalStatus']");
var radioSpouse = $("input[name='" + portletNamespace + "spouse_maritalStatus']");
$('.spouseValidate').change(function(){
	$('.addNewRecord').each(function() {
		var fieldsetId = $(this).data("fieldsetId");
		var tableId = $(this).data("tableId");
		
		updateRelationshipDropdown(fieldsetId, tableId);
    });
	
});

function changeRelationshipOptions(){
	var radioValueLa = $("input[name='" + portletNamespace + "la_maritalStatus']:checked").val();
	var radioValueProposer = $("input[name='" + portletNamespace + "proposer_maritalStatus']:checked").val();
	var radioValueSpouse= $("input[name='" + portletNamespace + "spouse_maritalStatus']:checked").val();
	 if(proposalFormData.common_data.is_la_pr_same_yn=="Y" && proposalFormData.common_data.spouse_exist_yn=="N"){
		 if(radioValueLa!=14){
			 disableSpouse(laFmilyIncome,"1327");
			 if(laFamilyDetails){
				 disableSpouse(laFamilyDetails,"1324");
			 }
		 }
	 }else if(proposalFormData.common_data.is_la_pr_same_yn=="N"){
		 disableSpouse(laFmilyIncome,"1327");
		 if(laFamilyDetails){
			 disableSpouse(laFamilyDetails,"1324");
		 }
		 
		 if(radioValueProposer!=14){
			 if(prSPFamilyDetails){
				 disableSpouse(prSPFamilyDetails,"1324")
			 } 
		 }
	 }else if(proposalFormData.common_data.is_la_pr_same_yn=="Y" && proposalFormData.common_data.spouse_exist_yn=="Y"){
		 if(radioValueLa!=14){
			 disableSpouse(laFmilyIncome,"1327");
		 }
		
	 }
	 
}
var test;
function disableSpouse(element,optionValue){
	var optionToDisable = element.querySelector(`[value="${optionValue}"]`);
	test=optionToDisable;
	if(optionToDisable){
		optionToDisable.remove();
	}	
	$('#'+element.id).val('').change();
}
// table validation 
function checkTableValidation(){
	var radioValueLa = $("input[name='" + portletNamespace + "la_maritalStatus']:checked").val();
	var radioValueProposer = $("input[name='" + portletNamespace + "proposer_maritalStatus']:checked").val();
	var radioValueSpouse= $("input[name='" + portletNamespace + "spouse_maritalStatus']:checked").val();
	var hasTableValidation=false;
	var hasTableValidationPrSp=false;
	if(proposalFormData.common_data.is_la_pr_same_yn=="Y" && proposalFormData.common_data.spouse_exist_yn=="N"){
		if(radioValueLa!=14){
			hasTableValidation=checkTableRecords('la_family_history_details_table','2');
		}else{
			hasTableValidation=checkTableRecords('la_family_history_details_table','3');
		}
		hasTableValidationPrSp=true;
	}
	else if(proposalFormData.common_data.is_la_pr_same_yn=="N"){
		if(laFamilyDetails){
			hasTableValidation=checkTableRecords('la_family_history_details_table','2');
		}
		if(prSPFamilyDetails && radioValueProposer!=14){
			hasTableValidationPrSp=checkTableRecords('pr_sp_family_history_details_table','2');
		}else if(prSPFamilyDetails && radioValueProposer==14 ){
			hasTableValidationPrSp=checkTableRecords('pr_sp_family_history_details_table','3');
		}
		
	}else if(proposalFormData.common_data.is_la_pr_same_yn=="Y" && proposalFormData.common_data.spouse_exist_yn=="Y"){
		hasTableValidation=checkTableRecords('la_family_history_details_table','3');
		hasTableValidationPrSp=checkTableRecords('pr_sp_family_history_details_table','3');
	}
	if(hasTableValidation==false){
		$('#error-messageLA').show();
	}else{
		$('#error-messageLA').hide();
	}
	if(hasTableValidationPrSp==false){
		$('#error-messagePrSp').show();
	}else{
		$('#error-messagePrSp').hide();
	}
	return hasTableValidation&&hasTableValidationPrSp;
}
 function checkTableRecords(tableId,value){
	 var hastableRecords=false;
	 var table = document.getElementById(tableId);
		var tbody = table.getElementsByTagName("tbody")[0];
		var relationshipValues = [];
		for (var i = 0; i < tbody.rows.length; i++) {
		    var row = tbody.rows[i];
		    var relationCell = row.cells[0];
		    var relationValue = relationCell.textContent.trim();
		    relationshipValues.push(relationValue);
		}
		if(value=="3"){
			if(relationshipValues.includes("Father")&& relationshipValues.includes("Mother")&& relationshipValues.includes("Spouse")){
				hastableRecords= true;
			}
		}else if(value=="2"){
			if(relationshipValues.includes("Father")&& relationshipValues.includes("Mother")){
				hastableRecords= true;
			}
		}
		
		return hastableRecords;
 }
	



