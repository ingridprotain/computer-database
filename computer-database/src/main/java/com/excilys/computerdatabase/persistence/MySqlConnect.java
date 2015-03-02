package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author ingrid
 * Une classe singleton, qui permet de se connecter à une base de données MySQL
 */
public class MySqlConnect {
	private static MySqlConnect mySqlConnect;
	private static Connection cn = null;
	
	private static boolean isTest = false;
	
	
	
	public static boolean isTest() {
		return isTest;
	}

	public static void setTest(boolean isTest) {
		MySqlConnect.isTest = isTest;
	}

	private MySqlConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return MySqlConnect L'objet de connexion à la base
	 */
	public static synchronized MySqlConnect getMySqlConnect() {
		if (mySqlConnect == null) {
			mySqlConnect = new MySqlConnect();
		}
		return mySqlConnect;
	}
	
	public Connection getMyInstance() {
		try {
			if (!isTest) {
				cn = DriverManager.getConnection("jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull","admincdb","qwerty1234");
			} else {
				cn = DriverManager.getConnection("jdbc:mysql://localhost/computer-database-db-test?zeroDateTimeBehavior=convertToNull","admincdb","qwerty1234");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cn;
	}
}
