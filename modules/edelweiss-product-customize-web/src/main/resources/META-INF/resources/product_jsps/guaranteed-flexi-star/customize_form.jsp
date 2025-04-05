<%@page import="in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants"%>
<%@ include file="/init.jsp"%>

<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>

<portlet:resourceURL id="/edelweiss/generate-bi" var="edelweissGenerateBIURL">
</portlet:resourceURL>

<portlet:resourceURL var="saveBasicDetailsURL" id="/save/basicDetails"></portlet:resourceURL>

<portlet:resourceURL var="saveFamilyDetailsURL" id="/save/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="deleteFamilyDetailsURL" id="/delete/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="updateLittleChampBenefitURL" id="/update/littleChampBenefit"></portlet:resourceURL>

<portlet:resourceURL var="saveFundDetailsURL" id="/save/fundDetails"></portlet:resourceURL>


<portlet:actionURL var="saveInvestmentDetails" name="/save/investmentDetails" ></portlet:actionURL>

<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>

<portlet:resourceURL var="getBasicDetailsURL" id="/get/BasicDetails"></portlet:resourceURL>

<%

Map<String, String> productMetaData = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("productMetaData")))
{
	productMetaData = (Map<String, String>)renderRequest.getAttribute("productMetaData");
}
%>
<!-- Loader -->
<div class="preloader" id="preloader"></div>
<%@ include file="/product_jsps/guaranteed-flexi-star/edit_basic_details.jsp" %>
<%-- <%@ include file="/edit_basic_details.jsp" %> --%>

<div class="edelweissTokioDetails p-2">
	<div class="container">
		<div class="edelweissTokioDetailsInner d-flex align-items-center">
			<div class="edelweissTokioHeading">
				<h5 class="fontbold">Your Details</h5>
				<h5 class="fontbold d-block d-md-none d-lg-none basic-details"><liferay-ui:message key="label-basic-details"/></h5>
			</div>
			<div class="edelweissTokioFormDetails d-none d-md-block">
				<ul class="nav" id="personal-details">
					<li class="nav-item">${basicDetailsMap['fullName']}</li>
					<li class="nav-item">${basicDetailsMap['gender']}</li>
					<li class="nav-item">${basicDetailsMap['dateOfBirth']}</li>
					<li class="nav-item">${basicDetailsMap['mobileNumber']}</li>
					<li class="nav-item">${basicDetailsMap['email']}</li>
					<li class="nav-item">${picklistOptionValues[basicDetailsMap['investmentObjective']]}</li>
					<li class="nav-item">${basicDetailsMap['basicInvestingFor']}</li>
					<c:if test="${basicDetailsMap['basicInvestingFor'] == 'Family' }">
						<li class="nav-item">${basicDetailsMap['assuranceFullName']}</li>
						<li class="nav-item">${basicDetailsMap['assuranceDob']}</li>
					</c:if>
				</ul>
			</div>
			<div class="editModel ml-md-auto">
				<!-- <a href="javascript:void(0);" data-toggle="modal"
					data-target="#exampleModalCenter"> -->
					<!-- <button type="button" name="editBasicDetails" id="editBasicDetails">
						<i class="fa fa-edit" style="font-size: 20px"></i>Edit
					</button> -->
					<span name="editBasicDetails" id="editBasicDetails"> <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
						viewBox="0 0 22 22">
                                <path fill="#999" fill-rule="nonzero"
							stroke="#999" stroke-width=".3"
							d="M19.705 2.295a4.41 4.41 0 0 0-6.243 0L2.016 13.738a.573.573 0 0 0-.162.328l-.848 6.282a.57.57 0 0 0 .162.482c.106.106.255.17.404.17.026 0 .051 0 .077-.004l6.225-.825c.315-.042.474-.332.432-.647a.576.576 0 0 0-.648-.495l-5.412.722.592-4.381 4.61 4.611c.107.107.194.17.343.17.15 0 .36-.059.467-.17L19.705 8.538A4.388 4.388 0 0 0 21 5.414a4.37 4.37 0 0 0-1.295-3.12zm-6.147 1.22l1.922 1.921L5.035 15.882 3.113 13.96 13.558 3.514zm-5.825 15.06l-1.88-1.879L16.298 6.251l1.88 1.879L7.732 18.576zM18.979 7.308L14.38 2.71a3.255 3.255 0 0 1 2.08-.746c.874 0 1.692.341 2.31.955.618.613.954 1.436.954 2.31 0 .771-.264 1.496-.745 2.08z"></path>
                            </svg>
                            </span>
			<!-- 	</a> -->
			</div>
			<div id="<portlet:namespace/>planBrochureContainer" class="brochure-link d-block d-md-none d-lg-none ml-auto pl-2" style="white-space: nowrap;">
				<a href="${productCustomizeSummaryData['productBrochure'].link.href}" 
					id="<portlet:namespace/>planBrochure" download="${productCustomizeSummaryData['productBrochure'].link.label}">
						<liferay-ui:message key="label-download-brochure"/>
				</a>
			</div>
		 </div>
		</div>
	</div>
