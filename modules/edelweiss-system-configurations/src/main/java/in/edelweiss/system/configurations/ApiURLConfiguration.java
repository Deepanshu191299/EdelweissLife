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
@Meta.OCD(id = "in.edelweiss.system.configurations.ApiURLConfiguration", name = "label-customer-service-api-url-configuration", localization = "content/Language")
public interface ApiURLConfiguration {

	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/oauth2/token", description = "label-oauth-url-description", name="label-oauth-url")
	public String getOAuthURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/becomeAdvisor", description = "label-become-an-advisor-form-submit-url-description", name="label-become-an-advisor-form-submit-url")
	public String getBecomeAnAdvisorFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/claim-tracking/status", description = "label-claim-tracking-form-submit-url-description", name="label-claim-tracking-form-submit-url")
	public String getClaimTrackingFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/enquire-us", description = "label-enquire-us-form-submit-url-description", name="label-enquire-us-form-submit-url")
	public String getEnquireUsFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/request", description = "label-request-form-submit-url-description", name="label-request-form-submit-url")
	public String getRequestFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/callback", description = "label-callback-form-submit-url-description", name="label-callback-form-submit-url")
	public String getCallbackFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/customer-policy-data", description = "label-customer-policy-data-url-description", name="label-customer-policy-data-url")
	public String getCustomerPolicyDataURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/dms/generate-download-url", description = "label-dms-generate-download-url-description", name="label-dms-generate-download-url")
	public String getDMSGenerateDownloadURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/dms/generate-upload-url", description = "label-dms-generate-upload-url-description", name="label-dms-generate-upload-url")
	public String getDMSGenerateUploadURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/claim", description = "label-claim-form-submit-url-description", name="label-claim-form-submit-url")
	public String getClaimFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/enach/mandate", description = "label-enach-mandate-form-submit-url-description", name="label-enach-mandate-form-submit-url")
	public String getEnachMandateFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/enach/failure", description = "label-enach-failure-form-submit-url-description", name="label-enach-failure-form-submit-url")
	public String getEnachFailureFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/services/getifscdetails", description = "label-ifsc-details-url-description", name="label-ifsc-details-url")
	public String getIFSCDetailsURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/otp/send", description = "label-OTP-send-url-description", name="label-OTP-send-url")
	public String getOTPSendURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/otp/validate", description = "label-OTP-validate-url-description", name="label-OTP-validate-url")
	public String getOTPValidateURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/contact-details", description = "label-contact-details-form-submit-url-description", name="label-contact-details-form-submit-url")
	public String getContactDetailsFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/fatca", description = "label-fatca-form-submit-url-description", name="label-fatca-form-submit-url")
	public String getFATCAFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/lead/generate", description = "label-lead-generate-form-submit-url-description", name="label-lead-generate-form-submit-url")
	public String getLeadGenerateFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/buyJourney/resume-application", description = "label-resume-application-form-submit-url-description", name="label-resume-application-form-submit-url")
	public String getResumeApplicationFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/customerService/payment", description = "label-renewal-form-submit-url-description", name="label-renewal-form-submit-url")
	public String getRenewalFormSubmitURL();
	
	@Meta.AD(required = false, deflt = "https://devapi.edelweisslife.in/sms/send", description = "label-SMS-send-url-description", name="label-SMS-send-url")
	public String getSMSSendURL();
	
	//Added Added By Akash
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/ICICIMandate/initiatemandate", description = "label-icici-mandate-url-description", name="label-icici-mandate-url")
	public String getICICIMandateRegistrationURL();
	
	//Added Added By Razina
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/SBIMandate/initiatemandate", description = "label-sbi-mandate-url-description", name="label-sbi-mandate-url")
	public String getSBIMandateRegistrationURL();
				
	@Meta.AD(required = false, deflt = "https://buyonlineapiuat.edelweisslife.in/api/v1/SBIMandate/mandateresponse", description = "label-sbi-mandate-response-url-description", name="label-sbi-mandate-response-url")
	public String getSBIMandateResponseRegistrationURL();
}
