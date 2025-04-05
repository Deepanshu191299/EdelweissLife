<%@ include file="init.jsp"%>

<portlet:resourceURL id="/add_lead" var="lmsURL"></portlet:resourceURL>
<liferay-portlet:renderURL varImpl="bookmarkURL"></liferay-portlet:renderURL>	
<c:choose>
	<c:when test="${not empty formView && not empty formView.fields}">
		<div class="lead-gen-form-wrapper">
			<button type="button" class="close" id="closeForm-btn">
				<span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
						<path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
					</svg>
				</span>
			</button>
	       <%@ include file="/form-header.jsp"%>
			<form name="generalForm" id="generalForm"
				method="POST">
				  <%@ include file="/form.jsp"%>
			</form>
		</div>
		<div class="lead-gen-mob-btn-wrapper p-3 text-center">
			<a class="edto-btn-primary mt-0" href="javascript:;" id="get-started">Get Started</a>
     	</div>
	</c:when>
		<c:otherwise>
			<liferay-ui:message key="form.configuration.issue" />
		</c:otherwise>
</c:choose>

 <%@ include file="/modal.jsp"%>

<script type="text/javascript">
	var productName = '${productName}';
	var blogsURL = '${lmsURL}';
	var resuourceURL = blogsURL.replace('/b/','/blogs/');
	var minAge = '${minAge}';
	var maxAge = '${maxAge}';
	
	$("#get-started").click(function() {
	    $(".lead-gen-mob-btn-wrapper").hide();
	    $(".lead-gen-form-wrapper").show();
	});
	
	$("#closeForm-btn").click(function() {
	    $(".lead-gen-form-wrapper").hide();
	    $(".lead-gen-mob-btn-wrapper").show();
	});
</script>
