package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.Pages;

public interface IComputerDAO {
	public Computer find(int id);
	public Computer create(Computer computer);
	public Computer update(Computer computer);
	public void delete(Computer computer);
	
	public int count();
	public int countSearch(String name);
	
	public List<Computer> getAll();
	public List<Computer> getAll(Pages pagination);
	public List<Computer> getByName(Pages pagination);
}
