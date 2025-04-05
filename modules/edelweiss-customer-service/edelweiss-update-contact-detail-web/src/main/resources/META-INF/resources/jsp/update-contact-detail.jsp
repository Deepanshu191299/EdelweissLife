<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitUpdateContactDetailFormURL"
	name="<%=UpdateContactDetailPortletKeys.SUBMIT_UPDATE_CONTACT_DETAIL_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<aui:form name="update-contact-detail-form"
	action="<%=submitUpdateContactDetailFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="updatePortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitUpdateContactDetailFormURL}"
		name="submitUpdateContactDetailFormURL" />
	<div class="application_step"
		id='<portlet:namespace/>update_contact_detail_step'>
		<div class="wealth-form-box">
			<h2 class="fontbold fs28 pb-3"><liferay-ui:message key="label-update-contact-detail" /></h2>
			<p class="fontregular fs18 txtLightBlack">
				<liferay-ui:message key="label-update-contact-detail-description" />
			</p>

			<div class="row mt-4">
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="fullName" label="" placeholder="&nbsp;"
							wrappedField="<%=true%>" cssClass="validate valpha">
							<aui:validator name="required" errorMessage='error-label-name' />
							<aui:validator name="custom"
								errorMessage="error-label-full-name">
						function(val, fieldNode, ruleValue) {
						var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
						return regex.test(val);
						}
					</aui:validator>
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-full-name" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="policyNumber" label="" placeholder="&nbsp;"
							wrappedField="<%=true%>" cssClass="validate document-validate valphanum"
							type="text" value="" maxLength="10" minLength="10">
							<aui:validator name="alphanum" />
							<aui:validator name="required"
								errorMessage='error-label-policy-number' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-ten">10</aui:validator>
							<aui:validator name="minLength"
								errorMessage="error-label-min-length-ten">10</aui:validator>
							<aui:validator name="custom"
								errorMessage="error-label-invalid-policy-number">
                    function(val, fieldNode, ruleValue) {
	                    
	                    var inputType;
	                    var inputValid = false;
	                    
	                    var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
	                    
	                    if(policyRegex.test(val)){
	                    	inputType = "policyNumber";
	                    	inputValid = true;
	                    }else{
	                    	inputValid = false;
	                    }
	                    return inputValid;
                    }
                </aui:validator>
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-policy-number" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input name="email"
							label="" placeholder="&nbsp;" wrappedField="<%=true%>"
							cssClass="validate" type="text">
							<aui:validator name="email" />
							<aui:validator name="required" errorMessage='error-label-email' />
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-claimant-email" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="mobileNo" label="" placeholder="&nbsp;" pattern = "[0-9]*" inputmode="numeric"
							wrappedField="<%=true%>" cssClass="validate vnumber" type="text"
							value="" maxLength="10" minLength="10">
							<aui:validator name="number" />
							<aui:validator name="required"
								errorMessage='error-label-mobile-number' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-ten">10</aui:validator>
							<aui:validator name="minLength"
								errorMessage="error-label-min-length-ten">10</aui:validator>
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-mobile-number" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="nomineeName" label="" placeholder="&nbsp;"
							wrappedField="<%=true%>" cssClass="validate valpha">
							<aui:validator name="required"
								errorMessage='error-label-nominee-name' />
							<aui:validator name="custom"
								errorMessage="error-label-full-name">
						function(val, fieldNode, ruleValue) {
						var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
						return regex.test(val);
						}
					</aui:validator>
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-nominee-name" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="dateOfBirth" label="" placeholder="&nbsp;"
							wrappedField="<%=true%>" cssClass="validate deathClaim vdate" type="text"
							value="" maxLength="10">
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
							<aui:validator name="custom"
								errorMessage="error-label-date-must-be-in-past">
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
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-date-of-birth" /></span>
					</label>
				</div>
				<div class="col-md-6 col-12">
					<label class="custom-field two"> <aui:input
							name="sumAssured" label="" placeholder="&nbsp;"
							wrappedField="<%=true%>" cssClass="validate vnumber" type="text"
							value="" maxLength="10">
							<aui:validator name="number" />
							<aui:validator name="required"
								errorMessage='error-label-sum-assured' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-ten">10</aui:validator>
						</aui:input> <span class="placeholder"><liferay-ui:message
								key="label-sum-assured" /></span>
					</label>
				</div>
			</div>
			<p class="noteText">
				<liferay-ui:message key="label-update-contact-note" />
			</p>
			<div class="mt-2 text-center text-md-left">
				<aui:button-row>
					<aui:button cssClass="edto-btn-primary" id="confirmSubmit"
						value="label-submit"
						onClick="validateUpdateContactDetailForm('update_contact_detail_step', true, 'update-contact-detail-form');"></aui:button>
				</aui:button-row>
			</div>
		</div>
	</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="updateContactDetailBackdrop"
	aria-labelledby="updateContactDetailBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close" data-dismiss="modal"
						aria-label="Close" onClick="closeUpdateContactModal(false);">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="responseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
					<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closeUpdateContactModal(false);" >
						<liferay-ui:message key="label-okay" />
					</button>
				 </div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>update-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script
	src="<%=request.getContextPath()%>/js/update-contact-detail.js?v=1.1"></script>
<style>

.td-heading {
	background-color: transparent !important;
	border: 0;
	color: #212529 !important;
	font-weight: bold !important;
}

.noteText {
	color: #6d6d6d;
    font-size: 0.875rem;
    font-family: 'Montserrat-Regular';
    margin-bottom: 20px;
}
</style>
