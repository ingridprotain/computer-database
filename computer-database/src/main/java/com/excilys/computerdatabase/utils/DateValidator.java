package com.excilys.computerdatabase.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.apache.commons.validator.GenericValidator;
import org.springframework.context.i18n.LocaleContextHolder;

final public class DateValidator {
	private DateValidator() {
		
	}
	
	public static boolean isDate(String date) {
		Locale locale = LocaleContextHolder.getLocale();
		String pattern = getPattern(locale);
		if (GenericValidator.isDate(date, pattern, true)) {
			try {
				LocalDateTime.parse(date + " 00:00:00", getFormat(locale));
				return true;
			} catch (DateTimeParseException e) {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public static LocalDateTime toLocalDateTime(String date) {
		Locale locale = LocaleContextHolder.getLocale();
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDateTime.parse(date + " 00:00:00", getFormat(locale));
		}
	}
	
	public static DateTimeFormatter getFormat(Locale locale) {
		DateTimeFormatter format;
		if (locale.getLanguage() == "fr") {
			format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		} else {
			format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		}
		return format;
	}
	
	public static String getPattern(Locale locale) {
		return (locale.getLanguage() == "fr" ? "dd/MM/YYYY" : "MM/dd/YYYY");
	}
}
