package services;

import java.sql.SQLException;
import java.util.ArrayList;

import database.repositories.EmployeeRepository;
import database.repositories.UserRepository;
import entities.Employee;
import entities.Entity;
import entities.User;

/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with users.
 * such as retrieving all users, retrieving a set of users, 
 * retrieving a single user by ID, updating a user, 
 * and deleting a user.
 * 
 * @author Shahana Thirukumar
 * 		- <b style="color:red">shahana2@hotmail.dk</b>
 *      - <a href="https://github.com/ShahanaT2000">Github Profile</a>
 *      
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 *       
 */
public class UserService extends BaseResourceService {

	public UserService() {
		repository = new UserRepository();
	}

	@Override
	public User find(Object id) {
		User user = (User) repository.find(id);
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		
		return (ArrayList<User>) repository.getAll();
	}

	@Override
	public User create(Entity entity) {
		
		User user = (User) entity;
		
		user.setPassword(HashingService.secureHash(user.getPassword()));
		
		repository.add(user);

		return user;
	}

	@Override
	public boolean update(Entity entity) {
		return repository.update((User) entity);
	}

	@Override
	public boolean delete(Entity entity) {
		return repository.delete((User) entity);
	}
	
	public User findByUsername(String username) {
		ArrayList<User> users = (ArrayList<User>) repository.getByCondition("userName", "=", username);
		
		if(users.size() > 0)
			return users.get(0);
		
		return null;
	}

	public Employee getAsEmployee(User user) throws SQLException {
		
		EmployeeRepository repo = new EmployeeRepository();
		
		
		Employee employee = (Employee) repo.find(user.getId());;
		
		
		return employee;
	}
}
