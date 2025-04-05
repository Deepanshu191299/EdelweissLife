<%@ include file="/init.jsp"%>

<portlet:resourceURL id="/edelweiss/customize-funds" var="edelweissCustomizeFundsURL">
</portlet:resourceURL>

<portlet:resourceURL id="/buyJourney/generate-bi" var="edelweissGenerateBIURL">
</portlet:resourceURL>

<portlet:resourceURL var="saveBasicDetailsURL" id="/save/basicDetails"></portlet:resourceURL>

<portlet:resourceURL var="saveFamilyDetailsURL" id="/save/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="deleteFamilyDetailsURL" id="/delete/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="updateLittleChampBenefitURL" id="/update/littleChampBenefit"></portlet:resourceURL>

<portlet:resourceURL var="saveFundDetailsURL" id="/save/fundDetails"></portlet:resourceURL>

<portlet:resourceURL var="loadAllocatedFundsURL" id="/load/allocatedFunds"></portlet:resourceURL>

<portlet:resourceURL var="loadFamilyDetailsURL" id="/load/familyDetails"></portlet:resourceURL>

<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>

<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>

<portlet:resourceURL var="getBasicDetailsURL" id="/get/BasicDetails"></portlet:resourceURL>

<div id="preloader"></div>

<%@ include file="/edit_basic_details.jsp" %>

<div class="edelweissTokioDetails p-2">
	<div class="container">
		<div class="edelweissTokioDetailsInner d-flex align-items-center">
			<div class="edelweissTokioHeading">
				<h5 class="fontbold"><liferay-ui:message key="label-your-details"/></h5>
			   <h5 class="fontbold d-block d-md-none d-lg-none basic-details"><liferay-ui:message key="label-basic-details"/></h5>
			</div>
			<div class="edelweissTokioFormDetails d-none d-md-block">
				<ul class="nav" id="personal-details">
					<li class="nav-item">${basicDetailsMap['fullName']}</li>
					<li class="nav-item">${basicDetailsMap['gender']}</li>
					<li class="nav-item">${basicDetailsMap['dateOfBirth']}</li>
					<li class="nav-item">${basicDetailsMap['mobileNumber']}</li>
					<li class="nav-item">${basicDetailsMap['email']}</li>
					<li class="nav-item">${picklistOptionValues[basicDetailsMap['investmentObjective']]}</li>
					<li class="nav-item">${basicDetailsMap['basicInvestingFor']}</li>
					<c:if test="${basicDetailsMap['basicInvestingFor'] == 'Family' }">
						<li class="nav-item">${basicDetailsMap['assuranceFullName']}</li>
						<li class="nav-item">${basicDetailsMap['assuranceDob']}</li>
					</c:if>
				</ul>
			</div>
			<div class="editModel ml-md-auto">
				<!-- <a href="javascript:void(0);" data-toggle="modal"
					data-target="#exampleModalCenter"> -->
					<!-- <button type="button" name="editBasicDetails" id="editBasicDetails">
						<i class="fa fa-edit" style="font-size: 20px"></i>Edit
					</button> -->
					<span name="editBasicDetails" id="editBasicDetails"> <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
						viewBox="0 0 22 22">
                                <path fill="#999" fill-rule="nonzero"
							stroke="#999" stroke-width=".3"
							d="M19.705 2.295a4.41 4.41 0 0 0-6.243 0L2.016 13.738a.573.573 0 0 0-.162.328l-.848 6.282a.57.57 0 0 0 .162.482c.106.106.255.17.404.17.026 0 .051 0 .077-.004l6.225-.825c.315-.042.474-.332.432-.647a.576.576 0 0 0-.648-.495l-5.412.722.592-4.381 4.61 4.611c.107.107.194.17.343.17.15 0 .36-.059.467-.17L19.705 8.538A4.388 4.388 0 0 0 21 5.414a4.37 4.37 0 0 0-1.295-3.12zm-6.147 1.22l1.922 1.921L5.035 15.882 3.113 13.96 13.558 3.514zm-5.825 15.06l-1.88-1.879L16.298 6.251l1.88 1.879L7.732 18.576zM18.979 7.308L14.38 2.71a3.255 3.255 0 0 1 2.08-.746c.874 0 1.692.341 2.31.955.618.613.954 1.436.954 2.31 0 .771-.264 1.496-.745 2.08z"></path>
                            </svg>
                            </span>
			<!-- 	</a> -->
			</div>
			<div id="<portlet:namespace/>planBrochureContainer" class="brochure-link d-block d-md-none d-lg-none ml-auto pl-2" style="white-space: nowrap;">
				<a href="${productCustomizeSummaryData['productBrochure'].link.href}" 
					id="<portlet:namespace/>planBrochure" download="${productCustomizeSummaryData['productBrochure'].link.label}">
						<liferay-ui:message key="label-download-brochure"/>
				</a>
			</div>
		 </div>
		</div>
	</div>


