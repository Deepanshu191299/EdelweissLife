<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="edelweisspdfgenerator.caption"/></b>
</p>

<portlet:resourceURL id="/generate_pdf" var="generatePdfURL" />
<button onclick="window.location.href='${generatePdfURL}'">Download PDF</button>