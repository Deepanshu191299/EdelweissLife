<%@page import="com.liferay.portal.kernel.util.HashMapBuilder"%>
<%@ include file="init.jsp" %>


<div class="container-view">
    
    <react:component
			module="js/FundsResource"
			props='<%=
				HashMapBuilder.<String, Object>put("type", 0
						)
				.build()
			%>'
		/>
    
</div>