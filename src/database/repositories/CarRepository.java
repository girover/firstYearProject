package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Car;
import database.entities.Entity;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the Car table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class CarRepository extends Repository {
	
	
	public CarRepository() {
		setTable("car");
	}
	
	
	/**
	 * Get car by its id from database
	 * @param id
	 * @return
	 */
	public Car getById(int id) {
		Car car = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
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

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

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
		
		Car car = (Car)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[brand] = ?, "
				   + "[model] = ?, "
				   + "[year] = ?, "
				   + "[color] = ?, "
				   + "[mileage] = ?, "
				   + "[transmission] = ?, "
				   + "[fuelType] = ?, "
				   + "[engineSize] = ?, "
				   + "[horsepower] = ?, "
				   + "[seats] = ?, "
				   + "[doors] = ?, "
				   + "[price] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
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
		        car.getPrice());
	}

	/**
	 * Insert the specified car into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		Car car = (Car)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[brand] = ?, "
				   + "[model] = ?, "
				   + "[year] = ?, "
				   + "[color] = ?, "
				   + "[mileage] = ?, "
				   + "[transmission] = ?, "
				   + "[fuelType] = ?, "
				   + "[engineSize] = ?, "
				   + "[horsepower] = ?, "
				   + "[seats] = ?, "
				   + "[doors] = ?, "
				   + "[price] = ?, "
				   + ") "
				   + "VALUES "
				   + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
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
		
		
		if(id > 0) {
			car.setId(id);
			car.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
