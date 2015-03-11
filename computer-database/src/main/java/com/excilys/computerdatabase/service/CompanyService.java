package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ICompanyDAO;

@Service
public class CompanyService implements ICompanyService{
	@Autowired
	private ICompanyDAO companyDao;

	@Override
	public Company find(int id) {
		// TODO Auto-generated method stub
		return companyDao.find(id);
	}

	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub
		
	}
}
