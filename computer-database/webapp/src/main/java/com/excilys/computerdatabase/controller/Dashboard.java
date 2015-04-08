package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;
import com.excilys.computerdatabase.utils.Pages;

@Controller
public class Dashboard {

	@Autowired
	private IComputerService computerService;
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	protected String getListOfComputer(
			ModelMap model,
			@RequestParam(value = "limit", required = false) String limitString,
			@RequestParam(value = "page", required = false) String pageString,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "orderByColumn", required = false) String orderByColumn) {

		List<Computer> computers;
		int count;
		
		//Set the pagination
		Pages pagination = new Pages();
		
		if (limitString != null) {
			pagination.setLimit(Integer.valueOf(limitString));
		}
		if (pageString != null) {
			pagination.setPage(Integer.valueOf(pageString));
		}
		pagination.setOffset(pagination.getPage() * pagination.getLimit() -pagination.getLimit());
		if (orderBy != null) {
			pagination.setOrderBy(orderBy);
		}
		if (orderByColumn != null) {
			pagination.setOrderByColumn(orderByColumn);
		}
		if (search != null) {
			pagination.setSearch(search);
			count = computerService.countSearch(search);
		} else {
			count = computerService.count();
		}
		computers = computerService.getAll(pagination);
		
		pagination.setTotalPages(count / pagination.getLimit());
		
		//get the computers to display
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerMapper.createComputerDTO(c));
		}
		
		model.addAttribute("page", pagination);
		model.addAttribute("computers", cDTOs);
		model.addAttribute("count", count);

		return "dashboard";
	}

	@RequestMapping(value="/admin/deleteComputers", method = RequestMethod.POST)
	protected String doPost(
			@RequestParam(value = "selection", required = true) String selection) {

		for (String field : selection.split(",")) {
			int id = Integer.parseInt(field);
			Computer computer = computerService.find(id);
			if (computer != null) {
				computerService.delete(computer);
			}
		}
		
		return "redirect:dashboard";
	}
}
