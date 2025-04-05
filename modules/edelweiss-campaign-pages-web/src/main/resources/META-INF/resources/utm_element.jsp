<%@page import="in.edelweiss.tokio.constants.EdelweissCommonConstants"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%
HttpServletRequest orginalRequest = PortalUtil.getOriginalServletRequest(request);
request.setAttribute("orginalRequest", orginalRequest);
request.setAttribute("utmPrams", EdelweissCommonConstants.UTM_CAMPAIGN_PARAMS);
%>
<aui:input type="hidden" name="cp_id" value="${orginalRequest.getParameter('cp_id')}"></aui:input>
<aui:input type="hidden" name="v_id" value="${orginalRequest.getParameter('v_id')}"></aui:input>
<aui:input type="hidden" name="c_id" value="${orginalRequest.getParameter('c_id')}"></aui:input>
 <c:forEach items="${utmPrams}" var="utm">
 	<c:if test="${ not empty orginalRequest.getParameter(utm)}">
 	   <aui:input type="hidden" name="${utm}" value="${orginalRequest.getParameter(utm)}"></aui:input>
 	</c:if>
 </c:forEach>