package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.ui.DateValidator;

@SuppressWarnings("serial")
public class AddComputer extends HttpServlet {

	private static CompanyService companyService = new CompanyService();
	private static ComputerService computerService = new ComputerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<CompanyDTO> companiesDTO = companyService.getAll();
		
		req.setAttribute("companiesDTO", companiesDTO);
		
		this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("message", null);
		
		List<String> errors = new ArrayList<String>();
		
		ComputerDTO cDTO = new ComputerDTO();
		cDTO.setName(req.getParameter("computerName"));
		String introduced = req.getParameter("introduced");
		String discontinued = req.getParameter("discontinued");
		
		
		if (DateValidator.isValid(introduced, "introduced")) {
			cDTO.setIntroduced(introduced);
		} else {
			errors.addAll(DateValidator.getErrors());
		}
		
		if (DateValidator.isValid(discontinued, "discontinued")) {
			cDTO.setDiscontinued(discontinued);
		} else {
			errors.addAll(DateValidator.getErrors());
		}
		
		if (!errors.isEmpty()) {
			req.setAttribute("errors", errors);
		} else {
			CompanyDTO companyDTO = companyService.find(Integer.valueOf(req.getParameter("companyId")));
			cDTO.setCompanyDTO(companyDTO);
			
			computerService.create(cDTO);
			req.setAttribute("message", "The computer " + cDTO.getName() + " has been well added");
		}

		this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

}
