package services;

import java.sql.ResultSet;
import java.util.ArrayList;

import database.repositories.CustomerRepository;
import entities.Customer;
import entities.Entity;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with customers.
 * such as retrieving all customers, retrieving a set of customers, 
 * retrieving a single customer by ID, updating a customer, 
 * and deleting a customer.
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class CustomerService extends BaseResourceService {

	public CustomerService() {
		repository = new CustomerRepository();
//		entityClass = Customer.class;
	}

	@Override
	public Customer find(Object id) {
		Customer customer = (Customer) repository.find(id);
		return customer;
	}

	@Override
	public ArrayList<Customer> getAll() {
		
		return (ArrayList<Customer>) repository.getAll();
	}

	@Override
	public Customer create(Entity entity) {
		
		Customer customer = (Customer) entity;
		// Hashing password
		String hashedPassword = HashingService.deterministicHash(customer.getCPRHash());
		
		customer.setCPRHash(hashedPassword);

		if(repository.add(customer) > 0)
			return customer;
		
		return null;
	}
	
	public ArrayList<Customer> search(String searchKey) throws Exception{
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		ResultSet result = ((CustomerRepository)repository).search(searchKey);
		
		while(result.next()) {
			Customer customer = new Customer();
			customer.mapFromResultSet(result);
			customers.add(customer);
		}
		
		return customers;
	}

	@Override
	public boolean update(Entity entity) {
		Customer customer = (Customer)entity;
		
		return repository.update(customer);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Customer) entity);
	}

	public Customer findByCPR(String cpr) {
		
		cpr = HashingService.deterministicHash(cpr);
		
		ArrayList<Customer> c = (ArrayList<Customer>) repository.getByCondition("CPRHash", "=", cpr);
		if(c.size()==0)
			return null;
		
		return c.get(0);
		
	}

}
