package test;

import static database.entities.factory.Factory.*;

import org.junit.jupiter.api.Test;

import database.entities.City;
import database.entities.Employee;
import database.repositories.EmployeeRepository;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class EmployeeRepositoryTest extends BaseTestCase {

	private EmployeeRepository repo = new EmployeeRepository();

	@Test
	void itShouldAddNewEmployeeToDatabase() {

		Employee employee = employeeFactory().make();
		assertTrue(repo.add(employee) > 0);
	}

	@Test
	void shouldFindEmployeeInDatabaseByItsId() {
		Employee employee = employeeFactory().make();
		assertTrue(repo.add(employee) > 0);
		
		System.out.println(employee);
		Employee retreivedEmployee =  repo.find(employee.getId());
		
		assertTrue(retreivedEmployee.getId() == employee.getId());
	}

}
