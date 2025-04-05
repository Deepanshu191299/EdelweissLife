<%@ include file="/init.jsp"%>

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
						<c:if test="${not empty customizePageProductJson.planOption}">
							<c:forEach var="option" items="${customizePageProductJson.planOption}">
								<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].policyOption}">
									<span id="<portlet:namespace/>policyOption">${option.label}</span>
								</c:if>
							</c:forEach>
						</c:if>
					</p>
				</div>
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="label-income-payout-type" />
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.incomePayoutType}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.incomePayoutType}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].incomePayoutType}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2' and summaryData.customerInvestmentDetailsRel[0].incomePayoutType eq '10'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="label-payout-increasing-percentage" />
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.payoutIncreasingPercentage}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.payoutIncreasingPercentage}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].payoutIncreasingPercentage}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="payment-frequency-label" />
						<span>${paymentFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].paymentFrequency]}</span>
					</p>
				</div>
				<%-- Lumpsum --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '1'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="sad-multiple" />
							<c:if test="${not empty customizePageProductJson.lumpsum.sumAssuredMultiple}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsum.sumAssuredMultiple.sumAssuredMultipleDefaultList}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].sumAssuredMultiple}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="sad-multiple" />
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.sumAssuredMultiple}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.sumAssuredMultiple.sumAssuredMultipleDefaultList}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].sumAssuredMultiple}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="income-duration" />
							
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.incomeDuration}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.incomeDuration}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].incomeDuration}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="income-payout-time" />
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.incomePayoutTime}">
								
							    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.incomePayoutTime}">
							    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].incomePayoutTime}">
							    		<span>${option.label}</span>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="income-start-point" />
							<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.incomeStartPoint}">
							    <c:forEach var="item" items="${customizePageProductJson.lumpsumPlusIncome.incomeStartPoint}">
							    	<c:if test="${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm eq item.ifPpt}">
							    		<c:forEach var="option" items="${item.ISPList}">
								    		<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].incomeStartPoint}">
									    		<span>${option.label}</span>
									    	</c:if>
							    		</c:forEach>
							    	</c:if>
						        </c:forEach>
							</c:if>
						</p>
					</div>
				</c:if>
				
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="label-pay-for" />
						<span>${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}
							Years</span>
					</p>
				</div>
				
				<%-- Lumpsum --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '1'}">
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="label-policy-term" />
							<span>${summaryData.customerInvestmentDetailsRel[0].policyTerm}
								Years</span>
						</p>
					</div>
				</c:if>
				
				<%-- Lumpsum + Income --%>
				<c:if test="${summaryData.customerInvestmentDetailsRel[0].policyOption eq '2'}">
				
					<c:set var="incomeDurationCalVal" value="0" />
			        <c:set var="payForCalVal" value="0" />
			        <c:set var="addByOne" value="1" />
			        
					<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.incomeDuration}">	
					    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.incomeDuration}">
					    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].incomeDuration}">
					    		<c:set var="incomeDurationCalVal" value="${option.label}" />
					    	</c:if>
				        </c:forEach>
					</c:if>
					
					<c:if test="${not empty summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}">	
					    <c:set var="payForCalVal" value="${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}" />
					</c:if>
					
			        <%-- parseInt(incomeDurationValue) + parseInt(payFor) + parseInt(1) --%>
			        <c:set var="sumOfPT" value="${incomeDurationCalVal + payForCalVal + addByOne}" />
			        
					<div class="col-md-4 col-12">
						<p>
							<liferay-ui:message key="label-policy-term" />
							<span>${sumOfPT} Years</span>
						</p>
					</div>
				</c:if>
				
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="employee-benifit" />
						<c:if test="${not empty customizePageProductJson.lumpsumPlusIncome.isEdelWeissEmployee}">
							
						    <c:forEach var="option" items="${customizePageProductJson.lumpsumPlusIncome.isEdelWeissEmployee}">
						    	<c:if test="${option.value eq summaryData.customerInvestmentDetailsRel[0].edelweissEmployee}">
						    		<span>${option.label}</span>
						    	</c:if>
					        </c:forEach>
						</c:if>
					</p>
				</div>
				<div class="col-md-4 col-12">
					<p>
						<liferay-ui:message key="inesting-for-label" />
						<span>${summaryData.customerInvestmentDetailsRel[0].investingFor}</span>
						<%-- <span>${investingForPicklist[summaryData.customerInvestmentDetailsRel[0].investingFor]}</span> --%>
					</p>
				</div>
			</div>
		</form>
	</div>
</div>

<input type="hidden"
	value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}'
	name="drop" id="inputHidden">
<div class="return-single-strip">
	<div class="row">
		<div class="col-md-5 col-12">
			<div class="sum-text">
				<h5>
					<liferay-ui:message key="label-maturity-amount" />
                    <span class="currency">${summaryData.customerInvestmentDetailsRel[0].totalReturns }</span>
				</h5>
			</div>
		</div>

		<div class="col-md-7 col-12">
			<div class="right-sum">
				<p>
					<liferay-ui:message key="download-benefit-label" />
				</p>
				<div class="pdf">
					<a href="${illustrationURL}" target="_blank" download><img
						src="/o/edelweisstokio-theme/images/pdf.svg" alt="icon"></a>
				</div>
			</div>
		</div>
	</div>
</div>