<c:choose>
	<c:when test="${isProductSelected}">
		<form name="<portlet:namespace />customizeForm" id="<portlet:namespace />customizeForm" method="post" action="${saveInvestmentDetails}">

			<input type="hidden" class="form-control" id="<portlet:namespace />productCode" name="<portlet:namespace />productCode" value="${productMetaData['productCode']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />productName" name="<portlet:namespace />productName" value="${productMetaData['productName']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerEnquiryId" name="<portlet:namespace />customerEnquiryId" value="${customerEnquiryId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerInvestmentDetailsId" name="<portlet:namespace />customerInvestmentDetailsId" value="${customerInvestmentDetailsId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerPolicyDetailsId" name="<portlet:namespace />customerPolicyDetailsId" value="${customerPolicyDetailsId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerFamilyDetailsId" name="<portlet:namespace />customerFamilyDetailsId" value="${customerFamilyDetailsId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />premiumAmount" name="<portlet:namespace />premiumAmount"   value=""/>
			<input type="hidden" class="form-control" id="<portlet:namespace />basePremiumAmount" name="<portlet:namespace />basePremiumAmount" type="hidden" value=""/>
			<input type="hidden" class="form-control" id="<portlet:namespace />totalMaturityAmount" name="<portlet:namespace />totalMaturityAmount" type="hidden" value=""/>
			<input type="hidden" name="<portlet:namespace />illustrationURL" id="<portlet:namespace />illustrationURL" />
			<input type="hidden" name="<portlet:namespace />quotationId" id="<portlet:namespace />quotationId" />
			<input type="hidden" name="<portlet:namespace />policyTerm" id="<portlet:namespace />policyTerm" /> 
			
			<input type="hidden" class="form-control" id="<portlet:namespace />selectedRidersIds" name="<portlet:namespace />selectedRidersIds" type="hidden" value='${productCustomizeSummaryData['productDefaultValueData'].selectedRidersIds}'/>

			
			<%-- To Show Little Champ Modal and Submit It from Little Champ --%>
			<input type="hidden" class="form-control" id="<portlet:namespace />isFormSubmitPending" name="<portlet:namespace />isFormSubmitPending" value="false" />
			
			<div class="edelweissForm">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div id="edelweissTokioForm1" class="edelweissTokioForm">
							<div class="row owl-carousel owl-theme">
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestmentAmountApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />investmentAmount" class="pform-title mb-0"><liferay-ui:message key="investment-amount" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="amount-you-will-invest" />
												</p>
											</div>
											<div class="pos">
												<label for="" class="label-plchld"><liferay-ui:message key="rupee-sign"/> </label>
												<input type="text" class="form-control border-0 pl-0" id="<portlet:namespace />investmentAmount" name="<portlet:namespace />investmentAmount" value="${productCustomizeSummaryData['productDefaultValueData'].getInvestmentAmount()}">
											</div>
											<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPolicyOptionApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyOption" class="pform-title mb-0"><liferay-ui:message key="label-plan-option" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-policy-preference" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />policyOption" name="<portlet:namespace />policyOption">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSIncomePayoutType()}">
									<div class="item" id="<portlet:namespace />incomePayoutTypeDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />incomePayoutType" class="pform-title mb-0"><liferay-ui:message key="label-income-payout-type" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-payout-preference" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />incomePayoutType" name="<portlet:namespace />incomePayoutType">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSPayoutIncreasingPercentage()}">
									<div class="item" id="<portlet:namespace />payoutIncreasingPercentageDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />payoutIncreasingPercentage" class="pform-title mb-0"><liferay-ui:message key="label-payout-increasing-percentage" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-payout-increase" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />payoutIncreasingPercentage" name="<portlet:namespace />payoutIncreasingPercentage">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isPaymentOptionApplicable()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />paymentOption" class="pform-title mb-0"><liferay-ui:message key="payment" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-payment-frequency" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />paymentOption" name="<portlet:namespace />paymentOption">

												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSSADMultipleDropdown()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />sumAssuredMultiple" class="pform-title mb-0"><liferay-ui:message key="sum-assured-multiple" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="sad-multiple" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />sumAssuredMultiple" name="<portlet:namespace />sumAssuredMultiple">

												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSPolicyTerm()}">
									<div class=" item" id="<portlet:namespace />policyTermDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyTerm" class="pform-title mb-0"><liferay-ui:message key="policy-term" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="duration-of-your-policy" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />policyTermSelect" name="<portlet:namespace />policyTermSelect">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSIncomeDurationApplicable()}">
									<div class=" item" id="<portlet:namespace />incomeDurationDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />incomeDuration" class="pform-title mb-0"><liferay-ui:message key="income-duration" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="income-benifit-payout-duration" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />incomeDuration" name="<portlet:namespace />incomeDuration">

												</select>
											</div>
										</div>
									</div>
								</c:if>
								
								<!-- need to modify lable -->
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSIncomePayoutTime()}">
									<div class=" item" id="<portlet:namespace />incomePayoutTimeDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />incomePayoutTime" class="pform-title mb-0"><liferay-ui:message key="income-payout-time" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="income-benifit-payout-time" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />incomePayoutTime" name="<portlet:namespace />incomePayoutTime">

												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSPayFor()}">
									<div class=" item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />payFor" class="pform-title mb-0"><liferay-ui:message key="pay-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="no-of-years-you-will-pay-the-premium" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />payFor" name="<portlet:namespace />payFor">
													
												</select>
											</div>
											<div>
												<liferay-ui:message key="policy-term" />
												<span id="<portlet:namespace />policyTermValue" data-policy-term="${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()}">
													${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()} Years
												</span> Years
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSIncomeStartPoint()}">
									<div class=" item" id="<portlet:namespace />incomeStartPointDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />incomeStartPoint" class="pform-title mb-0"><liferay-ui:message key="income-start-point" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="policy-year-on-which-pay-out-starts" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />incomeStartPoint" name="<portlet:namespace />incomeStartPoint">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSCashBack()}">
									<div class="item" id="<portlet:namespace />cashBackDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />cashBack" class="pform-title mb-0"><liferay-ui:message key="cash-back" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-preference-for-cash-back" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />cashBack" name="<portlet:namespace />cashBack">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isGFSCashBackPercentage()}">
									<div class="item" id="<portlet:namespace />cashBackPercentageDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />cashBackPercentage" class="pform-title mb-0"><liferay-ui:message key="cash-back-percentage" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="cash-back-percentage" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />cashBackPercentage" name="<portlet:namespace />cashBackPercentage">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isInvestingForApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />investingFor" class="pform-title mb-0"><liferay-ui:message key="investing-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="who-are-you-investing-for" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />investingFor" name="<portlet:namespace />investingFor">
													
												</select>
											</div>
										</div>
									</div>
								</c:if>
								<c:if test="${productCustomizeSummaryData['productDefaultValueData'].isEdelweissEmployeeApplicable()}">
									<div class=" item" id="<portlet:namespace />isEdelweissEmployeeDiv">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />isEdelweissEmployee" class="pform-title mb-0"><liferay-ui:message key="isEdelweissEmployee" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="awail-for-employee-benefit" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" id="<portlet:namespace />isEdelweissEmployee" name="<portlet:namespace />isEdelweissEmployee">

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
										<a
							href="${productCustomizeSummaryData['productBrochure'].link.href}"
							id="<portlet:namespace/>planBrochure"
							download="${productCustomizeSummaryData['productBrochure'].link.label}">
							<liferay-ui:message key="label-download-brochure" />
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
										<span class="rupee"><liferay-ui:message
												key="rupee-sign" /></span> <span
											id="<portlet:namespace/>totalGuaranteedBenefitAmount">0</span>
									</div>
                                    </div>
									</div>
									</div>
									<div class="break-proceed-group">
										<aui:button type="button" id="benefitAmountBreakup"
											cssClass="btn btn-primary breakup_btn float-left mx-0"
											value="label-amount-breakup" data-toggle="modal"
											data-target="#planMaturityBenefitsBreakup">
										</aui:button>
										<aui:button type="button" id="proceedBtn" disabled="true"
											cssClass="btn btn-primary edto-btn-primary float-right mb-2" value="label-proceed">
										</aui:button>
									</div>
								
							</div>
						</div>
						
					</div>
				</div>
 			</div>
		</form>
		<%@ include file="/product_jsps/guaranteed-flexi-star/family-details.jsp"%>
		<%@ include file="/product_jsps/guaranteed-flexi-star/product_features.jsp"%>
		<%@ include file="/product_jsps/guaranteed-flexi-star/product_riders.jsp"%>
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="please-select-product-name-from-page-setting" />
		</div>
	</c:otherwise>
