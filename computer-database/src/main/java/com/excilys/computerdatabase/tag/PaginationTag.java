package com.excilys.computerdatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class PaginationTag extends TagSupport {
	private String target;
	private String name;
	private String page;
	private String search;
	private String myClass;
	private int limit;
	private String orderBy;
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
    public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMyClass() {
		return myClass;
	}

	public void setMyClass(String myClass) {
		this.myClass = myClass;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int doStartTag() throws JspException{
    	JspWriter out = pageContext.getOut();
    	StringBuilder link = new StringBuilder();
    	link.append("<a class=\"");
    	link.append(myClass);
    	link.append("\" ");
    	link.append("href=\"/computer-database/");
    	link.append(target);
    	if (page != null) {
    		link.append("?page=");
        	link.append(page);
    	}
    	if (limit != 0) {
    		link.append("&limit=");
        	link.append(limit);
    	}
    	if (search != null && !search.equals("")) {
    		link.append("&search=");
        	link.append(search);
    	}
    	if (orderBy != null && !orderBy.equals("")) {
    		link.append("&orderBy=");
        	link.append(orderBy);
    	}
    	link.append("\">");
    	link.append(name);
    	link.append("</a>");
    	try {
			out.print(link.toString());
		} catch (IOException e) {
			throw new JspException("Error: " + e.getMessage());
		}
        return SKIP_BODY;
    }
    
    public int doEndTag(){
        return EVAL_PAGE;
    }
}
