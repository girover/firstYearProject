package database.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.App;
import database.entities.Car;
import database.entities.Entity;
import database.entities.User;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [user].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/UserRepository.java">Class Code On Github</a>
 */
public class UserRepository extends Repository {

	public UserRepository() {
		setTable("user");
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param id : the id of the user
	 * @return User entity
	 */
	public User getById(int id) {
		return mapResultSetToEntity(findById(id));
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param id : the id of the user
	 * @return User entity
	 */
	public User getById(String id) {
		return mapResultSetToEntity(getRowsByACondition(primaryKey, "=", id));
	}

	/**
	 * Get user by its primaryKey's value
	 * 
	 * @param value : the id of the user
	 * @return User entity
	 */
	public User getByAuthenticationField(String field, String value) {
		return mapResultSetToEntity(getRowsByACondition(field, "=", value));
	}

	/**
	 * Delete the given user from database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((User) entity).getId());
	}

	/**
	 * Update the given user in database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		User user = (User)entity;
		
		String sql = "UPDATE [" +table+ "] SET [userName] = ?, [password] = ?, "
				   + "[employeeId] = ? "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, user.getUserName(), user.getPassword(), user.getEmployeeId(), user.getId());
	}

	/**
	 * Insert the given user into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		User user = (User)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				+ "([employeeId], [userName], [password]) VALUES "
				+ "(?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, user.getEmployeeId(), user.getUserName(), user.getPassword());
		
		if(id > 0) {
			user.setId(id);
			user.setExist(true);
			
			return id;
		}
		
		return 0;
	}

	@Override
	public User first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public User find(int id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public User last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<User> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<User> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<User> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected User mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				User user = new User();
				user.mapFromResultSet(result);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<User> mapResultSetToEntityList(ResultSet result) {
		ArrayList<User> users = new ArrayList<>();
		
		try {
			while(result.next()) {
				User user = new User();
				user.mapFromResultSet(result);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
