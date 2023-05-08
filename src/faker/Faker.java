package faker;

import database.entities.Employee;
import database.entities.User;

public class Faker extends Provider {

	/**
	 * generating a fake male User
	 * @return User
	 */
	public static User userMale() {
		String name = Person.nameMale();
		return user(name);
	}
	
	/**
	 * generating a fake female User
	 * @return User
	 */
	public static User userFemale() {
		String name = Person.nameFemale();
		return user(name);
	}
	
	private static User user(String name) {
		
		User user = new User();
		
		String password = Password.digitPassword(getRandomInteger(4, 8));
		user.setUserName(name);
		user.setPassword(password);
		user.setEmployeeId(0);
		
		return user;
	}
	
	public static Employee employeeMale() {
		
		Employee employee = new Employee();
		
		employee.setAddress(Address.addressApartment());
		employee.setPhone(Phone.danishPhoneNumber());
		
		return employee;
	}
	public static Employee employeeFemale() {
		
		return new Employee();
	}
}
