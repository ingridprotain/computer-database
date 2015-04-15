package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

public interface IComputerWebService {
	
	/**
	 * Search a computer 
	 * @param id the id to the computer to research
	 * @return ComputerDTO if computer with the id selected exists, else return null
	 */
	ComputerDTO find( int id);
	
	/**
	 * get all the computers in database
	 * @return List<ComputerDTO>
	 */
	List<ComputerDTO> getAll();
	
	/**
	 * Edit a computer; if computer doesn't exit it inserts a new computer; else computer exit it updates the computer
	 * @param computerDTO to edit
	 * @return a message
	 */
	String edit(ComputerDTO computerDTO);
	
	/**
	 * Delete a computer in database
	 * @param id the id of the computer to delete
	 */
	void delete(int id);
}
