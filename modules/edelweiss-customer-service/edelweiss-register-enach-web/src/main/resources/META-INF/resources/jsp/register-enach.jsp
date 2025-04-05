<%@ include file="../init.jsp"%>

<liferay-portlet:resourceURL
	id="<%=RegisterEnachPortletKeys.ICICI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND%>"
	var="iciciMandateRegistrationURL" copyCurrentRenderParameters="false" />
	
<liferay-portlet:resourceURL
	id="<%=RegisterEnachPortletKeys.SBI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND%>"
	var="sbiMandateRegistrationURL" copyCurrentRenderParameters="false" />

<liferay-portlet:resourceURL
	id="<%=RegisterEnachPortletKeys.BANK_NAME_FROM_IFSC_MVC_RESOURCE_COMMAND%>"
	var="bankNameFromIFSCURL" copyCurrentRenderParameters="false" />
<liferay-portlet:resourceURL
	id="<%=RegisterEnachPortletKeys.ENACH_FAILURE_MVC_RESOURCE_COMMAND%>"
	var="enachFailureURL" copyCurrentRenderParameters="false" />

<%-- <portlet:resourceURL var="getMultipleLoginDataURL" id="/get/multipleLoginData"  />

<%
String createDateRr = (String) renderRequest.getAttribute("uniqueDateObj");
%> --%>

<%
	Map<String, String> bankNames = new TreeMap<String, String>();
	if (Validator.isNotNull(renderRequest.getAttribute("bankNames"))) {
		bankNames = (TreeMap<String, String>) renderRequest.getAttribute("bankNames");
	}

	JSONObject customerJSONObject = JSONFactoryUtil.createJSONObject();
	int status = 0;
	if (Validator.isNotNull((Integer) renderRequest.getAttribute("status"))) {
		status = (Integer) renderRequest.getAttribute("status");
	}
	
	boolean isAppTracker = false;
	if(Validator.isNotNull(renderRequest.getAttribute("isAppTracker"))){
		isAppTracker =  (Boolean) renderRequest.getAttribute("isAppTracker");	
	}
	
	boolean restrictICICIUrl = false;
	if(Validator.isNotNull(renderRequest.getAttribute("restrictICICIUrl"))){
		restrictICICIUrl =  (Boolean) renderRequest.getAttribute("restrictICICIUrl");
	}
	
	boolean restrictSBIUrl = false;
	if(Validator.isNotNull(renderRequest.getAttribute("restrictSBIUrl"))){
		restrictSBIUrl =  (Boolean) renderRequest.getAttribute("restrictSBIUrl");
	}
	
	String environmentLogo = StringPool.BLANK;
	if(Validator.isNotNull(renderRequest.getAttribute("environmentLogo"))){
		environmentLogo = (String) renderRequest.getAttribute("environmentLogo");
	}
	
	String environmentName = StringPool.BLANK;
	if(Validator.isNotNull(renderRequest.getAttribute("environmentName"))){
		environmentName = (String) renderRequest.getAttribute("environmentName");
	}
	
	String customerName = StringPool.BLANK;
	if(Validator.isNotNull(renderRequest.getAttribute("customerName"))){
		customerName = (String) renderRequest.getAttribute("customerName");
	}
	
	String disclaimerId = StringPool.BLANK;
	if(Validator.isNotNull(renderRequest.getAttribute("disclaimerId"))){
		disclaimerId = (String) renderRequest.getAttribute("disclaimerId");
	}
