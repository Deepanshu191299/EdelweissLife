package in.edelweiss.register.enach.constants;

/**
 * @author Abhijit AA
 */
public class RegisterEnachPortletKeys {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private RegisterEnachPortletKeys() {

	}
	
	public static final String REGISTERENACH =
		"in_edelweiss_register_enach_RegisterEnachPortlet";
	public static final String ENACHSUCCESS =
			"in_edelweiss_register_enach_EnachSuccessPortlet";
	public static final String ENACHFAILURE =
			"in_edelweiss_register_enach_EnachFailurePortlet";

	public static final String SUBMIT_REGISTER_ENACH_FORM_MVC_ACTION_COMMAND = "/submit/register-enach/form";
	public static final String BANK_NAME_FROM_IFSC_MVC_RESOURCE_COMMAND = "/ifsc/bankName";
	public static final String ENACH_FAILURE_MVC_RESOURCE_COMMAND = "/enach/failure";
	
	//Added By Akash
	public static final String ICICI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND="/icici/mandate-registration";
	public static final String SBI_MANDATE_REGISTRATION_MVC_RESOURCE_COMMAND="/sbi/mandate-registration";
}