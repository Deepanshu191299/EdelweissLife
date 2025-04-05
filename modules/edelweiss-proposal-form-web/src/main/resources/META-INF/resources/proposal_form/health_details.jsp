<%@page import="in.edelweiss.proposal.form.pf.model.CommonData"%>
<%@page import="in.edelweiss.proposal.form.pf.model.LifeStyleDetails"%>
<%@page import="in.edelweiss.common.contants.DateConstants"%>
<%@ include file="../init.jsp"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%-- 
<%	
	CommonData commonData = (CommonData) request.getAttribute("commonDetails");
	LifeStyleDetails healthDetail = (LifeStyleDetails) request.getAttribute("lifeStyleDetails");
	//healthDetail.getLaDetails().getHeightFeet()
%> --%>
<%
LifeStyleDetails lifeStyleDetails = (LifeStyleDetails)request.getAttribute("lifeStyleDetails");

   if(lifeStyleDetails.getProposerDetails()!=null){
		renderRequest.setAttribute("insurerDetails", lifeStyleDetails.getProposerDetails());
   }
   else if(lifeStyleDetails.getSpouseDetails()!=null){
	   renderRequest.setAttribute("insurerDetails",lifeStyleDetails.getSpouseDetails());
   }

%>


<div class="card">
	<div class="card-header" id="headingTwo">
		<!--<h2 class="mb-0 title-tabs">
			<span><img
				src="/o/edelweisstokio-theme/images/praposal/tick.png" alt="icon"></span>
			<liferay-ui:message key="label-health-details" />
		</h2>-->
		<h2 class="mb-0 title-tabs">
			<button class="btn btn-link text-left tab-btn collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			   <p><liferay-ui:message key="label-health-details" /></p>
			</button>
		 </h2>
	</div>
	<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
		data-parent="#accordionExample">
		<div class="card-body">
			<div class="edto-income-plan-tiles-wrapper">
				<aui:form name="health_details_form" action="hello" method="post"
					enctype="multipart/form-data" onSubmit="event.preventDefault();">
					<div id='<portlet:namespace/>health_details_step'>
						<liferay-util:include
							page="/proposal_form/health_details/height_weight_details.jsp"
							servletContext="<%=application%>" />
						<liferay-util:include
							page="/proposal_form/health_details/lifestyle_details.jsp"
							servletContext="<%=application%>" />
						<liferay-util:include
							page="/proposal_form/health_details/medical_details.jsp"
							servletContext="<%=application%>" />
					</div>
					
					<a class="edto-btn-primary mt-3" href="javascript:;" 
						onClick="saveHealthDetails('health_details_step', true, 'health_details_form',true);">
							Save & Continue
					</a>
					
				</aui:form>
			</div>
		</div>
	</div>
</div>

 <script src="<%=request.getContextPath()%>/js/health_details.js?t=<%=DateConstants.CURRENT_TIME_STAMP%>"></script>
<script>

	$('#<portlet:namespace/>addPrMedicalHistory').on('click', function() {
		console.log('I am getting clicked');
		var ifRadioBtncheckedPr = $('#medical-details-type2 input.pr-sp-check:checked').val();
		if(ifRadioBtncheckedPr === 'N'){
			$('.medical-table-error-pr').addClass('hide');
		}
		var varPrTableData = document.getElementById("pr_table_data").rows.length;
		console.log("1:", varPrTableData);
		$('#medical-details-type2 input.pr-sp-check').each(function() {
			if ($(this).is(":checked") && $(this).val() === "Y") {
				if (varPrTableData < 1) {
					console.log("2:", varPrTableData);
					$('.medical-table-error-pr').removeClass('hide');
				}else{
					console.log("3:", varPrTableData);
					$('.medical-table-error-pr').addClass('hide');
				}
			}
		})
});

$('#medical-details-type2 input.pr-sp-check').on('change', function() {
    var allUncheckedN = true;

    $('#medical-details-type2 input.pr-sp-check').each(function() {
        if ($(this).is(":checked") && $(this).val() === "Y") {
            var varLaTableData = document.getElementById("pr_table_data").rows.length;
            if (varLaTableData <= 1) {
                $('.medical-table-error-pr').removeClass('hide');
                allUncheckedN = false;
                return false; // Exit the loop early
            }
        }
    });

    if (allUncheckedN) {
        $('.medical-table-error-pr').addClass('hide');
    }
});


