package com.excilys.computerdatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.service.CompanyService;

@Controller
@RequestMapping("/welcome")
public class TestServlet {

	@Autowired
	CompanyService companyService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView helloWorld() {
		
		List<Company> userList = companyService.getAll();  
		return new ModelAndView("userList", "userList", userList);  
	}
}
