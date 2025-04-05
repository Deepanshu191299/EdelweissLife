<%@ include file="init.jsp"%>

<portlet:actionURL name="quote_journey/submit/buy_form_4"
	var="actionURL" />
<portlet:actionURL name="add/suitabilityForm" var="suitabilityMatrixURL" />

<form name="generalForm" id="generalForm" method="POST"
	action="${actionURL}">
	<div class="row">

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
		<div class="col-md-6 col-12 radio-wrapper">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="gender" /></label>
			</div>
			<div class="radio_container">
				<input type="radio" id="sir"
					<c:if test="${cookieLeadValue['gender'] == 'Male' || empty cookieLeadValue['gender']}">checked </c:if>
					value="Male" name='<portlet:namespace/>gender'>
					<label for="sir">Male</label> 
					<input type="radio" id="maam"
					<c:if test="${cookieLeadValue['gender'] == 'Female'}">checked </c:if> value="Female" name='<portlet:namespace/>gender'>
					<label for="maam">Female</label>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label" for='<portlet:namespace/>dob'>
				<liferay-ui:message key="date-of-birth" />
				</label> <input type="text" class="form-control" maxlength="10"
					value="${cookieLeadValue['dateOfBirth']}"
					id='<portlet:namespace/>dateOfBirth'
					name='<portlet:namespace/>dateOfBirth'><span
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
				<span class='label-plchld'>+91</span> <span class="underline"></span>

			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label">Annual Income Range</label>
				<select id='<portlet:namespace/>Income'
					name='<portlet:namespace/>Income'class="mt-0 form-select form-control">
					<option value="" disabled selected hidden><liferay-ui:message key="" /></option>
					<c:forEach items="${zpAnnualIncomeMap}" var="options">
						<option value="${options.key}" 
							<c:if test="${cookieLeadValue['annualIncome'] == options.key }">selected </c:if> >${options.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label for='<portlet:namespace/>emailId' class="main-label">
					<liferay-ui:message key="email-address" />
				</label> <input type="email" class="form-control basic-form-elem formBuild email-auto-complete-et"
					value="${cookieLeadValue['email']}" id='<portlet:namespace/>email'
					name='<portlet:namespace/>email' oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');"> 
					<span class="underline"></span>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="do-you-smoke?" /></label>
			</div>
			<div class="radio_container">
				<input type="radio" id="yes" value="1" 
					<c:if test="${cookieLeadValue['smoker'] == '1'}">checked </c:if>
					name='<portlet:namespace/>smoke'><label for="yes">Yes</label>
				<input type="radio" id="no" value="0" 
					<c:if test="${cookieLeadValue['smoker'] == '0' || empty cookieLeadValue['smoker']}">checked </c:if>
					name='<portlet:namespace/>smoke'><label for="no">No</label>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="are-you-married?" /></label>
			</div>
			<div class="radio_container mb-2">
				<input type="radio" id="marriedYes" value="true" 
					<c:if test="${cookieLeadValue['married'] == 'true' || empty cookieLeadValue['married']}">checked </c:if>
					name='<portlet:namespace/>MaritalStatusId'><label for="marriedYes">Yes</label>
				<input type="radio" id="marriedNo" value="false" 
					<c:if test="${cookieLeadValue['married'] == 'false'}">checked </c:if>
					name='<portlet:namespace/>MaritalStatusId'><label for="marriedNo">No</label>
			</div>
		</div>
		<div id="<portlet:namespace/>marriageDetails" style='display: none;' class="row col-md-12 m-0 p-0">
			<div class="col-md-6 col-12">
				<div class="input-box">
					<label class="main-label" for='<portlet:namespace/>SpouseName'><liferay-ui:message
							key="spouse-name" /></label> <input type="text" class="form-control"
						value="${cookieLeadValue['spouseName']}" id='<portlet:namespace/>SpouseName'
						name='<portlet:namespace/>SpouseName'
						oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
					${sessionScope.fullName} <span class="underline"></span>
				</div>
			</div>

			<div class="col-md-6 col-12">
				<div class="input-box">
					<label class="main-label" for='<portlet:namespace/>SpouseDob'>
						<liferay-ui:message key="spouse-date-of-birth" />
					</label> <input type="text" class="form-control" maxlength="10" 
						value="${cookieLeadValue['spouseDateOfBirth']}"
						id='<portlet:namespace/>SpouseDob'
						name='<portlet:namespace/>SpouseDob'> <span
						class="underline"></span>
				</div>
			</div>
			<div class="col-md-6 col-12">
				<div class="input-box mb-2">
					<label><liferay-ui:message key="do-your-spouse-smoke?" /></label>
				</div>
				<div class="radio_container mb-2">
					<input type="radio" id="spouseSmokeYes" value="138"
						<c:if test="${cookieLeadValue['spouseSmoke'] == '138'}">checked </c:if>
						name='<portlet:namespace/>SpouseSmoke'><label
						for="spouseSmokeYes">Yes</label> <input type="radio"
						id="spouseSmokeNo" value="137" 
						<c:if test="${cookieLeadValue['spouseSmoke'] == '137' || empty cookieLeadValue['spouseSmoke']}">checked </c:if>
						name='<portlet:namespace/>SpouseSmoke'><label
						for="spouseSmokeNo">No</label>
				</div>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box mb-2">
				<label><liferay-ui:message key="do-you-have-a-child?" /></label>
			</div>
			<div class="radio_container mb-2">
				<input type="radio" id="childYes" value="yes" <c:if test="${cookieLeadValue['child'] == 'yes'}">checked </c:if>
					name='<portlet:namespace/>ChildName'><label for="childYes">Yes</label>
				<input type="radio" id="childNo" value="no" <c:if test="${cookieLeadValue['child'] == 'no' || empty cookieLeadValue['child']}">checked </c:if>
					name='<portlet:namespace/>ChildName'><label for="childNo">No</label>
			</div>
		</div>
		<div id="<portlet:namespace/>childDetails" style='display: none;' class="col-md-6 m-0 p-0">
			<div class="col-12">
				<div class="input-box">
					<label class="main-label" for='<portlet:namespace/>child-dob'>
						<liferay-ui:message key="child-date-of-birth" />
					</label> <input type="text" class="form-control" maxlength="10" 
						value="${cookieLeadValue['childDateOfBirth']}"
						id='<portlet:namespace/>ChildDob'
						name='<portlet:namespace/>ChildDob'> <span
						class="underline"></span>
				</div>
			</div>
		</div>

		<div class="col-sm-12">
			<div class="edto-check-content m-0">
				<div class="form-group">
					<input type="checkbox" id="isNriId"
						${cookieLeadValue['isNRI']?'checked':''}
						name="<portlet:namespace/>isNri"> <label
						class="form-check-label" for="isNriId"> <liferay-ui:message
							key="nri-consent" /></label>
				</div>
				<div class="form-group">
					<input type="checkbox" id="sutabilityId"
						name='<portlet:namespace/>suitabiltyMatrix' 
						readonly="readonly" onclick="return false" 
						checked="checked"><label class="form-check-label" 
						for="sutabilityId"> <liferay-ui:message
							key="suitability-consent" /></label>
				</div>
				<div class="form-group">
					<input type="checkbox" id="consenttermsandconditionId"
						name='<portlet:namespace/>consenttermsandcondition'
						readonly="readonly" value="Consent" onclick="return false"
						checked="checked"><label class="form-check-label"
						for="consenttermsandconditionId"> <liferay-ui:message
							key="ndnc-consent" />
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
	<div class="modal wealth-modal" tabindex="-1" role="dialog"
		id="nriModal" style="display: none;">
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
							<p>I hereby declare that I am an NRI and that in order to
								avail GST benefit I must provide</p>
							<p>
								1. Proof of foreign address</br> 2. Filled and self attested GST
								declaration form</br> 3. My payment shall be made via NRE account &
								bank details shall be provided</br>
							</p>
							<p>I understand that GST will only be refunded to me after
								the company has successfully verified all my documents. Failure
								to provide any of the above documents will lead to waiver of GST
								benefit.</p>
						</div>
						<div class="col-12 mt-2">
							<div class="row">
								<div class="col-12 col-md-5">
									<p>
										Foreign Contact Number:</br> (Please enter country code)
									</p>
								</div>
								<div class="col-12 col-md-7">
									<div class="row">
										<div class="col-md-4 col-12">
											<label class="custom-field two"> <input type="text"
												onkeyup="if (/[^0-9-]|(^|\D)91/g.test(this.value)) this.value = this.value.replace(/[^0-9-]|(^|\D)91/g, '');"
												id='<portlet:namespace/>countryCode' maxlength='3'
												name='<portlet:namespace/>countryCode'> <span
												class="placeholder">Country Code</span>
											</label>
										</div>
										<div class="col-md-8 col-12">
											<label class="custom-field two"> <input type="tel"
												oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');"
												id='<portlet:namespace/>phoneNo' maxlength='11'
												name='<portlet:namespace/>phoneNo'
												value="${cookieLeadValue['nRIMobileNumber'] }"> <span
												class="placeholder">Phone No</span>
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

<script>

var incomeVal= $("#<portlet:namespace/>Income").val();
if(incomeVal==="" || incomeVal===null ){
$(".motion-label").removeClass("motion-label-heading")
}
else{
$(".motion-label").addClass("motion-label-heading")
}

if(showErrorModal == "true" ){
   	$('.error-msg').text('<liferay-ui:message key="unable-to-proceed"/>');
   	$('.error-blk').removeClass('hide');
   	$('.success-blk').addClass('hide');
   	$('.align-items-center').addClass('hide');
   	$('#errorModal').modal('show');
   }
</script>
<script type="text/javascript">
   var productName = '${productName}';
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
   var income="";
   

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
   spouseSmokerStatus=$('input[name="<portlet:namespace/>SpouseSmoke"]:checked').val()==137?'Yes':'No';   
   maritalStatus=$('input[name="<portlet:namespace/>MaritalStatusId"]:checked').val()=="true"?'Yes':'No';
   childStatus=$('input[name="<portlet:namespace/>ChildName"]:checked').val()=="yes"?'Yes':'No';
   income=$('#<portlet:namespace/>Income').val();
   spouseName=$('#<portlet:namespace/>SpouseName').val();
   spouseDOB=$('#<portlet:namespace/>SpouseDob').val();
   childDOB=$('#<portlet:namespace/>ChildDob').val();
   
</script>
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

.div-flex {
	display: flex;
	align-items: baseline;
}

.amt-words {
	font-size: 14px;
}
</style>
<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>