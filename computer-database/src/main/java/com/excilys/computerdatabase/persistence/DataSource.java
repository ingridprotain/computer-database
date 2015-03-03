package com.excilys.computerdatabase.persistence;

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DataSource {
	private static DataSource     datasource;
    private BoneCP connectionPool;
    
    private static boolean isTest = false;

    private static final String FICHIER_PROPERTIES = "persistence.properties";
    private static final String PROPERTY_URL = "urlBdd";
    private static final String PROPERTY_URL_TEST = "urlBddTest";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_PASSWORD = "password";
    
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        
    	Properties properties = new Properties();
    	//file persistence.properties
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
    	
        String urlBdd;
        String urlBddTest;
        String driver;
        String user;
        String password;
        
        try {
            properties.load( fichierProperties );
            urlBdd = properties.getProperty(PROPERTY_URL);
            urlBddTest = properties.getProperty(PROPERTY_URL_TEST);
            driver = properties.getProperty(PROPERTY_DRIVER);
            user = properties.getProperty(PROPERTY_USER);
            password = properties.getProperty(PROPERTY_PASSWORD);

        } catch ( FileNotFoundException e ) {
        	throw new IllegalStateException("Not found file: " + FICHIER_PROPERTIES);
        } catch ( IOException e ) {
        	throw new IllegalStateException("Unable to charge the file: " + FICHIER_PROPERTIES);
        }
    	
    	try {
            // load the database driver (make sure this is in your classpath!)
            Class.forName(driver);
        } catch (Exception e) {
        	throw new IllegalStateException("Problem during loading the Driver JDBC");
        }

        try {
            // setup the connection pool using BoneCP Configuration
            BoneCPConfig config = new BoneCPConfig();
            
            if (!isTest) {
            	config.setJdbcUrl(urlBdd);
			} else {
				config.setJdbcUrl(urlBddTest);
			}
            config.setUsername(user);
            config.setPassword(password);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            // setup the connection pool
            connectionPool = new BoneCP(config);
            
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.connectionPool.getConnection();
    }
}
