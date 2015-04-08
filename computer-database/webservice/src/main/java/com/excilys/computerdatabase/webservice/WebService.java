package com.excilys.computerdatabase.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("/find")
@Component
public class WebService {
	@Autowired
	private ComputerWebService computerWebService;
	
	@GET
	@Path("/computer")
	public Response find() {
 
		String result = computerWebService.find().toString();
		return Response.status(200).entity(result).build();
	}
}
