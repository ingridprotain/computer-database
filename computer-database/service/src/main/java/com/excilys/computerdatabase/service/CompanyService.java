package com.excilys.computerdatabase.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.ICompanyDAO;
import com.excilys.computerdatabase.persistence.IComputerDAO;

@Service
public class CompanyService implements ICompanyService{
	@Autowired
	private ICompanyDAO companyDao;
	
	@Autowired
	private IComputerDAO computerDao;

	@Override
	public Company find(int id) {
		return companyDao.find(id);
	}

	@Override
	public List<Company> getAll() {
		return companyDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Company company) {
		computerDao.deleteByCompanyId(company.getId());
		companyDao.delete(company);
	}
}
