package database.entities;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a part of Data Access Layer. This class represents a Data
 * Access Object for the carImage table in the database.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/entities/CarImage.java">Class Code On Github</a>
 */
public class CarImage extends Entity {

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

			setExist(true);
			
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
