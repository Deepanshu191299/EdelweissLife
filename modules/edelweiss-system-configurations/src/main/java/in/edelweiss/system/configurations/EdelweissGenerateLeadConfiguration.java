package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissGenerateLeadConfiguration", name = "label-qj-generate-lead-configuration", localization = "content/Language")
public interface EdelweissGenerateLeadConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-qj-generate-lead-url-description", name = "label-qj-generate-lead-url")
	public String getGenerateLeadQJURL();

	@Meta.AD(required = false, deflt = "", description = "label-qj-generate-lead-x-api-key-description", name = "label-qj-generate-lead-x-api-key")
	public String getGenerateLeadQJXAPIKEY();
	
	@Meta.AD(required = false, deflt = "", description = "label-oauth2-url-description", name = "label-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-username-description", name = "label-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-password-description", name = "label-oauth2-password")
	public String getOAuth2QJPassword();

}
