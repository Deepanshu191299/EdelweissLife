
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@ include file="/init.jsp" %>

<%
	Map<String, String> optionsList = new LinkedHashMap<>();
	if (Validator.isNotNull(request.getAttribute("edelweiss-ui:dynamic-dropdown:options-list"))) {
		optionsList = (Map<String, String>) request.getAttribute("edelweiss-ui:dynamic-dropdown:options-list");
	}
	
	String fieldName = GetterUtil.getString(request.getAttribute("edelweiss-ui:dynamic-dropdown:name"),StringPool.BLANK);
	String label = GetterUtil.getString(request.getAttribute("edelweiss-ui:dynamic-dropdown:label"),StringPool.BLANK);
	String cssClass = GetterUtil.getString(request.getAttribute("edelweiss-ui:dynamic-dropdown:cssClass"),StringPool.BLANK);
	String defaultValue = GetterUtil.getString(request.getAttribute("edelweiss-ui:dynamic-dropdown:value"),StringPool.BLANK);
%>

<div class="synamic-dropdown" id="<portlet:namespace/><%=fieldName+"container"%>">
	<aui:select label="<%=label %>" name="<%=fieldName %>" value='<%= defaultValue %>'>
		<aui:option label="label-select" value="" />
		<c:forEach items="<%=optionsList%>" var="optionEntry">
			<aui:option label="${optionEntry.value}"
				value="${optionEntry.key}" />
		</c:forEach>

	</aui:select>
</div>
