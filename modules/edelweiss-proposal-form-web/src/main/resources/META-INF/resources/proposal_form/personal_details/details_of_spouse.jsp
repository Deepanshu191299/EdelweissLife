<%@ include file="../../init.jsp"%>

<div id='details_of_spouse' class="communication-details">
	<h5><liferay-ui:message key="label-personal-details-for-spouse" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<p>
				<liferay-ui:message key="label-titleSpouse" />
			</p>
			<div class="radio_container">
				<c:forEach var="titleData" items="${masterMap['Title']}">
					<aui:input name='spouse_title' id="spouse_title_${titleData.name}"
						cssClass="validate spouse_title-val-${titleData.value}" type="radio" label="" required="required"
						value="${titleData.value}"
						disabled="true"
						checked="${spousePersonalDetails.titleDataId == titleData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','spouse_title')">
					</aui:input>
					<label for='<portlet:namespace/>spouse_title_${titleData.name}'>
						${titleData.name} </label>
				</c:forEach>
			</div>
	
			<div class="location-field">
				<div class="row">
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_firstName"
									onInput="validateRadioButtonsOnChange('personal_details_form','spouse_firstName')"
									placeholder="&nbsp;" label="" cssClass="validate" type="text"
									value="${spousePersonalDetails.firstName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-first-name" /></span>
							</label>
						</div>
					</div>
		
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_middleName"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${spousePersonalDetails.middleName}" maxLength="60" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-middle-name" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_lastName"
									onInput="validateRadioButtonsOnChange('personal_details_form','spouse_lastName')"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${spousePersonalDetails.lastName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
									<aui:validator name="required"></aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-last-name" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_fullName"
									onInput="validateRadioButtonsOnChange('personal_details_form','spouse_fullName')"
									placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
									value="${spousePersonalDetails.fullName}" readOnly="readOnly"
									maxLength="30" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-full-nameSpouse" /></span>
							</label>
						</div>
					</div>
				
					<div class="col-md-4 col-12">
						<div class="form-box">
							<c:if test="${not empty spousePersonalDetails.dob}">
								<fmt:setLocale value="en-US" />
								<fmt:parseDate var="spouse_parsedDob" pattern="yyyy-MM-dd" value="${spousePersonalDetails.dob}" />
								<fmt:formatDate var="spouse_formattedDob" value='${spouse_parsedDob}' pattern='dd-MM-yyyy' />
							</c:if>
							<label class="custom-field two"> <aui:input
									onInput="validateRadioButtonsOnChange('personal_details_form','spouse_dateOfBirth')"	
									name="spouse_dateOfBirth" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text" readOnly="readOnly"
									value="${spouse_formattedDob != null ? spouse_formattedDob : ''}" 
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
					<aui:input name='spouse_gender' id="spouse_gender_${genderData.name}"
						cssClass="validate" type="radio" label="" required="required"
						value="${genderData.value}" readOnly="readOnly"
						checked="${spousePersonalDetails.genderDataId == genderData.value ? true : false}"
						disabled="${spousePersonalDetails.genderDataId != genderData.value ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','spouse_gender')">
					</aui:input>
					<label for='<portlet:namespace/>spouse_gender_${genderData.name}'>
						${genderData.name} </label>
				</c:forEach>
			</div>
	
	
			<p>
				<liferay-ui:message key="label-marital-status" />
			</p>
			<div class="radio_container">
				<c:forEach var="maritalStatusData" items="${masterMap['Marital Status']}">
					<aui:input name='spouse_maritalStatus'
						id="spouse_maritalStatus_${maritalStatusData.name}" cssClass="validate spouseValidate spouse_maritalStatus-Input"
						type="radio" label="" required="required"
						value="${maritalStatusData.value}"
						checked="${spousePersonalDetails.maritalStatusDataId == maritalStatusData.value ? true : false}"
						disabled="${isLaPrSame && isSpouseExist && !(isWSP) ? true : false}"
						onChange="validateRadioButtonsOnChange('personal_details_form','spouse_maritalStatus')">
						<aui:validator name="required" errorMessage="marital-status-error-message"></aui:validator>
					</aui:input>
					<label for='<portlet:namespace/>spouse_maritalStatus_${maritalStatusData.name}'>
						${maritalStatusData.name} </label>
				</c:forEach>
			</div>
		
			<div class="location-field">
				<div class="row">
					<c:if test="${displayABHANoField}">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> <aui:input
									onkeyup="validateRadioButtonsOnChange('personal_details_form','spouse_abhaNo')"
										name="spouse_abhaNo" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text"
										value="${spousePersonalDetails.abhaNumber}" maxLength="14"
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

					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_panNo"
									placeholder="&nbsp;" label="" cssClass="validate valphanum pan-number-field" type="text"
									value="${spousePersonalDetails.panNumber}" maxLength="10" minLength="10" 
									wrappedField="<%=true %>" style="text-transform: uppercase" 
									onInput="validateRadioButtonsOnChange('personal_details_form','spouse_panNo')">
									<aui:validator name="required" errorMessage="Please provide PAN number">
										function() {
											var nriChecked = document.getElementById(portletNamespace+'spouse_nationality_Nri').checked;												
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
											
											if (isValidPan) {
												$("#spouse_runKYC").removeClass("disable-btn");
												$("#spouse_runKYC").removeClass('disabled');
												$("#spouse_runKYC").prop("disabled", false);
											} else {
												$("#spouse_runKYC").addClass("disable-btn");
												$("#spouse_runKYC").prop("disabled", true);
											}
											
											return isValidPan;
										 }
									</aui:validator>
									<aui:validator name="custom" errorMessage="same-pan-number-error-message">
										function(val, fieldNode, ruleValue) {
										
											var laPanNo = $("#<portlet:namespace/>la_panNo").val();
											var proposerPanNo = $("#<portlet:namespace/>proposer_panNo").val();
											
											if((val == laPanNo) || (val == proposerPanNo)){
												return false;
											}else{
												return true;
											}
										 }
									</aui:validator>
									<aui:validator name="custom" errorMessage="please-run-ckyc">
										function(val, fieldNode, ruleValue) {
											
											if(isSpouseCkycProceed){
												return true;
											}else{
												return false;
											}
										 }
									</aui:validator>
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-pan-number" /></span>
										
								<button type="button" id="spouse_runKYC" class="disable-btn disabled kyc-btn" disabled = "true"
									onclick="populatePANDetails('spouse_', '${spousePersonalDetails.dob}');">
									<liferay-ui:message key="label-run-ckyc" />
									<img src="/o/edelweisstokio-theme/images/praposal/info-co.png"
										alt="icon">
								</button>
							</label>
						</div>
					</div>
					
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="spouse_fatherName" placeholder="&nbsp;" label=""
									cssClass="validate valpha" type="text" 
									value="${(spousePersonalDetails.fatherName != null) 
									&& (spousePersonalDetails.fatherName != '') ? 
									spousePersonalDetails.fatherName : spousePersonalDetails.spouseName}" 
									maxLength="60" minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-father-name" /></span>
							</label>
						</div>
					</div>
					<div class="col-md-4 col-12">
						<div class="form-box">
							<label class="custom-field two"> <aui:input name="spouse_motherName"
									placeholder="&nbsp;" label="" cssClass="validate" type="text"
									value="${spousePersonalDetails.motherName}" maxLength="60" minLength="0" wrappedField="<%=true %>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="label-mother-name" /></span>
							</label>
						</div>
					</div>
					
				</div>
			</div>
		
			<p>
				<liferay-ui:message key="label-nationality" />
			</p>
			<div class="radio_container">
				<c:forEach var="nationalityData" items="${masterMap['Nationality']}">
					<c:if test="${nationalityData.value != 8}">
						<aui:input name='spouse_nationality'
							id="spouse_nationality_${nationalityData.name}"
							cssClass="validate" type="radio" label="" required="required"
							value="${nationalityData.value}"
							checked="${spousePersonalDetails.nationalityDataId == nationalityData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','spouse_nationality')">
							<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>spouse_nationality_${nationalityData.name}'>
							${nationalityData.name} </label>
					</c:if>
				</c:forEach>
			</div>
			<p>
				<liferay-ui:message key="label-age-proof-submitted" />
			</p>
			<div class="radio_container">
				<c:forEach var="ageProofData" items="${masterMap['Age Proof']}">
					<c:if test="${ageProofData.value != 2032}">
						<aui:input name='spouse_ageProof'
							id="spouse_ageProof_${ageProofData.name}" cssClass="validate"
							type="radio" label="" required="required"
							value="${ageProofData.value}"
							checked="${spousePersonalDetails.ageProofDataId == ageProofData.value ? true : false}"
							onChange="validateRadioButtonsOnChange('personal_details_form','spouse_ageProof')">
							<aui:validator name="required" errorMessage="age-proof-error-meesage"></aui:validator>
						</aui:input>
						<label
							for='<portlet:namespace/>spouse_ageProof_${ageProofData.name}'>
							${ageProofData.name} </label>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>	
