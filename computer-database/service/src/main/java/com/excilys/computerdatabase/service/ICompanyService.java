package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyService {
	public Company find(int id);
	public List<Company> getAll();
	public void delete(Company company);
}