</c:choose>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="errorMessageModal" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container modal-sm" role="document">
		<div class="modal-content">			
			<div class="modal-body">
			<div class="modal-header m-1 p-1">
							<h2 class="fs22 fontbold  w-100">
					<liferay-ui:message key="error" />
				</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
</svg>
					</span>
				</button>
			</div>
			<div id="errorMessageContainer"><liferay-ui:message key="service-unavailable-error-msg" /></div>
			<div class="modal-footer p-1" style="border: unset;">
				<button type="button" class="edto-btn-primary" data-dismiss="modal">
					<liferay-ui:message key="ok" />
				</button>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal  maturitybenefittable" tabindex="-1" role="dialog" id="planMaturityBenefitsBreakup" style="display: none;">
	<div class="modal-dialog modal-dialog-centered popup-container" role="document">
		<div class="modal-content maturity-benefit-wrapper">
			<div class="modal-header">
				<h2 class="modal-title"><liferay-ui:message key="label-maturity-benefits" /></h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
			   <div class="table-wrap">
				<table class="maturity-table table table-head scrl-tbl-w table-border" width="100%" id="planMaturityBenefitsBreakupTable">
			        <thead style="display: table;">
			        <tr>	        
			            <td>Policy Year</td>
			            <td>Annualized Premium</td>
			            <td>Income Benefit</td>
			            <td>Maturity Benefit (INR)</td>
			            <td>Death Benefit</td>
			        </tr>
			        </thead>
			        <tbody class="scrl-tbl-hgt m-cust-scroll maturity-data-table" id="planMaturityBenefitsBreakupTableBody">
			           
			        </tbody>
				</table>			
              </div>
		       <!-- 	<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal"><liferay-ui:message key="ok" /></button>
			    </div>
			    -->
		</div>
		</div>
	</div>
