package com.foxminded.igorFrenkel.dao;

import java.sql.*;

import org.apache.log4j.Logger;

public class DaoFactory {

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://localhost:5432/university";
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "197703";
	
	private static final Logger logger = Logger.getLogger(DaoFactory.class.getName());

	public static Connection getConnection() {

		Connection connection = null;
		try {
			Class.forName(DB_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			logger.info("Connection open");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Connection failed! Check your settings");
			logger.error("Cannot open connection. Check your settings", e);
		}
		return connection;
	}

}
