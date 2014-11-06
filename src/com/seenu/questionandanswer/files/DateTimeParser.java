package com.seenu.questionandanswer.files;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeParser {

	private static Date getServerFormattedDateTime(String serverDateTime) {
		Date dateParsed = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		try {
			dateParsed = sdf.parse(serverDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateParsed;

	}

	public static String startEndTime(String serverDateTime) {

		DateFormat formatterDate = new SimpleDateFormat("MMM dd, yyyy");

		Date dateParsed = getServerFormattedDateTime(serverDateTime);
		long timer = dateParsed.getTime();

		String dateFormatted = formatterDate.format(dateParsed);

		return dateFormatted;
	}

}
