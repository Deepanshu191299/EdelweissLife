<%@ include file="/init.jsp"%>
<%@page import="edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<liferay-portlet:actionURL var="submitCsbIntegrationFormURL"
	name="<%= EdelweissCsbIntegrationWebPortletKeys.SUBMIT_CSB_INTEGRATION_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL> 

<aui:form name="csb_integration_form" action="<%=submitCsbIntegrationFormURL%>" method="post">
	<div class="container">
		<div class="card csb-card">
		<aui:fieldset>
			<div class="row text-left mt-3 csb-header" style="">
				<div class="col-md-10 heading display-1">
					<h5>
						<strong>Section A - CSB Bank Details </strong>
					</h5>
				</div>
			</div>
			<aui:field-wrapper>
				<aui:row cssClass="csb-fields-space">
					<aui:col width="30">
						<aui:input name="csbAccountNo" type="text" id="csb-account-no"
							label="CSB Bank Account / Reference Number" oninput="validateAccountNumber(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">20</aui:validator>
							
						</aui:input> 
					</aui:col>

					<aui:col width="30" cssClass="csb-fields-space-column">
						<aui:input name="csbEmployeeId" type="text" id="csb-employee-id"
							label="CSB Bank Employee ID" oninput="validateEmployeeID(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">10</aui:validator>
						</aui:input>
					</aui:col>
					<aui:col width="30" cssClass="csb-fields-space-column">
						<aui:input name="csbEmployeeName" type="text"
							id="csb-employee-name" label="CSB Bank Employee Name" oninput="allowOnlyAlphabet(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">75</aui:validator>
						</aui:input>
					</aui:col>
				</aui:row>
			</aui:field-wrapper>
	



			<div class="row text-left mt-3 csb-header">
				<div class="col-md-12 heading display-1">
					<h5>
						<strong>Section B - Proposer Details </strong>
					</h5>
				</div>
			</div>
			<aui:field-wrapper>
				<aui:row cssClass="csb-fields-space">
					<aui:col width="50">
						<aui:input name="clientName" type="text" id="clinet-name"
							label="Client Name" oninput="allowOnlyAlphabet(this); checkRelation(this);">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">75</aui:validator>
						</aui:input>
					</aui:col>
					<aui:col width="50" cssClass="csb-fields-space-column">
					    <aui:input name="dob" type="text" id="age-dob" label="DOB" placeholder="dd/mm/yyyy" oninput="formatDate(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="custom" errorMessage="Age should be between 18 and 55 years">
												            function(val, fieldNode, ruleValue) {
												                return validateAgeOnSubmit(val);
												            }
							</aui:validator>
						</aui:input>
					</aui:col>
				</aui:row>
				<aui:row cssClass="csb-fields-space">
					<!-- Gender Start  -->
				 <aui:col width="50">
						<label for="gender">Gender</label>
						<span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
						<div class="radio_container">
							<aui:input required="true" cssClass="validate setOpen" label=""
								name="gender" type="radio" value="Male" id="gender_male"
								checked="">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>gender_male">Male</label>

							<aui:input required="true" cssClass="validate setOpen" label=""
								name="gender" type="radio" value="Female" id="gender_female"
								checked="">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>gender_female">Female</label>
						 </div>
					</aui:col> 
				 <!-- Gender End -->
					
					<aui:col width="50" cssClass="csb-fields-space-column">
						<label for="occupation">Occupation</label>
						<span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
						<div class="radio_container">
							<aui:input required="true" cssClass="validate setOpen" label=""
								name="occupation" type="radio" value="Salaried" id="occupation_yes"
								checked="">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>occupation_yes">Salaried</label>

							<aui:input required="true" cssClass="validate setOpen" label=""
								name="occupation" type="radio" value="Non-Salaried" id="occupation_no"
								checked="">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>occupation_no">Non-Salaried</label>
						</div>
					</aui:col>
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!--  Mobile Number start -->
				<aui:col width="50" >
						<aui:input name="mobileNumber" type="text" id="mobile-number"
							label="Mobile Number(Registered with Bank)" oninput="validateOnlyDigits(this)" >
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="minLength" errorMessage="Must be 10 digits.">10</aui:validator>
        					<aui:validator name="maxLength" errorMessage="Must be 10 digits.">10</aui:validator>
						</aui:input>
					</aui:col>
				<!-- Mobile Number End -->	
				<!-- Email Id Start -->
					<aui:col width="50" cssClass="csb-fields-space-column" >
						<aui:input name="emailId" type="text" id="email-Id"
							label="Email ID(Registered with Bank)" oninput="validateEmailLength(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">50</aui:validator>
							 <aui:validator name="email"/>
						</aui:input>
					</aui:col>
				<!--  Email Id end -->
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<aui:col width="50" >
						<aui:input name="customerId" type="text" id="customer-id"
							label="Customer ID" oninput="validateNeoApplicationNumber(this)">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
							<aui:validator name="maxLength">20</aui:validator>
						</aui:input>
				</aui:col>
				<!--  Branch Code Start -->
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="branchCode" type="text" id="branch-code"
							label="Branch Code" oninput="validateBranchCode(this)">
							<aui:validator name="required" errorMessage="this-field-is-required"  />
							<aui:validator name="maxLength">10</aui:validator>
							
						</aui:input>
					</aui:col>
				<!--  Branch Code End -->	
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				 <!-- Branch Name Start -->
				<aui:col width="50" >
						<aui:input name="branchName" type="text" id="branch-name"
							label="Branch Name" oninput="validateBranchName(this)">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
							<aui:validator name="maxLength">75</aui:validator>
						</aui:input>
					</aui:col>
				<!-- Branch Name end  -->		
					
				<!--  PinCode Start -->
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="pinCode" type="text" id="pin-code"
							label="Pin Code" oninput="validatePincode(this)">
							<aui:validator name="required"  errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">6</aui:validator>
							<aui:validator name="minLength" errorMessage="Please enter at least 6 digits.">6</aui:validator>
						</aui:input>
					</aui:col>
				<!-- PinCode End -->	
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!-- Neo Application No Start -->
					<aui:col width="50">
						<aui:input name="neoApplicatioNumber" type="text"
							id="neo-application-number" label="Neo Application No." oninput="validateNeoApplicationNumber(this)">
							<aui:validator name="maxLength">20</aui:validator>
						</aui:input>
					</aui:col>
				<!--  Neo Application No End  -->	
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="nomineeName" type="text" id="nominee-name"
							label="Nominee Name" oninput="allowOnlyAlphabet(this)">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
							<aui:validator name="maxLength">75</aui:validator>
							
						</aui:input>
					</aui:col>
					
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!-- Relation Start -->
					<aui:col width="50" >
						<aui:select name="relation" type="text" id="relation"
							label="Relation" onChange="populateNomineeName()">
							<aui:option value="">Select-Relation</aui:option>
							<aui:option value="Father">Father</aui:option>
							<aui:option value="Mother">Mother</aui:option>
							<aui:option value="Spouse">Spouse</aui:option>
							<aui:option value="Brother">Brother</aui:option>
							<aui:option value="Sister">Sister</aui:option>
							<aui:option value="Son">Son</aui:option>
							<aui:option value="Daughter">Daughter</aui:option>
							<aui:option value="Grandfather">Grandfather</aui:option>
							<aui:option value="Grandmother">Grandmother</aui:option>
							<aui:option value="Grandson">Grand Son</aui:option>
							<aui:option value="Granddaughter">Grand Daughter</aui:option>
							<aui:option value="Employer">Employer</aui:option>
							<aui:option value="Guardian">Guardian</aui:option>
							<aui:option value="Husband">Husband</aui:option>
							<aui:option value="Nephew">Nephew</aui:option>
							<aui:option value="Other">Other</aui:option>
							<aui:option value="Himself/Herself">Himself/Herself</aui:option>
							<aui:option value="Niece">Niece</aui:option>
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:select>
					</aui:col>
				</aui:row>	
				<!-- Relation End -->	
					<%-- <aui:col width="50" >
						<aui:input name="mobileNumber" type="text" id="mobile-number"
							label="Mobile Number" oninput="validateOnlyDigits(this)" >
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="minLength" errorMessage="Must be 10 digits.">10</aui:validator>
        					<aui:validator name="maxLength" errorMessage="Must be 10 digits.">10</aui:validator>
						</aui:input>
					</aui:col> --%>
				
				<%-- <aui:row>
				
					<aui:col width="50" cssClass="csb-fields-space-column" >
						<aui:input name="emailId" type="text" id="email-Id"
							label="Email ID" oninput="validateEmailLength(this)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							<aui:validator name="maxLength">50</aui:validator>
							 <aui:validator name="email"/>
						</aui:input>
					</aui:col>
			
				</aui:row>	 --%>
			</aui:field-wrapper>

			<aui:field-wrapper>
				<div class="row text-left mt-3 csb-header">
					<div class="col-md-12 heading display-1">
						<h5>
							<strong>Section C - Risk Cover Details </strong>
						</h5>
					</div>
				</div>
				<aui:row cssClass="question">
				<aui:col width="50" style="display:flex;">
						<div class="col-md-4">				
							<label for="premiumAmount">Risk Cover Opted<span class="opted-astrik"> *</span></label>
                  		</div> 
	                  <div class="col-md-4">
		                  <div class="container" style="margin-top:15px;">
									<b><aui:input required="true" cssClass="validate setOpen" label="5lac"
										name="riskcovered" type="radio" value="5"
										id="premiumAmount_5L" checked="">
										<aui:validator name="required"
											errorMessage="this-field-is-required"></aui:validator>
									</aui:input></b>
									<%-- <label for="<portlet:namespace/>premiumAmount_5L">5L</label> --%>
								 </div>
							</div>	 	 
						<div class="col-md-4"> 
							<div class="container" style="margin-top:15px;">
								<b><aui:input required="true" cssClass="validate setOpen" label="3lac"
									name="riskcovered" type="radio" value="3"
									id="premiumAmount_3L" checked="">
									<aui:validator name="required"
										errorMessage="this-field-is-required"></aui:validator>
								</aui:input></b>
								<%-- <label for="<portlet:namespace/>premiumAmount_3L">3L</label> --%>
							</div> 
						</div>
					</aui:col>	
				</aui:row>
				
				
				<aui:row>
					<aui:col width="50" style="display:flex;">
						<div class="col-md-4">
							<label>Premium to be Paid</label>
						</div>
						<div class="col-md-4">	
							<label id="premiumAmountLabel">Rs.2349/-</label>
	        			</div>
        				<div class="col-md-4">
	        				<label>Rs.1409/-</label>
				        </div>
					</aui:col>					
				</aui:row>
			</aui:field-wrapper>

			<aui:field-wrapper>
				<div class="row text-left mt-3 csb-header">
					<div class="col-md-12 heading display-1">
						<h5>
							<strong>Section D - Mandatory Health Declarations </strong>
						</h5>
					</div>
				</div>
			
				<div class="radio-wrapper csb-radio">
					<p class="p-wrap-class">
						<h6>a)  Have you been currently taking any medication or undergoing medical treatment, hospitalized in the last 5 years, have suffered, or are suffering from diabetes, heart disease, cancer, or any major medical illness, which require regular follow up or treatment?
						<span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
						
						</h6>
						
		</p>
	   <div class="radio_container radio_gap">
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="decease" type="radio" value="Y" id="decease_yes" checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>decease_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="decease" type="radio" value="N" id="decease_no" checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>decease_no">No</label>
		</div>
	</div>	

   <div class="radio-wrapper csb-radio">
		<p class="p-wrap-class">
			
					<h6>b)  Have you been advised to undergo any surgery, medical investigation, or treatment for any condition (including Covid 19)?
					<span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
				</h6>
		
		</p>
	   <div class="radio_container radio_gap">
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="treatment" type="radio" value="Y" id="treatment_yes"
							checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>treatment_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="treatment" type="radio" value="N" id="treatment_no"
							checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>treatment_no">No</label>
		</div>
	</div>
  
   <div class="radio-wrapper csb-radio">
		<p class="p-wrap-class">
			
					<h6>c)  Have you been currently diagnosed with Covid -19 or asked to undergo testing for Covid- 19 or have you come into close contact with someone who has tested positive for Covid-19?
					<span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
					</h6>
		
		</p>
	   <div class="radio_container radio_gap">
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="testedPositive" type="radio" value="Y"
							id="testedPositive_yes" checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>testedPositive_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="testedPositive" type="radio" value="N"
							id="testedPositive_no" checked="" onChange="disableProceed()">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>testedPositive_no">No</label>
		</div>
	</div>
 </aui:field-wrapper>
 
  <aui:field-wrapper>
    <div class="row text-left mt-3 csb-header">
            <div class="col-md-12 heading display-1">
                <h5>
							<strong>Section E - <aui:input type="checkbox" name="termsCondition" id="termsCondition" label="" checked="" onChange="disableProceed()">
							<aui:validator name="required" errorMessage="this-field-is-required"/>
							</aui:input>
							&nbsp;&nbsp;<a href="#" data-toggle="modal" data-target="#termsModal">Terms and Conditions 
							 <span class="reference-mark text-warning" id="jbfs____">
                  <svg aria-hidden="true" class="lexicon-icon lexicon-icon-asterisk" focusable="false">
                   <use href="https://uat.edelweisslife.in/o/edelweisstokio-theme/images/clay/icons.svg#asterisk"></use>
                  </svg></span>
							</a></strong>
						</h5>
            </div>
    </div>
    
  		</aui:field-wrapper>
	
    <div style="text-align: center; margin-top: 20px;">
        <!-- <button class="edto-btn-primary m-auto" type="button" id="finalSubmit"> Proceed </button> -->
        <aui:button class="edto-btn-primary m-auto"  type="submit" id="finalSubmit" value="Proceed"></aui:button>
    </div>
    
  </aui:fieldset> 
 </div>
  </div>
