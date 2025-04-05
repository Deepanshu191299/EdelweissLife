<%@ include file="../init.jsp"%>


<%
boolean isHomeRenewal = false;
if(Validator.isNotNull(renderRequest.getAttribute("isHomeRenewal"))){
	isHomeRenewal = (boolean)renderRequest.getAttribute("isHomeRenewal");	
}
/* String createDate=(new java.util.Date()).toString(); */
String createDate = (String) renderRequest.getAttribute("currTime");
%>

<liferay-portlet:actionURL var="submitSoftLoginDetailsFormURL"
	name="<%=SoftLoginFilterPortletKeys.SUBMIT_SOFT_LOGIN_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
	<%
		if(isHomeRenewal){
	%>
	<div class="invest-form-wrapper renewal-back grow-money">
		<div class="container">
			<div class="card banner1-text">
				<div class="card-body">
					<div class="invest-text">
                         <h2 class="fontbold fs28"><liferay-ui:message key="label-renew-your-policy" /></h2>
                         <h4 class="fontsemi fs16 pt-2 pb-2"><liferay-ui:message key="label-continue-reaping-your-policy-benefits" /></h4>
                     </div>
                     
					<aui:form name="soft-login-form"
						action="<%=submitSoftLoginDetailsFormURL%>" method="post"
						enctype="multipart/form-data" onSubmit="event.preventDefault();">
						<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
						<aui:input type="hidden" value="${submitSoftLoginDetailsFormURL}" name="submitSoftLoginDetailsFormURL" />
						<div class="application_step" id='<portlet:namespace/>soft_login_step'>
							<h5 class="fs18 pb-2"><liferay-ui:message key="label-share-your-details-to" /> <b><liferay-ui:message key="label-start-now" /></b></h5>
							<div class="row">
								<div class="col-md-6 col-12">
									<aui:input name="isHomeRenewal" type="hidden" value="<%=isHomeRenewal%>"></aui:input>
									<aui:input name="otpResend" type="hidden" value="false"></aui:input>
									<aui:input name="inputType" type="hidden" value=""></aui:input>
									<aui:input name="createDate" id="createDate-login" type="hidden" value="<%=createDate%>"></aui:input>
									<aui:input name="inputValue" label="" placeholder="label-policy-number-email-mobile" 
										cssClass="validate" type="text" value="" maxLength="30" autocomplete="off">
										<aui:validator name="required" errorMessage='error-label-input' />
										<aui:validator name="maxLength" errorMessage="error-label-max-length-thirty">30</aui:validator>
										<aui:validator name="custom" errorMessage="error-label-invalid-input">
						                    function(val, fieldNode, ruleValue) {
							                    
							                    var inputType;
							                    var inputValid = false;
							                    
							                    var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
							                    var mobileRegex = new RegExp("^[0-9]{10}$");
							                    var emailRegex = new RegExp("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
							                    
							                    if(policyRegex.test(val)){
							                    	inputType = "policyNumber";
							                    	inputValid = true;
							                    }else if(mobileRegex.test(val)){
							                    	inputType = "phoneNumber";
							                    	inputValid = true;
							                    }else if(emailRegex.test(val)){
							                    	inputType = "emailId";
							                    	inputValid = true;
							                    }else{
							                    	inputValid = false;
							                    }
							                    updateInputType(inputType);
							                    return inputValid;
						                    }
						                </aui:validator>
									</aui:input>
								</div>			
								<div class="col-md-4 col-12">
									<aui:input name="dateOfBirth" label="" placeholder="label-dob"
										cssClass="validate vdate" type="text" value="" maxLength="10" autocomplete="off">
										<aui:validator name="required" errorMessage='error-label-date-of-birth' />
										<aui:validator name="custom"
																errorMessage='error-label-date-invalid'>
																				function(val, fieldNode, ruleValue) {
														
																				var parts = val.split("/");
														
																				if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																					return false;
																				}else{
																				
																					let day = parseInt(parts[0]);
																	        		let month = parseInt(parts[1]);
																	        		let year = parseInt(parts[2]);
																				
																					// Create a list of days of a month      
																			        let ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
																			        if (month == 1 || month > 2) {
																			            if (day > ListofDays[month - 1]) {  
																			                return false;
																			            }
																			        } else if (month == 2) {
																			            let leapYear = false;
																			            if ((!(year % 4) && year % 100) || !(year % 400)) leapYear = true;
																			            if ((leapYear == false) && (day >= 29)) {
																			                return false;
																			            }
																			            else
																			                if ((leapYear == true) && (day > 29)) {
																			                    return false;
																			                }
																			        }
																			        return true;
																				}
																			}
																			</aui:validator>
										<aui:validator name="custom" errorMessage="error-label-date">
					                        function(val, fieldNode, ruleValue) {
					
					                        var parts = val.split("/");
					
					                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
					                        	return false;
					                        }else{
					                        	if(parts[0] > 31){
					                        		return false;
					                        	}
					                        	if(parts[1] > 12){
					                        		return false;
					                        	}
					                        	if(parts[2].length!=4){
					                        		return false;
					                        	}
					                        	else if(parts[0].length!=2 && parts[1].length!=2){
					                        		return false;
					                        	}
					                        	return true;
				                        	}
				                        }
					                    </aui:validator>
					                    <aui:validator name="custom" errorMessage="error-label-date-must-be-in-past">
					                        function(val, fieldNode, ruleValue) {
					
					                        var parts = val.split("/");
					                        var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
					
					                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
					                        	return false;
					                        }else{
					                        	var now = new Date();
											   	if(selectedDate > now) {
											    	return false;
											   	}
					                        	return true;
					                        	}
					                        }
					                    </aui:validator>
									</aui:input>
								</div>
							</div>
							<div class="row otpWrap" style='display: none;'>
								<div class="col-md-4 col-12">
									<aui:input name="otpId" type="hidden" value="" />
									<aui:input name="otp" placeholder="label-otp" 
										label="" cssClass="vnumber" type="text" value="" maxLength="6" minLength="6">
										<aui:validator name="number" />
										<aui:validator name="required" errorMessage='error-label-otp' />
										<aui:validator name="maxLength" errorMessage="error-label-max-length-six">6</aui:validator>
										<aui:validator name="minLength" errorMessage="error-label-min-length-six">6</aui:validator>
									</aui:input>
								</div>
								<div class="col-md-4 col-12">
								<aui:button
									cssClass="btn btn-secondary edto-btn-primary"
									id="resend" value="label-resend"
									onClick="resentOTP('soft_login_step', true, 'soft-login-form');"></aui:button>
								</div>
							</div>
							<aui:button-row cssClass="multi-btn">
								<aui:button
									cssClass="edto-btn-primary"
									id="confirmSubmit" value="label-continue"
									onClick="validateSoftLoginForm('soft_login_step', true, 'soft-login-form');"></aui:button>
							</aui:button-row>
						</div>
					</aui:form>
				</div>
			</div>
		</div>
	</div>
	<%		
		}else{
	%>
		<aui:form name="soft-login-form"
		action="<%=submitSoftLoginDetailsFormURL%>" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
		<input type="hidden" id="portalURL" value="<%=themeDisplay.getPortalURL()%>" />
		<input type="hidden" id="homeURL" value="<%=themeDisplay.getURLHome()%>" />
		<aui:input type="hidden" value="${submitSoftLoginDetailsFormURL}" name="submitSoftLoginDetailsFormURL" />
		<div class="application_step" id='<portlet:namespace/>soft_login_step'>
			<div class="row">
				<div class="col-md-6 col-12">
					<aui:input name="otpResend" type="hidden" value="false"></aui:input>
					<aui:input name="inputType" type="hidden" value=""></aui:input>
					<aui:input name="createDate" id="createDate-login" type="hidden" value="<%=createDate%>"></aui:input>
					
					<label class="custom-field two mb-0 pb-0">
					<aui:input name="inputValue" label="" placeholder="label-policy-number-email-mobile" 
						cssClass="validate" type="text" value="" maxLength="30" minLength="0" autocomplete="off">
						<aui:validator name="required" errorMessage='error-label-input' />
						<aui:validator name="maxLength" errorMessage="error-label-max-length-thirty">30</aui:validator>
						<aui:validator name="minLength" errorMessage="error-label-min-length-zero">0</aui:validator>
						<aui:validator name="custom" errorMessage="error-label-invalid-input">
		                    function(val, fieldNode, ruleValue) {
			                    
			                    var inputType;
			                    var inputValid = false;
			                    
			                    var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
			                    var mobileRegex = new RegExp("^[0-9]{10}$");
			                    var emailRegex = new RegExp("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
			                    
			                    if(policyRegex.test(val)){
			                    	inputType = "policyNumber";
			                    	inputValid = true;
			                    }else if(mobileRegex.test(val)){
			                    	inputType = "phoneNumber";
			                    	inputValid = true;
			                    }else if(emailRegex.test(val)){
			                    	inputType = "emailId";
			                    	inputValid = true;
			                    }else{
			                    	inputValid = false;
			                    }
			                    updateInputType(inputType);
			                    return inputValid;
		                    }
		                </aui:validator>
					</aui:input>
					</label>
				</div>			
				<div class="col-md-6 col-12">
				<label class="custom-field two mb-0 pb-0">
					<aui:input name="dateOfBirth" label="" placeholder="label-dob"
						cssClass="validate vdate" type="text" value="" maxLength="10" autocomplete="off">
						<aui:validator name="required" errorMessage='error-label-date-of-birth' />
						<aui:validator name="custom"
												errorMessage='error-label-date-invalid'>
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																
																	let day = parseInt(parts[0]);
													        		let month = parseInt(parts[1]);
													        		let year = parseInt(parts[2]);
																
																	// Create a list of days of a month      
															        let ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
															        if (month == 1 || month > 2) {
															            if (day > ListofDays[month - 1]) {  
															                return false;
															            }
															        } else if (month == 2) {
															            let leapYear = false;
															            if ((!(year % 4) && year % 100) || !(year % 400)) leapYear = true;
															            if ((leapYear == false) && (day >= 29)) {
															                return false;
															            }
															            else
															                if ((leapYear == true) && (day > 29)) {
															                    return false;
															                }
															        }
															        return true;
																}
															}
															</aui:validator>
						<aui:validator name="custom" errorMessage="error-label-date">
	                        function(val, fieldNode, ruleValue) {
	
	                        var parts = val.split("/");
	
	                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
	                        	return false;
	                        }else{
	                        	if(parts[0] > 31){
	                        		return false;
	                        	}
	                        	if(parts[1] > 12){
	                        		return false;
	                        	}
	                        	if(parts[2].length!=4){
	                        		return false;
	                        	}
	                        	else if(parts[0].length!=2 && parts[1].length!=2){
	                        		return false;
	                        	}
	                        	return true;
                        	}
                        }
	                    </aui:validator>
	                    <aui:validator name="custom" errorMessage="error-label-date-must-be-in-past">
	                        function(val, fieldNode, ruleValue) {
	
	                        var parts = val.split("/");
	                        var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
	
	                        if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
	                        	return false;
	                        }else{
	                        	var now = new Date();
							   	if(selectedDate > now) {
							    	return false;
							   	}
	                        	return true;
	                        	}
	                        }
	                    </aui:validator>
					</aui:input>
					</label>
				</div>
			</div>
			<div class="row otpWrap" style='display: none;'>
				<div class="col-md-6 col-12">
					<aui:input name="otpId" type="hidden" value="" />
					<label class="custom-field two pt-0">
						<aui:input name="otp" placeholder="label-otp" 
							label="" cssClass="vnumber" type="text" value="" maxLength="6" minLength="0">
							<aui:validator name="number" />
							<aui:validator name="required" errorMessage='error-label-otp' />
							<aui:validator name="maxLength" errorMessage="error-label-max-length-six">6</aui:validator>
							<aui:validator name="minLength" errorMessage="error-label-min-length-zero">0</aui:validator>
						</aui:input>
						
						<button class="btn btn-sm resendOtp-btn" id="resend" onclick="resentOTP('soft_login_step', true, 'soft-login-form');"><i class="fas fa-redo"></i> <liferay-ui:message key="label-resend" /></button>
					</label>
				</div>
			</div>
			<aui:button-row>
				<aui:button
					cssClass="edto-btn-primary"
					id="confirmSubmit" value="label-submit"
					onClick="validateSoftLoginForm('soft_login_step', true, 'soft-login-form');"></aui:button>
			</aui:button-row>
		</div>
	</aui:form>
	<%		
		}
	%>
