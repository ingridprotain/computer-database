package com.excilys.computerdatabase.mapper;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.util.DateValidator;

final public class ComputerDTOMapper {
	private ComputerDTOMapper() {
		
	}
	
	/**
	 * Convert a computer to a computerDTO
	 * @param computer
	 * @return ComputerDTO
	 */
	public static ComputerDTO createComputerDTO(Computer computer) {
		
		//Get the locale language of the user to display the dates
		Locale locale = LocaleContextHolder.getLocale();
		
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setId(computer.getId());
		computerDTO.setName(computer.getName());
		if (computer.getIntroduced() != null) {
			if (locale.getLanguage() == "fr") {
				computerDTO.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern("dd/MM/yyyy", locale)));
			} else {
				computerDTO.setIntroduced(computer.getIntroduced().format(DateTimeFormatter.ofPattern("MM/dd/yyyy", locale)));
			}
			
		}
		if (computer.getDiscontinued() != null) {
			if (locale.getLanguage() == "fr") {
				computerDTO.setDiscontinued(computer.getDiscontinued().format(DateTimeFormatter.ofPattern("dd/MM/yyyy", locale)));
			} else {
				computerDTO.setDiscontinued(computer.getDiscontinued().format(DateTimeFormatter.ofPattern("MM/dd/yyyy", locale)));
			}
		}
		if (computer.getCompany() != null) {
			computerDTO.setCompanyId(computer.getCompany().getId());
			computerDTO.setCompanyName(computer.getCompany().getName());
		}
		return computerDTO;
	}
	
	/**
	 * Convert a computerDTO to a computer
	 * @param computerDTO
	 * @return Computer
	 */
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