<!-- <div class="basic-details bg-color">
	<div class="container">
		<div class="basic-detail-row">
			<div class="edit-pencil">
				<button type="button" name="editBasicDetails" id="editBasicDetails">
					<i class="fa fa-edit" style="font-size: 20px"></i>Edit
				</button>
			</div>
			<div class="dwnd-brochure">
				<a class="dwnld-link" data-pdfname="Wealth Rise Plus Brochure" data-href="">Download Brochure</a>
			</div>
		</div>
	</div>
</div> -->

<c:choose>
	<c:when test="${isProductSelected}">
		<form name="<portlet:namespace />customizeForm" id="<portlet:namespace />customizeForm" method="post" action="${saveInvestmentDetails}">

			<input type="hidden" class="form-control" id="<portlet:namespace />productCode" name="<portlet:namespace />productCode" value="${productMetaData['productCode']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />productName" name="<portlet:namespace />productName" value="${productMetaData['productName']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerEnquiryId" name="<portlet:namespace />customerEnquiryId" value="${customerEnquiryId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerInvestmentDetailsId" name="<portlet:namespace />customerInvestmentDetailsId" value="${customerInvestmentDetailsId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerPolicyDetailsId" name="<portlet:namespace />customerPolicyDetailsId" value="${customerPolicyDetailsId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerFundAllocationDetailId" name="<portlet:namespace />customerFundAllocationDetailId" value="${customerFundAllocationDetailId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />customerFamilyDetailsId" name="<portlet:namespace />customerFamilyDetailsId" value="${customerFamilyDetailsId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />customerAllocatedFundDetails" name="<portlet:namespace />customerAllocatedFundDetails" value='${summaryData.customerFundAllocationDetailsRel.size() > 0 ? summaryData.customerFundAllocationDetailsRel[0].fundDetails : ""}'/>
			<input type="hidden" class="form-control" id="<portlet:namespace />isPdfByte" name="<portlet:namespace />isPdfByte" value="N" />
			<input type="hidden" class="form-control" id="<portlet:namespace />productType" name="<portlet:namespace />productType" value="${productCustomizeSummaryData['iNGPlanSubType']}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />savedPolicyOption" name="<portlet:namespace />savedPolicyOption" value="${productCustomizeSummaryData['productDefaultValueData'].policyOption.key}" />
			<input type="hidden" name="<portlet:namespace />quotationId" id="<portlet:namespace />quotationId" /> 
			<input type="hidden" name="<portlet:namespace />totalReturns" id="<portlet:namespace />totalReturns" /> 
			<input type="hidden" name="<portlet:namespace />projectedReturnsData" id="<portlet:namespace />projectedReturnsData" /> 
			<input type="hidden" name="<portlet:namespace />illustrationURL" id="<portlet:namespace />illustrationURL" />
			
			<div class="edelweissForm product-form">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div class="edelweissTokioForm" id="edelweissTokioForm1" >
							<div class="row owl-carousel owl-theme">
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestmentAmountApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />investmentAmount" class="pform-title mb-0"><liferay-ui:message key="investment-amount" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="amount-you-will-invest" />
												</p>
											</div>
											<div class="pos">
												<label for="" class="label-plchld"><liferay-ui:message key="rupee-sign"/> </label>
												<input type="text" class="form-control border-0 pl-0" id="<portlet:namespace />investmentAmount" name="<portlet:namespace />investmentAmount" value="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}">
											</div>
											<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
											
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyOptionApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyOption" class="pform-title mb-0"><liferay-ui:message key="policy-option" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-policy-preference" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />policyOption" name="<portlet:namespace />policyOption">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curPolicyOption" items="${productCustomizeSummaryData['policyOptionsData']}">
														<c:choose>
															<c:when test="${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == 'Myself'}">
																<option data-name="${curPolicyOption.name}" value="${curPolicyOption.key}" ${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == curPolicyOption.key ? 'selected' : ''}>${curPolicyOption.name}</option>
															</c:when>
															<c:when test="${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == 'Family' && curPolicyOption.key == 'baseCover'}">
																<option data-name="${curPolicyOption.name}" value="${curPolicyOption.key}" ${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == curPolicyOption.key ? 'selected' : ''}>${curPolicyOption.name}</option>
															</c:when>
														</c:choose>
													</c:forEach>
												</select>
											</div>
											
											<div id="editDetailsWrapper" style="display:none;">
												<a href="javascript:void(0);" id="editDetailsLink"><liferay-ui:message key="edit-details" /> <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 22 22">
                                <path fill="#999" fill-rule="nonzero" stroke="#999" stroke-width=".3" d="M19.705 2.295a4.41 4.41 0 0 0-6.243 0L2.016 13.738a.573.573 0 0 0-.162.328l-.848 6.282a.57.57 0 0 0 .162.482c.106.106.255.17.404.17.026 0 .051 0 .077-.004l6.225-.825c.315-.042.474-.332.432-.647a.576.576 0 0 0-.648-.495l-5.412.722.592-4.381 4.61 4.611c.107.107.194.17.343.17.15 0 .36-.059.467-.17L19.705 8.538A4.388 4.388 0 0 0 21 5.414a4.37 4.37 0 0 0-1.295-3.12zm-6.147 1.22l1.922 1.921L5.035 15.882 3.113 13.96 13.558 3.514zm-5.825 15.06l-1.88-1.879L16.298 6.251l1.88 1.879L7.732 18.576zM18.979 7.308L14.38 2.71a3.255 3.255 0 0 1 2.08-.746c.874 0 1.692.341 2.31.955.618.613.954 1.436.954 2.31 0 .771-.264 1.496-.745 2.08z"></path>
                            </svg></a>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestingForApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />investingFor" class="pform-title mb-0"><liferay-ui:message key="investing-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="who-are-you-investing-for" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />investingFor" name="<portlet:namespace />investingFor">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curInvestingFor" items="${productCustomizeSummaryData['investingForData']}">
														<c:choose>
															<c:when test="${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == 'baseCover'}">
																<option value="${curInvestingFor.name}" ${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == curInvestingFor.name ? 'selected' : ''}>${curInvestingFor.name}</option>
															</c:when>
															<c:when test="${productCustomizeSummaryData['productDefaultValueData'].policyOption.key != 'baseCover' && curInvestingFor.name == 'Myself'}">
																<option value="${curInvestingFor.name}" ${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == curInvestingFor.name ? 'selected' : ''}>${curInvestingFor.name}</option>
															</c:when>
														</c:choose>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyTermApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyTerm" class="pform-title mb-0"><liferay-ui:message key="policy-term" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="duration-of-your-policy" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />policyTerm" name="<portlet:namespace />policyTerm">
													<option value=""><liferay-ui:message key="select" /></option>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyForApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />payFor" class="pform-title mb-0"><liferay-ui:message key="pay-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="no-of-years-you-will-pay-the-premium" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />payFor" name="<portlet:namespace />payFor">
													<option value=""><liferay-ui:message key="select" /></option>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPaymentOptionApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />paymentOption" class="pform-title mb-0"><liferay-ui:message key="payment" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-payment-frequency" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />paymentOption" name="<portlet:namespace />paymentOption">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curPaymentOption" items="${productCustomizeSummaryData['paymentOptionsData']}">
														<option data-name="${curPaymentOption.name}" value="${curPaymentOption.key}" ${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>${curPaymentOption.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isFundManagementApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />fundManagement" class="pform-title mb-0"><liferay-ui:message key="fund-management" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="investment-strategy" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />fundManagement" name="<portlet:namespace />fundManagement">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curFundManagement" items="${productCustomizeSummaryData['fundManagementData']}">
														<option value="${curFundManagement.key}" ${productCustomizeSummaryData['productDefaultValueData'].fundManagement.key == curFundManagement.key ? 'selected' : ''}>${curFundManagement.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isSWPApplicable()}">
									<div class=" item" id="<portlet:namespace />swpWarpper">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />swp" class="pform-title mb-0"><liferay-ui:message key="swp" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="systematic-withdrawal-plan" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />swp" name="<portlet:namespace />swp">
													<c:forEach var="curSWPMaster" items="${swpMasterPicklistData}">
														<option data-name="${curSWPMaster.value}" value="${curSWPMaster.key}"
															${productCustomizeSummaryData['productDefaultValueData'].swp.key == curSWPMaster.key ? 'selected' : ''}>${curSWPMaster.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isFundValueToBeWithdrawnApplicable()}">
									<div class="item swp-options" style="display:none;">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />fundValuetobeWithdrawn" class="pform-title mb-0"><liferay-ui:message key="withdrawal-percentage" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="choose-a-withdrawal" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />fundValuetobeWithdrawn" name="<portlet:namespace />fundValuetobeWithdrawn">
													<c:forEach var="curFundWithdrawalPercent" items="${fundWithdrawalPercentPicklistData}">
														<option data-name="${curFundWithdrawalPercent.value}" value="${curFundWithdrawalPercent.key}" ${productCustomizeSummaryData['productDefaultValueData'].fundValueToBeWithdrawn.key == curFundWithdrawalPercent.key ? 'selected' : ''}>${curFundWithdrawalPercent.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyYearFromWhichSWPPayableApllicable()}">
									<div class="item swp-options" style="display:none;">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyYearFromWhichSWPPayable" class="pform-title mb-0"><liferay-ui:message key="withdrawal-year" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="choose-your-withdrawal-year" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />policyYearFromWhichSWPPayable" name="<portlet:namespace />policyYearFromWhichSWPPayable">
													<c:forEach var="curSWPWithdrawalYear" items="${swpWithdrawalYearPicklistData}">
														<option data-name="${curSWPWithdrawalYear.value}" value="${curSWPWithdrawalYear.key}" ${productCustomizeSummaryData['productDefaultValueData'].policyYearFromWhichSWPPayable.key == curSWPWithdrawalYear.key ? 'selected' : ''}>${curSWPWithdrawalYear.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isSWPFrequencyApplicable()}">
									<div class="item swp-options" style="display:none;">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />swpFrequency" class="pform-title mb-0"><liferay-ui:message key="fund-withdrawal" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="choose-withdrawal-frequency" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />swpFrequency" name="<portlet:namespace />swpFrequency">
													<c:forEach var="curWithdrawalFrequency" items="${fundWithdrawalFrequencyPicklistData}">
														<option data-name="${curWithdrawalFrequency.value}" value="${curWithdrawalFrequency.key}" ${productCustomizeSummaryData['productDefaultValueData'].swpFrequency.key == curWithdrawalFrequency.key ? 'selected' : ''}>${curWithdrawalFrequency.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
							</div>
						</div>
				
						<div class="edelweissTokioTotalReturns">
							<!-- Download Brochure -->
							<div class="download-brochure">
							<div class="left">
								<a class="rise-plus-brochure d-none d-md-block" href="${productCustomizeSummaryData['productBrochure'].link.href}" 
									id="<portlet:namespace/>planBrochure"
									download="${productCustomizeSummaryData['productBrochure'].link.label}">
									<liferay-ui:message key="label-download-brochure" />
								</a>
								<div class="premium-form-header">
									<div class="title">
										<liferay-ui:message key="total-returns" />
									</div>
									<div class="sub-title">
										<liferay-ui:message key="at-the-end-of-policy-term" />
									</div>
								</div>
								<div class="histy-amt-wrpr d-flex align-items-end">
									<div class="histy-amt-content">
										<div class="return-amt">
											<span class="rupee"><liferay-ui:message key="rupee-sign"/> </span><span id="returnAmtVal"></span>
										</div>
									</div>
								</div>
		                         </div>
								<div class="button-wrap button-ckeck">
									<div class="totalnav-dropwdown">
										<div class="labelInputGroup">
											<div class="select-container">
												<select class="form-control" name="<portlet:namespace />totalReturnsValue" id="<portlet:namespace />totalReturnsValue">
													<option value="4"><liferay-ui:message key="four-percent" /></option>
													<option value="8" selected><liferay-ui:message key="eight-percent" /></option>
												</select>
											</div>
										</div>
									</div>
										<input type="button" id="<portlet:namespace />proceedBtn" value="PROCEED" class="edto-btn-primary">		
								</div>
							</div>
						</div>
					</div>
				</div>
 			</div>
		
		
		</form>
		
		

		<%@ include file="/family-details.jsp"%>
		<%@ include file="/customize-features-section.jsp"%>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="please-select-product-name-from-page-setting" />
		</div>
	</c:otherwise>
</c:choose>


<%@ include file="child-spouse-details.jsp"%>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="errorMessageModal" style="display: none;">
<div class="modal-dialog modal-dialog-centered popup-container modal-sm" role="document">
		<div class="modal-content">			
			<div class="modal-body">
				<div class="modal-header m-1 p-1">
							<h2 class="fs22 fontbold  w-100">
					<liferay-ui:message key="error" />
				</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div id="errorMessageContainer"><liferay-ui:message key="service-unavailable-error-msg" /></div>
				<div class="modal-footer p-1" style="border: unset;">
				<button type="button" class="edto-btn-primary" data-dismiss="modal">
					<liferay-ui:message key="ok" />
				</button>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
	.full-div{
		display: inline-block !important;width: 100%;
	}
</style>

<script>
var saveBasicDetailsURL = "${saveBasicDetailsURL}"; 
var getBasicDetailsURL = "${getBasicDetailsURL}";
var customerDob="${basicDetailsMap['dateOfBirth']}";

var basicInvestingFor = '${basicDetailsMap["basicInvestingFor"]}';
if(basicInvestingFor == 'Family'){
	var assuranceFullName = '${basicDetailsMap["assuranceFullName"]}';
	var assuranceDob = '${basicDetailsMap["assuranceDob"]}';
	var assuredRelationValue = '${basicDetailsMap["assuredRelation"]}';
	customerDob="${basicDetailsMap['assuranceDob']}";
}

var LANG_MESSAGES = {};
LANG_MESSAGES["graph-label-booster-premium"] = "<liferay-ui:message key='graph-label-booster-premium' />";
LANG_MESSAGES["graph-label-addition"] = "<liferay-ui:message key='graph-label-addition' />";
LANG_MESSAGES["graph-label-funds"] = "<liferay-ui:message key='graph-label-funds' />";
LANG_MESSAGES["benchmark"] = "<liferay-ui:message key='benchmark' />";
LANG_MESSAGES["sfin-label"] = "<liferay-ui:message key='sfin-label' />";
LANG_MESSAGES["grand-son"] = "<liferay-ui:message key='grand-son' />";
LANG_MESSAGES["grand-daughter"] = "<liferay-ui:message key='grand-daughter' />";
LANG_MESSAGES["sons-full-name"] = "<liferay-ui:message key='sons-full-name' />";
LANG_MESSAGES["sons-dob"] = "<liferay-ui:message key='sons-dob' />";
LANG_MESSAGES["daughters-full-name"] = "<liferay-ui:message key='daughters-full-name' />";
LANG_MESSAGES["daughters-dob"] = "<liferay-ui:message key='daughters-dob' />";
LANG_MESSAGES["grandsons-full-name"] = "<liferay-ui:message key='grandsons-full-name' />";
LANG_MESSAGES["grandsons-dob"] = "<liferay-ui:message key='grandsons-dob' />";
LANG_MESSAGES["granddaughter-full-name"] = "<liferay-ui:message key='granddaughter-full-name' />";
LANG_MESSAGES["granddaughter-dob"] = "<liferay-ui:message key='granddaughter-dob' />";
LANG_MESSAGES["enter-your-x-full-name"] = "<liferay-ui:message key='enter-your-x-full-name' />";
LANG_MESSAGES["enter-your-x-date-of-birth"] = "<liferay-ui:message key='enter-your-x-date-of-birth' />";
LANG_MESSAGES["age-between-0-to-17-years"] = "<liferay-ui:message key='age-between-0-to-17-years' />";
LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
LANG_MESSAGES["enter-your-son-full-name"] = "<liferay-ui:message key='enter-your-son-full-name' />";
LANG_MESSAGES["enter-your-daughter-full-name"] = "<liferay-ui:message key='enter-your-daughter-full-name' />";
LANG_MESSAGES["enter-your-grand-son-full-name"] = "<liferay-ui:message key='enter-your-grand-son-full-name' />";
LANG_MESSAGES["enter-your-grand-daughter-full-name"] = "<liferay-ui:message key='enter-your-grand-daughter-full-name' />";
LANG_MESSAGES["age-between-18-to-60-years"] = "<liferay-ui:message key='age-between-18-to-60-years' />";
LANG_MESSAGES["age-between-0-to-15-years"] = "<liferay-ui:message key='age-between-0-to-15-years' />";
LANG_MESSAGES["your-childs-full-details"] = "<liferay-ui:message key='your-childs-full-details' />";
LANG_MESSAGES["your-spouses-full-details"] = "<liferay-ui:message key='your-spouses-full-details' />";
LANG_MESSAGES["enter-your-spouse-name"] = "<liferay-ui:message key='enter-your-spouse-name' />";
LANG_MESSAGES["enter-your-childs-name"] = "<liferay-ui:message key='enter-your-childs-name' />";
LANG_MESSAGES["your-spouses-date-of-birth"] = "<liferay-ui:message key='your-spouses-date-of-birth' />";
LANG_MESSAGES["your-childs-date-of-birth"] = "<liferay-ui:message key='your-childs-date-of-birth' />";


$(document).ready(function () {
	var config = new Object({}),
		 namespace = '<portlet:namespace />',
		 edelweissCustomizeFundsURL = "${edelweissCustomizeFundsURL}",
		 edelweissGenerateBIURL =  "${edelweissGenerateBIURL}",
		 saveFamilyDetailsURL = "${saveFamilyDetailsURL}",
		 updateLMSLeadURL = "${updateLMSLeadURL}",
		 deleteFamilyDetailsURL = "${deleteFamilyDetailsURL}",
		 updateLittleChampBenefitURL = "${updateLittleChampBenefitURL}",
		 saveFundDetailsURL = "${saveFundDetailsURL}",
		 loadAllocatedFundsURL = "${loadAllocatedFundsURL}",
		 saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}",
		 loadFamilyDetailsURL = "${loadFamilyDetailsURL}";
		 
		 config.namespace = namespace;
		 
		 config.wspPolicyTermPicklistData = '${wspPolicyTermPicklistData}';
		 config.wspPayForPicklistData = '${wspPayForPicklistData}';
		 
		 config.defaultPolicyTerm = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()}";
		 config.defaultPayFor = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyFor()}";
		 config.productInvestmentAmountRelsData = '${productInvestmentAmountRelsData}';
		 
		 config.investingForVal = "${productCustomizeSummaryData['productDefaultValueData'].investingFor.name}";
		 config.cookieLeadId = "${cookieLeadId}";
		 config.assuredRelationVal = "${basicDetailsMap['assuredRelation']}";
		 config.productPolicyOptionRelData = '${productPolicyOptionRelData}';
		 config.productInvestingForRelData = '${productInvestingForRelData}';
		 
		 config.edelweissCustomizeFundsURL = edelweissCustomizeFundsURL;
		 config.edelweissGenerateBIURL = edelweissGenerateBIURL;
		 config.saveFamilyDetailsURL = saveFamilyDetailsURL;
		 config.updateLMSLeadURL = updateLMSLeadURL;
		 config.deleteFamilyDetailsURL = deleteFamilyDetailsURL;
		 config.updateLittleChampBenefitURL = updateLittleChampBenefitURL;
		 config.saveFundDetailsURL = saveFundDetailsURL;
		 config.loadAllocatedFundsURL = loadAllocatedFundsURL;
		 config.saveCustomerInvestmentDataURL = saveCustomerInvestmentDataURL;
		 config.loadFamilyDetailsURL = loadFamilyDetailsURL;
		 config.LANG_MESSAGES = LANG_MESSAGES;
	 
	 edelweissProductCustomizePortlet.viewWealthSecurePlusCustomize(config);
});
</script>

