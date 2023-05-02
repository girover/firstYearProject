package database.entities;

import java.sql.ResultSet;

public class CarImage extends Entity{

	@Override
	public boolean makeFromResultSet(ResultSet result) {
		return false;
	}

}
