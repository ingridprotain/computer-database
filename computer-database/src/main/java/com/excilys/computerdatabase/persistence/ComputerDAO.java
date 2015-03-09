package com.excilys.computerdatabase.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.Pages;

public class ComputerDAO implements IComputerDAO {
	
	private static ComputerDAO computerDAO = null;
	
	private ComputerDAO() {
		
	}
	
	public static ComputerDAO getInstance() {
		if (computerDAO == null) {
			computerDAO = new ComputerDAO();
		}
		return computerDAO;
	}
	
	private static String startGetAllQuery = "SELECT c.id, c.name, c.introduced, c.discontinued, c.company_id, co.name FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id";

	@Override
	public Computer find(int id) {
		Computer computer = null;
		
		String query = "SELECT * FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id WHERE c.id = ?";
		PreparedStatement stmt = null;
		ResultSet result = null;
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query);
			stmt.setInt(1, id);
			
			result = stmt.executeQuery();
			while (result.next()) {
				computer = ComputerMapper.convertToComputer(result);
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
		return computer;
	}
	
	@Override
	public Computer create(Computer computer) {
		String query;
		if (computer.getCompany() != null) {
			query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?,?,?,?);";
		} else { 
			query = "INSERT INTO computer(name, introduced, discontinued) VALUES(?,?,?);";
		}
		
		PreparedStatement stmt = null;
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, (computer.getIntroduced() == null ? null : Timestamp.valueOf(computer.getIntroduced())));
			stmt.setTimestamp(3, (computer.getDiscontinued() == null ? null : Timestamp.valueOf(computer.getDiscontinued())));
			//If computer is linked to a company
			if (computer.getCompany() != null) {
				stmt.setInt(4, computer.getCompany().getId());
			}
			//If no result, return null
			if (stmt.executeUpdate() == 0) {
				computer = null;
			}
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the creation of one computer in the database");
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the PreparedStatement");
				}
			}
		}
		return computer;
	}
	
	@Override
	public Computer update(Computer computer) {
		String query;
		if (computer.getCompany() != null) {
			query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?;";
		} else { 
			query = "UPDATE computer SET name=?, introduced=?, discontinued=? WHERE id=?;";
		}
		
		PreparedStatement stmt = null;
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(query);
			stmt.setString(1, computer.getName());
			stmt.setTimestamp(2, (computer.getIntroduced() == null ? null : Timestamp.valueOf(computer.getIntroduced())));
			stmt.setTimestamp(3, (computer.getDiscontinued() == null ? null : Timestamp.valueOf(computer.getDiscontinued())));
			if (computer.getCompany() != null) {
				stmt.setInt(4, computer.getCompany().getId());
				stmt.setInt(5, computer.getId());
			} else {
				stmt.setInt(4, computer.getId());
			}
			if (stmt.executeUpdate() == 0) {
				computer = null;
			}	
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the updating of one computer in the database");
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new IllegalStateException("Problem during closing the PreparedStatement");
				}
			}
		}
		return computer;
	}

	@Override
	public void delete(Computer computer) {
		PreparedStatement stmt = null;
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("DELETE FROM computer WHERE id=?;");
			stmt.setInt(1, computer.getId());
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
	
	@Override
	public void deleteByCompanyId(int company_id) {
		PreparedStatement stmt = null;
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("DELETE FROM computer WHERE company_id=?;");
			stmt.setInt(1, company_id);
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

	@Override
	public int count() {
		int count = 0;
		
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("SELECT COUNT(*) FROM computer");
			result = stmt.executeQuery();
			
			if (result.first()) {
				count = new Integer(result.getInt(1));
			}
			
		} catch (SQLException e) {
			DataSource.INSTANCE.rollback();
			throw new IllegalStateException("Problem during the recuperation of all computers in the database");
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
		
		return count;
	}
	
	@Override
	public int countSearch(String name) {
		int count = 0;
		
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement("SELECT COUNT(*) FROM computer AS c LEFT JOIN company AS co ON c.company_id = co.id WHERE c.name LIKE ? or co.name LIKE ?");
			stmt.setString(1, "%" + name + "%");
			stmt.setString(2, "%" + name + "%");
			
			result = stmt.executeQuery();
			
			if (result.first()) {
				count = new Integer(result.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during the recuperation of all computers in the database");
		} finally {
			if (result != null) {
				//this.closeConnection(cn);
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
		
		return count;
	}
	
	@Override
	public List<Computer> getAll() {
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		List<Computer> computers = new ArrayList<Computer>();
		
		try {
			stmt = DataSource.INSTANCE.getConnection().prepareStatement(startGetAllQuery);
			result = stmt.executeQuery();
			
			while(result.next()) {
				computers.add(ComputerMapper.convertToComputer(result));
			}
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during the recuperation of all computers in the database");
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
		
		return computers;
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
