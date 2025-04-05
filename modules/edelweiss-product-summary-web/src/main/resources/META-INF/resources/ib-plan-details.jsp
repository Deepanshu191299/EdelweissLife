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
						<liferay-ui:message key="label-plan-option" />
						<%-- <span>${summaryData.customerInvestmentDetailsRel[0].policyOption}</span> --%>
						<span id="<portlet:namespace/>policyOption" >${masterPolicyOptionPicklist[summaryData.customerInvestmentDetailsRel[0].policyOption]}</span>
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
						<liferay-ui:message key="payout-option-label" />
						<span>${ibPayoutOptionPicklistData[summaryData.customerInvestmentDetailsRel[0].payoutOption]}</span>
					</p>
				</div>
				
				<div class="col-md-4 col-12" id="<portlet:namespace/>incomePayoutFrequencyDiv">
					<p>
						<liferay-ui:message key="payout-frequency-label" />
						<span>${incomePayoutFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].incomePayoutFrequency]}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="payout-period-label" />
						<span>${ibPayoutPeriodPicklistData[summaryData.customerInvestmentDetailsRel[0].payoutPeriod]}</span>
					</p>
				</div>
				
				
				<%-- <div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="premium-amount-label" />
						<span>${summaryData.customerInvestmentDetailsRel[0].investmentAmount}</span>
					</p>
				</div> --%>
			</div>

		</form>
	</div>
</div>