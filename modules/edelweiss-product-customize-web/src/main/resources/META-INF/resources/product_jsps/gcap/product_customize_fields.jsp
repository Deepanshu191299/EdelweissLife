<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@ include file="/init.jsp"%>
<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>
<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>
<portlet:resourceURL var="saveFamilyDetailsURL" id="/save/familyDetails"></portlet:resourceURL>
<script>
	var saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}";
	var saveFamilyDetailsURL = "${saveFamilyDetailsURL}";
	var saveInvestmentDetails = "${saveInvestmentDetails}";
	var selectedRidersIds = {};
	try{
		selectedRidersIds = JSON.parse('${productCustomizeSummaryData["productDefaultValueData"].selectedRidersIds}');
	}catch(e){
		console.warn("Unable to Parse selectedRidersIds JSON");
	}
</script>
	<style>
	.form-group {
	    margin-bottom: 0rem !important;
	}
.btn.breakup_btn {
    background: unset;
    border: 0;
    color: #317fff;
    font-weight: 500;
}
</style>
<form name="<portlet:namespace />customizeForm" id="<portlet:namespace />customizeForm" method="post" action="${saveInvestmentDetails}">
			
	<aui:input name="<%=ParameterConstants.SELECTED_RIDERS_IDS%>" type="hidden" value="${productCustomizeSummaryData['productDefaultValueData'].getSelectedRidersIds()}"/>
	<aui:input name="<%=ParameterConstants.TOTAL_MATURITY_AMOUNT%>" type="hidden" value=""/>
	<aui:input name="<%=ParameterConstants.QUOTATION_ID%>" type="hidden" value=""/>
	<aui:input name="<%=ParameterConstants.ILLUSTRATION_URL%>" type="hidden" value=""/>
	<aui:input name="<%=ParameterConstants.BASE_PREMIUM_AMOUNT%>" type="hidden" value=""/>
	<aui:input name="<%=ParameterConstants.PREMIUM_AMOUNT%>" type="hidden" value=""/>
	<aui:input name="<%=ParameterConstants.INVESTMENT_AMOUNT_WITH_TAX%>" type="hidden" value=""/>
	
	<aui:input name="<%=ParameterConstants.PRODUCT_CODE%>" type="hidden" value="${productMetaData['productCode']}"/>
	<aui:input name="<%=ParameterConstants.PRODUCT_NAME%>" type="hidden" value="${productMetaData['productName']}"/>
	<aui:input name="<%=ParameterConstants.CUSTOMER_ENQUIRY_ID%>" type="hidden" value="${customerEnquiryId }"/>
	<aui:input name="<%=ParameterConstants.CUSTOMER_INVESTMENT_DETAILS_ID%>" type="hidden" value="${customerInvestmentDetailsId }"/>
	<aui:input name="<%=ParameterConstants.CUSTOMER_POLICY_DETAILS_ID%>" type="hidden" value="${customerPolicyDetailsId }"/>
	<aui:input name="<%=ParameterConstants.CUSTOMER_FAMILY_DETAILS_ID%>" type="hidden" value="${customerFamilyDetailsId }"/>
	<aui:input name="<%=ParameterConstants.IS_FORM_SUBMIT_PENDING%>" type="hidden" value="false"/>
