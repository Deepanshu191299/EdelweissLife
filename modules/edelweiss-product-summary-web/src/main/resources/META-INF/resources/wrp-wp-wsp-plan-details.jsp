<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="plan-details-label" />
	</div>
	<div class="edto-summary-tiles">
		<span class="icon" name="editPlanDetails" id="editPlanDetails">
			<img alt="edit" src="/o/edelweisstokio-theme/images/edit-pen.svg">
		</span>
		<form>
			<div class="row">
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="product-name-label" />
						<span>${productMasterData.getString("productDisplayName").replaceAll("Tokio ","")}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="policy-term-label" />
						<span>${summaryData.customerInvestmentDetailsRel[0].policyTerm}
							Years</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="payment-duration-label" />
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
						<liferay-ui:message key="premium-amount-label" />
						<span id="<portlet:namespace/>planPremiumAmount"><liferay-ui:message key="rupee-sign" /> </span>
					</p>
				</div>
				<c:choose>
					<c:when test="${productName == 'Wealth Rise Plus' || productName == 'Wealth Secure Plus'}">
						<div class="col-md-4 col-12">
							<p>
								<liferay-ui:message key="system-withdrawal-plan-label" />
								<span>${swpMasterPicklist[summaryData.customerInvestmentDetailsRel[0].systematicWithdrawalPlan]}</span>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
				<c:if
					test="${productName == 'Wealth Secure Plus' && swpMasterPicklist[summaryData.customerInvestmentDetailsRel[0].systematicWithdrawalPlan] == 'Yes'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="withdrawal-percentage" />
							<span>${fundWithdrawalPercentPicklistData[summaryData.customerInvestmentDetailsRel[0].fundValuetobeWithdrawn]}</span>
						</p>
					</div>
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="withdrawal-year" />
							<span>${swpWithdrawalYearPicklistData[summaryData.customerInvestmentDetailsRel[0].policyYearFromWhichSWPPayable]}</span>
						</p>
					</div>
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="fund-withdrawal" />
							<span>${fundWithdrawalFrequencyPicklistData[summaryData.customerInvestmentDetailsRel[0].swpFrequency]}</span>
						</p>
					</div>
				</c:if>
				<c:choose>
					<c:when test="${productName eq 'Wealth Plus' or productCode eq '40030'}">
						<div class="col-md-4 col-12" style="display: none;">
							<p>
								<liferay-ui:message key="variant-label" />
								<span>${masterPolicyOptionPicklist[summaryData.customerInvestmentDetailsRel[0].policyOption]}</span>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-md-4 col-12">
							<p>
								<liferay-ui:message key="variant-label" />
								<span>${masterPolicyOptionPicklist[summaryData.customerInvestmentDetailsRel[0].policyOption]}</span>
							</p>
						</div>
					</c:otherwise>
				</c:choose>


				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="fund-management-label" />
						<span>${fundManagementPicklist[summaryData.customerInvestmentDetailsRel[0].investmentStrategy]}</span>
					</p>
				</div>
			</div>

		</form>
	</div>
</div>