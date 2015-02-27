package com.excilys.computerdatabase.ui;

import java.util.List;

import com.excilys.computerdatabase.dto.ComputerDTO;
import com.excilys.computerdatabase.service.ComputerService;

public class Pages {
	private int limit = 0;
	private int offset = 12;
	private int total;

	private static ComputerService computerService = new ComputerService();
	
	public Pages(String className) {
		setTotal(className);
	}
	
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}
	
	private void setTotal(String className) {
		
		this.total = computerService.count();
	}
	
	public List<ComputerDTO> first() {
		this.limit = 0;
		List<ComputerDTO> computers = computerService.getAll(0, this.offset);

		return computers;
	}
	
	public List<ComputerDTO> prev() {
		this.limit = this.limit - this.offset;
		if (this.limit < 0) {
			this.limit = 0;
		}
		List<ComputerDTO> computers = computerService.getAll(this.limit, this.offset);
		
		return computers;
	}
	
	public List<ComputerDTO> next() {
		this.limit = this.limit + this.offset;
		List<ComputerDTO> computers = computerService.getAll(this.limit, this.offset);

		return computers;
	}
	
	public List<ComputerDTO> last() {
		this.limit = this.total - this.offset;
		List<ComputerDTO> computers = computerService.getAll(this.limit, this.offset);

		return computers;
	}
	
	public List<ComputerDTO> findByLimit(int limit) {
		this.limit = limit*this.offset;
		List<ComputerDTO> computers = computerService.getAll(this.limit, this.offset);
		return computers;
	}
}