<script src="<%=request.getContextPath()%>/js/basic_detail.js?t=${currentTimeStamp}"></script>
<script src="<%=request.getContextPath()%>/js/wealth-secure-plus.js?t=${currentTimeStamp}"></script>

<%@ include file="/webengage-customize.jsp"%>
<%--
<script type="text/javascript">
	var productName = "${productMetaData['productName']}";
	$("#<portlet:namespace />proceedBtn").click(function() {
		
		var eventname = "wsp page 3";
	    var returnurl = window.location.href;
		
	    
		console.log("product-details::",productName);

		var premiumAmount = "";
		var sumAssured = "";
		var guaranteedBenefit = "";
		var returnsAt4Per = "";
		var returnsAt8Per = "";
		var planOption = "";
		var premiumPaymentTerm = "";
		var policyTerm = "";
		var paymentFrequency = "";
		var investingFor = "";
		var edelweissEmployee = false;
		var fundManagement = "";
		var swp = false;
		var equityLargeCapFund = "100";
		var equityTop250Fund = "";
		var bondFund = "";
		var equityMidCapFund = "";
		var managedFund = "";
		var giltFund = "";
		var equityBlueChipFund = "";

		var premiumAmount = $('#<portlet:namespace />investmentAmount').val();
		premiumAmount = premiumAmount.replaceAll(',','');

		var payOption = $('#<portlet:namespace />paymentOption').find(":selected").text();
		if(payOption==="Annual"){
			sumAssured = premiumAmount;
		}else if(payOption==="Semi-Annual"){
			sumAssured = premiumAmount*2;
		}else if(payOption==="Quarterly"){
			sumAssured = premiumAmount*4;
		}else if(payOption==="Monthly"){
			sumAssured = premiumAmount*12;
		}
		
		returnsAt4Per = percentReturnAmountObj["4"];
		returnsAt8Per = percentReturnAmountObj["8"];
		planOption = $('#<portlet:namespace />policyOption').find(":selected").text();
		premiumPaymentTerm = $('#<portlet:namespace />payFor').find(":selected").text();
		policyTerm = $('#<portlet:namespace />policyTerm').find(":selected").text();
		paymentFrequency =  $('#<portlet:namespace />paymentOption').find(":selected").text();
		investingFor = $('#<portlet:namespace />investingFor').find(":selected").text();
		fundManagement = $('#<portlet:namespace />fundManagement').find(":selected").text();
		swp = $('#<portlet:namespace />swp').find(":selected").text().toLowerCase()!='no'?true:false;

		var custAllocatedFundDetailsRefJson = $("#<portlet:namespace/>customerAllocatedFundDetails").val();
		if(Object.keys(custAllocatedFundDetailsRefJson).length===0){
			console.log('---customer-allocated-fund-details-is-empty---');
		}else{
			var curAllocatedFundRef = JSON.parse(custAllocatedFundDetailsRefJson);
			curAllocatedFundRef.forEach(fundRef => {
				var fundRefKey = fundRef["name"].replace(/ /g,'');
				if(fundRefKey==='EquityLargeCapFund'){
					equityLargeCapFund = fundRef["allocation"];
				}else if(fundRefKey==='EquityTop250Fund'){
					equityTop250Fund = fundRef["allocation"];
				}else if(fundRefKey==='BondFund'){
					bondFund = fundRef["allocation"];
				}else if(fundRefKey==='EquityMidCapFund'){
					equityMidCapFund = fundRef["allocation"];
				}else if(fundRefKey==='ManagedFund'){
					managedFund = fundRef["allocation"];
				}else if(fundRefKey==='GILTFund'){
					giltFund = fundRef["allocation"];
				}else if(fundRefKey==='EquityBlueChipFund'){
					equityBlueChipFund = fundRef["allocation"];
				}
			});
		}
		
	    webengage.track(eventname, {
	 	    "Product Name" : productName.toString(),
	 	   	"Premium Amount" : Number(premiumAmount),
	 	  	"Sum Assured" : Number(sumAssured),
	 	  	"Guaranteed Benefit" : Number(guaranteedBenefit),
	 	  	"Returns @4%" : Number(returnsAt4Per),
	 	  	"Returns @8%" : Number(returnsAt8Per),
	 	  	"Plan Option" : planOption.toString(),
	 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
	 	  	"Policy Term" : Number(policyTerm),
	 	  	"Payment Frequency" : paymentFrequency.toString(),
	 	  	"Investing For" : investingFor.toString(),
	 	  	"Edelweiss Employee" : edelweissEmployee,
	 	  	"Fund Management" : fundManagement.toString(),
	 	  	"SWP" : swp,
	 	  	"Equity Large Cap Fund" : Number(equityLargeCapFund),
	 	  	"Equity Top 250 Fund" : Number(equityTop250Fund),
	 	  	"Bond Fund" : Number(bondFund),
	 	  	"Equity Mid Cap Fund" : Number(equityMidCapFund),
	 	  	"Managed Fund" : Number(managedFund),
	 	  	"GILT Fund" : Number(giltFund),
	 	  	"Equity Blue Chip Fund" : Number(equityBlueChipFund),
	 	    "URL": returnurl
	       });	
	});
	
</script>
--%>