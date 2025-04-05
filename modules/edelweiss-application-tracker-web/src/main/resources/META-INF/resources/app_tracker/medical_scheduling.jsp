<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="in.edelweiss.common.contants.DateConstants"%>
<%@page import="in.edelweiss.common.util.DateFormatterUtil"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@ include file="/init.jsp"%>

<portlet:resourceURL var="getMultipleLoginDataURL" >
	<portlet:param value="multipleLoginData1" name="cmd" ></portlet:param>
</portlet:resourceURL>

<%

JSONObject policyFormJSONObject = (JSONObject) renderRequest.getAttribute("policyFormJSONObject");
JSONObject communicationJSONObject = (JSONObject) renderRequest.getAttribute("communicationJSONObject");
JSONObject verificationJSONObject = (JSONObject) renderRequest.getAttribute("verificationJSONObject");
Map<String, String> branchStates = new TreeMap<String, String>();
if(Validator.isNotNull(renderRequest.getAttribute("branchStates"))){
	branchStates = (TreeMap<String, String>) renderRequest.getAttribute("branchStates");
}

String policyNumberValue = Validator.isNotNull(policyFormJSONObject.getString("policy_number")) ? policyFormJSONObject.getString("policy_number") : StringPool.BLANK;
String genderValue = Validator.isNotNull(policyFormJSONObject.getString("la_gender")) ? policyFormJSONObject.getString("la_gender") : StringPool.BLANK;
String productNameValue = Validator.isNotNull(policyFormJSONObject.getString("product_name")) ? policyFormJSONObject.getString("product_name") : StringPool.BLANK;

String firstNameValue =  Validator.isNotNull(communicationJSONObject.getString("first_Name")) ? communicationJSONObject.getString("first_Name") : StringPool.BLANK;
String middleNameValue = Validator.isNotNull(communicationJSONObject.getString("middle_Name")) ? communicationJSONObject.getString("middle_Name") : StringPool.BLANK;
String lastNameValue = Validator.isNotNull(communicationJSONObject.getString("last_Name")) ? communicationJSONObject.getString("last_Name") : StringPool.BLANK;
String emailValue = Validator.isNotNull(communicationJSONObject.getString("pA_City")) ? communicationJSONObject.getString("pA_City") : StringPool.BLANK;
String panNumberValue = Validator.isNotNull(communicationJSONObject.getString("paN_Number")) ? communicationJSONObject.getString("paN_Number") : StringPool.BLANK;
String mobileNumberValue = Validator.isNotNull(communicationJSONObject.getString("mobile_Number")) ? communicationJSONObject.getString("mobile_Number") : StringPool.BLANK;
String alternateNumberValue = Validator.isNotNull(communicationJSONObject.getString("alternatePhoneNumber")) ? communicationJSONObject.getString("alternatePhoneNumber") : StringPool.BLANK;
String customerDOBValue = DateFormatterUtil.parseDateToSpecificFormat(Validator.isNotNull(communicationJSONObject.getString("customer_DOB")) ? communicationJSONObject.getString("customer_DOB") : StringPool.BLANK, DateConstants.HYPHEN_YYYY_MM_DD);
String visitTypeValue = Validator.isNotNull(communicationJSONObject.getString("visit_Type")) ? communicationJSONObject.getString("visit_Type") : StringPool.BLANK;

String iS_CA_PA_Same_YN = Validator.isNotNull(communicationJSONObject.getString("iS_CA_PA_Same_YN")) ? communicationJSONObject.getString("iS_CA_PA_Same_YN") : StringPool.BLANK;
String cityValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_City") : communicationJSONObject.getString("cA_City");
String stateValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_State") : communicationJSONObject.getString("cA_State");
String pinCodeValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_Pincode") : communicationJSONObject.getString("cA_Pincode");
String addressLineOneValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_Address_Line1") : communicationJSONObject.getString("cA_Address_Line1");
String addressLineTwoValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_Address_Line2") : communicationJSONObject.getString("cA_Address_Line2");
String addressLineThreeValue = iS_CA_PA_Same_YN.equalsIgnoreCase("Y") ? communicationJSONObject.getString("pA_Address_Line3") : communicationJSONObject.getString("cA_Address_Line3");

