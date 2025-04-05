<%@ include file="/init.jsp" %>
<portlet:resourceURL var="generateCisPDFUrl" id="/cis/generatepdf"/>

<portlet:resourceURL id="/cis/cishtmltopdf" var="generateCisHtmlToPdfURL" >
	<portlet:param name="appId" value="${appid == null || appid == ''  ? '' : appid}" />
	<portlet:param name="checkPage" value="isProposalForm" />
</portlet:resourceURL>

<portlet:resourceURL var="cisPushPdfFlagUrl" id="/cis/cisPushPdfFlag"/>
<!-- <portlet:resourceURL var="sendCisPDFUrl" id="/cis/sendpdf"/> -->
<script src="<%=request.getContextPath()%>/js/html2pdf.js"></script>
<script>
var downloadCISPdfFromBackend = () => {
	window.location.href="${generateCisHtmlToPdfURL}";
};
</script>