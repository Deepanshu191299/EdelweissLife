<%@ include file="init.jsp"%>
<input type="hidden"
	value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}'
	name="drop" id="inputHidden">
<div class="return-single-strip">
	<div class="row">
		<div class="col-md-5 col-12">
			<div class="sum-text">
				<h5>
					<c:choose>
					    <c:when test="${productName=='Zindagi Protect Plus'}">
					        <liferay-ui:message key="life-cover-amount" />
                            <span id="<portlet:namespace/>totalMaturityAmount" data-amount="${summaryData.customerInvestmentDetailsRel[0].investmentAmount }" class="productAmounts">
                            <liferay-ui:message key="rupee-sign" />${summaryData.customerInvestmentDetailsRel[0].investmentAmount }
                            </span>
					    </c:when>
					    <c:otherwise>
                            <liferay-ui:message key="label-maturity-amount" />
                            <span id="<portlet:namespace/>totalMaturityAmount" data-amount="${summaryData.customerInvestmentDetailsRel[0].totalReturns }" class="productAmounts">${summaryData.customerInvestmentDetailsRel[0].totalReturns }</span>
                        </c:otherwise>
					</c:choose>
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