ArrayList<String> testList = new ArrayList<String>();
JSONArray jsonArray = communicationJSONObject.getJSONArray("tpaTest_List");
for(int i = 0; i < jsonArray.length(); i++){
	testList.add(jsonArray.getJSONObject(i).getString("test_Code"));
}
String testListString = testList.toString();
testListString = testListString.replace("[", "").replace("]", "").replace(" ", "");

Date date = Calendar.getInstance().getTime();  
DateFormat dateFormat = new SimpleDateFormat(DateConstants.SLASH_DD_MM_YYYY);  
String currentDate = dateFormat.format(date);  

boolean medicalschedulingPendingStep = (boolean)request.getAttribute("medicalschedulingPending");

%>

<script>
var proposalNoJSON = "<%=policyNumberValue%>",
    firstNameJSON = "<%=firstNameValue%>",
    middlenameJSON = "<%=middleNameValue%>",
    lastNameJSON = "<%=lastNameValue%>",
    genderJSON = "<%=genderValue%>",
    mobileNumberJSON = "<%=mobileNumberValue%>",
    alternatePhoneNumberJSON = "<%=alternateNumberValue%>",
    emailIdJSON = "<%=emailValue%>",
    addressJSON = "<%=addressLineOneValue +" "+addressLineTwoValue%>",
	areaJSON = "<%=addressLineThreeValue%>",
	cityJSON = "<%=cityValue%>",
	stateJSON = "<%=stateValue%>",
	pinCodeJSON = "<%=pinCodeValue%>",
    testNamesJSON = "<%=testListString%>",
    customerDOBJSON = "<%=customerDOBValue%>",
    productNameJSON = "<%=productNameValue%>",
    customerIDNumberJSON = "<%=panNumberValue%>",
    visitTypeJSON = "<%=visitTypeValue%>";
</script>

