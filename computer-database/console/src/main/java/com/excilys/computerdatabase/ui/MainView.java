package com.excilys.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ICompanyService;
import com.excilys.computerdatabase.service.IComputerService;

@Component
public class MainView {
	
	public Scanner scan = new Scanner(System.in);
	
	@Autowired
	private IComputerService computerService;
	@Autowired
	private ICompanyService companyService;
	
	private Client client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	private WebTarget target = client.target("http://localhost:8080/webservice/rest/computer");
	
	public void start() {
		String choixUser = ""; 
		while (!choixUser.equals("exit"))
		{
			System.out.println("\nHello friend! Tap a command or consult the help : -h");
			choixUser = scan.nextLine();
			switch (choixUser) {
			case "-h":
				System.out.println("Help:");
				System.out.println("1. Consult computer's list: -l computers");
				System.out.println("2. Consult company's list: -l companies");
				System.out.println("3. Consult a computer's details: -l computer");
				System.out.println("4. Create a computer: -c computer");
				System.out.println("5. Update a computer: -u computer");
				System.out.println("6. Delete a computer: -d computer");
				System.out.println("7. Delete a company with related computers: -d company");
				System.out.println("Exit: exit");
				break;

			//Display the computers list
			case "-l computers":
			case "1":
				getAllComputers();
				break;
				
			//Display the companies list
			case "-l companies":
			case "2":
				getAllCompanies();
				break;
			
			//Display one computer's details
			case "-l computer":
			case "3":
				String computer_id = userChoice("Identifiant of the computer to research ?");
				getComputer(computer_id);
				break;
				
			case "-l company":
//				int company_id = userChoice("Identifiant of the company to research ?");
//				getCompany(company_id);
				break;
				
			//Add a computer
			case "-c computer":
			case "4":
				createComputer();
				break;

			//Update a computer
			case "-u computer":
			case "5":
				break;
			
			//Delete a computer
			case "-d computer":
			case "6":
//				int computer_del_id = userChoice("Identifiant of the computer to delete ?");
//				deleteComputer(computer_del_id);
				break;
			
			//Delete a company with computers related
			case "-d company":
			case "7":
//				int company_del_id = userChoice("Identifiant of the company to delete ?");
//				deleteCompany(company_del_id);
				break;
			
			default:
				break;
			}
		}
	}
	
	
	public void getComputer(String id) {
		ComputerDTO computerDTO = target.path("find").path(id).request(MediaType.APPLICATION_JSON).get(new GenericType<ComputerDTO>() {});
		System.out.println(computerDTO.toString());
	}
	
	public void getCompany(int id) {
		
	}
	
	public void getAllComputers() {
		List<ComputerDTO> computerDTOs = target.path("all").request(MediaType.APPLICATION_JSON).get(new GenericType<List<ComputerDTO>>() {});
		for(ComputerDTO cDTO : computerDTOs) {
			System.out.println(cDTO.toString());
		}
	}
	
	public void getAllCompanies() {
		System.out.println("List companies");
		List<Company> companies = companyService.getAll();
		for (Company c : companies) {
			System.out.println(c);
		}
	}
	
	public void deleteComputer(int id) {
		Computer computer = computerService.find(id);
		if (computer != null) {
			computerService.delete(computer);
			System.out.println("Computer deleted");
		} else {
			System.out.println("Computer doesn't exist");
		}
	}
	
	public void deleteCompany(int id) {
		Company company = companyService.find(id);
		if (company != null) {
			companyService.delete(company);
			System.out.println("Company and computers related deleted");
		} else {
			System.out.println("Company doesn't exist");
		}
	}
	
	public void createComputer() {
		String name = userChoice("Name of the computer?");
		editComputer(name);
	}
	
	public void updateComputer() {
		String name = userChoiceWithConfirm("Edit the name of the computer? Y/N", "Name : ");
		editComputer(name);
	}
	
	
	public void editComputer(String name)
	{
		
		String introduced = userChoiceWithConfirm("Edit the introduction date (format = mm/jj/aaaa)? Y/N", "Introduced in : ");
		String discontinued = userChoiceWithConfirm("Edit the discontinued date (format = mm/jj/aaaa)? Y/N", "Discontinued in : ");
		String company = userChoiceWithConfirm("Edit the company? Y/N", "Company's id: ");
		
		ComputerDTO computerDTO = new ComputerDTO();
		if (name != null) {
			computerDTO.setName(name);
		}
		if (introduced != null) {
			computerDTO.setIntroduced(introduced);
		}
		if (discontinued != null) {
			computerDTO.setDiscontinued(discontinued);
		}
		if (company != null) {
			computerDTO.setCompanyId(Integer.valueOf(company));
		}
		
		Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(computerDTO, MediaType.APPLICATION_JSON));
		if (response.getStatus() != 200 && response.getStatus() != 204) {
			System.out.println("Error " + response.getStatus());
		}
	}
	
	private boolean userConfirm() {
		String sConfirm = scan.nextLine();
		if (sConfirm.equals("Y") || sConfirm.equals("y")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String userChoice (String message) {
		System.out.println(message);
		return scan.nextLine();
	}
	
	public String userChoiceWithConfirm(String message, String submessage) {
		System.out.println(message);
		if (userConfirm() == true) {
			System.out.print(submessage);
			return scan.nextLine();
		}
		return null;
	}
}
