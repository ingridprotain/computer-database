package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;

public class ComputerDAO {
	
	private static ComputerDAO computerDAO = null;
	//private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	private ComputerDAO() {
		
	}
	
	public static ComputerDAO getInstance() {
		if (computerDAO == null) {
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}

	public Computer find(int id) {
		
		//logger.info("loog");
		Computer computer = null;
		
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name "
				+ "FROM computer AS c "
				+ "LEFT JOIN company AS co ON c.company_id = co.id "
				+ "WHERE c.id=" + id;
		
		Connection cn = null;
		Statement stmt = null;
		ResultSet result = null;
		
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.createStatement();
			result = stmt.executeQuery(query);
			while (result.next()) {
				computer = ComputerMapper.convertToComputer(result);
			}
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the recuperation of a computer in database");
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
		
		return computer;
	}

	public int count() {
		int count = 0;
		String query = "SELECT COUNT(*) FROM computer";
		
		Connection cn = null;
		Statement stmt = null;
		ResultSet result = null;
		
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.createStatement();
			result = stmt.executeQuery(query);
			
			if (result.first()) {
				count = new Integer(result.getInt(1));
			}
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the recuperation of the count of computers in database");
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
		
		return count;
	}

	public Computer create(Computer computer) {
		String query = null;

		if (computer.getCompany() != null) {
			query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?,?,?,?);";
		} else { 
			query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
		}
		
		Connection cn = null;
		PreparedStatement stmt = null;
		try {
			cn =  MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, (computer.getIntroduced() == null ? null : Timestamp.valueOf(computer.getIntroduced())));
			stmt.setTimestamp(3, (computer.getDiscontinued() == null ? null : Timestamp.valueOf(computer.getDiscontinued())));
			
			if (computer.getCompany() != null) {
				stmt.setInt(4, computer.getCompany().getId());
			}
			
			int result = stmt.executeUpdate();
			if (result == 0) {
				computer = null;
			}
			
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the creation of a computer in database");
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
		}
		
		return computer;
	}

	public Computer update(Computer computer) {
		String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
		
		Connection cn = null;
		PreparedStatement stmt = null;
		
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, (computer.getIntroduced() == null ? null : Timestamp.valueOf(computer.getIntroduced())));
			stmt.setTimestamp(3, (computer.getDiscontinued() == null ? null : Timestamp.valueOf(computer.getDiscontinued())));
			stmt.setInt(4, computer.getCompany().getId());
			stmt.setInt(5, computer.getId());
			
			int result = stmt.executeUpdate();
			if (result == 0) {
				computer = null;
			}
			
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the updating of a computer in database");
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
		}
		
		return computer;
	}

	public void delete(Computer computer) {
		String query = "DELETE FROM computer WHERE id=?;";
		
		PreparedStatement stmt = null;
		Connection cn = null;
		try {
			 cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			
			stmt.setInt(1, computer.getId());
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the deleting of a computer in database");
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
		}
	}
	
	/**
	 * Get all computers in the database
	 * @return a list of Computer
	 */
	public List<Computer> getAll(int limit, int offset, String orderBy) {
		if (orderBy == null) {
			orderBy = "ASC";
		}
		if (!orderBy.equals("DESC") && !orderBy.equals("ASC")) {
			orderBy = "ASC";
		} 
		String query = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name " +
			"FROM computer AS c " +
			"LEFT JOIN company AS co ON c.company_id = co.id " +
			"ORDER BY c.name " + orderBy + " " +
			"LIMIT ? OFFSET ?";
			

		List<Computer> computers = new ArrayList<Computer>();
		
		Connection cn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			
			//stmt.setString(1, "DESC");
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			result = stmt.executeQuery();
		    while (result.next()) {
		    	Computer c = ComputerMapper.convertToComputer(result);
		    	computers.add(c);
		    }
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during the recuperation of computers in database");
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
		}
		
		return computers;
	}
	
	/**
	 * Get all computers in the database by name
	 * @return a list of Computer
	 */
	public List<Computer> getByName(String name, int limit, int offset, String orderBy) {
		
		StringBuilder query = new StringBuilder("SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name ")
			.append("FROM computer AS c ")
			.append("LEFT JOIN company AS co ON c.company_id = co.id ")
			.append("WHERE c.name LIKE ?")
			.append(" LIMIT ? OFFSET ?");
		
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			Connection cn = MySqlConnect.getMySqlConnect().getMyInstance();
			PreparedStatement stmt = cn.prepareStatement(query.toString());
			stmt.setString(1, "%" + name + "%");
			stmt.setInt(2, limit);
			stmt.setInt(3, offset);
		    ResultSet result = stmt.executeQuery();
		    while (result.next()) {
		    	Computer c = ComputerMapper.convertToComputer(result);
		    	computers.add(c);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return computers;
	}
	
	public int countSearch(String name) {
		int count = 0;
		String query = "SELECT COUNT(*) FROM computer WHERE name LIKE ?";
		
		Connection cn = null;
		PreparedStatement stmt = null;
		
		try {
			cn = MySqlConnect.getMySqlConnect().getMyInstance();
			stmt = cn.prepareStatement(query);
			stmt.setString(1, "%" + name + "%");
			ResultSet result = stmt.executeQuery();
			if (result.first()) {
				count = new Integer(result.getInt(1));
			}
		} catch (SQLException e) {
			//Logs
			throw new IllegalStateException("Problem during the recuperation of the count of computers in database");
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
		}
		return count;
	}
}
