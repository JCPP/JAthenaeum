/**
 * 
 */
package com.github.jcpp.jathenaeum.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Class for commons validation methods.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Validator {
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	
	/**
	 * Check if the email is valid.
	 * @param email the string to check.
	 * @return True if it is a valid email, false otherwise.
	 */
	public static boolean isValidEmail(String email){
		return Pattern.compile(EMAIL_PATTERN).matcher(email).matches();
	}


	/**
	 * Validate the date. Inspired by <a href="www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/">mkyong tutorial</a>.
	 * @param dateToValidate the date to validate.
	 * @param dateFromat the date format.
	 * @return Return true if this date is valid, false otherwise.
	 */
	public static boolean isValidDate(String dateToValidate, String dateFromat){
		 
		if(dateToValidate == null){
			return false;
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
	
		try {
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
	
		} catch (ParseException e) {
			//e.printStackTrace();
			return false;
		}
	
		return true;
	}


	/**
	 * Validate the date. The default format is: <b>dd/MM/yyyy</b>.
	 * @param dateToValidate the date to validate.
	 * @return Return true if this date is valid, false otherwise.
	 */
	public static boolean isValidDate(String dateToValidate){
		return isValidDate(dateToValidate, "dd/MM/yyyy");
	}

}