<%@ include file="init.jsp"%>
<portlet:resourceURL id="/save/GeneratePolicy" var="generatePolicyURL" />
<div class="preloader" style="display:none;"></div>
<div class="edto-claim-philosophy-wrapper">
	<div class="container">
		<div class="claim-philosophyy-box summary-claim">
			<div class="col-lg-5 col-md-5 col-sm-12 col-12 p-0">
				<div class="philosophy-text">
					<p><liferay-ui:message key="kindly-proceed-towards-making-the-payment" /></p>
				</div>
			</div>
			<div class="col-lg-4 col-md-4 col-sm-12 col-12 p-0">
				<div class="premium-text">
					<h5><liferay-ui:message key="premium-amount" /></h5>
					<h1 id="<portlet:namespace/>premiumAmountContainer">
						<liferay-ui:message key="rupee-sign" />
						<c:choose>
							<c:when test="${(summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == fspProductCode)
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == gisProductCode)
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40048')
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == pgsProductCode)
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40007')
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40035')
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40003')
											 || (summaryData.customerInvestmentDetailsRel[0].paymentFrequency == 4 && productCode == '40039')}">
								<span id="<portlet:namespace/>premiumAmount" 
									data-amount='${summaryData.customerInvestmentDetailsRel[0].premiumAmount!=""?summaryData.customerInvestmentDetailsRel[0].premiumAmount*2:0}' class=""></span>
							</c:when>
							<c:otherwise>
								<span id="<portlet:namespace/>premiumAmount" data-amount="${summaryData.customerInvestmentDetailsRel[0].premiumAmount}" class=""></span>
							</c:otherwise>
						</c:choose>
					</h1>
					<a href="javascript:void(0);" id="<portlet:namespace/>premiumBreakUpAnchor"><liferay-ui:message
							key="break-up" /> <span><i class="fas fa-chevron-down"></i></span></a>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-12 col-12 pr-0 pl-0">
				<div class="letter-form">
					<button id="proceedSummary" class="edto-btn-primary">
						<liferay-ui:message key="proceed" />
					</button>
				</div>
			</div>
		</div>
	</div>
	<div class="premium-box none" id="breakupDiv">
		<ul>
			<li><liferay-ui:message key="premium-amount-non-col" /> <span
				id="premiumAmountCol"><liferay-ui:message key="rupee-sign" />
			</span></li>
			<li><liferay-ui:message key="total-amount-premium" /> <span
				id="totalPremium"><liferay-ui:message key="rupee-sign" /> </span></li>
		</ul>
	</div>
</div>

<div class="modal fade" id="summaryPopupModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true" style="display:none;">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<!-- Close button at the top-right corner -->
			<button type="button" class="close" data-dismiss="modal" aria-label="Close" style="position: absolute;top: 0px;right: 0px;background-color: #fff;padding: 10px 15px;z-index: 2060 !important;!i;!;box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);">
				<span aria-hidden="true" style="font-size: 24px;">×</span>
			</button>
			<div class="modal-body">
				<div class="wealth-form-box text-center w-100">
					<h4 class="text-center mb-3 error-msg" id="summaryPopupModalMsg"></h4> 
				</div>
			</div>
			<div class="modal-footer justify-content-center">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<aui:script>
$('#proceedSummary').click(function(){
	showLoader();
	Liferay.Util.fetch('${generatePolicyURL}',{
	method: 'POST',
	headers: new Headers({
      "Content-Type": "application/json",
    }),
	}).then((response)=> response.json())
	.then((response)=> {
	    if(response?.status){
	        window.location.href = response?.pfRedirectURL;
	    }else{
	    	hideLoader();
	    	openPopupModal(response?.message);
	    }
	}).catch((error)=>{
		hideLoader();
		openPopupModal("Error while processing your request. Please contact our support team if the problem persists.");
	  	console.log('error...',error);
	});
});
var showLoader = () => {
	$('.preloader').show();
};

var hideLoader = () => {
	$('.preloader').hide();
};

var openPopupModal = (msg) => {
	$("#summaryPopupModal").modal('show');
	$('#summaryPopupModalMsg').text(msg);
};
</aui:script>
<%@ include file="premium-amount-breakup-model.jsp"%>