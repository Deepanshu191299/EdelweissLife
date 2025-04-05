<%@ include file="init.jsp" %>

<portlet:resourceURL id="add_lead" var="lmsURL"></portlet:resourceURL>
<liferay-portlet:renderURL varImpl="bookmarkURL">
	</liferay-portlet:renderURL>

<c:choose>
	 <c:when test="${not empty formView && not empty formView.fields}">	 
	 	<div class="lead-gen-form-wrapper">
	 		<button type="button" class="close" id="closeForm-btn">
				<span aria-hidden="true">
					<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
						<path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
					</svg>
				</span>
			</button>
	       
	        <%@ include file="/form-header.jsp"%>

		   	<form name="campaignPageForm" action="${lmsURL}" id="campaignPageForm" method="POST">
	       			<div class="row">
					<div class="col-sm-12">
						<div class="secure-life-form-wrapper">
							<div class="camp-form">
								<c:forEach items="${formView.fields }" var="form">
									<c:set var="curFieldValue" 
										value="${(empty customerBasicDetails.getString(form.fieldReference))?form.text:customerBasicDetails.getString(form.fieldReference)}" />
									
									<c:if test="${form.type == 'text' && form.fieldReference != 'buttonText'}">
										<div class="form-group">
											<input type="${form.type}" value="${curFieldValue}"
												<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
												<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
												class="form-control etl"
												name='<portlet:namespace/>${form.fieldReference}'
												id='${form.fieldReference}' placeholder="${form.placeHolder}"
												<c:choose>
									                <c:when test="${form.fieldReference == 'fullName'}">
									                    oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');"
									                </c:when>
									                <c:when test="${form.fieldReference == 'email'}">
									                    oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');"
									                </c:when>
									            </c:choose> />
										</div>
									</c:if>
									
									<c:if test="${form.type == 'numeric'}">
									<div class="form-group">
										<input type="tel" maxlength=10 value="${curFieldValue}"
											<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}"
											oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
									</div>
									</c:if>
									
									<c:if test="${form.type == 'select'}">
									<div class="form-group">
										<select class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}'
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>>
											<option value="" disabled="disabled" selected>${form.placeHolder}</option>
											<c:forEach items="${form.optionValues}" var="options">
										    	<option value="${options.key}" ${curFieldValue == options.key ? 'selected' : ''}>${options.value}</option>
											</c:forEach>
										</select>
									</div>
									</c:if>
								</c:forEach>
							</div>
							
							<div class="col-sm-12">
								<div class="edto-check-content m-0">
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
							
							<div class="form-group d-flex justify-content-center mt-3 cta-btn">
								<div class="form-btn">
								<button type="submit" class="edto-btn-primary" id="submitbtn">
									<liferay-ui:message key="check-premium" />
								</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- Partner and UTM jsp -->
				<%@ include file="utm_element.jsp"%>
			
			</form>
		</div>
		<div class="lead-gen-mob-btn-wrapper p-3 text-center">
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
.camp-form {display: grid;
    grid-template-columns: auto auto auto auto auto;
    gap: 15px;}
.camp-form .form-group {display: block !important;}
.camp-form .form-group input {height: 45px !important;}
.camp-form .form-group select {border-radius: 0 !important;}
</style>
