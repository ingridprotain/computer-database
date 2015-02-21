package com.excilys.computerdatabase.ui;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistance.CompanyDAO;
import com.excilys.computerdatabase.persistance.ComputerDAO;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	
	public static ComputerDAO computerDao = new ComputerDAO();
	public static CompanyDAO companyDao = new CompanyDAO();
	
	public static DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
	
	public static void main(String[] args) {
		String choixUser = ""; 
		while (!choixUser.equals("exit"))
		{
			System.out.println("\nSalut l'ami! Tape une commande ou consulte l'aide -h");
			choixUser = scan.nextLine();
			switch (choixUser) {
			case "-h":
				System.out.println("Aide :");
				System.out.println("1. Consulter la liste des ordinateurs : -l computers");
				System.out.println("2. Consulter la liste des entreprises : -l companies");
				System.out.println("3. Consulter le détail d'un ordi : -l computer");
				System.out.println("4. Créer un ordinateur : -c computer");
				System.out.println("5. Modifier un ordinateur : -u computer");
				System.out.println("Quitter : exit");
				break;
			
			//Affiche la liste des ordinateurs
			case "-l computers":
				System.out.println("List computers");

				List<Computer> computers = computerDao.getListComputers();
				
				for (Computer c : computers) {
					System.out.println(c);
				}
				break;
				
			//Affiche la liste des entreprises
			case "-l companies":
				System.out.println("List companies");

				List<Company> companies = companyDao.getListCompanies();
				
				for (Company c : companies) {
					System.out.println(c);
				}
				break;
			
			//Affiche le détail d'un ordinateur
			case "-l computer":
				System.out.println("Numéro de l'ordinateur à  rechercher?");
				String computer_id_s = scan.nextLine();
				try {
					
					int computer_id = Integer.valueOf(computer_id_s);
					Computer computer_details = computerDao.find(computer_id);
					String message = (computer_details != null ? computer_details.toString() : "Ordinateur introuvable");
					System.out.println(message);
					
				} catch (NumberFormatException e){
					System.out.println("numéro de l'ordinateur invalide");
				}
				
				break;
				
			//Insérer un ordinateur
			case "-c computer":
				boolean insertComputerOk = false;
				while(insertComputerOk != true) {
					insertComputerOk = insertComputer();
				}
			break;

			//Modifier un ordinateur
			case "-u computer":
				boolean updateComputerOk = false;
				while(updateComputerOk != true) {
					updateComputerOk = updateComputer();
				}
			break;
			
			//Modifier un ordinateur
			case "-d computer":
				System.out.println("numéro de l'ordinateur à  supprimer ?");
				String computer_id_to_del_s = scan.nextLine();
				try {
					
					int computer_id = Integer.valueOf(computer_id_to_del_s);
					Computer d_computer = computerDao.find(computer_id);
					if (d_computer == null)
						System.out.println("Ordinateur introuvable");
					else {
						computerDao.delete(d_computer);
						System.out.println("Ordinateur supprimé");
					}
				} catch (NumberFormatException e){
					System.out.println("numéro de l'ordinateur invalide");
				}
			break;
			
			default:
				break;
			}
		}
	}
	
	/**
	 * Sous-menu permettant de guider l'utilisateur pendant la création d'un ordinateur
	 */
	public static boolean insertComputer()
	{
		Computer c_computer = new Computer();
		
		//champ name
		System.out.println("\tNom de l'ordinateur à  créer ?");
		c_computer = checkComputerName(c_computer);
		
		//champ introducted
		System.out.println("Insérer une date O/N d'introduction ? (format = jj/mm/aaaa)");
		c_computer = checkComputerIntroduced(c_computer);
		
		//champ discontinued
		System.out.println("Insérer une date O/N d'interruption ? (format = jj/mm/aaaa)");
		c_computer = checkComputerDiscontinued(c_computer);
		
		//champ company_id
		System.out.println("Insérer une entreprise ? ");
		c_computer = checkComputerCompany(c_computer);
		
		c_computer = computerDao.create(c_computer);
		if (c_computer == null)
			System.out.println("Problème lors de l'insertion de l'ordinateur");
		else 
			System.out.println("Ordinateur créé\n\t-> " + c_computer.toString());

		return true;
	}
	
	/**
	 * Sous-menu permettant de guider l'utilisateur pendant la modification d'un ordinateur
	 */
	public static boolean updateComputer(){
		System.out.println("numéro de l'ordinateur à  mettre à  jour?");
		String computer_id_s = scan.nextLine();
		try {
			int computer_id = Integer.valueOf(computer_id_s);
			Computer u_computer = computerDao.find(computer_id);
			if (u_computer == null)
				System.out.println("Ordinateur inexistant");
			else {
				//champ name
				System.out.println("Modifier le nom de l'ordinateur ?\n\tNom : " + u_computer.getName());
				u_computer = checkComputerName(u_computer);
				
				//champ introducted
				System.out.println("Modifier la date d'arrivée O/N ? (format = jj/mm/aaaa)\n\tDate d'arrivée : " + u_computer.getIntroduced());
				u_computer = checkComputerIntroduced(u_computer);
				
				//champ discontinued
				System.out.println("Modifier la date d'interruption O/N ? (format = jj/mm/aaaa)\n\tDate d'interruption : " + u_computer.getDiscontinued());
				u_computer = checkComputerDiscontinued(u_computer);
				
				//champ company_id
				System.out.println("Modifier l'entreprise ? " + u_computer.getCompany().getName());
				u_computer = checkComputerCompany(u_computer);
				
				u_computer = computerDao.update(u_computer);
				System.out.println("Ordinateur modifié\n\t-> " + u_computer.toString());
			}
			
		} catch (NumberFormatException e){
			System.out.println("numéro de l'ordinateur invalide");
		}
		return true;
	}
	
	private static Computer checkComputerName(Computer computer) {
		String computer_name = scan.nextLine();
		computer.setName(computer_name);
		
		return computer;
	}
	
	private static Computer checkComputerCompany(Computer computer) {
		String company_id_s = scan.nextLine();
		try {
			int company_id = Integer.valueOf(company_id_s);
			Company company = companyDao.find(company_id);
			if (company == null)
				System.out.println("Entreprise introuvable");
			else {
				computer.setCompany(company);
			}
		} catch (NumberFormatException e){
			System.out.println("numéro de l'entreprise incorrect");
		}
		return computer;
	}
	
	private static Computer checkComputerIntroduced(Computer computer) {
		String computer_date_i_confirm = scan.nextLine();
		if (computer_date_i_confirm.equals("O")) {
			
			try {
				System.out.print("Arrivé en : ");
				String computer_date_i_s = scan.nextLine();
				Date computer_date_i = format.parse(computer_date_i_s);
				computer.setIntroduced(new Timestamp(computer_date_i.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Date invalide");
			}
		}
		
		return computer;
	}
	
	private static Computer checkComputerDiscontinued(Computer computer) {
		String computer_date_d_confirm = scan.nextLine();
		if (computer_date_d_confirm.equals("O")) {
			try {
				System.out.print("Abandonné en : ");
				String computer_date_d_s = scan.nextLine();
				Date computer_date_d = format.parse(computer_date_d_s);
				computer.setDiscontinued(new Timestamp(computer_date_d.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Date invalide");
			}
		}
		return computer;
	}
}
