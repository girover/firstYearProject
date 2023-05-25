package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

public class City extends Entity {

	private String zipCode;
	private String city;

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
			zipCode = result.getString("zipCode");
			city = result.getString("city");

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
		sb.append("City:\n");
		sb.append(formatString("zip code", getZipCode()));
		sb.append(formatString("model", getCity()));
		
		return sb.toString();
	}

}
