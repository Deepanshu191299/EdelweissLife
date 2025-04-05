<%@ include file="/init.jsp"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@page import="edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys"%>



<portlet:resourceURL var="verifyOTPURL" id="<%= EdelweissCsbIntegrationWebPortletKeys.VALIDATE_OTP_CSB_INTEGRATION_MVC_RESOURCE_COMMAND %>">
	<portlet:param name="action" value="verifyOTP"/>
</portlet:resourceURL>

<portlet:resourceURL var="reSendOTPURL" id="<%= EdelweissCsbIntegrationWebPortletKeys.VALIDATE_OTP_CSB_INTEGRATION_MVC_RESOURCE_COMMAND %>">
	<portlet:param name="action" value="reSendOtp"/>
	<portlet:param name="ApplicationNumber" value="${appId}"/>
	<portlet:param name="Mobileno" value="${getCsbResponse.mobileNo}"/>
	<portlet:param name="clientName" value="${getCsbResponse.clientName}"/>
	<portlet:param name="emailAddress" value="${getCsbResponse.emailId}"/>
</portlet:resourceURL>

<portlet:resourceURL var="createTransactionURL"  id="<%= EdelweissCsbIntegrationWebPortletKeys.VALIDATE_OTP_CSB_INTEGRATION_MVC_RESOURCE_COMMAND %>">
	<portlet:param name="action" value="createPayment"/>
</portlet:resourceURL>