<div class="<%= medicalschedulingPendingStep ? "active show" : StringPool.BLANK %> tab-pane fade" id="pills-medical" role="tabpanel"
	aria-labelledby="pills-medical-tab">
	<h2><liferay-ui:message key="label-medical-scheduling" /></h2>
	<p><liferay-ui:message key="label-schedule-your-medical-appointment-our-medical-team-will-contact-you-for-further-process" /></p>
	<div class="accordion" id="accordionExample">
	<%if(Validator.isNotNull(verificationJSONObject) && Validator.isNotNull(verificationJSONObject.getString("tpaStatus")) && verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Pending")){ %>
		<div class="card">
			<div class="card-header" id="headingOne">
				<h2 class="mb-0">
					<button class="btn btn-block text-left arrow-btn" type="button"
						data-toggle="collapse" data-target="#collapseOne"
						aria-expanded="true" aria-controls="collapseOne">
						<liferay-ui:message key="label-schedule-medical-appointment-for" />
						<%=communicationJSONObject.getString("first_Name") +" "+ communicationJSONObject.getString("last_Name")%>
						<i class="fas fa-chevron-down"></i></a>
						<div class="status-wrapper">
							<p>
								<liferay-ui:message key="label-status" /> : 
								<span class="<%= verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Pending") ? "text-orange" : "" %>"><%= verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Pending") ? "Appointment Pending" : "" %></span>
							</p>
						</div>
					</button>
				</h2>
			</div>
			<div id="collapseOne" class="collapse show"
				aria-labelledby="headingOne" data-parent="#accordionExample">
				<div class="card-body">
					<div class="edto-income-plan-tiles-wrapper">
						<form name="<portlet:namespace/>medicalSchedulingForm" action=""
	  						  id="<portlet:namespace/>medicalSchedulingForm" method="POST">
							<div class="form-campign-wrapper">
								<div class="medical-test">
									<div class="radio-wrapper">
										<p><liferay-ui:message key="label-medical-tests" /> :</p>
										<div class="radio_container">
											<% if(Validator.isNotNull(communicationJSONObject.getJSONArray("tpaTest_List")) 
													&& communicationJSONObject.getJSONArray("tpaTest_List").length() > 0){
												
												JSONArray testJsonArray = communicationJSONObject.getJSONArray("tpaTest_List");
												for(int i = 0; i < testJsonArray.length(); i++){
													String testCode = testJsonArray.getJSONObject(i).getString("test_Code");
													String testName = testJsonArray.getJSONObject(i).getString("test_Name");
											%>
											<input type="radio" name="medicalTest" value="<%=testCode%>" id="<%=testCode%>">
											<label for="<%=testCode%>"><%=testName%></label> 
											<% 	} 
											   }
											%>
										</div>
									</div>
								</div>
								<h5><liferay-ui:message key="label-location-for-medical-test" /></h5>
								<div class="location-field">
									<div class="form-box">
										<div class="select-container">
											<select id="<portlet:namespace/>dcState" name="<portlet:namespace/>dcState" tabindex="-1"
												class="select2-hidden-accessible select2-dropClass validate" aria-hidden="true"
												onChange="populateLocations(this.value, 'dcCity');">
												<option value=""><liferay-ui:message key="label-select-state" /></option>
												<%
													for (Map.Entry<String, String> entry : branchStates.entrySet()) {
												%>
													<option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
												<%
													}
												%>
											</select>
										</div>
									</div>
									<div class="form-box">
										<div class="select-container">
											<select id="<portlet:namespace/>dcCity" name="<portlet:namespace/>dcCity" tabindex="-1"
												class="select2-hidden-accessible select2-dropClass validate" aria-hidden="true">
												<option value=""><liferay-ui:message key="label-select-city" /></option>
											</select>
										</div>
									</div>
									<div class="form-box">
										<label class="custom-field two"> 
										<input id="<portlet:namespace/>dcPincode" name="<portlet:namespace/>dcPincode" type="number"
										 value="<%= communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
												 	communicationJSONObject.getString("pA_Pincode") : communicationJSONObject.getString("cA_Pincode") %>" 
												 placeholder="&nbsp;" onChange="getDiagonosticCentreList(this.value)">
										<span class="placeholder"><liferay-ui:message key="label-pin-code" /></span>
										</label>
									</div>
								</div>
								<h5><liferay-ui:message key="label-preferred-test-type" /></h5>
								<div class="form-tabs-wrapper">
									<ul class="nav nav-tabs" id="smlTab" role="tablist">
										<% if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Home Visit")){%>
										<li class="nav-item">
											<a class="nav-link active" id="zindagi-tab" data-toggle="tab" href="#zindagi" role="tab"
											aria-controls="zindagi" aria-selected="true">
												<liferay-ui:message key="label-home-visit" />
											</a>
										</li>
										<%}else if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Center Visit")){ %>
										<li class="nav-item">
											<a class="nav-link active" id="bima-tab"
											data-toggle="tab" href="#bima" role="tab"
											aria-controls="bima" aria-selected="true">
												<liferay-ui:message key="label-diagonostic-centre" />
											</a>
										</li>
										<%} %>
									</ul>
									<div class="tab-content" id="smlTabContent">
									<% if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Home Visit")){%>
										<div class="tab-pane fade active show" id="zindagi"
											role="tabpanel" aria-labelledby="zindagi-tab">
											<h5><liferay-ui:message key="label-complete-address" /></h5>
											<div class="location-field">
												<div class="form-box">
													<label class="custom-field two"> 
													<input id="<portlet:namespace/>addressLineOne" name="<portlet:namespace/>addressLineOne" type="text" placeholder="&nbsp;" 
													value="<%= communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
															   communicationJSONObject.getString("pA_Address_Line1") : communicationJSONObject.getString("cA_Address_Line1") %>"> 
													<span class="placeholder">
														<liferay-ui:message key="label-address-line-1" />
													</span>
													</label>
												</div>
												<div class="form-box">
													<label class="custom-field two"> 
													<input id="<portlet:namespace/>addressLineTwo" name="<portlet:namespace/>addressLineTwo" 
															type="text" placeholder="&nbsp;"
														value="<%=  communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
												 					communicationJSONObject.getString("pA_Address_Line2") : communicationJSONObject.getString("cA_Address_Line2") %>">
													<span class="placeholder">
														<liferay-ui:message key="label-address-line-2" /></span>
													</label>
												</div>
												<div class="form-box">
													<div class="select-container">
														<select id="<portlet:namespace/>addressState" name="<portlet:namespace/>addressState" tabindex="-1"
															class="select2-hidden-accessible select2-dropClass validate" aria-hidden="true"
															onChange="populateLocations(this.value, 'addressCity');">
															<option value=""><liferay-ui:message key="label-select-state" /></option>
															<%
																for (Map.Entry<String, String> entry : branchStates.entrySet()) {
															%>
																<option value="<%=entry.getKey()%>" 
																	<%=entry.getValue().equalsIgnoreCase(communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
																		communicationJSONObject.getString("pA_State") : communicationJSONObject.getString("cA_State")) ? "selected" : StringPool.BLANK %> 
																		><%=entry.getValue()%></option>
															<%
																}
															%>
														</select>
													</div>
												</div>
												<div class="form-box">
													<div class="select-container">
														<select id="<portlet:namespace/>addressCity" name="<portlet:namespace/>addressCity" tabindex="-1"
															class="select2-hidden-accessible select2-dropClass validate" aria-hidden="true">
															<option value=""><liferay-ui:message key="label-select-city" /></option>
														</select>
													</div>
												</div>
												<div class="form-box">
													<label class="custom-field two"> 
													<input id="<portlet:namespace/>addressPincode" name="<portlet:namespace/>addressPincode" type="number" placeholder="&nbsp;" 
													value="<%=communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
												 communicationJSONObject.getString("pA_Pincode") : communicationJSONObject.getString("cA_Pincode") %>"
												 > <span
														class="placeholder"><liferay-ui:message key="label-pin-code" /></span>
													</label>
												</div>
												<div class="form-box">
													<label class="custom-field two"> 
													<input id="<portlet:namespace/>addressLandmark" name="<portlet:namespace/>addressLandmark" type="text" placeholder="&nbsp;"
														value="<%=communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") ? 
												 communicationJSONObject.getString("pA_Address_Line3") : communicationJSONObject.getString("cA_Address_Line3") %>"
												 > <span class="placeholder">
														<liferay-ui:message key="label-landmark" /></span>
													</label>
												</div>
											</div>
										</div>
										<%}else if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Center Visit")){ %>
										<div class="tab-pane fade active show" id="bima" role="tabpanel"
											aria-labelledby="bima-tab">
											<h5><liferay-ui:message key="label-choose-your-preferred-diagonostic-centre" /></h5>
											<div id="radioOptions">
												<div class="choose-preffered">
												    <div class="radio-choose">
												        <p id="default-ds">
												            <input type="radio" id="dc_345" value="345_SDAS" name="diagonosticCentre" checked="" />
												            <label for="dc_345"><liferay-ui:message key="label-default-diagonostic-centre" /></label>
												        </p>
												    </div>
												</div>
											</div>
										</div>
										<%} %>
									</div>
								</div>
								<h5><liferay-ui:message key="label-preferred-appointment-slot" /></h5>
								<div class="location-field">
									<div class="form-box">
										<label class="custom-field two"> 
										<input id="<portlet:namespace/>dateSlot" name="<portlet:namespace/>dateSlot" value="<%=currentDate%>"
											type="text" placeholder="&nbsp;" class="vdate" autocomplete="off"> <span
											class="placeholder"><liferay-ui:message key="label-date" /></span>
										</label>
									</div>
									<div class="form-box">
										<div class="select-container">
											<select id="<portlet:namespace/>timeSlot" name="<portlet:namespace/>timeSlot" tabindex="-1"
												class="select2-hidden-accessible select2-dropClass validate"
												aria-hidden="true">
												<option value=""><liferay-ui:message key="label-select-time-slot" /></option>
												<option value="10AM">10 AM</option>
												<option value="12PM">12 PM</option>
												<option value="2PM">2 PM</option>
												<option value="4PM">4 PM</option>
												<option value="6PM">6 PM</option>
												<option value="8PM">8 PM</option>
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="center-btn">
								<a class="edto-btn-primary" href="javascript:;"
								onClick="submitMedicalSchedulingForm();">
								<liferay-ui:message key="label-schedule" /></a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%}else if(Validator.isNotNull(verificationJSONObject) && Validator.isNotNull(verificationJSONObject.getString("tpaStatus")) && verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Completed")){ %>
		<div class="card">
			<div class="card-header" id="headingTwo">
				<h2 class="mb-0">
					<button class="btn btn-block text-left arrow-btn collapsed" type="button"
						data-toggle="collapse" data-target="#collapseTwo"
						aria-expanded="false" aria-controls="collapseTwo">
						<liferay-ui:message key="label-schedule-medical-appointment-for" />
						<%=communicationJSONObject.getString("first_Name") +" "+ communicationJSONObject.getString("last_Name")%>
						<i class="fas fa-chevron-down"></i></a>
						<div class="status-wrapper">
							<p>
								<liferay-ui:message key="label-status" /> : 
								<span class="text-green"><%= verificationJSONObject.getString("tpaStatus").equalsIgnoreCase("Completed") ? "Appointment Completed" : "" %></span>
							</p>
						</div>
					</button>
				</h2>
			</div>
			<div id="collapseTwo" class="collapse show" aria-labelledby="headingTwo"
				data-parent="#accordionExample">
				<div class="card-body">
					<div class="edto-income-plan-tiles-wrapper">
						<form>
							<div class="form-campign-wrapper">
								<div class="medical-test">
									<div class="radio-wrapper">
										<p><liferay-ui:message key="label-medical-tests" />:</p>
										<div class="radio_container">
											<% if(Validator.isNotNull(communicationJSONObject.getJSONArray("tpaTest_List")) 
													&& communicationJSONObject.getJSONArray("tpaTest_List").length() > 0){
												
												JSONArray testJsonArray = communicationJSONObject.getJSONArray("tpaTest_List");
												for(int i = 0; i < testJsonArray.length(); i++){
													String testCode = testJsonArray.getJSONObject(i).getString("test_Code");
													String testName = testJsonArray.getJSONObject(i).getString("test_Name");
											%>
											<input type="radio" name="medicalTest" value="<%=testCode%>" id="<%=testCode%>">
											<label for="<%=testCode%>"><%=testName%></label> 
											<% 	} 
											   }
											%>
										</div>
									</div>
								</div>
								<div class="location-filled-wrapper">
									<div class="located">
										<p><liferay-ui:message key="label-location-for-medical-test" /></p>
										<span><%=communicationJSONObject.getString("iS_CA_PA_Same_YN").equalsIgnoreCase("Y") == true ? 
												 communicationJSONObject.getString("pA_Pincode")+", "+communicationJSONObject.getString("pA_City")+" "+communicationJSONObject.getString("pA_State") : 
												 communicationJSONObject.getString("cA_Pincode")+", "+communicationJSONObject.getString("cA_City")+" "+communicationJSONObject.getString("cA_State") %>
										</span>
									</div>
									<div class="located">
										<p><liferay-ui:message key="label-preferred-test-type" /></p>
										<%if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Home Visit")){%>
										<span><liferay-ui:message key="label-home-visit" /></span>
										<%}else if(communicationJSONObject.getString("visit_Type").equalsIgnoreCase("Center Visit")){%>
										<span><liferay-ui:message key="label-diagonostic-centre" /></span>
										<%} %>
									</div>
									<p><liferay-ui:message key="label-your-appointment-completed-note" /></p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<%} %>
	</div>
