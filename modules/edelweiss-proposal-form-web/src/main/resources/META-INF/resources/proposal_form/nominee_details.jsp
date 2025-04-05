<%@ include file="../init.jsp"%>

<style>

.hide--appointee{ 
display: none;
}

.hide--nominee--address{ 
display: none;
}

.popup-select.nominee.select-container{
	margin-top: 12px !important;
}

@media screen and (max-width: 767px) {
	#nomineeModall {
		padding-right: 0px !important;
	}
	
	.modal-content{
		width: 100%;
	}
}

#proposal-form-table.communication-details .covind-inner-wrapper .edto-custom-table table thead th {
    background: none;
}
#proposal-form-table.communication-details .covind-inner-wrapper .custom-field {
    margin-top: 0px;
}

.nominee-percentage-error{
	color: red;
}
.select2-container--open .select2-dropdown {
    top: 77px;
    z-index: 10000;
}
.disable-click{
    pointer-events:none;
    opacity: 0.6;
}

.nominee-td-bold{
   font-weight: 600;
}

</style>

<div class="card">
   <div class="card-header" id="headingThree">
      <!--<h2 class="mb-0 title-tabs">
         <span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span>
         <liferay-ui:message key="label-nominee-details" />
      </h2>-->
      <h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
			   <p><liferay-ui:message key="label-nominee-details" /></p>
			</button>
		</h2>
   </div>
   <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
      <div class="card-body">
         <div class="edto-income-plan-tiles-wrapper nominee-details-section">

                   <div class="popupe-btn-wrapper">
                         <a class="edto-btn-primary" href="javascript:;" data-toggle="modal" data-target="#nomineeModal" id="nomineeModalBtn" onClick="updateRelationDropdown();">Add Nominee</a>    
                  </div>
                    
                   <div class="table-title-wrapper mt-4">
                                          <h5 class="pt-0">Nominee Details</h5>
                                          <div class="tbl-srol-btn" id="nominee_records_table_scroll">
                                             <button id="slideBack" type="button" disabled=""> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625ZM12.5 25C9.18479 25 6.00537 23.683 3.66116 21.3388C1.31696 18.9946 -6.91305e-07 15.8152 -5.46392e-07 12.5C-4.0148e-07 9.18479 1.31696 6.00537 3.66117 3.66116C6.00537 1.31696 9.18479 -6.91305e-07 12.5 -5.46392e-07C15.8152 -4.0148e-07 18.9946 1.31696 21.3388 3.66117C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25ZM17.9687 13.2812C18.1759 13.2812 18.3747 13.1989 18.5212 13.0524C18.6677 12.9059 18.75 12.7072 18.75 12.5C18.75 12.2928 18.6677 12.0941 18.5212 11.9476C18.3747 11.8011 18.176 11.7187 17.9687 11.7187L8.91719 11.7187L12.2719 8.36562C12.4186 8.21893 12.501 8.01996 12.501 7.8125C12.501 7.60504 12.4186 7.40607 12.2719 7.25937C12.1252 7.11268 11.9262 7.03026 11.7187 7.03026C11.5113 7.03026 11.3123 7.11268 11.1656 7.25937L6.47812 11.9469C6.40537 12.0194 6.34765 12.1057 6.30826 12.2006C6.26888 12.2955 6.2486 12.3972 6.2486 12.5C6.2486 12.6028 6.26888 12.7045 6.30826 12.7994C6.34765 12.8943 6.40537 12.9806 6.47812 13.0531L11.1656 17.7406C11.3123 17.8873 11.5113 17.9697 11.7187 17.9697C11.9262 17.9697 12.1252 17.8873 12.2719 17.7406C12.4186 17.5939 12.501 17.395 12.501 17.1875C12.501 16.98 12.4186 16.7811 12.2719 16.6344L8.91719 13.2812L17.9687 13.2812Z" fill=""></path>
                                                </svg>                                                
                                                </span> </button>
                                             <button id="slide" type="button"> <span><svg width="25" height="25" viewBox="0 0 25 25" fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" clip-rule="evenodd" d="M12.5 23.4375C15.4008 23.4375 18.1828 22.2852 20.234 20.234C22.2852 18.1828 23.4375 15.4008 23.4375 12.5C23.4375 9.59919 22.2852 6.8172 20.234 4.76602C18.1828 2.71484 15.4008 1.5625 12.5 1.5625C9.59919 1.5625 6.8172 2.71484 4.76602 4.76602C2.71484 6.8172 1.5625 9.59919 1.5625 12.5C1.5625 15.4008 2.71484 18.1828 4.76602 20.234C6.8172 22.2852 9.59919 23.4375 12.5 23.4375ZM12.5 -5.46392e-07C15.8152 -6.91305e-07 18.9946 1.31696 21.3388 3.66116C23.683 6.00537 25 9.18479 25 12.5C25 15.8152 23.683 18.9946 21.3388 21.3388C18.9946 23.683 15.8152 25 12.5 25C9.18479 25 6.00537 23.683 3.66117 21.3388C1.31696 18.9946 -4.0148e-07 15.8152 -5.46392e-07 12.5C-6.91305e-07 9.18479 1.31696 6.00537 3.66116 3.66117C6.00537 1.31696 9.18479 -4.0148e-07 12.5 -5.46392e-07ZM7.03125 11.7187C6.82405 11.7187 6.62534 11.8011 6.47882 11.9476C6.33231 12.0941 6.25 12.2928 6.25 12.5C6.25 12.7072 6.33231 12.9059 6.47882 13.0524C6.62534 13.1989 6.82405 13.2812 7.03125 13.2812L16.0828 13.2812L12.7281 16.6344C12.5814 16.7811 12.499 16.98 12.499 17.1875C12.499 17.395 12.5814 17.5939 12.7281 17.7406C12.8748 17.8873 13.0738 17.9697 13.2812 17.9697C13.4887 17.9697 13.6877 17.8873 13.8344 17.7406L18.5219 13.0531C18.5946 12.9806 18.6524 12.8943 18.6917 12.7994C18.7311 12.7045 18.7514 12.6028 18.7514 12.5C18.7514 12.3972 18.7311 12.2955 18.6917 12.2006C18.6524 12.1057 18.5946 12.0194 18.5219 11.9469L13.8344 7.25937C13.6877 7.11268 13.4887 7.03026 13.2812 7.03026C13.0738 7.03026 12.8748 7.11268 12.7281 7.25937C12.5814 7.40607 12.499 7.60504 12.499 7.8125C12.499 8.01996 12.5814 8.21893 12.7281 8.36562L16.0828 11.7187L7.03125 11.7187Z" fill=""></path>
                                                </svg>
                                                </span> </button>
                                          </div>
                     </div>  
                     <div class="nominee-percentage-error mt-1 hide">
                     <liferay-ui:message key="please-add-at-least-one-nominee" /> 
                   </div>
                     <div class="edto-custom-table pb-0" id="nominee_records_table_container">
                        <table id="nominee_records_table" class="table table-bordered w-100 ">
                           <thead>
                              <tr>
                              <th>
                              	<liferay-ui:message key="label-serial-number" />
                              </th>
                              <th>
                               <liferay-ui:message key="label-delete-edit" />
                              </th>
                                 <th>
                                    <liferay-ui:message key="label-nominee-name" />
                                 </th>
                                 <th>
                                    <liferay-ui:message key="label-nominee-dob" />
                                 </th>
                                 <th>
                                    <liferay-ui:message key="label-nominee-gender" />
                                 </th>
                                  <th>
                                    <liferay-ui:message key="label-nominee-percentage" />
                                 </th>
                                 <th>
                                    <liferay-ui:message key="label-nominee-relationship" />
                                 </th>
                              </tr>
                           </thead>
                           <tbody>
                              
                           </tbody>
                        </table>

                       
                  </div>
				<button class="edto-btn-primary mt-4" id="nomineeSaveAndContinue" onclick="validateSaveAndContinueNominee()">Save &amp; Continue</button>
         
         </div>
      </div>
   </div>
