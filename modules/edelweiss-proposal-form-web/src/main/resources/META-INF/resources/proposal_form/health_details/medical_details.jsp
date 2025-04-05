<%@ include file="../../init.jsp"%>

<c:if test="${commonDetails.formType == 'SIM00'}">
 	<liferay-util:include page="/proposal_form/health_details/medical_details_type2.jsp" servletContext="<%=application%>" />
 </c:if>
 <c:if test="${ commonDetails.formType == 'COM00'}">
 	 <liferay-util:include page="/proposal_form/health_details/medical_details_type1.jsp" servletContext="<%=application%>" />
 </c:if>

					