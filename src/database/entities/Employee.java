package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Entity {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String zipCode;
	private String hireDate;
	private String department;
	private String role;
	
	public Employee() {
		setTable("employee");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostNumber() {
		return zipCode;
	}

	public void setPostNumber(String postNumber) {
		this.zipCode = postNumber;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			firstName = result.getString("firstName");
			lastName = result.getString("lastName");
			email = result.getString("email");
			phone = result.getString("phone");
			address = result.getString("address");
			city = result.getString("city");
			zipCode = result.getString("zipCode");
			hireDate = result.getString("hireDate");
			department = result.getString("department");
			role = result.getString("role");
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
