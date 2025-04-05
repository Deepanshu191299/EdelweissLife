<div class="modal wealth-modal" tabindex="-1" role="dialog" id="editBasicDetailsModal" style="display: none;">
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
			<form name="editbasicDetailSummaryFm" id="editbasicDetailSummaryFm" method="POST" action="${editBasicActionURL}">
				<div class="row">
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>fullname'><liferay-ui:message
									key="your-name" /></label> <input type="text" class="form-control"
								value="${summaryData['fullName']}"
								id='<portlet:namespace/>fullName'
								name='<portlet:namespace/>fullName'
								oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
							${sessionScope.fullName} <span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12 radio-wrapper">
						<div class="input-box mt-2 mb-2">
							<label><liferay-ui:message key="gender" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="sir-gender" <c:if test="${summaryData['gender'] == 'Male' || empty summaryData['gender']}">checked </c:if> value="Male" name='<portlet:namespace />gender'> 
						<label for="sir-gender">
						  <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 22 22" xml:space="preserve" width="25px">
						    <path d="M10.1,0.5c0.3,0.1,0.6,0.1,0.9,0.2c0.7,0.2,1.3,0.6,1.8,1.1C12.9,1.9,13,2,13.1,1.9c0.7-0.1,1.4,0.1,1.9,0.5
							c0.8,0.5,1.3,1.3,1.4,2.3c0.1,0.9-0.2,1.7-0.7,2.4c-0.1,0.1-0.1,0.2,0.1,0.2c0.4,0.2,0.7,0.6,0.7,1c0,0.7-0.1,1.4-0.6,1.9
							c-0.1,0.1-0.2,0.2-0.3,0.3c-0.1,0.1-0.2,0.1-0.2,0.3c-0.3,1.3-1,2.4-2.1,3.2c-0.1,0.1-0.1,0.2-0.1,0.3c0,0.3,0,0.5,0,0.8
							c0,0.1,0,0.2,0.1,0.2c0.4,0,0.8,0,1.2,0c1.5,0.1,2.8,0.7,3.8,1.7c1.1,1.1,1.7,2.5,1.7,4.1c0,0.3-0.1,0.5-0.5,0.5
							c-5.7,0-11.4,0-17.1,0C2.1,21.5,2,21.4,2,21c0-1,0.3-2,0.8-2.8c0.9-1.5,2.1-2.4,3.8-2.8c0.7-0.2,1.4-0.2,2.1-0.2
							c0.2,0,0.2,0,0.2-0.2c0-0.3,0.1-0.6,0-0.8c-0.1-0.3-0.4-0.3-0.6-0.5c-0.7-0.7-1.3-1.5-1.6-2.4c0-0.1-0.1-0.2-0.2-0.2
							c-0.4-0.2-0.6-0.5-0.8-0.9C5.5,9.5,5.4,9,5.5,8.4C5.6,8.2,5.7,8,5.8,7.9c0.1-0.1,0.1-0.2,0-0.3C5.4,6.8,5.1,6,5.1,5.1
							c0-2.2,1.4-4,3.4-4.5c0.2,0,0.3,0,0.5-0.1C9.4,0.5,9.7,0.5,10.1,0.5z M14.8,8.7c0-0.4,0-0.7,0-1.1c0-0.1,0-0.2-0.1-0.4
							c-0.1-0.3-0.3-0.6-0.5-0.8c-0.4-0.5-0.9-0.8-1.4-1.2c-0.1-0.1-0.2,0-0.2,0c-0.4,0.3-0.9,0.7-1.3,1c-1,0.8-2.1,1.2-3.4,1.3
							c-0.4,0-0.7,0.3-0.7,0.6c0,0.7-0.1,1.4,0,2.1c0.3,1.8,1.3,3,2.9,3.6c0.3,0.1,0.7,0.3,1.1,0.2c0.8-0.2,1.5-0.5,2.1-1.1
							c0.7-0.7,1.2-1.5,1.4-2.4C14.8,10,14.8,9.3,14.8,8.7z M15.2,6.7c0.3-0.3,0.5-0.7,0.5-1.1c0.3-1.7-1.1-3.2-2.8-2.9
							c-0.2,0-0.4,0-0.6-0.2c-0.7-0.8-1.7-1.2-2.7-1.2c-1.2,0-2.3,0.5-3,1.5C6,3.4,5.8,4.3,5.8,5.3c0,0.8,0.3,1.5,0.8,2.2
							C6.9,7.2,7.1,7,7.4,6.9c0.2-0.1,0.5-0.1,0.7-0.1c0.9,0,1.7-0.3,2.4-0.9c0.6-0.5,1.3-1,1.9-1.4c0.2-0.2,0.3-0.2,0.6,0
							C13.9,5.1,14.7,5.8,15.2,6.7z M6,20.8c1,0,2.1,0,3.1,0c0.2,0,0.2-0.1,0.2-0.2c0.2-1,0.4-2,0.5-3.1c0-0.1,0-0.2-0.1-0.3
							c-0.3-0.4-0.7-0.8-0.9-1.3c0-0.1-0.1,0-0.1,0c-0.5,0-1.1,0-1.6,0c-1.3,0.2-2.5,0.8-3.3,1.9c-0.6,0.8-1,1.7-1.1,2.7
							c0,0.2,0,0.2,0.2,0.2C4,20.8,5,20.8,6,20.8z M16,20.8c1,0,2.1,0,3.1,0c0.2,0,0.2,0,0.2-0.2c0-0.2,0-0.3-0.1-0.5
							c-0.4-1.9-1.5-3.2-3.3-3.9c-0.8-0.3-1.6-0.3-2.4-0.3c-0.2,0-0.4,0-0.6,0.3c-0.2,0.4-0.5,0.7-0.8,1.1c-0.1,0.1-0.1,0.2-0.1,0.3
							c0.2,1,0.4,2,0.5,3c0,0.2,0.1,0.2,0.2,0.2C13.9,20.8,14.9,20.8,16,20.8z M12.4,14.5c-0.1,0-0.2,0.1-0.2,0.1
							c-0.6,0.2-1.3,0.4-1.9,0.1c-0.1-0.1-0.3-0.1-0.5-0.2c-0.1-0.1-0.2,0-0.2,0.1c0,0.3,0,0.7,0,1c0,0.1,0,0.2,0.1,0.3
							c0.2,0.3,0.5,0.6,0.7,1c0.1,0.1,0.1,0.1,0.2,0.1c0.3,0,0.5,0,0.8,0c0.1,0,0.2,0,0.2-0.1c0.2-0.3,0.5-0.6,0.7-0.9
							c0-0.1,0.1-0.1,0.1-0.2C12.4,15.3,12.4,14.9,12.4,14.5z M12,20.8c-0.2-1-0.4-2-0.5-3c0-0.1-0.1-0.2-0.2-0.2c-0.2,0-0.3,0-0.5,0
							c-0.1,0-0.2,0-0.2,0.2c-0.1,0.6-0.2,1.1-0.3,1.7c-0.1,0.4-0.1,0.7-0.2,1.1c0,0.1-0.1,0.2,0.1,0.2C10.8,20.8,11.4,20.8,12,20.8z
							 M15.5,7.9c0,0.6,0,1.1,0,1.7c0.2-0.3,0.4-0.9,0.3-1.3C15.8,8.2,15.7,8,15.5,7.9z M6.5,10c0-0.6,0-1.1,0-1.6c0,0,0-0.1,0-0.1
							c0,0-0.1,0-0.1,0C6.2,8.5,6.2,8.6,6.2,8.7C6.1,9.2,6.2,9.6,6.5,10z"></path>
						  </svg>
						</label>
						<input type="radio" id="maam-gender" <c:if test="${summaryData['gender'] == 'Female'}">checked </c:if> value="Female" name='<portlet:namespace />gender'> 
						<label for="maam-gender">
						  <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="Layer_1" x="0px" y="0px" viewBox="0 0 22 22" xml:space="preserve" " width=" 25px">
						    <path class="st1" d="M2,21.8c0-1.1-0.1-2.1,0-3.2c0.1-0.9,0.7-1.7,1.7-2c0.9-0.3,1.8-0.7,2.7-1.1c0,0,0.1,0,0.1-0.1  c-0.5-0.1-1.1-0.2-1.6-0.4c-0.8-0.2-1.5-0.5-2.1-1.1c-0.1-0.1-0.1-0.1,0-0.2c0.8-0.6,1.1-1.4,1.3-2.4c0.2-1,0.3-2,0.4-3  c0.1-1.4,0.3-2.7,0.8-4c0.7-1.8,1.9-3,3.8-3.4c1.7-0.4,3.3-0.4,4.9,0.3c1.2,0.5,2.1,1.3,2.6,2.4c0.5,1.1,0.8,2.2,1,3.4  c0.2,1.3,0.3,2.6,0.5,3.9c0.1,0.6,0.2,1.2,0.5,1.7c0.2,0.4,0.4,0.7,0.8,1c0.1,0.1,0.2,0.2,0,0.3c-0.6,0.6-1.4,0.9-2.1,1.1  c-0.5,0.2-1,0.3-1.5,0.3c0,0.1,0.1,0.1,0.1,0.1c0.9,0.4,1.8,0.8,2.7,1.1c1,0.3,1.6,1.2,1.6,2.2c0,1,0,2,0,2.9C14,21.8,8,21.8,2,21.8  z M11,20.9c2.6,0,5.3,0,7.9,0c0.2,0,0.2,0,0.2-0.2c0-0.6,0-1.1,0-1.7c0-0.8-0.4-1.3-1.2-1.6c-1.2-0.4-2.3-0.9-3.4-1.5  c-0.5-0.3-1-0.6-1.2-1.2c-0.1-0.1-0.1,0-0.2,0c-0.2,0.2-0.5,0.3-0.7,0.4c-0.7,0.4-1.4,0.6-2.2,0.3c-0.5-0.2-1-0.4-1.4-0.7  c0,0-0.1-0.1-0.2,0c-0.3,0.6-0.7,0.9-1.3,1.2c-1.1,0.6-2.2,1.1-3.4,1.4c-0.6,0.2-1,0.6-1.1,1.3c-0.1,0.6,0,1.3,0,1.9  c0,0.2,0,0.2,0.2,0.2C5.7,20.9,8.4,20.9,11,20.9z M15.7,8.4c0-0.2-0.1-0.7-0.1-1.1c0-0.1-0.1-0.2-0.2-0.2C15,7,14.6,6.8,14.2,6.7  c-0.7-0.3-1.5-0.7-2.1-1.2c-0.1-0.1-0.2-0.1-0.3,0c-0.2,0.2-0.3,0.3-0.5,0.4c-1.4,1.2-3,2.2-4.9,2.7c-0.1,0-0.1,0.1-0.1,0.2  c0,0.3,0,0.6,0.1,0.9c0.2,1.1,0.7,2,1.3,2.8c0.6,0.8,1.3,1.5,2.2,2c0.5,0.3,1.1,0.4,1.7,0.2c0.3-0.1,0.5-0.2,0.8-0.4  c0.9-0.6,1.7-1.4,2.2-2.3C15.3,10.9,15.7,9.8,15.7,8.4z M13.6,2.1c-0.1,0-0.1,0-0.1-0.1C12,1.6,10.6,1.6,9.2,1.9  C7.7,2.2,6.6,3.2,6,4.7c-0.4,1-0.5,2-0.7,3c0,0.2,0,0.2,0.2,0.2c1-0.1,1.9-0.5,2.8-0.9c1.4-0.7,2.6-1.8,3.7-2.9  C12.6,3.5,13.1,2.8,13.6,2.1z M14.2,14.2c0,0.1,0,0.1,0,0.2c0,0.2,0.2,0.3,0.4,0.3c0.3,0,0.6-0.1,0.9-0.1c0.9-0.2,1.7-0.4,2.5-0.7  c0.1-0.1,0.1-0.1,0-0.2c-0.2-0.3-0.3-0.6-0.5-0.9c-0.4-1-0.5-2.1-0.6-3.2c-0.1-0.7-0.2-1.4-0.2-2.1c0-0.1-0.1-0.2-0.2-0.2  c-0.1,0,0,0.1,0,0.2c0.1,0.8,0.1,1.6,0,2.4c-0.3,1.5-1.1,2.8-2.1,3.9C14.2,13.9,14.1,14,14.2,14.2z M7.9,14.2c0.1-0.2,0-0.3-0.2-0.4  C6.4,12.4,5.6,10.9,5.5,9c0-0.1,0-0.2-0.1-0.2C5.2,8.8,5.2,8.9,5.2,9C5.1,9.6,5,10.3,5,10.9c-0.1,0.8-0.3,1.7-0.7,2.4  C4.2,13.5,4,13.7,4,13.8C4.1,13.9,4.4,14,4.5,14c0,0,0,0,0.1,0c0.9,0.3,1.9,0.5,2.9,0.7c0.2,0,0.4,0,0.4-0.2  C7.9,14.4,7.9,14.3,7.9,14.2z M16.5,6.3C16.5,6.3,16.5,6.3,16.5,6.3c-0.1-0.6-0.3-1.2-0.5-1.7c-0.3-0.8-0.8-1.5-1.5-2  c-0.1,0-0.1-0.1-0.2,0c-0.5,0.7-1,1.4-1.6,2c-0.1,0.1-0.1,0.1,0,0.2c0.6,0.4,1.3,0.8,2,1.1c0.5,0.2,1.1,0.4,1.7,0.5  C16.4,6.4,16.5,6.5,16.5,6.3z"></path>
						  </svg>
						</label>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>dob'> <liferay-ui:message
									key="date-of-birth" />
							</label> <input type="text" class="form-control" maxlength="10"
								value="${summaryData['dateOfBirth']}"
								id='<portlet:namespace/>dateOfBirth'
								name='<portlet:namespace/>dateOfBirth'> <span
								class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>mobileNo'>
								<liferay-ui:message key="mobile-number" />
							</label> <input type="tel"
								class="form-control mob-pos basic-form-elem has-content formBuild"
								value="${summaryData['mobileNumber']}"
								id='<portlet:namespace/>mobileNumber'
								name='<portlet:namespace/>mobileNumber' maxlength='10'
								oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
							<span class='label-plchld'>+91</span> <span class="underline"></span>
			
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2 mb-2">
							<label><liferay-ui:message key="annual-income-range" /></label>
						</div>
						<div class="input-box mt-2">
							<input type="text" class="form-control"
									value="${summaryData['annualIncome']}" id='<portlet:namespace/>Income'
									name='<portlet:namespace/>Income' maxlength="15">
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label for='<portlet:namespace/>emailId' class="main-label">
								<liferay-ui:message key="email-address" />
							</label>
							<input type="email" class="form-control basic-form-elem formBuild"
								value="${summaryData['email']}" id='<portlet:namespace/>email'
								name='<portlet:namespace/>email' oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');">
								<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2 mb-2">
							<label><liferay-ui:message key="do-you-smoke?" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="yes" value="1" 
								<c:if test="${summaryData['smoker'] == '1'}">checked </c:if>
								name='<portlet:namespace/>smoke'><label for="yes">Yes</label>
							<input type="radio" id="no" value="0" 
								<c:if test="${summaryData['smoker'] == '0' || empty summaryData['smoker']}">checked </c:if>
								name='<portlet:namespace/>smoke'><label for="no">No</label>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2 mb-2">
							<label><liferay-ui:message key="are-you-married?" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="marriedYes" value="true" 
								<c:if test="${not empty summaryData.customerFamilyDetailsRel[0].spouseName}">checked</c:if> 
								name='<portlet:namespace/>MaritalStatusId'><label for="marriedYes">Yes</label>
							<input type="radio" id="marriedNo" value="false" 
								<c:if test="${empty summaryData.customerFamilyDetailsRel[0].spouseName}">checked </c:if>
								name='<portlet:namespace/>MaritalStatusId'><label for="marriedNo">No</label>
						</div>
					</div>
					<div id="<portlet:namespace/>marriageDetails" style='display: none;' class="row col-md-12 m-0 p-0">
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="main-label" for='<portlet:namespace/>SpouseName'><liferay-ui:message
										key="spouse-name" /></label> <input type="text" class="form-control"
									value="${summaryData.customerFamilyDetailsRel[0].spouseName}" id='<portlet:namespace/>SpouseName'
									name='<portlet:namespace/>SpouseName'
									oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
								${sessionScope.fullName} <span class="underline"></span>
							</div>
						</div>
			
						<div class="col-md-6 col-12">
							<div class="input-box mt-2">
								<label class="main-label" for='<portlet:namespace/>SpouseDob'>
									<liferay-ui:message key="spouse-date-of-birth" />
								</label> <input type="text" class="form-control" maxlength="10" 
									value="${summaryData.customerFamilyDetailsRel[0].spouseDateOfBirth}"
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
									<c:if test="${summaryData.customerFamilyDetailsRel[0].spouseSmoke == '138'}">checked </c:if>
									name='<portlet:namespace/>SpouseSmoke'><label
									for="spouseSmokeYes">Yes</label> <input type="radio"
									id="spouseSmokeNo" value="137" 
									<c:if test="${summaryData.customerFamilyDetailsRel[0].spouseSmoke == '137' || empty summaryData.customerFamilyDetailsRel[0].spouseSmoke}">checked </c:if>
									name='<portlet:namespace/>SpouseSmoke'><label
									for="spouseSmokeNo">No</label>
							</div>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>occupation'> <liferay-ui:message key="occupation" /> </label> 
							<select id='<portlet:namespace/>occupation' name='<portlet:namespace/>occupation'class="mt-2">
								<c:forEach items="${occupationPicklistData}" var="options">
									<option value="${options.key}" ${summaryData['occupation'] == options.key ? 'selected':''} >${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2">
							<label class="main-label" for='<portlet:namespace/>education'> <liferay-ui:message key="education" /> </label> 
							<select id='<portlet:namespace/>education' name='<portlet:namespace/>education'class="mt-2">
								<c:forEach items="${educationQualificationPicklistData}" var="options">
									<option value="${options.key}" ${summaryData['educationQualification'] == options.key ? 'selected':''}>${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box mt-2 mb-2">
							<label><liferay-ui:message key="do-you-have-a-child?" /></label>
						</div>
						<div class="radio_container">
							<input type="radio" id="childYes" value="yes" <c:if test="${not empty summaryData.customerFamilyDetailsRel[0].childDateOfBirth}">checked </c:if>
								name='<portlet:namespace/>ChildName'><label for="childYes">Yes</label>
							<input type="radio" id="childNo" value="no" <c:if test="${summaryData.customerFamilyDetailsRel[0].childDateOfBirth=='' || empty summaryData.customerFamilyDetailsRel[0].childDateOfBirth}">checked </c:if>
								name='<portlet:namespace/>ChildName'><label for="childNo">No</label>
						</div>
					</div>
					<div id="<portlet:namespace/>childDetails" style='display: none;' class="col-md-6">
						<div class="col-md-6 col-12">
							<div class="input-box mt-2 mt-2">
								<label class="main-label" for='<portlet:namespace/>child-dob'>
									<liferay-ui:message key="child-date-of-birth" />
								</label> <input type="text" class="form-control" maxlength="10" 
									value="${summaryData.customerFamilyDetailsRel[0].childDateOfBirth}"
									id='<portlet:namespace/>ChildDob'
									name='<portlet:namespace/>ChildDob'> <span
									class="underline"></span>
							</div>
						</div>
					</div>
					<div class="edto-bullet1 col-md-12 mb-3 mt-3">
						<div class="center-wealth-btn">
						    <input type="submit" id="save" class="edto-btn-primary" value='<liferay-ui:message key="save"></liferay-ui:message>' /> 
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
.radio-wrapper {
    display: flex;
    align-items: center;
    margin-top: 20px;
    gap: 30px;
}
 .radio-wrapper .radio_container {
    display: flex;
    align-items: center;
    gap: 10px;
}
 .radio-wrapper input + label {
    border-radius: 50%;
    border-radius: 50%;
    width: 45px;
    height: 45px;
    padding: 0;
    font-size: 20px;
    border: 1px solid #a8a2a2;
}
 .radio-wrapper .radio_container input[type="radio"]:checked + label{
    background-color: #1e90ff;
    color: #124093;
    transition: 0.3s;
    border: 1.2px solid #124093;
    background-color: #f0f0f0;
    color: #124093;
}
 .radio-wrapper .radio_container input[type="radio"]:checked + label svg {
    fill: #124093;
}
</style>