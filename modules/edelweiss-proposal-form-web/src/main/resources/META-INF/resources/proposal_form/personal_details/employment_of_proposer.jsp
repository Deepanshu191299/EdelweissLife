<%@ include file="../../init.jsp"%>

<liferay-portlet:resourceURL
		id="/gstin/state-code/validation" var="gstinStateCodeValidationRsourceURL" copyCurrentRenderParameters="false" >
		
	</liferay-portlet:resourceURL>

<div id='education_of_proposer' class="communication-details">
<h5><liferay-ui:message key="label-employment-details-for-proposer" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p style="white-space: wrap;">
				<liferay-ui:message
					key="label-do-you-wish-to-avail-GSTIN-benefit" />
			</p>
			<div class="radio_container">
				<aui:input name='proposer_gstBenefit' id="proposer_gstYes" cssClass="validate" 
					type="radio" label="" required="required" value="Y"
					checked="${(proposerPersonalDetails.gstinNumber != null) && 
						(proposerPersonalDetails.gstinNumber != 'undefined') &&
						(proposerPersonalDetails.gstinNumber != 'N') && 
						(proposerPersonalDetails.gstinNumber != '') ? true : false}"
					onclick="showPDDiv(true, 'proposer_gstAvailed');"
					onChange="validateRadioButtonsOnChange('personal_details_form','proposer_gstBenefit')">
					<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
				</aui:input>
				<label for="<portlet:namespace/>proposer_gstYes"><liferay-ui:message
						key="label-yes" /></label>
		
				<aui:input name='proposer_gstBenefit' id="proposer_gstNo" cssClass="validate" 
					type="radio" label="" required="required" value="N"
					checked="${proposerPersonalDetails.gstinNumber == 'N' ? true : false}"
					onclick="showPDDiv(false, 'proposer_gstAvailed');"
					onChange="validateRadioButtonsOnChange('personal_details_form','proposer_gstBenefit')">
				</aui:input>
				<label for="<portlet:namespace/>proposer_gstNo"><liferay-ui:message
						key="label-no" /></label> 
			 </div>
			
			<div class="location-field">
				<div class="row">
					 <div id="proposer_gstAvailed" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_gstNumber"
									placeholder="&nbsp;" label="" cssClass="valphanumwithspace" type="text"
									value="${proposerPersonalDetails.gstinNumber}" maxLength="15" minLength="0" wrappedField="<%=true %>" style="text-transform: uppercase" onChange="validateStateCodeForProposer()">
									<aui:validator name="required" errorMessage="gst-error-message" />
									<aui:validator name="custom" errorMessage="valid-gst-number-error">
										function(val, fieldNode, ruleValue) {
											$("#"+portletNamespace+"gstinCommonValidationProposer").html('');
											var gstNum = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[A-Z0-9]{1}$/;
	   										var gstNumValidation = gstNum.test(val.toUpperCase());
	   										
	   										var panNo = $("#" +portletNamespace+ "proposer_panNo").val();
											var gstinPAN = val.substring(2, 12);
											
											if(gstNumValidation && panNo==gstinPAN){
												return true;
											} else {
												return false;
											}
										 }
										 
									</aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-gst-number" /></span>
										<div class="image-container gst-no-tooltip"><img src="/o/edelweisstokio-theme/images/praposal/info-co.png" alt="icon">
											<div class="tooltip" >
											<liferay-ui:message key="gstno-tip1" /><br> 
											<liferay-ui:message key="gstno-tip2" /><br>
											<liferay-ui:message key="gstno-tip3" /><br>
											<liferay-ui:message key="gstno-tip4" /><br>
											<liferay-ui:message key="gstno-tip5" /><br> 
											<liferay-ui:message key="gstno-tip6" />
											</div>
										   </div>
								<div class="form-feedback-item form-validator-stack help-block">
									<div role="alert" id="<portlet:namespace/>gstinCommonValidationProposer" class="proposer_gstNumber_custom"></div>
								</div>
							</label>
						</div>
					</div> 
					<div class="col-md-12 col-12">
					<p>
						<liferay-ui:message key="label-employement-type" />
					</p>
					<div class="radio_container">
						<c:forEach var="employementData" items="${masterMap['Employment Type']}">
							<aui:input name='proposer_employementType' id="proposer_employementType_${employementData.name}"
								cssClass="validate" type="radio" label="" required="required"
								value="${employementData.value}" onclick="employementDiv('proposer_',this.value,true);"
								checked="${proposerEmploymentDetails.employmentTypeDataId == employementData.value ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','proposer_employementType')">
								<aui:validator name="required" errorMessage="employement-error-message"></aui:validator>
							</aui:input>
							<label for='<portlet:namespace/>proposer_employementType_${employementData.name}'>
								${employementData.name} </label>
						</c:forEach>
					</div>
					</div>
					<div id="proposer_nameOfEmployer" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-employer") %>" title=" " name="proposer_nameofEmployerBusiness" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_nameofEmployerBusiness'); proposer_displayEmployerOthersField(this);">
									<aui:option>
									</aui:option>
									<aui:option value="0" label="Others" ></aui:option>
 									<c:forEach var="companyData" items="${companyList}">
										<aui:option value="${companyData.companyID}"
											selected="${proposerEmploymentDetails.employerName == companyData.companyName ? true : false}"
											label="${companyData.companyName}">
										</aui:option>
									</c:forEach>
										
									<aui:validator name="required" errorMessage="name-of-employer-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="name-of-employer-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="proposer_nameOfEmployer_others" class="col-md-6 col-12" style="display:none;">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','proposer_nameOfEmployer_others')"
										name="proposer_nameOfEmployer_others" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${proposerEmploymentDetails.employerName}" maxLength="60"
										minLength="0" wrappedField="<%=true %>">
										<aui:validator name="required" errorMessage="employer-name-others-error-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-employer-name-others-field" /></span>
							</label>
						</div>
					</div>
					
					<div id="proposer_designationPosition" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_designationPositionHeld" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${proposerEmploymentDetails.designationHeld}" maxLength="60"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="position-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-designation-position-held" /></span>
							</label>
						</div>
					</div>
					
					<div id="proposer_jobDescription" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-job-description-nature-of-duty") %>" title=" " name="proposer_jobDescriptionNatureDuty" label=""
									cssClass="select2-select" required="true" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_jobDescriptionNatureDuty')">
									<aui:option>
									</aui:option>
									<c:forEach var="natureOfDutyData" items="${masterMap['Nature of Duty']}">
										<aui:option value="${natureOfDutyData.value}"
											selected="${proposerEmploymentDetails.dutyNatureTypeDataId == natureOfDutyData.value ? true : false}"
											label="${natureOfDutyData.name}">
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="proposer_businessNature" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_natureOfBusiness" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${proposerEmploymentDetails.businessNature}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="nature-of-business-error-meesage"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-nature-of-business" /></span>
							</label>
						</div>
					</div>
					
					<div id="proposer_workExperienceYear" class="col-md-6 col-12">
						<div class="form-box">
							<p><liferay-ui:message key="label-select-work-experience-years" /></p>
							<div class="number-wrapaper">
							<div class="number">
                               <span class="minus">-</span>
                               <aui:input
									name="proposer_workExperienceYears" placeholder="&nbsp;" label=""
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_workExperienceYears')"
									cssClass="numberField vnumber range-work-exp" type="text"
									value="${proposerEmploymentDetails.workingDurationYear==null || proposerEmploymentDetails.workingDurationYear==' ' ? 0 : proposerEmploymentDetails.workingDurationYear}" 
									maxLength="2" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="work-experience-error-message"></aui:validator>
									<!--<aui:validator errorMessage="valid-range-error-message" name="range">[0,57]</aui:validator>-->
								</aui:input>
                               <span class="plus plus-year">+</span>
                            </div>
						</div>
                            <span class="error" id="<portlet:namespace/>proposer_workExperienceYears_Error"></span>
						</div>
					</div>
					
					<div id="proposer_workExperienceMonth" class="col-md-6 col-12 mt-3">
						<div class="form-box">
							<p><liferay-ui:message key="label-select-work-experience-months" /></p>
							<div class="number-wrapaper">
							<div class="number">
                               <span class="minus">-</span>
                               <aui:input
									name="proposer_workExperienceMonths" placeholder="&nbsp;" label=""
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_workExperienceMonths')"
									cssClass="numberField vnumber range-month-height" type="text"
									value="${proposerEmploymentDetails.workingDurationMnth==null || proposerEmploymentDetails.workingDurationMnth == ' ' ? 0: proposerEmploymentDetails.workingDurationMnth}" 
									maxLength="2" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="work-experience-error-message"></aui:validator>
									<!--<aui:validator errorMessage="valid-range-error-message" name="range">[0,11]</aui:validator>-->
								</aui:input>
                               <span class="plus plus-month">+</span>
                            </div>
						</div>
                            <span class="error" id="<portlet:namespace/>proposer_workExperienceMonths_Error"></span>
						</div>
					</div>
					
					<div id="proposer_addressEmployerBusiness" class="col-md-6 col-12 mt-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_addressOfEmployerBusiness')"
									name="proposer_addressOfEmployerBusiness" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${proposerEmploymentDetails.employerAddress}" maxLength="90"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="employer-business-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-address-of-employer-business" /></span>
							</label>
						</div>
					</div>
					
					<div id="proposer_occupationIndustry" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-occupation-industry-type") %>" title=" " name="proposer_occupationIndustryType" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_occupationIndustryType')">
									<aui:option></aui:option>
									<c:forEach var="industryTypeData" items="${masterMap['Occupation Industry Type']}">
										<aui:option value="${industryTypeData.value}"
											selected="${proposerEmploymentDetails.organisationTypeDataId == industryTypeData.value ? true : false}"
											>${industryTypeData.name}
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="proposer_numberEmployeesCompany" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-number-of-employees-in-the-company") %>" title=" " name="proposer_numberOfEmployeesInCompany" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','proposer_numberOfEmployeesInCompany')">
									<aui:option></aui:option>
									<c:forEach var="noEmployeesData" items="${masterMap['Number of Employees in the Company']}">
										<aui:option value="${noEmployeesData.value}"
											selected="${proposerEmploymentDetails.employCountTypeDataId == noEmployeesData.value ? true : false}"
											label="${noEmployeesData.name}">
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_annualIncome')"
									name="proposer_annualIncome" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text" 
									value="${proposerEmploymentDetails.annualIncome}" maxLength="10"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="employer-income-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-annual-income" /></span>
							</label>
						</div>
					</div>
					
					<div class="col-md-12 col-12">
						<p style="white-space:wrap;">
							<liferay-ui:message
								key="label-is-your-source-of-income-other-than-the-employment-type-selected-above" />
						</p>
						<div class="radio_container">
							<aui:input name='proposer_otherIncomeSource' id="proposer_incomeYes" cssClass="validate"
								type="radio" label="" required="required" value="Y" onclick="showPDDiv(true, 'proposer_otherIncomeSource');"
								checked="${proposerEmploymentDetails.sourcIncomeOtherYn == 'Y' ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','proposer_otherIncomeSource')">
								<aui:validator name="required" errorMessage="income-source-error-meesage"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>proposer_incomeYes"><liferay-ui:message
									key="label-yes" /></label>
					
							<aui:input name='proposer_otherIncomeSource' id="proposer_incomeNo" cssClass="validate"
								type="radio" label="" required="required" value="N" onclick="showPDDiv(false, 'proposer_otherIncomeSource');"
								checked="${proposerEmploymentDetails.sourcIncomeOtherYn == 'N' ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','proposer_otherIncomeSource')">
							</aui:input>
							<label for="<portlet:namespace/>proposer_incomeNo"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					
					<div id="proposer_otherIncomeSource" class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','proposer_provideIncomeDetails')"
									name="proposer_provideIncomeDetails" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${proposerEmploymentDetails.sourceIncomeOtherDetails}" maxLength="150"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="error-message-for-income-details" />
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-provide-income-details" /></span>
							</label>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(document).ready(function() {
	loadGSTIncomeFields(portletNamespace, "proposer_");
	employementDiv("proposer_", $('input[name="'+portletNamespace +'proposer_employementType"]:checked').val(),false);

	// add condition on document ready for the employeement field
	var proposerNameOfEmployerBusiness = $('#'+portletNamespace+'proposer_nameofEmployerBusiness').val();
	console.log("proposerNameOfEmployerBusiness--->"+proposerNameOfEmployerBusiness);
	var proposerNameOfEmployerOthers = $('#<portlet:namespace/>proposer_nameOfEmployer_others').val();
	if(proposerNameOfEmployerBusiness == '' && proposerNameOfEmployerOthers != ''){
		console.log("inside the if condition");
		  $("select[name='<portlet:namespace/>proposer_nameofEmployerBusiness']").val("0");
		  $('#proposer_nameOfEmployer_others').show();	  
	}
	
});

