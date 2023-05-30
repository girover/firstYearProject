package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import faker.Faker;

/**
 * @author Rasmus Kortsen
 *         - Email: Rasmus.kortsen1@gmail.com
 *         - Github: https://github.com/rasm685p
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
