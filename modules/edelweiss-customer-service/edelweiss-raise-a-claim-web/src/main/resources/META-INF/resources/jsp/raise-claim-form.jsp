<%@ include file="../init.jsp"%>

<%
Map<String, String> claimTypeValues = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("claimTypes"))){
	claimTypeValues = (TreeMap<String, String>) renderRequest.getAttribute("claimTypes");
}

Map<String, String> addressProofValues = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("addressTypes"))){
	addressProofValues = (TreeMap<String, String>) renderRequest.getAttribute("addressTypes");
}

Map<String, String> validIDProofsValues = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("validIDProofs"))){
	validIDProofsValues = (TreeMap<String, String>) renderRequest.getAttribute("validIDProofs");
}


String acceptedExtensions = StringPool.BLANK;
if(Validator.isNotNull(renderRequest.getAttribute("documentExtensions"))){
	acceptedExtensions = (String)renderRequest.getAttribute("documentExtensions");
}

String recentPhotoExtensions = StringPool.BLANK;
if(Validator.isNotNull(renderRequest.getAttribute("recentPhotoExtensions"))){
	recentPhotoExtensions = (String)renderRequest.getAttribute("recentPhotoExtensions");
}


%>
<liferay-portlet:actionURL var="submitRaiseAClaimFormURL"
	name="<%=RaiseAClaimPortletKeys.SUBMIT_RAISE_A_CLAIM_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
<liferay-portlet:resourceURL
	id="<%=RaiseAClaimPortletKeys.UPLOAD_DOCUMENT_URL_MVC_RESOURCE_COMMAND%>"
	var="uploadDocumentURL" copyCurrentRenderParameters="false" />

