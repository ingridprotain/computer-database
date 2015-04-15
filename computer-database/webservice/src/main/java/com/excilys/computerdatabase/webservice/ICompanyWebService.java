package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.CompanyDTO;

public interface ICompanyWebService {
	
	/**
	 * get all the companies in database
	 * @return List<CompanyDTO>
	 */
	List<CompanyDTO> getAll();
	
	/**
	 * delete a company and all computers related to the company
	 * @param id the id of the company to delete
	 */
	public void delete(int id);
	
	/**
	 * Search a company 
	 * @param id the id to the company to research
	 * @return CompanyDTO if company with the id selected exists, else return null
	 */
	public CompanyDTO find(int id);
}
