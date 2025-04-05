// showing the div for all clicking
function showDiv(display, divId) {
	addRemoveValidate(display, divId);
	if (display) {
		document.getElementById(divId).style.display = 'block';
	} else {
		document.getElementById(divId).style.display = 'none';
	}
}

//Add Remove Validate Class from the field based on user selection
function addRemoveValidate(display, divId){
	const inputList = document.getElementById(divId).querySelectorAll('input, select');
	for (i = 0; i < inputList.length; i++) {
		if(display){
			$("#"+inputList[i].id).addClass("validate");	
		}else{
			$("#"+inputList[i].id).removeClass("validate");	
		}
	}
}

//Regex for only alphabets with space
function validateNameInput(input) {
    var alphabetsWithSpaceRegex = /^[A-Za-z ]+$/;
    return alphabetsWithSpaceRegex.test(input);
}


//Validate all fields
function saveHealthDetails(validationDivId, validateCompleteDiv, liferayFormId,doSubmit){
	console.log("Validate Function Triggered");
	var validationDiv=$("#" + portletNamespace + validationDivId);
	var validDiv=true;
	var	currentFormValidator;
	try {
		currentFormValidator=Liferay.Form.get(portletNamespace + liferayFormId).formValidator;
	} catch (error) {
		console.error(error);
	}
	currentFormValidator.resetAllFields();
	var currentInputList = validationDiv.find(".validate");
	for (i = 0; i < currentInputList.length; i++) {
		var currentInputId=currentInputList[i].id;
		var currentInputValue=currentInputList[i].value;
		if(validateCompleteDiv || (currentInputValue!=undefined && currentInputValue!=''))
		{
			currentFormValidator.validateField(currentInputId);
		}
	}
	if ($('#medicalHistoryTable_LA').length > 0) {
	var ifRadioBtnchecked = $('#medical-details-type2 input.la-check:checked').val();
	if(ifRadioBtnchecked === 'N'){
		$('.medical-table-error-la').addClass('hide');
	}
	var varLaTableData = document.getElementById("la_table_data").rows.length;
	$('#medical-details-type2 input.la-check').each(function() {
        if ($(this).is(":checked") && $(this).val() === "Y") {
            if (varLaTableData <= 1) {
    		    $('.medical-table-error-la').removeClass('hide');
    		    doSubmit = false;
    		}else{
    			$('.medical-table-error-la').addClass('hide');
    		}
        }
    })
	}

	if ($('#mediacal_history_table_PS').length > 0) {
		var ifRadioBtncheckedPr = $('#medical-details-type2 input.pr-sp-check:checked').val();
		if(ifRadioBtncheckedPr === 'N'){
			$('.medical-table-error-pr').addClass('hide');
		}
		var varPrTableData = document.getElementById("pr_table_data").rows.length;
		$('#medical-details-type2 input.pr-sp-check').each(function() {
			if ($(this).is(":checked") && $(this).val() === "Y") {
				if (varPrTableData <= 1) {
					$('.medical-table-error-pr').removeClass('hide');
					doSubmit = false;
				}else{
					$('.medical-table-error-pr').addClass('hide');
				}
			}
		})
	}

	if(validDiv && !currentFormValidator.hasErrors())
	{
		if(doSubmit){
			$('#'+ portletNamespace + 'loader').show();
			savePF();
			openNextStep(3);
			console.log("Form Submitted by Button");
			$('#'+ portletNamespace + 'loader').hide();
		}
		return true;
	}
	else
	{
		if(currentFormValidator.hasErrors())
		{
			try {
				currentFormValidator.focusInvalidField();
				scroolToErrorField();
			} catch (error) {
				console.debug(error);
			}
		}
		return;
	}
}

