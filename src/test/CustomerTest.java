package test;

import static database.entities.factory.Factory.*;

import org.junit.jupiter.api.Test;

import database.entities.City;
import database.entities.Customer;
import database.repositories.CityRepository;
import database.repositories.CustomerRepository;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CustomerTest extends BaseTestCase {

	private CustomerRepository repo = new CustomerRepository();
	private CityRepository cityRepo = new CityRepository();

	@Test
	void itCanAddNewCustomerToDatabase() {

		City city = cityFactory().make();
		assertTrue(cityRepo.add(city) > 0);
		
		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());
		
		assertTrue(repo.add(customer) > 0);
	}

	@Test
	void shouldFindCustomerInDatabaseByItsId() {
		
		City city = cityFactory().make();
		cityRepo.add(city);
		
		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());

		assertTrue(repo.add(customer) > 0);

		Customer retrievedCustomer = new Customer();
		retrievedCustomer = (Customer) repo.find(customer.getId());

		assertTrue(retrievedCustomer.getId() == customer.getId());
	}

}
