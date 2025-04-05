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
request.setAttribute("campaignPageForm", GetterUtil.getString(portletPreferences.getValue("campaignPageForm","")));
request.setAttribute("campaignPageTemplate", GetterUtil.getString(portletPreferences.getValue("campaignPageTemplate","")));
request.setAttribute("showTitle", GetterUtil.getBoolean(portletPreferences.getValue("showTitle","false")));
request.setAttribute("showDisclaimer", GetterUtil.getString(portletPreferences.getValue("showDisclaimer","")));
request.setAttribute("title", GetterUtil.getString(portletPreferences.getValue("title","")));
request.setAttribute("disclaimer", GetterUtil.getString(portletPreferences.getValue("disclaimer","")));
request.setAttribute("showThankYouMessage", GetterUtil.getBoolean(portletPreferences.getValue("showThankYouMessage","false")));
request.setAttribute("showDownloadBrochure", GetterUtil.getBoolean(portletPreferences.getValue("showDownloadBrochure","false")));
request.setAttribute("brochureURL", GetterUtil.getString(portletPreferences.getValue("brochureURL","")));
request.setAttribute("buttonLabel", GetterUtil.getString(portletPreferences.getValue("buttonLabel","START SAVINGS")));
request.setAttribute("redirectionURL", GetterUtil.getString(portletPreferences.getValue("redirectionURL","")));
request.setAttribute("ctaAlign", GetterUtil.getString(portletPreferences.getValue("ctaAlign","")));
request.setAttribute("facebookPageViewScript", GetterUtil.getString(portletPreferences.getValue("facebookPageViewScript","")));
request.setAttribute("googlePageViewScript", GetterUtil.getString(portletPreferences.getValue("googlePageViewScript","")));
request.setAttribute("googleLeadConversionScript", GetterUtil.getString(portletPreferences.getValue("googleLeadConversionScript","")));
request.setAttribute("facebookLeadConversionScript", GetterUtil.getString(portletPreferences.getValue("facebookLeadConversionScript","")));
request.setAttribute("googleTagSnippet", GetterUtil.getString(portletPreferences.getValue("googleTagSnippet","")));
request.setAttribute("facebookTagSnippet", GetterUtil.getString(portletPreferences.getValue("facebookTagSnippet","")));

String ctaAlign = GetterUtil.getString(portletPreferences.getValue("ctaAlign",""));
String googlePageViewScript = GetterUtil.getString(portletPreferences.getValue("googlePageViewScript",""));
String facebookPageViewScript = GetterUtil.getString(portletPreferences.getValue("facebookPageViewScript",""));
String googleLeadConversionScript = GetterUtil.getString(portletPreferences.getValue("googleLeadConversionScript",""));
String facebookLeadConversionScript = GetterUtil.getString(portletPreferences.getValue("facebookLeadConversionScript",""));
String googleTagSnippet = GetterUtil.getString(portletPreferences.getValue("googleTagSnippet",""));
String facebookTagSnippet = GetterUtil.getString(portletPreferences.getValue("facebookTagSnippet",""));
%>

<div id="tag-container"></div>
<script id="gtag-pixel-pageview-tracker"></script>
<script id="gtag-pixel-leadconversion-tracker"></script>

<script>
var namespace = "<portlet:namespace/>";
var ctaAlign = '${ctaAlign}'; 

var facebookLeadConversion = '${facebookLeadConversion}'; 
var facebookPageView = '${facebookPageView}'; 
var googleLeadConversion = '${googleLeadConversion}'; 
var googlePageView = '${googlePageView}'; 

var facebookPageViewScript = "function callFacebookPageView(){" + "${facebookPageViewScript.toString()}" + "}";
var googlePageViewScript = "function callGooglePageView(){" + "${googlePageViewScript.toString()}" + "}";
var googleLeadConversionScript = "${googleLeadConversionScript.toString()}";
var facebookLeadConversionScript = "function callFacebookLeadConversion(){" +  "${facebookLeadConversionScript.toString()}" + "}";
var googleTagSnippet = "${googleTagSnippet.toString()}";
var facebookTagSnippet = "${facebookTagSnippet.toString()}";

var tagContainer = document.getElementById("tag-container");
var pageViewContainer = document.getElementById("gtag-pixel-pageview-tracker");
var leadConversionContainer = document.getElementById("gtag-pixel-leadconversion-tracker");

tagContainer.innerHTML =  googleTagSnippet + facebookTagSnippet;
pageViewContainer.innerHTML =  googlePageViewScript + "\n" + facebookPageViewScript;
leadConversionContainer.innerHTML = googleLeadConversionScript + "\n" + facebookLeadConversionScript;
</script>

<style>
.cta-left-align .form-btn{margin: 0 !important;float: left;}
.cta-right-align .form-btn{float: right}
.cta-center-align .form-btn{margin: auto;}
</style>