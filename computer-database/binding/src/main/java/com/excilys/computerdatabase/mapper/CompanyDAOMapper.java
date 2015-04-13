package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAOMapper implements RowMapper<Company> {
	@Override
	public Company mapRow(ResultSet result, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(new Integer(result.getInt(1)));
		company.setName(result.getString(2));
		return company;
	}
}
