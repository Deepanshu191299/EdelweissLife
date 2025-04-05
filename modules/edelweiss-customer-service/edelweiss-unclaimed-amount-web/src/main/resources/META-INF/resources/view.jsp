<%@ include file="init.jsp" %>

<div class="unclaimed-amount-wrapper">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-12">
                <div class="wealth-form-box">
                    <h1 class="fontbold fs28 pb-3"><liferay-ui:message key="unclaimed-title"></liferay-ui:message></h1>
                    <p class="fontregular fs18 txtLightBlack">
                       <liferay-ui:message key="unclaimed-description"></liferay-ui:message>
                    </p>

                    <aui:form name="fm" onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "saveUnclaimedAmount();" %>'>
                        <div class="row">
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="fullName" label="" placeholder="&nbsp;" cssClass="valpha" wrappedField="<%=true %>" required="true">
                                    <aui:validator name="custom" errorMessage="please-enter-full-name">
										function(val, fieldNode, ruleValue) {
											var regex = new RegExp("^[a-zA-Z]+( [a-zA-Z]+)+$");
											return regex.test(val);
										}
								   </aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="client-full-name"></liferay-ui:message></span>
                                </label>
                               
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="dob" label=""  maxLength="10" cssClass="vdate" autocomplete="off" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                    <aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    		function(val){
						                    	val = val.trim();
						
												var regex = new RegExp("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
					                             return regex.test(val);
				                    	   }
				                    	</aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="date-of-birth"></liferay-ui:message></span>
                                </label>
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="policyNo" label="" placeholder="&nbsp;" wrappedField="<%=true %>" maxlength="10">
                                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-policy-number">
						                    function(val, fieldNode, ruleValue) {
						                          var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
						                          return policyRegex.test(val);
						                    }
					                 </aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="policy-no"></liferay-ui:message></span>
                                </label>
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="panNo" label="" placeholder="&nbsp;" wrappedField="<%=true %>">
                                    	<aui:validator name="alphanum"></aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="pan-card-no"></liferay-ui:message></span>
                                </label>
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="email" label="" placeholder="&nbsp;" wrappedField="<%=true %>">
                                    	<aui:validator name="email"></aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="email-id"></liferay-ui:message></span>
                                </label>
                            </div>
                            <div class="col-md-6 col-12">
                                <label class="custom-field two">
                                    <aui:input name="mobile" label="" cssClass="vnumber" placeholder="&nbsp;" 
                                    		   wrappedField="<%=true %>" maxLength="10" pattern = "[0-9]*" inputmode="numeric">
                                    	<aui:validator name="number"></aui:validator>
                                    	<aui:validator name="minLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
                                    	<aui:validator name="maxLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
                                    </aui:input>
                                    <span class="placeholder"><liferay-ui:message key="mobile-no"></liferay-ui:message></span>
                                </label>
                            </div>
                        </div>

                        <p> <liferay-ui:message key="terms-and-condition"></liferay-ui:message> </p>
                        
                        <div class="text-center text-md-left">
                        	<aui:button cssClass="edto-btn-primary" type="submit" value="search"></aui:button>
                        </div>
                        
                    </aui:form>
                </div>
            </div>
        </div>
         <!-- API Response -->
        <div class="summary-main-wrapper mt-5">
            
        </div>
    </div>
</div>

  
  <div class="modal team-modal wealth-modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="modal-header h-auto">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">	
                            <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">	
                                <path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>	
                            </svg>	
                        </span>
                    </button>
                </div>
                <div class="wealth-form-box">
                    <h4 class="text-center mb-3 error-msg">No Found Record</h4>

                    <div class="center-wealth-btn">
                        <button type="button" class="edto-btn-primary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id='<portlet:namespace/>update-fatca-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<aui:script>
function <portlet:namespace />saveUnclaimedAmount() {

    let loader = document.getElementById('<portlet:namespace/>update-fatca-loader');
    loader.style.display = 'block';
    
	var form = document.<portlet:namespace />fm;
	
	Liferay.Util.fetch('<portlet:resourceURL id="/unclaim_amount"></portlet:resourceURL>', {
		body: new FormData(form),
		method: 'POST',
	})
	.then(function (response) {
		loader.style.display = 'none';
		if(response.ok){
		     return response.json();
		}else{
		   showModal('Internal Server Error');
		}
		console.log('response .....',response)
	})
	.then(function (response) {
	console.log('response .....',response)
		if(response?.status == 200){
		     buildCard(response.jsonObject);
		}else if(response?.status == 400){
			if(response?.jsonObject[0]){
			   showModal(response.jsonObject[0]);
			}else{
			    showModal('No Record Found');
			}
		     
		}else{
		     showModal('Internal Server Error');
		}
	})
	.catch(function (e) {
		loader.style.display = 'none';
		console.log("error...",e)
	})
}

function showModal(msg){
      let error = document.querySelector('.error-msg');
      error.innerHTML = msg;
      $('#exampleModalCenter').modal('show');
      console.log('msg......',msg)
      
}

function buildCard(items){

    let mainDiv = document.querySelector('.summary-main-wrapper');
	items.forEach( (item) =>{
	   mainDiv.innerHTML = '';
	   let root = document.createElement('div');
	   root.classList.add('edto-summary-tiles');
	   
	   let form = document.createElement('form');
	    root.append(form);
	    
	   let rootRow = document.createElement('div');
	   rootRow.classList.add('row');
	   form.append(rootRow);
	   
	   let rootCol = document.createElement('div');
	   rootCol.classList.add('col-lg-12','col-12');
	   rootRow.append(rootCol);
		
	   let row = document.createElement('div');
	   row.classList.add('row');
	   rootCol.append(row);
	   
	   let policyHolderName = document.createElement('div');
	   policyHolderName.classList.add('col-md-6','col-12');
	   policyHolderName.innerHTML = '<div class="flabel"><p><liferay-ui:message key="policy-holder-name"></liferay-ui:message></p><span> ' + item.policyHolderName + '</span></div>';
	   row.append(policyHolderName);
	   
	   let address = document.createElement('div');
	   address.classList.add('col-md-6','col-12');
	   address.innerHTML = '<div class="flabel"><p><liferay-ui:message key="address-as-per-record"></liferay-ui:message></p><span> ' + item.address + '</span></div>';
	   row.append(address);
	   
	   let reason = document.createElement('div');
	   reason.classList.add('col-md-6','col-12');
	   reason.innerHTML = '<div class="flabel"><p><liferay-ui:message key="reason"></liferay-ui:message></p><span> ' + item.reason + '</span></div>';
	   row.append(reason);
	   
	   let unClaimedAmount = document.createElement('div');
	   unClaimedAmount.classList.add('col-md-6','col-12');
	   unClaimedAmount.innerHTML = '<div class="flabel"><p><liferay-ui:message key="amount"></liferay-ui:message></p><span> ' + item.unClaimedAmount + '</span></div>';
	   row.append(unClaimedAmount);
	   
	   mainDiv.append(root); 
	})

}
</aui:script>


