<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="in.edelweiss.common.contants.DateConstants"%>
<%@page import="in.edelweiss.common.util.DateFormatterUtil"%>
<%@page import="in.edelweiss.renewal.policy.util.RenewalPolicyApiUtil"%>
<%@page import="in.edelweiss.renewal.policy.web.constants.EdelweissRenewalPolicyWebPortletKeys"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="../init.jsp"%>

<%
String customerName = StringPool.BLANK;
if(Validator.isNotNull(renderRequest.getAttribute("customerName"))){
	customerName = (String)renderRequest.getAttribute("customerName");	
}

%>

<div class="container">
     <div class="row">
      <div class="col-lg-12 col-md-12 col-12">
<h1 class="fontbold fs28 py-3"><liferay-ui:message key="label-hi" /><%=" " + customerName%></h1>
<h1 class="fontbold fs28 pb-3"><liferay-ui:message key="label-continue-reaping-your-policy-benefits" /></h1>
<p class="fs18 txtGray mt-1 mb-2"><liferay-ui:message key="label-fill-in-the-below-information-to-renew-your-policy" /></p>
      </div>
      </div>
</div>
<%

	boolean status = false;
	if(Validator.isNotNull(renderRequest.getAttribute("status"))){
		status = (boolean) renderRequest.getAttribute("status");	
	}

	boolean isApiCalled=false;
	JSONArray customerDatajsonArray=null;
	JSONObject customerDatajsonObj = JSONFactoryUtil.createJSONObject();
	if (status) {
		 customerDatajsonArray = (JSONArray) renderRequest.getAttribute("customerDataObject");
	}else{
		 customerDatajsonObj = (JSONObject)renderRequest.getAttribute("customerDataObject");
		
		 if(customerDatajsonObj!=null){
			 isApiCalled= true;
		 } 
	}
%>
<aui:input type="hidden" value="<%=customerDatajsonObj %>"
			name="customerDatajsonObj"	id="customerDatajsonObj" />
<% 
	if (status) {
		for (int i = 0; i < customerDatajsonArray.length(); i++) {

			JSONObject customerDataObject = customerDatajsonArray.getJSONObject(i);
			String policyNumber = customerDataObject.getString("policyNumber");
			String dateOfBirth = customerDataObject.getString("dob");
			boolean isPolicyExpired= RenewalPolicyApiUtil.isPolicyExpired(customerDataObject.getString("paidToDate"), customerDataObject.getString("planType"));
			double amountDue = Double.parseDouble(customerDataObject.getString("amountDue"));
			NumberFormat formatter=NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
			String amount = formatter.format(amountDue);
			if(Validator.isNotNull(amount) && !amount.isEmpty() 
					&& amount.contains(".")){
				amount = amount.split("\\.")[1];
			}
%>
<style>
.summary-main-wrapper .summary-title {
    width: auto;
    display: inline-block;
}

@media (max-width: 767px) {
    .edto-summary-tiles span {
        font-size: 12px;
    }
}
</style>

<liferay-portlet:actionURL var="submitRenewalFormURL"
	name="<%=EdelweissRenewalPolicyWebPortletKeys.SUBMIT_RENEWAL_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<div class="container mt-2">
<div class="summary-main-wrapper">
                    <div class="summary-title"><%=customerDataObject.get("planDescription")%></div>
                    <div class="edto-summary-tiles m-0">
		<aui:form name="renewal-form" id="renewalPolicy"
			action="<%=submitRenewalFormURL %>" method="post"
			enctype="multipart/form-data" onSubmit="event.preventDefault();">
			<input type="hidden" id="portletNamespace"
				value="<portlet:namespace/>" />
			<aui:input type="hidden" value="${submitRenewalFormURL}"
				name="submitRenewalFormURL" />
				<aui:input type="hidden" value="<%=policyNumber %>"
				name="policyNumber" />
			
			<div class="row">
				<div class="col-md-4 col-12">
					<div>
					<span class="fs16 txtGray pr-3">	<liferay-ui:message key="label-policy-number" /></span>
						<span><%=customerDataObject.get("policyNumber")%></span>
					</div>
				    <div>
					<span class="fs16 txtGray pr-3">
						<liferay-ui:message key="label-policy-due-date" /></span>
						<span><%=DateFormatterUtil.parseDateToSpecificFormat((String)customerDataObject.get("paidToDate"), DateConstants.SLASH_DD_MM_YYYY) %></span>
					</div>
				</div>
				<div class="col-md-5 col-12">
				    <div>
					<span class="fs16 txtGray pr-3">
						<liferay-ui:message key="label-policy-status" /></span>
						<span><%=customerDataObject.get("status")%></span>
					</div>
					<div class="d-flex d-md-block mt-1 mt-md-0">
						<span class="fs16 txtGray pr-3">
							<liferay-ui:message key="label-email-address" />
						</span>
						<span class="wb-bw"><%=customerDataObject.get("emailId") %></span>
					</div>
				</div>
				<div class="col-md-3 col-12 pr-lg-0 align-self-center mt-3 mt-md-0">
				
			<aui:button-row>
					<aui:button
						cssClass="edto-btn-primary btn-outline-primary justify-content-center"
						id="" disabled="<%=Boolean.valueOf(isPolicyExpired)%>"
						value='<%=LanguageUtil.get(Locale.US, "label-pay-now")+" "+LanguageUtil.get(Locale.US, "label-rupee")+" "+ amount%>' 
						onClick='<%="renewalFormSubmit('renewal-form', '"+policyNumber+"','"+dateOfBirth+"');" %>' ></aui:button>
				</aui:button-row>
				</div>
			</div>				
		</aui:form>
	</div>
</div>
</div>
<%
	}
	}
