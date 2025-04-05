<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@ include file="init.jsp" %>

<style>
.coti-hide, .cb-hide, .io-hide, .cr-hide, .oc-hide{
	display: none;
}

</style>
<portlet:resourceURL var="getMultipleLoginDataURL" id="/get/multipleLoginData"></portlet:resourceURL>

<div class="edto-annual-wrapper">
    <div class="container">
        <div class="edtokio-bonus-rates">
            <h2 class="fontbold fs28 mb-3"><liferay-ui:message key="fatca-crs-annexure-individuals"></liferay-ui:message></h2>
            <h4 class="fontregular mb-2"><liferay-ui:message key="including-sole-proprietors"></liferay-ui:message></h4>
            <p class="fontregular mb-2"><liferay-ui:message key="please-consult-your-professional-tax-advisor-for-further-guidance-on-your-tax-residency-if-required"></liferay-ui:message></p>
            <p class="hide-fields"><liferay-ui:message key="details-under-fatca-crs-to-be-filled-by-each-policyholder-beneficiary-all-fields-in-this-form-are-mandatory"></liferay-ui:message></p>
            <div class="edto-sitemap-wrapper mt-4 py-3 px-3 mb-0">
                <aui:form name="fm" >
                    <div class="row temp">
                       <div class="col-md-6 col-12">
                            <label class="custom-field two">
                                <aui:input name="policyNumberA" label="" placeholder="&nbsp;" cssClass="valphanum" wrappedField="<%=true %>" value="${policyNumber}" maxlength="10" id="uniquePolicy">
	                                <aui:validator name="custom" errorMessage="please-enter-a-valid-policy-number">
					                    function(val, fieldNode, ruleValue) {
					                          var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
					                          document.getElementById('<portlet:namespace/>policyNumber').value = val;
					                          return policyRegex.test(val);
					                    }
					                 </aui:validator>
                                </aui:input>
                                <span class="placeholder"><liferay-ui:message key="application-no"></liferay-ui:message></span>
                            </label>
                        </div>
                        <div class="col-md-6 col-12">
                            <label class="custom-field two">
                                <aui:input name="dobA" label=""  maxLength="10" cssClass="vdate" value="${dob}" autocomplete="off" placeholder="&nbsp;" wrappedField="<%=true %>">
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    		function(val){
						                    	val = val.trim();
						                        document.getElementById('<portlet:namespace/>dob').value = val;
			                                    
			 									var regex = new RegExp("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
					                             return regex.test(val);
				                    	   }
				                    	</aui:validator>
                                </aui:input>
                                <span class="placeholder"><liferay-ui:message key="date-of-birth"></liferay-ui:message></span>
                            </label>
                        </div>
                    </div>
                
                    <div class="row orgn hide">
                        <div class="col-md-4 col-12">
                            <label class="custom-field two">
                                <aui:input name="policyNumber" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="valphanum" required="true" value="${policyNumber}" maxlength="10">
                                	<aui:validator name="custom" errorMessage="please-enter-a-valid-policy-number">
					                    function(val, fieldNode, ruleValue) {
					                          var policyRegex = new RegExp("^[0-9]{9}[a-zA-Z]{1}");
					                          document.getElementById('<portlet:namespace/>policyNumberA').value = val;
					                          return policyRegex.test(val);
					                    }
					                 </aui:validator>
                                </aui:input>
                                <span class="placeholder"><liferay-ui:message key="application-no"></liferay-ui:message></span>
                            </label>
                        </div>
                        <div class="col-md-4 col-12">
                            <label class="custom-field two">
                                <aui:input name="dob" label=""  maxLength="10" cssClass="vdate" value="${dob}" autocomplete="off" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
				                    	<aui:validator name="custom" errorMessage="please-enter-a-valid-date">
				                    		function(val){
						                    	val = val.trim();
						                        document.getElementById('<portlet:namespace/>dobA').value = val;
												var regex = new RegExp("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
					                             return regex.test(val);
				                    	   }
				                    	</aui:validator>
                                </aui:input>
                                <span class="placeholder"><liferay-ui:message key="date-of-birth"></liferay-ui:message></span>
                            </label>
                        </div>
                        
                        <div class="col-md-4 col-12 hide-fields">
                            <label class="custom-field two">
                                <aui:input name="name" label="" placeholder="&nbsp;" cssClass="valpha" wrappedField="<%=true %>" required="true">
	                                <aui:validator name="custom" errorMessage="please-enter-full-name">
										function(val, fieldNode, ruleValue) {
											var regex = new RegExp("^[a-zA-Z]+( [a-zA-Z]+)+$");
											return regex.test(val);
										}
								   </aui:validator>
                                </aui:input>
                                <span class="placeholder"><liferay-ui:message key="policy-holder-beneficiary-name"></liferay-ui:message></span>
                            </label>
                        </div>
                        
                        
                        <div class="col-md-4 col-12 hide-fields">
			                <p class="hide-fields radio-label"><liferay-ui:message key="are-you-a-tax-resident-of-country-other-than-india"> </liferay-ui:message></p>
			                <div class="updtFATCA-Form-field button-radio">
			                    <aui:input name="taxResidenceIndiaYN" type="radio" label="Yes"  value="Y" wrappedField="<%= true %>" ></aui:input>
			                    <aui:input name="taxResidenceIndiaYN" type="radio" label="No"  value="N" wrappedField="<%= true %>" checked="true"></aui:input>
			                </div>
			            </div>
			            
			            <div class="col-md-4 col-12 coti coti-hide">
			            	<p class="hide-fields country-label"><liferay-ui:message key="if-yes-please-provide-the-country-name"></liferay-ui:message></p>
                            <label class="custom-field two">
                                <aui:input name="taxResidenceSpecify" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
                                <span class="placeholder"><liferay-ui:message key="specify-country-name"></liferay-ui:message></span>
                            </label>
                        </div>
			            
			            
			            
			            <div class="col-md-4 col-12 hide-fields">
			                <p class="hide-fields radio-label"><liferay-ui:message key="are-you-holding-citizenship-of-any-other-country"> </liferay-ui:message></p>
			                <div class="updtFATCA-Form-field button-radio">
			                    <aui:input name="citizenshipIndianYN" type="radio" label="Yes" wrappedField="<%= true %>" value="Y"></aui:input>
			                    <aui:input name="citizenshipIndianYN" type="radio" label="No" wrappedField="<%= true %>" value="N" checked="true"></aui:input>
			                </div>
			            </div>
			            
			            <div class="col-md-4 col-12 oc oc-hide">
			            	<p class="hide-fields country-label"><liferay-ui:message key="if-yes-please-provide-the-country-name"></liferay-ui:message></p>
                            <label class="custom-field two">
                                <aui:input name="citizenshipSpecify" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
                                <span class="placeholder"><liferay-ui:message key="specify-country-name"></liferay-ui:message></span>
                            </label>
                        </div>
                        
                         <!-- Hidable Section Starts -->
                         <div class="coti coti-hide px-3 row">
                          	<div class="col-md-4 col-12 coti coti-hide">
				                <p class="hide-fields radio-label"><liferay-ui:message key="country-of-birth"> </liferay-ui:message></p>
				                <div class="updtFATCA-Form-field button-radio">
				                    <aui:input name="birthCountryIndiaYN" type="radio" label="India" wrappedField="<%= true %>" checked="true" value="Y"></aui:input>
				                    <aui:input name="birthCountryIndiaYN" type="radio" label="Other" wrappedField="<%= true %>" value="N"></aui:input>
				                </div>
				            </div>
				            
				            <div class="col-md-4 col-12 coti coti-hide cb cb-hide">
				                <p class="hide-fields country-label"><liferay-ui:message key="if-other-please-provide-the-country-name"> </liferay-ui:message></p>
	                            <label class="custom-field two">
	                                <aui:input name="birthCountrySpecify" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="specify-country-name"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
				                <p class="hide-fields radio-label"><liferay-ui:message key="are-you-of-indian-origin"> </liferay-ui:message></p>
				                <div class="updtFATCA-Form-field button-radio">
				                    <aui:input name="originIndianYN" type="radio" label="Yes" wrappedField="<%= true %>"  checked="true" value="Y"></aui:input>
				                    <aui:input name="originIndianYN" type="radio" label="No" wrappedField="<%= true %>" value="N"></aui:input>
				                </div>
				            </div>
				            
				            <div class="col-md-4 col-12 coti coti-hide io io-hide">
				                <p class="hide-fields country-label"><liferay-ui:message key="if-no-specify-origin"> </liferay-ui:message></p>
	                            <label class="custom-field two">
	                                <aui:input name="originSpecify" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="specify-origin"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
				                <p class="hide-fields radio-label"><liferay-ui:message key="country-of-current-residence"> </liferay-ui:message></p>
				                <div class="updtFATCA-Form-field button-radio">
				                    <aui:input name="currentResidenceIndiaYN" type="radio" label="India" wrappedField="<%= true %>"  checked="true" value="Y"></aui:input>
				                    <aui:input name="currentResidenceIndiaYN" type="radio" label="Other" wrappedField="<%= true %>" value="N"></aui:input>
				                </div>
				            </div>
				            
				            <div class="col-md-4 col-12 coti coti-hide cr cr-hide">
				               <p class="hide-fields country-label"><liferay-ui:message key="if-other-please-provide-the-country-name"> </liferay-ui:message></p>
	                            <label class="custom-field two">
	                                <aui:input name="currentResidenceSpecify" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="specify-country-name"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-12">
	                        	<p class="coti coti-hide pt-3 h5"> <liferay-ui:message key="contact-details-while-abroad-other-than-india"> </liferay-ui:message></p>
	                        </div>	
	                        
	                        <div class="col-md-12 col-12 coti coti-hide">
				                 <p class="hide-fields radio-label "><liferay-ui:message key="type-of-address"> </liferay-ui:message></p>
				                <div class="updtFATCA-Form-field button-radio">
				                    <aui:input name="addressType" type="radio" label="Residential" value="Residential" wrappedField="<%= true %>" checked="true"></aui:input>
				                    <aui:input name="addressType" type="radio" label="Business" value="Business" wrappedField="<%= true %>"></aui:input>
				                    <aui:input name="addressType" type="radio" label="Registered Office" value="Registered Office" wrappedField="<%= true %>"></aui:input>
				                </div>
				            </div>
				            
				            <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="addressLine1" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>                               
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="address"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="area" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="area"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="country" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="country"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="state" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="state"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="city" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="city"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="postCode" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true" cssClass="vnumber" maxlength="6">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="maxLength" errorMessage="please-enter-a-valid-6-digit-pin-code">6</aui:validator>
				                    	<aui:validator name="minLength" errorMessage="please-enter-a-valid-6-digit-pin-code">6</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="pin"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="emailAddress" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="email"></aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="email"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                         <div class="col-md-4 col-12 coti coti-hide">
	                         </div>
	                          <div class="col-md-4 col-12 coti coti-hide">
	                          </div>
	                          
	                        <div class="col-md-12">
	                        	<p class="coti coti-hide"> <liferay-ui:message key="telephone-no-s"> </liferay-ui:message></p>
	                        </div>
	                          
	                       <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="residencetelNumber" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="vnumber" maxlength="10">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="number"></aui:validator>
                                        <aui:validator name="minLength" errorMessage="please-enter-a-valid-10-digit-number">10</aui:validator>
                                        <aui:validator name="maxLength" errorMessage="please-enter-a-valid-10-digit-number">10</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="residence"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="officetelNumber" label="" placeholder="&nbsp;" wrappedField="<%=true %>" cssClass="vnumber" maxlength="10">
	                                	<aui:validator name="number"></aui:validator>
                                        <aui:validator name="minLength" errorMessage="please-enter-a-valid-10-digit-number">10</aui:validator>
                                        <aui:validator name="maxLength" errorMessage="please-enter-a-valid-10-digit-number">10</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="office"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="mobileNumber" label="" placeholder="&nbsp;" wrappedField="<%=true %>" 
	                                		   cssClass="vnumber" maxlength="10" pattern = "[0-9]*" inputmode="numeric">
	                                	<aui:validator name="required" errorMessage="this-field-is-required" >
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
				                    	<aui:validator name="number"></aui:validator>
				                    	<aui:validator name="minLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
				                    	<aui:validator name="maxLength" errorMessage="please-enter-a-valid-10-digit-mobile-number">10</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="mobile"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-12">
	                        	<p class="coti coti-hide"> <liferay-ui:message key="tax-details-of-other-countries-please-indicate-all-countries-in-which-you-are-resident-for-tax-purposes-and-associated-details"> </liferay-ui:message></p>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxCountry1" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="country-of-tax-residency"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxIdentificationNumber1" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="tax-identification-number-tin"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="identificationType1" label="" placeholder="&nbsp;" wrappedField="<%=true %>" required="true">
	                                	<aui:validator name="required" errorMessage="this-field-is-required">
				                    		function(val){
				                               var taxResidenceIndiaYN = $('input[name="<portlet:namespace/>taxResidenceIndiaYN"]:checked').val();		                      
				                               return taxResidenceIndiaYN === 'Y';
				                            }
				                    	</aui:validator>
	                                </aui:input>
	                                <span class="placeholder"><liferay-ui:message key="identification-type-tin-or-other-please-specify"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxCountry2" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="country-of-tax-residency"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxIdentificationNumber2" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="tax-identification-number-tin"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="identificationType2" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="identification-type-tin-or-other-please-specify"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxCountry3" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="country-of-tax-residency"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="taxIdentificationNumber3" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="tax-identification-number-tin"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-md-4 col-12 coti coti-hide">
	                            <label class="custom-field two">
	                                <aui:input name="identificationType3" label="" placeholder="&nbsp;" wrappedField="<%=true %>"></aui:input>
	                                <span class="placeholder"><liferay-ui:message key="identification-type-tin-or-other-please-specify"></liferay-ui:message></span>
	                            </label>
	                        </div>
	                        
	                        <div class="col-12">
		                        <div class="updtFATCA-Form-field pb-3">
			                        <p class="fs14"><liferay-ui:message key="to-also-include-usa-where-the-individual-is-a-citizen-green-card-holder-of-usa"></liferay-ui:message></p>
		                        </div>
		                        <div class="updtFATCA-Form-field">
			                        <p class="fs14"><liferay-ui:message key="in-case-tax-identification-number-is-not-available-kindly-provide-functional-equivalent"></liferay-ui:message></p>
		                        </div>
	                        </div>
                        </div>
                        <!-- Hidable Section Starts -->
                        
                        <div class="col-12">
						    <div class="updtFATCA-Form-field pt-3 pb-2">
						        <p class="h6"><liferay-ui:message key="certification-in-caps"></liferay-ui:message></p>
						    </div>
						    <div class="updtFATCA-Form-field pb-2">
						        <p class="h6"><liferay-ui:message key="i-under-penalty-of-perjury-i-certify-that"></liferay-ui:message></p>
						    </div>
						    <div class="updtFATCA-Form-field">
						        <ul class="green-bullet-list updtFATCA-Form-list">
						            <li class="updtFATCA-Form-listitm col-12 my-2"><liferay-ui:message key="fatca-certification-point-1.1"></liferay-ui:message></li>
						            <li class="updtFATCA-Form-listitm col-12 my-2"><liferay-ui:message key="fatca-certification-point-2.1"></liferay-ui:message></li>
						            <li class="updtFATCA-Form-listitm col-12 my-2"><liferay-ui:message key="fatca-certification-point-3.1"></liferay-ui:message></li>
						            <li class="updtFATCA-Form-listitm col-12 my-2"><liferay-ui:message key="fatca-certification-point-4.1"></liferay-ui:message></li>
						        </ul>
						    </div>
						</div>
                        
                    </div>

                    <div class="center-btn btn-submit">
                        <a class="edto-btn-primary" href="javascript:;" onclick="onBtnSubmit()">Submit</a>
                    </div>
                    
                    <div class="center-btn btn-update hide">
                         <aui:button type="submit" cssClass="edto-btn-primary" value="update" onClick='<%= "event.preventDefault(); " + liferayPortletResponse.getNamespace() + "updateFatca(event);" %>'></aui:button>
                    </div>
                    <aui:input name="addressLine2" type="hidden" label="" placeholder="&nbsp;" wrappedField="<%=true %>" value=""></aui:input>
                </aui:form>
            </div>
        </div>
    </div>
</div>

<div class="modal team-modal wealth-modal fade" id="lmsModal" tabindex="-1" role="dialog" aria-labelledby="lmsTitle" aria-hidden="true" style="display: none;">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true"><svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 15 15">
    <path fill="#999" fill-rule="nonzero" d="M8.827 7.5l5.898-5.899A.938.938 0 1 0 13.4.275L7.5 6.174 1.601.274A.938.938 0 1 0 .275 1.602l5.899 5.9-5.9 5.898a.938.938 0 1 0 1.327 1.326l5.9-5.899 5.898 5.9a.935.935 0 0 0 1.326 0 .938.938 0 0 0 0-1.327L8.827 7.5z"/>
</svg></span>
                    </button>
                </div>
                <div class="mb-2 d-flex justify-content-center align-items-center hide">
                <svg xmlns="http://www.w3.org/2000/svg" width="54" height="54" viewBox="0 0 54 54">
    <g fill="none" fill-rule="evenodd" transform="translate(1 1)">
        <circle cx="26" cy="26" r="26" fill="#FFF" stroke="#7ED321" stroke-width="1.6"/>
        <path fill="#7ED321" fill-opacity=".92" d="M20.303 35.103L13.4 28.2a1.98 1.98 0 0 1 2.8-2.8l4.103 4.103a2.4 2.4 0 0 0 3.394 0L35.8 17.4a1.98 1.98 0 0 1 2.8 2.8L23.697 35.103a2.4 2.4 0 0 1-3.394 0z"/>
    </g>
</svg>
                </div>
                <div class="wealth-form-box text-center w-100 error-blk hide"> 
                    <h4 class="text-center mb-3 error-msg">No Record Found</h4> 
                </div>
                <div class="wealth-form-box text-center w-100 success-blk hide">                    
                    <h3 class="mb-3"> Thank you!!</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<%
String createDateRr = (String) renderRequest.getAttribute("uniqueDateObj");
%>

<div id='<portlet:namespace/>update-fatca-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<aui:script>

$('input[name="<portlet:namespace/>taxResidenceIndiaYN"]').change(function(){
 $('.coti').toggleClass("coti-hide");
})

$('input[name="<portlet:namespace/>citizenshipIndianYN"]').change(function(){
 $('.oc').toggleClass("oc-hide");
})


$('input[name="<portlet:namespace/>birthCountryIndiaYN"]').change(function(){
 $('.cb').toggleClass("cb-hide");
})

$('input[name="<portlet:namespace/>originIndianYN"]').change(function(){
 $('.io').toggleClass("io-hide");
})

$('input[name="<portlet:namespace/>currentResidenceIndiaYN"]').change(function(){
 $('.cr').toggleClass("cr-hide");
})


var getMultipleLoginDataURL = "${getMultipleLoginDataURL}";
var policyNo = $('#<portlet:namespace />uniquePolicy').val();

function <portlet:namespace />updateFatca(event) {
 var createDateRr = '<%= createDateRr%>';
	console.log("createDateRr:::" + createDateRr);
	var form1 = new FormData();
	form1.append("<portlet:namespace/>" + "policyNumber", policyNo);
	Liferay.Util.fetch(getMultipleLoginDataURL, {
		body: form1,
		method: 'POST',
		async: true
	}).then(function(response) {
		return response.json();
	}).then(function(response) {
	console.log("Response from MultipleLogin::" + response.loginCreateDate);
		if(createDateRr && (createDateRr != response.loginCreateDate)){
			console.log("inside if:::");
			localStorage.setItem('multipleLoginsDetected', 'true');
			window.location.href='/login';
		}else {
			console.log("inside else::::::");
    let loader = document.getElementById('<portlet:namespace/>update-fatca-loader');
    loader.style.display = 'block';
    
	var form = document.<portlet:namespace />fm;
	var formData = new FormData(form);
	var data = {};
	
	for (const key of formData.keys()) {
        data[key.replace('<portlet:namespace />','')] = formData.get(key);
     }

	delete data.formDate; 
	delete data.policyNumberA;
	delete data.dobA;
	
	var newData = new FormData();
	newData.append('<portlet:namespace />json', JSON.stringify(data));

	
	Liferay.Util.fetch('<portlet:resourceURL id="/update_fatca"></portlet:resourceURL>', {
		body: newData,
		method: 'POST',
	})
	.then(function (response) {
	    loader.style.display = 'none';
		if(response.ok){
		     return response.json();
		}else{
		   showModal('Internal Server Error');
		}
	})
	.then(function (response) {
	    
		if(response?.status == 200){
		     showSuccessModal();
		}else if(response?.status == 400){
			if(response?.errors){
			   let msg = Array.isArray(response.errors) ? response.errors[0] : response.errors;
               msg = msg.includes('=') ? msg.split('=')[1] : msg;              
			   showFailureModal(msg);
			}else{
			    showFailureModal('No Record Found');
			}
		}else{
		     showFailureModal('Internal Server Error');
		}
		
	})
	}
	}).catch(function (e) {
	     loader.style.display = 'none';
	     showFailureModal('Internal Server Error');
		console.log("error...",e)
	});

}

function showModal(msg){
      let error = document.querySelector('.error-msg');
      error.innerHTML = msg;
      $('#updateFatcaModal').modal('show');   
}


function onBtnSubmit(){
    $(".orgn").toggleClass("hide");
    $(".temp").toggleClass("hide");
    $(".btn-submit").toggleClass("hide");
    $(".btn-update").toggleClass("hide");
}

function showSuccessModal(){
	$('.success-blk').removeClass('hide');
	$('.error-blk').addClass('hide');
	$('.align-items-center').removeClass('hide');
	
	$('#lmsModal').modal('show');
}

function showFailureModal(msg){
    let error = document.querySelector('.error-msg');
    error.innerHTML = msg;
   	$('.error-blk').removeClass('hide');
   	$('.success-blk').addClass('hide');
   	$('.align-items-center').addClass('hide');
   	$('#lmsModal').modal('show');
}
</aui:script>