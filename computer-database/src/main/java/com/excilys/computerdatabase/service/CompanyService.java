package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;
import com.excilys.computerdatabase.persistence.DataSource;

public class CompanyService extends AbstractService<Company>{

	@Override
	public Company findAbstract(int id) {
		return CompanyDAO.getInstance().find(id);
	}

	@Override
	public Company createAbstract(Company company) {
		throw new UnsupportedOperationException("We cannot create a company");
	}

	@Override
	public Company updateAbstract(Company company) {
		throw new UnsupportedOperationException("We cannot update a company");
	}

	@Override
	public void deleteAbstract(Company company) {
		//CompanyDAO.getInstance().delete(company);
		
	}

	@Override
	public List<Company> getAllAbstract() {
		return CompanyDAO.getInstance().getAll();
	}
	
	public void delete(Company company) {
		DataSource.INSTANCE.initTransaction();
		DataSource.INSTANCE.getConnection();
		ComputerDAO.getInstance().deleteByCompanyId(company.getId());
		CompanyDAO.getInstance().delete(company);
		DataSource.INSTANCE.closeConnection();
		DataSource.INSTANCE.closeTransaction();
	}
}
