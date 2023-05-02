package database.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.entities.Employee;
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
}
