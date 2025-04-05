<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend"%>
<liferay-theme:defineObjects />
<portlet:defineObjects />

<%
request.setAttribute("gssProductCode", GetterUtil.getString(portletPreferences.getValue("gssProductCode","")));
%>

<script>
	var portletNamespace = '<portlet:namespace/>';
	var gssProductCode = ${gssProductCode == null || gssProductCode == '' ? 0 : gssProductCode};
	var productName = "${productMetaData['productName']}";
	var productCode = "${productMetaData['productCode']}";
</script>

<style>
	.disabledbutton{
		pointer-events: none;
		cursor: not-allowed !important;
    	opacity: 0.65;
	}
</style>