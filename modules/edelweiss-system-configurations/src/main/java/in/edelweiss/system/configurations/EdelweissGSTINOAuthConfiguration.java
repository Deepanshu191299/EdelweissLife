package in.edelweiss.system.configurations;


import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissGSTINOAuthConfiguration", name = "label-gstin-oauth-configuration", localization = "content/Language")
public interface EdelweissGSTINOAuthConfiguration {
	@Meta.AD(required = false, deflt = "password", description = "label-gstin-oauth-grant_type-description", name = "label-gstin-oauth-grant_type")
	public String getGstinOAuth2GrantType();
	
	@Meta.AD(required = false, deflt = "https://login.microsoftonline.com/16a6cf82-ea84-49e5-a55d-b65a9a2100df/oauth2/token", description = "label-gstin-oauth-url-description", name = "label-gstin-oauth-url")
	public String getGstinOAuth2URL();
	
	@Meta.AD(required = false, deflt = "https://etliuat1.crm8.dynamics.com/", description = "label-gstin-oauth-resource-description", name = "label-gstin-oauth-resource")
	public String getGstinOAuth2Resource();

	@Meta.AD(required = false, deflt = "crmintegrationuser@etlife.in", description = "label-gstin-oauth-username-description", name = "label-gstin-oauth-username")
	public String getGstinOAuth2Username();

	@Meta.AD(required = false, deflt = "Dat#m!grate", description = "label-gstin-oauth-password-description", name = "label-gstin-oauth-password")
	public String getGstinOAuth2Password();
	
	@Meta.AD(required = false, deflt = "980b03c0-de36-46e4-9f70-c11a4e5e3492", description = "label-gstin-oauth-clientid-description", name = "label-gstin-oauth-clientId")
	public String getGstinOAuth2ClientId();
	
	@Meta.AD(required = false, deflt = "https://etliuat1.crm8.dynamics.com/api/data/v9.1/etli_attributemasters?$select=etli_drccode&$filter=etli_attributetype eq 'STATE' and  etli_attributetext eq 'Maharashtra'", description = "label-gstin-state-code-valdiation-api-description", name = "label-gstin-state-code-valdiation-api")
	public String getGstinStateCodeAPIValidation();
	
}
