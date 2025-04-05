<%@ include file="/init.jsp"%>

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
					<tbody>
						<c:set var="grandTotal" value="0" />
						<c:set var="basePremiumAmount" value="${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}" />

						<c:if test="${not empty biInputValidationsList}">
							<c:forEach var="validation" items="${biInputValidationsList}" begin="0" end="0">
							    <c:if test="${productCode eq validation.ProductId}">
								    <tr>
								        <td class="table-heading">Base Premium</td>
								        <td>${paymentFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].paymentFrequency]}</td>
								        <td>${validation.PT}</td>
								        <td>${validation.PPT}</td>
								        <td class="text-right currency">
								        	<c:choose>
												<c:when test="${summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4}">
													<c:set var="basePremiumAmount" value="${basePremiumAmount * 2}" />
													<p>${basePremiumAmount}</p>
												</c:when>
												<c:otherwise>
													<p>${basePremiumAmount}</p>
												</c:otherwise>
											</c:choose>
								        </td>
								    </tr>
							    </c:if>
							</c:forEach>
						</c:if>
						
					    <c:if test="${not empty ridersList}">
							<c:forEach var="rider" items="${ridersList}">
								<c:if test="${not empty biInputValidationsList}">
									<c:forEach var="validation" items="${biInputValidationsList}">
									    <c:if test="${rider.riderId eq validation.ProductId}">
									    									    	
									    	<c:set var="totalAmount" value="${validation.ModalPremium + validation.Tax}" />

											<tr>
										        <td class="table-heading">${rider.title}</td>
										        <td>${paymentFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].paymentFrequency]}</td>
										        <td>${validation.PT}</td>
										        <td>${validation.PPT}</td>
										        <td class="text-right currency">
										        	<c:choose>
														<c:when test="${summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4}">
															<c:set var="totalAmount" value="${totalAmount * 2}" />
															<p>${totalAmount}</p>
														</c:when>
														<c:otherwise>
															<p>${totalAmount}</p>
														</c:otherwise>
													</c:choose>
										        </td>
										    </tr>
											
											<!-- Add to grand total -->
											<c:set var="grandTotal" value="${grandTotal + totalAmount}" />
									    </c:if>
									</c:forEach>
								</c:if>
							</c:forEach>
						</c:if>
					</tbody>
					
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td class="table-heading" style="text-align: center;"><liferay-ui:message key="label-total-premium-amount"/></td>
							<td class="rightaling table-heading" style="text-align: end;">
								<span class="currency">${grandTotal + basePremiumAmount}</span>
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