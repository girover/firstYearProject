package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.Employee;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class EmployeeFactory implements EntityFactory {
	
	@Override
	public Employee make() {
		return Faker.employee();
	}

	@Override
	public ArrayList<Employee> make(int quantity) {
		
		ArrayList<Employee> employees = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			employees.add(Faker.employee());

		return employees;
	}

}
