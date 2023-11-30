package com.tosan.employee.persistance.daoimpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcConnection {

	static {

		try {
			FileInputStream fileInputStream = new FileInputStream("D:\\company-config.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);

			jdbcUrl = properties.getProperty("db.connection.url");
			user = properties.getProperty("db.connection.username");
			pass = properties.getProperty("db.connection.password");

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Connection connection;

	public Statement statement;

	private static JdbcConnection jdbcConnection;

	private static final String jdbcUrl;

	private static final String user;

	private static final String pass;

	private JdbcConnection() {

		try {
			connection = DriverManager.getConnection(jdbcUrl, user, pass);
			statement = connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static JdbcConnection getJdbcConnection() {
		if (jdbcConnection == null) {
			jdbcConnection = new JdbcConnection();
		}
		return jdbcConnection;
	}
}