%>
<%
	if (Validator.isNotNull(status) && status == 200) {
		JSONArray customerJSONArray = (JSONArray) renderRequest.getAttribute("customerJSONArray");
		if (Validator.isNotNull(customerJSONArray) && customerJSONArray.length() > 0) {
			for (int i = 0; i < customerJSONArray.length(); i++) {
				customerJSONObject = customerJSONArray.getJSONObject(i);
				String policyNumber = customerJSONObject.getString("policyNumber");
				String formName = policyNumber + "-register-enach-form";
				String formStep = policyNumber + "_register_enach_step";

				//Added By Akash For EndDate
				String policyTerm = customerJSONObject.getString("policyTerm");

				String frequency = customerJSONObject.getString("frequency");
				double premium = customerJSONObject.getDouble("premium");
				double premiumTwenty = premium * 0.2f;
				double maxAmount = Double.parseDouble(String.format("%.2f", premium + premiumTwenty));
%>
<liferay-portlet:actionURL var="submitRegisterEnachFormURL"
	name="<%=RegisterEnachPortletKeys.SUBMIT_REGISTER_ENACH_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>
<div class="edto-covid-teat-wrappper">
	<h1 class="fs22 fontbold pb-3">
		<liferay-ui:message key="label-hi" /><%=" " + customerName%></h1>
	<div class="fieldWrap">
		<aui:form name='<%=formName%>'
			action="<%=submitRegisterEnachFormURL%>" method="post"
			enctype="multipart/form-data" onSubmit="event.preventDefault();">
			<input type="hidden" id="portletNamespace"
				value="<portlet:namespace/>" />
			<aui:input type="hidden" value="${submitRegisterEnachFormURL}"
				name="submitRegisterEnachFormURL" />
			<aui:input type="hidden" value="${bankNameFromIFSCURL}"
				name="bankNameFromIFSCURL" />
			<aui:input type="hidden" value="${iciciMandateRegistrationURL}"
				name="iciciMandateRegistrationURL" />
			<aui:input type="hidden" value="${sbiMandateRegistrationURL}"
				name="sbiMandateRegistrationURL" />
			<%-- <aui:input type="hidden" value="${getMultipleLoginDataURL}"
				name="getMultipleLoginDataURL" />
			<aui:input name="createDate" id="createDate-login" type="hidden" value="<%=createDateRr%>"></aui:input> --%>
			<aui:input type="hidden" value="${enachFailureURL}"
				name="enachFailureURL" />
			<aui:input type="hidden" value="<%=environmentName%>"
				name="environmentName" />
			<aui:input type="hidden" value="<%=environmentLogo%>"
				name="environmentLogo" />
			<aui:input type="hidden" value="<%=String.valueOf(premium)%>"
				name='<%=policyNumber + "premium"%>' />
			<aui:input type="hidden" value="<%=String.valueOf(frequency)%>"
				name='<%=policyNumber + "frequency"%>' />
			<aui:input type="hidden" name="customerJSONObject"
				value="<%=String.valueOf(customerJSONObject)%>" />

			<aui:input type="hidden" name="maxAmount"
				value="<%=String.valueOf(maxAmount)%>" />
				
				<aui:input type="hidden" name="customerName"
				value="<%=customerName%>" />
				
				<aui:input type="hidden" value="<%=restrictICICIUrl%>"
				name="restrictICICIUrl" />
				<aui:input type="hidden" value="<%=restrictSBIUrl%>"
				name="restrictSBIUrl" />
			<div class="application_step summary-main-wrapper" id='<%=formStep%>'>
				<div class="summary-title">
					<span><%=(Validator.isNull(customerJSONObject.get("planDescription"))
									|| customerJSONObject.getString("planDescription").isEmpty()) == false
											? customerJSONObject.getString("planDescription")
											: "null"%></span>
				</div>
				<div class="edto-summary-tiles">
					<div class="row">
						<div class="col-sm-12">
							<div class="row">
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-policy-number" />
										</p>
										<span id='<%=policyNumber + "policyNumber"%>'><%=customerJSONObject.getString("policyNumber")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-policy-holders-dob" />
										</p>
										<span id='<%=policyNumber + "dob"%>'><%=DateFormatterUtil.parseDateToSpecificFormat(customerJSONObject.getString("dob"),
									DateConstants.HYPHEN_DD_MM_YYYY)%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-policy-holders-email" />
										</p>
										<span id='<%=policyNumber + "emailId"%>' class="wb-bw"><%=customerJSONObject.getString("emailId")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-policy-holders-mobile-no" />
										</p>
										<span id='<%=policyNumber + "mobileNumber"%>'><%=customerJSONObject.getString("phoneNumber")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-modal-premium" />
										</p>
										<span><%=String.valueOf(premium).replaceAll("\\.\\d+$", "")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-max-amount" />
										</p>
										<span id='<%=policyNumber + "policymaxAmount"%>'><%=String.valueOf(maxAmount).replaceAll("\\.\\d+$", "")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-debit-frequency" />
										</p>
										<span><%=customerJSONObject.getString("frequency")%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-enach-registration-date" />
										</p>
										<span><%=DateFormatterUtil.parseDate(new Date(), DateConstants.HYPHEN_DD_MM_YYYY,
									StringPool.BLANK)%></span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-bank-branch-ifsc-code" />
										</p>
										<span> <aui:input
												name='<%=policyNumber + "bankBranchIFSCCode"%>' label=""
												placeholder="label-bank-IFSC-code"
												cssClass="validate form-control-none valphanum border-bottom"
												type="text" value="" maxLength="11"
												style="text-transform: uppercase"
												onChange='<%="populateBankName(this.id,'" + policyNumber + "','" + formName + "');"%>'>
												<aui:validator name="required"
													errorMessage='error-label-bank-ifsc-code' />
												<aui:validator name="maxLength"
													errorMessage="error-label-max-length-eleven">11</aui:validator>
												<aui:validator name="custom"
													errorMessage="error-label-invalid-character">
                                                    function(val, fieldNode, ruleValue) {
                                                        var regex = new RegExp("^[a-zA-Z0-9]*$");
                                                        return regex.test(val);
                                                    }
                                                </aui:validator>
											</aui:input>
											<div id="<portlet:namespace/>ifscError" class="error"
												style="display: none;">
												<liferay-ui:message
													key="label-your-bank-is-not-registered-for-enach-kindly-try-with-different-bank-account-submit-manual-nach-form" />
											</div>
										</span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-authentication-mode" />
										</p>
										<div class="product-select-box">
											<div class="form-group">
												<aui:input name='<%=policyNumber + "authenticationMode"%>'
													id='<%=policyNumber + "authenticationModeDebit"%>'
													cssClass="validate preview" type="radio" label=""
													required="required" onClick="openBankModal();"
													value="debitcard">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "authenticationModeDebit"%>'><liferay-ui:message
														key="label-debit-card" /></label>
											</div>
											<div class="form-group">
												<aui:input name='<%=policyNumber + "authenticationMode"%>'
													id='<%=policyNumber + "authenticationModeNet"%>'
													cssClass="validate preview" type="radio"
													required="required" label="" value="net">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "authenticationModeNet"%>'><liferay-ui:message
														key="label-net-banking" /></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message
												key="label-enter-bank-for-enach-registration" />
										</p>
										<span> <aui:input name='<%=policyNumber + "bankName"%>'
												label="" placeholder="label-bank-name"
												cssClass="validate form-control-none border-bottom"
												type="text" value="" maxLength="50" readOnly="readOnly">
												<aui:validator name="required"
													errorMessage='error-label-bank-name' />
												<aui:validator name="maxLength"
													errorMessage="error-label-max-length-fifty">50</aui:validator>
												<aui:validator name="custom"
													errorMessage="error-label-invalid-character">
                                                    function(val, fieldNode, ruleValue) {
                                                        var regex = new RegExp("^[a-zA-Z0-9\\s-()\\,\\.]*$");
                                                        return regex.test(val);
                                                    }
                                                </aui:validator>
											</aui:input>
										</span>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p>
											<liferay-ui:message key="label-authentication-type" />
										</p>
										<div class="product-select-box">
											<div class="form-group">
												<aui:input name='<%=policyNumber + "authenticationType"%>'
													id='<%=policyNumber + "authenticationTypeAdhar"%>'
													cssClass="validate" type="radio" required="required"
													label="" value="esign">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "authenticationTypeAdhar"%>'><liferay-ui:message
														key="label-adhar-based" /></label>
											</div>
											<div class="form-group">
												<aui:input name='<%=policyNumber + "authenticationType"%>'
													id='<%=policyNumber + "authenticationTypeEsign"%>'
													cssClass="validate" type="radio" required="required"
													label="" value="api" checked="true">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "authenticationTypeEsign"%>'><liferay-ui:message
														key="label-normal-esign" /></label>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6 col-12">
									<div class="flabel">
										<p class="pr-3">
											<liferay-ui:message
												key="label-factor-authentication-cca-guidelines" />
										</p>
										<div class="product-select-box">
											<div class="form-group">
												<aui:input name='<%=policyNumber + "factorAuthentication"%>'
													id='<%=policyNumber + "factorAuthenticationMobile"%>'
													cssClass="validate" type="radio" required="required"
													label="" value="mobile" checked="true">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "factorAuthenticationMobile"%>'><liferay-ui:message
														key="label-mobile-number" /></label>
											</div>
											<div class="form-group">
												<aui:input name='<%=policyNumber + "factorAuthentication"%>'
													id='<%=policyNumber + "factorAuthenticationEmail"%>'
													cssClass="validate" type="radio" required="required"
													label="" value="email">
												</aui:input>
												<label
													for='<portlet:namespace/><%=policyNumber + "factorAuthenticationEmail"%>'><liferay-ui:message
														key="label-email-address" /></label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12 col-12">
							<div class="flabel mt-4">
								<div class="product-select-box">
									<div class="form-group">
										<aui:input name='<%=policyNumber + "diclarationDisclaimer"%>'
											cssClass="validate" type="checkbox" required="required"
											label="" value="declaration" checked="true"
											onchange='<%="buttonReadOnly(this.id,'" + policyNumber + "');"%>'
											wrappedField="<%=true%>">
										</aui:input>
										<label class="checkCir" id="acceptDeclaration"
											for='<portlet:namespace/><%=policyNumber + "diclarationDisclaimer"%>'><a
											href="#disclaimerId"><liferay-ui:message
													key="label-i-accept-declaration-disclaimer" /></a></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-12 col-12">
							<div class="center-btn">
								<aui:button-row>
									<aui:button cssClass="edto-btn-primary"
										id='<%=policyNumber + "confirmSubmit"%>'
										value="label-register-e-nach"
										onClick='<%="validateRegisterEnachForm('" + policyNumber + "','" + formStep
											+ "', true ,'" + formName + "','" + isAppTracker + "');"%>'>
									</aui:button>
								</aui:button-row>
							</div>
						</div>
					</div>
				</div>
			</div>
		</aui:form>
	</div>
</div>
<%
	}
		}
	} else {
		customerJSONObject = (JSONObject) renderRequest.getAttribute("customerJSONObject");
	}
