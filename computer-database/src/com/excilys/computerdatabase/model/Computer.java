package com.excilys.computerdatabase.model;

import java.util.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 
 * @author ingrid
 * DÃ©cris la table Computer
 */
public class Computer {
	private int id;
	private String name;
	private Timestamp introduced;
	private Timestamp discontinued;
	private Company company;
	
	private DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the introduced
	 */
	public Timestamp getIntroduced() {
		return introduced;
	}
	/**
	 * @return the discontinued
	 */
	public Timestamp getDiscontinued() {
		return discontinued;
	}
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(Timestamp introduced) {
		this.introduced = introduced;
	}
	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(Timestamp discontinued) {
		this.discontinued = discontinued;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		return "Ordinateur n°" + id + " : " + name 
			+ (introduced == null ? "" : ", introduit en " +  format.format(new Date(introduced.getTime())))
			+ (discontinued == null ? "" : ", désactivé en " + format.format(new Date(discontinued.getTime()))) 
			+ (company.getName() == null ? "" : " - Entreprise : " + company.getName());
		
	}
	
	
}
