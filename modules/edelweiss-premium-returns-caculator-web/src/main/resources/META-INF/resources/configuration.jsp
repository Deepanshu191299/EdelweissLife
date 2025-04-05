<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@page import="com.liferay.portal.kernel.service.ClassNameLocalServiceUtil"%>
<%@page import="com.liferay.journal.service.JournalArticleLocalServiceUtil"%>
<%@page import="com.liferay.journal.model.JournalArticle"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.dynamic.data.mapping.model.DDMStructure"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil"%>
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
			
			        <aui:input name="preferences--showGraph--" type="checkbox" value="<%=showGraph %>" />
                    <%
                       List<DDMStructure>  structures = DDMStructureLocalServiceUtil.search(themeDisplay.getCompanyId(), new long[]{themeDisplay.getScopeGroupId()}, ClassNameLocalServiceUtil.getClassNameId(JournalArticle.class), "Premium Returns Calculator", 0, 0, 2, null);
                       if(Validator.isNotNull(structures) && !structures.isEmpty()){
                    	   DDMStructure structure = structures.get(0);
                    	  
                    	   List<JournalArticle>  journalArticles = JournalArticleLocalServiceUtil.getArticlesByStructureId(themeDisplay.getScopeGroupId(), structure.getStructureKey(), 0, -1, -1, null);
                    %>
							<aui:select name="preferences--articleId--">
							  <% for(JournalArticle journalArticle : journalArticles){ %>
							     <aui:option value="<%=journalArticle.getArticleId() %>" label="<%=journalArticle.getTitle(locale) %>" selected="<%=articleId.equals(journalArticle.getArticleId())%>"></aui:option>
							 <%} %>
							</aui:select>

                   <%}else{ %>
                        <h1>No Structure is available with name Premium Returns Calculator </h1>
                   <%} %>

			</liferay-frontend:fieldset>
	</liferay-frontend:edit-form-body>

	<liferay-frontend:edit-form-footer>
		<aui:button type="submit" />

		<aui:button type="cancel" />
	</liferay-frontend:edit-form-footer>
</liferay-frontend:edit-form>

