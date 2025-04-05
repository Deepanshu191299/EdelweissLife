<%@page import="in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants"%>
<%@ include file="/init.jsp"%>
<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>

<portlet:resourceURL id="/edelweiss/generate-bi" var="edelweissGenerateBIURL">
</portlet:resourceURL>

<portlet:resourceURL var="saveBasicDetailsURL" id="/save/basicDetails"></portlet:resourceURL>

<portlet:resourceURL var="saveFamilyDetailsURL" id="/save/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="deleteFamilyDetailsURL" id="/delete/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="updateLittleChampBenefitURL" id="/update/littleChampBenefit"></portlet:resourceURL>

<portlet:resourceURL var="saveFundDetailsURL" id="/save/fundDetails"></portlet:resourceURL>


<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>

<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>

<portlet:resourceURL var="getBasicDetailsURL" id="/get/BasicDetails"></portlet:resourceURL>

<%

Map<String, String> productMetaData = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("productMetaData")))
{
	productMetaData = (Map<String, String>)renderRequest.getAttribute("productMetaData");
}
%>
<!-- Loader -->
<div class="preloader" id="preloader"></div>
<%@ include file="/product_jsps/premier-guaranteed-income/edit_basic_details.jsp" %>
<%-- <%@ include file="/edit_basic_details.jsp" %> --%>