//Not found LA_,Proposer_,Spouse_  in health_details.JSP file.
	const setLifeStyleDetails = (userPrefix, namespace, pfResponse) =>{
		
		//const healthDetails = {};
		const lifeStyleDetails = {};
		
		//height and weight details
		lifeStyleDetails.life_style_details_id= null;
		lifeStyleDetails.height_cms= null;
		lifeStyleDetails.height_feet= $('#'+namespace+ userPrefix +'heightFeet').val() ;
		lifeStyleDetails.height_inches= $('#'+namespace+ userPrefix +'heightInch').val() ; 
		lifeStyleDetails.weight_kg= $('#'+namespace+ userPrefix +'weight').val() ; 
		lifeStyleDetails.weight_variation_yn= $('input[name="'+ namespace + userPrefix + 'weightvariation"]:checked').val(); //Radio Button
		lifeStyleDetails.variation_type= $('#'+namespace+ userPrefix +'gain_in_kg').val()!==''?($('#'+namespace+ userPrefix +'gain_in_kg').val() >0?"GAIN":"LOSS"):null;
		lifeStyleDetails.variation_weight= $('#'+namespace+ userPrefix +'gain_in_kg').val() ;
		lifeStyleDetails.weight_variation_reason_data_id= $('#'+namespace+ userPrefix +'gain_loss_reason').val() ;
		
		//lifestyle details 
		lifeStyleDetails.q_travel_outside_yn= $('input[name="'+ namespace + userPrefix + 'q_travel_outside_yn"]:checked').val(); 
		lifeStyleDetails.q_travel_outside_country_code= null;
		lifeStyleDetails.q_travel_outside_country_oth= null;

		lifeStyleDetails.q_pilot_co_pilot_yn=  $('input[name="'+ namespace + userPrefix + 'q_pilot_co_pilot_yn"]:checked').val(); 
		lifeStyleDetails.q_adventurous_hobbies_yn= $('input[name="'+ namespace + userPrefix + 'q_adventurous_hobbies_yn"]:checked').val();
		lifeStyleDetails.q_sky_diving_or_parachuting_yn= $('input[name="'+ namespace + userPrefix + 'q_sky_diving_or_parachuting_yn"]:checked').val();
		lifeStyleDetails.q_paragliding_or_handgliding_yn= $('input[name="'+ namespace + userPrefix + 'q_paragliding_or_handgliding_yn"]:checked').val(); 
		lifeStyleDetails.q_mountaineering_or_outdoor_rock_climbing_yn= $('input[name="'+ namespace + userPrefix + 'q_mountaineering_or_outdoor_rock_climbing_yn"]:checked').val();  
		lifeStyleDetails.q_any_form_of_racing_yn=  $('input[name="'+ namespace + userPrefix + 'q_travel_outside_yn"]:checked').val();
		lifeStyleDetails.q_any_other_hazardous_hobby_yn=  $('input[name="'+ namespace + userPrefix + 'q_any_other_hazardous_hobby_yn"]:checked').val(); 
		lifeStyleDetails.q_adventurous_hobbies_details= $('#'+namespace+ userPrefix +'hazardousActivity').val() ;

		lifeStyleDetails.q_habit_forming_drugs_yn= $('input[name="'+ namespace + userPrefix + 'q_habit_forming_drugs_yn"]:checked').val(); 
		lifeStyleDetails.q_habit_forming_drugs_details= $('#'+namespace+ userPrefix +'q_habit_forming_drugs_details').val(); 

		lifeStyleDetails.q_consume_alcohol_yn=  $('input[name="'+ namespace + userPrefix + 'q_consume_alcohol_yn"]:checked').val();
		lifeStyleDetails.q_consume_alcohol_beer_qty= $('#'+namespace+ userPrefix +'beerpint').val() ; 
		lifeStyleDetails.q_consume_alcohol_beer_since= $('#'+namespace+ userPrefix +'beerpintyears').val() ; 
		lifeStyleDetails.q_consume_alcohol_hard_liquor_qty= $('#'+namespace+ userPrefix +'q_consume_alcohol_hard_liquor_qty').val() ;  
		lifeStyleDetails.q_consume_alcohol_hard_liquor_since= $('#'+namespace+ userPrefix +'q_consume_alcohol_hard_liquor_since').val() ; 
		lifeStyleDetails.q_consume_alcohol_wine_qty=$('#'+namespace+ userPrefix +'wine').val() ; 
		lifeStyleDetails.q_consume_alcohol_wine_since= $('#'+namespace+ userPrefix +'wineyears').val() ; 

		lifeStyleDetails.q_consume_narcotics_yn= $('input[name="'+ namespace + userPrefix + 'q_consume_narcotics_yn"]:checked').val();

		lifeStyleDetails.q_consume_narcotics_qty= $('#'+namespace+ userPrefix +'narcotics').val() ; 
		lifeStyleDetails.q_consume_narcotics_since=$('#'+namespace+ userPrefix +'narcoticsyears').val() ; 

		lifeStyleDetails.q_smoke_consume_yn= $('input[name="'+ namespace + userPrefix + 'q_smoke_consume_yn"]:checked').val();  
		lifeStyleDetails.q_smoke_consume_cigarettes_qty= $('#'+namespace+ userPrefix +'cigarettes').val() ; 
		lifeStyleDetails.q_smoke_consume_cigarettes_since= $('#'+namespace+ userPrefix +'cigarettesyears').val() ; 
		lifeStyleDetails.q_smoke_consume_bidi_qty= $('#'+namespace+ userPrefix +'bidi').val() ; 
		lifeStyleDetails.q_smoke_consume_bidi_since= $('#'+namespace+ userPrefix +'bidiyears').val() ; 
		lifeStyleDetails.q_smoke_consume_gutka_qty=$('#'+namespace+ userPrefix +'gutka').val() ; 
		lifeStyleDetails.q_smoke_consume_gutka_since=$('#'+namespace+ userPrefix +'gutkayears').val() ; 
		lifeStyleDetails.q_smoke_consume_paan_qty= $('#'+namespace+ userPrefix +'paan').val() ; 
		lifeStyleDetails.q_smoke_consume_paan_since= $('#'+namespace+ userPrefix +'paanyears').val() ; 
		lifeStyleDetails.q_smoke_consume_tobacco_pouch_qty= $('#'+namespace+ userPrefix +'tobacoo').val() ; 
		lifeStyleDetails.q_smoke_consume_tobacco_pouch_since= $('#'+namespace+ userPrefix +'tobacooyears').val() ; 

		lifeStyleDetails.q_smoke_consume_others= null;
		lifeStyleDetails.q_smoke_consume_cigar_qty= null;
		lifeStyleDetails.q_smoke_consume_cigar_since= null;
		lifeStyleDetails.q_smoke_consume_jarda_qty= null;
		lifeStyleDetails.q_smoke_consume_jarda_since= null;

		lifeStyleDetails.q_stopped_smoking_yn= $('input[name="'+ namespace + userPrefix + 'q_stopped_smoking_yn"]:checked').val();
		lifeStyleDetails.q_stopped_smoking_since=$('#'+namespace+ userPrefix +'smoking').val() ; 
		lifeStyleDetails.q_stopped_smoking_reason= $('#'+namespace+ userPrefix +'smokingreason').val() ; 

		//Medical details 

		lifeStyleDetails.q_consulted_influenza_yn= $('input[name="'+ namespace + userPrefix + 'q_consulted_influenza_yn"]:checked').val();
		lifeStyleDetails.q_consulted_influenza_details= $('#'+namespace+ userPrefix +'q_consulted_influenza_details').val() ;


		lifeStyleDetails.q_had_ecg_x_ray_yn= $('input[name="'+ namespace + userPrefix + 'q_had_ecg_x_ray_yn"]:checked').val();
		lifeStyleDetails.q_had_ecg_x_ray_details= $('#'+namespace+ userPrefix +'q_had_ecg_x_ray_details').val() ; 


		lifeStyleDetails.q_admitted_any_hospital_yn= $('input[name="'+ namespace + userPrefix + 'q_admitted_any_hospital_yn"]:checked').val();
		lifeStyleDetails.q_admitted_any_hospital_details= $('#'+namespace+ userPrefix +'q_admitted_any_hospital_details').val() ;


		lifeStyleDetails.q_taking_any_medication_yn= $('input[name="'+ namespace + userPrefix + 'q_taking_any_medication_yn"]:checked').val();
		lifeStyleDetails.q_taking_any_medication_details= $('#'+namespace+ userPrefix +'q_taking_any_medication_details').val() ;  

		lifeStyleDetails.q_any_disorder_heart_yn=  $('input[name="'+ namespace + userPrefix + 'q_any_disorder_heart_yn"]:checked').val();
		lifeStyleDetails.q_any_disorder_heart_details= $('#'+namespace+ userPrefix +'q_any_disorder_heart_details').val() ;  

		lifeStyleDetails.q_history_high_blood_pressure_yn= $('input[name="'+ namespace + userPrefix + 'q_history_high_blood_pressure_yn"]:checked').val();
		lifeStyleDetails.q_history_high_blood_pressure_details= $('#'+namespace+ userPrefix +'q_history_high_blood_pressure_details').val() ;  

		lifeStyleDetails.q_respiratory_lung_trouble_yn=  $('input[name="'+ namespace + userPrefix + 'q_respiratory_lung_trouble_yn"]:checked').val(); 
		lifeStyleDetails.q_respiratory_lung_trouble_details= $('#'+namespace+ userPrefix +'q_respiratory_lung_trouble_details').val() ;  

		lifeStyleDetails.q_diabetes_urine_yn=   $('input[name="'+ namespace + userPrefix + 'q_diabetes_urine_yn"]:checked').val();
		lifeStyleDetails.q_diabetes_urine_details=$('#'+namespace+ userPrefix +'q_diabetes_urine_details').val() ;  

		lifeStyleDetails.q_disease_kidneys_bladder_yn=  $('input[name="'+ namespace + userPrefix + 'q_disease_kidneys_bladder_yn"]:checked').val();
		lifeStyleDetails.q_disease_kidneys_bladder_details= $('#'+namespace+ userPrefix +'q_disease_kidneys_bladder_details').val() ;  

		lifeStyleDetails.q_disorder_digestive_system_yn=  $('input[name="'+ namespace + userPrefix + 'q_disorder_digestive_system_yn"]:checked').val();
		lifeStyleDetails.q_disorder_digestive_system_details=$('#'+namespace+ userPrefix +'q_disorder_digestive_system_details').val() ;  

		lifeStyleDetails.q_cancer_enlarged_gland_yn=    $('input[name="'+ namespace + userPrefix + 'q_cancer_enlarged_gland_yn"]:checked').val();
		lifeStyleDetails.q_cancer_enlarged_gland_details= $('#'+namespace+ userPrefix +'q_cancer_enlarged_gland_details').val() ;  

		lifeStyleDetails.q_tropical_diseases_yn=   $('input[name="'+ namespace + userPrefix + 'q_tropical_diseases_yn"]:checked').val();
		lifeStyleDetails.q_tropical_diseases_details= $('#'+namespace+ userPrefix +'q_tropical_diseases_details').val() ;  

		lifeStyleDetails.q_thyroid_disorder_goitre_yn=   $('input[name="'+ namespace + userPrefix + 'q_thyroid_disorder_goitre_yn"]:checked').val();
		lifeStyleDetails.q_thyroid_disorder_goitre_details= $('#'+namespace+ userPrefix +'q_thyroid_disorder_goitre_details').val() ;  

		lifeStyleDetails.q_anaemia_bleeding_yn=   $('input[name="'+ namespace + userPrefix + 'q_anaemia_bleeding_yn"]:checked').val();
		lifeStyleDetails.q_anaemia_bleeding_details= $('#'+namespace+ userPrefix +'q_anaemia_bleeding_details').val() ;  

		lifeStyleDetails.q_nervous_neurological_disorder_yn= $('input[name="'+ namespace + userPrefix + 'q_nervous_neurological_disorder_yn"]:checked').val();
		lifeStyleDetails.q_nervous_neurological_disorder_details= $('#'+namespace+ userPrefix +'q_nervous_neurological_disorder_details').val() ;  

		lifeStyleDetails.q_ear_eye_disorder_yn=   $('input[name="'+ namespace + userPrefix + 'q_ear_eye_disorder_yn"]:checked').val();
		lifeStyleDetails.q_ear_eye_disorder_details= $('#'+namespace+ userPrefix +'q_ear_eye_disorder_details').val() ;  

		lifeStyleDetails.q_disorder_muscle_bone_yn=   $('input[name="'+ namespace + userPrefix + 'q_disorder_muscle_bone_yn"]:checked').val();
		lifeStyleDetails.q_disorder_muscle_bone_details= $('#'+namespace+ userPrefix +'q_disorder_muscle_bone_details').val() ;  

		lifeStyleDetails.q_test_hiv_aids_yn=   $('input[name="'+ namespace + userPrefix + 'q_test_hiv_aids_yn"]:checked').val();
		lifeStyleDetails.q_test_hiv_aids_details= $('#'+namespace+ userPrefix +'q_test_hiv_aids_details').val() ;  

		lifeStyleDetails.q_excessive_alcohol_yn=  $('input[name="'+ namespace + userPrefix + 'q_excessive_alcohol_yn"]:checked').val();
		lifeStyleDetails.q_excessive_alcohol_details= $('#'+namespace+ userPrefix +'q_excessive_alcohol_details').val() ;  

		lifeStyleDetails.q_any_other_illness_disorder_yn=    $('input[name="'+ namespace + userPrefix + 'q_any_other_illness_disorder_yn"]:checked').val();
		lifeStyleDetails.q_any_other_illness_disorder_details= $('#'+namespace+ userPrefix +'q_any_other_illness_disorder_details').val() ;  

		lifeStyleDetails.q_deformity_p_abnormality_yn=  $('input[name="'+ namespace + userPrefix + 'q_deformity_p_abnormality_yn"]:checked').val();
		lifeStyleDetails.q_deformity_p_abnormality_details= $('#'+namespace+ userPrefix +'q_deformity_p_abnormality_details').val() ;  

		lifeStyleDetails.q_health_symptoms_appetite_yn=  $('input[name="'+ namespace + userPrefix + 'q_health_symptoms_appetite_yn"]:checked').val();
		lifeStyleDetails.q_health_symptoms_appetite_details= $('#'+namespace+ userPrefix +'q_health_symptoms_appetite_details').val() ;  

		lifeStyleDetails.q_pregnant_yn=   $('input[name="'+ namespace + userPrefix + 'q_pregnant_yn"]:checked').val();
		lifeStyleDetails.q_pregnant_weeks=$('#'+namespace+ userPrefix +'q_pregnant_weeks').val() ;  

		lifeStyleDetails.q_disorder_female_organ_yn=   $('input[name="'+ namespace + userPrefix + 'q_disorder_female_organ_yn"]:checked').val();
		lifeStyleDetails.q_disorder_female_organ_details= $('#'+namespace+ userPrefix +'q_disorder_female_organ_details').val() ;  

		lifeStyleDetails.q_hospitalized_condition_yn=   $('input[name="'+ namespace + userPrefix + 'q_hospitalized_condition_yn"]:checked').val();
		lifeStyleDetails.q_hospitalization_date= $('#'+namespace+ userPrefix +'q_hospitalization_date').val() ;  

		lifeStyleDetails.q_fully_recovered_medications_yn=  $('input[name="'+ namespace + userPrefix + 'q_fully_recovered_medications_yn"]:checked').val();
		lifeStyleDetails.q_fully_recovered_medications_details= $('#'+namespace+ userPrefix +'q_fully_recovered_medications_details').val() ; 
		
		lifeStyleDetails.q_health_care_covid_patients_yn= null;
		lifeStyleDetails.q_covid_tm_diabetes_yn=null; 
		lifeStyleDetails.q_covid_tm_coronary_yn=   null;
		
		return lifeStyleDetails;
	}
	
		const setMedialQuestionDetails = (userPrefix, namespace, pfResponse) =>{
				let tbody;
				const medicalQuestionDetailsArray=[];
				if( pfResponse.response_data.common_data.form_type==="SIM00"){
					if(userPrefix==='la_'){
						 tbody = document.getElementById("medicalHistoryTable_LA").getElementsByTagName("tbody")[0];
					}
					if(userPrefix==='pr_'){
						 tbody = document.getElementById("medicalHistoryTable_PS").getElementsByTagName("tbody")[0];
					}
					if (tbody.rows.length > 0) {
					const tableRows = tbody.getElementsByTagName("tr");
					
					for (let i = 0; i < tableRows.length; i++) {
				        const row = tableRows[i];
				        const cells = row.getElementsByTagName("td");

				        const rowData = {
				        		application_number: 0,
				        		med_q_details_row_id:i+1,
				        		disease_name:cells[0].textContent,
				        		diagnosis_date:cells[1].textContent,
				        		treatment_details:cells[2].textContent,
				        		question_no:null,
				        		dosage_details:cells[3].textContent,
				        		doctor_name:cells[4].textContent,
				        		further_test_date:cells[5].textContent,
				        		any_complications:cells[6].textContent,
				        		additional_remarks:cells[7].textContent,
				        };

				        medicalQuestionDetailsArray.push(rowData);
				    }
					
					return medicalQuestionDetailsArray;
					}
					else{
						return medicalQuestionDetailsArray;
					}
				}
				else{
					return medicalQuestionDetailsArray;
				}
				
		}
	
	
