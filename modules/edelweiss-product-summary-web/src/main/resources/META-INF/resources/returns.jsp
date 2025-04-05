<%@ include file="init.jsp"%>
<div class="return-single-strip">
	<div class="row">
		<div class="col-md-5 col-12">
			<div class="sum-text">
				<h5>
					<liferay-ui:message key="returns" /> <span id="valueOfRates"></span>
				</h5>
			</div>
		</div>
		<div class="col-md-2 col-12">
			<div class="select-box">
				<select aria-label="Default select example" id="rates">
					<option
						<c:if test="${summaryData.customerInvestmentDetailsRel[0].rate == '4'}">selected </c:if>
						value="4"><liferay-ui:message key="four-percentage" /></option>
					<option
						<c:if test="${summaryData.customerInvestmentDetailsRel[0].rate == '8'}">selected </c:if>
						value="8"><liferay-ui:message key="eight-percentage" /></option>
				</select>
			</div>
		</div>
		<div class="col-md-5 col-12">
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