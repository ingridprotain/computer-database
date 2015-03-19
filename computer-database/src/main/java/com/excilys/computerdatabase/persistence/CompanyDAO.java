package com.excilys.computerdatabase.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.model.Company;

@Repository
public class CompanyDAO implements ICompanyDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Company find(int id) {
		return (Company) sessionFactory.getCurrentSession().get(Company.class, id);
	}

	@Override
	public List<Company> getAll() {
		return new ArrayList<Company>();
	}

	@Override
	public void delete(Company company) {
	}
}
