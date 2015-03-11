package com.excilys.computerdatabase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.utils.Pages;

@Controller
@RequestMapping("/dashboard")
public class ListOfComputer {

	private static ComputerService computerService = new ComputerService();
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView doGet(
			@RequestParam(value = "limit", required = false) String limitString,
			@RequestParam(value = "page", required = false) String pageString,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "orderByColumn", required = false) String orderByColumn) {
		
		ModelAndView model = new ModelAndView("dashboard");
		
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
			computers = computerService.getByName(pagination);
		} else {
			count = computerService.count();
			computers = computerService.getAll(pagination);
		}
		pagination.setTotalPages(count / pagination.getLimit());
		
		//get the computers to display
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerMapper.createComputerDTO(c));
		}
		
		model.addObject("page", pagination);
		model.addObject("computers", cDTOs);
		model.addObject("count", count);
		
		return model;
	}
}
