package com.excilys.computerdatabase.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class ComputerValidator {
	
	private static List<String> errors = new ArrayList<String>();
	private static Computer computer;
	private static ComputerValidator computerValidator = null;
	
	private static DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
	
	private ComputerValidator() {
		
	}
	
	protected static ComputerValidator getInstance() {
		if (computerValidator == null) {
			computerValidator = new ComputerValidator();
		}
		return computerValidator;
	}
	
	protected void addError (String error) {
		errors.add("Error: " + error);
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public static Computer getComputer() {
		return computer;
	}
	
	public void validate(String name, String introduced, String discontinued, String companyIdString, int computerId) {
		this.initializeValue();
		System.out.println(name);
		if (name == null && computerId !=0) {
			this.addError("The computer's name cannot be null");
		} else {
			computer.setName(name);
		}
		
		if (introduced == null) {
			computer.setIntroduced(null);
		} else {
			computer.setIntroduced(LocalDateTime.parse(introduced + " 00:00:00", format));
		}
		
		if (discontinued == null) {
			computer.setDiscontinued(null);
		} else {
			computer.setDiscontinued(LocalDateTime.parse(discontinued + " 00:00:00", format));
		}
		
		//TODO Suite du validate
	}
	
	public void isExist(String IdComputerString) {
		this.initializeValue();
		try {
			int computerId = Integer.valueOf(IdComputerString);
			computer = ComputerDAO.getInstance().find(computerId);
			if (computer == null) {
				this.addError("Computer doesn't exist");
			}
			
		} catch (NumberFormatException e) {
			this.addError("Computer's id not valid: it should be an integer");
		}
	}
	
	protected void initializeValue() {
		errors = new ArrayList<String>();
		computer = new Computer();
	}
	
	public static void displayErrors() {
		for (String s : errors) {
			System.out.println(s);
		}
	}
}
