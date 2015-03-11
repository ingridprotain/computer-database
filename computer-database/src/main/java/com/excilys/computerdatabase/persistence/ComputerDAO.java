package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
	
	@Override
	public void create(Computer computer) {
		String query;
		Object[] objectQuery;
		if (computer.getCompany() != null) {
			query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?,?,?,?);";
			objectQuery = new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId()};
		} else { 
			query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
			objectQuery = new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued()};
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
			objectQuery = new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), computer.getId()};
		} else { 
			query = "UPDATE computer SET name=?, introduced=?, discontinued=? WHERE id=?;";
			objectQuery = new Object[]{computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getId()};
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
		String query = "SELECT COUNT(*) FROM computer";
		return 0;
	}
	
	@Override
	public int countSearch(String name) {
		String query = "SELECT COUNT(*) FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id WHERE c.name LIKE ? or co.name LIKE ?";
		return 0;
	}
	
	@Override
	public List<Computer> getAll() {
		return null;
	}
	
	@Override
	public List<Computer> getAll(Pages pagination) {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<Computer> computers = new ArrayList<Computer>();
		
		StringBuilder query = new StringBuilder();
		query.append(startGetAllQuery);
		query.append(" ORDER BY ");
		query.append(pagination.getOrderByColumn());
		query.append(" ");
		query.append(pagination.getOrderBy());
		query.append(" LIMIT ? OFFSET ?");
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query.toString());
			stmt.setInt(1, pagination.getLimit());
			stmt.setInt(2, pagination.getOffset());
			result = stmt.executeQuery();
			
			while(result.next()) {
				computers.add(ComputerMapper.convertToComputer(result));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during the recuperation of computers in the database");
		} finally {
			//this.closeConnection(cn);
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the ResultSet");
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the PreparedStatement");
				}
			}
		}
		return computers;
	}
	
	@Override
	public List<Computer> getByName(Pages pagination) {
		PreparedStatement stmt = null;
		ResultSet result = null;

		StringBuilder query = new StringBuilder();
		query.append(startGetAllQuery);
		query.append(" WHERE c.name LIKE ? or co.name LIKE ?");
		query.append(" ORDER BY ");
		query.append(pagination.getOrderByColumn());
		query.append(" ");
		query.append(pagination.getOrderBy());
		query.append(" LIMIT ? OFFSET ?");
		
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query.toString());
			stmt.setString(1, "%" + pagination.getSearch() + "%");
			stmt.setString(2, "%" + pagination.getSearch() + "%");
			stmt.setInt(3, pagination.getLimit());
			stmt.setInt(4, pagination.getOffset());
			result = stmt.executeQuery();
			
			while(result.next()) {
				computers.add(ComputerMapper.convertToComputer(result));
			}
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the recuperation of computers in the database");
		} finally {
			//this.closeConnection(cn);
			if (result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the ResultSet");
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the PreparedStatement");
				}
			}
		}
		return computers;
	}
}
