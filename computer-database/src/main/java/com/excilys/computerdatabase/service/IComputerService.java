package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.Pages;

public interface IComputerService {
	public Computer find(int id);
	public void create(Computer computer);
	public void update(Computer computer);
	public void delete(Computer computer);
	
	public List<Computer> getAll();
	public List<Computer> getAll(Pages pagination);
	
	public int count();
	public int countSearch(String name);
}
