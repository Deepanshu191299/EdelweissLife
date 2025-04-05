<%@ include file="../../init.jsp"%>
<div class="communication-details" id="lifestyle-personal-sec">
      		<div class="medical-test">
      			<div class="radio-wrapper">
      				<div class="location-field">
      					<div class="row">
						 <div class="col-md-6 col-12">
						 	<div class="edto-income-plan-tiles-wrapper">
								<h4>
						 			<liferay-ui:message
										key="lifestyle-personal-details" />
								</h4>
							</div>
						 </div>
						 
						 <div class="col-md-3 col-6">
							 <div class="input-box">
						 		<span>Life to be Insured</span>
						 	</div>
						 </div>
						<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						 <div class="col-md-3 col-6">
						 	<div class="input-box">
						 		<span>Proposer / Spouse</span>
						 	</div>
						 </div>
						 </c:if>
						 <div class="col-md-6 col-12">
						 	<div class="input-box">
						 		<p><liferay-ui:message key="label-q-consume-alcohol-yn" /></p>
						 	</div>
						 </div>
						 
						 <div class="col-md-3 col-6">
					       	<div class="radio_container">
					       		<aui:input label="" name="la_q_consume_alcohol_yn" type="radio"  
					       		disabled="${isLaMinor}"
					       		 value="Y" cssClass="setOpen validate" id="la_alcohol_yes" onclick="showDiv(true, 'la_alcoholFields');" checked="${laLifeStyleDetails.qConsumeAlcoholYn=='Y'?true:false }" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_yn')">
					       		<aui:validator name="required" errorMessage="this-field-is-required" />
					       		</aui:input>
					       		<label for="<portlet:namespace/>la_alcohol_yes"><liferay-ui:message key="label-yes" /></label>
					       		
					    		<aui:input label="" name="la_q_consume_alcohol_yn" type="radio" value="N" cssClass="validate" id="la_alcohol_no" onclick="showDiv(false, 'la_alcoholFields');" checked="${isLaMinor || laLifeStyleDetails.qConsumeAlcoholYn=='N'?true:false }" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_yn')">
					    		<aui:validator name="required" errorMessage="this-field-is-required" />
					    		</aui:input>
					    		<label for="<portlet:namespace/>la_alcohol_no"><liferay-ui:message key="label-no" /></label>
					       </div>
						 </div>
						 <c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						 <div class="col-md-3 col-6">
						 	<div class="radio_container alcohol">
						 		<aui:input name="pr_q_consume_alcohol_yn" type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_alcohol_yes" onclick="showDiv(true, 'pr_alcoholFields');" checked="${insurerDetails.qConsumeAlcoholYn=='Y'?true:false }" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_yn')">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_alcohol_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="pr_q_consume_alcohol_yn" type="radio" value="N" cssClass="validate" label="" id="ps_alcohol_no" onclick="showDiv(false, 'pr_alcoholFields');" checked="${insurerDetails.qConsumeAlcoholYn=='N'?true:false }" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_yn')">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_alcohol_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						 </c:if>
						 <div class="col-md-12 mb-4" id="la_alcoholFields" style="display:none;">
						 	<div class="input-wrapper">
						 		<label><span>Life Insured</span></label>
						 	</div>
						 	
							<div class="form-wrapper">
								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-beerpint"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_beerpint" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_beerpint')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:laLifeStyleDetails.qConsumeAlcoholBeerQty}" 
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-beerpintyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_beerpintyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_beerpintyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholBeerSince==null? 0:laLifeStyleDetails.qConsumeAlcoholBeerSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-hard-liquor-qty"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_q_consume_alcohol_hard_liquor_qty" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_hard_liquor_qty')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:laLifeStyleDetails.qConsumeAlcoholHardLiquorQty}" 
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-hard-liquor-since-years"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_q_consume_alcohol_hard_liquor_since" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_hard_liquor_since')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholHardLiquorSince==null? 0:laLifeStyleDetails.qConsumeAlcoholHardLiquorSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-wine"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_wine" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_wine')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:laLifeStyleDetails.qConsumeAlcoholWineQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-wineyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_wineyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_wineyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeAlcoholWineSince==null? 0:laLifeStyleDetails.qConsumeAlcoholWineSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>
							</div>
						 </div>
						 
						 <div class="col-md-12 mb-4" id="pr_alcoholFields" style="display:none;">
						 	<div class="input-wrapper">
						 		<label><span>Proposer / Spouse</span></label>
						 	</div>

							 <div class="form-wrapper">
								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-beerpint"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_beerpint" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_beerpint')"
												cssClass="numberField vnumber validate range-medical-section" type="text" 
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholBeerQty) :
												(ProposerLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholBeerQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-beerpintyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_beerpintyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_beerpintyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholBeerSince==null? 0:spouseLifeStyleDetails.qConsumeAlcoholBeerSince) : 
												(ProposerLifeStyleDetails.qConsumeAlcoholBeerSince==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholBeerSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-hard-liquor-qty"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_q_consume_alcohol_hard_liquor_qty" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_hard_liquor_qty')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholHardLiquorQty) : 
												(ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-hard-liquor-since-years"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_q_consume_alcohol_hard_liquor_since" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_hard_liquor_since')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholHardLiquorSince==null? 0:spouseLifeStyleDetails.qConsumeAlcoholHardLiquorSince) : 
												(ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorSince==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-wine"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_wine" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_wine')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholWineQty) : 
												(ProposerLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholWineQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-wineyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_wineyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_wineyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholWineSince==null? 0:spouseLifeStyleDetails.qConsumeAlcoholWineSince) : 
												(ProposerLifeStyleDetails.qConsumeAlcoholWineSince==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholWineSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_alcohol_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

							 </div>
						 </div>
						 
						  <div class="col-md-6 col-12">
						 	<div class="input-box">
						 		<p><liferay-ui:message key="label-q-consume-narcotics-yn" /></p>
						 	</div>
						 </div>
						 
						 <div class="col-md-3 col-6">
						 	<div class="radio_container">
						 		<aui:input name="la_q_consume_narcotics_yn" type="radio" disabled="${isLaMinor}" value="Y" cssClass="setOpen validate" label="" id="la_narcotics_yes" onclick="showDiv(true, 'la_narcoticsFields');" checked="${laLifeStyleDetails.qConsumeNarcoticsYn=='Y'?true:false }" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_narcotics_yn')">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_narcotics_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="la_q_consume_narcotics_yn" type="radio" value="N" cssClass="validate" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_narcotics_yn')" label="" id="la_narcotics_no" onclick="showDiv(false, 'la_narcoticsFields');" checked="${isLaMinor || laLifeStyleDetails.qConsumeNarcoticsYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_narcotics_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						 <c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						 <div class="col-md-3 col-6">
						 	<div class="radio_container narcotics">
						 		<aui:input name="pr_q_consume_narcotics_yn" type="radio" value="Y" cssClass="setOpen validate" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_narcotics_yn')" label="" id="ps_narcotics_yes" onclick="showDiv(true, 'pr_narcoticsFields');" checked="${insurerDetails.qConsumeNarcoticsYn=='Y'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_narcotics_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="pr_q_consume_narcotics_yn" type="radio" value="N" cssClass="validate" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_narcotics_yn')" label="" id="ps_narcotics_no" onclick="showDiv(false, 'pr_narcoticsFields');" checked="${insurerDetails.qConsumeNarcoticsYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_narcotics_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						 </c:if>
						 <div class="col-12 mb-4" id="la_narcoticsFields" style="display:none;">
						 	<div class="input-wrapper">
						 		<label><span>Life Insured</span></label>
						 	</div>
							<div class="form-wrapper">

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-narcotics"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_narcotics" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_narcotics')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeNarcoticsQty==null? 0:laLifeStyleDetails.qConsumeNarcoticsQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_narcotics_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-narcoticsyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_narcoticsyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_narcoticsyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qConsumeNarcoticsSince==null? 0:laLifeStyleDetails.qConsumeNarcoticsSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_consume_narcotics_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

							</div>
						 	
						</div>
						
						<div class="col-12 mb-4" id="pr_narcoticsFields" style="display:none;">
						 	<div class="input-wrapper">
						 		<label><span>Proposer / Spouse</span></label>
						 	</div>
						 	
							<div class="form-wrapper">

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-narcotics"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_narcotics" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_narcotics')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeNarcoticsQty==null? 0:spouseLifeStyleDetails.qConsumeNarcoticsQty) : 
												(ProposerLifeStyleDetails.qConsumeNarcoticsQty==null? 0:ProposerLifeStyleDetails.qConsumeNarcoticsQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_narcotics_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-narcoticsyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_narcoticsyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_narcoticsyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeNarcoticsSince==null? 0:spouseLifeStyleDetails.qConsumeNarcoticsSince) : 
												(ProposerLifeStyleDetails.qConsumeNarcoticsSince==null? 0:ProposerLifeStyleDetails.qConsumeNarcoticsSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_consume_narcotics_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						
						<div class="col-md-6 col-12">
						 	<div class="input-box">
						 		<p><liferay-ui:message key="label-q-smoke-consume-yn" /></p>
						 	</div>
						 </div>
						 
						 <div class="col-md-3 col-6">
						 	<div class="radio_container">
						 		<aui:input name="la_q_smoke_consume_yn" type="radio" disabled="${isLaMinor}" value="Y"  cssClass="setOpen validate" onChange="validateRadioButtonsOnChange('health_details_form','la_q_smoke_consume_yn')" label="" id="la_smoke_yes" onclick="showDiv(true, 'la_smokeFieldsss');" checked="${laLifeStyleDetails.qSmokeConsumeYn=='Y'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_smoke_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="la_q_smoke_consume_yn" type="radio" value="N" cssClass="validate" onChange="validateRadioButtonsOnChange('health_details_form','la_q_smoke_consume_yn')" label="" id="la_smoke_no" onclick="showDiv(false, 'la_smokeFieldsss');" checked="${isLaMinor || laLifeStyleDetails.qSmokeConsumeYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_smoke_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						 <c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						 <div class="col-md-3 col-6">
						 	<div class="radio_container smoke">
						 		<aui:input name="pr_q_smoke_consume_yn" type="radio" value="Y" cssClass="setOpen validate" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_smoke_consume_yn')" label="" id="ps_smoke_yes" onclick="showDiv(true, 'pr_smokeFields');" checked="${insurerDetails.qSmokeConsumeYn=='Y'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_smoke_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="pr_q_smoke_consume_yn" type="radio" value="N" cssClass="validate" label="" id="ps_smoke_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_smoke_consume_yn')" onclick="showDiv(false, 'pr_smokeFields');" checked="${insurerDetails.qSmokeConsumeYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_smoke_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						</c:if>
						<div class="col-12 mb-4" id="la_smokeFieldsss" style="display:none;">
							<div class="input-wrapper">
						 		<label><span>Life Insured</span></label>
						 	</div>

							<div class="form-wrapper">
								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-cigarettes"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_cigarettes" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_cigarettes')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:laLifeStyleDetails.qSmokeConsumeCigarettesQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>
								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-cigarettesyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_cigarettesyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_cigarettesyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeCigarettesSince==null? 0:laLifeStyleDetails.qSmokeConsumeCigarettesSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-bidi" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_bidi" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_bidi')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:laLifeStyleDetails.qSmokeConsume_BidiQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-bidiyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_bidiyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_bidiyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeBidiSince==null? 0:laLifeStyleDetails.qSmokeConsumeBidiSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-gutka"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_gutka" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_gutka')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:laLifeStyleDetails.qSmokeConsumeGutkaQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-gutkayears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_gutkayears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_gutkayears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeGutkaSince==null? 0:laLifeStyleDetails.qSmokeConsumeGutkaSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-paan"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_paan" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_paan')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumePaanQty==null? 0:laLifeStyleDetails.qSmokeConsumePaanQty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-paanyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_paanyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_paanyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.q_smoke_consume_paan_since==null? 0:laLifeStyleDetails.q_smoke_consume_paan_since}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-tobacoo" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_tobacoo" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_tobacoo')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:laLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-tobacooyears" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="la_tobacooyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','la_tobacooyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${laLifeStyleDetails.qSmokeConsumeTobaccoPouchSince==null? 0:laLifeStyleDetails.qSmokeConsumeTobaccoPouchSince}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

							</div>
						 	
						</div>
						
						<div class="col-12 mb-4" id="pr_smokeFields" style="display:none;">
							<div class="input-wrapper">
						 		<label><span>Proposer / Spouse</span></label>
						 	</div>

							<div class="form-wrapper">

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-cigarettes"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_cigarettes" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_cigarettes')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:spouseLifeStyleDetails.qSmokeConsumeCigarettesQty) : 
												(ProposerLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumeCigarettesQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-cigarettesyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_cigarettesyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_cigarettesyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeCigarettesSince==null? 0:spouseLifeStyleDetails.qSmokeConsumeCigarettesSince) : 
												(ProposerLifeStyleDetails.qSmokeConsumeCigarettesSince==null? 0:ProposerLifeStyleDetails.qSmokeConsumeCigarettesSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>


								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-bidi" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_bidi" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_bidi')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:spouseLifeStyleDetails.qSmokeConsume_BidiQty) : 
												(ProposerLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:ProposerLifeStyleDetails.qSmokeConsume_BidiQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-bidiyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_bidiyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_bidiyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeBidiSince==null? 0:spouseLifeStyleDetails.qSmokeConsumeBidiSince) : 
												(ProposerLifeStyleDetails.qSmokeConsumeBidiSince==null? 0:ProposerLifeStyleDetails.qSmokeConsumeBidiSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-gutka"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_gutka" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_gutka')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:spouseLifeStyleDetails.qSmokeConsumeGutkaQty) : 
												(ProposerLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumeGutkaQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-gutkayears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_gutkayears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_gutkayears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeGutkaSince==null? 0:spouseLifeStyleDetails.qSmokeConsumeGutkaSince) : 
												(ProposerLifeStyleDetails.qSmokeConsumeGutkaSince==null? 0:ProposerLifeStyleDetails.qSmokeConsumeGutkaSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-paan"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_paan" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_paan')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumePaanQty==null? 0:spouseLifeStyleDetails.qSmokeConsumePaanQty) : 
												(ProposerLifeStyleDetails.qSmokeConsumePaanQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumePaanQty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-paanyears"/></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_paanyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_paanyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.q_smoke_consume_paan_since==null? 0:spouseLifeStyleDetails.q_smoke_consume_paan_since) : 
												(ProposerLifeStyleDetails.q_smoke_consume_paan_since==null? 0:ProposerLifeStyleDetails.q_smoke_consume_paan_since)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-tobacoo" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_tobacoo" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_tobacoo')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:spouseLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty) : 
												(ProposerLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:ProposerLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

								<div class="pack-wrapper">
									<p><liferay-ui:message key="label-tobacooyears" /></p>
									<div class="number-wrapaper">
										<div class="number">
											<span class="minus">-</span>
											<aui:input
												name="pr_tobacooyears" placeholder="&nbsp;" label=""
												onkeyup="validateRadioButtonsOnChange('health_details_form','pr_tobacooyears')"
												cssClass="numberField vnumber validate range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeTobaccoPouchSince==null? 0:spouseLifeStyleDetails.qSmokeConsumeTobaccoPouchSince) : 
												(ProposerLifeStyleDetails.qSmokeConsumeTobaccoPouchSince==null? 0:ProposerLifeStyleDetails.qSmokeConsumeTobaccoPouchSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_smoke_consume_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
											</aui:input>
											<span class="plus plus-health-range">+</span>
										</div>
									</div>
								</div>

							</div>
						</div>
						
						<div class="col-md-6 col-12">
						 	<div class="input-box">
						 		<p><liferay-ui:message key="label-q-stopped-smoking-yn" /></p>
						 	</div>
						 </div>
						 
						 <div class="col-md-3 col-6">
						 	<div class="radio_container validate">
						 		<aui:input name="la_q_stopped_smoking_yn" type="radio" value="Y" disabled="${isLaMinor}" onChange="validateRadioButtonsOnChange('health_details_form','la_q_stopped_smoking_yn')" cssClass="setOpen validate" label="" id="la_quit_smoking_yes" onclick="showDiv(true, 'la_smokingFields');" checked="${laLifeStyleDetails.qStoppedSmokingYn=='Y'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_quit_smoking_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="la_q_stopped_smoking_yn" type="radio" value="N" onChange="validateRadioButtonsOnChange('health_details_form','la_q_stopped_smoking_yn')" cssClass="validate" label="" id="la_quit_smoking_no" onclick="showDiv(false, 'la_smokingFields');" checked="${isLaMinor || laLifeStyleDetails.qStoppedSmokingYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>la_quit_smoking_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						 <c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						 <div class="col-md-3 col-6">
						 	<div class="radio_container smoking">
						 		<aui:input name="pr_q_stopped_smoking_yn" type="radio" value="Y" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_stopped_smoking_yn')" cssClass="setOpen validate" label="" id="ps_quit_smoking_yes" onclick="showDiv(true, 'pr_smokingFields');" checked="${insurerDetails.qStoppedSmokingYn=='Y'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_quit_smoking_yes"><liferay-ui:message key="label-yes" /></label>
						 		
						 		<aui:input name="pr_q_stopped_smoking_yn" type="radio" value="N" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_stopped_smoking_yn')" cssClass="validate" label="" id="ps_quit_smoking_no" onclick="showDiv(false, 'pr_smokingFields');" checked="${insurerDetails.qStoppedSmokingYn=='N'?true:false }">
						 		<aui:validator name="required" errorMessage="this-field-is-required" />
						 		</aui:input>
						 		<label for="<portlet:namespace/>ps_quit_smoking_no"><liferay-ui:message key="label-no" /></label>
						 	</div>
						 </div>
						</c:if>
						<div class="col-12 mb-4" id="la_smokingFields" style="display:none;">
							<div class="input-wrapper">
								<label><span>Life Insured</span></label>
							</div>
							
							<div class="row">
						 		<div class="col-md-4 col-12">
									<div class="pack-wrapper with-input">
										<p><liferay-ui:message key="label-smokingyears" /></p>
										<div class="number-wrapaper">
											<div class="number">
												<span class="minus">-</span>
												<aui:input
													name="la_smoking" placeholder="&nbsp;" label=""
													onkeyup="validateRadioButtonsOnChange('health_details_form','la_smoking')"
													cssClass="numberField vnumber validate range-medical-section" type="text"
													value="${laLifeStyleDetails.qStoppedSmokingSince==null? 0:laLifeStyleDetails.qStoppedSmokingSince}"
													maxLength="3" minLength="1" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_stopped_smoking_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
												</aui:input>
												<span class="plus plus-health-range">+</span>
											</div>
										</div>
									</div>
							   	</div>
							   	<div class="col-md-4 col-12">
									<div class="form-box">
							             <label class="custom-field two">
							             	<aui:input oninput="validateRadioButtonsOnChange('health_details_form','la_smokingreason')" name="la_smokingreason" cssClass="valphanumwithspace validate" type="text" placeholder="&nbsp;" label="" wrappedField="<%=true %>" value="${laLifeStyleDetails.qStoppedSmokingReason}">
							             		<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>la_q_stopped_smoking_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
							             	</aui:input>
							             	<span class="placeholder"><liferay-ui:message key="label-smokingreason" /></span>
							             </label>
							       	</div>
								</div>
							</div>
						</div>
						
						<div class="col-12 mb-4" id="pr_smokingFields" style="display:none;">
							<div class="input-wrapper">
								<label><span>Proposer / Spouse</span></label>
							</div>
							
							<div class="row">
						 		<div class="col-md-4 col-12">
									<div class="pack-wrapper with-input">
										<p><liferay-ui:message key="label-smokingyears" /></p>
										<div class="number-wrapaper">
											<div class="number">
												<span class="minus">-</span>
												<aui:input
													name="pr_smoking" placeholder="&nbsp;" label=""
													onkeyup="validateRadioButtonsOnChange('health_details_form','pr_smoking')"
													cssClass="numberField vnumber validate range-medical-section" type="text"
													value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qStoppedSmokingSince==null? 0:spouseLifeStyleDetails.qStoppedSmokingSince) : 
													(ProposerLifeStyleDetails.qStoppedSmokingSince==null? 0:ProposerLifeStyleDetails.qStoppedSmokingSince)}"
													maxLength="3" minLength="1" wrappedField="<%=true %>">
													<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_stopped_smoking_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
												</aui:input>
												<span class="plus plus-health-range">+</span>
											</div>
										</div>
									</div>
							   	</div>
							   	<div class="col-md-4 col-12">
									<div class="form-box">
							             <label class="custom-field two">
							             	<aui:input name="pr_smokingreason" oninput="validateRadioButtonsOnChange('health_details_form','pr_smokingreason')" cssClass="valphanumwithspace validate" type="text" placeholder="&nbsp;" label="" wrappedField="<%=true %>" value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qStoppedSmokingReason:ProposerLifeStyleDetails.qStoppedSmokingReason}">
							             		<aui:validator name="required" errorMessage="other-details-message">
														function(val){
															var radioData = $('input[name=<portlet:namespace/>pr_q_stopped_smoking_yn]:checked').val();
															if(radioData == "Y"){
																return true;
															}else{
																return false;
															}
														}
													</aui:validator>
							             	</aui:input>
							             	<span class="placeholder"><liferay-ui:message key="label-smokingreason" /></span>
							             </label>
							       	</div>
								</div>
							</div>
						</div>
						
					</div>
      				</div>
      			</div>
      		</div>
		
</div>
