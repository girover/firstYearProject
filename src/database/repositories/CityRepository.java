package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.City;
import database.entities.Customer;
import database.entities.Entity;
import database.entities.User;

/**
 * This class provides a set of methods that can be used to perform common database operations,
 * such as retrieving all records, retrieving a single record by ID, updating a record, and deleting a
 * record.

 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *      
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/CustomerRepository.java">Class Code On Github</a>
 */
public class CityRepository extends Repository {

	public CityRepository() {
		setTable("city");
		setPrimaryKey("zipCode");
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + table + "] WHERE [" + primaryKey + "] = ?";

		return delete(sql, ((City) entity).getZipCode());
	}

	/**
	 * Updates the specified city in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		City city = (City) entity;

		String sql = "UPDATE [" + table + "] SET " 
				+ "[city] = ? " 
				+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, 
				city.getCity(), 
				city.getZipCode());
	}

	/**
	 * Insert the specified city into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {

		City city = (City) entity;

		String sql = "INSERT INTO [" + table + "] ([zipCode], [city]) VALUES (?, ?);";

		boolean affectedRows = insert(sql,
				city.getZipCode(),
				city.getCity());

		if (affectedRows) {
			city.setExist(true);

			return 1;
		}

		return 0;
	}
	
	@Override
	public City first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public City find(Object id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public City last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<City> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<City> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<City> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected City mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				City city = new City();
				city.mapFromResultSet(result);
				return city;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<City> mapResultSetToEntityList(ResultSet result) {
		ArrayList<City> cities = new ArrayList<>();
		
		try {
			while(result.next()) {
				City city = new City();
				city.mapFromResultSet(result);
				cities.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cities;
	}
}
