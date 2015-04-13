package com.excilys.computerdatabase.webservice.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.mapper.CompanyDTOMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.ICompanyService;
import com.excilys.computerdatabase.webservice.ICompanyWebService;

@RestController
@RequestMapping("/company")
public class CompanyWebService implements ICompanyWebService {
	
	@Autowired
	private ICompanyService companyService;
	
	@Override
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<CompanyDTO> getAll() {
		List<Company> companies = companyService.getAll();
		List<CompanyDTO> cDTOs = new ArrayList<CompanyDTO>();
		for (Company c : companies) {
			cDTOs.add(CompanyDTOMapper.createCompanyDTO(c));
		}
		return cDTOs;
	}
	
	@Override
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		Company company = companyService.find(id);
		if (company != null) {
			companyService.delete(company);
		}
	}
	
	@Override
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public CompanyDTO find(@PathVariable int id) {
		Company company = companyService.find(id);
		if (company != null) {
			return CompanyDTOMapper.createCompanyDTO(company);
		} else {
			return null;
		}
	}
}
