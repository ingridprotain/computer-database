package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.utils.Pages;

@SuppressWarnings("serial")
public class ListOfComputer extends HttpServlet{
	
	private static ComputerService computerService = new ComputerService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<Computer> computers;
		
		//Set the pagination
		Pages pagination = new Pages();
		String limitString = req.getParameter("limit");
		String pageString = req.getParameter("page");
		String search = req.getParameter("search");
		int count;
		
		if (limitString != null) {
			pagination.setLimit(Integer.valueOf(limitString));
		}
		if (pageString != null) {
			pagination.setPage(Integer.valueOf(pageString));
		}
		pagination.setOffset(pagination.getPage() * pagination.getLimit() -pagination.getLimit());
		if (req.getParameter("orderBy") != null) {
			pagination.setOrderBy(req.getParameter("orderBy"));
		}
		if (req.getParameter("orderByColumn") != null) {
			pagination.setOrderByColumn(req.getParameter("orderByColumn"));
		}
		if (search != null) {
			pagination.setSearch(search);
			count = computerService.countSearch(search);
			computers = computerService.getByName(search, pagination.getLimit(), pagination.getOffset(), pagination.getOrderBy(), pagination.getOrderByColumn());
		} else {
			count = computerService.count();
			computers = computerService.getAll(pagination.getLimit(), pagination.getOffset(), pagination.getOrderBy(), pagination.getOrderByColumn());
		}
		pagination.setTotalPages(count / pagination.getLimit());
		
		//get the computers to display
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerMapper.createComputerDTO(c));
		}
		
		req.setAttribute("page", pagination);
		req.setAttribute("computers", cDTOs);
		req.setAttribute("count", count);
		
		this.getServletContext().getRequestDispatcher("/static/views/dashboard.jsp").forward(req, resp);
	}
}
