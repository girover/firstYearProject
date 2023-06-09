package entities.factory;

import java.util.ArrayList;

import entities.Car;
import entities.Customer;
import faker.Faker;

/*
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
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

	public ArrayList<Customer> makeAndSetZipCode(int quantity, int zipCode){
		ArrayList<Customer> customers = make(quantity);
		for (Customer customer : customers) {
			customer.setZipCode(zipCode);
		}

		return customers;
	}

}
