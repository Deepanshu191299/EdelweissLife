package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissLRBasicAuthConfiguration", name = "label-lr-basic-auth-configuration", localization = "content/Language")
public interface EdelweissLRBasicAuthConfiguration {
	
	
	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-username-description", name = "label-lr-basic-auth-username")
	public String getLRUsername();

	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-password-description", name = "label-lr-basic-auth-password")
	public String getLRPassword();
	
	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-url-description", name = "label-lr-basic-auth-url")
	public String getLROauthURL();
	
	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-username-description", name = "label-lr-basic-auth-username")
	public String getLRClientId();

	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-password-description", name = "label-lr-basic-auth-password")
	public String getLRClientSecrete();

}
