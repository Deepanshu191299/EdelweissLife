<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@page import="in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants"%>
<%@ include file="/init.jsp"%>

<portlet:resourceURL var="saveBasicDetailsURL" id="/save/basicDetails"></portlet:resourceURL>
<portlet:resourceURL var="getBasicDetailsURL" id="/get/BasicDetails"></portlet:resourceURL>
<portlet:resourceURL id="/buyJourney/generate-bi" var="edelweissGenerateBIURL" />
<portlet:resourceURL id="/edelweiss/saral-jeevan-bima/generate-pl" var="saralJeevanBimaPLURL" />
<portlet:resourceURL id="/edelweiss/saral-jeevan-bima/generate-pm" var="saralJeevanBimaPMURL" />
<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>
<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>

<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>

<!-- Loader -->
<div id='<portlet:namespace/>tracker-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<c:if test="${not empty basicDetailsMap }">
	<liferay-util:include page="/product_jsps/saral-jeevan-bima/customer_details.jsp" servletContext="<%=application%>" />
	<%@ include file="edit_basic_detailss.jsp"%>
</c:if>


<c:choose>
	<c:when test="${isProductSelected}">
		<form name="<portlet:namespace />customizeForm"
			id="<portlet:namespace />customizeForm" method="post" action="${saveInvestmentDetails}">

			<input type="hidden" class="form-control" id="<portlet:namespace />productCode" name="<portlet:namespace />productCode" value="${productMetaData['productCode']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />productName" name="<portlet:namespace />productName" value="${productMetaData['productName']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerEnquiryId" name="<portlet:namespace />customerEnquiryId" value="${customerEnquiryId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />isFormSubmitPending" name="<portlet:namespace />isFormSubmitPending" value="false" />

			<aui:input name="<%=ParameterConstants.CUSTOMER_FAMILY_DETAILS_ID%>" type="hidden" value="${customerFamilyDetailsId }"/>
			<aui:input name="<%=ParameterConstants.PAY_FOR%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.TOTAL_MATURITY_AMOUNT%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.PREMIUM_AMOUNT%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.BASE_PREMIUM_AMOUNT%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.GST%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.ILLUSTRATION_URL%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.INVESTMENT_AMOUNT%>" type="hidden" value=""/>
			<aui:input name="<%=ParameterConstants.QUOTATION_ID%>" type="hidden" value=""/>

			<div class="edelweissForm">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div id="edelweissTokioForm1" class="edelweissTokioForm">
							<div class="row owl-carousel owl-theme">
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isLifeCoverApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />lifeCover"
												class="pform-title mb-0"><liferay-ui:message
												key="life-cover" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="label-sum-assured" />
												</p>
											</div>
											<div class="pos">
												<label for="" class="label-plchld"><liferay-ui:message
													key="rupee-sign" /> 
												</label>
													<input type="text" class="form-control border-0 pl-0" id="<portlet:namespace />lifeCover" name="<portlet:namespace />lifeCover" value="${productCustomizeSummaryData['productDefaultValueData'].getLifeCover()}">
											</div>
											<div class="amt-words" id="amt-in-word">
												<liferay-ui:message key="rupee-sign" />
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyTermApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyTerm"
													class="pform-title mb-0"><liferay-ui:message
													key="policy-term" />
												</label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="label-years-coverd" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control"
													id="<portlet:namespace />policyTerm" 
													name="<portlet:namespace />policyTerm" >
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPaymentOptionApplicable()}">
									<div class=" itannualIncomeJElem">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />paymentOption"
												class="pform-title mb-0"><liferay-ui:message
												key="payment-frequency" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="lable-life-cover-payout-frequency" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />paymentOption" name="<portlet:namespace />paymentOption">
													<c:forEach var="curPaymentOption"
														items="${productCustomizeSummaryData['paymentOptionsData']}">
														<option data-name="${curPaymentOption.name}"
															value="${curPaymentOption.key}"
															${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>${curPaymentOption.name}</option>
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
										<a href="${productCustomizeSummaryData['productBrochure'].link.href}" id="<portlet:namespace/>planBrochure" 
											download="${productCustomizeSummaryData['productBrochure'].link.label}">
											<liferay-ui:message key="label-download-brochure" />
										</a>
									</div>
						
									<div class="premium-form-header">
										<div class="title">
											<liferay-ui:message key="premium-amount" />
										</div>
									</div>
									<div class="histy-amt-wrpr d-flex align-items-end">
										<div class="histy-amt-content">
											<div class="<portlet:namespace/>totalGuaranteedBenefits return-amt">
												<span class="rupee">
													<liferay-ui:message key="rupee-sign" />
												</span>
												<span id="<portlet:namespace/>totalGuaranteedBenefitAmount">0</span>
											</div>
											<span class="">
												<liferay-ui:message key="label-incl-gst"/>
											</span>
										</div>
									</div>
								</div>
								<div class="break-proceed-group">
									<button class="btn btn btn-primary breakup_btn float-left mx-0 btn-secondary" id="<portlet:namespace/>benefitAmountBreakup" 
										type="button" data-target="#planMaturityBenefitsBreakup" data-toggle="modal"> 
										<span class="lfr-btn-label"><liferay-ui:message key="label-amount-breakup" /></span>
										<span><i class="fas fa-chevron-down"></i></span> 
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</c:when>
</c:choose>

<div class="modal  maturitybenefittable" tabindex="-1" role="dialog" id="planMaturityBenefitsBreakup" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container" role="document">
		<div class="modal-content maturity-benefit-wrapper">
			<div class="modal-header">
				<h4 class="modal-title"><liferay-ui:message key="premium-amount" /></h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			<div class="table-wrap">
    <table class="mb-0 sjbTable table table-borderless w-100">
        <tbody>
            <tr>
                <td>Premium Amount</td>
                <td><span class="rupee">&#8377;</span> <span id="premium-amount"></span></td>
            </tr>
            <tr>
                <td>GST</td>
                <td><span class="rupee">&#8377;</span> <span id="gst"></span></td>
            </tr>
            <tr class="total-row">
                <td>Total Premium</td>
                <td><span class="rupee">&#8377;</span> <span id="total-premium"></span></td>
            </tr>
        </tbody>
    </table>
</div>		
			</div>
		</div>
	</div>
</div>

<%@ include file="product_plans.jsp"%>

<script>
	var namespace="<portlet:namespace/>";

	var saveBasicDetailsURL = "${saveBasicDetailsURL}"; 
	var getBasicDetailsURL = "${getBasicDetailsURL}";
	var edelweissGenerateBIURL = "${edelweissGenerateBIURL}";
	var saralJeevanBimaPLURL = "${saralJeevanBimaPLURL}";
	var saralJeevanBimaPMURL = "${saralJeevanBimaPMURL}";
	var updateLMSLeadURL = "${updateLMSLeadURL}";
	var productMetaData = "${productMetaData}";
	
	var customerDob =  "${basicDetailsMap['dateOfBirth']}";
	var defaultPolicyTerm = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()}";
	
	var productValidation = '${productCustomizeSummaryData["productValidations"]}';
	try{
		productValidation = JSON.parse(productValidation);
		productValidation = productValidation[0]
	}catch(err){
		console.warn("Error while parsing productValidations JSON");
	}
	
	var personalDetailsJEl = "#personal-details",
	fullNameEl = namespace + "fullName",
	fullNameJEl = "#" + fullNameEl,
	dateOfBirthEl = namespace + "dateOfBirth",
	dateOfBirthJEl = "#" + dateOfBirthEl,
	emailEl = namespace + "email",
	emailJEl = "#" + emailEl,
	mobileNumberEl = namespace + "mobileNumber",
	mobileNumberJEl = "#" + mobileNumberEl,
	policyTermEl = namespace + "policyTerm",
	policyTermJEl = "#" + policyTermEl,
	occupationEl = namespace+"occupation",
	occupationJEl = "#"+occupationEl,
	educationQualificationEl = namespace+ "educationQualification",
	educationQualificationJEl = "#"+ educationQualificationEl,
	natureOfDutyEl = namespace+"natureOfDuty",
	natureOfDutyJEl = "#"+ natureOfDutyEl,
	spouseParentRelationEl = namespace+"spouseParentRelation",
	spouseParentRelationJEl = "#" + spouseParentRelationEl,
	spouseParentOccupationsEl = namespace+ "spouseParentOccupations",
	spouseParentOccupationsJEl = "#" + spouseParentOccupationsEl,
	spouseParentSumassuredEl = namespace + "spouseParentSumassured",
	spouseParentSumassuredJEl = "#" + spouseParentSumassuredEl,
	genderEl = namespace + "gender",
	smokerEl = namespace + "smoker",
	smokerJEl = "#" + smokerEl,
	annualIncomeEl  = namespace + "annualIncome",
	annualIncomeJEl = "#" + annualIncomeEl ,
	pinCodeEl  = namespace + "pincode",
	pinCodeJEl = "#" + pinCodeEl ;

	var investmentAmountEl = namespace + "investmentAmount";
	var investmentAmountJEl = "#" + investmentAmountEl;
	var lifeCoverEl = namespace + "lifeCover";
	var lifeCoverJEl = "#" + lifeCoverEl;
	var policyTermEl = namespace + "policyTerm";
	var policyTermJEl = "#" + policyTermEl;
	var paymentOptionEl = namespace + "paymentOption";
	var paymentOptionJEl = "#" + paymentOptionEl;
	var productCodeEl = namespace + "productCode";
	var productCodeJEl = "#" + productCodeEl;
	var saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}";
	var customizeFormEl = namespace + "customizeForm";
	var customizeFormJEl = "#" + customizeFormEl;
	
	var illustrationURLEl = portletNamespace + "illustrationURL";
	var illustrationURLJEl = "#" + illustrationURLEl;
	var gstEl = portletNamespace + "gst";
	var gstJEl = "#" + gstEl;
	var basePremiumAmountEl = portletNamespace + "basePremiumAmount";
	var basePremiumAmountJEl = "#" + basePremiumAmountEl;
	var premiumAmountEl = portletNamespace + "premiumAmount";
	var premiumAmountJEl = "#" + premiumAmountEl;
	var totalMaturityAmountEl = portletNamespace + "totalMaturityAmount";
	var totalMaturityAmountJEl = "#" + totalMaturityAmountEl;
	var payForEl = portletNamespace + "payFor";
	var payForJEl = "#" + payForEl;
	var quotationIdJEl = "#" + namespace + "quotationId";
	
	const containers = document.querySelectorAll('.container1');
	var recommendedDiv = "";
	var activeDiv = localStorage.getItem('activeDivId');
	var activeDivElement;
	var spanElements;
	var span1Value;
	var span3Value;
	var selectedCardTitle;
	var selectedProductValue;


	var sjbProductPlansValues = JSON.parse('${sjbProductPlansItems}');
  
  
	var LANG_MESSAGES = {};
	LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
	var rupeeSignLabel = LANG_MESSAGES["rupee-sign"];
