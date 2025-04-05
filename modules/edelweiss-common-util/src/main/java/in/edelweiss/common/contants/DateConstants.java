package in.edelweiss.common.contants;

import java.util.Date;

/**
 * This class contains Date Constants.
 *
 * Accessibility : Throughout application
 *
 * @author Abhijit AA
 */

public class DateConstants {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private DateConstants() {

	}
	
	public static final long CURRENT_TIME_STAMP = new Date().getTime();
	
	public static final String HYPHEN_DD_MMM_YYYY = "dd-MMM-yyyy";
	public static final String HYPHEN_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String HYPHEN_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String HYPHEN_DD_MMM_YYYY_HH_MM_SS = "dd-MMM-yyyy HH.mm.ss";
	public static final String HYPHEN_YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String HYPHEN_YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static final String SLASH_DD_MMM_YYYY = "dd/MMM/yyyy";
	public static final String SLASH_YYYY_MM_DD = "yyyy/MM/dd";
	public static final String SLASH_DD_MM_YYYY = "dd/MM/yyyy";
	public static final String SLASH_DD_MMM_YYYY_HH_MM_SS = "dd/MMM/yyyy HH.mm.ss";
	public static final String SLASH_YYYY_MM_DD_T_HH_MM_SS = "yyyy/MM/dd'T'HH:mm:ss";
}