<aui:form name="paymentFM" action="" method="post">
	<div class="container">
		<div class="card csb-card">
		<aui:fieldset>
			<div class="row text-left mt-3 csb-header" style="">
				<div class="col-md-10 heading display-1">
					<h5>
						<strong>Section A - CSB Bank Details</strong>
					</h5>
				</div>
			</div>
			<aui:field-wrapper>
				<aui:row cssClass="csb-fields-space">
					<aui:col width="30">
						<aui:input name="csbAccountNo" type="text" id="csb-account-no"
							label="CSB Bank Account / Reference Number" value="${getCsbResponse.cSBAccountNo}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input> 
					</aui:col>

					<aui:col width="30" cssClass="csb-fields-space-column">
						<aui:input name="csbEmployeeId" type="text" id="csb-employee-id"
							label="CSB Bank Employee ID" value="${getCsbResponse.cSBEmployeeId}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col>
					<aui:col width="30" cssClass="csb-fields-space-column">
						<aui:input name="csbEmployeeName" type="text"
							id="csb-employee-name" label="CSB Bank Employee Name" value="${getCsbResponse.cSBEmployeeName}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
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
							label="Client Name" value="${getCsbResponse.clientName}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col>
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="dob" type="text" id="age-dob" label="DOB" value="${getCsbResponse.clientDOB}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
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
								checked="${getCsbResponse.gender == 'Male' ? true : false}" disabled="true" >
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>gender_male">Male</label>

							<aui:input required="true" cssClass="validate setOpen" label=""
								name="gender" type="radio" value="Female" id="gender_female"
								checked="${getCsbResponse.gender == 'Female'? true : false}" disabled="true">
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
								name="occupation" type="radio" id="occupation_yes"
								value="Salaried" checked="${getCsbResponse.occupation == 'Salaried'? true : false}" disabled="true">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>occupation_yes">Salaried</label>

							<aui:input required="true" cssClass="validate setOpen" label=""
								name="occupation" type="radio" value="Non-Salaried" id="occupation_no"
								checked="${getCsbResponse.occupation == 'Non-Salaried'? true : false}" disabled="true">
								<aui:validator name="required"
									errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>occupation_no">Non-Salaried</label>
						</div>
					</aui:col>
				</aui:row>
				
				<aui:row cssClass="csb-fields-space">
				<!-- Mobile Number Start -->
				<aui:col width="50" >
						<aui:input name="mobileNumber" type="text" id="mobile-number" value="${getCsbResponse.mobileNo}" disabled="true"
							label="Mobile Number(Registered with Bank)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col>
				<!--  Mobile Number End -->
				<!--  Email Id Start -->
				<aui:col width="50" cssClass="csb-fields-space-column" >
						<aui:input name="emailId" type="text" id="email-Id" value="${getCsbResponse.emailId}" disabled="true"
							label="Email ID(Registered with Bank)">
							<aui:validator name="required" errorMessage="this-field-is-required" />
							 <aui:validator name="email"/>
						</aui:input>
					</aui:col>
				<!--  Email Id end -->
				
				</aui:row>
				
				<aui:row cssClass="csb-fields-space">
				
				<aui:col width="50" >
						<aui:input name="customerId" type="text" id="customer-id"
							label="Customer ID" value="${getCsbResponse.customerAccountNo}" disabled="true">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:input>
				</aui:col>
				<!--  Branch Code Start -->
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="branchCode" type="text" id="branch-code"
							label="Branch Code" value="${getCsbResponse.branchCode}" disabled="true">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col>
				<!--  Branch Code End -->	
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!-- Branch Name Start -->
					<aui:col width="50">
						<aui:input name="branchName" type="text" id="branch-name"
							label="Branch Name" value="${getCsbResponse.branchName}" disabled="true">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:input>
					</aui:col>
				<!-- Branch Name end  -->	
				<!--  PinCode Start -->
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="pinCode" type="text" id="pin-code" value="${getCsbResponse.pinCode}" disabled="true"
							label="Pin Code">
							<aui:validator name="required"  errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col>
				<!-- PinCode End -->	
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!-- Neo Application No Start -->
					<aui:col width="50">
						<aui:input name="neoApplicatioNumber" type="text" value="${getCsbResponse.neoApplicationNo}" disabled="true"
							id="neo-application-number" label="Neo Application No.">
						</aui:input>
					</aui:col>
				<!--  Neo Application No End  -->	
					<aui:col width="50" cssClass="csb-fields-space-column">
						<aui:input name="nomineeName" type="text" id="nominee-name" value="${getCsbResponse.nomineeName}" disabled="true"
							label="Nominee Name">
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:input>
					</aui:col>
					
				</aui:row>
				<aui:row cssClass="csb-fields-space">
				<!-- Relation Start -->
					<aui:col width="50" >
						<aui:input name="relation" type="text" id="relation" value="${getCsbResponse.relation}" disabled="true" label="Relation">
						<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:input>
						<%-- <aui:select name="relation" type="text" id="relation"
							label="Relation"  value="${getCsbResponse.relation}" disabled="true">
							<aui:option value="">Select-Relation</aui:option>
							<aui:option value="father">Father</aui:option>
							<aui:option value="mother">Mother</aui:option>
							<aui:option value="spouse">Spouse</aui:option>
							<aui:option value="brother">Brother</aui:option>
							<aui:option value="sister">Sister</aui:option>
							<aui:option value="son">Son</aui:option>
							<aui:option value="daughter">Daughter</aui:option>
							<aui:option value="grandfather">Grandfather</aui:option>
							<aui:option value="grandmother">Grandmother</aui:option>
							<aui:option value="grandson">Grand Son</aui:option>
							<aui:option value="granddaughter">Grand Daughter</aui:option>
							<aui:option value="employer">Employer</aui:option>
							<aui:option value="guardian">Guardian</aui:option>
							<aui:option value="husband">Husband</aui:option>
							<aui:option value="nephew">Nephew</aui:option>
							<aui:option value="other">Other</aui:option>
							<aui:option value="himself/herself">Himself/Herself</aui:option>
							<aui:option value="niece">Niece</aui:option>
							<aui:validator name="required"  errorMessage="this-field-is-required"/>
						</aui:select> --%>
					</aui:col>
				<!-- Relation End -->	
					<%-- <aui:col width="50" >
						<aui:input name="mobileNumber" type="text" id="mobile-number" value="${getCsbResponse.mobileNo}" disabled="true"
							label="Mobile Number">
							<aui:validator name="required" errorMessage="this-field-is-required" />
						</aui:input>
					</aui:col> --%>
				</aui:row>
			<%-- 	<aui:row class="">
				
					<aui:col width="50" cssClass="csb-fields-space-column" >
						<aui:input name="emailId" type="text" id="email-Id" value="${getCsbResponse.emailId}" disabled="true"
							label="Email ID">
							<aui:validator name="required" errorMessage="this-field-is-required" />
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
										id="premiumAmount_5L"  checked="${getCsbResponse.riskCoverOpted == '5'? true : false}"  disabled="true">
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
									id="premiumAmount_3L" checked="${getCsbResponse.riskCoverOpted == '3'? true : false}"  disabled="true">
									<aui:validator name="required"
										errorMessage="this-field-is-required"></aui:validator>
								</aui:input></b>
								<%-- <label for="<portlet:namespace/>premiumAmount_3L">3L</label> --%>
							</div> 
						</div>
					</aui:col>	
				</aui:row>
				
				<%-- <aui:row cssClass="question">
					<aui:col width="20">
						<label for="premiumAmount">Risk Cover Opted<span class="opted-astrik"> *</span></label>
					</aui:col>	
					<aui:col width="20">
						<div class="radio_container">
							<aui:input required="true" cssClass="validate setOpen" label="" name="riskcovered" type="radio" value="5"
				                id="premiumAmount_5L" checked="${getCsbResponse.riskCoverOpted == '5'? true : false}" >
				                <aui:validator name="required"  errorMessage="this-field-is-required"></aui:validator>
            				</aui:input>
							<label for="<portlet:namespace/>premiumAmount_5L">5L</label>
						 </div> 
					</aui:col>		
					 <aui:col width="20">	
						<div class="radio_container">
							<aui:input required="true" cssClass="validate setOpen" label="" name="riskcovered" type="radio" value="3"
				                id="premiumAmount_3L" checked="${getCsbResponse.riskCoverOpted == '3'? true : false}" disabled="true">
				                <aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
            				</aui:input>
							<label for="<portlet:namespace/>premiumAmount_3L">3L</label>
						</div>
					</aui:col>
				</aui:row> --%>
				<aui:row>
					<aui:col width="20">
						<label>Premium to be Paid</label>
					</aui:col>
	    			
	    			 <aui:col width="20">
        				<label id="premiumAmountLabel">Rs.2349/-</label>
        				<aui:input name="premiumAmount" type="hidden" value="2349" label="" id="premiumAmount_5L_input"/>
    				</aui:col>
				    <aui:col width="20">
				        <label>Rs.1409/-</label>
				        <aui:input name="premiumAmount" type="hidden" value="1409" label="" id="premiumAmount_3L_input"/>
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
							name="decease" type="radio" value="Y" id="decease_yes"
							checked="${getCsbResponse.medication == 'Y'? true : false}" disabled="true">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>decease_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="decease" type="radio" value="N" id="decease_no" 
							checked="${getCsbResponse.medication == 'N'? true : false}" disabled="true">
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
							checked="${getCsbResponse.surgery == 'Y'? true : false}" disabled="true">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>treatment_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="treatment" type="radio" value="N" id="treatment_no"
							checked="${getCsbResponse.surgery == 'N'? true : false}" disabled="true">
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
							id="testedPositive_yes" checked="${getCsbResponse.covid == 'Y'? true : false}" disabled="true">
				<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
			</aui:input>
			<label for="<portlet:namespace/>testedPositive_yes">Yes</label>
			
			<aui:input required="true" cssClass="validate setOpen" label=""
							name="testedPositive" type="radio" value="N"
							id="testedPositive_no" checked="${getCsbResponse.covid == 'N'? true : false}" disabled="true">
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
							<strong>Section E - <aui:input type="checkbox" name="termaCondition" label="" checked="true"></aui:input>
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
    	    <label style="float:left;">Application Number : ${appId}</label>
            <input  type="text" required="true" id="otpVerify" name="otp" placeholder="Enter OTP" maxLength="6" minLength="6" oninput="validatePincode(this)"></input>
	        <aui:button class="edto-btn-primary m-auto"  type="button" id="validateOtp" value="Validate OTP" ></aui:button> 
	        
	        <div class="resend-otp fs12 mt-2" id="disabled-resend-otp">
				<button type="button" class="btn btn-link" id="reSendOtp" onClick="resendOtpRemoveErrorMessage()">Resend OTP</button>
			</div>
			<div id="otp-error" class="mt-2"></div>
			<div id="otp-success" class="mt-2"></div>
       </div>
    
  </aui:fieldset> 
 </div>
  </div>
