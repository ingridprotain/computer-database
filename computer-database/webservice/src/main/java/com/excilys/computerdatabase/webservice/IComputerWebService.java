package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

public interface IComputerWebService {
	ComputerDTO find( int id);
	List<ComputerDTO> getAll();
	String edit(ComputerDTO computerDTO);
	void delete(int id);
}
