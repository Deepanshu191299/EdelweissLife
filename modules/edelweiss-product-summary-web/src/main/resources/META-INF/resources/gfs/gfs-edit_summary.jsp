<%@ include file="/init.jsp" %>
<div class="modal wealth-modal" tabindex="-1" role="dialog" id="editModal"
   style="display: none;">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
      <div class="modal-body">
         <div class="modal-header">
            <h5 class="fs22 fontbold"><liferay-ui:message key="edit-basic-details" /></h5>
            <button type="button" class="close" data-dismiss="modal"
               aria-label="Close"> 
            <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="wealth-form-box">
            <form name="<portlet:namespace/>editSummaryForm" id="<portlet:namespace/>editSummaryForm" method="POST" action="${editBasicActionURL}">
               <div class="row">               
               <div class="col-md-6 col-12">
                <p><liferay-ui:message key="how-do-we-address" /></p>                         
               <div class="radio_container" id="gender-wrapp">
                     <input type="radio"  id="sir"
                     <c:if test="${summaryData['gender'] == 'Male'}">checked </c:if>
                     value="Male" name='<portlet:namespace/>gender'><label for="sir"><liferay-ui:message key="sir" /></label>
                  
                     <input type="radio" id="maam"
                     <c:if test="${summaryData['gender'] == 'Female'}">checked </c:if>
                     value="Female" name='<portlet:namespace/>gender'><label for="maam"><liferay-ui:message key="mam" /> </label>
                  
               </div>
               </div>
               <div class="col-md-6 col-12">
                   <div class="input-box">
                  <label for='<portlet:namespace/>fullname'><liferay-ui:message key="your-name" /></label> <input type="text" class="form-control"
                     value="${summaryData['fullName']}"
                     id='<portlet:namespace/>fullName'
                     name='<portlet:namespace/>fullName' oninput="this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');">
                     <span class="underline"></span>
                     </div>
               </div>
               <div class="col-md-6 col-12">
                     <div class="input-box">
                  <label  for='<portlet:namespace/>dob'><liferay-ui:message key="dob-format" /></label> <input type="text" class="form-control"
                     value="${summaryData['dateOfBirth']}"
                     id='<portlet:namespace/>dateOfBirth'
                     name='<portlet:namespace/>dateOfBirth'>
                     <span class="underline"></span>
                                        </div>
               </div>
               <div class="col-md-6 col-12">
                  <div class="input-box">
                  <label class="main-label" for='<portlet:namespace/>mobileNo'><liferay-ui:message key="mobile-number" /></label> <input type="tel"
                     class="form-control"
                     value="${summaryData['mobileNumber']}"
                     id='<portlet:namespace/>mobileNumber'
                     name='<portlet:namespace/>mobileNumber' minlength='10' maxlength='10' oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                     <span class='label-plchld'>+91</span>
                     <span class="underline"></span>
                     </div>
               </div>
               <div class="col-md-6 col-12">
                     <div class="input-box">
                  		<label for='<portlet:namespace/>emailId' class="main-label"><liferay-ui:message key="email-address" /></label>
                  		<input type="email" class="form-control" value="${summaryData['email']}"
	                     id='<portlet:namespace/>email' name='<portlet:namespace/>email'
	                     oninput="this.value = this.value.replace(/[^a-zA-Z0-9@._-]/g, '');">
	                     <span class="underline"></span>
                	</div>
               </div>
               <div class="col-md-12 col-12">
                  <p><liferay-ui:message key="select-investment-obj" /></p>
                  <div class="radio_container">                    
 					<c:forEach items="${selectivePicklist}" var="selectivePicklistVal">
						<input type="radio" id="${selectivePicklistVal.key}" value="${selectivePicklistVal.key}" <c:if test="${selectivePicklistVal.key == summaryData.investmentObjective}"> checked </c:if>
						name='<portlet:namespace/>investmentObjective' /> <label for="${selectivePicklistVal.key}">${selectivePicklistVal.value}</label>
                    </c:forEach>
                  </div>
               </div>
         
    			<div class="col-md-12 col-12" id="investingFor">
    			 <p>You are investing for?</p>
                                <div class="radio_container">
                        <c:forEach var="curInvestingFor" items="${productRelationsData.productInvestingForRels[0].investingFors}">
                        	<c:choose>
                        		<c:when test="${productName == 'Wealth Secure Plus' && summaryData.customerInvestmentDetailsRel[0].policyOption != 'baseCover'}">
                        			<c:if test="${curInvestingFor.name == 'Myself'}">
											<li class="list-unstyled form-btn address-radio">
												<input type="radio" name="<portlet:namespace/>investingFor" id="${curInvestingFor.key}" value="${curInvestingFor.name}" ${summaryData.customerInvestmentDetailsRel[0].investingFor == curInvestingFor.name ? 'checked' : ''}>
												<label for="${curInvestingFor.key}">${curInvestingFor.name}</label>
											</li>
									</c:if>
                        		</c:when>
                        		<c:otherwise>
                        			<li class="list-unstyled form-btn address-radio">
										<input type="radio" name="<portlet:namespace/>investingFor" id="${curInvestingFor.key}" value="${curInvestingFor.name}" ${summaryData.customerInvestmentDetailsRel[0].investingFor == curInvestingFor.name ? 'checked' : ''}>
										<label for="${curInvestingFor.key}">${curInvestingFor.name}</label>
									</li>
                        		</c:otherwise>
							</c:choose>
						</c:forEach>
                  <%-- <li class="btn form-btn address-radio" >
                     <input type="radio"  name='<portlet:namespace/>investingFor' 
                     id="myself" value="Myself" checked>
                    <label for="myself"><liferay-ui:message key="myself"/></label>
                  </li>
                    <li class="btn form-btn address-radio form-btn-slct"
                       id="investing_for_family">
                       <input type="radio" value="Family" id="family" name='<portlet:namespace/>investingFor' >
                       <label for="family"><liferay-ui:message key="family" /> </label>
                    </li> --%>
              </div>
              </div>
            <div class="col-md-12 col-12" id="investingFormFamily">
                       <p><liferay-ui:message key="who-do-you-invest-for" /></p>
                      
                        <div class="radio_container" >
                        <input type="radio" name='<portlet:namespace/>assuredRelation' id='investForSon' value="Son" checked >
                            <label for="investForSon"><liferay-ui:message key="son"/> </label>
                         <input type="radio" name='<portlet:namespace/>assuredRelation' id='investForDaughter' value="Daughter"  >
                            <label for="investForDaughter"><liferay-ui:message key="daughter"/></label>
                    </div>
			</div>
			
				<div class="col-md-12 col-12">
                    <div class="row" id="assuranceNameForm">
                       	<div class="col-md-6 col-12">
                     		<div class="input-box">
	                            <label id="assurancename"><liferay-ui:message key="son-name" /></label>
	                            <input type="text" class="form-control" name='<portlet:namespace/>assuranceFullName' name='<portlet:namespace/>assuranceFullName' value="${summaryData.customerInvestmentDetailsRel[0].investingFor == 'Family' ? summaryData.customerFamilyDetailsRel[0].assuranceFullName : ''}" id='<portlet:namespace/>assuranceFullName' oninput="this.value = this.value.replace(/[^a-zA-Z. ]/g, '').replace(/(\..*)\./g, '$1');" >
	                            <span class="underline"></span>
                           	</div>
                        </div> 
                        <div class="col-md-6 col-12">
                           <div class="input-box">
                              <label id="assurancedob"><liferay-ui:message key="son-dob" /></label> 
                              <input type="text" class="form-control" name='<portlet:namespace/>assuranceDob' id='<portlet:namespace/>assuranceDob' value="${summaryData.customerInvestmentDetailsRel[0].investingFor == 'Family' ? summaryData.customerFamilyDetailsRel[0].assuranceDob : ''}" >
                              <span class="underline"></span>
                           </div>
                        </div> 
                    </div> 
                  </div>
                               <c:choose>
					  <c:when test="${productName eq 'Wealth Plus' && summaryData.customerInvestmentDetailsRel[0].investingFor == 'Family' && summaryData.customerFamilyDetailsRel[0].isRisingStarBenefitOpted == 'Yes'}">
						<div class="col-md-6 col-12" id="removeRisingStarWrapper">
            	      <p><liferay-ui:message key="rising-star" /></p>
                  <div class="radio_container">                  
                  <input type="hidden" value="${summaryData.customerFamilyDetailsRel[0].isRisingStarBenefitOpted}"
               name='<portlet:namespace/>risingStar' id='<portlet:namespace/>risingStar' >                 	
               <label id="<portlet:namespace/>removeRisingStarBenefit">
			        <liferay-ui:message key="remove" /></label>
               </div>
             </div>
					  </c:when>
					   <c:when test="${productName == 'Wealth Rise Plus' && summaryData.customerInvestmentDetailsRel[0].investingFor == 'Family' && summaryData.customerFamilyDetailsRel[0].isLittleChampBenefitOpted == 'Yes'}">
						<div class="col-md-6 col-12" id="removeLittleChampWrapper">
            	<p><liferay-ui:message key="little-champ" /></p>
              <div class="radio_container">                  
              <input type="hidden" value="${summaryData.customerFamilyDetailsRel[0].isLittleChampBenefitOpted}"
               name='<portlet:namespace/>littleChamp' id='<portlet:namespace/>littleChamp' >                 	
               <label id="<portlet:namespace/>removeLittleChampBenefit">
			        <liferay-ui:message key="remove" /></label>
               </div>
             </div>
					  </c:when>
					</c:choose>
					
					
            </div>
               <div class="center-wealth-btn">
            <input type="submit" id="save" class="edto-btn-primary" value='<liferay-ui:message key="save"></liferay-ui:message>' /> 
            </div>
            </form>
            
         </div>
         
      </div>
      </div>
   </div>
</div>