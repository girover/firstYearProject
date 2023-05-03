package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Employee;
import database.entities.Entity;
import database.entities.User;

public class EmployeeRepositroy extends Repository {

	public EmployeeRepositroy() {
		setTable("employee");
	}
	
	/**
	 * Get employee by its id from database
	 * @param id
	 * @return
	 */
	public Employee getById(int id) {
		Employee employee = null;
		
		try {
			ResultSet result = find(id);
			
			if(result.next()) {
				employee = new Employee();
				employee.makeFromResultSet(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employee;
	}

	/**
	 * Delete the specified employee from database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean delete(Entity entity) {

		String sql = "DELETE FROM [" + entity.getTable() + "] WHERE " + entity.getPrimaryKey() + " = ?";

		return delete(sql, ((Employee) entity).getId());
	}

	/**
	 * Updates the specified employee in the database with the latest changes.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public boolean update(Entity entity) {
		
		Employee employee = (Employee)entity;
		
		String sql = "UPDATE [" +table+ "] SET "
				   + "[firstName] = ?, "
				   + "[lastName] = ?, "
				   + "[email] = ?, "
				   + "[phone] = ?, "
				   + "[address] = ?, "
				   + "[city] = ?, "
				   + "[zipCode] = ?, "
				   + "[hireDate] = ?, "
				   + "[department] = ?, "
				   + "[role] = ?, "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
						employee.getFirstName(), 
						employee.getLastName(), 
						employee.getEmail(), 
						employee.getPhone(), 
						employee.getAddress(), 
						employee.getCity(), 
						employee.getPostNumber(), 
						employee.getHireDate(), 
						employee.getDepartment(), 
						employee.getRole(), 
						employee.getId());
	}

	/**
	 * Insert the specified employee into database table.
	 * 
	 * @param Entity entity
	 * @return boolean
	 */
	@Override
	public int add(Entity entity) {
		
		Employee employee = (Employee)entity;
		
		String sql = "INSERT INTO [" + table + "] "
				+ "("
				+ "[firstName], "
				+ "[lastName], "
				+ "[email], "
				+ "[phone], "
				+ "[address], "
				+ "[city], "
				+ "[zipCode], "
				+ "[hireDate], "
				+ "[department], "
				+ "[role], "
				+ ") "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?, ?, , ?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, 
						employee.getFirstName(), 
						employee.getLastName(), 
						employee.getEmail(),
						employee.getPhone(),
						employee.getAddress(),
						employee.getCity(),
						employee.getPostNumber(),
						employee.getHireDate(),
						employee.getDepartment(),
						employee.getRole()
					);
		
		if(id > 0) {
			employee.setId(id);
			employee.setExist(true);
			
			return id;
		}
		
		return 0;
	}
}
