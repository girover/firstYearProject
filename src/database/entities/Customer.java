package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the Customer table in the database.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussein Farhan
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
	private String zipCode;
	
	// Relational properties
	private String city;

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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public boolean mapFromResultSet(ResultSet result) {
		try {
			id = result.getInt("id");
			firstName = result.getString("firstName");
			lastName = result.getString("lastName");
			phone = result.getString("phone");
			email = result.getString("email");
			address = result.getString("address");
			zipCode = result.getString("zipCode");
			CPRHash = result.getString("CPRHash");
			
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
	
	public ArrayList<String[]> getAsCsvList(){
		
		String[] headers = {"ID","First Name","Last Name","Phone","Email","Address","Zip Code","City"};
		String[] values = {
							Integer.toString(getId())
							,getFirstName()
							,getLastName()
							,getPhone()
							,getEmail()
							,getAddress()
							,getZipCode()
							,getCity()
						  };
		ArrayList<String[]> data = new ArrayList<>();
		data.add(headers);
		data.add(values);
		
		return data;
	}
	
	private String formatString(String key, Object value) {
		return String.format("%-20s : %-20s\n", key, value);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Car:\n");
		sb.append(formatString("Id", getId()));
		sb.append(formatString("First name", getFirstName()));
		sb.append(formatString("Last name", getLastName()));
		sb.append(formatString("Phone", getPhone()));
		sb.append(formatString("Email", getEmail()));
		sb.append(formatString("Address", getAddress()));
		sb.append(formatString("Zip code", getZipCode()));
		sb.append(formatString("City", getCity()));
		
		return sb.toString();
	}

}
