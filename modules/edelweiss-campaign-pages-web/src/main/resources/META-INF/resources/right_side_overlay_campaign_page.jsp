
<%@ include file="init.jsp" %>

<portlet:resourceURL id="add_lead" var="lmsURL"></portlet:resourceURL>
<c:choose>
	<c:when test="${not empty formView && not empty formView.fields}">
  		<form name="campaignPageForm" action="${lmsURL}" id="campaignPageForm" method="POST">
	       		<div class="row">
					<div class="col-sm-12">
						<div class="secure-life-form-wrapper">
							
							<div class="two-col-form">
							
							<c:forEach items="${formView.fields }" var="form">
				
								<c:set var="curFieldValue"
									value="${lmsLeadDataMap[form.fieldReference]}" />
								
								<c:if test="${form.type == 'text' && form.fieldReference == 'fullName'}">
									<div class="form-group icon-fields">
									<span><i class="far fa-user"></i></span>
										<input type="${form.type}" value="${curFieldValue}"
										<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}"
											<c:if test="${form.fieldReference == 'fullName'}"> oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');" </c:if> />
									</div>
								</c:if>
								
								<c:if test="${form.type == 'numeric'}">
									<div class="form-group icon-fields">
									<span><i class="fas fa-mobile-android-alt"></i></span>
										<input type="tel" maxlength=10 value="${curFieldValue}"
											<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}"
											oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" />
									</div>
								</c:if>
								
								<c:if test="${form.type == 'text' && form.fieldReference == 'email'}">
									<div class="form-group icon-fields">
									<span><i class="far fa-envelope"></i></span>
										<input type="${form.type}" value="${curFieldValue}"
											<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}" 
											oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');"/>
									</div>
								</c:if>
								
								<c:if test="${form.type == 'text' && form.fieldReference != 'fullName' && form.fieldReference != 'email'}">
									<div class="form-group icon-fields">
									<span><i class="far fa-edit"></i></span>
										<input type="${form.type}" value="${curFieldValue}"
											<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>
											class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}' placeholder="${form.placeHolder}"  />
									</div>
								</c:if>
								
								<c:if test="${form.type == 'select'}">
									<div class="form-group icon-fields slct-box">
									<span><i class="far fa fa-bars"></i></span>
										<select class="form-control etl"
											name='<portlet:namespace/>${form.fieldReference}'
											id='${form.fieldReference}'
											<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}"  </c:if>>
											<option value="" disabled="disabled" selected>${form.placeHolder}</option>
											<c:forEach items="${form.optionValues}" var="options">
										    	<option value="${options.key}" ${curFieldValue == options.key ? 'selected' : ''}>${options.value}</option>
											</c:forEach>
										</select>
								</c:if>
								
								<c:if test="${form.type == 'radio'}">
									<div class="radio_container">
										<label class="main-label" for="${form.fieldReference}">${form.label}</label>						
										<c:forEach items="${form.optionValues}" var="options">							
											<input type="${form.type}" value="${options.key}"
												<c:if test="${form.hidden == 'true'}"> hidden="true" </c:if>
												<c:if test="${form.required == 'true'}"> data-required= ${form.required} data-errormsg = "${form.errorMessage}" </c:if>
												class="etl"
												name='<portlet:namespace/>${form.fieldReference}'
												id='${options.key}'
												<c:if test="${curFieldValue == options.key}"> checked </c:if> />
											<label for='${options.key}'>${options.value}</label>
										</c:forEach>
									</div>
								</c:if>
								
							</c:forEach>
							</div>

						</div>
							
							<div class="col-sm-12 p-0 my-1">
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
							<c:if test="${layout.getFriendlyURL() == '/zindagi-protect/birthday'}"> 
					 <div class="g-recaptcha" data-sitekey="6LczOm8qAAAAAIIuwZMRFoHjFexmzTyAQFj-qqh6"></div>
					 </c:if>
							 <c:choose>
    						<c:when test="${layout.getFriendlyURL() == '/landing/wealth-plus/buy-now'}">
                              <div class="center-btn cta-btn">
								<div class="form-btn">
									<button type="submit" class="edto-btn-primary" id="submitbtn">
										<!--<liferay-ui:message key="start-saving" />-->
										 <liferay-ui:message key="calculate-returns" />
									</button>
							    </div>
							</div>
                            </c:when>
                           <c:otherwise>
                              <div class="center-btn cta-btn">
								<div class="form-btn">
									<button type="submit" class="edto-btn-primary" id="submitbtn">
										<!--<liferay-ui:message key="start-saving" />-->
										 <liferay-ui:message key="protect-now" />
									</button>
							    </div>
							</div>
                           </c:otherwise>
                       </c:choose>
					</div>
				</div>
				
				<!-- Partner and UTM jsp -->
				<%@ include file="utm_element.jsp"%>
			</form>
	</c:when>
	<c:otherwise>
			<liferay-ui:message key="form.configuration.issue" />
	</c:otherwise>
</c:choose>

<%@ include file="/modal.jsp"%>

<script>
jQuery(document).ready(function() {
	var elemCount = document.getElementById("campaignPageForm").elements.length;
	if(elemCount > 5){
		$('.edto-compaign-main-wrapper').addClass('campaign-form-third');
		$('.edto-compaign-main-wrapper').removeClass('campaign-form-sec');
	}else{
		$('.edto-compaign-main-wrapper').addClass('campaign-form-sec');
		$('.edto-compaign-main-wrapper').removeClass('campaign-form-third');
	}
});
</script>

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
.center-btn {padding-top:0px}
.icon-fields span{position: absolute;left: 0;}
.icon-fields .error{width: 100%;margin-top: 5px;}
.icon-fields {flex-direction: column;}
.icon-fields input{width: calc(100% - 40px)  !important  ;margin-left: 40px;margin-top: 0 !important;}
.compaign-form-wrapper form .form-btn {max-width: 200px;}
.radio_container .main-label{margin-bottom: 0px;font-size: 14px;font-family: "Montserrat-Bold";box-shadow: none;border: 0;padding: 0;color: #212529;margin-right: 15px;text-transform: capitalize;}
button#submitbtn:after {
    content: '>>';
    font-size: 18px;
    font-family: "Montserrat-Regular";
    vertical-align: bottom;
    padding-right: 0;
}
.form-group select {height:40px}
.compaign-form-wrapper form .form-group span{z-index:1;}
.radio_container{width: 100%;margin: 15px 0;}
.slct-box span{border: 1px solid #cccccc !important;}
.slct-box select{border: 1px solid #cccccc !important;border-radius: 0 !important;}
</style>