</div>


<style>
	.full-div{
		display: inline-block !important;width: 100%;
	}
</style>
<script>

var saveBasicDetailsURL = "${saveBasicDetailsURL}"; 
var basicInvestingFor = '${basicDetailsMap["basicInvestingFor"]}';
var isNRI ='${basicDetailsMap["isNRI"]}';
var nRIMobileNumber  ='${basicDetailsMap["nRIMobileNumber"]}';
var getBasicDetailsURL = "${getBasicDetailsURL}";
var productJsonConfiguration = ${productJsonConfiguration};

var investmentAmountCus = "${customerInvestmentDetails.get("investmentAmount")}";
var policyOptionCus = "${customerInvestmentDetails.get("policyOption")}";
var incomePayoutTypeCus = "${customerInvestmentDetails.get("incomePayoutType")}";
var paymentFrequencyCus = "${customerInvestmentDetails.get("paymentFrequency")}";
var sumAssuredMultipleCus = "${customerInvestmentDetails.get("sumAssuredMultiple")}";
var incomeDurationCus = "${customerInvestmentDetails.get("incomeDuration")}";
var incomePayoutTimeCus = "${customerInvestmentDetails.get("incomePayoutTime")}";
var incomeStartPointCus = "${customerInvestmentDetails.get("incomeStartPoint")}";
var premiumPayingTermCus = "${customerInvestmentDetails.get("premiumPayingTerm")}";
var policyTermCus = "${customerInvestmentDetails.get("policyTerm")}";
var investingForCus = "${customerInvestmentDetails.get("investingFor")}";
var edelweissEmployeeCus = "${customerInvestmentDetails.get("edelweissEmployee")}";

