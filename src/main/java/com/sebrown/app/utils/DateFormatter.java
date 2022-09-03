/**
 * 
 */
package com.sebrown.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author SteveBrown
 *
 */
public class DateFormatter {

	public static final String DEFAULT_DATE = "01/01/1900";
	public static final String DATE_IN = "yyyyMMdd";
	public static final String DATE_OUT = "dd/MM/yyyy";
	public static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_IN);
		
	public static String getDateFromDouble(int strDate) {		
		String reqDate = DEFAULT_DATE;
		
		try {
			Date d = SDF.parse(String.valueOf(strDate));
			reqDate = new SimpleDateFormat(DATE_OUT).format(d);
		} catch (ParseException e) {	}
		
		return reqDate;
	}
	
	public static String getSimpleNow() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();

		return dateFormat.format(cal.getTime());
	}	
	
}
