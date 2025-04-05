<%@page import="java.util.HashMap"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@page import="java.util.Map"%>
<%@ include file="/init.jsp" %>

<liferay-util:html-top
	outputKey="in.edelweiss.taglib.servlet.taglib#/guest_ratings/page.jsp"
>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/css/main.css") %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>


<liferay-portlet:resourceURL var="guestRatingsURL" portletName="in_edelweiss_guest_rating_web_EdelweissGuestRatingWebPortlet"></liferay-portlet:resourceURL>

<%
HashMap<String, Object> map = (HashMap) request.getAttribute("edelweiss-ui:ratings:data");

map.put("guestRatingsURL",guestRatingsURL);
%>

<div class="ratings">

			<div class="ratings-like">
				<clay:button
					borderless="<%= true %>"
					disabled="<%= true %>"
					displayType="secondary"
					small="<%= true %>"
				>
					<clay:icon
						symbol="thumbs-up"
					/>
				</clay:button>
			</div>
			
			<react:component
		      module="js/Ratings"
		      props='<%= map %>'
	/>
</div>
