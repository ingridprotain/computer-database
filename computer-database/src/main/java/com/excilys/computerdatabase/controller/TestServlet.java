package com.excilys.computerdatabase.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.computerdatabase.ui.SpringTest;

@Configurable
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SpringTest test;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	public TestServlet() {
		test = new SpringTest();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	       
        PrintWriter out=response.getWriter();
        out.println("<br>Test : "+ test.getMessage());
        out.close();		
	}

}
