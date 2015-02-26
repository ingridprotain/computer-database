package com.excilys.computerdatabase.ui;

import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.CompanyDAO;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class Main {
	public static Scanner scan = new Scanner(System.in);

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
				System.out.println("Exit: exit");
				break;
			
			//Display the computers list
			case "-l computers":
			case "1":
				System.out.println("List computers");
				
				Pages pages = new Pages("Computer");
				List<ComputerDTO> computers = pages.first();
				while(true) {
					for (ComputerDTO c : computers) {
						System.out.println(c);
					}
					
					System.out.println("Total of computers : " +pages.getTotal());
					System.out.print("First : f - Previous : p - Next : n - Last : l  ");
					String choice = scan.nextLine();
					if (choice.equals("p")) {
						computers = pages.prev();
					} else if (choice.equals("n")) {
						computers = pages.next();
					} else if (choice.equals("f")) {
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
				ComputerValidator.getInstance().isExist(idComputerString);
				if (ComputerValidator.getInstance().getErrors().isEmpty()) {
					System.out.println(ComputerValidator.getComputer());
				} else {
					ComputerValidator.displayErrors();
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
				ComputerValidator.getInstance().isExist(idComputerToDelString);
				if (ComputerValidator.getInstance().getErrors().isEmpty()) {
					//ComputerDAO.getInstance().delete(ComputerValidator.getComputer());
				} else {
					ComputerValidator.displayErrors();
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
		String name = null;
		String introduced = null;
		String discontinued = null;
		String companyId = null;
		
		int computerId = 0;
		
		if (mode == "Update") {
			System.out.println("Identifiant of the computer to update ?");
			String idComputerToUpdString = scan.nextLine();
			ComputerValidator.getInstance().isExist(idComputerToUpdString);
			if (ComputerValidator.getInstance().getErrors().isEmpty()) {
				computerId = ComputerValidator.getComputer().getId();
			} else {
				ComputerValidator.displayErrors();
			}
		}
		
		
		//champ name
		if (mode == "Insert") {
			System.out.println("Name of the computer?");
			name = scan.nextLine();
		} else if (mode == "Update") {
			System.out.println("Change name of the computer? Y/N");
			if (userConfirm() == true) {
				name = scan.nextLine();
			}
		}
		
		//champ introducted
		System.out.println(mode + " the introduction date (format = mm/jj/aaaa)? Y/N");
		if (userConfirm() == true) {
			System.out.print("Introduced in : ");
			introduced = scan.nextLine();
		}
		
		//champ discontinued
		System.out.println(mode + " the discontinued date (format = mm/jj/aaaa)? Y/N");
		if (userConfirm() == true) {
			System.out.print("Discontinued in : ");
			discontinued = scan.nextLine();
		}
		
		//champ company_id
		System.out.println(mode + " the company? Y/N");
		if (userConfirm() == true) {
			System.out.print("Company's id: ");
			companyId = scan.nextLine();
		}
		ComputerValidator.getInstance().validate(name, introduced, discontinued, companyId, computerId);
		if (ComputerValidator.getInstance().getErrors().isEmpty()) {
			if (mode == "Insert") {
				//ComputerDAO.getInstance().create(ComputerValidator.getComputer());
			} else {
				//ComputerDAO.getInstance().update(ComputerValidator.getComputer());
			}
		} else {
			ComputerValidator.displayErrors();
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
