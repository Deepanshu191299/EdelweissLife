<%@page import="com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalServiceUtil"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMFormInstance"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="../init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<%
	List<DDMFormInstance> formInstances = DDMFormInstanceLocalServiceUtil.getFormInstances(themeDisplay.getScopeGroupId());
%>

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
				   		<aui:select name="preferences--campaignPageForm--" value="${campaignPageForm}">
				   			<aui:option value="" disabled="true">Select Campaign Page Form</aui:option>
				   			<c:forEach var="formInstance" items="<%=formInstances %>">
						   		<aui:option value="${formInstance.getFormInstanceId() }">${formInstance.getNameCurrentValue() }</aui:option>
						    </c:forEach>
				   		</aui:select>
					</aui:col>
					
					<aui:col md="6">
				   		<aui:select name="preferences--campaignPageTemplate--" value="${campaignPageTemplate}">
				   			<aui:option value="" disabled="true">Select Campaign Page Template</aui:option>
					   		<aui:option value="/vertical_campaign_page.jsp">Vertical Campaign Template</aui:option>
					   		<aui:option value="/horizontal_campaign_page.jsp">Horizontal Campaign Template</aui:option>
					   		<aui:option value="/right_side_overlay_campaign_page.jsp">Right-Side Overlay Campaign Template</aui:option>
					   		<aui:option value="/bottom_banner_overlay_campaign_page.jsp">Banner Bottom Overlay Campaign Template</aui:option>
				   		</aui:select>
					</aui:col>
					
				    <aui:col md="12">
						<label class="aui-field-label">CTA Button Alignment</label>
						<aui:input label="left" name="preferences--ctaAlign--" type="radio" inlineField="true" inlineLabel="right" value="LeftAlign" checked="${ctaAlign=='LeftAlign' ? true : false}" />
						<aui:input label="center" name="preferences--ctaAlign--" type="radio" inlineField="true" inlineLabel="right" value="CenterAlign" checked="${ctaAlign=='CenterAlign' ? true : false}" />
						<aui:input label="right" name="preferences--ctaAlign--" type="radio" inlineField="true" inlineLabel="right" value="RightAlign" checked="${ctaAlign=='RightAlign' ? true : false}" />
					</aui:col>
					
					<aui:col md="6">
						<aui:input name="preferences--googlePageViewScript--" type="textarea" value="${googlePageViewScript}" label="google-page-view-script">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				    </aui:col>
				   
				    <aui:col md="6">
						<aui:input name="preferences--facebookPageViewScript--" type="textarea" value="${facebookPageViewScript}" label="facebook-page-view-script">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				    </aui:col>

					<aui:col md="6">
						<aui:input name="preferences--googleLeadConversionScript--" type="textarea" value="${googleLeadConversionScript}" label="google-lead-conversion-script">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				    </aui:col>
				   
				    <aui:col md="6">
						<aui:input name="preferences--facebookLeadConversionScript--" type="textarea" value="${facebookLeadConversionScript}" label="facebook-lead-conversion-script">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				    </aui:col>
				    	
				   <aui:col md="6">
						<aui:input name="preferences--googleTagSnippet--" type="textarea" value="${googleTagSnippet}" label="google-tag-snippet">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="6">
						<aui:input name="preferences--facebookTagSnippet--" type="textarea" value="${facebookTagSnippet}" label="facebook-tag-snippet">
					   		<aui:validator name="required" errorMessage="this-field-is-required" />
				   		</aui:input>
				   </aui:col>
				   
				   <aui:col md="6">
				   		<aui:input name="preferences--showDownloadBrochure--" type="checkbox" value="${showDownloadBrochure}" />
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
				   
				   <aui:col md="12">
				   		<aui:input name="preferences--redirectionURL--" type="text" value="${redirectionURL}">
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