// Use event delegation to handle click events on dynamically added elements
$('#medicalHistoryTable_PS').on('click', '.removeEntry', function() {
	console.log('delete is clicked');
    var ifRadioBtncheckedDataBtn = $('#medical-details-type2 input.pr-sp-check:checked').val();
    if (ifRadioBtncheckedDataBtn === 'N') {
        $('.medical-table-error-pr').addClass('hide');
    }
    
    $('#medical-details-type2 input.pr-sp-check').each(function() {
        if ($(this).is(":checked") && $(this).val() === "Y") {
            var varLaTableData = document.getElementById("pr_table_data").rows.length;
            if (varLaTableData <= 2) {
                $('.medical-table-error-pr').removeClass('hide');
            } else {
                $('.medical-table-error-pr').addClass('hide');
            }
        }
    });
});

	// showing the div for all clicking

	function showDiv(display, divId) {
		if (display) {
			document.getElementById(divId).style.display = 'block';
		} else {
			document.getElementById(divId).style.display = 'none';
		}
	}
	
	//Add details to table for LA
	$('#<portlet:namespace/>addLaMedicalHistory').on('click', function() {
		var disease_name=$("input[name='<portlet:namespace/>la_diseaseName']").val();
		var diagnosis_date=$("input[name='<portlet:namespace/>la_diagnosisDate']").val();
		var treatment_details=$("input[name='<portlet:namespace/>la_treatmentDetails']").val();
		var dosage_details=$("input[name='<portlet:namespace/>la_dosageDetails']").val();
		var doctor_name=$("input[name='<portlet:namespace/>la_doctorName']").val();
		var further_test_date=$("input[name='<portlet:namespace/>la_furtherTestDate']").val();
		var any_complications=$("input[name='<portlet:namespace/>la_anyComplications']").val();
		var additional_remarks=$("input[name='<portlet:namespace/>la_additionalRemarks']").val();
		var count = $('#medicalHistoryTable_LA tr').length;
		if(disease_name!="" && diagnosis_date !="" && treatment_details!="" && dosage_details!="" && doctor_name!="" && further_test_date !="" && any_complications!="" && additional_remarks!=""){
			showLa.style.display="block";
			$('#medicalHistoryTable_LA tbody').append('<tr class="child"><td>'+disease_name+'</td><td>'+diagnosis_date+'</td><td>'+treatment_details+'</td><td>'+dosage_details+'</td><td>'+doctor_name+'</td><td>'+further_test_date+'</td><td>'+any_complications+'</td><td>'+additional_remarks+'</td><td><button class="removeEntry badge w-100 d-block la"><i class="far fa-trash-alt"></i></button></td></tr>');
			$('.la_medicalHistoryForm').find('input').val('');
		}
	});
	$('#<portlet:namespace/>addLaMedicalHistory').on('click', function() {
		var ifRadioBtncheckedDataBtn = $('#medical-details-type2 input.la-check:checked').val();
		if(ifRadioBtncheckedDataBtn === 'N'){
			$('.medical-table-error-la').addClass('hide');
		}
	$('#medical-details-type2 input.la-check').each(function() {
		if ($(this).is(":checked") && $(this).val() === "Y") {
				var varLaTableData = document.getElementById("la_table_data").rows.length;
				if (varLaTableData <= 1) {
		    		    $('.medical-table-error-la').removeClass('hide');
		    		}else{
		    			$('.medical-table-error-la').addClass('hide');
		    		}
		}
		});
	});
	
	$('#medical-details-type2 input.la-check').on('change', function() {
	    var allUncheckedN = true;

	    $('#medical-details-type2 input.la-check').each(function() {
	        if ($(this).is(":checked") && $(this).val() === "Y") {
	            var varLaTableData = document.getElementById("la_table_data").rows.length;
	            if (varLaTableData <= 1) {
	                $('.medical-table-error-la').removeClass('hide');
	                allUncheckedN = false;
	                return false; // Exit the loop early
	            }
	        }
	    });

	    if (allUncheckedN) {
	        $('.medical-table-error-la').addClass('hide');
	    }
	});

	
	// Use event delegation to handle click events on dynamically added elements
	$('#mediacal_history_table_LA').on('click', '.removeEntry', function() {
	    var ifRadioBtncheckedDataBtn = $('#medical-details-type2 input.la-check:checked').val();
	    if (ifRadioBtncheckedDataBtn === 'N') {
	        $('.medical-table-error-la').addClass('hide');
	    }
	    
	    $('#medical-details-type2 input.la-check').each(function() {
	        if ($(this).is(":checked") && $(this).val() === "Y") {
	            var varLaTableData = document.getElementById("la_table_data").rows.length;
	            if (varLaTableData <= 2) {
	                $('.medical-table-error-la').removeClass('hide');
	            } else {
	                $('.medical-table-error-la').addClass('hide');
	            }
	        }
	    });
	});

	
	
	//Add details to table for PR/SP
	$('#<portlet:namespace/>addPrMedicalHistory').on('click', function() {
		var disease_name=$("input[name='<portlet:namespace/>proposer_diseaseName']").val();
		var diagnosis_date=$("input[name='<portlet:namespace/>proposer_diagnosisDate']").val();
		var treatment_details=$("input[name='<portlet:namespace/>proposer_treatmentDetails']").val();
		var dosage_details=$("input[name='<portlet:namespace/>proposer_dosageDetails']").val();
		var doctor_name=$("input[name='<portlet:namespace/>proposer_doctorName']").val();
		var further_test_date=$("input[name='<portlet:namespace/>proposer_furtherTestDate']").val();
		var any_complications=$("input[name='<portlet:namespace/>proposer_anyComplications']").val();
		var additional_remarks=$("input[name='<portlet:namespace/>proposer_additionalRemarks']").val();
		var count = $('#medicalHistoryTable_PS tr').length;
		if(disease_name!="" && diagnosis_date !="" && treatment_details!="" && dosage_details!="" && doctor_name!="" && further_test_date !="" && any_complications!="" && additional_remarks!=""){
			showPa.style.display="block";
			$('#medicalHistoryTable_PS tbody').append('<tr class="child"><td>'+disease_name+'</td><td>'+diagnosis_date+'</td><td>'+treatment_details+'</td><td>'+dosage_details+'</td><td>'+doctor_name+'</td><td>'+further_test_date+'</td><td>'+any_complications+'</td><td>'+additional_remarks+'</td><td><button class="removeEntry badge w-100 d-block pr"><i class="far fa-trash-alt"></i></button></td></tr>');
			$('.pr_medicalHistoryForm').find('input').val('');
		}
	});
	
	//Remove Details
	$(document).on('click','.removeEntry',function(){
		$(this).parent().parent().remove();
	});
	
	$(document).on('click','.la',function(){
		if ($("#medicalHistoryTable_LA tbody tr").not("thead tr").length > 0) {
			showLa.style.display="block";
		}
		else{
			showLa.style.display="none";
		}
	});
	
	$(document).on('click','.pr',function(){
		if ($("#medicalHistoryTable_PS tbody tr").not("thead tr").length > 0) {
			showPa.style.display="block";
		}
		else{
			showPa.style.display="none";
		}
	});
	//set all as no button
	$("#set-all-no").on("click", function() {
        if ($(this).prop("checked")) {
        	 $(".setAll1 input[type='radio'][value='N']:not(:disabled)").prop("checked", true); 
        	$('.setClose:checked').each(function() {
                $(this).click();
            });
        	$('.setClose:checked').each(function() {
                $(this).change();
            });
        }
    });
	
	$("#set-all-no1").on("click", function() {
        if ($(this).prop("checked")) {
        	 $(".setAll2 input[type='radio'][value='N']:not(:disabled)").prop("checked", true); 
        	 $('.setClose1:checked').each(function() {
                 $(this).click();
             });
         	$('.setClose1:checked').each(function() {
                 $(this).change();
             });
        }
    });
	
	$(document).ready(function() {
        $('.setOpen:checked').each(function() {
            $(this).click();
        });
    });
	
	$('.setOff').on("click",function() {
		var setCheck = document.getElementById('set-all-no');
		setCheck.checked=false;
    });
	$('.setOff1').on("click",function() {
		var setCheck1 = document.getElementById('set-all-no1');
		setCheck1.checked=false;
    });
</script>
