package com.excilys.computerdatabase.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	
	@OneToMany
	@JoinColumn(name = "company_id")
	private Company company;
	
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
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	/**
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
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
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
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
		StringBuilder message = new StringBuilder();
		message.append("Computer ").append(id).append(": ").append(name)
			.append((introduced == null ? "" : ", introduced in " + introduced.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))
			.append((discontinued == null ? "" : ", discontinued in " + discontinued.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))
			.append((company.getName() == null ? "" : " - Company : " + company.getName()));
		
		return message.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
