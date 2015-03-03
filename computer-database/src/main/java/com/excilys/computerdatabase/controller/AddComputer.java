package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.CompanyMapper;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.utils.ComputerDTOValidate;

@SuppressWarnings("serial")
public class AddComputer extends HttpServlet {

	private static CompanyService companyService = new CompanyService();
	private static ComputerService computerService = new ComputerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Company> companies = companyService.getAll();
		
		//Creation of the DTOs
		List<CompanyDTO> companiesDTO = new ArrayList<CompanyDTO>();
		for (Company company : companies) {
			companiesDTO.add(CompanyMapper.createCompanyDTO(company));
		}
		
		req.setAttribute("companiesDTO", companiesDTO);
		
		this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//Received a computerDTO from the client
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(req.getParameter("computerName"));
		computerDTO.setIntroduced(req.getParameter("introduced"));
		computerDTO.setDiscontinued(req.getParameter("discontinued"));
		computerDTO.setCompanyId(Integer.valueOf(req.getParameter("companyId")));
		
		//If the computerDTO is valid, we add the computer to the database
		List<String> errors = ComputerDTOValidate.validate(computerDTO);
		if (errors.isEmpty()) {
			Computer computer = ComputerMapper.createComputer(computerDTO);
			computer = computerService.create(computer);
			req.setAttribute("message", "The computer " + computer.getName() + " has been well added");
		//Else, we return a list of errors
		} else {
			req.setAttribute("errors", errors);
		}
		
		this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

}
