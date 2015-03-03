package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO {
	private static CompanyDAO companyDAO = null;
	
	private CompanyDAO() {
		
	}
	
	public static CompanyDAO getInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	
	public Company find(int id) {
		Company company = null;
		
		String query = "SELECT id, name FROM company WHERE id=?";
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;

		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			stmt.setInt(1, id);
		    result = stmt.executeQuery();
		    if (result.next()) {
		    	company = CompanyMapper.convertToCompany(result);
		    }
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the recuperation of a company in database");
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of connection to the database");
			}
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the Statement");
			}
			
			try {
				result.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the ResultSet");
			}
		}
		
		return company;
	}
	
	/**
	 * Get all companies in database
	 * @return a list of Company
	 */
	public List<Company> getAll() {
		String query = "SELECT id, name FROM company";
		
		List<Company> companies = new ArrayList<Company>();
		Connection cn = null;
		Statement stmt = null;
		ResultSet result = null;
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.createStatement();
		    result = stmt.executeQuery(query);
		    while(result.next()) {
		    	Company c = CompanyMapper.convertToCompany(result);
		    	companies.add(c);
		    }
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the recuperation of list of companies in database");
		} finally {
			try {
				cn.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of connection to the database");
			}
			
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the Statement");
			}
			
			try {
				result.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the ResultSet");
			}
		}
		
		return companies;
	}
}
