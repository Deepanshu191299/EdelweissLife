package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissBJGeneratePolicyNoConfiguration", name = "label-bj-generate-policy-configuration", localization = "content/Language")
public interface EdelweissBJGeneratePolicyNoConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-bj-generate-policy-url-description", name = "label-bj-generate-policy-url")
	public String getBJGeneratePolicyURL();

	@Meta.AD(required = false, deflt = "", description = "label-bj-generate-policy-x-api-key-description", name = "label-bj-generate-policy-x-api-key")
	public String getBJGeneratePolicyXAPIKEY();
	
	@Meta.AD(required = false, deflt = "", description = "label-oauth2-url-description", name = "label-oauth2-url")
	public String getOAuth2QJURL();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-username-description", name = "label-oauth2-username")
	public String getOAuth2QJUsername();

	@Meta.AD(required = false, deflt = "", description = "label-oauth2-password-description", name = "label-oauth2-password")
	public String getOAuth2QJPassword();

}
