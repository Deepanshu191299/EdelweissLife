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
@Meta.OCD(id = "in.edelweiss.system.configurations.ProposalFormAPIURLConfiguration", name = "label-proposap-form-api-url-configuration", localization = "content/Language")
public interface ProposalFormAPIURLConfiguration {

	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/Master/getData", description = "label-master-data-url-description", name="label-master-data-url")
	public String getMasterDataURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/getCompanyList", description = "label-company-list-url-description", name="label-company-list-url")
	public String getCompanyListURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/proposalform/getPFDetails", description = "label-pf-details-url-description", name="label-pf-details-url")
	public String getPFDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/proposalform/savePFDetails", description = "label-save-pf-details-url-description", name="label-save-pf-details-url")
	public String getSavePFDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/get-ckyc", description = "label-get-c-kyc-url-description", name="label-get-c-kyc-url")
	public String getCKycDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/post-ckyc", description = "label-post-c-kyc-url-description", name="label-post-c-kyc-url")
	public String getPostCKycDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/sendOTP", description = "label-send-otp-url-description", name="label-send-otp-url")
	public String getSendOTPURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/verifyOTP", description = "label-validate-otp-url-description", name="label-validate-otp-url")
	public String getValidateOTPURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/createPaymentLink", description = "label-create-payment-link-url-description", name="label-create-payment-link-url")
	public String getCreatePaymentLinkURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/get-bank-detail", description = "label-bank-details-ifsc-url-description", name="label-bank-details-ifsc-url")
	public String getBankDetailsIFSCURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/proposalform/savethankyou", description = "label-save-thankyou-url-description", name="label-save-thankyou-url")
	public String getSavethankyouURL();
	
	@Meta.AD(required = false, deflt = "20000", description = "label-auto-save-interval-description", name="label-auto-save-interval")
	public int getAutoSaveIntervalURL();
	
	@Meta.AD(required = false, deflt = "120000", description = "label-auto-save-total-interval-description", name="label-auto-save-total-interval")
	public int getAutoSaveTotalIntervalURL();
	
	@Meta.AD(required = false, deflt = "false", description = "label-display-abha-no-field-description", 
			name="label-display-abha-no-field", 
			optionLabels = {"Yes", "No"}, 
			optionValues = {"true", "false"})
	public boolean getDisplayABHANoField();
	
	//E-KYC get link
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/get-ekyc-link/generate-ekyc-link", description = "label-get-e-kyc-link-wrapper-url-description", name="label-get-e-kyc-link-wrapper-url")
	public String getEKycLinkURL();
	
	@Meta.AD(required = false, deflt = "TBsat3wXdb1qhM4sRT3Sa6f8Pe5tQTad1UK7LibF", description = "label-get-e-kyc-link-wrapper-xapikey", name="label-get-e-kyc-link-xapikey")
	public String getEKycLinkXAPIKEY();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/oauth2/token", description = "label-get-e-kyc-oauth-url", name="label-get-e-kyc-oauth-url")
	public String getEKycOAuthURL();
	
	@Meta.AD(required = false, deflt = "13sej7smuiuiov55ecsntgvvp7", description = "label-get-e-kyc-link-wrapper-ci", name="label-get-e-kyc-link-clientID")
	public String getEKycLinkClientID();
	
	@Meta.AD(required = false, deflt = "nd51isdlla2bol8tkr62aorte21mug1nchl6c0s0jgij2tojtfq", description = "label-get-e-kyc-link-wrapper-sk", name="label-get-e-kyc-link-secretKey")
	public String getEKycLinkSecretKet();
	
	//E-KYC Details Post to liferay DB
	@Meta.AD(required = false, deflt = "https://uat.edelweisslife.in/o/c/ekycdetailses/", description = "label-get-e-kyc-internal-url-api-description", name="label-get-e-kyc-internal-api-url")
	public String getEKycInternalApiURL();
	
	//E-KYC Post to Pragathi DB
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/postEKYCDetails", description = "label-post-e-kyc-details-wrapper-url-description", name="label-post-e-kyc-details-wrapper-url")
	public String getEkycDetailsPostURL();
	
	// CIS Post Document
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/proposalform/postcisdocument", description = "label-pf-details-url-description", name="label-pf-details-url")
	public String getPostCisDocument();
	
	
	
}
