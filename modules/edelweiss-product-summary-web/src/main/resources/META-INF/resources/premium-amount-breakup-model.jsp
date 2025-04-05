<%@ include file="init.jsp"%>
<div class="modal summaryModal " tabindex="-1" role="dialog"
	id="<portlet:namespace/>planPremiumAmountBreakupTableModel" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container" role="document">
	<div class="popup-container">
		<div class="modal-content summary-popup-wrapper">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body summary-table">
				<table class="s-table" width="100%" id="<portlet:namespace/>planPremiumAmountBreakupTable">
				<thead>
					<tr>
						<th><liferay-ui:message key="label-particulars"/></th>
						<th><liferay-ui:message key="label-payment-mode"/></th>
						<th><liferay-ui:message key="label-policy-term"/></th>
						<th><liferay-ui:message key="label-paying-term"/></th>
						<th class="rightaling"><liferay-ui:message key="label-total-amount"/></th>
					</tr>
					</thead>
					<tbody id="<portlet:namespace/>planPremiumAmountBreakupTableBody">

					</tbody>
					<tbody id="<portlet:namespace/>totalPremiumAmountContainer">
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td class="table-heading" style="text-align: center;"><liferay-ui:message key="label-total-premium-amount"/></td>
							<td class="rightaling table-heading" style="text-align: end;">
								
								<c:choose>
									<c:when test="${(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == fspProductCode) ||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == gisProductCode)
														||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40048') ||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == pgsProductCode) ||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40007') 
														||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40035')
														||(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == ibProductCode)
														|| (productCode == '40039' && summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm != '1' && summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4)}">
														<span id="<portlet:namespace/>totalPremiumAmountAmount" class="productAmounts" 
														data-amount='${summaryData.customerInvestmentDetailsRel[0].premiumAmount!=""?summaryData.customerInvestmentDetailsRel[0].premiumAmount*2:0}'>
															${summaryData.customerInvestmentDetailsRel[0].premiumAmount!=""?summaryData.customerInvestmentDetailsRel[0].premiumAmount*2:0}
													</span>
									</c:when>
									<c:otherwise>
										<span id="<portlet:namespace/>totalPremiumAmountAmount" class="productAmounts" data-amount="${summaryData.customerInvestmentDetailsRel[0].premiumAmount}">${summaryData.customerInvestmentDetailsRel[0].premiumAmount}</span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
				<%-- <div id="<portlet:namespace/>totalPremiumAmountAmountContainer">
					<span></span>
					<liferay-ui:message key="rupee-sign" />
					<span id="<portlet:namespace/>totalPremiumAmountAmount">${summaryData.customerInvestmentDetailsRel[0].premiumAmount}</span>
				</div> --%>
			</div>
			<div class="modal-footer summary-disc">
			<span class="disclaimer"><liferay-ui:message key="premium-amount-is-inclusive-of-all-taxes-including-GST" /></span>
			</div>
		</div>
	</div>
	</div>
</div>