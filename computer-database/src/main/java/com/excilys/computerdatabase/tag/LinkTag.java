package com.excilys.computerdatabase.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class LinkTag extends TagSupport {
	
	private String target;
	private String name;
	private int computerId;
	private String myClass;
	
	public void setTarget(String pTarget) {
		target = pTarget;
	}

	public void setName(String pName) {
		name = pName;
	}

	public void setComputerId(int pComputerId) {
		computerId = pComputerId;
	}
	
	public void setMyClass(String pClass) {
		myClass = pClass;
	}
	
    public int doStartTag() throws JspException{
    	JspWriter out = pageContext.getOut();
    	StringBuilder link = new StringBuilder();
    	link.append("<a class=\"");
    	link.append(myClass);
    	link.append("\" ");
    	link.append("href=\"/computer-database/");
    	link.append(target);
    	if (computerId != 0) {
    		link.append("?computerId=");
        	link.append(computerId);
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
