package com.excilys.computerdatabase.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.validator.GenericValidator;

final public class DateValidator {
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	private DateValidator() {
		
	}
	
	public static boolean isDate(String date) {
		if (GenericValidator.isDate(date, "MM/dd/YYYY", true)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static LocalDateTime toLocalDateTime(String date) {
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDateTime.parse(date + " 00:00:00", format);
		}
	}
}