</div>

<div class="modal nominee-detail-popup fade" id="nomineeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="modal-header mb-4">
                    <h5 class="fs22 fontbold"><liferay-ui:message key="label-add-nominee" /></h5>
                    <button type="button" class="close" onClick="closeNomineeModal();" aria-label="Close">
                        <span aria-hidden="true">x</span>
                    </button>
                </div>
                <div class="table-form-wrapper">
              		<aui:form name="nomineeFM" action="" method="POST"
	                onSubmit='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "addNominee(event);" %>'>
	               
		               <div class="communication-details border-0 mb-0" id="proposal-form-table">
						  <input type="hidden" id="hiddenInput" value=" ">
							<div class="covind-inner-wrapper">
								<div class="covid-title-wrapper">
                  					<div id='<portlet:namespace/>nominee_details_step' class="medical-test">
					                     <div id='nominee_details' class="radio-wrapper">
					                        <div id="insurance_history_fields_container">
					                           <div class="location-field">
					                              <div class="row">
					                                 <div class="col-md-6 col-12">
					                                    <div class="form-box">
					                                       <label class="custom-field two">
					                                          <aui:input name="nomineeName" onKeyup="validateRadioButtonsOnChange('nomineeFM','nomineeName')"
					                                             placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
					                                             value="${ isLaMinor ? proposerPersonalDetails.fullName : ( commonDetails.spouseExistYn == 'Y' ? spousePersonalDetails.fullName : '') }" maxLength="90" minLength="0" wrappedField="<%=true %>">
					                                             <aui:validator name="required" errorMessage="label-please-provide-a-valid-nominee-name"></aui:validator>
					                                          </aui:input>
					                                          <span class="placeholder">
					                                             <liferay-ui:message key="label-nominee-name" />
					                                          </span>
					                                       </label>
					                                    </div>
					                                 </div>
					                                 <div class="col-md-6 col-12">
					                                    <div class="form-box">
					                                    <c:if test="${isLaMinor && not empty proposerPersonalDetails.dob}">
					                                        <fmt:setLocale value="en-US" />
													        <fmt:parseDate var="proposer_parsedDob" pattern="yyyy-MM-dd" value="${proposerPersonalDetails.dob}" />
													        <fmt:formatDate var="proposer_formattedDob" value='${proposer_parsedDob}' pattern='dd/MM/yyyy' />
												        </c:if>
												        <c:if test="${commonDetails.spouseExistYn == 'Y' && not empty spousePersonalDetails.dob}">
					                                        <fmt:setLocale value="en-US" />
													        <fmt:parseDate var="spouse_parsedDob" pattern="yyyy-MM-dd" value="${spousePersonalDetails.dob}" />
													        <fmt:formatDate var="spouse_formattedDob" value='${spouse_parsedDob}' pattern='dd/MM/yyyy' />
												        </c:if>
					                                       <label class="custom-field two">
					                                          <aui:input name="nomineeDateOfBirth" onChange="validateNomineeDOB()" 
					                                             placeholder="&nbsp;" label="" cssClass="validate nominee-tab" type="text" onKeyup="validateRadioButtonsOnChange('nomineeFM','nomineeDateOfBirth')"
					                                             value="${ proposer_formattedDob != null ? proposer_formattedDob : (spouse_formattedDob != null ? spouse_formattedDob : '') }" maxLength="10" minLength="0" wrappedField="<%=true %>">
					                                             <aui:validator name="required" errorMessage="label-please-provide-a-valid-nominee-date-of-birth"></aui:validator>
					                                             <aui:validator name="custom" errorMessage="Please provide a valid date">
																    function (val, fieldNode, ruleValue) {
																        return isValidDateInRangeNominee(val);
																    }
																</aui:validator>
																                                          </aui:input>
					                                          <span class="placeholder">
					                                             <liferay-ui:message key="label-nominee-dob" />
					                                          </span>
					                                          <!--<p class="nominee-date-icon"> <img src="/o/edelweisstokio-theme/images/praposal/date.png" alt="icon"></p>-->
					                                       </label>
					                                    </div>
					                                 </div>
					                                 
					                                 <!-- Mobile Number -->
													 <div class="col-md-6 col-12">
														<div class="form-box">
															<label class="custom-field two">
																<aui:input
																	name="nomineeMobileNumber" placeholder="&nbsp;" 
																	label="" cssClass="validate vnumber"
																	type="text" value="" maxLength="10"
																	minLength="0" wrappedField="<%=true %>">
																	<aui:validator name="required" errorMessage="mobile-number-error-message"/>
																</aui:input> 
																<span class="placeholder">
																	<liferay-ui:message	key="label-mobile-number" />
																</span>
															</label>
														</div>
													 </div>
													
													 <!-- Appointee Email Id -->
													 <div class="col-md-6 col-12">
														<div class="form-box">
															<label class="custom-field two">
																<aui:input
																	name="nomineeEmailId" placeholder="&nbsp;" 
																	label="" cssClass="validate" type="text"
																	value="" maxLength="30"
																	minLength="0" wrappedField="<%=true %>">
																	<aui:validator name="email"></aui:validator>
																	<aui:validator name="required" errorMessage="email-error-message"/>	
																</aui:input>
																<span class="placeholder"><liferay-ui:message key="label-email-id" />*</span>
															</label>
														</div>
													 </div>
					                                 
					                                  <div class="col-md-6 col-12">
					                                    <div class="form-box">
					                                       <label class="custom-field two">
					                                          <aui:input name="nomineePercentage" onKeyup="validateRadioButtonsOnChange('nomineeFM','nomineePercentage')"
					                                             placeholder="&nbsp;" label="" cssClass="validate vnumber" type="text" 
					                                             value="100" maxLength="3" minLength="1" wrappedField="<%=true %>">
					                                             <aui:validator name="required" errorMessage="label-please-provide-a-valid-percentage-value"></aui:validator>
					                                             <aui:validator name="custom" errorMessage="label-please-provide-a-valid-percentage-value">
					                                                 function(val){	
					                                                 		let total = getTotalNomineePercentage() + Number(val);                                               
												                              	return total > 100 ? false : true;	
					                                                       
											                            }
					                                             </aui:validator>
					                                             <aui:validator name="max" errorMessage="label-please-provide-a-valid-percentage-value">100</aui:validator>
					                                             <aui:validator name="min" errorMessage="label-please-provide-a-valid-percentage-value">1</aui:validator>
					                                             <aui:validator name="number"></aui:validator>
					                                          </aui:input>
					                                          <span class="placeholder">
					                                             <liferay-ui:message key="label-nominee-percentage" />
					                                          </span>
					                                       </label>
					                                    </div>
					                                 </div>
					                                 <div class="col-md-6 col-12">
					                                    <div class="form-box">
					                                       <div class="popup-select nominee select-container">
					                                          <aui:select title=" " name="nomineeRelationship" label="" cssClass="validate select2-select"
					                                             data-placeholder="<%=LanguageUtil.get(request, "label-nominee-relationship") %>">
					                                             <aui:option>
					                                                <liferay-ui:message key="label-nominee-relationship" />
					                                             </aui:option>
					                                             <c:forEach var="relation" items="${masterMap['Relationship of Nominee with Life Assured']}">
					                                                <aui:option value="${relation.value}" data-name="${relation.name}"
					                                                   label="${relation.name}" selected="${(commonDetails.spouseExistYn == 'Y' && commonDetails.productName != 'Wealth Secure+' && relation.name =='Spouse')}">
					                                                </aui:option>
					                                             </c:forEach>
					                                             <aui:validator name="required" errorMessage="label-please-select-nominee-relationship"> 
							                                             function(val){	
							                                               if(val == '' || !val){
							                                               		return false
							                                               }				                                                   
											                               return true;
											                            }
					                                             </aui:validator>
					                                          </aui:select>
					                                          <div class="select-error-message" style="display: none;"><liferay-ui:message key="label-please-select-nominee-relationship" /></div>
					                                       </div>
					                                    </div>
					                                 </div>  
					                                 
					                                 <div class="col-md-12 col-12">
					                                    <div class="form-box">
					                                       <p class="mb-1">
					                                          <liferay-ui:message key="label-nominee-gender" />
					                                       </p>
					                                       <div class="radio_container">
					                                          <c:forEach var="genderData" items="${masterMap['Gender']}">
					                                             <aui:input name='nomineeGender' id="nomineeGender${genderData.name}" data-name="${genderData.name}"
					                                                cssClass="validate validateNGender" type="radio" label=""
					                                                checked="${ (isLaMinor && proposerPersonalDetails.genderDataId == genderData.value)||  (commonDetails.spouseExistYn == 'Y' && spousePersonalDetails.genderDataId == genderData.value && commonDetails.productName != 'Wealth Secure+')}"
					                                                value="${genderData.value}">
					                                                <aui:validator name="required" errorMessage="label-please-select-a-nominee-gender"></aui:validator>
					                                             </aui:input>
					                                             <label for='<portlet:namespace/>nomineeGender${genderData.name}'>
					                                             ${genderData.name} </label>
					                                          </c:forEach>
					                                       </div>
					                                    </div>
					                                 </div>
					                                 
					                                 <!-- Appointee Start -->
					                                 <%@ include file="/proposal_form/nominee_detailes/appointee_details.jsp"%>
					                                 <!-- Appointee End -->
					                              </div>
					                           </div>
					                           
					                           <!-- Nominee Address Detailes Start -->
					                           <div class="hide--nominee--address nominee--address--block">
						                           <div class="location-field">
						                              <%@ include file="/proposal_form/nominee_detailes/address_details.jsp"%>
						                           </div>
						                           <!-- Bank Account Details Start -->
						                           <div class="location-field">
						                           	<%@ include file="/proposal_form/nominee_detailes/bank_details.jsp"%>
						                           </div>
					                               <!-- Bank Account Details End -->
					                           </div>
					                           <!-- Nominee Address Detailes end -->

					                           <div class="location-field">
					                              <div class="row">
					                              	<div class="col-12">
					                                    <div class="add-nominee-button center-btn">
					                                       <aui:button value="save" cssClass="edto-btn-primary"  type="submit" id="btnAddNominee"/>
					                                    </div>
					                                 </div>
					                              </div>
					                           </div>
					                        </div>
					                     </div>
			                    	</div>
			                	</div>
		            		</div>
		         		</div>
         			</aui:form>
            	</div>
            </div>
        </div>
    </div>
