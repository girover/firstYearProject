package services.log;

import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Entity;

/**
 * @author Majed Hussein Farhan - <b style="color:red">
 *         girover.mhf@gmail.com</b> -
 *         <a href="https://github.com/girover">Github</a>
 */
public class LogEntity extends Entity {
	
	private int id;
	private int userId;
	private String logType;
	private String logMessage;
	private String createdAt;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	public boolean mapFromResultSet(ResultSet result) {
		try {
			userId = result.getInt("userID");
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
