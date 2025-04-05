<%@ include file="../init.jsp"%>

<liferay-portlet:resourceURL
	id="<%=FundStatementPortletKeys.DOWNLOAD_URL_MVC_RESOURCE_COMMAND%>"
	var="downloadURLFromID" copyCurrentRenderParameters="false" />
	
<%

	JSONObject customerJSONObject = JSONFactoryUtil.createJSONObject();
	int status = 0;
	int ulipPlans = 0;
	if (Validator.isNotNull((Integer) renderRequest.getAttribute("status"))) {
		status = (Integer) renderRequest.getAttribute("status");
	}

	if (Validator.isNotNull(status) && status == 200) {
		JSONArray customerJSONArray = (JSONArray) renderRequest.getAttribute("customerJSONArray");
		if (Validator.isNotNull(customerJSONArray) && customerJSONArray.length() > 0) {
			for(int i = 0; i < customerJSONArray.length(); i++){
				customerJSONObject = customerJSONArray.getJSONObject(i);
				String policyNumber = customerJSONObject.getString("policyNumber");
				String formName = policyNumber + "-fund-statement-form";
				String formStep = policyNumber + "_fund_statement_step";
				String fundStatementStartDate = customerJSONObject.getString("contractIssueDate");
				String planType = (Validator.isNull(customerJSONObject.get("planType")) || customerJSONObject.getString("planType").isEmpty()) == false ? customerJSONObject.getString("planType") : "null";
				if(planType.equalsIgnoreCase("ULIP")){
					ulipPlans = ulipPlans + 1;
					
%>
<liferay-portlet:actionURL var="submitDownloadFundStatementFormURL"
	name="<%=FundStatementPortletKeys.SUBMIT_FUND_STATEMENT_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
<div class="fieldWrap">
	<aui:form name='<%=formName%>'
		action="<%=submitDownloadFundStatementFormURL%>" method="post"
		enctype="multipart/form-data" onSubmit="event.preventDefault();">
		<input type="hidden" id="fundPortletNamespace"
			value="<portlet:namespace/>" />
		<aui:input type="hidden" value="${submitDownloadFundStatementFormURL}"
			name="submitDownloadFundStatementFormURL" />
		<aui:input type="hidden" value="${downloadURLFromID}"
			name="downloadURLFromID" />
		<aui:input type="hidden" value="<%=fundStatementStartDate%>"
			name="fundStatementStartDate" />
		<aui:input type="hidden" name="customerJSONObject"
					value="<%=String.valueOf(customerJSONObject)%>" />
		<div class="application_step" id='<%=formStep%>'>

			<div class="edto-covid-teat-wrappper">
				<div class="container">
					<div class="summary-main-wrapper mb-0">
						<div class="summary-title"><%=customerJSONObject.getString("planDescription")%></div>
						<div class="edto-summary-tiles">
							<form>
								<div class="row">
									<div class="col-lg-12 col-12">
										<div class="row">
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-policy-number" /></p>
													<span> <%=customerJSONObject.getString("policyNumber")%></span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-policy-holders-dob" /></p>
													<span><%=DateFormatterUtil.parseDateToSpecificFormat(customerJSONObject.getString("dob"), DateConstants.SLASH_DD_MM_YYYY)%></span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-from" /></p>
													<span>
														<label class="custom-field two">
														<aui:input name='<%=policyNumber + "dateFrom"%>' label="" wrappedField="<%=true %>" placeholder="label-date-format-dd-mm-yyyy"
															cssClass="validate vdate" type="text" value="" maxLength="10">
															<aui:validator name="required" errorMessage='error-label-date-from' />
															<aui:validator name="custom" errorMessage='error-label-date-invalid'>
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																
																	let day = parseInt(parts[0]);
													        		let month = parseInt(parts[1]);
													        		let year = parseInt(parts[2]);
																
																	// Create a list of days of a month      
															        let ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
															        if (month == 1 || month > 2) {
															            if (day > ListofDays[month - 1]) {  
															                return false;
															            }
															        } else if (month == 2) {
															            let leapYear = false;
															            if ((!(year % 4) && year % 100) || !(year % 400)) leapYear = true;
															            if ((leapYear == false) && (day >= 29)) {
															                return false;
															            }
															            else
															                if ((leapYear == true) && (day > 29)) {
															                    return false;
															                }
															        }
															        return true;
																}
															}
															</aui:validator>
															<aui:validator name="custom" errorMessage="error-label-date">
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																	if(parts[0] > 31){
																		return false;
																	}
																	if(parts[1] > 12){
																		return false;
																	}
																	if(parts[2].length!=4){
																		return false;
																	}
																	else if(parts[0].length!=2 && parts[1].length!=2){
																		return false;
																	}
																	return true;
																}
															}
															</aui:validator>
															<aui:validator name="custom" errorMessage="error-label-date-must-be-in-past">
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
																var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																	var now = new Date();
																	if(selectedDate > now) {
																		return false;
																	}
																	return true;
																	}
																}
															</aui:validator>
															<aui:validator name="custom" errorMessage='<%=LanguageUtil.get(Locale.US, "error-label-date-must-be-from") +" "+ DateFormatterUtil.parseDateToSpecificFormat(fundStatementStartDate, DateConstants.SLASH_DD_MM_YYYY) %>'>
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
																var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																	var limitDate = new Date($("#<portlet:namespace/>"+"fundStatementStartDate").val());
																	if(selectedDate < limitDate) {
																		return false;
																	}
																	return true;
																	}
																}
															</aui:validator>
														</aui:input>
													</label>
													</span>
												</div>
											</div>
											<div class="col-md-6 col-12">
												<div class="flabel">
													<p><liferay-ui:message key="label-to" /></p>
													<span>
														<label class="custom-field two">
														<aui:input name='<%=policyNumber + "dateTo"%>' label=""  wrappedField="<%=true %>" placeholder="label-date-format-dd-mm-yyyy"
															cssClass="validate vdate" type="text" value="" maxLength="10">
															<aui:validator name="required" errorMessage='error-label-date-to' />
															<aui:validator name="custom" errorMessage='error-label-date-invalid'>
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																
																	let day = parseInt(parts[0]);
													        		let month = parseInt(parts[1]);
													        		let year = parseInt(parts[2]);
																
																	// Create a list of days of a month      
															        let ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
															        if (month == 1 || month > 2) {
															            if (day > ListofDays[month - 1]) {  
															                return false;
															            }
															        } else if (month == 2) {
															            let leapYear = false;
															            if ((!(year % 4) && year % 100) || !(year % 400)) leapYear = true;
															            if ((leapYear == false) && (day >= 29)) {
															                return false;
															            }
															            else
															                if ((leapYear == true) && (day > 29)) {
															                    return false;
															                }
															        }
															        return true;
																}
															}
															</aui:validator>
															<aui:validator name="custom" errorMessage="error-label-date">
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																	if(parts[0] > 31){
																		return false;
																	}
																	if(parts[1] > 12){
																		return false;
																	}
																	if(parts[2].length!=4){
																		return false;
																	}
																	else if(parts[0].length!=2 && parts[1].length!=2){
																		return false;
																	}
																	return true;
																	}
																}
															</aui:validator>
															<aui:validator name="custom" errorMessage="error-label-date-must-be-current-or-after-from-date">
																function(val, fieldNode, ruleValue) {
										
																var parts = val.split("/");
																var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
										
																if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
																	return false;
																}else{
																	var now = new Date();
																	var dateFromParts = $("#<portlet:namespace/>"+'<%=policyNumber%>'+"dateFrom").val().split("/");
																	var dateFrom = new Date(dateFromParts[1] + "/" + dateFromParts[0] + "/" + dateFromParts[2]);

																	if(selectedDate > now) {
																		return false;
																	}else if(dateFrom >= selectedDate){
																		return false;
																	}
																	return true;
																	}
																}
															</aui:validator>
														</aui:input>
													</label>
													</span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-12 col-12">
												<div class="center-btn pt-4 text-md-right">
													<aui:button-row>
														<aui:button
															cssClass="edto-btn-primary"
															id='<%=policyNumber + "confirmSubmit"%>' value="label-get-statement"
															onClick='<%="validateFundStatementForm('"+formStep+"', true ,'"+formName+"');" %>' >
															</aui:button>
													</aui:button-row>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!--  -->
				 </div>
			</div>
			
		</div>
	</aui:form>
