package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.DataSource;
import com.excilys.computerdatabase.utils.Pages;

public class ComputerService extends AbstractService<Computer>{

	@Override
	public Computer findAbstract(int id) {
		return ComputerDAO.getInstance().find(id);
	}

	@Override
	public Computer createAbstract(Computer object) {
		return ComputerDAO.getInstance().create(object);
	}

	@Override
	public Computer updateAbstract(Computer object) {
		return ComputerDAO.getInstance().update(object);
	}

	@Override
	public void deleteAbstract(Computer object) {
		ComputerDAO.getInstance().delete(object);
		
	}

	@Override
	public List<Computer> getAllAbstract() {
		return ComputerDAO.getInstance().getAll();
	}
	
	public List<Computer> getAll(Pages pagination) {
		DataSource.INSTANCE.getConnection();
		List<Computer> computers = ComputerDAO.getInstance().getAll(pagination);
		DataSource.INSTANCE.closeConnection();
		return computers;
	}
	
	public List<Computer> getByName(Pages pagination) {
		DataSource.INSTANCE.getConnection();
		List<Computer> computers = ComputerDAO.getInstance().getByName(pagination);
		DataSource.INSTANCE.closeConnection();
		return computers;
	}
	
	public int count() {
		DataSource.INSTANCE.getConnection();
		int count = ComputerDAO.getInstance().count();
		DataSource.INSTANCE.closeConnection();
		return count;
	}
	
	public int countSearch(String name) {
		DataSource.INSTANCE.getConnection();
		int count = ComputerDAO.getInstance().countSearch(name);
		DataSource.INSTANCE.closeConnection();
		return count;
	}
}
