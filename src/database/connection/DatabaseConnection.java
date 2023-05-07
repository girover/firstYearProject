package database.connection;

import java.sql.Connection;

public interface DatabaseConnection {

	public Connection getConnection();
	
	public void setConnection(Connection connection);
}
