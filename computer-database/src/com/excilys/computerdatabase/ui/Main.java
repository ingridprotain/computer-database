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
				System.out.println("numéro de l'ordinateur à rechercher?");
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
			default:
				break;
			}
		}
	}
	
	/**
	 * Sous-menu permettant de guider l'utilisateur dans sa création d'ordinateur
	 */
	public static boolean insertComputer()
	{
		Computer c_computer = new Computer();
		DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		
		//champ name
		System.out.println("\tNom de l'ordinateur à créer ?");
		String c_computer_name = scan.nextLine();
		c_computer.setName(c_computer_name);
		
		//champ introducted
		System.out.println("Insérer une date O/N d'introduction ? (format = jj/mm/aaaa)");
		String c_computer_date_i_confirm = scan.nextLine();
		if (c_computer_date_i_confirm.equals("O")) {
			
			try {
				System.out.print("Arrivé en : ");
				String c_computer_date_i_s = scan.nextLine();
				Date c_computer_date_i = format.parse(c_computer_date_i_s);
				c_computer.setIntroduced(new Timestamp(c_computer_date_i.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Date invalide");
			}
		}
		
		//champ discontinued
		System.out.println("Insérer une date O/N d'arrêt ?");
		String c_computer_date_d_confirm = scan.nextLine();
		if (c_computer_date_d_confirm.equals("O")) {
			try {
				System.out.print("Abandonné en : ");
				String c_computer_date_d_s = scan.nextLine();
				Date c_computer_date_d = format.parse(c_computer_date_d_s);
				c_computer.setIntroduced(new Timestamp(c_computer_date_d.getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println("Date invalide");
			}
		}
		
		//champ company_id
		//TODO
		
		c_computer = computerDao.create(c_computer);
		if (c_computer == null)
			System.out.println("Problème lors de l'insertion de l'ordinateur");
		else 
			System.out.println("Ordinateur créé\n\t-> " + c_computer.toString());

		return true;
	}

}
