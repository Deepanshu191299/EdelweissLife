<%@page import="com.liferay.portal.kernel.json.JSON"%>
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
	JSONArray recommendedPlansList = JSONFactoryUtil.createJSONArray();
	if(Validator.isNotNull(renderRequest.getAttribute("recommendedPlansList")))
	{
		recommendedPlansList = (JSONArray)renderRequest.getAttribute("recommendedPlansList");
	}
%>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />

<c:if test="<%=Validator.isNotNull(recommendedPlansList) && recommendedPlansList.length()>0%>">
	<div id="<portlet:namespace/>recomendedProducts">
		<div id="<portlet:namespace/>recomendedProductsContainer" class="row">
			<%
				for(int i=0; i<recommendedPlansList.length();i++){ 
					JSONObject recommendedProduct = recommendedPlansList.getJSONObject(i);
					
					String totalinstallmentprem = StringPool.BLANK;
					String totalinstallmentprem2ndYear = StringPool.BLANK;
					if(Validator.isNotNull(recommendedProduct.getJSONObject("additionalInformation")))
					{
						totalinstallmentprem = recommendedProduct.getJSONObject("additionalInformation").getString("totalinstallmentprem");
						totalinstallmentprem2ndYear = recommendedProduct.getJSONObject("additionalInformation").getString("totalinstallmentprem2ndYear");
					}
					if(Validator.isNull(totalinstallmentprem))
					{
						continue;
					}
					String benefitUniqueKey = recommendedProduct.getString("uniqueKey").replace(StringPool.SPACE, StringPool.BLANK);
			%>
				<div id="<portlet:namespace/>recommendedProduct<%=benefitUniqueKey %>" class="col-12 col-md-4">
					<c:if test="<%=Validator.isNotNull(recommendedProduct.getJSONObject("thumbnail")) && Validator.isNotNull(recommendedProduct.getJSONObject("thumbnail").getJSONObject("link")) %>" >
						<center><img src="<%=recommendedProduct.getJSONObject("thumbnail").getJSONObject("link").getString("href") %>" 
							id="<portlet:namespace/>productBenefitImage<%=benefitUniqueKey %>" alt=""/></center>
					</c:if>
					<center><%=recommendedProduct.getString("title", StringPool.BLANK) %></center>
					<div id="<portlet:namespace/>premiumAmountDiv<%=benefitUniqueKey %>" class="text-center">
						<h4 id="<portlet:namespace/>premiumAmount<%=benefitUniqueKey %>"><%=totalinstallmentprem %></h4>
						<p>Incl. of GST</p>
					</div>
					<c:if test="<%=Validator.isNotNull(recommendedProduct.getJSONArray("features")) && recommendedProduct.getJSONArray("features").length()>0%>">
						<div id="<portlet:namespace/>productFeatures<%=benefitUniqueKey %>">
							<%
								JSONArray productFeaturesList=recommendedProduct.getJSONArray("features");
								for(int j=0; j<productFeaturesList.length();j++){
									JSONObject productFeature=productFeaturesList.getJSONObject(j);
									if(Validator.isNull(productFeature) || !productFeature.getBoolean("isActive")){
										continue;
									}
							%>
								<p><%=productFeature.getString("title", StringPool.BLANK) %></p>
							<%} %>
						</div>
					</c:if>
				</div>
			<%} %>	
		</div>
		<div class="custom-link">
			<a href="#<portlet:namespace/>productRiders" onclick="showProductRiders()"><liferay-ui:message key="label-customize-plan" /></a>
		</div>
	</div>
</c:if>