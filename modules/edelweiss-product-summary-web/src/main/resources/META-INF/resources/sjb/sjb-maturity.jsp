<%@ include file="/init.jsp"%>
<input type="hidden"
	value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}'
	name="drop" id="inputHidden">
<div class="return-single-strip">
	<div class="row">
		<div class="col-md-5 col-12">
			<div class="sum-text">
				<h5>
					<liferay-ui:message key="life-cover" />
					<span id="<portlet:namespace/>totalMaturityAmount" data-amount="${summaryData.customerInvestmentDetailsRel[0].lifeCover}" class="productAmounts">${summaryData.customerInvestmentDetailsRel[0].lifeCover}</span>
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