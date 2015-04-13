package com.excilys.computerdatabase.ui;

import java.util.Scanner;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.excilys.computerdatabase.dto.CompanyDTO;
import com.excilys.computerdatabase.dto.ComputerDTO;

@Component
public class MainView {
	
	private Scanner scan = new Scanner(System.in);
	private RestTemplate restTemplate = new RestTemplate();
	
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
				getComputer();
				break;
				
			case "-l company":
				getCompany();
				break;
				
			//Add a computer
			case "-c computer":
			case "4":
				createComputer();
				break;

			//Update a computer
			case "-u computer":
			case "5":
				updateComputer();
				break;
			
			//Delete a computer
			case "-d computer":
			case "6":
				deleteComputer();
				break;
			
			//Delete a company with computers related
			case "-d company":
			case "7":
				deleteCompany();
				break;
			
			default:
				break;
			}
		}
	}
	
	
	public void getComputer() {
		String id = userChoice("Identifiant of the computer to research ?");
		ComputerDTO computerDTO = restTemplate.getForObject("http://localhost:8080/webservice/rest/computer/find/"+id, ComputerDTO.class);
		System.out.println(computerDTO.toString());
	}
	
	public void getCompany() {
		String id = userChoice("Company id to research ?");
		CompanyDTO companyDTO = restTemplate.getForObject("http://localhost:8080/webservice/rest/company/find/"+id, CompanyDTO.class);
		System.out.println(companyDTO.toString());
	}
	
	public void getAllComputers() {
		ComputerDTO[] arrayComputerDTOs = restTemplate.getForObject("http://localhost:8080/webservice/rest/computer/all", ComputerDTO[].class);
		for(ComputerDTO cDTO : arrayComputerDTOs) {
			System.out.println(cDTO.toString());
		}
	}
	
	public void getAllCompanies() {
		CompanyDTO[] arrayCompanyDTOs = restTemplate.getForObject("http://localhost:8080/webservice/rest/company/all", CompanyDTO[].class);
		for(CompanyDTO cDTO : arrayCompanyDTOs) {
			System.out.println(cDTO.toString());
		}
	}
	
	public void deleteComputer() {
		String id = userChoice("Computer id to delete ?");
		ComputerDTO computerDTO = restTemplate.getForObject("http://localhost:8080/webservice/rest/computer/find/"+id, ComputerDTO.class);
		if (computerDTO != null) {
			restTemplate.delete("http://localhost:8080/webservice/rest/computer/delete/"+id);
			System.out.println("Computer deleted");
		} else {
			System.out.println("Computer doesn't exist");
		}
	}
	
	public void deleteCompany() {
		String id = userChoice("Company id to delete ?");
		CompanyDTO companyDTO = restTemplate.getForObject("http://localhost:8080/webservice/rest/company/find/"+id, CompanyDTO.class);
		if (companyDTO != null) {
			restTemplate.delete("http://localhost:8080/webservice/rest/company/delete/"+id);
			System.out.println("Company and related computers deleted");
		} else {
			System.out.println("Company doesn't exist");
		}
	}
	
	public void createComputer() {
		String name = userChoice("Name of the computer?");
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(name);
		editComputer(computerDTO);
		restTemplate.postForObject("http://localhost:8080/webservice/rest/computer/edit", computerDTO, String.class);
	}
	
	public void updateComputer() {
		String id = userChoice("Computer id ?");
		ComputerDTO computerDTO = restTemplate.getForObject("http://localhost:8080/webservice/rest/computer/find/"+id, ComputerDTO.class);
		
		if (computerDTO != null) {
			String name = userChoiceWithConfirm("Edit the name of the computer? Y/N (" + computerDTO.getName() + ")", "Name : ");
			if (name != null) {
				computerDTO.setName(name);
			}
			editComputer(computerDTO);
			restTemplate.postForObject("http://localhost:8080/webservice/rest/computer/edit", computerDTO, ComputerDTO.class);
		}
		else {
			System.out.println("Computer doesn't exist");
		}
		
	}
	
	public void editComputer(ComputerDTO computerDTO)
	{
		String introduced = userChoiceWithConfirm("Edit the introduction date? Y/N " + (computerDTO.getIntroduced() == null ? "" : "(" + computerDTO.getIntroduced() + ")"), "Introduced in : ");
		String discontinued = userChoiceWithConfirm("Edit the discontinued date? Y/N " + (computerDTO.getDiscontinued() == null ? "" : "(" + computerDTO.getDiscontinued() + ")"), "Discontinued in : ");
		String company = userChoiceWithConfirm("Edit the company? Y/N " + (computerDTO.getCompanyName() == null ? "" : "(" + computerDTO.getCompanyName() + ")"), "Company's id: ");
		
		if (introduced != null) {
			computerDTO.setIntroduced(introduced);
		}
		if (discontinued != null) {
			computerDTO.setDiscontinued(discontinued);
		}
		if (company != null) {
			computerDTO.setCompanyId(Integer.valueOf(company));
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
