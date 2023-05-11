package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the Customer table in the database.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/Customer.java">Class Code On Github</a>
 */
public class Customer extends Entity {

	private int id;
	private String CPRHash;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String zipCode;
//	private String creditworthiness;

	public Customer() {
		setTable("customer");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCPRHash() {
		return CPRHash;
	}

	public void setCPRHash(String cPRHash) {
		CPRHash = cPRHash;
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

//	public String getCreditworthiness() {
//		return creditworthiness;
//	}
//
//	public void setCreditworthiness(String creditworthiness) {
//		this.creditworthiness = creditworthiness;
//	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			firstName = result.getString("firstName");
			lastName = result.getString("lastName");
			phone = result.getString("phone");
			email = result.getString("email");
			address = result.getString("address");
			city = result.getString("city");
			zipCode = result.getString("zipCode");
			CPRHash = result.getString("CPRHash");
//			creditworthiness = result.getString("creditworthiness");

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
