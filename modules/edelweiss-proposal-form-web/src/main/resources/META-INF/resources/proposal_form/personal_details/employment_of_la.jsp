<%@page import="in.edelweiss.proposal.form.constants.ProposalFormPortletKeys"%>
<%@ include file="../../init.jsp"%>

	<liferay-portlet:resourceURL
		id="/gstin/state-code/validation" var="gstinStateCodeValidationRsourceURL" copyCurrentRenderParameters="false" >
		
	</liferay-portlet:resourceURL>

<div id='education_of_la' class="communication-details">
<h5><liferay-ui:message key="label-employment-details-for-life-assured" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			 <p style="white-space:wrap">
				<liferay-ui:message
					key="label-do-you-wish-to-avail-GSTIN-benefit" />
			  </p> 
			
			 <div class="radio_container">
			 	<aui:input name='la_gstBenefit' id="la_gstYes" cssClass="validate" 
					type="radio" label="" required="required" value="Y" 
					checked="${(laPersonalDetails.gstinNumber != null) && 
						(laPersonalDetails.gstinNumber != 'undefined') &&
						(laPersonalDetails.gstinNumber != 'N') && 
						(laPersonalDetails.gstinNumber != '') ? true : false}"
					onclick="showPDDiv(true, 'la_gstAvailed');"
					onChange="validateRadioButtonsOnChange('personal_details_form','la_gstBenefit')" >
					<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
				</aui:input>
				<label for="<portlet:namespace/>la_gstYes"><liferay-ui:message key="label-yes" /></label>
		
				<aui:input name='la_gstBenefit' id="la_gstNo" cssClass="validate" 
					type="radio" label="" required="required" value="N"
					checked="${laPersonalDetails.gstinNumber == 'N' ? true : false}"
					onclick="showPDDiv(false, 'la_gstAvailed');"
					onChange="validateRadioButtonsOnChange('personal_details_form','la_gstBenefit')">
				</aui:input>
				<label for="<portlet:namespace/>la_gstNo"><liferay-ui:message key="label-no" /></label> 
			</div> 
			
			<div class="location-field">
				<div class="row">
					<div id="la_gstAvailed" class="col-md-6 col-12" style="display: none;">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="la_gstNumber" 
									onInput="validateRadioButtonsOnChange('personal_details_form','la_gstNumber')"
									placeholder="&nbsp;" label="" cssClass="valphanumwithspace" type="text"
									value="${laPersonalDetails.gstinNumber}" maxLength="15" 
									minLength="0" wrappedField="<%=true %>" style="text-transform: uppercase" onChange="validateStateCodeForLA()">
									<aui:validator name="required" errorMessage="gst-error-message"/>
									<aui:validator name="custom" errorMessage="valid-gst-number-error">
										function(val, fieldNode, ruleValue) {
											$("#"+portletNamespace+"gstinCommonValidationLA").html('');
											var gstNum = /^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[A-Z0-9]{1}$/; 
	   										var gstNumValidation = gstNum.test(val.toUpperCase());
	   								console.log("gst num:::"+ gstNumValidation );
	   										var panNo = $("#" +portletNamespace+ "la_panNo").val();
											var gstinPAN = val.substring(2, 12);
											
											if(gstNumValidation && panNo==gstinPAN){
												return true;
											} else {
												return false;
											}
																
										 }
									</aui:validator> 
								</aui:input> <span class="placeholder"><liferay-ui:message key="label-gst-number" /></span>
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
								<div id='<portlet:namespace/>gstin-loader' style='display: none;'>
									<div class="preloader"></div>
								</div>
								
								<div class="form-feedback-item form-validator-stack help-block">
									<div role="alert" id="<portlet:namespace/>gstinCommonValidationLA" class="la_gstNumber_custom"></div>
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
							<aui:input name='la_employementType' id="la_employementType_${employementData.name}"
								cssClass="validate" type="radio" label="" required="required"
								value="${employementData.value}" onclick="employementDiv('la_',this.value,true);"
								checked="${laEmploymentDetails.employmentTypeDataId == employementData.value ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','la_employementType')">
								<aui:validator name="required" errorMessage="employement-error-message"></aui:validator>
							</aui:input>
							<label for='<portlet:namespace/>la_employementType_${employementData.name}'>
								${employementData.name} </label>
						</c:forEach>
					</div>
					</div>

					<div id="la_nameOfEmployer" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-employer") %>" title=" " name="la_nameofEmployerBusiness" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','la_nameofEmployerBusiness'); la_displayEmployerOthersField(this);">
									<aui:option>
									</aui:option>
									<!--<aui:option value="0" label="Others" ></aui:option> -->
									 <aui:option value="0" label="Others"></aui:option>
									<c:forEach var="companyData" items="${companyList}">
										<aui:option value="${companyData.companyID}"
											selected="${laEmploymentDetails.employerName == companyData.companyName ? true : false}"
											label="${companyData.companyName}">
										</aui:option>
									</c:forEach>
										
									<aui:validator name="required" errorMessage="name-of-employer-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="name-of-employer-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="la_nameOfEmployer_others" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									oninput="validateRadioButtonsOnChange('personal_details_form','la_nameOfEmployer_others')"
										name="la_nameOfEmployer_others" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laEmploymentDetails.employerName}" maxLength="60"
										minLength="0" wrappedField="<%=true %>" required="true">
										<aui:validator name="required" errorMessage="employer-name-others-error-message"></aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-employer-name-others-field" /></span>
							</label>
						</div>
					</div>
					
					<div id="la_designationPosition" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','la_designationPositionHeld')"
									name="la_designationPositionHeld" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${laEmploymentDetails.designationHeld}" maxLength="60"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="position-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-designation-position-held" /></span>
							</label>
						</div>
					</div>
					
					<div id="la_jobDescription" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-job-description-nature-of-duty") %>" title=" " name="la_jobDescriptionNatureDuty" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','la_jobDescriptionNatureDuty')">
									<aui:option>
									</aui:option>
									<c:forEach var="natureOfDutyData" items="${masterMap['Nature of Duty']}">
										<aui:option value="${natureOfDutyData.value}"
											selected="${laEmploymentDetails.dutyNatureTypeDataId == natureOfDutyData.value ? true : false}"
											label="${natureOfDutyData.name}">
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="la_businessNature" class="col-md-6 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','la_natureOfBusiness')"
									name="la_natureOfBusiness" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${laEmploymentDetails.businessNature}" maxLength="30"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="nature-of-business-error-meesage"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-nature-of-business" /></span>
							</label>
						</div>
					</div>

					<div id="la_workExperienceYear" class="col-md-6 col-12">
						<div class="form-box">
							<p><liferay-ui:message key="label-select-work-experience-years" /></p>
						<div class="number-wrapaper">
							<div class="number">
                               <span class="minus">-</span>
                               <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_workExperienceYears')"
									name="la_workExperienceYears" placeholder="&nbsp;" label=""
									cssClass="numberField vnumber range-work-exp" type="text"
									value="${laEmploymentDetails.workingDurationYear == null || laEmploymentDetails.workingDurationYear == ' ' ? 0 : laEmploymentDetails.workingDurationYear}" 
									maxLength="2" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="work-experience-error-message"></aui:validator>
									<!--<aui:validator errorMessage="valid-range-error-message" name="range">[0,57]</aui:validator>-->
								</aui:input>
                               <span class="plus plus-year">+</span>
                            </div>
						</div>
                            <span class="error" id="<portlet:namespace/>la_workExperienceYears_Error"></span>
						</div>
					</div>
					
					<div id="la_workExperienceMonth" class="col-md-6 col-12 mt-3">
						<div class="form-box">
							<p><liferay-ui:message key="label-select-work-experience-months" /></p>
							<div class="number-wrapaper">
							<div class="number">
                               <span class="minus">-</span>
                               <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_workExperienceMonths')"
									name="la_workExperienceMonths" placeholder="&nbsp;" label=""
									cssClass="numberField vnumber range-month-height" type="text"
									value="${laEmploymentDetails.workingDurationMnth == null || laEmploymentDetails.workingDurationMnth == ' ' ? 0:laEmploymentDetails.workingDurationMnth}" 
									maxLength="2" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="work-experience-error-message"></aui:validator>
									<!--<aui:validator errorMessage="valid-range-error-message" name="range">[0,11]</aui:validator>-->
								</aui:input>
                               <span class="plus plus-month">+</span>
                            </div>
						</div>
                            <span class="error" id="<portlet:namespace/>la_workExperienceMonths_Error"></span>
						</div>
					</div>
					
					<div id="la_addressEmployerBusiness" class="col-md-6 col-12 mt-3">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','la_addressOfEmployerBusiness')"
									name="la_addressOfEmployerBusiness" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${laEmploymentDetails.employerAddress}" maxLength="90"
									minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required" errorMessage="employer-business-error-message"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-address-of-employer-business" /></span>
							</label>
						</div>
					</div>
					
					<div id="la_occupationIndustry" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-occupation-industry-type") %>" title=" " name="la_occupationIndustryType" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','la_occupationIndustryType')">
									<aui:option></aui:option>
									<c:forEach var="industryTypeData" items="${masterMap['Occupation Industry Type']}">
										<aui:option value="${industryTypeData.value}"
											selected="${laEmploymentDetails.organisationTypeDataId == industryTypeData.value ? true : false}"
											>${industryTypeData.name}
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div id="la_numberEmployeesCompany" class="col-md-6 col-12">
						<div class="form-box">
							<div class="select-container">
								<aui:select data-placeholder="<%=LanguageUtil.get(request, "label-select-number-of-employees-in-the-company") %>" title=" " name="la_numberOfEmployeesInCompany" label=""
									cssClass="select2-select" onChange="validateRadioButtonsOnChange('personal_details_form','la_numberOfEmployeesInCompany')">
									<aui:option></aui:option>
									<c:forEach var="noEmployeesData" items="${masterMap['Number of Employees in the Company']}">
										<aui:option value="${noEmployeesData.value}"
											selected="${laEmploymentDetails.employCountTypeDataId == noEmployeesData.value ? true : false}"
											label="${noEmployeesData.name}">
										</aui:option>
									</c:forEach>
									<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
								</aui:select>
								<!--<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>-->
							</div>
						</div>
					</div>
					
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_annualIncome')"
									name="la_annualIncome" placeholder="&nbsp;" label=""
									cssClass="validate vnumber" type="text" 
									value="${laEmploymentDetails.annualIncome}" maxLength="10"
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
							<aui:input name='la_otherIncomeSource' id="la_incomeYes" cssClass="validate"
								type="radio" label="" required="required" value="Y" onclick="showPDDiv(true, 'la_otherIncomeSource');"
								checked="${laEmploymentDetails.sourcIncomeOtherYn == 'Y' ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','la_otherIncomeSource')">
								<aui:validator name="required" errorMessage="income-source-error-meesage"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>la_incomeYes"><liferay-ui:message
									key="label-yes" /></label>
					
							<aui:input name='la_otherIncomeSource' id="la_incomeNo" cssClass="validate"
								type="radio" label="" required="required" value="N" onclick="showPDDiv(false, 'la_otherIncomeSource');"
								checked="${laEmploymentDetails.sourcIncomeOtherYn == 'N' ? true : false}"
								onChange="validateRadioButtonsOnChange('personal_details_form','la_otherIncomeSource')">
							</aui:input>
							<label for="<portlet:namespace/>la_incomeNo"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					
					<div id="la_otherIncomeSource" class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('personal_details_form','la_provideIncomeDetails')"
									name="la_provideIncomeDetails" placeholder="&nbsp;" label=""
									cssClass="valphanumwithspace" type="text" 
									value="${laEmploymentDetails.sourceIncomeOtherDetails}" maxLength="150"
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
	loadGSTIncomeFields(portletNamespace, "la_");
	employementDiv("la_", $('input[name="'+portletNamespace +'la_employementType"]:checked').val(),false);

	// add condition on document ready for the employeement field
	var employeeBusiness = $('#'+portletNamespace+'la_nameofEmployerBusiness').val();
	console.log("employeeBusiness--->"+employeeBusiness);
	var la_nameOfEmployer_othersVal = $('#<portlet:namespace/>la_nameOfEmployer_others').val();
	if(employeeBusiness == '' && la_nameOfEmployer_othersVal != ''){
		console.log("inside the if condition");
		  $("select[name='<portlet:namespace/>la_nameofEmployerBusiness']").val("0");
		  $('#la_nameOfEmployer_others').show();	  
	}
	
});