</div>





<script>
$(document).ready(function(){
		
   var container = document.getElementById('nominee_records_table_container');
   var button = document.getElementById('slide');
   var back = document.getElementById('slideBack');

	/**
	 * Identified Male Relations
	 */
    const maleRelations = [
      'Father',
      'Spouse',
      'Brother',
      'Son',
      'Grandfather',
      'Grand Son',
      'Employer',
      'Guardian',
      'Husband',
      'Nephew',
      'Other',
      'Himself/Herself',
      'Partner'
   ];
    /**
	 * Identified Male Relations
	 */
    const femaleRelations = [
        'Mother',
        'Spouse',
        'Sister',
        'Daughter',
        'Grandmother',
        'Grand Daughter',
        'Employer',
        'Guardian',
        'Himself/Herself',
        'Niece',
        'Partner',
        'Wife'
     ];
    
     
     
   button.onclick = function () {
       scrollTable('right', 25, 100, 10);
   };

   back.onclick = function () {
       scrollTable('left', 25, 100, 10);
   };

   container.addEventListener('scroll', function () {
       // Check if the table is fully scrolled to the right
       if (container.scrollLeft === container.scrollWidth - container.clientWidth) {
           button.disabled = true;
       } else {
           button.disabled = false;
       }

       // Check if the table is fully scrolled to the left
       if (container.scrollLeft === 0) {
           back.disabled = true;
       } else {
           back.disabled = false;
       }
   });

   function scrollTable(direction, speed, distance, step) {
       var scrollAmount = 0;
       var slideTimer = setInterval(function () {
           if (direction === 'left') {
               container.scrollLeft -= step;
           } else {
               container.scrollLeft += step;
           }
           scrollAmount += step;
           if (scrollAmount >= distance) {
               clearInterval(slideTimer);
           }
       }, speed);
   }

   /**
	 * Update the dropdown options
	 */
   updateNomineeDropDown = function() {
      let gender = document.querySelector('.validateNGender:checked')?document.querySelector('.validateNGender:checked').value:null;
		
      var nomineeRelationship = document.getElementsByName(portletNamespace+"nomineeRelationship")[0];
      var nomineeValue = nomineeRelationship.value;

      var nomineeRelationshipDropdown = $("#" + portletNamespace + "nomineeRelationship");
      if(!nomineeRelationshipDropdown || !nomineeRelationshipDropdown.length){
         return;   
      }

      var relationMaster = masterJson['Relationship of Nominee with Life Assured'];
      console.log(gender);
      if(gender==3){
         relationMaster = masterJson['Relationship of Nominee with Life Assured'].filter(option => maleRelations.includes(option.name));
      }else if(gender==4 || gender==1802 || gender==1970){
         relationMaster = masterJson['Relationship of Nominee with Life Assured'].filter(option => femaleRelations.includes(option.name));
      }

      nomineeRelationshipDropdown.empty();
      $.each(relationMaster, function(index, option) {
         nomineeRelationshipDropdown.append($('<option>', {
            value: option.value,
            text: option.name,
            'data-name': option.name
         }));
      });

      var optionExists = Array.from(nomineeRelationship.options).some(option => option.value === nomineeValue);
      if (optionExists) {
         nomineeRelationship.value = nomineeValue;
      } else{
         nomineeRelationshipDropdown.val("").change();
      }
   }

   /**
	 * Update the dropdown on the change of the gender value
	 */
   updateNomineeDropDown();
   $("input[name='"+portletNamespace+"nomineeGender']").on("change", updateNomineeDropDown);
   updateAppointeeDropdown();
});