function validateStateCodeForProposer(){
	$("#"+portletNamespace+"gstinCommonValidationProposer").html('');
	$('#'+ portletNamespace + 'loader').show();
	var gstinStateCodeValidation = '<%= gstinStateCodeValidationRsourceURL %>';
	var currentURL = new URL(window.location.href);
	var appId = currentURL.searchParams.get('appid');
	var gstinNo = $("#" +portletNamespace+ "proposer_gstNumber").val();
	$("#responseMessage").html('');
	
	var correspondingAddress = $("input[type='radio'][name='" + portletNamespace + "proposer_correspondingAddress']:checked");
	var sameAddress = $("input[type='radio'][name='" + portletNamespace + "proposer_isAddressSame']:checked");	
	if (correspondingAddress || sameAddress) {
	    var correspondingAddress1 = correspondingAddress.val();
		var sameAddress1 = sameAddress.val();
			 if(sameAddress1=='Y' || correspondingAddress1 == 'P'){
				 var s = document.getElementById(portletNamespace + 'proposer_pa_state');
					var st = s.options[s.selectedIndex];
					var stateName = st.getAttribute("data-name");
			 }else{
				 var s = document.getElementById(portletNamespace + 'proposer_ca_state');
					var st = s.options[s.selectedIndex];
					var stateName = st.getAttribute("data-name");
			 }
	}
	
	// Set a common error message for GSTIN validation
	var gstinErrorMessage = "Please provide valid GSTIN Number";
	
	var locationFormData = new FormData();
	locationFormData.append(portletNamespace + "appId", appId);
	locationFormData.append(portletNamespace + "state", stateName);
	locationFormData.append(portletNamespace + "gstinNo", gstinNo);
	
	Liferay.Util.fetch(gstinStateCodeValidation, {
		body: locationFormData,
		method: 'POST'
	})
	.then((response) => {
		return response.text();
	})
	.then((response) => {
		if(response=="false"){
			var errorHelperDiv = document.getElementById(portletNamespace+"proposer_gstNumberHelper");
			
			if(errorHelperDiv==null || errorHelperDiv == "null"){
				$("#"+portletNamespace+"gstinCommonValidationProposer").html(gstinErrorMessage);
			}
			$("#"+portletNamespace+"proposer_gstNumber").addClass("error-field");
		} else{
			$("#"+portletNamespace+"gstinCommonValidationProposer").html('');
			$("#"+portletNamespace+"proposer_gstNumber").removeClass("error-field");
		}
		hideLoader();
	})
	.catch((e) => {
		console.log(e);
		hideLoader();
	})
}

function proposer_displayEmployerOthersField(_this){
	if(_this.value=='0'){
		document.getElementById("proposer_nameOfEmployer_others").style.display="";
		$("#"+portletNamespace+"proposer_nameOfEmployer_others").addClass("validate");
	} else {
		document.getElementById("proposer_nameOfEmployer_others").style.display="none";
		$("#"+portletNamespace+"proposer_nameOfEmployer_others").removeClass("validate");
	}
}
</script>
<style>
.proposer_gstNumber_custom {
    color: #dc3545 !important;
    font-size: 12px !important;
}
</style>