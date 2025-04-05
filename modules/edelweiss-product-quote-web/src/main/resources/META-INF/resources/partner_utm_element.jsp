<%@page import="in.edelweiss.tokio.constants.EdelweissCommonConstants"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%
HttpServletRequest orginalRequest = PortalUtil.getOriginalServletRequest(request);
request.setAttribute("orginalRequest", orginalRequest);
request.setAttribute("utmPrams", EdelweissCommonConstants.UTM_CAMPAIGN_PARAMS);
%>
<c:if test="${ not empty orginalRequest.getParameter('src')}">
  <aui:input type="hidden" name="partnerSource" value="${orginalRequest.getParameter('src')}"></aui:input>
</c:if>

 <c:forEach items="${utmPrams}" var="utm">
 	<c:if test="${ not empty orginalRequest.getParameter(utm)}">
 	   <aui:input type="hidden" name="${utm}" value="${orginalRequest.getParameter(utm)}"></aui:input>
 	</c:if>
 </c:forEach>