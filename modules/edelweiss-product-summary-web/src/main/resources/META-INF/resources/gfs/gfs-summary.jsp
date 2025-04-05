<%@ include file="/init.jsp"%>

<portlet:actionURL name="/edit/basicDetailsURL" var="editBasicActionURL" />
<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>
<%@ include file="/gfs/gfs-edit_summary.jsp"%>
<input type="hidden"
	value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}'
	name="drop" id="inputHidden">
<div class="edto-wealth-plus-buy-wrapper pb-0">
	<div class="container">
		<div class="container">
			<!-- Personal Details -->
			<%@ include file="/gfs/gfs-summary-personal-details.jsp"%>
			<%@ include file="/gfs/gfs-plan-details.jsp"%>
		</div>
	</div>

	<%@ include file="/gfs/gfs-plan-premium-amount-breakup.jsp"%>

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
</c:if>
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
	if(paymentFrequency == 'Monthly'){
		premiumAmount = premiumAmount * 2;
	}
	
	if(productCode == "40071" && policyOption == "2"){
		policyTerm = "${sumOfPT}";
	}

	var LANG_MESSAGES = {};
	LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
</script>
<script src="<%=request.getContextPath()%>/gfs/js/gfs-main.js?t=<%=new Date().getTime()%>" type="text/javascript"></script>

<script>
    // Function to format a number to INR currency
    function formatToINR(value) {
        return new Intl.NumberFormat('en-IN', {
            style: 'currency',
            currency: 'INR',
            minimumFractionDigits: 0
        }).format(value);
    }

    // Get all elements with the class 'currency'
    const currencyElements = document.querySelectorAll('.currency');

    
    // Format and update each element
    currencyElements.forEach(element => {
        const value = parseFloat(element.textContent); // Parse the number from the text
        if (!isNaN(value)) { // Check if the content is a valid number
            element.textContent = formatToINR(value); // Update with formatted value
        }
    });
</script>