/**
 * Identified for Male appointee Relation
 **/
 const maleAppointeeRelation = [
	'Father',
	'GrandFather',
	'Other',
	'Spouse',
	'Uncle',
	'Brother'
 ];
 /**
 *  Identifier for the femaleAppointee Relationship
 **/
   const femaleAppointeeRelation=[
	 'Aunt',
	 'GrandMother',
	 'Mother',
	 'Other',
	 'Sister',
	 'Spouse'
   ];
 
// update the appointee relationship dropdown
   function updateAppointeeDropdown(){
   	var genderAppointee = document.querySelector('.validateAGender:checked')?document.querySelector('.validateAGender:checked').value:null;
		console.log(genderAppointee);
       var appointeeRelationship = document.getElementsByName(portletNamespace+"appointeeRelationship")[0];
       var appointeeeValue = appointeeRelationship.value;

       var appointeeRelationshipDropdown = $("#" + portletNamespace + "appointeeRelationship");
       if(!appointeeRelationshipDropdown || !appointeeRelationshipDropdown.length){
          return;   
       }
       
       let relationMaster = masterJson['Relationship Of Appointee to Nominee'];
       if(genderAppointee==3){
          relationMaster = masterJson['Relationship Of Appointee to Nominee'].filter(option => maleAppointeeRelation.includes(option.name));
       }else if(genderAppointee==4 || genderAppointee==1802 || genderAppointee==1970){
          relationMaster = masterJson['Relationship Of Appointee to Nominee'].filter(option => femaleAppointeeRelation.includes(option.name));
       }
       
       appointeeRelationshipDropdown.empty();
      
       $.each(relationMaster, function(index, option) {
       	 if (option.name !== "None") {
       	appointeeRelationshipDropdown.append($('<option>', {
             value: option.value,
             text: option.name,
             'data-name': option.name
          }));
       	 }
       });
       
       let optionExists = Array.from(appointeeRelationship.options).some(option => option.value === appointeeeValue);
       if (optionExists) {
       	appointeeRelationship.value = appointeeeValue;
       } else{
       	appointeeRelationshipDropdown.val("").change();
       }
   }
   
   
   $("input[name='"+portletNamespace+"appointeeGender']").on("change", updateAppointeeDropdown);
</script>

<aui:script>

function isValidDateInRangeNominee(val) {
   // Split the input date into day, month, and year components
   var dateParts = val.split("/");
   if (dateParts.length !== 3) {
       return false; // Date format is not correct
   }

   var day = parseInt(dateParts[0], 10);
   var month = parseInt(dateParts[1], 10);
   var year = parseInt(dateParts[2], 10);

   // Check for valid month and day values
   if (month < 1 || month > 12 || day < 1 || day > 31) {
       return false; // Invalid month or day
   }

   // Ensure the year is at least 1900 and not in the future
   var currentYear = new Date().getFullYear();

   if (year < 1900 || year > currentYear) {
       return false;
   }

   // Adjust two-digit years to the correct century
   if (year < 100) {
       if (year >= 0 && year <= 99) {
           year += (year < currentYear % 100) ? 2000 : 1900;
       } else {
           return false; // Invalid two-digit year
       }
   }

   // Create a new Date object with the parsed components
   var date = new Date(year, month - 1, day); // Subtract 1 from month since it's zero-based

   // Get the current date without time information
   var currentDate = new Date();
   currentDate.setHours(0, 0, 0, 0);

   // Check if the date is a valid date and not equal to the current date
   if (!isNaN(date) && date < currentDate) {
       return true; // Valid date within the range and not equal to the current date
   }

   return false; // Invalid date, out of range, or equal to the current date
}



	
var nominees = [];
var nomineesTableEntries = {};
var rowId = 1;

function disableAddNominee(val = 'N'){
  let disable = val == 'N' ? false : true;
  $('#<portlet:namespace/>btnAddNominee').prop('disabled', disable);
  if(disable){
      $("#nomineeModalBtn").addClass("disable-click");
  }else{
      $("#nomineeModalBtn").removeClass("disable-click");
  }
}

function showTotalPercentageError(val='S'){
	if(val == 'S'){
		$('.nominee-percentage-error').removeClass('hide');
	}else{
		$('.nominee-percentage-error').addClass('hide');
	}
}

function validateNomineeDOB(type = 'N'){

console.log('isMinor......',isMinor(type))

  if(isMinor(type)){
      $('.appointee--block').removeClass('hide--appointee');
      $('.nominee--address--block').addClass('hide--nominee--address');
  }else{
     $('.appointee--block').addClass('hide--appointee');
     $('.nominee--address--block').removeClass('hide--nominee--address');
  }
}

