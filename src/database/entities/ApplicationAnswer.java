package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the ApplicationAnswer table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class ApplicationAnswer extends Entity {
	
	
	
	
	
	public ApplicationAnswer() {
		setTable("applicationAnswer");
	}
	
	
	private int id;
	private int applicationID;
	private int userID;
	private String answerDate;
	private String accepted;
	private String note;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			applicationID = result.getInt("applicationID");
			userID = result.getInt("userID");
			answerDate = result.getString("answerDate");
			accepted = result.getString("accepted");
			note = result.getString("note");
			
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