</aui:form> 
  
<style>
.opted-astrik{
	color: #ffc107 !important;
	font-size: 28px; 
	-webkit-text-stroke:1px;"
}

.question {
	margin-top: 20px;
}

.csb-header {
	background-color: #FFF6E1;
	padding: 15px;
}

.csb-radio {
	margin-top: 20px;
}

.card.csb-card {
    margin-bottom: 30px;
    border: 1px solid #EC703A;
    border-radius: 8px;
}

.radio_container.radio_gap{
margin-top:20px;
}
.csb-fields-space{
margin-top:20px;
}

@media (max-width: 768px) {
    .csb-fields-space {
        margin-top: 20px;
    }
    .csb-fields-space-column{
      margin-top: 20px;
    }
   }
   
   .non-editable{
   pointer-events:none;
   } 
</style>

<script>
$(document).ready(function() {
    // Initially disable the proceed button
    $('#<portlet:namespace />finalSubmit').attr('disabled', true);  
    // Enable/Disable proceed button based on checkbox state
    /* $('#<portlet:namespace />termsCondition').change(function() {
        if ($(this).is(':checked')) {
            $('#<portlet:namespace />finalSubmit').attr('disabled', false);
        } else {
            $('#<portlet:namespace />finalSubmit').attr('disabled', true);
        }
    }); */
});

