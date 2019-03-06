package com.automation.helper.dateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * @author Kumar Rohan 
 *
 */
public class DateTimeHelper {
	
	public static String getCurrentDateTime() {

		DateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
		Calendar cal = Calendar.getInstance();
		String time = "" + dateFormat.format(cal.getTime());
		return time;
	}

	public static String getCurrentDate() {
		return getCurrentDateTime().substring(0, 11);
	}
	
	public static String getSystemDateInFormat(String format)
	{
		DateFormat dateFormater = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormater.format(date);
	}
	
	public static String getPreviousMonthDateInFormat(String format)
	{
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -61);
		return dateFormat.format(cal.getTime()).toString();
	}


}
