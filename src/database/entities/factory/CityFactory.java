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
		
		ArrayList<City> cities = new ArrayList<>();
		
		for(int i=0; i<quantity; i++) {
			City city = Faker.city();
			while(contains(cities, city)) {
				city = Faker.city();
			}
			cities.add(city);
		}

		return cities;
	}
	
	private boolean contains(ArrayList<City> cities, City city) {
		for (City c : cities) {
			if(c.getZipCode().equals(city.getZipCode()))
				return true;
		}
		
		return false;
	}

}
