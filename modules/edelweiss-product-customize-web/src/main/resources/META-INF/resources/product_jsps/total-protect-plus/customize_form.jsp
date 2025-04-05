<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="in.edelweiss.product.customize.web.constants.ParameterConstants"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ include file="/init.jsp"%>

<%
	Map<String, String> cookieLeadValue = null;
	if(Validator.isNotNull(renderRequest.getAttribute("cookieLeadValue")))
	{
		cookieLeadValue = (Map<String, String>)renderRequest.getAttribute("cookieLeadValue");
	}
	
	JSONObject liferayProductNestedData = null;
	if(Validator.isNotNull(renderRequest.getAttribute("liferayProductNestedData")))
	{
		liferayProductNestedData = (JSONObject)renderRequest.getAttribute("liferayProductNestedData");
	}
%>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />
<div id="<portlet:namespace/>customerBasicDetails">
	<label><liferay-ui:message key="label-basic-details"/></label>
	<ul class="b-detail-list personal-details">
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.FULL_NAME%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.FULL_NAME, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.GENDER%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.GENDER, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.DOB%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.DOB, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.SMOKE%>">
			<%=cookieLeadValue.getOrDefault(ParameterConstants.SMOKE, StringPool.BLANK).equalsIgnoreCase("1")?"Smoker":"Non-Smoker" %>
		</li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.MOBILE_NUMBER%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.MOBILE_NUMBER, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.EMAIL_ADDRESS%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.EMAIL_ADDRESS, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/><%=ParameterConstants.ANNUAL_INCOME_RANGE%>"><%=cookieLeadValue.getOrDefault(ParameterConstants.ANNUAL_INCOME_RANGE, StringPool.BLANK) %></li>
		<li class="b-detail-lstitem" id="<portlet:namespace/>age">${age }</li>
	</ul>
</div>

<%@ include file="/init.jsp"%>

 <c:choose>
    <c:when test = "${isProductSelected}">
		<aui:form name="customizeForm" method="post" action="">
			<div class="row">
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.LIFE_COVER%>"><liferay-ui:message key="label-life-cover" /></label><br/> 
							<small><liferay-ui:message key="label-total-financial-coverage" /></small>
							<aui:input type="text" cssClass="form-control" name="<%=ParameterConstants.LIFE_COVER%>" value="" />
						</div>
					</div>
				</c:if>
				
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.PLAN_OPTION%>"><liferay-ui:message key="label-plan-option" /></label><br/> 
							<small><liferay-ui:message key="label-pick-plan-preference" /></small>
							<aui:select cssClass="form-control" name="<%=ParameterConstants.PLAN_OPTION%>">
								<aui:option value=""><liferay-ui:message key="select" /></aui:option>
								<c:forEach var="planOption" items="${planOptions}">
									<aui:option value="${planOption.key}">${planOption.value}</aui:option>
								</c:forEach>
							</aui:select>
						</div>
					</div>
				</c:if>
				
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.POLICY_TERM%>"><liferay-ui:message key="label-policy-term" /></label> <br/>
							<small><liferay-ui:message key="label-years-covered" /></small>
							<aui:select class="form-control" name="<%=ParameterConstants.POLICY_TERM%>">
								<aui:option value=""><liferay-ui:message key="select" /></aui:option>
								<c:forEach var="policyTerm" begin = "0" end = "${100-(age+10) }" >
									<aui:option value="${100-age-policyTerm }" 
										selected="${(100-age-policyTerm) == (70-age) ? true : ''}">
										${100-age-policyTerm}
									</aui:option>
								</c:forEach>
							</aui:select>
						</div>
					</div>
				</c:if>
				
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.PAYMENT_PERIOD%>"><liferay-ui:message key="label-payment-period" /></label> <br/>
							<small><liferay-ui:message key="label-duration-of-your-plan" /></small>
							<aui:select cssClass="form-control" name="<%=ParameterConstants.PAYMENT_PERIOD%>">
								<aui:option value=""><liferay-ui:message key="select" /></aui:option>
								<c:forEach var="paymentPeriod" items="${paymentPeriods}">
									<aui:option value="${paymentPeriod.key}">${paymentPeriod.value}</aui:option>
								</c:forEach>
							</aui:select>
						</div>
					</div>
				</c:if>
				
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.PAYMENT_FREQUENCY%>"><liferay-ui:message key="label-payment-frequency" /></label> <br/>
							<small><liferay-ui:message key="label-premiums-paied-intervals" /></small> 
							<aui:select cssClass="form-control" name="<%=ParameterConstants.PAYMENT_FREQUENCY%>">
								<aui:option value=""><liferay-ui:message key="select" /></aui:option>
								<c:forEach var="paymentFrequency" items="${paymentFrequencies}">
									<aui:option value="${paymentFrequency.key}">${paymentFrequency.value}</aui:option>
								</c:forEach>
							</aui:select>
						</div>
					</div>
				</c:if>
				
				<c:if test="true">
					<div class="col-lg-3 col-md-4 col-sm-12">
						<div class="form-group">
							<label for="<portlet:namespace /><%=ParameterConstants.INCOME_TYPE%>"><liferay-ui:message key="label-income-type" /></label> <br/>
							<small><liferay-ui:message key="label-benifit-recive-mode" /></small>
							<aui:select cssClass="form-control" name="<%=ParameterConstants.INCOME_TYPE%>">
								<aui:option value=""><liferay-ui:message key="select" /></aui:option>
								<c:forEach var="curPaymentOption" items="${productCustomizeSummaryData['paymentOptionsData']}">
									<aui:option value="${curPaymentOption.key}" select="${productCustomizeSummaryData['productDefaultValueData'].paymentOption.key == curPaymentOption.key ? 'selected' : ''}">${curPaymentOption.name}</aui:option>
								</c:forEach>
							</aui:select>
						</div>
					</div>
				</c:if>
			</div>
		</aui:form>

		<div class="card w-25">
			<div class="card-body" style="background: #fff6e1;">
				<h2 class="card-title"><liferay-ui:message key="total-returns" /></h5>
				<h3 class="card-subtitle mb-2 text-muted"><liferay-ui:message key="at-the-end-of-policy-term" /></h6>
				<div class="return-amt">
					<span class="rupee"><liferay-ui:message key="rupee-sign" /></span>
					<span id="returnAmtVal"></span>
				</div>

				<select class="form-control col-4 mt-1" name="totalReturnsValue" id="totalReturnsValue">
					<option value="8" selected><liferay-ui:message key="eight-percent" /></option>
					<option value="4"><liferay-ui:message key="four-percent" /></option>
				</select>
			</div>
		</div>

	</c:when>
    <c:otherwise>
    	<div class="alert alert-info"><liferay-ui:message key="please-select-product-name-from-page-setting" /></div>
    </c:otherwise>
</c:choose>
<liferay-util:include page="/product_jsps/total-protect-plus/product_riders.jsp" servletContext="<%=application%>" />
<liferay-util:include page="/product_jsps/total-protect-plus/recommended_products.jsp" servletContext="<%=application%>" />
<script src="<%=request.getContextPath()%>/js/total-protect-plus.js?v=1.1"></script>