<liferay-ui:section>
	<aui:row>
		<aui:col md="6">
			<aui:input name="preferences--gssProductCode--" type="text" value="${gssProductCode}" label="GSS-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-gss-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
		<aui:col md="6">
			<aui:input name="preferences--ibProductCode--" type="text" value="${ibProductCode}" label="IB-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-ib-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
		<aui:col md="6">
			<aui:input name="preferences--gisProductCode--" type="text" value="${gisProductCode}" label="GIS-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-gis-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
		<aui:col md="6">
			<aui:input name="preferences--zpProductCode--" type="text" value="${zpProductCode}" label="ZP-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-zp-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
		<aui:col md="6">
			<aui:input name="preferences--pgsProductCode--" type="text" value="${pgsProductCode}" label="PGS-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-pgs-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
		<aui:col md="6">
			<aui:input name="preferences--fspProductCode--" type="text" value="${fspProductCode}" label="FSP-Product-Code" cssClass="number">
				<aui:validator name="required" errorMessage="please-enter-fsp-product-code"/>
				<aui:validator name="custom" errorMessage="Please enter numbers only">
					function(val){
						var charCode = (val.which) ? val.which : val.keyCode;
			            if (charCode < 48 || charCode > 57) {
			                return false;
			            }else{
			            	return true;
			            }
					}
				</aui:validator>
			</aui:input>
		</aui:col>
	</aui:row>
</liferay-ui:section>