package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.ui.Pages;

public class ListOfComputer extends HttpServlet{
	
	private static Pages pages = new Pages("Computer");
	private static ComputerService computerService = new ComputerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String pagination = req.getParameter("page");
		
		List<ComputerDTO> computers;
		if (pagination != null && pagination.equals("next")) {
			computers = pages.next();
		} else if (pagination != null && pagination.equals("prev")) {
			computers = pages.prev();
		} else if (req.getParameter("search") != null) {
			computers = computerService.getByName(req.getParameter("search"));
		} else {
			computers = pages.first();
		}
		
		req.setAttribute("totalComputers", pages.getTotal());
		req.setAttribute("computers", computers);
		this.getServletContext().getRequestDispatcher("/static/views/dashboard.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
