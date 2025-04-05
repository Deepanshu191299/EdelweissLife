package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * API Endpoint URL related configuration will be fetched by using the following class
 * 
 * @author Umer Farooq
 * 
 */

@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.EdelweissWebengageURLConfiguration", name = "label-webengage-url-configuration", localization = "content/Language")
public interface EdelweissWebengageURLConfiguration {

	@Meta.AD(required = false, deflt = "", description = "label-webengage-landing-url-description", name="label-webengage-landing-url")
	public String getProductLandingPageURL();
	
	@Meta.AD(required = false, deflt = "", description = "label-webengage-buy-url-description", name="label-webengage-buy-url")
	public String getBuyPageURL();
	
	@Meta.AD(required = false, deflt = "", description = "label-webengage-customize-url-description", name="label-webengage-customize-url")
	public String getCustomizePageURL();
	
	@Meta.AD(required = false, deflt = "", description = "label-webengage-summary-url-description", name="label-webengage-summary-url")
	public String getSummaryPageURL();
}
