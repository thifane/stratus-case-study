package org.ibm.achievementTracker.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Utility class containing some utility methods.
 * 
 * @author IBM
 * 
 */

public class AchievementTrackerWebUtils {

	/**
	 * Converts a String to Date.
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date convertStringToDate(String strDate) {

		Date convertedDate = null;
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		try {
			convertedDate = format.parse(strDate);
		} catch (ParseException e) {
			convertedDate = null;
		}
		return convertedDate;

	}

	/**
	 * Converts a Date object to XMLGregorianCalendar object.
	 * 
	 * @param date
	 * @return
	 */
	public static XMLGregorianCalendar convertDateToXMLGregorian(Date date) {

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar gregStartDate = null;
		try {
			gregStartDate = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e1) {
			gregStartDate = null;
		}
		return gregStartDate;
	}

	/**
	 * Converts an XMLGregorianCalendar object to String.
	 * 
	 * @param xgc
	 * @return
	 */
	public static String convertXMLGregorianToString(XMLGregorianCalendar xgc) {

		String formatted_string = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (xgc != null) {
			GregorianCalendar gc = xgc.toGregorianCalendar();
			formatted_string = sdf.format(gc.getTime());
		}
		return formatted_string;

	}
}
