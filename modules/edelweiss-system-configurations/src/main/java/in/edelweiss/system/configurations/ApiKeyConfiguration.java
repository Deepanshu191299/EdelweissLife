package in.edelweiss.system.configurations;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * API Key related configuration will be fetched by using the following class
 * 
 * @author Abhijit AA
 * 
 */
@ExtendedObjectClassDefinition(category = "edelweiss-configurations", scope = ExtendedObjectClassDefinition.Scope.COMPANY)
@Meta.OCD(id = "in.edelweiss.system.configurations.ApiKeyConfiguration", name = "label-customer-service-x-api-key-configuration", localization = "content/Language")
public interface ApiKeyConfiguration {

	@Meta.AD(required = false, deflt = "yLB2ed4jqD2spZwZZWD7x7ZkkwiyNxZi2vvOCTrH", description = "label-x-api-key-for-general-description", name="label-x-api-key-for-general")
	public String getXApiKeyForGeneral();
	
	@Meta.AD(required = false, deflt = "qel0I0dvlq1s2yUQ6yw62ac52nRk5jpA5K8PWK1P", description = "label-x-api-key-for-enach-description", name="label-x-api-key-for-enach")
	public String getXApiKeyForEnach();
	
	@Meta.AD(required = false, deflt = "DkSiVcCa212HwpNvgMG97MHmewha1Qj1inWZy12i", description = "label-x-api-key-for-enach-get-description", name="label-x-api-key-for-enach-get")
	public String getXApiKeyForEnachGet();
	
	@Meta.AD(required = false, deflt = "0lSCs2NjnZ1SggEEIpo64amvM5y098dW7A0X2jUB", description = "label-x-api-key-for-enach-OTP-description", name="label-x-api-key-for-enach-OTP")
	public String getXApiKeyForEnachOTP();
	
	@Meta.AD(required = false, deflt = "NVlMGYkCPm2UH80kPUs7x13swrzXoQup42nBSxcr", description = "label-x-api-key-for-IFSC-description", name="label-x-api-key-for-IFSC")
	public String getXApiKeyForIFSC();
	
	@Meta.AD(required = false, deflt = "dwV6mBV4S6azK3ZAFLdvY2UjnwZBs1jL7cKCjJaM", description = "label-x-api-key-for-buy-journey-description", name="label-x-api-key-for-buy-journey")
	public String getXApiKeyForBuyJourney();
	
	@Meta.AD(required = false, deflt = "VfOsuaW4bP21KLMwts7QE7BmMKCp69Fe678aUOwA", description = "label-x-api-key-for-enach-register-description", name="label-x-api-key-for-enach-register")
	public String getXApiKeyForEnachRegister();
	
	@Meta.AD(required = false, deflt = "4raLLdM6hY9haHCgK1PN34HqZSc03i6G3yIoqSfe", description = "label-x-api-key-for-send-sms-description", name="label-x-api-key-for-send-sms")
	public String getXApiKeyForSendSMS();
	
	@Meta.AD(required = false, deflt = "rsFyf6I6d92KiIyXqZ1mP7scJtd4Vdeh7bFCCRJz", description = "label-x-api-key-for-generate-lead-description", name="label-x-api-key-for-generate-lead")
	public String getXApiKeyForGenerateLead();
}
