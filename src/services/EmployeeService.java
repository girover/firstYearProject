package services;

import java.util.ArrayList;

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
	public Employee find(Object id) {
		Employee employee = (Employee) repository.find(id);
		return employee;
	}

	@Override
	public ArrayList<Employee> getAll() {
		
		return (ArrayList<Employee>) repository.getAll();
	}

	@Override
	public Employee create(Entity entity) {
		
		Employee employee = (Employee)entity;

		repository.add(employee);

		return employee;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((Employee) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Employee) entity);
	}

}
