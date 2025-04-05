<%@ include file="../init.jsp"%>

<liferay-portlet:actionURL var="submitPersonalityQuizFormURL"
	name="<%=PersonalityQuizPortletKeys.SUBMIT_PERSONALITY_QUIZ_FORM_MVC_ACTION_COMMAND%>">
</liferay-portlet:actionURL>

<%
	String quiz1Name = (String) renderRequest.getAttribute("quiz1Name");

	HashMap<String, String> quiz1Options = new HashMap<String, String>();
	if (Validator.isNotNull(renderRequest.getAttribute("quiz1Options"))) {
		quiz1Options = (HashMap<String, String>) renderRequest.getAttribute("quiz1Options");
	}

	String quiz2Name = (String) renderRequest.getAttribute("quiz2Name");

	HashMap<String, String> quiz2Options = new HashMap<String, String>();
	if (Validator.isNotNull(renderRequest.getAttribute("quiz2Options"))) {
		quiz2Options = (HashMap<String, String>) renderRequest.getAttribute("quiz2Options");
	}

	String quiz3Name = (String) renderRequest.getAttribute("quiz3Name");

	HashMap<String, String> quiz3Options = new HashMap<String, String>();
	if (Validator.isNotNull(renderRequest.getAttribute("quiz3Options"))) {
		quiz3Options = (HashMap<String, String>) renderRequest.getAttribute("quiz3Options");
	}

	String quiz4Name = (String) renderRequest.getAttribute("quiz4Name");

	HashMap<String, String> quiz4Options = new HashMap<String, String>();
	if (Validator.isNotNull(renderRequest.getAttribute("quiz4Options"))) {
		quiz4Options = (HashMap<String, String>) renderRequest.getAttribute("quiz4Options");
	}
%>
<div class="moveable-circle"></div>
                <div class="d-flex justify-content-center onix-star-block">
                    <div class="onix-stars animated lightSpeedIn">

                        <div class="star-zoom">
                            <img src="/o/edelweisstokio-theme/images/discovery/star.png" alt="" class="stars1">
                            <img src="/o/edelweisstokio-theme/images/discovery/Star.svg" alt="" class="star1 d-none">
                        </div>
                        <div class="arrows arrow1"></div>
                        <div class="star-zoom">
                            <img src="/o/edelweisstokio-theme/images/discovery/star.png" alt="" class="stars2">
                            <img src="/o/edelweisstokio-theme/images/discovery/Star.svg" alt="" class="star2 d-none">
                        </div>
                        <div class="arrows arrow2"></div>
                        <div class="star-zoom">
                            <img src="/o/edelweisstokio-theme/images/discovery/star.png" alt="" class="stars3">
                            <img src="/o/edelweisstokio-theme/images/discovery/Star.svg" alt="" class="star3 d-none">
                        </div>
                        <div class="arrows arrow3"></div>
                        <div class="star-zoom">
                            <img src="/o/edelweisstokio-theme/images/discovery/star.png" alt="" class="stars4">
                            <img src="/o/edelweisstokio-theme/images/discovery/Star.svg" alt="" class="star4 d-none">
                        </div>
                        <div class="arrows arrow4"></div>
                    </div>
                    <div class="pyro rotateInUpLeft">
                        <div class="before"></div>
                        <div class="after"></div>
                        <div class="onix-gift-icon">
                            <img src="/o/edelweisstokio-theme/images/discovery/gift.png" class=" heartBeat slow">
                        </div>
                    </div>

                </div>
