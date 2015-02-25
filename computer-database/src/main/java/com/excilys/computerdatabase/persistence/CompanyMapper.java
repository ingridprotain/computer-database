package com.excilys.computerdatabase.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.computerdatabase.model.Company;

public class CompanyMapper {
	public static Company convertToCompany(ResultSet result) {
		Company company = null;
		try {
			if (result != null) {
				company = new Company();
				
				company.setId(new Integer(result.getInt(1)));
				company.setName(result.getString(2));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during the mapping of the Company object");
		}
		return company;
	}
}
