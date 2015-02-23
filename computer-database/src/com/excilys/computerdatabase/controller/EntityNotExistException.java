package com.excilys.computerdatabase.controller;

public class EntityNotExistException extends Exception {
	public EntityNotExistException(String entity_name) {
		if (entity_name == "company")
			System.out.println("L'entreprise n'existe pas");
		else 
			System.out.println("L'ordinateur n'existe pas");
	}
}
