package test;

import static database.entities.factory.Factory.customerFactory;

import org.junit.jupiter.api.Test;

import database.entities.Customer;
import database.repositories.CustomerRepository;

/**
 * 
 * @author Majed Hussein Farhan - <b style="color:red">girover.mhf@gmail.com</b>
 *         - <a href="https://github.com/girover">Github Profile</a>
 *
 */
class CustomerTest extends BaseTestCase {

	private CustomerRepository repo = new CustomerRepository();

	@Test
	void itCanAddNewCustomerToDatabase() {

		Customer customer = customerFactory().make();
		assertTrue(repo.add(customer) > 0);
	}

	@Test
	void shouldFindCustomerInDatabaseByItsId() {
		Customer customer = customerFactory().make();

		assertTrue(repo.add(customer) > 0);

		Customer retrievedCustomer = new Customer();
		retrievedCustomer = (Customer) repo.find(customer.getId());

		assertTrue(retrievedCustomer.getId() == customer.getId());
	}

}
