package com.excilys.computerdatabase.utils;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

import junit.framework.TestCase;

public class ComputerDTOValidatorTest extends TestCase {
	public void testValidate() {
		ComputerDTO computerDTO = new ComputerDTO();
		
		//empty name could not be valid
		computerDTO.setName("");
		List<String> errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//null name could not be valid
		computerDTO.setName(null);
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//computer with a name is valid
		computerDTO.setName("with name");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertTrue(errors.isEmpty());
		
		//empty date is valid
		computerDTO.setIntroduced("");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertTrue(errors.isEmpty());
		
		//null date is valid
		computerDTO.setIntroduced(null);
		errors = ComputerDTOValidator.validate(computerDTO);
		assertTrue(errors.isEmpty());
		
		//false date (with azerty caracteres)
		computerDTO.setIntroduced("azerty");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//false date (with number)
		computerDTO.setIntroduced("123");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//false date (with wrong date)
		computerDTO.setIntroduced("40/40/1994");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//false date (with wrong format : it's MM/dd/YYYY not dd/MM/YYYY)
		computerDTO.setIntroduced("25/12/1994");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//valid date
		computerDTO.setIntroduced("03/25/1994");
		errors = ComputerDTOValidator.validate(computerDTO);
		assertTrue(errors.isEmpty());
		
		//false company (the company doesn't exist in database
		computerDTO.setCompanyId(5000);
		errors = ComputerDTOValidator.validate(computerDTO);
		assertFalse(errors.isEmpty());
		
		//valid date
		computerDTO.setCompanyId(2);
		errors = ComputerDTOValidator.validate(computerDTO);
		assertTrue(errors.isEmpty());
	}
}
