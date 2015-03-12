package com.excilys.computerdatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;

@Controller
@RequestMapping("/deleteComputers")
public class DeleteComputers {
	
	@Autowired
	private IComputerService computerService;

	@RequestMapping(method = RequestMethod.POST)
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
