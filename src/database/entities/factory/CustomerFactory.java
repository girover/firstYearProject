package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.Customer;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class CustomerFactory implements EntityFactory {

	@Override
	public Customer make() {
		return Faker.customer();
	}

	@Override
	public ArrayList<Customer> make(int quantity) {
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			customers.add(Faker.customer());

		return customers;
	}

}