</div>
<%
String createDateRr = (String) request.getAttribute("uniqueDateObj");
%>
<script>

$(document).ready(function () {
	
	$(".validate").on("change", function(event){
		$("#"+event.target.id + "-error").remove();
	});
	
	if(visitTypeJSON.toUpperCase() == "CENTER VISIT"){
		var pinCode = $("#"+portletNamespace+"dcPincode").val();
		if(pinCode){
			getDiagonosticCentreList(pinCode);
		}
	}else if(visitTypeJSON.toUpperCase() == "HOME VISIT"){
		var state = $("#"+portletNamespace+"addressState").val();
		if(state){
			populateLocations(state,"addressCity");
		}
	}
	
	$.validator.addMethod("minDate", function(value, element, required) {
		var date = new Date();
		date.setDate(date.getDate() - 1);
		var today = moment(date, 'DD/MM/YYYY').toDate();
	    var inputDate = moment(value, 'DD/MM/YYYY').toDate();
	    
	    if(inputDate > today){
	    	return true;
	    }
	    
	    return false;
	    
	}, "The Date should be current or future date");
	
	$.validator.addMethod("maxDateThirty", function(value, element, required) {
		var date = new Date();
		date.setDate(date.getDate() + 30);
		var maxDate = moment(date, 'DD/MM/YYYY').toDate();
	    var inputDate = moment(value, 'DD/MM/YYYY').toDate();
	    
	    if(inputDate < maxDate){
	    	return true;
	    }
	    
	    return false;
	    
	}, "The Date should be less than 30 day from today");
	
	initMedicalSchedulingFormValidation();
	
	function initMedicalSchedulingFormValidation(){
		
		var summaryFormRuleFields = new Object({}), summaryFormMessageFields = new Object({});
		
		summaryFormRuleFields[portletNamespace+"dcState"] = {
		    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"dcState"] = {
			    required: 'Please Select State'
		};
		
		summaryFormRuleFields[portletNamespace+"dcCity"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"dcCity"] = {
			    required: 'Please Select City'
		};
		
		summaryFormRuleFields[portletNamespace+"dcPincode"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"dcPincode"] = {
			    required: 'Please Enter Pin Code'
		};
		
		summaryFormRuleFields[portletNamespace+"addressLineOne"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressLineOne"] = {
			    required: 'Please Enter Address'
		};
		
		summaryFormRuleFields[portletNamespace+"addressLineTwo"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressLineTwo"] = {
			    required: 'Please Enter Address'
		};
		
		summaryFormRuleFields[portletNamespace+"addressState"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressState"] = {
			    required: 'Please Select State'
		};
		
		summaryFormRuleFields[portletNamespace+"addressCity"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressCity"] = {
			    required: 'Please Select City'
		};
		
		summaryFormRuleFields[portletNamespace+"addressPincode"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressPincode"] = {
			    required: 'Please Enter Pin Code'
		};
		
		summaryFormRuleFields[portletNamespace+"addressLandmark"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"addressLandmark"] = {
			    required: 'Please Enter Landmark'
		};
		
		summaryFormRuleFields[portletNamespace+"dateSlot"] = {
			    required: true,
			    minDate: true,
			    maxDateThirty:true
		};
		
		summaryFormMessageFields[portletNamespace+"dateSlot"] = {
			    required: 'Please Enter Date'
		};
		
		summaryFormRuleFields[portletNamespace+"timeSlot"] = {
			    required: true
		};
		
		summaryFormMessageFields[portletNamespace+"timeSlot"] = {
			    required: 'Please Select Time Slot'
		};
		
		$("#"+portletNamespace+"medicalSchedulingForm").validate({
			errorClass: "error",
			errorElement: "div",
			errorPlacement: function( error, element ) {
				error.appendTo(element.parent());
			},
			rules : summaryFormRuleFields,
			messages : summaryFormMessageFields,
			onfocusout: function(element) {
	           this.element(element);
	        },
	        submitHandler : function(form) {
				form.submit();
			}
		});
	}
	
});

