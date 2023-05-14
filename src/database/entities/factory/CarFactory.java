package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class CarFactory implements EntityFactory {

	@Override
	public Car make() {
		return Faker.car();
	}

	@Override
	public ArrayList<Car> make(int quantity) {
		ArrayList<Car> cars = new ArrayList<>();
		for(int i=0; i<quantity; i++)
			cars.add(Faker.car());

		return cars;
	}

}
