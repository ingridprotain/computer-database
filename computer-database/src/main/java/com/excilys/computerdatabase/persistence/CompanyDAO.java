package com.excilys.computerdatabase.persistence;

import java.beans.PropertyVetoException;
import java.io.IOException;
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
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				throw new IllegalStateException("Problem during connection to the database");
			}
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
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				throw new IllegalStateException("Problem during connection to the database");
			}
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

	/**
	 * Delete a company with all computers related to this company
	 */
	public void delete(Company company) {
		String deleteComputersByCompanyQuery = "DELETE FROM computer WHERE company_id = ?";
		String deleteCompany = "DELETE FROM company WHERE id = ?";
		
		Connection cn = null;
		PreparedStatement deleteComputersStmt = null;
		PreparedStatement deleteCompanyStmt = null;

		try {
			try {
				cn = DataSource.getInstance().getConnection();
			} catch (IOException | PropertyVetoException e) {
				throw new IllegalStateException("Problem during connection to the database");
			}
			
			cn.setAutoCommit(false);
			
			deleteComputersStmt = cn.prepareStatement(deleteComputersByCompanyQuery);
			deleteCompanyStmt = cn.prepareStatement(deleteCompany);
			
			deleteComputersStmt.setInt(1, company.getId());
			deleteCompanyStmt.setInt(1, company.getId());
			
			deleteComputersStmt.executeUpdate();
			deleteCompanyStmt.executeUpdate();
			cn.commit();
		} catch (SQLException e) {
			if (cn != null) {
				try {
					cn.rollback();
				} catch (SQLException e1) {
					throw new IllegalStateException("Problem during the closing of transaction");
				}
			}
		} finally {
			try {
				cn.setAutoCommit(true);
				cn.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of connection to the database");
			}
			
			try {
				deleteComputersStmt.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the Statement");
			}
			
			try {
				deleteCompanyStmt.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing of the Statement");
			}
		}
	}
}
