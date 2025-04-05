<div class="col-md-6 col-12 hide--appointee appointee--block">
   <div class="form-box">
      <label class="custom-field two">
         <aui:input name="appointeeName" onKeyup="validateRadioButtonsOnChange('nomineeFM','appointeeName')"
            placeholder="&nbsp;" label="" cssClass="validate valpha" type="text"
            value="" maxLength="30" minLength="0" wrappedField="<%=true %>">
            <aui:validator name="required" errorMessage="label-please-provide-a-valid-name-of-appointee"> 
				function(val){						                                                   
					return isMinor();
				}
            </aui:validator>
         </aui:input>
         <span class="placeholder">
            <liferay-ui:message key="label-appointee-name" />
         </span>
      </label>
   </div>
</div>
<div class="col-md-6 col-12 hide--appointee appointee--block">
   <div class="form-box">
      <label class="custom-field two">
         <aui:input name="appointeeDateOfBirth" onInput="validateRadioButtonsOnChange('nomineeFM','appointeeDateOfBirth')"
            placeholder="&nbsp;" label="" cssClass="validate nominee-tab" type="text"
            value="" maxLength="10" minLength="0" wrappedField="<%=true %>">
				<aui:validator name="required" errorMessage="Please provide a valid appointee Date of Birth"> 
					function(val){
					 	return isMinor();
					}
				</aui:validator>
				<aui:validator name="custom" errorMessage="label-appointee-cannot-be-a-minor"> 
					function(val){			                                                   
					   return !isMinor('A');
					}
				</aui:validator>
				<aui:validator name="custom" errorMessage="Please provide a valid date">
					function (val, fieldNode, ruleValue) {
					    return isValidDateInRangeNominee(val);
					}
				</aui:validator>
			</aui:input>
			<span class="placeholder">
			   <liferay-ui:message key="label-appointee-dob" />
			</span>
		</label>
	</div>
</div>

<!-- Appointee Mobile Number -->
<div class="col-md-6 col-12 hide--appointee appointee--block">
	<div class="form-box">
		<label class="custom-field two">
			<aui:input
				name="appointeeMobileNumber" placeholder="&nbsp;" 
				label="" cssClass="validate vnumber"
				type="text" value="" maxLength="10"
				minLength="0" wrappedField="<%=true %>">
				<aui:validator name="required" errorMessage="mobile-number-error-message"> 
					function(val){						                                                   
						return isMinor();
					}
				</aui:validator>
			</aui:input> 
			<span class="placeholder">
				<liferay-ui:message	key="label-appointee-mobile-number" />
			</span>
		</label>
	</div>
</div>

<!-- Appointee Email Id -->
<div class="col-md-6 col-12 hide--appointee appointee--block">
	<div class="form-box">
		<label class="custom-field two">
			<aui:input
				name="appointeeEmailId" placeholder="&nbsp;" 
				label="" cssClass="validate" type="text"
				value="" maxLength="30"
				minLength="0" wrappedField="<%=true %>">
				<aui:validator name="email"></aui:validator>
				<aui:validator name="required" errorMessage="email-error-message"> 
					function(val){						                                                   
						return isMinor();
					}
				</aui:validator>	
			</aui:input>
			<span class="placeholder"><liferay-ui:message key="label-appointee-email-id" />*</span>
		</label>
	</div>
</div>

<div class="col-md-6 col-12 hide--appointee appointee--block">
   	<div class="form-box">
      	<div class="popup-select select-container">
         	<aui:select title=" " name="appointeeRelationship" label="" cssClass="validate select2-select"
      			data-placeholder="<%=LanguageUtil.get(request, "label-appointee-relationship")%>" required="true">
	            <aui:option>
	               <liferay-ui:message key="label-appointee-relationship" />
	            </aui:option>
	            <c:forEach var="relation" items="${masterMap['Relationship Of Appointee to Nominee']}">
	               <c:if test="${relation.name!='None'}">
	              	 <aui:option value="${relation.value}" data-name="${relation.name}"
	                  label="${relation.name}">
	              	 </aui:option>
	               </c:if>
	            </c:forEach>
				<aui:validator name="required" errorMessage="label-please-provide-a-valid-relationship-of-appointee-to-nominee"> 
					function(val){	
						if(val == '' || !val){
							return true
						}				                                                   
						return isMinor();
					}
	            </aui:validator>
			</aui:select>
			<div class="select-error-message" style="display: none;"><liferay-ui:message key="label-please-provide-a-valid-relationship-of-appointee-to-nominee" /></div>
		</div>
	</div>
</div>

<div class="col-md-12 col-12 hide--appointee appointee--block">
	<div class="form-box">
		<p class="mb-1">
		   <liferay-ui:message key="label-appointee-gender" />
		</p>
		<div class="radio_container">
			<c:forEach var="genderData" items="${masterMap['Gender']}">
			<aui:input name='appointeeGender' id="appointeeGender${genderData.name}"
				cssClass="validate validateAGender" type="radio" label="" data-name="${genderData.name}"
				value="${genderData.value}">
				<aui:validator name="required" errorMessage="label-please-select-a-appointee-gender"> 
				function(val){						                                                   
				return isMinor();
				}
				</aui:validator>
			</aui:input>
			<label for='<portlet:namespace/>appointeeGender${genderData.name}'>
			${genderData.name} </label>
			</c:forEach>
		</div>
	</div>
</div>