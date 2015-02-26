package com.excilys.computerdatabase.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateValidator {
	
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	private static List<String> errors = new ArrayList<String>();

	public static List<String> getErrors() {
		return errors;
	}

	public static void addError(String error) {
		errors.add(error);
	}

	public static LocalDateTime toLocalDateTime(String date) {
		if (date == null || date.equals("")) {
			return null;
		} else {
			return LocalDateTime.parse(date + " 00:00:00", format);
		}
	}
	
	public static boolean isValid(String date, String nomDate) {
		errors = new ArrayList<String>();
		try {
			if (date == null || date.equals("")) {
				return true;
			} else {
				LocalDateTime.parse(date + " 00:00:00", format);
			}
		} catch (DateTimeParseException e) {
			addError("Le format de date doit être mm/dd/yyyy pour " + nomDate);
			return false;
		}
		return true;
	}
}
