package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistance.ComputerDAO;

public class ComputerController {
	private ComputerDAO computerDao;
	
	public ComputerController() {
		computerDao = new ComputerDAO();
	}
	
	public Computer checkIfComputerExist(int id) throws EntityNotExistException{
		Computer computer = computerDao.find(id);
		if (computer == null)
			throw new EntityNotExistException("computer");
		else 
			return computer;
	}
}
