<div class="modal wealth-modal" tabindex="-1" role="dialog" id="editModal" style="display: none;">
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
					<form name="<portlet:namespace/>editSummaryForm"
						id="<portlet:namespace/>editSummaryForm" class="buyForm1" method="POST" action="">
						<div class="row">
							<div class="col-md-6 col-12">                                 
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>fullname'>Full Name</label> 
									<input type="text" class="form-control"
										value="${basicDetailsMap['fullName']}"
										data-submitted-value="${basicDetailsMap['fullName']}"
										id='<portlet:namespace/>fullName'
										name='<portlet:namespace/>fullName'
										oninput="this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');">
									<span class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>dateOfBirth'> Date Of Birth(dd/mm/yy) </label> 
									<input type="text" class="form-control edelweiss-mask-date" maxlength="10"
										value="${basicDetailsMap['dateOfBirth']}"
										data-submitted-value="${basicDetailsMap['dateOfBirth']}"
										id='<portlet:namespace/>dateOfBirth'
										name='<portlet:namespace/>dateOfBirth'> 
									<span class="underline"></span>
								</div>
							</div>
							<div class="col-md-6 col-12">
								<div class="input-box mb-2"></div>
								<label class="main-label" for='<portlet:namespace/>gender'>Gender</label>
								<div class="radio_container">
									<input type="radio" id="male"
										<c:if test="${basicDetailsMap['gender'] == '3' || empty basicDetailsMap['gender']}">checked data-submitted-value="checked"</c:if>
										value="3" name='<portlet:namespace/>gender'>
									<label for="male">MALE</label> 
									<input type="radio" id="female"
										<c:if test="${basicDetailsMap['gender'] == '4'}">checked data-submitted-value="checked"</c:if>
										value="4" name='<portlet:namespace/>gender'>
									<label for="female">FEMALE</label>
									<input type="radio" id="transgender"
										<c:if test="${basicDetailsMap['gender'] == '1802'}">checked data-submitted-value="checked"</c:if>
										value="1802" name='<portlet:namespace/>gender'>
									<label for="transgender">TRANSGENDER</label>
								</div>
							</div>
							<div class="col-md-6 col-12" >
								<div class="input-box mt-2">
									<label class="main-label" for='<portletnamespace />occupation'>Your Occupation</label>
									<select class="form-select form-control"
										id='<portlet:namespace/>occupation'
										name='<portlet:namespace/>occupation' data-submitted-value="${basicDetailsMap['occupation'] }">
										<c:forEach items="${youroccupationPicklist}" var="occupation">
											<option value="${occupation.key}" ${basicDetailsMap['occupation'] == occupation.key ? 'selected' : '' }>${occupation.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div id="extraFields" class="row col-md-12 m-0 px-0 pt-3"  style="display: none">
							<div class="col-12 col-md-4">
								<div class="input-box mt-2">
									<label class="motion-label " for='<portletnamespace />spouseParentRelation'>Select Your Spouse/Parent</label>
									<select class="form-select form-control" id='<portlet:namespace/>spouseParentRelation' 
										name='<portlet:namespace/>spouseParentRelation' 
										data-submitted-value='${familyDetailsJSON.getString("assuredRelation") }'>
										<c:forEach items="${yourspouse}" var="spouse">
											<option value="${spouse.key}" ${familyDetailsJSON.getString("assuredRelation") == spouse.key ? 'selected' : '' }>${spouse.value}</option>
										</c:forEach>
									</select>
								</div>
								</div>
								<div class="col-12 col-md-4">
								<div class="input-box mt-2">
									<label class="motion-label" for='<portletnamespace />spouseParentOccupation'>Parent/Spouse's Occupation</label>
									<select class="form-select form-control"
										id='<portlet:namespace/>spouseParentOccupation' name='<portlet:namespace/>spouseParentOccupation' 
										data-submitted-value='${familyDetailsJSON.getString("occupation") }'>
										<c:forEach items="${spouseoccupation}" var="spouseoccupations">
											<option value="${spouseoccupations.key}" ${familyDetailsJSON.getString("occupation") == spouseoccupations.key ? 'selected' : '' }>${spouseoccupations.value}</option>
										</c:forEach>
									</select>
								</div>
								</div>
								<div class="col-12 col-md-4">
									<div class="input-box mt-2">
										<label class="motion-label " for='<portlet:namespace/>spouseParentSumassured'>Spouse/Parent Total Sum Assured</label> 
										<input type="text"
											class="form-control pl-3 formateAmount" id='<portlet:namespace/>spouseParentSumassured'
											value='${familyDetailsJSON.getString("totalSumAssured")}' oninput="this.value = this.value.replace(/[^0-9]/g, '');"
											data-submitted-value='${familyDetailsJSON.getString("totalSumAssured")}'
											name='<portlet:namespace/>spouseParentSumassured'>
										<label class="label-plchld" style="top: 9px !important;"><liferay-ui:message key="rupee-sign" /></label>
										<span class="underline"></span>
									</div>
								</div>
							</div> 

							<div class="col-md-6 col-12"  >
								<div class="input-box mt-2">
									<label class="main-label" for='<portletnamespace />educationQualification'>Your Education Qualification</label>
									<select class="form-select form-control"
										id='<portlet:namespace/>educationQualification' name='<portlet:namespace/>educationQualification' 
										data-submitted-value="${basicDetailsMap['educationQualification'] }">
										<c:forEach items="${youreducationalqualification}"
										var="educationQualification">
											<option value="${educationQualification.key}" ${basicDetailsMap['educationQualification'] == educationQualification.key ? 'selected' : '' }>${educationQualification.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6 col-12" id="somu">
								<div class="input-box mt-2">
									<label class="main-label" for='<portletnamespace />natureOfDuty'>Your Nature Of Duty</label>
									<select class="form-select form-control"
										id='<portlet:namespace/>natureOfDuty' 
										name='<portlet:namespace/>natureOfDuty' data-submitted-value="${basicDetailsMap['natureOfDuty'] }">
										<c:forEach items="${yournatureofduty}" var="natureOfDuty">
											<option value="${natureOfDuty.key}" ${basicDetailsMap['natureOfDuty'] == natureOfDuty.key ? 'selected' : '' }>${natureOfDuty.value}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label id="annualIncomes" class="main-label" for='<portlet:namespace/>annualIncome'>Your Annual Income</label> 
									<input type="text"
										value="${basicDetailsMap['annualIncome'] }" data-submitted-value="${basicDetailsMap['annualIncome'] }"
										class="form-control pl-3 formateAmount amt-in-word" id='<portlet:namespace/>annualIncome' oninput="this.value = this.value.replace(/[^0-9]/g, '');"
										name='<portlet:namespace/>annualIncome' data-amt-in-words="annualIncomeInWords"> 
									<label class="label-plchld label-down" ><liferay-ui:message key="rupee-sign" /></label>
									<span class="underline"></span>
									<span style="font-size:small;" id="annualIncomeInWords"></span>
									
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>pincode'>Your Pincode</label> <input type="text" class="form-control"
										id='<portlet:namespace/>pincode' oninput="this.value = this.value.replace(/[^0-9]/g, '').substring(0, 6);"
										value="${basicDetailsMap['pincode'] }" data-submitted-value="${basicDetailsMap['pincode'] }"
										name='<portlet:namespace/>pincode'>
										<span class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box">
									<label class="main-label" for='<portlet:namespace/>mobileNo'>
										Mobile Number
									</label>
									<input type="tel"
										class="form-control mob-pos basic-form-elem has-content formBuild"
										value="${basicDetailsMap['mobileNumber']}" data-submitted-value="${basicDetailsMap['mobileNumber'] }"
										id='<portlet:namespace/>mobileNumber'
										name='<portlet:namespace/>mobileNumber' maxlength='10'> 
										<span class='label-plchld'>+91</span>
										<span class="underline"></span>

								</div>
							</div>
							<div class="col-md-6 col-12">
								<div class="input-box">
									<label for='<portlet:namespace/>emailId' class="main-label">
										E-mail Adress
									</label> 
									<input type="email" class="form-control basic-form-elem formBuild"
										value="${basicDetailsMap['email']}" id='<portlet:namespace/>email' data-submitted-value="${basicDetailsMap['email'] }"
										name='<portlet:namespace/>email'
										oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');">
										<span class="underline"></span>
								</div>
							</div>

							<div class="col-md-6 col-12">
								<div class="input-box mb-2">
									<label>Do You Smoke?</label>
								</div>
								<div class="radio_container">
									<input type="radio" id="yes"
									<c:if test="${basicDetailsMap['smoker'] == '1' || empty basicDetailsMap['smoker']}">checked data-submitted-value="checked"</c:if>
									value="1" name='<portlet:namespace/>smoker'>
									<label for="yes">YES</label>

									<input type="radio" id="no"
									<c:if test="${basicDetailsMap['smoker'] == '0'}">checked data-submitted-value="checked"</c:if>
									value="0" name='<portlet:namespace/>smoker'>
									<label for="no">NO</label>
								</div>
								<span style="font-size:small;">Select "yes" if you have smoked in the last 12 months</span>
							</div>
						</div>
						<div class="center-wealth-btn">
									<input type="button" id="save" value="Save" class="edto-btn-primary" /> 
								</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>



