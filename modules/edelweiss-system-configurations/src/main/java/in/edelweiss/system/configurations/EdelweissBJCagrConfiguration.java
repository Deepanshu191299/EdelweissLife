package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissBJCagrConfiguration", name = "label-bj-cagr-configuration", localization = "content/Language")
public interface EdelweissBJCagrConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-bj-cagr-url-description", name = "label-bj-cagr-url")
	public String getBJCagrURL();

	@Meta.AD(required = false, deflt = "", description = "label-bj-cagr-x-api-key-description", name = "label-bj-cagr-x-api-key")
	public String getBJCagrXAPIKEY();
	
	@Meta.AD(required = false, deflt = "", description = "label-oauth2-url-description", name = "label-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-username-description", name = "label-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-password-description", name = "label-oauth2-password")
	public String getOAuth2QJPassword();

}