function isMinor(type = 'N') {
  const dob = type == 'N' ? $('#<portlet:namespace/>nomineeDateOfBirth').val() : $('#<portlet:namespace/>appointeeDateOfBirth').val();

  if (!dob) {
    return false;
  }

  const formattedDate = formatDate(dob);

  if (formattedDate.length === 10 && ageCalc(new Date(formattedDate)) < 18) {
    return true;
  }

  return false;
}

  
 function formatDate(dateString) {
    if (dateString && dateString.includes("/")) {
      const dateArr = dateString.split("/");
      return dateArr[2] + "-" + dateArr[1] + "-" + dateArr[0];
    }
    return dateString;
  }
  
  function ageCalc(date) {
        const n = new Date
          , e = new Date(date);
        let t = n.getFullYear() - e.getFullYear();
        const a = n.getMonth() - e.getMonth();
        return (a < 0 || 0 === a && n.getDate() < e.getDate()) && t--,t
    }
    
    
  function validateNomineePercentageonAdd(){
  
	   let nomineePercentage = getTotalNomineePercentage() + Number($('#<portlet:namespace />nomineePercentage').val());   
	   
	   if(nomineePercentage > 100){        
	        return true;
	   }
	   
	   if(nomineePercentage == 100){
	      disableAddNominee('Y');
	      showTotalPercentageError('H')
	   }else if(nomineePercentage < 100){
	     disableAddNominee('N');
	      showTotalPercentageError('S');
	   }
	   
	   return false;
  
  } 
  
  function validateNomineePercentageonRemove(){
  
  let nomineePercentage = getTotalNomineePercentage();
  
  	if(nomineePercentage >= 100){
  	    disableAddNominee('Y');
	    showTotalPercentageError('H')
  	}else if(nomineePercentage < 100){
  	      disableAddNominee('N');
	      showTotalPercentageError('S');
  	}
  
    return nomineePercentage;
  }
  
  function getTotalNomineePercentage(){
	  if(!nominees || nominees?.length == 0 ){
	  		return 0;
	  }
	  let nomineePercentage=0;
	  if($('#hiddenInput').val()==1){
	  let rowId=Number($('#hiddenInput').data('rowId'));
	  	nomineePercentage = nominees.filter((nominee)=>nominee.nomine_row_id!=rowId).reduce((total, nominee)=>{
        return total + Number(nominee.nominee_percentage);
     }, 0);
	  }else{
	  	nomineePercentage = nominees.reduce((total, nominee)=>{
        return total + Number(nominee.nominee_percentage);
     }, 0);
	  }
	   
     
     if(Number.isInteger(Number(nomineePercentage))){
         return nomineePercentage;
     }
     
     return 0;  
  }


function  <portlet:namespace />addNominee(){
     if(validateNomineePercentageonAdd()){
        return false;
     }
     if($('#hiddenInput').val()==1){
     let rowId=Number($('#hiddenInput').data('rowId'));
     nominees = nominees.filter((nominee) => nominee.nomine_row_id != Number(rowId));
 	 delete nomineesTableEntries[rowId];
     }else{
     	if(nominees.length>2){
     		return false;
     	}
     }
     const table = $('#nominee_records_table');
     const nominee = {};

		nominee.nominee_details_id = null;
		nominee.created_by = "${commonDetails.applicationNumber}";
		nominee.nomine_row_id = rowId;
		rowId = rowId + 1;
		nominee.nominee_name =  $('#<portlet:namespace />nomineeName').val(); 
		nominee.nominee_dob =  $('#<portlet:namespace />nomineeDateOfBirth').val();
		nominee.nominee_gender_data_id = $('input[name=<portlet:namespace />nomineeGender]:checked').val(); 
		nominee.nominee_percentage = $('#<portlet:namespace />nomineePercentage').val(); 
		nominee.nominee_la_relationship_data_id = $('#<portlet:namespace />nomineeRelationship').val();
		
		//nominee modification
		nominee.email_address = $('#<portlet:namespace />nomineeEmailId').val();
		nominee.mobile_number = $('#<portlet:namespace />nomineeMobileNumber').val();
		
		if(!nomineeIsAddressSame()){
			nominee.current_address_line1 = $('#<portlet:namespace />nominee_ca_address_line_one').val();
			nominee.current_address_line2 = $('#<portlet:namespace />nominee_ca_address_line_two').val();
			nominee.current_address_line3 = $('#<portlet:namespace />nominee_ca_address_line_three').val();
			nominee.current_city = $('#<portlet:namespace />nominee_ca_city').val();
			nominee.current_state = $('#<portlet:namespace />nominee_ca_state').val();
			nominee.current_pincode = $('#<portlet:namespace />nominee_ca_pincode').val();
			nominee.correspondance_add = $('input[name="'+ namespace + 'nominee_correspondingAddress"]:checked').val();
		}else{
			nominee.current_address_line1 = "";
			nominee.current_address_line2 = "";
			nominee.current_address_line3 = "";
			nominee.current_city = "";
			nominee.current_state = "";
			nominee.current_pincode = "";
			nominee.correspondance_add = "";
		}
		
		if(isMinor('N')){
			nominee.appointee_name = $('#<portlet:namespace />appointeeName').val(); 
			nominee.appointee_gender_data_id = $('input[name=<portlet:namespace />appointeeGender]:checked').val();
			nominee.appointee_dob = $('#<portlet:namespace />appointeeDateOfBirth').val();
			nominee.appointee_nominee_relationship_data_id = $('#<portlet:namespace />appointeeRelationship').val();
			nominee.appointee_mobile_number = $('#<portlet:namespace />appointeeMobileNumber').val();
			nominee.appointee_email_address = $('#<portlet:namespace />appointeeEmailId').val();
			
			//If Minor Add Below
			nominee.bank_account_number = "";
			nominee.ifsc_code = "";
			nominee.bank_name = "";
			nominee.bank_branch_location = "";
			
			nominee.permanent_address_line1 = "";
			nominee.permanent_address_line2 = "";
			nominee.permanent_address_line3 = "";
			nominee.permanent_city = "";
			nominee.permanent_state = "";
			nominee.permanent_pincode = "";
			nominee.current_permanent_same_yn = "";
		}else{
			nominee.appointee_name = '';
			nominee.appointee_gender_data_id = '';
			nominee.appointee_dob = '';
			nominee.appointee_nominee_relationship_data_id = '';
			nominee.appointee_mobile_number = '';
			nominee.appointee_email_address = '';
			
			//If Not Minor Add Below
			nominee.bank_account_number = $('#<portlet:namespace />nominee_bank_account_number').val();
			nominee.ifsc_code = $('#<portlet:namespace />nominee_ifsc_code').val();
			nominee.bank_name = $('#<portlet:namespace />nominee_bank_name').val();
			nominee.bank_branch_location = $('#<portlet:namespace />nominee_bank_branch_location').val();
			
			nominee.permanent_address_line1 = $('#<portlet:namespace />nominee_pa_address_line_one').val();
			nominee.permanent_address_line2 = $('#<portlet:namespace />nominee_pa_address_line_two').val();
			nominee.permanent_address_line3 = $('#<portlet:namespace />nominee_pa_address_line_three').val();
			nominee.permanent_city = $('#<portlet:namespace />nominee_pa_city').val();
			nominee.permanent_state = $('#<portlet:namespace />nominee_pa_state').val();
			nominee.permanent_pincode = $('#<portlet:namespace />nominee_pa_pincode').val();
			nominee.current_permanent_same_yn = $('input[name="'+ namespace + 'nominee_isAddressSame"]:checked').val();
		}
		
		const nomineeTable = [];
		nomineeTable.push(nominee.nominee_name);
		nomineeTable.push(nominee.nominee_dob);
		nomineeTable.push($('input[name=<portlet:namespace />nomineeGender]:checked').data("name"));
		nomineeTable.push(nominee.nominee_percentage);
		nomineeTable.push($('#<portlet:namespace />nomineeRelationship option:selected').data("name"));
		nomineeTable.push(nominee.appointee_name);		
		nomineeTable.push(nominee.appointee_dob);
		if(isMinor('N')){
		   nomineeTable.push($('input[name=<portlet:namespace />appointeeGender]:checked').data("name"));
		}else{
		   nomineeTable.push('');
		}
		
		nomineeTable.push($('#<portlet:namespace />appointeeRelationship option:selected').data("name"));
		
		//nominee.nomineeTable = nomineeTable;
		
		nomineesTableEntries[nominee.nomine_row_id] = nomineeTable;
		
		console.log('nominee......',nominee);
		nominees.push(nominee);
        console.log('nominees......',nominees);
        
       displayNomineeTable(nominees);
       $('#nomineeModal').modal('hide');
       if(nominees.length==3){
       	$("#nomineeModalBtn").addClass("disable-click");
       }    
       resetForm();
       $('.appointee--block').addClass('hide--appointee');
}

