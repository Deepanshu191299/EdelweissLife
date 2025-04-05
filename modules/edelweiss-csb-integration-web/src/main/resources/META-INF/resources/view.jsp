<%@ include file="/init.jsp"%>

<c:set var = "getCsbResponse" scope = "request" value="${getCsbResponse}" />
<c:set var = "appId" scope = "request" value="${appId}" />
<c:set var = "isReportPage" scope = "request" value="${isReportPage}" />

<div id='loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<c:choose>
   <c:when test = "${appId > 0}">
      <liferay-util:include page="/CsbIntegration/preview.jsp" servletContext="<%=application%>" />
   </c:when>
   
   <c:when test = "${isReportPage}">
      <liferay-util:include page="/CsbIntegration/download_report.jsp" servletContext="<%=application%>" />
   </c:when>
   
   <c:otherwise>
      <liferay-util:include page="/CsbIntegration/csbrm.jsp" servletContext="<%=application%>" />
   </c:otherwise>
</c:choose>

<style>
#webklipper-publisher-widget-container{
	display: none !important;
}
</style>
<script>
function showLoader(){
	$('#loader').show();
}

function hideLoader(){
	$('#loader').hide();
}
</script>