try {
	productJsonConfiguration = JSON.parse(productJsonConfiguration.customizePageProductJson);
	
	//setting cookies values
	
	if(policyOptionCus != ''){
		productJsonConfiguration.planOptionDefVal = policyOptionCus;
	}
	
	if(incomePayoutTypeCus != ''){
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.incomePayoutType = incomePayoutTypeCus;
	}
	
	if(paymentFrequencyCus != ''){
		productJsonConfiguration.lumpsum.lumsumDefault.paymentOption = paymentFrequencyCus;
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.paymentOption = paymentFrequencyCus;
	}
	
	if(incomeDurationCus != ''){
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.incomeDuration = incomeDurationCus;
	}
	
	if(incomePayoutTimeCus != ''){
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.incomePayoutTime = incomePayoutTimeCus;
	}
	
	if(incomeStartPointCus != ''){
		productJsonConfiguration.lumpsumPlusIncome.incomeStartPoint.forEach(isp => {
		    const ispTargetObj = isp.ISPList.some(item => item.value === incomeStartPointCus);

		    if (ispTargetObj) {
		    	isp.defaultISPVal = incomeStartPointCus;
		    }
		});
	}
	
	if(premiumPayingTermCus != ''){
		productJsonConfiguration.lumpsum.lumsumDefault.payFor = premiumPayingTermCus;
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.payFor = premiumPayingTermCus;
	}
	
	if(policyTermCus != ''){
		productJsonConfiguration.lumpsum.lumsumDefault.policyTerm = policyTermCus;
	}
	
	if(investingForCus != ''){
		productJsonConfiguration.lumpsum.lumsumDefault.investingFor = investingForCus;
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.investingFor = investingForCus;
	}
	
	if(edelweissEmployeeCus != ''){
		productJsonConfiguration.lumpsum.lumsumDefault.isEdelWeissEmployee = edelweissEmployeeCus;
		productJsonConfiguration.lumpsumPlusIncome.lumpsumPlusIncomeDefault.isEdelWeissEmployee = edelweissEmployeeCus;
	}
	
	if(sumAssuredMultipleCus != ''){
		productJsonConfiguration.lumpsum.sumAssuredMultiple.sumAssuredMultipleScenario.forEach(scenario => {
		    const sadValIncludesTarget = scenario.sadVal.some(item => item.value === sumAssuredMultipleCus);

		    if (sadValIncludesTarget) {
		        scenario.defaultVal = sumAssuredMultipleCus;
		    }
		});
		
		productJsonConfiguration.lumpsumPlusIncome.sumAssuredMultiple.sumAssuredMultipleScenario.forEach(scenario => {
		    const sadValIncludesTarget = scenario.sadVal.some(item => item.value === sumAssuredMultipleCus);

		    if (sadValIncludesTarget) {
		        scenario.defaultVal = sumAssuredMultipleCus;
		    }
		});
	}
	
} catch (error) {
  console.error("Error parsing customizePageProductJson:", error);
}

var LANG_MESSAGES = {};
LANG_MESSAGES["grand-son"] = "<liferay-ui:message key='grand-son' />";
LANG_MESSAGES["grand-daughter"] = "<liferay-ui:message key='grand-daughter' />";
LANG_MESSAGES["sons-full-name"] = "<liferay-ui:message key='sons-full-name' />";
LANG_MESSAGES["sons-dob"] = "<liferay-ui:message key='sons-dob' />";
LANG_MESSAGES["daughters-full-name"] = "<liferay-ui:message key='daughters-full-name' />";
LANG_MESSAGES["daughters-dob"] = "<liferay-ui:message key='daughters-dob' />";
LANG_MESSAGES["grandsons-full-name"] = "<liferay-ui:message key='grandsons-full-name' />";
LANG_MESSAGES["grandsons-dob"] = "<liferay-ui:message key='grandsons-dob' />";
LANG_MESSAGES["granddaughter-full-name"] = "<liferay-ui:message key='granddaughter-full-name' />";
LANG_MESSAGES["granddaughter-dob"] = "<liferay-ui:message key='granddaughter-dob' />";
LANG_MESSAGES["enter-your-x-full-name"] = "<liferay-ui:message key='enter-your-x-full-name' />";
LANG_MESSAGES["enter-your-x-date-of-birth"] = "<liferay-ui:message key='enter-your-x-date-of-birth' />";
LANG_MESSAGES["age-between-0-to-17-years"] = "<liferay-ui:message key='age-between-0-to-17-years' />";
LANG_MESSAGES["rupee-sign"] = "<liferay-ui:message key='rupee-sign' />";
LANG_MESSAGES["enter-your-son-full-name"] = "<liferay-ui:message key='enter-your-son-full-name' />";
LANG_MESSAGES["enter-your-daughter-full-name"] = "<liferay-ui:message key='enter-your-daughter-full-name' />";
LANG_MESSAGES["enter-your-grand-son-full-name"] = "<liferay-ui:message key='enter-your-grand-son-full-name' />";
LANG_MESSAGES["enter-your-grand-daughter-full-name"] = "<liferay-ui:message key='enter-your-grand-daughter-full-name' />";