<aui:form name="raise-a-claim-form"
	action="<%=submitRaiseAClaimFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitRaiseAClaimFormURL}"
		name="submitRaiseAClaimFormURL" />
	<aui:input type="hidden" value="${uploadDocumentURL}"
		name="uploadDocumentURL" />
	<div class="application_step"
		id='<portlet:namespace/>raise_a_claim_step'>
		<div class="edto-claim-pilosophy-wrapper">
			<div class="container">
				<div class="form-tiles-main-wrappper">
					<div class="row" id='<portlet:namespace/>short-form'>
						<div class="col-md-4 col-12">
						 <label class="custom-field two">
						 		<aui:input name="<%=ParameterConstants.NAME%>"  label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						 		 cssClass="validate valpha">
								<aui:validator name="required"
									errorMessage='error-label-claimant-name' />
								<aui:validator name="custom"
									errorMessage="error-label-full-name">
								function(val, fieldNode, ruleValue) {
								var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
								return regex.test(val);
								}
							</aui:validator>
							</aui:input>
							<span class="placeholder"><liferay-ui:message key="label-claimant-name" /></span>
						 </label>
						</div>
						<div class="col-md-4 col-12">
						<label class="custom-field two">
							<aui:input name="<%=ParameterConstants.MOBILE_NO%>" pattern = "[0-9]*" inputmode="numeric"
								label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate vnumber"
								type="text" value="" maxLength="10" minLength="10">
								<aui:validator name="number" />
								<aui:validator name="required"
									errorMessage='error-label-claimant-mobile-no' />
								<aui:validator name="maxLength"
									errorMessage="error-label-max-length-ten">10</aui:validator>
								<aui:validator name="minLength"
									errorMessage="error-label-min-length-ten">10</aui:validator>
							</aui:input>
							<span class="placeholder"><liferay-ui:message key="label-claimant-mobile-no" /></span>
						 </label>
						</div>
						<div class="col-md-4 col-12">
						<label class="custom-field two">
							<aui:input name="<%=ParameterConstants.POLICY_NUMBER%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>"
								cssClass="validate document-validate valphanum" type="text" value=""
								maxLength="10" minLength="10">
								<aui:validator name="alphanum" />
								<aui:validator name="required"
									errorMessage='error-label-policy-number' />
								<aui:validator name="maxLength"
									errorMessage="error-label-max-length-ten">10</aui:validator>
								<aui:validator name="minLength"
									errorMessage="error-label-min-length-ten">10</aui:validator>
								<aui:validator name="custom" errorMessage="error-label-invalid-policy-number">
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
							</aui:input>
							<span class="placeholder"><liferay-ui:message key="label-policy-number" /></span>
						</label>
						</div>
						
							<div class="claim-form-btn">
								<aui:button-row>
							<aui:button
								cssClass="edto-btn-primary"
								id="submit" value="label-submit"
								onClick="showClaimForm(this.id, 'short-form', 'full-form', true, 'raise-a-claim-form');"></aui:button>
						</aui:button-row>
							</div>
					
					</div>
					<div id='<portlet:namespace/>full-form' style='display: none;'>
			<div class="row">
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.EMAIL%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						cssClass="validate" type="text">
						<aui:validator name="email" />
						<aui:validator name="required"
							errorMessage='error-label-claimant-email' />
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-email" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.ADDRESS_1%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate valphanumspecchar"
						type="text" value="" maxLength="30">
						<aui:validator name="required"
							errorMessage='error-label-claimant-address-one' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-thirty">30</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z0-9\\s-/]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-address-one" /></span>
				 </label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.ADDRESS_LINE_2%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate valphanumspecchar"
						type="text" value="" maxLength="30">
						<aui:validator name="required"
							errorMessage='error-label-claimant-address-line-two' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-thirty">30</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z0-9\\s-/]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-address-line-two" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.ADDRESS_LINE_3%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						cssClass="validate valphanumspecchar" type="text" value=""
						maxLength="30">
						<aui:validator name="required"
							errorMessage='error-label-claimant-address-line-three' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-thirty">30</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z0-9\\s-/]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-address-line-three" /></span>
				 </label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.PIN_CODE%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate vnumber"
						type="text" value="" maxLength="6" minLength="6">
						<aui:validator name="number" />
						<aui:validator name="required"
							errorMessage='error-label-claimant-pin-code' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-six">6</aui:validator>
						<aui:validator name="minLength"
							errorMessage="error-label-min-length-six">6</aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-pin-code" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.CITY%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate valpha" 
						type="text" value="" maxLength="20">
						<aui:validator name="required"
							errorMessage='error-label-claimant-city' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-twenty">20</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z\\s]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-city" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.STATE%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate valpha" 
						type="text" value="" maxLength="20">
						<aui:validator name="required"
							errorMessage='error-label-claimant-state' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-twenty">20</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z\\s]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-state" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.BANK_ACCOUNT_NUMBER%>"
						label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						cssClass="validate preview vnumber" type="text" value=""
						maxLength="20">
						<aui:validator name="number" errorMessage='error-label-account-number'/>
						<aui:validator name="required"
							errorMessage='error-label-claimant-bank-account-number' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-twenty">20</aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-bank-account-number" /></span>
				 </label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.BANK_NAME%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="validate preview valpha"
						type="text" value="" maxLength="20">
						<aui:validator name="required"
							errorMessage='error-label-claimant-bank-name' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-twenty">20</aui:validator>
						<aui:validator name="custom"
							errorMessage="error-label-full-bank-name">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-bank-name" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<label class="custom-field two">
					<aui:input name="<%=ParameterConstants.BANK_ACCOUNT_IFSC%>"
						label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						cssClass="validate preview valphanum" type="text" value=""
						maxLength="11">
						<aui:validator name="alphanum" />
						<aui:validator name="required"
							errorMessage='error-label-claimant-bank-account-ifsc' />
						<aui:validator name="maxLength"
							errorMessage="error-label-max-length-eleven">11</aui:validator>
					</aui:input>
					<span class="placeholder"><liferay-ui:message key="label-claimant-bank-account-ifsc" /></span>
				</label>
				</div>
				<div class="col-md-4 col-12">
				<div class="select-container ">
					<aui:select name="<%=ParameterConstants.CLAIM_TYPE%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>"
						cssClass="validate"
						onChange="showDeathClaim(this.id, 'death-claim');">
						<aui:validator name="required"
							errorMessage='error-label-claim-type' />
						<aui:option selected="true">
							<liferay-ui:message key="label-select-claim-type" />
						</aui:option>
						<%
							for (Map.Entry<String, String> entry : claimTypeValues.entrySet()) {
						%>
						<aui:option value="<%=entry.getKey()%>"
							label="<%=entry.getValue()%>"></aui:option>
						<%
							}
						%>
					</aui:select>
				 </div>
				</div>
			</div>
			<div id='<portlet:namespace/>death-claim' style='display: none;'>
				<div class="row">
					<div class="col-md-4 col-12">
					<label class="custom-field two">
						<aui:input name="<%=ParameterConstants.DATE_OF_DEATH%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="deathClaim vdate"
							type="text" value="" maxLength="10">
							<aui:validator name="required"
								errorMessage='error-label-date-of-death' />
							<aui:validator name="custom" errorMessage='error-label-date-invalid'>
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
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-date-of-death" /></span>
					 </label>
					</div>
					<div class="col-md-4 col-12">
					<label class="custom-field two">
						<aui:input name="<%=ParameterConstants.PLACE_OF_DEATH%>" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="deathClaim valpha"
							type="text" value="" maxLength="20">
							<aui:validator name="required"
								errorMessage='error-label-place-of-death' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-twenty">20</aui:validator>
							<aui:validator name="custom"
								errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z\\s]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-place-of-death" /></span>
					 </label>
					</div>
					
					<div class="col-md-4 col-12">
						<div class="labelInputGroup selectWrapper">
							<div class="labelGroup">
								<p class="premiuim-small-text"><liferay-ui:message key="label-select-natural-death" /></p>
							</div>
						 	<div class="select-container ">
								<aui:select name="<%=ParameterConstants.IS_NATURAL_DEATH%>" label="" 
									cssClass="deathClaim" >
									<aui:validator name="required"
										errorMessage='error-label-natural-death' />
									<aui:option value="yes" label="label-yes"></aui:option>
									<aui:option value="no" label="label-no"></aui:option>
								</aui:select>
						 	</div>
					 	</div>
					</div>
					
					<div class="col-md-4 col-12">
					<label class="custom-field two">
						<aui:input name="<%=ParameterConstants.CAUSE_OF_DEATH_TEXT%>"
							label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="deathClaim valphanumspecchar"
							type="text" value="" maxLength="20">
							<aui:validator name="required"
								errorMessage='error-label-cause-of-death' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-twenty">20</aui:validator>
							<aui:validator name="custom"
								errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z0-9\\s-/]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-cause-of-death" /></span>
					</label>
					</div>
					<div class="col-md-4 col-12">
					 <label class="custom-field two">
						<aui:input
							name="<%=ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_NAME%>"
							label="" placeholder="&nbsp;" wrappedField="<%=true %>"
							cssClass="deathClaim valpha" type="text" value="">
							<aui:validator name="required"
								errorMessage='error-label-policy-holder-treating-doctor-name' />
							<aui:validator name="custom"
								errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z\\s]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-policy-holder-treating-doctor-name" /></span>
						</label>
					</div>
					<div class="col-md-4 col-12">
					<label class="custom-field two">
						<aui:input
							name="<%=ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_ADDRESS%>"
							label="" placeholder="&nbsp;" wrappedField="<%=true %>"
							cssClass="deathClaim valphanumspecchar" type="text" value="" maxLength="30"
							minLength="0">
							<aui:validator name="required"
								errorMessage='error-label-policy-holder-treating-doctor-address' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-thirty">30</aui:validator>
							<aui:validator name="custom"
								errorMessage="error-label-invalid-character">
	                    function(val, fieldNode, ruleValue) {
		                    var regex = new RegExp("^[a-zA-Z0-9\\s-/]*$");
		                    return regex.test(val);
	                    }
	                </aui:validator>
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-policy-holder-treating-doctor-address" /></span>
					 </label>
					</div>
					<div class="col-md-4 col-12">
					<label class="custom-field two">
						<aui:input
							name="<%=ParameterConstants.POLICY_HOLDER_TREATING_DOCTOR_MOB_NO%>"
							label="" placeholder="&nbsp;" wrappedField="<%=true %>"
							cssClass="deathClaim vnumber" type="text" value=""
							maxLength="10" minLength="10" pattern = "[0-9]*" inputmode="numeric">
							<aui:validator name="number" />
							<aui:validator name="required"
								errorMessage='error-label-policy-holder-treating-doctor-mobile-number' />
							<aui:validator name="maxLength"
								errorMessage="error-label-max-length-ten">10</aui:validator>
							<aui:validator name="minLength"
								errorMessage="error-label-min-length-ten">10</aui:validator>
						</aui:input>
						<span class="placeholder"><liferay-ui:message key="label-policy-holder-treating-doctor-mob-no" /></span>
					</label>
					</div>
				</div>
			</div>
			<h5 class="fs18 text-center pt-3 pb-3">
				<liferay-ui:message key="label-please-upload-documents-in-format" />
			</h5>
			<div class="claim-upload-doc-wrapper table-responsive">
				<table>
					<thead>
						<tr>
							<th><liferay-ui:message
								key="label-document" /></th>
							<th><liferay-ui:message
								key="label-document-type" /></th>
							<th><liferay-ui:message
								key="label-upload-document" /></th>
							<th><liferay-ui:message
								key="label-file-name" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<aui:input
								name="<%=ParameterConstants.DEATH_CERTIFICATE_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Death Certificate">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.DEATH_CERTIFICATE_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Proof of Death">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden"
											name="<%=ParameterConstants.DEATH_CERTIFICATE_ID%>"
											value="" />

										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>

										<aui:input name="<%=ParameterConstants.DEATH_CERTIFICATE%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required"
												errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.DEATH_CERTIFICATE_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.DEATH_CERTIFICATE%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.DEATH_CERTIFICATE%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><aui:input
									name="<%=ParameterConstants.ADDRESS_PROOF_DOCUMENT_TYPE%>"
									label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
									value="Address Proof">
								</aui:input></th>
							<td>
								<div class="labelInputGroup">
									<div class="select-container ">
										<aui:select
											name="<%=ParameterConstants.ADDRESS_PROOF_CONTENT_TYPE%>" label=""
											cssClass="validate document-validate">
											<aui:validator name="required"
												errorMessage='error-label-address-proof-type' />
											<aui:option selected="true">
												<liferay-ui:message key="label-select" />
											</aui:option>
											<%
												for (Map.Entry<String, String> entry : addressProofValues.entrySet()) {
											%>
											<aui:option value="<%=entry.getKey()%>"
												label="<%=entry.getValue()%>"></aui:option>
											<%
												}
											%>
										</aui:select>
									</div>
								</div>
							</td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.ADDRESS_PROOF_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
							
										<aui:input name="<%=ParameterConstants.ADDRESS_PROOF%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required"
												errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.ADDRESS_PROOF_ONE_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Address Proof 1">
							</aui:input></th>
							<td>
								<div class="labelInputGroup">
									<div class="select-container ">
										<aui:select
									name="<%=ParameterConstants.ADDRESS_PROOF_ONE_CONTENT_TYPE%>"
									label="" cssClass="validate document-validate">
									<aui:validator name="required"
										errorMessage='error-label-address-proof-type' />
									<aui:option selected="true">
										<liferay-ui:message key="label-select" />
									</aui:option>
									<%
										for (Map.Entry<String, String> entry : addressProofValues.entrySet()) {
									%>
									<aui:option value="<%=entry.getKey()%>"
										label="<%=entry.getValue()%>"></aui:option>
									<%
										}
									%>
								</aui:select>
									</div>
								</div>
							</td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.ADDRESS_PROOF_ONE_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
							
										<aui:input name="<%=ParameterConstants.ADDRESS_PROOF_ONE%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>" onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
							
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_ONE_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_ONE%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_ONE%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						

						<tr>
							<th><aui:input
								name="<%=ParameterConstants.ADDRESS_PROOF_TWO_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Address Proof 2">
							</aui:input></th>
							<td>
								<div class="labelInputGroup">
									<div class="select-container ">
										<aui:select
									name="<%=ParameterConstants.ADDRESS_PROOF_TWO_CONTENT_TYPE%>"
									label="" cssClass="validate document-validate">
									<aui:validator name="required"
										errorMessage='error-label-address-proof-type' />
									<aui:option selected="true">
										<liferay-ui:message key="label-select" />
									</aui:option>
									<%
										for (Map.Entry<String, String> entry : addressProofValues.entrySet()) {
									%>
									<aui:option value="<%=entry.getKey()%>"
										label="<%=entry.getValue()%>"></aui:option>
									<%
										}
									%>
								</aui:select>
									</div>
								</div>
							</td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.ADDRESS_PROOF_TWO_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
							
										<aui:input name="<%=ParameterConstants.ADDRESS_PROOF_TWO%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
													'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_TWO_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_TWO%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.ADDRESS_PROOF_TWO%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<!--  Valid ID Proofs Start -->
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.VALID_ID_PROOFS_DOCUMENT_TYPE%>"
								label="ID Proofs" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Valid ID Proofs">
							</aui:input></th>
							<td>
								<div class="labelInputGroup">
									<div class="select-container ">
										<aui:select
									name="<%=ParameterConstants.VALID_ID_PROOFS_CONTENT_TYPE%>"
									label="" cssClass="validate document-validate">
									<aui:validator name="required"
										errorMessage='error-label-valid-id-proofs' />
									<aui:option selected="true">
										<liferay-ui:message key="label-select" />
									</aui:option>
									<%
										for (Map.Entry<String, String> entry : validIDProofsValues.entrySet()) {
									%>
									<aui:option value="<%=entry.getKey()%>"
										label="<%=entry.getValue()%>"></aui:option>
									<%
										}
									%>
								</aui:select>
									</div>
								</div>
							</td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.VALID_ID_PROOFS_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
							
										<aui:input name="<%=ParameterConstants.VALID_ID_PROOFS%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
													'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.VALID_ID_PROOFS_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.VALID_ID_PROOFS%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.VALID_ID_PROOFS%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<!-- Valid ID Proof End -->
						<!--  Recent Photo Start -->
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.RECENT_PHOTO_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Recent Photo">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.RECENT_PHOTO_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Image">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.RECENT_PHOTO_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.RECENT_PHOTO%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=recentPhotoExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
											'<%=recentPhotoExtensions%>'
										</aui:validator>
											<aui:validator name="required"
												errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.RECENT_PHOTO_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.RECENT_PHOTO%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.RECENT_PHOTO%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<!-- Recent Photo End -->
						
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.CANCELLED_CHEQUE_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Cancelled Cheque">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.CANCELLED_CHEQUE_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Cheque">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.CANCELLED_CHEQUE_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.CANCELLED_CHEQUE%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
											'<%=acceptedExtensions%>'
										</aui:validator>
											<aui:validator name="required"
												errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.CANCELLED_CHEQUE_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.CANCELLED_CHEQUE%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.CANCELLED_CHEQUE%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<th><aui:input name="<%=ParameterConstants.POLICY_BOND_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Policy Bond">
							</aui:input></th>
							<td><aui:input name="<%=ParameterConstants.POLICY_BOND_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Policy">
							</aui:input></td>
							
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.POLICY_BOND_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.POLICY_BOND%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
										</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.POLICY_BOND_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.POLICY_BOND%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.POLICY_BOND%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.DOCTORS_CERTIFICATE_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Doctor's Certificate">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.DOCTORS_CERTIFICATE_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Certificate">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.DOCTORS_CERTIFICATE_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.DOCTORS_CERTIFICATE%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
										</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
								
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.DOCTORS_CERTIFICATE_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.DOCTORS_CERTIFICATE%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.DOCTORS_CERTIFICATE%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.CAUSE_OF_DEATH_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Cause of Death">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.CAUSE_OF_DEATH_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Certificate">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.CAUSE_OF_DEATH_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.CAUSE_OF_DEATH%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
													'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.CAUSE_OF_DEATH_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.CAUSE_OF_DEATH%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.CAUSE_OF_DEATH%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><aui:input name="<%=ParameterConstants.POLICE_FIR_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Police FIR">
							</aui:input></th>
							<td><aui:input name="<%=ParameterConstants.POLICE_FIR_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Document">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.POLICE_FIR_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.POLICE_FIR%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
													'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.POLICE_FIR_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.POLICE_FIR%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.POLICE_FIR%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><aui:input
								name="<%=ParameterConstants.POST_MORTEM_REPORT_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Post Mortem Report">
							</aui:input></th>
							<td><aui:input
								name="<%=ParameterConstants.POST_MORTEM_REPORT_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Proof of Death">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.POST_MORTEM_REPORT_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.POST_MORTEM_REPORT%>"
											label="" cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
										</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.POST_MORTEM_REPORT_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.POST_MORTEM_REPORT%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.POST_MORTEM_REPORT%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th><aui:input name="<%=ParameterConstants.PANCHNAMA_DOCUMENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Panchnama">
							</aui:input></th>
							<td><aui:input name="<%=ParameterConstants.PANCHNAMA_CONTENT_TYPE%>"
								label="" cssClass="validate td-heading" type="text" readOnly="readOnly"
								value="Other Documents">
							</aui:input></td>
							<td>
								<div class="file-fixed">
									<label class="cl-upload-btn-w pointer fvc">
										<aui:input cssClass="cl-file-input d-none" type="hidden" name="<%=ParameterConstants.PANCHNAMA_ID%>" value="" />
										<span class="cl-upload-btn d-inline-flex">
											<span class="link ft-semibold"><liferay-ui:message key="label-choose-file" /></span>
											<img class="ml-2" src="<%=request.getContextPath()%>/images/plus.svg" height="20">
										</span>
										<span class="error error-txt d-none"><liferay-ui:message key="label-please-upload-file" /></span>
										<aui:input name="<%=ParameterConstants.PANCHNAMA%>" label=""
											cssClass="validate cl-file-input d-none" type="file" value="" multiple="false" accept="<%=acceptedExtensions %>"
											onChange="uploadDocument(this.files,this.id,'raise-a-claim-form');">
											<aui:validator name="acceptFiles">
												'<%=acceptedExtensions%>'
											</aui:validator>
											<aui:validator name="required" errorMessage='error-label-file-required' />
										</aui:input>
									</label>
								</div>
							</td>
							<td>
								<div class="submit-doc file-fixed w-100" id="<portlet:namespace/><%=ParameterConstants.PANCHNAMA_PREVIEW%>" style="display: none;">
									<div class="doc-text-field">
										<div class="uploadedDocDetailBx">
											<div class="uploadedDocDetailBxInr">
												<label class="uploadedDoc d-flex align-items-center mb-0">
													<span class="uploadedFileName"></span>
													<div class=" px-2 small-cross remove-cross pointer" id="<portlet:namespace/><%=ParameterConstants.PANCHNAMA%>Remove" onClick="removeFile(this.id);">
														<img src="<%=request.getContextPath()%>/images/icons8-close-24.png" alt="" width="14px">
													</div>
												</label>
											</div>
											<div class="progress-cv d-flex align-items-center" id="<portlet:namespace/><%=ParameterConstants.PANCHNAMA%>Remove">
												<div class="progress">
													<div class="progress-bar bg-success" style="width:100%"></div>
												</div>
												<div class="small-tick px-1"><img src="<%=request.getContextPath()%>/images/small-tick.svg" alt=""></div>
											</div>
										</div>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="claim-form-btn">
				<aui:button-row>
					<aui:button
						cssClass="edto-btn-primary"
						id="confirmSubmit" value="label-confirm-submit"
						onClick="validateRaiseAClaimForm('raise_a_claim_step', true, 'raise-a-claim-form', true);">
					</aui:button>
				</aui:button-row>
			</div>			
		</div>
				</div>
			</div>
		</div>
		
		
	</div>
</aui:form>

<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog" id="claimIntimationBackdrop"  aria-labelledby="claimIntimationBackdropModal" 
aria-hidden="true" data-backdrop="static" data-keyboard="false">
   <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
	      <div class="modal-body">
	         <div class="modal-header h-auto">
	         	<h2></h2>
	            <button type="button" id="closeBtn" class="close close-right" data-dismiss="modal" aria-label="Close" onClick="closeModal(false);"> 
					<span aria-hidden="true">
						<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15"><path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"></path>
						</svg> 
					</span>
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
					<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closeModal(false);" >
						<liferay-ui:message key="label-okay" />
					</button>
				 </div>
	         </div>	         
	      </div>
      </div>
   </div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/raise-claim-form.js?v=1.1"></script>
<script>
	
	//Hide title attribute text on hover
	$('.application_step input, .application_step select').hover(function(e){
	    $(this).attr('title', '');
	});
	
</script>
<style>
	.claim-form-btn {
		display: flex;
    	justify-content: center;
    	width: 100%;
    	margin-top:15px;
		}
	.td-heading {
		background-color: transparent !important;
		border: 0;
		color: #212529 !important;
		font-weight: bold !important;
	}
</style> 
