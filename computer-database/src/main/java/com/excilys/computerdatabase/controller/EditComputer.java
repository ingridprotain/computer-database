package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.CompanyMapper;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ICompanyService;
import com.excilys.computerdatabase.service.IComputerService;
import com.excilys.computerdatabase.utils.Validatable;

@Controller
@RequestMapping("/editComputer")
public class EditComputer {
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IComputerService computerService;
	
	@Autowired
	private Validatable<ComputerDTO> computerDtoValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(
			ModelMap model,
			@RequestParam(value = "computerId", required = false) String id) {

		List<Company> companies = companyService.getAll();
		ComputerDTO computerDTO = new ComputerDTO();
		
		int computerId = 0;
		if (id != null) {
			computerId = Integer.valueOf(id);
		}

		//Creation of the DTOs
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		for (Company company : companies) {
			companiesDTO.add(CompanyMapper.createCompanyDTO(company));
		}
		
		//If isset computerId then we edit
		if (computerId != 0) {
			Computer computer = computerService.find(computerId);
			if (computer != null) {
				computerDTO = ComputerMapper.createComputerDTO(computer);
			}
		}

		model.addAttribute("computerDTO", computerDTO);
		model.addAttribute("companiesDTO", companiesDTO);
		
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			ModelMap model,
			@RequestParam(value = "computerName", required = true) String computerName,
			@RequestParam(value = "introduced", required = true) String introduced,
			@RequestParam(value = "discontinued", required = true) String discontinued,
			@RequestParam(value = "companyId", required = true) String companyId,
			@RequestParam(value = "computerId", required = false) String computerId) {

		//Received a computerDTO from the client
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(computerName);
		computerDTO.setIntroduced(introduced);
		computerDTO.setDiscontinued(discontinued);
		computerDTO.setCompanyId(Integer.valueOf(companyId));
		
		//Edit
		if (computerId != null) {
			computerDTO.setId(Integer.valueOf(computerId));
		}
		
		//If the computerDTO is valid, we add the computer to the database
		List<String> errors = computerDtoValidator.validate(computerDTO);
		if (errors.isEmpty()) {
			Computer computer = ComputerMapper.createComputer(computerDTO);
			//Create
			if (computer.getId() == 0) {
				computerService.create(computer);
			//Edit
			} else {
				computerService.update(computer);
			}
			return "redirect:dashboard";
		//Else, we return a list of errors
		} else {
			List<Company> companies = companyService.getAll();
			List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
			for (Company company : companies) {
				companiesDTO.add(CompanyMapper.createCompanyDTO(company));
			}
			model.addAttribute("companiesDTO", companiesDTO);
			model.addAttribute("errors", errors);
			model.addAttribute("computerDTO", computerDTO);
			
			return "editComputer";
		}
	}

}
