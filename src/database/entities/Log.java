package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the Log table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class Log extends Entity {
	
	private int id;
	private int userID;
	private String logType;
	private String logMessage;
	private String createdAt;
	
	
	public Log() {
		setTable("log");
	}
	
	
	

	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getUserID() {
		return userID;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}




	public String getLogType() {
		return logType;
	}




	public void setLogType(String logType) {
		this.logType = logType;
	}




	public String getLogMessage() {
		return logMessage;
	}




	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}




	public String getCreatedAt() {
		return createdAt;
	}




	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}




	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			userID = result.getInt("userID");
			logType = result.getString("logType");
			logMessage = result.getString("logMessage");
			createdAt = result.getString("createdAt");
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
