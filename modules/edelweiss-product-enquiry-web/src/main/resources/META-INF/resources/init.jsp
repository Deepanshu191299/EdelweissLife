<%@page import="java.util.Date"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend"%>
<%@ taglib uri="http://liferay.com/tld/social-bookmarks" prefix="liferay-social-bookmarks"%>
<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
request.setAttribute("showDownloadBrochure", GetterUtil.getBoolean(portletPreferences.getValue("showDownloadBrochure","false")));
request.setAttribute("showWatchVideo", GetterUtil.getBoolean(portletPreferences.getValue("showWatchVideo","false")));
request.setAttribute("brochureURL", GetterUtil.getString(portletPreferences.getValue("brochureURL","")));
request.setAttribute("videoURL", GetterUtil.getString(portletPreferences.getValue("videoURL","")));
request.setAttribute("showSharingIcons", GetterUtil.getBoolean(portletPreferences.getValue("showSharingIcons","false")));
request.setAttribute("showRatings", GetterUtil.getBoolean(portletPreferences.getValue("showRatings","false")));
request.setAttribute("minAge", GetterUtil.getString(portletPreferences.getValue("minAge","18")));
request.setAttribute("maxAge", GetterUtil.getString(portletPreferences.getValue("maxAge","60")));

request.setAttribute("showThankYouMessage", GetterUtil.getBoolean(portletPreferences.getValue("showThankYouMessage","false")));
request.setAttribute("redirectionURL", GetterUtil.getString(portletPreferences.getValue("redirectionURL","")));

request.setAttribute("showTitle", GetterUtil.getBoolean(portletPreferences.getValue("showTitle","false")));
request.setAttribute("title", GetterUtil.getString(portletPreferences.getValue("title","")));
request.setAttribute("showDisclaimer", GetterUtil.getBoolean(portletPreferences.getValue("showDisclaimer","false")));
request.setAttribute("disclaimer", GetterUtil.getString(portletPreferences.getValue("disclaimer","")));
request.setAttribute("buttonLabel", GetterUtil.getString(portletPreferences.getValue("buttonLabel","START SAVINGS")));
%>

<script src="${webEngageURL}?t=<%= new Date().getTime() %>"></script>