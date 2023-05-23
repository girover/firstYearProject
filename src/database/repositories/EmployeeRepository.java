package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.entities.Employee;
import database.entities.Entity;
import database.entities.User;

/**
 * This class is a part of Data Access Layer. 
 * A class that represents a repository responsible for basic CRUD (Create, Read, Update,
 * Delete) operations on a database table [employee].
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
 * @see <a href="https://github.com/girover/firstYearProject/blob/main/src/database/repositories/EmployeeRepository.java">Class Code On Github</a>
 */
public class EmployeeRepository extends Repository {

	public EmployeeRepository() {
		setTable("employee");
	}
	
	/**
	 * Get employee by its id from database
	 * @param id
	 * @return
	 */
//	public Employee getById(int id) {
//		Employee employee = null;
//		
//		try {
//			ResultSet result = findById(id);
//			
//			if(result.next()) {
//				employee = new Employee();
//				employee.makeFromResultSet(result);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return employee;
//	}

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
				   + "[role] = ? "
				   + "WHERE " + primaryKey + " = ?";
		
		return update(sql, 
						employee.getFirstName(), 
						employee.getLastName(), 
						employee.getEmail(), 
						employee.getPhone(), 
						employee.getAddress(), 
						employee.getCity(), 
						employee.getZipCode(), 
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
				+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		int id = insertAndGetGeneratedId(sql, 
						employee.getFirstName(), 
						employee.getLastName(), 
						employee.getEmail(),
						employee.getPhone(),
						employee.getAddress(),
						employee.getCity(),
						employee.getZipCode(),
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

	@Override
	public Employee first() {
		return mapResultSetToEntity(getFirstRow());
	}

	@Override
	public Employee find(int id) {
		return mapResultSetToEntity(findById(id));
	}

	@Override
	public Employee last() {
		return mapResultSetToEntity(getLastRow());
	}

	@Override
	public ArrayList<Employee> getByCondition(String column, String operation, Object value) {
		return mapResultSetToEntityList(getRowsByACondition(column, operation, value));
	}

	@Override
	public ArrayList<Employee> getAll() {
		return mapResultSetToEntityList(getAllRows());
	}

	@Override
	public ArrayList<Employee> paginate(int pageNumber) {
		return mapResultSetToEntityList(getByPage(pageNumber));
	}

	@Override
	protected Employee mapResultSetToEntity(ResultSet result) {
		try {
			if(result.next()) {
				Employee employee = new Employee();
				employee.mapFromResultSet(result);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected ArrayList<Employee> mapResultSetToEntityList(ResultSet result) {
		ArrayList<Employee> employees = new ArrayList<>();
		
		try {
			while(result.next()) {
				Employee employee = new Employee();
				employee.mapFromResultSet(result);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}
}