<div class="que-wrapper" id="quizForm">
<aui:form name="personality-quiz-form"
	action="<%=submitPersonalityQuizFormURL%>" method="post"
	enctype="multipart/form-data" onSubmit="event.preventDefault();">
	<input type="hidden" id="quizPortletNamespace"
		value="<portlet:namespace/>" />
	<aui:input type="hidden" value="${submitPersonalityQuizFormURL}"
		name="submitPersonalityQuizFormURL" />
		
			<div class="quiz_step" id='<portlet:namespace/>personality_quiz_step_1'>
				<div class="onix-first-question onix-que-steps">
					<div class="onix-goal-text jackInTheBox delay-1s">
						<p><%=quiz1Name%></p>
					</div>
					<div class="onix-question-list">
						<ul class="que-list">
							<%
								for (Map.Entry<String, String> entry : quiz1Options.entrySet()) {
							%>
							<li class="checkbox checkbox-circle first-answer">
								<aui:input name="quiz1" id="<%=entry.getKey() + "quiz1"%>" cssClass="validate preview"
									type="radio" required="required" label="" value="<%=entry.getKey()%>" 
									onClick="validatePersonalityQuizForm('personality_quiz_step_1', 'personality_quiz_step_2', true, 'personality-quiz-form', false)"
									>
								</aui:input>
								<label for='<portlet:namespace/><%=entry.getKey() + "quiz1"%>'>
									<%=entry.getValue()%>
								</label>
							</li>
							<%
								}
							%>
						</ul>
					</div>
			</div>
			<aui:button-row>
				<img id="backToContent"  src="/o/edelweisstokio-theme/images/discovery/back-arrow.png" >
		</aui:button-row>
		</div>
	<div class="quiz_step quiz_step_hide"
		id='<portlet:namespace/>personality_quiz_step_2'>
		<div class="onix-sec-question onix-que-steps">
			<div class="onix-goal-text">
				<p><%=quiz2Name%></p>
			</div>
			<div class="onix-question-list">
				<ul class="que-list">
					<%
						for (Map.Entry<String, String> entry : quiz2Options.entrySet()) {
					%>
					<li class="checkbox checkbox-circle sec-answer">
						<aui:input name="quiz2" id="<%=entry.getKey() + "quiz2"%>" cssClass="validate preview"
							type="radio" required="required" label="" value="<%=entry.getKey()%>"
							onClick="validatePersonalityQuizForm('personality_quiz_step_2', 'personality_quiz_step_3', true, 'personality-quiz-form', false);">
						</aui:input>
						<label for='<portlet:namespace/><%=entry.getKey() + "quiz2"%>'> 
							<%=entry.getValue()%>
						</label>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<aui:button-row>
				<img id="backToStepOne" onClick="validatePersonalityQuizForm('personality_quiz_step_2', 'personality_quiz_step_1', false, 'personality-quiz-form', false);" src="/o/edelweisstokio-theme/images/discovery/back-arrow.png" >
		</aui:button-row>
	</div>
	
	<div class="quiz_step quiz_step_hide"
		id='<portlet:namespace/>personality_quiz_step_3'>
		<div class="onix-third-question onix-que-steps">
			<div class="onix-goal-text">
				<p><%=quiz3Name%></p>
			</div>
			<div class="onix-question-list">
				<ul class="que-list">
					<%
						for (Map.Entry<String, String> entry : quiz3Options.entrySet()) {
					%>
					<li class="checkbox checkbox-circle third-answer">
						<aui:input name="quiz3" id="<%=entry.getKey() + "quiz3"%>" cssClass="validate preview"
							type="radio" required="required" label="" value="<%=entry.getKey()%>"
							onClick="validatePersonalityQuizForm('personality_quiz_step_3', 'personality_quiz_step_4', true, 'personality-quiz-form', false);">
						</aui:input>
						<label for='<portlet:namespace/><%=entry.getKey() + "quiz3"%>'> 
							<%=entry.getValue()%>
						</label>
					</li>
					<%
						}
					%>
					

				</ul>
			</div>

		</div>
		<aui:button-row>
				<img id="backToStepTwo" onClick="validatePersonalityQuizForm('personality_quiz_step_3', 'personality_quiz_step_2', false, 'personality-quiz-form', false);" src="/o/edelweisstokio-theme/images/discovery/back-arrow.png" >
				
		</aui:button-row>
	</div>
	
	<div class="quiz_step quiz_step_hide"
		id='<portlet:namespace/>personality_quiz_step_4'>
		
		<div class="onix-fourth-question onix-que-steps">
			<div class="onix-goal-text">
				<p><%=quiz4Name%></p>
			</div>
			<div class="onix-question-list">
				<ul class="que-list">
					<%
						for (Map.Entry<String, String> entry : quiz4Options.entrySet()) {
					%>
					<li class="checkbox checkbox-circle fourth-answer">
						<aui:input name="quiz4" id="<%=entry.getKey() + "quiz4"%>" cssClass="validate preview"
							type="radio" required="required" label="" value="<%=entry.getKey()%>"
							onClick="validatePersonalityQuizForm('personality_quiz_step_4', 'personality_quiz_step_5', true, 'personality-quiz-form', false);">
						</aui:input>
						<label for='<portlet:namespace/><%=entry.getKey() + "quiz4"%>'> 
							<%=entry.getValue()%>
						</label>
					</li>
					<%
						}
					%>
				</ul>
			</div>

		</div>
		<aui:button-row>
				<img id="backToStepThree" onClick="validatePersonalityQuizForm('personality_quiz_step_4', 'personality_quiz_step_3', false, 'personality-quiz-form', false);" src="/o/edelweisstokio-theme/images/discovery/back-arrow.png" >
		</aui:button-row>
	</div>

	<div class="quiz_step quiz_step_hide"
		id='<portlet:namespace/>personality_quiz_step_5'>
		<div class="onix-fifth-question">
			<div class="onix-goal-text">
				<p><liferay-ui:message key="label-know-your-investor-personality" /></p>
			</div>
			<div class="onix-question-list">
				<div class="unclaimed-amount-wrapper">
					<div class="wealth-form-box">
					  

						<form class="fifth-answer">
							<div class="row">
								<div class="col-md-12 col-12">
									<label class="custom-field two"> <aui:input name="name"
										label="" placeholder="label-enter-your-name" wrappedField="<%=true%>"
										cssClass="validate valpha">
										<aui:validator name="required" errorMessage='error-label-name' />
										<aui:validator name="custom" errorMessage="error-label-full-name">
											function(val, fieldNode, ruleValue) {
											var regex = new RegExp("^[a-zA-z]+([\\s][a-zA-Z]+)+$");
											return regex.test(val);
											}
										</aui:validator>
									</aui:input>
								</label>
								</div>
								<div class="col-md-12 col-12">
									<label class="custom-field two"> 
									<aui:input name="mobileNo" label="" pattern = "[0-9]*" inputmode="numeric"
										placeholder="label-enter-mobile-no" wrappedField="<%=true%>"
										cssClass="validate vnumber" type="text" value="" maxLength="10" minLength="10" >
										<aui:validator name="number" />
										<aui:validator name="required"
											errorMessage='error-label-mobile-no' />
										<aui:validator name="maxLength"
											errorMessage="error-label-max-length-ten">10</aui:validator>
										<aui:validator name="minLength"
											errorMessage="error-label-min-length-ten">10</aui:validator>
									</aui:input>
								</label>
								</div>
								<div class="col-md-12 col-12">
									<label class="custom-field two"> <aui:input name="email"
										label="" placeholder="label-enter-email-id" wrappedField="<%=true%>"
										cssClass="validate" type="text">
										<aui:validator name="email" />
										<aui:validator name="required" errorMessage='error-label-email' />
									</aui:input>
								</label>
								</div>
								<div class="col-md-12 col-12">
									<label class="custom-field two"> <aui:input
										name="dateOfBirth" label="" placeholder="label-dob"
										wrappedField="<%=true%>" cssClass="validate vdate" type="text" value=""
										maxLength="10">
										<aui:validator name="required"
											errorMessage='error-label-date-of-birth' />
										<aui:validator name="custom" errorMessage="error-label-date">
									function(val, fieldNode, ruleValue) {
			
									var parts = val.split("/");
			
									if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
										return false;
									}else{
										if(parts[0] > 31){
											return false;
										}
										if(parts[1] > 12){
											return false;
										}
										if(parts[2].length!=4){
											return false;
										}
										else if(parts[0].length!=2 && parts[1].length!=2){
											return false;
										}
										return true;
										}
									}
								</aui:validator>
										<aui:validator name="custom"
											errorMessage="error-label-date-must-be-in-past">
										function(val, fieldNode, ruleValue) {
				
										var parts = val.split("/");
										var selectedDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
				
										if(parts[1]=="" || parts[0]=="" || parts[2]=="" ){
											return false;
										}else{
											var now = new Date();
											   if(selectedDate > now) {
												return false;
											   }
											return true;
											}
										}
									</aui:validator>
									<aui:validator name="custom" errorMessage="error-label-age">
						                function(val, fieldNode, ruleValue) {
						
										var parts = val.split("/");
										var birthDate = new Date(parts[1] + "/" + parts[0] + "/" + parts[2]);
						                var today = new Date();
						                var result = false;
						                
						                var age = today.getFullYear() - birthDate.getFullYear();
						                var m = today.getMonth() - birthDate.getMonth();
						
						                if (m < 0 || (m===0 && today.getDate() < birthDate.getDate())) 
						                { 
						                	age--; 
						                } 
						                if (age >= 18 && age <= 80) 
						                {
						                	result = true;
					                    }
					                    else
					                    {
					                    	result = false;
					                    }
					                    return result;
					                    }
						            </aui:validator>
											</aui:input>
								</label>
								</div>
							</div>
							<!-- <a class="edto-btn-primary" href="javascript:;">Search</a> -->
							<aui:button-row>
						<aui:button cssClass="edto-btn-primary edto-btn-primary1"
							id="confirmSubmit" value="label-lets-go"
							onClick="validatePersonalityQuizForm('personality_quiz_step_5', 'personality_quiz_step_5', true, 'personality-quiz-form', true);"></aui:button>
					</aui:button-row>
						</form>
					</div>
				</div>
			</div>
			
		</div>
		
		
		<aui:button-row>
			<img id="backToStepFour" onClick="validatePersonalityQuizForm('personality_quiz_step_5', 'personality_quiz_step_4', false, 'personality-quiz-form', false);" src="/o/edelweisstokio-theme/images/discovery/back-arrow.png" >
		</aui:button-row>
	</div>
</aui:form>
</div>
<!--Modal -->
<div class="modal wealth-modal fade" tabindex="-1" role="dialog"
	id="personalityQuizBackdrop"
	aria-labelledby="personalityQuizBackdropModal" aria-hidden="true"
	data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h2></h2>
					<button type="button" id="closeBtn" class="close"
						data-dismiss="modal" aria-label="Close"
						onClick="closePersonalityQuizModal(false);">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<span id="modal_error" class="error"></span>
					<div class="cusscess-icon mb-5">
						<div class="text-center">
							<h3 id="responseMessage" class="fs22 fontbold  w-100"></h3>
						</div>
					</div>
					<div class="center-wealth-btn">
						<button id="okayBtn" type="button" class="edto-btn-primary"
							onClick="closePersonalityQuizModal(false);">
							<liferay-ui:message key="label-okay" />
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Loader -->
<div id='<portlet:namespace/>quiz-loader' style='display: none;'>
	<div class="preloader"></div>
</div>

<script src="<%=request.getContextPath()%>/js/personality-quiz.js?v=1.1"></script>
<style>
.td-heading {
	background-color: transparent !important;
	border: 0;
	color: #212529 !important;
	font-weight: bold !important;
}

.quiz_step_hide {
            display: none
}
</style>
