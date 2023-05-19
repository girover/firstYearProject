package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import app.FormData;
import database.entities.Car;
import database.entities.Customer;
import database.entities.Entity;
import database.repositories.CustomerRepository;


/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with customers.
 * such as retrieving all customers, retrieving a set of customers, 
 * retrieving a single customer by ID, updating a customer, 
 * and deleting a customer.
 * 
 * @version 1.0
 * 
 * @author Rasmus Lysgaard Villadsen
 * 		 - <b style="color:red"> mrmaklie@gmail.com</b>
 * 		 - <a href="https://github.com/MrMaklie">Github</a>
 */
public class CustomerService extends BaseResourceService {

	public CustomerService() {
		repository = new CustomerRepository();
//		entityClass = Customer.class;
	}

	@Override
	public Customer find(int id) {
		Customer customer = (Customer) repository.find(id);
		return customer;
	}

	@Override
	public ArrayList<Customer> getAll() {
		
		return (ArrayList<Customer>) repository.getAll();
	}

	@Override
	public Customer create(FormData data) {
		
		Customer customer = new Customer();
		// Hashing password
		String hashedPassword = HashingService.deterministicHash((String)data.input("cpr"));
		
		customer.setCPRHash(hashedPassword);
		customer.setFirstName((String)data.input("firstName"));
		customer.setLastName((String)data.input("lastName"));
		customer.setPhone((String)data.input("phone"));
		customer.setEmail((String)data.input("email"));
		customer.setAddress((String)data.input("address"));
		customer.setCity((String)data.input("city"));
		customer.setZipCode((String)data.input("zipCode"));
//		customer.setCreditworthiness((String)data.input("creditworthiness"));

		if(repository.add(customer) > 0)
			return customer;
		
		return null;
	}
	
	public ArrayList<Customer> search(String searchKey) throws Exception{
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		ResultSet result = ((CustomerRepository)repository).search(searchKey);
		
		if(result.next()) {
			Customer customer = new Customer();
			customer.mapFromResultSet(result);
			customers.add(customer);
			
		}
		
		return customers;
	}

	@Override
	public boolean update(Entity entity) {
		Customer customer = (Customer)entity;
		// Expecting hashing CPR number here
		// .....   code ....
//		customer.setCPRHash(null);
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
