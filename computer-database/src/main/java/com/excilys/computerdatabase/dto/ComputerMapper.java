package com.excilys.computerdatabase.dto;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.utils.DateValidator;

final public class ComputerMapper {
	private ComputerMapper() {
		
	}
	
	public static ComputerDTO createComputerDTO(Computer computer) {
		Locale locale = LocaleContextHolder.getLocale();
		
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			if (locale.getLanguage() == "fr") {
				computerDTO.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern("d MMMM yyyy", locale)));
			} else {
				computerDTO.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern("MMM, dd yyyy", locale)));
			}
			
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

		computer.setId(computerDTO.getId());
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
