<%@ include file="../init.jsp"%>


   <script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>

<div class="card">
	<div class="card-header" id="headingFive">
		<!--<h2 class="mb-0 title-tabs">
			<span><img
				src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon" /></span>
			<liferay-ui:message key="label-covid-details" />
		</h2>-->
		<h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
			   <p><liferay-ui:message key="label-covid-questionnaire" /></p>
			</button>
		 </h2>
	</div>
	<div id="collapseFive" class="collapse covid-details-section" aria-labelledby="headingFive"
		data-parent="#accordionExample">
		<div class="card-body">
			<div class="edto-income-plan-tiles-wrapper">
				<div class="row">
					<div class="col-md-6 col-6">
						<span class="title"> <img
							src="/o/edelweisstokio-theme/images/praposal/insure.png"
							alt="icon" /> <liferay-ui:message key="life-to-be-insured" /></span>
					</div>
					<c:if
						test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-6 col-6">
							<span class="title"> <img
								src="/o/edelweisstokio-theme/images/praposal/proposal.png"
								alt="icon" /> <liferay-ui:message key="covid-proposer" /></span>
						</div>
					</c:if>
				</div>
				<aui:form name="covid_details_form" action="" method="post">
					<div class="communication-details border-0">
					<div id='<portlet:namespace/>covid_details_step'>
						<h5>COVID Questionnaire</h5>
						<div class="covind-inner-wrapper">
							<div class="covid-title-wrapper">
								<h6>1) Have you ever tested positive for the novel
									coronavirus (SARS-CoV-2/COVID-19)? If yes, provide the date of
									positive diagnosis. And also details of subsequent tests*</h6>
							</div>
							<div class="row">
								<div class="col-md-6 col-6">
									<div class="form-box">
										<div class="radio_container">
											<aui:input name="la_covid_yn" type="radio" id="la_covid_yes" required="true" onChange="validateRadioButtonsOnChange('covid_details_form','la_covid_yn')"
												value="Y" label=""
												checked="${laCovidDetails.covidComplicationYn=='Y'?true:false }"
												onclick="covidComplication('la_covid_yes','myselfToggle','showQuestion','la_')">
											</aui:input>
											<label for="<portlet:namespace/>la_covid_yes"><liferay-ui:message
													key="label-yes" /></label>

											<aui:input name="la_covid_yn" type="radio" id="la_covid_no" required="true" onChange="validateRadioButtonsOnChange('covid_details_form','la_covid_yn')"
												value="N" label=""
												checked="${laCovidDetails.covidComplicationYn=='N'?true:false }"
												onclick="covidComplication('la_covid_yes','myselfToggle','showQuestion','la_')">
											</aui:input>
											<label for="<portlet:namespace/>la_covid_no"><liferay-ui:message
													key="label-no" /></label>
										</div>
									</div>
									
								</div>
							
								<div class="col-md-6 col-6">
								<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
									<div class="form-box">
										<div class="radio_container">
											<aui:input name="pr_covid_yn" type="radio" id="pr_covid_yes" required="true" onChange="validateRadioButtonsOnChange('covid_details_form','pr_covid_yn')"
												value="Y" label=""
												checked="${prSpCovidDetails.covidComplicationYn=='Y'?true:false }"
												onclick="covidComplication('pr_covid_yes','prSpToggle','showPrSpQuestion','pr_')">
											</aui:input>
											<label for="<portlet:namespace/>pr_covid_yes"><liferay-ui:message
													key="label-yes" /></label>

											<aui:input name="pr_covid_yn" type="radio" id="pr_covid_no" required="true" onChange="validateRadioButtonsOnChange('covid_details_form','pr_covid_yn')"
												value="N" label=""
												checked="${prSpCovidDetails.covidComplicationYn=='N'?true:false }"
												onclick="covidComplication('pr_covid_yes','prSpToggle','showPrSpQuestion','pr_')">
											</aui:input>
											<label for="<portlet:namespace/>pr_covid_no"><liferay-ui:message
													key="label-no" /></label>
										</div>
									</div>
								</c:if>
								</div>
							 
							 <div class="col-md-6 col-12">
								<div id="showQuestion" style="display: none;">
									<p class="d-md-none">Life to be Insured</p>
									<div class="col-md-12 col-12">
										<p class="mb-3 mt-4">a) If yes, please select any one
											option?</p>

									</div>

									<div class="col-md-12 col-12">
										<div class="form-box">
											<div class="select-container">
												<aui:select title=" " cssClass="select2-select" name="la_covid_details" label=""
													value="${laCovidDetails.covidDetails}" data-placeholder="<%=LanguageUtil.get(request, "label-please-select-an-option") %>">
													<aui:option value=""></aui:option>
													<aui:option value="HOSP">Hospitalized</aui:option>
													<aui:option value="HOME QUAR">Asymptomatic Home Quarantined</aui:option>
													<aui:option value="ONGOING">Ongoing</aui:option>
													<aui:validator name="required" errorMessage="Please Enter Valid Details">
														function(val){
														   var la_covid_yn = $('input[name="<portlet:namespace/>la_covid_yn"]:checked').val();		
														   
														   if(val == '' || !val){
																	return false
															}
																				  
														   return la_covid_yn === 'Y';
														}
													</aui:validator>
												</aui:select>
												<div class="select-error-message" style="display: none;"><liferay-ui:message key="Please Provide Valid Details" /></div>
											</div>
										</div>
									</div>

									<div class="col-md-12 col-12">
										<p class="mb-3 mt-4">b) Date of diagnosis</p>
									</div>
									<div class="col-md-12 col-12">
										<div class="form-box">
											<label class="custom-field two mt-0"> <aui:input
													name="la_diagnosis_date" placeholder="&nbsp;" label=""
													cssClass="validate date-of-diagnosis" type="text" value="${laCovidDetails.diagnosisDate}"
													maxLength="15" minLength="0" wrappedField="<%=true%>" onInput="validateRadioButtonsOnChange('covid_details_form','la_diagnosis_date')">
													<aui:validator name="required" errorMessage="Please provide a valid Date">
														function(val){
														   var la_covid_yn = $('input[name="<portlet:namespace/>la_covid_yn"]:checked').val();		                      
														   return la_covid_yn === 'Y';
														}
													</aui:validator>
													<aui:validator name="custom" errorMessage="Please provide a valid Date">
															    function (val, fieldNode, ruleValue) {
															        return isValidDateInRangeForCovid(val);
															    }
													</aui:validator>
												</aui:input>
												<span class="placeholder"><liferay-ui:message key="Date of diagnosis" /></span>
											</label>
										</div>
									</div>

									<div class="col-md-12 col-12">
										<p class="mb-3 mt-4">c) Diagnosis details</p>
									</div>
									<div class="col-md-12 col-12">
										<div class="form-box">
											<label class="custom-field two mt-0"> <aui:input
													name="la_diagnosis_details" placeholder="&nbsp;" label=""
													cssClass="validate valphanumwithspace" type="text" maxLength="250"
													minLength="0" wrappedField="<%=true%>"
													value="${laCovidDetails.diagnosisDetails}" onInput="validateRadioButtonsOnChange('covid_details_form','la_diagnosis_details')">
													<aui:validator name="required" errorMessage="Please Provide Valid Details">
														function(val){
														   var la_covid_yn = $('input[name="<portlet:namespace/>la_covid_yn"]:checked').val();		                      
														   return la_covid_yn === 'Y';
														}
													</aui:validator>
												</aui:input>
												<span class="placeholder"><liferay-ui:message key="Diagnosis details" /></span>
											</label>
										</div>
									</div>
							</div>
							 </div>
							 <div class="col-md-6 col-12">
								<div id="showPrSpQuestion" style="display: none;">
									<p class="d-md-none">Proposer/ Spouse</p>
									<div class="col-md-12 col-12">
												<p class="mb-3 mt-4">a) If yes, please select any one
													option?</p>
											</div>
											<div class="col-md-12 col-12">
												<div class="form-box">
													<div class="select-container">
														<aui:select title=" " required="true" cssClass="select2-select" name="pr_covid_details" label="" data-placeholder="<%=LanguageUtil.get(request, "label-please-select-an-option") %>"
															value="${prSpCovidDetails.covidDetails}">
															<aui:option value=""></aui:option>
															<aui:option value="HOSP">Hospitalized</aui:option>
															<aui:option value="HOME QUAR">Asymptomatic Home Quarantined</aui:option>
															<aui:option value="ONGOING">Ongoing</aui:option>
															<aui:validator name="required" errorMessage="Please Provide Valid Details">
																function(val){
																   var la_covid_yn = $('input[name="<portlet:namespace/>pr_covid_yn"]:checked').val();	
																   
																   if(val == '' || !val){
		                                               		             return false
		                                                            }
																   	                      
				                                                   return la_covid_yn === 'Y';
																}
															</aui:validator>
														</aui:select>
														<div class="select-error-message" style="display: none;"><liferay-ui:message key="Please Provide Valid Details" /></div>
													</div>
												</div>
											</div>
											<div class="col-md-12 col-12">
												<p class="mb-3 mt-4">b) Date of diagnosis</p>
											</div>
											<div class="col-md-12 col-12">
												<div class="form-box">
													<label class="custom-field two mt-0"> <aui:input
															name="pr_diagnosis_date" placeholder="&nbsp;" label=""
															cssClass="validate date-of-diagnosis" type="text" value="${prSpCovidDetails.diagnosisDate}"
															maxLength="15" minLength="0" wrappedField="<%=true%>" onInput="validateRadioButtonsOnChange('covid_details_form','pr_diagnosis_date')">
															<aui:validator name="required" errorMessage="Please provide a valid Date">
																function(val){
																   var la_covid_yn = $('input[name="<portlet:namespace/>pr_covid_yn"]:checked').val();		                      
				                                                   return la_covid_yn === 'Y';
																}
															</aui:validator>
															<aui:validator name="custom" errorMessage="Please provide a valid Date">
															    function (val, fieldNode, ruleValue) {
															        return isValidDateInRangeForCovid(val);
															    }
															</aui:validator>
														</aui:input>
														<span class="placeholder"><liferay-ui:message key="Date of diagnosis" /></span>	
																					
													</label>
												</div>
											</div>
											<div class="col-md-12 col-12">
												<p class="mb-3 mt-4">c) Diagnosis details</p>
											</div>
											<div class="col-md-12 col-12">
												<div class="form-box">
													<label class="custom-field two mt-0"> <aui:input
															name="pr_diagnosis_details" placeholder="&nbsp;" label=""
															cssClass="validate valphanumwithspace" type="text" maxLength="250"
															minLength="0" wrappedField="<%=true%>"
															value="${prSpCovidDetails.diagnosisDetails}" onInput="validateRadioButtonsOnChange('covid_details_form','pr_diagnosis_details')">
																<aui:validator name="required" errorMessage="Please Provide Valid Details">
																function(val){
																   var la_covid_yn = $('input[name="<portlet:namespace/>pr_covid_yn"]:checked').val();		                      
				                                                   return la_covid_yn === 'Y';
																}
															</aui:validator>
														</aui:input> 
														<span class="placeholder"><liferay-ui:message key="Diagnosis details" /></span>
													</label>
												</div>
											</div>
									</div>
							 </div>
							</div>
						</div>
								</div>								
						<a class="edto-btn-primary mt-3" href="javascript:;" 
						onClick="saveCovidDetails();">
							Save & Continue
					</a>
					</div>									
				</aui:form>
			</div>
		</div>
	</div>
