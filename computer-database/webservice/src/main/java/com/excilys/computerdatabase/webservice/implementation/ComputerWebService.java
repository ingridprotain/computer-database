package com.excilys.computerdatabase.webservice.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.mapper.ComputerDTOMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;
import com.excilys.computerdatabase.webservice.IComputerWebService;

@RestController
@RequestMapping("/computer")
public class ComputerWebService implements IComputerWebService {
	
	@Autowired
	private IComputerService computerService;
	
	@Override
	@RequestMapping(value="/find/{id}", method = RequestMethod.GET)
	public ComputerDTO find(@PathVariable int id) {
		Computer computer = computerService.find(id);
		if (computer != null) {
			return ComputerDTOMapper.createComputerDTO(computer);
		} else {
			return null;
		}
	}
	
	@Override
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<ComputerDTO> getAll() {
		List<Computer> computers = computerService.getAll();
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerDTOMapper.createComputerDTO(c));
		}
		return cDTOs;
	}
	
	@Override
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public String edit(@PathVariable ComputerDTO computerDTO) {
		//TODO fix that
		Computer computer = ComputerDTOMapper.createComputer(computerDTO);
		if (computer.getId() == 0) {
			computerService.create(computer);
		} else {
			computerService.update(computer);
		}
		return "";
	}

	@Override
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		Computer computer = computerService.find(id);
		if (computer != null) {
			computerService.delete(computer);
		}
	}
}
