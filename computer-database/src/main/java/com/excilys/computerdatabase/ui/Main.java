package com.excilys.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.service.CompanyService;
import com.excilys.computerdatabase.service.ComputerService;
import com.excilys.computerdatabase.utils.ComputerDTOValidator;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	
	private static ComputerService computerService = new ComputerService();
	private static CompanyService companyService = new CompanyService();

	public static void main(String[] args) {
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
				System.out.println("List computers");
				
				Pages pages = new Pages();
				List<ComputerDTO> computers = pages.first();
				while(true) {
					for (ComputerDTO c : computers) {
						System.out.println(c);
					}
					
					System.out.println("Total of computers : " +pages.getCount());
					System.out.print("First : f - Last : l  ");
					String choice = scan.nextLine();
					if (choice.equals("f")) {
						computers = pages.first();
					} else if (choice.equals("l")){
						computers = pages.last();
					} else {
						break;
					}
				}
				break;
				
			//Display the companies list
			case "-l companies":
			case "2":
				System.out.println("List companies");

				List<Company> companies = CompanyDAO.getInstance().getAll();
				
				for (Company c : companies) {
					System.out.println(c);
				}
				break;
			
			//Display one computer's details
			case "-l computer":
			case "3":
				System.out.println("Identifiant of the computer to research ?");
				String idComputerString = scan.nextLine();
				
				Computer computer = computerService.find(Integer.parseInt(idComputerString));
				if (computer != null) {
					System.out.println(computer);
				} else {
					System.out.println("Computer doesn't exist");
				}
				break;
				
			//Add a computer
			case "-c computer":
			case "4":
				editComputer("Insert");
				break;

			//Update a computer
			case "-u computer":
			case "5":
				editComputer("Update");
				break;
			
			//Delete a computer
			case "-d computer":
			case "6":
				System.out.println("Identifiant of the computer to delete ?");
				String idComputerToDelString = scan.nextLine();
				Computer computerToDel = computerService.find(Integer.parseInt(idComputerToDelString));
				if (computerToDel != null) {
					computerService.delete(computerToDel);
					System.out.println("Computer deleted");
				} else {
					System.out.println("Computer doesn't exist");
				}
				break;
			
			case "-d company":
			case "7":
				System.out.println("Identifiant of the company to delete ?");
				String companyIdString = scan.nextLine();
				Company companyToDel = companyService.find(Integer.parseInt(companyIdString));
				if (companyToDel != null) {
					companyService.delete(companyToDel);
					System.out.println("Company and computers related deleted");
				} else {
					System.out.println("Company doesn't exist");
				}
				
				break;
			
			default:
				break;
			}
		}
	}
	
	/**
	 * Under the edit menu
	 */
	public static void editComputer(String mode)
	{
		ComputerDTO computerDTO = new ComputerDTO();
		
		if (mode == "Update") {
			System.out.println("Identifiant of the computer to update ?");
			String idComputerToUpdString = scan.nextLine();
			Computer computer = computerService.find(Integer.parseInt(idComputerToUpdString));
			if (computer != null) {
				System.out.println(computer);
			} else {
				System.out.println("Computer doesn't exist");
			}
		}
		
		
		//champ name
		if (mode == "Insert") {
			System.out.println("Name of the computer?");
			computerDTO.setName(scan.nextLine());
		} else if (mode == "Update") {
			System.out.println("Change name of the computer? Y/N");
			if (userConfirm() == true) {
				computerDTO.setName(scan.nextLine());
			}
		}
		
		//champ introducted
		System.out.println(mode + " the introduction date (format = mm/jj/aaaa)? Y/N");
		if (userConfirm() == true) {
			System.out.print("Introduced in : ");
			computerDTO.setIntroduced(scan.nextLine());
		}
		
		//champ discontinued
		System.out.println(mode + " the discontinued date (format = mm/jj/aaaa)? Y/N");
		if (userConfirm() == true) {
			System.out.print("Discontinued in : ");
			computerDTO.setDiscontinued(scan.nextLine());
		}
		
		//champ company_id
		System.out.println(mode + " the company? Y/N");
		if (userConfirm() == true) {
			System.out.print("Company's id: ");
			computerDTO.setCompanyId((Integer.parseInt(scan.nextLine())));
		}
		
		List<String> errors = ComputerDTOValidator.validate(computerDTO);
		if (errors.isEmpty()) {
			Computer computer = ComputerMapper.createComputer(computerDTO);
			if (mode == "Insert") {
				computerService.create(computer);
			} else {
				//ComputerDAO.getInstance().update(ComputerValidator.getComputer());
			}
		} else {
			for(String error : errors) {
				System.out.println(error);
			}
		}
	}
	
	private static boolean userConfirm() {
		String sConfirm = scan.nextLine();
		if (sConfirm.equals("Y") || sConfirm.equals("y")) {
			return true;
		} else {
			return false;
		}
	}
}
