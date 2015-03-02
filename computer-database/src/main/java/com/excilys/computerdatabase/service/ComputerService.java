package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class ComputerService {

	public Computer create(Computer computer){
		return ComputerDAO.getInstance().create(computer);
	}
	
	public Computer find(int id) {
		return ComputerDAO.getInstance().find(id);
	}
	
	public List<Computer> getAll(int limit, int offset) {
		return ComputerDAO.getInstance().getAll(limit, offset);
	}
	
	public int count() {
		return ComputerDAO.getInstance().count();
	}
	
	public int countSearch(String name) {
		return ComputerDAO.getInstance().countSearch(name);
	}
	
	public List<Computer> getByName(String name, int limit, int offset) {
		return ComputerDAO.getInstance().getByName(name, limit, offset);
	}
}
