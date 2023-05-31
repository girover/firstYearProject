package entities.factory;

import java.util.ArrayList;

import entities.Car;
import entities.Employee;
import faker.Faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
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
