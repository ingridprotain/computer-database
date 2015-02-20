package com.excilys.computerdatabase.persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public class ComputerDAO extends DAO<Computer>{

	@Override
	public Computer find(int id) {
		Computer computer = null;
		
		String query = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id=" + id;
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			if (result.first()) {
				computer = new Computer();
				
				computer.setId(new Integer(result.getInt(1)));
				computer.setName(result.getString(2));
				computer.setIntroduced(result.getTimestamp(3));
				computer.setDiscontinued(result.getTimestamp(4));
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
		
		String query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
		
		try {
			stmt = MySqlConnect.getMySqlConnect().cn.prepareStatement(query);
			
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, computer.getIntroduced());
			stmt.setTimestamp(3, computer.getDiscontinued());
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
	public Computer update(Computer obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Computer obj) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Permet de récupérer tous les computers en base
	 * @return une liste d'objet Computer
	 */
	public List<Computer> getListComputers() {
		String query = "SELECT id, name, introduced, discontinued, company_id FROM computer";
		
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			ResultSet result = MySqlConnect.getMySqlConnect().selectQuery(query);
			while(result.next()) {
				Computer c = new Computer();
				c.setId(new Integer(result.getInt(1)));
				c.setName(result.getString(2));
				c.setIntroduced(result.getTimestamp(3));
				c.setDiscontinued(result.getTimestamp(4));
				computers.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computers;
	}
}