</div>


<aui:script>
function isValidDateInRangeForCovid(val) {
    // Split the input date into day, month, and year components
    var dateParts = val.split("/");
    if (dateParts.length !== 3) {
        return false; // Date format is not correct
    }

    var day = parseInt(dateParts[0], 10);
    var month = parseInt(dateParts[1], 10); // No need to subtract 1 from month
    var year = parseInt(dateParts[2], 10);

    // Check if the day and month are within valid ranges
    if (day < 1 || day > 31 || month < 1 || month > 12) {
        return false; // Invalid day or month
    }

    // Create a new Date object with the parsed components
    var date = new Date(year, month - 1, day); // Subtract 1 from month since it's zero-based
    var currentDate = new Date(); // Get the current date
    var minDate = new Date("2020-01-01"); // Set the minimum date to January 1, 2020

    // Check if the date is a valid date and falls within the range
    if (!isNaN(date) && date >= minDate && date <= currentDate) {
        return true; // Valid date within the range
    }

    return false; // Invalid date or out of range
}


var namespace = "<portlet:namespace/>";

function covidComplication(fieldId,toggleId,divId,prefix){
    
	if(document.getElementById(namespace + fieldId).checked){	
		 showDiv(true,divId);
	}else{
		 showDiv(false,divId);
  }
	
}
function showDiv(display, divId) {
	if (display) {
		document.getElementById(divId).style.display = 'block';
	} else {
		document.getElementById(divId).style.display = 'none';
	}
}

