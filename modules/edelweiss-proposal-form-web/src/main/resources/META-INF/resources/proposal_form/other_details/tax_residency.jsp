<%@ include file="../../init.jsp"%>

<!-- Tax Residency Section -->
<div id="tax_fields_container" class="travel-declaration">
	<h5>
		<liferay-ui:message key="label-tax-other-details" />
	</h5>
	<div class="radio-wrapper">
		<p>
			<liferay-ui:message key="label-tax-residency" />
		</p>
		<div class="radio_container">
			<aui:input name='la_tax_residence_declaration_yn' id="la_tax_residence_declaration_yes" disabled="true" cssClass="validate requiredRadio"
				type="radio" label="" required="required" value="Y" checked="${isLaMinor? (commonDetails.nriGst == 'N'?true:false): (commonDetails.nriGst == 'N'?true:false) }">
			</aui:input>
			<label for="<portlet:namespace/>la_tax_residence_declaration_yes" class="h-auto">
				<liferay-ui:message key="label-india-resident" />
			</label>
			<aui:input name='la_tax_residence_declaration_yn' id="la_tax_residence_declaration_no" disabled="true" cssClass="validate requiredRadio"
				type="radio" label="" required="required" value="N" checked="${isLaMinor? (commonDetails.nriGst == 'Y'?true:false):(commonDetails.nriGst =='Y'?true:false) }">
			</aui:input>
			<label for="<portlet:namespace/>la_tax_residence_declaration_no" class="h-auto">
				<liferay-ui:message key="label-other-then-india-resident" />
			</label>
		</div>
		<label class="custom-field two has-error requiredRadioMessage" id="<portlet:namespace/>la_tax_residence_declaration_yn_required" 
			style="display: none;">
			<div class="form-feedback-item form-validator-stack help-block">
				<div role="alert" class="required">
					<liferay-ui:message key="this-field-is-required" />
				</div>
			</div>
		</label>
	</div>
</div>
<!-- Tax Residency Section Ends Here-->