function resetForm(){
	$('#<portlet:namespace />nomineeName').val('');
	$('#<portlet:namespace />nomineeDateOfBirth').val('');
	
	setNomineePercentage();
	
	$('input[name=<portlet:namespace />nomineeGender]').prop('checked', false);
	
	$('#<portlet:namespace />nomineeRelationship').val('').change();
	$('#<portlet:namespace />appointeeName').val('');
	$('input[name=<portlet:namespace />appointeeGender]').prop('checked', false);
	$('#<portlet:namespace />appointeeDateOfBirth').val('');
	$('#<portlet:namespace />appointeeRelationship').val('').change();
}

function removeNominee(event){
  
  let rowId = event.currentTarget.parentNode.parentNode.getAttribute('data-id');
  console.log('rowId.......',rowId)
  event.currentTarget.parentNode.parentNode.remove();
  const appointeeRow = document.getElementById(rowId + '_appointee');
  if(appointeeRow){
     appointeeRow.remove();
  }
  nominees = nominees.filter((nominee) => nominee.nomine_row_id != Number(rowId));
  delete nomineesTableEntries[rowId];
  validateNomineePercentageonRemove();
  setNomineePercentage();
  updateSerielNumber();
  if(nominees?.length == 0){
		$('#nominee_records_table_container').hide();
		$('#nominee_records_table_scroll').hide();
  }
}


function displayNomineeTable(nomineeList = []){
     console.log('nomineeList......',nomineeList);
     
     $('#nominee_records_table_container').hide();
	 $('#nominee_records_table_scroll').hide();
	 
     $('#nominee_records_table tbody').html('');
     
     nomineeList?.length > 0 &&  nomineeList.forEach((nominee) => {
		let tbody = document.querySelector('#nominee_records_table_container tbody');
       let isNomineeMinor = validateDOB(nominee.nominee_dob);
       var newRow = document.createElement("tr");
       var serialCell = document.createElement('td');
       var count = 0;
		for (var j = 0; j < tbody.rows.length; j++) {
		  var row = tbody.rows[j];
		  if (row.classList.contains('record')) {
		    count++;
		  }
		}
	  serialCell.textContent =count+1;
	  newRow.appendChild(serialCell);
       
       var lstCell = document.createElement("td");

		var deleteButton = document.createElement("button");
		deleteButton.className = "badge w-100 d-block nominee-remove";
		deleteButton.setAttribute("onclick", "removeNominee(event)");
		deleteButton.type = "button";
		deleteButton.innerHTML = '<i class="far fa-trash-alt"></i>';
		

		var editButton = document.createElement("button");
		editButton.className = "badge w-100 d-block nominee-edit"; 
		editButton.setAttribute("onclick", "editNominee(event)"); 
		editButton.type = "button";
		editButton.innerHTML = '<i class="far fa-edit"></i>';
		
		lstCell.appendChild(editButton);
		lstCell.appendChild(deleteButton);
        newRow.append(lstCell);
        if(isNomineeMinor){
         serialCell.setAttribute('rowspan', 2);
         lstCell.setAttribute('rowspan', 2);
        }
       newRow.setAttribute("data-id", nominee.nomine_row_id);
       newRow.classList.add('record');
       let i = 0;
       for (let key in nomineesTableEntries[nominee.nomine_row_id]) {
       		if( i <= 4){
       		
            	var cell = document.createElement("td");
		   		cell.textContent = nomineesTableEntries[nominee.nomine_row_id][key];
		   		newRow.appendChild(cell);  
       		}
		   		i++;       
        }
        
          
          
          $('#nominee_records_table tbody').append(newRow);
          
         
          
          if(isNomineeMinor){          
		       var appointeeRow = document.createElement("tr");
		       appointeeRow.setAttribute("id", nominee.nomine_row_id + '_appointee');
		       let i = 0;
		       let label = 'Name Of Appointee';
		       for (let key in nomineesTableEntries[nominee.nomine_row_id]) {
		       		if( i > 4){
		       		
		       			if( i == 6){
		       			  label = 'Appointee Date Of Birth';
		       			}else if(i == 7){
		       				label = 'Appointee Gender';
		       			}else if(i == 8){
		       				label = 'Relationship of Appointee to Nominee';
		       			}
		       		
		            	var cell = document.createElement("td");
		            	if( i === 8){
		            	   cell.setAttribute('colspan', 2);
		            	}
				   		cell.innerHTML =   '<div class="nominee-td-bold">' + label + '</div> <div>'+ nomineesTableEntries[nominee.nomine_row_id][key] +'</div>';
				   		appointeeRow.appendChild(cell);  
		       		}
				   		i++;       
		        }
		          $('#nominee_records_table tbody').append(appointeeRow);
		           
          }
          
           
     })

	if(nomineeList?.length > 0){
		$('#nominee_records_table_container').show();
		$('#nominee_records_table_scroll').show();
	}
	
}


