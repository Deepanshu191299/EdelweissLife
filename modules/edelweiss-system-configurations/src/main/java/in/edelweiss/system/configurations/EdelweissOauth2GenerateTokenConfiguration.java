package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissOauth2GenerateTokenConfiguration", name = "label-qj-oauth2-configuration", localization = "content/Language")
public interface EdelweissOauth2GenerateTokenConfiguration {
	
	@Meta.AD(required = false, deflt = "", description = "label-qj-oauth2-url-description", name = "label-qj-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-qj-oauth2-username-description", name = "label-qj-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-qj-oauth2-password-description", name = "label-qj-oauth2-password")
	public String getOAuth2QJPassword();
	
	

}