function populateLocations(inputId, fieldName){
	
	$("#"+portletNamespace + fieldName).find('option').remove().end()
	.append("<option selected='true' value=''>City</option>");
	
	var locationFormData = new FormData();
	locationFormData.append(portletNamespace + "selectedId", inputId);
	locationFormData.append(portletNamespace + "fieldName", fieldName);
	
	Liferay.Util.fetch("<portlet:resourceURL id='populateLocations' />", {
		body: locationFormData,
		method: 'POST'
	})

	.then((response) => {
		return response.json();
	})

	.then((validationResponse) => {
		console.info(validationResponse);
		if(validationResponse != null && validationResponse.isOptionsFetched){
			
			var options = validationResponse.options;
			var citySelect = "";
			const dropdownOptions = [];
			options.forEach((option) => {
				dropdownOptions.push(
						"<option value='" + option.key + "'>" + option.value + "</option>"
				);
				
				if(cityJSON){
					if(cityJSON.toUpperCase() === option.value.toUpperCase()){
						citySelect = option.key;
					}	
				}
			})
			
			const inputField = document.getElementById(portletNamespace + fieldName);
			inputField.innerHTML = inputField.innerHTML + dropdownOptions.join('');
			
			if(citySelect != ""){
				$("[name="+portletNamespace + fieldName+"]").val(citySelect);
			}
			
		}
		else if(validationResponse != null && validationResponse.internalError){
			console.debug("false - Internal Error Occured");
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			openModal();
			return false;
		}
	})
	
	.catch(function(error) {
		console.error(error);
		$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
		$("#closeBtn").attr("onclick","closeModal(true)");
		hideLoader();
		openModal();
	});
}

