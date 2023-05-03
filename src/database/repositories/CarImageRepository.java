package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.CarImage;
import database.entities.Entity;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [carImage].
 *
 * This class provides a set of methods that can be used to perform common database operations,
 * such as retrieving all records, retrieving a single record by ID, updating a record, and deleting a
 * record.
 *
 * @version 1.0
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussen Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/CarImageRepository.java">Class Code On Github</a>
 */
public class CarImageRepository extends Repository {

	public CarImageRepository() {
		setTable("carImage");
	}

	/**
	 * Get CarImage by its id from database
	 * 
	 * @param id
	 * @return
	 */
	public CarImage getById(int id) {
		
		CarImage carImage = null;

		try {
			ResultSet result = find(id);

			if (result.next()) {
				carImage = new CarImage();
				carImage.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carImage;
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE [" + entity.getPrimaryKey() + "] = ?";

		return delete(sql, ((CarImage) entity).getId());
	}

	/**
	 * Updates the specified CarImage in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		CarImage carImage = (CarImage) entity;

		String sql = "UPDATE [" + table + "] SET "
					+ "[carID] = ?,"
					+ "[image] = ?,"
					+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, carImage.getCarID(), carImage.getImage(), carImage.getId());
	}

	/**
	 * Insert the specified CarImage into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {

		CarImage carImage = (CarImage) entity;

		String sql = "INSERT INTO [" + table + "] ("
					+ "[carID],"
					+ "[image]) "
					+ "VALUES (?, ?)";

		int id = insertAndGetGeneratedId(sql, carImage.getCarID(), carImage.getImage());

		if (id > 0) {
			carImage.setId(id);
			carImage.setExist(true);

			return id;
		}

		return 0;
	}
}
