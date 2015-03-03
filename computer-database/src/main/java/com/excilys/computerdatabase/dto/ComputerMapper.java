package com.excilys.computerdatabase.dto;

import java.time.format.DateTimeFormatter;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.DateValidator;

final public class ComputerMapper {
	private ComputerMapper() {
		
	}
	
	public static ComputerDTO createComputerDTO(Computer computer) {
		ComputerDTO computerDTO = new ComputerDTO();
		
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			computerDTO.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		}
		if (computer.getDiscontinued() != null) {
			computerDTO.setDiscontinued(computer.getDiscontinued().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
		}
		computerDTO.setCompanyId(computer.getCompany().getId());
		computerDTO.setCompanyName(computer.getCompany().getName());
		return computerDTO;
	}
	
	public static Computer createComputer(ComputerDTO computerDTO) {
		Computer computer = new Computer();

		computer.setName(computerDTO.getName());
		computer.setIntroduced(DateValidator.toLocalDateTime(computerDTO.getIntroduced()));
		computer.setDiscontinued(DateValidator.toLocalDateTime(computerDTO.getDiscontinued()));
		
		if (computerDTO.getCompanyId() != 0) {
			Company company = new Company();
			company.setId(computerDTO.getCompanyId());
			company.setName(computerDTO.getCompanyName());
			computer.setCompany(company);
		}
		
		return computer;
	}
}
