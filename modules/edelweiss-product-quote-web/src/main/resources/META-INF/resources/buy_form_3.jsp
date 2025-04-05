<%@ include file="init.jsp"%>

<portlet:actionURL name="add/buy_journey" var="actionURL" />
<style>
.div-flex {
	display: flex;
	align-items: baseline;
}

.amt-words {
	font-size: 14px;
}
#generalForm label.label-plchld.label-down {
    top:9px !important;
}
label.motion-label.sumAssured {
    width:100%;
}
</style>
<form name="generalForm" id="generalForm" class="buyForm1" method="POST" action="${actionURL}">
	<div class="row">
		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="main-label" for='<portlet:namespace/>fullname'><liferay-ui:message key="sjb-fullName" /></label>
				<input type="text" class="form-control"
					value="${cookieLeadValue['fullName']}"
					id='<portlet:namespace/>fullName'
					name='<portlet:namespace/>fullName'
					oninput="this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');">
				<span class="underline"></span>
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
			<div class="input-box mb-2">
				<label><liferay-ui:message key="your-gender" /></label>
			</div>
			<div class="radio_container mb-4">
				<input type="radio" id="male"
					<c:if test="${cookieLeadValue['gender'].equalsIgnoreCase('Male') || cookieLeadValue['gender'].equalsIgnoreCase('3') || empty cookieLeadValue['gender']}">checked </c:if>
					value="3" name='<portlet:namespace/>gender'><label
					for="male">MALE</label> <input type="radio" id="female"
					<c:if test="${cookieLeadValue['gender'].equalsIgnoreCase('Female') || cookieLeadValue['gender'].equalsIgnoreCase('4')}">checked </c:if>
					value="4" name='<portlet:namespace/>gender'><label
					for="female">FEMALE</label>
					<input type="radio" id="transgender"
					<c:if test="${cookieLeadValue['gender'].equalsIgnoreCase('Transgender') || cookieLeadValue['gender'].equalsIgnoreCase('1802')}">checked </c:if>
					value="1802" name='<portlet:namespace/>gender'><label
					for="transgender">TRANSGENDER</label>
			</div>
		</div>

		 <div class="col-md-6 col-12" >
			<div class="input-box mt-2">
			<label class="motion-label" ><liferay-ui:message key="sjb-occupation" /></label>
				<select class="form-select form-control"
					id='<portlet:namespace/>occupation'
					name='<portlet:namespace/>occupation' onchange="toggleExtraFields(this.value); setNatureDuty(this.value);">
					<option value="" hidden></option>
					<c:forEach items="${youroccupationPicklist}" var="occupation">
                      <option value="${occupation.key}" ${cookieLeadValue['occupation'] == occupation.key ? 'selected' : ''}>
	                    	${occupation.value}
	                  </option>
	 				</c:forEach>
				</select>
			</div>
		</div> 
		
		
		<div id="extraFields" class="row col-md-12 m-0 px-0" style="display: none">
		<div class="col-12 col-md-4">
			<div class="input-box mt-2 " >
			<label class="motion-label" ><liferay-ui:message key="select-your-spouse" /></label>
				<select class="form-select form-control"
					id='<portlet:namespace/>spouseParentRelation' name='<portlet:namespace/>spouseParentRelation'>
					<option value="" hidden></option>
					<c:forEach items="${yourspouse}" var="spouse">
						<option value="${spouse.key}" ${cookieLeadValue['spouseParentRelation'] == spouse.key ? 'selected' : ''}>${spouse.value}</option>
					</c:forEach>
				</select>
				</div>
			</div>
			
				<div class="col-12 col-md-4">
			<div class="input-box mt-2 ">
						<label class="motion-label" ><liferay-ui:message key="parent-occupation" /></label>		
				<select class="form-select form-control"
					id='<portlet:namespace/>spouseParentOccupation'
					name='<portlet:namespace/>spouseParentOccupation'>
					<option value="" hidden></option>
					<c:forEach items="${spouseoccupation}" var="spouseoccupations">
						<option value="${spouseoccupations.key}" ${cookieLeadValue['familyOccupation'] == spouseoccupations.key ? 'selected' : ''}>${spouseoccupations.value}</option>
					</c:forEach>
				</select>
			</div>
			</div>
			
			<div class="col-12 col-md-4">
			<div class="input-box mt-2">
				<label class="motion-label sumAssured" for='<portlet:namespace/>spouseParentSumassured'><liferay-ui:message
						key="parent-total-sum-assured" /></label> 
						  
						<input type="text"
						value="${cookieLeadValue['familyTotalSumAssured']}" oninput="this.value = this.value.replace(/[^0-9]/g, '');"
					class="form-control pl-3 formateAmount" id='<portlet:namespace/>spouseParentsumassured'
					name='<portlet:namespace/>spouseParentSumassured' autocomplete="off" data-amt-in-words="spouseParentSumassuredInWords">
				<label class="label-plchld label-down" ><liferay-ui:message key="rupee-sign" /></label>
				<span class="underline"></span>
				<span style="font-size:small;" id="spouseParentSumassuredInWords"></span>
			</div>
			</div>
		</div> 

		<div class="col-md-6 col-12"  >
			<div class="input-box mt-2">
			<label class="motion-label" ><liferay-ui:message key="sjb-educationqualification" /></label>
				<select class="form-select form-control"
					id='<portlet:namespace/>educationQualification'
					name='<portlet:namespace/>educationQualification'>
					<option value="" hidden></option>
					<c:forEach items="${youreducationalqualification}"
						var="educationQualification">
						<option value="${educationQualification.key}" ${cookieLeadValue['educationQualification'] == educationQualification.key ? 'selected' : ''}>${educationQualification.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="col-md-6 col-12" id="somu">
			<div class="input-box mt-2">
						<label class="motion-label" ><liferay-ui:message key="sjb-natureofduty" /></label>
				<select class="form-select form-control"
					id='<portlet:namespace/>natureOfDuty' 
					name='<portlet:namespace/>natureOfDuty'>
					<option value="" hidden></option>
					<c:forEach items="${yournatureofduty}" var="natureOfDuty">
						<option value="${natureOfDuty.key}" ${cookieLeadValue['natureOfDuty'] == natureOfDuty.key ? 'selected' : ''}>${natureOfDuty.value}</option>
					</c:forEach>
				</select>
				<div style="font-size:small;">Select 'Others' if nature of duty is not found</div>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label id="annualIncomes" class="motion-label" for='<portlet:namespace/>annualIncome'><liferay-ui:message
						key="your-annual-income" /></label>
						
						 <input type="text"
						value="${cookieLeadValue['annualIncome']}"
					class="form-control pl-3 amt-in-word formateAmount" id='<portlet:namespace/>annualIncome'
					oninput="this.value = this.value.replace(/[^0-9]/g, '');"
					name='<portlet:namespace/>annualIncome' autocomplete="off" data-amt-in-words="annualIncomeInWords"></span>
					<label class="label-plchld label-down" ><liferay-ui:message key="rupee-sign" /></label>
					 <span class="underline"></span>
					 <span style="font-size:small;" id="annualIncomeInWords"></span>
			</div>
		</div>

		<div class="col-md-6 col-12">
			<div class="input-box">
				<label class="motion-label" for='<portlet:namespace/>pincode'><liferay-ui:message
						key="your-pincode" /></label> <input type="text" class="form-control"
						oninput="this.value = this.value.replace(/[^0-9]/g, '').substring(0, 6);"
						value="${cookieLeadValue['pincode']}"
					id='<portlet:namespace/>pincode'
					name='<portlet:namespace/>pincode'> <span
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
					name='<portlet:namespace/>mobileNumber' maxlength='10'> <span
					class='label-plchld'>+91</span> <span class="underline"></span>

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
				<c:forEach items="${smokerPicklistMap}" var="smoker">
					<input type="radio" id="${smoker.key}"
						value="${smoker.key}"
						<c:if test="${smoker.key == ((not empty cookieLeadValue['smoker'])?cookieLeadValue['smoker']:'0')}">checked</c:if>
						name='<portlet:namespace/>smoker'
						id='<portlet:namespace/>smoker' />
					<label for="${smoker.key}">${smoker.value}</label>
				</c:forEach>
			</div>
			<span style="font-size:small;"><liferay-ui:message key="below-smoke" /></span>
		</div>
		<div class="edto-check-content mt-4">
			<div class="form-group">
				<input type="checkbox"
					name='<portlet:namespace/>consenttermsandcondition'
					readonly="readonly" value="Consent" onclick="return false"
					checked="checked" for="flexCheckDefault"> <label
					for="flexCheckDefault"> <liferay-ui:message
						key="suitability-consent" /></label>
			</div>
			<div class="form-group">
				<input type="checkbox"
					name='<portlet:namespace/>consenttermsandcondition'
					readonly="readonly" value="Consent" onclick="return false"
					checked="checked" for="flexCheckDefault"> <label
					for="flexCheckDefault"> <liferay-ui:message
						key="ndnc-consent" /></label>
			</div>
			
		</div>
		<div class="edto-bullet1 mb-3 mt-3">
		<div>
			<button type="submit" class="edto-btn-primary" id='prequote-submit'
				name='prequote-submit'>
				<liferay-ui:message key="get-quote" />
			</button>
		</div>
	</div>
	</div>
	
	<!-- Partner and UTM jsp -->
	<%@ include file="partner_utm_element.jsp"%>
</form>
<%@ include file="/error-modal.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/buy_form_3.js?v=3.11"></script>
<script type="text/javascript">
/*
 * Display the error-up if exist
 */
if(showErrorModal == "true" ){
	$('.error-msg').text('<liferay-ui:message key="unable-to-proceed"/>');
	$('.error-blk').removeClass('hide');
	$('.success-blk').addClass('hide');
	$('.align-items-center').addClass('hide');
	$('#errorModal').modal('show');
}
 
$( "#generalForm input,#generalForm select " ).each(function() {
	if($(this).val()==="" || $(this).val()===null ){
	$(this).parent().children('.motion-label').removeClass("motion-label-heading")
        console.log($(this).parent().children('.motion-label'));
	}
	else{
	$(this).parent().children('.motion-label').addClass("motion-label-heading")
	}
	});
	
	
$( "#extraFields input,#extraFields select " ).each(function() {
	if($(this).val()==="" || $(this).val()===null ){
	$(this).parent().children('.motion-label').removeClass("motion-label-heading")
        console.log($(this).parent().children('.motion-label'));
	}
	else{
	$(this).parent().children('.motion-label').addClass("motion-label-heading")
	}
	});


var rupeeSignLabel = "<liferay-ui:message key='rupee-sign' />";
	
</script>
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
</script>
<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>