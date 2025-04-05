<%@page import="in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.log.LogFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.log.Log"%>
<%@ include file="/init.jsp"%>
<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.petra.string.StringPool"%>


<portlet:resourceURL var="edelweissGenerateBIURL" id="/buyJourney/generate-bi"></portlet:resourceURL>
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

/* Log log = LogFactoryUtil.getLog("/edelweiss-product-customize-web/src/main/resources/META-INF/resources/product_jsps/zindagi-protect/customize_form.jsp"); */

Map<String, String> productMetaData = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("productMetaData"))){
	productMetaData = (Map<String, String>)renderRequest.getAttribute("productMetaData");
}

Map<String, String> basicDetailsMap = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("basicDetailsMap")))
{
	basicDetailsMap = (Map<String, String>)renderRequest.getAttribute("basicDetailsMap");
}

JSONObject familyDetailsResponseJson = null;
if(Validator.isNotNull(renderRequest.getAttribute("familyDetailsResponseJson")))
{
	familyDetailsResponseJson = (JSONObject)renderRequest.getAttribute("familyDetailsResponseJson");
}

Map<String, String> customerEnquiryMap = new HashMap();
if(Validator.isNotNull(renderRequest.getAttribute("customerEnquiryMap")))
{
	customerEnquiryMap = (Map<String, String>)renderRequest.getAttribute("customerEnquiryMap");
}

JSONObject productEligibilityRelJson = null;
if(Validator.isNotNull(renderRequest.getAttribute("productEligibilityRelJson")))
{
	productEligibilityRelJson = (JSONObject)renderRequest.getAttribute("productEligibilityRelJson");
}
%>

