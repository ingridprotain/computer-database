package com.excilys.computerdatabase.mapper;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.model.Company;

final public class CompanyDTOMapper {
	private CompanyDTOMapper() {
		
	}
	
	/**
	 * Convert a company to a companyDTO
	 * @param company
	 * @return CompanyDTO
	 */
	public static CompanyDTO createCompanyDTO(Company company) {
		CompanyDTO companyDTO = new CompanyDTO();
		if (company == null) {
			return null;
		}
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		
		return companyDTO;
	}
	
	/**
	 * Convert a companyDTO to a company
	 * @param companyDTO
	 * @return Company
	 */
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
