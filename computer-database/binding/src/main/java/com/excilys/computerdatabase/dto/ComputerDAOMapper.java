package com.excilys.computerdatabase.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;

public class ComputerDAOMapper implements RowMapper<Computer> {
	@Override
	public Computer mapRow(ResultSet result, int rowNum) throws SQLException {
		Computer computer = new Computer();
		computer.setId(new Integer(result.getInt(1)));
		computer.setName(result.getString(2));
		if (result.getTimestamp(3) == null) {
			computer.setIntroduced(null);
		} else {
			computer.setIntroduced(result.getTimestamp(3).toLocalDateTime());
		}
		
		if (result.getTimestamp(4) == null) {
			computer.setDiscontinued(null);
		} else {
			computer.setDiscontinued(result.getTimestamp(4).toLocalDateTime());
		}
		
		//If the computer is linked to one company
		if (new Integer(result.getInt(5)) != null) {
			Company company = new Company();
			company.setId((result.getInt(5)));
			company.setName(result.getString(6));
			computer.setCompany(company);
		}
		return computer;
	}
}
