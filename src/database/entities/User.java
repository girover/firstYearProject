package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import authentication.Auth;
import authentication.Authenticatable;

public class User extends Auth implements Authenticatable {

	public User() {
		super();
		setTable("user");
		setPrimaryKey("id");
	}

	private int id;
	private int employeeId;
	private String userName;
	private String password;
	private Employee employee;

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

	@Override
	public boolean login(String firstCredential, String secondCredential) {

		return false;
	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
	
		try {
			id = result.getInt("id");
			employeeId = result.getInt("employeeId");
			userName = result.getString("name");
			password = result.getString("password");
	
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
