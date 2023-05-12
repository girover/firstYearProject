package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Car;
import database.entities.Entity;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [car].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/CarRepository.java">Class Code On Github</a>
 */
public class CarRepository extends Repository {

	public CarRepository() {
		setTable("car");
	}

	/**
	 * Get car by its id from database
	 * 
	 * @param id
	 * @return
	 */
	public Car getById(int id) {
		
		Car car = null;

		try {
			ResultSet result = find(id);

			if (result.next()) {
				car = new Car();
				car.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] "
					+ "WHERE [" + entity.getPrimaryKey() + "] = ?";

		return delete(sql, ((Car) entity).getId());
	}

	/**
	 * Updates the specified car in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		Car car = (Car) entity;

		String sql = "UPDATE [" + table + "] SET " 
				+ "[brand] = ?," 
				+ "[model] = ?," 
				+ "[year] = ?,"
				+ "[color] = ?," 
				+ "[mileage] = ?," 
				+ "[transmission] = ?," 
				+ "[fuelType] = ?,"
				+ "[engineSize] = ?," 
				+ "[horsepower] = ?," 
				+ "[seats] = ?," 
				+ "[doors] = ?," 
				+ "[price] = ? "
				+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, 
				car.getBrand(), 
				car.getModel(), 
				car.getYear(), 
				car.getColor(), 
				car.getMileage(),
				car.getTransmission(), 
				car.getFuelType(), 
				car.getEngineSize(), 
				car.getHorsepower(), 
				car.getSeats(),
				car.getDoors(), 
				car.getPrice(), 
				car.getId());
	}

	/**
	 * Insert the specified car into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {

		Car car = (Car) entity;

		String sql = "INSERT INTO [" + table + "] (" 
				+ "[brand]," 
				+ "[model]," 
				+ "[year],"
				+ "[color]," 
				+ "[mileage]," 
				+ "[transmission]," 
				+ "[fuelType],"
				+ "[engineSize]," 
				+ "[horsepower]," 
				+ "[seats]," 
				+ "[doors]," 
				+ "[price]"
				+ ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, 
				car.getBrand(), 
				car.getModel(), 
				car.getYear(), 
				car.getColor(),
				car.getMileage(), 
				car.getTransmission(), 
				car.getFuelType(), 
				car.getEngineSize(), 
				car.getHorsepower(),
				car.getSeats(), 
				car.getDoors(), 
				car.getPrice());

		if (id > 0) {
			car.setId(id);
			car.setExist(true);

			return id;
		}

		return 0;
	}
	
	public ArrayList<Car> getDistinctModels() throws SQLException{
		
		ArrayList<Car> cars = new ArrayList<>();
		
		ResultSet result = getAll();
		while(result.next()) {
			Car car = new Car();
			car.makeFromResultSet(result);
			cars.add(car);
		}
		return cars;
	}
	
	public ArrayList<Car> getByModel(String model){
		ArrayList<Car> cars = new ArrayList<>();
		
		ResultSet result = getByACondition("model", "=", model);
		try {
			while(result.next()) {
				Car car = new Car();
				car.makeFromResultSet(result);
				cars.add(car);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}
}
