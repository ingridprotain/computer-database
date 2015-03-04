package com.excilys.computerdatabase.ui;

import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.dto.ComputerMapper;
import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.service.ComputerService;

public class Pages {

	private int limit = 10;
	private int offset = 0;
	private int count;
	private int totalPages;
	private int actualPage = 0;
	
	private String actualRequest = "getAll";
	private String searchParam;
	private String orderBy;
	private String orderByColumn;
	
	private static ComputerService computerService = new ComputerService();
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getActualPage() {
		return actualPage;
	}
	
	public String getSearchParam() {
		return searchParam;
	}

	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	
	public String getActualRequest() {
		return actualRequest;
	}

	public void setActualRequest(String actualRequest) {
		if (!this.actualRequest.equals(actualRequest)) {
			this.actualRequest = actualRequest;
			offset = 0;
		}
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		/*if (orderBy == "ASC" || orderBy == "DESC") {
			this.orderBy = orderBy;
		} else {
			this.orderBy = "ASC";
		}*/
		this.orderBy = orderBy;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		if (orderByColumn != null) {
			if(orderByColumn.equals("company")) {
				orderByColumn = "co.name";
			} else if (orderByColumn.equals("introduced")) {
				orderByColumn = "c.introduced";
			} else if (orderByColumn.equals("discontinued")) {
				orderByColumn = "c.discontinued";
			}
		} else {
			orderByColumn = "c.name";
		}
		this.orderByColumn = orderByColumn;
	}

	
	public List<ComputerDTO> first() {
		offset = 0;
		actualPage = 1;
		return returnByRequest();
	}
	
	public List<ComputerDTO> last() {
		offset = count - limit;
		actualPage = totalPages;
		return returnByRequest();
	}

	public List<ComputerDTO> getByPage(int page) {
		actualPage = page;
		offset = page*limit - limit;
		return returnByRequest();
	}

	
	public List<ComputerDTO> search() {
		List<Computer> computers = computerService.getByName(searchParam, limit, offset, orderBy);
		
		count = computerService.countSearch(searchParam);
		totalPages = (int) Math.ceil(count/limit);
		
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.createComputerDTO(computer));
		}
		
		return computersDTO;
	}
	
	public List<ComputerDTO> getAll() {
		List<Computer> computers = computerService.getAll(limit, offset, orderBy, orderByColumn);
		
		count = computerService.count();
		double total = Math.ceil((double) count / (double) limit);
		totalPages = (int) total;
		
		List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
		for (Computer computer : computers) {
			computersDTO.add(ComputerMapper.createComputerDTO(computer));
		}
		
		return computersDTO;
	}

	public List<ComputerDTO> returnByRequest() {
		if (actualRequest == "search") {
			return search();
		} else {
			return getAll();
		}
	}
}
