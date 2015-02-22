package com.excilys.computerdatabase.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO extends DAO<Company> {

	@Override
	public Company find(int id) {
		Company company = null;
		
		String query = "SELECT id, name FROM company AS c WHERE c.id=" + id;
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			if (result.first()) {
				company = new Company();
				
				company.setId(new Integer(result.getInt(1)));
				company.setName(result.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return company;
	}

	@Override
	public Company create(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company obj) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Permet de récupérer toutes les companies en base
	 * @return une liste d'objet Company
	 */
	public List<Company> getListCompanies() {
		String query = "SELECT id, name FROM company";
		
		List<Company> companies = new ArrayList<Company>();
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			while(result.next()) {
				Company c = new Company();
				c.setId(new Integer(result.getInt(1)));
				c.setName(result.getString(2));
				companies.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return companies;
	}
}
