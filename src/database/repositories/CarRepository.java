package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Car;
import database.entities.Entity;
import database.entities.LoanApplication;
import database.entities.User;

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
				+ "[kmPerLiter]," 
				+ "[VIN]," 
				+ "[price],"
				+ "[description]"
				+ ") VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
				car.getKmPerLiter(), 
				car.getVin(), 
				car.getPrice(), 
				car.getDescription());

		if (id > 0) {
			car.setId(id);
			car.setExist(true);

			return id;
		}

		return 0;
	}

	public ArrayList<Car> getDistinctModels() throws SQLException{
		
		String sql = "SELECT DISTINCT [model] FROM " + table + ";";
		ResultSet result = select(sql);
		
		ArrayList<Car> carsModels = new ArrayList<>();
		while(result.next()) {
			Car car = new Car();
			car.setModel(result.getString("model"));
			carsModels.add(car);
		}
		
		return carsModels;
	}
	
	public ArrayList<Car> getFreeCars(){
		String sql = "SELECT * FROM [car] "
				+ "WHERE [id] NOT IN (SELECT [carID] FROM [loanApplication] WHERE [status] = '"+LoanApplication.PROCESSING+"' or [status] = '"+LoanApplication.APPROVED+"');";
		
		ResultSet result = select(sql);
		return mapResultSetToEntityList(result);
	}
	
	public ArrayList<Car> getByModel(String model){
		return mapResultSetToEntityList(getRowsByACondition("model", "=", model));
	}

	@Override
	public Car first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public Car find(int id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public Car last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<Car> getByCondition(String column, String operation, String value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<Car> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<Car> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected Car mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				Car car = new Car();
				car.mapFromResultSet(result);
				return car;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<Car> mapResultSetToEntityList(ResultSet result) {
		ArrayList<Car> cars = new ArrayList<>();
		
		try {
			while(result.next()) {
				Car car = new Car();
				car.mapFromResultSet(result);
				cars.add(car);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cars;
	}
}
