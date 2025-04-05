<%@ include file="../init.jsp" %>

<div class="card">
    <div class="card-header" id="headingFive">
        <h2 class="mb-0 title-tabs">
            <span><img src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon" /></span>
            <liferay-ui:message key="label-covid-details" />
        </h2>
    </div>
    <div id="collapseFive" class="collapse" aria-labelledby="headingFive" data-parent="#accordionExample">
        <div class="card-body">
            <div class="edto-income-plan-tiles-wrapper">
             <form>
                <div class="communication-details border-0">
                    <h5>COVID Questionnaire</h5>
                    <div class="covind-inner-wrapper">
                        <div class="covid-title-wrapper">
                            <div class="row">
                                <div class="col-md-6 col-12">
                                    <span class="title"> <img src="/o/edelweisstokio-theme/images/praposal/insure.png" alt="icon" /> <liferay-ui:message key="life-to-be-insured" /></span>
                                  
                                    <label class="toggleSwitch nolabel" onclick="">
                                                   <input type="checkbox" checked />
                                                   <span>
                                                       <span>NO TO ALL</span>
                                                       <span>NO TO ALL</span>
                                                   </span>
                                                   <a></a>
                                               </label>
                                </div>
                                <div class="col-md-6 col-12">
                                    <span class="title"> <img src="/o/edelweisstokio-theme/images/praposal/proposal.png" alt="icon" /> <liferay-ui:message key="proposer" /></span>
                                    <label class="toggleSwitch nolabel" onclick="">
                                                   <input type="checkbox" checked />
                                                   <span>
                                                       <span>NO TO ALL</span>
                                                       <span>NO TO ALL</span>
                                                   </span>
                                                   <a></a>
                                               </label>
                                </div>
                            </div>
                            <h6>1) Have you ever tested positive for the novel coronavirus (SARS-CoV-2/COVID-19)? If yes, provide the date of positive diagnosis. And also details of subsequent tests</h6>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-12">
                            <div class="form-box">
                            <div class="radio_container">
										<aui:input name="lifeInsured" type="radio" value="yes" label="" id="lifeInsured_yes"></aui:input>
										<label for="<portlet:namespace/>lifeInsured_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="lifeInsured" type="radio" value="no" label="" id="lifeInsured_no"></aui:input>
										<label for="<portlet:namespace/>lifeInsured_no"><liferay-ui:message key="no" /></label>
									</div>
                            </div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<div class="radio_container">
										<aui:input name="proposerSpouse" type="radio" value="yes" label="" id="proposerSpouse_yes"></aui:input>
										<label for="<portlet:namespace/>proposerSpouse_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="proposerSpouse" type="radio" value="no" label="" id="proposerSpouse_no"></aui:input>
										<label for="<portlet:namespace/>proposerSpouse_no"><liferay-ui:message key="no" /></label>
									</div>
								</div>
                            </div>
                            <div class="col-md-12 col-12">
                                <p class="mb-3 mt-4">a) If yes, please select any one option?</p>
                                
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            <div class="select-container">
                                    <aui:select title=" " name="lifeinsuredLI" label="select" cssClass="select2-select">
                                        <aui:option type="hidden"></aui:option>
                                        <aui:option value="">Hospitalized</aui:option>
                                        <aui:option value="">Asymptomatic Home Quarantined</aui:option>
                                        <aui:option value="">Ongoing</aui:option>
                                    </aui:select>
                                </div>
							</div>
							</div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                                <div class="select-container">
                                    <aui:select title=" " name="lifeinsuredPO" label="select">
                                        <aui:option type="hidden"></aui:option>
                                        <aui:option value="">Hospitalized</aui:option>
                                        <aui:option value="">Asymptomatic Home Quarantined</aui:option>
                                        <aui:option value="">Ongoing</aui:option>
                                    </aui:select>
                                </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-12">
								 <p class="mb-3 mt-4">b) Date of diagnosis</p>
							</div>
							  <div class="col-md-6 col-12">
							    <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="diagnosisLI" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
							</div>
                              
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="diagnosisPO" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                           
                            <div class="col-md-12 col-12">
								 <p class="mb-3 mt-4">c) Diagnosis details</p>
							</div>
                            <div class="col-md-6 col-12">
                            	<div class="form-box">
								<label class="custom-field two mt-0"> <aui:input
										name="diagnosisdetailsLI" placeholder="&nbsp;" label=""
										cssClass="validate" type="text" 
										value="" maxLength="30"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="Please Provide Details" /></span>
								</label>
							</div>
                                
                            </div>
                            <div class="col-md-6 col-12">
                            	<div class="form-box">
								<label class="custom-field two mt-0"> <aui:input
										name="diagnosisdetailsPO" placeholder="&nbsp;" label=""
										cssClass="validate" type="text" 
										value="" maxLength="30"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="Please Provide Details" /></span>
								</label>
							</div>
                                
                            </div>
                            <div class="col-md-12 col-12">
								<p class="mb-3 mt-4">a) If yes, provide the details of subsequent tests</p>
							</div>
							 <div class="col-md-6 col-12">
                            	<div class="form-box">
								<label class="custom-field two mt-0"> <aui:input
										name="testPO" placeholder="&nbsp;" label=""
										cssClass="validate" type="text" 
										value="" maxLength="30"
										minLength="0" wrappedField="<%=true%>">
									</aui:input> <span class="placeholder"><liferay-ui:message
											key="Please Provide Details" /></span>
								</label>
							</div>
                                
                            </div>
                            <div class="col-md-12 col-12">
								<p class="mb-3 mt-4">b) If yes, then whether you were hospitalized or Asymptomatic home quarantined?</p>
							</div>
                            <div class="col-md-6 col-12">
                                 <div class="form-box">
                                <div class="select-container">
                                    <aui:select title=" " name="lifeinsuredPO">
                                        <aui:option type="hidden"></aui:option>
                                        <aui:option value="">Hospitalized</aui:option>
                                        <aui:option value="">Asymptomatic Home Quarantined</aui:option>
                                    </aui:select>
                                </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                                <div class="select-container">
                                    <aui:select title=" " name="lifeinsuredPO" label="select">
                                        <aui:option type="hidden"></aui:option>
                                        <aui:option value="">Hospitalized</aui:option>
                                        <aui:option value="">Asymptomatic Home Quarantined</aui:option>
                                    </aui:select>
                                </div>
                                </div>
                            </div>
                            <div class="col-md-12 col-12">
								 <p class="mb-3 mt-4">c) If yes, Then whether you suffered Covid-19 related Complications?</p>
							</div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<div class="radio_container">
										<aui:input name="lifeInsuredLI1" type="radio" value="yes" label="" id="lifeInsuredLI1_yes"></aui:input>
										<label for="<portlet:namespace/>lifeInsuredLI1_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="lifeInsuredLI1" type="radio" value="no" label="" id="lifeInsuredLI1_no"></aui:input>
										<label for="<portlet:namespace/>lifeInsuredLI1_no"><liferay-ui:message key="no" /></label>
									</div>
									</div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<div class="radio_container">
										<aui:input name="proposerSpousePO1" type="radio" value="yes" label="" id="proposerSpousePO1_yes"></aui:input>
										<label for="<portlet:namespace/>proposerSpousePO1_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="proposerSpousePO1" type="radio" value="no" label="" id="proposerSpousePO1_no"></aui:input>
										<label for="<portlet:namespace/>proposerSpousePO1_no"><liferay-ui:message key="no" /></label>
									</div>
									</div>
                            </div>

                            <div class="col-md-12 col-12">
                                <h6 class="text-divide">2) Have you been vaccinated for COVID19?</h6>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                           			 <div class="radio_container">
										<aui:input name="lifeInsured" type="radio" value="yes" label="" id="lifeInsuredCOVID19_yes"></aui:input>
										<label for="<portlet:namespace/>lifeInsuredCOVID19_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="lifeInsured" type="radio" value="no" label="" id="lifeInsuredCOVID19_no"></aui:input>
										<label for="<portlet:namespace/>lifeInsuredCOVID19_no"><liferay-ui:message key="no" /></label>
									</div>
									</div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<div class="radio_container">
										<aui:input name="proposerSpouse" type="radio" value="yes" label="" id="proposerSpouse_yes"></aui:input>
										<label for="<portlet:namespace/>proposerSpouse_yes"><liferay-ui:message key="yes" /></label>
										
										<aui:input name="proposerSpouse" type="radio" value="no" label="" id="proposerSpouse_no"></aui:input>
										<label for="<portlet:namespace/>proposerSpouse_no"><liferay-ui:message key="no" /></label>
									</div>
								</div>
                            </div>
                            <div class="col-md-12 col-12">
								<p class="mb-3 mt-4">a) Date of administration of the first dose</p>
							</div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="administrationLI" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="administrationPO" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                            <div class="col-md-12 col-12">
                                <p class="mb-3 mt-4">b) Date of administration of the second dose</p>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="seconddoseLI" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="seconddosePO" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                            <div class="col-md-12 col-12">
                               <p class="mb-3 mt-4">c) Date of administration of Booster dose</p>
                            </div>
                            <div class="col-md-6 col-12">
                             <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="seconddosePO" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                             <div class="col-md-6 col-12">
                              <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="boosterdoseLI" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                           <div class="col-md-6 col-12">
                            <div class="form-box">
                            	<label class="custom-field two mt-0"> <aui:input
									name="boosterdosePO" placeholder="&nbsp;" label=""
									cssClass="validate vdate" type="text"
									value="" maxLength="10"
									minLength="0" wrappedField="<%=true%>">
								</aui:input> <span class="placeholder"><liferay-ui:message
										key="Please Provide Details" /></span>
							</label>
                              </div>
                            </div>
                            
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>