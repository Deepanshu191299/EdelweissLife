<style>
.radio_container label {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: inherit;
    width: auto;
    height: 34px;
    text-align: center;
    border-radius: 9999px;
    overflow: hidden;
    transition: linear 0.3s;
    border: 1px solid #cbcbcb;
    line-height: 25px;
    padding: 3px 25px;
    color: #999;
    text-transform: uppercase;
    font-size: 0.75rem;
    font-family: 'Montserrat-SemiBold';
    box-shadow: 1px 1px 13px 0 #00000021;
    cursor: pointer;
}
</style>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="editModal" style="display: none;">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-body">      
          <div class="modal-header">
             <h5 class="fs22 fontbold">Edit Basic Details</h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close"> 
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
          <div class="wealth-form-box">
            <form name="<portlet:namespace/>editSummaryForm"
               id="<portlet:namespace/>editSummaryForm" method="POST"
               action="">
               <div class="row">
                     <div class="col-md-6 col-12">              
                <div class="input-box mb-2"> <label>How do we address you?</label></div>
               <div class="radio_container" id="gender-wrapp">                  
                     <input type="radio" id="sir"
                     <c:if test="${basicDetailsMap['gender'] == 'Male'}">checked </c:if>
                     value="Male" name="<portlet:namespace/>gender"><label for="sir">Sir</label>
                     <input type="radio" id="maam"
                     <c:if test="${basicDetailsMap['gender'] == 'Female'}">checked </c:if>
                     value="Female" name="<portlet:namespace/>gender"><label for="maam">Ma'am</label>
               </div>
               </div>
                <div class="col-md-6 col-12">
                       <div class="input-box">
                  <label for='<portlet:namespace/>fullname'>Your
                  Name</label> <input type="text" class="form-control"
                     value="${basicDetailsMap['fullName']}"
                     id='<portlet:namespace/>fullName'
                     name='<portlet:namespace/>fullName'
                     oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
                     <span class="underline"></span>
                     </div>
               </div>
               <div class="col-md-6 col-12">
                                <div class="input-box">
                  <label for='<portlet:namespace/>dob'>Date of birth (dd/mm/yyyy)</label> 
                  <input type="text" maxlength="10" class="form-control edelweiss-mask-date" value="${basicDetailsMap['dateOfBirth']}" id="<portlet:namespace/>dateOfBirth" name="<portlet:namespace/>dateOfBirth">
               <span class="underline"></span>
                     </div>
               </div>
                <div class="col-md-6 col-12">
                                <div class="input-box">
                  <label  for='<portlet:namespace/>mobileNo'>Mobile
                  Number</label> <input type="tel"
                     class="form-control"
                     value="${basicDetailsMap['mobileNumber']}"
                     id='<portlet:namespace/>mobileNumber'
                     name='<portlet:namespace/>mobileNumber' maxlength='10'
                     oninput="this.value = this.value.replace(/[^0-9]/g, '').replace(/(\..*)\./g, '$1');">
                      <span class='label-plchld'>+91</span>
              <span class="underline"></span>
                     </div>
               </div>
               <div class="col-md-6 col-12">
                                <div class="input-box">
                  <label for='<portlet:namespace/>emailId' class="main-label">E-mail
                  address</label> <input type="email"
                     class="form-control" value="${basicDetailsMap['email']}"
                     id='<portlet:namespace/>email' name='<portlet:namespace/>email' 
                     oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');">
                     <span class="underline"></span>
                     </div>
               </div>
               <div class="col-md-12 col-12">
                  <p>Select Investment Objective</p>
                  <div class="radio_container">
                           <c:forEach items="${picklistOptionValues}" var="picklistOptions">
                             <input data-name="${picklistOptions.value}" id="${picklistOptions.key}" type="radio" value="${picklistOptions.key}" <c:if test="${picklistOptions.key == basicDetailsMap['investmentObjective']}"> checked </c:if> name='<portlet:namespace/>investmentObjective' /><label for="${picklistOptions.key}">${picklistOptions.value}
                           </label>
                           </c:forEach>
                  </div>
               </div>
         
    			<div class="col-md-12 col-12 basicInvestingForWarpper" id="investingFor">
    				<p>You are investing for?</p>
                 		<div class="radio_container">
                                <c:forEach var="curInvestingFor" items="${productCustomizeSummaryData['investingForData']}">
                                	<li class="btn form-btn p-0 address-radio">
                                		<input type="radio" name="<portlet:namespace/>basicInvestingFor" id="${curInvestingFor.key}" value="${curInvestingFor.name}" ${(productCustomizeSummaryData['productDefaultValueData'].investingFor.name == curInvestingFor.name || (empty basicDetailsMap['basicInvestingFor'] && curInvestingFor.name == 'Myself')) ? 'checked' : ''}>
                                		<label for="${curInvestingFor.key}">${curInvestingFor.name}</label>
                                	</li>
								</c:forEach>
													
                  <%-- <li class="btn form-btn address-radio">
                     <input type="radio" name="<portlet:namespace/>basicInvestingFor" id="myself" value="Myself" <c:if test="${basicDetailsMap['basicInvestingFor'] == 'Myself' || empty basicDetailsMap['basicInvestingFor']}">  checked</c:if>>
                     <label for="myself">Myself</label>
                  </li>
                    <li class="btn form-btn address-radio form-btn-slct" id="investing_for_family">
                       <input type="radio" value="Family" id="family" name="<portlet:namespace/>basicInvestingFor" <c:if test="${basicDetailsMap['basicInvestingFor'] == 'Family'}"> checked </c:if>>
                       <label for="family">Family</label>
                    </li> --%>
                   
                   </div>
              </div>   
                     <div class="col-md-12 col-12" id="investingFormFamily">                       
                          <div class="input-box mb-2"> <label> Who Do You Invest for ?</label></div>
                        <div class="radio_container">
                        <li class="btn form-btn p-0 address-radio">
                        <input type="radio" name="<portlet:namespace/>assuredRelation" id="investForSon" value="Son" <c:if test="${basicDetailsMap['assuredRelation'] == 'Son'}"> checked </c:if>>
                            <label for="investForSon">Son</label>
                       </li>
                        <li class="btn form-btn p-0 address-radio">
                            <input type="radio" name="<portlet:namespace/>assuredRelation" id="investForDaughter" value="Daughter" <c:if test="${basicDetailsMap['assuredRelation'] == 'Daughter'}"> checked </c:if>>
                            <label for="investForDaughter">Daughter</label>    
                        </li>
                            </div>                      
                    </div>
