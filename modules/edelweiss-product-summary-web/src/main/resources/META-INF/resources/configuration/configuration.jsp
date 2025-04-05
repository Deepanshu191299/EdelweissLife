<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="/init.jsp" %>

<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />
<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="configurationRenderURL" />

<liferay-frontend:edit-form
    action="<%= configurationActionURL %>"
    method="post"
    name="fm"
    id="fm"
>
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= configurationRenderURL %>" />
    <liferay-frontend:edit-form-body>
        <liferay-frontend:fieldset>
			<liferay-ui:tabs names="Product Configurations,tab2" refresh="false" tabsValues="Product Configurations,tab2" >
			    <%@ include file="/configuration/productCodeConfig.jsp" %>
			    <liferay-ui:section>
			        Other Configuration If Required Add Here
			    </liferay-ui:section>
			</liferay-ui:tabs>
		</liferay-frontend:fieldset>
    </liferay-frontend:edit-form-body>

    <liferay-frontend:edit-form-footer>
        <aui:button type="submit" />
        <aui:button type="cancel" />
    </liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

<script>
    $(document).ready(function() {
        $('.number').on('keypress', function (e) {
            // Allow only numbers (0-9)
            var charCode = (e.which) ? e.which : e.keyCode;
            if (charCode < 48 || charCode > 57) {
                e.preventDefault();
            }
        });
    });
</script>

<style>
#<portlet:namespace />fm{
    padding: 0px !important;
}
</style>