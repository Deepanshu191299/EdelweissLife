<%@ include file="init.jsp"%> 
<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="plan-details-label" />
	</div>
	<div class="edto-summary-tiles mt-0">
		<span class="icon" name="editPlanDetails" id="editPlanDetails">
			<a href="${customizepageURL }">
				<img alt="edit" src="${themeDisplay.getPathThemeImages()}/edit-pen.svg"> 
			</a>
		</span>
		<form>
			<div class="row">
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="product-name-label" />
						<span>${productName}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-base-premium" />
						<span class="productAmounts" data-amount="${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}">${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-income-option" />
						<span id="<portlet:namespace/>incomeOption" >${activeIncomePlanIncomeOption[summaryData.customerInvestmentDetailsRel[0].incomeOption]}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-payment-duration" />
						<span>${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}
							Years</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="payment-frequency-label" />
						<span>${paymentFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].paymentFrequency]}</span>
					</p>
				</div>
					<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="family-income-benefit-label" />
						<span>${activeIncomePlanFamilyBenefitIncome[summaryData.customerInvestmentDetailsRel[0].familyIncomeBenefits]}</span>
					</p>
				</div>
				
				<div class="col-md-4 col-12" id="<portlet:namespace/>maturityAge">
					<p>
						<liferay-ui:message key="maturity-age" />
					 <span>${activeIncomePlanMaturityAge[summaryData.customerInvestmentDetailsRel[0].maturityAge]} Years</span>
					</p>
				</div>
				<div class="col-md-4 col-12" id="<portlet:namespace/>guaranteedIncomeType">
					<p>
						 <liferay-ui:message key="guaranteed-income-type" />
						 <span>${maturityPayoutOptionPicklist[summaryData.customerInvestmentDetailsRel[0].guaranteedIncomeType]}</span>
					</p>
				</div>
				
			</div>

		</form>
	</div>
</div>