</div>
<script type="text/javascript">	
	$(".spouse_maritalStatus-Input").click(function(){
		
		var isSpouseEducationFieldActive = $("#isSpouseEducationFieldActive").val();
		
		var maritalStatusInput = $(this).val();

		var spouseGenderDataId = ${spousePersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		var transGenderDataId = 1802;
		var companyGenderDataId = 1970;
		
		var maritalStatSingle = 13;
		var maritalStatMarried = 14;
		var maritalStatWidow = 15;
		var maritalStatDivorcee = 16;
		
		var educationQualificationDataId = 22;
		
		if(isSpouseEducationFieldActive == "false"){
			if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow || maritalStatusInput == maritalStatDivorcee) && (spouseGenderDataId == maleGenderDataId)){
				//single, married, widow, divorcee male
				spouseHandleFieldDisabled("spouse_title-val-1");
			}else if( (maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee) && (spouseGenderDataId == feMaleGenderDataId)){
				//single female
				spouseHandleFieldDisabled("spouse_title-val-2");
			}else if( (maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (spouseGenderDataId == feMaleGenderDataId)){
				//married or widow female
				spouseHandleFieldDisabled("spouse_title-val-124");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (spouseGenderDataId == transGenderDataId)){
				//checked m/s while as company
				spouseHandleFieldDisabled("spouse_title-val-1803");
			}else if((maritalStatusInput == maritalStatSingle || maritalStatusInput == maritalStatDivorcee || maritalStatusInput == maritalStatMarried || maritalStatusInput == maritalStatWidow) && (spouseGenderDataId == companyGenderDataId)){
				//checked m/s while as company
				spouseHandleFieldDisabled("spouse_title-val-1879");
			}
		}
	});
	
	$( document ).ready(function() {
		var userAgeInNum = calculateAge($('input[name="<portlet:namespace/>spouse_dateOfBirth"]').val());
		var spouseGenderDataId = ${spousePersonalDetails.genderDataId};
		var maleGenderDataId = 3;
		var feMaleGenderDataId = 4;
		if(userAgeInNum < 18 && (spouseGenderDataId == maleGenderDataId)){
			//minor male
			//checked master
			spouseHandleFieldDisabled("spouse_title-val-1297");
		}else if(userAgeInNum < 18 && spouseGenderDataId == feMaleGenderDataId){
			//minor female	
			//checked ms.
			spouseHandleFieldDisabled("spouse_title-val-2");
		}
	});
	
	function spouseHandleFieldDisabled(checkedClassName){
		//checked the field
		$("."+checkedClassName).prop("checked",true);
		//disabled the rem field
		$(".spouse_title-val-1, .spouse_title-val-2, .spouse_title-val-124, .spouse_title-val-1295, .spouse_title-val-1297, .spouse_title-val-1803, .spouse_title-val-1879").not("."+checkedClassName).prop("disabled",true);
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
//CKYC Implementation for Spouse
var isSpouseCkycProceed = false;
</script>