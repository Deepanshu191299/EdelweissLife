<%@ include file="/init.jsp"%>
<%@page import="edelweiss.csb.integration.web.constants.EdelweissCsbIntegrationWebPortletKeys"%>

<portlet:resourceURL var="exportCSVURL" id="<%= EdelweissCsbIntegrationWebPortletKeys.CSB_INTEGRATION_DOWNLOAD_REPORT_MVC_RESOUCE_COMMAND %>">
	<portlet:param name="export" value="exportCSV" />
</portlet:resourceURL>


<h2>Download Report</h2>
<div>
	<a href="${exportCSVURL}">Download</a>
</div>