</aui:form> 

<script>
$(document).ready(function() {
    // Initially disable the proceed button
	disableEnableResendOtp(); 
});
	
var verifyOTPURL = "${verifyOTPURL}";
var reSendOTPURL = "${reSendOTPURL}";
var createTransactionURL = "${createTransactionURL}";

$("#<portlet:namespace/>validateOtp").click(function(){
	console.log("validateOtp clicked");
	showLoader();
	var validateOtpResponseData = $.ajax({
		  type: "POST",
		  url: verifyOTPURL,
		  data: {
			  <portlet:namespace/>ApplicationNumber: "${appId}",
			  <portlet:namespace/>Mobileno: "${getCsbResponse.mobileNo}",
			  <portlet:namespace/>OTPCode: $('#otpVerify').val()
		  },
		  dataType: 'json'
	}).then((data)=> {
		hideLoader();
		console.log('response...', JSON.stringify(data));
		var response = data;
		//var response = JSON.stringify(data);
		console.log("response from API::" + response);
		console.log("response from API::" + response?.status);
		if(response?.status){
			console.log('response true');
			removeValidateOtpError();
			createPaymentLink();
		}else{
			console.log("response false");
			ValidateOtpError(response);
		}
	}).catch((error)=>{
		hideLoader();
	  console.error('error...',error);
	});
});

