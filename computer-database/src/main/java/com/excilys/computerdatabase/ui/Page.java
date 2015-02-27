package com.excilys.computerdatabase.ui;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;

public class Page {

	private int limit = 10;
	private int offset = 0;
	private int count;
	private int totalPages;
	private int actualPage = 0;
	
	private String actualRequest = "getAll";
	private String searchParam;
	
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
	
	
	public List<ComputerDTO> first() {
		offset = 0;
		actualPage = 1;
		return returnByRequest();
	}
	
	public List<ComputerDTO> prev() {
		offset = offset - limit;
		if (offset < 0) {
			offset = 0;
		}
		actualPage -= 1;
		if (actualPage <1) {
			actualPage = 1;
		}
		return returnByRequest();
	}
	
	public List<ComputerDTO> next() {
		offset = offset + limit;
		if (offset > count) {
			offset = count;
		}
		actualPage += 1;
		if (actualPage > totalPages) {
			actualPage = totalPages;
		}
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
		List<ComputerDTO> computers = computerService.getByName(searchParam, limit, offset);
		count = computerService.countSearch(searchParam);
		totalPages = (int) Math.ceil(count/limit);
		return computers;
	}
	
	public List<ComputerDTO> getAll() {
		List<ComputerDTO> computers = computerService.getAll(limit, offset);
		count = computerService.count();
		double total = Math.ceil((double) count / (double) limit);
		totalPages = (int) total;
		return computers;
	}

	public List<ComputerDTO> returnByRequest() {
		if (actualRequest == "search") {
			return search();
		} else {
			return getAll();
		}
	}
}
