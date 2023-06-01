package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *
 */
public class City extends Entity {

	private int zipCode;
	private String city;

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
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
			zipCode = result.getInt("zipCode");
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
