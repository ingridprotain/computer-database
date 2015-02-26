package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.persistence.CompanyDAO;

public class CompanyService {
	private static CompanyDTO companyDTO = new CompanyDTO();
	
	public List<CompanyDTO> getAll() {
		List<Company> companies = CompanyDAO.getInstance().getAll();
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		
		if (companies != null) {
			for (Company c : companies) {
				companyDTOs.add(companyDTO.createCompanyDTO(c));
			}
		}
		return companyDTOs;
	}
	
	public CompanyDTO find(int id) {
		Company company = CompanyDAO.getInstance().find(id);
		CompanyDTO cDTO = companyDTO.createCompanyDTO(company);
		
		return cDTO;
	}
}
