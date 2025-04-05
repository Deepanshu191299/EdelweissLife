<%@ include file="/init.jsp"%>

<div class="container">
    <div class="grid-wrapper">
        <label for="radio-card-1" class="radio-card">
            <input type="radio" name="interest" id="radio-card-1" value="retirement" checked="true" />
            <div class="card-content-wrapper">
                <span class="check-icon"></span>
                <div class="card-content">
                    <div class="card-icon"><img src="/o/edelweisstokio-theme/images/plan-goal-1.svg" alt="" /></div>
                    <div class="card-text">
                        <p class="para1">Retirement Investment</p>
                    </div>
                </div>
            </div>
        </label>
        <!-- /.radio-card -->

        <label for="radio-card-2" class="radio-card">
            <input type="radio" name="interest" id="radio-card-2" value="growMoney" />
            <div class="card-content-wrapper">
                <span class="check-icon"></span>
                <div class="card-content">
                    <div class="card-icon"><img src="/o/edelweisstokio-theme/images/plan-goal-2.svg" alt="" /></div>
                    <div class="card-text">
                        <p class="para1">Dream Home</p>
                    </div>
                </div>
            </div>
        </label>
        <!-- /.radio-card -->
        <label for="radio-card-3" class="radio-card">
            <input type="radio" name="interest" id="radio-card-3" value="childEducation" />
            <div class="card-content-wrapper">
                <span class="check-icon"></span>
                <div class="card-content">
                    <div class="card-icon"><img src="/o/edelweisstokio-theme/images/plan-goal-3.svg" alt="" /></div>
                    <div class="card-text">
                        <p class="para1">Kids Education</p>
                    </div>
                </div>
            </div>
        </label>
        <!-- /.radio-card -->
        <label for="radio-card-4" class="radio-card">
            <input type="radio" name="interest" id="radio-card-4" value="growMoney" />
            <div class="card-content-wrapper">
                <span class="check-icon"></span>
                <div class="card-content">
                    <div class="card-icon"><img src="/o/edelweisstokio-theme/images/plan-goal-5.svg" alt="" /></div>
                    <div class="card-text">
                        <p class="para1">Dream Vacation</p>
                    </div>
                </div>
            </div>
        </label>
        <!-- /.radio-card -->
    </div>
    <div class="edto-tabs-form-wrapper">
        <aui:form name="fm" onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "generateLead(event);" %>'>
        <aui:input name="investmentObjective" type="hidden" label="" placeholder="&nbsp;" wrappedField="<%=true %>" value="retirement"></aui:input>
        <aui:input name="riskAppetite" type="hidden" label="" placeholder="&nbsp;" wrappedField="<%=true %>" value="high"></aui:input>
        <aui:input name="requestType" type="hidden" label="" placeholder="&nbsp;" wrappedField="<%=true %>" value="ISP"></aui:input>
        <aui:input name="productId" type="hidden" label="" placeholder="&nbsp;" wrappedField="<%=true %>" value="IPS_DEFAULT_PRODUCT"></aui:input>
            <div id="tabform1" class="desc">
                <div class="row">
                    <div class="col-md-4 col-12">
                        <label class="custom-field two">
                            <aui:input name="fullName" label="" placeholder="&nbsp;" wrappedField="<%=true %>"  required="true">
                            	<aui:validator name="custom" errorMessage="please-enter-full-name">
										function(val, fieldNode, ruleValue) {
										var regex = new RegExp("^[a-zA-Z]+( [a-zA-Z]+)+$");
										return regex.test(val);
										}
									</aui:validator>
                            </aui:input>
                            <span class="placeholder"><liferay-ui:message key="name"></liferay-ui:message></span>
                        </label>
                    </div>
                    <div class="col-md-4 col-12">
                        <label class="custom-field two">
                            <aui:input name="dateOfBirth" label=""  maxLength="10" cssClass="vdate" value="${dob}" autocomplete="off" placeholder="&nbsp;" wrappedField="<%=true %>"  required="true">
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    		function(val){
						                    	val = val.trim();
			 									var regex = new RegExp("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
					                             return regex.test(val);
				                    	   }
				                    	</aui:validator>
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    	   function(val){	                      
				                               return valDate(val);
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="custom" errorMessage="the-age-should-be-between-18-to-80">
			                              function(val){
			                                var today = moment(new Date(), 'DD/MM/YYYY').toDate();
			                                var birthDate = moment(val, 'DD/MM/YYYY').toDate();
			                                var age = today.getFullYear() - birthDate.getFullYear();
											var m = today.getMonth() - birthDate.getMonth();
			                                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			                                       age--;
			                                }
											return !(age < 18 || age > 80);
			                              }
                                      </aui:validator>
                                </aui:input>
                            <span class="placeholder"><liferay-ui:message key="date-of-birth"></liferay-ui:message></span>
                        </label>
                    </div>
                    <div class="col-md-4 col-12">
                        <div class="labelInputGroup selectWrapper">
                            <div class="labelGroup">
                                <p class="premiuim-small-text"><liferay-ui:message key="your-income"></liferay-ui:message></p>
                            </div>
                            <div class="select-container">
                                <aui:select name="annualIncome" class="form-control" label="">
                                       <c:forEach items="${entries}" var="entry">
										  <aui:option value="${entry.key}">${entry.nameCurrentValue}</aui:option>
										</c:forEach>
                                </aui:select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-12">
                        <label class="custom-field two">
                            <aui:input name="mobileNumber" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true" cssClass="vnumber" maxlength="10">
                            	<aui:validator name="minLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
                            	<aui:validator name="maxLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
                            	<aui:validator name="number"></aui:validator>
                            </aui:input>
                            <span class="placeholder"><liferay-ui:message key="mobile-number"></liferay-ui:message></span>
                        </label>
                    </div>
                    <div class="col-md-4 col-12">
                        <label class="custom-field two">
                             <aui:input name="email" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
				                    	<aui:validator name="email"></aui:validator>
	                         </aui:input>
                            <span class="placeholder"><liferay-ui:message key="email"></liferay-ui:message></span>
                        </label>
                    </div>
                    <div class="col-md-4 col-12 chidl-dob hide">
                        <label class="custom-field two">
                            <aui:input name="assuranceDob" label=""  maxLength="10" cssClass="vdate" autocomplete="off" placeholder="&nbsp;" wrappedField="<%=true %>">
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    		function(val){
						                    	val = val.trim();
			 									var regex = new RegExp("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
					                             return regex.test(val);
				                    	   }
				                    	</aui:validator>
				                    	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var interest = $('input[name="interest"]:checked').val();		                      
				                               return interest === "childEducation";
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    	   function(val){	                      
				                               return valDate(val);
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="custom" errorMessage="the-age-should-be-between-0-to-17">
			                              function(val){
			                                var today = moment(new Date(), 'DD/MM/YYYY').toDate();
			                                var birthDate = moment(val, 'DD/MM/YYYY').toDate();
			                                var age = today.getFullYear() - birthDate.getFullYear();
											var m = today.getMonth() - birthDate.getMonth();
			                                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
			                                       age--;
			                                }
											return !(age > 17);
			                              }
                                      </aui:validator>				                    	
                                </aui:input>
                            <span class="placeholder"><liferay-ui:message key="child-date-of-birth"></liferay-ui:message></span>
                        </label>
                    </div>
                </div>
                
                <div class="text-center mt-3">
                	<aui:button type="submit" cssClass="edto-btn-primary" value="show-me-how"></aui:button>
                </div>
            </div>
        </aui:form>
    </div>
</div>

<div id='<portlet:namespace/>loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<div class="modal team-modal wealth-modal fade" id="lmsModal" tabindex="-1" role="dialog" aria-labelledby="lmsTitle" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="wealth-form-box text-center w-100 success-block">                    
                    <h3 class="mb-3"> Thank you!!</h3>
                    <p class="success-msg">Thank you for sharing your details with us.</p>
                    <p class="success-msg">Our Investment expert will get in touch with you soon to understand your life insurance needs.</p>
                </div>
                <div class="wealth-form-box text-center w-100 error-block hide">
                    <h4 class="text-center mb-3 error-msg">Internal Server Error, Please try again after some time.</h4>
                </div>
            </div>
        </div>
    </div>
</div>
<style>
.labelInputGroup .select-container select {
    font-size: 14px;
    padding: 0;
    height: 40px;
}
.edto-tabs-form-wrapper .selectWrapper {
	padding: 10px 0;
}
.selectWrapper .premiuim-small-text {
	z-index: 1;
}
</style>
<aui:script>

function valDate(date) {
    let dateformat = /^(0?[1-9]|[1-2][0-9]|3[01])[\/](0?[1-9]|1[0-2])/;

    // Matching the date through regular expression      
    if (date.match(dateformat)) {
        let operator = date.split('/');

        // Extract the string into month, date and year      
        let datepart = [];
        if (operator.length > 1) {
            datepart = date.split('/');
        }
        let day = parseInt(datepart[0]);
        let month = parseInt(datepart[1]);
        let year = parseInt(datepart[2]);

        // Create a list of days of a month      
        let ListofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        if (month == 1 || month > 2) {
            if (day > ListofDays[month - 1]) {
                //to check if the date is out of range
                console.log("Invalid date")     
                return false;
            }
        } else if (month == 2) {
            let leapYear = false;
            if ((!(year % 4) && year % 100) || !(year % 400)) leapYear = true;
            if ((leapYear == false) && (day >= 29)) {
                console.log("Invalid date")
                return false;
            }
            else
                if ((leapYear == true) && (day > 29)) {
                    console.log('Invalid date format!');
                    return false;
                }
        }
    } else {
        console.log("Invalid date format!");
        return false;
    }
    return "Valid date";
}

$('input[type=radio][name=interest]').change(function() {
    if (this.value == "childEducation") {
       $('.chidl-dob').removeClass("hide");
    }else{
       $('.chidl-dob').addClass("hide");
    }
    $('#<portlet:namespace/>investmentObjective').val(this.value);
});

function <portlet:namespace />generateLead(event) {

   let loader = document.getElementById('<portlet:namespace/>loader');
    loader.style.display = 'block';
    
    var form = document.<portlet:namespace />fm;
	var formData = new FormData(form);
	
	Liferay.Util.fetch('<portlet:resourceURL id="/add_lead"></portlet:resourceURL>', {
		body: formData,
		method: 'POST',
	})
	.then(function (response) {
	    loader.style.display = 'none';
		if(response.ok){
		     return response.json();
		}else{
		   showFailureModal();
		}
	})
	.then(function (response) {
	   if(response?.status == 200){
	      Liferay.Util.navigate('/investment-product-suggestion');
	   }else{
	     showFailureModal(response.message);
	   }
	
	})
	.catch(function (e) {
	     loader.style.display = 'none';
	     showFailureModal();
		console.log("error...",e)
	})
	
 function showSuccessModal(){
	$('.success-blk').removeClass('hide');
	$('.error-blk').addClass('hide');
	$('.align-items-center').removeClass('hide');
	
	$('#lmsModal').modal('show');
}
		
  function showFailureModal(msg){
  	if(msg){
  		$('.error-msg').text(msg);
  	}
  	$('.error-blk').removeClass('hide');
  	$('.success-blk').addClass('hide');
  	$('.align-items-center').addClass('hide');
  	$('#lmsModal').modal('show');
 }
}
</aui:script>
