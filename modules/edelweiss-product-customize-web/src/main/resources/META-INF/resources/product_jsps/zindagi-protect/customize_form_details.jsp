<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>
<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<%
Map<String, String> payForPicklistData = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("basicDetailsMap")))
{
	payForPicklistData = (Map<String, String>)renderRequest.getAttribute("payForPicklistData");
}
%>

<script>
	var saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}";
	var saveInvestmentDetails = "${saveInvestmentDetails}";

	var selectedRidersIds = {};
	try{
		selectedRidersIds = JSON.parse('${productCustomizeSummaryData["productDefaultValueData"].selectedRidersIds}');
	}catch(e){
		console.warn("Unable to Parse selectedRidersIds JSON");
	}
	var payForPicklistData = '<%=payForPicklistData%>';
	
</script>
<form name="<portlet:namespace />customizeForm" id="<portlet:namespace />customizeForm" method="post" action="${saveInvestmentDetails}">

	<input type="hidden" class="form-control" id="<portlet:namespace />productCode" name="<portlet:namespace />productCode" value="${productMetaData['productCode']}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />productName" name="<portlet:namespace />productName" value="${productMetaData['productName']}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />customerEnquiryId" name="<portlet:namespace />customerEnquiryId" value="${customerEnquiryId}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />customerInvestmentDetailsId" name="<portlet:namespace />customerInvestmentDetailsId" value="${customerInvestmentDetailsId}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />customerPolicyDetailsId" name="<portlet:namespace />customerPolicyDetailsId" value="${customerPolicyDetailsId}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />customerFamilyDetailsId" name="<portlet:namespace />customerFamilyDetailsId" value="${customerFamilyDetailsId}" />
	<input type="hidden" class="form-control" id="<portlet:namespace />premiumAmount" name="<portlet:namespace />premiumAmount"   value="" />
	<input type="hidden" class="form-control" id="<portlet:namespace />basePremiumAmount" name="<portlet:namespace />basePremiumAmount" type="hidden" value="" />
	<input type="hidden" class="form-control" id="<portlet:namespace />totalMaturityAmount" name="<portlet:namespace />totalMaturityAmount" type="hidden" value="" />
	<input type="hidden" name="<portlet:namespace />policyTerm" id="<portlet:namespace />policyTerm" />
	<input type="hidden" name="<portlet:namespace />projectedReturnsData" id="<portlet:namespace />projectedReturnsData" />
	<input type="hidden" name="<portlet:namespace />illustrationURL" id="<portlet:namespace />illustrationURL" />
	<input type="hidden" name="<portlet:namespace />quotationId" id="<portlet:namespace />quotationId" />
	<input type="hidden" name="<portlet:namespace />quotationId" id="<portlet:namespace />quotationId" />
	<input type="hidden" name="<portlet:namespace />annualPrem2" id="<portlet:namespace />annualPrem2" />
	<input type="hidden" class="form-control" id="<portlet:namespace />selectedRidersIds" name="<portlet:namespace />selectedRidersIds" type="hidden" value='${productCustomizeSummaryData["productDefaultValueData"].selectedRidersIds}'/>
	<input type="hidden" class="form-control" id="<portlet:namespace />isFormSubmitPending" name="<portlet:namespace />isFormSubmitPending" value="false" />
	
	<div class="edelweissForm">
		<div class="container">
			<div class="edelweissFormInner d-flex">
				<div id="edelweissTokioForm1" class="edelweissTokioForm">
					<div class="row owl-carousel owl-theme">
						<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isLifeCoverApplicable()}">
							<div class="item">
								<div class="labelInputGroup">
									<div class="labelGroup">
										<label for="<portlet:namespace />lifeCover" class="pform-title mb-0"><liferay-ui:message key="life-cover" /></label>
										<p class="premiuim-small-text">
											<liferay-ui:message key="your-total-financial-coverage" />
										</p>
									</div>
									<div class="pos">
										<label for="" class="label-plchld"><liferay-ui:message key="rupee-sign"/> </label>
										<input type="text" class="form-control border-0 pl-0" id="<portlet:namespace />lifeCover" name="<portlet:namespace />lifeCover" value="${productCustomizeSummaryData['productDefaultValueData'].getLifeCover()}">
									</div>
									<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
								</div>
							</div>
						</c:if>
						<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyOptionApplicable()}">
							<div class="item">
								<div class="labelInputGroup">
									<div class="labelGroup">
										<label for="<portlet:namespace />planOption" class="pform-title mb-0"><liferay-ui:message key="label-plan-option" /></label>
										<p class="premiuim-small-text">
											<liferay-ui:message key="pick-your-plan-option" />
										</p>
									</div>
									<div class="select-container">
										<select class="form-control" id="<portlet:namespace />planOption" name="<portlet:namespace />planOption" 
											data-selected-value="${productCustomizeSummaryData['productDefaultValueData'].policyOption.key }">
											<c:forEach var="curPlanOption" items="${productCustomizeSummaryData['policyOptionsData']}">
												
												<option data-name="${curPlanOption.name}" value="${curPlanOption.key}" 
													${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == curPlanOption.key ? 'selected' : ''}>
													${curPlanOption.name}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</c:if>
							<div class=" item">
								<div class="labelInputGroup">
									<div class="labelGroup">
										<label for="<portlet:namespace />custPolicyTerm" class="pform-title mb-0"><liferay-ui:message key="policy-term" /></label>
										<p class="premiuim-small-text">
											<liferay-ui:message key="duration-of-your-policy" />
										</p>
									</div>
									<div class="select-container ">
										<select class="form-control" id="<portlet:namespace />custPolicyTerm" name="<portlet:namespace />custPolicyTerm"
											data-selected-value="${productCustomizeSummaryData['productDefaultValueData'].policyTerm }">
											<option data-name="${productCustomizeSummaryData['productDefaultValueData'].policyTerm}" 
												value="${productCustomizeSummaryData['productDefaultValueData'].policyTerm}" selected >
												${productCustomizeSummaryData['productDefaultValueData'].policyTerm}
											</option>
										</select>
									</div>
								</div>
							</div>
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
										<select class="form-control" id="<portlet:namespace />payFor" name="<portlet:namespace />payFor"
											data-selected-value="${productCustomizeSummaryData['productDefaultValueData'].policyFor }">
										<c:forEach var="curPayFor" items="${payForPicklistData}">
											<option data-name="${curPayFor.key}" value="${curPayFor.key}" 
												${productCustomizeSummaryData['productDefaultValueData'].policyFor == curPayFor.key ? 'selected' : ''}>
												${curPayFor.value}
											</option>
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
										<label for="<portlet:namespace />paymentOption" class="pform-title mb-0"><liferay-ui:message key="payment-frequency" /></label>
										<p class="premiuim-small-text">
											<liferay-ui:message key="intervals-in-which-premiums-are-paid" />
										</p>
									</div>
									<div class="select-container ">
										<select class="form-control" id="<portlet:namespace />paymentOption" name="<portlet:namespace />paymentOption">
											<c:forEach var="curPaymentOption" items="${productCustomizeSummaryData['paymentOptionsData']}">
												<option data-name="${curPaymentOption.name}" value="${curPaymentOption.key}"
                                                    ${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>
                                                    ${curPaymentOption.name}
                                                </option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</c:if>
						<div class="item">
								<div class="labelInputGroup">
									<div class="labelGroup">
										<label for="<portlet:namespace />payoutOption" class="pform-title mb-0"><liferay-ui:message key="income-type" /></label>
										<p class="premiuim-small-text">
											<liferay-ui:message key="mode-in-which-benefit-is-received" />
										</p>
									</div>
									<div class="select-container ">
										<select class="form-control" id="<portlet:namespace />payoutOption" name="<portlet:namespace />payoutOption">
											<c:forEach var="curIncomeType" items="${incomeTypePicklistData}">
												<option data-name="${curIncomeType.key}" value="${curIncomeType.key}"  
													${productCustomizeSummaryData['productDefaultValueData'].payoutOption.key == curIncomeType.key ? 'selected' : ''}>
													${curIncomeType.value}
												</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="item d-none income-period-div">
							<div class="labelInputGroup">
								<div class="labelGroup">
									<label for="<portlet:namespace />incomePeriod" class="pform-title mb-0"><liferay-ui:message key="income-period" /></label>
									<p class="premiuim-small-text">
										<liferay-ui:message key="income-period" />
									</p>
								</div>
								<div class="select-container ">
									<select class="form-control" id="<portlet:namespace />incomePeriod" name="<portlet:namespace />incomePeriod">
										<option data-name="36" value="27" ${productCustomizeSummaryData['productDefaultValueData'].incomePeriod == 36 ? 'selected' : ''}>36</option>
										<option data-name="60" value="28" ${productCustomizeSummaryData['productDefaultValueData'].incomePeriod == 60 ? 'selected' : ''}>60</option>
									</select>
								</div>
							</div>
						</div>
						<div class="item d-none income-percentage-div">
							<div class="labelInputGroup">
								<div class="labelGroup">
									<label for="<portlet:namespace />incomePercentage" class="pform-title mb-0"><liferay-ui:message key="income-percentage" /></label>
									<p class="premiuim-small-text">
										<liferay-ui:message key="income-percentage" />
									</p>
								</div>
								<div class="select-container ">
									<select class="form-control" id="<portlet:namespace />incomePercentage" name="<portlet:namespace />incomePercentage">
									</select>
								</div>
							</div>
						</div>
						<div class="prem-bb item" id="<portlet:namespace />premiumBreakBenefitDiv">
							<div class="labelInputGroup">
								<div class="labelGroup">
									<label for="<portlet:namespace />premiumBreakBenefit" class="pform-title mb-0"><liferay-ui:message key="premium-break-benefit" /></label>
									<p class="premiuim-small-text">
										<liferay-ui:message key="premium-break-benefit" />
									</p>
								</div>
								<div class="select-container ">
									<select class="form-control" id="<portlet:namespace />premiumBreakBenefit" name="<portlet:namespace />premiumBreakBenefit">
										<option data-name="22" value="22">Yes</option>
										<option data-name="23" value="23" selected>No</option>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>

			<div class="edelweissTokioTotalReturns">
					<div class="download-brochure download-brochure1">
					<div class="accordion" id="edelweissTokioTotalReturnsMob">
						  <div class="card">
							<div class="card-header" id="headingOne">
							  <h2 class="mb-0">
								<button class="btn btn-link btn-block text-left pb-3" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
									<div class="pre-benefits_row base-plan d-flex px-3 pt-3" id="<portlet:namespace/>basePlanPriceMob" data-base-plan-price="" >
										<liferay-ui:message key="label-base-plan"/>
										<span id="<portlet:namespace/>basePlanPriceAmountMob"></span>
									</div>
									<a class="px-3 fs12">Premium Break-Up</a>
								</button>
							  </h2>
							</div>

							<div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#edelweissTokioTotalReturnsMob">
							  <div class="card-body">
							  	<div class="pre-benefits benefit-brkup_row d-flex p-3 flex-column" id="<portlet:namespace/>selectedRidersListMob">
									<strong><liferay-ui:message key="label-additional-riders" /></strong>
									<div id="<portlet:namespace/>selectedRidersListContainerMob">
										
									</div>
								</div>
							  </div>
							</div>
						  </div>
						  
						</div>
						<div class="d-none d-md-block" id="<portlet:namespace/>planBrochureContainer">
							<a href="${productCustomizeSummaryData['productBrochure'].link.href}"
							   id="<portlet:namespace/>planBrochure"
							   download="${productCustomizeSummaryData['productBrochure'].link.label}">
									
								<liferay-ui:message key="label-download-brochure" />
							</a>
						</div>
						<div class="premium-form-header">
	                    	<div class="title">
							<div><liferay-ui:message key="premium-amount" /></div>
						</div>
						</div>
						<div class="histy-amt-wrpr d-flex align-items-end">
							<div class="histy-amt-content">
								<div class="<portlet:namespace/>totalGuaranteedBenefits return-amt">
									<span id="<portlet:namespace/>totalGuaranteedBenefitAmount">
									</span>
								</div>
							</div>
						</div>
						<div class="break-proceed-group">
							<aui:button type="button" id="proceedBtn" cssClass="btn eligibility-proceed-btn edto-btn-primary float-right mb-2" value="label-proceed">
							</aui:button>
						</div>
						<!--<div class="break-proceed-group">
							<div>
								<aui:button id="benefitAmountBreakup" cssClass="btn btn-primary breakup_btn" value="label-amount-breakup" href="javascript:;"/>
							</div>
							<div class="discount-title">
								<div><liferay-ui:message key="discounted-premium" /></div>
								<aui:button id="moreDetails" cssClass="btn btn-primary breakup_btn" value="label-more-details" href="javascript:;"/>
							</div>
						</div>-->
					</div>
				</div>
			</div>
		</div>
		</div>
</form>
<script>
for (let i = 0; i < $('.owl-item.active').length; i++) {
    if($($('.owl-item.active')[i].children[0]).hasClass('d-none')){
        $($('.owl-item.active')[i]).addClass('d-none')
    }
}

$( document ).ready(function() {
    for (let i = 0; i < $('.owl-item').length; i++) {
        if($($('.owl-item')[i].children[0]).hasClass('d-none')){
            $($('.owl-item')[i]).addClass('d-none')
        }
    }
});

</script>
<style>

.break-proceed-group{justify-content: end;}
.break-proceed-group button, .break-proceed-group button span{cursor: pointer !important;color: #fff !important}
.edelweissTokioTotalReturns .download-brochure .premium-form-header .title{color: #3c3c3c !important;background: transparent !important;padding:0px !important}
.form-group {
	margin-bottom: 0rem !important;
}
.btn.breakup_btn {
    background: unset;
    border: 0;
    color: #317fff;
    font-weight: 500;
}
</style>