$("#reSendOtp").click(function(){
	console.log("reSendOtp clicked");
	showLoader();
	var validateOtpResponseData = $.ajax({
		  type: "POST",
		  url: reSendOTPURL,
		  dataType: 'json',
		  data: {
			  <portlet:namespace/>currentPreURL:location.href
		  }
	}).then((data)=> {
		hideLoader();
		console.log('response...', JSON.stringify(data));
	   
		var response = JSON.stringify(data);
		if(response.responseData?.status){
			console.log('response true');
			removeValidateOtpError();
		}else{
			console.log("response false");
			ValidateOtpError(response);
		}
	}).catch((error)=>{
		hideLoader();
	  console.error('error...',error);
	});
});

function ValidateOtpError(response){
	console.log("check ValidateOtpError:::: ");
	if(!response?.status && response?.errors){
		console.log("check ValidateOtpError:::: + iffffffffffff");
	      $('#otp-error').html(response.errors[0]);
	      $("#otp-error").addClass("error-color");
	      $("#otp-error").removeClass("success-color");
   }else if(!response?.status){
	   console.log("check ValidateOtpError:::: + elsesssssss");
	     $('#otp-error').html(response.errors[0]);
	     $("#otp-error").addClass("error-color");
	     $("#otp-error").removeClass("success-color");
   }
}

function removeValidateOtpError(){
	$('#otp-error').html('');
    $("#otp-error").hide();
}

function createPaymentLink() {
	console.log("inside the createPaymentLink::");
	showLoader();
	var validateOtpResponseData = $.ajax({
		  type: "POST",
		  url: createTransactionURL,
		  data: {
			  <portlet:namespace/>Source: "website",
			  <portlet:namespace/>Type: "CSBLANDING",
			  <portlet:namespace/>ApplicationNumber: "${appId}",
			  <portlet:namespace/>PolicyNumber: "${appId}",
			  <portlet:namespace/>CustomerName: "${getCsbResponse.clientName}",
			  <portlet:namespace/>CustomerEmail: "${getCsbResponse.emailId}",
			  <portlet:namespace/>CustomerMobile: "${getCsbResponse.mobileNo}",
			  <portlet:namespace/>Amount: ${getCsbResponse.premium}+".00",
			  <portlet:namespace/>Gateway: "BILLDESKSDK",
			  <portlet:namespace/>Payment_Term: 1,
			  <portlet:namespace/>PaymentMode: "Single",
			  <portlet:namespace/>Payment_Description: "",
			  <portlet:namespace/>ShowInfoPage: "N",
			  <portlet:namespace/>siToBeRegistered: false,
			  <portlet:namespace/>RecurringAmount: 0
			},
			dataType: 'json'
			}).then((data)=> {
				hideLoader();
				console.log('response...', JSON.stringify(data));
				var response = data;
				
				if(response?.status){
					console.log("payment done:::", response);
					removeValidateOtpError();
					window.location.href = response.responseData;
		        } else {
		            console.log("payment error:::");
		            $('#otp-error').html('<liferay-ui:message key="label-unable-to-generate-payment-link-please-try-after-some-time" />');
		            $("#otp-error").addClass("error-color").removeClass("success-color");
		        }
			}).catch((error)=>{
				hideLoader();
			  console.error('error...',error);
			});
}

function validatePincode(input) {
    // Remove any non-digit characters
    input.value = input.value.replace(/\D/g, '');
    // Ensure the input length does not exceed 6 characters
    if (input.value.length > 6) {
        input.value = input.value.substring(0, 6);
    }
}

function resendOtpRemoveErrorMessage() {
    // Hide the error message
    document.getElementById("otp-error").style.display = "none";
 
    // Clear the input field
    document.getElementById("otpVerify").value = "";
 
    // Show the success message
    let successMessage = document.getElementById("otp-success");
    successMessage.innerHTML = "OTP resend successfully";
    successMessage.style.display = "block";
    // call disableenableresendOtp method
    disableEnableResendOtp();
    $("#otp-success").addClass("success-color");
    // Optionally, hide the success message after a few seconds
    setTimeout(function() {
        successMessage.style.display = "none";
    }, 30000); // Hide after 30 seconds
   
}
function disableEnableResendOtp(){
	 // Disable the Resend OTP link
    $('#reSendOtp').attr('disabled', true);
    // Enable the link after 30 seconds
     setTimeout(function() {
    	   $('#reSendOtp').removeAttr('disabled');
    }, 30000);
}
</script>


<style>

       
      
#otp-error {
	text-align: center;
    width: 100%;
    font-size: 12px;
}
.success-color {
    color: green;
}
.error-color {
    color: red;
    }
    
.resend-otp{
    text-align: center;
    cursor: pointer;
}
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

</style>
