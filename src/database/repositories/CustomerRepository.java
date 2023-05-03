package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Customer;
import database.entities.Entity;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [customer].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/CustomerRepository.java">Class Code On Github</a>
 */
public class CustomerRepository extends Repository {

	public CustomerRepository() {
		setTable("customer");
	}

	/**
	 * Get customer by its id from database
	 * 
	 * @param id
	 * @return
	 */
	public Customer getById(int id) {
		Customer customer = null;

		try {
			ResultSet result = find(id);

			if (result.next()) {
				customer = new Customer();
				customer.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE [" + entity.getPrimaryKey() + "] = ?";

		return delete(sql, ((Customer) entity).getId());
	}

	/**
	 * Updates the specified customer in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {

		Customer customer = (Customer) entity;

		String sql = "UPDATE [" + table + "] SET " 
				+ "[nameame] = ?," 
				+ "[lastName] = ?," 
				+ "[email] = ?,"
				+ "[phone] = ?," 
				+ "[address] = ?," 
				+ "[city] = ?," 
				+ "[zipCode] = ? " 
				+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, 
				customer.getName(), 
				customer.getLastName(), 
				customer.getEmail(), 
				customer.getPhone(),
				customer.getAddress(), 
				customer.getCity(), 
				customer.getZipCode(), 
				customer.getId());
	}

	/**
	 * Insert the specified customer into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {

		Customer customer = (Customer) entity;

		String sql = "INSERT INTO [" + table + "] (" 
				+ "[name], " 
				+ "[lastName], " 
				+ "[email], " 
				+ "[phone], "
				+ "[address], " 
				+ "[city], " 
				+ "[zipCode]) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		int id = insertAndGetGeneratedId(sql, 
				customer.getName(), 
				customer.getLastName(), 
				customer.getEmail(),
				customer.getPhone(), 
				customer.getAddress(), 
				customer.getCity(), 
				customer.getZipCode());

		if (id > 0) {
			customer.setId(id);
			customer.setExist(true);

			return id;
		}

		return 0;
	}
}
