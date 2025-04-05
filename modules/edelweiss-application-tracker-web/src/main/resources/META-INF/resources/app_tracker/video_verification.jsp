<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="/init.jsp"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>

<%
JSONObject verificationJSONObject = (JSONObject) renderRequest.getAttribute("verificationJSONObject");
String videoUrl  = StringPool.BLANK;
if(Validator.isNotNull(verificationJSONObject.getString("pivcdirecturl"))){
	if(verificationJSONObject.getString("pivcdirecturl").contains("https://")){
		videoUrl = verificationJSONObject.getString("pivcdirecturl");
	}else{
		videoUrl = "https://" + verificationJSONObject.getString("pivcdirecturl");
	}
}

boolean videoPendingStep = (boolean)request.getAttribute("videoPending");
%>

<div class="<%= videoPendingStep ? "active show" : StringPool.BLANK %> tab-pane fade" id="pills-video" role="tabpanel"
	aria-labelledby="pills-video-tab">
	<h2><liferay-ui:message key="label-at-pivc-title" /></h2>
	<p><liferay-ui:message key="label-at-pivc-subtitle" /></p>

<c:choose>

<c:when test="<%=Validator.isNotNull(verificationJSONObject) && Validator.isNotNull(verificationJSONObject.getString("pivcStatus")) && (verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Pending") || verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("InProgress")) %>">
		
	<div class="application-form-tiles-wrapper">
		<div class="video-wrappper">
			<h4><liferay-ui:message key="label-at-pivc-greetings" /> ${userName},</h4>
			<p><liferay-ui:message key="label-at-pivc-description" /></p>
		</div>
	</div>
	<div class="video-verification-wrapper">
		<p>
			<span> <svg width="32" height="30" viewBox="0 0 32 30"
					fill="none" xmlns="http://www.w3.org/2000/svg">
                              <path
						d="M4.0257 19.0214H6.44113L7.04498 17.4111H10.1649L10.7688 19.0214H13.1842L9.76234 10.0642H7.34691L4.0257 19.0214ZM8.55463 12.5803L9.56106 15.7002H7.5482L8.55463 12.5803Z"
						fill="#343434"></path>
                              <path
						d="M28.18 0H12.0772V5.03215H14.09V2.01286H28.18C28.7839 2.01286 29.1865 2.41543 29.1865 3.01929V14.09C29.1865 14.6939 28.7839 15.0964 28.18 15.0964H15.0964V22.1415H9.76237L6.03858 25.0601V22.1415H3.01929C2.41543 22.1415 2.01286 21.7389 2.01286 21.135V10.0643C2.01286 9.46044 2.41543 9.05787 3.01929 9.05787H16.1029V7.04501H3.01929C1.30836 7.04501 0 8.35336 0 10.0643V21.135C0 22.846 1.30836 24.1543 3.01929 24.1543H4.02572V29.2871L10.3662 24.1543H17.1093V17.1093H28.18C29.891 17.1093 31.1993 15.8009 31.1993 14.09V3.01929C31.1993 1.30836 29.891 0 28.18 0Z"
						fill="#343434"></path>
                           </svg>
			</span> <liferay-ui:message key="label-at-choose-your-preferred-language-to-complete-verification" />
		</p>
		<div class="select-container">
			<select id="document" class="select2-hidden-accessible select2-dropClass">
				<option>English</option>
				<option>Hindi</option>
				<option>Marathi</option>
				<option>Gujarati</option>
				<option>Tamil</option>
				<option>Telugu</option>
			</select>
		</div>
		<div class="ceter-btn">
			<a class="edto-btn-primary" href="<%= videoUrl%>"><liferay-ui:message key="label-at-start-video-verification" /></a>
			<p>
				<liferay-ui:message key="label-at-having-trouble" />  <a href="javascript:;"><liferay-ui:message key="label-at-contact-here" /></a>
			</p>
		</div>
	</div>
</c:when>
<c:when test="<%=Validator.isNotNull(verificationJSONObject) && Validator.isNotNull(verificationJSONObject.getString("pivcStatus")) && verificationJSONObject.getString("pivcStatus").equalsIgnoreCase("Completed") %>">
        <div class="application-table-content">
          <div class="table-form-wrapper">
            <div class="td-form">
              <h3><liferay-ui:message key="label-at-pivc-complets-title" /></h3>
            </div>

            <div class="td-form">
              <span>
                <svg
                  width="18"
                  height="18"
                  viewBox="0 0 18 18"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fill-rule="evenodd"
                    clip-rule="evenodd"
                    d="M0 9C0 6.61305 0.948212 4.32387 2.63604 2.63604C4.32387 0.948212 6.61305 0 9 0C11.3869 0 13.6761 0.948212 15.364 2.63604C17.0518 4.32387 18 6.61305 18 9C18 11.3869 17.0518 13.6761 15.364 15.364C13.6761 17.0518 11.3869 18 9 18C6.61305 18 4.32387 17.0518 2.63604 15.364C0.948212 13.6761 0 11.3869 0 9H0ZM8.4864 12.852L13.668 6.3744L12.732 5.6256L8.3136 11.1468L5.184 8.5392L4.416 9.4608L8.4864 12.8532V12.852Z"
                    fill="#00C511"
                  ></path>
                </svg>
                <liferay-ui:message key="label-at-piv-completed" />
              </span>
            </div>
          </div>
        </div>
</c:when>
<c:otherwise>

	<div class="application-form-tiles-wrapper">
		<div class="video-wrappper">
			<h4><liferay-ui:message key="label-at-pivc-greetings" /> ${userName},</h4>
			<p><liferay-ui:message key="label-at-pivc-nodetails-description" /></p>
		</div>
	</div>

</c:otherwise>

</c:choose>		
	
</div>