</script>

<style>

	#rupee {
		display: inline-block;
		font-size: 20px;
		margin-right: 5px;
	}

	.main-container {
		display: flex;
	}

	.container1 {
		flex: 1;
		margin: 10px;
		padding-top: 20px;
		border: 1px solid #ccc;
		border-radius: 5px;
		cursor: pointer;
	}
	.container1.active {
		border: 2px solid blue;
	}
	.sjbTable tbody > tr > td:nth-child(2) {
    text-align: right;
 }
.total-row td {
    border-top: 1px solid #ccc;
    font-family: 'Montserrat-Bold';
}
@media (min-width: 992px) {
.maturitybenefittable .popup-container {
    max-width: 580px !important;
}
}
.sjbCard .offer-top {
    min-height: auto;
}
@media (max-width: 767px) {
	.main-container {
		flex-direction: column;
		align-items: center;

}
}
.sjbCard .bullet-list {
		height: auto;
	}
</style>

<script src="<%=request.getContextPath()%>/product_jsps/saral-jeevan-bima/js/sjb_basic_detail.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>
<script src="<%=request.getContextPath()%>/product_jsps/saral-jeevan-bima/js/sjb_main.js?t<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>

<%@ include file="/webengage-customize.jsp"%>
<%--
<script type="text/javascript">
	var customerDob =  "${basicDetailsMap['basicInvestingFor'] == 'Family'? basicDetailsMap['assuranceDob'] :  basicDetailsMap['dateOfBirth'] }";

	callSJBWebEngage = function() {
		
		var eventname = "SJB_Quote";
	    var returnurl = window.location.href;
		
	    var productName = "${productMetaData['productName']}";
		console.log("product-details::",productName);
		
		var lifeCover = "";
		var sumAssured = "";
		var guaranteedBenefit = "";
		var planOption = "";
		var premiumPaymentTerm = "";
		var policyTerm = "";
		var paymentFrequency = "";
		var investingFor = "";
		var edelweissEmployee = false;
		var premiumAmount = $(premiumAmountJEl).val();
		
		var lifeCover = $('#<portlet:namespace />lifeCover').val();
		lifeCover = lifeCover.replaceAll(',','');

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
		
		if(String(activeDiv) === "productForRegular"){
			selectedCardTitle = "Regular Pay";
		}else if(String(activeDiv) === "productFor10"){
			selectedCardTitle = "Limited Pay for 10 years";
		}else if(String(activeDiv) === "productFor5"){
			selectedCardTitle = "Limited Pay for 5 years";
		}else if(String(activeDiv) === "productForSingle"){
			selectedCardTitle = "Single Pay";
		}

		premiumPaymentTerm = $('#<portlet:namespace />policyFor').find(":selected").text();
		policyTerm = $('#<portlet:namespace />policyTerm').find(":selected").text();
		paymentFrequency =  $('#<portlet:namespace />paymentOption').find(":selected").text();
		investingFor = $('#<portlet:namespace />investingFor').find(":selected").text();

	    webengage.track(eventname, {
	 	    "productName" : "Saral Jeevan Bima",
	 	   	"premiumAmount" : Number(selectedProductValue),
	 	  	"lifeCover" : Number(sumAssured),
	 	  	"planSelected" : selectedCardTitle.toString(),
	 	  	"Premium Payment Term" : Number(premiumPaymentTerm),
	 	  	"policyTerm" : Number(policyTerm),
	 	  	"paymentFrequency" : paymentFrequency.toString(),
	 	  	"Investing For" : investingFor.toString(),
	 	  	"Edelweiss Employee" : edelweissEmployee,
	 	    "URL": returnurl
	       });	
	}

</script>
--%>