/**
 * 
 */
package com.github.jcpp.jathenaeum.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class to convert data type.
 * @author <a href="https://github.com/DavidePastore">DavidePastore</a>
 *
 */
public class Converter {
	
	/**
	 * Converts string to date. It uses the <b>dd/MM/yyyy</b> format.
	 * @param dateToConvert the string date to convert.
	 * @return The Date object.
	 */
	public static Date fromStringToDate(String dateToConvert){
		return fromStringToDate(dateToConvert, "dd/MM/yyyy");
	}
	
	/**
	 * Converts string to date, using the dateFormat.
	 * @param dateToConvert the string date to convert.
	 * @param dateFormat the date format.
	 * @return The date object.
	 */
	public static Date fromStringToDate(String dateToConvert, String dateFormat){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			date = sdf.parse(dateToConvert);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	/**
	 * Convert a java.util.Date object to a java.sql.Date object.
	 * @param utilDate the java.util.Date object.
	 * @return Returns the java.sql.Date object
	 */
	public static java.sql.Date fromUtilDateToSqlDate(Date utilDate){
	    return new java.sql.Date(utilDate.getTime());
	}

}
