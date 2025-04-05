<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>


<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
	
	<liferay-frontend:edit-form-body>
			<liferay-frontend:fieldset>
			
				<aui:row>
				   <aui:col md="6">
				   		<aui:input name="preferences--showTitle--" type="checkbox" value="${showTitle}" />
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--showDisclaimer--" type="checkbox" value="${showDisclaimer}" />
				   </aui:col>
				   
				   <aui:col>
				   		<aui:input name="preferences--title--" type="text" value="${title}" label="form-title">
					   		<aui:validator name="custom" errorMessage="this-field-is-required">
					   			function(val){
					   			     let checked = $('#<portlet:namespace/>showTitle').is(':checked');
					   			     return !(checked && val == '');
					   			}
					   		</aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col>
				   		<aui:input name="preferences--disclaimer--" type="text" value="${disclaimer}">
					   		<aui:validator name="custom" errorMessage="this-field-is-required">
					   			function(val){
					   			     let checked = $('#<portlet:namespace/>showDisclaimer').is(':checked');
					   			     return !(checked && val == '');
					   			}
					   		</aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col>
				   		<aui:input name="preferences--buttonLabel--" type="text" value="${buttonLabel}" required="true">
				   		</aui:input>
				   </aui:col>
				
				
				   <aui:col md="6">
				   		<aui:input name="preferences--showDownloadBrochure--" type="checkbox" value="${showDownloadBrochure}" />
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--showWatchVideo--" type="checkbox" value="${showWatchVideo}" />
				   </aui:col>
				   
				   <aui:col>
				   		<aui:input name="preferences--brochureURL--" type="text" value="${brochureURL}">
					   		<aui:validator name="custom" errorMessage="this-field-is-required">
					   			function(val){
					   			     let checked = $('#<portlet:namespace/>showDownloadBrochure').is(':checked');
					   			     return !(checked && val == '');
					   			}
					   		</aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col>
				   		<aui:input name="preferences--videoURL--" type="text" value="${videoURL}">
					   		<aui:validator name="custom" errorMessage="this-field-is-required">
					   			function(val){
					   			     let checked = $('#<portlet:namespace/>showWatchVideo').is(':checked');
					   			     return !(checked && val == '');
					   			}
					   		</aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="6">
				   		 <aui:input name="preferences--showSharingIcons--" type="checkbox" value="${showSharingIcons}" />
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--showRatings--" type="checkbox" value="${showRatings}" />
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--minAge--" type="text" value="${minAge}">
				   			<aui:validator name="number"></aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--maxAge--" type="text" value="${maxAge}">
				   		   <aui:validator name="number"></aui:validator>
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="12">
				   		<aui:input name="preferences--redirectionURL--" type="text" value="${redirectionURL}">
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="12">
				   		<aui:input name="preferences--showThankYouMessage--" type="checkbox" value="${showThankYouMessage}">
				   		</aui:input>
				   </aui:col>
				   
				</aui:row>

			</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

