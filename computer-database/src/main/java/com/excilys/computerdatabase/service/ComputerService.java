package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.utils.Pages;

public class ComputerService {

	public Computer create(Computer computer){
		return ComputerDAO.getInstance().create(computer);
	}
	
	public Computer find(int id) {
		return ComputerDAO.getInstance().find(id);
	}
	
	public Computer update(Computer computer) {
		return ComputerDAO.getInstance().update(computer);
	}
	
	public List<Computer> getAll(Pages pagination) {
		return ComputerDAO.getInstance().getAll(pagination.getLimit(), pagination.getOffset(), pagination.getOrderBy(), pagination.getOrderByColumn());
	}
	
	public int count() {
		return ComputerDAO.getInstance().count();
	}
	public int countSearch(String name) {
		return ComputerDAO.getInstance().countSearch(name);
	}
	
	public int search(String name) {
		return ComputerDAO.getInstance().countSearch(name);
	}
	
	public List<Computer> getByName(Pages pagination) {
		return ComputerDAO.getInstance().getByName(pagination.getSearch(), pagination.getLimit(), pagination.getOffset(), pagination.getOrderBy(), pagination.getOrderByColumn());
	}
	
	public void delete(Computer computer) {
		ComputerDAO.getInstance().delete(computer);
	}
}
