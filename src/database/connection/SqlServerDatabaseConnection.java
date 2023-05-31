package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import app.configs.Config;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *     
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class SqlServerDatabaseConnection implements DatabaseConnection {

	private Connection connection;

	public SqlServerDatabaseConnection() {
		loadJdbcDriver();
		openConnection();
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

	private boolean openConnection() {
		try {
			System.out.println("Connecting to database...");

			setConnection(DriverManager.getConnection(connectionString()));

			System.out.println("Connected to database");

			return true;
		} catch (SQLException e) {
			setConnection(null);

			System.out.println("Could not connect to database!");
			System.out.println(e.getMessage());

			return false;
		}
	}
	
	private String connectionString() {
		
	    StringBuilder sb = new StringBuilder();
		
		sb.append("jdbc:sqlserver://");
		sb.append(Config.get("database.sqlServer.hostname")+":");
		sb.append(Config.get("database.sqlServer.port")+";");
		sb.append("instanceName="+Config.get("database.sqlServer.instanceName")+";");
		sb.append("databaseName="+Config.get("database.sqlServer.dbName")+";");
		
		String trustServerCertificate = Config.get("database.sqlServer.trustServerCertificate");
		if(trustServerCertificate != null && trustServerCertificate.equals("yes"))
			sb.append("trustServerCertificate=true;");
		
		String integratedSecurity = Config.get("database.sqlServer.integratedSecurity");
		if(integratedSecurity != null && integratedSecurity.equals("yes"))
			sb.append("integratedSecurity=true;");
		
	    
	    return sb.toString();
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