// disableproceed button based on medical condition 
function disableProceed(){
	 var medicalValue = $('input[name="<portlet:namespace/>decease"]:checked').val();
	 var treatment = $('input[name="<portlet:namespace/>treatment"]:checked').val();
	 var testedPositive = $('input[name="<portlet:namespace/>testedPositive"]:checked').val();
	 var termsChecked = $('input[name="<portlet:namespace/>termsCondition"]').is(':checked');
	 	console.log("medicalValue----"+medicalValue);
	 	console.log("treatment----"+treatment);
	 	console.log("testedPositive----"+testedPositive);
	 	console.log("termsChecked:::"+ termsChecked);
		if((medicalValue == 'Y' || treatment == 'Y' || testedPositive == 'Y') && termsChecked){
			console.log("disabled");
			$('#<portlet:namespace />finalSubmit').attr('disabled', true);	
		}else if(termsChecked){
			console.log("enabled");
			$('#<portlet:namespace />finalSubmit').attr('disabled', false);
		}else {
			console.log("disbaled");
			$('#<portlet:namespace />finalSubmit').attr('disabled', true);	
		}
}
 
	
// populate nominee name 
function populateNomineeName() {
    var relation = $('#<portlet:namespace/>relation').val();
    var clientName = $('#<portlet:namespace/>clinet-name').val();
    var nomineeNameField = $('#<portlet:namespace/>nominee-name');
    console.log("relation  ---" + relation);
    console.log("clientName  ---" + clientName);
    console.log("nomineeName  ---" + nomineeNameField.val());
    if (relation === 'Himself/Herself') {
        if (clientName) { // Check if clientName is not empty
            nomineeNameField.val(clientName);
            nomineeNameField.addClass("non-editable");
        } else {
            nomineeNameField.val(''); // Clear nomineeName if clientName is empty
            nomineeNameField.addClass("non-editable");
        }
    }
    if (relation != 'Himself/Herself') {
    	 nomineeNameField.removeClass("non-editable");
    }
}

