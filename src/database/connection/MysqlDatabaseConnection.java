package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import configs.Config;

public class MysqlDatabaseConnection implements DatabaseConnection {

	private Connection connection;

	public MysqlDatabaseConnection(String databaseName) {
		loadJdbcDriver();
		openConnection(databaseName);
	}

	private boolean loadJdbcDriver() {
		try {
			System.out.println("Loading JDBC driver...");

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("JDBC driver loaded");

			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load JDBC driver!");
			return false;
		}
	}

	private boolean openConnection(String databaseName) {
		try {
			System.out.println("Connecting to database...");

			setConnection(DriverManager.getConnection(connectionString(databaseName)));

			System.out.println("Connected to database");

			return true;
		} catch (SQLException e) {
			setConnection(null);

			System.out.println("Could not connect to database!");
			System.out.println(e.getMessage());

			return false;
		}
	}
	
	private String connectionString(String databaseName) {
	    String connectionString =
        "jdbc:sqlserver://localhost:1433;" +
        "instanceName=SQLEXPRESS;" +
        "databaseName=" + databaseName + ";" +
        "trustServerCertificate=true;" +
        "integratedSecurity=true;";
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:sqlserver://");
		sb.append(Config.get("database."));
	    
	    return connectionString;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
