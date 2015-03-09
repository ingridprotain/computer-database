package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;

public class CompanyDAO implements ICompanyDAO {
	private static CompanyDAO companyDAO = null;
	
	private CompanyDAO() {
		
	}
	
	public static CompanyDAO getInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
		}
		return companyDAO;
	}
	
	@Override
	public Company find(int id) {
		Company company = null;
		
		String query = "SELECT id, name FROM company WHERE id=?";

		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			if (result.next()) {
				company = CompanyMapper.convertToCompany(result);
			}
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the recuperation of a computer in the database");
		} finally {
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
		return company;
	}
	
	@Override
	public List<Company> getAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<Company> companies = new ArrayList<Company>();
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("SELECT id, name FROM company");
			result = stmt.executeQuery();
			
			while(result.next()) {
				companies.add(CompanyMapper.convertToCompany(result));
			}
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the recuperation of all companies in the database");
		} finally {
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
		return companies;
	}

	@Override
	public void delete(Company company) {
		PreparedStatement stmt = null;
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("DELETE FROM company WHERE id=?;");
			stmt.setInt(1, company.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the deleting of a computer in the database");
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the PreparedStatement");
				}
			}
		}
	}
	

}
