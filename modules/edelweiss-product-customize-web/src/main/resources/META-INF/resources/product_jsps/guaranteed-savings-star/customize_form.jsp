<%@page import="in.edelweiss.product.customize.web.constants.EdelweissProductCustomizeConstants"%>
<%@page import="in.edelweiss.common.contants.ParameterConstants"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/init.jsp"%>
<portlet:resourceURL var="deleteFamilyDetailsURL" id="/delete/familyDetails"></portlet:resourceURL>
<portlet:resourceURL var="getBasicDetailsURL" id="/get/BasicDetails"></portlet:resourceURL>
<portlet:resourceURL var="saveBasicDetailsURL" id="/save/basicDetails"></portlet:resourceURL>
<portlet:resourceURL var="updateLMSLeadURL" id="/update/LMSLead"></portlet:resourceURL>
<%
	Map<String, String> productMetaData = new HashMap();
	if(Validator.isNotNull(renderRequest.getAttribute("productMetaData")))
	{
		productMetaData = (Map<String, String>)renderRequest.getAttribute("productMetaData");
	}
	
	Map<String, String> basicDetailsMap = new HashMap();
	if(Validator.isNotNull(renderRequest.getAttribute("basicDetailsMap")))
	{
		basicDetailsMap = (Map<String, String>)renderRequest.getAttribute("basicDetailsMap");
	}
%>
<script>
	var productMetaData = {}; 
	try{
		productMetaData = JSON.parse('<%=JSONFactoryUtil.createJSONObject(productMetaData)%>');
	}catch(e){
		console.error("Unable to Parse productMetaData JSON");
	}
	
	var productInvestmentAmountRel = {};
	try{
		productInvestmentAmountRel = JSON.parse('${productInvestmentAmountRel}');
	}catch(e){
		console.error("Unable to Parse productInvestmentAmountRel JSON");
	}
	
	var productPolicyForRel = {};
	try{
		productPolicyForRel = JSON.parse('${productPolicyForRel}');
	}catch(e){
		console.error("Unable to Parse productPolicyForRel JSON");
	}
	
	
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
	illustrationURLJEl = "#" + namespace + "illustrationURL",
	investmentAmountEl = namespace + "investmentAmount",
	investmentAmountJEl =  "#" + investmentAmountEl, 
	policyTermEl = namespace + "policyTerm",
	policyTermJEl = "#" + policyTermEl,
	policyForEl = namespace + "policyFor",
	policyForJEl = "#" + policyForEl,
	productNameJEl = "#" + namespace + "productName";
	
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
	
	var updateLMSLeadURL = "${updateLMSLeadURL}";
	var saveBasicDetailsURL = "${saveBasicDetailsURL}"; 
	var getBasicDetailsURL = "${getBasicDetailsURL}";
	var basicInvestingFor = '${basicDetailsMap["basicInvestingFor"]}';
	var genderValue = '${basicDetailsMap["gender"]}';
	var investmentObjectiveValue = '${basicDetailsMap["investmentObjective"]}';
	var assuranceFullName;
	var assuranceDob;
	var assuredRelationValue;
	if(basicInvestingFor == 'Family'){
		assuranceFullName = '${basicDetailsMap["assuranceFullName"]}';
		assuranceDob = '${basicDetailsMap["assuranceDob"]}';
		assuredRelationValue = '${basicDetailsMap["assuredRelation"]}';
	}
	 var basicDetailsMap = JSON.parse('<%=JSONFactoryUtil.createJSONObject(basicDetailsMap)%>');
</script>

<!-- Loader -->
<div id='<portlet:namespace/>tracker-loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
<c:if test="${not empty basicDetailsMap }" >
	<liferay-util:include page="/product_jsps/guaranteed-savings-star/customer_details.jsp" servletContext="<%=application%>" />
	<%@ include file="/edit_basic_details.jsp"%>
</c:if>


 <c:choose>
    <c:when test = "${isProductSelected}">
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/product_customize_fields.jsp" servletContext="<%=application%>" />
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/product_features.jsp" servletContext="<%=application%>" />
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/product_riders.jsp" servletContext="<%=application%>" />
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/error_message_model.jsp" servletContext="<%=application%>" />
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/maturity_benefits_breakup_model.jsp" servletContext="<%=application%>" />
		<liferay-util:include page="/product_jsps/guaranteed-savings-star/maturity_benefits_breakup_model.jsp" servletContext="<%=application%>" />
	</c:when>
    <c:otherwise>
    	<div class="alert alert-info"><liferay-ui:message key="please-select-product-name-from-page-setting" /></div>
    </c:otherwise>
</c:choose>

<script src="<%=request.getContextPath()%>/js/guaranteed-savings-star.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>
<script src="<%=request.getContextPath()%>/product_jsps/guaranteed-savings-star/js/gss-basic-detail.js?t=<%=EdelweissProductCustomizeConstants.CURRENT_TIME_STAMP%>"></script>