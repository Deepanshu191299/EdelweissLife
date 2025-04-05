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
							value="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}" />
					</div>
					<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyOptionApplicable()}">
			<div class="item">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.POLICY_OPTION%>"><liferay-ui:message key="label-plan-option" /></label><br/> 
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-choose-between-2-option" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.POLICY_OPTION %>" id="<portlet:namespace /><%=ParameterConstants.POLICY_OPTION %>" label="" class="form-control payForRelInput">
							<c:forEach var="curPolicyOption" items="${productCustomizeSummaryData['policyOptionsData']}">
								<option 
									data-name="${curPolicyOption.name}" value="${curPolicyOption.key}" 
										${productCustomizeSummaryData['productDefaultValueData'].policyOption.key == curPolicyOption.key ? 'selected' : ''}>
										${curPolicyOption.name}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyForApplicable()}">
			<div class="item" >
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>"><liferay-ui:message key="label-pay-for" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-years-pay-premium" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>" id="<portlet:namespace /><%=ParameterConstants.PAY_FOR %>" label="" class="form-control">
							<c:forEach var="curPayFor" items="${payForMap}">
								<option value="${curPayFor.key}" 
									${productCustomizeSummaryData['productDefaultValueData'].policyFor == curPayFor.value ? 'selected' : ''}>
									${curPayFor.value}
								</option>
							</c:forEach>
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
						<select name="<portlet:namespace /><%=ParameterConstants.PAYMENT_OPTION%>" id="<portlet:namespace /><%=ParameterConstants.PAYMENT_OPTION%>" label="" class="form-control investmentAmountInput">
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
						<select name="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>" id="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>" label="" class="form-control customizeInput">
							<c:forEach var="policyTermYear" begin="10" end="40">
								<option ${productCustomizeSummaryData['productDefaultValueData'].policyTerm == policyTermYear ? 'selected' : ''} 
									value="${policyTermYear }">${policyTermYear }</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPayoutOptionApplicable()}">
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
							<c:forEach var="payoutOption" items="${payoutOptionMap }">
								<option value="${payoutOption.key}" 
									${productCustomizeSummaryData['productDefaultValueData'].payoutOption.key == payoutOption.key ? 'selected' : ''}>
										${payoutOption.value}
								</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
		</c:if>
		
		<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isFamilyIncomeBenefitApplicable()}">
			<div class="item" id="<portlet:namespace/>familyIncomeBenefitContainer">
				<div class="labelInputGroup">
					<div class="labelGroup">
						<label class="pform-title mb-0" for="<portlet:namespace /><%= ParameterConstants.FAMILY_INCOME_BENEFIT%>"><liferay-ui:message key="label-family-income-benefit" /></label> <br/>
						<p class="premiuim-small-text">
							<liferay-ui:message key="label-extra-cover-for-critical-illness-death" />
						</p>
					</div>
					<div class="select-container">
						<select name="<portlet:namespace /><%= ParameterConstants.FAMILY_INCOME_BENEFIT%>" id="<portlet:namespace /><%= ParameterConstants.FAMILY_INCOME_BENEFIT%>" label="" class="form-control customizeInput">
							<c:forEach var="familyIncomeBenebit" items="${productCustomizeSummaryData['familyIncomeBenefitOptions']}">
								<option value="${familyIncomeBenebit.key}" 
									${productCustomizeSummaryData['productDefaultValueData'].familyIncomeBenefit.key == familyIncomeBenebit.key ? 'selected' : ''}>
										${familyIncomeBenebit.name}
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
						<select name="<portlet:namespace /><%=ParameterConstants.INVESTING_FOR %>" id="<portlet:namespace /><%=ParameterConstants.INVESTING_FOR %>" label="" class="form-control customizeInput">
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
		console.log("product-details::",productName);
		
		var premiumAmount = "";
		var sumAssured = "";
		var guaranteedBenefit = "";
		var planOption = "";
		var premiumPaymentTerm = "";
		var policyTerm = "";
		var benefitDuration = "";
		var benefitStartPoint = "";
		var paymentFrequency = "";
		var investingFor = "";
		var familyIncomeBenefit = "";
		var benefitFrequency = "";
		var edelweissEmployee = false;
		var lumpsumBenefit = false;
		var benefitType = "";
		var adbRider = "";
		var atpdRider = "";
		var hcbRider = "";
		var ciRider = "";
		var wopRider = false;
		var pwbRider = false;
		var incomeBenefitRider = "";
		
		var premiumAmount = $('#<portlet:namespace />investmentAmount').val();
		premiumAmount = premiumAmount.replaceAll(',','');
		
		var payOption = $('#<portlet:namespace />paymentOption').find(":selected").text();
		if(payOption==='Annual'){
			sumAssured = premiumAmount;
		}else if(payOption==='Semi-Annual'){
			sumAssured = premiumAmount*2;
		}else if(payOption==='Quarterly'){
			sumAssured = premiumAmount*4;
		}else if(payOption==='Monthly'){
			sumAssured = premiumAmount*12;
		}
		
		guaranteedBenefit = $('#totalGuaranteedBenefitAmount span').text();
		premiumPaymentTerm = $('#<portlet:namespace />payFor').find(":selected").text();
		policyTerm = $('#<portlet:namespace />policyTerm').find(":selected").text();
		paymentFrequency =  $('#<portlet:namespace />paymentOption').find(":selected").text();
		investingFor = $('#<portlet:namespace />investingFor').find(":selected").text();
		familyIncomeBenefit = $('#<portlet:namespace />familyIncomeBenefit').find(":selected").text();
		benefitFrequency = $('#<portlet:namespace />payoutOption').find(":selected").text();
		planOption = $('#<portlet:namespace />policyOption').find(":selected").text();
		benefitType = $('#<portlet:namespace />policyOption').find(":selected").text();
		
		prodRidersList.forEach(prodRidersRef => {
			var refKey = prodRidersRef["key"];
			var riderId = prodRidersRef["riderId"];
			if(refKey==='IncomeBenefitRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					incomeBenefitRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(incomeBenefitRider===undefined) {incomeBenefitRider='';}
			}else if(refKey==='CriticalIllnessRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					ciRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(ciRider===undefined) {ciRider='';}
			}else if(refKey==='ADBRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					adbRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(adbRider===undefined) {adbRider='';}
			}else if(refKey==='WaiverOfPremiumRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					wopRider = $('#<portlet:namespace/>selectedRider_'+riderId).prop('checked')==true?'True':'False';
				}else{
					wopRider = '';
				}
			}else if(refKey==='ATPDRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					atpdRider = $('#<portlet:namespace/>riderPremiumAmount_'+riderId).find(":selected").val();
				}
				if(atpdRider===undefined) {atpdRider='';}
			}else if(refKey==='PayorWaiverBenefitRider'){
				if($('#<portlet:namespace/>selectedRider_'+riderId).is(":checked")===true){
					pwbRider = $('#<portlet:namespace/>selectedRider_'+riderId).prop('checked')==true?'True':'False';
				}else{
					pwbRider = '';
				}
			}
		});

		
	    webengage.track(eventname, {
	 	    "Product Name" : productName.toString(),
	 	   	"Premium Amount" : Number(premiumAmount),
	 	  	"Sum Assured" : Number(sumAssured),
	 	  	"Guaranteed Benefit" : Number(guaranteedBenefit),
	 	  	"Plan Option" : planOption.toString(),
	 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
	 	  	"Policy Term" : Number(policyTerm),
	 	  	"Benefit Duration" : Number(benefitDuration),
	 	  	"Benefit Start Point" : Number(benefitStartPoint),
	 	  	"Payment Frequency" : paymentFrequency.toString(),
	 	  	"Investing For" : investingFor.toString(),
	 	  	"Family Income Benefit" : familyIncomeBenefit.toString(),
	 	  	"Benefit Frequency" : benefitFrequency.toString(),
	 	  	"Edelweiss Employee" : edelweissEmployee,
	 	  	"Lumpsum Benefit" : lumpsumBenefit,
	 	  	"Benefit Type" : benefitType.toString(),
	 	  	"ADB Rider" : Number(adbRider),
	 	  	"ATPD Rider" : Number(atpdRider),
	 	   	"HCB Rider" : Number(hcbRider),
	 	  	"CI Rider" : Number(ciRider),
	 	  	"WOP Rider" : wopRider,
	 	  	"PWB Rider" : pwbRider,
	 	  	"Income Benefit Rider" : Number(incomeBenefitRider),
	 	    "URL": returnurl
	       });	
	});
</script>
--%>