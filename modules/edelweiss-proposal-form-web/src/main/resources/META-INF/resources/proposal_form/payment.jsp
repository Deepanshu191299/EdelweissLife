<div class="card d-md-none">
	<div class="card-header" id="headingOne">
		<!--<h2 class="mb-0 title-tabs">
			<span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span>
			<liferay-ui:message key="label-personal-details" />
		</h2>-->
		<h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed validation-otp-popup" type="button" aria-expanded="false">
			   <p><liferay-ui:message key="label-payment" /></p>
			</button>
		 </h2>
	</div>
</div>
<div class="modal fade" id="pfpaymentModal" tabindex="-1" role="dialog" aria-labelledby="pfpaymentModalLabel" aria-hidden="true" style="display: none;">
  	<div class="modal-dialog modal-dialog-centered" role="document">
    	<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          	<span aria-hidden="true">
						    <svg
						        xmlns="http://www.w3.org/2000/svg" width="15" height="15"
						        viewBox="0 0 15 15">
						        <path fill="#999" fill-rule="nonzero"
						            d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
						    </svg>
						</span>
			        </button>
		      	</div>
			   	<div class="otp-header text-center mb-4">
					<h2 class="main-heading"> <liferay-ui:message key="label-validate-otp" /> </h2>		
					<h3 class="fs18 otp-sent-text"><liferay-ui:message key="label-otp-message" /></h3>			
				</div>
				<aui:form action="" name="paymentFM" method="POST">
					<div class="otp-field label-center">
						<label class="custom-field two"> 
							<aui:input
								cssClass="form-control vnumber" name="otp" label=""
								placeholder="&nbsp;" wrappedField="<%=true%>" minLength="6" maxLength="6" style="text-align: center;">
								<aui:validator name="required"
									errorMessage='please-enter-a-valid-otp' />
								<aui:validator name="maxLength">6</aui:validator>
                                <aui:validator name="minLength">6</aui:validator>
							</aui:input> 
							<span class="placeholder"><liferay-ui:message key="enter-6-digit-otp" /></span>
						</label>
					</div>
					<div class="center-wealth-btn d-flex">
					  	<aui:button cssClass="edto-btn-primary m-auto" type="button" id="pfPaymentSubmit" onClick="verifyOTPForPayment();"
							value="Proceed">
						</aui:button>
					</div>
					<div class="resend-otp fs12 mt-2">
						<span><liferay-ui:message key="label-resend-otp" /></span>
					</div>
					<div id="otp-error" class="success-color mt-2"></div>
				</aui:form>
			</div>
		</div>
	</div>
</div>

<style>
#pfpaymentModal .modal-dialog{
   top:20%
}
.otp-header h2{
   font-size: 22px 
}
.resend-otp{
    text-align: center;
    cursor: pointer;
}
.modal-content{
	width: 600px;
}
.modal-header{
	border: none;
	justify-content: flex-end;
}
.otp-field {
	width: 50%;
    margin: 0 auto;
    text-align: center;
}

.otp-field.label-center .placeholder{
	left: 85px;
}
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
@media screen and (max-width: 767px) {
	#pfpaymentModal {
		padding-right: 0px !important;
	}
	
	.modal-content{
		width: 100%;
	}
}
#pfpaymentModal .modal-dialog {
    top: 0 !important;
    max-width: 640px;
</style>

<aui:script>

$('#pfpaymentModalBtn').click(function(){
	sendOtp();
	$('#<portlet:namespace/>finalFormSubmission').val('N');
})

$('.resend-otp').click(function(){
	sendOtp('Y');
})

