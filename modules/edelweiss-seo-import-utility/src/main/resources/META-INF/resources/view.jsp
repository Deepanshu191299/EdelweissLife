<%@ include file="init.jsp" %>
<portlet:actionURL name="updateLayoutSeo" var="actionURL" />
<div class="container px-5 ippb-bg">
	<div class="row mt-5">
		<div class="col-md-10">
			<aui:form method="post" action="${actionURL}" enctype="multipart/form-data">
				<aui:select id="actionType" name="actionType" label="Action Type">
					<aui:option selected="true" disabled="true" value="0">Select</aui:option>
					<aui:option value="update-layout-seo">Update Layout SEO</aui:option>
				</aui:select>
				<aui:input type="file" id="uploadMasterData" name="uploadMasterData" label="Upload Master Data" />
				<aui:button type="submit" name="uploadMasterDataBtn" id="uploadMasterDataBtn" value="Submit"/>
			</aui:form>
		</div>
	</div>
</div>