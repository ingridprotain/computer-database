package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

public class CompanyService {
	
	public List<Company> getAll() {
		return CompanyDAO.getInstance().getAll();
	}
	
	public Company find(int id) {
		return CompanyDAO.getInstance().find(id);
	}
}
