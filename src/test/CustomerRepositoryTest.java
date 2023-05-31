package test;

import static entities.factory.Factory.cityFactory;
import static entities.factory.Factory.customerFactory;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.repositories.CityRepository;
import database.repositories.CustomerRepository;
import entities.City;
import entities.Customer;

/**
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CustomerRepositoryTest extends BaseTestCase {

	private CustomerRepository repo = new CustomerRepository();
	
	/**
	 * Since the zipCode column in the Customers table is a foreign key 
	 * that references the zipCode column in the City table,
	 * it is important to create the cities before creating the customers. 
	 * This ensures that the referenced zipCode codes exist before they are referenced.
	 */
	private City city;
	
	@BeforeEach
	void createRelationships() {
		CityRepository repo = new CityRepository();
		City city = cityFactory().make();
		repo.add(city);
		
		this.city = city;
	}

	@Test
	void shouldAddNewCustomerToDatabase() {

		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());

		assertTrue(repo.add(customer) > 0);
	}

	@Test 
	void shouldAddListOfCustomersToDatabase(){
		
		ArrayList<Customer> customers = customerFactory().makeAndSetZipCode(10, city.getZipCode());
		
		int createdCustomers = repo.addAll(customers);
		
		assertTrue(createdCustomers == 10);
	}

	@Test
	void shouldFindCustomerInDatabaseByItsId() {

		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());

		assertTrue(repo.add(customer) > 0);

		Customer retrievedCustomer = repo.find(customer.getId());

		assertTrue(retrievedCustomer.getId() == customer.getId());
	}

	@Test
	void shouldGetAllCustomersFromDatabase() {

		ArrayList<Customer> customers = customerFactory().makeAndSetZipCode(10, city.getZipCode());
		repo.addAll(customers);
		
		assertTrue(repo.getAll().size() == 10);
	}
	
	@Test
	void shouldCountCustomersInTheDatabase() {
		ArrayList<Customer> customers = customerFactory().makeAndSetZipCode(3, city.getZipCode());
		repo.addAll(customers);
		
		assertTrue(repo.count() == 3);
	}
	
	@Test
	void shouldDeleteCustomerFromDatabase(){
		
		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());
		repo.add(customer);
		
		assertTrue(repo.count() == 1);
		
		repo.delete(customer);
		assertTrue(repo.count() == 0);
	}
	
	@Test
	void shouldPerformCorrectCustomerUpdateInDatabase() {

		Customer customer = customerFactory().make();
		customer.setZipCode(city.getZipCode());
		int generatedId = repo.add(customer);
		
		assertTrue(generatedId > 0);
		
		customer.setAddress("new address");
		customer.setEmail("test@test.com");
		customer.setFirstName("first name");
		customer.setLastName("last name");
		customer.setPhone("0000000");
		
		assertTrue(repo.update(customer));
	}
}