function getDiagonosticCentreList(pinCode){
	
	if(visitTypeJSON.toUpperCase() == "CENTER VISIT"){
		
		showLoader();
		
		const reqJSON = JSON.stringify({"pinCode": pinCode, "testName": ""});
		
		Liferay.Util.fetch(dcListURL, {
			headers : {
				"Content-Type": "application/json"
			},
			method : "POST",
			body : reqJSON
		})

		.then((response) => {
			return response.json();
		})

		.then((validationResponse) => {
			
			console.info(validationResponse);
			
			const radioOptions = document.getElementById("radioOptions");
			radioOptions.innerHTML = "";
			
			if(validationResponse != null && validationResponse.status){
				
				if(validationResponse.responseData){
					
					var dcList = validationResponse.responseData;
					
						dcList.forEach((dc) => {
							if(dc.dcId != null && dc.dcId != "" && (Number(dc.dcId) == 0)){
								const inputTag = document.createElement("input");
								inputTag.setAttribute("id", "dc_345");
								inputTag.setAttribute("value", "345_SDAS");
								inputTag.setAttribute("type", "radio");
								inputTag.setAttribute("name", "diagonosticCentre");
								
								const labelTag = document.createElement("label");
								labelTag.setAttribute("for", "dc_345");
								labelTag.innerHTML = '<liferay-ui:message key="label-default-diagonostic-centre" />';
								
								const pTag = document.createElement("p");
								pTag.appendChild(inputTag);
								pTag.appendChild(labelTag);
								
								const radioChoose = document.createElement("div");
								radioChoose.className="radio-choose";
								radioChoose.appendChild(pTag);
								
								const choosePreffered = document.createElement("div");
								choosePreffered.className="choose-preffered";
								choosePreffered.appendChild(radioChoose);
								
								radioOptions.appendChild(choosePreffered);
							}else{
								const inputTag = document.createElement("input");
								inputTag.setAttribute("id", "dc_"+dc.dcId);
								inputTag.setAttribute("value", dc.dcId+"_"+dc.dcName);
								inputTag.setAttribute("type", "radio");
								inputTag.setAttribute("name", "diagonosticCentre");
								
								const labelTag = document.createElement("label");
								labelTag.setAttribute("for", "dc_"+dc.dcId);
								labelTag.innerHTML = dc.dcName;
								
								const pTag = document.createElement("p");
								pTag.appendChild(inputTag);
								pTag.appendChild(labelTag);
								
								const radioChoose = document.createElement("div");
								radioChoose.className="radio-choose";
								radioChoose.appendChild(pTag);
								
								const choosePreffered = document.createElement("div");
								choosePreffered.className="choose-preffered";
								choosePreffered.appendChild(radioChoose);
								
								radioOptions.appendChild(choosePreffered);
							}
						})
				}
			}else{
				const inputTag = document.createElement("input");
				inputTag.setAttribute("id", "dc_345");
				inputTag.setAttribute("value", "345_SDAS");
				inputTag.setAttribute("type", "radio");
				inputTag.setAttribute("name", "diagonosticCentre");
				
				const labelTag = document.createElement("label");
				labelTag.setAttribute("for", "dc_345");
				labelTag.innerHTML = '<liferay-ui:message key="label-default-diagonostic-centre" />';
				
				const pTag = document.createElement("p");
				pTag.appendChild(inputTag);
				pTag.appendChild(labelTag);
				
				const radioChoose = document.createElement("div");
				radioChoose.className="radio-choose";
				radioChoose.appendChild(pTag);
				
				const choosePreffered = document.createElement("div");
				choosePreffered.className="choose-preffered";
				choosePreffered.appendChild(radioChoose);
				
				radioOptions.appendChild(choosePreffered);
			}
			
			hideLoader();
		})

		.catch(function(error) {
			console.error(error);
			$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
			$("#closeBtn").attr("onclick","closeModal(true)");
			hideLoader();
			openModal();
		});
	}
}

