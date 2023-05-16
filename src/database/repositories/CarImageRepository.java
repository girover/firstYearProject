package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Car;
import database.entities.CarImage;
import database.entities.Entity;
import database.entities.User;

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
	
	public ArrayList<CarImage> getByCar(Car car) throws SQLException{
		String sql = "SELECT * FROM [carImage] WHERE [carID] = ?";
		
		ResultSet result = select(sql, car.getId());
		
		ArrayList<CarImage> list = new ArrayList<>();
		
		while(result.next()) {
			CarImage carImage = new CarImage();
			carImage.mapFromResultSet(result);
			list.add(carImage);
		}
		
		return list;
	}

	@Override
	public CarImage first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public CarImage find(int id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public CarImage last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<CarImage> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<CarImage> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected CarImage mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				CarImage carImage = new CarImage();
				carImage.mapFromResultSet(result);
				return carImage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<CarImage> mapResultSetToEntityList(ResultSet result) {
		ArrayList<CarImage> carImages = new ArrayList<>();
		
		try {
			while(result.next()) {
				CarImage carImage = new CarImage();
				carImage.mapFromResultSet(result);
				carImages.add(carImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carImages;
	}
}
