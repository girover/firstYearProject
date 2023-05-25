package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Entity {
	
	public static final String MANAGER = "manager";
	public static final String SELLER = "seller";

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String zipCode;
	private String hireDate;
	private String department;
	private String role;
	
	// Relationship attributes
	private String city;
	
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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String postNumber) {
		this.zipCode = postNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
	public boolean mapFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			firstName = result.getString("firstName");
			lastName = result.getString("lastName");
			email = result.getString("email");
			phone = result.getString("phone");
			address = result.getString("address");
			zipCode = result.getString("zipCode");
			hireDate = result.getString("hireDate");
			department = result.getString("department");
			role = result.getString("role");

			try {				
				city = result.getString("city");
			} catch(SQLException e){
				city = "";
			}
			
			setExist(true);
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String formatString(String key, Object value) {
		return String.format("%-20s : %-20s\n", key, value);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car:\n");
		sb.append(formatString("id", getId()));
		sb.append(formatString("first name", getFirstName()));
		sb.append(formatString("last name", getLastName()));
		sb.append(formatString("email", getEmail()));
		sb.append(formatString("phone", getPhone()));
		sb.append(formatString("address", getAddress()));
		sb.append(formatString("hire date", getHireDate()));
		sb.append(formatString("department", getDepartment()));
		sb.append(formatString("role", getRole()));
		sb.append(formatString("zip code", getZipCode()));
		sb.append(formatString("city", getCity()));
		
		return sb.toString();
	}

}
