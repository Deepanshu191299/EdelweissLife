<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<%
	
	JSONObject liferayProductNestedData = null;
	if(Validator.isNotNull(renderRequest.getAttribute("liferayProductNestedData")))
	{
		liferayProductNestedData = (JSONObject)renderRequest.getAttribute("liferayProductNestedData");
	}
	
	JSONArray productRidersList = liferayProductNestedData.getJSONArray("riders");
%>
<input type="hidden" id="portletNamespace" value="<portlet:namespace/>" />

<c:if test="<%=Validator.isNotNull(productRidersList) && productRidersList.length()>0%>">
	<div id="<portlet:namespace/>productRiders" style="display: none;">
		<div id="<portlet:namespace/>productRidersContainer" class="row">
			<%for(int i=0; i<productRidersList.length();i++){ 
				JSONObject productRider=productRidersList.getJSONObject(i);
				String riderUniqueKey=productRider.getString("uniqueKey").replace(StringPool.SPACE, StringPool.BLANK);
				if(!productRider.getBoolean("isActive"))
				{
					continue;
				}
			%>
				<div id="<portlet:namespace/>rider<%=riderUniqueKey %>" class="col-12 col-md-4">
					<h4><%=productRider.getString("title", StringPool.BLANK) %></h4>
					<div id="<portlet:namespace/>brochure<%=riderUniqueKey %>" class="">
						<a href="" download="<%=productRider.getString("key", StringPool.BLANK) %>"><liferay-ui:message key="label-download-brochure"/></a>
					</div>
					<div id="<portlet:namespace/>riderDescription<%=riderUniqueKey %>">
						<%=productRider.getString("description", StringPool.BLANK) %>
					</div>
				</div>
			<%} %>
		</div>
		<div class="custom-link">
			<a href="#<portlet:namespace/>recomendedProducts"  onclick="showProductFeatures()" id=""><liferay-ui:message key="label-recommended-plan-compare"/></a>
		</div>
	</div>
</c:if>