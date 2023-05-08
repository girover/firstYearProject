package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import app.FormData;

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
	}

	@Override
	public Customer find(int id) {
		Customer customer = (Customer) repository.find(id);
		return customer;
	}

	@Override
	public ArrayList<Customer> getAll() {
		
		ArrayList<Customer> customers = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				Customer customer = new Customer();
				if (customer.makeFromResultSet(result))
					customers.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public ArrayList<Customer> getPage(int page) {

		
		ArrayList<Customer> customers = new ArrayList<>();
		
		ResultSet result = repository.getPage(page);
		try {
			while (result.next()) {
				Customer customer = new Customer();
				if (customer.makeFromResultSet(result))
					customers.add(customer);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
	}

	@Override
	public Customer create(FormData data) {
		
		Customer customer = new Customer();
		
		customer.setName((String)data.input("name"));
		customer.setLastName((String)data.input("lastName"));
		customer.setPhone((String)data.input("phone"));
		customer.setEmail((String)data.input("email"));
		customer.setAddress((String)data.input("address"));
		customer.setCity((String)data.input("city"));
		customer.setZipCode((String)data.input("zipCode"));
		customer.setCreditworthiness((String)data.input("creditworthiness"));

		repository.add(customer);

		return customer;
	}

	@Override
	public Customer update(Entity entity) {
		if (repository.update((Customer) entity))
			return ((Customer) entity);
		
		return null;
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((Customer) entity);
	}

}