if(basicInvestingFor == 'Family'){
	var assuranceFullName = '${basicDetailsMap["assuranceFullName"]}';
	var assuranceDob = '${basicDetailsMap["assuranceDob"]}';
	var assuredRelationValue = '${basicDetailsMap["assuredRelation"]}';
}
var selectedRidersIds = {};
try{
	selectedRidersIds = JSON.parse('${productCustomizeSummaryData["productDefaultValueData"].selectedRidersIds}');
}catch(e){
	console.error(e);
}
$(document).ready(function () {
	var config = new Object({}),
	namespace = '<portlet:namespace />',
	customizeFormJEl = "#" + namespace + "customizeForm",
	productCustomizeConfigJsonJEl = productJsonConfiguration,

	investmentAmountEl = namespace + "investmentAmount",
	policyOptionEl = namespace + "policyOption",
	incomePayoutTypeEl = namespace + "incomePayoutType",
	payoutIncreasingPercentageEl = namespace + "payoutIncreasingPercentage",
	paymentOptionEl = namespace + "paymentOption",
	sumAssuredMultipleEl = namespace + "sumAssuredMultiple",
	incomeDurationEl=namespace + "incomeDuration",
	incomePayoutTimeEl=namespace + "incomePayoutTime",
	incomeStartPointEl=namespace + "incomeStartPoint",
	policyForEl = namespace + "payFor",
	isEdelweissEmployeeEl= namespace + "isEdelweissEmployee",
	cashBackEl= namespace + "cashBack",
	cashBackPercentageEl= namespace + "cashBackPercentage",
	investingForEl = namespace + "investingFor",
	policyTermEl = namespace + "policyTerm",
	policyTermSelectEl = namespace + "policyTermSelect",

	investmentAmountJEl = "#" + investmentAmountEl,
	policyOptionJEl = "#" + policyOptionEl,
	incomePayoutTypeJEl = "#" + incomePayoutTypeEl,
	payoutIncreasingPercentageJEl = "#" + payoutIncreasingPercentageEl,
	paymentOptionJEl = "#" + paymentOptionEl,
	sumAssuredMultipleJEl = "#" + sumAssuredMultipleEl,
	incomeDurationJEl= "#" + incomeDurationEl,
	incomePayoutTimeJEl= "#" + incomePayoutTimeEl,
	incomeStartPointJEl= "#" + incomeStartPointEl,
	policyForJEl = "#" + policyForEl,
	isEdelweissEmployeeJEl= "#" + isEdelweissEmployeeEl,
	cashBackJEl= "#" + cashBackEl,
	cashBackPercentageJEl= "#" + cashBackPercentageEl,
	investingForJEl = "#" + investingForEl,
	policyTermJEl = "#" + policyTermEl,
	policyTermSelectJEl = "#" + policyTermSelectEl,

	returnAmtValJEl = "#returnAmtVal",
	totalReturnsValueJEl = "#" + namespace + "totalReturnsValue",
	projectedReturnFundAllocation = "#" + namespace + "projectedReturnFundAllocation";
	fundDataTableJEl = "#fundDataTable";
	customizeFundLinkJEl = "#customizeFundLink",
	customizeFundTableJEl = "#customizeFundTable",
	errorMessageModalJEl = "#errorMessageModal",
	edelweissGenerateBIURL =  "${edelweissGenerateBIURL}",
	chartLinkJEl = "#" + namespace + "chartLink",
	fundTableLinkJEl = "#" + namespace + "fundTableLink",
	highchartContainerEl = "highchart-container"
	highchartContainerJEl = "#" + highchartContainerEl,
	fundDataTableJEl = "#fundDataTable",
	totalReturnsJEl = "#" + namespace + "totalReturns",
	illustrationURLJEl = "#" + namespace + "illustrationURL",
	quotationIdJEl = "#" + namespace + "quotationId",
	projectedReturnsDataJEl = "#" + namespace + "projectedReturnsData",
	fullNameJEl = "#" + namespace + "fullName",
	dateOfBirthJEl = "#" + namespace + "dateOfBirth",
	mobileNumberJEl = "#" + namespace + "mobileNumber",
	emailJEl = "#" + namespace + "email",
	assuranceFullNameJEl = "#" + namespace + "assuranceFullName",
	assuranceDobJEl = "#" + namespace + "assuranceDob",
	genderEl = namespace + "gender",
	genderJEl = "#" + genderEl,
	basicInvestingForEl = namespace + "basicInvestingFor",
	assuredRelationEl = namespace + "assuredRelation",
	proceedSubmitBtnJEl = "#" + namespace + "proceedBtn",
	loaderJEl = "#spinner-div",
	productCodeJEl = "#" + namespace + "productCode",
	familyModalJEl = "#" + namespace + "familyModal",
	investingForWrapperJEl = "#investingForWrapper",
	investingForGrandsonEl = namespace + "investingForGrandson",
	investingForGrandDaughterEl = namespace + "investingForGrandDaughter",
	familyRadioEl = namespace + "familyRadio",
	investingForFullNameEl = namespace + "investingForFullName",
	investingForDOBEl = namespace + "investingForDOB",
	investingForFullNameJEl = "#" + investingForFullNameEl,
	investingForDOBJEl = "#" + investingForDOBEl,
	investingForFullNameLableJEl = "#" + namespace + "investingForFullNameLable",
	investingForDOBLabelJEl = "#" + namespace + "investingForDOBLabel",
	saveFamilyDetailsURL = "${saveFamilyDetailsURL}",
	saveFamilyBtnJEl = "#" + namespace + "saveFamilyBtn",
	familyDetailsFormJEl = "#" + namespace + "familyDetailsForm",
	doNotaddOnJEl = "#" + namespace + "doNotaddOn",
	littleChampModalJEl = "#" + namespace + "littleChampModal",
	updateLMSLeadURL = "${updateLMSLeadURL}",
	productNameJEl = "#" + namespace + "productName",
	investmentObjectiveEl = namespace + "investmentObjective",
	deleteFamilyDetailsURL = "${deleteFamilyDetailsURL}",
	addLittleChampBenefitJEl = "#" + namespace + "addLittleChampBenefit",
	isLittleChampBenefitOptedJEl = "#" + namespace + "isLittleChampBenefitOpted",
	familyModalCloseJEl = "#" + namespace + "familyModalClose",
	errorMessageContainerJEl = "#errorMessageContainer",
	addLittleChampBenefitURL = "${addLittleChampBenefitURL}",
	saveFundDetailsURL = "${saveFundDetailsURL}",
	saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}";
	config.namespace = namespace;
	
	config.investmentAmountEl = investmentAmountEl;
	config.policyOptionEl = policyOptionEl;
	config.incomePayoutTypeEl = incomePayoutTypeEl;
	config.payoutIncreasingPercentageEl=payoutIncreasingPercentageEl;
	config.paymentOptionEl=paymentOptionEl;
	config.sumAssuredMultipleEl=sumAssuredMultipleEl;
	config.incomeDurationEl=incomeDurationEl;
	config.incomePayoutTimeEl = incomePayoutTimeEl;
	config.incomeStartPointEl = incomeStartPointEl;
	config.policyForEl = policyForEl;
	config.isEdelweissEmployeeEl = isEdelweissEmployeeEl;
	config.cashBackEl = cashBackEl;
	config.cashBackPercentageEl = cashBackPercentageEl;
	config.investingForEl = investingForEl;
	config.policyTermEl = policyTermEl;
	config.policyTermSelectEl = policyTermSelectEl;
	
	config.productCustomizeConfigJsonJEl = productCustomizeConfigJsonJEl;
	config.investmentAmountJEl = investmentAmountJEl;
	config.policyOptionJEl = policyOptionJEl;
	config.incomePayoutTypeJEl = incomePayoutTypeJEl;//not in script
	config.payoutIncreasingPercentageJEl = payoutIncreasingPercentageJEl;//not in script
	config.paymentOptionJEl = paymentOptionJEl;
	config.sumAssuredMultipleJEl = sumAssuredMultipleJEl;//not in script
	config.incomeDurationJEl = incomeDurationJEl;
	config.incomePayoutTimeJEl = incomePayoutTimeJEl;//not in script
	config.incomeStartPointJEl = incomeStartPointJEl;
	config.policyForJEl = policyForJEl;
	config.isEdelweissEmployeeJEl = isEdelweissEmployeeJEl;
	config.cashBackJEl = cashBackJEl;//not in script
	config.cashBackPercentageJEl = cashBackPercentageJEl;//not in script
	config.investingForJEl = investingForJEl;
	config.policyTermJEl = policyTermJEl;
	config.policyTermSelectJEl = policyTermSelectJEl;

	config.customizeFormJEl = customizeFormJEl;

	config.illustrationURLJEl = illustrationURLJEl;
	config.quotationIdJEl = quotationIdJEl;
	config.saveCustomerInvestmentDataURL = saveCustomerInvestmentDataURL;

	config.errorMessageModalJEl = errorMessageModalJEl,

	/* config.returnAmtValJEl = returnAmtValJEl;
	config.totalReturnsValueJEl = totalReturnsValueJEl;
	config.projectedReturnFundAllocation = projectedReturnFundAllocation;
	config.fundDataTableJEl = fundDataTableJEl;
	config.customizeFundLinkJEl = customizeFundLinkJEl;
	config.customizeFundTableJEl = customizeFundTableJEl;
	config.chartLinkJEl = chartLinkJEl;
	config.fundTableLinkJEl = fundTableLinkJEl;
	config.highchartContainerEl = highchartContainerEl;
	config.highchartContainerJEl = highchartContainerJEl;
	config.fundDataTableJEl = fundDataTableJEl;
	config.totalReturnsJEl = totalReturnsJEl;
	config.projectedReturnsDataJEl = projectedReturnsDataJEl; */

	config.gisRelFieldValues = JSON.parse('${gisPayForISPRelItems}');
	config.defaultPolicyTerm = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyTerm()}";
	config.defaultPolicyFor = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyFor()}";
	config.productInvestmentAmountRelsData = '${productInvestmentAmountRelsData}';
	config.productMetaData = JSON.parse('<%=JSONFactoryUtil.createJSONObject(productMetaData)%>'); 

	config.edelweissGenerateBIURL = edelweissGenerateBIURL;

	config.fullNameJEl = fullNameJEl;
	config.dateOfBirthJEl = dateOfBirthJEl;
	config.emailJEl = emailJEl;
	config.assuranceFullNameJEl = assuranceFullNameJEl;
	config.assuranceDobJEl = assuranceDobJEl;
	config.genderJEl = genderJEl;
	config.genderEl = genderEl;
	config.mobileNumberJEl = mobileNumberJEl;
	config.basicInvestingForEl = basicInvestingForEl;
	config.assuredRelationEl = assuredRelationEl;
	config.proceedSubmitBtnJEl = proceedSubmitBtnJEl;
	config.loaderJEl = loaderJEl;
	config.productCodeJEl = productCodeJEl;
	config.familyModalJEl = familyModalJEl;
	config.investingForWrapperJEl = investingForWrapperJEl;
	config.investingForGrandsonEl = investingForGrandsonEl;
	config.investingForGrandDaughterEl = investingForGrandDaughterEl;
	config.familyRadioEl = familyRadioEl;

	config.investingForFullNameEl = investingForFullNameEl;
	config.investingForDOBEl = investingForDOBEl;

	config.investingForFullNameJEl = investingForFullNameJEl;
	config.investingForDOBJEl = investingForDOBJEl;
	config.investingForFullNameLableJEl = investingForFullNameLableJEl;
	config.investingForDOBLabelJEl = investingForDOBLabelJEl;
	config.saveFamilyDetailsURL = saveFamilyDetailsURL;
	config.saveFamilyBtnJEl = saveFamilyBtnJEl;
	config.familyDetailsFormJEl = familyDetailsFormJEl;
	config.doNotaddOnJEl = doNotaddOnJEl;
	config.littleChampModalJEl = littleChampModalJEl;
	config.investmentObjectiveEl = investmentObjectiveEl;

	config.investingForVal = "${productCustomizeSummaryData['productDefaultValueData'].investingFor.name}";
	config.cookieLeadId = "${cookieLeadId}";
	config.assuredRelationVal = "${basicDetailsMap['assuredRelation']}";
	config.updateLMSLeadURL = updateLMSLeadURL;
	config.productNameJEl = productNameJEl;
	config.deleteFamilyDetailsURL = deleteFamilyDetailsURL;
	config.addLittleChampBenefitJEl = addLittleChampBenefitJEl;
	config.isLittleChampBenefitOptedJEl = isLittleChampBenefitOptedJEl;
	config.isLittleChampBenefitOpted = "${basicDetailsMap['isLittleChampBenefitOpted']}";
	config.familyModalCloseJEl = familyModalCloseJEl;
	config.errorMessageContainerJEl = errorMessageContainerJEl;
	config.addLittleChampBenefitURL = addLittleChampBenefitURL;
	config.saveFundDetailsURL = saveFundDetailsURL;
	config.LANG_MESSAGES = LANG_MESSAGES;
	
	edelweissProductCustomizePortlet.viewProductCustomize(config);
	 
});

</script>
<script src="<%=request.getContextPath()%>/product_jsps/guaranteed-flexi-star/js/gfs-main.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>""></script>
<script src="<%=request.getContextPath()%>/product_jsps/guaranteed-flexi-star/js/gfs-basic-details.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>""></script>
<script type="text/javascript">
	var productName = "${productMetaData['productName']}";
</script>
<%@ include file="/webengage-customize.jsp"%>