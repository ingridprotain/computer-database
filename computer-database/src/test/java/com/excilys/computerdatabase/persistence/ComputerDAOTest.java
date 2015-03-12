package com.excilys.computerdatabase.persistence;

import com.excilys.computerdatabase.model.Computer;

import junit.framework.TestCase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComputerDAOTest extends TestCase {
	ComputerDAO computerDAO;
	static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	protected void setUp() throws Exception {
		MySqlConnect.setTest(true);
	}
	
	public void testFind() {
		
		String dateIntroduced = "1980-05-01 00:00";
		String dateDiscontinued = "1984-04-01 00:00";
		
		//Test on one computer
		Computer computer = computerDAO.find(1);
		assertEquals("Test1", computer.getName());
		assertEquals(LocalDateTime.parse(dateIntroduced, format), computer.getIntroduced());
		assertEquals(LocalDateTime.parse(dateDiscontinued, format), computer.getDiscontinued());
		assertEquals(1, computer.getCompany().getId());
		
		//ComputerName cannot be null
		assertNotNull(computer.getName());
	}
	
	public void testCount() {
		int result = computerDAO.count();
		
		assertEquals(2, result);
		assertNotNull(result);
	}
	
	public void testCreate() {
		Computer computerCreate = new Computer(); 
		computerCreate.setName("Test create");
		computerCreate.setIntroduced(LocalDateTime.parse("2015-04-01 00:00", format));
		computerCreate.setIntroduced(null);
		
		computerDAO.create(computerCreate);
		
		/*List<Computer> computers = null;//computerDAO.getAll(computerDAO.count(), 0, "ASC");
		Computer computerSelect = computers.get(computers.size()-1);
		assertEquals(computerCreate.getName(), computerSelect.getName());
		assertEquals(computerCreate.getIntroduced(), computerSelect.getIntroduced());
		assertEquals(computerCreate.getDiscontinued(), computerSelect.getDiscontinued());
		assertEquals(computerCreate.getCompany(), null);*/
	}
	
	public void testUpdate() {
		
	}
	
	public void testDelete() {
		//List<Computer> computers = null;//computerDAO.getAll(computerDAO.count(), 0, "ASC");
		/*Computer computerDelete = computers.get(computers.size()-1);
		
		computerDAO.delete(computerDelete);
		
		assertEquals(computerDAO.count(), 2);*/
	}
	
	public void testGetAll() {
		//List<Computer> computers = null;//computerDAO.getAll(computerDAO.count(), 0, "ASC");
		//assertEquals(computers.size(), computerDAO.count());
	}
	
	public void testGetByName() {
		//List<Computer> computersByName = computerDAO.getByName("test2", computerDAO.count(), 0, "ASC");
		//assertEquals(computersByName.size(), 1);
	}
	
	public void testCountSearch() {
		int result = computerDAO.countSearch("test2");
		assertEquals(result, 1);
	}
}
