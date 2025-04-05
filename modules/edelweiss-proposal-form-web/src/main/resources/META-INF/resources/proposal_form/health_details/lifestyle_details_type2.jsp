<%@ include file="../../init.jsp"%>
<div class="communication-details" id="lifestyle-personal-sec">
	<div class="medical-test">
		<div class="radio-wrapper">
			<div class="location-field">
				<div class="row">
					<div class="col-md-6 col-12">
						<div class="edto-income-plan-tiles-wrapper">
							<h4>
								<liferay-ui:message key="lifestyle-personal-details" />
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
							<p><liferay-ui:message key="label-q-travel-outside-yn"  /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_travel_outside_yn" type="radio" value="Y" cssClass="setOpen validate"
								label="" id="la_plan_travel_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_travel_outside_yn')"
								checked="${laLifeStyleDetails.qTravelOutsideYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_plan_travel_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_travel_outside_yn" type="radio" value="N" cssClass="validate"
								label="" id="la_plan_travel_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_travel_outside_yn')"
								checked="${laLifeStyleDetails.qTravelOutsideYn=='N'?true:false }">
							</aui:input>
							<label for="<portlet:namespace/>la_plan_travel_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container travel">
								<aui:input required="true" name="pr_q_travel_outside_yn" type="radio"
									value="Y"  cssClass="setOpen validate" label="" id="ps_plan_travel_yes" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_travel_outside_yn')"
									checked="${insurerDetails.qTravelOutsideYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_plan_travel_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_travel_outside_yn" type="radio"
									value="N" label="" id="ps_plan_travel_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_travel_outside_yn')"
									checked="${insurerDetails.qTravelOutsideYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_plan_travel_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
						<p><liferay-ui:message key="label-q-pilot-co-pilot-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_pilot_co_pilot_yn" type="radio" value="Y" cssClass="setOpen validate"
								label="" id="la_pilot_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_pilot_co_pilot_yn')"
								checked="${laLifeStyleDetails.qPilotCoPilotYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_pilot_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" required="true" name="la_q_pilot_co_pilot_yn" type="radio" value="N"
								label="" id="la_pilot_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_pilot_co_pilot_yn')"
								checked="${laLifeStyleDetails.qPilotCoPilotYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_pilot_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_pilot_co_pilot_yn"
									type="radio" value="Y"  cssClass="setOpen validate" label="" id="ps_pilot_yes" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pilot_co_pilot_yn')"
									checked="${insurerDetails.qPilotCoPilotYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_pilot_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_pilot_co_pilot_yn"
									type="radio" value="N" label="" id="ps_pilot_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_pilot_co_pilot_yn')"
									checked="${insurerDetails.qPilotCoPilotYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_pilot_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<p> <liferay-ui:message key="label-q-adventurous-hobbies-yn"  /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_adventurous_hobbies_yn" type="radio" value="Y" cssClass="setOpen validate" 
								label="" id="la_adventure_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_adventurous_hobbies_yn')"
								checked="${laLifeStyleDetails.qAdventurousHobbiesYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_adventure_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_adventurous_hobbies_yn" type="radio" value="N"
								label="" id="la_adventure_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_adventurous_hobbies_yn')"
								checked="${laLifeStyleDetails.qAdventurousHobbiesYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_adventure_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
						<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_adventurous_hobbies_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_adventurous_hobbies_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_adventure_yes"
									checked="${insurerDetails.qAdventurousHobbiesYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_adventure_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_adventurous_hobbies_yn"
									type="radio" value="N" label="" id="ps_adventure_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_adventurous_hobbies_yn')"
									checked="${insurerDetails.qAdventurousHobbiesYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_adventure_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-sky-diving-or-parachuting-yn" /> </p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_sky_diving_or_parachuting_yn" type="radio"
								value="Y" cssClass="setOpen validate" label="" id="la_skydiving_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_sky_diving_or_parachuting_yn')"
								checked="${laLifeStyleDetails.qSkyDivingOrParachutingYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_skydiving_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input name="la_q_sky_diving_or_parachuting_yn" type="radio" onChange="validateRadioButtonsOnChange('health_details_form','la_q_sky_diving_or_parachuting_yn')"
								value="N" cssClass="setOpen validate" label="" id="la_skydiving_no"
								checked="${laLifeStyleDetails.qSkyDivingOrParachutingYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_skydiving_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_sky_diving_or_parachuting_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_sky_diving_or_parachuting_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_skydiving_yes"
									checked="${insurerDetails.qSkyDivingOrParachutingYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_skydiving_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_sky_diving_or_parachuting_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_sky_diving_or_parachuting_yn')"
									type="radio" value="N" label="" id="ps_skydiving_no"
									checked="${insurerDetails.qSkyDivingOrParachutingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_skydiving_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-paragliding-or-handgliding-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_paragliding_or_handgliding_yn" type="radio"
								value="Y" cssClass="setOpen validate" label="" id="la_paragliding_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_paragliding_or_handgliding_yn')"
								checked="${laLifeStyleDetails.qParaglidingOrHandglidingYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_paragliding_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_paragliding_or_handgliding_yn" type="radio"
								value="N" label="" id="la_paragliding_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_paragliding_or_handgliding_yn')"
								checked="${laLifeStyleDetails.qParaglidingOrHandglidingYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_paragliding_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true"
									name="pr_q_paragliding_or_handgliding_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_paragliding_or_handgliding_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_paragliding_yes"
									checked="${insurerDetails.qParaglidingOrHandglidingYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_paragliding_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate"
									name="pr_q_paragliding_or_handgliding_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_paragliding_or_handgliding_yn')"
									type="radio" value="N" label="" id="ps_paragliding_no"
									checked="${insurerDetails.qParaglidingOrHandglidingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_paragliding_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
								<p><liferay-ui:message key="label-q-mountaineering-or-outoor-rock-climbing-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_mountaineering_or_outdoor_rock_climbing_yn" onChange="validateRadioButtonsOnChange('health_details_form','la_q_mountaineering_or_outdoor_rock_climbing_yn')"
								type="radio" value="Y"  cssClass="setOpen validate" label="" id="la_mountaineering_yes"
								checked="${laLifeStyleDetails.qMountaineeringOrOutdoorRockClimbingYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_mountaineering_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_mountaineering_or_outdoor_rock_climbing_yn" onChange="validateRadioButtonsOnChange('health_details_form','la_q_mountaineering_or_outdoor_rock_climbing_yn')"
								type="radio" value="N" label="" id="la_mountaineering_no"
								checked="${laLifeStyleDetails.qMountaineeringOrOutdoorRockClimbingYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_mountaineering_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true"
									name="pr_q_mountaineering_or_outdoor_rock_climbing_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_mountaineering_or_outdoor_rock_climbing_yn')"
									type="radio" value="Y"  cssClass="setOpen validate" label="" id="ps_mountaineering_yes"
									checked="${insurerDetails.qMountaineeringOrOutdoorRockClimbingYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_mountaineering_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate"
									name="pr_q_mountaineering_or_outdoor_rock_climbing_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_mountaineering_or_outdoor_rock_climbing_yn')"
									type="radio" value="N" label="" id="ps_mountaineering_no"
									checked="${insurerDetails.qMountaineeringOrOutdoorRockClimbingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_mountaineering_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-any-form-of-racing-yn" ></liferay-ui:message></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" name="la_q_any_form_of_racing_yn" type="radio" value="Y" cssClass="setOpen validate"
								label="" id="la_racing_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_form_of_racing_yn')"
								checked="${laLifeStyleDetails.qAnyFormOfRacingYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_racing_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_any_form_of_racing_yn" type="radio" value="N"
								label="" id="la_racing_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_form_of_racing_yn')"
								checked="${laLifeStyleDetails.qAnyFormOfRacingYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_racing_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_any_form_of_racing_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_form_of_racing_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_racing_yes"
									checked="${insurerDetails.qAnyFormOfRacingYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_racing_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_any_form_of_racing_yn"
									type="radio" value="N" label="" id="ps_racing_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_form_of_racing_yn')"
									checked="${insurerDetails.qAnyFormOfRacingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_racing_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-any-other-hazardous-hobby-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container hazardousActivity">
							<aui:input required="true" name="la_q_any_other_hazardous_hobby_yn" type="radio"
								value="Y" cssClass="setOpen validate" label="" id="la_hazardous_yes"
								onclick="showDiv(true, 'la_hazardousActivityFields');" onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_other_hazardous_hobby_yn')"
								checked="${laLifeStyleDetails.qAnyOtherHazardousHobbyYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_hazardous_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_any_other_hazardous_hobby_yn" type="radio"
								value="N" label="" id="la_hazardous_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_any_other_hazardous_hobby_yn')"
								onclick="showDiv(false, 'la_hazardousActivityFields');"
								checked="${laLifeStyleDetails.qAnyOtherHazardousHobbyYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_hazardous_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_any_other_hazardous_hobby_yn"
									type="radio" value="Y"  cssClass="setOpen validate" label="" id="ps_hazardous_yes"
									 onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_other_hazardous_hobby_yn')"
									checked="${insurerDetails.qAnyOtherHazardousHobbyYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_hazardous_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_any_other_hazardous_hobby_yn"
									type="radio" value="N" label="" id="ps_hazardous_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_any_other_hazardous_hobby_yn')"
									checked="${insurerDetails.qAnyOtherHazardousHobbyYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_hazardous_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-12" id="la_hazardousActivityFields"
						style="display: none;">
						<div class="input-wrapper">
							<label><span>Life Insured</span></label>
						</div>
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('health_details_form','la_hazardousActivity')"
									name="la_hazardousActivity" type="text" placeholder="&nbsp;" label=""
									wrappedField="<%=true %>" maxLength="150" cssClass="valphanumwithspace validate"
									value="${laLifeStyleDetails.qAdventurousHobbiesDetails }">
										<aui:validator name="required" errorMessage="other-details-message">
											function requiedField(val){
												var politicalDetails = $('input[name="<portlet:namespace/>la_q_any_other_hazardous_hobby_yn"]:checked').val();
												if(politicalDetails == "Y"){
													return true;
												}else{
													return false;
												}
												return politicalDetails;
											}
										</aui:validator>
									</aui:input>
								<span class="placeholder"><liferay-ui:message key="label-if-yes-mention-details" /></span>
							</label>
						</div>
					</div>

					<div class="col-12" id="pr_hazardousActivityFields"
						style="display: none;">
						<div class="input-wrapper">
							<label><span>Proposer / Spouse</span></label>
						</div>
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('health_details_form','pr_hazardousActivity')"
									name="pr_hazardousActivity" type="text" placeholder="&nbsp;" label=""
									wrappedField="<%=true %>" cssClass=" "
									value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qAdventurousHobbiesDetails:ProposerLifeStyleDetails.qAdventurousHobbiesDetails}">
										<aui:validator name="required" errorMessage="other-details-message">
											function requiedField(val){
												var politicalDetails = $('input[name="<portlet:namespace/>pr_q_any_other_hazardous_hobby_yn"]:checked').val();
												if(politicalDetails == "Y"){
													return true;
												}else{
													return false;
												}
												return politicalDetails;
											}
										</aui:validator>
									</aui:input>
								<span class="placeholder"><liferay-ui:message key="label-if-yes-mention-details" /></span>
							</label>
						</div>
					</div>

					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-habit-forming-drugs-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container drugs">
							<aui:input required="true" name="la_q_habit_forming_drugs_yn" type="radio" value="Y" cssClass="setOpen validate" disabled="${isLaMinor}"
								label="" id="la_drugs_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_habit_forming_drugs_yn')"
								onclick="showDiv(true, 'la_drugsFields');"
								checked="${laLifeStyleDetails.qHabitFormingDrugsYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_drugs_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_habit_forming_drugs_yn" type="radio" value="N"
								label="" id="la_drugs_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_habit_forming_drugs_yn')"
								onclick="showDiv(false, 'la_drugsFields');"
								checked="${isLaMinor || laLifeStyleDetails.qHabitFormingDrugsYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_drugs_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container pilot">
								<aui:input required="true" name="pr_q_habit_forming_drugs_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_habit_forming_drugs_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_drugs_yes"
									checked="${insurerDetails.qHabitFormingDrugsYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_drugs_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_habit_forming_drugs_yn"
									type="radio" value="N" label="" id="ps_drugs_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_habit_forming_drugs_yn')"
									checked="${insurerDetails.qHabitFormingDrugsYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_drugs_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-12" id="la_drugsFields" style="display: none;">
						<div class="input-wrapper">
							<label><span>Life Insured</span></label>
						</div>
						<div class="form-box">
							<label class="custom-field two"> <aui:input
									name="la_q_habit_forming_drugs_details" type="text" maxLength="150"
									oninput="validateRadioButtonsOnChange('health_details_form','la_q_habit_forming_drugs_details')"
									placeholder="&nbsp;" label="" wrappedField="<%=true %>" cssClass="valphanumwithspace validate"
									value="${laLifeStyleDetails.qHabitFormingDrugsDetails }">
										<aui:validator name="required" errorMessage="other-details-message">
											function requiedField(val){
												var politicalDetails = $('input[name="<portlet:namespace/>la_q_habit_forming_drugs_yn"]:checked').val();
												if(politicalDetails == "Y"){
													return true;
												}else{
													return false;
												}
												return politicalDetails;
											}
										</aui:validator>
									</aui:input>
								<span class="placeholder"><liferay-ui:message key="label-if-yes-mention-details" /></span>
							</label>
						</div>
					</div>

					<div class="col-12" id="pr_drugsFields" style="display: none;">
						<div class="input-wrapper">
							<label><span>Proposer</span></label>
						</div>
						<div class="form-box">
							<label class="custom-field two"> <aui:input
								oninput="validateRadioButtonsOnChange('health_details_form','pr_q_habit_forming_drugs_details')"
									name="pr_q_habit_forming_drugs_details" type="text" cssClass=" "
									placeholder="&nbsp;" label="" wrappedField="<%=true %>"
									value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qHabitFormingDrugsDetails:ProposerLifeStyleDetails.qHabitFormingDrugsDetails}">
										<aui:validator name="required" errorMessage="other-details-message">
											function requiedField(val){
												var politicalDetails = $('input[name="<portlet:namespace/>pr_q_habit_forming_drugs_yn"]:checked').val();
												if(politicalDetails == "Y"){
													return true;
												}else{
													return false;
												}
												return politicalDetails;
											}
										</aui:validator>
									</aui:input>
								<span class="placeholder"><liferay-ui:message key="label-if-yes-mention-details" /></span>
							</label>
						</div>
					</div>

					<div class="col-md-6 col-12">
						<div class="input-box">
							<p><liferay-ui:message key="label-q-consume-alcohol-yn" /></p>
						</div>
					</div>

					<div class="col-md-3 col-6">
						<div class="radio_container">
							<aui:input required="true" label="" disabled="${isLaMinor}" name="la_q_consume_alcohol_yn" type="radio"
								value="Y" cssClass="setOpen validate" id="la_alcohol_yes"
								onclick="showDiv(true, 'la_alcoholFields');" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_yn')"
								checked="${laLifeStyleDetails.qConsumeAlcoholYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_alcohol_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" label="" name="la_q_consume_alcohol_yn" type="radio"
								value="N" id="la_alcohol_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_consume_alcohol_yn')"
								onclick="showDiv(false, 'la_alcoholFields');"
								checked="${isLaMinor || laLifeStyleDetails.qConsumeAlcoholYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_alcohol_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container alcohol">
								<aui:input required="true" name="pr_q_consume_alcohol_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_yn')"
									type="radio" value="Y" cssClass="setOpen validate" label="" id="ps_alcohol_yes"
									checked="${insurerDetails.qConsumeAlcoholYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_alcohol_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_consume_alcohol_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_consume_alcohol_yn')"
									type="radio" value="N" label="" id="ps_alcohol_no"
									checked="${insurerDetails.qConsumeAlcoholYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_alcohol_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-md-12 mb-4" id="la_alcoholFields" style="display: none;">
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:laLifeStyleDetails.qConsumeAlcoholBeerQty}">
												<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:laLifeStyleDetails.qConsumeAlcoholHardLiquorQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:laLifeStyleDetails.qConsumeAlcoholWineQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
					<div class="col-md-12 mb-4" id="pr_alcoholFields" style="display: none;">
						<div class="input-wrapper">
							<label><span>Proposer</span></label>
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
											cssClass="numberField vnumber  range-medical-section" type="text" 
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholBeerQty) : 
											(ProposerLifeStyleDetails.qConsumeAlcoholBeerQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholBeerQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
											cssClass="numberField vnumber  range-medical-section" type="text" 
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholHardLiquorQty) :
											(ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholHardLiquorQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
											cssClass="numberField vnumber  range-medical-section" type="text" 
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:spouseLifeStyleDetails.qConsumeAlcoholWineQty) :
											(ProposerLifeStyleDetails.qConsumeAlcoholWineQty==null? 0:ProposerLifeStyleDetails.qConsumeAlcoholWineQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_consume_alcohol_yn"]:checked').val();
													if(radioAns == "Y"){
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
							<aui:input required="true" name="la_q_smoke_consume_yn" disabled="${isLaMinor || laPersonalDetails.isSmokerYn=='N'}" type="radio" value="Y" cssClass="setOpen validate"
								label="" id="la_smoke_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_smoke_consume_yn')"
								onclick="showDiv(true, 'la_smokeFields');"
								checked="${laPersonalDetails.isSmokerYn=='Y' || laLifeStyleDetails.qSmokeConsumeYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_smoke_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_smoke_consume_yn" type="radio" value="N" disabled="${laPersonalDetails.isSmokerYn=='Y'}"
								label="" id="la_smoke_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_smoke_consume_yn')"
								onclick="showDiv(false, 'la_smokeFields');"
								checked="${isLaMinor || laPersonalDetails.isSmokerYn=='N' || laLifeStyleDetails.qSmokeConsumeYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_smoke_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container smoke">
								<aui:input required="true" name="pr_q_smoke_consume_yn" type="radio" cssClass="setOpen validate"
									value="Y" label="" id="ps_smoke_yes" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_smoke_consume_yn')"
									checked="${insurerDetails.qSmokeConsumeYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_smoke_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_smoke_consume_yn" type="radio"
									value="N" label="" id="ps_smoke_no" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_smoke_consume_yn')"
									checked="${insurerDetails.qSmokeConsumeYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_smoke_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-12 mb-4" id="la_smokeFields" style="display: none;">
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:laLifeStyleDetails.qSmokeConsumeCigarettesQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
								<p><liferay-ui:message key="label-bidi"/></p>
								<div class="number-wrapaper">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="la_bidi" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','la_bidi')"
											cssClass="numberField vnumber validate range-medical-section" type="text" 
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:laLifeStyleDetails.qSmokeConsume_BidiQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:laLifeStyleDetails.qSmokeConsumeGutkaQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.qSmokeConsumePaanQty==null? 0:laLifeStyleDetails.qSmokeConsumePaanQty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
								<p><liferay-ui:message key="label-tobacoo"/></p>
								<div class="number-wrapaper">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="la_tobacoo" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','la_tobacoo')"
											cssClass="numberField vnumber validate range-medical-section" type="text" 
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${laLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:laLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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

					<div class="col-12 mb-4" id="pr_smokeFields" style="display: none;">
						<div class="input-wrapper">
							<label><span>Proposer</span></label>
						</div>
						<div class="form-wrapper">
							<div class="pack-wrapper">
								<p><liferay-ui:message key="label-cigarettes" /></p>
								<div class="number-wrapaper">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="pr_cigarettes" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','pr_cigarettes')"
											cssClass="numberField vnumber  range-medical-section" type="text"
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:spouseLifeStyleDetails.qSmokeConsumeCigarettesQty) : 
											(ProposerLifeStyleDetails.qSmokeConsumeCigarettesQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumeCigarettesQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
											cssClass="numberField vnumber  range-medical-section" type="text"
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:spouseLifeStyleDetails.qSmokeConsume_BidiQty) : 
											(ProposerLifeStyleDetails.qSmokeConsume_BidiQty==null? 0:ProposerLifeStyleDetails.qSmokeConsume_BidiQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
								<p><liferay-ui:message key="label-gutka" /></p>
								<div class="number-wrapaper">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="pr_gutka" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','pr_gutka')"
											cssClass="numberField vnumber  range-medical-section" type="text"
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:spouseLifeStyleDetails.qSmokeConsumeGutkaQty) : 
											(ProposerLifeStyleDetails.qSmokeConsumeGutkaQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumeGutkaQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
								<p><liferay-ui:message key="label-paan" /></p>
								<div class="number-wrapaper">
									<div class="number">
										<span class="minus">-</span>
										<aui:input
											name="pr_paan" placeholder="&nbsp;" label=""
											onkeyup="validateRadioButtonsOnChange('health_details_form','pr_paan')"
											cssClass="numberField vnumber  range-medical-section" type="text"
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qSmokeConsumePaanQty==null? 0:spouseLifeStyleDetails.qSmokeConsumePaanQty) :
											(ProposerLifeStyleDetails.qSmokeConsumePaanQty==null? 0:ProposerLifeStyleDetails.qSmokeConsumePaanQty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
											cssClass="numberField vnumber  range-medical-section" type="text"
											maxLength="3" minLength="1" wrappedField="<%=true %>"
											value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:spouseLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty) : 
											(ProposerLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty==null? 0:ProposerLifeStyleDetails.q_smoke_consume_tobacco_pouch_qty)}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_smoke_consume_yn"]:checked').val();
													if(radioAns == "Y"){
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
							<aui:input required="true" name="la_q_stopped_smoking_yn" type="radio" disabled="${isLaMinor}" value="Y" cssClass="setOpen validate"
								label="" id="la_quit_smoking_yes" onChange="validateRadioButtonsOnChange('health_details_form','la_q_stopped_smoking_yn')"
								onclick="showDiv(true, 'la_smokingFields');"
								checked="${laLifeStyleDetails.qStoppedSmokingYn=='Y'?true:false }">
								<aui:validator name="required" errorMessage="this-field-is-required" />
							</aui:input>
							<label for="<portlet:namespace/>la_quit_smoking_yes"><liferay-ui:message
									key="label-yes" /></label>

							<aui:input cssClass="validate" name="la_q_stopped_smoking_yn" type="radio" value="N"
								label="" id="la_quit_smoking_no" onChange="validateRadioButtonsOnChange('health_details_form','la_q_stopped_smoking_yn')"
								onclick="showDiv(false, 'la_smokingFields');"
								checked="${isLaMinor || laLifeStyleDetails.qStoppedSmokingYn=='N'?true:false }"></aui:input>
							<label for="<portlet:namespace/>la_quit_smoking_no"><liferay-ui:message
									key="label-no" /></label>
						</div>
					</div>
					<c:if test="${commonDetails.isLaPrSameYn == 'N' || commonDetails.spouseExistYn == 'Y'}">
						<div class="col-md-3 col-6">
							<div class="radio_container smoking">
								<aui:input required="true" name="pr_q_stopped_smoking_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_stopped_smoking_yn')"
									type="radio" value="Y"  cssClass="setOpen validate" label="" id="ps_quit_smoking_yes"
									checked="${insurerDetails.qStoppedSmokingYn=='Y'?true:false }">
									<aui:validator name="required" errorMessage="this-field-is-required" />
								</aui:input>
								<label for="<portlet:namespace/>ps_quit_smoking_yes"><liferay-ui:message
										key="label-yes" /></label>

								<aui:input cssClass="validate" name="pr_q_stopped_smoking_yn" onChange="validateRadioButtonsOnChange('health_details_form','pr_q_stopped_smoking_yn')"
									type="radio" value="N" label="" id="ps_quit_smoking_no"
									checked="${insurerDetails.qStoppedSmokingYn=='N'?true:false }"></aui:input>
								<label for="<portlet:namespace/>ps_quit_smoking_no"><liferay-ui:message
										key="label-no" /></label>
							</div>
						</div>
					</c:if>
					<div class="col-12" id="la_smokingFields" style="display: none;">
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
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_stopped_smoking_yn"]:checked').val();
													if(radioAns == "Y"){
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
									<label class="custom-field two"> <aui:input maxlength="150" 
										oninput="validateRadioButtonsOnChange('health_details_form','la_smokingreason')"
											name="la_smokingreason" type="text" placeholder="&nbsp;"
											label="" wrappedField="<%=true %>" cssClass="validate valphanumwithspace"
											value="${laLifeStyleDetails.qStoppedSmokingReason}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>la_q_stopped_smoking_yn"]:checked').val();
													if(radioAns == "Y"){
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
					
					<div class="col-12" id="pr_smokingFields" style="display: none;">
						<div class="input-wrapper">
							<label><span>Proposer</span></label>
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
												cssClass="numberField vnumber  range-medical-section" type="text"
												value="${commonDetails.spouseExistYn == 'Y' ? (spouseLifeStyleDetails.qStoppedSmokingSince==null? 0:spouseLifeStyleDetails.qStoppedSmokingSince) : 
												(ProposerLifeStyleDetails.qStoppedSmokingSince==null? 0:ProposerLifeStyleDetails.qStoppedSmokingSince)}"
												maxLength="3" minLength="1" wrappedField="<%=true %>">
												<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_stopped_smoking_yn"]:checked').val();
													if(radioAns == "Y"){
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
									<label class="custom-field two"> <aui:input maxlength="150" 
										oninput="validateRadioButtonsOnChange('health_details_form','pr_smokingreason')"
											name="pr_smokingreason" type="text" placeholder="&nbsp;"
											label="" wrappedField="<%=true %>" cssClass="valphanumwithspace"
											value="${commonDetails.spouseExistYn == 'Y' ? spouseLifeStyleDetails.qStoppedSmokingReason:ProposerLifeStyleDetails.qStoppedSmokingReason}">
											<aui:validator name="required" errorMessage="other-details-message">
												function requiedField(val){
													var radioAns = $('input[name="<portlet:namespace/>pr_q_stopped_smoking_yn"]:checked').val();
													if(radioAns == "Y"){
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