package com.excilys.computerdatabase.persistance;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * @author ingrid
 * Une classe singleton, qui permet de se connecter à une base de données MySQL
 */
public class MySqlConnect {
	private static MySqlConnect mySqlConnect;
	public Connection cn = null;
	private Statement statement;
	
	private MySqlConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			this.cn = DriverManager.getConnection("jdbc:mysql://localhost/computer-database-db?zeroDateTimeBehavior=convertToNull","admincdb","qwerty1234");
			
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
	
	/**
    * @param query String la requête à exécuter
    * @return un ResultSet qui contient les resultats de la requête ou null
    * @throws SQLException
    */
   public ResultSet selectQuery(String query) throws SQLException {
       statement = mySqlConnect.cn.createStatement();
       ResultSet res = statement.executeQuery(query);
       return res;
   }

}
