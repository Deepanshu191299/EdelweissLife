package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * URL related configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.URLRelatedConfiguration", name = "label-url-related-configuration", localization = "content/Language")
public interface URLRelatedConfiguration {
	
	@Meta.AD(required = false, deflt = "/soft-login", description = "label-soft-login-page-url-description", name="label-soft-login-page-url")
	public String getSoftLoginPageURL();
	
	@Meta.AD(required = false, deflt = "/renew-online,/register-enach,/update-contact-details", description = "label-soft-login-required-description", name="label-soft-login-required")
	public String getSoftLoginRequiredURL();
	
	@Meta.AD(required = false, deflt = "/customer-services-ppc,/customer-services-us,/customer-services-rpc", description = "label-soft-login-otp-required-description", name="label-soft-login-otp-required")
	public String getSoftLoginOTPRequiredURL();
	
	@Meta.AD(required = false, deflt = "blogs", description = "label-blogs-page-name-description", name="label-blogs-page-name")
	public String getBlogsPageName();
	
	@Meta.AD(required = false, deflt = "/register-enach", description = "label-register-enach-page-url-description", name="label-register-enach-page-url")
	public String getRegisterEnachPageURL();
	
	@Meta.AD(required = false, deflt = "/renew-online", description = "label-renew-your-policy-page-url-description", name="label-renew-your-policy-page-url")
	public String getRenewYourPolicyPageURL();

	@Meta.AD(required = false, deflt = "", description = "label-advisor-login-url-description", name="label-advisor-login-url")
	public String getAdvisorLoginURL();
	
	//added by akash
	@Meta.AD(required = false, deflt = "https://www.edelweisslife.in", description = "label-icici-redirect-url-description", name="label-icici-redirect-url")
	public String getICICIMandateRedirectURL();
	
	//Added By Akash For ICICI Mandate Registration
		@Meta.AD(required = false, deflt = "false", description = "label-icici-url-restriction-on-description", 
				name="label-icici-url-restriction-on", optionLabels = {"Enable", "Disable"},  optionValues = {"true", "false"})
		public boolean getICICIURLRestrictionOn();
	
	//added by razeena
	@Meta.AD(required = false, deflt = "https://www.edelweisslife.in", description = "label-sbi-redirect-url-description", name="label-sbi-redirect-url")
	public String getSBIMandateRedirectURL();
						
	//Added By razeena For SBI Mandate Registration
	@Meta.AD(required = false, deflt = "false", description = "label-sbi-url-restriction-on-description", 
			name="label-sbi-url-restriction-on", optionLabels = {"Enable", "Disable"},  optionValues = {"true", "false"})
	public boolean getSBIURLRestrictionOn();
}
