package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.CompanyDTOMapper;
import com.excilys.computerdatabase.mapper.ComputerDTOMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ICompanyService;
import com.excilys.computerdatabase.service.IComputerService;

@Controller
@RequestMapping("/admin/editComputer")
public class EditComputer {
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IComputerService computerService;
	
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
			companiesDTO.add(CompanyDTOMapper.createCompanyDTO(company));
		}
		
		//If isset computerId then we edit
		if (computerId != 0) {
			Computer computer = computerService.find(computerId);
			if (computer != null) {
				computerDTO = ComputerDTOMapper.createComputerDTO(computer);
			}
		}

		model.addAttribute("computerDTO", computerDTO);
		model.addAttribute("companiesDTO", companiesDTO);
		
		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(
			ModelMap model,
			@Valid ComputerDTO computerDTO,
			BindingResult result) {
		
		if (result.hasErrors()) {
			List<Company> companies = companyService.getAll();
			List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
			for (Company company : companies) {
				companiesDTO.add(CompanyDTOMapper.createCompanyDTO(company));
			}
			model.addAttribute("companiesDTO", companiesDTO);
			model.addAttribute("computerDTO", computerDTO);
			return "editComputer";
		} else {
			Computer computer = ComputerDTOMapper.createComputer(computerDTO);
			//Create
			if (computer.getId() == 0) {
				computerService.create(computer);
			//Edit
			} else {
				computerService.update(computer);
			}
			return "redirect:/dashboard";
		}
	}

}
