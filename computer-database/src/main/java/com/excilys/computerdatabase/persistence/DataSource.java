package com.excilys.computerdatabase.persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum DataSource {
	INSTANCE; 
	
    public BoneCP connectionPool = null;
    
    private boolean isTest = false;

    private static final String FICHIER_PROPERTIES = "persistence.properties";
    private static final String PROPERTY_URL = "urlBdd";
    private static final String PROPERTY_URL_TEST = "urlBddTest";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USER = "user";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_MIN_CONNECTIONS = "minConnectionPerPartition";
    private static final String PROPERTY_MAX_CONNECTIONS = "maxConnectionPerPartition";
    private static final String PROPERTY_PARTITIONS_COUNT = "partitionCount";
    
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    
    private DataSource() {
        
    	Properties properties = new Properties();
    	//file persistence.properties
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
    	
        String urlBdd;
        String urlBddTest;
        String driver;
        String user;
        String password;
        int minConnections;
        int maxConnections;
        int partitionCount;
        
        try {
            properties.load( fichierProperties );
            urlBdd = properties.getProperty(PROPERTY_URL);
            urlBddTest = properties.getProperty(PROPERTY_URL_TEST);
            driver = properties.getProperty(PROPERTY_DRIVER);
            user = properties.getProperty(PROPERTY_USER);
            password = properties.getProperty(PROPERTY_PASSWORD);
            minConnections = Integer.valueOf(properties.getProperty(PROPERTY_MIN_CONNECTIONS));
            maxConnections = Integer.valueOf(properties.getProperty(PROPERTY_MAX_CONNECTIONS));
            partitionCount = Integer.valueOf(properties.getProperty(PROPERTY_PARTITIONS_COUNT));
            
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
            config.setMinConnectionsPerPartition(minConnections);
            config.setMaxConnectionsPerPartition(maxConnections);
            config.setPartitionCount(partitionCount);
            // setup the connection pool
            connectionPool = new BoneCP(config);
            
        } catch (SQLException e) {
        	throw new IllegalStateException("Problem with the pools of connection");
        }

    }
    
    public Connection getConnection() {
		Connection cn = null;
		if (connectionThreadLocal.get() != null) {
			return connectionThreadLocal.get();
		}

		try {
			cn = DataSource.INSTANCE.connectionPool.getConnection();
			connectionThreadLocal.set(cn);
			cn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during connection to the database");
		}
		
		return cn;
	}
    
    public void initTransaction() {
    	Connection cn = null;
    	try {
			cn = DataSource.INSTANCE.connectionPool.getConnection();
			cn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during init transaction to the database");
		}
    	connectionThreadLocal.set(cn);
    }
    
    public void closeTransaction() {
    	Connection cn = connectionThreadLocal.get();
    	
    	try {
			cn.commit();
			cn.close();
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during closing transaction to the database");
		}
    	connectionThreadLocal.remove();
    }
	
	public void closeConnection() {
		Connection cn = connectionThreadLocal.get();
		boolean isAutoComit = false;
		try {
			isAutoComit = cn.getAutoCommit();
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during closing connection to the database");
		}
		if (isAutoComit) {
			try {
				cn.close();
			} catch (SQLException e) {
				throw new IllegalStateException("Problem during closing connection to the database");
			}
			connectionThreadLocal.remove();
		}
	}
	
	public void rollback() {
		try {
			connectionThreadLocal.get().rollback();
		} catch (SQLException e) {
			throw new IllegalStateException("Problem during rollback");

		}
	}
}
