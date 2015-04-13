package com.excilys.computerdatabase.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.CompanyMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.ICompanyService;

@RestController
@RequestMapping("/company")
public class CompanyWebService {
	
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<CompanyDTO> getAll() {
		List<Company> companies = companyService.getAll();
		List<CompanyDTO> cDTOs = new ArrayList<CompanyDTO>();
		for (Company c : companies) {
			cDTOs.add(CompanyMapper.createCompanyDTO(c));
		}
		return cDTOs;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		Company company = companyService.find(id);
		if (company != null) {
			companyService.delete(company);
		}
	}
	
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public CompanyDTO find(@PathVariable int id) {
		Company company = companyService.find(id);
		if (company != null) {
			return CompanyMapper.createCompanyDTO(company);
		} else {
			return null;
		}
	}
}
