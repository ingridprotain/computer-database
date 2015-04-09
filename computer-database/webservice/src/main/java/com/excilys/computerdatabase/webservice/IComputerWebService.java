package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;

public interface IComputerWebService {
	ComputerDTO find(int param);
	List<ComputerDTO> getAll();
	public void insert(ComputerDTO computerDTO);
}
