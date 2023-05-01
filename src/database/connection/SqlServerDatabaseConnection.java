package database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlServerDatabaseConnection implements IDatabaseConnection {

	private Connection connection;

	  public SqlServerDatabaseConnection(String databaseName) {
	    loadJdbcDriver();
	    openConnection(databaseName);
	  }
	  
	  private boolean loadJdbcDriver() {
	    try {
	      System.out.println("Loading JDBC driver...");
	      
	      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	      
	      System.out.println("JDBC driver loaded");
	      
	      return true;
	    }
	    catch (ClassNotFoundException e) {
	      System.out.println("Could not load JDBC driver!");
	      return false;
	    }
	  }
	  
	  private boolean openConnection(String databaseName) {
	    String connectionString =
	        "jdbc:sqlserver://localhost:1433;" +
	        "instanceName=SQLEXPRESS;" +
	        "databaseName=" + databaseName + ";" +
	        "trustServerCertificate=true;" +
	        "integratedSecurity=true;";
	    
	    try {
	      System.out.println("Connecting to database...");
	      
	      setConnection(DriverManager.getConnection(connectionString));
	      
	      System.out.println("Connected to database");
	      
	      return true;
	    }
	    catch (SQLException e) {
	      setConnection(null);

	      System.out.println("Could not connect to database!");
	      System.out.println(e.getMessage());
	      
	      return false;
	    }
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
