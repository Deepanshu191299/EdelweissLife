<%@ include file="../../init.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id='details_of_la' class="communication-details">
	<h5><liferay-ui:message key="label-details-of-life-assured" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p>
				<liferay-ui:message key="label-title" />
			</p>
			<div class="radio_container">
				<c:forEach var="titleData" items="${masterMap['Title']}">					
					<aui:input name='la_title' id="la_title_${titleData.name}"
						cssClass="validate la_title-val-${titleData.value}" type="radio" label=""
						disabled="true"
						required="required" value="${titleData.value}"
						checked="${laPersonalDetails.titleDataId == titleData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','la_title')">
					</aui:input>
					<label for='<portlet:namespace/>la_title_${titleData.name}'>
						${titleData.name} 
					</label>
				</c:forEach>
			</div>

			<div class="location-field">
				<div class="row">
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_firstName')"
									name="la_firstName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" maxLength="30"
									value="${laPersonalDetails.firstName}" readOnly="readOnly"
									minLength="0" wrappedField="<%=true%>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-first-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_middleName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" 
									value="${laPersonalDetails.middleName}" maxLength="60"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-middle-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input onkeyup="validateRadioButtonsOnChange('personal_details_form','la_lastName')"
									name="la_lastName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" readOnly="readOnly"
									value="${laPersonalDetails.lastName}" maxLength="30"
									minLength="0" wrappedField="<%=true%>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-last-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_fullName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" readOnly="readOnly"
									value="${laPersonalDetails.fullName}" maxLength="30"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-full-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<c:if test="${not empty laPersonalDetails.dob}">
							    <fmt:setLocale value="en-US" />
								<fmt:parseDate var="la_parsedDob" pattern="yyyy-MM-dd" value="${laPersonalDetails.dob}" />
								<fmt:formatDate var="la_formattedDob" value='${la_parsedDob}' pattern='dd-MM-yyyy' />
							</c:if>
							<label class="custom-field two"> <aui:input
									name="la_dateOfBirth" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text" readOnly="readOnly"
									value="${la_formattedDob != null ? la_formattedDob : ''}" 
									maxLength="10" minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-date-of-birth" /></span>
										<!--<p class="pb-0 nominee-date-icon"> <img src="/o/edelweisstokio-theme/images/praposal/date.png" alt="icon"></p>-->
							</label>
						</div>
					</div>
				</div>
			</div>

			<p>
				<liferay-ui:message key="label-gender" />
			</p>
			<div class="radio_container">
				<c:forEach var="genderData" items="${masterMap['Gender']}">
					<aui:input name='la_gender' id="la_gender_${genderData.name}"
						cssClass="validate" type="radio" label="" required="required"
						value="${genderData.value}" readOnly="readOnly"
						checked="${laPersonalDetails.genderDataId == genderData.value ? true : false}"
						disabled="${laPersonalDetails.genderDataId != genderData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','la_gender')">
					</aui:input>
					<label for='<portlet:namespace/>la_gender_${genderData.name}'>
						${genderData.name} </label>
				</c:forEach>
			</div>

			<c:if test="${!(isInfant || isLaMinor) }">
				<p>
					<liferay-ui:message key="label-marital-status" />
				</p>
				<div class="radio_container">
					<c:forEach var="maritalStatusData"
						items="${masterMap['Marital Status']}">
						<aui:input name='la_maritalStatus'
							id="la_maritalStatus_${maritalStatusData.name}"
							cssClass="validate spouseValidate maritalStatus-Input" type="radio" label="" required="required"
							value="${maritalStatusData.value}"
							checked="${laPersonalDetails.maritalStatusDataId == maritalStatusData.value ? true : false}"
							disabled="${isLaPrSame && isSpouseExist && !(isWSP) ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','la_maritalStatus')">
							<aui:validator name="required" errorMessage="marital-status-error-message"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>la_maritalStatus_${maritalStatusData.name}'>
							${maritalStatusData.name} </label>
					</c:forEach>
					
				</div>
			</c:if>

			<div class="location-field">
				<div class="row">
					<c:if test="${displayABHANoField}">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','la_abhaNo')"
										name="la_abhaNo" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text"
										value="${laPersonalDetails.abhaNumber}" maxLength="14"
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
										key="abha-tooltip-meesage" /> <a target="_blank" href="<liferay-ui:message
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
									name="la_fatherName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" 
									value="${(laPersonalDetails.fatherName != null) 
									&& (laPersonalDetails.fatherName != '') ? 
									laPersonalDetails.fatherName : laPersonalDetails.spouseName}" 
									maxLength="60" minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-father-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_motherName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" 
									value="${laPersonalDetails.motherName}" maxLength="60"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-mother-name" /></span>
							</label>
						</div>
					</div>
														
					
					<c:if test="${!(isInfant || isLaMinor) }">				
						<!-- EKYC Related Changes  -->	
						<div class="col-md-4 col-12">
							<div class="form-box">
								<span>
									<span id="run-ekyc-for-la" class="d-none">la</span>
									<input type="hidden" id="isEkycFailed" value="${isEkyc_la_failed != null ? isEkyc_la_failed : false}">
									<a class="edto-btn-primary mt-3 eKYC-button ${ekycSuccess_la=='true'?'disable':''}" href="javascript:void(0);" onclick="callProteanEkycApi('la');">
										<liferay-ui:message key="label-run-ekyc" />
									</a>
								</span>
							</div>
						</div>
						
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
										name="la_panNo" placeholder="&nbsp;" label=""
										cssClass="validate valphanum" type="text"
										value="${laPersonalDetails.panNumber}" maxLength="10"
										minLength="10" wrappedField="<%=true%>"
										style="text-transform: uppercase" 
										onInput="validateRadioButtonsOnChange('personal_details_form','la_panNo')"
										required="">
										<aui:validator name="required"
											errorMessage="Please provide PAN number">
											function() {
											
												var nriChecked = document.getElementById(portletNamespace+'la_nationality_Nri').checked;												
												if(nriChecked){
													nriChecked = false;
												} else{
													nriChecked = true;
												}
												return nriChecked;
											}
										</aui:validator>
										<aui:validator name="custom"
											errorMessage="valid-pan-number-error-message">
										function(val, fieldNode, ruleValue) {
											var panNum = /^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$/;  
											var isValidPan = panNum.test(val);
											var ekycSuccess_la='${ekycSuccess_la}';
											
											console.log("ekycSuccess_la : "+ekycSuccess_la);
											
											
											return isValidPan;
										 }
									</aui:validator>
										<aui:validator name="custom"
											errorMessage="same-pan-number-error-message">
										function(val, fieldNode, ruleValue) {
											var proposerPanNo = $("#<portlet:namespace />proposer_panNo").val();
											var spousePanNo = $("#<portlet:namespace />spouse_panNo").val();
											
											if((val == proposerPanNo) || (val == spousePanNo)){
												return false;
											}else{
												return true;
											}
										 }
									</aui:validator>

									<aui:validator name="custom"
											errorMessage="please-run-ckyc">
										function(val, fieldNode, ruleValue) {
											var isLaEkycFailed = $("#isEkycFailed").val();
											
											if(isLaEkycFailed == 'false' || isLaCkycProceed){
												$("#la_runKYC").addClass("disable-btn").prop('disabled',true);
												return true;
											}else{
											    $("#la_runKYC").removeClass("disable-btn").prop('disabled',false);
											    return false;
											}
										 }
									</aui:validator>
									</aui:input> <span class="placeholder"> <liferay-ui:message
											key="label-pan-number" />
								</span>
									<button type="button" id="la_runKYC" class="disable-btn disabled kyc-btn" disabled = "true"
										onclick="populatePANDetails('la_', '${laPersonalDetails.dob}');">
										<liferay-ui:message key="label-run-ckyc" />
										<img src="/o/edelweisstokio-theme/images/praposal/info-co.png"
											alt="icon">
									</button>
								</label>
							</div>
						</div>
					</c:if>
					<!-- LDXP-1013 Changes Ends  -->
				</div>
			</div>

			<p>
				<liferay-ui:message key="label-nationality" />
			</p>
			<div class="radio_container">
				<c:forEach var="nationalityData" items="${masterMap['Nationality']}">
					<c:if test="${nationalityData.value != 8}">
						<aui:input name='la_nationality'
							id="la_nationality_${nationalityData.name}" cssClass="validate"
							type="radio" label="" required="required"
							value="${nationalityData.value}"
							checked="${laPersonalDetails.nationalityDataId == nationalityData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','la_nationality'); la_updateDisabledAttribute(this);">
							<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>la_nationality_${nationalityData.name}'>
							${nationalityData.name} </label>
					</c:if>
				</c:forEach>
			</div>
			<!-- LDXP-1140 Start -->
			<c:if test="${ekycSuccess_la!='true'}">
			<p>
				<liferay-ui:message key="label-age-proof-submitted" />
			</p>
			<div class="radio_container">
				<c:forEach var="ageProofData" items="${masterMap['Age Proof']}">
					<c:if test="${ageProofData.value != 2032}">
						<aui:input name='la_ageProof'
							id="la_ageProof_${ageProofData.name}" cssClass="validate"
							type="radio" label="" required="required"
							value="${ageProofData.value}"
							checked="${laPersonalDetails.ageProofDataId == ageProofData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','la_ageProof')">
							<aui:validator name="required" errorMessage="age-proof-error-meesage"></aui:validator>
						</aui:input>
						<label for='<portlet:namespace/>la_ageProof_${ageProofData.name}'>
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
	$(".maritalStatus-Input").click(function(){
		
		var isLaEducationFieldActive = $("#isLaEducationFieldActive").val();
		
		var maritalStatusInput = $(this).val();
		
		var laGenderDataId = ${laPersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		var transGenderDataId = 1802;
		var companyGenderDataId = 1970;
		
		var maritalStatSingle = 13;
		var maritalStatMarried = 14;
		var maritalStatWidow = 15;
		var maritalStatDivorcee = 16;
		
		if(isLaEducationFieldActive == "false"){
			if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow || maritalStatusInput == maritalStatDivorcee) && (laGenderDataId == maleGenderDataId)){
				//single, married, widow, divorcee male
				laHandleFieldDisabled("la_title-val-1");
			}else if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee) && (laGenderDataId == feMaleGenderDataId)){
				//single female
				laHandleFieldDisabled("la_title-val-2");
			}else if( (maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (laGenderDataId == feMaleGenderDataId)){
				//married or widow female
				laHandleFieldDisabled("la_title-val-124");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (laGenderDataId == transGenderDataId)){
				//checked m/s while as company
				laHandleFieldDisabled("la_title-val-1803");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (laGenderDataId == companyGenderDataId)){
				//checked m/s while as company
				laHandleFieldDisabled("la_title-val-1879");
			}
		}
	});
	
	$( document ).ready(function() {
		var userAgeInNum = calculateAge($('input[name="<portlet:namespace/>la_dateOfBirth"]').val());
		var laGenderDataId = ${laPersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		if(userAgeInNum < 18 && (laGenderDataId == maleGenderDataId)){
			//minor male
			//checked master
			laHandleFieldDisabled("la_title-val-1297");
		}else if(userAgeInNum < 18 && laGenderDataId == feMaleGenderDataId){
			//minor female	
			//checked ms.
			laHandleFieldDisabled("la_title-val-2");
		}
	});
	
	function laHandleFieldDisabled(checkedClassName){
		//checked the field
		$("."+checkedClassName).prop("checked",true);
		//disabled the rem field
		$(".la_title-val-1, .la_title-val-2, .la_title-val-124, .la_title-val-1295, .la_title-val-1297, .la_title-val-1803, .la_title-val-1879").not("."+checkedClassName).prop("disabled",true);
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
   
   var laNationality = $('input[name="'+ namespace + 'la_nationality"]:checked').val();
   var taxResidencyCheck = '<%= renderRequest.getAttribute("laTaxResidency") %>';
   console.log("taxResidencyCheck:::", taxResidencyCheck + "laNationality::", laNationality);
   la_updateDisabledAttribute(laNationality,taxResidencyCheck); 
    
 // Attach change event listener to the radio buttons
    $('input[name="'+ namespace + 'la_nationality"]').change(function() {
        var laNationality = $('input[name="'+ namespace +'la_nationality"]:checked').val();
        la_updateDisabledAttribute(laNationality);
    });
});


function la_updateDisabledAttribute(laNationality,taxResidencyCheck){
	
	console.log("inside la_updateDisabledAttribute function::", laNationality + "....." + taxResidencyCheck );

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
   	
    if((isLaPrSame == true) && (isWP || isWRP || isWGP || isCSP || isIB || isAI || isSJB || isWSP) == true){
    	if(laNationality === '5'){
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
//CKYC Implementation for LA
var isLaCkycProceed = false;
$(document).ready(function() {
	var isLaEkycFailed = $("#isEkycFailed").val();
	if(isLaEkycFailed == 'false'){
		$("#la_runKYC").addClass("disable-btn").prop('disabled',true);
	}else{
	    $("#la_runKYC").removeClass("disable-btn").prop('disabled',false);
	}
});
</script>