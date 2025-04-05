<%@ include file="init.jsp"%>

<portlet:actionURL name="quote_journey/submit/buy_form_2"
	var="actionURL" />
<portlet:actionURL name="add/suitabilityForm" var="suitabilityMatrixURL" />

<style>
.radio_container label {
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: inherit;
	width: auto;
	height: 34px;
	text-align: center;
	border-radius: 9999px;
	overflow: hidden;
	transition: linear 0.3s;
	border: 1px solid #cbcbcb;
	line-height: 25px;
	padding: 3px 25px;
	color: #999;
	text-transform: uppercase;
	font-size: 0.75rem;
	font-family: 'Montserrat-SemiBold';
	box-shadow: 1px 1px 13px 0 #00000021;
	cursor: pointer;
}

.radio_container input[type="radio"]:checked+label {
	transition: 0.3s;
	border: 1.2px solid #124093;
	background-color: #f0f0f0;
	color: #124093;
}
.wealth-modal.modal .modal-dialog {
    transform: translate(0, 50%) !important;
}
.div-flex {display: flex;align-items: baseline;}
.amt-words {font-size: 14px;}
</style>

<form name="generalForm" id="generalForm" method="POST"
	action="${actionURL}">
	<div class="row">
		<div class="col-md-6 col-12">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="how-do-we-address-you?" /></label>
			</div>
			<div class="radio_container">
				<input type="radio" id="sir"
					<c:if test="${cookieLeadValue['gender'] == 'Male' || empty cookieLeadValue['gender']}">checked </c:if>
					value="Male" name='<portlet:namespace/>gender'><label
					for="sir">Sir</label> <input type="radio" id="maam"
					<c:if test="${cookieLeadValue['gender'] == 'Female'}">checked </c:if>
					value="Female" name='<portlet:namespace/>gender'><label
					for="maam">Ma'am</label>
			</div>
		</div>


		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label" for='<portlet:namespace/>fullname'><liferay-ui:message
						key="your-name" /></label> <input type="text" class="form-control"
					value="${cookieLeadValue['fullName']}"
					id='<portlet:namespace/>fullName'
					name='<portlet:namespace/>fullName'
					oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
				${sessionScope.fullName} <span class="underline"></span>
			</div>
		</div>
		<input type="hidden" name='<portlet:namespace/>fromProductPage'
						id='<portlet:namespace/>fromProductPage'  value="${fromProductPage}"/>
		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label" for='<portlet:namespace/>dob'> <liferay-ui:message
						key="date-of-birth" />
				</label> <input type="text" class="form-control" maxlength="10"
					value="${cookieLeadValue['dateOfBirth']}"
					id='<portlet:namespace/>dateOfBirth'
					name='<portlet:namespace/>dateOfBirth'> <span
					class="underline"></span>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label" for='<portlet:namespace/>mobileNo'>
					<liferay-ui:message key="mobile-number" />
				</label> <input type="tel"
					class="form-control mob-pos basic-form-elem has-content formBuild"
					value="${cookieLeadValue['mobileNumber']}"
					id='<portlet:namespace/>mobileNumber'
					name='<portlet:namespace/>mobileNumber' maxlength='10'
					oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
					<span class='label-plchld'>+91</span>
				<span class="underline"></span>

			</div>
		</div>
		<div class="col-md-6 col-12">
			<div class="input-box">
				<label for='<portlet:namespace/>emailId' class="main-label">
					<liferay-ui:message key="email-address" />
				</label>
				<input type="email" class="form-control basic-form-elem formBuild email-auto-complete-et"
					value="${cookieLeadValue['email']}" id='<portlet:namespace/>email'
					name='<portlet:namespace/>email' oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');">
					 <span class="underline"></span>
			</div>
		</div>

		<div class="col-md-12 col-12">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="select-investment-objective" /></label>
			</div>
			<div class="radio_container">
				<c:forEach items="${selectiveObjectiveMap}" var="selectiveObjective">
					<input type="radio" id="${selectiveObjective.key}"
						value="${selectiveObjective.key}"
						<c:if test="${selectiveObjective.key == ((not empty cookieLeadValue['investmentObjective'])?cookieLeadValue['investmentObjective']:'growMoney')}"> checked </c:if>
						name='<portlet:namespace/>investmentObjective' />
					<label for="${selectiveObjective.key}">${selectiveObjective.value}</label>
				</c:forEach>

			</div>
		</div>
		<div class="col-sm-12">
			<div class="edto-check-content m-0">
				<div class="form-group">
					<input type="checkbox"
					id="isNriId" ${cookieLeadValue['isNRI']?'checked':''}
					name="<portlet:namespace/>isNri"> <label
					class="form-check-label" for="isNriId"> <liferay-ui:message
							key="nri-consent" /></label>
				</div>
				<div class="form-group">
					<input type="checkbox"
					id="sutabilityId" name='<portlet:namespace/>suitabiltyMatrix'
					<c:if test="${cookieLeadValue['suitabilityConsent'] == 'true' || empty cookieLeadValue['suitabilityConsent']}"> checked </c:if>>
					<label class="form-check-label" for="sutabilityId"> <liferay-ui:message
							key="suitability-consent" /></label>
				</div>
				<div class="form-group">
					<input type="checkbox"
					id="consenttermsandconditionId"
					name='<portlet:namespace/>consenttermsandcondition'
					readonly="readonly" value="Consent" onclick="return false"
					checked="checked"><label class="form-check-label" for="consenttermsandconditionId">
						<liferay-ui:message key="ndnc-consent" />
				</label>
				</div>
			</div>
	
		</div>

		<div class="edto-bullet1 mb-3 mt-3">
			<div>
				<button type="submit" class="edto-btn-primary" id='prequote-submit'
					name='prequote-submit'>
					<liferay-ui:message key="start-planning" />
				</button>
			</div>
		</div>
	</div>
	<div class="modal wealth-modal" tabindex="-1" role="dialog" id="nriModal"
		style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				
				<div class="modal-body">
					<div class="modal-header">
						<h5 class="modal-title">Self Declaration</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="row">
						<div class="col-12">
							<p>I hereby declare that I am an NRI and that in order to avail GST benefit I must provide</p>
							<p>
								1. Proof of foreign address</br>
								2. Filled and self attested GST declaration form</br>
								3. My payment shall be made via NRE account & bank details shall be provided</br>
							</p>
							<p>I understand that GST will only be refunded to me after the company has successfully verified all my documents. Failure to provide any of the above documents will lead to waiver of GST benefit.</p>
						</div>
						<div class="col-12 mt-2">
							<div class="row">
								<div class="col-12 col-md-5">
									<p>
										Foreign Contact Number:</br>
										(Please enter country code)
									</p>
								</div>
								<div class="col-12 col-md-7">
									<div class="row">
										<div class="col-md-4 col-12">
											<label class="custom-field two">
												<input type="text" onkeyup="if (/[^0-9-]|(^|\D)91/g.test(this.value)) this.value = this.value.replace(/[^0-9-]|(^|\D)91/g, '');"
												id='<portlet:namespace/>countryCode' maxlength='3'
												name='<portlet:namespace/>countryCode'>
												<span class="placeholder">Country
													Code</span>
											</label>
										</div>
										<div class="col-md-8 col-12">
											<label class="custom-field two">
												<input type="tel" oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"
												id='<portlet:namespace/>phoneNo' maxlength='11'
												name='<portlet:namespace/>phoneNo' value="${cookieLeadValue['nRIMobileNumber'] }">
												<span class="placeholder">Phone No</span>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="edto-btn-primary" class="close"
						data-dismiss="modal" id="isNriSubmitBtn">Continue</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Partner and UTM jsp -->
	<%@ include file="partner_utm_element.jsp"%>