function validateCovidSection(){

	 let currentFormValidator = Liferay.Form.get('<portlet:namespace/>covid_details_form').formValidator;
    
     currentFormValidator.validate();
    
     if (currentFormValidator.hasErrors()) {
        currentFormValidator.focusInvalidField();
		return false;
	 }
     return true;
}

function saveCovidDetails(){

     if(!validateCovidSection()){
     	return false;
     }

	 savePF();
	 
	 // Complete covid section
	 openNextStep(5);
	 finalSubmission();

}

const setCovidDetails = (userPrefix, namespace, pfResponse) =>{

	const spouseExist = '${commonDetails.spouseExistYn}';
	const isLaPrSame =  '${commonDetails.isLaPrSameYn}';

	
	if(isLaPrSame == 'Y' && userPrefix == 'pr_'){
		return null;
	}else if(userPrefix == 'sp_' && spouseExist == 'N'){
		return null;
	}
	
	if(userPrefix == 'sp_'){
		userPrefix = 'pr_'
	}
	
	const covidDetails = {};
	
	covidDetails.txn_id= null;
	covidDetails.close_contact_yn= "";
	covidDetails.close_contact_details= "";
	covidDetails.serving_notice_of_quarantine_yn= "";
	covidDetails.serving_notice_of_quarantine_details= "";
	covidDetails.advised_to_test_yn= "";
	covidDetails.advised_to_test_details= null;
	covidDetails.tested_positive_yn= "";
	covidDetails.tested_positive_details= "";
	covidDetails.experienced_symptoms_yn= "";
	covidDetails.experienced_symptoms_details= "";
	covidDetails.healthcare_professionals_yn= "";
	covidDetails.healthcare_professionals_details= "";
	covidDetails.travel_is_nri_yn= "";
	covidDetails.travel_nri_country= "";
	covidDetails.travel_nri_city= "";
	covidDetails.travel_nri_date_of_travel= "";
	covidDetails.travel_nri_intended_duration= "";
	covidDetails.travel_travel_abroad_past14_days_yn= "";
	covidDetails.travel_travel_abroad_past14_days_country= "";
	covidDetails.travel_travel_abroad_past14_days_city= "";
	covidDetails.travel_travel_abroad_past14_days_date_arrived= "";
	covidDetails.travel_travel_abroad_past14_days_date_departed= "";
	covidDetails.travel_travel_abroad_next3_month_yn= "";
	covidDetails.travel_travel_abroad_next3_month_country= "";
	covidDetails.travel_travel_abroad_next3_month_city= "";
	covidDetails.travel_travel_abroad_next3_month_date_of_travel= "";
	covidDetails.travel_travel_abroad_next3_month_intended_duration= "";
	covidDetails.covid_vaccinated_yn= "";
	covidDetails.covid_vaccinated_first_dose_date= "";
	covidDetails.covid_vaccinated_second_dose_date= "";
	covidDetails.covid_vaccinated_name= "";
	covidDetails.covid_vaccinated_adverse_reaction_yn= "";
	covidDetails.covid_vaccinated_adverse_reaction_treatment_taken= "";
	covidDetails.complete_recovery_date= "";
	covidDetails.covid_form_path= null;
	covidDetails.daily_duties_details= "";
	covidDetails.hospitalized_home_quarantine= null;
	covidDetails.covid_vaccinated_booster_date= null;
	covidDetails.covid_complication_yn= $('input[name="'+ namespace + userPrefix+'covid_yn"]:checked').val();
	covidDetails.covid_details= $('#'+namespace+ userPrefix +'covid_details').val();
	covidDetails.diagnosis_details=$('#'+namespace+ userPrefix +'diagnosis_details').val();
	covidDetails.diagnosis_date= $('#'+namespace+ userPrefix +'diagnosis_date').val();
	

	return covidDetails;
}

