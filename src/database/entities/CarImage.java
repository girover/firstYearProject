package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the carImage table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class CarImage extends Entity{
	
	private int id;
	private int carID;
	private String image;
	
	public CarImage() {
		setTable("carimage");
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		
		
		try {
			id = result.getInt("id");
			carID = result.getInt("carID");
			image = result.getString("image");
			
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