<div class="edelweissTokioDetails p-2">
	<div class="container">
		<div class="edelweissTokioDetailsInner d-flex align-items-center">
			<div class="edelweissTokioHeading">
				<h5 class="fontbold">Your Details</h5>
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
			<input type="hidden" class="form-control" id="<portlet:namespace />customerFamilyDetailsId" name="<portlet:namespace />customerFamilyDetailsId" value="${customerFamilyDetailsId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />premiumAmount" name="<portlet:namespace />premiumAmount"   value=""/>
			<input type="hidden" class="form-control" id="<portlet:namespace />basePremiumAmount" name="<portlet:namespace />basePremiumAmount" type="hidden" value=""/>
			<input type="hidden" class="form-control" id="<portlet:namespace />totalMaturityAmount" name="<portlet:namespace />totalMaturityAmount" type="hidden" value=""/>
			<input type="hidden" name="<portlet:namespace />illustrationURL" id="<portlet:namespace />illustrationURL" />
			<input type="hidden" name="<portlet:namespace />quotationId" id="<portlet:namespace />quotationId" /> 
			
			<input type="hidden" class="form-control" id="<portlet:namespace />selectedRidersIds" name="<portlet:namespace />selectedRidersIds" type="hidden" value='${productCustomizeSummaryData['productDefaultValueData'].selectedRidersIds}'/>

			
			<%-- To Show Little Champ Modal and Submit It from Little Champ --%>
			<input type="hidden" class="form-control" id="<portlet:namespace />isFormSubmitPending" name="<portlet:namespace />isFormSubmitPending" value="false" />
			
			<div class="edelweissForm">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div id="edelweissTokioForm1" class="edelweissTokioForm">
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
												<input type="text" name="<portlet:namespace /><%=ParameterConstants.INVESTMENT_AMOUNT %>" id="<portlet:namespace /><%=ParameterConstants.INVESTMENT_AMOUNT %>" label="" class="form-control investmentAmountInput border-0 pl-0"
													data-default-monthly-amount="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}"
													value="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}" />
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
													<c:forEach var="curPolicyOption" items="${productCustomizeSummaryData['policyOptionsData']}">
														<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyOptionApplicable()}">
																<option data-name="${curPolicyOption.name}" value="${curPolicyOption.key}" ${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == curPolicyOption.key ? 'selected' : ''}>${curPolicyOption.name}</option>
														</c:if>
													
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyForApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyFor" class="pform-title mb-0"><liferay-ui:message key="pay-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="no-of-years-you-will-pay-the-premium" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />policyFor" name="<portlet:namespace />policyFor">
													<c:forEach var="curPayFor" items="${payForDataMap}">
														<option value="${curPayFor.key}"
															${productCustomizeSummaryData['productDefaultValueData'].policyFor == curPayFor.value ? 'selected' : ''}>
															${curPayFor.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGetIncomeForApplicable()}">
									<div class=" item" id="<portlet:namespace />getIncomeForDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />getIncomeFor" class="pform-title mb-0"><liferay-ui:message key="get-income-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="income-benefit-payout-duration" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />getIncomeFor" name="<portlet:namespace />getIncomeFor">
													<option value=""><liferay-ui:message key="select" /></option>
														<c:forEach var="curGetIncomeFor" items="${getIncomeForDataMap}">
															<option value="${curGetIncomeFor.key}"
																${productCustomizeSummaryData['productDefaultValueData'].getIncomeFor == curGetIncomeFor.value ? 'selected' : ''}>
																${curGetIncomeFor.value}</option>
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
													<c:forEach var="curPolictTerm" items="${policyTermDataMap}">
															<option value="${curPolictTerm.key}"
																${productCustomizeSummaryData['productDefaultValueData'].policyTerm == curPolictTerm.value ? 'selected' : ''}>
																${curPolictTerm.value}</option>
														</c:forEach>
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
													<c:forEach var="curPaymentOption" items="${productCustomizeSummaryData['paymentOptionsData']}">
														<option data-name="${curPaymentOption.name}" value="${curPaymentOption.key}" ${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>${curPaymentOption.name}</option>
													</c:forEach>
												</select>
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
													<c:forEach var="curInvestingFor" items="${productCustomizeSummaryData['investingForData']}">
														<option value="${curInvestingFor.name}" ${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == curInvestingFor.name ? 'selected' : ''}>${curInvestingFor.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isFamilyIncomeBenefitApplicable()}">
									<div class=" item" id="<portlet:namespace />familyIncomeBenefitDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />familyIncomeBenefit" class="pform-title mb-0"><liferay-ui:message key="familyIncomeBenefit" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="family-income-benefit-content" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />familyIncomeBenefit" name="<portlet:namespace />familyIncomeBenefit">
													<c:forEach var="curfamilyIncomBenefit" items="${familyIncomBenefitrPicklistData}">
														<option data-name="${curfamilyIncomBenefit.value}" value="${curfamilyIncomBenefit.key}"
															${productCustomizeSummaryData['productDefaultValueData'].familyIncomeBenefit.key == curfamilyIncomBenefit.key ? 'selected' : ''}>${curfamilyIncomBenefit.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isIncomePayoutFrequencyApplicable()}">
									<div class=" item" id="<portlet:namespace />incomePayoutFrequencyDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />incomePayoutFrequency" class="pform-title mb-0"><liferay-ui:message key="incomePayoutFrequency" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="income-payout-frequency" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />incomePayoutFrequency" name="<portlet:namespace />incomePayoutFrequency">
													<c:forEach var="curincomePayoutFrequency" items="${incomePayoutFrequencyPicklistData}">
														<option data-name="${curincomePayoutFrequency.value}" value="${curincomePayoutFrequency.key}"
															${productCustomizeSummaryData['productDefaultValueData'].incomePayoutFrequency.key == curincomePayoutFrequency.key ? 'selected' : ''}>${curincomePayoutFrequency.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isLumpSumBenefitApplicable()}">
									<div class=" item" id="<portlet:namespace />lumpSumBenefitDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />lumpSumBenefit" class="pform-title mb-0"><liferay-ui:message key="lumpSumBenefit" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="lump-sum-benefit" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />lumpSumBenefit" name="<portlet:namespace />lumpSumBenefit">
													<c:forEach var="curlumpSumBenefit" items="${lumpSumBenefitPicklistData}">
														<option data-name="${curlumpSumBenefit.value}" value="${curlumpSumBenefit.key}"
															${productCustomizeSummaryData['productDefaultValueData'].lumpSumBenefit.key == curlumpSumBenefit.key ? 'selected' : ''}>${curlumpSumBenefit.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isMaturityPayoutApplicable()}">
								<div class=" item" id="<portlet:namespace />maturityPayoutOptionDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />maturityPayoutOption" class="pform-title mb-0"><liferay-ui:message key="maturity-Payout-Option" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="maturity-payout-option-content" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />maturityPayoutOption" name="<portlet:namespace />maturityPayoutOption">
													<c:forEach var="curmaturityPayoutOption" items="${maturityPayoutOptionPicklistData}">
														<option data-name="${curmaturityPayoutOption.value}" value="${curmaturityPayoutOption.key}"
															${productCustomizeSummaryData['productDefaultValueData'].maturityPayout.key == curmaturityPayoutOption.key ? 'selected' : ''}>${curmaturityPayoutOption.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isEdelweissEmployeeApplicable()}">
									<div class=" item" id="<portlet:namespace />isEdelweissEmployeeDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />isEdelweissEmployee" class="pform-title mb-0"><liferay-ui:message key="isEdelweissEmployee" /></label>
											</div>
											<div class="select-container mt-3">
												<select class="form-control" id="<portlet:namespace />isEdelweissEmployee" name="<portlet:namespace />isEdelweissEmployee">
													<c:forEach var="curisEdelweissEmployee" items="${isEdelweissEmployeePicklistData}">
														<option data-name="${curisEdelweissEmployee.value}" value="${curisEdelweissEmployee.key}"
															${productCustomizeSummaryData['productDefaultValueData'].isEdelweissEmployee.key == curisEdelweissEmployee.key ? 'selected' : ''}>${curisEdelweissEmployee.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<div class="item" style="display:none;">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />employeeCode" class="pform-title mb-0"><liferay-ui:message key="employee-code"/></label>
												<p class="premiuim-small-text"><liferay-ui:message key="your-employee-code"/></p>
											</div>
											<div class="pos">
												<input type="text" name="<portlet:namespace />employeeCode" id="<portlet:namespace />employeeCode" label="" class="form-control investmentAmountInput border-0 pl-0" oninput="validateEmployeeCodeInput($(this))"/ >
											</div>
											<div style="display:none; font-size:10px; color:#F44336 !important;" id="error-message" >Please enter valid Employee Code.</div>
										</div>
								</div>
							</div>
						</div>

					<div class="edelweissTokioTotalReturns">
							<div class="download-brochure download-broucher-mob">
							
                                  <div class="left">
									<div class="d-none d-md-block" id="<portlet:namespace/>planBrochureContainer">
										<a
							href="${productCustomizeSummaryData['productBrochure'].link.href}"
							id="<portlet:namespace/>planBrochure"
							download="${productCustomizeSummaryData['productBrochure'].link.label}">
							<liferay-ui:message key="label-download-brochure" />
						</a>
									</div>

									<div class="premium-form-header">
			                     	<div class="title">
										<liferay-ui:message key="label-total-guaranteed-benefits" />
									</div>
									</div>
									<div class="histy-amt-wrpr d-flex align-items-end">
				                    <div class="histy-amt-content">
									<div class="<portlet:namespace/>totalGuaranteedBenefits return-amt">
										<span class="rupee"><liferay-ui:message
												key="rupee-sign" /></span> <span
											id="<portlet:namespace/>totalGuaranteedBenefitAmount">0</span>
									</div>
                                    </div>
									</div>
									</div>
									<div class="break-proceed-group">
										<aui:button type="button" id="benefitAmountBreakup"
											cssClass="btn btn-primary breakup_btn float-left mx-0"
											value="label-amount-breakup" data-toggle="modal"
											data-target="#planMaturityBenefitsBreakup">
										</aui:button>
										<aui:button type="button" id="proceedBtn" disabled="true"
											cssClass="btn btn-primary edto-btn-primary float-right mb-2" value="label-proceed">
										</aui:button>
									</div>
								
							</div>
						</div>
						
					</div>
				</div>
 			</div>
		</form>
		<%@ include file="/product_jsps/premier-guaranteed-income/family-details.jsp"%>
		<%@ include file="/product_jsps/premier-guaranteed-income/product_features.jsp"%>
		<%@ include file="/product_jsps/premier-guaranteed-income/product_riders.jsp"%>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="please-select-product-name-from-page-setting" />
		</div>
	</c:otherwise>
</c:choose>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="errorMessageModal" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container modal-sm" role="document">
		<div class="modal-content">			
			<div class="modal-body">
			<div class="modal-header m-1 p-1">
							<h2 class="fs22 fontbold  w-100">
					<liferay-ui:message key="error" />
				</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
</svg>
					</span>
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

<div class="modal  maturitybenefittable" tabindex="-1" role="dialog" id="planMaturityBenefitsBreakup" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container" role="document">
		<div class="modal-content maturity-benefit-wrapper">
			<div class="modal-header">
				<h2 class="modal-title"><liferay-ui:message key="label-maturity-benefits" /></h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			   <div class="table-wrap">
				<table class="maturity-table table table-head scrl-tbl-w table-border" width="100%" id="planMaturityBenefitsBreakupTable">
			        <thead style="display: table;">
			        <tr>	        
			            <td>Policy Year</td>
			            <td>Annualized Premium</td>
			            <td>Income Benefit</td>
			            <td>Maturity Benefit (INR)</td>
			            <td>Death Benefit</td>
			        </tr>
			        </thead>
			        <tbody class="scrl-tbl-hgt m-cust-scroll maturity-data-table" id="planMaturityBenefitsBreakupTableBody">
			           
			        </tbody>
				</table>			
              </div>
		       <!-- 	<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><liferay-ui:message key="ok" /></button>
			    </div>
			    -->
		</div>
		</div>
	</div>
</div>
<script>
var saveBasicDetailsURL = "${saveBasicDetailsURL}"; 
var basicInvestingFor = '${basicDetailsMap["basicInvestingFor"]}';
var isNRI ='${basicDetailsMap["isNRI"]}';
var nRIMobileNumber  ='${basicDetailsMap["nRIMobileNumber"]}';
var getBasicDetailsURL = "${getBasicDetailsURL}";

var LANG_MESSAGES = {};
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

if(basicInvestingFor == 'Family'){
	var assuranceFullName = '${basicDetailsMap["assuranceFullName"]}';
	var assuranceDob = '${basicDetailsMap["assuranceDob"]}';
	var assuredRelationValue = '${basicDetailsMap["assuredRelation"]}';
}
var selectedRidersIds = {};
try{
	selectedRidersIds = JSON.parse('${productCustomizeSummaryData["productDefaultValueData"].selectedRidersIds}');
}catch(e){
	console.error(e);
}
//validate EmployeeCode on onInput 


function validateEmployeeCodeInput(input) {
	 const errorMessage = $('#error-message');
	    let value = input.val();
	    // Remove non-numeric characters
	    value = value.replace(/[^\d]/g, '');
	    input.val(value);
	    // Limit the input to 6 digits
	    if (value.length > 6) {
	        input.val(value.slice(0, 6));
	        value = value.slice(0, 6);  // Ensure the length is updated after slicing
	    }
	    // Show or hide the error message
	    if (value.length === 6 || value.length === 0) {
	        errorMessage.hide();
	    } else {
	        errorMessage.show();
	    }
	}


$(document).ready(function () {
	
	 $('#<portlet:namespace />isEdelweissEmployee').change(function() {
		 console.log("inside change condition");
	        var selectedValue = $(this).val();
	        if (selectedValue === '3') {
	        	console.log("inside yes condition");
	            $('#<portlet:namespace />employeeCode').closest('.item').show();
	        } else {
	            $('#<portlet:namespace />employeeCode').closest('.item').hide();
	        }
	    });
	
	var config = new Object({}),
		 namespace = '<portlet:namespace />',
		 customizeFormJEl = "#" + namespace + "customizeForm",
		 investingForJEl = "#" + namespace + "investingFor",
		 
		 policyOptionEl = namespace + "policyOption",
		 policyTermEl = namespace + "policyTerm",
		 policyForEl = namespace + "policyFor",
		 getIncomeForEl= namespace + "getIncomeFor",
		 employeeCodeEl = namespace + "employeeCode",
		 isEdelweissEmployeeEl= namespace + "isEdelweissEmployee",
		 incomePayoutFrequencyEl=namespace + "incomePayoutFrequency",
		 paymentOptionEl = namespace + "paymentOption",
		 investmentAmountEl = namespace + "investmentAmount",
		 familyIncomeBenefitEl=namespace+"familyIncomeBenefit",
		 lumpSumBenefitEl= namespace + "lumpSumBenefit",
		 maturityPayoutOptionEl=  namespace +"maturityPayoutOption",
		 
		 investmentAmountJEl =  "#" + investmentAmountEl,
		 policyOptionJEl = "#" + policyOptionEl,
		 policyTermJEl = "#" + policyTermEl,
		 policyForJEl = "#" + policyForEl,
		 paymentOptionJEl ="#"+paymentOptionEl ,
		 getIncomeForJEl="#"+ getIncomeForEl,
		
		 familyIncomeBenefitJEl="#"+familyIncomeBenefitEl,
		 employeeCodeJEl ="#"+ employeeCodeEl,
		 isEdelweissEmployeeJEl ="#"+ isEdelweissEmployeeEl,
		 incomePayoutFrequencyJEl="#"+incomePayoutFrequencyEl,
		 lumpSumBenefitJEl= "#"+ lumpSumBenefitEl,
		 maturityPayoutOptionJEl=  "#" +maturityPayoutOptionEl,
		 
		 returnAmtValJEl = "#returnAmtVal",
		 totalReturnsValueJEl = "#" + namespace + "totalReturnsValue",
		 projectedReturnFundAllocation = "#" + namespace + "projectedReturnFundAllocation";
		 fundDataTableJEl = "#fundDataTable";
		 customizeFundLinkJEl = "#customizeFundLink",
		 customizeFundTableJEl = "#customizeFundTable",
		 errorMessageModalJEl = "#errorMessageModal",
		 edelweissGenerateBIURL =  "${edelweissGenerateBIURL}",
		 chartLinkJEl = "#" + namespace + "chartLink",
		 fundTableLinkJEl = "#" + namespace + "fundTableLink",
		 highchartContainerEl = "highchart-container"
		 highchartContainerJEl = "#" + highchartContainerEl,
		 fundDataTableJEl = "#fundDataTable",
		 totalReturnsJEl = "#" + namespace + "totalReturns",
		 illustrationURLJEl = "#" + namespace + "illustrationURL",
		 quotationIdJEl = "#" + namespace + "quotationId",
		 projectedReturnsDataJEl = "#" + namespace + "projectedReturnsData",
		 fullNameJEl = "#" + namespace + "fullName",
		 dateOfBirthJEl = "#" + namespace + "dateOfBirth",
		 mobileNumberJEl = "#" + namespace + "mobileNumber",
		 emailJEl = "#" + namespace + "email",
		 assuranceFullNameJEl = "#" + namespace + "assuranceFullName",
		 assuranceDobJEl = "#" + namespace + "assuranceDob",
		 genderEl = namespace + "gender",
		 genderJEl = "#" + genderEl,
		 basicInvestingForEl = namespace + "basicInvestingFor",
		 assuredRelationEl = namespace + "assuredRelation",
		 proceedSubmitBtnJEl = "#" + namespace + "proceedBtn",
		 loaderJEl = "#spinner-div",
		 productCodeJEl = "#" + namespace + "productCode",
		 familyModalJEl = "#" + namespace + "familyModal",
		 investingForWrapperJEl = "#investingForWrapper",
		 investingForGrandsonEl = namespace + "investingForGrandson",
		 investingForGrandDaughterEl = namespace + "investingForGrandDaughter",
		 familyRadioEl = namespace + "familyRadio",
		 investingForFullNameEl = namespace + "investingForFullName",
		 investingForDOBEl = namespace + "investingForDOB",
		 investingForFullNameJEl = "#" + investingForFullNameEl,
		 investingForDOBJEl = "#" + investingForDOBEl,
		 investingForFullNameLableJEl = "#" + namespace + "investingForFullNameLable",
		 investingForDOBLabelJEl = "#" + namespace + "investingForDOBLabel",
		 saveFamilyDetailsURL = "${saveFamilyDetailsURL}",
		 saveFamilyBtnJEl = "#" + namespace + "saveFamilyBtn",
		 familyDetailsFormJEl = "#" + namespace + "familyDetailsForm",
		 doNotaddOnJEl = "#" + namespace + "doNotaddOn",
		 littleChampModalJEl = "#" + namespace + "littleChampModal",
		 updateLMSLeadURL = "${updateLMSLeadURL}",
		 productNameJEl = "#" + namespace + "productName",
		 investmentObjectiveEl = namespace + "investmentObjective",
		 deleteFamilyDetailsURL = "${deleteFamilyDetailsURL}",
		 addLittleChampBenefitJEl = "#" + namespace + "addLittleChampBenefit",
		 isLittleChampBenefitOptedJEl = "#" + namespace + "isLittleChampBenefitOpted",
		 familyModalCloseJEl = "#" + namespace + "familyModalClose",
		 errorMessageContainerJEl = "#errorMessageContainer",
		 addLittleChampBenefitURL = "${addLittleChampBenefitURL}",
		 saveFundDetailsURL = "${saveFundDetailsURL}",
		 saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}";
		 config.namespace = namespace;
		 
		 config.investmentAmountEl = investmentAmountEl;
		 config.policyOptionEl = policyOptionEl;
		 config.policyForEl = policyForEl;
		 config.getIncomeForEl=getIncomeForEl;
		 config.policyTermEl = policyTermEl;
		 config.paymentOptionEl = paymentOptionEl;
		 
		 config.investmentAmountJEl = investmentAmountJEl;
		 config.policyOptionJEl = policyOptionJEl;
		 config.policyForJEl = policyForJEl;
		 config.getIncomeForJEl=getIncomeForJEl;
		 config.policyTermJEl = policyTermJEl;
		 config.paymentOptionJEl = paymentOptionJEl;
		 config.investingForJEl = investingForJEl;
		 config.isEdelweissEmployeeJEl=isEdelweissEmployeeJEl;
		 config.familyIncomeBenefitJEl=familyIncomeBenefitJEl;
		 config.incomePayoutFrequencyJEl=incomePayoutFrequencyJEl;
		 config.maturityPayoutOptionJEl=maturityPayoutOptionJEl;
		 config.lumpSumBenefitJEl=lumpSumBenefitJEl;
		 config.employeeCodeJEl = employeeCodeJEl;
		 config.customizeFormJEl = customizeFormJEl;
		 
		 config.illustrationURLJEl = illustrationURLJEl;
		 config.quotationIdJEl = quotationIdJEl;
		 config.saveCustomerInvestmentDataURL = saveCustomerInvestmentDataURL;
     
		 config.errorMessageModalJEl = errorMessageModalJEl,

		 /* config.returnAmtValJEl = returnAmtValJEl;
		 config.totalReturnsValueJEl = totalReturnsValueJEl;
		 config.projectedReturnFundAllocation = projectedReturnFundAllocation;
		 config.fundDataTableJEl = fundDataTableJEl;
		 config.customizeFundLinkJEl = customizeFundLinkJEl;
		 config.customizeFundTableJEl = customizeFundTableJEl;
		 config.chartLinkJEl = chartLinkJEl;
		 config.fundTableLinkJEl = fundTableLinkJEl;
		 config.highchartContainerEl = highchartContainerEl;
		 config.highchartContainerJEl = highchartContainerJEl;
		 config.fundDataTableJEl = fundDataTableJEl;
		 config.totalReturnsJEl = totalReturnsJEl;
		 config.projectedReturnsDataJEl = projectedReturnsDataJEl; */
		 
		 config.pgiRelFieldValues = JSON.parse('${pgiRelFieldValues}');
		 config.pgiPolicyOptionValues=JSON.parse('${productCustomizeSummaryData["policyOptionsData"]}');
		 config.defaultPolicyTerm = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()}";
		 config.defaultPolicyFor = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyFor()}";
		 config.productInvestmentAmountRelsData = '${productInvestmentAmountRelsData}';
		 config.productMetaData = JSON.parse('<%=JSONFactoryUtil.createJSONObject(productMetaData)%>'); 
		 
		 config.edelweissGenerateBIURL = edelweissGenerateBIURL;
		 
		 config.fullNameJEl = fullNameJEl;
		 config.dateOfBirthJEl = dateOfBirthJEl;
		 config.emailJEl = emailJEl;
		 config.assuranceFullNameJEl = assuranceFullNameJEl;
		 config.assuranceDobJEl = assuranceDobJEl;
		 config.genderJEl = genderJEl;
		 config.genderEl = genderEl;
		 config.mobileNumberJEl = mobileNumberJEl;
		 config.basicInvestingForEl = basicInvestingForEl;
		 config.assuredRelationEl = assuredRelationEl;
		 config.proceedSubmitBtnJEl = proceedSubmitBtnJEl;
		 config.loaderJEl = loaderJEl;
		 config.productCodeJEl = productCodeJEl;
		 config.familyModalJEl = familyModalJEl;
		 config.investingForWrapperJEl = investingForWrapperJEl;
		 config.investingForGrandsonEl = investingForGrandsonEl;
		 config.investingForGrandDaughterEl = investingForGrandDaughterEl;
		 config.familyRadioEl = familyRadioEl;
		 
		 config.investingForFullNameEl = investingForFullNameEl;
		 config.investingForDOBEl = investingForDOBEl;
		 
		 config.investingForFullNameJEl = investingForFullNameJEl;
		 config.investingForDOBJEl = investingForDOBJEl;
		 config.investingForFullNameLableJEl = investingForFullNameLableJEl;
		 config.investingForDOBLabelJEl = investingForDOBLabelJEl;
		 config.saveFamilyDetailsURL = saveFamilyDetailsURL;
		 config.saveFamilyBtnJEl = saveFamilyBtnJEl;
		 config.familyDetailsFormJEl = familyDetailsFormJEl;
		 config.doNotaddOnJEl = doNotaddOnJEl;
		 config.littleChampModalJEl = littleChampModalJEl;
		 config.investmentObjectiveEl = investmentObjectiveEl;
		 
		 config.investingForVal = "${productCustomizeSummaryData['productDefaultValueData'].investingFor.name}";
		 config.cookieLeadId = "${cookieLeadId}";
		 config.assuredRelationVal = "${basicDetailsMap['assuredRelation']}";
		 config.updateLMSLeadURL = updateLMSLeadURL;
		 config.productNameJEl = productNameJEl;
		 config.deleteFamilyDetailsURL = deleteFamilyDetailsURL;
		 config.addLittleChampBenefitJEl = addLittleChampBenefitJEl;
		 config.isLittleChampBenefitOptedJEl = isLittleChampBenefitOptedJEl;
		 config.isLittleChampBenefitOpted = "${basicDetailsMap['isLittleChampBenefitOpted']}";
		 config.familyModalCloseJEl = familyModalCloseJEl;
		 config.errorMessageContainerJEl = errorMessageContainerJEl;
		 config.addLittleChampBenefitURL = addLittleChampBenefitURL;
		 config.saveFundDetailsURL = saveFundDetailsURL;
		 config.LANG_MESSAGES = LANG_MESSAGES;
	 
	 edelweissProductCustomizePortlet.viewProductCustomize(config);
	 
});

