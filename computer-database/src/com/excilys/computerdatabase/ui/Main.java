package com.excilys.computerdatabase.ui;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.excilys.computerdatabase.controller.CompanyController;
import com.excilys.computerdatabase.controller.ComputerController;
import com.excilys.computerdatabase.controller.EntityNotExistException;
import com.excilys.computerdatabase.model.Company;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistance.CompanyDAO;
import com.excilys.computerdatabase.persistance.ComputerDAO;

public class Main {
	public static Scanner scan = new Scanner(System.in);
	
	private static ComputerDAO computerDao = new ComputerDAO();
	private static CompanyDAO companyDao = new CompanyDAO();
	
	private static ComputerController computerControler = new ComputerController();
	private static CompanyController companyControler = new CompanyController();
	
	private static DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
	
	public enum ErrorMessage {
		COMPUTER_NOT_INT("Numéro de l'ordinateur invalide : doit être un nombre"),
		COMPANY_NOT_INT("Numéro de l'entreprise invalide : doit être un nombre"),
		NOT_DATE("Date invalide");
		
		private String name ="";
		ErrorMessage(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	
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
				System.out.println("6. Supprimer un ordinateur : -d computer");
				System.out.println("Quitter : exit");
				break;
			
			//Affiche la liste des ordinateurs
			case "-l computers":
			case "1":
				System.out.println("List computers");

				List<List<Computer>> p_computers = new Pagination<Computer>().getPages(computerDao.getListComputers());
				
				for (List<Computer> list : p_computers) {
					for (Computer c : list) {
						System.out.println(c);
					}
					System.out.println("Suivant ? O/N");
					String suivant_computer_choice = scan.nextLine();
					if (!user_confirm(suivant_computer_choice)) {
						break;
					}
				}
				break;
				
			//Affiche la liste des entreprises
			case "-l companies":
			case "2":
				System.out.println("List companies");

				List<List<Company>> p_companies = new Pagination<Company>().getPages(companyDao.getListCompanies());
				
				for (List<Company> list : p_companies) {
					for (Company c : list) {
						System.out.println(c);
					}
					System.out.println("Suivant ? O/N");
					String suivant_company_choice = scan.nextLine();
					if (!user_confirm(suivant_company_choice)) {
						break;
					}
				}
				break;
			
			//Affiche le détail d'un ordinateur
			case "-l computer":
			case "3":
				System.out.println("Numéro de l'ordinateur à rechercher?");
				String computer_id_s = scan.nextLine();
				try {
					int computer_id = Integer.valueOf(computer_id_s);
					try {
						Computer computer_details = computerControler.checkIfComputerExist(computer_id);
						System.out.println(computer_details);
					} catch (EntityNotExistException ce) {
						// TODO Auto-generated catch block
						ce.getStackTrace();
					}
				} catch (NumberFormatException e){
					System.out.println(ErrorMessage.COMPUTER_NOT_INT);
				}
				
				break;
				
			//Insérer un ordinateur
			case "-c computer":
			case "4":
				boolean insertComputerOk = false;
				while(insertComputerOk != true) {
					insertComputerOk = insertComputer();
				}
			break;

			//Modifier un ordinateur
			case "-u computer":
			case "5":
				boolean updateComputerOk = false;
				while(updateComputerOk != true) {
					updateComputerOk = updateComputer();
				}
			break;
			
			//Supprimer un ordinateur
			case "-d computer":
			case "6":
				System.out.println("numéro de l'ordinateur à supprimer ?");
				String computer_id_to_del_s = scan.nextLine();
				try {
					int computer_id = Integer.valueOf(computer_id_to_del_s);
					try {
						Computer d_computer = computerControler.checkIfComputerExist(computer_id);
						computerDao.delete(d_computer);
						System.out.println("Ordinateur supprimé");
					} catch (EntityNotExistException ce) {
						ce.getStackTrace();
					}
				} catch (NumberFormatException e){
					System.out.println(ErrorMessage.COMPUTER_NOT_INT);
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
		System.out.println("\tNom de l'ordinateur à créer ?");
		c_computer = checkComputerName(c_computer);
		
		//champ introducted
		System.out.println("Insérer une date d'introduction (format = jj/mm/aaaa) ? O/N");
		c_computer = checkComputerIntroduced(c_computer);
		
		//champ discontinued
		System.out.println("Insérer une date O/N d'interruption (format = jj/mm/aaaa) ? O/N");
		c_computer = checkComputerDiscontinued(c_computer);
		
		//champ company_id
		System.out.println("Insérer une entreprise ? O/N");
		c_computer = checkComputerCompany(c_computer);
		
		c_computer = computerDao.create(c_computer);
		if (c_computer == null)
			System.out.println("Probléme lors de l'insertion de l'ordinateur");
		else 
			System.out.println("Ordinateur créé\n\t-> " + c_computer.toString());

		return true;
	}
	
	/**
	 * Sous-menu permettant de guider l'utilisateur pendant la modification d'un ordinateur
	 */
	public static boolean updateComputer(){
		System.out.println("numéro de l'ordinateur à mettre à jour?");
		String computer_id_s = scan.nextLine();
		try {
			int computer_id = Integer.valueOf(computer_id_s);
			Computer u_computer;
			try {
				u_computer = computerControler.checkIfComputerExist(computer_id);
				//champ name
				System.out.println("Modifier le nom de l'ordinateur O/N?\n\tNom : " + u_computer.getName());
				String computer_name_i_confirm = scan.nextLine();
				if (user_confirm(computer_name_i_confirm)) {
					System.out.print("Nouveau nom : ");
					u_computer = checkComputerName(u_computer);
				}
				
				//champ introducted
				System.out.println("Modifier la date d'arrivée (format = jj/mm/aaaa) ? O/N\n\tDate d'arrivée : " + u_computer.getIntroduced());
				u_computer = checkComputerIntroduced(u_computer);
				
				//champ discontinued
				System.out.println("Modifier la date d'interruption (format = jj/mm/aaaa) ? O/N\n\tDate d'interruption : " + u_computer.getDiscontinued());
				u_computer = checkComputerDiscontinued(u_computer);
				
				//champ company_id
				System.out.println("Modifier l'entreprise ? O/N\n\tEntreprise : " + u_computer.getCompany().getName());
				u_computer = checkComputerCompany(u_computer);
				
				u_computer = computerDao.update(u_computer);
				System.out.println("Ordinateur modifié\n\t-> " + u_computer.toString());
			} catch (EntityNotExistException ce) {
				// TODO Auto-generated catch block
				ce.getStackTrace();
			}
		} catch (NumberFormatException e){
			System.out.println(ErrorMessage.COMPUTER_NOT_INT);
		}
		return true;
	}
	
	private static Computer checkComputerName(Computer computer) {
		String computer_name = scan.nextLine();
		computer.setName(computer_name);
		
		return computer;
	}
	
	private static Computer checkComputerCompany(Computer computer) {
		String computer_company_i_confirm = scan.nextLine();
		if (user_confirm(computer_company_i_confirm)) {
			System.out.print("Numéro de l'entreprise : ");
			String company_id_s = scan.nextLine();
			try {
				int company_id = Integer.valueOf(company_id_s);
				try {
					Company company = companyControler.checkIfCompanyExist(company_id);
					computer.setCompany(company);
				} catch (EntityNotExistException ce) {
					// TODO Auto-generated catch block
					ce.getStackTrace();
				}
			} catch (NumberFormatException e){
				System.out.println(ErrorMessage.COMPANY_NOT_INT);
			}
		}
		return computer;
	}
	
	private static Computer checkComputerIntroduced(Computer computer) {
		String computer_date_i_confirm = scan.nextLine();
		if (user_confirm(computer_date_i_confirm)) {
			try {
				System.out.print("Arrivé en : ");
				String computer_date_i_s = scan.nextLine();
				Date computer_date_i = format.parse(computer_date_i_s);
				computer.setIntroduced(new Timestamp(computer_date_i.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(ErrorMessage.NOT_DATE);
			}
		}
		
		return computer;
	}
	
	private static Computer checkComputerDiscontinued(Computer computer) {
		String computer_date_d_confirm = scan.nextLine();
		if (user_confirm(computer_date_d_confirm)) {
			try {
				System.out.print("Abandonné en : ");
				String computer_date_d_s = scan.nextLine();
				Date computer_date_d = format.parse(computer_date_d_s);
				computer.setDiscontinued(new Timestamp(computer_date_d.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(ErrorMessage.NOT_DATE);
			}
		}
		return computer;
	}
	
	private static boolean user_confirm(String s_confirm) {
		if (s_confirm.equals("O") || s_confirm.equals("o")) {
			return true;
		} else {
			return false;
		}
	}
}
