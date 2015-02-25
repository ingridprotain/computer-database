package com.excilys.computerdatabase.ui;

import java.util.List;

import com.excilys.computerdatabase.model.Computer;
import com.excilys.computerdatabase.persistence.ComputerDAO;

public class Pages {
	private int limit = 0;
	private int offset = 20;
	private int total;
	
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
		
		this.total = ComputerDAO.getInstance().count();
	}
	
	public List<Computer> first() {
		this.limit = 0;
		List<Computer> computers = ComputerDAO.getInstance().getAll(0, this.offset);

		return computers;
	}
	
	public List<Computer> prev() {
		this.limit = this.limit - this.offset;
		if (this.limit < 0) {
			this.limit = 0;
		}
		List<Computer> computers = ComputerDAO.getInstance().getAll(this.limit, this.offset);
		
		return computers;
	}
	
	public List<Computer> next() {
		this.limit = this.limit + this.offset;
		List<Computer> computers = ComputerDAO.getInstance().getAll(this.limit, this.offset);

		return computers;
	}
	
	public List<Computer> last() {
		this.limit = this.total - this.offset;
		List<Computer> computers = ComputerDAO.getInstance().getAll(this.limit, this.offset);

		return computers;
	}
}
