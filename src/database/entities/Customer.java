package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the Customer table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class Customer extends Entity {
	
	private int id;
	private String name;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String zipCode;
	private String creditworthiness;
	
	
	
	public Customer() {
		setTable("payment");
	}

	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
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




	public String getZipCode() {
		return zipCode;
	}




	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	


	public String getCreditworthiness() {
		return creditworthiness;
	}



	public void setCreditworthiness(String creditworthiness) {
		this.creditworthiness = creditworthiness;
	}



	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			name = result.getString("name");
			lastName = result.getString("lastName");
			phone = result.getString("phone");
			email = result.getString("email");
			address = result.getString("address");
			city = result.getString("city");
			zipCode = result.getString("zipCode");
			creditworthiness = result.getString("creditworthiness");
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
