package com.excilys.computerdatabase.model;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "introduced", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date introduced;
	
	@Column(name = "discontinued", nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date discontinued;
	
	@ManyToOne(fetch=FetchType.EAGER, optional=true)
	@JoinColumn(name="company_id", referencedColumnName="id")
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
		return (introduced != null ? new Timestamp(introduced.getTime()).toLocalDateTime() : null);
	}
	/**
	 * @return the discontinued
	 */
	public LocalDateTime getDiscontinued() {
		return (discontinued != null ? new Timestamp(discontinued.getTime()).toLocalDateTime() : null);
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
		this.introduced = (introduced == null ? null : new Date(Timestamp.valueOf(introduced).getTime()));
	}
	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = (discontinued == null ? null : new Date(Timestamp.valueOf(discontinued).getTime()));
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
//			.append((introduced == null ? "" : ", introduced in " + introduced.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))
//			.append((discontinued == null ? "" : ", discontinued in " + discontinued.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))
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