// check relation for client name 
function checkRelation(input) {
        var relation = $('#<portlet:namespace/>relation').val();
        var nomineeNameField = $('#<portlet:namespace/>nominee-name');

        if (relation === 'Himself/Herself') {
            nomineeNameField.val(input.value);
        }
    }



// allow only  20 digits in csb bankaccount number. 
function validateAccountNumber(input) {
    // Remove any non-digit characters
    /* input.value = input.value.replace(/\D/g, '');
    // Ensure the input length does not exceed 20 characters
    if (input.value.length > 20) {
        input.value = input.value.substring(0, 20);
    } */
    
    input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    // Ensure the input length does not exceed 18 characters
    if (input.value.length > 18) {
        input.value = input.value.substring(0, 18);
    }
}


function validateEmployeeID(input){
	 input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    // Ensure the input length does not exceed 20 characters
    if (input.value.length > 10) {
        input.value = input.value.substring(0, 10);
    }
	
}


// allow only 10 digits in employeeId
function validateOnlyDigits(input) {
    // Remove any non-digit characters
    input.value = input.value.replace(/\D/g, '');
    // Ensure the input length does not exceed 10 characters
    if (input.value.length > 10) {
        input.value = input.value.substring(0, 10);
    }
}
// client name,employe name.
function allowOnlyAlphabet(input) {
    // Remove any non-alphabetic characters except spaces
    input.value = input.value.replace(/[^a-zA-Z\s]/g, '');
    
    // Ensure the input length does not exceed 75 characters
    if (input.value.length > 75) {
        input.value = input.value.substring(0, 75);
    }
}
// validatebranchName 
function validateBranchName(input) {
    // Ensure the input length does not exceed 75 characters
    if (input.value.length > 75) {
        input.value = input.value.substring(0, 75);
    }
}

