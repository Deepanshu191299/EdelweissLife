<%
String spouseName = StringPool.BLANK;
String spouseDateOfBirth = StringPool.BLANK;
String childDateOfBirth = StringPool.BLANK;
String spouseSmoke = StringPool.BLANK;

if(Validator.isNotNull(familyDetailsResponseJson)){
	if(Validator.isNotNull(familyDetailsResponseJson.getString("spouseName"))){
		spouseName = familyDetailsResponseJson.getString("spouseName");
	}
	if(Validator.isNotNull(familyDetailsResponseJson.getString("spouseDateOfBirth"))){
		spouseDateOfBirth = familyDetailsResponseJson.getString("spouseDateOfBirth");
	}
	if(Validator.isNotNull(familyDetailsResponseJson.getString("spouseSmoke"))){
		spouseSmoke = familyDetailsResponseJson.getString("spouseSmoke");
	}
	if(Validator.isNotNull(familyDetailsResponseJson.getString("childDateOfBirth"))){
		childDateOfBirth = familyDetailsResponseJson.getString("childDateOfBirth");
	}
}
%>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="editZPBasicDetailsModal" style="display: none;">
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
			<form name="<portlet:namespace/>editSummaryForm" id="<portlet:namespace/>editSummaryForm" method="POST" action="">
				<div class="row">
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>fullname'><liferay-ui:message
									key="your-name" /></label> <input type="text" class="form-control"
								value="${basicDetailsMap['fullName']}"
								id='<portlet:namespace/>fullName'
								name='<portlet:namespace/>fullName'
								oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
							${sessionScope.fullName} <span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mb-2">
							<label><liferay-ui:message key="gender" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="sir" <c:if test="${basicDetailsMap['gender'] == 'Male' || empty basicDetailsMap['gender']}">checked </c:if> value="Male" name='<portlet:namespace />gender'> 
						<label for="sir">Male</label>
						<input type="radio" id="maam" <c:if test="${basicDetailsMap['gender'] == 'Female'}">checked </c:if> value="Female" name='<portlet:namespace />gender'> 
						<label for="maam">Female</label>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<label class="main-label" for='<portlet:namespace/>dob'> <liferay-ui:message
									key="date-of-birth" />
							</label> <input type="text" class="form-control save-n-eligibility" maxlength="10"
								value="${basicDetailsMap['dateOfBirth']}"
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
								class="form-control mob-pos basic-form-elem save-n-eligibility has-content formBuild"
								value="${basicDetailsMap['mobileNumber']}"
								id='<portlet:namespace/>mobileNumber'
								name='<portlet:namespace/>mobileNumber' maxlength='10'
								oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
							<span class='label-plchld'>+91</span> <span class="underline"></span>
			
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mb-2">
							<label><liferay-ui:message key="annual-income" /></label>
						</div>
						<div class="input-box">
							<div class="pos">
								<input type="text" class="form-control save-n-eligibility save-income productAmounts" id='<portlet:namespace/>Income'
										name='<portlet:namespace/>Income' maxlength="15"
										oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
							</div>		
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<label for='<portlet:namespace/>emailId' class="main-label">
								<liferay-ui:message key="email-address" />
							</label> <input type="email" class="form-control basic-form-elem save-n-eligibility formBuild"
								value="${basicDetailsMap['email']}" id='<portlet:namespace/>email'
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
								<c:if test="${basicDetailsMap['smoker'] eq '1'}">checked </c:if>
								name='<portlet:namespace/>smoke'><label for="yes">Yes</label>
							<input type="radio" id="no" value="0" 
								<c:if test="${basicDetailsMap['smoker'] eq '0'}">checked </c:if>
								name='<portlet:namespace/>smoke'><label for="no">No</label>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mb-2">
							<label><liferay-ui:message key="are-you-married?" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="marriedYes" value="true" 
								<c:if test="<%= !spouseName.equals(StringPool.BLANK)  %>">checked</c:if>
								name='<portlet:namespace/>MaritalStatusId'><label for="marriedYes">Yes</label>
							<input type="radio" id="marriedNo" value="false" 
								<c:if test="<%= spouseName.equals(StringPool.BLANK)  %>">checked </c:if>
								name='<portlet:namespace/>MaritalStatusId'><label for="marriedNo">No</label>
						</div>
					</div>
					<div id="<portlet:namespace/>marriageDetails" style='display: none;' class="row col-md-12 m-0 p-0">
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="main-label" for='<portlet:namespace/>SpouseName'><liferay-ui:message
										key="spouse-name" /></label> <input type="text" class="form-control save-n-eligibility"
									value="<%=spouseName %>" id='<portlet:namespace/>SpouseName'
									name='<portlet:namespace/>SpouseName'
									oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
								${sessionScope.fullName} <span class="underline"></span>
							</div>
						</div>
			
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="main-label" for='<portlet:namespace/>SpouseDob'>
									<liferay-ui:message key="spouse-date-of-birth" />
								</label> <input type="text" class="form-control save-n-eligibility" maxlength="10"
									value="<%=spouseDateOfBirth %>"
									id='<portlet:namespace/>SpouseDob'
									name='<portlet:namespace/>SpouseDob'> <span
									class="underline"></span>
							</div>
						</div>
						<div class="col-md-6 col-12">
							<div class="input-box mt-2 mb-2">
								<label><liferay-ui:message key="do-your-spouse-smoke?" /></label>
							</div>
							<div class="radio_container">
								<input type="radio" id="spouseSmokeYes" value="138"
									<c:if test="<%= spouseSmoke.equals("138") %>">checked </c:if>
										name='<portlet:namespace/>SpouseSmoke'><label
										for="spouseSmokeYes">Yes</label> <input type="radio"
										id="spouseSmokeNo" value="137" 
									<c:if test="<%= spouseSmoke.equals("137") %>">checked </c:if>
										name='<portlet:namespace/>SpouseSmoke'><label
										for="spouseSmokeNo">No</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label motion-label" for='<portlet:namespace/>occupation'> <liferay-ui:message key="occupation" /> </label> 
							<select id='<portlet:namespace/>occupation' name='<portlet:namespace/>occupation' class="form-select save-n-eligibility form-control valid mt-2" required>
								<option value="" disabled selected hidden><liferay-ui:message key="" /></option>
								<c:forEach items="${occupationPicklistData}" var="options">
									<option value="${options.key}" ${customerEnquiryMap['occupation'] == options.key ? 'selected':''}>${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label motion-label" for='<portlet:namespace/>educationQualification'> <liferay-ui:message key="education" /> </label> 
							<select id='<portlet:namespace/>educationQualification' name='<portlet:namespace/>educationQualification' class="form-select save-n-eligibility form-control valid mt-2" required>
								<option value="" disabled selected hidden><liferay-ui:message key="" /></option>
								<c:forEach items="${educationPicklistData}" var="options">
									<option value="${options.key}" ${customerEnquiryMap['educationQualification'] == options.key ? 'selected':''}>${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mb-2">
							<label><liferay-ui:message key="do-you-have-a-child?" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="childYes" value="yes" <c:if test="<%= !childDateOfBirth.equals(StringPool.BLANK) %>">checked</c:if>
								name='<portlet:namespace/>ChildName'><label for="childYes">Yes</label>
							<input type="radio" id="childNo" value="no" <c:if test="<%= childDateOfBirth.equals(StringPool.BLANK) %>">checked</c:if>
								name='<portlet:namespace/>ChildName'><label for="childNo">No</label>
						</div>
					</div>
					<div id="<portlet:namespace/>childDetails" style='display: none;' class="col-md-6 m-0 p-0">
						<div class="col-12">
							<div class="input-box">
								<label class="main-label" for='<portlet:namespace/>child-dob'>
									<liferay-ui:message key="child-date-of-birth" />
								</label> <input type="text" class="form-control" maxlength="10" 
									value="<%=childDateOfBirth %>"
									id='<portlet:namespace/>ChildDob'
									name='<portlet:namespace/>ChildDob'> <span
									class="underline"></span>
							</div>
						</div>
					</div>
					<div class="zp-eligibility-sorry-msg d-none col-md-12" style="text-align: center;">
                        <p>Sorry, Zindagi Protect Plus cannot be offered to the current profile</p>
                    </div>
					<div class="zp-eligibility-success-msg d-none col-md-12" style="text-align: center;">
                        <p>You are eligible for Zindagi Protect Plus</p>
                    </div>
					<div class="edto-bullet1 mb-3 mt-3 col-md-12">
						<div class="center-wealth-btn">
						    <input type="button" id="save" class="edto-btn-primary save-and-eligibility-btn" value='<liferay-ui:message key="save"></liferay-ui:message>' />
						    <input type="button" id="close" class="edto-btn-primary d-none save-close-btn" value='<liferay-ui:message key="okay"></liferay-ui:message>' />
						</div>
					</div>
				</div>
			</form>
         </div>
         </div>
      </div>
   </div>
</div>

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
</style>