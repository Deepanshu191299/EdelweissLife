<%@ include file="../../init.jsp"%>
<div class="form-tabs-wrapper mt-0">
	<div class="tab-content" id="heightWeightSection-la">
		<div class="tab-pane active" id="physical-la">
			<h5><liferay-ui:message key="height-and-weight-details-of-life-assured" /></h5>
			<p class="pt-2">Height</p>
			<div class="location-field physical-details align-items-end">
				<div class="number-wrapaper">
					<div class="number">
						<span class="minus">-</span>
						<aui:input
							name="la_heightFeet" placeholder="&nbsp;" label=""
							id="la_heightFeet"
							onkeyup="validateRadioButtonsOnChange('health_details_form','la_heightFeet')"
							cssClass="numberField vnumber  range-month-height validate" type="text"
							value="${laLifeStyleDetails.heightFeet==null? 0:laLifeStyleDetails.heightFeet }" 
							maxLength="2" minLength="1" wrappedField="<%=true %>">
							<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
						</aui:input>
						<span class="plus plus-month">+</span>
					</div>
					<p class="pt-3">(Feet)*</p>
				</div>
				<div class="number-wrapaper">
					<div class="number">
						<span class="minus">-</span>
						<aui:input
							name="la_heightInch" placeholder="&nbsp;" label=""
							onkeyup="validateRadioButtonsOnChange('health_details_form','la_heightInch')"
							id="la_heightInch"
							cssClass="numberField vnumber  range-month-height validate" type="text"
							value="${laLifeStyleDetails.heightInches==null? 0:laLifeStyleDetails.heightInches }" 
							maxLength="2" minLength="1" wrappedField="<%=true %>">
							<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
						</aui:input>
						<span class="plus plus-month">+</span>
					</div>
					<p class="pt-3">(Inches)*</p>
				</div>
				<div class="number-weight">
					<div class="form-box w-100">
						<label class="custom-field two">
			            	<aui:input type="text" cssClass="validate vnumber" name="la_weight" wrappedField="<%=true %>" maxLength="3" placeholder="&nbsp;" label="" value="${laLifeStyleDetails.weightKg}"
							onkeyup="validateRadioButtonsOnChange('health_details_form','la_weight')">
			            		<aui:validator name="required" errorMessage="weight-error-message"></aui:validator>
			            	</aui:input>
				            <span class="placeholder">Weight (in kgs)*</span>
		             	</label>
					</div>
					<p>(Kgs)</p>
				</div>
			</div>
		</div>
		<div class="communication-details border-0 mb-0 pb-0">
			<div class="medical-test">
				<div class="radio-wrapper">
					<p class="p-wrap-class">
						<liferay-ui:message key="has-there-variation-in-weight" />
					</p>
					<div class="radio_container">
						<aui:input required="true" cssClass="validate setOpen" label="" name="la_weightvariation"
							type="radio" value="Y" id="la_variation_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_weightvariation')"
							checked="${laLifeStyleDetails.weightVariationYn=='Y'?true:false }" onclick="showDiv(true, 'la_reason');">
							<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
						</aui:input>
						<label for="<portlet:namespace/>la_variation_yes"><liferay-ui:message
								key="label-yes" /></label>
		
						<aui:input cssClass="validate" label="" name="la_weightvariation"
							type="radio" value="N" id="la_variation_no" onChange="validateRadioButtonsOnChange('health_details_form','la_weightvariation')"
							checked="${laLifeStyleDetails.weightVariationYn=='N'?true:false }" onclick="showDiv(false, 'la_reason');"></aui:input>
						<label for="<portlet:namespace/>la_variation_no"><liferay-ui:message
								key="label-no" /></label>
					</div>
		
					<div class="location-field">
						<div class="weightvariationExtrafields" id="la_reason" style="display: none;">
							<div class="row">
								<div class="col-md-4 col-12">
									<div class="form-box">
										<div class="select-container">
											<aui:select title=" " id="la_gain_in_kg" name="la_gain_in_kg" value="${laLifeStyleDetails.variationWeight}" label=""
												cssClass="validate select2-select" required="true" onchange="updateReasonLabel('la_gain_in_kg','setGain');"
												data-placeholder="Gain/Loss (in kgs) from  6 moths ago*">
												<aui:option></aui:option>
												<aui:option value="-10">-10 kgs</aui:option>
												<aui:option value="-9">-9 kgs</aui:option>
												<aui:option value="-8">-8 kgs</aui:option>
												<aui:option value="-7">-7 kgs</aui:option>
												<aui:option value="-6">-6 kgs</aui:option>
												<aui:option value="-5">-5 kgs</aui:option>
												<aui:option value="-4">-4 kgs</aui:option>
												<aui:option value="-3">-3 kgs</aui:option>
												<aui:option value="-2">-2 kgs</aui:option>
												<aui:option value="-1">-1 kgs</aui:option>
												<aui:option value="1">1 kgs</aui:option>
												<aui:option value="2">2 kgs</aui:option>
												<aui:option value="3">3 kgs</aui:option>
												<aui:option value="4">4 kgs</aui:option>
												<aui:option value="5">5 kgs</aui:option>
												<aui:option value="6">6 kgs</aui:option>
												<aui:option value="7">7 kgs</aui:option>
												<aui:option value="8">8 kgs</aui:option>
												<aui:option value="9">9 kgs</aui:option>
												<aui:option value="10">10 kgs</aui:option>
												<aui:validator name="required" errorMessage="state-error-meesage">
													function(val){
														var radioData = $('input[name=<portlet:namespace/>la_weightvariation]:checked').val();
														if(radioData == "Y"){
															return true;
														}else{
															return false;
														}
													}
												</aui:validator>
											</aui:select>
											<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
										</div>
									</div>
								</div>
								<div class="col-md-4 col-12">
									<div class="form-box">
										<div class="select-container setGain">
											<aui:select id="la_gain_loss_reason" name="la_gain_loss_reason" value="${laLifeStyleDetails.weightVariationReasonDataId}" label="" required="true"
												cssClass="validate select2-select" data-placeholder="Reason for Gain*">
												<aui:option></aui:option>
												<aui:option value="120">DIET</aui:option>
												<aui:option value="121">EXERCISE</aui:option>
												<aui:option value="122">ILLNESS</aui:option>
												<aui:option value="123">PREGNANCY</aui:option>
												<aui:validator name="required" errorMessage="state-error-meesage">
													function(val){
														var radioData = $('input[name=<portlet:namespace/>la_weightvariation]:checked').val();
														if(radioData == "Y"){
															return true;
														}else{
															return false;
														}
													}
												</aui:validator>
											</aui:select>
											<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
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
	
	<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
		<div class="tab-content" id="heightWeightSection-ps">
			<div class="tab-pane active" id="physical-ps">
			<c:if test="${commonDetails.isLaPrSameYn == 'N'}">
				<h5><liferay-ui:message key="height-and-weight-details-of-proposer" /></h5>
			</c:if>
			<c:if test="${commonDetails.spouseExistYn == 'Y'}">
			<h5><liferay-ui:message key="height-and-weight-details-of-spouse" /></h5>
			</c:if>
				<p class="pt-2">Height</p>
				<div class="location-field physical-details align-items-end">
					<div class="number-wrapaper">
						<div class="number">
							<span class="minus">-</span>
							<aui:input
								name="pr_heightFeet" placeholder="&nbsp;" label=""
								id="pr_heightFeet" 
								onkeyup="validateRadioButtonsOnChange('health_details_form','pr_heightFeet')"
								cssClass="validate numberField vnumber  range-month-height" type="text"
								value="${commonDetails.spouseExistYn == 'Y' ? ( spouseLifeStyleDetails.heightFeet==null? 0:spouseLifeStyleDetails.heightFeet ) :
								 (ProposerLifeStyleDetails.heightFeet==null? 0:ProposerLifeStyleDetails.heightFeet )}" 
								maxLength="2" minLength="1" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
							</aui:input>
							<span class="plus plus-month">+</span>
						</div>
						<p class="pt-3">(Feet)*</p>
					</div>
					<div class="number-wrapaper">
						<div class="number">
							<span class="minus">-</span>
							<aui:input
								name="pr_heightInch" placeholder="&nbsp;" label=""
								onkeyup="validateRadioButtonsOnChange('health_details_form','pr_heightInch')"
								id="pr_heightInch"
								cssClass="validate numberField vnumber  range-month-height" type="text"
								value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.heightInches==null? 0:spouseLifeStyleDetails.heightInches) :
								( ProposerLifeStyleDetails.heightInches ==null? 0:ProposerLifeStyleDetails.heightInches)}" 
								maxLength="2" minLength="1" wrappedField="<%=true %>">
								<aui:validator name="required" errorMessage="please-select-one-error-message"></aui:validator>
							</aui:input>
							<span class="plus plus-month">+</span>
						</div>
						<p class="pt-3">(Inches)*</p>
					</div>
					<div class="number-weight">
						<div class="form-box w-100">
							<label class="custom-field two">
				             	 <aui:input type="text" cssClass="validate vnumber" name="pr_weight" maxLength="3" wrappedField="<%=true %>" placeholder="&nbsp;" label=""
								  onkeyup="validateRadioButtonsOnChange('health_details_form','pr_weight')" 
				             	 value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.weightKg : ProposerLifeStyleDetails.weightKg}">
				             	 	<aui:validator name="required" errorMessage="weight-error-message"></aui:validator>
				             	 </aui:input>
					             <span class="placeholder">Weight (in kgs)*</span>
			             	</label>
						</div>
						<p>(Kgs)</p>
					</div>
				</div>
			</div>
			<div class="communication-details border-0 mb-0 pb-0">
				<div class="medical-test">
					<div class="radio-wrapper">
						<p class="p-wrap-class">
							<liferay-ui:message key="has-there-variation-in-weight" />
						</p>
						<div class="radio_container">
							<aui:input required="true" cssClass="validate setOpen" label="" name="pr_weightvariation" onChange="validateRadioButtonsOnChange('health_details_form','pr_weightvariation')"
								type="radio" value="Y" id="proposer_variation_yes" onclick="showDiv(true, 'pr_reason');" checked="${insurerDetails.weightVariationYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required"></aui:validator>
							</aui:input>
							<label for="<portlet:namespace/>proposer_variation_yes"><liferay-ui:message
									key="label-yes" /></label>
		
							<aui:input cssClass="validate" label="" name="pr_weightvariation" onChange="validateRadioButtonsOnChange('health_details_form','pr_weightvariation')"
								type="radio" value="N" id="proposer_variation_no" onclick="showDiv(false, 'pr_reason');" checked="${insurerDetails.weightVariationYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>proposer_variation_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
		
						<div class="location-field">
							<div class="weightvariationExtrafields" id="pr_reason" style="display: none">
								<div class="row">
									<div class="col-md-4 col-12">
										<div class="form-box">
											<div class="select-container">
												<div class="form-box">
													<aui:select title=" " id="pr_gain_in_kg" name="pr_gain_in_kg" 
													    value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.variationWeight : ProposerLifeStyleDetails.variationWeight}" label=""
														cssClass="validate select2-select" required="true" onchange="updateReasonLabel('pr_gain_in_kg','setLoss');"
														data-placeholder="Gain/Loss (in kgs) from  6 moths ago*">
														<aui:option></aui:option>
														<aui:option value="-10">-10 kgs</aui:option>
														<aui:option value="-9">-9 kgs</aui:option>
														<aui:option value="-8">-8 kgs</aui:option>
														<aui:option value="-7">-7 kgs</aui:option>
														<aui:option value="-6">-6 kgs</aui:option>
														<aui:option value="-5">-5 kgs</aui:option>
														<aui:option value="-4">-4 kgs</aui:option>
														<aui:option value="-3">-3 kgs</aui:option>
														<aui:option value="-2">-2 kgs</aui:option>
														<aui:option value="-1">-1 kgs</aui:option>
														<aui:option value="1">1 kgs</aui:option>
														<aui:option value="2">2 kgs</aui:option>
														<aui:option value="3">3 kgs</aui:option>
														<aui:option value="4">4 kgs</aui:option>
														<aui:option value="5">5 kgs</aui:option>
														<aui:option value="6">6 kgs</aui:option>
														<aui:option value="7">7 kgs</aui:option>
														<aui:option value="8">8 kgs</aui:option>
														<aui:option value="9">9 kgs</aui:option>
														<aui:option value="10">10 kgs</aui:option>
														<aui:validator name="required" errorMessage="state-error-meesage">
															function(val){
																var radioData = $('input[name=<portlet:namespace/>la_weightvariation]:checked').val();
																if(radioData == "Y"){
																	return true;
																}else{
																	return false;
																}
															}
														</aui:validator>
													</aui:select>
													<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4 col-12">
										<div class="form-box">
											<div class="select-container setLoss">
												<aui:select id="pr_gain_loss_reason" name="pr_gain_loss_reason" label="" value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.weightVariationReasonDataId : ProposerLifeStyleDetails.weightVariationReasonDataId}" required="true"
													cssClass="validate select2-select" data-placeholder="Reason for Gain*">
													<aui:option></aui:option>
													<aui:option value="120">DIET</aui:option>
													<aui:option value="121">EXERCISE</aui:option>
													<aui:option value="122">ILLNESS</aui:option>
													<aui:option value="123">PREGNANCY</aui:option>
													<aui:validator name="required" errorMessage="state-error-meesage">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_weightvariation]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
												</aui:select>
												<div class="select-error-message" style="display: none;"><liferay-ui:message key="state-error-meesage" /></div>
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
	</c:if>
	
</div>

 <script>
 function updateReasonLabel(divId,className) {
		  var gainLossSelect = $('#' + portletNamespace + divId);
		  var selectedValue = gainLossSelect.val();
		  
		  if (selectedValue >= 0) {
		    $('.' + className + ' .select2-selection__placeholder').html("Reason for Gain*");
		  } else if (selectedValue < 0) {
		    $('.' + className + ' .select2-selection__placeholder').html("Reason for Loss*");
		  }
		}
 
$(document).ready(function() {	
 updateReasonLabel('la_gain_in_kg','setGain');
 updateReasonLabel('pr_gain_in_kg','setLoss'); 
});
</script>  
