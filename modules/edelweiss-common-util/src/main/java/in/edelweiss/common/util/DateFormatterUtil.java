package in.edelweiss.common.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.regex.Pattern;

import in.edelweiss.common.contants.DateConstants;
import in.edelweiss.common.contants.ParameterConstants;

/**
 * The purpose of this class is to mention date related activities
 *
 * Accessibility : Throughout application
 *
 * @author Ashish Singh
 * 
 */

public class DateFormatterUtil {

	/**
	 * private constructor to hide the implicit public one
	 * 
	 */
	private DateFormatterUtil() {

	}
	
	/**
	 * 
	 * To parse the string into Java Date object
	 * 
	 * @param date
	 * @return Date
	 */
	public static Date parseDate(String date) {
		Date parsedDate = null;
		if (Validator.isNotNull(getMatchedDateFormat(date))) {
			try {
				parsedDate = new SimpleDateFormat(getMatchedDateFormat(date)).parse(date);
			} catch (Exception exception) {
				logger.error("Error while parsing the date to matched format" + date + ": " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			}
		}
		return parsedDate;
	}

	/**
	 * Get the matched date format based on Regex Pattern.
	 * Note :- Regex patterns are created based on the Date 
	 * Format in the Date Constants.
	 * 
	 * Add data to map based on the requirement.
	 * 
	 * @param date
	 * @return String
	 */
	public static String getMatchedDateFormat(String date) {
		String dateFormat = StringPool.BLANK;

		Map<String, String> datePatternMapList = new HashMap<>();
		datePatternMapList.put(DateConstants.HYPHEN_YYYY_MM_DD, "^\\d{4}-\\d{2}-\\d{2}$");
		datePatternMapList.put(DateConstants.HYPHEN_DD_MM_YYYY, "^\\d{2}-\\d{2}-\\d{4}$");
		datePatternMapList.put(DateConstants.HYPHEN_YYYY_MM_DD_T_HH_MM_SS, "^\\d{4}-\\d{2}-\\d{2}\\w{1}\\d{2}:\\d{2}:\\d{2}$");
		datePatternMapList.put(DateConstants.SLASH_YYYY_MM_DD, "^\\d{4}/\\d{2}/\\d{2}$");
		datePatternMapList.put(DateConstants.SLASH_DD_MM_YYYY, "^\\d{2}/\\d{2}/\\d{4}$");

		for (Entry<String, String> patternEntry : datePatternMapList.entrySet()) {
			Pattern datePattern = Pattern.compile(patternEntry.getValue());
			if (datePattern.matcher(date).matches()) {
				return patternEntry.getKey();
			}
		}

		return dateFormat;
	}
	
	/**
	 * To parse the string to specific date pattern string
	 * 
	 * @param date
	 * @param toFormat
	 * @return Date
	 */
	public static String parseDateToSpecificFormat(String date, String toFormat) {
		String formattedDate = StringPool.BLANK;
		if (Validator.isNotNull(getMatchedDateFormat(date))) {
			try {
				Date parsedDate = new SimpleDateFormat(getMatchedDateFormat(date)).parse(date);
				if(Validator.isNotNull(parsedDate)) {
					formattedDate = new SimpleDateFormat(toFormat).format(parsedDate);
				}
			} catch (Exception exception) {
				logger.error("Error while parsing the date to specific format " + date + ": " + exception.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(ParameterConstants.EXCEPTION, exception);
				}
			}
		}
		return formattedDate;
	}
	
	/**
	 * To parse the string into Java Date object adding required days.
	 * 
	 * @param date
	 * @param format
	 * @param daysToAddInDate
	 * @return Date
	 */
	public static Date addDaysInDate(Date date, String format, int daysToAddInDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String formatDate = sdf.format(date);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(formatDate));
			c.add(Calendar.DATE, daysToAddInDate);
			return sdf.parse(sdf.format(c.getTime()));
		} catch (ParseException exception) {
			logger.error("Error while parsing the date adding days in date " + date + ": " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return null;
	}

	/**
	 * To parse the string into Java Date object
	 * 
	 * @param date
	 * @param format
	 * @return Date
	 */
	public static Date parseDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException exception) {
			logger.error("Error while parsing the date to string date " + date + ": " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return null;
	}

	/**
	 * Formate The date to the given format to specified timezone
	 * 
	 * @param date
	 * @param pattern
	 * @param timezone
	 * @return String
	 */
	public static String parseDate(Date date, String pattern, String timezone) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			if(Validator.isNotNull(timezone) && 
					!timezone.isBlank() && !timezone.isEmpty()){
				formatter.setTimeZone(TimeZone.getTimeZone(timezone));
			}
			return formatter.format(date);
		} catch (Exception exception) {
			logger.error("Error while formatting the date based on timezone" + date + ": " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return null;
	}

	public static int calculateAge(String birthStr){

		try {
			
			Calendar currentDate = Calendar.getInstance();
			Calendar birthDate = Calendar.getInstance();
			birthDate.setTime(parseDate(birthStr));

			int age = currentDate.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
			if ((birthDate.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH))
					|| (birthDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH)
					&& birthDate.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH))){  
				age--;  
			}
			return age;
		
		} catch (Exception exception) {
			logger.error("Error while formatting the date" + birthStr + ": " + exception.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(ParameterConstants.EXCEPTION, exception);
			}
		}
		return -1;
	}

	private static Log logger = LogFactoryUtil.getLog(DateFormatterUtil.class);

}