%>
<!-- Modal -->
<div class="modal wealth-modal fade" id="enachBankBackdrop"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="enachBankBackdropLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body modal-body-fixd">
				<div class="modal-header mb-0">
					<button type="button" name="bankBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeBankModal();">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-4">
						<div class="edto-bullet1">
							<ul>
								<%
									for (Map.Entry<String, String> entry : bankNames.entrySet()) {
								%>
								<li><%=entry.getValue()%></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal wealth-modal fade" id="enachBackdrop"
	data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="enachBackdropLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header  mb-0">
					<button type="button" id="closeBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closeEnachModal(false);">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-4">
						<div class="text-center">
							<h3 id="responseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="okayBtn" type="button" class="edto-btn-primary"
							onClick="closeEnachModal(false);">
							<liferay-ui:message key="label-okay" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Image loader -->
<div id='<portlet:namespace/>enach-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<!--Added  Modal By Akash-->
<div class="modal team-modal wealth-modal fade" id=iciciModal
	tabindex="-1" role="dialog" aria-labelledby="iciciModal"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h3 class="main-heading">ICICI Details</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"><svg
								xmlns="http://www.w3.org/2000/svg" width="15" height="15"
								viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero"
									d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
				</svg></span>
					</button>
				</div>
				<!-- Added by akash -->
				<aui:form action="" name="iciciForm" id="iciciForm" method="POST">
				<aui:input type="hidden"  name="bankIFSC"
				value="" />
					<div class="container" id='iciciStep'>
						<div class="row">
							<aui:input type="hidden"
								value="<%=Validator.isNotNull(customerJSONObject) && Validator.isNotNull(customerJSONObject.getString("policyTerm")) && !customerJSONObject.getString("policyTerm").isBlank() ? customerJSONObject.getString("policyTerm") : StringPool.BLANK%>"
								name="policyTerm" id="policyTerm" />
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										name="policyNo" id="polpolicyNo" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-policy-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input name="dob"
										readOnly="readOnly" cssClass="form-control validate"
										id="policyDOB" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-date-of-birth" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										name="mobile" id="policyMobile" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-mobile-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input name="email"
										cssClass="form-control validate" id="policyEmail" label=""
										placeholder="&nbsp;" wrappedField="<%=true%>">
										<aui:validator name="email"></aui:validator>
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-email-id" /></span>

								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										required="true" cssClass="form-control validate" type="text"
										label="" id="debitAccountNumber" name="debitAccountNumber"
										wrappedField="<%=true%>" maxLength="15">
										<aui:validator name="maxLength"
											errorMessage="error-label-icici-debit-account-number">15</aui:validator>
											<%-- <aui:validator name="minLength"
											errorMessage="error-label-icici-debit-account-number">10</aui:validator> --%>
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input><span class="placeholder"><liferay-ui:message key="label-icici-debit-account-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										type="text" label="" id="policymaxAmount"
										name="policymaxAmount" wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-mandate-amount" /></span>
								</label>
							</div>
						</div>
						<div class="center-wealth-btn d-flex">
							<aui:button cssClass="edto-btn-primary m-auto" type="button"
								id='icici-proceed' name='icici-proceed' value="Proceed"
								onClick='<%="validateRegisterEnachForm('','iciciStep', true ,'iciciForm');"%>'>
							</aui:button>
						</div>
					</div>
			</div>
			</aui:form>
		</div>
	</div>
</div>

<!--Ended  Modal By Akash-->

<!--Added  Modal By Razina for sbiModal-->
<div class="modal team-modal wealth-modal fade" id=sbiModal
	tabindex="-1" role="dialog" aria-labelledby="sbiModal"
	aria-hidden="true" style="display: none;">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h3 class="main-heading">SBI Details</h3>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true"><svg
								xmlns="http://www.w3.org/2000/svg" width="15" height="15"
								viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero"
									d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z" />
				</svg></span>
					</button>
				</div>
				<!-- Added by Razina -->
				<aui:form action="" name="sbiForm" id="sbiForm" method="POST">
				<aui:input type="hidden"  name="bankIFSC"
				value="" />
					<div class="container" id='sbiStep'>
						<div class="row">
							<aui:input type="hidden"
								value="<%=Validator.isNotNull(customerJSONObject) && Validator.isNotNull(customerJSONObject.getString("policyTerm")) && !customerJSONObject.getString("policyTerm").isBlank() ? customerJSONObject.getString("policyTerm") : StringPool.BLANK%>"
								name="policyTerm" id="policyTerm" />
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										name="policyNo" id="polpolicyNosbi" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-policy-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input name="dob"
										readOnly="readOnly" cssClass="form-control validate"
										id="policyDOBsbi" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-date-of-birth" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										name="mobile" id="policyMobilesbi" label="" placeholder="&nbsp;"
										wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-mobile-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input name="email"
										cssClass="form-control validate" id="policyEmailsbi" label=""
										placeholder="&nbsp;" wrappedField="<%=true%>">
										<aui:validator name="email"></aui:validator>
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-email-id" /></span>

								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										required="true" cssClass="form-control validate" type="text"
										label="" id="debitAccountNumbersbi" name="debitAccountNumber"
										wrappedField="<%=true%>" maxLength="12">
										<aui:validator name="maxLength"
											errorMessage="error-label-sbi-debit-account-number">12</aui:validator>
											<aui:validator name="minLength"
											errorMessage="error-label-sbi-debit-account-number">10</aui:validator>
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input><span class="placeholder"><liferay-ui:message key="label-icici-debit-account-number" /></span>
								</label>
							</div>
							<div class="col-md-6">
								<label class="custom-field two"> <aui:input
										readOnly="readOnly" cssClass="form-control validate"
										type="text" label="" id="policymaxAmountsbi"
										name="policymaxAmount" wrappedField="<%=true%>">
										<aui:validator name="required"
											errorMessage='Field is Required' />
									</aui:input> <span class="placeholder"><liferay-ui:message key="label-icici-mandate-amount" /></span>
								</label>
							</div>
						</div>
						<div class="center-wealth-btn d-flex">
							<aui:button cssClass="edto-btn-primary m-auto" type="button"
								id='sbi-proceed' name='sbi-proceed' value="Proceed"
								onClick='<%="validateRegisterEnachForm('','sbiStep', true ,'sbiForm');"%>'>
							</aui:button>
						</div>
					</div>
			</div>
			</aui:form>
		</div>
	</div>
</div>

<form method="POST" action="" id="sbiMandateSIteRedirect">
	<input name="encdata" value="" type="hidden" id="sbiMandateSIteRedirect_encdata">
    <input name="merchant_code" value="" type="hidden" id="sbiMandateSIteRedirect_merchant_code">
</form>


<!--Ended  Modal By Razina-->

<script src="https://app.digio.in/sdk/v7/digio.js"></script>
<script src="<%=request.getContextPath()%>/js/digio.js?v=1.1"></script>
<script src="<%=request.getContextPath()%>/js/register-enach.js?t=<%=new Date().getTime()%>"></script>
<script>
	$(document).ready(function() {

				var status = '<%=status%>';
				var customerJSONObject = JSON.parse('<%=customerJSONObject%>');

				if (status != null && status != 200) {
					console.debug("false - API Exception");
					$("#responseMessage").html(customerJSONObject.message);
					openEnachModal();
					return false;
				} else if (status != null && status == 0) {
					console.debug("false - Internal Error Occured");
					$("#responseMessage").html('<liferay-ui:message key="label-internal-error-occured" />');
					openEnachModal();
					return false;
				}
			});
</script>

<script>
    $("#acceptDeclaration").click(function() {
        $('html, body').animate({
            scrollTop: $("<%='#' + disclaimerId%>").offset().top
        }, 2000);
    });
</script>

<!-- <script>
function onClickedIcici() {
console.log("Oncliked ICICI::::::::::::::");
	var hiddenVal = $('#<portlet:namespace />polpolicyNo').val();
	console.log("hiddeVal:::" + hiddenVal);
	validateRepRegisterEnachForm(hiddenVal,'iciciStep', true ,'iciciForm');
}

function onClickedSbi() {
	console.log("Oncliked SBI::::::::::::::");
		var hiddenVal = $('#<portlet:namespace />polpolicyNosbi').val();
		console.log("hiddeVal:::" + hiddenVal);
		validateRepRegisterEnachForm(hiddenVal,'sbiStep', true ,'sbiForm');
	}
</script> -->

<style>
.flabel .product-select-box .form-group label.checkCir {
	color: #6d6d6d;
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-khtml-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.flabel .product-select-box .form-group label:before {
	top: 3px;
}

.flabel .product-select-box .form-group label.checkCir:before {
	border-radius: 50%;
	padding: 7px;
}

.flabel .product-select-box .form-group input:checked+label.checkCir:before
	{
	background-color: #7ed321;
	padding: 7px;
}

.flabel .product-select-box .form-group input:checked+label.checkCir:after
	{
	content: "";
	top: 7px;
	left: 6px;
	width: 4px;
	height: 6px;
	border: solid #ffffff;
	border-width: 0 1px 1px 0;
}

.modal-body-fixd {
	max-height: 380px;
	overflow-x: hidden;
}
</style>