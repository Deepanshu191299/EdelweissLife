<%@page import="java.text.NumberFormat"%>
<%@page import="in.edelweiss.proposal.form.pf.model.ProductDetails"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%><%@
taglib
	uri="http://liferay.com/tld/portlet" prefix="liferay-portlet"%><%@
taglib
	uri="http://liferay.com/tld/theme" prefix="liferay-theme"%><%@
taglib
	uri="http://liferay.com/tld/ui" prefix="liferay-ui"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<liferay-theme:defineObjects />
<portlet:defineObjects />
<%@page import="com.liferay.portal.kernel.util.Validator"%>

<%@ include file="/proposal_form/cis_handler.jsp" %>
<%
	/* HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
	String transactionId = httpReq.getParameter("transactionId"); */
	String transactionId = (String) renderRequest.getAttribute("transactionId");
%>

<!-- <div class="preloader show--loader"></div>
<div class="preloader" id="preloader"></div>-->
<section class="section-thankyou">

	<div class="thankyou-container container pt-5">


		<div class="policy mb-3 mt-5">
			Policy Number: <a href="#">${commonDetails.policyNo}</a>
		</div>

		<div class="thankyou-banner row">

			<div class="col-md-8 col-8 premier-g-income">
				<div class="thankyou-header mb-3">
					<h2 class="mb-4">
						<liferay-ui:message key="almost-there-just-a-few-more-steps-to-go" />
					</h2>
					<p>
						<liferay-ui:message
							key="download-your-application-form-and-benefit-illustration-to-proceed-to-customer-verification" />
					</p>
				</div>

				<div class="thankyou-product-details mb-5">
					<p class="mb-3 product-name">
						<liferay-ui:message key="edelweiss-tokio-life" />
						<span id="product-name">${productDetails.productName.replaceAll("Tokio ","")}</span>
					</p>
					<div class="product-details">

						<div class="details">
							<div>
								<img src="<%=request.getContextPath()%>/images/thankyou1.png" />
							</div>
							<div>
								<label><liferay-ui:message key="sum-assured" /></label>
								<p>
									&#x20B9;
									<fmt:formatNumber value="${productDetails.sumAssured}"
										pattern="#,##0" />
								</p>
							</div>
						</div>
						<div class="details">
							<div>
								<img src="<%=request.getContextPath()%>/images/thankyou2.png" />
							</div>
							<div>
								<label><liferay-ui:message key="mode-of-payment" /></label>
								<p>${productDetails.premiuPaymentFrequency}</p>
							</div>



						</div>
						<div class="details">

							<div>
								<img src="<%=request.getContextPath()%>/images/thankyou3.png" />
							</div>
							<div>
								<label><liferay-ui:message key="payment-term" /></label>
								<p>${productDetails.premiumPaymentTerm}</p>
							</div>


						</div>
						<div class="details">

							<div>
								<img src="<%=request.getContextPath()%>/images/thankyou4.png" />
							</div>
							<div>
								<label><liferay-ui:message key="policy-term" /></label>
								<p>${productDetails.policyTerm}</p>
							</div>


						</div>

					</div>

				</div>

				<div class="thankyou-product-pdf">

					<a target="_blank" href="${productDetails.proposalFormPath}"
						onclick="downloadPdf('P')" id="applicationFormPdf" class="mr-5">
						<img src="<%=request.getContextPath()%>/images/download-icon.svg"
						alt="download" /> <liferay-ui:message key="application-form" />
					</a> <a target="_blank" href="${productDetails.pdfPath}"
						onclick="downloadPdf('B')" id="benefitIllustrationPdf"> <img
						src="<%=request.getContextPath()%>/images/download-icon.svg"
						alt="download" /> <liferay-ui:message key="benefit-illustration" />
					</a> <input id="transaction-Id" type="hidden" name="transactionId"
						value="<%=transactionId%>"></input> <a href="javascript:void(0);"
						onclick="downloadPaymentAck();" id="paymentAcknowledgementPdf">
						<img src="<%=request.getContextPath()%>/images/download-icon.svg"
						alt="download" /> <liferay-ui:message key="payment-ack" />
					</a>
					<a id="downloadCIS" href="javascript:void(0);" onclick="downloadCISPdfFromBackend()"><img class="pr-2" src="/o/edelweisstokio-theme/images/praposal/download-icon.svg" alt="icon"> CIS PDF</a>
					

				</div>
				<div class="thankyou-product-pdf mt-3">
					<c:if test="${not empty commonDetails.getCovidLaUrl()}">
						<a target="_blank" href="${commonDetails.getCovidLaUrl()}"
							onclick="downloadPdf('LACQ')" id="laCovidQuestionnairePdf"
							class="mr-2"> <img
							src="<%=request.getContextPath()%>/images/download-icon.svg"
							alt="download" /> <liferay-ui:message
								key="label-la-covid-questionnaire" />
						</a>
					</c:if>

					<c:if test="${not empty commonDetails.getCovidPrUrl()}">
						<a target="_blank" href="${commonDetails.getCovidPrUrl()}"
							onclick="downloadPdf('PRSPCQ')" id="prSpCovidQuestionnairePdf">
							<img src="<%=request.getContextPath()%>/images/download-icon.svg"
							alt="download" /> <liferay-ui:message
								key="label-pr-sp-covid-questionnaire" />
						</a>
					</c:if>
				</div>
				<!-- ChildProduct Details Start -->
				<c:if
					test="${productDetails.productName eq 'Wealth Rise+' && childProduct.productName eq 'Guaranteed Savings STAR'}">
					<div class="thankyou-product-details mb-5">
						<p class="mb-3 product-name">
							<liferay-ui:message key="edelweiss-tokio-life" />
							<span id="product-name">${childProduct.productName}</span>
						</p>
						<div class="product-details">
							<div class="details">
								<div>
									<img src="<%=request.getContextPath()%>/images/thankyou1.png">
								</div>
								<div>
									<label><liferay-ui:message key="sum-assured" /></label>
									<p>
										&#x20B9;
										<fmt:formatNumber value="${childProduct.sumAssured}"
											pattern="#,##0" />
									</p>
								</div>
							</div>
							<div class="details">
								<div>
									<img src="<%=request.getContextPath()%>/images/thankyou2.png" />
								</div>
								<div>
									<label><liferay-ui:message key="mode-of-payment" /></label>
									<p>${childProduct.premiuPaymentFrequency}</p>
								</div>
							</div>
							<div class="details">
								<div>
									<img src="<%=request.getContextPath()%>/images/thankyou3.png" />
								</div>
								<div>
									<label><liferay-ui:message key="payment-term" /></label>
									<p>${childProduct.premiumPaymentTerm}</p>
								</div>
							</div>
							<div class="details">
								<div>
									<img src="<%=request.getContextPath()%>/images/thankyou4.png" />
								</div>
								<div>
									<label><liferay-ui:message key="policy-term" /></label>
									<p>${childProduct.policyTerm}</p>
								</div>
							</div>
						</div>
					</div>
					<!-- Child Product Details End -->
					<!--  Download pdf Start -->
					<div class="thankyou-product-pdf">
						<a target="_blank" href="${childProduct.proposalFormPath}"
							onclick="downloadPdf('P')" id="applicationFormPdf" class="mr-5">
							<img src="<%=request.getContextPath()%>/images/download-icon.svg"
							alt="download" /> <liferay-ui:message key="application-form" />
						</a> <a target="_blank" href="${childProduct.pdfPath}"
							onclick="downloadPdf('B')" id="benefitIllustrationPdf"> <img
							src="<%=request.getContextPath()%>/images/download-icon.svg"
							alt="download" /> <liferay-ui:message key="benefit-illustration" />
						</a>
					</div>
					<!-- Download Pdf End -->
				</c:if>
				<div class="thankyou-product-amount mt-6">
					<c:choose>
						<c:when
							test="${productDetails.productName eq 'Wealth Rise+' && childProduct.productName eq 'Guaranteed Savings STAR'}">
							<p class="mb-3">
								<liferay-ui:message key="total-premium-amount" />
								: &#x20B9;<span id="total-amount" class="thank-you-page-amount">${productDetails.totalPremiumAmount + childProduct.totalPremiumAmount}
								</span>
							</p>
						</c:when>
						<c:otherwise>
							<p class="mb-3">
								<liferay-ui:message key="total-premium-amount" />
								: &#x20B9;<span id="total-amount" class="thank-you-page-amount">${productDetails.totalPremiumAmount}
								</span>
							</p>
						</c:otherwise>
					</c:choose>
					<c:if test="${(commonDetails.nriGst eq 'Y')}">
						<p>
							<strong>Note : </strong>You can claim your GST Refund post
							issuance of policy. Refund for GST will be eligible only upon
							fulfilment of all criteria applicable for NRI GST Refund
						</p>
						<br />
					</c:if>
					<button class="btn" disabled="disabled" id="thankyouBtn"
						onclick="thankyou()">
						<liferay-ui:message key="thankyou-continue" />
					</button>

				</div>

			</div>

		</div>

	</div>



