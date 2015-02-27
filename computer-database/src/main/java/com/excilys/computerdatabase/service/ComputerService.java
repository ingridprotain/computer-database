package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class ComputerService {
	
	private static ComputerDTO computerDTO = new ComputerDTO();
	
	public ComputerDTO create(ComputerDTO cDTO){
		Computer computer = computerDTO.createComputer(cDTO);
		computer = ComputerDAO.getInstance().create(computer);
		cDTO = computerDTO.createComputerDTO(computer);
		return cDTO;
	}
	
	public ComputerDTO find(int id) {
		return computerDTO.createComputerDTO(ComputerDAO.getInstance().find(id));
	}
	
	public List<ComputerDTO> getAll(int limit, int offset) {
		List<Computer> computers = ComputerDAO.getInstance().getAll(limit, offset);
		List<ComputerDTO> computerDTOs = new ArrayList<ComputerDTO>();
		
		if (computers != null) {
			for (Computer c : computers) {
				computerDTOs.add(computerDTO.createComputerDTO(c));
			}
		}
		return computerDTOs;
	}
	
	public int count() {
		return ComputerDAO.getInstance().count();
	}
	
	public int countSearch(String name) {
		return ComputerDAO.getInstance().countSearch(name);
	}
	
	public List<ComputerDTO> getByName(String name, int limit, int offset) {
		List<Computer> computers = ComputerDAO.getInstance().getByName(name, limit, offset);
		List<ComputerDTO> computerDTOs = new ArrayList<ComputerDTO>();
		
		if (computers != null) {
			for (Computer c : computers) {
				computerDTOs.add(computerDTO.createComputerDTO(c));
			}
		}
		return computerDTOs;
	}
}