function setNomeniees(){
    var pfResponse = JSON.parse('${pfResponse}');
    let temp = pfResponse?.response_data?.nominee_details;
    
    temp = temp?.length > 0 && temp.map((nominee, index) => {
        nominee.nomine_row_id = rowId;
        rowId = rowId + 1;
    
        const nomineeTable = [];
		nomineeTable[0] = nominee.nominee_name || '';
		if (nominee.nominee_dob) {
				  const dateParts = nominee.nominee_dob.split('-');
				  if (dateParts.length === 3 && dateParts[0].length === 4 && dateParts[1].length === 2 && dateParts[2].length === 2) {
				    nomineeTable[1] = dateParts[2]+'/'+dateParts[1]+'/'+dateParts[0];
				  } else {
				    nomineeTable[1] = nominee.nominee_dob;
				  }
				} else {
				  nomineeTable[1] = '';
				}
		//nomineeTable[1] = nominee.nominee_dob || '';
		
		nomineeTable[3] = nominee.nominee_percentage || '';
		nomineeTable[5] = nominee.appointee_name || '';
		if(nominee.appointee_dob){
		 const dateParts1 = nominee.appointee_dob.split('-');
				  if (dateParts1.length === 3 && dateParts1[0].length === 4 && dateParts1[1].length === 2 && dateParts1[2].length === 2) {
				    nomineeTable[6] = dateParts1[2]+'/'+dateParts1[1]+'/'+dateParts1[0];
				  } else {
				    nomineeTable[6] = nominee.appointee_dob;
				  }
				} else {
				  nomineeTable[6] = '';
				}
		//nomineeTable[6] = nominee.appointee_dob || '';
		 
		<c:forEach var="genderData" items="${masterMap['Gender']}">
		    if(Number('${genderData.value}') == nominee.nominee_gender_data_id){
		        nomineeTable[2] = '${genderData.name}';
		    }
		    
		    if(Number('${genderData.value}') == nominee.appointee_gender_data_id){
		        nomineeTable[7] = '${genderData.name}';
		    }
		</c:forEach>
		
		<c:forEach var="relation" items="${masterMap['Relationship of Nominee with Life Assured']}">
		    if(Number('${relation.value}') == nominee.nominee_la_relationship_data_id){
		        nomineeTable[4] = '${relation.name}';
		    }
		</c:forEach>
		
		<c:forEach var="relation" items="${masterMap['Relationship Of Appointee to Nominee']}">
		    
		    if(Number('${relation.value}') == nominee?.appointee_nominee_relationship_data_id){
		        nomineeTable[8] = '${relation.name}';
		    }
		</c:forEach>
		
		if(!nomineeTable[2]){
			nomineeTable[2] = '';
		}
		if(!nomineeTable[7]){
			nomineeTable[7] = '';
		}
		if(!nomineeTable[4]){
			nomineeTable[4] = '';
		}
		if(!nomineeTable[8]){
			nomineeTable[8] = '';
		}
		
		
		//nominee.nomineeTable = nomineeTable;
		
		nomineesTableEntries[nominee.nomine_row_id] = nomineeTable;
		return nominee;
    
    })
    console.log('temp....',temp)
    if(!temp == false){
       nominees = temp;
    }
    
    displayNomineeTable(temp);
    validateNomineePercentageonRemove();

    
    
    setNomineePercentage();
    if(nominees.length>=3){
			$("#nomineeModalBtn").addClass("disable-click");
		}
}
setNomeniees();

function setNomineeDetails(){
	console.log("inside the setNomineeDetails");
   var updatedNomineesList = JSON.parse(JSON.stringify(nominees));
   updatedNomineesList.forEach((nominee, index) => {
      nominee.nomine_row_id = index + 1;
   });
   return updatedNomineesList;
}

function setNomineePercentage(){
    let totalPercentage = 100 - getTotalNomineePercentage();
	if(totalPercentage > 0){
		$('#<portlet:namespace />nomineePercentage').val(totalPercentage);
	}else{
	    $('#<portlet:namespace />nomineePercentage').val(0);
	}
}

function validateSaveAndContinueNominee(){


     if(!validateNomineeSection()){  
        $('.error-msg').html('<liferay-ui:message key="label-total-percentage-can-not-be-less-than-100" />')
        $('#lmsModal').modal({backdrop: 'static', keyboard: false}, 'show'); 
        return false;
     }
     
     savePF();
     openNextStep(4);
     
}

$('.validateNGender').change(
    function(){    
         let currentFormValidator = Liferay.Form.get('<portlet:namespace/>nomineeFM').formValidator;  
         currentFormValidator.validateField("<portlet:namespace/>nomineeGender");
    });
    
 $('.validateAGender').change(
    function(){    
         let currentFormValidator = Liferay.Form.get('<portlet:namespace/>nomineeFM').formValidator;  
         currentFormValidator.validateField("<portlet:namespace/>appointeeGender");
    });
    
  function validateNomineeSection(){
  	let nomineePercentage = getTotalNomineePercentage();
  	if(nomineePercentage < 100 || nomineePercentage > 100){
  		return false;
  	}
  	
  	for(const nomine of nominees){
  		
  		if(checkNulll(nomine?.nominee_name) || checkNulll(nomine?.nominee_la_relationship_data_id) || checkNulll(nomine?.nominee_dob) || checkNulll(nomine?.nominee_gender_data_id)){ 		
  			console.log('Nominee details are null ....')
  			return false;
  		}
  		
  		let isNomineeMinor = validateDOB(nomine.nominee_dob);
  		
  		console.log('isNomineeMinor ....',isNomineeMinor)
  		
  		if(isNomineeMinor && (checkNulll(nomine?.appointee_name) || checkNulll(nomine?.appointee_nominee_relationship_data_id) || checkNulll(nomine?.appointee_dob) || checkNulll(nomine?.appointee_gender_data_id))){
  		   console.log('Appointee details are null ....')
  		   return false;  		
  		}
  		
  		if(isNomineeMinor && validateDOB(nomine.appointee_dob)){
  		   console.log('Appointee is minor ....')
  		   return false;
  		}
  	}
  	
  	return true;
  
  }  
  
function validateDOB(dob) {
  const formattedDate = formatDate(dob);

  if (formattedDate != null && formattedDate.length === 10 && ageCalc(new Date(formattedDate)) < 18) {
    return true;
  }

  return false;
}
  
  function checkNulll(val){
      console.log('Nominee details .............',val)
	  if(!val || val === '' || val == null){
	  	return true;
	  }
	  
	  return false;
  
  }
  
  $('#nomineeModalBtn').click(function(){
    	$('#hiddenInput').val('0');
    });
  
    

