<%@ include file="init.jsp"%>
<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="plan-details-label" />
	</div>
	<div class="edto-summary-tiles">
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
						<span data-amount="${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}" class="productAmounts">${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-pay-for" />
						<span>${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}
							Years</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-policy-term" />
						<span>${summaryData.customerInvestmentDetailsRel[0].policyTerm}
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
						<liferay-ui:message key="premium-amount-label" />
						<span data-amount="${summaryData.customerInvestmentDetailsRel[0].investmentAmount}" class="productAmounts">${summaryData.customerInvestmentDetailsRel[0].investmentAmount}</span>
					</p>
				</div>
			</div>

		</form>
	</div>
</div>