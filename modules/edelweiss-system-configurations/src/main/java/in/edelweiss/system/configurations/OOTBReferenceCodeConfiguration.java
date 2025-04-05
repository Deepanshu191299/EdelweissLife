package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * OOTB Reference Code configuration will be fetched by using the following class
 *
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.OOTBReferenceCodeConfiguration", name = "label-ootb-reference-code-configuration", localization = "content/Language")
public interface OOTBReferenceCodeConfiguration {
	
	@Meta.AD(required = false, deflt = "addressTypes", description = "label-address-type-picklist-external-reference-code-description", name="label-address-type-picklist-external-reference-code")
	public String getAddressTypePickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "claimTypes", description = "label-claim-type-picklist-external-reference-code-description", name="label-claim-type-picklist-external-reference-code")
	public String getClaimTypePickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "bankNames", description = "label-bank-names-picklist-external-reference-code-description", name="label-bank-names-picklist-external-reference-code")
	public String getBankNamesPickListExternalReferenceCode();
		
	@Meta.AD(required = false, deflt = "askForAnything", description = "label-ask-for-anything-picklist-external-reference-code-description", name="label-ask-for-anything-picklist-external-reference-code")
	public String getAskForAnythingPickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "quiz1", description = "label-quiz-1-picklist-external-reference-code-description", name="label-quiz-1-picklist-external-reference-code")
	public String getQuiz1PickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "quiz2", description = "label-quiz-2-picklist-external-reference-code-description", name="label-quiz-2-picklist-external-reference-code")
	public String getQuiz2PickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "quiz3", description = "label-quiz-3-picklist-external-reference-code-description", name="label-quiz-3-picklist-external-reference-code")
	public String getQuiz3PickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "quiz4", description = "label-quiz-4-picklist-external-reference-code-description", name="label-quiz-4-picklist-external-reference-code")
	public String getQuiz4PickListExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "IndiaStates", description = "label-state-object-external-reference-code-description", name="label-state-object-external-reference-code")
	public String getStateObjectExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "IndiaCities", description = "label-city-object-external-reference-code-description", name="label-city-object-external-reference-code")
	public String getCityObjectExternalReferenceCode();
	
	@Meta.AD(required = false, deflt = "validIDProofs", description = "label-valid-id-proofs-description", name="label-valid-id-proofs")
	public String getValidIDProofsExternalReferenceCode();
	
	
}