function submitMedicalSchedulingForm(){
	console.log("inside submitMedicalSchedulingForm------:::" + proposalNoJSON );
	 var createDateRr = '<%= createDateRr%>';
		console.log("createDateRr:::" + createDateRr);
		var form1 = new FormData();
		form1.append("<portlet:namespace/>" + "policyNumberTest", proposalNoJSON);
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
	
				$("#"+portletNamespace+"medicalSchedulingForm").validate().form();
				if($("#"+portletNamespace+"medicalSchedulingForm").valid()) {
				
				showLoader();
				
				var address = addressJSON;
				var area = areaJSON;
				var city = cityJSON;
				var state = stateJSON;
				var pinCode = pinCodeJSON;
				var preferredDate = $("#"+portletNamespace+"dateSlot").val();
				var preferredTime = $("#"+portletNamespace+"timeSlot").val();
				var prefferedDCID = 0;
			    var prefferedDC = "";
			    
				if(visitTypeJSON.toUpperCase() == "HOME VISIT"){
					address = $("#"+portletNamespace+"addressLineOne").val() +" "+ $("#"+portletNamespace+"addressLineTwo").val();
					area = $("#"+portletNamespace+"addressLandmark").val();
					city = $("#"+portletNamespace+"addressCity :selected").text();
					state = $("#"+portletNamespace+"addressState :selected").text();
					pinCode = $("#"+portletNamespace+"addressPincode").val();
				}else if(visitTypeJSON.toUpperCase() == "CENTER VISIT"){
					var diagnosCentre = $('input[name="diagonosticCentre"]:checked').val();
					prefferedDCID = diagnosCentre.split("_")[0];
				    prefferedDC = diagnosCentre.split("_")[1];
				}
			    
				const reqJSON = JSON.stringify({
				"Application_Number": Number(applicationNumber),
				"ProposalNo": proposalNoJSON,
				"MasterPolicyNo": proposalNoJSON,
				"FirstName": firstNameJSON,
				"Middlename": middlenameJSON,
				"LastName": lastNameJSON,
				"Gender": genderJSON,
				"MobileNumber": mobileNumberJSON,
				"AlternatePhoneNumber": alternatePhoneNumberJSON,
				"EmailId": emailIdJSON,
				"Address": address,
				"Area": area,
				"City": city,
				"State": state,
				"Pincode": pinCode,
				"TestName": testNamesJSON,
				"PrefferedDate": preferredDate,
				"PrefferedTime": preferredTime,
				"PrefferedDCID": Number(prefferedDCID),
				"PrefferedDC": prefferedDC,
				"CustomerDOB": customerDOBJSON,
				"ProductName": productNameJSON,
				"CustomerIDNumber": customerIDNumberJSON,
				"VisitType": visitTypeJSON
			});
				
				console.log(reqJSON);
				Liferay.Util.fetch(medicalSchedulingURL, {
					headers : {
						"Content-Type": "application/json"
					},
					method : "POST",
					body : reqJSON
				})
		
				.then((response) => {
					return response.json();
				})
		
				.then((validationResponse) => {
					console.info(validationResponse);
					var responseDataJson = JSON.parse(validationResponse.responseData);
					console.debug("responseDataJson ...",responseDataJson);
					if(validationResponse != null && validationResponse.status){
						console.debug("True - Successfully Sumitted");
							$("#responseMessage").html(responseDataJson?.message);
						openModal();
					}else if(validationResponse && !validationResponse?.status){
						console.info("false - API Exception");
						$("#responseMessage").html(responseDataJson?.message);
						openModal();
						return false;
					}else{
						$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
						openModal();
					}
					
					hideLoader();
				})
				
				.catch(function(error) {
					console.error(error);
					$("#responseMessage").html('<liferay-ui:message key="label-please-try-after-some-time" />');
					$("#closeBtn").attr("onclick","closeModal(false)");
					hideLoader();
					openModal();
				});
				
			}
			}
		}).catch(function (e) {
		     loader.style.display = 'none';
		     showFailureModal('Internal Server Error');
			console.log("error...",e)
		});
	
}
</script>