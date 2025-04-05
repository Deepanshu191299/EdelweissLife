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
						<span>${productName}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="base-plan-label" />
						<span>
							<liferay-ui:message key="rupee-sign" />${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount }
						</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="policy-duration-label" />
						<span>${summaryData.customerInvestmentDetailsRel[0].policyTerm}&nbsp;Years</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="payment-duration-label" />
						<span id="<portlet:namespace/>planPremiumAmount">${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}&nbsp;Years</span>
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
						<liferay-ui:message key="payout-label" />
						<c:choose>
							 <c:when test="${summaryData.customerInvestmentDetailsRel[0].payoutOption eq 24}">
							 		<span>Lumpsum</span>
							 </c:when>
							 <c:when test="${summaryData.customerInvestmentDetailsRel[0].payoutOption eq 25}">
							 		<span>Monthly Income</span>
							 </c:when>
							 <c:when test="${summaryData.customerInvestmentDetailsRel[0].payoutOption eq 26}">
							 		<span>Lumpsum Plus Monthly Income</span>
							 </c:when>
						</c:choose>			
					</p>
				</div>
			</div>
		</form>
	</div>
</div>