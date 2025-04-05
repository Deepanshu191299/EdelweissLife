package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * API Endpoint URL related configuration will be fetched by using the following class
 * 
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.AppTrackerAPIURLConfiguration", name = "label-application-tracker-api-url-configuration", localization = "content/Language")
public interface AppTrackerAPIURLConfiguration {

	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/proposalform/getPolicyDetails", description = "label-proposal-form-policy-details-url-description", name="label-proposal-form-policy-details-url")
	public String getProposalFormPolicyDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/get-verification-data", description = "label-verification-details-url-description", name="label-verification-details-url")
	public String getVerificationDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/Master/getData", description = "label-document-master-options-url-description", name="label-document-master-options-url")
	public String getDocumentMasterOptionsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/proposalform/savedocuments", description = "label-save-document-url-description", name="label-save-document-url")
	public String getSaveDocumentURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/proposalform/deletedocument", description = "label-delete-document-url-description", name="label-delete-document-url")
	public String getDeleteDocumentURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/proposalform/getdocumentlist", description = "label-document-fetch-url-description", name="label-document-fetch-url")
	public String getDocumentFetchURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/getCommunicationDetails", description = "label-communication-details-url-description", name="label-communication-details-url")
	public String getCommunicationsDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/get-dc-list", description = "label-dc-list-url-description", name="label-dc-list-url")
	public String getDCListURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/tpa-register-case", description = "label-tpa-register-case-url-description", name="label-tpa-register-case-url")
	public String getTPARegisterCaseURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/client-app-tracker", description = "label-client-app-tracker-url-description", name="label-client-app-tracker-url")
	public String getClientAppTrackerURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/get-dms-documents-list", description = "label-get-dms-list-url-description", name="label-get-dms-list-url")
	public String getDMSListURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/generate-download-url", description = "label-generate-download-url-description", name="label-generate-download-url")
	public String generateDownloadURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/client-search-requirements", description = "label-client-search-requirements-url-description", name="label-client-search-requirements-url")
	public String getClientSearchRequirementsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/getRequirementDetails", description = "label-requirements-details-url-description", name="label-requirements-details-url")
	public String getRequirementsDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/mark-received", description = "label-mark-received-description", name="label-mark-received-url")
	public String getMarkReceivedURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v2/services/generate-dms-upload-url", description = "label-generate-dms-upload-url-description", name="label-generate-dms-upload-url")
	public String generateDMSUploadURL();

}
