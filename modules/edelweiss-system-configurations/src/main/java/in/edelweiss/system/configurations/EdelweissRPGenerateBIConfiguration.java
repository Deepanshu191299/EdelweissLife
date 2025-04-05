package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissRPGenerateBIConfiguration", name = "label-rp-generate-bi-configuration", localization = "content/Language")
public interface EdelweissRPGenerateBIConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-rp-generate-bi-url-description", name = "label-rp-generate-bi-url")
	public String getRPGenerateBIURL();

	@Meta.AD(required = false, deflt = "", description = "label-rp-generate-bi-x-api-key-description", name = "label-rp-generate-bi-x-api-key")
	public String getRPGenerateBIXAPIKEY();
	
	@Meta.AD(required = false, deflt = "", description = "label-oauth2-url-description", name = "label-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-username-description", name = "label-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-password-description", name = "label-oauth2-password")
	public String getOAuth2QJPassword();

}
