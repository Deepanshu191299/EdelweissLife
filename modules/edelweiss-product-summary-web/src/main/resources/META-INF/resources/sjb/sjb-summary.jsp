<%@page import="java.util.Date"%>
<%@ include file="/init.jsp"%>
<portlet:actionURL name="/edit/basicDetailsURL" var="editBasicActionURL" />

<input type="hidden" value='${summaryData.customerInvestmentDetailsRel[0].projectedReturnsData}' name="drop" id="inputHidden">

<c:set var="paymentFrequancy" value="${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm != '1'?summaryData.customerInvestmentDetailsRel[0].paymentFrequency:'5' }" scope="request" />
<div class="edto-wealth-plus-buy-wrapper pb-0">
  <div class="container">
	<div class="container">
		<!-- Personal Details -->
		<%@ include file="/sjb/sjb-personal-details.jsp"%>
		<%@ include file="/sjb/sjb-plan-details.jsp"%>
		<%@ include file="/sjb/sjb-maturity.jsp"%>
	</div>
</div>

<%@ include file="/plan-premium-amount-breakup.jsp"%>

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
	
	var paymentFrequency = "${paymentFrequencyPicklist[paymentFrequancy]}";
	var policyTerm = "${summaryData.customerInvestmentDetailsRel[0].policyTerm}";
	var payingTerm = "${summaryData.customerInvestmentDetailsRel[0].premiumPayingTerm}";
	var payoutOption = "${summaryData.customerInvestmentDetailsRel[0].payoutOption}";
	var basePremiumAmount = "${summaryData.customerInvestmentDetailsRel[0].basePremiumAmount}";
	var premiumAmount = "${summaryData.customerInvestmentDetailsRel[0].premiumAmount}";
	if(paymentFrequency == 'Monthly'){
		premiumAmount = premiumAmount * 2;
	}
	var gst ="${summaryData.customerInvestmentDetailsRel[0].gst}";

	var LANG_MESSAGES = {};
	LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
	
	/*
	 * Sort Education droupdown options
	 */
	var educationQualificationValue = $("#"+portletNamespace+"educationQualification").val();
	var educationQualificationDropdown = $("#"+portletNamespace+"educationQualification");
	var dropdownOptions = educationQualificationDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  var aValue = parseInt($(a).val());
	  var bValue = parseInt($(b).val());
	  return bValue - aValue;
	});
	educationQualificationDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"educationQualification").val(educationQualificationValue);
	

	/*
	 * Sort Nature of Duty Dropdown
	 */
	var natureOfDutyValue = $("#"+portletNamespace+"natureOfDuty").val();
	var natureOfDutyDropdown = $("#"+portletNamespace+"natureOfDuty");
	var dropdownOptions = natureOfDutyDropdown.find("option");
	dropdownOptions.detach().sort(function(a, b) {
	  return a.textContent.localeCompare(b.textContent)
	});
	natureOfDutyDropdown.append(dropdownOptions);
	$("#"+portletNamespace+"natureOfDuty").val(natureOfDutyValue);
</script>
<script src="<%=request.getContextPath()%>/js/gss-main.js?t=<%=new Date().getTime()%>" type="text/javascript"></script>