<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>
	<meta content="initial-scale=1.0, width=device-width" name="viewport" />
	
	<link rel="shortcut icon" type="image/x-icon" href="/o/edelweisstokio-theme/images/favicon.ico" />
	<@liferay_util["include"] page=top_head_include />
	<meta name="description" content="ak" />
	
	<link rel="stylesheet" href="${css_folder}/plugins/owl.carousel.min.css" type="text/css">
	<link rel="stylesheet" href="${css_folder}/plugins/owl.theme.default.min.css" type="text/css">
	<link rel="preload" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" as="style" onload="this.onload=null;this.rel='stylesheet'">
    <noscript><link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css"
	integrity="sha384-twcuYPV86B3vvpwNhWJuaLdUSLF9+ttgM2A6M870UYXrOsxKfER2MKox5cirApyA" crossorigin="anonymous"></noscript>
	
	<#assign curURLTheme = themeDisplay.getLayout().getFriendlyURL() />
	<#if curURLTheme?lower_case != "/home">
		<link rel="stylesheet" href="${css_folder}/plugins/font-awesome.css" type="text/css">
		<link rel="stylesheet" href="${css_folder}/plugins/highcharts.css" type="text/css">
		
		<link rel="stylesheet" href="${css_folder}/plugins/select2.min.css" type="text/css">
		<link rel="stylesheet" href="${css_folder}/plugins/slick.css" type="text/css">
	</#if>
	<!-- Google Tag Manager -->

<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':

new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],

j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=

'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);

})(window,document,'script','dataLayer','GTM-WB3HVTC');</script>

<!-- End Google Tag Manager -->

<script type="text/javascript">
    (function(c,l,a,r,i,t,y){
        c[a]=c[a]||function(){(c[a].q=c[a].q||[]).push(arguments)};
        t=l.createElement(r);t.async=1;t.src="https://www.clarity.ms/tag/"+i;
        y=l.getElementsByTagName(r)[0];y.parentNode.insertBefore(t,y);
    })(window, document, "clarity", "script", "kfx1vyhfis");
</script>
</head>

<body id="body" class="${css_class}">
<!-- Google Tag Manager (noscript) -->

<noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-WB3HVTC"

height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>

<!-- End Google Tag Manager (noscript) -->

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="edelweisstokio-portal position-relative" id="wrapper">
	<section id="content">
		<h2 class="hide-accessible sr-only" role="heading" aria-level="1">${the_title}</h2>

		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />
	
	<script src="${javascript_folder}/plugins/owl.carousel.min.js" ></script>
	<#--  <script src="${javascript_folder}/plugins/slick.min.js"></script>  -->
	<#--  <script src="${javascript_folder}/plugins/select.js"></script>  -->
	
	<script src="${javascript_folder}/plugins/moment.min.js" ></script>
	<#if curURLTheme?lower_case != "/home">
		<script src="${javascript_folder}/plugins/jquery.bxslider.min.js" ></script>
		<script src="${javascript_folder}/plugins/select2.min.js" ></script>		
		<script src="${javascript_folder}/plugins/jquery.inputmask.min.js" ></script>		
		<script src="${javascript_folder}/plugins/jquery.validate.min.js" ></script>		
		<#-- <script src="${javascript_folder}/plugins/highcharts.js" async></script>
		  <script src="${javascript_folder}/plugins/state.js"></script>  -->
		<script id='_webengage_script_tag' src="${javascript_folder}/web_engage/web_engage.js"></script>
		<script src="${javascript_folder}/plugins/email-domain-auto-complete.js" ></script>
	</#if>
	<script src="${javascript_folder}/plugins/custom.js" ></script>
	<script src="${javascript_folder}/plugins/custom-liferay.js" ></script>
</body>

</html>