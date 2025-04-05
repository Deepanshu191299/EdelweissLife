<div class="modal wealth-modal" tabindex="-1" role="dialog" id="verifyCustEligibilityModal" style="display: none;">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-body">      
          <div class="modal-header">
             <h5 class="fs22 fontbold">Verify your eligibility for Zindagi Protect Plus</h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close"> 
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
          <div class="wealth-form-box">
			<form name="verifyCustEligibility" id="verifyCustEligibility" method="POST" action="">
				<div class="row">
					<div class="col-md-6 col-12">
						<div class="input-box">
							<label class="main-label motion-label " for='<portlet:namespace/>occupation'> <liferay-ui:message key="occupation" /> </label> 
							<select id='<portlet:namespace/>occupation' name='<portlet:namespace/>occupation' class="form-select eligibility-modal form-control valid mt-2 modal-occupation" required="true">
								<option value="" disabled selected hidden><liferay-ui:message key="" /></option>
								<c:forEach items="${occupationPicklistData}" var="options">
									<option value="${options.key}" 
										<c:if test="${customerEnquiryMap['occupation'] == options.key }">selected </c:if> >${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<label class="main-label motion-label" for='<portlet:namespace/>educationQualification'> <liferay-ui:message key="education" /> </label> 
							<select id='<portlet:namespace/>educationQualification' name='<portlet:namespace/>educationQualification' class="form-select eligibility-modal form-control valid mt-2 modal-educationQualification" required="true">
								<option value="" disabled selected hidden><liferay-ui:message key="" /></option>
								<c:forEach items="${educationPicklistData}" var="options">
									<option value="${options.key}" 
										<c:if test="${customerEnquiryMap['educationQualification'] == options.key }">selected </c:if> >${options.value}</option>
								</c:forEach>
							</select>
							<span class="underline"></span>
						</div>
					</div>					
					<div class="col-md-6 col-12">
						<div class="input-box mb-2">
							<label><liferay-ui:message key="annual-income" /></label>
							<input type="text" class="form-control modal-Income eligibility-modal" id='<portlet:namespace/>Income'
									name='<portlet:namespace/>Income' maxlength="15"
									oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');" required="true">
						</div>
					</div>
					<div class="zp-eligibility-sorry-msg d-none col-md-12" style="text-align: center;">
                        <p>Sorry, Zindagi Protect Plus cannot be offered to the current profile</p>
                    </div>
					<div class="zp-eligibility-success-msg d-none col-md-12" style="text-align: center;"> 
						<p>You are eligible for Zindagi Protect Plus</p>
					</div>
					<div class="edto-bullet1 mb-3 mt-3 col-md-12">
						<div class="center-wealth-btn">
						    <input type="button" id="verifyEligibilityBtn" class="edto-btn-primary verify-eligibility-btn" value='<liferay-ui:message key="verify-eligibility"></liferay-ui:message>' />
						    <input type="button" id="eligibilityOkBtn" class="edto-btn-primary eligibility-success-btn d-none" value='<liferay-ui:message key="close"></liferay-ui:message>' /> 
						    <input type="button" id="eligibilityNotOkBtn" class="edto-btn-primary eligibility-failure-btn d-none" value='<liferay-ui:message key="close"></liferay-ui:message>' /> 						    
						</div>
					</div>
				</div>	
			</form>
         </div>
         </div>
      </div>
   </div>
</div>
<style>
#verifyCustEligibility label.motion-label {
    color: #212529;
    font-size: 14px;
    font-family: 'Montserrat-Bold';
}
.radio-wrapper {
    display: flex;
    align-items: center;
    margin-top: 20px;
    gap: 30px;
}
 .radio-wrapper .radio_container {
    display: flex;
    align-items: center;
    gap: 10px;
}
 .radio-wrapper input + label {
    border-radius: 50%;
    border-radius: 50%;
    width: 45px;
    height: 45px;
    padding: 0;
    font-size: 20px;
    border: 1px solid #a8a2a2;
}
 .radio-wrapper .radio_container input[type="radio"]:checked + label{
    background-color: #1e90ff;
    color: #124093;
    transition: 0.3s;
    border: 1.2px solid #124093;
    background-color: #f0f0f0;
    color: #124093;
}
 .radio-wrapper .radio_container input[type="radio"]:checked + label svg {
    fill: #124093;
}
</style>