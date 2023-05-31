package entities.factory;

import java.util.ArrayList;

import entities.Car;
import entities.User;
import faker.Faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
 *
 */
public class UserFactory implements EntityFactory {
	
	@Override
	public User make() {
		return Faker.user();
	}

	@Override
	public ArrayList<User> make(int quantity) {
		
		ArrayList<User> users = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			users.add(Faker.user());

		return users;
	}

}
