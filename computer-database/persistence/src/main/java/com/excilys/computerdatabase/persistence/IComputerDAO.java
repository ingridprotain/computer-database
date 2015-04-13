package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.Pages;

public interface IComputerDAO {
	public Computer find(int id);
	public void create(Computer computer);
	public void update(Computer computer);
	public void delete(Computer computer);
	public void deleteByCompanyId(int company_id);
	
	public int count();
	public int countSearch(String name);
	
	public List<Computer> getAll();
	public List<Computer> getAll(Pages pagination);
}
