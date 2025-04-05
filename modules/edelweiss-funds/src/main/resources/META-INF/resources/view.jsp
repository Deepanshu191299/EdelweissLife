<%@page import="com.liferay.portal.kernel.util.HashMapBuilder"%>
<%@ include file="init.jsp" %>

<style>
.funds-card-wrapper .owl-carousel .owl-stage {
	margin: auto;
}
@media (max-width: 768px) {
	.testi-slider .owl-nav .owl-prev {left: 11%;}
	.testi-slider .owl-nav .owl-next {right: 11%;}
}
</style>

<div class="container-view container">
    
    <react:component
			module="js/FundsResource"
			props='<%=
				HashMapBuilder.<String, Object>put("type", 1
						)
				.build()
			%>'
		/>
    
</div>

