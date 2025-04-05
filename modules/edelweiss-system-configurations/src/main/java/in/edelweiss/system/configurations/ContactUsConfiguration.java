package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * Contact Us configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.ContactUsConfiguration", name = "label-contact-us-configuration", localization = "content/Language")
public interface ContactUsConfiguration {

	@Meta.AD(required = false, deflt = "false", description = "label-display-sms-indicator-description", 
			name="label-display-sms-indicator", 
			optionLabels = {"Show", "Hide"}, 
			optionValues = {"true", "false"})
	public boolean getDisplaySMSIndicator();
}
