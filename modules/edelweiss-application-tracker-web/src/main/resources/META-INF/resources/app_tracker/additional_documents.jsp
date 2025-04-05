<%@page import="in.edelweiss.common.util.ConfigurationUtil"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %>

<%
JSONObject policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");

HashMap<String, Object> map = new HashMap();

map.put("dob",policyFormJSONObject.getString("la_dob"));
map.put("policyNumber",policyFormJSONObject.getString("policy_number"));
map.put("applicationNumber",policyFormJSONObject.getString("application_number"));
map.put("clientSearchRequirementsURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().getClientSearchRequirementsURL());
map.put("getRequirementsDetailsURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().getRequirementsDetailsURL());
map.put("markReceivedURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().getMarkReceivedURL());
map.put("generateDMSUploadURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().generateDMSUploadURL());
%>

<div class="tab-pane fade" id="pills-additionaldoc" role="tabpanel" aria-labelledby="pills-additionaldoc-tab">
			
			<react:component
		      module="js/AdditionalDocuments"
		      props='<%= map %>'
	/>
</div>