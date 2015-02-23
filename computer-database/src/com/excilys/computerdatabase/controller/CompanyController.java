package com.excilys.computerdatabase.controller;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistance.CompanyDAO;

public class CompanyController {
	private CompanyDAO companyDao;
	
	public CompanyController() {
		companyDao = new CompanyDAO();
	}
	
	public Company checkIfCompanyExist(int id) throws EntityNotExistException{
		Company company = companyDao.find(id);
		if (company == null)
			throw new EntityNotExistException("company");
		else 
			return company;
	}
}
