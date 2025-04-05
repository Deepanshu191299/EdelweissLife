<%@page import="in.edelweiss.common.contants.DateConstants"%>
<%@ include file="/init.jsp"%>

<link href="<%=request.getContextPath()%>/js/jquery-ui.css"
	rel="stylesheet">

<style>
.show--loader {
	display: none;
}
.custom-accord-wrappper .btn-link:hover {
    text-decoration: none;
}
</style>

<div class="preloader show--loader"></div>

<div id="<portlet:namespace />renderPfcontainer">
</div>

<aui:script>
   function showLoader(){
	   $(".preloader").removeClass("show--loader");
   }
   
   function hideLoader(){
	   $(".preloader").addClass("show--loader");
   }
</aui:script>

<aui:script require="frontend-js-web/index as frontendJsWeb">
   var {runScriptsInElement} = frontendJsWeb;
   function renderPfForm(){
   
      showLoader();
      
	  <liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/pf/render_pf_form" var="renderPFFormURL">
	  </liferay-portlet:resourceURL>
	  
	  var renderPfcontainer = document.querySelector('#<portlet:namespace />renderPfcontainer');
	  
	  Liferay.Util.fetch('${renderPFFormURL}')
				.then((response) => {
					return response.text();
				})
				.then((response) => {
					renderPfcontainer.innerHTML = response;
					runScriptsInElement(renderPfcontainer);
					hideLoader();
					
				})
				.catch((e) => {
					console.log(e);
					hideLoader();
				})

   }
   
   
renderPfForm();
</aui:script>