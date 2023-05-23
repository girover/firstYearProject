package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.User;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
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
