package com.excilys.computerdatabase.persistence;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.Pages;

public class ComputerDAO implements IComputerDAO {
	
	@Autowired
	private DataSource dataSource;
	
	private String startGetAllQuery = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id";

	@Override
	public Computer find(int id) {
		String query = "SELECT * FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id WHERE c.id = ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		Computer computer = jdbcTemplate.queryForObject(query, new Object[]{id}, new ComputerMapper());
		return computer;
	}
	
	private Timestamp getTimestampValue(LocalDateTime date) {
		return (date == null ? null : Timestamp.valueOf(date));
	}
	
	@Override
	public void create(Computer computer) {
		String query;
		Object[] objectQuery;
		if (computer.getCompany() != null) {
			query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?,?,?,?);";
			objectQuery = new Object[]{computer.getName(), getTimestampValue(computer.getIntroduced()), getTimestampValue(computer.getDiscontinued()), computer.getCompany().getId()};
		} else { 
			query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
			objectQuery = new Object[]{computer.getName(), getTimestampValue(computer.getIntroduced()), getTimestampValue(computer.getDiscontinued())};
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, objectQuery);
	}
	
	
	@Override
	public void update(Computer computer) {
		String query;
		Object[] objectQuery;
		if (computer.getCompany() != null) {
			query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
			objectQuery = new Object[]{computer.getName(), getTimestampValue(computer.getIntroduced()), getTimestampValue(computer.getDiscontinued()), computer.getCompany().getId(), computer.getId()};
		} else { 
			query = "UPDATE computer SET name=?, introduced=?, discontinued=? WHERE id=?;";
			objectQuery = new Object[]{computer.getName(), getTimestampValue(computer.getIntroduced()), getTimestampValue(computer.getDiscontinued()), computer.getId()};
		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, objectQuery);
	}

	@Override
	public void delete(Computer computer) {
		String query = "DELETE FROM computer WHERE id=?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, new Object[]{computer.getId()});
	}
	
	@Override
	public void deleteByCompanyId(int company_id) {
		String query = "DELETE FROM computer WHERE company_id=?;";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(query, new Object[]{company_id});
	}

	@Override
	public int count() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT COUNT(*) FROM computer";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	@Override
	public int countSearch(String name) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT COUNT(*) FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id WHERE c.name LIKE ? or co.name LIKE ?";
		return jdbcTemplate.queryForObject(query, new Object[]{name, name}, Integer.class);
	}
	
	@Override
	public List<Computer> getAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate.query(startGetAllQuery, new ComputerMapper());
	}
	
	@Override
	public List<Computer> getAll(Pages pagination) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		StringBuilder query = new StringBuilder();
		query.append(startGetAllQuery);
		query.append(" ORDER BY ");
		query.append(pagination.getOrderByColumn());
		query.append(" ");
		query.append(pagination.getOrderBy());
		query.append(" LIMIT ? OFFSET ?");
		
		return jdbcTemplate.query(query.toString(), new Object[]{ pagination.getLimit(), pagination.getOffset()}, new ComputerMapper());
	}
	
	@Override
	public List<Computer> getByName(Pages pagination) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		StringBuilder query = new StringBuilder();
		query.append(startGetAllQuery);
		query.append(" WHERE c.name LIKE ? or co.name LIKE ?");
		query.append(" ORDER BY ");
		query.append(pagination.getOrderByColumn());
		query.append(" ");
		query.append(pagination.getOrderBy());
		query.append(" LIMIT ? OFFSET ?");

		return jdbcTemplate.query(query.toString(), new Object[]{"%" + pagination.getSearch() + "%", "%" + pagination.getSearch() + "%", pagination.getLimit(), pagination.getOffset()}, new ComputerMapper());
	}
}
