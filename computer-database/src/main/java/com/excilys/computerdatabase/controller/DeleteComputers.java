package com.excilys.computerdatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;

@SuppressWarnings("serial")
public class DeleteComputers extends HttpServlet {
	
	private static ComputerService computerService = new ComputerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		for (String field : req.getParameter("selection").split(",")) {
			int id = Integer.parseInt(field);
			Computer computer = computerService.find(id);
			if (computer != null) {
				computerService.delete(computer);
			}
		}
		resp.sendRedirect("/computer-database/dashboard");
	}

}
