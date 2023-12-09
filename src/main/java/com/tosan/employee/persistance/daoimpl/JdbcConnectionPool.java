package com.tosan.employee.persistance.daoimpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcConnectionPool {

	static {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream("D:\\company-config.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);

			jdbcUrl = properties.getProperty("db.connection.url");
			user = properties.getProperty("db.connection.username");
			pass = properties.getProperty("db.connection.password");

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fileInputStream != null)
					fileInputStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static final String jdbcUrl;

	private static final String user;

	private static final String pass;

	private static List<Connection> availableConnections = new ArrayList<>();

	private static List<Connection> usedConnections = new ArrayList<>();

	private static Connection getNewConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(jdbcUrl, user, pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static Connection getConnection() {
		synchronized (JdbcConnectionPool.class) {
			Connection connection;
			if (availableConnections.isEmpty()) {
				connection = getNewConnection();
				usedConnections.add(connection);
				return connection;
			} else {
				connection = availableConnections.remove(0);
				usedConnections.add(connection);
				return connection;
			}
		}
	}

	public static void releaseConnection(Connection connection) {
		synchronized (JdbcConnectionPool.class) {
			usedConnections.remove(connection);
			availableConnections.add(connection);
		}
	}

}