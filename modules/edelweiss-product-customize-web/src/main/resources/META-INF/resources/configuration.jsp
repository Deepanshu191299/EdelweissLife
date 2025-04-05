<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@ include file="init.jsp" %>

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
            <aui:row>
                <aui:col md="6">
					<aui:input name="preferences--gssProductCode--" type="text" value="${gssProductCode}" id="gssProductCode" label="Gss-Product-Code">
						<aui:validator name="required" errorMessage="Please enter gss product code"/>
						<aui:validator name="custom" errorMessage="Please enter numbers only">
							function(val){
							 	var charCode = (val.which) ? val.which : val.keyCode;
					            if (charCode < 48 || charCode > 57) {
					                return false;
					            }else{
					            	return true;
					            }
							}
						</aui:validator>
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

<script>
    $(document).ready(function() {
        $('#<portlet:namespace />gssProductCode').on('keypress', function (e) {
            // Allow only numbers (0-9)
            var charCode = (e.which) ? e.which : e.keyCode;
            if (charCode < 48 || charCode > 57) {
                e.preventDefault();
            }
        });
    });
</script>