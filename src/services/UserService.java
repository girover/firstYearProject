package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.FormData;
import database.entities.Car;
import database.entities.Employee;
import database.entities.Entity;
import database.entities.User;
import database.repositories.EmployeeRepository;
import database.repositories.UserRepository;

/**
 * This class is a part of Service Layer (Business Logic Layer). 
 *
 * This class provides a set of methods that can be used to deal with users.
 * such as retrieving all users, retrieving a set of users, 
 * retrieving a single user by ID, updating a user, 
 * and deleting a user.
 * 
 * @version 1.0
 * 
 * @author Majed Hussein Farhan
 * 		 - <b style="color:red">girover.mhf@gmail.com</b>
 *       - <a href="https://github.com/girover">Github</a>
 */
public class UserService extends BaseResourceService {

	public UserService() {
		repository = new UserRepository();
//		entityClass = User.class;
	}

	@Override
	public User find(int id) {
		User user = (User) repository.find(id);
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		
		ArrayList<User> users = new ArrayList<>();
		
		ResultSet result = repository.getAll();
		
		try {
			while (result.next()) {
				User user = new User();
				if (user.makeFromResultSet(result))
					users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User create(FormData data) {
		
		User user = new User();
		
		user.setUserName((String)data.input("userName"));
		user.setPassword((String)data.input("password"));
		user.setEmployeeId((int)data.input("employeeId"));

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

	public Employee getAsEmployee(User user) throws SQLException {
		
		EmployeeRepository repo = new EmployeeRepository();
		
		ResultSet result = repo.find(user.getId());
		
		Employee employee = null;
		
		if (result.next()){
			employee = new Employee();
			employee.makeFromResultSet(result);
		}
		
		return employee;
	}
}
