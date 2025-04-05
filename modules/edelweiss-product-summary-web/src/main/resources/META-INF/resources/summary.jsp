<%@ include file="init.jsp"%>

<portlet:actionURL name="/edit/basicDetailsURL" var="editBasicActionURL" />
<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>
<%@ include file="edit_summary.jsp"%>
<input type="hidden"
	value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}'
	name="drop" id="inputHidden">
<div class="edto-wealth-plus-buy-wrapper pb-0">
  <div class="container">
	<div class="container">
		<!-- Personal Details -->
		<%@ include file="summary-personal-details.jsp"%>
		<c:choose>
			<c:when test="${productName=='Guaranteed Savings Star'}">
				<!-- Plan Details: Guaranteed Savings STAR (Product Based Conditional)-->
				<%@ include file="gss-plan-details.jsp"%>

				<!-- Plan Return Details: Guaranteed Savings STAR (Product Based Conditional)-->
				<%@ include file="maturity.jsp"%>
			</c:when>
			<c:when test="${productName=='Premier Guaranteed Income' || productName=='Premier Guaranteed Star'}">
				<!-- Plan Details: Premier Guaranteed Income (Product Based Conditional)-->
				<%@ include file="pgi-plan-details.jsp"%>
				<%@ include file="maturity.jsp"%>
			</c:when>
			<c:when test="${productName=='Active Income'}">
				<!-- Plan Details: Active Income Plan (Product Based Conditional)-->
				<%@ include file="aip-plan-details.jsp"%>
				<%@ include file="returns.jsp"%>
				
			</c:when>
			<c:when test="${productName=='Flexi Savings Plan'}">
				<!-- Plan Details: Flexi Savings Plan (Product Based Conditional)-->
				<%@ include file="fsp-plan-details.jsp"%>
				<%@ include file="maturity.jsp"%>
				
			</c:when>
			<c:when test="${productName=='GCAP'}">
				<!-- Plan Details: Flexi Savings Plan (Product Based Conditional)-->
				<%@ include file="gcap-plan-details.jsp"%>
				<%@ include file="maturity.jsp"%>
				
			</c:when>
			<c:when test="${productName=='Guaranteed Income STAR'}">
				<!-- Plan Details: Flexi Savings Plan (Product Based Conditional)-->
				<%@ include file="gis-plan-details.jsp"%>
				<%@ include file="maturity.jsp"%>
				
			</c:when>
			<c:when test="${productName=='Income Builder'}">
				<!-- Plan Details: Flexi Savings Plan (Product Based Conditional)-->
				<%@ include file="ib-plan-details.jsp"%>
				<%@ include file="maturity.jsp"%>
				
			</c:when>
			<c:when test="${productName=='Zindagi Protect Plus'}">
				<!-- Plan Details: Zindagi Protect(Product Based Conditional)-->
				<%@ include file="zp-details.jsp"%>
				<%@ include file="maturity.jsp"%>
				<%@ include file="edit_basic_details_modal.jsp"%>
			</c:when>
			<c:otherwise>
				<!-- Plan Details: WRP, WP & WSP (Product Based Conditional)-->
				<%@ include file="wrp-wp-wsp-plan-details.jsp"%>

				<!-- Plan Return Details: WRP, WP & WSP (Product Based Conditional)-->
				<%@ include file="returns.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
</div>

<%@ include file="plan-premium-amount-breakup.jsp"%>

<script type="text/javascript">
	var namespace = "<portlet:namespace/>";

	var basicInvestingFor = '${summaryData.customerInvestmentDetailsRel[0].investingFor}';
	var assuranceFullName = '${summaryData.customerFamilyDetailsRel[0].assuranceFullName}';
	var assuranceDob = '${summaryData.customerFamilyDetailsRel[0].assuranceDob}';
	var assuredRelationValue = '${summaryData.customerFamilyDetailsRel[0].assuredRelation}';
	var productName = '${productName}';
	var productCode = '${productCode}';
	var investmentAmount = '${summaryData.customerInvestmentDetailsRel[0].investmentAmount}';
	var selectedRidersList = JSON.parse('${selectedRidersList}');
	var paymentFrequency = "${paymentFrequencyPicklist[summaryData.customerInvestmentDetailsRel[0].paymentFrequency]}";
	var policyTerm = "${summaryData.customerInvestmentDetailsRel[0].policyTerm}";
	var policyOption = "${summaryData.customerInvestmentDetailsRel[0].policyOption}";
	var payingTerm = "${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}";
	var payoutOption = "${summaryData.customerInvestmentDetailsRel[0].payoutOption}";
	var basePremiumAmount = "${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}";
	var premiumAmount = "${summaryData.customerInvestmentDetailsRel[0].premiumAmount}";
	if(paymentFrequency == 'Monthly' && (productCode === fspProductCode.toString() || productCode === gisProductCode.toString() || productCode === '40048' || productCode === pgsProductCode.toString() || productCode === '40007' || productCode === '40035' || productCode === ibProductCode.toString())){
		premiumAmount = premiumAmount * 2;
	}

	var LANG_MESSAGES = {};
	LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
</script>
<c:if test="${productName=='Guaranteed Savings Star' || productName=='Active Income' || productName=='Flexi Savings Plan' || productName=='Guaranteed Income STAR' || productName=='Premier Guaranteed Income' || productName=='Premier Guaranteed Star' || productName=='GCAP' || productName=='Income Builder' || productName=='Zindagi Protect Plus'}">
	<script src="<%=request.getContextPath()%>/js/gss-main.js?t=<%=new Date().getTime()%>" type="text/javascript"></script>
</c:if>