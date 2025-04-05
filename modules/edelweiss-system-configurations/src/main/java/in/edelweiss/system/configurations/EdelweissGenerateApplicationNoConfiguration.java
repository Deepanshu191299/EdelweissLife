package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissGenerateApplicationNoConfiguration", name = "label-generate-application-configuration", localization = "content/Language")
public interface EdelweissGenerateApplicationNoConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-generate-application-url-description", name = "label-generate-application-url")
	public String getGenerateApplicationURL();

	@Meta.AD(required = false, deflt = "", description = "label-generate-application-x-api-key-description", name = "label-generate-application-x-api-key")
	public String getGenerateApplicationXAPIKEY();
	
	@Meta.AD(required = false, deflt = "", description = "label-oauth2-url-description", name = "label-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-username-description", name = "label-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-password-description", name = "label-oauth2-password")
	public String getOAuth2QJPassword();

}