</div>
<%
				}
			}
		}
	}else {
		customerJSONObject = (JSONObject) renderRequest.getAttribute("customerJSONObject");
	}
%>
<!-- Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog" id="fundBackdrop"  aria-labelledby="fundBackdrop" aria-hidden="true">
	<div class="modal-dialog" role="document">
	   <div class="modal-content">
		   <div class="modal-body">
			  <div class="modal-header">
				  <h2></h2>
				 <button type="button" id="closeBtn" class="close" data-dismiss="modal" aria-label="Close" onClick="closeFundModal(false);"> 
				 <span aria-hidden="true">&times;</span>
				 </button>
			  </div>
			  <div class="wealth-form-box">
				 <span id="modal_error" class="error"></span>
				 <div class="cusscess-icon mb-5">
				   <div class="text-center">
					<h5 id="responseMessage"></h5>
				  </div>
				 </div>
				 <div class="center-wealth-btn">
					<button id="okayBtn" type="button" class="edto-btn-primary" onClick="closeFundModal(false);" >
						<liferay-ui:message key="label-okay" />
					</button>
				 </div>
			  </div>	         
		   </div>
	   </div>
	</div>
 </div>
<!-- Image loader -->
<div id='<portlet:namespace/>fund-loader' style='display: none;'>
	<div class="preloader"></div>
</div>
<!-- Image loader -->
<script src="<%=request.getContextPath()%>/js/fund-statement.js?v=1.1"></script>
<script>
	$(document).ready(
			function() {

				var status = '<%=status%>';
				var ulipPlans = '<%=ulipPlans%>';
				var customerJSONObject = JSON.parse('<%=customerJSONObject%>');

				if (status != null && status != 200) {
					console.debug("false - API Exception");
					$("#responseMessage").html(customerJSONObject.message);
					openFundModal();
					return false;
				}else if (ulipPlans != null && ulipPlans == 0) {
					console.debug("No Ulip Plans");
					$("#responseMessage").html('<liferay-ui:message key="label-ulip-policy-not-found" />');
					openFundModal();
					return false;
				}else if (status != null && status == 0) {
					console.debug("false - Internal Error Occured");
					$("#responseMessage").html('<liferay-ui:message key="label-internal-error-occured" />');
					openFundModal();
					return false;
				}
			});	
</script>
<style>
	.flabel{align-items: center;}
</style>