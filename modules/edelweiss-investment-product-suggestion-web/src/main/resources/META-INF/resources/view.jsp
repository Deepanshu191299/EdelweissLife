<%@ include file="/init.jsp" %>

<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<portlet:resourceURL var="saveIPSBasicDetailsURL" id="/save_ips/basicDetails"></portlet:resourceURL>

<portlet:resourceURL var="getBasicDetailsURL" id="/get/ipsBasicDetails"></portlet:resourceURL>

<portlet:resourceURL var="saveFamilyDetailsURL" id="/save/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="deleteFamilyDetailsURL" id="/delete/familyDetails"></portlet:resourceURL>

<portlet:resourceURL var="saveCustomerInvestmentDataURL" id="/save/customerInvestmentData"></portlet:resourceURL>

<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>

<portlet:resourceURL var="productListURL" id="/edelweiss-ips/product-list"></portlet:resourceURL>

<portlet:resourceURL var="productModificationURL" id="/edelweiss-ips/product-modification"></portlet:resourceURL>

<div id="preloader"></div>

<%@ include file="/edit_basic_details.jsp" %>

<div class="edelweissTokioDetails p-2">
	<div class="container">
		<div class="edelweissTokioDetailsInner d-flex align-items-center">
			<div class="edelweissTokioHeading">
				<h5 class="fontbold">Your Details</h5>
			</div>
			<div class="edelweissTokioFormDetails d-none d-md-block">
				<ul class="nav" id="personal-details">
					<li class="nav-item">${basicDetailsMap['fullName']}</li>
					<c:if test="${not empty basicDetailsMap['gender']}">
					    <li class="nav-item">${basicDetailsMap['gender']}</li>
					</c:if>					
					<li class="nav-item">${basicDetailsMap['dateOfBirth']}</li>
					<li class="nav-item">${basicDetailsMap['mobileNumber']}</li>
					<c:if test="${not empty basicDetailsMap['email']}">
					    <li class="nav-item">${basicDetailsMap['email']}</li>
					</c:if>					
					<li class="nav-item">${picklistOptionValues[basicDetailsMap['investmentObjective']]}</li>
					<li class="nav-item">${riskAppetitePickListData[summaryData.riskAppetite]}</li>
					<c:if test="${basicDetailsMap['basicInvestingFor'] == 'Family' }">
					<c:if test="${not empty basicDetailsMap['assuranceFullName']}">
					    <li class="nav-item">${basicDetailsMap['assuranceFullName']}</li>
					</c:if>											
						<li class="nav-item">${basicDetailsMap['assuranceDob']}</li>
					</c:if>
				</ul>
			</div>
			<div class="editModel ml-auto">
					<span name="editBasicDetails" id="editBasicDetails"> <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22"
						viewBox="0 0 22 22">
                                <path fill="#999" fill-rule="nonzero"
							stroke="#999" stroke-width=".3"
							d="M19.705 2.295a4.41 4.41 0 0 0-6.243 0L2.016 13.738a.573.573 0 0 0-.162.328l-.848 6.282a.57.57 0 0 0 .162.482c.106.106.255.17.404.17.026 0 .051 0 .077-.004l6.225-.825c.315-.042.474-.332.432-.647a.576.576 0 0 0-.648-.495l-5.412.722.592-4.381 4.61 4.611c.107.107.194.17.343.17.15 0 .36-.059.467-.17L19.705 8.538A4.388 4.388 0 0 0 21 5.414a4.37 4.37 0 0 0-1.295-3.12zm-6.147 1.22l1.922 1.921L5.035 15.882 3.113 13.96 13.558 3.514zm-5.825 15.06l-1.88-1.879L16.298 6.251l1.88 1.879L7.732 18.576zM18.979 7.308L14.38 2.71a3.255 3.255 0 0 1 2.08-.746c.874 0 1.692.341 2.31.955.618.613.954 1.436.954 2.31 0 .771-.264 1.496-.745 2.08z"></path>
                            </svg>
                            </span>
			</div>
		 </div>
		</div>
	</div>

