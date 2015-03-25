package com.excilys.computerdatabase.dto;

import com.excilys.computerdatabase.model.Company;

final public class CompanyMapper {
	private CompanyMapper() {
		
	}
	
	public static CompanyDTO createCompanyDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company == null) {
			return null;
		}
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		
		return companyDTO;
	}
	
	public static Company createCompany(CompanyDTO companyDTO) {
		Company company = new Company();
		
		if (companyDTO == null) {
			return null;
		}
		company.setId(companyDTO.getId());
		company.setName(companyDTO.getName());
		
		return company;
	}
}