</form>

<div class="modal wealth-modal" tabindex="-1" role="dialog"
	id="suitabilityModal" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h5 class="fs22 fontbold">
						<liferay-ui:message key="suitability-matrix" />
					</h5>
					<button type="button" id="suitabilityModalCloseIcon" class="close"
						data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form action="${suitabilityMatrixURL}" name="suitabilityForm"
					id="suitabilityForm" method="Post">
					<div class="row">
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="motion-label"><liferay-ui:message key="your-income" /></label>
								<select class="form-select form-control"
									id='<portlet:namespace/>annualIncome'
									name='<portlet:namespace/>annualIncome'>
									<option value="" disabled selected hidden>
										<liferay-ui:message key="" />
									</option>
									<c:forEach items="${suitabilityAnnualIncomeMap}"
										var="annualIncome">
										<option value="${annualIncome.key}">
											${annualIncome.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="motion-label"><liferay-ui:message key="risk-appetite" /></label>
								<select class="form-select form-control"
									id='<portlet:namespace/>riskAppetite'
									name='<portlet:namespace/>riskAppetite'>
									<option value="" disabled selected hidden><liferay-ui:message
												key="" /></option>
									<c:forEach items="${suitabilityRiskAppetitieMap}"
										var="riskAppetite">
										<option value="${riskAppetite.key}">
											${riskAppetite.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="motion-label"><liferay-ui:message key="investment-objective" /></label>
								<select class="form-select form-control"
									id='<portlet:namespace/>investmentObjective'
									name='<portlet:namespace/>investmentObjective'>
									<option value="" disabled selected hidden><liferay-ui:message
												key="" /></option>
									<c:forEach items="${selectiveObjectiveMap}"
										var="selectiveObjective">
										<option value="${selectiveObjective.key}">
											${selectiveObjective.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="col-md-6 col-12">
							<div class="input-box div-flex">
								<label for="otherInsurance" class="otherInsurance"><liferay-ui:message
										key="other-insurance" /></label>
								<div class="radio_container ml-3">
									<span> <input type="radio"
										name='<portlet:namespace/>otherInsurance'
										id='<portlet:namespace/>otherInsuranceYes' value="Yes">
										<label for='<portlet:namespace/>otherInsuranceYes'> <liferay-ui:message
												key="yes" /></label>
									</span> <span> <input type="radio"
										name='<portlet:namespace/>otherInsurance'
										id='<portlet:namespace/>otherInsuranceNo' value="No" checked>
										<label for='<portlet:namespace/>otherInsuranceNo'> <liferay-ui:message
												key="no" /></label>
									</span>
								</div>
							</div>
						</div>

						<div class="col-md-6 col-12 mb-3">
							<div class="input-box mb-2 mt-2">
								<div id="totalSumAssuredDiv">
									<label class="motion-label"><liferay-ui:message key="total-sum-assured" /></label>
									<input type="text" name='<portlet:namespace/>totalSumAssured'
										id='<portlet:namespace/>totalSumAssured' class="form-control"
										onkeypress="return /[0-9]/.test(event.key)"> <span
										class="underline"></span>
								</div>
							</div>
							<div class="amt-words" id="amt-in-word">
								<liferay-ui:message key="rupee-sign" />
							</div>
						</div>
						
					</div>
					<input type="hidden" name='<portlet:namespace/>suitabilityGender'
						id='<portlet:namespace/>suitabilityGender' /> <input
						type="hidden" name='<portlet:namespace/>suitabilityFullName'
						id='<portlet:namespace/>suitabilityFullName' /> <input
						type="hidden" name='<portlet:namespace/>suitablityMobileNumber'
						id='<portlet:namespace/>suitablityMobileNumber' /> <input
						type="hidden" name='<portlet:namespace/>suitabilityDateOfBirth'
						id='<portlet:namespace/>suitabilityDateOfBirth' /> <input
						type="hidden" name='<portlet:namespace/>suitabilityEmail'
						id='<portlet:namespace/>suitabilityEmail' />
					
					<div class="center-wealth-btn d-flex">
						<button type="submit" class="edto-btn-primary m-auto"
							id='suitability-submit' name='prequote-submit'>
							<liferay-ui:message key="continue" />
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="/error-modal.jsp"%>
<script type="text/javascript">
   var firstname="";
   var lastname="";
   var name="";
   var phone="";
   var gender="";
   var email="";
   var birthdate="";
   var lmsid="";
   var leadId="";
   var investmentObjective=""; 
   var incomeRange="";
   var smokerStatus="";
   var maritalStatus="";
   var spouseName="";
   var spouseDOB="";
   var spouseSmokerStatus="";
   var childStatus="";
   var childDOB="";
   var occupation="";
   var education="";
   var natureOfDuty="";

   var eventname="Buy Page";
   var returnurl=window.location.href;
   leadId = '${cookieLeadValue["leadId"]}';
   name=$('#<portlet:namespace/>fullName').val();
   firstname = name.split(' ')[0];
   lastname = name.split(' ')[1];
   phone=$('#<portlet:namespace/>mobileNumber').val();
   gender=$("input[name='<portlet:namespace/>gender']:checked").val();
   birthdate=$('#<portlet:namespace/>dateOfBirth').val();
   email=$('#<portlet:namespace/>email').val();
   incomeRange=$('#<portlet:namespace/>annualIncome').val()==null?'':$('#<portlet:namespace/>annualIncome').val();

   if(showErrorModal == "true" ){
	   	$('.error-msg').text('<liferay-ui:message key="unable-to-proceed"/>');
	   	$('.error-blk').removeClass('hide');
	   	$('.success-blk').addClass('hide');
	   	$('.align-items-center').addClass('hide');
	   	$('#errorModal').modal('show');
	   }
</script>
<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>