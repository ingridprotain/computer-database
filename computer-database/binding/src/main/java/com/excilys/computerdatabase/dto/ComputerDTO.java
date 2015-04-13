package com.excilys.computerdatabase.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.computerdatabase.util.MyDate;

public class ComputerDTO {
	private int id;
	@NotEmpty(message="{NotEmpty.Computer.name}")
	private String name;
	
	@MyDate
	private String introduced;
	@MyDate
	private String discontinued;
	
	private int companyId;
	private String companyName;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(id).append(" - ").append(name);
		if (introduced != null) {
			string.append(" introduced : ").append(introduced);
		}
		if (discontinued != null) {
			string.append(", discontinued : ").append(discontinued);
		}
		if (companyName != null) {
			string.append(" - ").append(companyName);
		}
		
		return string.toString();
	}
	
	
}
