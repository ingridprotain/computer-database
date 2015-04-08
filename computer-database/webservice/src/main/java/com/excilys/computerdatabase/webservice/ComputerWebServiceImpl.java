package com.excilys.computerdatabase.webservice;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;

public class ComputerWebServiceImpl implements ComputerWebService {
	
	@Autowired
	private IComputerService computerService;
	
	@Override
	public ComputerDTO find() {
		Computer computer = computerService.find(1);
		
		return ComputerMapper.createComputerDTO(computer);
	}
}
