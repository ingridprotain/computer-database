package com.excilys.computerdatabase.utils;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;

final public class ComputerDTOValidator {
	
	private static CompanyService companyService = new CompanyService();
	
	private ComputerDTOValidator() {
	}

	public static List<String> validate(ComputerDTO computerDTO) {
		List<String> errors = new ArrayList<String>();
		
		if (computerDTO.getName() == null || computerDTO.getName().equals("")) {
			errors.add("Please enter a computer name");
		}
		
		if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
			if (!DateValidator.isDate(computerDTO.getIntroduced())) {
				errors.add("Please enter a correct introduced date to the format mm/dd/YYYY");
			}
		}
		
		if (computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
			if (!DateValidator.isDate(computerDTO.getDiscontinued())) {
				errors.add("Please enter a correct discontinued date to the format mm/dd/YYYY");
			}
		}

		if (computerDTO.getCompanyId() != 0) {
			Company company = companyService.find(computerDTO.getCompanyId());
			if (company == null) {
				errors.add("The company doesn't exist");
			}
		}
		
		return errors;
	}
}
