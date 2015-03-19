package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.Pages;

@Repository
public class ComputerDAO implements IComputerDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Computer find(int id) {
		return (Computer) sessionFactory.getCurrentSession().get(Computer.class, id);
	}

	@Override
	public void create(Computer computer) {
		sessionFactory.getCurrentSession().save(computer);
	}
	
	@Override
	public void update(Computer computer) {
		sessionFactory.getCurrentSession().update(computer);
	}

	@Override
	public void delete(Computer computer) {
		sessionFactory.getCurrentSession().delete(computer);
	}
	
	@Override
	public void deleteByCompanyId(int company_id) {
	}

	@Override
	public int count() {
		return 0;
	}
	
	@Override
	public int countSearch(String name) {
		return 0;
	}
	
	@Override
	public List<Computer> getAll() {
		return new ArrayList<Computer>();
	}
	
	@Override
	public List<Computer> getAll(Pages pagination) {
		return new ArrayList<Computer>();
	}
	
	@Override
	public List<Computer> getByName(Pages pagination) {
		return new ArrayList<Computer>();
	}
}
