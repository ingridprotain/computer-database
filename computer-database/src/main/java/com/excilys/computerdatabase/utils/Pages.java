package com.excilys.computerdatabase.utils;

public class Pages {
	private int limit;
	private int offset;
	private int totalPages;
	private int page;
	private String search;
	private String orderBy;
	private String orderByColumn;
	
	public Pages() {
		reset();
	}
	
	public void reset() {
		limit = 10;
		offset = 0;
		page =1;
		search = "";
		orderBy = "ASC";
		orderByColumn = "c.name";
	}
	
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		if (orderBy != "ASC" || orderBy != "DESC") {
			orderBy = "ASC";
		} else {
			this.orderBy = orderBy;
		}
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		if (orderByColumn != null) {
			if (orderByColumn.equals("name")) {
				this.orderByColumn = "c.name";
			}
			else if(orderByColumn.equals("company")) {
				this.orderByColumn = "co.name";
			} else if (orderByColumn.equals("introduced")) {
				this.orderByColumn = "c.introduced";
			} else if (orderByColumn.equals("discontinued")) {
				this.orderByColumn = "c.discontinued";
			}
		} else {
			this.orderByColumn = "c.name";
		}
	}
}
