package com.excilys.computerdatabase.persistence;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO implements ICompanyDAO {
	@Autowired
	private DataSource dataSource;
	
	@Override
	public Company find(int id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT id, name FROM company WHERE id=?";
		Company company = jdbcTemplate.queryForObject(query, new Object[] {id}, new CompanyMapper());
		return company;
	}

	@Override
	public List<Company> getAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT id, name FROM company";
		return jdbcTemplate.query(query, new CompanyMapper());
	}

	@Override
	public void delete(Company company) {
		String query = "DELETE FROM company WHERE id=?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, new Object[]{company.getId()});
	}
}
