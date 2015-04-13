package com.excilys.computerdatabase.webservice;

import java.util.List;

import com.excilys.computerdatabase.dto.CompanyDTO;

public interface ICompanyWebService {
	List<CompanyDTO> getAll();
	public void delete(int id);
	public CompanyDTO find(int id);
}
