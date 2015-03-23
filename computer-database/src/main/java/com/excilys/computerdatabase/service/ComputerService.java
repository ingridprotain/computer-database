package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.IComputerDAO;
import com.excilys.computerdatabase.utils.Pages;

@Service
public class ComputerService implements IComputerService{
	
	@Autowired
	private IComputerDAO computerDao;

	@Override
	public Computer find(int id) {
		return computerDao.find(id);
	}

	@Override
	public void create(Computer computer) {
		computerDao.create(computer);
	}

	@Override
	public void update(Computer computer) {
		computerDao.update(computer);
	}

	@Override
	public void delete(Computer computer) {
		computerDao.delete(computer);
	}

	@Override
	public List<Computer> getAll() {
		return computerDao.getAll();
	}

	@Override
	public List<Computer> getAll(Pages pagination) {
		return computerDao.getAll(pagination);
	}
	
	@Override
	public int count() {
		return computerDao.count();
	}

	@Override
	public int countSearch(String name) {
		return computerDao.countSearch(name);
	}

}
