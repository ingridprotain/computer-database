package com.excilys.computerdatabase.dto;

import com.excilys.computerdatabase.model.Company;

public class CompanyDTO {
	private int id;
	private String name;
	
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
	
	public CompanyDTO createCompanyDTO(Company c) {
		CompanyDTO cDTO = new CompanyDTO();
		if (c == null) {
			return null;
		}
		cDTO.id = c.getId();
		cDTO.name = c.getName();
		
		return cDTO;
	}
	
	public Company createCompany(CompanyDTO cDTO) {
		Company c = new Company();
		
		if (cDTO == null) {
			return null;
		}
		c.setId(cDTO.id);
		c.setName(cDTO.name);
		
		return c;
	}
}
