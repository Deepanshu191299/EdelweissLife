package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * Oauth Credentials related configuration will be fetched by using the following class
 * 
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.OAuthCredentialsConfiguration", name = "label-oauth-credentials-configuration", localization = "content/Language")
public interface OAuthCredentialsConfiguration {
	
	@Meta.AD(required = false, deflt = "27kdhbaog2aavr6kn0hsrs0u4k", description = "label-oauth-username-description", name="label-oauth-username")
	public String getOAuthUsername();
	
	@Meta.AD(required = false, deflt = "t0diek9nffk3eekqn6pmgueo3oap7npavdm5d8fl69ifepdlip0", description = "label-oauth-password-description", name="label-oauth-password")
	public String getOAuthPassword();
	
	@Meta.AD(required = false, deflt = "3t0ukloilab82em4n3rivl6eap", description = "label-enach-oauth-username-description", name="label-enach-oauth-username")
	public String getEnachOAuthUsername();
	
	@Meta.AD(required = false, deflt = "63qu82ge3hgkigab7ufufu3a02og7raabnpdbnr78o3sdpac98f", description = "label-enach-oauth-password-description", name="label-enach-oauth-password")
	public String getEnachOAuthPassword();
		
}
