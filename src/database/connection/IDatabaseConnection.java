package database.connection;

import java.sql.Connection;

public interface IDatabaseConnection {

	public Connection getConnection();
	
	public void setConnection(Connection connection);
}
