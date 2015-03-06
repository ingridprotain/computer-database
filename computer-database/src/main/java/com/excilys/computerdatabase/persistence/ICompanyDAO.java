package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

public interface ICompanyDAO {
	public Company find(int id);
	public List<Company> getAll();
}
