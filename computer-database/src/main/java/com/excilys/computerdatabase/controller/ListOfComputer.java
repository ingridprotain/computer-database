package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.ui.Page;

@SuppressWarnings("serial")
public class ListOfComputer extends HttpServlet{
	
	//private static Pages pages = new Pages("Computer");
	private static Page pagination = new Page();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		
		//Type of request ? search or getAll request?
		if (req.getParameter("search") != null) {
			pagination.setActualRequest("search");
			pagination.setSearchParam(req.getParameter("search"));
			req.setAttribute("search", req.getParameter("search"));
		} else {
			pagination.setActualRequest("getAll");
		}
		
		//Set limit of pagination
		if (limit != null) {
			if (Integer.valueOf(limit) != null) {
				pagination.setLimit(Integer.valueOf(limit));
			}
		}
		
		//Page
		if (page != null) {
			if (page.equals("last")) {
				cDTOs = pagination.last();
			} else if (page.equals("first")) {
				cDTOs = pagination.first();
			} else if (page.equals("next")) {
				cDTOs = pagination.next();
			} else if (page.equals("prev")) {
				cDTOs = pagination.prev();
			} else if (Integer.valueOf(page) != null) {
				cDTOs = pagination.getByPage(Integer.valueOf(page));
			}
		} else {
			cDTOs = pagination.first();
		}
		
		//Attribute to the jsp page
		req.setAttribute("actualPage", pagination.getActualPage());
		req.setAttribute("totalPages", pagination.getTotalPages());
		req.setAttribute("totalComputers", pagination.getCount());
		req.setAttribute("computers", cDTOs);
		
		this.getServletContext().getRequestDispatcher("/static/views/dashboard.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
