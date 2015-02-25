package com.excilys.computerdatabase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class AddComputer extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Computer computer = new Computer();
		computer.setName(req.getParameter("computerName"));
		
		ComputerDAO.getInstance().create(computer);

		//this.getServletContext().getRequestDispatcher("/static/views/addComputer.jsp").forward(req, resp);
	}

}
