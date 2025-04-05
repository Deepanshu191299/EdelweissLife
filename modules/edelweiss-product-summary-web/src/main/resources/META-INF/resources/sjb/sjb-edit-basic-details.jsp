<%@ include file="/init.jsp"%>


<div class="modal wealth-modal" tabindex="-1" role="dialog"
	id="editModals" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h5 class="fs22 fontbold">Edit Basic Details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<form name="<portlet:namespace/>editSummaryForm" class="buyForm1"
						id="<portlet:namespace/>editSummaryForm" method="POST"
						action="${editBasicActionURL}">
						<div class="row">
							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>fullname'>Full
										Name</label> <input type="text" class="form-control"
										value="${summaryData['fullName']}"
										id='<portlet:namespace/>fullName'
										name='<portlet:namespace/>fullName'
										oninput="this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');">
									 <span class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>dateOfBirth'>
										Date Of Birth(dd/mm/yy) </label> <input type="text"
										class="form-control edelweiss-mask-date" maxlength="10"
										value="${summaryData['dateOfBirth']}"
										id='<portlet:namespace/>dateOfBirth'
										name='<portlet:namespace/>dateOfBirth'> <span
										class="underline"></span>
								</div>
							</div>
							<div class="col-md-6 col-12">
								<div class="input-box mb-2">
									<label>Gender</label>
								</div>
								<div class="radio_container">
									<input type="radio" id="male"
										<c:if test="${summaryData['gender'] == '3' || empty basicDetailsMap['gender']}">checked </c:if>
										value="3" name='<portlet:namespace/>gender'><label
										for="male">MALE</label> <input type="radio" id="female"
										<c:if test="${summaryData['gender'] == '4'}">checked </c:if>
										value="4" name='<portlet:namespace/>gender'><label
										for="female">FEMALE</label>
										<input type="radio" id="transgender"
					<c:if test="${summaryData['gender'] == '1802'}">checked </c:if>
					value="1802" name='<portlet:namespace/>gender'><label
					for="transgender">TRANSGENDER</label>
								</div>
							</div>
							<div class="col-md-6 col-12">
								<div class="input-box mt-2">
									<label class="main-label" for='<portletnamespace />occupation'>Your
										Occupation</label> <select class="form-select form-control"
										id='<portlet:namespace/>occupation'
										name='<portlet:namespace/>occupation' onchange="toggleExtraFields(this.value); setNatureDuty(this.value);">
										<%-- <option value="${basicDetailsMap['occupation']}" >${basicDetailsMap['occupation']}
					</option> --%>
										<c:forEach items="${youroccupationPicklist}" var="occupation">
											<option value="${occupation.key}"
												${summaryData['occupation'] == occupation.key ? 'selected' : '' }>${occupation.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>

			<div id="extraFields" class="row col-md-12 m-0 px-0 pt-3"  style="display: none">
			<c:set var="familyDetails" value="${summaryData.getCustomerFamilyDetailsRel() != null and summaryData.getCustomerFamilyDetailsRel().size()>0 ? summaryData.getCustomerFamilyDetailsRel().get(0) : null }" scope="request" />
			<div class="col-12 col-md-4">
			<div class="input-box mt-2">
			<label class="motion-label" for='<portletnamespace spouseParentRelation'>Select Your Spouse/Parent</label>
				<select class="form-select form-control"
					id='<portlet:namespace/>spouseParentRelation' name='<portlet:namespace/>spouseParentRelation'>
					<c:forEach items="${yourspouse}" var="spouse">
						<option value="${spouse.key}" ${familyDetails.getAssuredRelation() == spouse.key ? 'selected' : '' }>${spouse.value}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="col-12 col-md-4">
			<div class="input-box mt-2">
			<label class="motion-label" for='<portletnamespace />spouseParentOccupation'>Parent/Spouse's Occupation</label>
				<select class="form-select form-control"
					id='<portlet:namespace/>spouseParentOccupation'
					name='<portlet:namespace/>spouseParentOccupation'>
					<option value="" disabled selected hidden>
						
					</option>
					<c:forEach items="${spouseoccupation}" var="spouseoccupations">
						<option value="${spouseoccupations.key}" ${familyDetails.getOccupation() == spouseoccupations.key ? 'selected' : '' }>${spouseoccupations.value}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			<div class="col-12 col-md-4">
			<div class="input-box mt-2">
				<label class="motion-label" for='<portlet:namespace/>spouseParentSumassured'>Spouse/Parent Total Sum Assured</label> 
				<input type="text" oninput="this.value = this.value.replace(/[^0-9]/g, '');"
					class="form-control pl-3 formateAmount" id='<portlet:namespace/>spouseParentSumassured'
					value="${familyDetails.getTotalSumAssured()}"
					name='<portlet:namespace/>spouseParentSumassured'> 
					<label class="label-plchld" style="top: 9px !important;" ><liferay-ui:message key="rupee-sign" /></label>
					<span class="underline"></span>
			</div>
		</div>  
		</div>
	
							<div class="col-md-6 col-12">
								<div class="input-box mt-2">
									<label class="main-label"
										for='<portletnamespace />educationQualification'>Your
										Education Qualification</label> <select
										class="form-select form-control"
										id='<portlet:namespace/>educationQualification'
										name='<portlet:namespace/>educationQualification'>
										<c:forEach items="${youreducationalqualification}"
											var="educationQualification">
											<option value="${educationQualification.key}"
												${summaryData['educationQualification'] == educationQualification.key ? 'selected' : '' }>${educationQualification.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-6 col-12" id="somu">
								<div class="input-box mt-2">
									<label class="main-label"
										for='<portletnamespace />natureOfDuty'>Your Nature OF
										Duty</label> <select class="form-select form-control"
										id='<portlet:namespace/>natureOfDuty'
										name='<portlet:namespace/>natureOfDuty'>
										<c:forEach items="${yournatureofduty}" var="natureOfDuty">
											<option value="${natureOfDuty.key}"
												${summaryData['natureOfDuty'] == natureOfDuty.key ? 'selected' : '' }>${natureOfDuty.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label id="annualIncomes" class="main-label"
										for='<portlet:namespace/>annualIncome'>Your Annual
										Income</label> <input type="text" data-amt-in-words="annualIncomeInWords"
										value="${summaryData['annualIncome'] }" class="form-control pl-3 formateAmount amt-in-word"
										id='<portlet:namespace/>annualIncome' oninput="this.value = this.value.replace(/[^0-9]/g, '');"
										name='<portlet:namespace/>annualIncome'>
										<label class="label-plchld label-down" ><liferay-ui:message key="rupee-sign" /></label>
										 <span class="underline"></span>
										 <span style="font-size:small;" id="annualIncomeInWords"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>pincode'>Your
										Pincode</label> <input type="text" class="form-control"
										id='<portlet:namespace/>pincode'
										value="${summaryData['pincode'] }" oninput="this.value = this.value.replace(/[^0-9]/g, '').substring(0, 6);"
										name='<portlet:namespace/>pincode'> <span
										class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>mobileNo'>
										Mobile Number </label> <input type="tel"
										class="form-control mob-pos basic-form-elem has-content formBuild"
										value="${summaryData['mobileNumber']}"
										id='<portlet:namespace/>mobileNumber'
										name='<portlet:namespace/>mobileNumber' maxlength='10'>
									<span class='label-plchld label-down'>+91</span> <span class="underline"></span>

								</div>
							</div>
							<div class="col-md-6 col-12">
								<div class="input-box">
									<label for='<portlet:namespace/>emailId' class="main-label">
										E-mail Adress </label> <input type="email"
										class="form-control basic-form-elem formBuild"
										value="${summaryData['email']}" id='<portlet:namespace/>email'
										name='<portlet:namespace/>email' oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');"> 
										<span class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box mb-2">
									<label>Do You Smoke?</label>
								</div>
								<div class="radio_container">
									<input type="radio" id="yes"
										<c:if test="${summaryData['smoker'] == '1' || empty basicDetailsMap['smoker']}">checked </c:if>
										value="1" name='<portlet:namespace/>smoker'><label
										for="yes">YES</label> <input type="radio" id="no"
										<c:if test="${summaryData['smoker'] == '0'}">checked </c:if>
										value="0" name='<portlet:namespace/>smoker'><label
										for="no">NO</label>
								</div>
								<span style="font-size:small;">Select "yes" if you have smoked in the last 12 months</span>
							</div>
						</div>
						<div class="center-wealth-btn">
									<input type="submit" id="save" value="Save"
										class="edto-btn-primary" />
								</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<style>
.wealth-form-box .label-down {
    top:32.5px !important; 
}
</style>

<script>
var annualIncomeVal = document.getElementById("annualIncomes");
var rupeeSignLabel = "<liferay-ui:message key='rupee-sign' />";
const chooseSpouse = {
		h: ['1327'],
		st: ['1328', '1329'],
	};
	$(document).ready(function() {
		var value = document.getElementById(portletNamespace + "occupation");
		var selectedValue = value.value;
		if (selectedValue === 'h' || selectedValue === 'st') {
			$('#extraFields').show();
			annualIncomeVal.innerText = "Parent/Spouse's Annual Income";
		}
		updateChooseSpouse();
	});
		function toggleExtraFields(value) {
			var extraFields = document.getElementById("extraFields");
			if (value === "h" || value==="st") {
				extraFields.style.display = "flex";
				annualIncomeVal.innerText = "Parent/Spouse's Annual Income";
				updateChooseSpouse();
				$( "#extraFields input,#extraFields select " ).each(function() {
					if($(this).val()==="" || $(this).val()===null ){
					$(this).parent().children('.motion-label').removeClass("motion-label-heading")
				        console.log($(this).parent().children('.motion-label'));
					}
					else{
					$(this).parent().children('.motion-label').addClass("motion-label-heading")
					}
					});
			} else {
				extraFields.style.display = "none";
				annualIncomeVal.innerText = "Your Annual Income";
				/*
				 * Reset the fields
				 */
				const extraFieldsDiv = document.getElementById('extraFields');
				const inputFields = extraFieldsDiv.querySelectorAll('input, select');

				inputFields.forEach((field) => {
					field.value = '';
				});
			}
		} 
		
	
		function setNatureValue(dropdownName, selectedValue){
			
			for (var i = 0; i < dropdownName.options.length; i++) {
				if (dropdownName.options[i].text === selectedValue) {
					dropdownName.selectedIndex = i;
					break;
				}
			}
			
		} 
		function updateChooseSpouse() {
			const occupation = document.getElementById(portletNamespace+'occupation');
			const spouse = document.getElementById(portletNamespace+'spouseParentRelation');
			const selectedOccupation = occupation.value;
			const selectedValue = spouse.value;
			if (selectedOccupation in chooseSpouse) {
				const allowedOptions = chooseSpouse[selectedOccupation];
				for (let option of spouse.options) {
					option.style.display = allowedOptions.includes(option.value) ? 'block' : 'none'; 
					option.disabled  = allowedOptions.includes(option.value) ? false : true;
				}
				if( spouse.querySelector("option[value='"+selectedValue+"']") && spouse.querySelector("option[value='"+selectedValue+"']").style.display == "none"){
					spouse.value = chooseSpouse[selectedOccupation][0];
				}
			} 
			
			else {
				for (let option of spouse.options) {
					option.style.display = 'block';
					option.disabled  = false;
				}
			}
		}
		
			  $( "#extraFields input,#extraFields select " ).each(function() {
					if($(this).val()==="" || $(this).val()===null ){
					$(this).parent().children('.motion-label').removeClass("motion-label-heading")
				        console.log($(this).parent().children('.motion-label'));
					}
					else{
					$(this).parent().children('.motion-label').addClass("motion-label-heading")
					}
					});
				$( "#extraFields input" ).click(function() {
					  $(this).parent().children('.motion-label').addClass("motion-label-heading")
					});
				$( "#extraFields select" ).mousedown(function() {
					  $(this).parent().children('.motion-label').addClass("motion-label-heading")
					});
				
				/*
				 * Update Nature Of Duty Drodpwn based on Occupation
				 */
				function setNatureDuty(selectedOccupationValue){
					var selectedOccupation = $("#"+portletNamespace+"occupation option[value='"+selectedOccupationValue+"']").text().toLowerCase().trim();
					var matchednatureOfDutyOption = $("#"+portletNamespace+"natureOfDuty option").filter(function() {
					    return $(this).text().toLowerCase().trim() == selectedOccupation
					  });
					if(matchednatureOfDutyOption && matchednatureOfDutyOption.length>0&& (selectedOccupationValue=="st" || selectedOccupationValue=="h")){
					    $("#"+portletNamespace+"natureOfDuty option").hide();
					    $("#"+portletNamespace+"natureOfDuty option[value='"+matchednatureOfDutyOption.val()+"']").show();
					    $("#"+portletNamespace+"natureOfDuty").val(matchednatureOfDutyOption.val());
					}else{
						$("#"+portletNamespace+"natureOfDuty option").show();
					}
				}
				setNatureDuty($("#"+portletNamespace+"occupation").val());
	

const basicDetailsForm = document.getElementById("<portlet:namespace/>editSummaryForm");
const submitButton = document.getElementById("save");

submitButton.addEventListener("click", function(event) {
	event.preventDefault();
	$('#editModals').modal("hide");
	$("input.formateAmount").each(function() {
		$(this).val($(this).val().replaceAll(",",""));
	});
 
	basicDetailsForm.submit();
});
				
</script>
