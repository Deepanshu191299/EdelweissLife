<div class="fund-equity250-header mt-4">
	<div class="container">
		<div class="fund-equity250-inner">
			<div class="fund-equity250-heading-box mb-3">						
				<div class="fund-equity250-content">
					<c:if test="${not empty customizeFeaturesCard.sectionTagLine}">
				    	<p class="d-cstm-block">${customizeFeaturesCard.sectionTagLine}</p>
				  	</c:if>
					<h5 class="recomendedFundsStrongTag fs24 fontsemi">${customizeFeaturesCard.sectionTitle}</h5>
					<p class="d-cstm-block">${customizeFeaturesCard.sectionSubTitle}</p>
					<c:if test="${not empty customizeFeaturesCard.shortNote}">
				    	<p class="cstm-block-note">${customizeFeaturesCard.shortNote}</p>
				  	</c:if>
				</div>
				
				<div class="fund-equity250-customize-wrapper">
				   <a href="javascript:void(0);" id="customizeFundLink" class="pointer customize-btn"><liferay-ui:message key="customize-funds" /></a>
				   <a href="javascript:void(0);" id="featuresLink" class="pointer" style="display:none;"><liferay-ui:message key="features" /></a>
				</div>
			</div>
			<%@ include file="/customize-funds.jsp"%>
			<%@ include file="/highcharts.jsp"%>
		</div>
	</div>
</div>