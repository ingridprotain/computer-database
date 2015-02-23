package com.excilys.computerdatabase.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerDAO extends DAO<Computer>{

	@Override
	public Computer find(int id) {
		Computer computer = null;
		
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name "
				+ "FROM computer AS c "
				+ "LEFT JOIN company AS co ON c.company_id = co.id "
				+ "WHERE c.id=" + id;
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			if (result.first()) {
				computer = new Computer();
				
				computer.setId(new Integer(result.getInt(1)));
				computer.setName(result.getString(2));
				computer.setIntroduced(result.getTimestamp(3));
				computer.setDiscontinued(result.getTimestamp(4));
				
				//Si l'ordinateur est lié à une compagnie
				if (new Integer(result.getInt(5)) != null) {
					Company company = new Company();
					company.setId((result.getInt(5)));
					company.setName(result.getString(6));
					computer.setCompany(company);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computer;
	}

	@Override
	public Computer create(Computer computer) {

		PreparedStatement stmt = null;
		String query = null;
		
		if (computer.getCompany() != null)
			query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?,?,?,?);";
		else 
			query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
		try {
			stmt = MySqlConnect.getMySqlConnect().cn.prepareStatement(query);
			
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, computer.getIntroduced());
			stmt.setTimestamp(3, computer.getDiscontinued());
			if (computer.getCompany() != null) 
				stmt.setInt(4, computer.getCompany().getId());
			//TODO company
			
			int result = stmt.executeUpdate();
			if (result == 0) {
				computer = null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computer;
	}

	@Override
	public Computer update(Computer computer) {
		PreparedStatement stmt = null;
		
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
		
		try {
			stmt = MySqlConnect.getMySqlConnect().cn.prepareStatement(query);
			
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, computer.getIntroduced());
			stmt.setTimestamp(3, computer.getDiscontinued());
			stmt.setInt(4, computer.getCompany().getId());
			stmt.setInt(5, computer.getId());
			//TODO company
			
			int result = stmt.executeUpdate();
			if (result == 0) {
				computer = null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computer;
	}

	@Override
	public void delete(Computer computer) {
		PreparedStatement stmt = null;
		
		String query = "DELETE FROM computer WHERE id=?;";
		
		try {
			stmt = MySqlConnect.getMySqlConnect().cn.prepareStatement(query);
			
			stmt.setInt(1, computer.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de récupérer tous les computers en base
	 * @return une liste d'objet Computer
	 */
	public List<Computer> getListComputers() {
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name "
				+ "FROM computer AS c "
				+ "LEFT JOIN company AS co ON c.company_id = co.id ";
		
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			while(result.next()) {
				Computer c = new Computer();
				c.setId(new Integer(result.getInt(1)));
				c.setName(result.getString(2));
				c.setIntroduced(result.getTimestamp(3));
				c.setDiscontinued(result.getTimestamp(4));
				
				if (new Integer(result.getInt(5)) != null) {
					Company co = new Company();
					co.setId(new Integer(result.getInt(5)));
					co.setName(result.getString(6));
					c.setCompany(co);
				}
				computers.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computers;
	}
}
