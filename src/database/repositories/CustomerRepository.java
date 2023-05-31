package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Customer;
import entities.Entity;
import entities.User;

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
				+ "[CPRHash] = ?," 
				+ "[firstName] = ?," 
				+ "[lastName] = ?," 
				+ "[email] = ?,"
				+ "[phone] = ?," 
				+ "[address] = ?," 
				+ "[zipCode] = ? " 
				+ "WHERE [" + primaryKey + "] = ?";

		return update(sql, 
				customer.getCPRHash(), 
				customer.getFirstName(), 
				customer.getLastName(), 
				customer.getEmail(), 
				customer.getPhone(),
				customer.getAddress(), 
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
				+ "[CPRHash],"
				+ "[firstName]," 
				+ "[lastName]," 
				+ "[email]," 
				+ "[phone],"
				+ "[address]," 
				+ "[zipCode]) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";

		int id = insertAndGetGeneratedId(sql,
				customer.getCPRHash(),
				customer.getFirstName(), 
				customer.getLastName(), 
				customer.getEmail(),
				customer.getPhone(), 
				customer.getAddress(), 
				customer.getZipCode());

		if (id > 0) {
			customer.setId(id);
			customer.setExist(true);

			return id;
		}

		return 0;
	}
	
	public ResultSet search(String searchKey) {
		
		String sql = selectSql("WHERE firstName LIKE ? OR lastName LIKE ?;");
		
		return select(sql, "%" + searchKey + "%", "%" + searchKey + "%");
	}
	
	public Customer findByCpr(String hashedCpr) {
		
		String sql = selectSql("WHERE CPRHash = ?;");
		
		ResultSet result = select(sql, hashedCpr);
		try {
			if(result.next()) {
				Customer customer = new Customer();
				customer.mapFromResultSet(result);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Customer first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public Customer find(Object id) {
		return mapResultSetToEntity(joinWhere("city", "zipCode", "zipCode", primaryKey, "=", id));
	}

	@Override
	public Customer last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<Customer> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(joinWhere("city", "zipCode", "zipCode", column, operation, value));
	}
	
	private String selectSql(String conditionSql) {
		
		String joinWithCitySql = "SELECT * FROM [" + table + "] "
				 + "LEFT JOIN [city] "
				 + "ON [" + table + "].[zipCode] = "
				 + "[city].[zipCode] ";
		
		return joinWithCitySql + conditionSql;
	}

	@Override
	public ArrayList<Customer> getAll() {
		
		return mapResultSetToEntityList(join("city", "zipCode", "zipCode"));
	}

	@Override
	public ArrayList<Customer> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected Customer mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				Customer customer = new Customer();
				customer.mapFromResultSet(result);
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<Customer> mapResultSetToEntityList(ResultSet result) {
		ArrayList<Customer> customers = new ArrayList<>();
		
		try {
			while(result.next()) {
				Customer customer = new Customer();
				customer.mapFromResultSet(result);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}
}
