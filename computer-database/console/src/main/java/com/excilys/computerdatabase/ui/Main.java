package com.excilys.computerdatabase.ui;

import java.net.MalformedURLException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("/console-context.xml");
		MainView m = (MainView) context.getBean(MainView.class);
		m.start();
		context.close();
	}
}
