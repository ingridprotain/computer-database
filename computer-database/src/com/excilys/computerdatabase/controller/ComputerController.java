package com.excilys.computerdatabase.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistance.ComputerDAO;
import com.excilys.computerdatabase.ui.Main.ErrorMessage;

public class ComputerController extends Controller {
	
	private DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
	private ComputerDAO computerDao;
	
	private CompanyController companyController = new CompanyController();
	
	public ComputerController() {
		computerDao = new ComputerDAO();
	}
	
	public Computer checkIfComputerExist(int id) throws EntityNotExistException{
		Computer computer = computerDao.find(id);
		if (computer == null)
			throw new EntityNotExistException("computer");
		else 
			return computer;
	}
	
	public Computer checkComputerIntroduced(Computer computer, String introduced_string) {
		
		try {
			Date computer_date_i = format.parse(introduced_string);
			computer.setIntroduced(new Timestamp(computer_date_i.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(ErrorMessage.NOT_DATE);
		}
		return computer;
	}
	
	public Computer checkComputerDiscontinued(Computer computer, String discontinued_string) {
		try {
			Date computer_date_d = format.parse(discontinued_string);
			computer.setDiscontinued(new Timestamp(computer_date_d.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(ErrorMessage.NOT_DATE);
		}
		
		return computer;
	}
	
	public Computer checkComputerCompany(Computer computer, String company_id_s) {
		try {
			int company_id = Integer.valueOf(company_id_s);
			try {
				Company company = companyController.checkIfCompanyExist(company_id);
				computer.setCompany(company);
			} catch (EntityNotExistException ce) {
				// TODO Auto-generated catch block
				ce.getStackTrace();
			}
		} catch (NumberFormatException e){
			System.out.println(ErrorMessage.COMPANY_NOT_INT);
		}
		return computer;
	}
	
	public Computer create(Computer computer){
		computer = computerDao.create(computer);
		return computer;
	}
	
	public void delete(Computer computer) {
		computerDao.delete(computer);
	}
	
	public List<Computer> getListComputers() {
		return computerDao.getListComputers();
	}
	
	public Computer update(Computer computer) {
		computer = computerDao.update(computer);
		return computer;
	}
}