<div class="col-12">
                    <div class="row" id="assuranceNameForm">
                     <div class="col-md-6 col-12">
                        <div class="input-box"> 
                            <label id="assurancename"><liferay-ui:message key="sons-full-name" /></label>
                            <input type="text" class="form-control" name="<portlet:namespace/>assuranceFullName" id="<portlet:namespace/>assuranceFullName" 
                            	value="${basicDetailsMap['basicInvestingFor'] == 'Family' ? basicDetailsMap['assuranceFullName'] : ''}"
                            	oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
                            <span class="underline"></span>
                           </div>                      
                          </div> 
                           <div class="col-md-6 col-12">
                              <div class="input-box">
                               <label id="assurancedob"><liferay-ui:message key="sons-dob" /></label> 
                              <input type="text" maxlength="10" class="form-control edelweiss-mask-date" name="<portlet:namespace/>assuranceDob" id="<portlet:namespace/>assuranceDob" value="${basicDetailsMap['basicInvestingFor'] == 'Family' ? basicDetailsMap['assuranceDob'] : ''}">
                              <span class="underline"></span>
                           </div> 
                           </div> 
                    </div>
                    </div>
                  	</div>
                  	  <div class="center-wealth-btn">
               <input type="button" id="save" value="Save" class="edto-btn-primary" /> 
               </div>
            </form>
         </div>
         </div>
      </div>
   </div>
</div>
