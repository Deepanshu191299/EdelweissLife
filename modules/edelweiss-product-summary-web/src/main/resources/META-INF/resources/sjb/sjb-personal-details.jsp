<portlet:actionURL name="/edit/basicDetailsURL" var="editBasicActionURL" />

<%@ include file="/sjb/sjb-edit-basic-details.jsp" %>


<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="personal-details" />
	</div>
	<div class="edto-summary-tiles mt-0">
		<span class="icon" name="editBasicDetailsSjb" id=editBasicDetailsSjb>
			<img alt="edit" src="/o/edelweisstokio-theme/images/edit-pen.svg">
		</span>
		<form>
			<div class="row">
				<div class="col-md-4 col-12">
					<p class="sjb-truncate-text">
						<liferay-ui:message key="name" />
						<span> ${summaryData.fullName}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="mobile-number-label" />
						<span>${summaryData.mobileNumber}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="annual-salary" />
						<span class="productAmounts" data-amount="${summaryData.annualIncome}">${summaryData.annualIncome}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="dob-label" />
						<span>${summaryData.dateOfBirth}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="gender-label" />
						<c:choose>
							<c:when test="${summaryData.gender == '3'}">
								<span>Male</span>
							</c:when>
							<c:when test="${summaryData.gender == '4'}">
								<span>Female</span>
							</c:when>
							<c:when test="${summaryData.gender == '1802'}">
								<span>Transgender</span>
							</c:when>
						</c:choose>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="smoker" />
						<span>${summaryData.smoker == 0 ? 'No' : 'Yes'}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p class="sjb-truncate-text ws-nowrap">
						<liferay-ui:message key="email-address-label" />
						<span>${summaryData.email}</span>
					</p>
				</div>
			</div>
		</form>
	</div>
</div>
  <script src="<%=request.getContextPath()%>/js/gss-main.js?v=1.5"></script>
