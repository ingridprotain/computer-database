package com.excilys.computerdatabase.utils;

import junit.framework.TestCase;

public class DateValidatorTest extends TestCase {
	public void testIsDate() {
		String date = "40/40/2005";
		boolean isDate = DateValidator.isDate(date);
		assertFalse(isDate);
		
		date = "azerty";
		isDate = DateValidator.isDate(date);
		assertFalse(isDate);
		
		date = "123";
		isDate = DateValidator.isDate(date);
		assertFalse(isDate);
		
		date = "25/03/1994";
		isDate = DateValidator.isDate(date);
		assertFalse(isDate);
		
		date = "31/02/1994";
		isDate = DateValidator.isDate(date);
		assertFalse(isDate);
		
		date = "03/25/1994";
		isDate = DateValidator.isDate(date);
		assertTrue(isDate);
		
		date = "03-25-1994";
		isDate = DateValidator.isDate(date);
		assertFalse(isDate);
	}
}
