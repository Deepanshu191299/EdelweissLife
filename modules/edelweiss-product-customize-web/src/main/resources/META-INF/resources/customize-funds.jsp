<div class="customize-fund-wrapper mb-2">
	<div class="table-allocation-wrapper">
	<div class="table-responsive">
<table class="table fixed-table" id="customizeFundTable" style="display:none;">
	<thead>
		<tr>
			<th scope="col"><liferay-ui:message key="fund-name" /></th>
			<th scope="col"><liferay-ui:message key="fund-inception-date" /></th>
			<th scope="col"><liferay-ui:message key="allocation-percentage" /></th>
			<th scope="col"><liferay-ui:message key="projected-returns" /><sup>1</sup></th>
			<th scope="col"><liferay-ui:message key="past-returns" /><sup>2</sup></th>
			<th scope="col"><liferay-ui:message key="benchmark-returns" /></th>
		</tr>
		<tr>
            <th></th>
            <th></th>
            <th></th>
            <th>
            <div class="labelInputGroup">
				<div class="select-container ">
            	<select class="form-control" name="<portlet:namespace />projectedReturnFundAllocation" id="<portlet:namespace />projectedReturnFundAllocation">
					<option value="4" <c:if test="${summaryData.customerInvestmentDetailsRel[0].rate == '4'}">selected </c:if>>
						<liferay-ui:message key="four-percent" />
					</option>
					<option value="8" data-check="${summaryData.customerInvestmentDetailsRel[0].rate }" <c:if test="${summaryData.customerInvestmentDetailsRel[0].rate == '8' || summaryData.customerInvestmentDetailsRel[0].rate == '' || summaryData.customerInvestmentDetailsRel[0].rate == null}">selected </c:if>>
						<liferay-ui:message key="eight-percent" />
					</option>
				</select>
				</div>
				</div>
            </th>
            <th colspan="2" class="table-dark-bg">
				<div class="labelInputGroup">
					<div class="select-container">
					<select class="form-control" id="<portlet:namespace />benchMarkAndReturnSelect" name="<portlet:namespace />benchMarkAndReturnSelect" aria-label="Select Year">
						<option value="Since Inception"><liferay-ui:message key="since-inception" /></option>
						<option value="1"><liferay-ui:message key="one-year" /></option>
						<option value="2"><liferay-ui:message key="two-year" /></option>
						<option value="3"><liferay-ui:message key="three-year" /></option>
						<option value="4"><liferay-ui:message key="four-year" /></option>
						<option value="5"><liferay-ui:message key="five-year" /></option>
						<option value="7"><liferay-ui:message key="seven-year" /></option>
					</select>
				</div>
				</div>
            </th>
        </tr>
	</thead>
	<tbody class="equity-section-tbody">
	</tbody>
</table>

</div>
</div>
</div>
<style>
.pointer.customize-btn{
   display:inline-block !important; 
}
.fixed-table tbody tr td{
   position: relative;
}
.fixed-table tbody tr td small {
    width: 100%;
    text-align: left;
    padding: 0;
    display: block;
    font-size: 12px;
    color: #999;
    line-height: 20px;
}
.fixed-table td .allocation{
	width: 130px;
    border: 1px solid #ec703a;
    text-align: center;
    position: relative;
    padding: 8px;
    display: flex;
    justify-content: center;
    align-items: center;
    align-self: center;
    justify-content: space-between;
}
.fixed-table td .decrease{
	border: 1px solid #ec703a;
    width: 21px;
    height: 21px;
    border-radius: 50%;
    display: block;
    line-height: 20px;
    color: #ec703a;
    text-align: center;
    font-size: 24px;
    line-height: 18px;
}
.fixed-table td .increase{
	border: 1px solid #ec703a;
    width: 21px;
    height: 21px;
    border-radius: 50%;
    display: block;
    color: #ec703a;
    text-align: center;
    font-size: 18px;
    line-height: 21px;
}
</style>