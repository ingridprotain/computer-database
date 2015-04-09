package com.excilys.computerdatabase.webservice;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.IComputerService;

@Component
@Path("/computer")
public class ComputerWebService implements IComputerWebService{
	
	@Autowired
	private IComputerService computerService;
	
	public ComputerWebService() {
	}
	
	@Override
	@GET
	@Path("/find/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ComputerDTO find(@PathParam("id") int id) {
		return ComputerMapper.createComputerDTO(computerService.find(id));
	}

	@Override
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ComputerDTO> getAll() {
		List<Computer> computers = computerService.getAll();
		List<ComputerDTO> cDTOs = new ArrayList<ComputerDTO>();
		for (Computer c : computers) {
			cDTOs.add(ComputerMapper.createComputerDTO(c));
		}
		return cDTOs;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(ComputerDTO computerDTO) {
		Computer computer = ComputerMapper.createComputer(computerDTO);
		computerService.create(computer);
	}
}
