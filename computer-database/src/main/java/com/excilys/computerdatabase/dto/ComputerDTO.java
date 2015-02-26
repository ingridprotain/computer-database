package com.excilys.computerdatabase.dto;

import java.time.format.DateTimeFormatter;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.ui.DateValidator;

public class ComputerDTO {
	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO companyDTO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	public ComputerDTO createComputerDTO(Computer c) {
		ComputerDTO cDTO = new ComputerDTO();
		
		cDTO.id = c.getId();
		cDTO.name = c.getName();
		if (c.getIntroduced() != null) {
			cDTO.introduced = c.getIntroduced().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		if (c.getDiscontinued() != null) {
			cDTO.discontinued = c.getDiscontinued().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		}
		cDTO.companyDTO = new CompanyDTO().createCompanyDTO(c.getCompany());
		return cDTO;
	}
	
	public Computer createComputer(ComputerDTO cDTO) {
		Computer c = new Computer();

		c.setName(cDTO.name);
		c.setIntroduced(DateValidator.toLocalDateTime(cDTO.introduced));
		c.setDiscontinued(DateValidator.toLocalDateTime(cDTO.discontinued));
		
		c.setCompany(new CompanyDTO().createCompany(cDTO.companyDTO));
		
		return c;
		
	}
}
