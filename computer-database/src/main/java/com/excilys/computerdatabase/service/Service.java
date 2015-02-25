package com.excilys.computerdatabase.service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class Service {
	public Computer create(Computer computer){
		computer = ComputerDAO.getInstance().create(computer);
		return computer;
	}
}