</script>

<style>
	.full-div{
		display: inline-block !important;width: 100%;
	}
</style>

<%--
<script type="text/javascript">
	
	$("#<portlet:namespace/>proceedBtn").click(function() {
		
		var eventname = "pgi";
	    var returnurl = window.location.href;
		
	    var productName = "${productMetaData['productName']}";
		console.log("product-details::",productName);
		
		var premiumAmount = "";
		var sumAssured = "";
		var guaranteedBenefit = "";
		var planOption = "";
		var premiumPaymentTerm = "";
		var policyTerm = "";
		var benefitDuration = "";
		var paymentFrequency = "";
		var investingFor = "";
		var familyIncomeBenefit = "";
		var benefitFrequency = "";
		var edelweissEmployee = false;
		var lumpsumBenefit = false;
		var benefitType = "";
		var adbRider = "";
		var atpdRider = "";
		var hcbRider = "";
		var ciRider = "";
		var wopRider = false;
		var pwbRider = false;
		var incomeBenefitRider = "";

		var premiumAmount = $('#<portlet:namespace />investmentAmount').val();
		premiumAmount = premiumAmount.replaceAll(',','');

		var payOption = $('#<portlet:namespace />paymentOption').find(":selected").text();
		if(payOption==='Annual'){
			sumAssured = premiumAmount;
		}else if(payOption==='Semi-Annual'){
			sumAssured = premiumAmount*2;
		}else if(payOption==='Quarterly'){
			sumAssured = premiumAmount*4;
		}else if(payOption==='Monthly'){
			sumAssured = premiumAmount*12;
		}
		
		guaranteedBenefit = $('#totalGuaranteedBenefitAmount span').text();
		planOption = $('#<portlet:namespace />policyOption').find(":selected").text();
		premiumPaymentTerm = $('#<portlet:namespace />policyOption').find(":selected").text();
		policyTerm = $('#<portlet:namespace />policyTerm').find(":selected").text();
		benefitDuration = $('#<portlet:namespace />getIncomeFor').find(":selected").text();
		paymentFrequency =  $('#<portlet:namespace />paymentOption').find(":selected").text();
		investingFor = $('#<portlet:namespace />investingFor').find(":selected").text();
		familyIncomeBenefit = $('#<portlet:namespace />familyIncomeBenefit').find(":selected").text();
		benefitFrequency = $('#<portlet:namespace />incomePayoutFrequency').find(":selected").text();
		lumpSumBenefit = $('#<portlet:namespace />lumpSumBenefit').find(":selected").text().toLowerCase()!='no'?true:false;
		benefitType = $('#<portlet:namespace />maturityPayoutOption').find(":selected").text();

		prodRidersList.forEach(prodRidersRef => {
			var refKey = prodRidersRef["key"];
			var riderId = prodRidersRef["riderId"];
			if(refKey==='IncomeBenefitRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					incomeBenefitRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(incomeBenefitRider===undefined) {incomeBenefitRider='';}
			}else if(refKey==='CriticalIllnessRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					ciRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(ciRider===undefined) {ciRider='';}
			}else if(refKey==='ADBRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					adbRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(adbRider===undefined) {adbRider='';}
			}else if(refKey==='WaiverOfPremiumRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					wopRider = $('#<portlet:namespace/>selectedRider_'+riderId).prop('checked')==true?'True':'False';
				}else{
					wopRider = '';
				}
			}else if(refKey==='ATPDRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					atpdRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(atpdRider===undefined) {atpdRider='';}
			}else if(refKey==='PayorWaiverBenefitRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					pwbRider = $('#<portlet:namespace/>selectedRider_'+riderId).prop('checked')==true?'True':'False';
				}else{
					pwbRider = '';
				}
			}
		});

	    webengage.track(eventname, {
	 	    "Product Name" : productName.toString(),
	 	   	"Premium Amount" : Number(premiumAmount),
	 	  	"Sum Assured" : Number(sumAssured),
	 	  	"Guaranteed Benefit" : Number(guaranteedBenefit),
	 	  	"Plan Option" : planOption.toString(),
	 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
	 	  	"Policy Term" : Number(policyTerm),
	 	  	"Benefit Duration" : Number(benefitDuration),
	 	  	"Payment Frequency" : paymentFrequency.toString(),
	 	  	"Investing For" : investingFor.toString(),
	 	  	"Family Income Benefit" : familyIncomeBenefit.toString(),
	 	  	"Benefit Frequency" : benefitFrequency.toString(),
	 	  	"Edelweiss Employee" : edelweissEmployee,
	 	  	"Lumpsum Benefit" : lumpsumBenefit,
	 	  	"Benefit Type" : benefitType.toString(),
	 	  	"ADB Rider" : Number(adbRider),
	 	  	"ATPD Rider" : Number(atpdRider),
	 	   	"HCB Rider" : Number(hcbRider),
	 	  	"CI Rider" : Number(ciRider),
	 	  	"WOP Rider" : wopRider,
	 	  	"PWB Rider" : pwbRider,
	 	  	"Income Benefit Rider" : Number(incomeBenefitRider),
	 	    "URL": returnurl
	       });	
	});
</script>
--%>
<script src="<%=request.getContextPath()%>/product_jsps/premier-guaranteed-income/js/pgi-main.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>
<script src="<%=request.getContextPath()%>/product_jsps/premier-guaranteed-income/js/pgi-basic-detail.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>
<%@ include file="/webengage-customize.jsp"%>