function sendOtp(resendOTL = "N"){

showLoader();
const data = {};

if(resendOTL == 'N'){
    $('#<portlet:namespace/>pfPaymentSubmit').prop('disabled', false);
	data['ApplicationNumber'] = "${commonDetails.applicationNumber}";
	data['Name'] = "${commonDetails.personName}";
	data['Resend'] = "N";
	data['Usedfor'] = "${commonDetails.applicationNumber}";
	
	if("${commonDetails.isLaPrSameYn}" == "N"){
	    data['Mobileno'] = "${proposerCommunicationDetails.mobileNumber}";
	    data['EmailAddress'] = "${proposerCommunicationDetails.emailId}";
	}else{
		data['Mobileno'] = "${laPersonalDetails.mobileNumber}";
	    data['EmailAddress'] = "${laPersonalDetails.emailAddress}";
	}
	
}else{

	data['ApplicationNumber'] = "${commonDetails.applicationNumber}";
	data['Mobileno'] = "";
	data['EmailAddress'] = "";
	data['Name'] = "${commonDetails.personName}";
	data['Resend'] = "Y";
	data['Usedfor'] = "${commonDetails.applicationNumber}";

}
	Liferay.Util.fetch('${sendOTPURL}',{
	method: 'POST',
	headers: new Headers({
      "Content-Type": "application/json",
    }),
	body: JSON.stringify(data),
	}).then((response)=> response.json())
	.then((response)=> {
	    hideLoader();
	    if(response?.status){
	        let optMSG = '<liferay-ui:message key="label-otp-message" />';
	        if("${commonDetails.isLaPrSameYn}" == "N" && !${isLaMinor}){
	            optMSG = optMSG.replace("{0}", "${proposerPersonalDetails.mobileNumber}");
	        	optMSG = optMSG.replace("{1}", "${proposerPersonalDetails.emailAddress}");
	        }else{
	       	 	optMSG = optMSG.replace("{0}", "${laPersonalDetails.mobileNumber}");
	       		optMSG = optMSG.replace("{1}", "${laPersonalDetails.emailAddress}");
	        }
	        
	        $('.otp-sent-text').text(optMSG);
	        
	    	$('#pfpaymentModal').modal({backdrop: 'static', keyboard: false}, 'show');
	    	$('#otp-error').html(response.responseData.Result);
	    	$("#otp-error").removeClass("error-color");
	        $("#otp-error").addClass("success-color");
	    }else{
	      $('#otp-error').html('<liferay-ui:message key="label-unable-to-send-otp-please-try-after-some-time" />');
	      $("#otp-error").addClass("error-color");
	      $("#otp-error").removeClass("success-color");
	    }
	    
	}).catch((error)=>{
	  console.log('error...',error);
	  hideLoader();
	})

}

async function <portlet:namespace />verifyOTP() {
 
    showLoader();
    const data = {};

	data['ApplicationNumber'] = "${commonDetails.applicationNumber}";
	
	if("${commonDetails.isLaPrSameYn}" == "N"){
	    data['Mobileno'] = "${proposerCommunicationDetails.mobileNumber}";
	}else{
		data['Mobileno'] = "${laPersonalDetails.mobileNumber}";
	}
	
    
	let otp = $('#<portlet:namespace/>otp').val();
	
	if(!isNaN(otp)){
	   data['OTPCode'] = $('#<portlet:namespace/>otp').val();
	}
	
	const response = await Liferay.Util.fetch('${verifyOTPURL}',{
							method: 'POST',
							headers: new Headers({
						      "Content-Type": "application/json",
						    }),
							body: JSON.stringify(data),
							});
	hideLoader();						
	const responseJSON = await response.json();
	return 	responseJSON;					

}

function validateOTP(response){

   if(!response?.status && response?.errors){
      $('#otp-error').html(response.errors[0]);
      $("#otp-error").addClass("error-color");
      $("#otp-error").removeClass("success-color");
   }else if(!response?.status){
     $('#otp-error').html('<liferay-ui:message key="label-please-enter-a-valid-otp" />');
     $("#otp-error").addClass("error-color");
     $("#otp-error").removeClass("success-color");
   }

}


async function verifyOTPForPayment(){

    const formValidator = Liferay.Form.get('<portlet:namespace />paymentFM').formValidator;

    formValidator.validate();
    
    if (formValidator.hasErrors()) {
				return;
	 }
    
	const response = await <portlet:namespace />verifyOTP();
	console.log('response......',response);
	if(response?.status){
	   let finalFormSubmission = $('#<portlet:namespace/>finalFormSubmission').val();
	   
	   if(finalFormSubmission === 'N'){
	   		OnAfterPFSubmission();
	      <portlet:namespace />createPaymentLink();
	   }else{
	      savePFCommit();
	   }
	   
	}else{
	   validateOTP(response);	
	}
	
}

function <portlet:namespace />createPaymentLink(){

     const data = {};
     showLoader();

	data['ApplicationNumber'] = "${commonDetails.applicationNumber}";
	data['CustomerName'] = "${commonDetails.personName}";
	data['PolicyNumber'] = "${commonDetails.policyNo}";
	
	if("${commonDetails.isLaPrSameYn}" == "N"){
	    data['CustomerMobile'] = "${proposerCommunicationDetails.mobileNumber}";

	}else{
		data['CustomerMobile'] = "${laPersonalDetails.mobileNumber}";
	}
	
    Liferay.Util.fetch('${createPaymentLinkURL}',{
	method: 'POST',
	headers: new Headers({
      "Content-Type": "application/json",
    }),
	body: JSON.stringify(data),
	}).then((response)=> response.json())
	.then((response)=> {
	    
	   if (response.status && response.responseData) {
              window.location.href = response.responseData;
              $('#<portlet:namespace/>pfPaymentSubmit').prop('disabled', true);
      }else{
	      $('#otp-error').html('<liferay-ui:message key="label-unable-to-generate-payment-link-please-try-after-some-time" />');
	      $("#otp-error").addClass("error-color");
	      $("#otp-error").removeClass("success-color");
	    }
	   hideLoader(); 
	}).catch((error)=>{
	  console.log('error...',error);
	  hideLoader();
	})

}


</aui:script>
