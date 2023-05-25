package database.entities.factory;

import java.util.ArrayList;

import database.entities.Car;
import database.entities.City;
import faker.Faker;

/**
 * @author Majed Hussein Farhan 
 * 		- <b style="color:red">girover.mhf@gmail.com</b>
 *      - <a href="https://github.com/girover">Github Profile</a>
 *
 */
public class CityFactory implements EntityFactory {
	
	@Override
	public City make() {
		return Faker.city();
	}

	@Override
	public ArrayList<City> make(int quantity) {
		
		ArrayList<City> cars = new ArrayList<>();
		
		for(int i=0; i<quantity; i++)
			cars.add(Faker.city());

		return cars;
	}

}
