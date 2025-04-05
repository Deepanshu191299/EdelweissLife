
<div class="modal wealth-modal" tabindex="-1" role="dialog" id="<portlet:namespace/>childSpouseIndividualModal" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h5 class="fs22 fontbold" id="childSpouseHeader"><liferay-ui:message key="your-spouses-full-details" /></h5>
					<button type="button" id="childSpouseIndividualModalClose" class="close" data-dismiss="modal" aria-label="Close" style="margin: -1rem -1rem -1rem auto;">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				 <form name="<portlet:namespace/>childSpouseIndividualDetailsForm" id="<portlet:namespace/>childSpouseIndividualDetailsForm" method="POST">
				 	
					<div class="row">
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>spouseChildFullName"><liferay-ui:message key="sons-full-name" /></label> 
									<input class="form-control edelweiss-allow-aplha-space" type="text" name="<portlet:namespace/>spouseChildFullName" 
										id="<portlet:namespace/>spouseChildFullName" value="" oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
								<span class="underline"></span>
							</div>
						</div>
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>spouseChildDOB"><liferay-ui:message key="sons-dob" /></label><br/> 
									<input class="form-control edelweiss-mask-date" type="text" name="<portlet:namespace/>spouseChildDOB" id="<portlet:namespace/>spouseChildDOB" value="">
								<span class="underline"></span>
							</div>
						</div>
					</div>
					<div class="center-wealth-btn">
						<input type="submit" id="<portlet:namespace />saveIndividualFamilyButton" value="Save" class="edto-btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<div class="modal wealth-modal" tabindex="-1" role="dialog" id="<portlet:namespace/>childSpouseJointModal" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<div class="modal-header">
					<h5 class="fs22 fontbold"><liferay-ui:message key="enter-your-child-and-spouses-full-details" /></h5>
					<button type="button" id="childSpouseJointModalClose" class="close" data-dismiss="modal" aria-label="Close" style="margin: -1rem -1rem -1rem auto;">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				 <form name="<portlet:namespace/>childSpouseJointDetailsForm" id="<portlet:namespace/>childSpouseJointDetailsForm" method="POST">
				 
					<div class="row">
						<div class="col-12"><liferay-ui:message key="your-spouses-full-details" /></div>
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>spouseFullName"><liferay-ui:message key="name-child-spouse-label" /></label> 
									<input class="form-control edelweiss-allow-aplha-space" type="text" name="<portlet:namespace/>spouseFullName" 
									id="<portlet:namespace/>spouseFullName" value="${customerFamilyDetailsRel.getSpouseName()}"
									oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
								<span class="underline"></span>
							</div>
						</div>
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>spouseDOB"><liferay-ui:message key="your-spouses-date-of-birth" /></label><br/> 
									<input class="form-control edelweiss-mask-date" type="text" name="<portlet:namespace/>spouseDOB" id="<portlet:namespace/>spouseDOB" value="${customerFamilyDetailsRel.getSpouseDateOfBirth()}">
								<span class="underline"></span>
							</div>
						</div>
						<div class="col-12"><liferay-ui:message key="your-childs-full-details" /></div>
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>childFullName"><liferay-ui:message key="name-child-spouse-label" /></label> 
									<input class="form-control edelweiss-allow-aplha-space" type="text" name="<portlet:namespace/>childFullName" 
									id="<portlet:namespace/>childFullName" 
									value="${summaryData.customerInvestmentDetailsRel.size() > 0 && summaryData.customerInvestmentDetailsRel[0].investingFor == 'Myself' ? customerFamilyDetailsRel.getAssuranceFullName() : ''}"
									oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');">
								<span class="underline"></span>
							</div>
						</div>
						<div class="col-md-6 col-12">
	                        <div class="input-box"> 
									<label for="<portlet:namespace/>childDOB"><liferay-ui:message key="your-childs-date-of-birth" /></label><br/> 
									<input class="form-control edelweiss-mask-date" type="text" name="<portlet:namespace/>childDOB" id="<portlet:namespace/>childDOB" value="${summaryData.customerInvestmentDetailsRel.size() > 0 && summaryData.customerInvestmentDetailsRel[0].investingFor == 'Myself' ? customerFamilyDetailsRel.getAssuranceDob() : ''}">
								<span class="underline"></span>
							</div>
						</div>
					</div>
					<div class="center-wealth-btn">
						<input type="submit" id="<portlet:namespace />saveJointFamilyButton" value="Save" class="edto-btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%--
<script type="text/javascript">
$("#<portlet:namespace />saveJointFamilyButton").click(function() {
		
		var eventname = "Customize Page";
	    var returnurl = window.location.href;
		
	    var productName = "${productMetaData['productName']}";
		console.log("product-details::",productName);
		
		var childName = "";
		var childDOB = "";
		var spouseName = "";
		var spouseDOB = "";

		childName = $('#<portlet:namespace />childFullName').val();
		childDOB = $('#<portlet:namespace />childDOB').val();
		spouseName = $('#<portlet:namespace />spouseFullName').val();
		spouseDOB = $('#<portlet:namespace />spouseDOB').val();
		
		webengage.track(eventname, {
	 	    "Product_Name" : productName,
	    	"Child Name" : childName,
	 	   	"Child DOB" : childDOB,
	 	   	"Spouse Name" : spouseName,
	 	   	"Spouse DOB" : spouseDOB,
	 	    "URL": returnurl
	       });	
	});

</script>
--%>