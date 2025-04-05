<%@page import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<script>
    var portletNamespace = '<portlet:namespace/>';
    var showErrorModal = "${showErrorModal}";
    
    var productName = '${productName}';
    var productCode = '${productCode}';
    
	var LANG_MESSAGES = {};
	LANG_MESSAGES["enter-your-date-of-birth"] = "<liferay-ui:message key='enter-your-date-of-birth' />";
    LANG_MESSAGES["spouse-dob-within-10-of-your-age"] = "<liferay-ui:message key='spouse-dob-within-10-of-your-age' />";
	LANG_MESSAGES["age-should-be-between-18-to-65"] = "<liferay-ui:message key='age-should-be-between-18-to-65' />";
	LANG_MESSAGES["please-enter-your-spouse-name"] = "<liferay-ui:message key='please-enter-your-spouse-name' />";
	LANG_MESSAGES["please-enter-your-full-name"] = "<liferay-ui:message key='please-enter-your-full-name' />";
	LANG_MESSAGES["please-enter-spouse-dob"] = "<liferay-ui:message key='please-enter-spouse-dob' />";
	LANG_MESSAGES["enter-valid-date"] = "<liferay-ui:message key='enter-valid-date' />";
	LANG_MESSAGES["spouse-age-should-be-between-18-to-65"] = "<liferay-ui:message key='spouse-age-should-be-between-18-to-65' />";
	LANG_MESSAGES["please-enter-child-dob"] = "<liferay-ui:message key='please-enter-child-dob' />";
	LANG_MESSAGES["child-age-should-be-between-0-to-18"] = "<liferay-ui:message key='child-age-should-be-between-0-to-18' />";
	LANG_MESSAGES["select-your-annual-income-range"] = "<liferay-ui:message key='select-your-annual-income-range' />";
    LANG_MESSAGES["please-enter-valid-mobile-number"] = "<liferay-ui:message key='please-enter-valid-mobile-number' />";
	LANG_MESSAGES["enter-a-valid-email-address"] = "<liferay-ui:message key='enter-a-valid-email-address' />";

</script>