<script>
var productMetaData = {}; 
try{
	productMetaData = JSON.parse('<%=JSONFactoryUtil.createJSONObject(productMetaData)%>');
}catch(e){
	console.error("Unable to Parse productMetaData JSON");
}
</script>
<script>

	var LANG_MESSAGES = {};
	LANG_MESSAGES["graph-label-booster-premium"] = "<liferay-ui:message key='graph-label-booster-premium' />";
	LANG_MESSAGES["graph-label-addition"] = "<liferay-ui:message key='graph-label-addition' />";
	LANG_MESSAGES["graph-label-funds"] = "<liferay-ui:message key='graph-label-funds' />";
	LANG_MESSAGES["benchmark"] = "<liferay-ui:message key='benchmark' />";
	LANG_MESSAGES["sfin-label"] = "<liferay-ui:message key='sfin-label' />";
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
	LANG_MESSAGES["your-childs-full-details"] = "<liferay-ui:message key='your-childs-full-details' />";
	LANG_MESSAGES["your-spouses-full-details"] = "<liferay-ui:message key='your-spouses-full-details' />";
	LANG_MESSAGES["enter-your-spouse-name"] = "<liferay-ui:message key='enter-your-spouse-name' />";
	LANG_MESSAGES["enter-your-childs-name"] = "<liferay-ui:message key='enter-your-childs-name' />";
	LANG_MESSAGES["your-spouses-date-of-birth"] = "<liferay-ui:message key='your-spouses-date-of-birth' />";
	LANG_MESSAGES["your-childs-date-of-birth"] = "<liferay-ui:message key='your-childs-date-of-birth' />";
	
	LANG_MESSAGES["please-enter-the-annual-income"] = "<liferay-ui:message key='please-enter-the-annual-income' />";
	LANG_MESSAGES["please-select-the-occupation"] = "<liferay-ui:message key='please-select-the-occupation' />";
	LANG_MESSAGES["please-select-the-education"] = "<liferay-ui:message key='please-select-the-education' />";


	LANG_MESSAGES["enter-your-date-of-birth"] = "<liferay-ui:message key='enter-your-date-of-birth' />";
    LANG_MESSAGES["spouse-dob-within-10-of-your-age"] = "<liferay-ui:message key='spouse-dob-within-10-of-your-age' />";
	LANG_MESSAGES["age-should-be-between-18-to-65"] = "<liferay-ui:message key='age-should-be-between-18-to-65' />";
	LANG_MESSAGES["please-enter-your-spouse-name"] = "<liferay-ui:message key='please-enter-your-spouse-name' />";
	LANG_MESSAGES["please-enter-your-full-name"] = "<liferay-ui:message key='please-enter-your-full-name' />";
	LANG_MESSAGES["please-enter-spouse-dob"] = "<liferay-ui:message key='please-enter-spouse-dob' />";
	LANG_MESSAGES["enter-valid-date"] = "<liferay-ui:message key='enter-valid-date' />";
	LANG_MESSAGES["spouse-age-should-be-between-18-to-65"] = "<liferay-ui:message key='spouse-age-should-be-between-18-to-65' />";
	LANG_MESSAGES["please-enter-child-dob"] = "<liferay-ui:message key='please-enter-child-dob' />";
	LANG_MESSAGES["child-age-should-be-between-0-to-18"] = "<liferay-ui:message key='child-age-should-be-between-0-to-18' />";
	LANG_MESSAGES["select-your-annual-income-range"] = "<liferay-ui:message key='select-your-annual-income-range' />";
    LANG_MESSAGES["please-enter-valid-mobile-number"] = "<liferay-ui:message key='please-enter-valid-mobile-number' />";
	LANG_MESSAGES["enter-a-valid-email-address"] = "<liferay-ui:message key='enter-a-valid-email-address' />";


	
	
	var namespace="<portlet:namespace/>";
	var assuranceDobJEl = "#" + namespace + "assuranceDob",
	assuranceFullNameJEl = "#" + namespace + "assuranceFullName",
	assuredRelationEl = namespace + "assuredRelation",
	basicInvestingForEl = namespace + "basicInvestingFor",
	dateOfBirthJEl = "#" + namespace + "dateOfBirth",
	emailJEl = "#" + namespace + "email",
	familyDetailsFormJEl = "#" + namespace + "familyDetailsForm",
	familyModalJEl = "#" + namespace + "familyModal",
	familyModalCloseJEl = "#" + namespace + "familyModalClose",
	familyRadioEl = namespace + "familyRadio",
	fullNameJEl = "#" + namespace + "fullName",
	genderEl = namespace + "gender",
	investingForDOBEl = namespace + "investingForDOB",
	investingForDOBJEl = "#" + investingForDOBEl,
	investingForFullNameEl = namespace + "investingForFullName",
	investingForFullNameJEl = "#" + investingForFullNameEl,
	investingForFullNameLableJEl = "#" + namespace + "investingForFullNameLable",
	investingForGrandDaughterEl = namespace + "investingForGrandDaughter",
	investingForGrandsonEl = namespace + "investingForGrandson",
	investingForJEl = "#" + namespace + "investingFor",
	investingForDOBLabelJEl = "#" + namespace + "investingForDOBLabel",
	investingForWrapperJEl = "#investingForWrapper",
	investmentObjectiveEl = namespace + "investmentObjective",
	mobileNumberJEl = "#" + namespace + "mobileNumber",
	personalDetailsJEl = "#personal-details",
	paymentOptionEl = namespace + "paymentOption",
	paymentOptionJEl = "#" + paymentOptionEl,
	deleteFamilyDetailsURL = "${deleteFamilyDetailsURL}",
	customerFamilyDetailsIdJEl = "#" + namespace + "customerFamilyDetailsId",
	customerInvestmentDetailsIdJEl = "#" + namespace + "customerInvestmentDetailsId",
	assuredRelationVal = "${basicDetailsMap['assuredRelation']}",
	investingForFullNameErrMsg = Liferay.Language.get("enter-your-x-full-name"),
	investingForDOBErrMsg = Liferay.Language.get("enter-your-x-date-of-birth"),
	minorAgeErrMsg = Liferay.Language.get("age-between-0-to-17-years"),
	isCustomerNri = "${basicDetailsMap['isNRI']}",
	dateOfBirthEl = namespace + "dateOfBirth",
	dateOfBirthJEl = "#" + dateOfBirthEl,
	genderEl = namespace + "gender",
	emailEl = namespace + "email",
	emailJEl = "#" + emailEl,
	mobileNumberEl = namespace + "mobileNumber",
	mobileNumberJEl = "#" + mobileNumberEl,
	basicInvestingForEl = namespace + "basicInvestingFor",
	fullNameEl = namespace + "fullName",
	fullNameJEl = "#" + fullNameEl,
	assuredRelationEl = namespace + "assuredRelation",
	assuranceFullNameJEl = "#" + namespace + "assuranceFullName",
	assuranceDobJEl = "#" + namespace + "assuranceDob",
	paymentOptionJEl = "#" + paymentOptionEl,
	policyOptionEl = namespace + "policyOption",
	policyOptionJEl = "#" + policyOptionEl,
	investingForFullNameJEl = "#" + investingForFullNameEl,
	investingForDOBJEl = "#" + investingForDOBEl,
	investmentObjectiveEl = namespace + "investmentObjective",
	totalReturnsJEl = "#" + namespace + "totalReturns",
	productCodeJEl = "#" + namespace + "productCode",
	quotationIdJEl = "#" + namespace + "quotationId",
	anualPrem2JE1 = "#" + namespace + "annualPrem2",
	illustrationURLJEl = "#" + namespace + "illustrationURL",
	investmentAmountEl = namespace + "investmentAmount",
	investmentAmountJEl =  "#" + investmentAmountEl, 
	policyTermEl = namespace + "custPolicyTerm",
	policyTermJEl = "#" + policyTermEl,
	policyForEl = namespace + "payFor",
	policyForJEl = "#" + policyForEl,
	planOptionEl = namespace + "planOption",
	planOptionJEl = "#" + planOptionEl,
	selectedRiderJEl = "#"+namespace+"selectedRider_",
	riderPremiumAmountJEl = "#"+namespace+"riderPremiumAmount_",
	incomeTypeEl = namespace + "payoutOption",
	incomeTypeJEl = "#" + incomeTypeEl,
	incomePeriodEl = namespace + "incomePeriod",
	incomePeriodJEl = "#" + incomePeriodEl,
	incomePercentageEl = namespace + "incomePercentage",
	incomePercentageJEl = "#" + incomePercentageEl,
	premiumPayDetailsEl = namespace+"premiumPayDetails",
	premiumPayDetailsJEl = "#" + premiumPayDetailsEl,
	verifyEligibilityBtnJEl = "#" + "verifyEligibilityBtn",
	childBenefitSelectJEl = "#" + namespace + "childFutureProtectBenefit";
	
	productNameJEl = "#" + namespace + "productName";
	lifeCoverJEl = "#" + namespace + "lifeCover";
	premiumBreakBenefitJEl = '#'+namespace+'premiumBreakBenefit';
	eligibilityOkBtnJEl = "#"+"eligibilityOkBtn";
	eligibilityNotOkBtnJEl = "#"+"eligibilityNotOkBtn";
	smokeJE = portletNamespace + "smoke";
    maritalStatusJE = portletNamespace + "MaritalStatusId";
    spouseNameJE = portletNamespace + "SpouseName";
    spouseDobJE = portletNamespace + "SpouseDob";
    spouseSmokeJE = portletNamespace + "SpouseSmoke";
    childJE = portletNamespace + "ChildName";
    childDobJE = portletNamespace + "ChildDob";
    annualIncomeRangeJE = portletNamespace + "Income";
    occupationJE = portletNamespace + "occupation";
    educationJE = portletNamespace + "educationQualification";

	var grandSonLabel = LANG_MESSAGES["grand-son"],
	grandDaughterLabel = LANG_MESSAGES["grand-daughter"],
	sonsFullNameLabel = LANG_MESSAGES["sons-full-name"],
	sonsDobLabel = LANG_MESSAGES["sons-dob"],
	daughtersFullNameLabel = LANG_MESSAGES["daughters-full-name"],
	daughtersDobLabel = LANG_MESSAGES["daughters-dob"],
	grandsonsFullNameLabel = LANG_MESSAGES["grandsons-full-name"],
	grandsonsDobLabel = LANG_MESSAGES["grandsons-dob"],
	grandDaughterFullNameLabel = LANG_MESSAGES["granddaughter-full-name"],
	grandDaughterDobLabel = LANG_MESSAGES["granddaughter-dob"],
	rupeeSignLabel = LANG_MESSAGES["rupee-sign"],
	enterYourXDateOfBirthErrorMsg = LANG_MESSAGES["enter-your-x-date-of-birth"],
	ageBetween18to60ErrorMsg = LANG_MESSAGES["age-between-18-to-60-years"],
	yourSpousesFullFetailsHearder = LANG_MESSAGES["your-spouses-full-details"],
	ageBetween0to15ErrorMsg = LANG_MESSAGES["age-between-0-to-15-years"],
	yourChildsFullFetailsHearder = LANG_MESSAGES["your-childs-full-details"],
	enterYourChildsNameLabel = LANG_MESSAGES["enter-your-childs-name"],
	yourChildsDOBLabel = LANG_MESSAGES["your-childs-date-of-birth"],
	ageBetween0to17ErrorMsg = LANG_MESSAGES["age-between-0-to-17-years"],
	enterYourXFullNameErrorMsg = LANG_MESSAGES["enter-your-x-full-name"];
	var customerDob="${basicDetailsMap['dateOfBirth']}";
	
	var updateLMSLeadURL = "${updateLMSLeadURL}";
	var getBasicDetailsURL = "${getBasicDetailsURL}";
	var saveBasicDetailsURL = "${saveBasicDetailsURL}";

	var basicDetailsMap = JSON.parse('<%=JSONFactoryUtil.createJSONObject(basicDetailsMap)%>');
	
	var customerEnquiryMap = '<%=customerEnquiryMap%>';
	
	var familyDetailsResponseJson = '<%=familyDetailsResponseJson%>';
	
	var productEligibilityRelJson = '<%=productEligibilityRelJson%>';
	
	defaultPolicyFor = "${productCustomizeSummaryData['productDefaultValueData'].getPolicyFor()}";
	productPolicyForRel = JSON.parse('${productCustomizeSummaryData["productPolicyForRel"]}');

