<div id='address_of_nominee'
	class="communication-details border-bottom-0 mb-0 pb-0">
	<h5 class="my-4"><liferay-ui:message key="label-permanent-address-of-nominee" /></h5>
	<div class="medical-test">
		<div class="radio-wrapper">
			<!-- Permanent Address -->
			<div id="nominee_permanent_address">
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_pa_address_line_one" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !isMinor();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_pa_address_line_two" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !isMinor();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_pa_address_line_three" placeholder="&nbsp;" label=""
										cssClass="validate valphanumwithspace" type="text" 
										value="${laCommunicationDetails.paAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="popup-select nominee select-container">
                                    <aui:select title=" " name="nominee_pa_state" label="" cssClass="validate select2-select"
                                       data-placeholder="<%=LanguageUtil.get(request, "label-select-state")%>">
                                       <aui:option>
                                          <liferay-ui:message key="select-state" />
                                       </aui:option>
                                       <c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}" label="${stateData.name}">
											</aui:option>
										</c:forEach>
                                       	<aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !isMinor();
				                            }
                                      	</aui:validator>
                                    </aui:select>
                                    <div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
                                 </div>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_pa_city" placeholder="&nbsp;" label=""
										cssClass="validate valpha" type="text" 
										value="${laCommunicationDetails.paCity}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="city-error-meesage"> 
	                                            function(val){						                                                   
				                               return !isMinor();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
					    <div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_pa_pincode" placeholder="&nbsp;" label=""
										cssClass="validate vnumber" type="text" 
										value="${laCommunicationDetails.paPincode}" maxLength="6"
										minLength="6" wrappedField="<%=true%>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message"> 
	                                            function(val){						                                                   
				                               return !isMinor();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>

				<p style="white-space:wrap;">
					<liferay-ui:message
						key="label-is-the-permanent-address-and-communication-address-same" />
				</p>
				<div class="radio_container">
					<aui:input name='nominee_isAddressSame' id="nominee_yes" cssClass="validate" checked="true"
						type="radio" label="" required="required" onclick="showPDDiv(false, 'nominee_current_address');"
						value="Y" >
					<aui:validator name="required" errorMessage="please-select-one-error-message"> 
						function(val){						                                                   
							return !isMinor();
						}
                  	</aui:validator>
					</aui:input>
					<label for="<portlet:namespace/>nominee_yes"><liferay-ui:message
							key="label-yes" /></label>

					<aui:input name='nominee_isAddressSame' id="nominee_no" cssClass="validate"
						type="radio" label="" required="required" onclick="showPDDiv(true, 'nominee_current_address');"
						value="N">
					</aui:input>
					<label for="<portlet:namespace/>nominee_no"><liferay-ui:message
							key="label-no" /></label>

				</div>
			</div>

			<!-- Current Address -->
			<div id="nominee_current_address">
				<h5 class="my-4"><liferay-ui:message key="label-current-address-of-nominee" /></h5>
				<div class="location-field">
					<div class="row">
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_ca_address_line_one" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine1}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !nomineeIsAddressSame();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-one" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two"> 
									<aui:input name="nominee_ca_address_line_two" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine2}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !nomineeIsAddressSame();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-two" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_ca_address_line_three" placeholder="&nbsp;" label=""
										cssClass="valphanumwithspace" type="text" 
										value="${laCommunicationDetails.caAddressLine3}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-address-line-three" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<div class="popup-select nominee select-container">
                                    <aui:select title=" " name="nominee_ca_state" label="" cssClass="validate select2-select"
                                       data-placeholder="<%=LanguageUtil.get(request, "label-select-state")%>">
                                       <aui:option>
                                          <liferay-ui:message key="select-state" />
                                       </aui:option>
                                       <c:forEach var="stateData" items="${masterMap['State']}">
											<aui:option data-name="${stateData.name}" value="${stateData.value}" label="${stateData.name}">
											</aui:option>
										</c:forEach>
                                       <aui:validator name="required" errorMessage="valid-address-meesage"> 
	                                            function(val){						                                                   
				                               return !nomineeIsAddressSame();
				                            }
                                      	</aui:validator>
                                    </aui:select>
                                    <div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
                                 </div>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input name="nominee_ca_city" placeholder="&nbsp;" label=""
										cssClass="valpha" type="text" 
										value="${laCommunicationDetails.caCity}" maxLength="50"
										minLength="0" wrappedField="<%=true%>">
										<aui:validator name="required" errorMessage="city-error-meesage"> 
	                                            function(val){						                                                   
				                               return !nomineeIsAddressSame();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-city" /></span>
								</label>
							</div>
						</div>
						<div class="col-md-4 col-12">
							<div class="form-box">
								<label class="custom-field two">
									<aui:input	name="nominee_ca_pincode" placeholder="&nbsp;" label=""
										cssClass="vnumber" type="text" 
										value="${laCommunicationDetails.caPincode}" maxLength="6"
										minLength="6" wrappedField="<%=true%>">
										<aui:validator name="minLength" errorMessage="label-minimum-error">6</aui:validator>
										<aui:validator name="required" errorMessage="pin-code-error-message"> 
	                                            function(val){						                                                   
				                               return !nomineeIsAddressSame();
				                            }
                                      	</aui:validator>
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="label-pincode" /></span>
								</label>
							</div>
						</div>
					</div>
				</div>

				<p style="white-space:wrap">
					<liferay-ui:message
						key="label-which-of-the-above-address-is-your-correspondence-address" />
				</p>
				<div class="radio_container">
					<aui:input name='nominee_correspondingAddress' id="nominee_permanent"
						cssClass="" type="radio" label="" required="required" checked="true"
						value="P">
						<aui:validator name="required" errorMessage="this-field-is-required"> 
                            function(val){						                                                   
                      			return !nomineeIsAddressSame();
                   			}
                       	</aui:validator>
					</aui:input>
					<label for="<portlet:namespace/>nominee_permanent"><liferay-ui:message
							key="label-permanent" /></label>

					<aui:input name='nominee_correspondingAddress' id="nominee_current"
						cssClass="" type="radio" label="" required="required"
						value="C">
					</aui:input>
					<label for="<portlet:namespace/>nominee_current"><liferay-ui:message
							key="label-current" /></label>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
$(document).ready(function () {
	
	showPDDiv(!nomineeIsAddressSame(), 'nominee_current_address');
	
	$('input[name="'+ namespace + 'nominee_isAddressSame"]').change(function() {
		//console.log("nomineeIsAddressSame "+nomineeIsAddressSame())
	});
});

function nomineeIsAddressSame() {
	const isAddressSame = $('input[name="'+ namespace + 'nominee_isAddressSame"]:checked').val();
	
	if (isAddressSame == 'N') {
		return false;
	}
	
	if(isAddressSame == 'Y'){
		return true;
	}
	return false;
}
</script>

<style>
.popup-select.nominee.select-container .select2-container--default .select2-selection--single .select2-selection__rendered{
	font-size: 15px;
}
</style>