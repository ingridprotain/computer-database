package com.excilys.computerdatabase.webservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;

@RestController
@RequestMapping("/computer")
public class ComputerWebService {
	
	@Autowired
	private IComputerService computerService;
	
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public ComputerDTO find(@PathVariable int id) {
		Computer computer = computerService.find(id);
		if (computer != null) {
			return ComputerMapper.createComputerDTO(computer);
		} else {
			return null;
		}
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<ComputerDTO> getAll() {
		List<Computer> computers = computerService.getAll();
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerMapper.createComputerDTO(c));
		}
		return cDTOs;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@PathVariable ComputerDTO computerDTO) {
//		Computer computer = ComputerMapper.createComputer(computerDTO);
//		if (computer.getId() == 0) {
//			computerService.create(computer);
//		} else {
//			computerService.update(computer);
//		}
//		return "";
		Computer computer = new Computer();
		computer.setName("aaaaa");
		computerService.create(computer);
		return null;
	}
	
	
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		Computer computer = computerService.find(id);
		if (computer != null) {
			computerService.delete(computer);
		}
	}
}
