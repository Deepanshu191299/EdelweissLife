<%@ include file="../../init.jsp"%>

<div id='details_of_proposer' class="communication-details">
	<h5><liferay-ui:message key="label-personal-details-for-proposer" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p>
				<liferay-ui:message key="label-title" />
			</p>
			<div class="radio_container">
				<c:forEach var="titleData" items="${masterMap['Title']}">
					<aui:input name='proposer_title' id="proposer_title_${titleData.name}"
						cssClass="validate proposer_title-val-${titleData.value}" type="radio" label="" required="required"
						value="${titleData.value}"
						disabled="true"
						checked="${proposerPersonalDetails.titleDataId == titleData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','proposer_title')">
					</aui:input>
					<label for='<portlet:namespace/>proposer_title_${titleData.name}'>
						${titleData.name} </label>
				</c:forEach>
			</div>
	
			<div class="location-field">
				<div class="row">
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_firstName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${proposerPersonalDetails.firstName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-first-name" /></span>
							</label>
						</div>
					</div>
		
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_middleName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${proposerPersonalDetails.middleName}" maxLength="60" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-middle-name" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_lastName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${proposerPersonalDetails.lastName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-last-name" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_fullName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${proposerPersonalDetails.fullName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-full-nameSpouse" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<c:if test="${not empty proposerPersonalDetails.dob}">
							    <fmt:setLocale value="en-US" />
								<fmt:parseDate var="proposer_parsedDob" pattern="yyyy-MM-dd" value="${proposerPersonalDetails.dob}" />
								<fmt:formatDate var="proposer_formattedDob" value='${proposer_parsedDob}' pattern='dd-MM-yyyy' />
							</c:if>
							<label class="custom-field two"> <aui:input
									name="proposer_dateOfBirth" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text" readOnly="readOnly"
									value="${proposer_formattedDob != null ? proposer_formattedDob : ''}"
									maxLength="10" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-date-of-birthSpouse" /></span>
										<!--<p class="pb-0 nominee-date-icon"> <img src="/o/edelweisstokio-theme/images/praposal/date.png" alt="icon"></p>-->
							</label>
						</div>
					</div>
				</div>
			</div>
		
			<p>
				<liferay-ui:message key="label-genderSpouse" />
			</p>
			<div class="radio_container">
				<c:forEach var="genderData" items="${masterMap['Gender']}">
					<aui:input name='proposer_gender' id="proposer_gender_${genderData.name}"
						cssClass="validate" type="radio" label="" required="required"
						value="${genderData.value}" readOnly="readOnly"
						checked="${proposerPersonalDetails.genderDataId == genderData.value ? true : false}"
						disabled="${proposerPersonalDetails.genderDataId != genderData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','proposer_gender')">
					</aui:input>
					<label for='<portlet:namespace/>proposer_gender_${genderData.name}'>
						${genderData.name} </label>
				</c:forEach>
			</div>
	
	
			<p>
				<liferay-ui:message key="label-marital-status" />
			</p>
			<div class="radio_container">
				<c:forEach var="maritalStatusData" items="${masterMap['Marital Status']}">
					<aui:input name='proposer_maritalStatus'
						id="proposer_maritalStatus_${maritalStatusData.name}" cssClass="validate spouseValidate proposer_maritalStatus-Input"
						type="radio" label="" required="required"
						value="${maritalStatusData.value}"
						checked="${proposerPersonalDetails.maritalStatusDataId == maritalStatusData.value ? true : false}"
						disabled="${isLaPrSame && isSpouseExist ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','proposer_maritalStatus')">
						<aui:validator name="required" errorMessage="marital-status-error-message"></aui:validator>
					</aui:input>
					<label for='<portlet:namespace/>proposer_maritalStatus_${maritalStatusData.name}'>
						${maritalStatusData.name} </label>
				</c:forEach>
			</div>
		
			<div class="location-field">
				<div class="row">
					<c:if test="${displayABHANoField}">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','proposer_abhaNo')"
										name="proposer_abhaNo" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text"
										value="${proposerPersonalDetails.abhaNumber}" maxLength="14"
										minLength="14" wrappedField="<%=true%>"
										style="text-transform: uppercase">
										<aui:validator name="minLength" errorMessage="abha-error-message">14</aui:validator>
										<aui:validator name="maxLength" errorMessage="abha-error-message">14</aui:validator>
									</aui:input> <span class="placeholder"> <liferay-ui:message
											key="label-abha-number" />
								</span>
								<div class="image-container">
							        <img src="/o/edelweisstokio-theme/images/praposal/info-co.png" alt="icon">
							        <div class="tooltip"><liferay-ui:message
										key="abha-tooltip-meesage" />  <a target="_blank" href="<liferay-ui:message
										key="abha-tooltip-link" />"><liferay-ui:message
										key="abha-tooltip-link" /></a></div>
							    </div>
								<div class="error-for-digit hide" style="color:#dc3545;font-size:11.2px;"><liferay-ui:message
									key="abha-min-digit-error-meesage" /></div>
								</label>
							</div>
						</div>
					</c:if>

					<!-- LDXP-1013 Changes Starts  -->
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="proposer_fatherName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" 
									value="${(proposerPersonalDetails.fatherName != null) 
									&& (proposerPersonalDetails.fatherName != '') ? 
									proposerPersonalDetails.fatherName : proposerPersonalDetails.spouseName}" 
									maxLength="60" minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-father-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_motherName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${proposerPersonalDetails.motherName}" maxLength="60" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-mother-name" /></span>
							</label>
						</div>
					</div>


					<!-- EKYC Related Changes  -->	
					<div class="col-md-4 col-12">
						<div class="form-box">
							<span><a class="edto-btn-primary mt-3 eKYC-button ${ekycSuccess_proposer=='true'?'disable':''}" href="javascript:void(0);" onclick="callProteanEkycApi('proposer');">
								<liferay-ui:message key="label-run-ekyc" />
							</a></span>				
						</div>
					</div>
						
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="proposer_panNo"
									placeholder="&nbsp;" label="" cssClass="validate valphanum" type="text"
									value="${proposerPersonalDetails.panNumber}" maxLength="10" minLength="10" 
									wrappedField="<%=true %>" style="text-transform: uppercase" 
									onInput="validateRadioButtonsOnChange('personal_details_form','proposer_panNo')">
									<aui:validator name="required" errorMessage="Please provide PAN number">
										function() {
										var nriChecked = document.getElementById(portletNamespace+'proposer_nationality_Nri').checked;												
												if(nriChecked){
													nriChecked = false;
												} else{
													nriChecked = true;
												}
												return nriChecked;
											}
									</aui:validator>
									<aui:validator name="custom" errorMessage="valid-pan-number-error-message">
										function(val, fieldNode, ruleValue) {
											var panNum = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$/;  
											var isValidPan = panNum.test(val);
											var ekycSuccess_proposer='${ekycSuccess_proposer}';

											console.log("ekycSuccess_proposer : "+ekycSuccess_proposer);
											
											return isValidPan;
										 }
									</aui:validator>
									<aui:validator name="custom" errorMessage="same-pan-number-error-message">
										function(val, fieldNode, ruleValue) {
											var laPanNo = $("#<portlet:namespace/>la_panNo").val();
											var spousePanNo = $("#<portlet:namespace/>spouse_panNo").val();
											
											if((val == laPanNo) || (val == spousePanNo)){
												return false;
											}else{
												return true;
											}
										 }
									</aui:validator>
									<aui:validator name="custom" errorMessage="please-run-ckyc">
										function(val, fieldNode, ruleValue) {
											var isProposerEkycSuccess = $("#ekycSuccess_proposer").val();
											if(isProposerEkycSuccess == 'false' && !isProposerCkycProceed){
												console.log("ckyc false");
												$("#proposer_runKYC").removeClass("disable-btn").prop('disabled',false);
												return false;
											}else{
												console.log("ckyc true");
												$("#proposer_runKYC").addClass("disable-btn").prop('disabled',true);
												return true;
											}
										 }
									</aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-pan-number" /></span>
								
								<button type="button" id="proposer_runKYC" class="disable-btn disabled kyc-btn" disabled = "true"
									onclick="populatePANDetails('proposer_', '${proposerPersonalDetails.dob}');">
									<liferay-ui:message key="label-run-ckyc" />
									<img src="/o/edelweisstokio-theme/images/praposal/info-co.png"
										alt="icon">
								</button>
							</label>
						</div>
					</div>
					<!-- LDXP-1013 Changes Ends  -->
					
				</div>
			</div>
		
			<p>
				<liferay-ui:message key="label-nationality" />
			</p>
			<div class="radio_container">
				<c:forEach var="nationalityData" items="${masterMap['Nationality']}">
					<c:if test="${nationalityData.value != 8}">
						<aui:input name='proposer_nationality'
							id="proposer_nationality_${nationalityData.name}"
							cssClass="validate" type="radio" label="" required="required"
							value="${nationalityData.value}"
							checked="${proposerPersonalDetails.nationalityDataId == nationalityData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','proposer_nationality'); proposer_updateDisabledAttribute(this);">
							<aui:validator name="required" errorMessage="state-error-meesage"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>proposer_nationality_${nationalityData.name}'>
							${nationalityData.name} </label>
					</c:if>
				</c:forEach>
			</div>
			<!-- LDXP-1140 Start -->
			<c:if test="${ekycSuccess_proposer!='true'}">
			<p>
				<liferay-ui:message key="label-age-proof-submitted" />
			</p>
			<div class="radio_container">
				<c:forEach var="ageProofData" items="${masterMap['Age Proof']}">
					<c:if test="${ageProofData.value != 2032}">
						<aui:input name='proposer_ageProof'
							id="proposer_ageProof_${ageProofData.name}" cssClass="validate"
							type="radio" label="" required="required"
							value="${ageProofData.value}"
							checked="${proposerPersonalDetails.ageProofDataId == ageProofData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','proposer_ageProof')">
							<aui:validator name="required" errorMessage="age-proof-error-meesage"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>proposer_ageProof_${ageProofData.name}'>
							${ageProofData.name} </label>
					</c:if>
				</c:forEach>
			</div>
			</c:if>
			<!-- LDXP-1140 End -->		
		</div>
	</div>	
</div>

<script type="text/javascript">	
	$(".proposer_maritalStatus-Input").click(function(){
		
		var isProposerEducationFieldActive = $("#isProposerEducationFieldActive").val();
		
		var maritalStatusInput = $(this).val();
		
		var proposerGenderDataId = ${proposerPersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		var transGenderDataId = 1802;
		var companyGenderDataId = 1970;
		
		var maritalStatSingle = 13;
		var maritalStatMarried = 14;
		var maritalStatWidow = 15;
		var maritalStatDivorcee = 16;
		
		var educationQualificationDataId = 22;
		
		if(isProposerEducationFieldActive == "false"){
			if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow || maritalStatusInput == maritalStatDivorcee) && (proposerGenderDataId == maleGenderDataId)){
				//single, married, widow, divorcee male
				proposerHandleFieldDisabled("proposer_title-val-1");
			}else if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee) && (proposerGenderDataId == feMaleGenderDataId)){
				//single female
				proposerHandleFieldDisabled("proposer_title-val-2");
			}else if( (maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (proposerGenderDataId == feMaleGenderDataId)){
				//married or widow female
				proposerHandleFieldDisabled("proposer_title-val-124");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (proposerGenderDataId == transGenderDataId)){
				//checked m/s while as company
				proposerHandleFieldDisabled("proposer_title-val-1803");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (proposerGenderDataId == companyGenderDataId)){
				//checked m/s while as company
				proposerHandleFieldDisabled("proposer_title-val-1879");
			}
		}
	});
	
	$( document ).ready(function() {
		var proposerGenderDataId = ${proposerPersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		var userAgeInNum = calculateAge($('input[name="<portlet:namespace/>proposer_dateOfBirth"]').val());
		
		if(userAgeInNum < 18 && (proposerGenderDataId == maleGenderDataId)){
			//minor male
			//checked master
			proposerHandleFieldDisabled("proposer_title-val-1297");
		}else if(userAgeInNum < 18 && proposerGenderDataId == feMaleGenderDataId){
			//minor female	
			//checked ms.
			proposerHandleFieldDisabled("proposer_title-val-2");
		}
	});
	
	function proposerHandleFieldDisabled(checkedClassName){
		//checked the field
		$("."+checkedClassName).prop("checked",true);
		//disabled the rem field
		$(".proposer_title-val-1, .proposer_title-val-2, .proposer_title-val-124, .proposer_title-val-1295, .proposer_title-val-1297, .proposer_title-val-1803, .proposer_title-val-1879").not("."+checkedClassName).prop("disabled",true);
	}
	

	function calculateAge(dateString) {
	    // Parse the date string into a Date object
	    var parts = dateString.split("/");
	    var day = parseInt(parts[0], 10);
	    var month = parseInt(parts[1], 10);
	    var year = parseInt(parts[2], 10);
	    
	    // Get the current date
	    var currentDate = new Date();
	    var currentYear = currentDate.getFullYear();
	    var currentMonth = currentDate.getMonth() + 1; // Month is zero-based
	    var currentDay = currentDate.getDate();

	    // Calculate age
	    var age = currentYear - year;
	    
	    // Adjust age if the birthday hasn't occurred yet this year
	    if (currentMonth < month || (currentMonth === month && currentDay < day)) {
	        age--;
	    }
	    
	    return age;
	}
</script>

<script>
$(document).ready(function () {
	   var proposerNationality = $('input[name="'+ namespace + 'proposer_nationality"]:checked').val();
	   var taxResidencyCheck = '<%= renderRequest.getAttribute("laTaxResidency") %>';
	   console.log("proposerNationality::" , proposerNationality + "taxResidencyCheck:::" + taxResidencyCheck);
	    
	    proposer_updateDisabledAttribute(proposerNationality,taxResidencyCheck);
	    
	    // Attach change event listener to the radio buttons
	    $('input[name="'+ namespace + 'proposer_nationality"]').change(function() {
	        var proposerNationality = $('input[name="'+ namespace +'proposer_nationality"]:checked').val();
	       
	        proposer_updateDisabledAttribute(proposerNationality);
	    });
	});
	
function proposer_updateDisabledAttribute(proposerNationality,taxResidencyCheck){
	
	console.log("inside proposer_updateDisabledAttribute function::", proposerNationality + "....." + taxResidencyCheck );

    var isWP = <%= renderRequest.getAttribute("isWP") %>;
    var isWRP = <%= renderRequest.getAttribute("isWRP") %>;
    var isWGP = <%= renderRequest.getAttribute("isWGP") %>;
    var isCSP = <%= renderRequest.getAttribute("isCSP") %>;
    var isIB = <%= renderRequest.getAttribute("isIB") %>;
    var isAI = <%= renderRequest.getAttribute("isAI") %>;
    var isSJB = <%= renderRequest.getAttribute("isSJB") %>;
    var isWSP = <%= renderRequest.getAttribute("isWSP") %>;
    
    var isLaPrSame = <%= renderRequest.getAttribute("isLaPrSame") %>;
    
    var laTaxResidenceDeclarationYes = document.getElementById(portletNamespace +'la_tax_residence_declaration_yes');
   	var laTaxResidenceDeclarationNo = document.getElementById(portletNamespace +'la_tax_residence_declaration_no');
   	
	var la_tax_residence_declaration_yes =  document.getElementById(portletNamespace +'la_tax_residence_declaration_yes');
   	var la_tax_residence_declaration_no =  document.getElementById(portletNamespace +'la_tax_residence_declaration_no');
   	
    if((isLaPrSame == false) && (isWP || isWRP || isWGP || isCSP || isIB || isAI || isSJB || isWSP) == true){
    	if(proposerNationality === '5'){
    		laTaxResidenceDeclarationYes.disabled = true;
            laTaxResidenceDeclarationNo.disabled = true;
            
            la_tax_residence_declaration_yes.checked = true;
            la_tax_residence_declaration_no.checked = false;
    	} else {
    		laTaxResidenceDeclarationYes.disabled = false;
            laTaxResidenceDeclarationNo.disabled = false;
            
            if(taxResidencyCheck === 'Y'){
            	la_tax_residence_declaration_yes.checked = true;
                la_tax_residence_declaration_no.checked = false;
             } else {
            	 la_tax_residence_declaration_yes.checked = false;
                la_tax_residence_declaration_no.checked = true;
        	} 
            
    	}    	
    } else {
    	laTaxResidenceDeclarationYes.disabled = true;
        laTaxResidenceDeclarationNo.disabled = true;
    }
}
</script>

<script>
//CKYC Implementation for Proposer
var isProposerCkycProceed = false;
$(document).ready(function() {
	var isProposerEkycFailed = $("#ekycSuccess_proposer").val();
	if(isProposerEkycFailed == 'false'){
	    $("#proposer_runKYC").removeClass("disable-btn").prop('disabled',false);
	}else{
		$("#proposer_runKYC").addClass("disable-btn").prop('disabled',true);
	}
});
</script>