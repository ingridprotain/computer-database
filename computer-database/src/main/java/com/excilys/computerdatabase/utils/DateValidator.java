package com.excilys.computerdatabase.utils;

import org.apache.commons.validator.GenericValidator;

final public class DateValidator {
	private DateValidator() {
		
	}
	
	public static boolean isDate(String date) {
		if (GenericValidator.isDate(date, "'MM/dd/yyyy'", true)) {
			return true;
		}else {
			return false;
		}
	}
}
