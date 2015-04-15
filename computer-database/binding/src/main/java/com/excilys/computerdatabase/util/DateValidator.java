package com.excilys.computerdatabase.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.GenericValidator;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * the method isValid is use to validate if a date is in correct format or not
 */
public class DateValidator implements ConstraintValidator<MyDate, String> {
	@Override
	public void initialize(MyDate arg0) {
	}
	
	/**
	 * Depending on the language used by the user, the format is not the same
	 * @param date it's the date to valid in string format
	 * @param ctx ConstraintValidatorContext
	 * @return true if the date is in the good format depending on the language; else return false
	 * (in french : dd/MM/yyyy - in english : MM/dd/yyyy)
	 */
	@Override
	public boolean isValid(String date, ConstraintValidatorContext ctx) {
		if (date == null || date.equals("")) {
			return true;
		} else {
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
	public static LocalDateTime toLocalDateTime(String date) {
		Locale locale = LocaleContextHolder.getLocale();
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDateTime.parse(date + " 00:00:00", getFormat(locale));
		}
	}
}