%>
<div class="modal wealth-modal" tabindex="-1" role="dialog"
	id="staticBackdrop">
	<div class="modal-dialog role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onClick="closeModal(false);"> 
				 <span aria-hidden="true">&times;</span>
				 </button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-4">
						<div class="text-center">
							<h5 id="responseMessage"></h5>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closeModal(false);" >
							<liferay-ui:message key="label-done" />
						</button>
					 </div>
				 </div>
			</div>
		</div>
	</div>
</div>

<!-- Image loader -->
<div id='<portlet:namespace/>loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<!-- Image loader -->

<script>

$(document).ready(function () {
	var status ="<%=status%>" ;
	var isApiCalled ="<%=isApiCalled%>" ;
	
    var customerJson = JSON.parse($("#<portlet:namespace/>customerDatajsonObj").val());
    
    console.debug(customerJson);
    
    if(isApiCalled && status!="true"){
  		
		$("#responseMessage").html(customerJson.message);
		$('#'+ namespace + 'loader').hide();
		$('#staticBackdrop').modal('show');
		return false;
	}
 	else if(!status && !isApiCalled){
		console.debug(status);
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		$('#'+ namespace + 'loader').hide();
		$('#staticBackdrop').modal('show');
		return false;
	}
    
});

var namespace=$("#portletNamespace").val();
function renewalFormSubmit(liferayFormId, policyNumber, dateOfBirth){
	
		$('#'+ namespace + 'loader').show();
		
		var form = new FormData();
		form.append(namespace + "policyNumber", policyNumber);
		form.append(namespace + "dateOfBirth", dateOfBirth);
		
		var url = $("#"+namespace+"submitRenewalFormURL").val()

		Liferay.Util.fetch(url, {
			body: form,
			method: 'POST',
		})

		.then((response) => {
			return response.json();
		})

		.then((data) => {
			showPopUp(data);
		})
		
		.catch(function(error) {
			console.debug(error);
			$("#responseMessage").html(Liferay.Language.get("label-please-try-after-some-time"));
			$("#closeBtn").attr("onclick","closeModal(true)");
			$("#okayBtn").attr("onclick","closeModal(true)");
			$('#'+ namespace + 'loader').hide();
			$('#staticBackdrop').modal('show');
		});	
}

function showPopUp(validationResponse){

	console.log(validationResponse)
	$("#responseMessage").html('');
	if(validationResponse.isStatusApproved){
		console.debug(validationResponse.responseData);
		window.location.href = validationResponse.responseData
	}
	else if(validationResponse != null && !validationResponse.isStatusApproved){
		console.debug("false - API Exception");
		$("#responseMessage").html(validationResponse.responseData);
		$('#'+ namespace + 'loader').hide();
		$('#staticBackdrop').modal('show');
		return false;
	}
	else if(validationResponse != null && validationResponse.internalError){
		console.debug("false - Internal Error Occured");
		$("#responseMessage").html(Liferay.Language.get("label-internal-error-occured"));
		$('#'+ namespace + 'loader').hide();
		$('#staticBackdrop').modal('show');
		return false;
	}
}

function closeModal(redirect){
	if(redirect){
		window.location.href="/";
	}else{
		$('#staticBackdrop').modal('hide');
	}
}
</script>