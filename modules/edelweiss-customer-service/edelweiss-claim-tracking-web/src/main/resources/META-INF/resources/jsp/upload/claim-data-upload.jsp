<%@ include file="/init.jsp"%>

<portlet:resourceURL id="/tracking/upload/excel" var="uploadExcelDataResourceURL"/>
	
<div class="fieldWrap">
	<div class="application_step" id='<portlet:namespace/>claim_tracking_step'>
		<div class="edto-document-sub-tabs-wrapper pt-0">
			<div class="container">
				<div class="document-tabs-wrapper">
					<div class="doc-tab-body">
						<div>
							<h2 class="fontbold fs28 text-center pt-0 pb-3">
								<liferay-ui:message key="label-upload-track-claim-data" />
							</h2>
						</div>
						<div class="tracking-form-wrapper p-md-5">
							<form id="uploadForm" enctype="multipart/form-data" class="w-100">
								<input type="file" id="fileInput" name="file" accept=".xls,.xlsx" required />
								<div id="responseMessage"></div>
								<div class="d-flex justify-content-between">
									<aui:button cssClass="edto-btn-primary" id="uploadSubmit" value="Upload" />
									<aui:button cssClass="edto-btn-primary" id="downloadTemplate" value="Download Template"/>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
    $(document).ready(function () {        
        $("#<portlet:namespace/>uploadSubmit").click(function (e) {
            e.preventDefault();
            var fileInput = $("#fileInput")[0].files[0];
            
            if (!fileInput) {
                $("#responseMessage").html("<p style='color:red;'>Please select a file.</p>");
                return;
            }
            
            var allowedExtensions = ["xls", "xlsx"];
            var fileExtension = fileInput.name.split('.').pop().toLowerCase();
            
            if (!allowedExtensions.includes(fileExtension)) {
                $("#responseMessage").html("<p style='color:red;'>Only Excel files (.xls, .xlsx) are allowed.</p>");
                return;
            }
            
            var formData = new FormData();
            formData.append("<portlet:namespace/>excelFile", fileInput);

            $.ajax({
                url: '${uploadExcelDataResourceURL}',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                	//response = JSON.parse(response);
                	console.log("response ++++ "+response);
                	if(response.status){
                		$("#responseMessage").html("<p style='color:green;'>"+response.message+"</p>");
                		location.reload();
                	}else{
                		$("#responseMessage").html("<p style='color:red;'>"+response.message+"</p>");
                	}
                    
                },
                error: function () {
                    $("#responseMessage").html("<p style='color:red;'>Upload failed!</p>");
                }
            });
        });
    });
</script>