package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Customer;
import database.entities.Entity;
/**
 * This class is a part of Data Access Layer.
 * This class represents a Data Access Object for the Customer table in the database.
 *
 * @author Rasmus Lysgaard Villadsen
 * @email mrmaklie@gmail.com
 * @see <a href="https://github.com/MrMaklie">https://github.com/MrMaklie</a>
 */
public class CustomerRepository extends Repository {
	
	
	public CustomerRepository() {
		setTable("customer");
	}
	
	
	/**
	 * Get customer by its id from database
	 * @param id
	 * @return
	 */
	public Customer getById(int id) {
		Customer customer = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
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

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

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
		
		Customer customer = (Customer)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[nameame] = ?, "
				   + "[lastName] = ?, "
				   + "[email] = ?, "
				   + "[phone] = ?, "
				   + "[address] = ?, "
				   + "[city] = ?, "
				   + "[zipCode] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
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
		
		Customer customer = (Customer)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				   + "("
				   + "[name], "
				   + "[lastName], "
				   + "[email], "
				   + "[phone], "
				   + "[address], "
				   + "[city], "
				   + "[zipCode], "
				   + ") "
				   + "VALUES "
				   + "(name, lastName, email, phone, address, city, zipCode)";
		
		int id = insertAndGetGeneratedId(sql, 
				customer.getName(), 
				customer.getLastName(), 
				customer.getEmail(),
				customer.getPhone(),
				customer.getAddress(),
				customer.getCity(),
				customer.getZipCode());
		
		if(id > 0) {
			customer.setId(id);
			customer.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
