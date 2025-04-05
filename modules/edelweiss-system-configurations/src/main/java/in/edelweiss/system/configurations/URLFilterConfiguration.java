package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * URL Filter configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.URLFilterConfiguration", name = "label-url-filter-configuration", localization = "content/Language")
public interface URLFilterConfiguration {
	
	@Meta.AD(required = false, deflt = "false", description = "label-is-url-restriction-on-description", 
			name="label-is-url-restriction-on", optionLabels = {"Enable", "Disable"},  optionValues = {"true", "false"})
	public boolean getIsURLRestrictionOn();
	
	@Meta.AD(required = false, deflt = "{", description = "label-url-restrict-pattern-description", name="label-url-restrict-pattern")
	public String[] getURLRestrictPattern();
}