<!-- Modal -->
<div class="modal wealth-modal" tabindex="-1" role="dialog"
	id=loginBackdrop>
	<div class="modal-dialog role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close" data-dismiss="modal"
						aria-label="Close" onClick="closeLoginModal(false);">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<span id="modal_error" class="error"></span>

				<div class="text-center">
					<h5 id="responseMessage"></h5>
				</div>
				<div class="text-center mt-3">
					<button type="button" id="okayBtn" class="edto-btn-primary" onClick="closeLoginModal(false);">
						<liferay-ui:message key="label-done" />
					</button>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Image loader -->
<div id='<portlet:namespace/>login-loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<!-- Image loader -->

<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="multipleLoginBackdrop"
	aria-labelledby="multipleLoginBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="multipleLoginCloseBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeMultipleLoginModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="multipleLoginMessage" class="fs22 fontbold w-100" style="color : #3c3c3c"></h3>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="<%=request.getContextPath()%>/js/soft-login.js?v=1.1"></script>
<script>
function updateInputType(inputType){
	$("#<portlet:namespace/>inputType").val(inputType);
}

window.onload = function() {
    if (localStorage.getItem('multipleLoginsDetected') === 'true') {
    	console.log("inside this::::");
    	$('#multipleLoginBackdrop').modal('show');
    	$("#multipleLoginMessage").html("You have been signed out from the application as multiple logins<br>have been detected from the same user.");
    }
}
function closeMultipleLoginModal() {
	$('#multipleLoginBackdrop').modal('hide');
}

//Hide title attribute text on hover
$('.application_step input, .application_step select').hover(function(e){
    $(this).attr('title', '');
});
</script>