function editNominee(event){
	var otherDetailsFormValidator = Liferay.Form.get(portletNamespace+'nomineeFM').formValidator;
	otherDetailsFormValidator.resetAllFields();
	let rowId = event.currentTarget.parentNode.parentNode.getAttribute('data-id');
	$('#hiddenInput').val('1');
	$('#hiddenInput').data('rowId',rowId);
	let curNominee = nominees.find((nominee) => nominee.nomine_row_id == Number(rowId));
	$('#nomineeModal').modal({backdrop: 'static', keyboard: false}, 'show');
		
	$('#<portlet:namespace />nomineeName').val(curNominee.nominee_name);
	$('#<portlet:namespace />nomineeEmailId').val(curNominee.email_address);
	$('#<portlet:namespace />nomineeMobileNumber').val(curNominee.mobile_number);
	var nomineeDob,appointeeDob;
	if(curNominee.nominee_dob){
		var dateParts = curNominee.nominee_dob.split('-');
			if (dateParts.length === 3 && dateParts[0].length === 4 && dateParts[1].length === 2 && dateParts[2].length === 2) {
				nomineeDob = dateParts[2]+'/'+dateParts[1]+'/'+dateParts[0];
			} else {
				nomineeDob = curNominee.nominee_dob;
			}
		$('#<portlet:namespace />nomineeDateOfBirth').val(nomineeDob);
	}else {
		$('#<portlet:namespace />nomineeDateOfBirth').val(curNominee.nominee_dob);
	}

	$('input[name="<portlet:namespace />nomineeGender"][value="' + curNominee.nominee_gender_data_id + '"]').prop('checked', true);
	$('#<portlet:namespace />nomineePercentage').val(curNominee.nominee_percentage);
	updateNomineeDropDown();
	$('#<portlet:namespace />nomineeRelationship').val(curNominee.nominee_la_relationship_data_id).trigger('change');
	let isNomineeMinor = validateDOB(curNominee.nominee_dob);
	
	if(isNomineeMinor){
	   validateNomineeDOB('N');
		$('#<portlet:namespace />appointeeName').val(curNominee.appointee_name); 
		if(curNominee.appointee_dob){
		var datePartsAppoointee = curNominee.appointee_dob.split('-');
		  if (datePartsAppoointee.length === 3 && datePartsAppoointee[0].length === 4 && datePartsAppoointee[1].length === 2 && datePartsAppoointee[2].length === 2) {
			appointeeDob = datePartsAppoointee[2]+'/'+datePartsAppoointee[1]+'/'+datePartsAppoointee[0];
		  } else {
			appointeeDob = curNominee.appointee_dob;
		  }
		$('#<portlet:namespace />appointeeDateOfBirth').val(appointeeDob);
		}else{
		$('#<portlet:namespace />appointeeDateOfBirth').val(curNominee.appointee_dob);
		}
		 
		$('input[name="<portlet:namespace />appointeeGender"][value="' + curNominee.appointee_gender_data_id + '"]').prop('checked', true);
		updateAppointeeDropdown();
		$('#<portlet:namespace />appointeeRelationship').val(curNominee.appointee_nominee_relationship_data_id).trigger('change');
		$('#<portlet:namespace />appointeeMobileNumber').val(curNominee.appointee_mobile_number);
		$('#<portlet:namespace />appointeeEmailId').val(curNominee.appointee_email_address);
		
	}else{
		validateNomineeDOB('Y');
		$('#<portlet:namespace />nominee_ca_address_line_one').val(curNominee.current_address_line1);
		$('#<portlet:namespace />nominee_ca_address_line_two').val(curNominee.current_address_line2);
		$('#<portlet:namespace />nominee_ca_address_line_three').val(curNominee.current_address_line3);
		$('#<portlet:namespace />nominee_ca_city').val(curNominee.current_city);
		$('#<portlet:namespace />nominee_ca_state').val(curNominee.current_state);
		$('#<portlet:namespace />nominee_ca_pincode').val(curNominee.current_pincode);
		$('input[name="'+ namespace + 'nominee_correspondingAddress"][value="' + curNominee.correspondance_add + '"]').prop('checked', true);


		$('#<portlet:namespace />nominee_bank_account_number').val(curNominee.bank_account_number);
		$('#<portlet:namespace />nominee_ifsc_code').val(curNominee.ifsc_code);
		$('#<portlet:namespace />nominee_bank_name').val(curNominee.bank_name);
		$('#<portlet:namespace />nominee_bank_branch_location').val(curNominee.bank_branch_location);
			
		$('#<portlet:namespace />nominee_pa_address_line_one').val(curNominee.permanent_address_line1);
		$('#<portlet:namespace />nominee_pa_address_line_two').val(curNominee.permanent_address_line2);
		$('#<portlet:namespace />nominee_pa_address_line_three').val(curNominee.permanent_address_line3);
		$('#<portlet:namespace />nominee_pa_city').val(curNominee.permanent_city);
		$('#<portlet:namespace />nominee_pa_state').val(curNominee.permanent_state);
		$('#<portlet:namespace />nominee_pa_pincode').val(curNominee.permanent_pincode);
		$('input[name="'+ namespace + 'nominee_isAddressSame"][value="' + curNominee.current_permanent_same_yn + '"]').prop('checked', true);
	}
	disableAddNominee('N');
	showTotalPercentageError('T');
}
  function closeNomineeModal(){
  let tbody = document.querySelector('#nominee_records_table_container tbody');
  	$('#nomineeModal').modal('hide');
  	$('#hiddenInput').val('0');
  	 validateNomineeDOB('Y');
  	 if(tbody.rows.length>0){
  	 	resetForm();
  	 }
  	let totalPercentage = nominees.reduce((total, nominee)=>{
  	  return total + Number(nominee.nominee_percentage);
     }, 0);
  	if(totalPercentage>=100){
  	  disableAddNominee('Y');
  	}else if(totalPercentage<100 && nominees.length!=3)
  	{
  		disableAddNominee('N');
  	}else if(totalPercentage<100 && nominees.length==3){
  		 disableAddNominee('Y');
  		 showTotalPercentageError('S');
  	}
  }
  
  function updateSerielNumber(){
  	let tbody = document.querySelector('#nominee_records_table_container tbody');
  	const rows = tbody.querySelectorAll('tr.record');

    rows.forEach((row, index) => {
      row.cells[0].textContent = index + 1;
    });
  }
  function updateRelationDropdown(){
  let otherDetailsFormValidator = Liferay.Form.get(portletNamespace+'nomineeFM').formValidator;
	otherDetailsFormValidator.resetAllFields();
  	updateNomineeDropDown();
  	let dob = $('#<portlet:namespace />nomineeDateOfBirth').val();
  	let isNomineeMinor = validateDOB(dob);
  	if(isNomineeMinor){
  		validateNomineeDOB('N');
  	}
    let tbody = document.querySelector('#nominee_records_table_container tbody');
    let  pfResponse = JSON.parse('${pfResponse}');
    let temp=pfResponse.response_data.common_data;
    if(tbody.rows.length<1 && temp.spouse_exist_yn=="Y"){
    	let genderDataValue= $('input[name=<portlet:namespace />nomineeGender]:checked').val();
    	if(genderDataValue=='3'){
    		$('#<portlet:namespace />nomineeRelationship').val('1382').trigger('change');
    	}else if(genderDataValue=='4'){
    		$('#<portlet:namespace />nomineeRelationship').val('1388').trigger('change');
    	}
    }
  }
  $('#'+portletNamespace+'nomineeDateOfBirth').on('input', function(){
  	validateNomineeDOB();
  })
</aui:script>