</script>

<!-- Loader -->
<div id='<portlet:namespace/>tracker-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<!-- Customer Basic Details -->
<c:if test="${not empty basicDetailsMap }" >
	<%@ include file="/product_jsps/zindagi-protect/basic_details.jsp"%>
	<%@ include file="/product_jsps/zindagi-protect/edit_basic_details_modal.jsp"%>
</c:if>

<!-- Product Details -->
<c:choose>
	<c:when test="${isProductSelected}">
			 <%@ include file="/product_jsps/zindagi-protect/customize_form_details.jsp"%>
			 <%@ include file="/product_jsps/zindagi-protect/product_riders.jsp"%>
			 <%@ include file="/product_jsps/zindagi-protect/verify_customer_eligibility_modal.jsp"%>
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
			            <td>Age Attained</td>
			            <td>Policy Year</td>
			            <td>Guaranteed Income</td>
			            <td>Cash Bonus</td>
			            <td>Maturity Value (INR)</td>
			        </tr>
			        </thead>
			        <tbody class="scrl-tbl-hgt m-cust-scroll maturity-data-table" id="planMaturityBenefitsBreakupTableBody">
			        </tbody>
				</table>			
              </div>
		</div>
		</div>
	</div>
</div>

<script src="<%=request.getContextPath()%>/product_jsps/zindagi-protect/js/zp-main.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/product_jsps/zindagi-protect/js/zp-riders.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/product_jsps/zindagi-protect/js/zp-basic-details.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>" type="text/javascript"></script>
<%@ include file="/webengage-customize.jsp"%>
<style>
.full-div{
		display: inline-block !important;width: 100%;
}
.break-proceed-group {
    display: flex;
    margin-top: 10px;
    justify-content: space-between;
    align-items: self-end;
}
.break-proceed-group >div {
   flex:1;
}
.discount-title div {
    font-family: 'Montserrat-Medium';
    font-size: 13px;
    line-height: 15px;
    color: #124093;
    font-weight: 600;
}
.break-proceed-group button ,.break-proceed-group button span {
    font-size: 14px;
    display: flex;
    color: #8e8e8e;
    font-weight: 600;
    cursor: default;
}
.break-proceed-group button::focus-visible , .break-proceed-group button:active:focus{
outline:unset !important;
box-shadow:unset !important;
border:0px !important;
}
.btn:active:focus, .btn-primary:active:focus {
    box-shadow:unset !important;
}
.edelweissTokioTotalReturns .download-brochure .premium-form-header .title {
    display: block;
    font-family: Montserrat-SemiBold;
    font-size: 15px;
    color: #fff;
    line-height: 18px;
    padding: 3px 15px;
    text-transform: capitalize;
    background: #124093;
}
</style>