function validateStateCodeForLA(){
	$("#"+portletNamespace+"gstinCommonValidationLA").html('');
	$('#'+ portletNamespace + 'loader').show();
	var gstinStateCodeValidation = '<%= gstinStateCodeValidationRsourceURL %>';
	var currentURL = new URL(window.location.href);
	var appId = currentURL.searchParams.get('appid');
	var gstinNo = $("#" +portletNamespace+ "la_gstNumber").val();
	$("#responseMessage").html('');

	var correspondingAddress = $("input[type='radio'][name='" + portletNamespace + "la_correspondingAddress']:checked");	
	var sameAddress = $("input[type='radio'][name='" + portletNamespace + "la_isAddressSame']:checked");	
	
	if (correspondingAddress || sameAddress ) {
	    var correspondingAddress1 = correspondingAddress.val();
	 var sameAddress1 = sameAddress.val();
	 if(sameAddress1=='Y' || correspondingAddress1 == 'P'){
		 var s = document.getElementById(portletNamespace + 'la_pa_state');
			var st = s.options[s.selectedIndex];
			var stateName = st.getAttribute("data-name");
	 }else{
		 var s = document.getElementById(portletNamespace + 'la_ca_state');
			var st = s.options[s.selectedIndex];
			var stateName = st.getAttribute("data-name");
	 }
	}
	
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
			console.log("inside if::");
			var errorHelperDiv = document.getElementById(portletNamespace+"la_gstNumberHelper");	
			if(errorHelperDiv==null || errorHelperDiv == "null"){
				$("#"+portletNamespace+"gstinCommonValidationLA").html(gstinErrorMessage);
			}
			$("#"+portletNamespace+"la_gstNumber").addClass("error-field");
		} else{
			console.log("inside else::");
			$("#"+portletNamespace+"gstinCommonValidationLA").html('');
			$("#"+portletNamespace+"la_gstNumber").removeClass("error-field");
		}
		hideLoader();
	})
	.catch((e) => {
		console.log(e);
		hideLoader();
	})
}

function la_displayEmployerOthersField(_this){
	if(_this.value=='0'){
		document.getElementById("la_nameOfEmployer_others").style.display="";
		$("#"+portletNamespace+"la_nameOfEmployer_others").addClass("validate");
	} else {
		document.getElementById("la_nameOfEmployer_others").style.display="none";
		$("#"+portletNamespace+"la_nameOfEmployer_others").removeClass("validate");
	}
}
</script>

<style>
.la_gstNumber_custom {
    color: #dc3545 !important;
    font-size: 12px !important;
}
</style>