package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import authentication.Auth;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the User table in the database.
 *
 * @version 1.0
 * 
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 *
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/Customer.java">Class Code On Github</a>
 */
public class User extends Auth {

	public User() {
		super();
		setTable("user");
		setPrimaryKey("id");
	}

	private int id;
	private int employeeId;
	private String userName;
	private String password;
	// Relationship attributes
	private Employee employee;
	private int maxApprovalLimit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public int getMaxApprovalLimit() {
		return maxApprovalLimit;
	}

	public void setMaxApprovalLimit(int maxApprovalLimit) {
		this.maxApprovalLimit = maxApprovalLimit;
	}

	@Override
	public boolean login(String firstCredential, String secondCredential) {

		return false;
	}

	@Override
	public boolean mapFromResultSet(ResultSet result) {
	
		try {
			id = result.getInt("id");
			userName = result.getString("userName");
			password = result.getString("password");
			employeeId = result.getInt("employeeId");

			setExist(true);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