</section>

<%@ include file="/proposal_form/error_modal.jsp"%>

<aui:script>
var proposalFormPdf = false;
var biPdf = false;

var laCovidQuestionnairePdf = ${empty commonDetails.getCovidLaUrl() ? true : false};
var prSpCovidQuestionnairePdf = ${empty commonDetails.getCovidPrUrl() ? true : false};

function downloadPdf(val){

	if(val == 'P'){
	   proposalFormPdf = true;
	}
	
	if(val == 'B'){
	  biPdf = true;
	}

	if(val == 'LACQ'){
		laCovidQuestionnairePdf = true;
	 }
	 
	 if(val == 'PRSPCQ'){
		prSpCovidQuestionnairePdf = true;
	 }
	
	if(proposalFormPdf && biPdf && laCovidQuestionnairePdf && prSpCovidQuestionnairePdf){
		$('#thankyouBtn').prop('disabled', false);
	}

}


function thankyou(){

$(".preloader").removeClass("show--loader");

var data = {
	application_number : '${commonDetails.applicationNumber}'
}

    Liferay.Util.fetch('${savethankyouURL}',{
		method: 'POST',
		headers: new Headers({
	      "Content-Type": "application/json",
	    }),
		body: JSON.stringify(data),
		})
		.then((response) => response.json())
		.then((response) => {
		    $(".preloader").addClass("show--loader");
			if(response?.status){
			   Liferay.Util.navigate('${appTrackerUrl}')
			}else{
			    $('.error-msg').html(response.errors[0])
               $('#lmsModal').modal({backdrop: 'static', keyboard: false}, 'show'); 
			}
		}).catch((e) => {
		  $(".preloader").addClass("show--loader");
		   $('.error-msg').html('Error while processing your request. Please contact our support team if the problem persists.')
           $('#lmsModal').modal({backdrop: 'static', keyboard: false}, 'show'); 
		   console.log(e);
		})
			
}

$("#total-amount").html(Math.round($("#total-amount").html()));

</aui:script>
<script>

$(document).ready(function() {  
 // Get the span element
var spanElement = $('.thank-you-page-amount'); 
 // Get the text content of the span
var originalText = spanElement.text();  
 // Remove any commas from the original text
var originalNumber = originalText.replace(/,/g, '');  
 // Parse the original number
var parsedNumber = parseInt(originalNumber);   
// Format the number with commas
var formattedNumber = parsedNumber.toLocaleString();   
// Set the formatted text back to the span 
spanElement.text(formattedNumber); });

	function downloadPaymentAck() {
		$
				.ajax({
					url : "https://buyonlineapiuat.edelweisslife.in/api/v1/services/paymentpdf",
					method : "post",
					data : JSON.stringify({
						transactionId : document
								.getElementById("transaction-Id").value
					}),
					headers : {
						'Content-Type' : 'application/json'
					},
					xhrFields : {
						responseType : 'blob'
					},
					success : function(blob) {
						var link = document.createElement('a');
						link.href = window.URL.createObjectURL(blob);
						link.download = "Payment_Acknowledgement.pdf";
						link.click();
					}
				});
	}
</script>