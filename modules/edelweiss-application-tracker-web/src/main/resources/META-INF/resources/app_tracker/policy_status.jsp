<%@page import="in.edelweiss.common.util.ConfigurationUtil"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.util.HashMap"%>
<%@ taglib uri="http://liferay.com/tld/react" prefix="react" %>


<%
JSONObject policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");

HashMap<String, Object> map = new HashMap();

map.put("dob",policyFormJSONObject.getString("la_dob"));
map.put("policyNo",policyFormJSONObject.getString("policy_number"));
map.put("clientAppTrackerURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().getClientAppTrackerURL());
map.put("getDMSListURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().getDMSListURL());
map.put("generateDownloadURL",ConfigurationUtil.getAppTrackerAPIURLConfiguration().generateDownloadURL());

%>

<div class="tab-pane fade"
      id="pills-policy"
      role="tabpanel"
      aria-labelledby="pills-policy-tab">
			
			<react:component
		      module="js/PolicyStatus"
		       props='<%= map %>'
	/>
</div>