<div class="edelweissForm">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div id="edelweissTokioForm1" class="edelweissTokioForm">
	<div class="row owl-carousel owl-theme">
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestmentAmountApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.INVESTMENT_AMOUNT %>"><liferay-ui:message key="label-investment-amount" /></label><br/> 
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-amount-you-will-invest" />
						</p>
					</div>
					<div class="pos">
						<label for="" class="label-plchld"><liferay-ui:message key="rupee-sign"/> </label>
						<input type="text" name="<portlet:namespace /><%=ParameterConstants.INVESTMENT_AMOUNT %>" id="<portlet:namespace /><%=ParameterConstants.INVESTMENT_AMOUNT %>" label="" class="form-control investmentAmountInput border-0 pl-0"
							data-default-monthly-amount="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}"
							value="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}"
							oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
					</div>
					<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyTermApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>"><liferay-ui:message key="label-policy-term" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-duration-of-your-policy" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>" id="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>" 
							label="" class="form-control">
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyForApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>"><liferay-ui:message key="label-pay-for" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-years-pay-premium" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>" id="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>" label="" 
							class="form-control">
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPaymentOptionApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.PAYMENT_OPTION%>"><liferay-ui:message key="label-payment-mode" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-payment-frequency" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.PAYMENT_OPTION%>" id="<portlet:namespace /><%=ParameterConstants.PAYMENT_OPTION%>" label="" 
							class="form-control investmentAmountInput">
							<c:forEach var="curPaymentOption" items="${productCustomizeSummaryData['paymentOptionsData']}">
								<option data-name="${curPaymentOption.name}" value="${curPaymentOption.key}" 
									${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>
									${curPaymentOption.name}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestingForApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.INVESTING_FOR %>"><liferay-ui:message key="investing-for" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="who-are-you-investing-for" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.INVESTING_FOR %>" id="<portlet:namespace /><%=ParameterConstants.INVESTING_FOR %>" label="" 
							class="form-control">
							<c:forEach var="curInvestingFor" items="${productCustomizeSummaryData['investingForData']}">
								<option value="${curInvestingFor.name}"
									${productCustomizeSummaryData['productDefaultValueData'].investingFor.name == curInvestingFor.name ? 'selected' : ''}>
										${curInvestingFor.name}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGCAPPayoutOptionApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.PAYOUT_OPTION%>"><liferay-ui:message key="label-payout-option" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-maturity-benefit-nature" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.PAYOUT_OPTION%>" id="<portlet:namespace /><%=ParameterConstants.PAYOUT_OPTION%>" label="" class="form-control customizeInput">
							<c:forEach var="curGCPPayoutOption" items="${gcapPayoutOption}">
								<option data-name="${curGCPPayoutOption.value}" value="${curGCPPayoutOption.key}" 
									${productCustomizeSummaryData['productDefaultValueData'].gcapPayoutOption.key == curGCPPayoutOption.key ? 'selected' : ''}>
									${curGCPPayoutOption.value}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
	</div>
</div>
<div class="edelweissTokioTotalReturns">
		<div class="download-brochure download-broucher-mob">			
		 <div class="left">
			<div class="d-none d-md-block" id="<portlet:namespace/>planBrochureContainer">
				<a href="${productCustomizeSummaryData['productBrochure'].link.href}" 
					id="<portlet:namespace/>planBrochure" download="${productCustomizeSummaryData['productBrochure'].link.label}">
						<liferay-ui:message key="label-download-brochure"/>
				</a>
			</div>
			
		  	<div class="premium-form-header">
		  	<div class="title">
		  	<liferay-ui:message key="label-total-guaranteed-benefits" />
		  	</div>
		  	</div>
			<div class="histy-amt-wrpr d-flex align-items-end">
			<div class="histy-amt-content">
			<div class="<portlet:namespace/>totalGuaranteedBenefits return-amt">
				<span id="<portlet:namespace/>totalGuaranteedBenefitAmount">
					<liferay-ui:message key="rupee-sign" />0
				</span>
			</div>
			</div>
			</div>
				</div>
			<div class="break-proceed-group">
				<aui:button type="button" id="benefitAmountBreakup" cssClass="btn btn-primary breakup_btn float-left mx-0" value="label-amount-breakup">
				</aui:button>
				<aui:button type="button" id="proceedBtn" cssClass="btn edto-btn-primary float-right mb-2" value="label-proceed">
				</aui:button>
			</div>
		
	</div>
</div>

</div>
</div>
</div>

</form>
<%@ include file="/family-details.jsp"%>
<%@ include file="/webengage-customize.jsp"%>
<%--
<script type="text/javascript">
	
	$("#<portlet:namespace/>proceedBtn").click(function() {
		
		var eventname = "Customize Page";
	    var returnurl = window.location.href;
		
	    var productName = "${productMetaData['productName']}";
		console.log("product-name::",productName);
		
		var premiumAmount = "";
		var sumAssured = "";
		var guaranteedBenefit = "";
		var premiumPaymentTerm = "";
		var policyTerm = "";
		var paymentFrequency = "";
		var investingFor = "";
		var childName = "";
		var childDOBStr = "";
		var childDOB = "";
		var benefitFrequency = "";
		var adbRider = "";
		var atpdRider = "";
		var ciRider = "";
		var wopRider = "";
		var pwbRider = "";
		var incomeBenefitRider = "";
		
		premiumAmount = $('#<portlet:namespace />investmentAmount').val();
		premiumAmount = Number(premiumAmount.replaceAll(',',''));
		
		var payOption = $('#<portlet:namespace />paymentOption').find(":selected").text();
		if(payOption =='Annual'){
			sumAssured = premiumAmount;
		}else if(payOption =='Semi-Annual'){
			sumAssured = premiumAmount * 2;
		}else if(payOption =='Quarterly'){
			sumAssured = premiumAmount * 4;
		}else if(payOption =='Monthly'){
			sumAssured = premiumAmount * 12;
		}
		
		guaranteedBenefit = $('#<portlet:namespace />totalMaturityAmount').val();
		premiumPaymentTerm = $('#<portlet:namespace />payFor').find(":selected").val();
		paymentFrequency =  $('#<portlet:namespace />paymentOption').find(":selected").val();
		investingFor = $('#<portlet:namespace />investingFor').find(":selected").val();
		policyTerm = $('#<portlet:namespace />policyTerm').find(":selected").val();
		payoutOption = $('#<portlet:namespace />payoutOption').find(":selected").val();
		
		if(investingFor == "Family"){
			childName = $('#<portlet:namespace/>assuranceFullName').val()===undefined?'':$('#<portlet:namespace/>assuranceFullName').val();
			childDOBStr = $('#<portlet:namespace/>assuranceDob').val()===undefined?'':$('#<portlet:namespace/>assuranceDob').val();
			if(childDOBStr){
				var dateParts = childDOBStr.split('/');
			    childDOB = new Date(dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0]);
			}	
		}
		
		prodRidersList.forEach(prodRidersRef => {
			var refKey = prodRidersRef["key"];
			if(refKey==='IncomeBenefitRider'){
				incomeBenefitRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).find(":selected").val();
				if(incomeBenefitRider===undefined) {incomeBenefitRider='';}
			}else if(refKey==='CriticalIllnessRider'){
				ciRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).find(":selected").val();
				if(ciRider===undefined) {ciRider='';}
			}else if(refKey==='ADBRider'){
				adbRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).find(":selected").val();
				if(adbRider===undefined) {adbRider='';}
			}else if(refKey==='WaiverOfPremiumRider'){
				wopRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).prop('checked')==true?'True':'False';
			}else if(refKey==='ATPDRider'){
				atpdRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).find(":selected").val();
				if(atpdRider===undefined) {atpdRider='';}
			}else if(refKey==='PayorWaiverBenefitRider'){
				pwbRider = $('#<portlet:namespace/>riderPremiumAmount_'+prodRidersRef["riderId"]).prop('checked')==true?'True':'False';
			}
		});
		
	    webengage.track(eventname, {
	    	"Product Name" : productName.toString(),
	 	   	"Premium Amount" : Number(premiumAmount),
	 	  	"Sum Assured" : Number(sumAssured),
	 	  	"Guaranteed Benefit" : Number(guaranteedBenefit),
	 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
	 	  	"Policy Term" : Number(policyTerm),
	 	  	"Payment Frequency" : paymentFrequency.toString(),
	 	  	"Investing For" : investingFor.toString(),
	 	  	"Child Name" : childName.toString(),
	 	  	"Child DOB" : childDOB,
	 	  	"Benefit Frequency" : payoutOption.toString(),
	 	  	"ADB Rider" : Number(adbRider),
	 	  	"ATPD Rider" : Number(atpdRider),
	 	  	"CI Rider" : Number(ciRider),
	 	  	"WOP Rider" : wopRider,
	 	  	"PWB Rider" : pwbRider,
	 	  	"Income Benefit Rider" : Number(incomeBenefitRider),
	 	    "URL": returnurl
	       });	
	});

</script>
--%>