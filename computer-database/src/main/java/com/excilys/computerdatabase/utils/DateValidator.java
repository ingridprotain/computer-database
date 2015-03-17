package com.excilys.computerdatabase.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.validator.GenericValidator;

final public class DateValidator {
	private DateValidator() {
		
	}
	
	public static boolean isDate(String date) {
		String pattern = "yyyy-MM-dd";
		if (GenericValidator.isDate(date, pattern, true)) {
			try {
				LocalDateTime.parse(date + " 00:00:00", getFormat());
				return true;
			} catch (DateTimeParseException e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static LocalDateTime toLocalDateTime(String date) {
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDateTime.parse(date + " 00:00:00", getFormat());
		}
	}
	
	public static DateTimeFormatter getFormat() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return format;
	}
}
