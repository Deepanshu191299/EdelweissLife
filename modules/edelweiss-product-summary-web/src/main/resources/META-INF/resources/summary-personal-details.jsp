<portlet:actionURL name="/edit/basicDetailsURL" var="editBasicActionURL" />

<%@ include file="edit_summary.jsp"%>
<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="personal-details" />
	</div>
	<div class="edto-summary-tiles mt-0">
		<span class="icon" name="editBasicDetails" id=editBasicDetails>
			<img alt="edit" src="/o/edelweisstokio-theme/images/edit-pen.svg">
		</span>
		<form>
			<div class="row">
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="name" />
						<span> ${summaryData.fullName}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="email-address-label" />
						<span>${summaryData.email}</span>
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
						<liferay-ui:message key="gender-label" />
						<span>${summaryData.gender}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="dob-label" />
						<span>${summaryData.dateOfBirth}</span>
					</p>
				</div>
				<c:choose>
					<c:when test="${(productName eq 'Zindagi Protect Plus')}">
							<div class="col-md-4 col-12">
								<p>
									<liferay-ui:message key="annual-income" />
									<span><liferay-ui:message key="rupee-sign" />${summaryData.annualIncome}</span>
								</p>
							</div>
						<c:if test="${not empty summaryData.customerFamilyDetailsRel[0].spouseName}">
							<div class="col-md-4 col-12">
								<p>
									<liferay-ui:message key="spouse-name" />
									<span>${summaryData.customerFamilyDetailsRel[0].spouseName}</span>
								</p>
							</div>
							<div class="col-md-4 col-12">
								<p>
									<liferay-ui:message key="spouse-dob" />
									<span>${summaryData.customerFamilyDetailsRel[0].spouseDateOfBirth}</span>
								</p>
							</div>
							<div class="col-md-4 col-12">
								<p>
									<liferay-ui:message key="spouse-smoke-status" />
									<span>${summaryData.customerFamilyDetailsRel[0].spouseSmoke eq '138' ? 'Yes':'No'}</span>
								</p>
							</div>
						</c:if>
						<c:if test="${not empty summaryData.customerFamilyDetailsRel[0].childDateOfBirth}">
							<div class="col-md-4 col-12">
								<p>
									<liferay-ui:message key="child-dob" />
									<span>${summaryData.customerFamilyDetailsRel[0].childDateOfBirth}</span>
								</p>
							</div>
						</c:if>
					</c:when>
				</c:choose>	
				<c:choose>
					<c:when
						test="${summaryData.customerInvestmentDetailsRel[0].investingFor == 'Family' }">
						<div class="col-md-4 col-12">
							<p>
								<liferay-ui:message key="investing-for" />
								<c:choose>
									<c:when test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandDaughter'}">
										<span><liferay-ui:message key="investing-for-granddaughter" /></span>	
									</c:when>
									<c:when test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandSon'}">
										<span><liferay-ui:message key="investing-for-grandson" /></span>	
									</c:when>
									<c:otherwise>
										<span>${summaryData.customerFamilyDetailsRel[0].assuredRelation}</span>
									</c:otherwise>
								</c:choose>
								
							</p>
						</div>

						<div class="col-md-4 col-12">
							<p>
								<c:choose>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'Son'}">
										<liferay-ui:message key="son-name" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'Daughter'}">
										<liferay-ui:message key="daughter-name" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandSon'}">
										<liferay-ui:message key="grand-son-name" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandDaughter'}">
										<liferay-ui:message key="grand-daughter-name" />
									</c:when>
								</c:choose>
								<span>${summaryData.customerFamilyDetailsRel[0].assuranceFullName}</span>
							</p>
						</div>
						<div class="col-md-4 col-12">
							<p>
								<c:choose>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'Son'}">
										<liferay-ui:message key="son-dob-label" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'Daughter'}">
										<liferay-ui:message key="daughter-dob-label" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandSon'}">
										<liferay-ui:message key="grand-son-dob-label" />
									</c:when>
									<c:when
										test="${summaryData.customerFamilyDetailsRel[0].assuredRelation == 'GrandDaughter'}">
										<liferay-ui:message key="grand-daughter-dob-label" />
									</c:when>
								</c:choose>
								<span>${summaryData.customerFamilyDetailsRel[0].assuranceDob}</span>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<c:if test="${not (productName eq 'Zindagi Protect Plus')}">
						<div class="col-md-4 col-12">
							<p>
								<liferay-ui:message key="investing-for" />
								<span>${summaryData.customerInvestmentDetailsRel[0].investingFor}</span>
							</p>
						</div>
						</c:if>
					</c:otherwise>
				</c:choose>
			</div>
		</form>
	</div>
</div>