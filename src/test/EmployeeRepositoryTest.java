package test;

import static entities.factory.Factory.employeeFactory;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import database.repositories.EmployeeRepository;
import entities.Employee;

/**
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class EmployeeRepositoryTest extends BaseTestCase {

	private EmployeeRepository repo = new EmployeeRepository();

	@Test
	void shouldAddNewEmployeeToTheDatabase() {

		Employee employee = employeeFactory().make();
		assertTrue(repo.add(employee) > 0);
	}

	@Test
	void shouldAddAListOfEmployeesToTheDatabase() {

		ArrayList<Employee> employees = employeeFactory().make(10);

		int createdEmployeesCount = repo.addAll(employees);

		assertTrue(createdEmployeesCount == 10);
	}

	@Test
	void shouldFindEmployeeInDatabaseByItsId() {
		
		Employee employee = employeeFactory().make();
		assertTrue(repo.add(employee) > 0);
		
		Employee retreivedEmployee =  repo.find(employee.getId());
		
		assertTrue(retreivedEmployee.getId() == employee.getId());
	}

	@Test
	void shouldGetAllEmployeesFromTheDatabase() {

		ArrayList<Employee> employees = employeeFactory().make(10);
		repo.addAll(employees);

		assertTrue(repo.getAll().size() == 10);
	}

	@Test
	void shouldCountEmployeesInTheDatabase() {
		ArrayList<Employee> employees = employeeFactory().make(10);
		repo.addAll(employees);

		assertTrue(repo.count() == 10);
	}

	@Test
	void shouldDeleteEmployeeFromTheDatabase() {
		Employee employee = employeeFactory().make();
		repo.add(employee);

		assertTrue(repo.count() == 1);

		repo.delete(employee);
		assertTrue(repo.count() == 0);
	}

	@Test
	void shouldPerformCorrectEmployeeUpdateInDatabase() {

		Employee employee = employeeFactory().make();

		int generatedId = repo.add(employee);

		assertTrue(generatedId > 0);

		employee.setFirstName("f name");
		employee.setLastName("l name");
		employee.setPhone("88888888");
		employee.setAddress("addddddddddd");
		employee.setEmail("test@test.com");
		employee.setRole("seller");

		assertTrue(repo.update(employee));
		
		Employee retrievedEmployee = repo.find(employee.getId());
		assertTrue(retrievedEmployee != null);
		assertTrue(retrievedEmployee.getFirstName().equals("f name"));
		assertTrue(retrievedEmployee.getLastName().equals("l name"));
		assertTrue(retrievedEmployee.getPhone().equals("88888888"));
		assertTrue(retrievedEmployee.getAddress().equals("addddddddddd"));
		assertTrue(retrievedEmployee.getEmail().equals("test@test.com"));
		assertTrue(retrievedEmployee.getRole().equals("seller"));
	}

}