<c:if test="${laCovidDetails.covidComplicationYn == 'Y'}">
	covidComplication('la_covid_yes','myselfToggle','showQuestion','la_');
</c:if>

<c:if test="${prSpCovidDetails.covidComplicationYn == 'Y'}">
	covidComplication('pr_covid_yes','prSpToggle','showPrSpQuestion','pr_');
</c:if>

function NoToAll_la(){
 if( document.getElementById("myselfToggle").checked){	  
 document.getElementById(namespace+"la_covid_no").checked=true;	
 showDiv(false,'showQuestion');
 //document.getElementById(namespace+"la_covid_details").value="";	
 //document.getElementById(namespace+"la_diagnosis_details").value="";	
 //document.getElementById(namespace+"la_diagnosis_Date").value="";	
 }
}
function NoToAll_prSp(){
	 if( document.getElementById("prSpToggle").checked){
	 document.getElementById(namespace+"pr_covid_no").checked=true;	
	 showDiv(false,'showPrSpQuestion');
	 //document.getElementById(namespace+"pr_covid_details").value="";	
	 //document.getElementById(namespace+"pr_diagnosis_details").value="";	
	 //document.getElementById(namespace+"pr_diagnosis_Date").value="";	
	 }
	}
	
	$("#"+namespace+"la_covid_yes").on("click", function(){
		$("#myselfToggle").prop("checked", false);
	})
	
	$("#"+namespace+"pr_covid_yes").on("click", function(){
		$("#prSpToggle").prop("checked", false);
	})
	
</aui:script>