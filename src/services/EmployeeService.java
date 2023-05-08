package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

import database.entities.Employee;
import database.entities.Entity;
import database.repositories.EmployeeRepository;




/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with employees.
 * such as retrieving all employees, retrieving a set of employees, 
 * retrieving a single employee by ID, updating a employee, 
 * and deleting a employee.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class EmployeeService extends BaseResourceService {

	public EmployeeService() {
		repository = new EmployeeRepository();
	}

	@Override
	public Employee find(int id) {
		Employee employee = (Employee) repository.find(id);
		return employee;
	}

	@Override
	public ArrayList<Employee> getAll() {
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				Employee employee = new Employee();
				if (employee.makeFromResultSet(result))
					employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public ArrayList<Employee> getPage(int page) {

		
		ArrayList<Employee> employees = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				Employee employee = new Employee();
				if (employee.makeFromResultSet(result))
					employees.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}

	@Override
	public Employee create(FormData data) {
		
		Employee employee = new Employee();
		
		employee.setFirstName((String)data.input("firstName"));
		employee.setLastName((String)data.input("lastName"));
		employee.setEmail((String)data.input("email"));
		employee.setPhone((String)data.input("phone"));
		employee.setAddress((String)data.input("address"));
		employee.setCity((String)data.input("city"));
		employee.setZipCode((String)data.input("zipCode"));
		employee.setHireDate((String)data.input("hireDate"));
		employee.setDepartment((String)data.input("department"));
		employee.setRole((String)data.input("role"));

		repository.add(employee);

		return employee;
	}

	@Override
	public Employee update(Entity entity) {
		if (repository.update((Employee) entity))
			return ((Employee) entity);
		
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Employee) entity);
	}

}
