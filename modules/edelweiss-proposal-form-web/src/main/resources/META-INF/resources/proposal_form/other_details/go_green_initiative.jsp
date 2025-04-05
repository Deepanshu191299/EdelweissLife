<%@ include file="../../init.jsp"%>

<!-- Go Green Initiative Details -->
<div id="go_green_initiative_fields_container" class="medical-test">
	<input type="hidden" id="sendCopyVal" value="${commonDetails.sendCopySh == '' || commonDetails.sendCopySh == null ? 'S' : commonDetails.sendCopySh}"/>
    <div class="radio-wrapper">
        <div class="location-field">
            <h4>
                <liferay-ui:message key="label-go-green-initiative-details" />
            </h4>
            <p>
                <liferay-ui:message key="label-need-physical-copy" />
            </p>
            <div class="radio-wrapper">
                <div class="radio_container">
                    <aui:input name='go_green_initiative_yn' id="go_green_initiative_yes" cssClass="validate hasDependentFields requiredRadio la_radioYes"
                        type="radio" label="" required="true" value="H">
                    </aui:input>
                    <label for="<portlet:namespace/>go_green_initiative_yes">
                        <liferay-ui:message key="label-yes" />
                    </label>
                    
                    <aui:input name='go_green_initiative_yn' id="go_green_initiative_no" cssClass="validate hasDependentFields requiredRadio la_radioNo"
                        type="radio" label="" required="true" value="S">
                    </aui:input>
                    <label for="<portlet:namespace/>go_green_initiative_no">
                        <liferay-ui:message key="label-no" />
                    </label>
                    
                    <label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>go_green_initiative_yn_required" 
                    style="display: none;">
                        <div class="form-feedback-item form-validator-stack help-block">
                            <div role="alert" class="required">
                                <liferay-ui:message key="this-field-is-required" />
                            </div>
                        </div>
                    </label>
                </div>
                
                <div class="row">
                    <div id="goGreenIntiativeNO" class="col-md-6 col-12" style="display: none;">
                        <div class="form-box">
                            <p>
                                <strong>Note : </strong>Thank you for choosing a soft copy and supporting our Go Green initiative! 
                                Your soft copy will be sent to your email and mobile with a download URL. 
                                It's equivalent to a physical copy and suffices for all policy needs.
                            </p>
                            <br />
                        </div>
                    </div>
                    
                    <div id="goGreenIntiativeYes" class="col-md-6 col-12" style="display: none;">
                        <div class="form-box">
						    <span class="text-danger">Think Again!</span>
	                       	<p>Not supporting 'Go-Green'? Let's work together to make a difference.</p>
						</div>
                    </div>
                </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	checkRadioSelection();
});

$("input[name=<portlet:namespace/>go_green_initiative_yn]").change(function(){
	var goGreenFieldOneVal = $(this).val();
	$("#sendCopyVal").val(goGreenFieldOneVal);
	checkRadioSelection();
});

$("input[name=<portlet:namespace/>are_you_sure_go_green_initiative_yn]").change(function(){
	var goGreenFieldTwoVal = $(this).val();
	$("#sendCopyVal").val(goGreenFieldTwoVal);
});

function goGreenHandleFieldSelect(checkedClassName){
	//checked the field
	$("#<portlet:namespace/>"+checkedClassName).prop("checked",true);
}

function checkRadioSelection(){
	var sendCopyVal = $('#sendCopyVal').val();
	
	if(sendCopyVal == ''){
		sendCopyVal = 'S';
	}
	
	console.log("sendCopyVal :::: "+sendCopyVal);
	if(sendCopyVal === 'H'){
		$("#goGreenIntiativeYes").show();
		$("#goGreenIntiativeNO").hide();
		goGreenHandleFieldSelect("go_green_initiative_yes");
	}else{
		$("#goGreenIntiativeNO").show();
		$("#goGreenIntiativeYes").hide();
		goGreenHandleFieldSelect("go_green_initiative_no");
	}
}
</script>
