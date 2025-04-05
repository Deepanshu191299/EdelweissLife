<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend"%>
<liferay-theme:defineObjects />

<portlet:defineObjects />

<%@ page import="java.util.Date" %>

<%
request.setAttribute("gssProductCode", GetterUtil.getString(portletPreferences.getValue("gssProductCode","40063")));
request.setAttribute("ibProductCode", GetterUtil.getString(portletPreferences.getValue("ibProductCode","40060")));
request.setAttribute("gisProductCode", GetterUtil.getString(portletPreferences.getValue("gisProductCode","40062")));
request.setAttribute("zpProductCode", GetterUtil.getString(portletPreferences.getValue("zpProductCode","40069")));
request.setAttribute("pgsProductCode", GetterUtil.getString(portletPreferences.getValue("pgsProductCode","40065")));
request.setAttribute("fspProductCode", GetterUtil.getString(portletPreferences.getValue("fspProductCode","40064")));
%>

<script>
var portletNamespace = '<portlet:namespace/>';
var namespace = '<portlet:namespace/>';
var gst;
var gssProductCode = ${gssProductCode == null || gssProductCode == '' ? 40063 : gssProductCode};
var ibProductCode = ${ibProductCode == null || ibProductCode == '' ? 40060 : ibProductCode};
var gisProductCode = ${gisProductCode == null || gisProductCode == '' ? 40062 : gisProductCode};
var zpProductCode = ${zpProductCode == null || zpProductCode == '' ? 40069 : zpProductCode};
var pgsProductCode = ${pgsProductCode == null || pgsProductCode == '' ? 40065 : pgsProductCode};
var fspProductCode = ${fspProductCode == null || fspProductCode == '' ? 40064 : fspProductCode};
</script>