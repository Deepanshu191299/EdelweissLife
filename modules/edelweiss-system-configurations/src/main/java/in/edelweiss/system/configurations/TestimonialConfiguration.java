package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * Testimonial configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.TestimonialConfiguration", name = "label-testimonial-configuration", localization = "content/Language")
public interface TestimonialConfiguration {

	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-username-description", name = "label-lr-basic-auth-username")
	public String getLRUsername();

	@Meta.AD(required = false, deflt = "", description = "label-lr-basic-auth-password-description", name = "label-lr-basic-auth-password")
	public String getLRPassword();
	
	@Meta.AD(required = false, deflt = "0", description = "label-structure-folder-id-description", name="label-structure-folder-id")
	public long getStructureFolderId();
	
	@Meta.AD(required = false, deflt = "0", description = "label-content-structure-id-description", name="label-content-structure-id")
	public long getContentStructureId();
	
	@Meta.AD(required = false, deflt = "0", description = "label-male-image-id-description", name="label-male-image-id")
	public long getMaleImageId();

	@Meta.AD(required = false, deflt = "0", description = "label-female-image-id-description", name="label-female-image-id")
	public long getFemaleImageId();
	
	@Meta.AD(required = false, deflt = "0", description = "label-default-image-id-description", name="label-default-image-id")
	public long getDefaultImageId();
	
	@Meta.AD(required = false, deflt = ".xlsx,.xls", description = "label-testimonial-document-extension-description", name="label-testimonial-document-extension")
	public String getTestimonialDocumentExtension();
	
}