// validatebranch code 
function validateBranchCode(input) {
    // Remove any non-alphanumeric characters
    input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    // Ensure the input length does not exceed 10 characters
    if (input.value.length > 10) {
        input.value = input.value.substring(0, 10);
    }
}

// validatepincode
// allow only 6 digits in pinCode
function validatePincode(input) {
    // Remove any non-digit characters
    input.value = input.value.replace(/\D/g, '');
    // Ensure the input length does not exceed 6 characters
    if (input.value.length > 6) {
        input.value = input.value.substring(0, 6);
    }
}

// NeoApplicaiton number
function validateNeoApplicationNumber(input) {
    // Remove any non-alphanumeric characters
    input.value = input.value.replace(/[^a-zA-Z0-9]/g, '');
    // Ensure the input length does not exceed 20 characters
    if (input.value.length > 20) {
        input.value = input.value.substring(0, 20);
    }
}
//validate Email 
function validateEmailLength(input) {
	  // Remove spaces
    input.value = input.value.replace(/\s/g, '');
    // Ensure the input length does not exceed 50 characters
    if (input.value.length > 50) {
        input.value = input.value.substring(0, 50);
    }
}

//vaildate DOB
function formatDate(input) {
    // Get the raw value and remove all non-numeric characters
    let value = input.value.replace(/\D/g, '');
    let newValue = '';
    for (let i = 0; i < value.length; i++) {
        let char = value.charAt(i);
        if (i === 0) { // First digit of the day
            if (char > '3') {
                continue; // Ignore if the first digit is greater than 3
            }
        } else if (i === 1) { // Second digit of the day
            if (value.charAt(0) === '3' && char > '1') {
                continue; // Ignore if the first digit is 3 and second digit is greater than 1
            }
        } else if (i === 2) { // After the second digit of the day
            if (newValue.length < 3) { // Ensure we add the slash only once
                newValue += '/';
            }
            if (char > '1') {
                continue; // Ignore if the first digit of the month is greater than 1
            }
        } else if (i === 3) { // Second digit of the month
            if (value.charAt(2) === '1' && char > '2') {
                continue; // Ignore if the first digit of the month is 1 and second digit is greater than 2
            }
        } else if (i === 4) { // After the second digit of the month
            if (newValue.length < 6) { // Ensure we add the slash only once
                newValue += '/';
            }
        } else if (i >= 6) { // Year part
            if (newValue.length >= 10) {
                break; // Stop if the formatted value already has 10 characters
            }
        }
        // Append the character to the new value
        newValue += char;
    }
    // Set the formatted value back to the input field
    input.value = newValue;
}
 
function calculateAge(dob) {
    const diffMs = Date.now() - dob.getTime();
    const ageDate = new Date(diffMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}
 
function isAgeWithinRange(dob) {
    const currentDate = new Date();
    const ageDiff = currentDate.getFullYear() - dob.getFullYear();
    if (ageDiff === 55) {
        // Check if the day difference is within 364 days
        const daysDiff = Math.floor((currentDate - new Date(currentDate.getFullYear(), dob.getMonth(), dob.getDate())) / (1000 * 60 * 60 * 24));
        return daysDiff <= 364;
    }
    return true;
}
 
function validateAgeOnSubmit(value) {
    if (!value) return false;
 
    const [day, month, year] = value.split('/').map(Number);
    const dob = new Date(year, month - 1, day);
    const age = calculateAge(dob);
 
    return age >= 18 && (age < 55 || (age === 55 && isAgeWithinRange(dob)));
}
 
// Attach event listener for input event to the age input field
document.getElementById('<portlet:namespace/>age-dob').addEventListener('input', function() {
    formatDate(this);
});

document.addEventListener("DOMContentLoaded", function() {
    console.log("inside picker");
	flatpickr("#<portlet:namespace />age-dob", {
        dateFormat: "d/m/Y",
        allowInput: true
    });
});

</script>