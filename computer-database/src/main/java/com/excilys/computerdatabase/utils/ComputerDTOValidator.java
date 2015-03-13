package com.excilys.computerdatabase.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.ICompanyService;

public class ComputerDTOValidator implements Validatable<ComputerDTO>{
	
	@Autowired
	private ICompanyService companyService;
	
	@Override
	public List<String> validate(ComputerDTO computerDTO) {
		List<String> errors = new ArrayList<String>();
		
		if (computerDTO.getName() == null || computerDTO.getName().equals("")) {
			errors.add("Please enter a computer name");
		}
		
		if (computerDTO.getIntroduced() != null && !computerDTO.getIntroduced().equals("")) {
			if (!DateValidator.isDate(computerDTO.getIntroduced())) {
				errors.add("Please enter a correct introduced date to the format " + DateValidator.getPattern(LocaleContextHolder.getLocale()));
			}
		}
		
		if (computerDTO.getDiscontinued() != null && !computerDTO.getDiscontinued().equals("")) {
			if (!DateValidator.isDate(computerDTO.getDiscontinued())) {
				errors.add("Please enter a correct discontinued date to the format " + DateValidator.getPattern(LocaleContextHolder.getLocale()));
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