<c:choose>
	<c:when test="${isProductSelected}">
		<form name="<portlet:namespace />customizeForm" id="<portlet:namespace />customizeForm" method="post" action="">

			<input type="hidden" class="form-control" id="<portlet:namespace />productCode" name="<portlet:namespace />productCode" value="${productMetaData['productCode']}">
			<input type="hidden" class="form-control" id="<portlet:namespace />productName" name="<portlet:namespace />productName" value="${productMetaData['productName']}">
			<input type="hidden" id="portletNamespace" name="portletNamespace" value="<portlet:namespace/>" />
			<input type="hidden" class="form-control" id="<portlet:namespace />customerEnquiryId" name="<portlet:namespace />customerEnquiryId" value="${customerEnquiryId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerInvestmentDetailsId" name="<portlet:namespace />customerInvestmentDetailsId" value="${customerInvestmentDetailsId}">
			<input type="hidden" class="form-control" id="<portlet:namespace />customerFamilyDetailsId" name="<portlet:namespace />customerFamilyDetailsId" value="${customerFamilyDetailsId}" /> 
			
			<input type="hidden" class="form-control" id="<portlet:namespace />savedPolicyOption" name="<portlet:namespace />savedPolicyOption" value="${productIPSData['productDefaultValueData'].policyOption.key}" />
			
			<input type="hidden" class="form-control" id="<portlet:namespace />annualIncome" name="<portlet:namespace />annualIncome" value="${summaryData.annualIncome}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />riskAppetite" name="<portlet:namespace />riskAppetite" value="${summaryData.riskAppetite}" />
			
			<input type="hidden" class="form-control" id="<portlet:namespace />productVId" name="<portlet:namespace />productVId" value="${productIPSData['productMetaDataRelsData'].vId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />productCPId" name="<portlet:namespace />productCPId" value="${productIPSData['productMetaDataRelsData'].cpId}" />
			<input type="hidden" class="form-control" id="<portlet:namespace />productCId" name="<portlet:namespace />productCId" value="${productIPSData['productMetaDataRelsData'].cId}" />
			
			<input type="hidden" class="form-control" id="<portlet:namespace />isInvestmentAmontUpdated" name="<portlet:namespace />isInvestmentAmontUpdated" value="${isInvestmentAmontUpdated}" />
			
			<div class="edelweissForm">
				<div class="container">
					<div class="edelweissFormInner d-flex">
						<div id="edelweissTokioForm1" class="edelweissTokioForm w-100">
							<div class="row owl-carousel owl-theme">
								<c:if test="${productIPSData['productDefaultValueData'].isInvestmentAmountApplicable()}">
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
												<input type="text" class="form-control border-0  pl-0" id="<portlet:namespace />investmentAmount" name="<portlet:namespace />investmentAmount" value="${productIPSData['productDefaultValueData'].getInvestmentAmount()}">
											</div>
											<div class="amt-words" id="amt-in-word"><liferay-ui:message key="rupee-sign"/></div>
											
										</div>
									</div>
								</c:if>

								

								<c:if test="${productIPSData['productDefaultValueData'].isInvestingForApplicable()}">
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
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curInvestingFor" items="${productIPSData['investingForData']}">
																<option value="${curInvestingFor.name}" ${productIPSData['productDefaultValueData'].investingFor.name == curInvestingFor.name ? 'selected' : ''}>${curInvestingFor.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productIPSData['productDefaultValueData'].isPolicyTermApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />policyTerm" class="pform-title mb-0"><liferay-ui:message key="policy-term" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="duration-of-your-policy" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />policyTerm" name="<portlet:namespace />policyTerm">
													<option value=""><liferay-ui:message key="select" /></option>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productIPSData['productDefaultValueData'].isPolicyForApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />payFor" class="pform-title mb-0"><liferay-ui:message key="pay-for" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="no-of-years-you-will-pay-the-premium" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />payFor" name="<portlet:namespace />payFor">
													<option value=""><liferay-ui:message key="select" /></option>
												</select>
											</div>
										</div>
									</div>
								</c:if>

								<c:if test="${productIPSData['productDefaultValueData'].isPaymentOptionApplicable()}">
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />paymentOption" class="pform-title mb-0"><liferay-ui:message key="payment" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="your-payment-frequency" />
												</p>
											</div>
											<div class="select-container ">
												<select class="form-control" id="<portlet:namespace />paymentOption" name="<portlet:namespace />paymentOption">
													<option value=""><liferay-ui:message key="select" /></option>
													<c:forEach var="curPaymentOption" items="${productIPSData['paymentOptionsData']}">
														<option data-name="${curPaymentOption.name}" value="${curPaymentOption.key}" ${productIPSData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}>${curPaymentOption.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
								</c:if>
									<div class="item">
										<div class="labelInputGroup">
											<div class="labelGroup">
												<label for="<portlet:namespace />totalReturnsValue" class="pform-title mb-0"><liferay-ui:message key="returns" /></label>
												<p class="premiuim-small-text">
													<liferay-ui:message key="fund-returns" />
												</p>
											</div>
											<div class="select-container">
												<select class="form-control" name="<portlet:namespace />totalReturnsValue" id="<portlet:namespace />totalReturnsValue">
													<option value="4"><liferay-ui:message key="four-percent" /></option>
													<option value="8" selected><liferay-ui:message key="eight-percent" /></option>
												</select>
											</div>
										</div>
									</div>
							</div>
						</div>
					</div>
				</div>
 			</div>
		</form>

		<%@ include file="/family-details.jsp"%>
		
	</c:when>
	<c:otherwise>
		<div class="alert alert-info">
			<liferay-ui:message key="please-select-product-name-from-page-setting" />
		</div>
	</c:otherwise>
</c:choose>


<div class="modal wealth-modal" tabindex="-1" role="dialog" id="errorMessageModal" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">			
			<div class="modal-body">
			<div class="modal-header">
							<h2 class="fs22 fontbold  w-100">
					<liferay-ui:message key="error" />
				</h2>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div id="errorMessageContainer"><liferay-ui:message key="service-unavailable-error-msg" /></div>
			<div class="modal-footer">
				<button type="button" class="edto-btn-primary" data-dismiss="modal">
					<liferay-ui:message key="ok" />
				</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
var saveIPSBasicDetailsURL = "${saveIPSBasicDetailsURL}"; 
var getBasicDetailsURL = "${getBasicDetailsURL}";

var basicInvestingFor = '${basicDetailsMap["basicInvestingFor"]}';
if(basicInvestingFor == 'Family'){
	var assuranceFullName = '${basicDetailsMap["assuranceFullName"]}';
	var assuranceDob = '${basicDetailsMap["assuranceDob"]}';
	var assuredRelationValue = '${basicDetailsMap["assuredRelation"]}';
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
LANG_MESSAGES["age-between-18-to-60-years"] = "<liferay-ui:message key='age-between-18-to-60-years' />";
LANG_MESSAGES["age-between-0-to-15-years"] = "<liferay-ui:message key='age-between-0-to-15-years' />";


$(document).ready(function () {
	var config = new Object({}),
		 namespace = '<portlet:namespace />',
		 saveFamilyDetailsURL = "${saveFamilyDetailsURL}",
		 updateLMSLeadURL = "${updateLMSLeadURL}",
		 deleteFamilyDetailsURL = "${deleteFamilyDetailsURL}",
		 saveCustomerInvestmentDataURL = "${saveCustomerInvestmentDataURL}",
		 loadFamilyDetailsURL = "${loadFamilyDetailsURL}",
		 productListURL = "${productListURL}";
		 productModificationURL = "${productModificationURL}";
		 
		 config.namespace = namespace;
		 
		 config.ipsPolicyTermPicklistData = '${ipsPolicyTermPicklistData}';
		 config.ipsPayForPicklistData = '${ipsPayForPicklistData}';
		 
		 config.defaultPolicyTerm = "${productIPSData['productDefaultValueData'].getPolicyTerm()}";
		 config.defaultPayFor = "${productIPSData['productDefaultValueData'].getPolicyFor()}";
		 config.productInvestmentAmountRelsData = '${productInvestmentAmountRelsData}';
		 
		 config.investingForVal = "${productIPSData['productDefaultValueData'].investingFor.name}";
		 config.cookieLeadId = "${cookieLeadId}";
		 
		 config.saveFamilyDetailsURL = saveFamilyDetailsURL;
		 config.updateLMSLeadURL = updateLMSLeadURL;
		 config.deleteFamilyDetailsURL = deleteFamilyDetailsURL;
		 config.saveCustomerInvestmentDataURL = saveCustomerInvestmentDataURL;
		 config.loadFamilyDetailsURL = loadFamilyDetailsURL;
		 config.productListURL = productListURL;
		 config.productModificationURL = productModificationURL;
		 config.LANG_MESSAGES = LANG_MESSAGES;
	 
		 edelweissIPSPortlet.viewIPSCustomize(config);
});
</script>
