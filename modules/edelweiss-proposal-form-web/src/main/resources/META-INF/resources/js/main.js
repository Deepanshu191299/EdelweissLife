function getPfResponse(namespace, pfResponse,commit = {}){
	
	console.log('Default pfResponse from GetPFDetails ==>',pfResponse);
	
	const pfRequest = pfResponse.response_data;
	
	//Set final commit state
	if(commit.hasOwnProperty('pdf_only')){
		pfRequest['commit_data'] = commit.commit_data;
	}
	
	if(commit.hasOwnProperty('pdf_only')){
		pfRequest['pdf_only'] = commit.pdf_only;
	}
	
	//Personal Details JSON Request Object for LA, PA, SP
	const personalDetails = {};
	personalDetails.la_details = setPersonalDetails('la_', namespace, pfResponse);
	personalDetails.proposer_details = setPersonalDetails('proposer_', namespace, pfResponse);
	personalDetails.spouse_details = setPersonalDetails('spouse_', namespace, pfResponse);
	pfRequest.personal_details = personalDetails;
	
	
	//Communication Details JSON Request Object for LA, PA, SP
	const communicationDetails = {};
	communicationDetails.la_details = setCommunicationDetails('la_', namespace, pfResponse);
	communicationDetails.proposer_details = setCommunicationDetails('proposer_', namespace, pfResponse);
	communicationDetails.spouse_details = setCommunicationDetails('spouse_', namespace, pfResponse);
	pfRequest.communication_details = communicationDetails;
	
	//Employement Details JSON Request Object for LA, PA, SP
	const employementDetails = {};
	employementDetails.la_details = setEmploymentDetails('la_', namespace, pfResponse);
	employementDetails.proposer_details = setEmploymentDetails('proposer_', namespace, pfResponse);
	employementDetails.spouse_details = setEmploymentDetails('spouse_', namespace, pfResponse);
	pfRequest.employment_details = employementDetails;
	
	//Nominee Details JSon Request
	var isNomineeEnabled = $("#isNomineeEnabled").val();
	console.log("isNomineeEnabled::"+ isNomineeEnabled);
	if(isNomineeEnabled === "true"){
		pfRequest.nominee_details = setNomineeDetails();
	}else{
		pfRequest.nominee_details = [];
	}
	
	//medial question details
	const lifeStyleDetails = {};
	const medialQuestionDetails = {};
	
	lifeStyleDetails.la_details = setLifeStyleDetails('la_', namespace, pfResponse);
	medialQuestionDetails.la_details=setMedialQuestionDetails('la_', namespace, pfResponse);
	
	if(pfResponse.response_data.common_data.spouse_exist_yn === 'N' && pfResponse.response_data.common_data.is_la_pr_same_yn === 'N'){
		lifeStyleDetails.proposer_details = setLifeStyleDetails('pr_', namespace, pfResponse);
		medialQuestionDetails.proposer_details = setMedialQuestionDetails('pr_', namespace, pfResponse);
	}else{
		lifeStyleDetails.proposer_details =null;
		medialQuestionDetails.proposer_details = [];
	}
	
	if(pfResponse.response_data.common_data.spouse_exist_yn === 'Y'){
		lifeStyleDetails.spouse_details = setLifeStyleDetails('pr_', namespace, pfResponse);
		medialQuestionDetails.spouse_details = setMedialQuestionDetails('pr_', namespace, pfResponse);
	}else{
		lifeStyleDetails.spouse_details = null;
	}
	
	pfRequest.common_data.send_copy_sh = $('#sendCopyVal').val();
	
	pfRequest.medical_question_details=medialQuestionDetails;
	pfRequest.life_style_details=lifeStyleDetails;
	

	//Provided LA_,Proposer_,Spouse_ in covid_question.JSP 
	const covidDetails = {};
	covidDetails.la_details = setCovidDetails('la_', namespace, pfResponse);
	covidDetails.proposer_details = setCovidDetails('pr_', namespace, pfResponse);
	covidDetails.spouse_details = setCovidDetails('sp_', namespace, pfResponse);
	pfRequest.covid_question_details = covidDetails;
	console.log(covidDetails);
	

	/*
	 * Other Details - Insurance History Details of LA, Proposer and Spouse
	 */
	pfRequest.insurance_history_details = insuranceHistoryRequestObject(pfResponse);
	
	/*
	 * Other Details - Family Details
	 */
	pfRequest.family_details = familyDetailsRequestObject(pfResponse);
	
	/*
	 * Other Details - Family History Details
	 */
	pfRequest.family_history_details = familyHistoryRequestObject(pfResponse);

	//Insurance History Details.jsp - Not Added LA_,Spouse_,Proposer_ in Same JSP file.
	const setInsuranceHistoryDetails = (userPrefix = 'la_') =>{

		const insuranceHistoryDetails = {};

		insuranceHistoryDetails.app_insurance_available_yn= null;
		insuranceHistoryDetails.app_insurance_name= null;
		insuranceHistoryDetails.app_insurance_reason= null;
		insuranceHistoryDetails.app_insurance_date= null;
		insuranceHistoryDetails.app_dis_ci_benefits_yn= null;
		insuranceHistoryDetails.app_dis_ci_name= null;
		insuranceHistoryDetails.app_dis_ci_reason= null;
		insuranceHistoryDetails.app_dis_ci_date= null;
		insuranceHistoryDetails.details_ins_policy_available_yn= null;
		insuranceHistoryDetails.ih_sf_rejected_yn= $('#'+namespace+ userPrefix +'isLifeInsuredLiHiSfRejected').val(); //Name Changes Required - Radio Button
		insuranceHistoryDetails.ih_sf_rejected_reason= $('#'+namespace+ userPrefix +'reasonLifeInsuredLiHiSfRejected').val();
		insuranceHistoryDetails.ih_sf_special_rate_policy_yn= $('#'+namespace+ userPrefix +'isProposerSpousePendingIPAvailable').val();
		insuranceHistoryDetails.e_policy_yn= null;

		//Array To add Details 
		const historyDetails = {};

		historyDetails.ih_row_id= 1;
		historyDetails.policy_number= "LA Proposal No";
		historyDetails.insurance_company_data_id= $('#'+namespace+ userPrefix +'laPendingInsuranceCompanyName').val(); 
		historyDetails.insurance_company_other= null;
		historyDetails.proposal_date= null;
		historyDetails.policy_issue_year= "2022";
		historyDetails.policy_issue_date= null;
		historyDetails.sum_assured=$('#'+namespace+ userPrefix +'laPendingInsuranceSumAssured').val();
		historyDetails.annualised_premium= 100000;
		historyDetails.policy_status_data_id= 131;
		historyDetails.policy_status_other= null;
		historyDetails.acceptance_terms=$('#'+namespace+ userPrefix +'laPendingInsuranceTerms').val(); 
		historyDetails.reason_for_extra_premium= null;
	}

	//Family Details :- .jsp -Present Under -  insurance History Details.jsp - Not Added LA_,Spouse_,Proposer_ in Same JSP file.
	const setFamilyDetails = (userPrefix = 'la_') =>{

		const familyDetails = {};

		familyDetails.ih_row_id= 2;
		familyDetails.relationship_data_id= $('#'+namespace+ userPrefix +'laRelationshipType').val();  
		familyDetails.occupation= $('#'+namespace+ userPrefix +'familyOccupation').val();  
		familyDetails.annual_income= $('#'+namespace+ userPrefix +'familyAnnualIncome').val();   
		familyDetails.total_sum_assured= $('#'+namespace+ userPrefix +'familySumAssured').val();  
		familyDetails.can_delete= true;
	}

	//Present Under medical history details.jsp file
	const setMedicalHistoryDetails = (userPrefix = 'la_') =>{

		const medicalHistoryDetails = {};

		medicalHistoryDetails.medq_details_row_id= 1;
		medicalHistoryDetails.disease_name= $('#'+namespace+ userPrefix +'diseaseName').val();  
		medicalHistoryDetails.diagnosis_date= "01/08/2023"; // We need to Merge first DD MM YYYY then we can set and Send 
		medicalHistoryDetails.treatment_details= $('#'+namespace+ userPrefix +'treatmentDetails').val();  
		medicalHistoryDetails.dosage_details= $('#'+namespace+ userPrefix +'dosageDetails').val();  
		medicalHistoryDetails.doctor_name= $('#'+namespace+ userPrefix +'doctorName').val();  
		medicalHistoryDetails.further_test_date= "24/08/2023"; // We need to Merge first DD MM YYYY then we can set and Send 
		medicalHistoryDetails.any_complications= $('#'+namespace+ userPrefix +'anyComplications').val();  
		medicalHistoryDetails.additional_remarks= $('#'+namespace+ userPrefix +'additionalRemarks').val();  
	}


	//Currently Not Found in Any JSP File.
	const setFamilyHistoryDetails = (userPrefix = 'la_') =>{

		//Array To add details.
		const familyHistoryDetails = {};

		familyHistoryDetails.fh_any_disease_yn= "Y";
		familyHistoryDetails.fh_any_disease_details= "LA yes Does anybody in your family fathermotherbrotherssisters";
		familyHistoryDetails.fh_etli_policies_yn=  "Y";
		familyHistoryDetails.fh_row_id = 1 ;
		familyHistoryDetails.family_member_data_id = 1324;
		familyHistoryDetails.age = 22;
		familyHistoryDetails.health_status = 93 ;
		familyHistoryDetails.age_on_death =  null;
		familyHistoryDetails.cause_of_death = null;
	}
	
	
	return pfRequest;
}