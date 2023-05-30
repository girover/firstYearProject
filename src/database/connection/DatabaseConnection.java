package database.connection;

import java.sql.Connection;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 */
public interface DatabaseConnection {

	public Connection getConnection();
	
	public void setConnection(Connection connection);
}
