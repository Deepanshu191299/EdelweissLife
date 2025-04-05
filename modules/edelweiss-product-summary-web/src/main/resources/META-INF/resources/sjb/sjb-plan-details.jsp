<%@ include file="/init.jsp"%>
<div class="summary-main-wrapper">
	<div class="summary-title">
		<liferay-ui:message key="plan-details-label" />
	</div>
	<div class="edto-summary-tiles">
		<span class="icon" name="editPlanDetails" id="editPlanDetails">
			<a href="${customizepageURL }"> <img alt="edit"
				src="${themeDisplay.getPathThemeImages()}/edit-pen.svg">
		</a>

		</span>
		<form>
			<div class="row">
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="plan-Name" />
						<span>${productName}</span>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="premium" />
						<c:choose>
							<c:when test="${paymentFrequancy == 4}">
								<span id="<portlet:namespace/>totalPremiumAmountAmount"
									class="productAmounts"
									data-amount='${summaryData.customerInvestmentDetailsRel[0].premiumAmount!=""?summaryData.customerInvestmentDetailsRel[0].premiumAmount*2:0}'>
									${summaryData.customerInvestmentDetailsRel[0].premiumAmount!=""?summaryData.customerInvestmentDetailsRel[0].premiumAmount*2:0}
								</span>
							</c:when>
							<c:otherwise>
								<span id="<portlet:namespace/>totalPremiumAmountAmount"
									class="productAmounts"
									data-amount="${summaryData.customerInvestmentDetailsRel[0].premiumAmount}">${summaryData.customerInvestmentDetailsRel[0].premiumAmount}</span>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="policy-duration" />
						<span>${summaryData.customerInvestmentDetailsRel[0].policyTerm}
							Years</span>
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
						<span>${paymentFrequencyPicklist[paymentFrequancy]}</span>
					</p>
				</div>
			</div>

		</form>
	</div>
</div>