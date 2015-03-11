package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.model.Company;

import junit.framework.TestCase;

public class CompanyDAOTest extends TestCase {
	CompanyDAO companyDAO;
	
	protected void setUp() throws Exception {
		MySqlConnect.setTest(true);
		//companyDAO = CompanyDAO.getInstance();
	}
	
	public void testFind() {
		Company company = companyDAO.find(1);
		assertEquals("Apple Inc.", company.getName());

		assertNotNull(company.getName());
	}
	
	public void testGetAll() {
		List<Company> companies = companyDAO.getAll();
		
		for(Company company : companies) {
			assertNotNull(company.getId());
			assertNotNull(company.getName());
		}
	}
	
}
