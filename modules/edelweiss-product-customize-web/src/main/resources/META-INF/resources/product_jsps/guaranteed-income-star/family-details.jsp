
<div class="modal wealth-modal" tabindex="-1" role="dialog" id="<portlet:namespace/>familyModal" style="display: none;">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
						<div class="modal-header">
				<h5 class="fs22 fontbold"><liferay-ui:message key="investing-for-family-modal-title" /></h5>
				<button type="button" id="<portlet:namespace/>familyModalClose" class="close" data-dismiss="modal" aria-label="Close" style="margin: -1rem -1rem -1rem auto;">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
				 <form name="<portlet:namespace/>familyDetailsForm" id="<portlet:namespace/>familyDetailsForm" method="POST">
					<div class="row">
						<div class="col-md-12 col-12">
							<label><liferay-ui:message key="who-do-you-want-to-invest-for" /></label>						
							<div class="radio_container" id="investingForWrapper">								
								<input type="radio" data-gender="Male" data-name="Son" value="Son" name="<portlet:namespace/>familyRadio" id="<portlet:namespace/>investingForSon" checked="checked">
								<label for="<portlet:namespace/>investingForSon"><liferay-ui:message key="son" /></label>
							
								<input type="radio" data-gender="Female" data-name="Daughter" value="Daughter" name="<portlet:namespace/>familyRadio" id="<portlet:namespace/>investingForDaughter">
								<label for="<portlet:namespace/>investingForDaughter"><liferay-ui:message key="daughter" /></label>
							</div>
						</div>
						<div class="col-md-6 col-12">
                        <div class="input-box"> 
								<label for="<portlet:namespace/>investingForFullName" id="<portlet:namespace/>investingForFullNameLable"><liferay-ui:message key="sons-full-name" /></label> 
								<input class="form-control" type="text" name="<portlet:namespace/>investingForFullName" 
									id="<portlet:namespace/>investingForFullName" 
									value="${basicDetailsMap['basicInvestingFor'] == 'Family' ? basicDetailsMap['assuranceFullName'] : ''}"
									oninput="this.value = this.value.replace(/[^a-zA-Z ]/g, '').replace(/(\..*)\./g, '$1');" 
									onblur="if (this.value == '') {this.value = '';}" onfocus="if (this.value == '') {this.value = '';}">
							<span class="underline"></span>
							</div>
						</div>
						<div class="col-md-6 col-12">
                        <div class="input-box"> 
								<label for="<portlet:namespace/>investingForDOB" id="<portlet:namespace/>investingForDOBLabel"><liferay-ui:message key="sons-dob" /></label><br/> 
								<input class="form-control edelweiss-mask-date" type="text" name="<portlet:namespace/>investingForDOB" id="<portlet:namespace/>investingForDOB" value="${basicDetailsMap['basicInvestingFor'] == 'Family' ? basicDetailsMap['assuranceDob'] : ''}">
							<span class="underline"></span>
							</div>
						</div>
					</div>
					<div class="center-wealth-btn">
					<input type="submit" id="<portlet:namespace />saveFamilyBtn" value="Save" class="edto-btn-primary">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- Little Champ Modal For Wealth Rise Plus Product --%>
<c:if test="${productMetaData['productName'] == 'Wealth Rise Plus'}">
	<div class="modal wealth-modal" tabindex="-1" role="dialog" id="<portlet:namespace/>littleChampModal" style="display: none;">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close" id="<portlet:namespace/>littleChampModalClose">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="wealth-form-box">
					<div class="text-center">
						<h3 class="fs22 fontbold  w-100"><liferay-ui:message key="congratulations" /></h3>
						<input type="hidden" class="form-control" id="<portlet:namespace />isLittleChampBenefitOpted" name="<portlet:namespace />isLittleChampBenefitOpted" value="${basicDetailsMap['isLittleChampBenefitOpted']}">
	                    <h4 class="my-2"><liferay-ui:message key="you-are-eligible-for-little-champ-benefit" /></h4>
	                    <h4 class="my-2"><liferay-ui:message key="benefits-of-little-champ" /></h4>
	                </div>   
					<div class="my-4">
						<ul class="edto-bullet1">
							<li><liferay-ui:message key="little-champ-benefit1" /></li>
							<li><liferay-ui:message key="little-champ-benefit2" /></li>
							<li><liferay-ui:message key="little-champ-benefit3" /></li>
	                    </ul>
					</div>
	                    <div class="center-wealth-btn text-center">
							<a href="javascript:void(0);" class="edto-btn-primary" id="<portlet:namespace/>addLittleChampBenefit"><liferay-ui:message key="add" /></a>
						</div>
						<div class="text-center my-2">
							<a href="javascript:void(0);" id="<portlet:namespace/>doNotaddOn"><liferay-ui:message key="dont-want-this-add-on" /></a>
						</div>
	                </div>
				</div>
			</div>
		</div>
	</div>
</c:if>

<%--
<script type="text/javascript">
$("#<portlet:namespace />saveFamilyBtn").click(function() {
		
		var eventname = "Customize Page";
	    var returnurl = window.location.href;
		
	    var productName = "${productMetaData['productName']}";
		console.log("product-details::",productName);
		
		var childName = "";
		var childDOB = "";
		
		childName = $('#<portlet:namespace />investingForFullName').val();
		childDOB = $('#<portlet:namespace />investingForDOB').val();

		webengage.track(eventname, {
	 	    "Product_Name" : productName,
	    	"Child Name" : childName,
	 	   	"Child DOB" : childDOB,
	 	    "URL": returnurl
	       });	
	});

</script>
--%>