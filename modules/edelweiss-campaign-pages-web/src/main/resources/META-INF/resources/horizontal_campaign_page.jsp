<%@ include file="init.jsp" %>

<portlet:resourceURL id="add_lead" var="lmsURL"></portlet:resourceURL>
<liferay-portlet:renderURL varImpl="bookmarkURL">
	</liferay-portlet:renderURL>
<c:choose>
	 <c:when test="${not empty formView && not empty formView.fields}">	 
	 	<div class="lead-gen-form-wrapper d-block">
	 		<button type="button" class="close d-none" id="closeForm-btn">
				<span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
						<path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
					</svg>
				</span>
			</button>
		   	<%@ include file="/form-header.jsp"%>
		   	<form name="campaignPageForm" id="campaignPageForm"  action="${lmsURL}" method="post">
				<div class="application_step" id='<portlet:namespace/>raise_a_claim_step'>
					<div class="edto-claim-pilosophy-wrapper">
						<div class="container">
							<div class="form-tiles-main-wrappper">
								<div class="row" id='<portlet:namespace/>short-form'>
									<c:forEach items="${formView.fields }" var="formField">
										<c:set var="curFieldValue" value="${(empty customerBasicDetails.getString(formField.fieldReference))?formField.text:customerBasicDetails.getString(formField.fieldReference)}" />
										<c:if test="${formField.type == 'text'}">
											<div class="col-md-4 col-lg-3 col-12">
								 				<label class="custom-field two">
								 					<aui:input name="${formField.fieldReference}" label="" placeholder="&nbsp;" wrappedField="${true }" type="${formField.type}"
								 		 					cssClass="validate etl" hidden="${formField.hidden}" value="${curFieldValue }">
													</aui:input>
													<span class="placeholder"><liferay-ui:message key="${formField.label}" /></span>
								 				</label>
											</div>
										</c:if>
										<c:if test="${formField.type == 'select'}">
											<div class="col-md-4 col-lg-3 col-12">
												<div class="select-container ">
													<aui:select name="${formField.fieldReference}" label="" placeholder="&nbsp;" wrappedField="${true }"
														cssClass="validate etl" value="${curFieldValue }">
														<aui:option selected="true">
															<liferay-ui:message key="${formField.label}" />
														</aui:option>
														<c:forEach items="${formField.optionValues}" var="options">
															<aui:option value="${options.key}">
																${options.value}
															</aui:option>
														</c:forEach>
													</aui:select>
												 </div>
												</div>
										</c:if>
										<c:if test="${formField.type == 'radio'}">
											<div class="col-md-4 col-lg-3 col-12">
												<div class="input-box mb-2">
													<label>${formField.label }</label>
												</div>
												<div class="radio_container mb-4">
													<c:forEach items="${formField.optionValues.keySet()}" var="option">
														<aui:input type="radio" id='${formField.fieldReference}_${option}' label=""
															value="${option }" name='${formField.fieldReference}' 
															checked = "${option.equalsIgnoreCase(curFieldValue)}">
														</aui:input>
														<label for="<portlet:namespace/>${formField.fieldReference}_${option}">${formField.optionValues.getOrDefault(option,"")}</label> 
													</c:forEach>
												</div>
											</div>
										</c:if>
									</c:forEach>
								<div class="col-sm-12">
									<div class="edto-check-content m-0">
										<div class="form-group">
											<input type="checkbox" id="sutabilityId"
												name='<portlet:namespace/>suitabiltyMatrix' 
												readonly="readonly" onclick="return false" 
												checked="checked">
											<label class="form-check-label"  for="sutabilityId"> 
												<liferay-ui:message key="suitability-consent" />
											</label>
										</div>
										<div class="form-group">
											<input type="checkbox" id="consenttermsandconditionId"
												name='<portlet:namespace/>consenttermsandcondition'
												readonly="readonly" value="Consent" onclick="return false"
												checked="checked">
											<label class="form-check-label" for="consenttermsandconditionId"> 
												<liferay-ui:message key="ndnc-consent" />
											</label>
										</div>
									</div>
								</div>

								<div class="form-group justify-content-center mt-3 cta-btn">
									<div class="form-btn">
									<button type="submit" class="edto-btn-primary" id="submitbtn">
										<liferay-ui:message key="check-returns" />
									</button>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>
				<!-- Partner and UTM jsp -->
				<%@ include file="utm_element.jsp"%>
			</form>
		</div>
		<div class="lead-gen-mob-btn-wrapper p-3 text-center d-none">
			<a class="edto-btn-primary mt-0" href="javascript:;" id="get-started">Get Started</a>
     	</div>
    </c:when>
	<c:otherwise>
			<liferay-ui:message key="form.configuration.issue" />
	</c:otherwise>
</c:choose>

 <%@ include file="/modal.jsp"%>

<script type="text/javascript">
	var productName = '${productName}';
	var resuourceURL = '${lmsURL}';
	var minAge = '18';
	var maxAge = '65';
	
	$("#get-started").click(function() {
	    $(".lead-gen-mob-btn-wrapper").hide();
	    $(".lead-gen-form-wrapper").show();
	});
	
	$("#closeForm-btn").click(function() {
	    $(".lead-gen-form-wrapper").hide();
	    $(".lead-gen-mob-btn-wrapper").show();
	});
</script>


<style>
.edto-claim-pilosophy-wrapper .form-tiles-main-wrappper {box-shadow: none;padding: 0;border: 0;
 margin-top: 0;}
 .edto-claim-pilosophy-wrapper .container{padding:0}
.select-container  .form-group {display: block !important;}
.select-container .form-group .help-block {font-size:11px}
.select-container  .form-group select{margin-bottom:0;width: 100%;border-left: 0 !important;border-right: 0 !important;border-top: 0 !important; background-color: transparent;border-bottom: 1px solid #dbdbdb;border-radius: 0;font-size: 0.875rem;}

</style>