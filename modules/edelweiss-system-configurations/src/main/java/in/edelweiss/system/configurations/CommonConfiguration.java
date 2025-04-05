package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * Common configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.CommonConfiguration", name = "label-common-configuration", localization = "content/Language")
public interface CommonConfiguration {

	@Meta.AD(required = false, deflt = ".pdf,.png,.jpg,.jpeg", description = "label-document-extension-description", name="label-document-extension")
	public String getDocumentExtensions();
	
	@Meta.AD(required = false, deflt = "1", description = "label-session-timeout-in-minutes-description", name="label-session-timeout-in-minutes")
	public String getSessionTimeoutInMinutes();
	
	@Meta.AD(required = false, deflt = "", description = "label-register-enach-disclaimer-id-description", name="label-register-enach-disclaimer-id")
	public String getRegisterEnachDisclaimerId();
	
	@Meta.AD(required = false, deflt = "complaint_types", description = "label-complaint-vocabulary-name-description", name="label-complaint-vocabulary-name")
	public String getComplaintVocabularyName();
	
	@Meta.AD(required = false, deflt = "", description = "label-http-request-timeout-in-milliseconds-description", name="label-http-request-timeout-in-milliseconds")
	public String getHttpRequestTimeout();
	
	@Meta.AD(required = false, deflt = "", description = "label-lms-form-validation-description", name="label-lms-form-validation")
	public boolean validateLMSData();
	
	@Meta.AD(required = false, deflt = "stage", description = "label-environment-name-description", name="label-environment-name")
	public String getEnvironmentName();
	
	@Meta.AD(required = false, deflt = "http://www.edelweisslife.in/documents/d/guest/logo-1-jpg", description = "label-environment-logo-url-description", name="label-environment-logo-url")
	public String getEnvironmentLogoURL();
}
