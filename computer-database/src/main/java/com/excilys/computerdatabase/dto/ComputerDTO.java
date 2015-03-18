package com.excilys.computerdatabase.dto;

public class ComputerDTO {
	private int id;
	private String name;
	
	private String introduced;
	private String introducedDateFormat;
	
	private String discontinued;
	private String discontinuedDateFormat;
	
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
	public String getIntroducedDateFormat() {
		return introducedDateFormat;
	}
	public void setIntroducedDateFormat(String introducedDateFormat) {
		this.introducedDateFormat = introducedDateFormat;
	}
	public String getDiscontinuedDateFormat() {
		return discontinuedDateFormat;
	}
	public void setDiscontinuedDateFormat(String discontinuedDateFormat) {
		this.discontinuedDateFormat = discontinuedDateFormat;
	}
}
